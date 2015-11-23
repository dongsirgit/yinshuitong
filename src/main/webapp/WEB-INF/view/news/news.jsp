<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司动态</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/index/index.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="75px" frameborder="0" scrolling="no"></iframe>
<%-- <jsp:include page="news2.html"/> --%>

<%@include file="../base/footer.html" %>
<script type="text/javascript" >
$(function(){
	var high=$('.content_r').height();
	$('.content_l').height(high)
	
})
</script>
</body>
</html>