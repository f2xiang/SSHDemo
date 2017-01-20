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
		//���õ�ǰҳ
		pageBean.setCurrentPage(currentPage);
		//����ÿҳ��ʾ --5
		int pageCount = 5;
		pageBean.setPageCount(pageCount);
		//�����ܵļ�¼��
		int totalCount = orderDao.findTotalCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		//�����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//������ʼ
		int beginIndex = (currentPage - 1) * pageCount;
		//����ÿҳ��ʾ����
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
		//���õ�ǰҳ
		pageBean.setCurrentPage(currentPage);
		//�����ܵļ�¼��
		int totalCount = this.orderDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		//����ÿҳ��ʾ
		int pageCount = 10;
		pageBean.setPageCount(pageCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//������ʼ
		int beginIndex = (currentPage - 1) * pageCount;
		//��������
		List<Order> list = this.orderDao.findByPage(beginIndex, pageCount);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public List<OrderItem> findOrderItem(Integer oid) {
		return this.orderDao.findOrderItem(oid);
	}
	
	
}
