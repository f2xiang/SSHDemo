package com.tjrac.bos.service;

import com.tjrac.bos.domain.Staff;
import com.tjrac.bos.utils.PageBean;

public interface StaffService {

	public void add(Staff model);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 逻辑删除 ： 修改删除的标志位 为 1
	 * @param ids
	 */
	public void deleteBatch(String ids);

	/**
	 * 修改信息
	 * @param model
	 */
	public void update(Staff staff);

	/**
	 * 通过id找到员工
	 * @param id
	 * @return
	 */
	public Staff findById(String id);

	/**
	 * 逻辑还原：将删除的标志位为1的员工 改为0
	 * @param ids
	 */
	public void restoreBatch(String ids);

}
