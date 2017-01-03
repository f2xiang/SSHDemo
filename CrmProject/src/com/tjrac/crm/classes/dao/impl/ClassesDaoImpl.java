package com.tjrac.crm.classes.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.classes.dao.ClassesDao;
import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.page.PageBean;

public class ClassesDaoImpl extends HibernateDaoSupport implements ClassesDao {

	@Override
	public List<CrmClasses> findAll() {
		return this.getHibernateTemplate().find("from CrmClasses");
	}

	@Override
	public CrmClasses findById(String classId) {
		return this.getHibernateTemplate().get(CrmClasses.class, classId);
	}

	@Override
	public void addOrUpdate(CrmClasses classes) {
		this.getHibernateTemplate().saveOrUpdate(classes);
		
	}

	@Override
	public int findTotalRecord() {
		String hql = "select count(c) from CrmClasses c";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	@Override
	public List<CrmClasses> findAll(PageBean<CrmClasses> pageBean,
			int startIndex, int pageSize) {
		String hql = "from CrmClasses";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
}
