package com.tjrac.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.RoleDao;
import com.tjrac.bos.domain.Function;
import com.tjrac.bos.domain.Role;
import com.tjrac.bos.service.RoleService;
import com.tjrac.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Resource
	private RoleDao roleDao;

	@Override
	public void save(Role model, String functionIds) {
		this.roleDao.save(model);  //持久

		//角色 关联 权限
		String[] ids = functionIds.split(",");
		for (String id : ids) {
			Function function = new Function(id);  //托管
			model.getFunctions().add(function);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		this.roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		return this.roleDao.findAll();
	}
	
	
	
}
