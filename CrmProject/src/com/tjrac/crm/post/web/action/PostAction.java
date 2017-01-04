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
import com.tjrac.crm.page.PageBean;
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
	
	
	//--------------------��ҳ����-------------------
	private int pageNum = 1;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private int pageSize = 2;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//------------------------------------------------
	
	
	/**
	 * ��ѯ���е�ְ�� ����ְ�����Ӧ�Ĳ���
	 * @return
	 */
	public String findAll(){
//		List<CrmPost> allPost = this.postService.findAll();
//		ActionContext.getContext().put("allPost", allPost);
		
		
		//��ҳ��ѯ����
		PageBean<CrmPost> pageBean = this.postService.findAll(pageNum, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		return "findAll";
	}
	
	
	public String editUI(){

		//���ְ��id��Ϊ �� ���Ǹ���  ��Ҫ���ݵĻ���   
		if(StringUtils.isNotBlank(crmPost.getPostId())){
			CrmPost findPost = postService.findById(crmPost.getPostId());
			ActionContext.getContext().put("findPost", findPost);
		}
		
		
		//1 ���� ���еĲ�������
		List<CrmDepartment> allDepartment =  departmentService.findAll();
		ActionContext.getContext().getValueStack().set("allDepartment", allDepartment);
		
		//2�������е�ְ������
		List<CrmPost> allPost = postService.findAll();
		ActionContext.getContext().getValueStack().set("allPost", allPost);
		
		
		return "editUI";
	}
	
	
	public String addOrUpdate(){
		this.postService.addOrUpdate(crmPost);
		return "addOrUpdate";
	}
	
	
}
