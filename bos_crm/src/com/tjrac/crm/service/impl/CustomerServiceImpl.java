package com.tjrac.crm.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.tjrac.crm.domain.Customer;
import com.tjrac.crm.service.CustomerService;
import com.tjrac.crm.utils.HibernateUtils;



public class CustomerServiceImpl implements CustomerService {

	public List<Customer> findnoassociationCustomers() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		String hql = "from Customer where decidedzone_id is null";
		List<Customer> customers = session.createQuery(hql).list();

		session.getTransaction().commit();
		session.close();

		return customers;
	}

	public List<Customer> findhasassociationCustomers(String decidedZoneId) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		String hql = "from Customer where decidedzone_id = ?";
		List<Customer> customers = session.createQuery(hql).setParameter(0, decidedZoneId).list();

		session.getTransaction().commit();
		session.close();

		return customers;
	}

	public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 取消定区所有关联客户
		String hql2 = "update Customer set decidedzone_id=null where decidedzone_id=?";
		session.createQuery(hql2).setParameter(0, decidedZoneId).executeUpdate();

		// 进行关联
		String hql = "update Customer set decidedzone_id=? where id =?";
		if (customerIds != null) {
			for (Integer id : customerIds) {
				session.createQuery(hql).setParameter(0, decidedZoneId).setParameter(1, id).executeUpdate();
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Customer findByPhone(String phonenumber) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		String hql = "from Customer where telephone = ?";
		List<Customer> customers = session.createQuery(hql).setParameter(0, phonenumber).list();

		session.getTransaction().commit();
		session.close();
		if(customers != null && customers.size() > 0){
			return customers.get(0);
		}
		return null;
	}

	@Override
	public String findDecidedzoneByAddress(String address) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		String hql = "select decidedzone_id from Customer where address = ?";
		List<String> customers = session.createQuery(hql).setParameter(0, address).list();

		session.getTransaction().commit();
		session.close();
		if(customers != null && customers.size() > 0){
			return customers.get(0);
		}
		return null;
	}

}
