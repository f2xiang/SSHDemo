package com.tjrac.crm.post.web.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
	

}
