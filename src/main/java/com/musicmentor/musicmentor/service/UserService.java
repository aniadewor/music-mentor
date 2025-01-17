package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public User registerUser(User user) {
        //sprawdzenie czy e-mail użytkownika istnieje w bazie
        //szyfrowanie hasła
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
