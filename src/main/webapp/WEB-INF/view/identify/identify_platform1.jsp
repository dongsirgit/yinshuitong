<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getContextPath();
   	User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
    int loginflag = 0;
    if(null != user){
    	loginflag = 1;
    }
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实名认证-税局端</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    
    <style type="text/css">
   		.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; border:1px solid #AFAEAC; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
   		.procurrent{background-color: blue;color: white;}
    </style>
    
    <script type="text/javascript">
		
		$(document).ready(function(){
			
			
			$(document).delegate('.city',"click",function(){
				$(this).addClass("procurrent").siblings().removeClass("procurrent");
				$('#sp_city').text($(this).text());
			});
			
			//服务协议已阅读状态
			$(".readdiv span").click(function(){
				$(this).children("em").toggleClass("colorf")
			});
			
			$(".fdivbtn2").click(function(){
				$(".mask_alpha,.fdiv").hide(200);
			});
			
		})
		
		function getCity(){
			var id = $('#id').val();
			 $.ajax({
					type:"POST",
					url:"<%=basePath %>/users/identify/getCity",
					data:{id:id},
					dataType:"JSON",
					//dataType:"text",
					success:function(data){
						//alert(JSON.stringify(data));
						$(".city").remove();
						$.each( data.list, function(i, n){
							$("<span class='city'>&nbsp;&nbsp;"+n.aname+"&nbsp;&nbsp;</span>$").insertAfter("#city");
						});
						
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
			        	alert("加载失败!");
			        }
			});
		}
		
		function nextIdentify(){
			//到国税网站认证
			window.open('http://www.baidu.com');
			$('#div_iden').show();
		}
		function goback(){
			location.href = '<%=basePath %>/users/identify/';
		}
		
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	
   	<div class="mainn" align="center">
   		<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 50px;"><!--  border:1px solid #AFAEAC; -->
   			<span style=" font-size: 36px;">实名认证<br/></span>
   		</div>
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; line-height: 30px;">
	   		<span style="font-size: 16px;">系统将引导您到${province}国税局网站进行实名认证并查询数据<br/></span>
   		</div>
   		
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; line-height: 30px;">
	   		<div class="readdiv"><span><em>√</em></span>
	   		<font style="font-size: 16px;">我已经阅读并同意<a href="<%=basePath %>/users/applyLoan/read" target="_blank">《数据授权协议》</a></font></div>
   		</div>
   		
    	<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 8px;"><!--  border:1px solid #AFAEAC; -->
   			<button onclick="goback()" style="width: 100px;">返回</button>
   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   			<button onclick="nextIdentify()" style="width: 100px;">去认证</button>
   			<br/>
    		<span id='sp_fail' style=" font-size: 13px; color: red; display:none;">认证失败，如有问题请联系客服！</span>
   		</div>
    	<input type="hidden" id='id'>
       
   	</div>
    
    <div class="mask_alpha" style="display: none;"></div>
    <div id='div_login' class="fdiv"  style="display: none;">
        <p>请阅读协议并同意！</p>
        <div><button class="fdivbtn2">确定</button>
        </div>
    </div>
    <div id='div_iden' class="fdiv"  style="display: none;">
        <p>系统已经引导您到${province}国税局网站调取涉税数据</p>
        <div><button style="width: 160px;" class="fdivbtn2">遇到问题,认证失败</button>
        <button class="fdivbtn1" onclick="javascript:location.href='<%=basePath %>/users/identify/success';">认证成功</button></div>
        <br/><span>操作过程中有任何疑问可咨询客服热线 400000000</span>
    </div>
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>