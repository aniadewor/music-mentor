package com.musicmentor.musicmentor.controller;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import com.musicmentor.musicmentor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private List<User> users = new ArrayList<>();
    private UserService userService;

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
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser1(@RequestBody User user) {
        System.out.println("Received user: " + user);
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {

   return userService.getUserById(id);
//        Optional<User> user = users.stream().filter(user1 -> user1.getId() == id).findFirst();
    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email)
    //@PathVariable oznacza, że wartość {email} z adresu URL zostanie przekazana do parametru metody email.
    {
        return users.stream() //users.stream() tworzy strumień (Stream) z obiektów w tej liście.
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                //filter(...) przesiewa elementy strumienia, pozostawiając tylko te, dla których warunek jest prawdziwy.
                .findFirst();

        }
    }
