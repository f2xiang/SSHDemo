package com.tjrac.crm.post.domain;

import java.util.HashSet;
import java.util.Set;

import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.staff.domain.CrmStaff;

/*
 * CREATE TABLE `crm_post` (
  `postId` varchar(255) NOT NULL PRIMARY KEY,
  `postName` varchar(100) DEFAULT NULL,
  `depId` varchar(255) DEFAULT NULL,   --->外键
  CONSTRAINT FOREIGN KEY (`depId`) REFERENCES `crm_department` (`depId`)
);
 */
public class CrmPost {
	private String postId;
	
	private String postName;
	
	// 职位 对 部门    多 对 【一】   
    private CrmDepartment department;
	
	
	//职务  对  员工  一 对 【多】
	private Set<CrmStaff> staffSet = new HashSet<CrmStaff>();


	public String getPostId() {
		return postId;
	}


	public void setPostId(String postId) {
		this.postId = postId;
	}


	public String getPostName() {
		return postName;
	}


	public void setPostName(String postName) {
		this.postName = postName;
	}


	public CrmDepartment getDepartment() {
		return department;
	}


	public void setDepartment(CrmDepartment department) {
		this.department = department;
	}


	public Set<CrmStaff> getStaffSet() {
		return staffSet;
	}


	public void setStaffSet(Set<CrmStaff> staffSet) {
		this.staffSet = staffSet;
	}
	
	
	
	
	
	
	
}
