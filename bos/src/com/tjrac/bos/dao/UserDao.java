package com.tjrac.bos.dao;

import com.tjrac.bos.dao.base.BaseDao;
import com.tjrac.bos.domain.User;

public interface UserDao extends BaseDao<User>{

	public User findByNameAndPwd(String username, String password);

	/**
	 * �����û�����ѯ �û�
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);

}
