package com.tjrac.crm.department.service.impl;

import java.util.List;

import com.tjrac.crm.department.dao.DepartmentDao;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.page.PageBean;

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

	@Override
	public PageBean<CrmDepartment> findAll(int pageNum, int pageSize) {
		
		//总记录数
		int totalRecord  = departmentDao.findTotalRecord();
		
		//创建对象
		PageBean<CrmDepartment> pageBean = new PageBean<CrmDepartment>(pageNum, pageSize, totalRecord);
		
		//分页数据
		List<CrmDepartment> data = departmentDao.findAll(pageBean.getStartIndex(), pageBean.getPageSize());
		
		pageBean.setData(data);
		
		return pageBean;
	}
	
	

}
