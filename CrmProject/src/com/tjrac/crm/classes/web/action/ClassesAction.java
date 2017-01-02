package com.tjrac.crm.classes.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.classes.service.ClassesService;

public class ClassesAction extends ActionSupport implements ModelDriven<CrmClasses>{
	
	//��װ����
	private CrmClasses classes = new CrmClasses();
	
	@Override
	public CrmClasses getModel() {
		return classes;
	}
	
	
	//------------service---------------
	private ClassesService classesService;
	
	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}
	
	//---------------------------------
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public String findAll(){
		List<CrmClasses> allClasses =  classesService.findAll();
		ActionContext.getContext().getValueStack().set("allClasses", allClasses);
		return "findAll";
	}
	
	/**
	 * ��ʾ�ϴ��ı�
	 * @return
	 */
	public String uploadUI(){
		CrmClasses findClasses = classesService.findById(classes.getClassId());
		ActionContext.getContext().getValueStack().push(findClasses);
		return "uploadUI";
	}
	
	
	
	//-----------------�ļ��ϴ�����-------------
	private File schedule;     //�ϴ�����
	private String scheduleFileName;  //�ϴ��ļ���
	private String scheduleContentType; //�ϴ�����
	
	public void setSchedule(File schedule) {
		this.schedule = schedule;
	}
	public void setScheduleFileName(String scheduleFileName) {
		this.scheduleFileName = scheduleFileName;
	}
	public void setScheduleContentType(String scheduleContentType) {
		this.scheduleContentType = scheduleContentType;
	}
	
	/**
	 * �ļ��ϴ�
	 * @return
	 * @throws IOException 
	 */
	public String upload() throws IOException{
		//1.�� �α� ���浽Ӳ��λ��  
		
		//1.1  tomcat../WEB-INF/upload/...
		String parentDir = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		
		//1.2    �ļ������������
		String fileName = UUID.randomUUID().toString().replace("-", "");
		
		//1.3    ���� ����  ����
		FileUtils.copyFile(schedule, new File(parentDir, fileName));
		
		
		//2.����
		classes.setUploadTime(new Date());   //���µ�ʱ��
		classes.setUploadPath("/WEB-INF/upload/"+fileName);  //�ϴ���·��
		classes.setUploadFilename(scheduleFileName);  //�ϴ����ļ���
		
		//�����ϴ�
		classesService.updateUpload(classes);
		
		return "upload";
	}
}
