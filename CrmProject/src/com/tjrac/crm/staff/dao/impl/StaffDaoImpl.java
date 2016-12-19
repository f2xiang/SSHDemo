package com.tjrac.crm.staff.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.staff.dao.StaffDao;
import com.tjrac.crm.staff.domain.CrmStaff;

public class StaffDaoImpl extends HibernateDaoSupport implements StaffDao{
    
	/**
	 * ����  �˺� ���� 
	 * ��¼�ɹ�  ȡ��list�ĵ�һ��ֵ  ��֮ ����null
	 * ע�⣺Ҫע��sessionFactory
	 */
	@Override
	public CrmStaff find(String loginName, String loginPwd) {
		List<CrmStaff> allStaff = this.getHibernateTemplate()
		    .find("from CrmStaff where loginName = ? and loginPwd = ?", loginName, loginPwd);
		if(allStaff.size() == 1){
			return allStaff.get(0);
		}
		return null;
	}

}
