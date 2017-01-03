package com.tjrac.crm.classes.service.impl;

import java.util.List;

import com.tjrac.crm.classes.dao.ClassesDao;
import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.classes.service.ClassesService;
import com.tjrac.crm.page.PageBean;

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

	@Override
	public void addOrUpdate(CrmClasses classes) {
		this.classesDao.addOrUpdate(classes);
	}

	@Override
	public PageBean<CrmClasses> findAll(int pageNum, int pageSize) {
		//总记录数
		int totalReocrd = this.classesDao.findTotalRecord();
		
		//创建对象
		PageBean<CrmClasses> pageBean = new PageBean<CrmClasses>(pageNum, pageSize, totalReocrd);
		
		//分页数据
		List<CrmClasses> data = this.classesDao.findAll(pageBean, pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);
		
		return pageBean;
	}
	
	

}
