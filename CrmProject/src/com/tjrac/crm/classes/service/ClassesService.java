package com.tjrac.crm.classes.service;

import java.util.List;

import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.page.PageBean;

public interface ClassesService {
	public List<CrmClasses> findAll();
	
	public CrmClasses findById(String classid);

	public void updateUpload(CrmClasses classes);
	
	public void addOrUpdate(CrmClasses classes);

	/**
	 * ∑÷“≥≤È—Ø
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<CrmClasses> findAll(int pageNum, int pageSize);
}
