package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Contact;
import com.mpfleet.admin.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public String getAll(Model model, String keyword){
        List<Contact> contacts;

        contacts = keyword == null? contactService.findAll():contactService.findByKeyword(keyword);

        model.addAttribute("contacts", contacts);
        return "/admin/contact/allContacts";
    }


    @GetMapping("/contact/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable Long id){
        return contactService.findById(id);
    }

    @GetMapping("/addcontact")
    public String addContact(){
        return "/admin/contact/addContact";
    }

    //edit / details
    @GetMapping("/contact/{op}/{id}")
    public String editContact(@PathVariable Long id, @PathVariable String op, Model model){
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "/admin/contact/" + op + "Contact";
    }

    @PostMapping("/contacts")
    public String save(Contact contact){
        contactService.save(contact);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){
        contactService.delete(id);
        return "redirect:/contacts";
    }
}
