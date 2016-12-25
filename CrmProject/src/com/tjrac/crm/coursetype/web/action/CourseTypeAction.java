package com.tjrac.crm.coursetype.web.action;


import java.util.List;

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
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		/*  简单查询
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType();
		ActionContext.getContext().put("allCourseType", allCourseType);
		*/
		
		//条件查询
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType(courseType);
		ActionContext.getContext().put("allCourseType", allCourseType);
		
		return "findAll";
	}
	
	
}
