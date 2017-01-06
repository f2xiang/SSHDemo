<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
   function showPost(obj){
   		//1 获得选中的部门
   		var deptId = obj.value;
   		//2发送ajax 根据部门查询职务
   		//2.1 获得引擎
   		var xmlhttp = null;
   		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}else {// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
   		//2.2 设置回调函数
   		xmlhttp.onreadystatechange = function(){
			//请求完成 正常响应
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				//1 获得数据 并 展示
				var textData = xmlhttp.responseText;
				
				//2 因为传过来的是字符串 所以还要手动的转化成json对象
				var jsonData = eval("("+ textData +")")
				
				//创建职务select的元素的对象 通过id进行回显数据
				var postSelectElement = document.getElementById("postSelectId");
				postSelectElement.innerHTML = "<option value=''>----请--选--择----</option>";
				
				//3 json数据的遍历
				for(var i = 0; i < jsonData.length; i++){
					var postObj = jsonData[i];
					//获得 职务 的id  和 名称
					var postId = postObj.postId;
					var postName = postObj.postName;
					//最后 回显数据
					postSelectElement.innerHTML += "<option value='"+postId+"'>"+postName+"</option>";
				}
				
			}
		};
   		//2.3 创建连接
   		var url = "${pageContext.request.contextPath}/postAction_findAllByDept?department.depId=" + deptId;
   		xmlhttp.open("GET", url);
   		//2.4 发送数据-->因为是get方式 所以数据都在url上进行拼接了 所以数据为null
   		xmlhttp.send(null);
   }
</script>
</head>

<body >
 <table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="39%" align="left">[员工管理]</td>
   
    <td width="57%"align="right">
    	<%--高级查询 --%>
		<a href="javascript:void(0)" onclick="javascript:document.forms[0].submit();"><img src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif" /></a>
    	<%--员工注入 --%>
    	 
	  	<a href="${pageContext.request.contextPath}/staffAction_addUI">
	  		<img src="${pageContext.request.contextPath}/images/button/tianjia.gif" />
	  	</a>
      
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>
	 
<!-- 查询条件：马上查询 -->
<s:form namespace="/" action="staffAction_findAll" >
	<s:hidden id="pageNum" name="pageNum" value="1"></s:hidden>
	<table width="88%" border="0" style="margin: 20px;" >
	  <tr>
	    <td width="80px">部门：</td>
	    <td width="200px">
	    	<s:select list="listDept" name="post.department.depId" onchange="showPost(this)"
			    	  listKey="depId" listValue="depName"
			    	  headerKey="" headerValue="--请选择部门--">
	    	</s:select>
	    </td>
	    <td width="80px" >职务：</td>
	    <td width="200px" >
	    	<s:select list="post != null? post.department.postSet:{}" name="post.postId"
	    	   	      listKey="postId" listValue="postName"  id="postSelectId"
	    	   	      headerKey="" headerValue="--请选择职务--">
	    	</s:select>
	    </td>
	    <td width="80px">姓名：</td>
	    <td width="200px" ><s:textfield name="staffName" size="12"></s:textfield></td>
	    <td ></td>
	  </tr>
	</table>
</s:form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
  <tr>
    <td ><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
  </tr>
</table>

<table width="100%" border="1" >
  <tr class="henglan" style="font-weight:bold;">
    <td width="10%" align="center">员工姓名</td>
    <td width="6%" align="center">性别</td>
    <td width="12%" align="center">入职时间</td>
    <td width="15%" align="center">所属部门</td>
    <td width="10%" align="center">职务</td>
    <td width="10%" align="center">编辑</td>
  </tr>
  
    <!-- 奇偶行的样式不一样 -->
    <s:iterator value="#pageBean.data" status="vs">
	  <tr class="<s:property value="#vs.even? 'tabtd1': 'tabtd2' "/>"> 
	    <td align="center"><s:property value="staffName"/>  </td>
	    <td align="center"><s:property value="gender"/>  </td>
	    <td align="center"><s:date name="onDutyDate" format="yyyy-MM-dd"/>  </td>
	    <td align="center"><s:property value="post.department.depName"/>  </td>
	    <td align="center"><s:property value="post.postName"/>  </td>
	  	<td width="7%" align="center">
	  		<s:a namespace="/" action="staffAction_editUI">
	  		    <s:param name="staffId" value="staffId"></s:param>
	  			<img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" />
	  		</s:a>
	  	</td>
	  </tr>
   </s:iterator>
</table>


<table border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td align="right">
    	<span>第     <s:property value="#pageBean.pageNum"/> / <s:property value="#pageBean.totalPage"/>  页</span>
        <span>
        	<s:if test="#pageBean.pageNum > 1">
				<a href="javascript:void(0)" onclick="showPage(1)">[首页]</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.pageNum - 1"/>)">[上一页]</a>&nbsp;&nbsp;
			</s:if>  
			   
			<s:iterator begin="#pageBean.start" end="#pageBean.end" var="num">
			   	<a href="javascript:void(0)" onclick="showPage(<s:property value="#num" />)"><s:property value="#num" /></a>&nbsp;&nbsp;
			</s:iterator>
			 
			<s:if test="#pageBean.pageNum < #pageBean.totalPage">
			    <a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.pageNum + 1"/>)">[下一页]</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.totalPage"/>)">[尾页]</a>
			</s:if> 
        </span>
    </td>
  </tr>
</table>
<script type="text/javascript">
	function showPage(num){
		//1 修改隐藏域的值
		document.getElementById("pageNum").value = num;
		//2 提交表单
		document.forms[0].submit();
	}
</script>
</body>
</html>
