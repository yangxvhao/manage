<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>信贷审批管理系统-注册</title>
</head>
<body>
<table>
    <tr><td><span>欢迎进入审批管理系统</span></td></tr>
    <tr><td><form action="/index" method="post" ><span style="color:red">${error}</span><br/>
        用户名：<input type="text" name="name" path="name"/><br/>
        密  码：<input type="password" name="password" path="password"/><br/>
        角  色：<input type="radio" name="role" value="经理" checked>经理</input>
        <input type="radio" name="role" value="办单员">办单员</input><br/>
        <input type="submit" name="注册" value="注册"/>
        <input type="reset" name="重置"/>
    </form>
    </td></tr>
    <tr><td>已注册：<a href="/login">登录</a></td></tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>