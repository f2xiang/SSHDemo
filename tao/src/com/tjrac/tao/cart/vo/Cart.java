package com.tjrac.tao.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ����  ��װ������ļ��� �Լ� �ܼ�
 * @author FengXiang
 *
 */
public class Cart implements Serializable{
	//-------------���ﳵ ���Եķ�װ -----------
	//��֤keyΨһ ���� ���� ��Ʒ��id��Ϊkey value��CartItem
	Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;  //�ܼ�

	
	//--------------���ﳵ����-------------------
	/**
	 * ���һ��������
	 * @param cartItem
	 */
	public void add2Cart(CartItem cartItem){
		//�жϹ��ﳵ�Ƿ��Ѿ����ڹ�������
	        //--����  1����������  2���ܼ�����  -- �ܼ�  = �ܼ� + С��
		    //--������  1����map�����  2���ܼ�ҲҪ����
		Integer pid = cartItem.getProduct().getPid();
		
		if(map.containsKey(pid)){   //����
			CartItem old =	map.get(pid);     //��ù��ﳵ��ԭ���Ĺ�����
			old.setCount(old.getCount() + cartItem.getCount());  //ԭ�������� + ���������
		}else{                      //������
			map.put(pid, cartItem);
		}
		
		//�����ܼ�
		this.total += cartItem.getSubtotal();
	
	}
	
	
	/**
	 * �Ƴ�һ�������� --����keyֵ���Ƴ�
	 * @param pid map��key
	 */
	public void removeFromCart(Integer pid){
		//1�����������Ƴ����ﳵ  --remove���� �Ƴ����� ���Ƴ���value
		CartItem cartItem =	map.remove(pid);
		
		//2���ܼƷ����仯  -- ���� �Ƴ��������С��
		this.total -= cartItem.getSubtotal();
	}
	
	
	/**
	 * ��չ��ﳵ
	 */
	public void clearCart(){
		//1��������еĹ�����
		map.clear();
		
		//2�����ܼ�����Ϊ0
		this.total = 0;
	}
	
	
	
	
	
	//--------------set  get-------------------
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
}
