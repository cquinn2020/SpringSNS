package com.caseyquinn.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.caseyquinn.userservice.exception.UserAlreadyExistsException;
import com.caseyquinn.userservice.model.User;
import com.caseyquinn.userservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        // Check if the user already exists
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email '" + user.getEmail() + "' already exists");
        }

        // encrypt the password
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.toString();
        System.out.println("UserService.registerUser()");
        System.out.println("encodedPassword: " + encodedPassword);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
