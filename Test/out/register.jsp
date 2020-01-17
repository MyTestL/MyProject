<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/16
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>注册</title>
</head>
<body>
<h1>${msg}</h1>
<form action="UploadServlet?type=register" method="post" enctype="multipart/form-data">
	用户名:<input type="text" name="username">
	密码:<input type="password" name="password">
	爱好:<input type="checkbox" name="loves" value="eat">吃
	<input type="checkbox" name="loves" value="drink">喝
	文件上传:<input type="file" name="file">
	<input type="submit" value="提交">
</form>
</body>
</html>
