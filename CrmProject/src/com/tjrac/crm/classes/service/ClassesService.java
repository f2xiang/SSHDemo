package com.tjrac.crm.classes.service;

import java.util.List;

import com.tjrac.crm.classes.domain.CrmClasses;

public interface ClassesService {
	public List<CrmClasses> findAll();
	
	public CrmClasses findById(String classid);

	public void updateUpload(CrmClasses classes);
}
