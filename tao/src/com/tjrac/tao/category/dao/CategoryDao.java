package com.tjrac.tao.category.dao;

import java.util.List;

import com.tjrac.tao.category.vo.Category;

public interface CategoryDao {

	/**
	 * ��ѯ����һ������
	 * @return
	 */
	public List<Category> findAll();

}
