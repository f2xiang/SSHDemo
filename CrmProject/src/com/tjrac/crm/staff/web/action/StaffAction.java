package com.tjrac.crm.staff.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;

public class StaffAction extends ActionSupport implements ModelDriven<CrmStaff>{

	private CrmStaff crmStaff = new CrmStaff();
	
	@Override
	public CrmStaff getModel() {
		return crmStaff;
	}

	
	private StaffService staffService;
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	/**
	 * Ա���ĵ�¼ ����service  ע��
	 * @return
	 */
	public String login(){
		//1 ����service ��ѯ Ա��  ע��
		CrmStaff findStaff =  staffService.login(crmStaff);
		if(findStaff != null){
			//�ɹ�    1����session�б���  
			ActionContext.getContext().getSession().put("loginStaff", findStaff);
			//   2���ض�����ҳ ȷ����ַ���ı仯
			return "success";
		}
		//���ɹ�
		this.addFieldError("", "�û��������벻ƥ��");  //�൱�ڼӵ�valuestack��  Ĭ��һ������
		return "login";
	}
	
	
	public String home(){
		return "home";
	}
}
