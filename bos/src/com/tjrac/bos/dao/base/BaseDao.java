package com.tjrac.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import com.tjrac.bos.utils.PageBean;

/**
 * �־ò�ͨ�÷���
 * @author FengXiang
 *
 * @param <T>
 */
public interface BaseDao<T> {
	public void save(T entity);
	
	public void delete(T entity);
	
	public void update(T entity);
	
	public T findById(Serializable id);
	
	public List<T> findAll();
	
	//ͨ���޸ķ���
	public void update(String queryName, Object ... objects);
	
	/**
	 * ͨ�õķ�ҳ��ѯ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
}
