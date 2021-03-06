package com.tjrac.tao.admin.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.tao.admin.dao.AdminUserDao;
import com.tjrac.tao.admin.vo.AdminUser;

public class AdminUserDaoImpl extends HibernateDaoSupport implements AdminUserDao{

	@Override
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list =  this.getHibernateTemplate().find(hql, adminUser.getUsername(), adminUser.getPassword());
		if( list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
