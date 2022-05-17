package com.project.usermanagementsystem.service;

import com.project.usermanagementsystem.entity.User;
import com.project.usermanagementsystem.exception.NullRequestBodyException;
import com.project.usermanagementsystem.exception.UserNotFoundException;
import com.project.usermanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public String addUser(User user) {
        if(user==null) throw new NullRequestBodyException("The input body is null!");
        repository.save(user);
        return "User added successfully!";
    }

    @Override
    public List<User> getUsers() {
        //if the list is empty, it returns an empty collection: []
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return existingUser;
    }

    @Override
    public String deleteUser(Long id) {
        repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        repository.deleteById(id);
        return "User with id " +id+ " deleted successfully!";
    }

    @Override
    public String updateUser(User user) {
        if(user==null) throw new NullRequestBodyException("The input body is null!");
        User existingUser = repository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));
        //update the user according to the input
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        repository.save(existingUser);
        return "User with id " +user.getId()+ " updated successfully!";
    }
}
