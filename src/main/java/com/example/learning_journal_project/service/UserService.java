package com.example.learning_journal_project.service;

import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Passwort hashen
        userRepository.save(user);
    }
}

