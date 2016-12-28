package com.tjrac.crm.post.dao;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.post.domain.CrmPost;

public interface PostDao {
	public List<CrmPost> findPostByDept(CrmDepartment dept);
	
	public List<CrmPost> findAll();
	
	public CrmPost findById(String postId);
	
	public void addOrUpdate(CrmPost post);
	
	//-------因为 更新 + 添加 出错了 所以加个add测试-----
	public void add(CrmPost post);
}
