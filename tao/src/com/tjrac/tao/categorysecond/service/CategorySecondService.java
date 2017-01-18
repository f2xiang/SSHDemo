package com.tjrac.tao.categorysecond.service;

import java.util.List;

import com.tjrac.tao.categorysecond.vo.CategorySecond;
import com.tjrac.tao.util.PageBean;

/**
 * 二级菜单管理的service层
 * @author FengXiang
 *
 */
public interface CategorySecondService {

	/**
	 * 分页查询二级分类
	 * @param currentPage
	 * @return
	 */
	public PageBean<CategorySecond> findAllByPage(Integer currentPage);

	/**
	 * 添加一个二级分类
	 * @param categorySecond
	 */
	public void add(CategorySecond categorySecond);

	/**
	 * 删除二级分类
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond);

	/**
	 * 根据id查询二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid);

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
