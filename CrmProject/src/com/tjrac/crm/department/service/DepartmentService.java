package com.tjrac.crm.department.service;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;

public interface DepartmentService {
	public List<CrmDepartment> findAll();
	
	public CrmDepartment findById(String depId);
	
	public void addOrUpdate(CrmDepartment dept);
}
