package com.mpfleet.mockito.fleet;

import com.mpfleet.fleet.models.VehicleMake;
import com.mpfleet.fleet.repositories.VehicleMakeRepository;
import com.mpfleet.fleet.services.VehicleMakeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleMakeServiceTest {

    @Mock
    private VehicleMakeRepository vehicleMakeRepository;

    @InjectMocks
    private VehicleMakeService vehicleMakeService;

    @Test
    public void testFindAll() {
        List<VehicleMake> expectedMakes = Arrays.asList(
                new VehicleMake(),
                new VehicleMake(),
                new VehicleMake()
        );
        expectedMakes.get(0).setDescription("Audi");
        expectedMakes.get(1).setDescription("Mercedes");
        expectedMakes.get(2).setDescription("Bmw");

        when(vehicleMakeRepository.findAll()).thenReturn(expectedMakes);

        List<VehicleMake> actualMakes = vehicleMakeService.findAll();

        Assert.assertEquals(expectedMakes, actualMakes);
        Mockito.verify(vehicleMakeRepository).findAll();
    }

    @Test
    public void testFindById() {
        VehicleMake expectedMake = new VehicleMake();
        expectedMake.setId(1L);
        expectedMake.setDescription("Audi");

        when(vehicleMakeRepository.findById(1L)).thenReturn(Optional.of(expectedMake));

        Optional<VehicleMake> actualMake = vehicleMakeService.findById(1L);

        Assert.assertEquals(Optional.of(expectedMake), actualMake);
        Mockito.verify(vehicleMakeRepository).findById(1L);
    }

    @Test
    public void testDelete() {
        doNothing().when(vehicleMakeRepository).deleteById(1L);
        vehicleMakeService.delete(1L);
        Mockito.verify(vehicleMakeRepository).deleteById(1L);
    }

    @Test
    public void testSave() {
        VehicleMake makeToSave = new VehicleMake();
        makeToSave.setDescription("Audi");
        when(vehicleMakeRepository.save(makeToSave)).thenReturn(makeToSave);
        vehicleMakeService.save(makeToSave);
        Mockito.verify(vehicleMakeRepository).save(makeToSave);
    }
}
