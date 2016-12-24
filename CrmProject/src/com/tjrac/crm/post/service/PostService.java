package com.tjrac.crm.post.service;

import java.util.List;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.post.domain.CrmPost;

public interface PostService {
	public List<CrmPost> findPostByDept(CrmDepartment dept);
}
