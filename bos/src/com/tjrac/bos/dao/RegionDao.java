package com.tjrac.bos.dao;

import java.util.List;

import com.tjrac.bos.dao.base.BaseDao;
import com.tjrac.bos.domain.Region;

public interface RegionDao extends BaseDao<Region>{

	/**
	 * ģ����ѯ
	 * @param q
	 * @return
	 */
	public List<Region> findByQ(String q);

	

}
