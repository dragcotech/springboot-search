package com.mpfleet.services.security;

import com.mpfleet.security.models.Role;
import com.mpfleet.security.models.User;
import com.mpfleet.security.repositories.RoleRepository;
import com.mpfleet.security.repositories.UserRepository;
import com.mpfleet.security.services.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testFindAll() {
        List<Role> roles = Arrays.asList(
                new Role("USER", "Index"),
                new Role("SUPER_ADMIN", "All")
        );

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = roleService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        Mockito.verify(roleRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Role role = new Role("SUPER_ADMIN", "All");
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));

        Role result = roleService.findById(1L);

        assertNotNull(result);
        assertEquals(role.getDescription(), result.getDescription());

        Mockito.verify(roleRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testDelete() {
        doNothing().when(roleRepository).deleteById(anyLong());
        roleService.delete(1L);
        Mockito.verify(roleRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testSave() {
        Role role = new Role("SUPER_ADMIN", "All");
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        roleService.save(role);

        Mockito.verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void testAssignUserRole() {
        User user = new User();
        user.setId(1L);
        Role role = new Role();
        role.setId(2L);
        Set<Role> userRoles = new HashSet<>();
        user.setRoles(userRoles);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);

        roleService.assignUserRole(1L, 2L);

        verify(userRepository, times(1)).findById(anyLong());
        verify(roleRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any(User.class));

        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    public void testUnassignUserRole() {
        User user = new User();
        user.setId(1L);
        Role role = new Role();
        role.setId(2L);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        roleService.unassignUserRole(1L, 2L);

        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any(User.class));

        assertEquals(0, user.getRoles().size());
    }

    @Test
    public void testGetUserRoles() {
        User user = new User();
        user.setId(1L);
        Role role1 = new Role();
        role1.setId(1L);
        Role role2 = new Role();
        role2.setId(2L);
        Set<Role> roles = new HashSet<>(Arrays.asList(role1, role2));
        user.setRoles(roles);

        Set<Role> userRoles = roleService.getUserRoles(user);

        assertEquals(roles, userRoles);
    }

    @Test
    public void testGetUserNotRoles() {
        User user = new User();
        user.setId(1L);
        Role role1 = new Role();
        role1.setId(1L);
        Role role2 = new Role();
        role2.setId(2L);
        List<Role> roles = new ArrayList<>(Arrays.asList(role1, role2));

        when(roleRepository.getUserNotRoles(1L)).thenReturn(roles);

        List<Role> userNotRoles = roleService.getUserNotRoles(user);

        assertEquals(roles, userNotRoles);
    }
}
