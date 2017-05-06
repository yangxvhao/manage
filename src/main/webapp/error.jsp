<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
  String path = request.getContextPath();
  String resPath = request.getContextPath()+"/resources/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>诚安聚立用户中心-错误提示</title>
	    <link rel="stylesheet" type="text/css" href="<%=resPath%>css/public.css"/>
	    <link rel="stylesheet" type="text/css" href="<%=resPath%>css/font-awesome.min.css" />
	    <link rel="stylesheet" type="text/css" href="<%=resPath%>css/style.css" />
	    <script type="text/javascript" src="<%=resPath %>js/jquery-1.11.3.min.js" ></script>
	    <script type="text/javascript" src="<%=resPath %>js/common.js" ></script>
	</head>
	<body>
     <!--顶部-->
     <div class="section">
     	<div class="head-content" style="background-color:#ffffff">
     		<div class="error">
				<div class="da-error-wrapper">
	                   	<div class="da-error-pin"></div>
	                    <div class="da-error-code  firsterror">
	                    	warning <span>Error</span>
					    </div>
					    <div class="loginTishi"><i class="fa fa-bullhorn fa-lg"></i>会话ID:<%=session.getId() %></div>
						<div class="loginTishi"><i class="fa fa-bullhorn fa-lg"></i>权限验证：<%=session.getAttribute("flag") %></div>
	                	<h1 class="da-error-heading">很抱歉，出错了！</h1>
	                    <p><a href="javascript:;">温馨提示：</a></p>
						<div>
						<p>${error}</p>
					   </div>
					   <p style=" text-align:center;"><a class="on" href="<%=path%>/index.jsp">返回首页</a><a href="javascript:history.go(-1)">返回上一页</a></p>
	                </div>
			</div>
     	</div>
     </div>
     <!--foot-->
	</body>
</html>
