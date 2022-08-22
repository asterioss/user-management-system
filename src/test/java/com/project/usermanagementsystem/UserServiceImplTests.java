package com.project.usermanagementsystem;

import com.project.usermanagementsystem.entity.User;
import com.project.usermanagementsystem.exception.UserNotFoundException;
import com.project.usermanagementsystem.repository.UserRepository;
import com.project.usermanagementsystem.service.UserService;
import com.project.usermanagementsystem.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest(UserServiceImpl.class)
public class UserServiceImplTests {
    @InjectMocks
    //@Autowired
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");

        when(userRepository.save(user)).thenReturn(user);

        userService.addUser(user);
        //User existUser = userRepository.findById(1L).get();

        //assertNotNull(existUser);
        //assertThat(existUser.getId()).isEqualTo(user.getId());
        //assertThat(existUser.getLastName()).isEqualTo("Lentzos");
        verify(userRepository).save(user);
    }

    @Test
    public void testGetUsers () {
        List<User> users = new ArrayList();

        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");
        users.add(user);

        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("Kostas");
        user1.setLastName("Papadopoulos");
        user1.setEmail("panatha@gmail.com");
        users.add(user1);

        when(userRepository.findAll()).thenReturn(users);
        List<User> existUsers = userService.getUsers();

        assertEquals(existUsers, users);
        assertThat(existUsers.size()).isEqualTo(2);
        assertThat(existUsers.get(0).getEmail()).isEqualTo("ekei@gmail.com");
        verify(userRepository).findAll();
    }

    @Test
    public void testGetUserById () {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User existUser = userService.getUserById(user.getId());

        assertNotNull(existUser);
        //assertThat(existUser).isSameAs(user);
        assertThat(existUser.getId()).isEqualTo(user.getId());
        assertThat(existUser.getFirstName()).isEqualTo("Asterios");
        verify(userRepository).findById(user.getId());
    }

    @Test
    public void testDeleteUser () {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User existUser = userService.getUserById(user.getId());

        userService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    public void testUpdateUser () {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");
        when(userRepository.save(user)).thenReturn(user);

        user.setFirstName("Manolis");
        user.setLastName("Theodorou");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.updateUser(user);

        verify(userRepository).save(user);
        verify(userRepository).findById(user.getId());
    }
}
