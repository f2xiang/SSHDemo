package com.tjrac.tao.user.service;

import com.tjrac.tao.user.vo.User;

/**
 * �û�ģ��service��
 * @author FengXiang
 *
 */
public interface UserService {
	/**
	 * �����û��������û��Ƿ����
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * ����һ���û�
	 * @param user
	 */
	public void add(User user);

	/**
	 * ���ݼ������ѯ�û�
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * �û�����ɹ� �޸����ݿ�����
	 * @param findUser
	 */
	public void updateUser(User findUser);

	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User login(User user);
}
