<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath();%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/login/login.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/common/base.css" />
	<script type="text/javascript" src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/scripts/user/formState.js"></script>
	<script type="text/javascript" src="<%=basePath %>/scripts/common/md5.js"></script>
	<script type="text/javascript">
	    if(top != self){
	    	if(top.location != self.location)
	    		top.location = self.location; 
		}
	    function getRealValue(Obj){
	    	var value=$.trim(Obj.val());
	    	Obj.val(value);
	    	return Obj.val();
	    }
	    function checkMobilePhone(){
	    	var mobilePhone=getRealValue($("#mobilePhone")),
	    	    mobilePhoneMsg=$("#mobilePhoneMsg"),
	    	    reg_mobilePhone=/^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
	    	if(!mobilePhone){
	    		mobilePhoneMsg.text("手机号不能为空!");
	    		return false
	    	}else if(!reg_mobilePhone.test(mobilePhone)){
	    		mobilePhoneMsg.text("请输入合法的手机号");
	    		return false;
	    	}
	    	mobilePhoneMsg.text("");
	    	return true;
	    }
	    function checkUserPwd(){
            var userPwd=getRealValue($("#userPwd_hidden")),
            userPwdMsg=$("#userPwdMsg");
            if(!userPwd){
                userPwdMsg.text("密码不能为空！");
                return false;
            }
            userPwdMsg.text("");
            return true;
	    }
		function login(){
		    if(checkMobilePhone() && checkUserPwd()){
		        var userPwd=hex_md5($("#userPwd_hidden").val());
		        $("#userPwd_md5").val(userPwd);
		        $.post(
		        	    "<%=basePath%>/user/login",
		        	    $("#myform").serialize(),
		        	    function(data) {
		        	        if(data == 4) {
	                            //账号错误
	                            $("#userPwdMsg").text("用户名或密码输入错误!");
	                        }else if(data == 0){
	                            location.href="<%=basePath%>/users/loginSuccess";
	                        }else{
	                        	//包含参数异常码15
	                        	$("#userPwdMsg").text("参数异常!");
	                        }
	                    }
	                );
		     }
		}
	    function keyLogin(et){
	        if(et.keyCode == 13 || et.which == 13){
	        	login();
	        }
	    }
	</script>
</head>
<body class="backgroud" onkeydown="keyLogin(event);">
<iframe src="<%=basePath%>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div>
  <div class="wrap_629">
    <div class="content">
    <img src="<%=basePath%>/images/login/logintopbg.jpg" width="629" height="26" />
      <form id="myform" >
        <div class="username form-item">
          <label>手机号</label>
            <input type="text" class="inputState" style="color:#b0b0b0;" name="mobilePhone" id="mobilePhone" value="13588888888" onblur="checkMobilePhone()" />
            <span style="display: none;">请输入手机号码</span>
        </div>
        <span id="mobilePhoneMsg" style="color:red;height:28px;display:inline-block"></span>
        <div class="password form-item">
            <label>密码</label>
            <input type="password" class="pwdState" id="userPwd_hidden" style="color:#000;display:none" value="1234qwer" onblur="checkUserPwd()" />
            <input type="text" class="pwdState" id="userPwd_text" style="color:#b0b0b0;" value="请输入密码" />
            <input name="userPass" id="userPwd_md5" type="hidden" ></input>
        </div>
        <div class="info">
            <span id="userPwdMsg"><c:if test="${null != notePass}">${notePass}</c:if></span>
            <a href="<%=basePath%>/user/findPwd">忘记密码？</a>
        </div>
        <div class="login_button"><a onclick="login()">登录</a></div>
      </form>
    </div>
  </div>
  <div class="footer">@百望股份有限公司 All rights reserved   |  京ICP备15045264号</div>
</div>
</body>
</html>