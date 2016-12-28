package com.tjrac.crm.page;

import java.util.List;

public class PageBean<T> {
	//3个必选项
	private int pageNum;
	
	private int pageSize;
	
	private int totalRecord;
	
	//2个计算项
	private int startIndex;
	
	private int totalPage;
	
	//数据
	private List<T> data;
	
	//动态数据条
	private int start ;
	private int end ;
	
	
	public PageBean(int pageNum, int pageSize, int totalRecord) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		
		//计算
		
		//开始的索引值
		this.startIndex = (this.pageNum - 1) * this.pageSize;
		
		//总的页数   总的记录数 + 最大半页 数 /  一页数 
		this.totalPage = (this.totalRecord + this.pageSize - 1) / this.pageSize;
		
		
		//动态数据条
		//1、初始化开始 和 结束的值
		this.start = 1;
		this.end = 10;
		
		
		//(如果总的数据为4) 
		if(this.end > this.totalPage){
			this.end = this.totalPage;
		}else{
			//如果总的数据为33   当前页 前4 后 5
			this.start = this.pageNum - 4;
			this.end = this.pageNum + 5;
			
			//那么第一页呢？ 1-4 = 负数 
			if(this.start < 1){
				this.start = 1;
				this.end = 10;
			}
			
			//那么超出最后一页了呢  总共22条数据   21 + 5 > 总的页数
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
