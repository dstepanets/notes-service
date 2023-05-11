package com.proxyseller.notes.service;

import com.proxyseller.notes.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

	List<User> getAllUsers();

	Optional<User> getUser(ObjectId userId);

	User createUser(User user);

	User updateUser(ObjectId userId, User user);

	void deleteUser(ObjectId userId);
}
