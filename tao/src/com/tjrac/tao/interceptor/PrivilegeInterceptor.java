package com.tjrac.tao.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tjrac.tao.admin.vo.AdminUser;

/**
 * ��̨Ȩ��У��������--��¼������
 * @author FengXiang
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	/**
	 * ִ�����صķ��� 
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//�ж�session���Ƿ񱣴�����Ϣ
		AdminUser admin = 	(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin == null){
			//û�е�¼
			ActionSupport action  =	(ActionSupport) invocation.getAction();
			action.addFieldError("", "����û�е�¼ û��Ȩ��");
			return "loginFail";
		}else{
			//����
			return invocation.invoke();
		}
		
		
	}

}
