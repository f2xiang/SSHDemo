package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Region;
import com.tjrac.bos.utils.PageBean;

public interface RegionService {

	/**
	 * 保存一次性导入的数据
	 * @param list
	 */
	public void saveBatch(List<Region> list);

	public void pageQuery(PageBean pageBean);

}
