package com.tjrac.crm.department.dao;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;


public interface DepartmentDao {
	/**
	 * 查询所有部门
	 * @return
	 */
	public List<CrmDepartment>  findAll();
	
	/**
	 * 根据id找到部门
	 * @param depId id
	 * @return  部门
	 */
	public CrmDepartment findById(String depId);
	
	/**
	 * 增加 或 更新 部门
	 * @param dept
	 */
	public void addOrEdit(CrmDepartment dept);
}
