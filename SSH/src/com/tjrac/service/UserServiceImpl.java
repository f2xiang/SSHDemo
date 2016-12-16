package com.tjrac.service;

import com.tjrac.beans.User;
import com.tjrac.dao.UserDao;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
}
