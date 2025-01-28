package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    public User registerUser(User user) {
     if(getUserByEmail(user.getEmail())!=null){
         throw new IllegalArgumentException("Email already exists");
     }
//     String encryptedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean loginUser(String email, String password, HttpSession session) {
        if(getUserByEmail(email) == null) {
            throw new IllegalArgumentException("Email not found");
        }
        User user = getUserByEmail(email);
        if(user.getPassword().equals(password)) {
            session.setAttribute("user", user);
        }
        else {
            throw new IllegalArgumentException("Password incorrect");
        }
        return true;
    }
}
