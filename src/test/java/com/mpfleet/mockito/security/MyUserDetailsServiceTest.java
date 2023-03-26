package com.mpfleet.mockito.security;

import com.mpfleet.security.models.User;
import com.mpfleet.security.repositories.UserRepository;
import com.mpfleet.security.services.MyUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        UserDetails result = myUserDetailsService.loadUserByUsername("testuser");

        assertNotNull("Not Null", result);
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());

        Mockito.verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        try {
            myUserDetailsService.loadUserByUsername("testuser");
        } catch (UsernameNotFoundException ex) {
            assertEquals("User not found", ex.getMessage());
        }

        Mockito.verify(userRepository, times(1)).findByUsername(anyString());
    }
}
