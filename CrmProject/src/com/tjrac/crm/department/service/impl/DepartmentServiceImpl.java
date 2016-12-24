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
		return departmentDao.findAll();
	}
	
	

}
