package com.tjrac.tao.categorysecond.dao;

import java.util.List;

import com.tjrac.tao.categorysecond.vo.CategorySecond;

/**
 * �����˵������dao��
 * @author FengXiang
 *
 */
public interface CategorySecondDao {

	/**
	 * ͳ�ƶ������������
	 * @return
	 */
	public int findTotalCount();

	/**
	 * ��ҳ��ѯ�������������
	 * @param beginIndex
	 * @param pageCount
	 * @return
	 */
	public List<CategorySecond> findByPage(int beginIndex, int pageCount);

	/**
	 * ��Ӷ�������
	 * @param categorySecond
	 */
	public void add(CategorySecond categorySecond);

	/**
	 * ����id�ҵ�ָ����������
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid);

	/**
	 * ɾ����������
	 * @param findByCsid
	 */
	public void delete(CategorySecond categorySecond);

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
