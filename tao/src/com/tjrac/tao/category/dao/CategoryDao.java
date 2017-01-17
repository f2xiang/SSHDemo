package com.tjrac.tao.category.dao;

import java.util.List;

import com.tjrac.tao.category.vo.Category;

public interface CategoryDao {

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
	 * 删除一个一级分类
	 * @param category
	 */
	public void delteByCid(Category category);
	
	/**
	 * 根据id查询一个一级分类
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
