package com.tjrac.tao.admin.dao;

import com.tjrac.tao.admin.vo.AdminUser;

/**
 * ��̨����Ա��dao��
 * @author FengXiang
 *
 */
public interface AdminUserDao {

	/**
	 * ��̨����Ա��¼
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser);

}
