package com.tjrac.tao.product.service;

import java.util.List;

import com.tjrac.tao.product.vo.Product;
import com.tjrac.tao.util.PageBean;

/**
 * ��Ʒ��service��
 * @author FengXiang
 *
 */
public interface ProductService {

	/**
	 * ������Ʒ�Ĳ�ѯ
	 * @return 
	 */
	public	List<Product> findHot();

	/**
	 * ������Ʒ�Ĳ�ѯ
	 * @return
	 */
	public List<Product> findNew();

	/**
	 * ������Ʒid�鿴��Ʒ��ϸ��Ϣ
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid);

	/**
	 * ����һ�������id��ѯ��ҳ����Ʒ����
	 * @param cid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findByPageCid(Integer cid, int currentPage);

	/**
	 * ���ݶ��������id��ѯ��ҳ����Ʒ����
	 * @param csid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findByPageCsid(Integer csid, int currentPage);

	/**
	 * ��ҳ��ѯ������Ʒ
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findAll(int currentPage);

	/**
	 * ���һ����Ʒ
	 * @param product
	 */
	public void add(Product product);

	
	/**
	 * ������Ʒ����Ϣ
	 * @param product
	 */
	public void update(Product product);

	/**
	 * ɾ����Ʒ
	 * @param product
	 */
	public void delete(Product product);
	
	

}
