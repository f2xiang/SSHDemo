package com.tjrac.crm.coursetype.service;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;

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
}
