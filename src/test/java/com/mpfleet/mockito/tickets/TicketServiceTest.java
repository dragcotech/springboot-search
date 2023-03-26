package com.mpfleet.mockito.tickets;

import com.mpfleet.tickets.models.Ticket;
import com.mpfleet.tickets.repositories.TicketRepository;
import com.mpfleet.tickets.services.TicketService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testFindAll() {
        List<Ticket> tickets = Arrays.asList(
                new Ticket("test","test", LocalDate.now(),LocalDate.now(),"test",null, null,null),
                new Ticket("test2", "test2",LocalDate.now(),LocalDate.now(), "test2",null,null,null)
        );

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.findAll();

        Mockito.verify(ticketRepository).findAll();
        Assert.assertEquals(tickets, result);
        Assert.assertEquals(2,result.size());
        Assert.assertEquals("test", result.get(0).getSubject());
        Assert.assertEquals("test2", result.get(1).getSubject());
    }

    @Test
    public void testFindPage() {
        List<Ticket> tickets = Arrays.asList(
                new Ticket("test","test", LocalDate.now(),LocalDate.now(),"test",null, null,null),
                new Ticket("test2", "test2",LocalDate.now(),LocalDate.now(), "test2",null,null,null),
                new Ticket("test3", "test3",LocalDate.now(),LocalDate.now(), "test3",null,null,null)
        );

        Page<Ticket> page = new PageImpl<>(tickets);
        when(ticketRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        Page<Ticket> result = ticketService.findPage(1);

        Mockito.verify(ticketRepository).findAll(PageRequest.of(0, 10));
        Assert.assertEquals(page, result);
    }

    @Test
    public void testFindById() {
        Ticket ticket = new Ticket("test","test", LocalDate.now(),LocalDate.now(),"test",null, null,null);
        ticket.setId(1L);
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.findById(1L);

        Mockito.verify(ticketRepository).findById(1L);
        Assert.assertEquals(ticket, result);
    }

    @Test
    public void testDelete() {
        doNothing().when(ticketRepository).deleteById(anyLong());
        ticketService.delete(1L);
        Mockito.verify(ticketRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testSave() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        ticketService.save(ticket);

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }
}
