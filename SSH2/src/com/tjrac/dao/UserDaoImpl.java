package com.tjrac.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.beans.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	

}
