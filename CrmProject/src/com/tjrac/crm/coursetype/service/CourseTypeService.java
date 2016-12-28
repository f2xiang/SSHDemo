package com.tjrac.crm.coursetype.service;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.page.PageBean;

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
	
	/**
	 * ����id���ҿγ����
	 * @param courseTypeId
	 * @return
	 */
	public CrmCourseType findById(String courseTypeId);
	
	/**
	 * ������� ���¿γ����
	 * @param courseType
	 */
	public void addOrEdit(CrmCourseType courseType);
	
	/**
	 * �������ķ�ҳ��ѯ����
	 * @param courseType  ����
	 * @param pageNum   ��ǰҳ
	 * @param pageSize   ÿҳ��ʾ����
	 * @return
	 */
	public PageBean<CrmCourseType> findAll(CrmCourseType courseType, int pageNum, int pageSize);
}
