package com.tjrac.crm.coursetype.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.coursetype.dao.CourseTypeDao;
import com.tjrac.crm.coursetype.domain.CrmCourseType;

public class CourseTypeDaoImpl extends HibernateDaoSupport implements CourseTypeDao{

	@Override
	public List<CrmCourseType> findAll() {
		return this.getHibernateTemplate().find("from CrmCourseType");
	}

	@Override
	public List<CrmCourseType> findAll(String condition, Object[] params) {
		String hql = "from CrmCourseType where 1 = 1" + condition;
		return this.getHibernateTemplate().find(hql, params);
	}

}
