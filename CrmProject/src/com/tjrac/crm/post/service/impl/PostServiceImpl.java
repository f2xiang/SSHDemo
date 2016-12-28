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

	@Override
	public List<CrmPost> findAll() {
		return this.postDao.findAll();
	}

	@Override
	public CrmPost findById(String postId) {
		return this.postDao.findById(postId);
	}

	@Override
	public void addOrUpdate(CrmPost post) {
		this.postDao.addOrUpdate(post);
	}

	@Override
	public void add(CrmPost post) {
		this.postDao.add(post);
	}

	
}
