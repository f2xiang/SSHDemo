package com.tjrac.tao.categorysecond.service;

import java.util.List;

import com.tjrac.tao.categorysecond.vo.CategorySecond;
import com.tjrac.tao.util.PageBean;

/**
 * �����˵������service��
 * @author FengXiang
 *
 */
public interface CategorySecondService {

	/**
	 * ��ҳ��ѯ��������
	 * @param currentPage
	 * @return
	 */
	public PageBean<CategorySecond> findAllByPage(Integer currentPage);

	/**
	 * ���һ����������
	 * @param categorySecond
	 */
	public void add(CategorySecond categorySecond);

	/**
	 * ɾ����������
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond);

	/**
	 * ����id��ѯ��������
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid);

	/**
	 * ���¶�������
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond);

	/**
	 * ��ѯ���еĶ�������
	 * @return
	 */
	public List<CategorySecond> findAll();

}
