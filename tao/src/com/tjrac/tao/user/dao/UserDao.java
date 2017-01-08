package com.tjrac.tao.user.dao;

import com.tjrac.tao.user.vo.User;

/**
 * �û�ģ��dao��
 * @author FengXiang
 *
 */
public interface UserDao {
	/**
	 * �����û�����ѯ�Ƿ�������û�
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * ���ݿ��һ���û�
	 * @param user
	 */
	public void add(User user);

	/**
	 * ���ݼ���������û�
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * ����ɹ� �������ݿ��û���Ϣ
	 * @param findUser
	 */
	public void updateUser(User findUser);

	/**
	 * �û���¼
	 * @return
	 */
	public User login(User user);
}
