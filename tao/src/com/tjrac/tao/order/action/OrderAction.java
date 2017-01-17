package com.tjrac.tao.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.cart.vo.Cart;
import com.tjrac.tao.cart.vo.CartItem;
import com.tjrac.tao.order.service.OrderService;
import com.tjrac.tao.order.vo.Order;
import com.tjrac.tao.order.vo.OrderItem;
import com.tjrac.tao.user.vo.User;
import com.tjrac.tao.util.PageBean;
import com.tjrac.tao.util.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	//------------模型驱动-------------
	Order order = new Order();
	
	@Override
	public Order getModel() {
		return order;
	}

	
	//--------------service----------------
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	//--------------动作----------------
	
	/**
	 * 生成订单
	 * @return
	 */
	public String addOrder(){
		//保存数据到 数据库
			//数据的补全
		order.setOrdertime(new Date());
		order.setState(1);   //1： 未付款     2：已经付款 没发货  3： 已经发货 没确认收货   4：交易完成
		//获取购物车里面的相关数据
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属的用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			this.addFieldError("", "亲  您还没有登录 请先登录");
			return "login";
		}
		order.setUser(user);
		
		this.orderService.save(order);
		
		//清空购物车
		cart.clearCart();
		
		//回显数据到页面
		//----order 就在模型驱动里 默认栈顶
		
		return "addOrder";
	}
	
	
	//-------------分页数据-------------------
	private Integer currentPage;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	//-------------------------------
	
	
	
	/**
	 * 我的订单 查询
	 * @return
	 */
	public String findByUid(){
		//拿到用户id
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//调用service
		PageBean<Order> pageBean = this.orderService.findByPageUid(currentPage, user.getUid());
		
		//分页数据的显示
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUid";
	}
	
	/**
	 * 根据订单id查询订单
	 * @return
	 */
	public String findByOid(){
		order =	this.orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	
	//-----------接收 付款银行的选择-----------------
	private String pd_FrpId;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	//--------------------
	
	/**
	 * 支付订单  完成订单表数据的补全
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		//修改订单 数据补全
		Order findOrder = this.orderService.findByOid(order.getOid());
		findOrder.setAddr(order.getAddr());
		findOrder.setPhone(order.getPhone());
		findOrder.setName(order.getName());
		this.orderService.updateOrder(findOrder);
		
		//付款  - 第三方支付公司 - 网银 - 三方支付公司 - 网站 
		//1 设置参数
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
//		String p3_Amt = order.getTotal().toString();  
		String p3_Amt = "0.01";  //测试金额 0.01
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://127.0.0.1:8080/tao/order_callBack.action";
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1";
		
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		//2 向三方支付出发 传递参数
		StringBuffer sbStringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sbStringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		sbStringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		sbStringBuffer.append("p2_Order=").append(p2_Order).append("&");
		sbStringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		sbStringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		sbStringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		sbStringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		sbStringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sbStringBuffer.append("p8_Url=").append(p8_Url).append("&");
		sbStringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		sbStringBuffer.append("pa_MP=").append(pa_MP).append("&");
		sbStringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		sbStringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sbStringBuffer.append("hmac=").append(hmac);
		
		//重定向 302
		ServletActionContext.getResponse().sendRedirect(sbStringBuffer.toString());
		
		return NONE;
	}
	
	//-------------付款成功 传回来的参数-----------------
	private String r3_Amt;
	
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	
	private String r6_Order;
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
	//-------------------------
	
	/**
	 * 付款成功 回调  --省略了校验hmac等
	 * @return
	 */
	public String callBack(){
		//修改订单的状态 为 已付款
		Order findOrder = this.orderService.findByOid(Integer.valueOf(r6_Order));
		findOrder.setState(2);
		this.orderService.updateOrder(findOrder);
		
		//页面上显示付款成功的信息
		this.addActionMessage("订单付款成功！ 订单编号："+r6_Order+"付款金额："+r3_Amt);
		return "msg";
	}
}
