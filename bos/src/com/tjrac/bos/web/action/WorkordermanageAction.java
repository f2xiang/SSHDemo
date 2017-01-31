package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Workordermanage;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage>{
	
	/**
	 * ���������
	 * @return
	 * @throws IOException 
	 */
	public String add() throws IOException{
		this.workordermanageService.save(model);
		ServletActionContext.getResponse().getWriter().print("1");
		return NONE;
	}
	
	
	
	/**
	 * ajax��ѯ����
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Workordermanage> wList = this.workordermanageService.findAll();
		this.writeList2Json(wList, new String[]{"updatetime"});
		return NONE;
	}
	
}
