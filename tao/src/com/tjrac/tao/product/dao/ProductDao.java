package com.tjrac.tao.product.dao;

import java.util.List;

import com.tjrac.tao.product.vo.Product;

/**
 * ��Ʒ��dao��
 * @author FengXiang
 *
 */
public interface ProductDao {

	/**
	 * ������Ʒ�Ĳ�ѯ -- ��ѯ10 ��   ������ ishot=1
	 * @return
	 */
	public List<Product> findHot();

	/**
	 * ������Ʒ�Ĳ�ѯ -- ��ѯ10�� ���� ʱ�併��
	 * @return
	 */
	public List<Product> findNew();

	/**
	 * ͨ����Ʒid��ѯ��Ʒ
	 * @return
	 */
	public Product findByPid(Integer pid);

	/**
	 * ����һ�������id��ѯ��Ʒ�ĸ���
	 * @param cid
	 * @return
	 */
	public int findTotalCount(Integer cid);

	/**
	 * ����һ�������id����ѯ��Ʒ�ļ���
	 * @param cid
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int beginIndex,
			int pageCount);

	/**
	 * ���ݶ����������id��ѯ��Ʒ�ĸ���
	 * @param csid
	 * @return
	 */
	public int findTotalCountByCsid(Integer csid);

	/**
	 * ���ݶ��������id��ѯ��ҳ������
	 * @param csid
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int beginIndex,
			int pageCount);

}
