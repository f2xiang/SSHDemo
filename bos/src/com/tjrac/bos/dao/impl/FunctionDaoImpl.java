package com.tjrac.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjrac.bos.dao.FunctionDao;
import com.tjrac.bos.dao.base.impl.BaseDaoImpl;
import com.tjrac.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{

	@Override
	public List<Function> findByUserid(Integer id) {
		String hql = "select distinct f from Function f left outer join f.roles r left outer join r.users u where u.id = ?";
		return this.getHibernateTemplate().find(hql, id);
	}

}
