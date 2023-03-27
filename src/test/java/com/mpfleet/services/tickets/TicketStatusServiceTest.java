package com.mpfleet.services.tickets;

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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketStatusServiceTest {

    @Mock
    private TicketStatusRepository ticketStatusRepository;

    @InjectMocks
    private TicketStatusService ticketStatusService;

    @Test
    public void testFindAll() {
        List<TicketStatus> ticketStatuses = Arrays.asList(
                new TicketStatus(),
                new TicketStatus()
        );

        when(ticketStatusRepository.findAll()).thenReturn(ticketStatuses);

        List<TicketStatus> actualEmployeeStatuses = ticketStatusService.findAll();

        assertEquals(2, actualEmployeeStatuses.size());
        assertEquals(ticketStatuses, actualEmployeeStatuses);
    }

    @Test
    public void testFindById() {
        TicketStatus ticketStatus = new TicketStatus();

        when(ticketStatusRepository.findById(1L)).thenReturn(Optional.of(ticketStatus));

        Optional<TicketStatus> actualTicketStatus = ticketStatusService.findById(1L);

        assertTrue(actualTicketStatus.isPresent());
        assertEquals(Optional.of(ticketStatus), actualTicketStatus);
    }

    @Test
    public void testFindByIdNotFound() {
        when(ticketStatusRepository.findById(any())).thenReturn(Optional.empty());

        Optional<TicketStatus> actualTicketStatus = ticketStatusService.findById(1L);

        assertFalse(actualTicketStatus.isPresent());
    }

    @Test
    public void testDelete() {
        ticketStatusService.delete(1L);
        Mockito.verify(ticketStatusRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        TicketStatus ticketStatus = new TicketStatus();
        ticketStatusService.save(ticketStatus);
        Mockito.verify(ticketStatusRepository, times(1)).save(ticketStatus);
    }
}
