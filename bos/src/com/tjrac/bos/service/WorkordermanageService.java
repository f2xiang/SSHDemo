package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Workordermanage;

public interface WorkordermanageService {

	/**
	 * ���湤����
	 * @param model
	 */
	public void save(Workordermanage model);

	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Workordermanage> findAll();

}
