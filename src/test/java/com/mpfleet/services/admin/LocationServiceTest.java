package com.mpfleet.services.admin;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.Location;
import com.mpfleet.admin.models.State;
import com.mpfleet.admin.repositories.LocationRepository;
import com.mpfleet.admin.services.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    public static final Country country1 = new Country("USA", "United States of America", "North America", "American", "NA", null);
    public static final Country country2 = new Country("CAN", "Canada", "North America", "Canadian", "NA", null);
    public static final Country country3 = new Country("MEX", "Mexico", "North America", "Mexican", "NA", null);

    public static final State state1 = new State("Sofia", "1000", country1 ,"Capital of Bulgaria");
    public static final State state2 = new State("Berlin", "0030", country2 ,"Capital of Germany");
    public static final State state3 = new State("Rome", "0006", country3,"Capital of Italy");

    @Test
    public void testFindAll() {
        List<Location> expectedLocations = Arrays.asList(
                new Location("St. Alexander Nevsky Cathedral is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria","Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the 50 largest Christian church buildings by volume in the world.",country1,state1,"pl. Sveti Aleksandar Nevski"),
                new Location("Eltz Castle is a medieval castle nestled in the hills above the Moselle between Koblenz and Trier, Germany.","It is still owned by a branch of House of Eltz who have lived there since the 12th century.",country2, state2,"56294 Wierschem"),
                new Location("The Colosseum is an elliptical amphitheatre in the centre of the city of Rome","It is the largest ancient amphitheatre ever built, and is still the largest standing amphitheatre in the world, despite its age",country3,state3,"Piazza del Colosseo, 1, 00184 Roma RM")
        );

        when(locationRepository.findAll()).thenReturn(expectedLocations);

        List<Location> result = locationService.findAll();

        assertEquals(3, result.size());
        assertEquals("Berlin", result.get(1).getState().getCapital());
        assertEquals("Mexico", result.get(2).getCountry().getName());
        assertEquals("pl. Sveti Aleksandar Nevski", result.get(0).getAddress());
    }

    @Test
    public void testFindPage() {
        int pageNumber = 1;

        List<Location> expectedLocations = Arrays.asList(
                new Location("St. Alexander Nevsky Cathedral is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria","Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the 50 largest Christian church buildings by volume in the world.",country1,state1,"pl. Sveti Aleksandar Nevski"),
                new Location("Eltz Castle is a medieval castle nestled in the hills above the Moselle between Koblenz and Trier, Germany.","It is still owned by a branch of House of Eltz who have lived there since the 12th century.",country2, state2,"56294 Wierschem"),
                new Location("The Colosseum is an elliptical amphitheatre in the centre of the city of Rome","It is the largest ancient amphitheatre ever built, and is still the largest standing amphitheatre in the world, despite its age",country3,state3,"Piazza del Colosseo, 1, 00184 Roma RM")
        );

        Page<Location> expectedPage = new PageImpl<>(expectedLocations);
        when(locationRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        Page<Location> actualPage = locationService.findPage(pageNumber);

        assertEquals(expectedPage, actualPage);
        verify(locationRepository).findAll(any(Pageable.class));
    }

    @Test
    public void testFindById() {
        Location location = new Location("St. Alexander Nevsky Cathedral is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria","Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the 50 largest Christian church buildings by volume in the world.",country1,state1,"pl. Sveti Aleksandar Nevski");
        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        Location result = locationService.findById(location.getId());

        assertNotNull(result);
        assertEquals("pl. Sveti Aleksandar Nevski", result.getAddress());
        assertEquals("Sofia", result.getState().getCapital());
        assertEquals("USA", result.getCountry().getCode());
    }

    @Test
    public void testSave() {
        Location location = new Location("St. Alexander Nevsky Cathedral is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria","Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the 50 largest Christian church buildings by volume in the world.",country1,state1,"pl. Sveti Aleksandar Nevski");
        locationService.save(location);
        verify(locationRepository).save(location);
    }

    @Test
    public void testDeleteById() {
        locationService.deleteById(1L);
        verify(locationRepository).deleteById(1L);
    }

    @Test
    public void testFindByKeyword() {
        List<Location> expectedLocations = Arrays.asList(
                new Location("St. Alexander Nevsky Cathedral is a Bulgarian Orthodox cathedral in Sofia, the capital of Bulgaria","Built in Neo-Byzantine style, it serves as the cathedral church of the Patriarch of Bulgaria and it is one of the 50 largest Christian church buildings by volume in the world.",country1,state1,"pl. Sveti Aleksandar Nevski"),
                new Location("Eltz Castle is a medieval castle nestled in the hills above the Moselle between Koblenz and Trier, Germany.","It is still owned by a branch of House of Eltz who have lived there since the 12th century.",country2, state2,"56294 Wierschem"),
                new Location("The Colosseum is an elliptical amphitheatre in the centre of the city of Rome","It is the largest ancient amphitheatre ever built, and is still the largest standing amphitheatre in the world, despite its age",country3,state3,"Piazza del Colosseo, 1, 00184 Roma RM")
        );

        when(locationRepository.findByKeyword("Eltz Castle")).thenReturn(expectedLocations);

        List<Location> result = locationService.findByKeyword("Eltz Castle");

        assertEquals(3, result.size());
        assertEquals(expectedLocations.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(expectedLocations.get(2).getDescription(), result.get(2).getDescription());
    }

}
