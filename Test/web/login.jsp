<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/16
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登录</title>
</head>
<body>
<form action="LoginServlet" method="get">
	<input type="hidden" name="type" value="login"/>
	用户名:<input type="text" name="username"/><br/>
	密码:<input type="password" name="password"/><br/>
	<input type="submit" value="登录"/>
</form>
</body>
</html>
