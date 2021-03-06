package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.User;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.utils.MyMd5Util;
import com.tjrac.bos.web.action.base.BaseAction;
import com.tjrac.crm.domain.Customer;
import com.tjrac.crm.service.CustomerService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	
	//接收验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
	/**
	 * 使用shiro提供的方法来进行认证
	 * @return
	 */
	public String login(){
		//判断验证码是否正确
		String checkcode2 = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(checkcode2)){
			//验证码正确
			//获得当前的用户对象
			Subject subject = SecurityUtils.getSubject();  //未认证状态
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MyMd5Util.getMD5Value(model.getPassword()));
			try {
				subject.login(token );
			} catch (UnknownAccountException e) {
				//设置错误信息
				this.addFieldError("", "用户名不存在");
				return "login";
			} catch (Exception e) {
				this.addFieldError("", "用户名或密码不正确");
				return "login";
			}
			//认证通过  获取认证信息对象中 的user
			User user =	(User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "loginsuccess";
		}else{
			this.addFieldError("", "验证码不正确");
			return "login";
		}
		
	}
	
	
	/**
	 * 登录模块--采用shiro的方法来代替
	 * @return
	 */
	public String login2(){
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
	
	
	
	/**
	 * 分页查询 ajax
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.userService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "noticebills", "roles" });
		return NONE;
	}
	
	
	
	//接收角色的id
	private String[] roleIds;
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
	
	
	/**
	 * 添加用户
	 * @return
	 */
	public String add(){
		this.userService.save(model, roleIds);
		return "list";
	}
	
}
