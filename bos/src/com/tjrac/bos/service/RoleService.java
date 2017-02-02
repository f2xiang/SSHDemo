package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Role;
import com.tjrac.bos.utils.PageBean;

public interface RoleService {

	/**
	 * �����ɫ
	 * @param model
	 * @param functionIds
	 */
	public void  save(Role model, String functionIds);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	public void  pageQuery(PageBean pageBean);
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<Role> findAll();

}
