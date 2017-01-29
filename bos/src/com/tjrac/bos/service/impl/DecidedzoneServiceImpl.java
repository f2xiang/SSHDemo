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
		
		//���һ������һ��
		for (String sid : subareaid) {   
			Subarea subarea = subareaDao.findById(sid);  //�־ö���  �Զ��������ݿ�
			subarea.setDecidedzone(model);
		}
		
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		this.decidedzoneDao.pageQuery(pageBean);
	}
	
	
	
}
