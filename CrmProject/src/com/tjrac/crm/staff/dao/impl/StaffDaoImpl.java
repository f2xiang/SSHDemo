package com.tjrac.crm.staff.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.crm.staff.dao.StaffDao;
import com.tjrac.crm.staff.domain.CrmStaff;

public class StaffDaoImpl extends HibernateDaoSupport implements StaffDao{
    
	/**
	 * 参数  账号 密码 
	 * 登录成功  取出list的第一个值  反之 返回null
	 * 注意：要注入sessionFactory
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

	@Override
	public List<CrmStaff> findAll() {
		List<CrmStaff> allStaff = this.getHibernateTemplate().find("from CrmStaff");
		return allStaff;
	}

	@Override
	public CrmStaff findById(String staffId) {
		return this.getHibernateTemplate().get(CrmStaff.class, staffId);
	}

	@Override
	public void add(CrmStaff crmStaff) {
		this.getHibernateTemplate().save(crmStaff);
	}

	

}
