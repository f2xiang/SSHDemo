package com.tjrac.bos.service;

import com.tjrac.bos.domain.User;

public interface UserService {

	/**
	 * ÓÃ»§µÇÂ¼
	 * @param model
	 * @return
	 */
	public User login(User model);

	/**
	 * ĞŞ¸ÄÃÜÂë
	 * @param password
	 * @param id
	 */
	public void updatepwd(String password, Integer id);


}
