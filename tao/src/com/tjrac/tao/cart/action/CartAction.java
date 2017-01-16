package com.tjrac.tao.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tjrac.tao.cart.vo.Cart;
import com.tjrac.tao.cart.vo.CartItem;
import com.tjrac.tao.product.service.ProductService;

/**
 * ���ﳵ��action
 * @author FengXiang
 *
 */
public class CartAction extends ActionSupport{
	
	//������빺�ﳵ ��������������  pid ��  count ��������
	
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
	 * �����Ʒ �� ���ﳵ
	 * @return
	 */
	public String addCart(){
		//1����װһ��cartitem����
		CartItem cartItem = new CartItem();
		//��������
		cartItem.setCount(count);
		//������Ʒ  ����id��ѯ
		cartItem.setProduct(this.productService.findByPid(pid));
		
		//2������������ӵ����ﳵ
		//���ﳵӦ�÷ŵ�session���� ��������Ӧ�ô�session������
		Cart cart = getCart();
		cart.add2Cart(cartItem);
		return "addCart";
	}

	/**
	 * ��չ��ﳵ
	 * @return
	 */
	public String clearCart(){
		//���cart����
		Cart cart = getCart();
		//������շ���
		cart.clearCart();
		
		return "clearCart";
	}
	
	
	/**
	 * �ӹ��ﳵ���Ƴ�
	 * @return
	 */
	public String delCart(){
		Cart cart = getCart();
		cart.removeFromCart(pid);
		
		return "delCart";
	}
	
	/**
	 * �ҵĹ��ﳵ��ת
	 * @return
	 */
	public String myCart(){
		return "myCart";
	}
	
	
	/**
	 * ��session�л�ù��ﳵ
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
