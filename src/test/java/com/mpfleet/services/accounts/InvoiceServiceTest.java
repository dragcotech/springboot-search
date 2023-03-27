package com.mpfleet.services.accounts;

import com.mpfleet.accounts.models.Invoice;
import com.mpfleet.accounts.models.InvoiceStatus;
import com.mpfleet.accounts.repositories.InvoiceRepository;
import com.mpfleet.accounts.services.InvoiceService;
import com.mpfleet.admin.models.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    private final Client client1 = new Client("Acme Corporation","123 Main St","555-123-4567","555-987-6543","www.acmecorp.com","info@acmecorp.com",null,null,"Acme Corporation is a global leader in industrial supplies and manufacturing.");
    private final Client client2 = new Client("Stellar Designs","456 Park Ave, Suite 100","555-123-4567","555-987-6543","www.stellardesigns.com","contact@stellardesigns.com",null,null,"Stellar Designs is a high-end fashion and accessory retailer with a focus on sustainable materials and ethical production.");

    private final InvoiceStatus invoiceStatus1 = new InvoiceStatus();
    private final InvoiceStatus invoiceStatus2 = new InvoiceStatus();

    @Test
    public void testFindAll() {
        List<Invoice> expectedInvoices = Arrays.asList(
                new Invoice(LocalDate.now(),"Payment received on time.",invoiceStatus1,client1),
                new Invoice(LocalDate.now(),"Please pay the amount due as soon as possible.", invoiceStatus2, client2)
        );

        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        List<Invoice> actualInvoices = invoiceService.findAll();

        Assert.assertEquals(expectedInvoices, actualInvoices);
        verify(invoiceRepository).findAll();
    }

    @Test
    public void testFindById() {
        Invoice invoice = new Invoice(LocalDate.now(),"Payment received on time.",invoiceStatus1,client1);
        when(invoiceRepository.findById(invoice.getId())).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.findById(invoice.getId());

        Assert.assertEquals(invoice, result);
        verify(invoiceRepository).findById(invoice.getId());
    }

    @Test
    public void testSave() {
        Invoice invoice =  new Invoice(LocalDate.now(),"Payment received on time.",invoiceStatus1,client1);
        invoiceService.save(invoice);
        Mockito.verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    public void testDelete() {
        doNothing().when(invoiceRepository).deleteById(1L);
        invoiceService.delete(1L);
        verify(invoiceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByKeyword() {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add( new Invoice(LocalDate.now(),"Payment received on time.",invoiceStatus1,client1));

        when(invoiceRepository.findByKeyword("invoice")).thenReturn(invoices);

        List<Invoice> result = invoiceService.findByKeyword("invoice");

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Payment received on time.", result.get(0).getRemarks());
        Assert.assertEquals("Acme Corporation", result.get(0).getClient().getName()); // passed
    }
}
