<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath();%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>找回密码</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/login/findpassword.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div class="forget">
	<div class="forget_con find">
    	<h3>找回密码</h3>
        <div class="find_con">
        	<p>密码已发送至您的邮箱，请查收并妥善保管！</p>
        	<a href="<%=basePath %>/user/forwardLogin" target="_top">登录网站</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${mail}" target="_blank">登陆邮箱</a>
        </div>
    </div>
</div>
<!--底部-->
<%@include file="../base/footer.html" %>
</body>
</html>
