package com.caseyquinn.userservice.unit.service;

import org.junit.jupiter.api.Test; // JUnit 5 - testing framework
import org.mockito.Mockito; // Mockito - mocking framework
import org.springframework.beans.factory.annotation.Autowired; // Spring - dependency injection framework
import org.springframework.boot.test.context.SpringBootTest; // Spring Boot - testing framework
import org.springframework.boot.test.mock.mockito.MockBean; // Spring Boot - mocking framework

import com.caseyquinn.userservice.exception.UserAlreadyExistsException;
import com.caseyquinn.userservice.model.User;
import com.caseyquinn.userservice.repository.UserRepository;
import com.caseyquinn.userservice.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals; // JUnit 5 - assertion library
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired // inject the UserService bean
    private UserService userService;

    @MockBean // mock the UserRepository bean
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        // mock the findByEmail method to return null (user does not exist)
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        User savedUser = userService.registerUser(user);

        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void testRegisterExistingUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        // mock the findByEmail method to return a user (user already exists)
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        // When this lambda is invoked, it will attempt to register the user, which is
        // expected to throw the exception.
        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(user));
    }
}
