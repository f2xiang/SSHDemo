package com.tjrac.bos.service;

import com.tjrac.bos.domain.Decidedzone;
import com.tjrac.bos.utils.PageBean;

public interface DecidedzoneService {

	/**
	 * ���һ������
	 * @param model
	 * @param subareaid
	 */
	public void add(Decidedzone model, String[] subareaid);

	/**
	 * ��ҳ�б�
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

}
