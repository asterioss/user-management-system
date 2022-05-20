package com.project.usermanagementsystem;

import com.project.usermanagementsystem.entity.User;
import com.project.usermanagementsystem.repository.UserRepository;
import com.project.usermanagementsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserManagementSystemApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	/*@Test
	void contextLoads() {
	}*/

	@Test
	public void testAddUser () {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Asterios");
		user.setLastName("Lentzos");
		user.setEmail("ekei@gmail.com");

		userService.addUser(user);
		User existUser = userRepository.findById(1L).get();

		assertNotNull(existUser);
		assertThat(existUser.getId()).isEqualTo(user.getId());
		assertThat(existUser.getLastName()).isEqualTo("Lentzos");
	}

	@Test
	public void testGetUsers () {
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

		List<User> users = (List<User>) userRepository.findAll();
		User existUser = userRepository.findById(2L).get();

		assertNotNull(existUser);
		assertThat(users.size()).isEqualTo(2);
		assertThat(existUser.getFirstName()).isEqualTo("Kostas");
		assertThat(users.get(0).getEmail()).isEqualTo("ekei@gmail.com");
		assertThat(users.get(1).getLastName()).isEqualTo("Papadopoulos");
	}

	@Test
	public void testGetUserById () {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Asterios");
		user.setLastName("Lentzos");
		user.setEmail("ekei@gmail.com");
		userService.addUser(user);

		User existUser = userService.getUserById(user.getId());

		assertNotNull(existUser);
		assertThat(existUser.getId()).isEqualTo(user.getId());
		assertThat(existUser.getFirstName()).isEqualTo("Asterios");
	}

	@Test
	public void testDeleteUser () {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Asterios");
		user.setLastName("Lentzos");
		user.setEmail("ekei@gmail.com");
		userService.addUser(user);

		userService.deleteUser(user.getId());
		List<User> users = (List<User>) userRepository.findAll();

		//check an i lista einai adeia
		assertThat(users.size()).isEqualTo(0);
	}

	@Test
	public void testUpdateUser () {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Asterios");
		user.setLastName("Lentzos");
		user.setEmail("ekei@gmail.com");
		userService.addUser(user);

		user.setFirstName("Manolis");
		user.setLastName("Theodorou");
		userService.updateUser(user);

		User existUser = userService.getUserById(user.getId());

		assertNotNull(existUser);
		assertThat(existUser.getFirstName()).isEqualTo("Manolis");
		assertThat(existUser.getLastName()).isEqualTo("Theodorou");
	}





}
