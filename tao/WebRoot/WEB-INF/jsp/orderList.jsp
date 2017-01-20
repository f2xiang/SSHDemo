<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>我的订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="爱小淘"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	
	<%@ include file="menu.jsp" %>
	
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单列表</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
					<s:iterator value="pageBean.list" var="order">
					<tr>
						<th colspan="5">订单编号：  <s:property value="#order.oid" /> &nbsp;&nbsp;&nbsp;
							订单状态：
							<s:if test="#order.state == 1">
								<s:a namespace="/" action="order_findByOid">
									 <s:param name="oid" value="#order.oid"></s:param>
									 <font color="red">付款</font>
								</s:a>	
							</s:if>
							<s:if test="#order.state == 2">
								已付款
							</s:if>
							<s:if test="#order.state == 3">
								<s:a namespace="/" action="order_updateState">
									<s:param name="oid" value="%{#order.oid}"></s:param>
									<font color="red"> 确认收货 </font>
								</s:a>	
							</s:if>
							<s:if test="#order.state == 4">
								交易完成
							</s:if>
						</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator value="#order.orderItems" var="orderItem">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image" />"/>
							</td>
							<td>
								<a target="_blank"><s:property value="#orderItem.product.pname"/></a>
							</td>
							<td>
								<s:property value="#orderItem.product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItem.count"/> 
								<div>
									<span class="increase">&nbsp;</span>
									<span class="decrease">&nbsp;</span>
								</div>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#orderItem.subtotal"/>    </span>
							</td>
						</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<td colspan="5">
						<div class="pagination">
							<span>	第<s:property value="pageBean.currentPage" />/<s:property value="pageBean.totalPage" />页</span>
								
								<!-- 如果是第一页 就不显示首页  和 上一页 -->
								<s:if test="pageBean.currentPage != 1">
									<a href="${pageContext.request.contextPath}/order_findByUid?currentPage=1" class="firstPage">&nbsp;</a> 
									<a href="${pageContext.request.contextPath}/order_findByUid?currentPage=<s:property value="pageBean.currentPage-1" />"  class="previousPage">&nbsp;</a>
								</s:if>
								
								<!-- 如果当前页已被选中 不能再被点了  而且 加上被选中的样式 -->
								<s:iterator begin="1" end="pageBean.totalPage" var="i">
									<s:if test="pageBean.currentPage != #i">
										<a  href="${pageContext.request.contextPath}/order_findByUid?currentPage=<s:property value="#i" />"><s:property value="#i" /></a>
									</s:if>
									<s:else>
										<span class="currentPage"><s:property value="#i" /> </span>
									</s:else>
								</s:iterator>
								
								<!-- 如果在最后一页 不显示下一页 和 最后一页 -->
								<s:if test="pageBean.currentPage != pageBean.totalPage">
									<a class="nextPage" href="${pageContext.request.contextPath}/order_findByUid?currentPage=<s:property value="pageBean.currentPage+1" />">&nbsp;</a>
									<a class="lastPage" href="${pageContext.request.contextPath}/order_findByUid?currentPage=<s:property value="pageBean.totalPage" />">&nbsp;</a>
								</s:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>
