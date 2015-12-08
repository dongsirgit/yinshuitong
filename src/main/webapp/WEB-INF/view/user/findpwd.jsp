<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>忘记密码</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/user/user.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/login/findpassword.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formVerifier.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formState.js"></script>
	<script type="text/javascript">
	    var basePath = '<%=basePath%>';
	    function checkMobilePhone(){
	    	var settings={
	    			mobilePhone:$("#mobilePhone"),
	    			mobilePhoneMsg:$("#mobilePhoneMsg"),
	    			reg_mobilePhone:/^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/
	    			},
	    		    result = verifier.init(settings).checkMobilePhone();
		    if(result){
		    	$("#mobilePhoneMsg").text("");
		    	return true;
		    }
		    return false;
	    }
       function checkMobilePhoneAgain(){
            var settings={
                    mobilePhone:$("#mobilePhone"),
                    mobilePhoneMsg:$("#mobilePhoneMsg"),
                    reg_mobilePhone:/^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/
                    },
                    result = verifier.init(settings).checkMobilePhone(),
                    checkResult = false;
            if(result == "exist"){
            	$("#mobilePhoneMsg").text("");
                checkResult = true;
            }else if(result == "noexist"){
                $("#mobilePhoneMsg").text("该手机号不存在！");
            }
            return checkResult;
        }
	    function checkPhoneCode(){
	    	var settings={
	    			phoneCode:$("#phoneCode"),
	    			phoneCodeMsg:$("#phoneCodeMsg"),
	    			mobilePhone:$("#mobilePhone"),
	    			reg_phoneCode:/^\d{6}$/
	    	}
	    	return verifier.init(settings).checkPhoneCode();
	    }
		function getPhoneCode(){
	        if(checkMobilePhoneAgain()){
	           $.ajax({
	               type:"POST",
	               url:basePath+"/user/sendPhoneCode", 
	               data:{"mobilePhone":$("#mobilePhone").val()},
	               async:true,
	               success:function(data){
/* 	                   if(data == 0){ */
	                       //发送成功
	                       $("#phoneCode").attr("disabled",false);
	                       timer();
	                       $("#mobilePhoneMsg").text("");
	                       alert(data);
/* 	                   }else if(data == 3){
	                       $("#mobilePhoneMsg").text("该手机号不存在");
	                   }else if(data == 16){
	                       //服务异常,如欠费等
	                       $("#mobilePhoneMsg").text("服务器异常,请刷新后重试！");
	                   }else{
	                       //包含参数异常错误码15
	                       $("#mobilePhoneMsg").text("参数异常！");
	                   } */
	               }
	          });
	        }
	    }
		var timer = (function(){
	        var time = 120;
	        return function(){
	            $(".getPhoneCodeBtn").css("background-color","#ccc").attr("onclick","");
	            var id = setInterval(function(){
	                if(time >= 0){
	                    $(".getPhoneCodeBtn").text(time+"秒后重试");
	                    time--;
	                }else{
	                    $(".getPhoneCodeBtn").css("background-color","#c39b66").attr("onclick","getPhoneCode()").text("获取验证码");
	                    clearInterval(id);
	                    time = 120;
	                }
	            },1000);
	        }
	    })();
		function checkPhoneOwner(){
			if(checkMobilePhoneAgain() && checkPhoneCode() && verifier.checkPhoneOwner()){
				location = basePath+"/user/findPwd_reset";
			}
		}
	</script>
</head>
<body>
	<iframe src="<%=basePath%>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	<form id="myform">
		<div class="forget">
			<div class="forget_con">
				<h3>找回密码</h3>
				<div class="hg">
				    <label style="width:80px;font-size:15px">手机号:</label>
					<input class="inputState" type="text" value="手机号码" name="mobilePhone" id="mobilePhone" onblur="checkMobilePhone()" style="color:#b0b0b0;IME-MODE: disabled;" 
                        onkeyup="this.value=this.value.replace(/\D/g,'');if(this.value.length>11){this.value=this.value.substr(0,11)};"  
                        onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					<span style="display:none">手机号码</span>
					<p class="prompt" id="mobilePhoneMsg"></p>
				</div>
				<div class="getPhoneCodeBtn" onclick="getPhoneCode()">获取验证码</div>
				<div class="hg">
				    <label style="width:80px;font-size:15px">验证码:</label>
					<input class="inputState" type="text" value="验证码" name="phoneCode" id="phoneCode" 
					   onblur="checkPhoneCode()" style="width:200px;color:#b0b0b0;" disabled="disabled" 
					   onkeyup="this.value=this.value.replace(/\D/g,'');if(this.value.length>6){this.value=this.value.substr(0,6)};"  
                       onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					<span style="display:none">验证码</span>
					<p class="prompt" id="phoneCodeMsg"></p>
				</div>
				<a onclick="checkPhoneOwner()" class="sub_btn">下一步</a>
				<p class="text">手机验证码校验成功后,方可修改密码！</p>
			</div>
		</div>
	</form>
	<%@include file="../base/footer.html"%>
</body>
</html>
