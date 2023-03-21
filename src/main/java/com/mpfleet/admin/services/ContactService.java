package com.mpfleet.admin.services;

import com.mpfleet.admin.models.Contact;
import com.mpfleet.admin.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }

    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> findByKeyword(String keyword){
        return contactRepository.findByKeyword(keyword);
    }
}
