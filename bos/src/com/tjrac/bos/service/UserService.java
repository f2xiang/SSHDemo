package com.tjrac.bos.service;

import com.tjrac.bos.domain.User;
import com.tjrac.bos.utils.PageBean;

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

	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * �����û� �Լ� ��Ӧ�Ľ�ɫ
	 * @param model
	 * @param roleIds
	 */
	public void save(User model, String[] roleIds);


}
