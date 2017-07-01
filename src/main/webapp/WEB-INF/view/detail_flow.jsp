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
    <title>信贷审批管理系统-流程详情</title>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<iframe name="formsubmit" style="display:none;">
</iframe>
<table border="1" width="80%" align="center">
    <tr><td><span style="color: red">${status}</span>
        <span style="color: red">${error}</span></td>
        <td align = "center">流程id</td>
        <td align = "center">申请单号</td>
        <td align = "center">流程名称</td>
        <td align = "center">流程权重</td>
        <td align = "center">流程结果</td>
        <td align = "center">流程价格</td>
        <td align="center">操作</td>
    </tr>
    <c:forEach items="${list}" var="flow" varStatus="vs">
        <form action="/flow/update" method="post" target="formsubmit">
    <tr>
        <td><s:property value="#vs.index+1"/></td>
        <td align = "center">${flow.id}</td>
        <td align = "center">${flow.applyid}</td>
        <td align = "center">${flow.flowname}</td>
        <%--<td align = "center">${flow.flowscale}</td>--%>
        <td align="center">
            <select name="flowscale">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </td>
        <td align = "center">${flow.flowresult}</td>
        <td align = "center">${flow.flowprice}</td>
        <c:set var="applyid" value="${flow.applyid}" scope="page"></c:set>
        <c:set var="id" value="${flow.id}" scope="page"></c:set>
        <%--<input hidden="hidden" type="text" value= name="applyid"/>--%>
        <td align="center"><input hidden="hidden" type="text" value=${applyid} name="applyid"/>
            <input hidden="hidden" type="text" value=${id} name="id"/>
        <input type="submit" value="提交" /></td>
    </tr>
        </form>
    </c:forEach>
    <c:if test="${status==null&&error==null}">
    <tr><td colspan="8" align="center" style="padding-top: 5px">
        <form action="/flow/result" method="post" style="padding-top: 10px">
            <input hidden="hidden" type="text" value=${applyid} name="applyid"/>

            <%--<input type="radio" name="result" value="通过"/>通过--%>
            <%--<input type="radio" name="result" value="拒绝"/>拒绝&nbsp;&nbsp;--%>
            <span>已确认三方权重，</span>
            <input type="submit" name="提交审批"/>
        </form>
    </td></tr>
    </c:if>
    <tr><td colspan="8" align="right" style="padding-right: 20%"><a href="javascript:history.go(-1)">返回上一页</a></td></tr>
</table>
</body>
</html>
</body>
</html>
