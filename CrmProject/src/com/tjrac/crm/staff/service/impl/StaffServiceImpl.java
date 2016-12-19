package com.tjrac.crm.staff.service.impl;

import com.tjrac.crm.staff.dao.StaffDao;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;

public class StaffServiceImpl implements StaffService{
    //ע��  Ҫ��spring����ע��
	private StaffDao staffDao;
	
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
	@Override
	public CrmStaff login(CrmStaff crmStaff) {
		return staffDao.find(crmStaff.getLoginName(), crmStaff.getLoginPwd());
	}

}
