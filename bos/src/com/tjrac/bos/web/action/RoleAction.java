package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Role;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	//����Ȩ�޵�id
	private String functionIds;
	
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	public String add(){
		this.roleService.save(model, functionIds);
		return "list";
	}
	
	
	/**
	 * ��ҳ��ѯ���еĽ�ɫ ajax 
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.roleService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "detachedCriteria", "functions", "users"});
		return NONE;
	}
	
	
	
	/**
	 * ��ѯ���н�ɫ
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Role> rList = this.roleService.findAll();
		this.writeList2Json(rList, new String[]{"functions", "users"});
		return NONE;
	}
	
}
