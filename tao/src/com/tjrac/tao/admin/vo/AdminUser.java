package com.tjrac.tao.admin.vo;

import java.io.Serializable;

/**
 * 后台管理员实体类
 * @author FengXiang
 *
 */
public class AdminUser implements Serializable{
	/*
 CREATE TABLE `adminuser` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
	 */
	
	private Integer uid;
	private String username;
	private String password;
	
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
