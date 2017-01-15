package com.tjrac.tao.product.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.tao.product.dao.ProductDao;
import com.tjrac.tao.product.vo.Product;
import com.tjrac.tao.util.PageHibernateCallback;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao{

	@Override
	public List<Product> findHot() {
		
		//采用离线条件查询   
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//查询条件 is_hot = 1 --是热门商品
		criteria.add(Restrictions.eq("is_hot", 1));
		
		//排序输出   按照时间的顺序 降序排序   时间从大到小
		criteria.addOrder(Order.desc("pdate"));
		
		//查询10个  采用分页 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	
	@Override
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		criteria.addOrder(Order.desc("pdate"));
		
		return this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
	}


	@Override
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}


	@Override
	public int findTotalCount(Integer cid) {
		String hql = "select count(p) from Product p where p.categorySecond.category.cid = ? "; 
		List<Long> list =  this.getHibernateTemplate().find(hql, cid);
		return list.get(0).intValue();
	}


	@Override
	public List<Product> findByPageCid(Integer cid, int beginIndex,
			int pageCount) {
		//TODO BUG!!!
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//分页
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, beginIndex, pageCount));
	}

}
