package com.project.usermanagementsystem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.usermanagementsystem.controller.UserController;
import com.project.usermanagementsystem.entity.User;
import com.project.usermanagementsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");

        when(userService.addUser(user)).thenReturn("User added successfully!");

        mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(toJson(user)))
                .andExpect(status().isCreated())
                //.andExpect(contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.users[0].lastName").value(user.getLastName()))
                .andDo(print());
    }

    @Test
    public void testGetUsers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");
        userService.addUser(user);

        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("Kostas");
        user1.setLastName("Papadopoulos");
        user1.setEmail("panatha@gmail.com");
        userService.addUser(user1);

        List<User> users = Arrays.asList(user, user1);

        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value(user.getFirstName()))
                .andDo(print());
    }

    @Test
    public void testGetUserById () throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");
        userService.addUser(user);

        when(userService.getUserById(user.getId())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" +user.getId().toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andDo(print());
    }

    @Test
    public void testDeleteUser () throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");
        userService.addUser(user);

        when(userService.deleteUser(user.getId())).thenReturn("User with id " +user.getId()+ " deleted successfully!");

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" +user.getId().toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdateUser () throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Asterios");
        user.setLastName("Lentzos");
        user.setEmail("ekei@gmail.com");
        userService.addUser(user);

        user.setFirstName("Manolis");
        user.setLastName("Theodorou");

        ObjectMapper mapper = new ObjectMapper();

        when(userService.updateUser(user)).thenReturn("User with id " +user.getId()+ " updated successfully!");

        mockMvc.perform(MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.firstName").value("Manolis"))
                .andDo(print());
    }

    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
