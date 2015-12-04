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
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    
    <style type="text/css">
   		.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; 
   		border:1px solid #AFAEAC; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
   		.procurrent{background-color: blue;color: white;}
    </style>
    
    <script type="text/javascript">
		
		$(document).ready(function(){
			
			$(".province").click(function(){
				$(this).addClass("procurrent").siblings().removeClass("procurrent");
				$('#sp_province').text($(this).text());
// 				$('#id').val($(this).attr('value'));
				$('#div_select_province').hide();
				$('#div_select_city').hide();
				getCity($(this).attr('value'));
				$('#sp_city').text('');
			});
			
			$(document).delegate('.city',"click",function(){
				$(this).addClass("procurrent").siblings().removeClass("procurrent");
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
					//dataType:"text",
					success:function(data){
						//alert(JSON.stringify(data));
						$(".city").remove();
						$.each( data.list, function(i, n){
							$("<span class='city' value='"+n.id+"' idenUri='"+n.verifyUrl+"'>&nbsp;&nbsp;"+n.aname+"&nbsp;&nbsp;</span>$").insertAfter("#city");
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
	
   	<div class="mainn" align="center">
   		<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 8px;"><!--  border:1px solid #AFAEAC; -->
   			<span style=" font-size: 36px;">实名认证<br/></span>
    		<span style=" font-size: 18px;">请正确选择要认证的企业注册所在地，否则无法顺利通过认证影响您的融资申请</span>
   		</div>
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; border:1px solid #AFAEAC; padding: 10px; text-align: left; line-height: 30px;">
	   		<span id="province" style="font-weight: bold;">选择省份:<br/></span>
   			<c:forEach items="${province}" var="province">
   				<span class='province' onclick="javascript:;" value='${province.id}'>&nbsp;&nbsp;${province.aname}&nbsp;&nbsp;</span>
   			</c:forEach>
	    		<!-- <span onclick="" style="background-color: blue;color: white;">&nbsp;&nbsp;北京市1&nbsp;&nbsp;</span>
	    		<span onclick="">&nbsp;&nbsp;上海市&nbsp;&nbsp;</span> -->
	    		
   		</div>
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; border:1px solid #AFAEAC; padding: 10px; text-align: left; line-height: 30px;margin-top: 10px;">
   			<span id='city' style="font-weight: bold; margin-bottom: 50px;">选择城市:<br/></span>
    		<!-- <span onclick=""  style="background-color: blue;color: white;">&nbsp;海淀区&nbsp;</span> -->
   		</div>
    	<div style="width: 80%; height: 100%; border:1px solid #AFAEAC; padding: 10px; text-align:center; line-height: 30px;margin-top: 20px;">
    		<span style="font-size: 16px;">您选择的是:</span>
   			<span id='sp_province' style="margin-bottom: 50px; font-size: 28px;"></span>
   			<span id='sp_city' style="margin-bottom: 50px; font-size: 28px;"></span><br/>
   		</div>
    	<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 8px;"><!--  border:1px solid #AFAEAC; -->
   			<br/><br/>
   			<button onclick="nextIdentify()" style="width: 100px;">下一步</button>
   			<br/>
    		<span id='div_select_province' style=" font-size: 13px; color: red; display: none;">请选择省份!</span>
    		<span id='div_select_city' style=" font-size: 13px; color: red; display: none;">请选择城市!</span>
   		</div>
   		<form id='form' action="<%=basePath %>/users/identify/next" method="post">
    		<input type="hidden" id='id' name='id'>
    		<input type="hidden" id='verifyUrl' name='verifyUrl'>
   		</form>
       
   	</div>
    
    
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>