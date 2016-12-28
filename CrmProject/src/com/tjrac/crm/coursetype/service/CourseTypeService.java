package com.tjrac.crm.coursetype.service;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.page.PageBean;

public interface CourseTypeService {
	/**
	 * 查询所有
	 * @return
	 */
	public List<CrmCourseType> findAllCourseType();

	/**
	 * 带条件的查询所有
	 * @param courseType
	 * @return
	 */
	public List<CrmCourseType> findAllCourseType(CrmCourseType courseType);
	
	/**
	 * 根据id查找课程类别
	 * @param courseTypeId
	 * @return
	 */
	public CrmCourseType findById(String courseTypeId);
	
	/**
	 * 保存或者 更新课程类别
	 * @param courseType
	 */
	public void addOrEdit(CrmCourseType courseType);
	
	/**
	 * 带条件的分页查询所有
	 * @param courseType  条件
	 * @param pageNum   当前页
	 * @param pageSize   每页显示个数
	 * @return
	 */
	public PageBean<CrmCourseType> findAll(CrmCourseType courseType, int pageNum, int pageSize);
}
