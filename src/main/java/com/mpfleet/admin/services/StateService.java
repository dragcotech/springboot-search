package com.mpfleet.admin.services;

import com.mpfleet.admin.models.State;
import com.mpfleet.admin.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> findAll(){
        return this.stateRepository.findAll();
    }

    public void save(State country){
        stateRepository.save(country);
    }

    public void delete(Long id){
        stateRepository.deleteById(id);
    }

    public State getById(Long id) {
        return stateRepository.findById(id).orElse(null);
    }
}
