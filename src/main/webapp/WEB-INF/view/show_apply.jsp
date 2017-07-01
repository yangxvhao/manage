<%@ page import="com.credit.model.User" %>
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
    <title>信贷审批管理系统-申请单展示</title>

    <script type="text/javascript" src="/js/page.js">
    </script>
    <script type="text/javascript">
        function checkStatus(status) {
            if(status=="已提交"){
                alert("初审还没进行！")
                return false;
            }if(status=="终审中"){
                alert("二级审批已结束！")
                return false;
            }

            return true;
        }
        function checkRole(role) {
            if(role=="经理"||role==="主管"){
                alert("初审由办单员审核！");
                return false;
            }
            return true;
        }
        function checkRole1(role) {
            if(role!="主管"){
                alert("二级审核由主管审批！");
                return false;
            }
            return true;
        }
        function checkRole2(role,status) {
            if(role!="经理"){
                alert("终审由经理审批！");
                return false;
            }
            if(status=="审批结束"){
                alert("审批已经结束！");
                return false;
            }
            if(status!="终审中"){
                alert("还未到终审！");
                return false;
            }
            return true;
        }
    </script>
    <%
        User user=(User) session.getAttribute("User");
    %>
</head>
<body bgcolor="#3af7ff" onload="goPage(1,10)">
<div style="width: auto;margin-bottom: 10px;"><img src="/image/yewu.jpg" width="100%"></div>
<hr/>
<table border="0" width="98%" align="center" rules="rows">
    <tr><td align="center" colspan="15"><h1>欢迎<%=session.getAttribute("name").toString()%>进入审批管理系统</h1></td></tr>
    <tr>
        <td colspan="15">
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
        </td>
    </tr>
    <tr>
        <td colspan="13" align="left">
            <span>${upload_success}</span>
            <span style="color:red;font-size: 10px">${upload_error}</span>
            <span style="color:red;font-size: 10px">${show_error}</span>
            <a href="/apply/show" style="padding-left: 10px">刷新</a>
            <form action="/apply/query" method="post">
            <table border="0"><tr>
                <td>
                    <span>类型：</span><br>
                    <select id="applyType" name="applyType">
                        <option value="">全部</option>
                        <option value="商品">商品</option>
                        <option value="分期">分期</option>
                        <option value="现金">现金</option>
                    </select>
                </td>
                <td>
                    <span>开始时间：</span><br>
                    <input type="date" name="applyTimeStart"/>
                </td>
                <td>
                    <span>结束时间：</span><br>
                    <input type="date" name="applyTimeEnd"/>
                </td>
                <td>
                    <span>申请金额：</span><br>
                    <input type="text" size="8" name="applyMoneyMin"/>-
                    <input type="text" size="8" name="applyMoneyMax"/>
                </td>
                <td>
                    <span>状态：</span><br>
                    <select id="status" name="status">
                        <option value="">全部</option>
                        <option value="已提交">已提交</option>
                        <option value="一级审批中">一级审批中</option>
                        <option value="一级审批中">二级审批中</option>
                        <option value="审批结束">审批结束</option>
                    </select>
                </td>
                <td>
                    <span>结果：</span><br>
                    <select id="result" name="result">
                        <option value="">全部</option>
                        <option value="未审批">未审批</option>
                        <option value="初过二过终过">初过二过终过</option>
                        <option value="初过二过终拒">初过二过终拒</option>
                        <option value="初拒二过终过">初拒二过终过</option>
                        <option value="初拒二过终拒">初拒二过终拒</option>
                        <option value="初过二拒终过">初过二拒终过</option>
                        <option value="初过二拒终拒">初过二拒终拒</option>
                        <option value="初拒二拒终拒">初拒二拒终拒</option>
                        <option value="初拒二拒终过">初拒二拒终过</option>
                        <option value="初过二无终过">初过二无终过</option>
                        <option value="初过二无终拒">初过二无终拒</option>
                        <option value="初拒二无终过">初拒二无终过</option>
                        <option value="初拒二无终拒">初拒二无终拒</option>
                    </select>
                </td>
                <td align="center">
                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询"/>
                </td>
            </tr>
            </table>
        </form></td>
        <td align="center">
            <a href="/apply/delete" style="text-decoration: dashed">清空</a>
            </td>
        <td align="center" colspan="3">
            <span style="color: red">${delete_status}</span>
        </td>
    </tr>
    <tr>
        <td align = "center" width="101px">申请单号</td>
        <td align = "center" width="101px">申请人姓名</td>
        <td align = "center" width="101px">申请人年龄</td>
        <td align = "center"  width="180px">申请人证件号</td>
        <td align = "center" width="101px">申请类型</td>
        <td align = "center" width="90px">担保人</td>
        <td align = "center" width="90px">办单员</td>
        <td align = "center" width="101px">申请日期</td>
        <td align = "center" width="101px">申请金额</td>
        <td align = "center" width="101px">审批金额</td>
        <td align="left" width="101px">状态</td>
        <td align="left" width="120px">审批结果</td>
        <td align="left" width="90px">初审</td>
        <td align="left" width="90px">二审</td>
        <td align="left" width="90px">终审</td>
    </tr>
    <tr>
        <td colspan="15">
            <table border="0" rules="rows"  cellspacing="0" cellpadding="0" id="idPage" width="100%">
                <c:forEach items="${list}" var="apply" varStatus="vs">
                <tr><s:property value="#vs.index+1"/>
                    <td align = "center" width="101px">${apply.id}</td>
                    <td align = "center" width="101px">${apply.applyname}</td>
                    <td align = "center" width="101px">${apply.applyage}</td>
                    <td align = "center" width="180px">${apply.applyidcard}</td>
                    <td align = "center" width="101px">${apply.applytype}</td>
                    <td align = "center" width="91px">${apply.guarantorname}</td>
                    <td align = "center" width="91px">${apply.applymember}</td>
                    <td align = "center" width="101px">${apply.applydate}</td>
                    <td align = "center" width="101px">${apply.applymoney}</td>
                    <td align = "center" width="101px">${apply.checkmoney}</td>
                    <td align="center" width="101px">${apply.status}</td>
                    <td align="center" width="120px">${apply.result}</td>
                    <td align="center" width="120px">
                        <a href="/apply/check/${apply.id}/通过" style="text-decoration: dashed" onclick="return checkRole('<%=user.getRole()%>')">通过</a>
                        <a href="/apply/check/${apply.id}/拒绝" style="text-decoration: dashed" onclick="return checkRole('<%=user.getRole()%>')">拒绝</a>
                    </td>
                    <td align="center" width="101px">
                        <a href="/flow/query/${apply.id}" onclick="return checkStatus('${apply.status}')">查看</a>
                    </td>
                    <td align="center" width="120px">
                        <a href="/apply/check2/${apply.id}/通过" style="text-decoration: dashed" onclick="return checkRole2('<%=user.getRole()%>','${apply.status}')">通过</a>
                        <a href="/apply/check2/${apply.id}/拒绝" style="text-decoration: dashed" onclick="return checkRole2('<%=user.getRole()%>','${apply.status}')">拒绝</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
         </td>
    </tr>
    <tr>
        <td colspan="10">
            <table width="60%" align="right">
                <tr><td><div id="barcon" name="barcon"></div></td></tr>
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

