package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Subarea;
import com.tjrac.bos.utils.PageBean;

public interface SubareaService {

	/**
	 * 添加分区
	 * @param model
	 */
	public void add(Subarea model);

	/**
	 * 分区分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 查询所有
	 * @return
	 */
	public List<Subarea> findAll();

}
