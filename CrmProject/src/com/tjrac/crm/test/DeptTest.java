package com.tjrac.crm.test;

import java.util.List;

import org.junit.Test;

import com.tjrac.crm.coursetype.dao.CourseTypeDao;
import com.tjrac.crm.coursetype.dao.impl.CourseTypeDaoImpl;
import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.department.dao.DepartmentDao;
import com.tjrac.crm.department.dao.impl.DepartmentDaoImpl;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.department.service.impl.DepartmentServiceImpl;

public class DeptTest {
	@Test
	public void testDept(){
		DepartmentService departmentService = new DepartmentServiceImpl();
		List<CrmDepartment> allDept =  departmentService.findAll();
		System.out.println(allDept);
	}
	@Test
	public void testDeptdao(){
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		List<CrmDepartment> allDept =  departmentDao.findAll();
		System.out.println(allDept);
	}
	@Test
	public void testCourse(){
		CourseTypeDao courseTypeDao = new CourseTypeDaoImpl();
		List<CrmCourseType> allCour =  courseTypeDao.findAll();
		System.out.println(allCour);
	}
}
