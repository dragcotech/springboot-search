package com.mpfleet.services.admin;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.repositories.CountryRepository;
import com.mpfleet.admin.services.CountryService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Test
    public void testFindAll() {
        List<Country> expectedCountries = Arrays.asList(
                new Country("USA", "United States of America", "North America", "American", "NA", null),
                new Country("CAN", "Canada", "North America", "Canadian", "NA", null),
                new Country("MEX", "Mexico", "North America", "Mexican", "NA", null)
        );

        when(countryRepository.findAll()).thenReturn(expectedCountries);

        List<Country> actualCountries = countryService.findAll();

        assertEquals(expectedCountries, actualCountries);
        verify(countryRepository).findAll();
    }

    @Test
    public void testFindPage() {
        int pageNumber = 1;
        List<Country> expectedCountries = Arrays.asList(
                new Country("USA", "United States of America", "North America", "American", "NA", null),
                new Country("CAN", "Canada", "North America", "Canadian", "NA", null),
                new Country("MEX", "Mexico", "North America", "Mexican", "NA", null)
        );

        Page<Country> page = new PageImpl<>(expectedCountries);

        when(countryRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Country> result = countryService.findPage(pageNumber);

        verify(countryRepository).findAll(eq(PageRequest.of(0,10)));

        assertEquals(expectedCountries, result.getContent());
    }

    @Test
    public void testSave() {
        Country country = new Country("USA", "United States of America", "North America", "American", "NA", null);
        countryService.save(country);
        verify(countryRepository).save(country);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        countryService.delete(id);
        verify(countryRepository).deleteById(id);
    }

    @Test
    public void testGetById() {
        Country expectedCountry = new Country("USA", "United States of America", "North America", "American", "NA", null);
        when(countryRepository.findById(expectedCountry.getId())).thenReturn(Optional.of(expectedCountry));

        Country actualCountry = countryService.getById(expectedCountry.getId());

        assertEquals(expectedCountry, actualCountry);
        verify(countryRepository).findById(expectedCountry.getId());
    }

    @Test
    public void testFindByKeyword() {
        String keyword = "United States";
        List<Country> expectedCountries = Arrays.asList(
                new Country("USA", "United States of America", "North America", "American", "NA", null),
                new Country("BGN", "Bulgaria", "Bulgaria", "Bulgaria", "Europe", null)
        );

        Mockito.when(countryRepository.findByKeyword(keyword)).thenReturn(expectedCountries);

        List<Country> actualCountries = countryService.findByKeyword(keyword);

        assertEquals(expectedCountries, actualCountries);
        Mockito.verify(countryRepository).findByKeyword(keyword);
    }
}
