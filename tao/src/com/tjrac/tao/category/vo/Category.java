package com.tjrac.tao.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.tjrac.tao.categorysecond.vo.CategorySecond;
/**
 * һ������ģ���ʵ����
 * @author FengXiang
 *
 */
public class Category implements Serializable{
	/*
CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
	 */
	
	
	private Integer cid;
	
	private String cname;
	
	//һ��һ������  ���� �� ��� ��������  ������  һ �� [��]
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
