package com.tjrac.crm.staff.service;

import java.util.List;

import com.tjrac.crm.staff.domain.CrmStaff;

/**
 * Ա����service��
 * @author Administrator
 *
 */
public interface StaffService {
	/**
	 * ��¼
	 * @param crmStaff Ա��(��װ�� �˺� �� ����)
	 * @return Ա��
	 */
	public CrmStaff login(CrmStaff crmStaff);
	/**
	 * ��ѯ���е�Ա��
	 * @return
	 */
	public List<CrmStaff> findAllStaff();
	
	public CrmStaff findById(String staffId);
	
	
	public void addStaff(CrmStaff crmStaff);
}
