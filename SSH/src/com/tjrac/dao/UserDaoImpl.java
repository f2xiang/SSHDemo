package com.tjrac.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tjrac.beans.User;

public class UserDaoImpl implements UserDao{

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void saveUser(User user) {
		this.hibernateTemplate.save(user);
	}

}
