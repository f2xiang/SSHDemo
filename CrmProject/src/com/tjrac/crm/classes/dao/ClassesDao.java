package com.tjrac.crm.classes.dao;

import java.util.List;

import com.tjrac.crm.classes.domain.CrmClasses;

public interface ClassesDao {
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<CrmClasses> findAll();
	
	/**
	 * ͨ��id��ѯ
	 * @param classId
	 * @return
	 */
	public CrmClasses findById(String classId);
}
