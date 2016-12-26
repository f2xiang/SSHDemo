package com.tjrac.crm.coursetype.dao;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;

public interface CourseTypeDao {
	/**
	 * 查询课程类别的所有
	 * @return
	 */
	public List<CrmCourseType> findAll();

	/**
	 * 条件查询
	 * @param condition
	 * @param params
	 * @return
	 */
	public List<CrmCourseType> findAll(String condition, Object[] params);
	
	/**
	 * 通过id查找课程类别
	 * @param courseTypeId 课程类别
	 * @return
	 */
	public CrmCourseType findById(String courseTypeId);
	
	/**
	 * 保存 或者 更新 课程类别
	 * @param courseType
	 */
	public void saveOrUpdate(CrmCourseType courseType);
}
