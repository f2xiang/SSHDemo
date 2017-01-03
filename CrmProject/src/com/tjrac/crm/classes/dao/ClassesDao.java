package com.tjrac.crm.classes.dao;

import java.util.List;

import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.page.PageBean;

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
	
	/**
	 * ��� �� ���� �༶��Ϣ
	 * @param classes
	 */
	public void addOrUpdate(CrmClasses classes);

	public int findTotalRecord();

	public List<CrmClasses> findAll(PageBean<CrmClasses> pageBean,
			int startIndex, int pageSize);
}
