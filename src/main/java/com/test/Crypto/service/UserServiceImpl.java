package com.test.Crypto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.Crypto.model.User;
import com.test.Crypto.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findUserByName(String name) {
		return userRepository.findUserByName(name);
	}

	@Override
	@Transactional
	public void updateUserEvent(Long id, String event) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("user with id: " + id + " does not exists"));

		if (user != null) {
			user.setEvent(event);
		}
	}

	@Override
	@Transactional
	public void updateUser(Long id, User user) {
		User us = userRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("user with id: " + user.getId() + " does not exists"));

		if (us != null) {
			us.setName(user.getName());
			us.setCryptocurrencySymbol(user.getCryptocurrencySymbol());
			us.setCurrentPrice(user.getCurrentPrice());
		}
	}
}
