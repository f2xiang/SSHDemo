package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Region;
import com.tjrac.bos.utils.PageBean;

public interface RegionService {

	/**
	 * ����һ���Ե��������
	 * @param list
	 */
	public void saveBatch(List<Region> list);

	/**
	 * ��ҳ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Region> findAll();

	/**
	 * ģ����ѯ
	 * @param q
	 * @return
	 */
	public List<Region> findByQ(String q);

}
