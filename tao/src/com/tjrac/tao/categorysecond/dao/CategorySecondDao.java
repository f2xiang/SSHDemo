package com.tjrac.tao.categorysecond.dao;

import java.util.List;

import com.tjrac.tao.categorysecond.vo.CategorySecond;

/**
 * 二级菜单管理的dao层
 * @author FengXiang
 *
 */
public interface CategorySecondDao {

	/**
	 * 统计二级分类的数据
	 * @return
	 */
	public int findTotalCount();

	/**
	 * 分页查询二级分类的数据
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<CategorySecond> findByPage(int beginIndex, int pageCount);

	/**
	 * 添加二级分类
	 * @param categorySecond
	 */
	public void add(CategorySecond categorySecond);

	/**
	 * 根据id找到指定二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid);

	/**
	 * 删除二级分类
	 * @param findByCsid
	 */
	public void delete(CategorySecond categorySecond);

	/**
	 * 更新二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond);

	/**
	 * 查询所有的二级分类
	 * @return
	 */
	public List<CategorySecond> findAll();

}
