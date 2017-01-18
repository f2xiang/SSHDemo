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
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//��ҳ
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, beginIndex, pageCount));
	}


	@Override
	public int findTotalCountByCsid(Integer csid) {
		String hql = "select count(p) from Product p where p.categorySecond.csid = ? "; 
		List<Long> list =  this.getHibernateTemplate().find(hql, csid);
		return list.get(0).intValue();
	}


	@Override
	public List<Product> findByPageCsid(Integer csid, int beginIndex,
			int pageCount) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		//��ҳ
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, beginIndex, pageCount));
	}


	@Override
	public int findTotalCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}


	@Override
	public List<Product> findAllByPage(int beginIndex, int pageCount) {
		String hql = "from Product order by pdate desc";
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, beginIndex, pageCount));
	}


	@Override
	public void add(Product product) {
		this.getHibernateTemplate().save(product);
	}


	@Override
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}


	@Override
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

}
