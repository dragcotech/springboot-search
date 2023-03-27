package com.mpfleet.controllers.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpfleet.accounts.models.Invoice;
import com.mpfleet.accounts.models.Transaction;
import com.mpfleet.accounts.models.TransactionStatus;
import com.mpfleet.accounts.models.TransactionType;
import com.mpfleet.accounts.services.TransactionService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "$2a$10$Xm9Mhw7W9YpFHdaq089dZ.rjqTk6ID6WZoj7UWtVCyA/61nmieraW", authorities = "ACCOUNTS_ADMIN")
@Transactional
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testGetAllPages() throws Exception {
        mockMvc.perform(get("/accounts/transactions"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currentPage", "totalPages", "totalItems", "transactions"))
                .andExpect(view().name("accounts/transaction/transactions"));
    }

    @Test
    public void testGetOnePage() throws Exception {
        mockMvc.perform(get("/accounts/transactions/page/{pageNumber}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currentPage", "totalPages", "totalItems", "transactions"))
                .andExpect(view().name("accounts/transaction/transactions"));
    }

    @Test
    public void testAddTransaction() throws Exception {
        mockMvc.perform(get("/accounts/addtransaction"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("transactionStatuses", "transactionTypes", "contacts", "suppliers", "clients", "employees"))
                .andExpect(view().name("/accounts/transaction/addTransaction"));
    }

    @Test
    public void testEditTransaction() throws Exception {
        Transaction test = transactionService.findById(2L);

        mockMvc.perform(get("/accounts/transactions/edit/" + test.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("transaction", "transactionStatuses", "transactionTypes", "contacts", "suppliers", "clients", "employees"))
                .andExpect(view().name("/accounts/transaction/editTransaction"));
    }

    @Test
    public void testSaveTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setRemarks("TRANSACTION");
        transaction.setId(11L);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInvoice = mapper.writeValueAsString(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvoice))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accounts/transactions"));

        List<Transaction> transactions = transactionService.findAll();
        assertEquals(7, transactions.size());
    }

}
