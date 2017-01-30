package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Decidedzone;
import com.tjrac.bos.web.action.base.BaseAction;
import com.tjrac.crm.domain.Customer;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

	
	//���շ�����id��
	private String [] subareaid;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	
	
	/**
	 * ����һ������
	 * @return
	 */
	public String add(){
		
		this.decidedzoneService.add(model, subareaid);
		return "list";
	}
	
	
	
	
	/**
	 * ��ҳ
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.decidedzoneService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria", "decidedzones", "subareas"});
		
		return NONE;
	}
	
	
	/**
	 * ��ѯû�й��������Ŀͻ�  ajax����
	 * @return
	 * @throws IOException 
	 */
	public String findNoAssociationCustomers() throws IOException{
		List<Customer> customers = this.customerService.findnoassociationCustomers();
		this.writeList2Json(customers, new String[]{"station","address"});
		return NONE;
	}
	
	
	/**
	 * ��ѯ�Ѿ����������Ŀͻ�
	 * @return
	 * @throws IOException 
	 */
	public String findHasAssociationCustomers() throws IOException{
		List<Customer> customers = this.customerService.findhasassociationCustomers(model.getId());
		this.writeList2Json(customers, new String[]{"station","address"});
		return NONE;
		
	}
	
	
	
	//����Ҫ�����Ŀͻ��ǵ�id
	private Integer [] customerIds;
	
	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}
	
	/**
	 * ���������ͻ�
	 * @return
	 */
	public String assigncustomerstodecidedzone(){
		this.customerService.assignCustomersToDecidedZone(customerIds, model.getId());
		return "list";
	}
}
