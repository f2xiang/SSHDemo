package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Role;
import com.tjrac.bos.utils.PageBean;

public interface RoleService {

	/**
	 * 保存角色
	 * @param model
	 * @param functionIds
	 */
	public void  save(Role model, String functionIds);
	
	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void  pageQuery(PageBean pageBean);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Role> findAll();

}
