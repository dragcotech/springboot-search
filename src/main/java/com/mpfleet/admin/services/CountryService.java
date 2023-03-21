package com.mpfleet.admin.services;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Country> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 10);
        return countryRepository.findAll(pageable);
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

    public List<Country> findByKeyword(String keyword){
        return countryRepository.findByKeyword(keyword);
    }
}
