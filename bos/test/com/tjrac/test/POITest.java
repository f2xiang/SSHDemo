package com.tjrac.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class POITest {
	
	/**
	 * ʹ��POI����Excel�ļ�
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void test1() throws FileNotFoundException, IOException{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("e:\\abc.xls")));
		HSSFSheet sheet = workbook.getSheetAt(0); //���sheet1ҳ
		for (Row row : sheet) {   //ÿһ��sheetҳ�� �кܶ� ��
			String v1 = row.getCell(0).getStringCellValue();   //ÿһ�������кܶ�ĵ�Ԫ��
			String v2 = row.getCell(1).getStringCellValue();
			String v3 = row.getCell(2).getStringCellValue();
			String v4 = row.getCell(3).getStringCellValue();
			String v5 = row.getCell(4).getStringCellValue();
			
			System.out.println(v1 + "---" + v2 + "---" + v3 + "---" + v4 + "---" + v5);
		}
		
	}
}
