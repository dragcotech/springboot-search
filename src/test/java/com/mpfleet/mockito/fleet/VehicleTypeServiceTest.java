package com.mpfleet.mockito.fleet;

import com.mpfleet.fleet.models.VehicleType;
import com.mpfleet.fleet.repositories.VehicleTypeRepository;
import com.mpfleet.fleet.services.VehicleTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTypeServiceTest {

    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private VehicleTypeService vehicleTypeService;

    @Test
    public void testFindAll() {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        VehicleType vehicleType1 = new VehicleType();
        vehicleType1.setId(1L);
        vehicleType1.setDescription("Car");
        vehicleTypes.add(vehicleType1);

        VehicleType vehicleType2 = new VehicleType();
        vehicleType2.setId(2L);
        vehicleType2.setDescription("Truck");
        vehicleTypes.add(vehicleType2);

        Mockito.when(vehicleTypeRepository.findAll()).thenReturn(vehicleTypes);

        List<VehicleType> result = vehicleTypeService.findAll();

        assertEquals(2, result.size());
        assertEquals("Car", result.get(0).getDescription());
        assertEquals("Truck", result.get(1).getDescription());
    }

    @Test
    public void testFindById() {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(1L);
        vehicleType.setDescription("Car");

        Mockito.when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.of(vehicleType));

        Optional<VehicleType> result = vehicleTypeService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Car", result.get().getDescription());
    }

    @Test
    public void testDelete() {
        vehicleTypeService.delete(1L);
        Mockito.verify(vehicleTypeRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        VehicleType vehicleType = new VehicleType();
        vehicleTypeService.save(vehicleType);
        Mockito.verify(vehicleTypeRepository).save(vehicleType);
    }

}
