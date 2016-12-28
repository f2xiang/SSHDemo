package com.tjrac.crm.coursetype.web.action;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.coursetype.service.CourseTypeService;
import com.tjrac.crm.page.PageBean;

public class CourseTypeAction extends ActionSupport implements ModelDriven<CrmCourseType> {

	private CrmCourseType courseType = new CrmCourseType();
	
	@Override
	public CrmCourseType getModel() {
		return courseType;
	}
	
	//----------------service----------------
	private CourseTypeService courseTypeService;
	
	public void setCourseTypeService(CourseTypeService courseTypeService) {
		this.courseTypeService = courseTypeService;
	}
	
	
	//-----------------��ҳ����-----------------
	private int pageNum = 1;   //Ĭ�ϵ�ǰҳ Ϊ1
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private int pageSize = 1;   //Ĭ��ÿҳ��ʾ2������
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//////////////////////////////////////////////
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public String findAll(){
		/*  �򵥲�ѯ
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType();
		ActionContext.getContext().put("allCourseType", allCourseType);
		*/
		
		//������ѯ
		/*
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType(courseType);
		ActionContext.getContext().put("allCourseType", allCourseType);
		*/
		
		//��ҳ + ����
		PageBean<CrmCourseType> pageBean = 
				courseTypeService.findAll(courseType, pageNum, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "findAll";
	}
	
	/**
	 * ����༭��ʱ�� ui���� ���ݵĻ���
	 * @return
	 */
	public String addOrEditUI(){
		//�����������bean��������id  �Ǿ��Ǳ༭  û��id �������
		if(StringUtils.isNotBlank(this.courseType.getCourseTypeId())){
			//����id��ѯ�������� ѹ��ֵջ    ��jspҳ��������ݵĻ���
			CrmCourseType findCourseType = this.courseTypeService.findById(courseType.getCourseTypeId());
			ActionContext.getContext().getValueStack().push(findCourseType);
		}
		
		return "addOrEditUI";
	}
	
	/**
	 * ���� ���� ���� �γ����
	 * @return
	 */
	public String addOrEdit(){
		this.courseTypeService.addOrEdit(courseType);
		return "addOrEdit";
	}
	
}
