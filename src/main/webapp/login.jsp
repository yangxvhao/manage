<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/6
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<html>
<head>
    <title>信贷审批管理系统-登录</title>
</head>
<body>
<h1>请登录！</h1>
<table>
    <tr><td><span>欢迎进入审批管理系统</span></td></tr>
    <tr><td><form action="/login" method="post" >
        用户名：<input type="text" name="name" path="name"/><br/>
        密  码：<input type="password" name="password" path="password"/><br/>
        角  色：<input type="radio" name="role" value="0">经理</input>
        <input type="radio" name="role" value="1">办单员</input><br/>
        <input type="submit" name="登录" value="登录"/>
        <input type="reset" name="重置"/>
    </form></td></tr>
    <tr><td><a href="index.jsp">注册</a></td> </tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>
</body>
</html>
