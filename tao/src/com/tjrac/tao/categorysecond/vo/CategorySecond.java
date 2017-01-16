package com.tjrac.tao.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.tjrac.tao.category.vo.Category;
import com.tjrac.tao.product.vo.Product;

/**
 * 二级分类实体类
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
	
	//多个 二级分类 属于 一个 一级分类  所以 多 对 [一]
	private Category category;  //一级分类的外键
	
	
	//一个二级分类下面有 多个 商品 所以 是 一 对 【多】
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
