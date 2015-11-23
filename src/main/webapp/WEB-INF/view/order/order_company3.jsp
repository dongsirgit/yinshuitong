<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	Cuser user = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
	Long id = 0l;
	try{
		id = Long.valueOf((null == request.getParameter("id") ||"".equals(request.getParameter("id")) ? "0":request.getParameter("id")));
	}catch(Exception e){
		
	}
	String applyType = request.getParameter("type");
	System.out.println("-------applyType------"+applyType);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>企业经营税务贷</title> -->
<% if(null!= applyType && "1".equals(applyType)){ %>
	<title>企业经营税务贷</title>
<% }else if(null!= applyType && "3".equals(applyType)){ %>
	<title>企业法人税务贷</title>
<%}%>
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
		<div class="upload">
	    	<h3>贷款申请已提交！请等待工作人员的审核</h3>
	        <p><span>为了使您的贷款申请更容易被银行快速审批通过，您可以通过客户端上传您的增值税销项发票，银行在审批贷款时将参考这些数据。</span></p>
	        <div class="uploadbtn clearfix">
	        	<a href="<%=basePath %>/users/init/userInfo" class="think">考虑一下</a>
	        	<a href="javascript:;" class="download">下载客户端并上传发票</a>
	        </div>
	    </div>
	</div>	

	<%@include file="../base/footer.html" %>
</body>
</html>