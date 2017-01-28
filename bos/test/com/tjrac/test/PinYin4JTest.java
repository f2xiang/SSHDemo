package com.tjrac.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.tjrac.bos.utils.PinYin4jUtils;

public class PinYin4JTest {
	
	/**
	 * pinyin4J的使用
	 */
	@Test
	public void test1(){
		//测试数据  
		String province = "浙江省";
		String city = "杭州市";
		String district = "西湖区";
		
		
		
		
		//城市编码 hangzhou
		city = city.substring(0, city.length() - 1);
		String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
		String citycode = StringUtils.join(stringToPinyin);
		
		
		//简码ZJHZXH
		province = province.substring(0, province.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province + city + district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		
		String shortcode = StringUtils.join(headByString);   

		
	}
}
