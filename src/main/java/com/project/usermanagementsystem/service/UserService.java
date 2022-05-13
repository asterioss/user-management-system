package com.project.usermanagementsystem.service;

import com.project.usermanagementsystem.entity.User;

import java.util.List;

public interface UserService {
    String addUser(User user);
    List<User> getUsers();
    User getUserById(Long id);
    String deleteUser(Long id);
    String updateUser(User user);
}
