package com.tjrac.crm.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MyMd5Util {
	/**
	 * ͨ��MD5���� ���������� תΪ���Ĵ洢
	 * @param loginPwd  ����
	 * @return   ����
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
