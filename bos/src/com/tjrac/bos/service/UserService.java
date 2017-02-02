package com.tjrac.bos.service;

import com.tjrac.bos.domain.User;
import com.tjrac.bos.utils.PageBean;

public interface UserService {

	/**
	 * 用户登录
	 * @param model
	 * @return
	 */
	public User login(User model);

	/**
	 * 修改密码
	 * @param password
	 * @param id
	 */
	public void updatepwd(String password, Integer id);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 保存用户 以及 相应的角色
	 * @param model
	 * @param roleIds
	 */
	public void save(User model, String[] roleIds);


}
