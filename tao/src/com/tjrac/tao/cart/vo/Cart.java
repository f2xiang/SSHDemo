package com.tjrac.tao.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象  封装购物项的集合 以及 总计
 * @author FengXiang
 *
 */
public class Cart implements Serializable{
	//-------------购物车 属性的封装 -----------
	//保证key唯一 所以 用了 商品的id作为key value：CartItem
	Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;  //总计

	
	//--------------购物车功能-------------------
	/**
	 * 添加一个购物项
	 * @param cartItem
	 */
	public void add2Cart(CartItem cartItem){
		//判断购物车是否已经存在购物项了
	        //--存在  1、数量增加  2、总计增加  -- 总计  = 总计 + 小计
		    //--不存在  1、向map中添加  2、总计也要增加
		Integer pid = cartItem.getProduct().getPid();
		
		if(map.containsKey(pid)){   //存在
			CartItem old =	map.get(pid);     //获得购物车中原来的购物项
			old.setCount(old.getCount() + cartItem.getCount());  //原来的数量 + 加入的数量
		}else{                      //不存在
			map.put(pid, cartItem);
		}
		
		//设置总计
		this.total += cartItem.getSubtotal();
	
	}
	
	
	/**
	 * 移除一个购物项 --根据key值来移除
	 * @param pid map的key
	 */
	public void removeFromCart(Integer pid){
		//1、将购物项移出购物车  --remove方法 移出返回 被移除的value
		CartItem cartItem =	map.remove(pid);
		
		//2、总计发生变化  -- 减掉 移出购物项的小计
		this.total -= cartItem.getSubtotal();
	}
	
	
	/**
	 * 清空购物车
	 */
	public void clearCart(){
		//1、清空所有的购物项
		map.clear();
		
		//2、把总计设置为0
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
