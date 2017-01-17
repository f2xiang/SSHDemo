package com.tjrac.tao.admin.dao;

import com.tjrac.tao.admin.vo.AdminUser;

/**
 * 后台管理员的dao层
 * @author FengXiang
 *
 */
public interface AdminUserDao {

	/**
	 * 后台管理员登录
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser);

}
