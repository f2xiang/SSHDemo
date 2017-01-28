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

	public void pageQuery(PageBean pageBean);

}
