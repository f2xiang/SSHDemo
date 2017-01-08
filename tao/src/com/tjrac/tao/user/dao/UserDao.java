package com.tjrac.tao.user.dao;

import com.tjrac.tao.user.vo.User;

/**
 * 用户模块dao层
 * @author FengXiang
 *
 */
public interface UserDao {
	/**
	 * 根据用户名查询是否有这个用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 数据库存一个用户
	 * @param user
	 */
	public void add(User user);

	/**
	 * 根据激活码查找用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * 激活成功 更新数据库用户信息
	 * @param findUser
	 */
	public void updateUser(User findUser);

	/**
	 * 用户登录
	 * @return
	 */
	public User login(User user);
}
