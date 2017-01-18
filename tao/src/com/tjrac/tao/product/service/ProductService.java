package com.tjrac.tao.product.service;

import java.util.List;

import com.tjrac.tao.product.vo.Product;
import com.tjrac.tao.util.PageBean;

/**
 * 商品的service层
 * @author FengXiang
 *
 */
public interface ProductService {

	/**
	 * 热门商品的查询
	 * @return 
	 */
	public	List<Product> findHot();

	/**
	 * 最新商品的查询
	 * @return
	 */
	public List<Product> findNew();

	/**
	 * 根据商品id查看商品详细信息
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid);

	/**
	 * 根据一级分类的id查询分页的商品数据
	 * @param cid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findByPageCid(Integer cid, int currentPage);

	/**
	 * 根据二级分类的id查询分页的商品数据
	 * @param csid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findByPageCsid(Integer csid, int currentPage);

	/**
	 * 分页查询所有商品
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findAll(int currentPage);

	/**
	 * 添加一个商品
	 * @param product
	 */
	public void add(Product product);

	
	/**
	 * 更新商品的信息
	 * @param product
	 */
	public void update(Product product);

	/**
	 * 删除商品
	 * @param product
	 */
	public void delete(Product product);
	
	

}
