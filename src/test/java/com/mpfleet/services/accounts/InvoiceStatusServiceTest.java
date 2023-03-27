package com.mpfleet.services.accounts;

import com.mpfleet.accounts.models.InvoiceStatus;
import com.mpfleet.accounts.repositories.InvoiceStatusRepository;
import com.mpfleet.accounts.services.InvoiceStatusService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceStatusServiceTest {

    @Mock
    private InvoiceStatusRepository invoiceStatusRepository;

    @InjectMocks
    private InvoiceStatusService invoiceStatusService;

    @Test
    public void testFindAll() {
        List<InvoiceStatus> invoiceStatuses = Arrays.asList(
                new InvoiceStatus(), new InvoiceStatus(), new InvoiceStatus()
        );

        when(invoiceStatusRepository.findAll()).thenReturn(invoiceStatuses);

        List<InvoiceStatus> result = invoiceStatusService.findAll();

        assertEquals(invoiceStatuses, result);
        Assert.assertEquals(3,result.size());
        Mockito.verify(invoiceStatusRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        long id = 1L;
        Optional<InvoiceStatus> invoiceStatus = Optional.of(new InvoiceStatus());
        when(invoiceStatusRepository.findById(id)).thenReturn(invoiceStatus);

        Optional<InvoiceStatus> result = invoiceStatusService.findById(id);

        assertEquals(invoiceStatus, result);
        Mockito.verify(invoiceStatusRepository, times(1)).findById(id);
    }

    @Test
    public void testDelete() {
        long id = 1L;
        invoiceStatusService.delete(id);
        Mockito.verify(invoiceStatusRepository, times(1)).deleteById(id);
    }

    @Test
    public void testSave() {
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatusService.save(invoiceStatus);
        Mockito.verify(invoiceStatusRepository, times(1)).save(invoiceStatus);
    }
}
