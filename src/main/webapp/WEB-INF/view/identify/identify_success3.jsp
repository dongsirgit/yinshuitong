<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getContextPath();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>认证成功</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
    <div class="main">
	<div class="renzheng_submit">
    	<h2 class="shimingrzh2">实 名 认 证</h2>
        <p class="renzheng_submit_p">您的证件已提交，管理员审核通过后才可以申请贷款。审核时间为3~5个工作日，请您耐心等待。</p>
        <p class="renzheng_submit_return"><a href="<%=basePath %>/" target="_top">返回网站首页</a></p>
    </div>
</div>
    <%@include file="../base/footer.html" %>
</body>
</html>