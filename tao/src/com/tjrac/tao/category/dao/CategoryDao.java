package com.tjrac.tao.category.dao;

import java.util.List;

import com.tjrac.tao.category.vo.Category;

public interface CategoryDao {

	/**
	 * 查询所有一级分类
	 * @return
	 */
	public List<Category> findAll();

}
