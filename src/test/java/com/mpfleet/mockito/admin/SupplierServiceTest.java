package com.mpfleet.mockito.admin;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.State;
import com.mpfleet.admin.models.Supplier;
import com.mpfleet.admin.repositories.SupplierRepository;
import com.mpfleet.admin.services.SupplierService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    private final Country country1 = new Country("USA", "United States of America", "North America", "American", "NA", null);
    private final Country country2 = new Country("CAN", "Canada", "North America", "Canadian", "NA", null);
    private final State state1 = new State("Berlin", "0030", country1 ,"Capital of Germany");
    private final State state2 = new State("Rome", "0006", country2,"Capital of Italy");

    @Test
    public void testFindAll() {
        List<Supplier> expectedSuppliers = Arrays.asList(
                new Supplier("XYZ Inc.","456 Oak St, Somewhere, State 8","+1-555-555-5555","+1-555-555-5555","www.xyzinc.com","info@xyzinc.com",country1,state1,"XYZ Inc. offers computer hardware and software for businesses and individuals."),
                new Supplier("123 Manufacturing","789 Maple Ave, Nowhere, State 5","+1-555-555-5555","+1-555-555-5555","www.123manufacturing.com","info@123manufacturing.com",country2,state2,"123 Manufacturing produces custom parts and components for industrial and commercial equipment.")
        );

        Mockito.when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

        List<Supplier> actualSuppliers = supplierService.findAll();

        Assert.assertEquals(expectedSuppliers, actualSuppliers);
    }

    @Test
    public void testFindPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> page = new PageImpl<>(Collections.emptyList(), pageable, 0);
        Mockito.when(supplierRepository.findAll(pageable)).thenReturn(page);

        Page<Supplier> result = supplierService.findPage(1);

        Assert.assertEquals(page, result);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Supplier supplier = new Supplier();
        Mockito.when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.findById(id);

        Assert.assertEquals(supplier, result);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        supplierService.deleteById(id);
        Mockito.verify(supplierRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testSave() {
        Supplier supplier = new Supplier();
        supplierService.save(supplier);

        Mockito.verify(supplierRepository, Mockito.times(1)).save(supplier);
    }
    @Test
    public void testFindByKeyword() {
        List<Supplier> suppliers = Arrays.asList(
                new Supplier("XYZ Inc.","456 Oak St, Somewhere, State 8","+1-555-555-5555","+1-555-555-5555","www.xyzinc.com","info@xyzinc.com",country1,state1,"XYZ Inc. offers computer hardware and software for businesses and individuals."),
                new Supplier("123 Manufacturing","789 Maple Ave, Nowhere, State 5","+1-555-555-5555","+1-555-555-5555","www.123manufacturing.com","info@123manufacturing.com",country2,state2,"123 Manufacturing produces custom parts and components for industrial and commercial equipment.")
        );


        Mockito.when(supplierRepository.findByKeyword("XYZ Inc.")).thenReturn(suppliers);

        List<Supplier> result = supplierService.findByKeyword("XYZ Inc.");

        Assert.assertEquals(suppliers, result);
        Mockito.verify(supplierRepository).findByKeyword("XYZ Inc.");
    }

}
