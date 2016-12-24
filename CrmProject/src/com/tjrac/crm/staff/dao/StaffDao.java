package com.tjrac.crm.staff.dao;

import java.util.List;

import com.tjrac.crm.staff.domain.CrmStaff;

/**
 * ���Ա������в���
 * @author Administrator
 *
 */
public interface StaffDao {
	/**
	 * ����Ա�����û��� �� ���� ����Ա��
	 * @param loginName �û���
	 * @param loginPwd  ����
	 * @return   �ҵ���Ա��
	 */
	public CrmStaff find(String loginName, String loginPwd);
	/**
	 * ��ѯ���е�Ա��
	 * @return
	 */
	public List<CrmStaff> findAll();
	
	/**
	 * ͨ��Ա����id��ѯԱ��
	 * @param staffId Ա����id
	 * @return  Ա��ʵ����
	 */
	public CrmStaff findById(String staffId);
	
	
	public void add(CrmStaff crmStaff);
	
}
