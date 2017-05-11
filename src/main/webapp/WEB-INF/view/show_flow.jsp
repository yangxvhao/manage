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
    <title>信贷审批管理系统-流程单展示</title>
</head>
<body>
<table border="1" width="80%">
    <tr><td align="center" colspan="10"><h1>欢迎${name}进入审批管理系统</h1></td></tr>
    <tr><td colspan="10"><span>${upload_success}</span><span style="color:red">${upload_error}</span>
        <span style="color:red">${show_error}</span><br/>
       <a href="/flow/show/${name}/${role}">流程单展示</a>
    </td></tr>
    <tr><td>查询：</td>
        <td colspan="6"><form action="/flow/query/${name}/${role}" method="post">
            <table><tr>
                <td>
                    <span>结果：</span><select id="flowResult" name="flowResult">
                        <option value="">全部</option>
                        <option value="通过">通过</option>
                        <option value="未通过">未通过</option>
                    </select>
                </td>
                <td>
                    <span>申请单号：</span><input type="text" name="applyId"/>
                </td>
                <td><span>排序：</span><input type="radio" value="1" name="isSort"/>是
                    <input type="radio" value="0" name="isSort" checked="checked"/>否</td>
                <td>
                    <input type="submit" value="查询"/>
                </td>
            </tr></table>
        </form></td>
    </tr>
    <tr><td>.</td>
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
</table>
>><a href="/root/${name}/${role}">主页</a></td>&nbsp;>>
<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>
</body>
</html>
