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
    <title>信贷审批管理系统-报表管理</title>
</head>
<body>
<table>
    <tr><td><span>欢迎${name}进入审批管理系统</span></td></tr>
    <tr><td><span>计算流程总价：</span><a href="/report/total">提交</a>&nbsp;&nbsp;<span>${totalCost_success}</span></td></tr>
    <tr><td><span>请选择报表需要的字段：</span></td></tr>
    <tr><td><span style="color:red">${manage_error}</span><br/>
        <form action="/report/total" method="post">
            <input type="checkbox" name="reportField" value="applyId"/>申请单号&nbsp;
            <input type="checkbox" name="reportField" value="applyName"/>申请人&nbsp;
            <input type="checkbox" name="reportField" value="applyMember"/>办单员&nbsp;
            <input type="checkbox" name="reportField" value="applyDate"/>审批时间&nbsp;
            <input type="checkbox" name="reportField" value="flowCost"/>审批成本&nbsp;
            <br/><span>请输入报表文件名称：</span>
            <input type="text" name="fileName" />
            <input type="submit" value="生成报表">
        </form>
    </td></tr>
    <tr><td><span>${report_success}</span><a href="/report/download/${fileName}">下载</a></td></tr>
</table>
>><a href="/root/${name}/${role}">主页</a>&nbsp;>>
<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>
</body>
</html>
