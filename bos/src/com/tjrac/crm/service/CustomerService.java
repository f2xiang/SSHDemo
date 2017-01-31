package com.tjrac.crm.service;

import java.util.List;

import com.tjrac.crm.domain.Customer;



// 客户服务接口 
public interface CustomerService {
	// 未关联定区客户
	public List<Customer> findnoassociationCustomers();

	// 查询已经关联指定定区的客户
	public List<Customer> findhasassociationCustomers(String decidedZoneId);

	// 将未关联定区客户关联到定区上
	public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);
	
	//根据手机号查询客户信息
	public Customer findByPhone(String phonenumber);
	
	//根据地址 查询定区的id
	public String findDecidedzoneByAddress(String address);
}
