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

	
	//接收分区的id们
	private String [] subareaid;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	
	
	/**
	 * 增加一个定区
	 * @return
	 */
	public String add(){
		
		this.decidedzoneService.add(model, subareaid);
		return "list";
	}
	
	
	
	
	/**
	 * 分页
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.decidedzoneService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria", "decidedzones", "subareas"});
		
		return NONE;
	}
	
	
	/**
	 * 查询没有关联定区的客户  ajax请求
	 * @return
	 * @throws IOException 
	 */
	public String findNoAssociationCustomers() throws IOException{
		List<Customer> customers = this.customerService.findnoassociationCustomers();
		this.writeList2Json(customers, new String[]{"station","address"});
		return NONE;
	}
	
	
	/**
	 * 查询已经关联定区的客户
	 * @return
	 * @throws IOException 
	 */
	public String findHasAssociationCustomers() throws IOException{
		List<Customer> customers = this.customerService.findhasassociationCustomers(model.getId());
		this.writeList2Json(customers, new String[]{"station","address"});
		return NONE;
		
	}
	
	
	
	//接收要关联的客户们的id
	private Integer [] customerIds;
	
	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}
	
	/**
	 * 定区关联客户
	 * @return
	 */
	public String assigncustomerstodecidedzone(){
		this.customerService.assignCustomersToDecidedZone(customerIds, model.getId());
		return "list";
	}
}
