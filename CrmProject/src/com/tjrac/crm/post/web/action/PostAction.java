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
	 * ajax 通过部门 查询所有的职务
	 * @return
	 * @throws IOException 
	 */
	public String findAllByDept() throws IOException{
		//1、查询
		List<CrmPost> allPost = postService.findPostByDept(crmPost.getDepartment());
		
		//2、转化成json数据     采用json-lib -- 不过要先排除不需要的数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String [] {"department","staffSet"});
		
		String jsonDate = JSONArray.fromObject(allPost, jsonConfig).toString();
		
		//然后 审查元素发现 有乱码的问题
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		
		//3、将json数据发送到浏览器
		ServletActionContext.getResponse().getWriter().write(jsonDate);
		return "none";
	}
	
	
	//--------------------分页数据-------------------
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
	 * 查询所有的职务 包括职务相对应的部门
	 * @return
	 */
	public String findAll(){
//		List<CrmPost> allPost = this.postService.findAll();
//		ActionContext.getContext().put("allPost", allPost);
		
		
		//分页查询数据
		PageBean<CrmPost> pageBean = this.postService.findAll(pageNum, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		return "findAll";
	}
	
	
	public String editUI(){

		//如果职务id不为 空 就是更新  需要数据的回显   
		if(StringUtils.isNotBlank(crmPost.getPostId())){
			CrmPost findPost = postService.findById(crmPost.getPostId());
			ActionContext.getContext().put("findPost", findPost);
		}
		
		
		//1 回显 所有的部门数据
		List<CrmDepartment> allDepartment =  departmentService.findAll();
		ActionContext.getContext().getValueStack().set("allDepartment", allDepartment);
		
		//2回显所有的职务数据
		List<CrmPost> allPost = postService.findAll();
		ActionContext.getContext().getValueStack().set("allPost", allPost);
		
		
		return "editUI";
	}
	
	
	public String addOrUpdate(){
		this.postService.addOrUpdate(crmPost);
		return "addOrUpdate";
	}
	
	
}
