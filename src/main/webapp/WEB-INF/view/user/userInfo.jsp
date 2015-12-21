<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String basePath = request.getContextPath();
	int showPage = 0;
	try{
		showPage = Integer.valueOf((request.getParameter("showPage") == null ||"".equals(request.getParameter("showPage")) || 
				"undefined".equals(request.getParameter("showPage"))? "0":request.getParameter("showPage")));
	}catch(Exception e){
		e.printStackTrace();
		response.sendRedirect(basePath+"/users/init/userInfo?showPage=0");
	}
	String pageBack = request.getParameter("page");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>   
<head>   
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>贷款列表</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/help/help.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/index/index.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/login/login.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/order/order.css" />
	<script type="text/javascript" language="javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>/scripts/common/md5.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		openLi(0);
		$('.content_l ul li').click(function(e) {
// 	        var index=$(this).index();
//             $("#goToPageForm input").val(index);
//             $("#goToPageForm").submit();
			$(this).addClass('current').siblings().removeClass('current');
	    });
	});
	function openLi(index){
		if(index == 0){
			$("#openli").attr("src","<%=basePath%>/users/init/userInfo_account");
		}
	}
</script>
<script language="javascript" type="text/javascript"> 
	function dyniframesize(down) { 
		var ifm= document.getElementById("openli");
		var subWeb = document.frames ? document.frames["openli"].document : ifm.contentDocument;
		if(ifm != null && subWeb != null) {
			  ifm.height = subWeb.body.scrollHeight;
		}
	} 
</script> 
</head>
<body>
	<iframe src="<%=basePath%>/basic/head" width="100%" height="75px" frameborder="0" scrolling="no"></iframe>
	
	<div class="content center clearfix act_list_nobg">
		<div class="content_l">
	        <h3>我的银税通</h3>
	        <ul>
	        	<li class="current" id="list_loans">我的账户</li>
	        	<li ><a href="<%=basePath %>/product/list">我要贷款</a></li>
	        </ul>
	    </div>
		<div class="content_r">
			<iframe id="openli" src="" allowtransparency="true" width="100%" frameborder="0" scrolling="no" name="openli" onload="javascript:dyniframesize('openli');"></iframe>
	    </div>
	</div>
    <form action="<%=basePath%>/users/init/userInfo" id="goToPageForm" method="post" style="display:none" >
       <input name="showPage"/>
    </form>
	<%@include file="../base/footer.html" %>

</body>
</html>
