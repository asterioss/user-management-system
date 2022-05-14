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
        return repository.findById(id).orElse(null);
    }

    @Override
    public String deleteUser(Long id) {
        User existingUser = repository.findById(id).orElse(null);
        if(existingUser!=null) repository.deleteById(id);
        return "User with id "+id+" deleted successfully!";
    }

    @Override
    public String updateUser(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        if(existingUser!=null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            repository.save(existingUser);
        }
        return "User with id "+user.getId()+" updated successfully!";
    }
}
