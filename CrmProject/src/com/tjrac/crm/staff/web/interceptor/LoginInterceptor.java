package com.tjrac.crm.staff.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	/**
	 * ��¼��������ʵ��
	 */
	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		//�ж�session����û�����ǵĵ�¼��־   �оͷ��� û�о�����
		Object obj = ActionContext.getContext().getSession().get("loginStaff");
		if(obj == null){
			//���� ����ôһ������ �οͽ�ȥ������ ��ֱ�ӱ�ת����ҳ�� ʲô��ʾ��û�� ���� ����������Ҫ��һ��    �Ѻ���Ϣ��ʾ
			//1����ȡ����ʱ��action
			Object action = invocation.getAction();
			//2���ж����action�ǲ���ActionSupport������ 
			// ������ ���ܵ���addFieldError
			if(action instanceof ActionSupport){
				ActionSupport actionSupport = (ActionSupport) action;
				actionSupport.addFieldError("", "��~~~���ȵ�¼����");
			}
			//û�е�¼  �� ��Ҫ��¼
			return "login";
		}
		//��¼�ɹ� ����
		return invocation.invoke();
	}

}
