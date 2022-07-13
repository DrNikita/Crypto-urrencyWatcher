package com.test.Crypto.service;

import java.util.List;
import java.util.Optional;

import com.test.Crypto.model.User;

public interface UserService {

	List<User> findAll();

	void save(User user);

	Optional<User> findById(Long id);

	Optional<User> findUserByName(String name);

	void updateUserEvent(Long id, String event);

	void updateUser(Long id, User user);

}
