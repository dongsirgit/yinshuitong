<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String basePath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片预览</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>/scripts/common/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/scripts/order/order.js"></script>
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div class="main">
	<ul class="piclist clearfix">
			<c:forEach var="attach" items="${attachList}">
				<li><a title="点击放大"><span><img src="${attach.attachurl }" width="715" height="403"/></span></a></li>
			</c:forEach>
    </ul>
</div>
<%@ include  file="../base/footer.html"%>
<div class="mask"></div>
<div class="alertimgbox"></div>
</body>
</html>