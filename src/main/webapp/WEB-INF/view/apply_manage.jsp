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
    <title>信贷审批管理系统-申请单管理</title>
</head>
<body>

<table>
    <tr><td><span>欢迎进入审批管理系统</span></td></tr>
    <tr><td><span>${change_succes}</span></td></tr>
    <tr><td><span style="color:red">${error}</span><br/>
        <span>请选择要导入的申请单（只支持.xls文件）</span><br/>
        <form action="/apply/upload" method="post" enctype="multipart/form-data">
            请选择文件:<input type="file" name="excelFile"><br/>
            <input type="submit" value="提交">
        </form>
    </td></tr>
    <tr><td>已导入，<a href="/apply/show">申请单展示</a></td></tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>
</body>
</html>
