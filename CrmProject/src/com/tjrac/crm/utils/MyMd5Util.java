package com.tjrac.crm.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MyMd5Util {
	/**
	 * 通过MD5加密 把明文密码 转为密文存储
	 * @param loginPwd  明文
	 * @return   密文
	 */
	public static String getMD5Value(String loginPwd){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte [] md5ValueByte = messageDigest.digest(loginPwd.getBytes());
			BigInteger md5Value = new BigInteger(1, md5ValueByte);
			return md5Value.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
