package com.tjrac.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
			this.staffDao.update("staff.update", "1", id);
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

	@Override
	public void restoreBatch(String ids) {
		String[] staffId = ids.split(",");
		for (String id : staffId) {
			this.staffDao.update("staff.update", "0", id);
		}
	}

	@Override
	public List<Staff> findAll() {
		//��ѯdeltagΪ0���ɼ�Ա
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		return this.staffDao.findByCriteria(detachedCriteria);
	}
	
	
}
