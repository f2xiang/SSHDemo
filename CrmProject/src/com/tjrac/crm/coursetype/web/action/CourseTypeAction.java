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
	
	
	//-----------------分页数据-----------------
	private int pageNum = 1;   //默认当前页 为1
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private int pageSize = 1;   //默认每页显示2条数据
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//////////////////////////////////////////////
	
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
		/*
		List<CrmCourseType> allCourseType = courseTypeService.findAllCourseType(courseType);
		ActionContext.getContext().put("allCourseType", allCourseType);
		*/
		
		//分页 + 条件
		PageBean<CrmCourseType> pageBean = 
				courseTypeService.findAll(courseType, pageNum, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "findAll";
	}
	
	/**
	 * 点击编辑的时候 ui界面 数据的回显
	 * @return
	 */
	public String addOrEditUI(){
		//如果传过来的bean对象里有id  那就是编辑  没有id 就是添加
		if(StringUtils.isNotBlank(this.courseType.getCourseTypeId())){
			//根据id查询到的数据 压入值栈    让jsp页面进行数据的回显
			CrmCourseType findCourseType = this.courseTypeService.findById(courseType.getCourseTypeId());
			ActionContext.getContext().getValueStack().push(findCourseType);
		}
		
		return "addOrEditUI";
	}
	
	/**
	 * 保存 或者 更新 课程类别
	 * @return
	 */
	public String addOrEdit(){
		this.courseTypeService.addOrEdit(courseType);
		return "addOrEdit";
	}
	
}
