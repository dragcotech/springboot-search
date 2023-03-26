package com.mpfleet.mockito.admin;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.State;
import com.mpfleet.admin.repositories.StateRepository;
import com.mpfleet.admin.services.StateService;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateService stateService;

    public static final Country country1 = new Country("USA", "United States of America", "North America", "American", "NA", null);
    public static final Country country2 = new Country("CAN", "Canada", "North America", "Canadian", "NA", null);
    public static final Country country3 = new Country("MEX", "Mexico", "North America", "Mexican", "NA", null);


    @Test
    public void testFindAll() {
        List<State> expectedStates = Arrays.asList(
                new State("Sofia", "1000", country1 ,"Capital of Bulgaria"),
                new State("Berlin", "0030", country2 ,"Capital of Germany"),
                new State("Rome", "0006", country3,"Capital of Italy")
        );

        when(stateRepository.findAll()).thenReturn(expectedStates);

        List<State> actualStates = stateService.findAll();

        assertEquals(expectedStates, actualStates);
        verify(stateRepository).findAll();
    }

    @Test
    public void testFindPage() {
        int pageNumber = 1;
        List<State> expectedStates = Arrays.asList(
                new State("Sofia", "1000", country1 ,"Capital of Bulgaria"),
                new State("Berlin", "0030", country2 ,"Capital of Germany"),
                new State("Rome", "0006", country3,"Capital of Italy")
        );

        Page<State> expectedPage = new PageImpl<>(expectedStates);
        when(stateRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        Page<State> actualPage = stateService.findPage(pageNumber);

        assertEquals(expectedPage, actualPage);
        verify(stateRepository).findAll(any(Pageable.class));
    }

    @Test
    public void testSave() {
        State expectedState = new State("Sofia", "1000", country1 ,"Capital of Bulgaria");
        stateService.save(expectedState);
        verify(stateRepository).save(expectedState);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        stateService.delete(id);
        verify(stateRepository).deleteById(id);
    }

    @Test
    public void testGetById() {
        State expectedState = new State("Sofia", "1000", country1 ,"Capital of Bulgaria");
        when(stateRepository.findById(expectedState.getId())).thenReturn(Optional.of(expectedState));

        State actualState = stateService.getById(expectedState.getId());

        assertEquals(expectedState, actualState);
        verify(stateRepository).findById(expectedState.getId());
    }

    @Test
    public void testFindByKeyword() {
        List<State> expectedStates = Arrays.asList(
                new State("Sofia", "1000", country1 ,"Capital of Bulgaria"),
                new State("Berlin", "0030", country2 ,"Capital of Germany"),
                new State("Rome", "0006", country3,"Capital of Italy")
        );

        Mockito.when(stateRepository.findByKeyword(Mockito.anyString())).thenReturn(expectedStates);

        List<State> result = stateService.findByKeyword("Sofia");

        assertEquals(3, result.size());
        assertEquals("Sofia", result.get(0).getCapital());
        assertEquals("Rome", result.get(2).getDetails()); // passed
    }
}
