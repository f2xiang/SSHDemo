package com.tjrac.tao.category.service;

import java.util.List;

import com.tjrac.tao.category.vo.Category;

/**
 * 一级分类模块的service层
 * @author FengXiang
 *
 */
public interface CategoryService {

	/**
	 * 查询所有一级分类
	 * @return
	 */
	public List<Category> findAll();

	/**
	 * 保存一级分类
	 * @param category
	 */
	public void save(Category category);

	/**
	 * 根据id删除一个一级分类
	 * @param cid
	 */
	public void deleteByCid(Integer cid);

	/**
	 * 根据id查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid);

	/**
	 * 更新一级分类的信息
	 * @param category
	 */
	public void update(Category category);

}
