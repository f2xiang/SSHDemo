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
	/**
	 * ����Ա����id�ҵ�Ա��
	 * @param staffId Ա��id
	 * @return Ա��
	 */
	public CrmStaff findById(String staffId);
	
	/**
	 * ����һ��Ա��
	 * @param crmStaff
	 */
	public void addStaff(CrmStaff crmStaff);
	
	/**
	 * �޸�Ա������
	 * @param crmStaff ��װҪ�޸����ݵ�Ա��ʵ��
	 */
	public void updateStaff(CrmStaff crmStaff);
}
