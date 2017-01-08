package com.tjrac.tao.user.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.tjrac.tao.user.dao.UserDao;
import com.tjrac.tao.user.service.UserService;
import com.tjrac.tao.user.vo.User;
import com.tjrac.tao.util.MailUtils;
import com.tjrac.tao.util.UUIDUtils;

@Transactional
public class UserServiceImpl implements UserService{
	
	//---------------UserDao--------------
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findByUsername(String username) {
		return this.userDao.findByUsername(username);
	}

	@Override
	public void add(User user) {
		user.setState(0);  //用户激活： 0 未激活   , 1 已激活
		
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();  //生成64位
		user.setCode(code);
		
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
		
		this.userDao.add(user);
	}

	@Override
	public User findByCode(String code) {
		return this.userDao.findByCode(code);
	}

	@Override
	public void updateUser(User findUser) {
		this.userDao.updateUser(findUser);
	}

	@Override
	public User login(User user) {
		return this.userDao.login(user);
	}
	
	
	
	
}
