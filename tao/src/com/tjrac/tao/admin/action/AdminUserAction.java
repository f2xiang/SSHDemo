package com.tjrac.tao.admin.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.admin.service.AdminUserService;
import com.tjrac.tao.admin.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	//-----------------ģ������----------
	
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
	
	
	
	//----------------����---------------
	
	/**
	 * ��̨��¼
	 * @return
	 */
	public String login(){
		AdminUser findAdminUser = this.adminUserService.login(adminUser);
		if(findAdminUser == null){
			//��¼ʧ��
			this.addFieldError("","�û������������");
			return "loginFail";
		}else{
			//���õ�¼��־
			ServletActionContext.getRequest().getSession().setAttribute("admin", findAdminUser);
			return "loginSuccess";
		}
		
	}
	
}
