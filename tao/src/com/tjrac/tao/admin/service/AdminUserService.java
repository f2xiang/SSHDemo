package com.tjrac.tao.admin.service;

import com.tjrac.tao.admin.vo.AdminUser;

/**
 * ��̨����Ա��service��
 * @author FengXiang
 *
 */
public interface AdminUserService {

	/**
	 * ��̨����Ԫ��¼
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser);

}
