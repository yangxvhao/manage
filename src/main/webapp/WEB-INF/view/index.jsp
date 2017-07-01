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
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>信贷审批管理系统-注册</title>
    <script type="text/javascript">
        // 验证输入不为空的脚本代码
        function checkForm(form) {
            var regx=/[\u0000-\u00FF]/;
            if(form.name.value == "") {
                alert("用户名不能为空!");
                //表示将输入焦点移至对象上.
                form.name.focus();
                return false;
            }
            if(form.name.value.length>10){
                alert("用户名长度不能超过十位")
                form.name.focus();
                return false
            }
            if(form.role.value=="办单员"&&regx.test(form.name.value)){
                alert("用户名为中文（办单员姓名）！")
                form.name.focus();
                return false;
            }
            if(form.password.value == "") {
                alert("密码不能为空!");
                form.password.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/index.jpg" width="100%"></div>
<hr/>
<table align="center" border="0" cellpadding="0" cellspacing="0">
    <%--<tr><td></td></tr>--%>
    <tr><td align="center"><h1>欢迎进入审批管理系统</h1></td></tr>
        <%--<tr><td ><span style="font-size: 25px;padding-left: 30%">请注册：</span></td></tr>--%>
    <tr>
        <td align="center">
        <form action="/index" method="post" onsubmit="return checkForm(this)" >
            <table border="0" cellspacing="0">
                <tr>
                    <td><span style="color:red;font-size: 10px">${error}</span></td>
                </tr>
                <tr>
                    <td>用户名：<input type="text" name="name" path="name"/></td>
                </tr>
                <tr>
                    <td>密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" path="password"/></td>
                </tr>
                <tr>
                    <td>角&nbsp;&nbsp;&nbsp;色：<input type="radio" name="role" value="经理">经理
                        <input type="radio" name="role" value="主管" checked>主管
                    <input type="radio" name="role" value="办单员">办单员</td>
                </tr>
                <tr><td align="center">
                    <input type="submit" name="注册" value="注册"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="reset" name="重置"/></td>
                </tr>


            </table>
        </form>
        </td>
    </tr>
    <tr><td align="center">已注册：&nbsp;&nbsp;<a href="/login">登录</a></td></tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>