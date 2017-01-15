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

}
