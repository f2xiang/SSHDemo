package com.tjrac.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjrac.bos.dao.UserDao;
import com.tjrac.bos.dao.base.impl.BaseDaoImpl;
import com.tjrac.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByNameAndPwd(String username, String password) {
		String hql = "from User where username = ?  and password = ?";
		List<User> list =  this.getHibernateTemplate().find(hql, username, password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
