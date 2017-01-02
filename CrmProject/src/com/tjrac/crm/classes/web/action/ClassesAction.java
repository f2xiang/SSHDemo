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
	
	//封装数据
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
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		List<CrmClasses> allClasses =  classesService.findAll();
		ActionContext.getContext().getValueStack().set("allClasses", allClasses);
		return "findAll";
	}
	
	/**
	 * 显示上传的表单
	 * @return
	 */
	public String uploadUI(){
		CrmClasses findClasses = classesService.findById(classes.getClassId());
		ActionContext.getContext().getValueStack().push(findClasses);
		return "uploadUI";
	}
	
	
	
	//-----------------文件上传所需-------------
	private File schedule;     //上传内容
	private String scheduleFileName;  //上传文件名
	private String scheduleContentType; //上传类型
	
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
	 * 文件上传
	 * @return
	 * @throws IOException 
	 */
	public String upload() throws IOException{
		//1.将 课表 保存到硬盘位置  
		
		//1.1  tomcat../WEB-INF/upload/...
		String parentDir = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		
		//1.2    文件名：随机生成
		String fileName = UUID.randomUUID().toString().replace("-", "");
		
		//1.3    保存 开流  关流
		FileUtils.copyFile(schedule, new File(parentDir, fileName));
		
		
		//2.更新
		classes.setUploadTime(new Date());   //更新的时间
		classes.setUploadPath("/WEB-INF/upload/"+fileName);  //上传的路径
		classes.setUploadFilename(scheduleFileName);  //上传的文件名
		
		//更新上传
		classesService.updateUpload(classes);
		
		return "upload";
	}
}
