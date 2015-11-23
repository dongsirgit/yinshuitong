<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.Puser,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	Puser user = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
	Long id = 0l;
	try{
		id = Long.valueOf((null == request.getParameter("id") ||"".equals(request.getParameter("id")) ? "0":request.getParameter("id")));
	}catch(Exception e){
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人消费贷3</title>
	<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
</head>
<body>
	<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>

	<div class="steps">
	    <ul class="clearfix stepsnum3">
	    	<li class="colorred">填写基本信息</li>
	    	<li class="colorred">上传证件资料</li>
	    	<li class="colorred">提交贷款申请</li>
	    </ul>
	</div>
	
	<div class="main">
		<div class="upload personor">
	    	<h3>贷款申请已提交！请等待工作人员的审核</h3>
	        <div class="uploadbtn clearfix personorderbtn">
	        	<a href="<%=basePath %>/users/init/userInfo" class="think">查看我的贷款</a>
	        	<a href="<%=basePath %>/" class="download">返回网站首页</a>
	        </div>
	    </div>
	</div>
	
	<%@include file="../base/footer.html" %>
	
</body>
</html>