package com.tjrac.crm.department.dao;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;


public interface DepartmentDao {
	/**
	 * ��ѯ���в���
	 * @return
	 */
	public List<CrmDepartment>  findAll();
	
	/**
	 * ����id�ҵ�����
	 * @param depId id
	 * @return  ����
	 */
	public CrmDepartment findById(String depId);
	
	/**
	 * ���� �� ���� ����
	 * @param dept
	 */
	public void addOrEdit(CrmDepartment dept);
}
