package com.mpfleet.admin.services;

import com.mpfleet.admin.models.Client;
import com.mpfleet.admin.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Page<Client> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 10);
        return clientRepository.findAll(pageable);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public List<Client> findByKeyword(String keyword){
        return clientRepository.findByKeyword(keyword);
    }
}
