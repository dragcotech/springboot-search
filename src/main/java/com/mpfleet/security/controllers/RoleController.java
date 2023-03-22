package com.mpfleet.security.controllers;

import com.mpfleet.security.models.Role;
import com.mpfleet.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/security/roles")
    public String parameters(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "security/roles";
    }

    @GetMapping("/security/role/{id}")
    @ResponseBody
    public Role getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping("/security/roles")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/security/roles";
    }

    @RequestMapping(value = "/security/role/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/security/roles";
    }

    @RequestMapping("/security/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Long userId,
                             @PathVariable Long roleId) {
        roleService.assignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/" + userId;
    }

    @RequestMapping("/security/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Long userId,
                               @PathVariable Long roleId) {
        roleService.unassignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/" + userId;
    }
}
