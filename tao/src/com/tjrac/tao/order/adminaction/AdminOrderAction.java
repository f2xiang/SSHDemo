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

	//-----------------模型驱动--------------
	
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
	
	//-----------分页数据------------------------
	private int currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//-----------------------------------
	/**
	 * 查询所有的订单--带分页
	 * @return
	 */
	public String findAll(){
		PageBean<Order> pageBean = this.orderService.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	/**
	 * 根据订单的id查询订单项
	 * @return
	 */
	public String findOrderItem(){
		List<OrderItem> list =	this.orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	
	/**
	 * 修改订单的状态
	 * @return
	 */
	public String updateState(){
		//根据id查询订单
		Order findOrder = this.orderService.findByOid(order.getOid());
		//修改订单状态
		findOrder.setState(3);
		//更新
		this.orderService.updateOrder(findOrder);
		return "updateState";
	}
}
