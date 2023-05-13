package com.proxyseller.notes.controller.api;

import com.proxyseller.notes.model.User;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserRestController {

	private UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
		return userService.getUser(new ObjectId(id))
				.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public User createUser(@RequestBody User user) {	// TODO use DTO
		return userService.createUser(user);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
		return userService.updateUser(new ObjectId(id), user);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") String id) {
		userService.deleteUser(new ObjectId(id));
	}
}
