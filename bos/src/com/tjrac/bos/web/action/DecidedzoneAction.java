package com.tjrac.bos.web.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Decidedzone;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

	
	//���շ�����id��
	private String [] subareaid;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	
	
	/**
	 * ����һ������
	 * @return
	 */
	public String add(){
		
		this.decidedzoneService.add(model, subareaid);
		return "list";
	}
	
	
	
	
	/**
	 * ��ҳ
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.decidedzoneService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria", "decidedzones", "subareas"});
		
		return NONE;
	}
}
