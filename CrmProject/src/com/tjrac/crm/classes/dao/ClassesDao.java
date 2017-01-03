package com.tjrac.crm.classes.dao;

import java.util.List;

import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.page.PageBean;

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
	
	/**
	 * 添加 或 更新 班级信息
	 * @param classes
	 */
	public void addOrUpdate(CrmClasses classes);

	public int findTotalRecord();

	public List<CrmClasses> findAll(PageBean<CrmClasses> pageBean,
			int startIndex, int pageSize);
}
