package com.tjrac.tao.category.dao;

import java.util.List;

import com.tjrac.tao.category.vo.Category;

public interface CategoryDao {

	/**
	 * ��ѯ����һ������
	 * @return
	 */
	public List<Category> findAll();

	/**
	 * ����һ������
	 * @param category
	 */
	public void save(Category category);

	/**
	 * ɾ��һ��һ������
	 * @param category
	 */
	public void delteByCid(Category category);
	
	/**
	 * ����id��ѯһ��һ������
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid);

	/**
	 * ����һ���������Ϣ
	 * @param category
	 */
	public void update(Category category);

}
