package com.tjrac.tao.admin.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.admin.service.AdminUserService;
import com.tjrac.tao.admin.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	//-----------------模型驱动----------
	
	private AdminUser adminUser = new AdminUser();
	
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	//----------------service---------------
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	
	
	//----------------动作---------------
	
	/**
	 * 后台登录
	 * @return
	 */
	public String login(){
		AdminUser findAdminUser = this.adminUserService.login(adminUser);
		if(findAdminUser == null){
			//登录失败
			this.addFieldError("","用户名或密码错误");
			return "loginFail";
		}else{
			//设置登录标志
			ServletActionContext.getRequest().getSession().setAttribute("admin", findAdminUser);
			return "loginSuccess";
		}
		
	}
	
}
