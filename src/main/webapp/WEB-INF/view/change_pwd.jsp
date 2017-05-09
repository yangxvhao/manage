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
    <title>信贷审批管理系统-修改密码</title>
</head>
<body>
<%
    session.getAttribute("user");
%>
<table>
    <tr><td><span>欢迎进入审批管理系统</span></td></tr>
    <tr><td>${change_succes}</td></tr>
    <tr><td><form action="/changePwd" method="post" >
        用户名：<input  type="text" name="name" value=${name}><br/>
        新密码：<input type="password" name="password" /><br/>
        角  色：<input type="text" name="role" value=${role}><br>
        <input type="submit" name="修改" value="修改"/>
        <input type="reset" name="重置"/>
    </form></td></tr>
    <tr><td><a href="/index">首页</a></td> </tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>
</body>
</html>
