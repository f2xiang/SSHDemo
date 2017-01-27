package com.tjrac.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * ��ҳ��Ϣ��װ
 * @author FengXiang
 *
 */
public class PageBean {
	private int currentPage;
	
	private int pageSize;
	
	private int total;
	
	private DetachedCriteria detachedCriteria;  //���߲�ѯ�������� ��װ��װ����
	
	private List rows; //��ǰҳ��Ҫչʾ�����ݵļ��� --���ҳ����easyui�ķ�ҳ��ʾ��ʹ��

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
