package com.tjrac.tao.cart.vo;

import java.io.Serializable;

import com.tjrac.tao.product.vo.Product;

/**
 * ��������� �����Ʒ��Ϣ  ���� С�Ƶ�
 * @author FengXiang
 *
 */
public class CartItem implements Serializable{
	private Product product;   //�������Ʒ��Ϣ
	
	private int count;  //����
	 
	private double subtotal; //С�� -- ֻ��get���� û��set  get����ֱ�ӻ�ȡ  ���� * ����

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
	 * ��� С��  ��Ʒ�ĵ��� * ����
	 * @return
	 */
	public double getSubtotal() {  
		return this.product.getShop_price() * this.count;
	}

	
	
}
