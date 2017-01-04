package com.tjrac.crm.staff.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;
import com.tjrac.crm.utils.MyMd5Util;

public class StaffAction extends ActionSupport implements ModelDriven<CrmStaff>{

	//------------获得模型驱动----------
	private CrmStaff crmStaff = new CrmStaff();
	
	@Override
	public CrmStaff getModel() {
		return crmStaff;
	}
	

	//----------员工操作的service-------------
	private StaffService staffService;
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	
	//---------部门操作的service--------------
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
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
	
	//----------返回主界面---------
	public String home(){
		return "home";
	}
	
	public String findAll(){
		List<CrmStaff> allStaff = staffService.findAllStaff();
		//把查询的结果存放到值栈中 方便 jsp获得
		ActionContext.getContext().put("allStaff", allStaff); 
		return "findAll";
	}
	
	public String editUI(){
		CrmStaff findStaff = staffService.findById(crmStaff.getStaffId());
		//把找到的bean压入值栈，让jsp页面好进行数据的回显
		ActionContext.getContext().getValueStack().push(findStaff);
		//回显员工信息的同时还有回显员工的部门以及职务  涉及到对部门和职务的查询
		
		
		List<CrmDepartment> departmentList = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("departmentList", departmentList);
		
		return "editUI";
	}
	
	/**
	 * 员工的编辑更新
	 * @return
	 */
	public String edit(){
		staffService.updateStaff(crmStaff);
		return "edit";
	}
	
	/**
	 * 添加员工的UI界面 需要显示可选择的部门  以及 响应的职务 所以需要用到dept的service
	 * @return
	 */
	public String addUI(){
		//添加员工的时候 要先显示有什么部门 以及 这个部门下面有什么 职务
		List<CrmDepartment> departmentList = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("departmentList", departmentList);
		
		return "addUI";
	}
	
	/**
	 * 添加员工
	 * @return
	 */
	public String add(){
		//要把密码加密  再 添加 
		String md5Pwd = MyMd5Util.getMD5Value(crmStaff.getLoginPwd()); 
		crmStaff.setLoginPwd(md5Pwd);
		staffService.addStaff(crmStaff); 
		return "add";
	}
	
	/**
	 * 注销
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().remove("loginStaff");
		return "logout";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String updPwdUI(){
		//修改密码 TODO
		
		return "updPwdUI";
	}
	
	
	
	public String updPwd(){
		
		return "updPwd";
	}
}
