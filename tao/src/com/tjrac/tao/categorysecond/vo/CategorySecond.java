package com.tjrac.tao.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.tjrac.tao.category.vo.Category;
import com.tjrac.tao.product.vo.Product;

/**
 * ��������ʵ����
 * @author FengXiang
 *
 */
public class CategorySecond implements Serializable {
	/*
   CREATE TABLE `categorysecond` (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `FK936FCAF21DB1FD15` (`cid`),
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
 */
	
	private Integer csid;
	
	private String csname;
	
	//��� �������� ���� һ�� һ������  ���� �� �� [һ]
	private Category category;  //һ����������
	
	
	//һ���������������� ��� ��Ʒ ���� �� һ �� ���ࡿ
	private Set<Product> products = new HashSet<Product>();
	

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
}
