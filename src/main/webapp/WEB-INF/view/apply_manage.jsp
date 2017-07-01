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
    <title>信贷审批管理系统-申请单管理</title>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<table align="center" border="0">
    <tr><td><h1>欢迎<%=session.getAttribute("name").toString()%>进入审批管理系统</h1></td></tr>
    <tr><td>
        <table>
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
        </table>
    </td></tr>
    <tr><td><span>${change_succes}</span></td></tr>
    <tr><td style="padding-left: 20px"><span style="color:red">${error}</span><br/>
        <span>请选择要导入的申请单（只支持.xls文件）</span><br/><br>
        <form action="/apply/upload" method="post" enctype="multipart/form-data">
            请选择文件:<input type="file" name="excelFile"><br/><br>
            <input type="submit" value="提交" align="center">
        </form>
    </td></tr>
    <tr><td style="padding-left: 20px">已导入，<a href="/apply/show">申请单展示</a></td></tr>
</table>

<div style="padding-left: 20%;padding-top: 50px">
    >><a href="/root">主页</a>&nbsp;>>
    <a href="javascript:history.go(-1)">返回上一页</a>
</div>
</body>
</html>
</body>
</html>
