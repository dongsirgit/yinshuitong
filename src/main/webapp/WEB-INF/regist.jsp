<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath();%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>注册</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/login/login.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/order/order.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/user/user.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/common/md5.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formVerifier.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formState.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/order/order.js"></script>
</head>
<body class="backgroud" onkeydown="keyLogin(event);">
    <iframe src="<%=basePath%>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
    <div class="register">
        <div class="wrap_629">
            <div class="content">
                <img src="<%=basePath%>/images/login/logintopbg.jpg" width="629" height="26" />
                <div class="regist_info">今后您可以通过注册的用户名和密码登录百望融资服务平台，办理贷款申请、查询审批进度 </div>
                <br />
                <form action="<%=basePath%>/user/register" method="post" id="myform" onsubmit="return register()">
                    <div class="form-item ">
                        <label>手机号</label>
                        <input type="text" class="inputState" id="mobilePhone" style="color:#b0b0b0;IME-MODE: disabled;" name="mobilePhone" value="请输入手机号" 
                            onblur="checkMobilePhone()" 
                            onkeyup="this.value=this.value.replace(/\D/g,'');if(this.value.length>11){this.value=this.value.substr(0,11)};"  
                            onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                        <span style="display: none">请输入手机号</span>
                    </div>
                    <div class="info"><span id="mobilePhoneMsg"></span></div>
                    <div class="getPhoneCodeBtn" onclick="getPhoneCode()">获取验证码</div>
                    <div class="form-item" style="width:240px;padding-right:0">
                        <label>验证码</label>
                        <input type="text" class="inputState" id="phoneCode" style="color:#b0b0b0;width:148px" name="phoneCode" value="请输入验证码" 
                            onblur="checkPhoneCode()" disabled="disabled"
                            onkeyup="this.value=this.value.replace(/\D/g,'');if(this.value.length>6){this.value=this.value.substr(0,6)};"  
                            onafterpaste="this.value=this.value.replace(/\D/g,'')" />
                        <span style="display: none">请输入验证码</span>
                    </div>
                    <div class="info"><span id="phoneCodeMsg"></span></div>
                    <div class="form-item">
                        <label>登录密码</label>
                        <input type="password" class="pwdState" id="userPwd1_hidden" style="color:#000; display:none" value="" 
                            onblur="checkPwd1()"  onkeyup="if(this.value.length>16){this.value=this.value.substr(0,16)};"/>
                        <input type="text" class="pwdState" id="userPwd1_text" style="color:#b0b0b0;" value="请输入登录密码" />
                    </div>
                    <div class="info">
                        <span id="userPwd1Msg" style="margin-right:130px"></span>
                        <i class="pwdLevel level1 nolevel">弱</i>
                        <i class="pwdLevel level2 nolevel">中</i>
                        <i class="pwdLevel level3 nolevel">强</i>
                    </div>
                    <div class="form-item pwdState">
                        <label>确认密码</label>
                        <input type="password" class="pwdState" id="userPwd2_hidden" style="color: #000; display: none" value="" 
                            onblur="checkPwd2()" onkeyup="if(this.value.length>16){this.value=this.value.substr(0,16)};"/>
                        <input type="text" class="pwdState" id="userPwd2_text" style="color:#b0b0b0;" value="请输入确认密码" />
                        <input name="userPass" type="hidden" id="userPwd_md5" />
                    </div>
                    <div class="info"><span id="userPwd2Msg"></span></div>
                    <div class="login_button">
                        <input type="submit" value='注册' class="registerBtn"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="footer">@百望股份有限公司 All rights reserved | 京ICP备15045264号</div>
    </div>
</body>
<script type="text/javascript">
    var basePath = '<%=basePath%>';
	function checkMobilePhone(){
		var settings = {
				mobilePhone:$("#mobilePhone"),
				mobilePhoneMsg:$("#mobilePhoneMsg"),
				reg_mobilePhone:/^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/
				},
				result = verifier.init(settings).checkMobilePhone(),
				checkResult = false;
		if(result == "exist"){
			$("#mobilePhoneMsg").text("该手机号已存在");
		}else if(result == "noexist"){
			$("#mobilePhoneMsg").text("");
		    checkResult = true;			
		}
		return checkResult;
	}
	function checkPhoneCode(){
		var settings = {
				phoneCode:$("#phoneCode"),
				phoneCodeMsg:$("#phoneCodeMsg"),
				mobilePhone:$("#mobilePhone"),
				reg_phoneCode:/^\d{6}$/
				};
		return verifier.init(settings).checkPhoneCode();
	}
	function checkPwd1(){
		var settings = {
				userPwd1:$("#userPwd1_hidden"),
				userPwd1Msg:$("#userPwd1Msg"),
				reg_userPwd:/^\S{8,16}$/,
				reg_userPwd_weak:/^(\S)\1+$|^\d{8}$|^[A-Za-z_]{8}$|^\W{8}?$/,
				reg_userPwd_normal:/^[\d|(A-Za-z_)]{8,}$|^[\d|\W]{8,}$|^[(A-Za-z_)|\W]{8,}$/,		
				reg_userPwd_strong:/^[\d|(A-Za-z_)|\W]{8,}$/	
			},
			result = verifier.init(settings).checkPwd1(),
		    checkResult = true;
		$(".pwdLevel").css("visibility","hidden").addClass("nolevel");
		if(result){
			$(".pwdLevel").css("visibility","visible");
			if(result == "weak"){
				$(".pwdLevel:lt(1)").removeClass("nolevel");
			}else if(result == "normal"){
				$(".pwdLevel:lt(2)").removeClass("nolevel");
			}else{
				$(".pwdLevel:lt(3)").removeClass("nolevel");
			}
		}else{
			checkResult = false;
		}
		return checkResult;
	}
	function checkPwd2(){
		var settings = {
				userPwd1:$("#userPwd1_hidden"),
				userPwd2:$("#userPwd2_hidden"),
				userPwd1Msg:$("#userPwd1Msg"),
				userPwd2Msg:$("#userPwd2Msg")
				};
		return verifier.init(settings).checkPwd2();
	}
	function getPhoneCode(){
		if(checkMobilePhone()){
		   $.ajax({
               type:"POST",
               url:basePath+"/user/sendPhoneCode", 
               data:{"mobilePhone":$("#mobilePhone").val()},
               async:true,
               success:function(data){
             	   /* if(data == 0){ */
            		   //发送成功
			           $("#phoneCode").attr("disabled",false);
			           timer();
            		   $("#mobilePhoneMsg").text("");
            		   alert(data);
/*              	   }else if(data == 3){
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
					$(".getPhoneCodeBtn").text(time+"s后重试");
					time--;
				}else{
					$(".getPhoneCodeBtn").css("background-color","#c39b66").attr("onclick","getPhoneCode()").text("获取验证码");
					clearInterval(id);
					time = 120;
				}
			},1000);
		}
	})();
	function register(){
		if(checkMobilePhone() && checkPwd1() && checkPwd2() 
				&& checkPhoneCode() && verifier.checkPhoneOwner()){
			var userPwd=hex_md5($("#userPwd1_hidden").val());
			$("#userPwd_md5").val(userPwd);
			return true;
		}else{
			return false;
		}
	}
   function keyLogin(et) {
        if (et.keyCode == 13 || et.which == 13) {
        	register();
        }
    }
</script>
</html>
