package com.tjrac.bos.service;

import com.tjrac.bos.domain.Staff;
import com.tjrac.bos.utils.PageBean;

public interface StaffService {

	public void add(Staff model);

	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * �߼�ɾ�� �� �޸�ɾ���ı�־λ Ϊ 1
	 * @param ids
	 */
	public void deleteBatch(String ids);

	/**
	 * �޸���Ϣ
	 * @param model
	 */
	public void update(Staff staff);

	public Staff findById(String id);

}
