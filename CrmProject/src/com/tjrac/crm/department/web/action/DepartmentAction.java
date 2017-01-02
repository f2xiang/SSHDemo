package com.tjrac.crm.department.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.page.PageBean;

public class DepartmentAction extends ActionSupport implements ModelDriven<CrmDepartment>{

	private CrmDepartment department = new CrmDepartment();
	
	@Override
	public CrmDepartment getModel() {
		return department;
	}
	
	//----------------service------------------
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	//-----------------------分页数据-----------------
	
	private int pageNum = 1;  //默认第一页
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private int pageSize = 2;  //默认每页两条数据
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	public String findAll(){
//		List<CrmDepartment> allDept =  departmentService.findAll();
//		ActionContext.getContext().put("allDept", allDept);
		
		//分页
		PageBean<CrmDepartment> pageBean = departmentService.findAll(pageNum, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		
		
		return "findAll";
	}
	
	public String editUI(){
		//如果传上来的对象 有 id  就是修改 
		if(StringUtils.isNotBlank(department.getDepId())){
			CrmDepartment findDept = this.departmentService.findById(department.getDepId());
			ActionContext.getContext().getValueStack().push(findDept);
		}
		return "editUI";
	}
	
	
	
	public String addOrEdit(){
		this.departmentService.addOrUpdate(department);
		return "addOrEdit";
		
	}

}
