package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Subarea;
import com.tjrac.bos.utils.PageBean;

public interface SubareaService {

	/**
	 * ��ӷ���
	 * @param model
	 */
	public void add(Subarea model);

	/**
	 * ������ҳ��ѯ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Subarea> findAll();

}
