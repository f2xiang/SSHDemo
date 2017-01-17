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

	//------------ģ������-------------
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
	
	
	//--------------����----------------
	
	/**
	 * ���ɶ���
	 * @return
	 */
	public String addOrder(){
		//�������ݵ� ���ݿ�
			//���ݵĲ�ȫ
		order.setOrdertime(new Date());
		order.setState(1);   //1�� δ����     2���Ѿ����� û����  3�� �Ѿ����� ûȷ���ջ�   4���������
		//��ȡ���ﳵ������������
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//���ö����������û�
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			this.addFieldError("", "��  ����û�е�¼ ���ȵ�¼");
			return "login";
		}
		order.setUser(user);
		
		this.orderService.save(order);
		
		//��չ��ﳵ
		cart.clearCart();
		
		//�������ݵ�ҳ��
		//----order ����ģ�������� Ĭ��ջ��
		
		return "addOrder";
	}
	
	
	//-------------��ҳ����-------------------
	private Integer currentPage;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	//-------------------------------
	
	
	
	/**
	 * �ҵĶ��� ��ѯ
	 * @return
	 */
	public String findByUid(){
		//�õ��û�id
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//����service
		PageBean<Order> pageBean = this.orderService.findByPageUid(currentPage, user.getUid());
		
		//��ҳ���ݵ���ʾ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUid";
	}
	
	/**
	 * ���ݶ���id��ѯ����
	 * @return
	 */
	public String findByOid(){
		order =	this.orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	
	//-----------���� �������е�ѡ��-----------------
	private String pd_FrpId;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	//--------------------
	
	/**
	 * ֧������  ��ɶ��������ݵĲ�ȫ
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		//�޸Ķ��� ���ݲ�ȫ
		Order findOrder = this.orderService.findByOid(order.getOid());
		findOrder.setAddr(order.getAddr());
		findOrder.setPhone(order.getPhone());
		findOrder.setName(order.getName());
		this.orderService.updateOrder(findOrder);
		
		//����  - ������֧����˾ - ���� - ����֧����˾ - ��վ 
		//1 ���ò���
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
//		String p3_Amt = order.getTotal().toString();  
		String p3_Amt = "0.01";  //���Խ�� 0.01
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
		
		//2 ������֧������ ���ݲ���
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
		
		//�ض��� 302
		ServletActionContext.getResponse().sendRedirect(sbStringBuffer.toString());
		
		return NONE;
	}
	
	//-------------����ɹ� �������Ĳ���-----------------
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
	 * ����ɹ� �ص�  --ʡ����У��hmac��
	 * @return
	 */
	public String callBack(){
		//�޸Ķ�����״̬ Ϊ �Ѹ���
		Order findOrder = this.orderService.findByOid(Integer.valueOf(r6_Order));
		findOrder.setState(2);
		this.orderService.updateOrder(findOrder);
		
		//ҳ������ʾ����ɹ�����Ϣ
		this.addActionMessage("��������ɹ��� ������ţ�"+r6_Order+"�����"+r3_Amt);
		return "msg";
	}
}
