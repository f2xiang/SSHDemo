package com.tjrac.crm.post.dao;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.post.domain.CrmPost;

public interface PostDao {
	public List<CrmPost> findPostByDept(CrmDepartment dept);
	
	public List<CrmPost> findAll();
	
	public CrmPost findById(String postId);
}
