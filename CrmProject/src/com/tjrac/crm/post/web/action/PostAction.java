package com.tjrac.crm.post.web.action;

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
	
	

}
