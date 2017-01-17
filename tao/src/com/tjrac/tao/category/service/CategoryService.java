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

	/**
	 * ����һ������
	 * @param category
	 */
	public void save(Category category);

	/**
	 * ����idɾ��һ��һ������
	 * @param cid
	 */
	public void deleteByCid(Integer cid);

	/**
	 * ����id��ѯһ������
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
