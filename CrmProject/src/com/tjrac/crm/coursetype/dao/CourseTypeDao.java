package com.tjrac.crm.coursetype.dao;

import java.util.List;

import com.tjrac.crm.coursetype.domain.CrmCourseType;

public interface CourseTypeDao {
	/**
	 * 查询课程类别的所有
	 * @return
	 */
	public List<CrmCourseType> findAll();

	/**
	 * 条件查询
	 * @param condition
	 * @param params
	 * @return
	 */
	public List<CrmCourseType> findAll(String condition, Object[] params);
	
	/**
	 * 通过id查找课程类别
	 * @param courseTypeId 课程类别
	 * @return
	 */
	public CrmCourseType findById(String courseTypeId);
	
	/**
	 * 保存 或者 更新 课程类别
	 * @param courseType
	 */
	public void saveOrUpdate(CrmCourseType courseType);

	/**
	 * 分页  根据条件查询出数据 得到数据的总记录数
	 * @param condition  条件
	 * @param params
	 * @return  条件数据的记录数
	 */
	public int getTotalRecord(String condition, Object[] params);

	/**
	 * 分页  查询结果
	 * @param condition 条件
	 * @param params   条件参数
	 * @param startIndex  分页起始
	 * @param pageSize    每页的条数
	 * @return      某页的数据
	 */
	public List<CrmCourseType> findAll(String condition, Object[] params,
			int startIndex, int pageSize);
}
