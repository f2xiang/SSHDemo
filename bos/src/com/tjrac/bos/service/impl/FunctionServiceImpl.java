package com.tjrac.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.FunctionDao;
import com.tjrac.bos.domain.Function;
import com.tjrac.bos.service.FunctionService;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService{
	
	@Resource
	private FunctionDao functionDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		this.functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findAll() {
		return this.functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		Function function = model.getFunction();
		if(function != null && function.getId().equals("")){
			model.setFunction(null);
		}
		this.functionDao.save(model);
	}
	
	
	
}
