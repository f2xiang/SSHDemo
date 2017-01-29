package com.tjrac.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.DecidedzoneDao;
import com.tjrac.bos.dao.SubareaDao;
import com.tjrac.bos.domain.Decidedzone;
import com.tjrac.bos.domain.Subarea;
import com.tjrac.bos.service.DecidedzoneService;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{
	@Resource
	private DecidedzoneDao decidedzoneDao;
	
	@Resource
	private SubareaDao subareaDao;

	@Override
	public void add(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save(model);
		
		//多的一方关联一方
		for (String sid : subareaid) {   
			Subarea subarea = subareaDao.findById(sid);  //持久对象  自动更新数据库
			subarea.setDecidedzone(model);
		}
		
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		this.decidedzoneDao.pageQuery(pageBean);
	}
	
	
	
}
