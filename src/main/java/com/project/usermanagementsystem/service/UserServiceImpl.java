package com.project.usermanagementsystem.service;

import com.project.usermanagementsystem.entity.User;
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
        repository.save(user);
        return "User added successfully!";
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }

    @Override
    public String updateUser(User user) {
        return null;
    }
}
