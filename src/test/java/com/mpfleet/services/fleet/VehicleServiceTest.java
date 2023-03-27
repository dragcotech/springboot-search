package com.mpfleet.services.fleet;

import com.mpfleet.fleet.models.Vehicle;
import com.mpfleet.fleet.repositories.VehicleRepository;
import com.mpfleet.fleet.services.VehicleService;
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

import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void testFindAll() {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(),
                new Vehicle()
        );

        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<Vehicle> result = vehicleService.findAll();

        Mockito.verify(vehicleRepository, Mockito.times(1)).findAll();
        Assert.assertEquals(vehicles, result);
    }

    @Test
    public void testFindPage() {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(),
                new Vehicle()
        );

        Page<Vehicle> page = new PageImpl<>(vehicles);
        Mockito.when(vehicleRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        Page<Vehicle> result = vehicleService.findPage(1);

        Mockito.verify(vehicleRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
        Assert.assertEquals(page, result);
    }

    @Test
    public void testFindById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        Mockito.when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        Vehicle result = vehicleService.findById(1L);

        Mockito.verify(vehicleRepository, Mockito.times(1)).findById(1L);
        Assert.assertEquals(vehicle, result);
    }

    @Test
    public void testDelete() {
        doNothing().when(vehicleRepository).deleteById(1L);
        vehicleService.delete(1L);
        Mockito.verify(vehicleRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        Vehicle vehicle = new Vehicle();
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        vehicleService.save(vehicle);
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(vehicle);
    }

    @Test
    public void testFindByKeyword() {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(),
                new Vehicle()
        );

        Mockito.when(vehicleRepository.findByKeyword("keyword")).thenReturn(vehicles);

        List<Vehicle> result = vehicleService.findByKeyword("keyword");

        Mockito.verify(vehicleRepository, Mockito.times(1)).findByKeyword("keyword");
        Assert.assertEquals(vehicles, result);
    }
}
