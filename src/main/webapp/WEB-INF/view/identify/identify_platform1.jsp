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
    <script type="text/javascript">
    	var id_sub = 0;
		$(document).ready(function(){
			
			//服务协议已阅读状态
			$(".readdiv span").click(function(){
				id_sub +=1;
				$(this).children("em").toggleClass("colorf");
			});
			
			$(".fdivbtn1").click(function(){
				$(".mask_alpha,.fdiv").hide();
			});
			$(".fdivbtn2").click(function(){
				$(".mask_alpha,.fdiv").hide();
			});
		})
		
		
		function nextIdentify(id,verifyUrl){//点击去认证
			if($('#xieyi').is(":checked")){
				$("#xieyidiv").hide();//阅读协议
			}else{
				$("#xieyidiv").show();//阅读协议
				return;
			}
			
			//到国税网站认证
			//window.open('http://www.bjsat.gov.cn/bjsat/?id='+id);
			var uu = verifyUrl+'?id='+id;
			window.open(uu);
			$('#div_iden').show();
			$('#mask_alpha').show();
			
		}
		function goback(){//点击返回
			location.href = '<%=basePath %>/users/identify/';
		}
		
		
		function identSuc(){//点击认证成功
			$.ajax({
				type:"POST",
				url:"<%=basePath %>/users/identify/taxResult",
				dataType:"JSON",
				success:function(data){
					//alert(JSON.stringify(data));
					/* if(data.result == '-1'){
						//认证过
					}else  */
					if(data.result=="5588" && data.success == 1){
						$('.fdiv').hide();
						location.href='<%=basePath %>/users/identify/success'
					}else if(data.success == -2){
						$('#div_reiden').show();
						$('#mask_alpha').show();
					}else{
						$('#div_iden').hide();
						$('#mask_alpha').hide();
						$('#sp_fail').show();
					}
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					if(XMLHttpRequest.responseText=="timeOut"){
		        		location.reload();
		        	}else{
		        		alert("Error_plat1");
		        	}
		        }
		});
		}
		
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	<div class="main">
		<div class="upload shimingrz">
	    	<h2 class="shimingrzh2">实名认证</h2>
	        <p class="ac ft16">系统将引导您到${province}国税局网站进行实名认证并查询数据</p>
	        <p class="ac ft14"><input id="xieyi" type="checkbox"/>我已经阅读并同意<a href="<%=basePath %>/users/identify/toProtocol" target="_blank">《数据授权协议》</a> </p>
	        <div class="uploadbtn clearfix">
	        	<a href="javascript:;" onclick="goback()" class="think">返回</a>
	        	<a href="javascript:;" onclick="nextIdentify('${id}','${verifyUrl}')" class="download">去认证</a>
	        </div>
	        <p id='sp_fail' class="wrong" style="display:none;"><em>认证失败，如有问题请联系客服！</em></p>
	    </div>
	</div>
    <div class="mask_alpha" style="display: none;"></div>
    <div id='xieyidiv' class="fdiv"  style="display: none;">
        <p>请阅读协议并同意！</p>
        <div><button class="fdivbtn1">确定</button>
        </div>
    </div>
    <div id='div_iden' class="fdiv"  style="display: none;">
        <p>系统已经引导您到${province}国税局网站调取涉税数据</p>
        <div><button style="width: 160px;" class="fdivbtn2">遇到问题,认证失败</button>
        <button class="fdivbtn1" onclick="javascript:identSuc();">认证成功</button></div>
        <br/><span>操作过程中有任何疑问可咨询客服热线 400000000</span>
    </div>
    
    <div id='div_reiden' class="fdiv"  style="display: none;">
        <p>该企业已经通过实名认证，不能重复认证!</p>
        <div><button class="fdivbtn1">确定</button>
        </div>
    </div>
    
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>