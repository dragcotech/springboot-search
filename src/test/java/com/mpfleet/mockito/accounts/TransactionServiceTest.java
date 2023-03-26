package com.mpfleet.mockito.accounts;

import com.mpfleet.accounts.models.Transaction;
import com.mpfleet.accounts.repositories.TransactionRepository;
import com.mpfleet.accounts.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testFindAll() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(),
                new Transaction()
        );

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
    }

    @Test
    public void testSave() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        transactionService.save(transaction);

        Mockito.verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testDelete() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.delete(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindPage(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(),
                new Transaction(),
                new Transaction()
        );

        Page<Transaction> page = new PageImpl<>(transactions);

        when(transactionRepository.findAll(any(Pageable.class))).thenReturn(page);
        Page<Transaction> result = transactionService.findPage(1);

        ArgumentCaptor<Pageable> pageableArgument = ArgumentCaptor.forClass(Pageable.class);
        verify(transactionRepository).findAll(pageableArgument.capture());
        assertEquals(0, pageableArgument.getValue().getOffset());
        assertEquals(10, pageableArgument.getValue().getPageSize());

        assertEquals(3, result.getContent().size());
    }
}
