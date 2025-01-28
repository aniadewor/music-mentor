package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
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

    @GetMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody String email, @RequestBody String password, HttpSession session) {
        System.out.println("Received user: " + email);
        boolean success = userService.loginUser(email, password, session);
       if (success) {
           return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
       }
       else {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
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