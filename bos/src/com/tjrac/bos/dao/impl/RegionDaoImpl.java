package com.tjrac.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjrac.bos.dao.RegionDao;
import com.tjrac.bos.dao.base.impl.BaseDaoImpl;
import com.tjrac.bos.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao{

	@Override
	public List<Region> findByQ(String q) {
		String hql = "from Region where province like ? or city like ? or district like ?";
		return this.getHibernateTemplate().find(hql, "%"+q+"%", "%"+q+"%", "%"+q+"%");
	}

}
