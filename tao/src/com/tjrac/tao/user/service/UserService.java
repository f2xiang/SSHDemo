package com.tjrac.tao.user.service;

import com.tjrac.tao.user.vo.User;

/**
 * 用户模块service层
 * @author FengXiang
 *
 */
public interface UserService {
	/**
	 * 根据用户名查找用户是否存在
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 增加一个用户
	 * @param user
	 */
	public void add(User user);

	/**
	 * 根据激活码查询用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * 用户激活成功 修改数据库内容
	 * @param findUser
	 */
	public void updateUser(User findUser);

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);
}
