package com.tjrac.crm.staff.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;

public class StaffAction extends ActionSupport implements ModelDriven<CrmStaff>{

	private CrmStaff crmStaff = new CrmStaff();
	
	@Override
	public CrmStaff getModel() {
		return crmStaff;
	}

	
	private StaffService staffService;
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	/**
	 * 员工的登录 调用service  注入
	 * @return
	 */
	public String login(){
		//1 调用service 查询 员工  注入
		CrmStaff findStaff =  staffService.login(crmStaff);
		if(findStaff != null){
			//成功    1、在session中保存  
			ActionContext.getContext().getSession().put("loginStaff", findStaff);
			//   2、重定向到首页 确保地址栏的变化
			return "success";
		}
		//不成功
		this.addFieldError("", "用户名与密码不匹配");  //相当于加到valuestack中  默认一次请求
		return "login";
	}
	
	
	public String home(){
		return "home";
	}
}
