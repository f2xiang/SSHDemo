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
	 * ��ӷ���
	 * @return
	 */
	public String add(){
		this.subareaService.add(model);
		return "list";
	}
	
	
	
	/**
	 * ��ҳ������ѯ
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		//��װ��ѯ����
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		
		//�����  �ؼ��� ����������
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			detachedCriteria2.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		if(region != null){  //������һ��������������
			detachedCriteria2.createAlias("region", "r");  //�������� ���ڶ�������ѯ
			
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
	 * �����ļ� ʹ��POI д�뵽excel�ļ���
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException{
		List<Subarea> sList = this.subareaService.findAll();
		
		//�������д���һ��excel�ļ���ͨ�������д���ͻ����ṩ����
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//����sheetҳ
		HSSFSheet sheet = hssfWorkbook.createSheet("��������");
		//��������
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("�������");
		headRow.createCell(1).setCellValue("������");
		headRow.createCell(2).setCellValue("��ַ�ؼ���");
		headRow.createCell(3).setCellValue("ʡ����");
		
		for (Subarea subarea : sList) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			dataRow.createCell(3)
			     .setCellValue(subarea.getRegion().getProvince()+subarea.getRegion().getCity()+subarea.getRegion().getDistrict());
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
		
		hssfWorkbook.write(outputStream);
		return NONE;
	}
	
	
	
	
	/**
	 * ajax����  ��ѯ δ�������������ķ���
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Subarea> sList = this.subareaService.findAllNotAssociation();
		this.writeList2Json(sList, new String[]{"decidedzone", "region", "startnum", "endnum", "single"});
		return NONE;
	}
}
