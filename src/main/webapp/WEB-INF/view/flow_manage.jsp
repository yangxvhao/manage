<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>信贷审批管理系统-流程管理</title>
</head>
<body>
<table>
    <tr><td><span>欢迎${name}进入审批管理系统</span></td></tr>
    <tr><td><span>${change_succes}</span></td></tr>
    <tr><td><span style="color:red">${manage_error}</span><br/>
        <span>请选择要导入的流程单（只支持.xls文件）</span><br/>
        <form action="/flow/upload/${name}/${role}" method="post" enctype="multipart/form-data">
            请选择文件:<input type="file" name="excelFile"><br/>
            <input type="submit" value="提交">
        </form>
    </td></tr>
    <tr><td>已导入，<a href="/flow/show/${name}/${role}">流程展示</a></td></tr>

</table>
>><a href="/root/${name}/${role}">主页</a>&nbsp;>>
<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>
</body>
</html>
