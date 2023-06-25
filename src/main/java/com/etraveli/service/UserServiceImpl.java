package com.etraveli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etraveli.dao.UserDao;
import com.etraveli.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Autowired
	public void setPersonDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}
	
	@Override
	public void create(User user) {
		userDao.create(user);
	}
	
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	
	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}
}