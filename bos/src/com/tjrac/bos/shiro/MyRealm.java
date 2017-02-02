package com.tjrac.bos.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.tjrac.bos.dao.FunctionDao;
import com.tjrac.bos.dao.UserDao;
import com.tjrac.bos.domain.Function;
import com.tjrac.bos.domain.User;

/**
 * �Զ���realm
 * @author FengXiang
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private FunctionDao functionDao;
	
	
	/**
	 * ��֤����:return null ��ʾδ�ҵ��û��� �����쳣
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		
		//�����û���������
		User user = this.userDao.findUserByUsername(username);
		if(user == null){
			//�û���������
			return null;
		}else{
			//�û�������    
			String password = user.getPassword();  //�õ����ݿ�������ڵ�����
			//�����򵥵���֤��Ϣ����--����һ��user ����������ĵط�����ȡ��  ����˵  ��Ȩ��ʱ��  ��¼��ʱ��  ��֤ͨ�� ȡ���� �ŵ�session��
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getClass().getSimpleName());
			return info;
		}
	}

	
	
	/**
	 * ��Ȩ
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		/* Ϊ��ǰ���û�����  ���� ��ɫ
		info.addStringPermission("staff");
		info.addRole("staff");*/
		
		//���ݵ�ǰ��¼���û� ��ѯ����Ӧ��Ȩ��  ��Ȩ
		//��֤��ʱ��Ž�ȥ��һ��user���� ����������ĵط�ȡ���� 
		User user = (User) principals.getPrimaryPrincipal();  
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			//��������Ա--ӵ�����е�Ȩ�� --��ѯ���е�Ȩ�� 
			list =	functionDao.findAll();
		}else{
			//��ͨ�� ����Ա--����id��ѯ���Ӧ��Ȩ��
			list = functionDao.findByUserid(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}


}
