package com.tjrac.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import com.tjrac.bos.utils.PageBean;

/**
 * 持久层通用方法
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
	
	//通用修改方法
	public void update(String queryName, Object ... objects);
	
	/**
	 * 通用的分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
}
