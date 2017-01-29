package com.tjrac.bos.service;

import com.tjrac.bos.domain.Decidedzone;
import com.tjrac.bos.utils.PageBean;

public interface DecidedzoneService {

	/**
	 * 添加一个定区
	 * @param model
	 * @param subareaid
	 */
	public void add(Decidedzone model, String[] subareaid);

	/**
	 * 分页列表
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

}
