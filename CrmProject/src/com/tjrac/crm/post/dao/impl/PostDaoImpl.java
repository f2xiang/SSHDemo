package com.tjrac.crm.post.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.post.dao.PostDao;
import com.tjrac.crm.post.domain.CrmPost;

public class PostDaoImpl extends HibernateDaoSupport implements PostDao{

	@Override
	public List<CrmPost> findPostByDept(CrmDepartment dept) {
		return this.getHibernateTemplate().find("from CrmPost where department = ?", dept);
	}

	@Override
	public List<CrmPost> findAll() {
		return this.getHibernateTemplate().find("from CrmPost");
	}

	@Override
	public CrmPost findById(String postId) {
		return this.getHibernateTemplate().get(CrmPost.class, postId);
	}

	@Override
	public void addOrUpdate(CrmPost post) {
		this.getHibernateTemplate().saveOrUpdate(post);
	}

	@Override
	public int findTotalRecord() {
		String hql = "select count(c) from CrmPost c";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	@Override
	public List<CrmPost> findAll(int startIndex, int pageSize) {
		String hql = "from CrmPost";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}
}
