package com.tjrac.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.SubareaDao;
import com.tjrac.bos.domain.Subarea;
import com.tjrac.bos.service.SubareaService;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService{
	
	@Resource
	private SubareaDao subareaDao;

	@Override
	public void add(Subarea model) {
		this.subareaDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		this.subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return this.subareaDao.findAll();
	}
	
	
}
