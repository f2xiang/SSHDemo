package com.tjrac.crm.staff.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;
import com.tjrac.crm.utils.MyMd5Util;

public class StaffAction extends ActionSupport implements ModelDriven<CrmStaff>{

	//------------���ģ������----------
	private CrmStaff crmStaff = new CrmStaff();
	
	@Override
	public CrmStaff getModel() {
		return crmStaff;
	}
	

	//----------Ա��������service-------------
	private StaffService staffService;
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	
	//---------���Ų�����service--------------
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
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
	
	//----------����������---------
	public String home(){
		return "home";
	}
	
	public String findAll(){
		List<CrmStaff> allStaff = staffService.findAllStaff();
		//�Ѳ�ѯ�Ľ����ŵ�ֵջ�� ���� jsp���
		ActionContext.getContext().put("allStaff", allStaff); 
		return "findAll";
	}
	
	public String editUI(){
		CrmStaff findStaff = staffService.findById(crmStaff.getStaffId());
		//���ҵ���beanѹ��ֵջ����jspҳ��ý������ݵĻ���
		ActionContext.getContext().getValueStack().push(findStaff);
		//����Ա����Ϣ��ͬʱ���л���Ա���Ĳ����Լ�ְ��  �漰���Բ��ź�ְ��Ĳ�ѯ
		
		
		List<CrmDepartment> departmentList = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("departmentList", departmentList);
		
		return "editUI";
	}
	
	/**
	 * Ա���ı༭����
	 * @return
	 */
	public String edit(){
		staffService.updateStaff(crmStaff);
		return "edit";
	}
	
	/**
	 * ���Ա����UI���� ��Ҫ��ʾ��ѡ��Ĳ���  �Լ� ��Ӧ��ְ�� ������Ҫ�õ�dept��service
	 * @return
	 */
	public String addUI(){
		//���Ա����ʱ�� Ҫ����ʾ��ʲô���� �Լ� �������������ʲô ְ��
		List<CrmDepartment> departmentList = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("departmentList", departmentList);
		
		return "addUI";
	}
	
	/**
	 * ���Ա��
	 * @return
	 */
	public String add(){
		//Ҫ���������  �� ��� 
		String md5Pwd = MyMd5Util.getMD5Value(crmStaff.getLoginPwd()); 
		crmStaff.setLoginPwd(md5Pwd);
		staffService.addStaff(crmStaff); 
		return "add";
	}
	
	/**
	 * ע��
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().remove("loginStaff");
		return "logout";
	}
	
	/**
	 * �޸�����
	 * @return
	 */
	public String updPwdUI(){
		//�޸����� TODO
		
		return "updPwdUI";
	}
	
	
	
	public String updPwd(){
		
		return "updPwd";
	}
}
