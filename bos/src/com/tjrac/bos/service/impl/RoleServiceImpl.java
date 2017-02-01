package com.tjrac.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.RoleDao;
import com.tjrac.bos.domain.Function;
import com.tjrac.bos.domain.Role;
import com.tjrac.bos.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Resource
	private RoleDao roleDao;

	@Override
	public void save(Role model, String functionIds) {
		this.roleDao.save(model);  //�־�

		//��ɫ ���� Ȩ��
		String[] ids = functionIds.split(",");
		for (String id : ids) {
			Function function = new Function(id);  //�й�
			model.getFunctions().add(function);
		}
	}
	
	
	
}
