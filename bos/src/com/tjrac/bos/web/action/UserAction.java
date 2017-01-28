package com.tjrac.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.User;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.utils.MyMd5Util;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	//接收验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
	
	
	/**
	 * 登录模块
	 * @return
	 */
	public String login(){
		//判断验证码是否正确
		String checkcode2 = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(checkcode2)){
			//验证码正确
			User finduser =  this.userService.login(model);
			if(finduser != null){
				//登陆成功 设置成功标志
				ServletActionContext.getRequest().getSession().setAttribute("user", finduser);
				return "loginsuccess";
			}else{
				this.addFieldError("", "账号或密码不正确");
				return "login";
			}
		}else{
			this.addFieldError("", "验证码不正确");
			return "login";
		}
		
	}
	
	/**
	 * 用户退出登录
	 * @return
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	
	/**
	 * 修改密码
	 * @return
	 * @throws IOException 
	 */
	public String editPassword() throws IOException{
		User user =	 (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String password = MyMd5Util.getMD5Value(model.getPassword());
		String flag = "1";
		try {
			this.userService.updatepwd(password, user.getId());
		} catch (Exception e) {
			//修改密码失败
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}
}
