package com.tjrac.tao.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.user.service.UserService;
import com.tjrac.tao.user.vo.User;

/**
 * 用户模块action类
 * @author FengXiang
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//--------------模型驱动-------------------
	User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	
	//----------------service------------------
	
	 private UserService userService;
	 
	 public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	//-------------------------------------------
	/**
	 * 注册页面UI
	 * @return
	 */
	public String registerUI(){
		
		return "registerUI";
	}
	
	/**
	 * ajax异步校验   校验用户名是否已存在
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		User findUser = this.userService.findByUsername(user.getUsername());
		
		//获得response对象  向页面输出结果-->输出中文 设置编码
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		if(findUser != null){
			//用户名已经存在
			response.getWriter().write("<font color='red'>用户名已经存在</font>");
		}else{
			//用户名可以使用
			response.getWriter().write("<font color='green'>用户名可以使用</font>");
		}
		
		//ajax不需要页面的跳转
		return NONE;
	}

	/**
	 * 用户注册
	 * @return
	 */
	public String register(){
		
		//验证码的校验
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		
		if(checkcode.equalsIgnoreCase(user.getCaptcha())){
			//验证码正确
			
			//注册数据的后台校验 -- > UserAction-user_register-validation.xml
			
			//数据存到数据库
			this.userService.add(user);
			this.addActionMessage("注册成功！！请到邮箱中激活~");
			return "msg";
		}else{
			this.addActionMessage("验证码错误");
			return "input";
		}
		
		
		
	}
	
	/**
	 * 邮箱激活
	 * @return
	 */
	public String active(){
		//用户点击的链接 带过来的激活码 根据激活码查询用户  若存在  修改状态 为1 
		
		//接收激活码，查询用户
		User findUser = this.userService.findByCode(user.getCode());
		if(findUser == null){
			//说明没有查到
			this.addActionMessage("激活码错误，激活失败");
		}else{
			//激活成功  
			//1改变激活状态
			findUser.setState(1);
			//2清空激活码
			findUser.setCode(null);
			//3更新数据库信息
			this.userService.updateUser(findUser);
			//4发送消息
			this.addActionMessage("激活成功! 请登录");
		}
		
		return "msg";
		
	}
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	public String loginUI(){
		return "loginUI";
	}
	
	/**
	 *用户登录  成功回到主界面
	 * @return
	 */
	public String login(){
		User findUser = this.userService.login(user);
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		
		if(checkcode.equalsIgnoreCase(user.getCaptcha())){
			//验证码正确
			if(findUser == null){
				//登录失败
				this.addFieldError("", "用户名或密码错误");
				return LOGIN;
			}else{
				//成功 登录标志
				ServletActionContext.getRequest().getSession().setAttribute("user", findUser);
				return "loginSuccess";
			}
		}else{
			//验证码错误
			this.addFieldError("", "验证码错误");
			return LOGIN;
		}
		
		
	}
	
	
	/**
	 * 注销登录
	 * @return
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "logout";
	}
}
