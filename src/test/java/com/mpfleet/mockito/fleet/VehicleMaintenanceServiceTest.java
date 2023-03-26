package com.mpfleet.mockito.fleet;

import com.mpfleet.fleet.models.VehicleMaintenance;
import com.mpfleet.fleet.repositories.VehicleMaintenanceRepository;
import com.mpfleet.fleet.services.VehicleMaintenanceService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleMaintenanceServiceTest {

    @Mock
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    @InjectMocks
    private VehicleMaintenanceService vehicleMaintenanceService;

    @Test
    public void testFindAll() {
        List<VehicleMaintenance> maintenanceList = Arrays.asList(
                new VehicleMaintenance(),
                 new VehicleMaintenance()
        );
        maintenanceList.get(0).setRemarks("Maintenance 1");
        maintenanceList.get(1).setRemarks("Maintenance 2");

        when(vehicleMaintenanceRepository.findAll()).thenReturn(maintenanceList);

        List<VehicleMaintenance> result = vehicleMaintenanceService.findAll();

        assertEquals(2, result.size());
        assertEquals("Maintenance 1", result.get(0).getRemarks());
        assertEquals("Maintenance 2", result.get(1).getRemarks());
        Mockito.verify(vehicleMaintenanceRepository, times(1)).findAll();
    }

    @Test
    public void testFindPage() {
        List<VehicleMaintenance> maintenanceList = Arrays.asList(
                new VehicleMaintenance(),
                new VehicleMaintenance()
        );
        maintenanceList.get(0).setRemarks("Maintenance 1");
        maintenanceList.get(1).setRemarks("Maintenance 2");

        Page<VehicleMaintenance> page = new PageImpl<>(maintenanceList);
        when(vehicleMaintenanceRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<VehicleMaintenance> result = vehicleMaintenanceService.findPage(1);

        assertEquals(2, result.getContent().size());
        assertEquals("Maintenance 1", result.getContent().get(0).getRemarks());
        assertEquals("Maintenance 2", result.getContent().get(1).getRemarks());
        verify(vehicleMaintenanceRepository).findAll(eq(PageRequest.of(0,10)));
    }

    @Test
    public void testFindById() {
        VehicleMaintenance maintenance = new VehicleMaintenance();
        maintenance.setRemarks("Maintenance 1");
        when(vehicleMaintenanceRepository.findById(1L)).thenReturn(Optional.of(maintenance));

        VehicleMaintenance result = vehicleMaintenanceService.findById(1L);

        assertEquals("Maintenance 1", result.getRemarks());
        verify(vehicleMaintenanceRepository, times(1)).findById(1L);
    }

    @Test
    public void testDelete() {
        vehicleMaintenanceService.delete(1L);
        verify(vehicleMaintenanceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSave() {
        VehicleMaintenance maintenance = new VehicleMaintenance();
        vehicleMaintenanceService.save(maintenance);
        verify(vehicleMaintenanceRepository, times(1)).save(maintenance);
    }

    @Test
    public void testFindByKeyword() {
        String keyword = "oil";
        List<VehicleMaintenance> maintenanceList = new ArrayList<>();
        VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();
        vehicleMaintenance.setRemarks("oil change");
        maintenanceList.add(vehicleMaintenance);

        when(vehicleMaintenanceRepository.findByKeyword(keyword)).thenReturn(maintenanceList);

        List<VehicleMaintenance> result = vehicleMaintenanceService.findByKeyword(keyword);

        assertFalse(result.isEmpty());
        assertEquals("oil change", result.get(0).getRemarks());
    }

}
