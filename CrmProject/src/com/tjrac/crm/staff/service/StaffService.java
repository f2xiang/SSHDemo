package com.tjrac.crm.staff.service;

import java.util.List;

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
	/**
	 * 查询所有的员工
	 * @return
	 */
	public List<CrmStaff> findAllStaff();
	
	public CrmStaff findById(String staffId);
	
	
	public void addStaff(CrmStaff crmStaff);
}
