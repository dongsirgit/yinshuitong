<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交成功</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div class="main">
	<div class="upload personor">
    	<h3>您的贷款申请已经提交，工作人员会尽快与您取得联系，请保持手机通畅。</h3>
        <div class="uploadbtn clearfix personorderbtn">
        	<a href="<%=basePath %>/users/init/userInfo" class="think">查看我的贷款</a>
        	<a href="<%=basePath %>/" class="download">返回网站首页</a>
        </div>
    </div>
</div>
<%@include file="../base/footer.html" %>
</body>
</html>