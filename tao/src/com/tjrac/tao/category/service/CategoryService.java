package com.tjrac.tao.category.service;

import java.util.List;

import com.tjrac.tao.category.vo.Category;

/**
 * һ������ģ���service��
 * @author FengXiang
 *
 */
public interface CategoryService {

	/**
	 * ��ѯ����һ������
	 * @return
	 */
	public List<Category> findAll();

}
