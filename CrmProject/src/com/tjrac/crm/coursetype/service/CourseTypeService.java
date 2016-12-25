package com.tjrac.crm.coursetype.service;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;

public interface CourseTypeService {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<CrmCourseType> findAllCourseType();

	/**
	 * �������Ĳ�ѯ����
	 * @param courseType
	 * @return
	 */
	public List<CrmCourseType> findAllCourseType(CrmCourseType courseType);
}
