<%@ page import="com.credit.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/6
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信贷审批管理系统-登录</title>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<table align="center">
    <tr><td align="center"><h1>欢迎<%=session.getAttribute("name").toString()%>进入审批管理系统</h1></td></tr>
    <tr>
        <td>>><a href="/changePwd">修改密码</a></td>
        </tr>
        <tr><td>>><a href="/apply/manage">申请单管理</a></td></tr>
        <tr><td>>><a href="/flow/manage">流程管理</a></td></tr>
        <tr><td><span style="color:red;font-size:10px">${flow_error}</span></td></tr>
        <tr><td>
            >><a href="/report/manage">报表管理</a>
        </td></tr>
        <tr><td>
            <span style="color:red;font-size:10px">${report_error}</span>
        </td></tr>
        <tr><td>
            >><a href="/logout">退出</a></td>
    </tr>
</table>
</body>
</html>
</body>
</html>
