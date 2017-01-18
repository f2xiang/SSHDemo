package com.tjrac.tao.categorysecond.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.tao.categorysecond.dao.CategorySecondDao;
import com.tjrac.tao.categorysecond.vo.CategorySecond;
import com.tjrac.tao.util.PageHibernateCallback;

public class CategorySecondDaoImpl extends HibernateDaoSupport implements CategorySecondDao{

	@Override
	public int findTotalCount() {
		String hql = "select count(*) from CategorySecond ";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	@Override
	public List<CategorySecond> findByPage(int beginIndex, int pageCount) {
		String hql = "from CategorySecond order by csid desc";
		return this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, beginIndex, pageCount));
	}

	@Override
	public void add(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	@Override
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	@Override
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	@Override
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	@Override
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
