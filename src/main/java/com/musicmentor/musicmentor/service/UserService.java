package com.musicmentor.musicmentor.service;

import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.repository.UserRepository;
import com.musicmentor.musicmentor.security.HashUntil;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(User user) {
     if(getUserByEmail(user.getEmail())!=null){
         throw new IllegalArgumentException("Email already exists");
     }
     user.setPassword(HashUntil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean loginUser(String email, String password, HttpSession session) {
        String hashedPassword = HashUntil.hashPassword(password);
        if(getUserByEmail(email) == null) {
            throw new IllegalArgumentException("Email not found");
        }
        User user = getUserByEmail(email);
        if(user.getPassword().equals(hashedPassword)){
            session.setAttribute("user", user);
        }
        else {
            throw new IllegalArgumentException("Password incorrect");
        }
        return true;
    }
    public List<User> getAllUsersBySchoolName(String schoolName) {
        List<User> allUsers = userRepository.findAll();
        return userRepository.findAll().stream().filter(user -> user.getSchoolName()!= null && user.getSchoolName().equalsIgnoreCase(schoolName)).collect(Collectors.toList());
    }
    public String updateUserClassName(Integer id, String className) {
        User user = userRepository.findById(id).get();
        user.setClassName(className);
        return userRepository.save(user).getClassName();
    }
}
