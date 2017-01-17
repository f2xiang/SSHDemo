package com.tjrac.tao.order.dao;

import java.util.List;

import com.tjrac.tao.order.vo.Order;

/**
 * 订单的dao层
 * @author FengXiang
 *
 */
public interface OrderDao {

	/**
	 * 保存订单
	 * @param order
	 */
	public void save(Order order);

	/**
	 * 指定用户的订单个数查询
	 * @return
	 */
	public int findTotalCountByUid(Integer uid);

	/**
	 * 分页查询指定用户的订单数据
	 * @param uid
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<Order> findByPageUid(Integer uid, int beginIndex, int pageCount);

	/**
	 * 根据订单id查询订单
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid);

	/**
	 * 修改订单
	 * @param findOrder
	 */
	public void updateOrder(Order findOrder);

}
