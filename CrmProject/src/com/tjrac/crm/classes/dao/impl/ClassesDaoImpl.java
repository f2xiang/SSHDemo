package com.tjrac.crm.classes.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.classes.dao.ClassesDao;
import com.tjrac.crm.classes.domain.CrmClasses;

public class ClassesDaoImpl extends HibernateDaoSupport implements ClassesDao {

	@Override
	public List<CrmClasses> findAll() {
		return this.getHibernateTemplate().find("from CrmClasses");
	}

	@Override
	public CrmClasses findById(String classId) {
		return this.getHibernateTemplate().get(CrmClasses.class, classId);
	}
	
}
