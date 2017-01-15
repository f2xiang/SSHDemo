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
		
		//��������������ѯ   
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//��ѯ���� is_hot = 1 --��������Ʒ
		criteria.add(Restrictions.eq("is_hot", 1));
		
		//�������   ����ʱ���˳�� ��������   ʱ��Ӵ�С
		criteria.addOrder(Order.desc("pdate"));
		
		//��ѯ10��  ���÷�ҳ ִ�в�ѯ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	
	@Override
	public List<Product> findNew() {
		//ʹ������������ѯ
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
		//��ҳ
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, beginIndex, pageCount));
	}

}
