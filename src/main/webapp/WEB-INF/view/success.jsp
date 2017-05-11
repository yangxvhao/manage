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
<table>
    <tr><td><span>欢迎<span>${user.name}</span>进入审批管理系统</span></td></tr>
    <tr><td><a href="/changePwd/${user.name}/${user.role}">修改密码</a></td>
        <td><a href="/apply/manage/${user.name}/${user.role}">申请单管理</a></td>
        <td><a href="/flow/manage/${user.name}/${user.role}">流程管理</a><span style="color:red">${flow_error}</span></td>
        <td><a href="/report/manage/${user.name}/${user.role}">报表管理</a><span style="color:red">${cost_error}</span></td>
        <td><a href="/index">退出</a></td>
    <tr></tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>
</body>
</html>
