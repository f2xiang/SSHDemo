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
 * 自定义realm
 * @author FengXiang
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private FunctionDao functionDao;
	
	
	/**
	 * 认证方法:return null 表示未找到用户名 会抛异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		
		//根据用户名查密码
		User user = this.userDao.findUserByUsername(username);
		if(user == null){
			//用户名不存在
			return null;
		}else{
			//用户名存在    
			String password = user.getPassword();  //拿到数据库里面存在的密码
			//创建简单的认证信息对象--参数一：user 可以在任意的地方将其取出  比如说  授权的时候  登录的时候  认证通过 取出来 放到session里
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getClass().getSimpleName());
			return info;
		}
	}

	
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		/* 为当前的用户授予  或者 角色
		info.addStringPermission("staff");
		info.addRole("staff");*/
		
		//根据当前登录的用户 查询其相应的权限  授权
		//认证的时候放进去的一个user对象 可以在任意的地方取出来 
		User user = (User) principals.getPrimaryPrincipal();  
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			//超级管理员--拥有所有的权限 --查询所有的权限 
			list =	functionDao.findAll();
		}else{
			//普通的 管理员--根据id查询相对应的权限
			list = functionDao.findByUserid(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}


}
