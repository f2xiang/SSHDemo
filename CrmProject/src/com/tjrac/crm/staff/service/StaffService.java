package com.tjrac.crm.staff.service;

import com.tjrac.crm.staff.domain.CrmStaff;

/**
 * 员工的service层
 * @author Administrator
 *
 */
public interface StaffService {
	/**
	 * 登录
	 * @param crmStaff 员工(封装了 账号 和 密码)
	 * @return 员工
	 */
	public CrmStaff login(CrmStaff crmStaff);
}
