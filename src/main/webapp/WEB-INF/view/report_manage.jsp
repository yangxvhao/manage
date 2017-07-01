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
    <title>信贷审批管理系统-报表管理</title>
    <script type="text/javascript">
        function checkEmpty() {
            var names = document.getElementsByName("reportField");
            var flag = false ;//标记判断是否选中一个
            for(var i=0;i<names.length;i++){
                if(names[i].checked){
                    flag = true ;
                    break ;
                }
            }
            if(!flag){
                alert("报表需要字段请最少选择一项！");
                return false ;
            }
            if(reportForm.fileName.value==""){
                alert("文件名不能为空！");
                reportForm.fileName.focus();
                return false;
            }
            return true;
        }
        function preview() {
            var names = document.getElementsByName("reportField");
            var flag = false ;//标记判断是否选中一个
            for(var i=0;i<names.length;i++){
                if(names[i].checked){
                    flag = true ;
                    break ;
                }
            }
            if(!flag){
                alert("报表需要字段请最少选择一项！");
                return false ;
            }
            document.reportForm.action="/report/total/true";
            document.reportForm.submit();
            
        }
        function make() {
            if(checkEmpty()){
                document.reportForm.action="/report/total/false";
                document.reportForm.submit();
            }
            return false;
        }
    </script>
</head>
<body bgcolor="#3af7ff">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<table align="center" border="0">
    <tr><td align="center"><h1>欢迎<%=session.getAttribute("name").toString()%>进入审批管理系统</h1></td></tr>
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
    <tr><td style="padding-left: 10px;padding-top: 20px"><span>计算流程总价：</span><a href="/report/total">提交</a>
        <span style="font-size: 10px;color: red">(注：需先计算总价才能生成报表)</span> &nbsp;&nbsp;
        <br><span>${totalCost_success}</span>
    </td></tr>
    <tr>
        <td style="padding-left: 10px;padding-top: 20px">
            <span>业务统计：<a href="/report/count">查看</a></span>
            <%--<span>办单员：</span><br>--%>
            <%--<select name="applyName">--%>
                <%--<c:forEach items="${nameList}" var="e" varStatus="vs" >--%>
                    <%--<s:property value="#vs.index+1"/>--%>
                <%--<option  value="${e}"><c:out value="${e}"/></option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
        </td>
    </tr>
    <tr><td style="padding-left: 15%"><span style="color:red">${manage_error}</span><br/>
        </td></tr>
    <tr><td ><span style="padding-left: 8px">请选择报表需要的字段：</span><hr style="border: 0">
        <form name="reportForm" method="post">
            <input type="checkbox" name="reportField" value="applyId"/>申请单号&nbsp;
            <input type="checkbox" name="reportField" value="applyName"/>申请人&nbsp;
            <input type="checkbox" name="reportField" value="applyMember"/>办单员&nbsp;
            <input type="checkbox" name="reportField" value="applyDate"/>审批时间&nbsp;
            <input type="checkbox" name="reportField" value="status"/>审批状态&nbsp;
            <input type="checkbox" name="reportField" value="result"/>审批结果&nbsp;
            <input type="checkbox" name="reportField" value="flowCost"/>审批成本&nbsp;

            <br/><br/><span style="padding-left: 8px">请输入报表文件名称：</span>
            <input type="text" name="fileName" />
            <input type="submit" value="生成报表" onclick="return make()">&nbsp;&nbsp;
            <input type="submit" value="报表预览" onclick="return preview()">
        </form>
    </td></tr>
    <tr><td style="padding-left: 15%">
        <span style="color: red">${report_success}</span><span>${report_error}</span>
        <a href="/report/download/${fileName}">下载</a></td></tr>
</table>
<div style="padding-left: 10%;padding-top: 50px">
    >><a href="/root">主页</a>&nbsp;>>
    <a href="javascript:history.go(-1)">返回上一页</a>
</div>
</body>
</html>
</body>
</html>
