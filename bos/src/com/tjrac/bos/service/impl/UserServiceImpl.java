package com.tjrac.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.UserDao;
import com.tjrac.bos.domain.User;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.utils.MyMd5Util;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	@Override
	public User login(User model) {
		String username = model.getUsername();
		String password = MyMd5Util.getMD5Value(model.getPassword()) ;
		return this.userDao.findByNameAndPwd(username, password);
	}



	@Override
	public void updatepwd(String password, Integer id) {
		this.userDao.update("editPassword", password, id);
	}


}
