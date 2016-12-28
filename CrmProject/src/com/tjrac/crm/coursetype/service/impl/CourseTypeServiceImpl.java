package com.tjrac.crm.coursetype.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tjrac.crm.coursetype.dao.CourseTypeDao;
import com.tjrac.crm.coursetype.domain.CrmCourseType;
import com.tjrac.crm.coursetype.service.CourseTypeService;
import com.tjrac.crm.page.PageBean;

public class CourseTypeServiceImpl implements CourseTypeService{

	private CourseTypeDao courseTypeDao;
	
	public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
		this.courseTypeDao = courseTypeDao;
	}
	
	@Override
	public List<CrmCourseType> findAllCourseType() {
		return courseTypeDao.findAll();
	}

	@Override
	public List<CrmCourseType> findAllCourseType(CrmCourseType courseType) {
		//拼凑查询条件  以及   实际参数
		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		
		//过滤条件  
		//1、课程名称 不为空 才 进行 查询  而且 应该 用 模糊查询 
		if(StringUtils.isNotBlank(courseType.getCourseName())){
			sb.append(" and courseName like ?");
			paramsList.add("%"+courseType.getCourseName()+"%");
		}
		
		//2、课程简介
		if(StringUtils.isNotBlank(courseType.getRemark())){
			sb.append(" and remark like ?");
			paramsList.add("%"+ courseType.getRemark() +"%");
		}
		
		//3、总学时    a~b
		if(StringUtils.isNotBlank(courseType.getTotalStart())){
			sb.append(" and total >= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getTotalEnd())){
			sb.append(" and toal <= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalEnd()));
		}
		
		
		//4、课程费用 a~b
		if(StringUtils.isNotBlank(courseType.getCourseCostStart())){
			sb.append(" and courseCost >= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getCourseCostEnd())){
			sb.append(" and courseCost <= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostEnd()));
		}
		
		
		//有几个条件 就有几个参数
		String condition = sb.toString();
		
		Object [] params = paramsList.toArray();
		
		return courseTypeDao.findAll(condition, params);
	}

	@Override
	public CrmCourseType findById(String courseTypeId) {
		return courseTypeDao.findById(courseTypeId);
	}

	@Override
	public void addOrEdit(CrmCourseType courseType) {
		courseTypeDao.saveOrUpdate(courseType);
	}

	@Override
	public PageBean<CrmCourseType> findAll(CrmCourseType courseType, int pageNum,
			int pageSize) {
		
		//1 条件查询
		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		
		//过滤条件  
		//1、课程名称 不为空 才 进行 查询  而且 应该 用 模糊查询 
		if(StringUtils.isNotBlank(courseType.getCourseName())){
			sb.append(" and courseName like ?");
			paramsList.add("%"+courseType.getCourseName()+"%");
		}
		
		//2、课程简介
		if(StringUtils.isNotBlank(courseType.getRemark())){
			sb.append(" and remark like ?");
			paramsList.add("%"+ courseType.getRemark() +"%");
		}
		
		//3、总学时    a~b
		if(StringUtils.isNotBlank(courseType.getTotalStart())){
			sb.append(" and total >= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getTotalEnd())){
			sb.append(" and toal <= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalEnd()));
		}
		
		
		//4、课程费用 a~b
		if(StringUtils.isNotBlank(courseType.getCourseCostStart())){
			sb.append(" and courseCost >= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getCourseCostEnd())){
			sb.append(" and courseCost <= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostEnd()));
		}
		
		
		//有几个条件 就有几个参数
		String condition = sb.toString();
		
		Object [] params = paramsList.toArray();
		
		
		//2分页
		
		//2.1 总的记录数   调用dao 查询总的记录（包括 条件）
		int totalRecord = this.courseTypeDao.getTotalRecord(condition, params);

		//2.2 创建对象
		PageBean<CrmCourseType> pageBean = new PageBean<CrmCourseType>(pageNum, pageSize, totalRecord);
		
		//2.3 分页数据
		List<CrmCourseType> data = 
				this.courseTypeDao.findAll(condition, params, pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);
		
		return pageBean;
	}

}
