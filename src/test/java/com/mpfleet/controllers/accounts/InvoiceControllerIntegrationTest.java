package com.mpfleet.controllers.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpfleet.accounts.models.Invoice;
import com.mpfleet.accounts.models.InvoiceStatus;
import com.mpfleet.accounts.services.InvoiceService;
import com.mpfleet.admin.models.Client;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "admin", password = "$2a$10$Xm9Mhw7W9YpFHdaq089dZ.rjqTk6ID6WZoj7UWtVCyA/61nmieraW", authorities = "ACCOUNTS_ADMIN")
public class InvoiceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testGetAllPages() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/invoices"))
                .andExpect(status().isOk())
                .andExpect(view().name("accounts/invoice/invoices"))
                .andExpect(model().attributeExists("invoices"))
                .andReturn();

        ModelMap modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();
        List<Invoice> invoices = (List<Invoice>) modelMap.get("invoices");

        assertEquals(10, invoices.size());
    }

    @Test
    public void testGetOnePage() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/invoices/page/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("accounts/invoice/invoices"))
                .andExpect(model().attributeExists("invoices"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("totalItems"))
                .andReturn();

        ModelMap modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();
        List<Invoice> invoices = (List<Invoice>) modelMap.get("invoices");

        assertEquals(10,invoices.size());

        int currentPage = (int) modelMap.get("currentPage");
        assertEquals(1, currentPage);

        int totalPages = (int) modelMap.get("totalPages");
        assertEquals(1, totalPages);

        long totalItems = (long) modelMap.get("totalItems");
        assertEquals(10, totalItems);
    }

    @Test
    public void testAddInvoice() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/addinvoice"))
                .andExpect(status().isOk())
                .andExpect(view().name("accounts/invoice/addInvoice"))
                .andExpect(model().attributeExists("invoiceStatuses"))
                .andExpect(model().attributeExists("clients"))
                .andReturn();

        ModelMap modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();

        List<InvoiceStatus> invoiceStatuses = (List<InvoiceStatus>) modelMap.get("invoiceStatuses");
        assertEquals(10, invoiceStatuses.size());

        List<Client> clients = (List<Client>) modelMap.get("clients");
        assertEquals(5, clients.size());
    }

    @Test
    public void testEditInvoice() throws Exception {
        Invoice invoice = invoiceService.findById(1L);

        MvcResult result = mockMvc.perform(get("/accounts/invoice/Edit/" + invoice.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("/accounts/invoice/EditInvoice"))
                .andExpect(model().attributeExists("invoice"))
                .andExpect(model().attributeExists("invoiceStatuses"))
                .andExpect(model().attributeExists("clients"))
                .andReturn();

        ModelMap modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();

        List<InvoiceStatus> invoiceStatuses = (List<InvoiceStatus>) modelMap.get("invoiceStatuses");
        assertEquals(10, invoiceStatuses.size());

        List<Client> clients = (List<Client>) modelMap.get("clients");
        assertEquals(5, clients.size());

        Invoice editedInvoice = (Invoice) modelMap.get("invoice");
        Assert.assertNotNull(editedInvoice);
        assertEquals(invoice.getId(), editedInvoice.getId());

        result = mockMvc.perform(get("/accounts/invoice/Details/" + invoice.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("/accounts/invoice/DetailsInvoice"))
                .andExpect(model().attributeExists("invoice"))
                .andReturn();

        modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();

        Invoice detailedInvoice = (Invoice) modelMap.get("invoice");
        Assert.assertNotNull(detailedInvoice);
        assertEquals(invoice.getId(), detailedInvoice.getId());
    }

    @Test
    public void testSaveInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setRemarks("INV-001");
        invoice.setId(11L);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInvoice = mapper.writeValueAsString(invoice);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvoice))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accounts/invoices"));

        List<Invoice> invoices = invoiceService.findAll();
        assertEquals(11, invoices.size());
    }
}
