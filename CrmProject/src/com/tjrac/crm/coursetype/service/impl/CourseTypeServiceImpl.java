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
		//ƴ�ղ�ѯ����  �Լ�   ʵ�ʲ���
		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		
		//��������  
		//1���γ����� ��Ϊ�� �� ���� ��ѯ  ���� Ӧ�� �� ģ����ѯ 
		if(StringUtils.isNotBlank(courseType.getCourseName())){
			sb.append(" and courseName like ?");
			paramsList.add("%"+courseType.getCourseName()+"%");
		}
		
		//2���γ̼��
		if(StringUtils.isNotBlank(courseType.getRemark())){
			sb.append(" and remark like ?");
			paramsList.add("%"+ courseType.getRemark() +"%");
		}
		
		//3����ѧʱ    a~b
		if(StringUtils.isNotBlank(courseType.getTotalStart())){
			sb.append(" and total >= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getTotalEnd())){
			sb.append(" and toal <= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalEnd()));
		}
		
		
		//4���γ̷��� a~b
		if(StringUtils.isNotBlank(courseType.getCourseCostStart())){
			sb.append(" and courseCost >= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getCourseCostEnd())){
			sb.append(" and courseCost <= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostEnd()));
		}
		
		
		//�м������� ���м�������
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
		
		//1 ������ѯ
		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<Object>();
		
		//��������  
		//1���γ����� ��Ϊ�� �� ���� ��ѯ  ���� Ӧ�� �� ģ����ѯ 
		if(StringUtils.isNotBlank(courseType.getCourseName())){
			sb.append(" and courseName like ?");
			paramsList.add("%"+courseType.getCourseName()+"%");
		}
		
		//2���γ̼��
		if(StringUtils.isNotBlank(courseType.getRemark())){
			sb.append(" and remark like ?");
			paramsList.add("%"+ courseType.getRemark() +"%");
		}
		
		//3����ѧʱ    a~b
		if(StringUtils.isNotBlank(courseType.getTotalStart())){
			sb.append(" and total >= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getTotalEnd())){
			sb.append(" and toal <= ?");
			paramsList.add(Integer.valueOf(courseType.getTotalEnd()));
		}
		
		
		//4���γ̷��� a~b
		if(StringUtils.isNotBlank(courseType.getCourseCostStart())){
			sb.append(" and courseCost >= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostStart()));
		}
		
		if(StringUtils.isNotBlank(courseType.getCourseCostEnd())){
			sb.append(" and courseCost <= ?");
			paramsList.add(Double.valueOf(courseType.getCourseCostEnd()));
		}
		
		
		//�м������� ���м�������
		String condition = sb.toString();
		
		Object [] params = paramsList.toArray();
		
		
		//2��ҳ
		
		//2.1 �ܵļ�¼��   ����dao ��ѯ�ܵļ�¼������ ������
		int totalRecord = this.courseTypeDao.getTotalRecord(condition, params);

		//2.2 ��������
		PageBean<CrmCourseType> pageBean = new PageBean<CrmCourseType>(pageNum, pageSize, totalRecord);
		
		//2.3 ��ҳ����
		List<CrmCourseType> data = 
				this.courseTypeDao.findAll(condition, params, pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);
		
		return pageBean;
	}

}
