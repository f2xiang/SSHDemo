package com.tjrac.crm.post.web.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.post.domain.CrmPost;
import com.tjrac.crm.post.service.PostService;

public class PostAction extends ActionSupport implements ModelDriven<CrmPost>{
	
	private CrmPost crmPost = new CrmPost();
	
	@Override
	public CrmPost getModel() {
		return crmPost;
	}
	
	//service
	private PostService postService;
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	////////////////////////////////
	/**
	 * ajax ͨ������ ��ѯ���е�ְ��
	 * @return
	 * @throws IOException 
	 */
	public String findAllByDept() throws IOException{
		//1����ѯ
		List<CrmPost> allPost = postService.findPostByDept(crmPost.getDepartment());
		
		//2��ת����json����     ����json-lib -- ����Ҫ���ų�����Ҫ������
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String [] {"department","staffSet"});
		
		String jsonDate = JSONArray.fromObject(allPost, jsonConfig).toString();
		
		//Ȼ�� ���Ԫ�ط��� �����������
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		
		//3����json���ݷ��͵������
		ServletActionContext.getResponse().getWriter().write(jsonDate);
		return "none";
	}
	
	/**
	 * ��ѯ���е�ְ�� ����ְ�����Ӧ�Ĳ���
	 * @return
	 */
	public String findAll(){
		List<CrmPost> allPost = this.postService.findAll();
		ActionContext.getContext().put("allPost", allPost);
		return "findAll";
	}
	
	
	public String editUI(){

		//���ְ��id��Ϊ �� ���Ǹ���  ��Ҫ���ݵĻ���  
		
		List<CrmDepartment> allDepartment =  departmentService.findAll();
		ActionContext.getContext().getValueStack().set("allDepartment", allDepartment);
		
		
		
		if(StringUtils.isNotBlank(crmPost.getPostId())){
			CrmPost findPost = postService.findById(crmPost.getPostId());
			ActionContext.getContext().put("findPost", findPost);
		}
		
		return "editUI";
	}
	
	
	public String addOrUpdate(){
		
		return "addOrUpdate";
	}
	
}
