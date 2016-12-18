package com.tjrac.web;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.beans.User;
import com.tjrac.service.UserService;
import com.tjrac.service.UserServiceImpl;

public class UserAction extends ActionSupport implements ModelDriven<User>   {

	//1、封装数据
	private User user = new User();
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

	public User getModel() {
		return user;
	}

	
	//2、调用service
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String saveUser(){
		userService.saveUser(user);
		return "success";
	}
	
	
	
	
	
}
