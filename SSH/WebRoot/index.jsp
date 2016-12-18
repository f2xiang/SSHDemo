<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>index.jsp</title>
	
  </head>
  
  <body>
     <h1>this is index page</h1>
     
     <form action="${pageContext.request.contextPath }/userAction_saveUser.action">
                            用户名：<input type="text" name="name"><br>
                            密     码:<input type="password" name="password"><br>
             <input type="submit" value="提交">                  
     </form>
     
     
  </body>
</html>
