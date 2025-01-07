package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private List<User> users = new ArrayList<>();
    public UserController() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@gmail.com");
        user1.setPassword("password");
        user1.setRole(Role.STUDENT);
        users.add(user1);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@gmail.com");
        user1.setPassword("password");
        user1.setRole(Role.STUDENT);
        return user1;
    }
    @GetMapping("/{id}")
    public Optional <User> getUserById(@PathVariable Integer id) {

        Optional <User> user = users.stream().filter(user1 -> user1.getId() == id).findFirst();
        return user;

    }
}
