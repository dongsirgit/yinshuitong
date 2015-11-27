<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getContextPath();
    User cuser = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品详情</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    
    <style type="text/css">
   		.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
    	.productt{ padding-bottom:90px; margin-bottom:60px;}
    	
    </style>
    
    <script type="text/javascript">
        function tess(){
        	
        	$.ajax({
        		type:"POST",
        		url:"<%=basePath %>/product/saveDetail",
        		data:$('#form').serialize(),
        		asysn:false,
        		dataType:"JSON",
        		//dataType:"text",
        		success:function(data){
        			alert(data)
        		},
        		error:function(XMLHttpRequest, textStatus, errorThrown) {
    	        	alert("加载失败!");
    	        }
        	});
        	
        	
        }
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	
	<%-- <c:if test="${detail != null}"> --%>
    	<div class="mainn">
	    	<form id='form' action="<%=basePath %>/product/saveDetail" method="post">
		    	开通地区<textarea rows="2" cols="100" id='ktdq' name='ktdq'></textarea><br>
		    	产品说明<textarea rows="5" cols="100" id='cpsm' name='cpsm' wrap="hard"></textarea><br>
		    	产品优势<textarea rows="5" cols="100" id='cpys' name='cpys'></textarea><br>
		    	贷款对象<textarea rows="5" cols="100" id='dkdx' name='dkdx'></textarea><br>
		    	申办条件<textarea rows="5" cols="100" id='sbtj' name='sbtj'></textarea><br>
		    	申办材料<textarea rows="5" cols="100" id='sbcl' name='sbcl'></textarea><br>
		    	申办流程<textarea rows="5" cols="100" id='sblc' name='sblc'></textarea><br>
		    	贷款费率<textarea rows="5" cols="100" id='dkfl' name='dkfl'></textarea><br>
		    	<button type="submit">tijiao</button>
	    	</form>
		    	<button onclick="tess()" value="niha" >niho</button>
	    	
	    	
        
    	</div>
	<%-- </c:if> --%>
    
    
    
    <script type="text/javascript">
    	$('#protectList tbody tr').mouseover(function(){
    		$(this).css('background','#fffceb');
    	});
    	$('#protectList tbody tr').mouseout(function(){
    		$(this).css('background','#fff');
    	});
    	$('#protectList tbody tr td').live(function(){
    		$(this).css('padding','20px;');
    	});
    	
    	
    </script>
    
    <!-- <div class="mask_alpha" style="display: none;"></div>
    <div class="fdiv"  style="display: none;">
        <p>您需要完善用户信息后才能申请贷款！</p>
        <div><a class="fdivbtn1" href="javascript:userInfo();">去完善</a><a class="fdivbtn2">取消</a></div>
    </div> -->
    <%@include file="../base/footer.html" %>
    
</body>
</html>