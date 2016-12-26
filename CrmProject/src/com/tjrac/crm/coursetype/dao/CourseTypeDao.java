package com.tjrac.crm.coursetype.dao;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;

public interface CourseTypeDao {
	/**
	 * ��ѯ�γ���������
	 * @return
	 */
	public List<CrmCourseType> findAll();

	/**
	 * ������ѯ
	 * @param condition
	 * @param params
	 * @return
	 */
	public List<CrmCourseType> findAll(String condition, Object[] params);
	
	/**
	 * ͨ��id���ҿγ����
	 * @param courseTypeId �γ����
	 * @return
	 */
	public CrmCourseType findById(String courseTypeId);
	
	/**
	 * ���� ���� ���� �γ����
	 * @param courseType
	 */
	public void saveOrUpdate(CrmCourseType courseType);
}
