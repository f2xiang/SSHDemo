package com.tjrac.bos.service;

import com.tjrac.bos.domain.User;

public interface UserService {

	/**
	 * �û���¼
	 * @param model
	 * @return
	 */
	public User login(User model);

	/**
	 * �޸�����
	 * @param password
	 * @param id
	 */
	public void updatepwd(String password, Integer id);


}
