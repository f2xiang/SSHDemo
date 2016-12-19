package com.tjrac.crm.staff.dao;

import com.tjrac.crm.staff.domain.CrmStaff;

/**
 * 针对员工类进行操作
 * @author Administrator
 *
 */
public interface StaffDao {
	public CrmStaff find(String loginName, String loginPwd);
}
