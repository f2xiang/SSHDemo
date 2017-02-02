package com.tjrac.bos.dao;

import java.util.List;

import com.tjrac.bos.dao.base.BaseDao;
import com.tjrac.bos.domain.Function;

public interface FunctionDao extends BaseDao<Function>{

	/**
	 * �����û���id��ѯ��Ӧ��Ȩ��
	 * @param id
	 * @return
	 */
	public List<Function> findByUserid(Integer id);

}
