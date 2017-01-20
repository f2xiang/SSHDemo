package com.tjrac.tao.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.order.service.OrderService;
import com.tjrac.tao.order.vo.Order;
import com.tjrac.tao.order.vo.OrderItem;
import com.tjrac.tao.util.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	//-----------------ģ������--------------
	
	private Order order = new Order();
	
	@Override
	public Order getModel() {
		return order;
	}

	//-------------service----------------------
	
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//-----------��ҳ����------------------------
	private int currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//-----------------------------------
	/**
	 * ��ѯ���еĶ���--����ҳ
	 * @return
	 */
	public String findAll(){
		PageBean<Order> pageBean = this.orderService.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	/**
	 * ���ݶ�����id��ѯ������
	 * @return
	 */
	public String findOrderItem(){
		List<OrderItem> list =	this.orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	
	/**
	 * �޸Ķ�����״̬
	 * @return
	 */
	public String updateState(){
		//����id��ѯ����
		Order findOrder = this.orderService.findByOid(order.getOid());
		//�޸Ķ���״̬
		findOrder.setState(3);
		//����
		this.orderService.updateOrder(findOrder);
		return "updateState";
	}
}
