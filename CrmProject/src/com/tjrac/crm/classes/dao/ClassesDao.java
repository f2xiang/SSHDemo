package com.tjrac.crm.classes.dao;

import java.util.List;

import com.tjrac.crm.classes.domain.CrmClasses;

public interface ClassesDao {
	/**
	 * 查询所有
	 * @return
	 */
	public List<CrmClasses> findAll();
	
	/**
	 * 通过id查询
	 * @param classId
	 * @return
	 */
	public CrmClasses findById(String classId);
}
