package com.tjrac.tao.admin.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.tjrac.tao.admin.dao.AdminUserDao;
import com.tjrac.tao.admin.service.AdminUserService;
import com.tjrac.tao.admin.vo.AdminUser;

@Transactional
public class AdminUserServiceImpl implements AdminUserService{
	private AdminUserDao adminUserDao;
	
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	@Override
	public AdminUser login(AdminUser adminUser) {
		return this.adminUserDao.login(adminUser);
	}
	
	
}
