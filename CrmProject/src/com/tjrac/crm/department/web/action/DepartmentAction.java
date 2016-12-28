package com.tjrac.crm.department.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;

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
	
	
	public String findAll(){
		List<CrmDepartment> allDept =  departmentService.findAll();
		ActionContext.getContext().put("allDept", allDept);
		return "findAll";
	}
	
	public String editUI(){
		//����������Ķ��� �� id  �����޸� 
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
