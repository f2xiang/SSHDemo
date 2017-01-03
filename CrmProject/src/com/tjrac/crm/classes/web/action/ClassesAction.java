package com.tjrac.crm.classes.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.classes.domain.CrmClasses;
import com.tjrac.crm.classes.service.ClassesService;
import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.coursetype.service.CourseTypeService;
import com.tjrac.crm.department.domain.CrmDepartment;
import com.tjrac.crm.page.PageBean;

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
	
	private CourseTypeService courseTypeService;
	
	public void setCourseTypeService(CourseTypeService courseTypeService) {
		this.courseTypeService = courseTypeService;
	}
	
	//---------------------------------
	//��ҳ����
	private int pageNum = 1;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private int pageSize = 2;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//-----------------------------------
	/**
	 * ��ѯ����
	 * @return
	 */
	public String findAll(){
//		List<CrmClasses> allClasses =  classesService.findAll();
//		ActionContext.getContext().getValueStack().set("allClasses", allClasses);
		
		//��ҳ
		PageBean<CrmClasses> pageBean = classesService.findAll(pageNum, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		
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
	
	
	/**
	 * ���� ���� ����  ��UI����
	 * @return
	 */
	public String editUI(){
		//�����id  ����  ����  û�� id ��������
		if(StringUtils.isNotBlank(classes.getClassId())){
			//��id �༭ ���ݻ��� 
			CrmClasses findClasses = this.classesService.findById(classes.getClassId());
			ActionContext.getContext().getValueStack().push(findClasses);
		}
		
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType();
		ActionContext.getContext().getValueStack().set("allCourseType", allCourseType);
		
		return "editUI";
	}
	
	
	public String saveOrUpdate(){
		this.classesService.addOrUpdate(classes);
		return "saveOrUpdate";
	}
	
	/**
	 * �鿴ĳ���༶
	 * @return
	 */
	public String showClass(){
		CrmClasses classes = this.classesService.findById(this.classes.getClassId());
		ActionContext.getContext().getValueStack().push(classes);
		return "showClass";
	}
	
	
	
}
