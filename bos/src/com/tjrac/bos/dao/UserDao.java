package com.tjrac.bos.dao;

import com.tjrac.bos.dao.base.BaseDao;
import com.tjrac.bos.domain.User;

public interface UserDao extends BaseDao<User>{

	public User findByNameAndPwd(String username, String password);

	/**
	 * 根据用户名查询 用户
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);

}
