package com.tjrac.tao.order.service;

import com.tjrac.tao.order.vo.Order;
import com.tjrac.tao.util.PageBean;

/**
 * 订单模块的service层
 * @author FengXiang
 *
 */
public interface OrderService {

	/**
	 * 保存订单
	 * @param order
	 */
	public void save(Order order);

	/**
	 * 我的订单  分页查询
	 * @param currentPage
	 * @param uid
	 * @return
	 */
	public PageBean<Order> findByPageUid(Integer currentPage, Integer uid);

	/**
	 * 根据订单id查询订单
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid);

	/**
	 * 修改订单信息
	 * @param findOrder
	 */
	public void updateOrder(Order findOrder);

}
