package com.mpfleet.services.accounts;

import com.mpfleet.accounts.models.TransactionType;
import com.mpfleet.accounts.repositories.TransactionTypeRepository;
import com.mpfleet.accounts.services.TransactionTypeService;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTypeServiceTest {

    @Mock
    private TransactionTypeRepository transactionTypeRepository;

    @InjectMocks
    private TransactionTypeService transactionTypeService;

    @Test
    public void testFindAll() {
        List<TransactionType> transactionTypes = Arrays.asList(
                new TransactionType(),
                new TransactionType()
        );

        when(transactionTypeRepository.findAll()).thenReturn(transactionTypes);

        List<TransactionType> result = transactionTypeService.findAll();

        assertEquals(2, result.size());
        Mockito.verify(transactionTypeRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {

        Optional<TransactionType> transactionType = Optional.of(new TransactionType());
        when(transactionTypeRepository.findById(1L)).thenReturn(transactionType);

        Optional<TransactionType> result = transactionTypeService.findById(1L);

        assertTrue(result.isPresent());
        Mockito.verify(transactionTypeRepository, times(1)).findById(1L);
    }

    @Test
    public void testDelete() {
        transactionTypeService.delete(1L);
        Mockito.verify(transactionTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        TransactionType transactionType = new TransactionType();
        transactionTypeService.save(transactionType);
        Mockito.verify(transactionTypeRepository).save(transactionType);
    }

}
