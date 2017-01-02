package com.tjrac.crm.classes.service.impl;

import java.util.List;

import com.tjrac.crm.classes.dao.ClassesDao;
import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.classes.service.ClassesService;

public class ClassesServiceImpl implements ClassesService{

	private ClassesDao classesDao;
	
	public void setClassesDao(ClassesDao classesDao) {
		this.classesDao = classesDao;
	}
	
	@Override
	public List<CrmClasses> findAll() {
		return classesDao.findAll();
	}

	@Override
	public CrmClasses findById(String classid) {
		return classesDao.findById(classid);
	}

	@Override
	public void updateUpload(CrmClasses classes) {
		//先查询  再更新  -- 快照 和 一级缓存
		CrmClasses findClass = this.classesDao.findById(classes.getClassId());
		findClass.setUploadFilename(classes.getUploadFilename());
		findClass.setUploadPath(classes.getUploadPath());
		findClass.setUploadTime(classes.getUploadTime());
	}
	
	

}
