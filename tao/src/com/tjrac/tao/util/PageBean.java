package com.tjrac.tao.util;

import java.util.List;

/**
 * ��ҳʵ����ķ�װ
 * @author FengXiang
 * @param <T>
 *
 */
public class PageBean<T> {
	private int currentPage;  //��ǰҳ
	
	private int totalCount;  //�ܵļ�¼��
	
	private int totalPage;  //�ܵ�ҳ��
	
	private int pageCount; //ÿҳ��ʾ�ļ�¼��
	
	private List<T> list;  //����
	

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
