package com.proxyseller.notes.service.impl;

import com.proxyseller.notes.exception.EntityNotFoundException;
import com.proxyseller.notes.model.User;
import com.proxyseller.notes.repository.UserRepository;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUser(ObjectId userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User createUser(User user) {
		return userRepository.insert(user);
	}

	@Override
	public User updateUser(ObjectId userId, User user) {
		assertUserExists(userId);
		user.setId(userId);
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(ObjectId userId) {
		assertUserExists(userId);
		userRepository.deleteById(userId);
	}

	private void assertUserExists(ObjectId userId) {
		if (!userRepository.existsById(userId)) {
			throw new EntityNotFoundException(String.format("User with ID=%s not found.", userId));
		}
	}
}
