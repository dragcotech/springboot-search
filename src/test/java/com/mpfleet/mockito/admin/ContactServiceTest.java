package com.mpfleet.mockito.admin;

import com.mpfleet.admin.models.Contact;
import com.mpfleet.admin.repositories.ContactRepository;
import com.mpfleet.admin.services.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;


    @Test
    public void testFindAllContacts() {
        List<Contact> contacts = Arrays.asList(
                new Contact("Martin","Parunev","232323","email@email","232323","none"),
                new Contact("Irena","Paruneva","232323","email@email","232323","none")
        );

        when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> result = contactService.findAll();

        verify(contactRepository).findAll();

        assertEquals(contacts, result);
    }

    @Test
    public void testFindContactById() {
        Contact expectedContact = new Contact("Martin","Parunev","232323","email@email","232323","none");

        when(contactRepository.findById(expectedContact.getId())).thenReturn(Optional.of(expectedContact));

        Contact actualContact = contactService.findById(expectedContact.getId());

        assertEquals(expectedContact, actualContact);
        Mockito.verify((contactRepository).findById(expectedContact.getId()));
    }

    @Test
    public void testSaveContact() {
        Contact contact = new Contact("Martin","Parunev","232323","email@email","232323","none");
        contactService.save(contact);
        verify(contactRepository).save(contact);
    }

    @Test
    public void testDeleteContact() {
        contactService.delete(1L);
        verify(contactRepository).deleteById(1L);
    }

    @Test
    public void testFindByKeyword() {
        List<Contact> contacts = Arrays.asList(
                new Contact("Martin","Parunev","232323","email@email","232323","none"),
                new Contact("Irena","Paruneva","232323","email@email","232323","none")
        );

        when(contactRepository.findByKeyword("Martin")).thenReturn(contacts);
        List<Contact> result = contactService.findByKeyword("Martin");
        verify(contactRepository).findByKeyword("Martin");
        assertEquals(contacts, result);
    }
}
