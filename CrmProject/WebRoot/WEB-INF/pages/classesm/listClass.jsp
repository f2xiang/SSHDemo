<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sys.css" type="text/css"/>
<title>班级管理</title>
</head>

<body>
<!--距离页面顶端5px-->
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="topg"></td>
  </tr>
</table>
<form name="createForm" action="" method="post">
<table border="0" cellspacing="0" cellpadding="0" class="wukuang">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="20%" align="left">[班级管理]</td>
    <td width="42%"align="center">&nbsp;</td>
    <td width="36%"align="right">
    	<%--添加班级 --%>
    	<s:a namespace="/" action="classesAction_editUI">
    		<img src="${pageContext.request.contextPath}/images/button/tianjia.gif" class="img"/>
    	</s:a>
    	<%--高级查询 
        <a href="queryClass.html"><img src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif" class="img"/></a>
    	--%>
    </td>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>
</form>
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="topg"></td>
  </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td ><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
  </tr>
</table>

<table width="97%" border="1">
<thead>
  <tr class="henglan" style="font-weight:bold;">
	<th width="150px" align="center">班级名称</th>
    <th width="200px" align="center">所属课程</th>
    <th width="80px" align="center">开班时间</th>
    <th width="80px" align="center">毕业时间</th>
    
    <th width="80px" align="center">状态</th>
    <th width="70px" align="center">学生总数</th>
    <th width="70px" align="center">升学数</th>
    <th width="70px" align="center">转班数</th>
    <th width="50px" align="center">编辑</th>
	<th width="50px" align="center">查看</th>
	<th align="center">课表</th>
  </tr>
  </thead>
  
  

  <tbody>
  	<s:iterator value="#pageBean.data" status="vs">
	  <tr class="<s:property value="#vs.even? 'tabtd2': 'tabtd1' "/>">
	    <td align="center"><s:property value="name" /> </td>
	    <td align="center"><s:property value="courseType.courseName" />  </td>
	    <td align="center"><s:date name="beginTime" format="yyyy-MM-dd" /></td>
	    <td align="center"><s:date name="endTime" format="yyyy-MM-dd" /></td>
	    <td align="center"><s:property value="status"/></td>
	    <td align="center"><s:property value="totalCount"/></td>
	    <td align="center"><s:property value="upgradeCount"/></td>
	    <td align="center"><s:property value="changeCount"/></td>
	    <td align="center">
	    	<s:a namespace="/" action="classesAction_editUI">
	    		<s:param name="classId" value="classId"></s:param>
	    		<img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/>
	    	</s:a>
	    </td>
		<td align="center">
			<s:a namespace="/" action="classesAction_showClass">
	    		<s:param name="classId" value="classId"></s:param>
				<img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/>
			</s:a>
		</td>
		<td align="center" title="上次上传时间：2015-04-02">   
			<s:a namespace="/" action="classesAction_uploadUI">
				上传
				<s:param name="classId" value="classId"></s:param>
			</s:a>
			<a href="${pageContext.request.contextPath}/pages/classesm/downloadClass">下载</a> <br/>
		</td>
	  </tr>
	  </s:iterator>
  </tbody>
</table>


<s:form namespace="/" action="classesAction_findAll">
	  <s:hidden id="pageNum" name="pageNum" value="1"></s:hidden>
</s:form>

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
		document.forms[1].submit();
	}
</script>
</body>
</html>
