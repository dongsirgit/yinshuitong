<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%
	String basePath = request.getContextPath();
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传发票</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>

<!-- <iframe src="head.html" width="100%" height="74px" frameborder="0" scrolling="no"></iframe> -->
<!-- 企业用户 -->
<c:if test="${typeUser=='0'}">
<div class="main">
	<div class="upload">
    	<h3>贷款申请已提交！请等待工作人员的审核</h3>
        <p><span>为了使您的贷款申请更容易被银行快速审批通过，您可以通过客户端上传您的增值税销项发票，银行在审批贷款时将参考这些数据。</span></p>
        <div class="uploadbtn clearfix">
        	<a href="<%=basePath %>/users/init/userInfo" class="think">考虑一下</a>
        	<a href="javascript:;" class="download">下载客户端并上传发票</a>
        </div>
    </div>
</div>
</c:if>

<!-- 个人用户 -->
<c:if test="${typeUser=='1'}">
<div class="main">
	<div class="upload personor">
    	<h3>贷款申请已提交！请等待工作人员的审核</h3>
        <div class="uploadbtn clearfix personorderbtn">
        	<a href="<%=basePath %>/users/init/userInfo" class="think">查看我的贷款</a>
        	<a href="<%=basePath %>/" class="download">返回网站首页</a>
        </div>
    </div>
</div>
</c:if>
<%@include file="../base/footer.html" %>
</body>
</html>