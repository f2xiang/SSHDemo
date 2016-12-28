package com.tjrac.crm.page;

import java.util.List;

public class PageBean<T> {
	//3����ѡ��
	private int pageNum;
	
	private int pageSize;
	
	private int totalRecord;
	
	//2��������
	private int startIndex;
	
	private int totalPage;
	
	//����
	private List<T> data;
	
	//��̬������
	private int start ;
	private int end ;
	
	
	public PageBean(int pageNum, int pageSize, int totalRecord) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		
		//����
		
		//��ʼ������ֵ
		this.startIndex = (this.pageNum - 1) * this.pageSize;
		
		//�ܵ�ҳ��   �ܵļ�¼�� + ����ҳ �� /  һҳ�� 
		this.totalPage = (this.totalRecord + this.pageSize - 1) / this.pageSize;
		
		
		//��̬������
		//1����ʼ����ʼ �� ������ֵ
		this.start = 1;
		this.end = 10;
		
		
		//(����ܵ�����Ϊ4) 
		if(this.end > this.totalPage){
			this.end = this.totalPage;
		}else{
			//����ܵ�����Ϊ33   ��ǰҳ ǰ4 �� 5
			this.start = this.pageNum - 4;
			this.end = this.pageNum + 5;
			
			//��ô��һҳ�أ� 1-4 = ���� 
			if(this.start < 1){
				this.start = 1;
				this.end = 10;
			}
			
			//��ô�������һҳ����  �ܹ�22������   21 + 5 > �ܵ�ҳ��
			if(this.end > this.totalPage){
				this.end = this.totalPage;
				this.start = this.totalPage - 9;
			}
		}
		
		
		
		
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	
	
	
}
