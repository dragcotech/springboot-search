package com.mpfleet.services.fleet;

import com.mpfleet.fleet.models.VehicleMovement;
import com.mpfleet.fleet.repositories.VehicleMovementRepository;
import com.mpfleet.fleet.services.VehicleMovementService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleMovementServiceTest {

    @Mock
    private VehicleMovementRepository vehicleMovementRepository;

    @InjectMocks
    private VehicleMovementService vehicleMovementService;

    @Test
    public void testFindAll() {
        List<VehicleMovement> expectedVehicleMovements = Arrays.asList(
                new VehicleMovement(),
                new VehicleMovement()
        );
        expectedVehicleMovements.get(0).setId(1L);
        expectedVehicleMovements.get(1).setId(2L);

        when(vehicleMovementRepository.findAll()).thenReturn(expectedVehicleMovements);

        List<VehicleMovement> actualVehicleMovements = vehicleMovementService.findAll();

        assertEquals(expectedVehicleMovements, actualVehicleMovements);
    }

    @Test
    public void testFindPage() {
        List<VehicleMovement> expectedVehicleMovements = Arrays.asList(
                new VehicleMovement(),
                new VehicleMovement()
        );
        expectedVehicleMovements.get(0).setId(1L);
        expectedVehicleMovements.get(1).setId(2L);

        Page<VehicleMovement> expectedPage = new PageImpl<>(expectedVehicleMovements);
        when(vehicleMovementRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        Page<VehicleMovement> actualPage = vehicleMovementService.findPage(1);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testFindById() {
        VehicleMovement expectedVehicleMovement = new VehicleMovement();
        expectedVehicleMovement.setId(1L);

        when(vehicleMovementRepository.findById(1L)).thenReturn(Optional.of(expectedVehicleMovement));

        VehicleMovement actualVehicleMovement = vehicleMovementService.findById(1L);

        assertEquals(expectedVehicleMovement, actualVehicleMovement);
    }

    @Test
    public void testDelete() {
        vehicleMovementService.delete(1L);
        Mockito.verify(vehicleMovementRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        VehicleMovement vehicleMovementToSave = new VehicleMovement();
        vehicleMovementToSave.setId(1L);
        vehicleMovementService.save(vehicleMovementToSave);
        Mockito.verify(vehicleMovementRepository, times(1)).save(vehicleMovementToSave);
    }

    @Test
    public void testFindByKeyword() {
        String keyword = "test";
        List<VehicleMovement> expectedVehicleMovements = Arrays.asList(
                new VehicleMovement(),
                new VehicleMovement()
        );
        expectedVehicleMovements.get(0).setId(1L);
        expectedVehicleMovements.get(1).setId(2L);

        when(vehicleMovementRepository.findByKeyword(keyword)).thenReturn(expectedVehicleMovements);

        List<VehicleMovement> actualVehicleMovements = vehicleMovementService.findByKeyword(keyword);

        assertEquals(expectedVehicleMovements, actualVehicleMovements);
    }
}
