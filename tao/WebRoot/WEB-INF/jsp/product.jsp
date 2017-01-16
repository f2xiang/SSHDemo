<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<title>爱小淘</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

<script>
	function addCar(){
		document.getElementById("cartForm").submit();
	}
</script>
</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a>
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="爱小淘"/>
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="image\r___________renleipic_01/header.jpg" alt="正品保障" title="正品保障" height="50" width="320"/>
		</div>	
	</div>
	
	<%@ include file="menu.jsp" %>
</div>
	<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.cList" var="c">
				<dl>
					<dt>
						<a href="${pageContext.request.contextPath}/product_findByCid?cid=<s:property value="#c.cid" />&currentPage=1"><s:property value="#c.cname" /> </a>
					</dt>
					<s:iterator value="#c.categorySeconds" var="cs">
						<dd>
							<a href="${pageContext.request.contextPath}/product_findByCsid?csid=<s:property value="#cs.csid" />&currentPage=1"><s:property value="#cs.csname" /> </a>
						</dd>
					</s:iterator>
				</dl>
				</s:iterator>
			</div>
			

		</div>
		
		<!-- 商品详情 -->
		<div class="span18 last">
			
			<div class="productImage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
						<div class="zoomPad">
						<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath}/<s:property value="model.image" />"/>
						<div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;" class="zoomPup"></div>
						<div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;" class="zoomWindow"><div style="width: 368px;" class="zoomWrapper"><div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div><div style="width: 0%; height: 0px;" class="zoomWrapperImage"><img src="" style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;"></div></div></div><div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">Loading zoom</div></div>
					</a>
				
			</div>
			<div class="name"><s:property value="model.pname" /> </div>
			<div class="sn">
				<div>编号：<s:property value="model.pid" /> </div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong>￥： <s:property value="model.shop_price" /> </strong>
							参 考 价：
							<del>￥  <s:property value="model.market_price" /> 元/份</del>
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt>    </dt>
						<dd>
							<span>    </span>
						</dd>
					</dl>
			</div>
			<!-- 加个表单 点击加入购物车 把数量传上去 -->
			<form action="${pageContext.request.contextPath}/cart_addCart.action" method="post" id="cartForm">
				<input type="hidden" name="pid" value="<s:property value="model.pid" />" />
				<div class="action">
					<dl class="quantity">
						<dt>购买数量:</dt>
						<dd>
							<input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text"/>
						</dd>
						<dd>
							件
						</dd>
					</dl>
					<div class="buy">
						<input id="add2Cart" class="addCart" value="加入购物车" type="button" onclick="addCar()"/>
					</div>
				</div>
			</form>
			<div id="bar" class="bar">
				<ul>
						<li id="introductionTab">
							<a href="#introduction"> <s:property value="model.pdesc" /> </a>
						</li>
						
				</ul>
			</div>
				
				<div id="introduction" name="introduction" class="introduction">
					<div class="title">
						<strong>商品介绍</strong>
					</div>
					<div>
						<img src="${pageContext.request.contextPath}/<s:property value="model.image" />"/>
					</div>
				</div>
				
				
				
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