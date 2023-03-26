package com.mpfleet.mockito.hr;


import com.mpfleet.hr.models.Employee;
import com.mpfleet.hr.models.EmployeeType;
import com.mpfleet.hr.models.JobTitle;
import com.mpfleet.hr.repositories.EmployeeRepository;
import com.mpfleet.hr.services.EmployeeService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    private final EmployeeType employeeType1 = new EmployeeType();
    private final EmployeeType employeeType2 = new EmployeeType();
    private final JobTitle jobTitle1 = new JobTitle();
    private final JobTitle jobTitle2 = new JobTitle();
    @Test
    public void testFindAll() {
        List<Employee> employees = Arrays.asList(
                new Employee("none","test", LocalDate.now(), employeeType1, jobTitle1),
                new Employee("none", "test1", LocalDate.now(), employeeType2, jobTitle2)
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findAll();

        Mockito.verify(employeeRepository).findAll();
        assertEquals(employees, result);
        assertEquals(2,result.size());
        assertEquals("test", result.get(0).getUsername());
        assertEquals("test1", result.get(1).getUsername());

    }

    @Test
    public void testFindPage() {
        List<Employee> employees = Arrays.asList(
                new Employee("none","test", LocalDate.now(), employeeType1, jobTitle1),
                new Employee("none", "test1", LocalDate.now(), employeeType2, jobTitle2)
        );

        Page<Employee> page = new PageImpl<>(employees);
        when(employeeRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        Page<Employee> result = employeeService.findPage(1);

        Mockito.verify(employeeRepository).findAll(PageRequest.of(0, 10));
        assertEquals(page, result);
    }

    @Test
    public void testFindById() {
        Employee employee = new Employee("none","test", LocalDate.now(), employeeType1, jobTitle1);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(1L);

        Mockito.verify(employeeRepository).findById(1L);
        assertEquals(employee, result);
    }

    @Test
    public void testDelete() {
        employeeService.delete(1L);
        Mockito.verify(employeeRepository).deleteById(1L);
    }

    @Test
    public void testSave() {
        Employee employee = new Employee("none","test", LocalDate.now(), employeeType1, jobTitle1);
        when(employeeRepository.save(employee)).thenReturn(employee);

        employeeService.save(employee);

        Mockito.verify(employeeRepository).save(employee);
    }
}
