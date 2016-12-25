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

	@Override
	public void updateStaff(CrmStaff crmStaff) {
		//这里呢 遇到这么一个问题   就是我们不知道用户有没有修改了他的密码(默认32位)
		//所以 这里我们采取这么一个方案：
		//先查询  通过id 查询出用户的密码 和 页面上的进行比较  如果 一致  说明没有修改密码
		//如果不一致  说明 修改了密码了 要重新进行MD5的加密
		//把该id的员工的数据  全部设置
		
		//1、根据id把承载原始数据的员工实体找到
		CrmStaff findStaff = staffDao.findById(crmStaff.getStaffId());
		//2、判断密码是否一致  不一致 把页面传过来的员工的密码 先进行md5加密 再传给 找到的那个员工实体
		if(! findStaff.getLoginPwd().equals(crmStaff.getLoginPwd())){
			findStaff.setLoginPwd(MyMd5Util.getMD5Value(crmStaff.getLoginPwd()));
		}
		//3、其他的数据依次的传给那个员工实体
		findStaff.setLoginName(crmStaff.getLoginName());
		findStaff.setStaffName(crmStaff.getStaffName());
		findStaff.setGender(crmStaff.getGender());
		findStaff.setOnDutyDate(crmStaff.getOnDutyDate());
		findStaff.setPost(crmStaff.getPost());
	}

}
