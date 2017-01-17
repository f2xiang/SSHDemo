package com.tjrac.tao.order.service;

import com.tjrac.tao.order.vo.Order;
import com.tjrac.tao.util.PageBean;

/**
 * ����ģ���service��
 * @author FengXiang
 *
 */
public interface OrderService {

	/**
	 * ���涩��
	 * @param order
	 */
	public void save(Order order);

	/**
	 * �ҵĶ���  ��ҳ��ѯ
	 * @param currentPage
	 * @param uid
	 * @return
	 */
	public PageBean<Order> findByPageUid(Integer currentPage, Integer uid);

	/**
	 * ���ݶ���id��ѯ����
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid);

	/**
	 * �޸Ķ�����Ϣ
	 * @param findOrder
	 */
	public void updateOrder(Order findOrder);

}
