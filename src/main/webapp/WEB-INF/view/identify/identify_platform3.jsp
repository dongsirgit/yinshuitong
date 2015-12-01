<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getContextPath();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实名认证-平台人工</title>
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
			
			if($('#code').val()=='请输入验证码'){
				$('#code').val('')
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
					url:"<%=basePath %>/users/identify/plat3",
					data:data,
					dataType:"JSON",
					//dataType:"text",
					success:function(data){
						//alert(JSON.stringify(data));
						if(data.success=='1'){
							$('.fdiv').hide();
							location.href='<%=basePath %>/users/identify/succes';
						}
						else if(data.success == -2){//验证码错误
							$('#sp_fail_code').show();
						}
						else if(data.success == -1){//验证码错误
							$('#sp_fail_code_null').show();
						}
						else if(data.success == -22){//重复认证
							$('#div_reiden').show();
							$('#mask_alpha').show();
						}
						else if(data.success == -3){
							$('#div_iniden').show();
							$('#mask_alpha').show();
						}else{
							$('.fdiv').hide();
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
		
		
		function codefocus(obj){
			$('#sp_fail_code').hide();
			$('#sp_fail_code_null').hide();
			
			if(obj.value =='请输入验证码'){
				obj.value ='';
			}
		}
		function codeblur(obj){
			if(obj.value == ''){
				obj.value ='请输入验证码';
				$('#sp_fail_code_null').hide();
			}
		}
		
		function changeCode(){
			$('#img_code').attr('src',"<%=basePath%>/imageServlet?temp="+new Date().getTime().toString(36));
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
	   		<span style="font-size: 16px;">上传证件认证  需人工审核<br/></span>
   		</div>
   		
   		<div style="width: 50%; height:auto!important; height:60px; min-height:60px;">
   			<form id = 'form' enctype="multipart/form-data">
		   		<p style="margin:20px">
		   			<input id="corpName" style="width: 300px;" name="corpName" type="text" value="请填写公司全称" onfocus="corfocus(this)" onblur="corblur(this)" maxlength="20">
		   			<br/>
		   			<span id='sp_fail_cor' style=" font-size: 13px; color: red; display: none;">请输入正确公司名称</span>
		   		</p>
		   		<p style="margin:20px">
		   			<input id="taxSn" name="taxSn" style="width: 300px;" type="text" value="请填写公司纳税识别号"  onfocus="taxfocus(this)" onblur="taxblur(this)" maxlength="20">
		   			<br/>
		   			<span id='sp_fail_tax' style=" font-size: 13px; color: red; display: none;">请输入正确纳税识别号</span>
		   		</p>
		   		<div class="yzm form-item">
                	<input type="text" class="changeState" name="code" id="code" style="width: 235px;" 
                		value="请输入验证码" onfocus="codefocus(this)" onblur="codeblur(this)" maxlength="4"/><!-- onblur="check_code();"  -->
                    <img id='img_code' src="<%=basePath%>/imageServlet" alt="验证码" title="点击更换" id="code_image" onclick="changeCode()"/>
                    <span id='sp_fail_code' style=" font-size: 13px; color: red; display:none;"><br>验证码输入错误</span>
                    <span id='sp_fail_code_null' style=" text-align:right; font-size: 13px; color: red; display:none;"><br>请输入验证码</span>
               	</div>
               	
		   		<p style="margin:20px">税务登记证复印件加盖公章: <input type="file" > <br/></p>
		   		<p style="margin:20px"><input type="button" value="获取办卡人信息"> <br/></p>
		   		<p> 注: 图片格式为jps、jpeg、png，单个文件不大于1M;扫描件或相片皆可,图片上的文字必须清晰;请将图片选择摆正后上传,方便工作人员审核,避免审核不通过</p>
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
    <div id='div_iniden' class="fdiv"  style="display: none;">
        <p>该企业正在进行实名认证，不能重复认证!</p>
        <div><button class="fdivbtn2">确定</button>
        </div>
    </div>
    
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>