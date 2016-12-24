package com.tjrac.crm.staff.service.impl;

import java.util.List;

import com.tjrac.crm.staff.dao.StaffDao;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;
import com.tjrac.crm.utils.MyMd5Util;

public class StaffServiceImpl implements StaffService{
    //注意  要用spring进行注入
	private StaffDao staffDao;
	
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
	@Override
	public CrmStaff login(CrmStaff crmStaff) {
		String staffMd5Pwd =  MyMd5Util.getMD5Value(crmStaff.getLoginPwd());
		return staffDao.find(crmStaff.getLoginName(), staffMd5Pwd);
	}

	@Override
	public List<CrmStaff> findAllStaff() {
		return staffDao.findAll();
	}

	@Override
	public CrmStaff findById(String staffId) {
		return staffDao.findById(staffId);
	}

	@Override
	public void addStaff(CrmStaff crmStaff) {
		staffDao.add(crmStaff);
	}

}
