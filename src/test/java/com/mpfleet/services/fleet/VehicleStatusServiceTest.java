package com.mpfleet.services.fleet;

import com.mpfleet.fleet.models.VehicleMake;
import com.mpfleet.fleet.repositories.VehicleMakeRepository;
import com.mpfleet.fleet.services.VehicleMakeService;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleStatusServiceTest {
    @Mock
    private VehicleMakeRepository vehicleMakeRepository;

    @InjectMocks
    private VehicleMakeService vehicleMakeService;

    @Test
    public void testFindAll() {
        when(vehicleMakeRepository.findAll()).thenReturn(Arrays.asList(new VehicleMake(), new VehicleMake()));

        List<VehicleMake> vehicleMakes = vehicleMakeService.findAll();

        assertNotNull(vehicleMakes);
        assertEquals(2, vehicleMakes.size());
    }

    @Test
    public void testFindById() {
        when(vehicleMakeRepository.findById(1L)).thenReturn(Optional.of(new VehicleMake()));
        Optional<VehicleMake> vehicleMake = vehicleMakeService.findById(1L);
        assertTrue(vehicleMake.isPresent());
    }

    @Test
    public void testDelete() {
        vehicleMakeService.delete(1L);
        Mockito.verify(vehicleMakeRepository).deleteById(1L);
    }

    @Test
    public void testSave() {
        VehicleMake vehicleMake = new VehicleMake();
        vehicleMakeService.save(vehicleMake);
        Mockito.verify(vehicleMakeRepository).save(vehicleMake);
    }
}
