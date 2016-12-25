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
	/**
	 * 根据员工的id找到员工
	 * @param staffId 员工id
	 * @return 员工
	 */
	public CrmStaff findById(String staffId);
	
	/**
	 * 增加一个员工
	 * @param crmStaff
	 */
	public void addStaff(CrmStaff crmStaff);
	
	/**
	 * 修改员工数据
	 * @param crmStaff 封装要修改数据的员工实体
	 */
	public void updateStaff(CrmStaff crmStaff);
}
