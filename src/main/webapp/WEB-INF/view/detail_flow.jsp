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
    <title>信贷审批管理系统-流程详情</title>
</head>
<body>
<table border="1" width="80%" align="center">
    <tr><td><span style="color: red">${status}</span>
        <span style="color: red">${error}</span></td>
        <td align = "center">流程id</td>
        <td align = "center">申请单号</td>
        <td align = "center">流程名称</td>
        <td align = "center">流程权重</td>
        <td align = "center">流程结果</td>
        <td align = "center">流程价格</td>
    </tr>
    <c:forEach items="${list}" var="flow" varStatus="vs">
    <tr>
        <td><s:property value="#vs.index+1"/></td>
        <td align = "center">${flow.id}</td>
        <td align = "center">${flow.applyid}</td>
        <td align = "center">${flow.flowname}</td>
        <td align = "center">${flow.flowscale}</td>
        <td align = "center">${flow.flowresult}</td>
        <td align = "center">${flow.flowprice}</td>
    </tr>
    </c:forEach>
    <tr><a href="javascript:history.go(-1)">返回上一页</a></tr>
</table>
</body>
</html>
</body>
</html>
