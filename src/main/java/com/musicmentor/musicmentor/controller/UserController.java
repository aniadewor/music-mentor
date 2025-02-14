package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.request.LoginRequest;
import com.musicmentor.musicmentor.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;

    public UserController() {
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("Received user: " + user);
        userService.registerUser(user);
        // bad request
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
        System.out.println("Received user: " + loginRequest.getEmail());
        boolean success = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword(), session);
       if (success) {
           return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(loginRequest.getEmail()));
       }
       else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) { // Sprawdzamy, czy użytkownik nie jest nullem
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}