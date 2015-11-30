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
<title>实名认证-平台1</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    
    <style type="text/css">
   		.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; border:1px solid #AFAEAC; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
   		.procurrent{background-color: blue;color: white;}
    </style>
    
    <script type="text/javascript">
		
		$(document).ready(function(){
			
			//服务协议已阅读状态
			$(".readdiv span").click(function(){
				$(this).children("em").toggleClass("colorf")
			});
			
			$(".fdivbtn2").click(function(){
				$(".mask_alpha,.fdiv").hide(200);
			});
			
		})
		
		
		
		function nextIdentify(){
			if($('#corpName').val()=='请填写公司全称'){
				$('#corpName').val('')
			}
			if($('#taxSn').val()=='请填写公司纳税识别号'){
				$('#taxSn').val('')
			}
			if($('#idcard').val()=='请填写法定代表人身份证号码'){
				$('#idcard').val('')
			}
			var data = $('#form').serialize();
			 $.ajax({
					type:"POST",
					url:"<%=basePath %>/users/identify/plat2",
					data:data,
					dataType:"JSON",
					//dataType:"text",
					success:function(data){
						//alert(JSON.stringify(data));
						if(data.suc=='1'){
							location.href='<%=basePath %>/users/identify/success';
						}else{
							$('#sp_fail').show();
						}
						
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
		function goback(){
			location.href = '<%=basePath %>/users/identify/';
		}
		
		
		function corfocus(obj){
			if(obj.value =='请填写公司全称'){
				obj.value ='';
			}
		}
		function corblur(obj){
			if(obj.value == ''){
				obj.value ='请填写公司全称';
			}
		}
		
		function taxfocus(obj){
			if(obj.value =='请填写公司纳税识别号'){
				obj.value ='';
			}
		}
		function taxblur(obj){
			if(obj.value == ''){
				obj.value ='请填写公司纳税识别号';
			}
		}
		
		function idcardfocus(obj){
			if(obj.value =='请填写法定代表人身份证号码'){
				obj.value ='';
			}
		}
		function idcardblur(obj){
			if(obj.value == ''){
				obj.value ='请填写法定代表人身份证号码';
			}
		}
		
		
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	
   	<div class="mainn" align="center">
   		<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 20px;"><!--  border:1px solid #AFAEAC; -->
   			<span style=" font-size: 36px;">实名认证<br/></span>
   		</div>
   		
   		<div style="width: 80%; height:auto!important; height:20px; min-height:20px; line-height: 20px;">
	   		<span style="font-size: 16px;">请正确填写贵公司在国税系统中留存的信息<br/></span>
   		</div>
   		
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px;">
   			<form id = 'form'>
		   		<p style="margin:20px"><input id="corpName" style="width: 300px;" name="corpName" type="text" value="请填写公司全称" onfocus="corfocus(this)" onblur="corblur(this)"><br/></p>
		   		<p style="margin:20px"><input id="taxSn" name="taxSn" style="width: 300px;" type="text" value="请填写公司纳税识别号"  onfocus="taxfocus(this)" onblur="taxblur(this)"><br/></p>
		   		<p style="margin:20px"><input id="idcard" name="idcard" type="text" style="width: 300px;" value="请填写法定代表人身份证号码"  onfocus="idcardfocus(this)" onblur="idcardblur(this)"><br/></p>
	   		</form>
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
    
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>