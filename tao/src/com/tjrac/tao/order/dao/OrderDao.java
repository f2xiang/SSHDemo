package com.tjrac.tao.order.dao;

import java.util.List;

import com.tjrac.tao.order.vo.Order;

/**
 * ������dao��
 * @author FengXiang
 *
 */
public interface OrderDao {

	/**
	 * ���涩��
	 * @param order
	 */
	public void save(Order order);

	/**
	 * ָ���û��Ķ���������ѯ
	 * @return
	 */
	public int findTotalCountByUid(Integer uid);

	/**
	 * ��ҳ��ѯָ���û��Ķ�������
	 * @param uid
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<Order> findByPageUid(Integer uid, int beginIndex, int pageCount);

	/**
	 * ���ݶ���id��ѯ����
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid);

	/**
	 * �޸Ķ���
	 * @param findOrder
	 */
	public void updateOrder(Order findOrder);

}
