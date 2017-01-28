package com.tjrac.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Region;
import com.tjrac.bos.service.RegionService;
import com.tjrac.bos.utils.FileUtils;
import com.tjrac.bos.utils.PageBean;
import com.tjrac.bos.utils.PinYin4jUtils;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	
	//�����ļ�
	private File myFile;
	
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
	
	
	/**
	 * һ������
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException{
		
		String flag = "1";
		
		try {
			//ʹ��poi����excel�ļ�
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(myFile));
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);  //���sheetҳ
			
			List<Region> list = new ArrayList<Region>();
			for (Row row : sheet) {   //���ÿһ�� -- ���ǵ�һ���Ǳ����� ���� ����Ҫ 
				int rowNum = row.getRowNum();
				if(rowNum == 0){
					//��һ�� ������ ����
					continue;
				}
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				
				Region region = new Region(id, province, city, district, postcode, null, null, null);
				
				//������������  ���б��� �� ���� ����pinyin4J
				city = city.substring(0, city.length() - 1);
				String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
				String citycode = StringUtils.join(stringToPinyin);
				
				
				//����
				province = province.substring(0, province.length() - 1);
				district = district.substring(0, district.length() - 1);
				String info = province + city + district;
				String[] headByString = PinYin4jUtils.getHeadByString(info);
				String shortcode = StringUtils.join(headByString);  
				
				region.setCitycode(citycode);
				region.setShortcode(shortcode);
				
				list.add(region);
			}
			this.regionService.saveBatch(list);
		} catch (Exception e) {
			flag = "0";  
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}

	
	
	/**
	 * ��ҳ��ѯ
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.regionService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria", "subareas" });
		return NONE;
	}
	
	
	//comboboxĬ��ģ����ѯ�Ĳ���
	private String q;
	
	public void setQ(String q) {
		this.q = q;
	}
	
	/**
	 * ��ѯ����ajax
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Region> rList = null;
		if(StringUtils.isNotBlank(q)){
			rList = this.regionService.findByQ(q);
		}else{
			rList = this.regionService.findAll();
		}
		this.writeList2Json(rList, new String[]{"subareas"});
		return NONE;
	}


	
	/**
	 * ������excel�ļ�
	 * @throws IOException 
	 */
	public String exportXls() throws IOException{
		List<Region> rList = this.regionService.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("��������");
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("������");
		headerRow.createCell(1).setCellValue("ʡ����");
		headerRow.createCell(2).setCellValue("�ʱ�");
		headerRow.createCell(3).setCellValue("����");
		headerRow.createCell(4).setCellValue("���б���");
		
		for (Region region : rList) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(region.getId());
			dataRow.createCell(1).setCellValue(region.getProvince() + region.getCity() + region.getDistrict());
			dataRow.createCell(2).setCellValue(region.getPostcode());
			dataRow.createCell(3).setCellValue(region.getShortcode());
			dataRow.createCell(4).setCellValue(region.getCitycode());
		}
		
		String filename = "abc.xls";
		//Ϊ�˷�ֹ�����ļ������������� ��������һ��������
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		
		//һ���� ����ͷ ���ṩ���أ�
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(contentType);
		ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
		
		workbook.write(outputStream);
		
		
		return NONE;
	}

	
}
