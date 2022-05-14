package com.project.usermanagementsystem.controller;

import com.project.usermanagementsystem.entity.User;
import com.project.usermanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    //a simple example with requests
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }
}
