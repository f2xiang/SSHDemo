package com.tjrac.crm.department.service.impl;

import java.util.List;

import com.tjrac.crm.department.dao.DepartmentDao;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public List<CrmDepartment> findAll() {
		return this.departmentDao.findAll();
	}

	@Override
	public CrmDepartment findById(String depId) {
		return this.departmentDao.findById(depId);
	}

	@Override
	public void addOrUpdate(CrmDepartment dept) {
		this.departmentDao.addOrEdit(dept);
	}
	
	

}
