<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%
	String basePath = request.getContextPath();
%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>注册成功</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div class="main registsuc">
	<div class="upload">
    	<h3>恭喜你，注册成功！</h3>
        <div class="uploadbtn clearfix">
        	<a href="<%=basePath %>/users/init/userInfo?showPage=1" class="download perfect">完善账户信息</a>
        	<a href="<%=basePath %>/" class="think return">暂不完善，返回首页</a>
        </div>
        <p class="cue">温馨提示：完善账户信息后才可以申请贷款</p>
    </div>
</div>
<%@include file="../base/footer.html" %>
</body>
</html>