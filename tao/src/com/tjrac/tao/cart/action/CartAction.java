package com.tjrac.tao.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tjrac.tao.cart.vo.Cart;
import com.tjrac.tao.cart.vo.CartItem;
import com.tjrac.tao.product.service.ProductService;

/**
 * 购物车的action
 * @author FengXiang
 *
 */
public class CartAction extends ActionSupport{
	
	//点击加入购物车 传过来两个参数  pid 和  count 购买数量
	
	private Integer pid;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	private Integer count;
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	//---------------service-----------
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 添加商品 到 购物车
	 * @return
	 */
	public String addCart(){
		//1、封装一个cartitem对象
		CartItem cartItem = new CartItem();
		//设置数量
		cartItem.setCount(count);
		//设置商品  根据id查询
		cartItem.setProduct(this.productService.findByPid(pid));
		
		//2、将购物项添加到购物车
		//购物车应该放到session里面 所以我们应该从session里面获得
		Cart cart = getCart();
		cart.add2Cart(cartItem);
		return "addCart";
	}

	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart(){
		//获得cart对象
		Cart cart = getCart();
		//调用清空方法
		cart.clearCart();
		
		return "clearCart";
	}
	
	
	/**
	 * 从购物车中移除
	 * @return
	 */
	public String delCart(){
		Cart cart = getCart();
		cart.removeFromCart(pid);
		
		return "delCart";
	}
	
	/**
	 * 我的购物车跳转
	 * @return
	 */
	public String myCart(){
		return "myCart";
	}
	
	
	/**
	 * 从session中获得购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	
}
