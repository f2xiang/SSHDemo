package com.tjrac.crm.staff.dao;

import java.util.List;

import com.tjrac.crm.staff.domain.CrmStaff;

/**
 * 针对员工类进行操作
 * @author Administrator
 *
 */
public interface StaffDao {
	/**
	 * 根据员工的用户名 和 密码 查找员工
	 * @param loginName 用户名
	 * @param loginPwd  密码
	 * @return   找到的员工
	 */
	public CrmStaff find(String loginName, String loginPwd);
	/**
	 * 查询所有的员工
	 * @return
	 */
	public List<CrmStaff> findAll();
	
	/**
	 * 通过员工的id查询员工
	 * @param staffId 员工的id
	 * @return  员工实体类
	 */
	public CrmStaff findById(String staffId);
	
	
	public void add(CrmStaff crmStaff);
	
}
