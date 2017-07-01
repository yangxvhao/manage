<%@ page import="com.credit.model.SubList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <title>信贷审批管理系统-成本展示</title>
    <script type="text/css">
        *{margin:0;padding:0;}
        #flow {color: red;border: solid;text-align: center;}
    </script>
    <script type="text/javascript" src="/js/page.js">
    </script>
    <script type="text/javascript">
        function checkEmpty() {
            if(reportForm.fileName.value==""){
                alert("文件名不能为空！");
                reportForm.fileName.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body onLoad="goPage(1,10);" bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<table border="0" cellspacing="0" cellpadding="0" width="80%" align="center" rules="rows">
    <tr><td align="center" colspan="10"><h1>欢迎${name}进入审批管理系统</h1></td></tr>
    <tr>
        <td colspan="10">
            <table>
                <tr>
                    <td>>><a href="/changePwd">修改密码</a></td>
                    <td>>><a href="/apply/manage">申请单管理</a></td>
                    <c:if test="${sessionScope.User.role!='办单员'}">
                        <td >>><a href="/flow/manage">流程管理</a></td>
                    </c:if>
                    <c:if test="${sessionScope.User.role!='办单员'}">
                        <td>
                            >><a href="/report/manage">报表管理</a>
                        </td>
                    </c:if>
                    <td>
                        <span style="color:red;font-size:10px">${report_error}</span>
                    </td>
                    <td>
                        >><a href="/logout">退出</a></td>
                </tr>
            </table>
        </td>
    </tr>
    <%--<c:forEach items="${fields}" var="field">--%>
    <tr>
        <%--<s:property value="#vs.index+1"/>--%>
        <td align = "center" width="350px">${fields[0]}</td>
        <td align = "center" width="350px">${fields[1]}</td>
        <td align = "center" width="350px">${fields[2]}</td>
        <td align = "center" width="350px">${fields[3]}</td>
        <td align = "center" width="350px">${fields[4]}</td>
        <td align = "center" width="350px">${fields[5]}</td>
        <td align = "center" width="350px">${fields[6]}</td>
    </tr>
    <%--</c:forEach>--%>
    <tr>
        <td colspan="7"  align="top">
            <table  border="0" rules="rows" cellspacing="0" id="idPage" width="100%" >
                <c:forEach items="${list}" var="report" varStatus="vs">
                <tr >
                    <s:property value="#vs.index+1"/>
                    <td align = "center" width="350px">${report[0]}</td>
                    <td align = "center"  width="350px">${report[1]}</td>
                    <td align = "center" width="350px">${report[2]}</td>
                    <td align = "center" width="350px">${report[3]}</td>
                    <td align = "center" width="350px">${report[4]}</td>
                    <td align = "center" width="350px">${report[5]}</td>
                    <td align = "center" width="350px">${report[6]}</td>
                </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="6">
            <table width="60%" align="right">
                <tr><td><div id="barcon" name="barcon"></div></td></tr>
            </table>
        </td>
    </tr>
    <%--<tr>--%>
        <%--<td colspan="6">--%>
            <%--<%--%>
                <%--request.setAttribute("list","teestststs");--%>
                <%--request.setAttribute("fields","fefefefee");--%>
            <%--%>--%>
            <%--&lt;%&ndash;<c:set var="field" scope="page" value="${list}"></c:set>&ndash;%&gt;--%>
            <%--<form name="reportForm" action="/report/create" method="post" onsubmit="return checkEmpty()">--%>
                <%--<input type="text" value="<%=request.getAttribute("list")%>" hidden name="list"/>--%>
                <%--<input type="text" value="${strings}" hidden name="fields"/>--%>
                <%--<br/><br/><span style="padding-left: 8px">请输入报表文件名称：</span>--%>
                <%--<input type="text" name="fileName" />--%>
                <%--<input type="submit" value="生成报表" onclick="return make()">--%>
            <%--</form>--%>
        <%--</td>--%>
    <%--</tr>--%>
   <%-- <tr><td style="padding-left: 15%" colspan="6">
        <span style="color: red">${report_success}</span><span>${report_error}</span>
        <a href="/report/download/${fileName}">下载</a></td></tr>--%>
</table>
<div style="padding-left: 10%;padding-top: 50px">
    >><a href="/root">主页</a>&nbsp;>>
    <a href="javascript:history.go(-1)">返回上一页</a>
</div>
</body>
</html>
