package com.mpfleet.mockito.hr;

import com.mpfleet.hr.models.EmployeeType;
import com.mpfleet.hr.repositories.EmployeeTypeRepository;
import com.mpfleet.hr.services.EmployeeTypeService;
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
public class EmployeeTypeServiceTest {

    @Mock
    private EmployeeTypeRepository employeeTypeRepository;

    @InjectMocks
    private EmployeeTypeService employeeTypeService;

    @Test
    public void testFindAll() {
        List<EmployeeType> employeeTypes = Arrays.asList(
                new EmployeeType(),
                new EmployeeType()
        );

        when(employeeTypeRepository.findAll()).thenReturn(employeeTypes);

        List<EmployeeType> actualEmployeeTypes = employeeTypeService.findAll();

        assertEquals(2, actualEmployeeTypes.size());
        assertEquals(employeeTypes, actualEmployeeTypes);
    }

    @Test
    public void testFindById() {
        EmployeeType employeeType = new EmployeeType();

        when(employeeTypeRepository.findById(1L)).thenReturn(Optional.of(employeeType));

        Optional<EmployeeType> actualEmployeeType = employeeTypeService.findById(1L);

        assertTrue(actualEmployeeType.isPresent());
        assertEquals(Optional.of(employeeType), actualEmployeeType);
    }

    @Test
    public void testFindByIdNotFound() {
        when(employeeTypeRepository.findById(any())).thenReturn(Optional.empty());

        Optional<EmployeeType> actualEmployeeType = employeeTypeService.findById(1L);

        assertFalse(actualEmployeeType.isPresent());
    }

    @Test
    public void testDelete() {
        employeeTypeService.delete(1L);
        Mockito.verify(employeeTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        EmployeeType employeeType = new EmployeeType();
        employeeTypeService.save(employeeType);
        Mockito.verify(employeeTypeRepository, times(1)).save(employeeType);
    }
}
