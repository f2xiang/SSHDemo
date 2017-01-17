package com.tjrac.tao.category.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.tao.category.dao.CategoryDao;
import com.tjrac.tao.category.vo.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao{

	@Override
	public List<Category> findAll() {
		String hql = "from Category";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	@Override
	public void delteByCid(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	@Override
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	@Override
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
