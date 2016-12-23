package com.tjrac.crm.staff.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	/**
	 * 登录拦截器的实现
	 */
	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		//判断session中有没有我们的登录标志   有就放行 没有就拦截
		Object obj = ActionContext.getContext().getSession().get("loginStaff");
		if(obj == null){
			//但是 有这么一个问题 游客进去不访问 而直接被转到主页面 什么提示都没有 所以 我们在这里要加一个    友好信息提示
			//1、获取运行时的action
			Object action = invocation.getAction();
			//2、判断这个action是不是ActionSupport的子类 
			// 是子类 就能调用addFieldError
			if(action instanceof ActionSupport){
				ActionSupport actionSupport = (ActionSupport) action;
				actionSupport.addFieldError("", "亲~~~请先登录！！");
			}
			//没有登录  则 需要登录
			return "login";
		}
		//登录成功 放行
		return invocation.invoke();
	}

}
