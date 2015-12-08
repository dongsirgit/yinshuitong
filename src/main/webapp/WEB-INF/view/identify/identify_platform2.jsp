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
    	var id_sub = 0;
		$(document).ready(function(){
			
			//服务协议已阅读状态
			$(".readdiv span").click(function(){
				id_sub +=1;
				$(this).children("em").toggleClass("colorf")
			});
			
			$(".fdivbtn2").click(function(){
				$(".mask_alpha,.fdiv").hide(200);
			});
			
		})
		
		
		
		function nextIdentify(){
			if($('#corpName').val()=='请填写公司全称' || $('#corpName').val()==''){
				$('#corpName').val('');
				$('#sp_fail_cor').show();
				return false;
			}
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#corpName").val())){
				$('#sp_fail_cor').show();
				return ;
			}
			if($('#taxSn').val()=='请填写公司纳税识别号' || $('#taxSn').val()==''){
				$('#taxSn').val('');
				$('#sp_fail_tax').show();
				return false;
			}
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#taxSn").val())){
				$('#sp_fail_tax').show();
				return ;
			}
			if($('#idcard').val()=='请填写法定代表人身份证号码' || $('#idcard').val()==''){
				$('#idcard').val('');
				$('#sp_fail_idc').show();
				return false;
			}
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#idcard").val())){
				$('#sp_fail_idc').show();
				return ;
			}
			
			if(id_sub%2==0){
				$("#xieyidiv").hide();//阅读协议
			}else{
				$("#xieyidiv").show();//阅读协议
				return;
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
						if(data.success=='1'){
							$('.fdiv').hide();
							location.href='<%=basePath %>/users/identify/success';
						}else if(data.success == '-2'){
							$('#div_reiden').show();
							$('#mask_alpha').show();
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
			$('#sp_fail_cor').hide();
			if(obj.value =='请填写公司全称'){
				obj.value ='';
			}
		}
		function corblur(obj){
			if(obj.value == ''){
				obj.value ='请填写公司全称';
				$('#sp_fail_cor').show();
			}else 
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#corpName").val())){
				$('#sp_fail_cor').show();
			}
		}
		
		function taxfocus(obj){
			$('#sp_fail_tax').hide();
			if(obj.value =='请填写公司纳税识别号'){
				obj.value ='';
			}
		}
		function taxblur(obj){
			if(obj.value == ''){
				obj.value ='请填写公司纳税识别号';
				$('#sp_fail_tax').show();
			}else 
				if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#taxSn").val())){
					$('#sp_fail_tax').show();
				}
		}
		
		function idcardfocus(obj){
			$('#sp_fail_idc').hide();
			if(obj.value =='请填写法定代表人身份证号码'){
				obj.value ='';
			}
		}
		function idcardblur(obj){
			if(obj.value == ''){
				obj.value ='请填写法定代表人身份证号码';
				$('#sp_fail_idc').show();
			}else 
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#idcard").val())){
				$('#sp_fail_idc').show();
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
		   		<p style="margin:20px">
		   			<input id="corpName" style="width: 300px;" name="corpName" type="text" value="请填写公司全称" 
		   				onfocus="corfocus(this)" onblur="corblur(this)" maxlength="20"><br/>
		   			<span id='sp_fail_cor' style=" font-size: 13px; color: red; display: none;">请输入正确公司名称</span>
		   		</p>
		   		<p style="margin:20px">
		   			<input id="taxSn" name="taxSn" style="width: 300px;" type="text" value="请填写公司纳税识别号"  
		   				onfocus="taxfocus(this)" onblur="taxblur(this)" maxlength="20"><br/>
		   			<span id='sp_fail_tax' style=" font-size: 13px; color: red; display: none;">请输入正确纳税识别号</span>
		   		</p>
		   		<p style="margin:20px">
		   			<input id="idcard" name="idcard" type="text" style="width: 300px;" value="请填写法定代表人身份证号码" 
		   				onfocus="idcardfocus(this)" onblur="idcardblur(this)" maxlength="20"><br/>
		   			<span id='sp_fail_idc' style=" font-size: 13px; color: red; display: none;">请输入正确法定代表人身份证号码</span>
		   		</p>
	   		</form>
   		</div>
   		
   		
   		
   		<div style="width: 80%; height:auto!important; height:60px; min-height:60px; line-height: 30px;">
	   		<div class="readdiv"><span><em>√</em></span>
	   		<font style="font-size: 16px;">我已经阅读并同意<a href="<%=basePath %>/users/identify/toProtocol" target="_blank">《数据授权协议》</a></font></div>
   		</div>
   		
    	<div style="width: 80%; height: 100%; padding-top: 20px; padding-bottom: 8px;"><!--  border:1px solid #AFAEAC; -->
   			<button onclick="goback()" style="width: 100px;">返回</button>
   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   			<button onclick="javascript:return nextIdentify();" style="width: 100px;">去认证</button>
   			<br/>
    		<span id='sp_fail' style=" font-size: 13px; color: red; display:none;">认证失败，如有问题请联系客服！</span>
   		</div>
    	<input type="hidden" id='id'>
       
   	</div>
    
    <div class="mask_alpha" style="display: none;"></div>
    <div id='xieyidiv' class="fdiv"  style="display: none;">
        <p>请阅读协议并同意！</p>
        <div><button class="fdivbtn2">确定</button>
        </div>
    </div>
    
    <div id='div_reiden' class="fdiv"  style="display: none;">
        <p>该企业已经通过实名认证，不能重复认证!</p>
        <div><button class="fdivbtn2">确定</button>
        </div>
    </div>
    
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>