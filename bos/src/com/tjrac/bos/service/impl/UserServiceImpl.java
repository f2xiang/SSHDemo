package com.tjrac.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.UserDao;
import com.tjrac.bos.domain.Role;
import com.tjrac.bos.domain.User;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.utils.MyMd5Util;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	
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



	@Override
	public void pageQuery(PageBean pageBean) {
		this.userDao.pageQuery(pageBean);
	}



	@Override
	public void save(User model, String[] roleIds) {
		//密码加密
		String password = MyMd5Util.getMD5Value(model.getPassword());
		model.setPassword(password);
		this.userDao.save(model); //持久对象
		//用户关联角色
		for (String id : roleIds) {
			Role role = new Role(id);   //detached
			model.getRoles().add(role);
		}
	}


}
