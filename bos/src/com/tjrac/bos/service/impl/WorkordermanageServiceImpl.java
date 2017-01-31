package com.tjrac.bos.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.WorkordermanageDao;
import com.tjrac.bos.domain.Workordermanage;
import com.tjrac.bos.service.WorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService{

	@Resource
	private WorkordermanageDao workordermanageDao;
	
	@Override
	public void save(Workordermanage model) {
		model.setUpdatetime(new Date());
		this.workordermanageDao.save(model);
	}

	@Override
	public List<Workordermanage> findAll() {
		return this.workordermanageDao.findAll();
	}

}
