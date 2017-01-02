package com.tjrac.crm.department.service;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.page.PageBean;

public interface DepartmentService {
	public List<CrmDepartment> findAll();
	
	public CrmDepartment findById(String depId);
	
	public void addOrUpdate(CrmDepartment dept);

	/**
	 * ∑÷“≥≤È—Ø
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<CrmDepartment> findAll(int pageNum, int pageSize);
}
