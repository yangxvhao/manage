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
    <title>信贷审批管理系统-申请单展示</title>
</head>
<body>

<table>
    <tr><td align="center"><span >欢迎进入审批管理系统</span></td></tr>
    <tr><td><span>${upload_success}</span></td></tr>
    <tr><td><span style="color:red">${upload_error}</span><br/>
       <a href="/apply/show">申请单展示</a>
    </td></tr>
    <tr><td></td>
        <td align = "center">申请单号</td>
        <td align = "center">申请人姓名</td>
        <td align = "center">申请人年龄</td>
        <td align = "center">申请人证件号</td>
        <td align = "center">申请类型</td>
        <td align = "center">担保人</td>
        <td align = "center">办单员</td>
        <td align = "center">申请日期</td>
        <td align = "center">申请金额</td>
    </tr>
    <c:forEach items="${list}" var="apply" varStatus="vs">
    <tr><td><s:property value="#vs.index+1"/></td>
        <td align = "center">${apply.id}</td>
        <td align = "center">${apply.applyname}</td>
        <td align = "center">${apply.applyage}</td>
        <td align = "center">${apply.applyidcard}</td>
        <td align = "center">${apply.applytype}</td>
        <td align = "center">${apply.guarantorname}</td>
        <td align = "center">${apply.applymember}</td>
        <td align = "center">${apply.applydate}</td>
        <td align = "center">${apply.applymoney}</td>
    </tr>
    </c:forEach> </tr>
    <tr><td></td></tr>
    <tr></tr>
    <tr></tr>
</table>

</body>
</html>
</body>
</html>
