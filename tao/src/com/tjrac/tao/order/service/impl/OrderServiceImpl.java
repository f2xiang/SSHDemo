package com.tjrac.tao.order.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjrac.tao.order.dao.OrderDao;
import com.tjrac.tao.order.service.OrderService;
import com.tjrac.tao.order.vo.Order;
import com.tjrac.tao.order.vo.OrderItem;
import com.tjrac.tao.util.PageBean;

@Transactional
public class OrderServiceImpl implements OrderService{
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void save(Order order) {
		this.orderDao.save(order);
	}

	@Override
	public PageBean<Order> findByPageUid(Integer currentPage, Integer uid) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//设置每页显示 --5
		int pageCount = 5;
		pageBean.setPageCount(pageCount);
		//设置总的记录数
		int totalCount = orderDao.findTotalCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置起始
		int beginIndex = (currentPage - 1) * pageCount;
		//设置每页显示数据
		List<Order> list = orderDao.findByPageUid(uid, beginIndex, pageCount);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public Order findByOid(Integer oid) {
		return this.orderDao.findByOid(oid);
	}

	@Override
	public void updateOrder(Order findOrder) {
		this.orderDao.updateOrder(findOrder);
	}

	@Override
	public PageBean<Order> findAll(int currentPage) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//设置总的记录数
		int totalCount = this.orderDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		//设置每页显示
		int pageCount = 10;
		pageBean.setPageCount(pageCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置起始
		int beginIndex = (currentPage - 1) * pageCount;
		//设置数据
		List<Order> list = this.orderDao.findByPage(beginIndex, pageCount);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public List<OrderItem> findOrderItem(Integer oid) {
		return this.orderDao.findOrderItem(oid);
	}
	
	
}
