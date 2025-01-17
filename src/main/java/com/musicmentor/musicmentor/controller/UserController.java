package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.service.UserService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
         else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email)
    {
        return userService.getUserByEmail(email);
    }
}