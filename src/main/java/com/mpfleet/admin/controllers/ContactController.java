package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Contact;
import com.mpfleet.admin.services.ContactService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/admin/contacts")
    @PageTitle("Contacts")
    public String getAllPages(Model model, String keyword){
        return getOnePage(model, 1, keyword);
    }

    @GetMapping("/admin/contacts/page/{pageNumber}")
    @PageTitle("Contacts")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
        Page<Contact> page = contactService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        page.getContent();
        List<Contact> contacts;
        contacts = keyword == null? contactService.findPage(currentPage).getContent():contactService.findByKeyword(keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("contacts", contacts);

        return "admin/contact/allContacts";
    }


    @GetMapping("/admin/contact/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable Long id){
        return contactService.findById(id);
    }

    @GetMapping("/admin/addcontact")
    @PageTitle("Add Contact")
    public String addContact(){
        return "/admin/contact/addContact";
    }

    //edit / details
    @GetMapping("/admin/contact/{op}/{id}")
    @PageTitle("Edit/Details Contact")
    public String editContact(@PathVariable Long id, @PathVariable String op, Model model){
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "/admin/contact/" + op + "Contact";
    }

    @PostMapping("/admin/contacts")
    public String save(Contact contact){
        contactService.save(contact);
        return "redirect:/admin/contacts";
    }

    @RequestMapping(value = "/admin/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){
        contactService.delete(id);
        return "redirect:/admin/contacts";
    }
}
