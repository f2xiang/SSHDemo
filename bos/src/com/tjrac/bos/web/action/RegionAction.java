package com.tjrac.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Region;
import com.tjrac.bos.service.RegionService;
import com.tjrac.bos.utils.PageBean;
import com.tjrac.bos.utils.PinYin4jUtils;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	
	@Resource
	private RegionService regionService;
	
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}
	
	
	//接收文件
	private File myFile;
	
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
	
	
	/**
	 * 一键导入
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException{
		
		String flag = "1";
		
		try {
			//使用poi解析excel文件
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(myFile));
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);  //获得sheet页
			
			List<Region> list = new ArrayList<Region>();
			for (Row row : sheet) {   //获得每一行 -- 但是第一行是标题行 我们 不需要 
				int rowNum = row.getRowNum();
				if(rowNum == 0){
					//第一行 标题行 忽略
					continue;
				}
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				
				Region region = new Region(id, province, city, district, postcode, null, null, null);
				
				//导入其他数据  城市编码 和 简码 利用pinyin4J
				city = city.substring(0, city.length() - 1);
				String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
				String citycode = StringUtils.join(stringToPinyin);
				
				
				//简码
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
	 * 分页查询
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.regionService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria" });
		return NONE;
	}
}
