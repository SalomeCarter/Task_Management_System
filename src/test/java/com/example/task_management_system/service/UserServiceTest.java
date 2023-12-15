package com.example.task_management_system.service;

import com.example.task_management_system.dto.LoginUserDto;
import com.example.task_management_system.dto.RegUserDto;
import com.example.task_management_system.entity.SessionUser;
import com.example.task_management_system.entity.User;
import org.junit.jupiter.api.Test;
import com.example.task_management_system.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Arrange
        RegUserDto regUserDto = new RegUserDto("testuser", "testuser@example.com", "password");

        // Mock репозитория
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        userService.save(regUserDto);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLoginUser() {
        // Arrange
        LoginUserDto loginUserDto = new LoginUserDto("testuser@example.com", "password");
        User testUser = new User();
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");

        // Mock репозитория
        when(userRepository.findByEmail("testuser@example.com")).thenReturn(Optional.of(testUser));

        // Act
        Optional<SessionUser> sessionUser = userService.login(loginUserDto);

        // Assert
        assertTrue(sessionUser.isPresent());
        assertEquals("testuser@example.com", sessionUser.get().getEmail());
    }
}
