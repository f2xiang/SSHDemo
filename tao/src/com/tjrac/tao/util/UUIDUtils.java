package com.tjrac.tao.util;

import java.util.UUID;

/**
 * ��������ַ����Ĺ�����
 * @author FengXiang
 *
 */
public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
