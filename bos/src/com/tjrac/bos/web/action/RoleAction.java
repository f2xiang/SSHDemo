package com.tjrac.bos.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Role;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	//接收权限的id
	private String functionIds;
	
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	public String add(){
		this.roleService.save(model, functionIds);
		return "list";
	}
	
}
