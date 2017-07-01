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
    <title>信贷审批管理系统-找回密码</title>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/index.jpg" width="100%"></div>
<hr/>
<table align="center" border="0" cellpadding="0" cellspacing="0">
    <tr><td align="center"><h1>欢迎进入审批管理系统</h1></td></tr>
    <tr><td align="center" style="color: red;">${forget}</td></tr>
    <tr><td align="center"><form action="/forgetPwd" method="post" >
        用户名：<input  type="text" name="name" value=""><br/>
        新密码：<input type="password" name="password" /><br/><br/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" name="提交" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" name="重置"/>
    </form></td></tr>
    <tr>
        <td align="center"><a href="/login">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:history.go(-1)">返回上一页</a></td>
    </tr>
</table>

</body>
</html>
