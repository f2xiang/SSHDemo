package com.tjrac.crm.coursetype.web.action;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.coursetype.service.CourseTypeService;

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
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType(courseType);
		ActionContext.getContext().put("allCourseType", allCourseType);
		
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
