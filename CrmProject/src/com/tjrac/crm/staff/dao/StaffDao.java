package com.tjrac.crm.staff.dao;

import com.tjrac.crm.staff.domain.CrmStaff;

/**
 * ���Ա������в���
 * @author Administrator
 *
 */
public interface StaffDao {
	public CrmStaff find(String loginName, String loginPwd);
}
