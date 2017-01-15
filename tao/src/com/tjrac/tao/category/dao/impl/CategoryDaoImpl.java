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

}
