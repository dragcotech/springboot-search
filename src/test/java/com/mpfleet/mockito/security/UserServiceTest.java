package com.mpfleet.mockito.security;

import com.mpfleet.security.models.User;
import com.mpfleet.security.repositories.UserRepository;
import com.mpfleet.security.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindAll() {
        List<User> expectedUsers = Arrays.asList(
                new User("Test","Testov", "test", "test", null),
                new User("Testov", "Test", "test", "test2", null)
        );

        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findAll();

        assertEquals(expectedUsers.size(), actualUsers.size());
        assertTrue(expectedUsers.containsAll(actualUsers));
    }

    @Test
    public void testFindById() {
        Long userId = 1L;
        User expectedUser = new User("Test","Testov", "test", "test", null);
        expectedUser.setId(userId);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findById(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testDelete() {
        Long userId = 1L;
        userService.delete(userId);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }

    @Test
    public void testSave() {
        User expectedUser = new User("Test","Testov", "test", "password", null);

        Mockito.when(encoder.encode(expectedUser.getPassword())).thenReturn("password");

        userService.save(expectedUser);

        Mockito.verify(encoder, Mockito.times(1)).encode(expectedUser.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).save(expectedUser);
        assertEquals("password", expectedUser.getPassword());
    }
}
