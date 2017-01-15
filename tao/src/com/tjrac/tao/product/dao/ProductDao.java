package com.tjrac.tao.product.dao;

import java.util.List;

import com.tjrac.tao.product.vo.Product;

/**
 * 商品的dao层
 * @author FengXiang
 *
 */
public interface ProductDao {

	/**
	 * 热门商品的查询 -- 查询10 个   条件： ishot=1
	 * @return
	 */
	public List<Product> findHot();

	/**
	 * 最新商品的查询 -- 查询10个 条件 时间降序
	 * @return
	 */
	public List<Product> findNew();

	/**
	 * 通过商品id查询商品
	 * @return
	 */
	public Product findByPid(Integer pid);

	/**
	 * 根据一级分类的id查询商品的个数
	 * @param cid
	 * @return
	 */
	public int findTotalCount(Integer cid);

	/**
	 * 根据一级分类的id来查询商品的集合
	 * @param cid
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int beginIndex,
			int pageCount);

	/**
	 * 根据二级级分类的id查询商品的个数
	 * @param csid
	 * @return
	 */
	public int findTotalCountByCsid(Integer csid);

	/**
	 * 根据二级分类的id查询分页的数据
	 * @param csid
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int beginIndex,
			int pageCount);

}
