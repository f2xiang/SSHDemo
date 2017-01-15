<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.user == null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/user_loginUI.action">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/user_registerUI.action">注册</a>|
				</li>
				</s:if>
				<s:else>
					<li id="headerUsername" class="headerUsername" style="display: list-item;">
						欢迎你：<s:property value="#session.user.name" />|
					</li>
					<li id="headerLogout" class="headerUsername" style="display: list-item;">
						<a href="#">我的订单</a>|
					</li>
					<li id="headerLogout" class="headerLogout" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_logout.action">退出</a>|
					</li>
				</s:else>
				
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="./购物车.htm">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath }/index.action">首页</a>
						|
					</li>
					<s:iterator value="#session.cList" var="category">
					<li>
						<s:a namespace="/" action="product_findByCid">
							<s:param name="cid" value="#category.cid"></s:param> 
							<s:param name="currentPage" value="1"></s:param> 
							<s:property value="#category.cname"/> 
						</s:a>
						|
					</li>
					</s:iterator>
					
		</ul>
	</div>