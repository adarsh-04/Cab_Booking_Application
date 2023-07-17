package com.example.cabbooking.services;

import com.example.cabbooking.models.User;
import com.example.cabbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Invalid user details");
        }
        userRepository.addUser(user);
    }

    public User getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        return userRepository.getUserByUsername(username);
    }
}
