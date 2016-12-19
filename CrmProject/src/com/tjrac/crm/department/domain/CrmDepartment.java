package com.tjrac.crm.department.domain;

import java.util.HashSet;
import java.util.Set;

import com.tjrac.crm.post.domain.CrmPost;

/*
 * CREATE TABLE `crm_department` (
  `depId` varchar(255) NOT NULL PRIMARY KEY,
  `depName` varchar(50) DEFAULT NULL
);
 */
public class CrmDepartment {
	
	private String depId;
	
	private String depName;
	

	//����  �� ְ��   һ �� ���ࡿ
	private Set<CrmPost> postSet = new HashSet<CrmPost>();


	public String getDepId() {
		return depId;
	}


	public void setDepId(String depId) {
		this.depId = depId;
	}


	public String getDepName() {
		return depName;
	}


	public void setDepName(String depName) {
		this.depName = depName;
	}


	public Set<CrmPost> getPostSet() {
		return postSet;
	}


	public void setPostSet(Set<CrmPost> postSet) {
		this.postSet = postSet;
	}
	
	

}
