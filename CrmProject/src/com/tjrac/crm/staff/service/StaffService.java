package com.tjrac.crm.staff.service;

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
}
