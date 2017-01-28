package com.tjrac.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.tjrac.bos.utils.PinYin4jUtils;

public class PinYin4JTest {
	
	/**
	 * pinyin4J��ʹ��
	 */
	@Test
	public void test1(){
		//��������  
		String province = "�㽭ʡ";
		String city = "������";
		String district = "������";
		
		
		
		
		//���б��� hangzhou
		city = city.substring(0, city.length() - 1);
		String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
		String citycode = StringUtils.join(stringToPinyin);
		
		
		//����ZJHZXH
		province = province.substring(0, province.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province + city + district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		
		String shortcode = StringUtils.join(headByString);   

		
	}
}
