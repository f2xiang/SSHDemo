package com.tjrac.crm.post.service.impl;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.post.dao.PostDao;
import com.tjrac.crm.post.domain.CrmPost;
import com.tjrac.crm.post.service.PostService;

public class PostServiceImpl implements PostService{

	private PostDao postDao;
	
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Override
	public List<CrmPost> findPostByDept(CrmDepartment dept) {
		return postDao.findPostByDept(dept);
	}

}