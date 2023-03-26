package com.mpfleet.mockito.accounts;

import com.mpfleet.tickets.models.TicketStatus;
import com.mpfleet.tickets.repositories.TicketStatusRepository;
import com.mpfleet.tickets.services.TicketStatusService;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionStatusServiceTest {

    @Mock
    private TicketStatusRepository ticketStatusRepository;

    @InjectMocks
    private TicketStatusService ticketStatusService;

    @Test
    public void testFindAll() {
        List<TicketStatus> ticketStatuses = Arrays.asList(new TicketStatus(), new TicketStatus());
        when(ticketStatusRepository.findAll()).thenReturn(ticketStatuses);

        List<TicketStatus> result = ticketStatusService.findAll();

        assertEquals(ticketStatuses, result);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        TicketStatus ticketStatus = new TicketStatus();
        when(ticketStatusRepository.findById(id)).thenReturn(Optional.of(ticketStatus));

        Optional<TicketStatus> result = ticketStatusService.findById(id);

        assertEquals(Optional.of(ticketStatus), result); // passed
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        ticketStatusService.delete(id);
        Mockito.verify(ticketStatusRepository).deleteById(id);
    }

    @Test
    public void testSave() {
        TicketStatus ticketStatus = new TicketStatus();
        ticketStatusService.save(ticketStatus);
        Mockito.verify(ticketStatusRepository).save(ticketStatus);
    }
}
