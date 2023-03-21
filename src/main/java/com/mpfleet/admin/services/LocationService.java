package com.mpfleet.admin.services;

import com.mpfleet.admin.models.Location;
import com.mpfleet.admin.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Page<Location> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 10);
        return locationRepository.findAll(pageable);
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }

    public List<Location> findByKeyword(String keyword){
        return locationRepository.findByKeyword(keyword);
    }

}
