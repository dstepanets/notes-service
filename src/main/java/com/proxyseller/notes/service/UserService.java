package com.proxyseller.notes.service;

import com.proxyseller.notes.model.User;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

	User register(User user);

	List<User> getAllUsers();

	Optional<User> getUser(ObjectId userId);

	User createUser(User user);

	User updateUser(ObjectId userId, User user);

	void deleteUser(ObjectId userId);
}
