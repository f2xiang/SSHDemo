package com.tjrac.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.RegionDao;
import com.tjrac.bos.domain.Region;
import com.tjrac.bos.service.RegionService;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{
	
	@Resource
	private RegionDao regionDao;


	@Override
	public void saveBatch(List<Region> list) {
		for (Region region : list) {
			this.regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		this.regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return this.regionDao.findAll();
	}

	@Override
	public List<Region> findByQ(String q) {
		return this.regionDao.findByQ(q);
	}
	
	
}
