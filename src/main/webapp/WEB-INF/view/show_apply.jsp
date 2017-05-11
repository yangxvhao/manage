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
<table border="1" width="80%" align="center">
    <tr><td align="center" colspan="11"><h1>欢迎${name}进入审批管理系统</h1></td></tr>
    <tr><td colspan="11"><span>${upload_success}</span><span style="color:red">${upload_error}</span>
        <span style="color:red">${show_error}</span><br/>
       <a href="/apply/show/${name}/${role}">申请单展示</a>
    </td></tr>
    <tr><td>查询：</td>
        <td colspan="10"><form action="/apply/query/${name}/${role}" method="post">
            <table><tr>
                <td>
                    <span>类型：</span><select id="applyType" name="applyType">
                        <option value="">全部</option>
                        <option value="商品">商品</option>
                        <option value="分期">分期</option>
                        <option value="现金">现金</option>
                    </select>
                </td>
                <td>
                    <span>开始时间：</span><input type="date" name="applyTimeStart"/>
                </td>
                <td>
                    <span>结束时间：</span><input type="date" name="applyTimeEnd"/>
                </td>
                <td>
                    <span>申请金额：</span><input type="text" size="10" name="applyMoneyMin"/>-
                    <input type="text" size="10" name="applyMoneyMax"/>
                </td>
                <td>
                    <input type="submit" value="查询"/>
                </td>
            </tr></table>
        </form></td>
    </tr>
    <tr><td>.</td>
        <td align = "center">申请单号</td>
        <td align = "center">申请人姓名</td>
        <td align = "center">申请人年龄</td>
        <td align = "center">申请人证件号</td>
        <td align = "center">申请类型</td>
        <td align = "center">担保人</td>
        <td align = "center">办单员</td>
        <td align = "center">申请日期</td>
        <td align = "center">申请金额</td>
        <td align="center">流程查看</td>
    </tr>
    <c:forEach items="${list}" var="apply" varStatus="vs">
    <tr>
        <td><s:property value="#vs.index+1"/></td>
        <td align = "center">${apply.id}</td>
        <td align = "center">${apply.applyname}</td>
        <td align = "center">${apply.applyage}</td>
        <td align = "center">${apply.applyidcard}</td>
        <td align = "center">${apply.applytype}</td>
        <td align = "center">${apply.guarantorname}</td>
        <td align = "center">${apply.applymember}</td>
        <td align = "center">${apply.applydate}</td>
        <td align = "center">${apply.applymoney}</td>
        <td align="center"><a href="/flow/query/${role}/${apply.id}">查看</a></td>
    </tr>
    </c:forEach>
</table>
>><a href="/root/${name}/${role}">主页</a></td>&nbsp;>>
<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>
</body>
</html>
