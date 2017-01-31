package com.tjrac.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Noticebill;
import com.tjrac.bos.domain.User;
import com.tjrac.bos.web.action.base.BaseAction;
import com.tjrac.crm.domain.Customer;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
	
	
	/**
	 * ajax���� �����û��ֻ������ѯ�û�����Ϣ
	 * @return
	 * @throws IOException 
	 */
	public String findCustomerByTelephone() throws IOException{
		Customer customer =	this.customerService.findByPhone(model.getTelephone());
		this.writeObj2Json(customer, new String[]{});
		return NONE;
	}
	
	
	
	/**
	 * ���һ��ҵ��֪ͨ��,���Ҹ��ݵ�ַ�������Զ��ֵ�
	 * @return
	 */
	public String add(){
		//���ݲ�ȫ
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		model.setUser(user);
		this.noticebillService.add(model);
		return "addUI";
	}
}
