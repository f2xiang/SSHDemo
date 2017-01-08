package com.tjrac.tao.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码进行md5加密工具类
 * @author FengXiang
 *
 */
public class MD5Utils {
	public static String getMD5(String psw){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] md5bytes =  md.digest(psw.getBytes());
			BigInteger bigInteger = new BigInteger(1, md5bytes);
			return bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
