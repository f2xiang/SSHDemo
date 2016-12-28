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

	/**
	 * ��ҳ  ����������ѯ������ �õ����ݵ��ܼ�¼��
	 * @param condition  ����
	 * @param params
	 * @return  �������ݵļ�¼��
	 */
	public int getTotalRecord(String condition, Object[] params);

	/**
	 * ��ҳ  ��ѯ���
	 * @param condition ����
	 * @param params   ��������
	 * @param startIndex  ��ҳ��ʼ
	 * @param pageSize    ÿҳ������
	 * @return      ĳҳ������
	 */
	public List<CrmCourseType> findAll(String condition, Object[] params,
			int startIndex, int pageSize);
}
