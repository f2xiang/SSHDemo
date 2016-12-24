package com.tjrac.crm.department.dao;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;

public interface DepartmentDao {
	public List<CrmDepartment>  findAll();
}
