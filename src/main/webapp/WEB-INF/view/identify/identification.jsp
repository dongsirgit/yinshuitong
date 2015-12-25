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
<title>实名认证</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    
    <script type="text/javascript">
		
		$(document).ready(function(){
			
			$(".province").click(function(){
				$(this).addClass("selected").siblings().removeClass("selected");
				$('#sp_province').text($(this).text());
				$('#div_select_province').hide();
				$('#div_select_city').hide();
				getCity($(this).attr('value'));
				$('#sp_city').text('');
			});
			
			$(document).delegate('.city_c',"click",function(){
				$(this).addClass("selected").siblings().removeClass("selected");
				$('#sp_city').text($(this).text());
				$('#div_select_province').hide();
				$('#div_select_city').hide();
				$('#id').val($(this).attr('value'));
				$('#verifyUrl').val($(this).attr('idenUri'));
			});
		})
		
		function getCity(proId){
			var id = proId;
			 $.ajax({
					type:"POST",
					url:"<%=basePath %>/users/identify/getCity",
					data:{id:id},
					dataType:"JSON",
					success:function(data){
						$(".city_c").remove();
						$.each( data.list, function(i, n){
							$("<a class='city_c' value='"+n.id+"' idenUri='"+n.verifyUrl+"'>"+n.aname+"</a>").insertAfter("#city");
						});
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
						if(XMLHttpRequest.responseText=="timeOut"){
			        		location.reload();
			        	}else{
			        		alert("Error");
			        	}
			        }
			});
		}
		
		function nextIdentify(){
			//location.href='<%=basePath %>/users/identify/next';
			if($('#sp_province').text() == ''){
				$('#div_select_province').show();
				return;
			}
			if($('#sp_city').text() == ''){
				$('#div_select_city').show();
				return;
			}
			$("form").submit();
		}
		
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
    <div class="main">
	<div class="chose_area">
		<h1>实名认证</h1>
		<p>请正确选择要认证的企业注册所在地，否则无法顺利通过认证影响您的贷款申请</p>
		<div class="provinces">
			<h4>选择省份：</h4>
			<c:forEach items="${province}" var="province">
   				<a class='province' href="javascript:;" value='${province.id}'>${province.aname}</a>
   			</c:forEach>
		</div>
		<div class="city">
			<h4 id='city'>选择地区：</h4>
		</div>
		<div class="chose_result">您选择的是：
			<span id='sp_province'></span>
   			<span id='sp_city'></span>
   		</div>
		<a class="next_btn" href="javascript:;" onclick="nextIdentify()">下一步</a>
		<p id='div_select_province' class="wronginfo_chose" style="display: none;">请选择省份!</p>
    	<p id='div_select_city' class="wronginfo_chose" style="display: none;">请选择地区!</p>
	</div>
	<form id='form' action="<%=basePath %>/users/identify/next" method="post">
    		<input type="hidden" id='id' name='id'>
    		<input type="hidden" id='verifyUrl' name='verifyUrl'>
   		</form>
</div>
    <%@include file="../base/footer.html" %>
</body>
</html>