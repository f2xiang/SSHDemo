package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Function;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	
	/**
	 * ��ҳ��ѯ����	
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		//������һ������  ��������function������page�ֶ� �� ��ҳ�����ݳ�ͻ�ˡ�����
		//strutsĬ�ϰ����ݷŵ�model��  û�취 ��������ֻ���ֹ�ȡ����  �ŵ�pageBean����
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));

		this.functionService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"detachedCriteria", "currentPage", "functions", "function", "roles" });
		return NONE;
	}
	
	
	
	
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Function> fList = this.functionService.findAll();
		this.writeList2Json(fList, new String[]{"function", "description", "functions", "roles", "generatemenu"});
		return NONE;	
	}
	
	
	
	
	/**
	 * ���Ȩ��
	 * @return
	 */
	public String add(){
		this.functionService.save(model);
		return "list";
	}
}
