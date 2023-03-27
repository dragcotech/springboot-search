package com.mpfleet.services.hr;


import com.mpfleet.hr.models.EmployeeStatus;
import com.mpfleet.hr.repositories.EmployeeStatusRepository;
import com.mpfleet.hr.services.EmployeeStatusService;
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
public class EmployeeStatusServiceTest {

    @Mock
    private EmployeeStatusRepository employeeStatusRepository;

    @InjectMocks
    private EmployeeStatusService employeeStatusService;

    @Test
    public void testFindAll() {
        List<EmployeeStatus> employeeStatuses = Arrays.asList(
                new EmployeeStatus(),
                new EmployeeStatus()
        );

        when(employeeStatusRepository.findAll()).thenReturn(employeeStatuses);

        List<EmployeeStatus> actualEmployeeStatuses = employeeStatusService.findAll();

        assertEquals(2, actualEmployeeStatuses.size());
        assertEquals(employeeStatuses, actualEmployeeStatuses);
    }

    @Test
    public void testFindById() {
        EmployeeStatus employeeStatus = new EmployeeStatus();

        when(employeeStatusRepository.findById(1L)).thenReturn(Optional.of(employeeStatus));

        Optional<EmployeeStatus> actualEmployeeStatus = employeeStatusService.findById(1L);

        assertTrue(actualEmployeeStatus.isPresent());
        assertEquals(Optional.of(employeeStatus), actualEmployeeStatus);
    }

    @Test
    public void testFindByIdNotFound() {
        when(employeeStatusRepository.findById(any())).thenReturn(Optional.empty());

        Optional<EmployeeStatus> actualEmployeeStatus = employeeStatusService.findById(1L);

        assertFalse(actualEmployeeStatus.isPresent());
    }

    @Test
    public void testDelete() {
        employeeStatusService.delete(1L);
        Mockito.verify(employeeStatusRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        EmployeeStatus employeeStatus = new EmployeeStatus();
        employeeStatusService.save(employeeStatus);
        Mockito.verify(employeeStatusRepository, times(1)).save(employeeStatus);
    }
}
