package com.mpfleet.security.controllers;

import com.mpfleet.security.models.User;
import com.mpfleet.security.services.RoleService;
import com.mpfleet.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/security/users")
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/security/users";
    }

    @GetMapping("/security/user/{op}/{id}")
    public String editUser(@PathVariable Long id, @PathVariable String op, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles",roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/security/user" + op;
    }

    @PostMapping("/users/addNew")
    public RedirectView addNew(User user, RedirectAttributes redir) {
        userService.save(user);

        RedirectView redirectView = new RedirectView("/login", true);
        redir.addFlashAttribute("message", "You have successfully registered!");
        return redirectView;
    }

}
