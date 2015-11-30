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
    
    <style type="text/css">
   		.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; border:1px solid #AFAEAC; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
   		.procurrent{background-color: blue;color: white;}
    </style>
    
    <script type="text/javascript">
		$(document).ready(function(){
			
		})
		
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	
   	<div class="mainn" align="center">
   		<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 50px;"><!--  border:1px solid #AFAEAC; -->
   			<span style=" font-size: 36px;">实名认证<br/></span>
   		</div>
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; line-height: 30px;">
	   		<span style="font-size: 16px;">√您已经通过实名认证，可以去申请贷款了！<br/></span>
   		</div>
   		
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; line-height: 30px;">
	   		<div class="readdiv">
	   		<font style="font-size: 16px;"><a href="<%=basePath %>/product/list" target="_top">返回贷款产品列表>></a></font></div>
   		</div>
   		
    	
    	
       
   	</div>
    
   
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>