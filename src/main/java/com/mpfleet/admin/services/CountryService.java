package com.mpfleet.admin.services;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll(){
        return this.countryRepository.findAll();
    }

    public void save(Country country){
        countryRepository.save(country);
    }

    public void delete(Long id){
        countryRepository.deleteById(id);
    }

    public Country getById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }
}
