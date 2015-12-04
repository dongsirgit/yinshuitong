<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="<%=basePath%>/styles/common/base.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="header_wrap">
	<div class="header clearfix">
    	<a href="<%=basePath%>/" target="_top"><img src="<%=basePath %>/images/common/logo.jpg" alt="" class="logo"></a>
        <c:choose>
    		<c:when test="${loginedUser != null}">
    			<div class="header_right clearfix">
		            <div class="header_right_login" style="display:block;">您好，<span id ="mobilePhone"></span>!
		             	<a href="<%=basePath%>/users/init/userInfo" class="mine" target="_top">我的银税通</a> 
		       	 		<a href="<%=basePath%>/users/exit"  class="quiet" target="_top">[退出]</a> 
		            </div>
		            <div class="header_right_left">
		                <a href="<%=basePath%>/" class="help" target="_top">首页</a>
		                <a href="#" class="help" target="_blank">贷款产品</a>
		                <a href="<%=basePath%>/basic/forwardHelp" class="help" target="_blank">帮助中心</a>
		                <a href="<%=basePath%>/basic/aboutUs" class="help" target="_blank">关于我们</a>
		            </div>
		        </div>
		    </c:when>
		    <c:otherwise>
		    	<div class="header_right clearfix">
		            <!--未登录-->
		            <div class="not_logoin">
		                <a href="<%=basePath%>/user/forwardRegist" class="regist head_btn" target="_top">快速注册</a>
		                <a href="<%=basePath%>/user/forwardLogin" class="logoin head_btn" target="_top">立即登录</a>
		            </div>
		            <div class="header_right_left">
		                <a href="<%=basePath%>/" class="help" target="_top">首页</a>
		                <a href="#" class="help" target="_blank">贷款产品</a>
		                <a href="<%=basePath%>/basic/forwardHelp" class="help" target="_blank">帮助中心</a>
		                <a href="<%=basePath%>/basic/aboutUs" class="help" target="_blank">关于我们</a>
		            </div>
		        </div>
		    </c:otherwise>
        </c:choose>
    </div>
</div>
<script type="text/javascript">
	var all = '${loginedUser.mobilePhone}';
	var str4rp = all.substring(3, 7);
	var result = all.replace(str4rp, "****");
	document.getElementById("mobilePhone").innerHTML = result;
</script>
</body>
</html>