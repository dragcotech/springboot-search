package com.mpfleet.mockito.fleet;

import com.mpfleet.fleet.models.VehicleModel;
import com.mpfleet.fleet.repositories.VehicleModelRepository;
import com.mpfleet.fleet.services.VehicleModelService;
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

@RunWith(MockitoJUnitRunner.class)
public class VehicleModelServiceTest {

    @Mock
    private VehicleModelRepository vehicleModelRepository;

    @InjectMocks
    private VehicleModelService vehicleModelService;

    @Test
    public void testFindAll() {
        List<VehicleModel> vehicleModels = Arrays.asList(
                new VehicleModel(),
                new VehicleModel()
        );
        vehicleModels.get(0).setId(1L); vehicleModels.get(0).setDescription("Model1");
        vehicleModels.get(1).setId(2L); vehicleModels.get(1).setDescription("Model2");

        Mockito.when(vehicleModelRepository.findAll()).thenReturn(vehicleModels);
        List<VehicleModel> result = vehicleModelService.findAll();

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("Model1", result.get(0).getDescription());
        Assert.assertEquals("Model2", result.get(1).getDescription());
    }

    @Test
    public void testFindById() {
       VehicleModel vehicleModel = new VehicleModel();
       vehicleModel.setDescription("Model1");
       vehicleModel.setId(1L);

        Mockito.when(vehicleModelRepository.findById(1L)).thenReturn(Optional.of(vehicleModel));
        Optional<VehicleModel> result = vehicleModelService.findById(1L);
        Assert.assertEquals("Model1", result.get().getDescription());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        vehicleModelService.delete(id);
        Mockito.verify(vehicleModelRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testSave() {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModelService.save(vehicleModel);
        Mockito.verify(vehicleModelRepository, Mockito.times(1)).save(vehicleModel);
    }

}
