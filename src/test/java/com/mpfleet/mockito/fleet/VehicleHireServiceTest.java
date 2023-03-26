package com.mpfleet.mockito.fleet;

import com.mpfleet.fleet.models.VehicleHire;
import com.mpfleet.fleet.repositories.VehicleHireRepository;
import com.mpfleet.fleet.services.VehicleHireService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(MockitoJUnitRunner.class)
public class VehicleHireServiceTest {

    @Mock
    private VehicleHireRepository vehicleHireRepository;

    @InjectMocks
    private VehicleHireService vehicleHireService;

    @Test
    public void testFindAll() {
        List<VehicleHire> vehicleHires = Arrays.asList(
                new VehicleHire(),
                new VehicleHire()
        );

        Mockito.when(vehicleHireRepository.findAll()).thenReturn(vehicleHires);

        List<VehicleHire> result = vehicleHireService.findAll();

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testFindPage() {
        List<VehicleHire> vehicleHires = Arrays.asList(
                new VehicleHire(),
                new VehicleHire()
        );

        Page<VehicleHire> page = new PageImpl<>(vehicleHires);

        Mockito.when(vehicleHireRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        Page<VehicleHire> result = vehicleHireService.findPage(1);

        Assert.assertEquals(2, result.getTotalElements());
    }

    @Test
    public void testFindById() {
        VehicleHire vehicleHire = new VehicleHire();
        vehicleHire.setId(1L);

        Mockito.when(vehicleHireRepository.findById(1L)).thenReturn(Optional.of(vehicleHire));

        VehicleHire result = vehicleHireService.findById(1L);

        Assert.assertNotNull(result);
        Assert.assertEquals(1L, result.getId().longValue());
    }

    @Test
    public void testDelete() {
        vehicleHireService.delete(1L);
        Mockito.verify(vehicleHireRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        VehicleHire vehicleHire = new VehicleHire();
        vehicleHireService.save(vehicleHire);
        Mockito.verify(vehicleHireRepository, Mockito.times(1)).save(vehicleHire);
    }

    @Test
    public void testFindByKeyword() {
        List<VehicleHire> vehicleHires = Arrays.asList(
                new VehicleHire(),
                new VehicleHire()
        );

        Mockito.when(vehicleHireRepository.findByKeyword(Mockito.anyString())).thenReturn(vehicleHires);

        List<VehicleHire> result = vehicleHireService.findByKeyword("test");

        Assert.assertEquals(2, result.size());
    }
}
