package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Workordermanage;

public interface WorkordermanageService {

	/**
	 * 保存工作单
	 * @param model
	 */
	public void save(Workordermanage model);

	/**
	 * 查询所有
	 * @return
	 */
	public List<Workordermanage> findAll();

}
