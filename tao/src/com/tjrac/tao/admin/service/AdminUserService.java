package com.tjrac.tao.admin.service;

import com.tjrac.tao.admin.vo.AdminUser;

/**
 * 后台管理员的service层
 * @author FengXiang
 *
 */
public interface AdminUserService {

	/**
	 * 后台管理元登录
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser);

}
