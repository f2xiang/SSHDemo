package com.tjrac.tao.order.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.tao.order.dao.OrderDao;
import com.tjrac.tao.order.vo.Order;
import com.tjrac.tao.util.PageHibernateCallback;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao{

	@Override
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	@Override
	public int findTotalCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		return list.get(0).intValue();
	}

	@Override
	public List<Order> findByPageUid(Integer uid, int beginIndex, int pageCount) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, beginIndex, pageCount));
	}

	@Override
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	@Override
	public void updateOrder(Order findOrder) {
		this.getHibernateTemplate().update(findOrder);
	}

}
