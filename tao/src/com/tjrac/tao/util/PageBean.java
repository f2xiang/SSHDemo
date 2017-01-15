package com.tjrac.tao.util;

import java.util.List;

/**
 * 分页实体类的封装
 * @author FengXiang
 * @param <T>
 *
 */
public class PageBean<T> {
	private int currentPage;  //当前页
	
	private int totalCount;  //总的记录数
	
	private int totalPage;  //总的页数
	
	private int pageCount; //每页显示的记录数
	
	private List<T> list;  //数据
	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
	
}
