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
	<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/index/index.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/login/login.css">
	<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>/scripts/common/md5.js"></script>
	<%-- <script type="text/javascript" language="javascript" src="<%=basePath %>/scripts/user/userInfo.js"></script> --%>
	
<script type="text/javascript">

	var basePath = '<%=basePath%>';
	var showPage = <%=showPage%>;
	var pageBack = '<%=pageBack%>';//返回页面
	var userType = '<%=userType%>';
	$(document).ready(function(){
		if(showPage==1){
			$(document).attr("title","企业信息");
		}else if(showPage==2){
			if(userType=='0'){
				$(document).attr("title","邀请好友");
			}else if(userType=='22'){
				$(document).attr("title","邀请记录");
			}
		}else if(showPage==3){
			$(document).attr("title","修改密码");
		}
		
		$('.content_l ul li').eq(showPage).addClass('current').siblings().removeClass('current');
		openLi(showPage);
		
		$('.content_l ul li').click(function(e) {
	        var index=$(this).index();
            $("#goToPageForm input").val(index);
            $("#goToPageForm").submit();
			$(this).addClass('current').siblings().removeClass('current');
	    });
		
	});
	function openLi(index){
		if(index == '0'){
			$("#openli").attr("src",basePath+"/users/init/userInfo_applyList")
		}else if(index == '1'){
			$("#openli").attr("src",basePath+"/users/init/userInfo_company?pageBack="+pageBack)
		}else if(index == '2'){
			if(userType=='0'){
				$("#openli").attr("src",basePath+"/users/init/list_invite")
			}else if(userType=='22'){
				$("#openli").attr("src",basePath+"/users/init/inviteList_jf")
			}
		}else if(index == '3'){
			$("#openli").attr("src",basePath+"/users/init/userInfo_changePwd")
		}else{
			$("#openli").attr("src",basePath+"/users/init/userInfo_applyList")
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
	        	<li class="current" id="list_loans">贷款列表</li>
	        	<%
					if(null!=cuser){
				%>
	        	<li>企业信息</li>
	        	<%
					}else if(null!=puser){
				%>
				<li>个人信息</li>
				<%
					}
				%>
				<c:if test="${sessionInfo.userType=='0'}">
					<li>邀请好友</li>
				</c:if>
				<c:if test="${sessionInfo.userType=='22'}">
					<li>邀请记录</li>
				</c:if>
	        	<li>修改密码</li>
	        </ul>
	        <a href="<%=basePath %>/basic/productList" class=" head_btn content_l_btn">我要贷款</a>
	    </div>
	    
		<div class="content_r">
			<iframe id="openli" src="" allowtransparency="true" width="100%" frameborder="0" scrolling="no" name="openli" onload="javascript:dyniframesize('openli');"></iframe><!--   -->
	    </div>
	</div>
    <form action="<%=basePath%>/users/init/userInfo" id="goToPageForm" method="post" style="display:none" >
       <input name="showPage"/>
    </form>
	
	<%@include file="../base/footer.html" %>

</body>
</html>
