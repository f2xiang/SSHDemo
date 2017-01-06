package com.tjrac.crm.staff.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tjrac.crm.page.PageBean;
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

	@Override
	public boolean updatePwdOld(String oldPassword, String staffId) {
		boolean flag = false;
		
		//用户 输入的密码加密成md5
		String md5pwd = MyMd5Util.getMD5Value(oldPassword);
		
		//从数据库里找出来的密码
		String psw = this.staffDao.findById(staffId).getLoginPwd();
		
		if(psw.equals(md5pwd)){
			flag = true;
		}
		
		return flag;
	}

	@Override
	public boolean updatePwdNew(String newPassword, String reNewPassword, String staffId) {
		boolean flag = false;
		//检验 两次输入的新密码是否一致
		if(newPassword.equals(reNewPassword)){
			flag = true;
		}
		
		
		//新密码重新加密， 更改数据库中的密码
		CrmStaff findStaff =  this.staffDao.findById(staffId);
		String md5pwd = MyMd5Util.getMD5Value(newPassword);
		findStaff.setLoginPwd(md5pwd);
		
		return flag;
	}

	@Override
	public PageBean<CrmStaff> findAllStaff(CrmStaff crmStaff, int pageNum,
			int pageSize) {
		
		//1 条件查询
		StringBuilder sb = new StringBuilder();
		List<Object> paramslist = new ArrayList<Object>();
		
		//过滤条件
		//部门
//		if(StringUtils.isNotBlank(crmStaff.getPost().getDepartment().getDepId())){
//			sb.append(" and CrmStaff.post.")
//		}
		//TODO 部门 职务 的 多条件查询
		//职务
		
		//姓名
		if(StringUtils.isNotBlank(crmStaff.getStaffName())){
			sb.append(" and staffName like ?");
			paramslist.add("%"+crmStaff.getStaffName()+"%");
		}
		
		String condition = sb.toString();
		
		Object [] params = paramslist.toArray();
		
		
		//分页
		int totalRecord = this.staffDao.getTotalRecord(condition, params);
		
		PageBean<CrmStaff> pageBean = new PageBean<CrmStaff>(pageNum, pageSize, totalRecord);
		
		List<CrmStaff> data =
				 this.staffDao.findAll(condition, params, pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);
		
		return pageBean;
	}

}
