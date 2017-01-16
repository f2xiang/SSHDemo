package com.tjrac.tao.cart.vo;

import java.io.Serializable;

import com.tjrac.tao.product.vo.Product;

/**
 * 购物项对象 买的商品信息  数量 小计等
 * @author FengXiang
 *
 */
public class CartItem implements Serializable{
	private Product product;   //购买的商品信息
	
	private int count;  //数量
	 
	private double subtotal; //小计 -- 只有get方法 没有set  get里面直接获取  单价 * 数量

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 获得 小计  商品的单价 * 数量
	 * @return
	 */
	public double getSubtotal() {  
		return this.product.getShop_price() * this.count;
	}

	
	
}
