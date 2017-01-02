package com.tjrac.crm.department.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.department.dao.DepartmentDao;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.page.PageHibernateCallback;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao{

	@Override
	public List<CrmDepartment> findAll() {
		return this.getHibernateTemplate().find("from CrmDepartment");
	}

	
	@Override
	public CrmDepartment findById(String depId) {
		return this.getHibernateTemplate().get(CrmDepartment.class, depId); 		
	}


	@Override
	public void addOrEdit(CrmDepartment dept) {
		this.getHibernateTemplate().saveOrUpdate(dept);
	}


	@Override
	public int findTotalRecord() {
		String hql = "select count(c) from CrmDepartment c";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}


	@Override
	public List<CrmDepartment> findAll(int startIndex, int pageSize) {
		
		String hql = "from CrmDepartment";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
		
//		return this.getHibernateTemplate().execute(
//				new PageHibernateCallback<CrmDepartment>().setHql(hql).setStartIndex(startIndex).setPageSize(pageSize));
	}

	
}
