package com.tjrac.crm.staff.domain;

import java.util.Date;

import com.tjrac.crm.post.domain.CrmPost;

/*
 * CREATE TABLE `crm_staff` (
  `staffId` varchar(255) NOT NULL PRIMARY KEY,
  `loginName` varchar(100) DEFAULT NULL,
  `loginPwd` varchar(100) DEFAULT NULL,
  `staffName` varchar(100) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `onDutyDate` datetime DEFAULT NULL,
  `postId` varchar(255) DEFAULT NULL,
  CONSTRAINT FOREIGN KEY (`postId`) REFERENCES `crm_post` (`postId`)
);
 */
public class CrmStaff {
	private String staffId;
	
	private String loginName;
	
	private String loginPwd;
	
	private String staffName;
	
	private String gender;
	
	private Date onDutyDate;
	
	//     员工 对 职务  多 对 【一】
	private CrmPost post;
	
	
	//------------修改密码------------
	private String oldPassword;  //老的密码
	private String newPassword; //新的密码
	private String reNewPassword; //再次确认密码
	
	
	

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getOnDutyDate() {
		return onDutyDate;
	}

	public void setOnDutyDate(Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}

	public CrmPost getPost() {
		return post;
	}

	public void setPost(CrmPost post) {
		this.post = post;
	}
	
	
    
	

}
