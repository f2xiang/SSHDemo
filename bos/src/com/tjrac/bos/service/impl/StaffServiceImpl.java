package com.tjrac.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.StaffDao;
import com.tjrac.bos.domain.Staff;
import com.tjrac.bos.service.StaffService;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
	
	@Resource
	private StaffDao staffDao;
	
	

	@Override
	public void add(Staff model) {
		this.staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		this.staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		String[] staffId = ids.split(",");
		for (String id : staffId) {
			this.staffDao.update("staff.delete", id);
		}
	}

	@Override
	public void update(Staff staff) {
		this.staffDao.update(staff);
	}

	@Override
	public Staff findById(String id) {
		return this.staffDao.findById(id);
	}
	
	
}
