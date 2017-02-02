package com.tjrac.bos.dao;

import java.util.List;

import com.tjrac.bos.dao.base.BaseDao;
import com.tjrac.bos.domain.Function;

public interface FunctionDao extends BaseDao<Function>{

	/**
	 * 根据用户的id查询相应的权限
	 * @param id
	 * @return
	 */
	public List<Function> findByUserid(Integer id);

}
