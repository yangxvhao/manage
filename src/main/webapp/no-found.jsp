<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
  String path = request.getContextPath();
  String resPath = request.getContextPath()+"/resources/";
  request.getRequestURI();
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
	                    	error <span>404</span>
					    </div>
	                	<h1 class="da-error-heading">很抱歉，您要访问的页面不存在！<%=request.getRequestURI() %></h1>
	                    <p><a href="javascript:;">温馨提示：</a></p>
						<div>
						<p>1、请检查您访问的网址是否正确</p>
						<p>2、如果您不能确认访问的网址，请浏览百度更多页面查看更多网址。</p>
						<p>3、回到顶部重新发起搜索。</p>
						<p>4、如有任何意见或建议，请及时反馈给我们。</p>
					   </div>
					   <p style=" text-align:center;"><a class="on" href="<%=path%>/index.jsp">返回首页</a><a href="javascript:self.close();">关闭本页</a></p>
	                </div>
			</div>
     	</div>
     </div>
     <!--foot-->
	</body>
</html>
