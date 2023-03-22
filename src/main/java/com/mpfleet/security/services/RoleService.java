package com.mpfleet.security.services;

import com.mpfleet.security.models.Role;
import com.mpfleet.security.models.User;
import com.mpfleet.security.repositories.RoleRepository;
import com.mpfleet.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void assignUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        assert user != null;
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public void unassignUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        user.getRoles().removeIf(x -> Objects.equals(x.getId(), roleId));
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    public List<Role> getUserNotRoles(User user) {
        return roleRepository.getUserNotRoles(user.getId());
    }

}
