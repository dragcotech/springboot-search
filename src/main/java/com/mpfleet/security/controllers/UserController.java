package com.mpfleet.security.controllers;

import com.mpfleet.interceptor.annotations.PageTitle;
import com.mpfleet.security.models.User;
import com.mpfleet.security.repositories.UserRepository;
import com.mpfleet.security.services.RoleService;
import com.mpfleet.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, RoleService roleService, UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }


    @GetMapping("/security/users")
    @PageTitle("Users")
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/security/users";
    }

    @GetMapping("/security/user/{op}/{id}")
    @PageTitle("Edit/Details User")
    public String editUser(@PathVariable Long id, @PathVariable String op, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles",roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/security/user" + op;
    }

    @PostMapping("/users/addNew")
    public RedirectView addNew(User user, RedirectAttributes redir) {

        boolean exists = userRepository.existsByUsername(user.getUsername());
        RedirectView redirectView;

        if (!exists){
            redir.addFlashAttribute("message", "You have successfully registered!");
            redirectView = new RedirectView("/login", true);
            userService.save(user);
        } else {
            redir.addFlashAttribute("message", "Username exists");
            redirectView = new RedirectView("/register", true);
        }

        return redirectView;
    }

    @RequestMapping(value = "/security/user/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/security/users";
    }

}
