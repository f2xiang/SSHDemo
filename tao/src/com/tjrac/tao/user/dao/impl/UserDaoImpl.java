package com.tjrac.tao.user.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.tao.user.dao.UserDao;
import com.tjrac.tao.user.vo.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	
	@Override
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list =  this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void add(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list =  this.getHibernateTemplate().find(hql, code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateUser(User findUser) {
		this.getHibernateTemplate().update(findUser);
	}

	@Override
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list =  this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}
		return null;
	}
	
	
}
