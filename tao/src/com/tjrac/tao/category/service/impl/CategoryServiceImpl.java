package com.tjrac.tao.category.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjrac.tao.category.dao.CategoryDao;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;
/**
 * 一级分类的service实现类
 * @author FengXiang
 *
 */
@Transactional
public class CategoryServiceImpl implements CategoryService{
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	
	//----------------------------------------
	@Override
	public List<Category> findAll() {
		return this.categoryDao.findAll();
	}
	
	
}
