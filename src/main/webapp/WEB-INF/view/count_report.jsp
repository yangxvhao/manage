<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.credit.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/6
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信贷审批管理系统-登录</title>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<table align="center" width="80%" rules="rows">
    <tr><td align="center" colspan="5"><h1>欢迎<%=session.getAttribute("name").toString()%>进入审批管理系统</h1></td></tr>
    <tr><td align="center" colspan="5"><table>
        <tr>
            <td>>><a href="/changePwd">修改密码</a></td>
            <td>>><a href="/apply/manage">申请单管理</a></td>
            <%--${sessionScope.User.role}JSTL语句判断是否显示标签--%>
            <c:if test="${sessionScope.User.role!='办单员'}">
                <td >>><a href="/flow/manage">流程管理</a></td>
            </c:if>
            <c:if test="${sessionScope.User.role!='办单员'}">
                <td>
                    >><a href="/report/manage">报表管理</a>
                </td>
            </c:if>
            <td>
                >><a href="/logout">退出</a></td>
        </tr>
    </table></td>
    </tr>
    <tr align="center">
        <td align = "center" width="180px">办单员</td>
        <td align = "center" width="180px">总单数</td>
        <td align = "center" width="180px">审批结束单数</td>
        <td align = "center" width="180px">通过单数</td>
        <td align = "center" width="180px">通过率</td>
    </tr>
    <tr align="center">
        <td colspan="6"  align="top">
            <table  border="0" rules="rows"  cellspacing="0" id="idPage" width="100%" >
                <c:forEach items="${nameList}" var="e" varStatus="vs">
                    <tr >
                        <s:property value="#vs.index+1"/>
                        <c:if test="${e[0]!=''}">
                        <td align = "center" width="173px">${e[0]}</td>
                        <td align = "center"  width="173px">${e[1]}</td>
                        <td align = "center"  width="173px">${e[2]}</td>
                        <td align = "center" width="173px">${e[3]}</td>
                        <td align = "center" width="173px">${e[4]}</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
<div style="padding-left: 10%;padding-top: 50px">
    >><a href="/root">主页</a>&nbsp;>>
    <a href="javascript:history.go(-1)">返回上一页</a>
</div>
</body>
</html>
</body>
</html>
