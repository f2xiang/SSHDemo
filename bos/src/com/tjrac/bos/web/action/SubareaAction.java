package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Region;
import com.tjrac.bos.domain.Subarea;
import com.tjrac.bos.utils.FileUtils;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{
	
	
	/**
	 * 添加分区
	 * @return
	 */
	public String add(){
		this.subareaService.add(model);
		return "list";
	}
	
	
	
	/**
	 * 分页条件查询
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		//封装查询条件
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		
		//如果有  关键字 参数传过来
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			detachedCriteria2.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		if(region != null){  //至少有一个参数传过来了
			detachedCriteria2.createAlias("region", "r");  //创建别名 用于多表关联查询
			
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			
			if(StringUtils.isNotBlank(province)){
				detachedCriteria2.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			
			if(StringUtils.isNotBlank(city)){
				detachedCriteria2.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			
			if(StringUtils.isNotBlank(district)){
				detachedCriteria2.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		
		this.subareaService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria", "decidedzone", "subareas"});
		
		return NONE;
	}
	
	
	
	/**
	 * 导出文件 使用POI 写入到excel文件中
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException{
		List<Subarea> sList = this.subareaService.findAll();
		
		//在内容中创建一个excel文件，通过输出流写到客户端提供下载
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//创建sheet页
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		//创建标题
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("区域编号");
		headRow.createCell(2).setCellValue("地址关键字");
		headRow.createCell(3).setCellValue("省市区");
		
		for (Subarea subarea : sList) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			dataRow.createCell(3)
			     .setCellValue(subarea.getRegion().getProvince()+subarea.getRegion().getCity()+subarea.getRegion().getDistrict());
		}
		
		String filename = "abc.xls";
		//为了防止下载文件中文名称乱码 这里用了一个工具类
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		
		//一个流 两个头 （提供下载）
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(contentType);
		ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
		
		hssfWorkbook.write(outputStream);
		return NONE;
	}
	
	
	
	
	/**
	 * ajax请求  查询 未被关联到定区的分区
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Subarea> sList = this.subareaService.findAllNotAssociation();
		this.writeList2Json(sList, new String[]{"decidedzone", "region", "startnum", "endnum", "single"});
		return NONE;
	}
}
