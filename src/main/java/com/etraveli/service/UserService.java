package com.etraveli.service;

import java.util.List;

import com.etraveli.model.User;

public interface UserService {
	List<User> findAll();
	User findById(Long id);
	void create(User user);
	void update(User user);
	void delete(Long id);
}