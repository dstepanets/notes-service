package com.proxyseller.notes.service.impl;

import com.proxyseller.notes.exception.EntityNotFoundException;
import com.proxyseller.notes.exception.ValidationException;
import com.proxyseller.notes.model.User;
import com.proxyseller.notes.repository.UserRepository;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private static final User visitorUser = User.builder()
			.id(ObjectId.get())
			.name(User.Role.VISITOR.name())
			.role(User.Role.VISITOR.name())
			.build();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User Name: " + username));
	}

	@Override
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Transactional
	@Override
	public User register(User user) {
		if (userRepository.findByName(user.getName()).isPresent()) {
			throw new ValidationException("This UserName is registered already");
		}
		final String encryptedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		user.setRole(User.Role.USER.name());

		return userRepository.insert(user);
	}

	@Override
	public User getVisitorUser() {
		return visitorUser;
	}

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
