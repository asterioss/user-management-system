package com.project.usermanagementsystem.controller;

import com.project.usermanagementsystem.entity.User;
import com.project.usermanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    //a simple example with requests
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody(required = false) User user) {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<User>(service.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@Valid @RequestBody(required = false) User user) {
        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }
}
