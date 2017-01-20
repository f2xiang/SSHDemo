package com.tjrac.tao.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tjrac.tao.admin.vo.AdminUser;

/**
 * 后台权限校验拦截器--登录拦截器
 * @author FengXiang
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	/**
	 * 执行拦截的方法 
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//判断session中是否保存了信息
		AdminUser admin = 	(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin == null){
			//没有登录
			ActionSupport action  =	(ActionSupport) invocation.getAction();
			action.addFieldError("", "您还没有登录 没有权限");
			return "loginFail";
		}else{
			//放行
			return invocation.invoke();
		}
		
		
	}

}
