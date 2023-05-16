package ru.musailov.Project2SpringBoot.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.musailov.Project2SpringBoot.auth.ApplicationUser;
import ru.musailov.Project2SpringBoot.auth.ApplicationUserService;
import ru.musailov.Project2SpringBoot.auth.User;
import ru.musailov.Project2SpringBoot.auth.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ApplicationUserServiceTest {

    private ApplicationUserService applicationUserService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        applicationUserService = new ApplicationUserService(passwordEncoder, userRepository);
    }

    @Test
    public void loadUserByUsername_UserExists_ReturnsUserDetails() {
        String username = "arsenii";
        String password = "9312";
        User user = new User(username, password);
        when(userRepository.findUserByUsername(username)).thenReturn(user);

        ApplicationUser applicationUser = (ApplicationUser) applicationUserService.loadUserByUsername(username);

        assertEquals(username, applicationUser.getUsername());
        assertEquals(password, applicationUser.getPassword());
        verify(userRepository, times(1)).findUserByUsername(username);
    }

    @Test
    public void loadUserByUsername_UserDoesNotExist_ThrowsException() {
        String username = "testuser";
        when(userRepository.findUserByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            applicationUserService.loadUserByUsername(username);
        });

        verify(userRepository, times(1)).findUserByUsername(username);
    }

    @Test
    public void signUpUser_UserDoesNotExist_Success() {
        String username = "testuser";
        String password = "testpassword";
        User user = new User(username, password);
        when(userRepository.findUserByUsername(username)).thenReturn(null);

        String result = applicationUserService.signUpUser(user);

        assertEquals("login", result);
        verify(userRepository, times(1)).findUserByUsername(username);
        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode(password);
    }

    @Test
    public void signUpUser_UserExists_ThrowsException() {
        String username = "testuser";
        User user = new User(username, "testpassword");
        when(userRepository.findUserByUsername(username)).thenReturn(new User());

        assertThrows(IllegalStateException.class, () -> {
            applicationUserService.signUpUser(user);
        });

        verify(userRepository, times(1)).findUserByUsername(username);
        verify(userRepository, never()).save(user);
        verify(passwordEncoder, never()).encode(anyString());
    }
}