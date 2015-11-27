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
	<script type="text/javascript" src="<%=basePath%>/scripts/common/md5.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formVerifier.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formState.js"></script>
	<script type="text/javascript">
	    var basePath = '<%=basePath%>';
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
	    function changePwd(){
	    	if(checkPwd1() && checkPwd2()){
	    		 $.ajax({
	                 type:"POST",
	                 url:basePath+"/user/changePwd", 
	                 data:{"userPass":hex_md5($("#userPwd1_hidden").val())},
	                 async:true,
	                 success:function(data){
	                	 if(data == 0){
	                		 location=basePath+"/user/findPwd_success";
	                	 }else if(data == 3){
	                		 $("#userPwd2Msg").text("请先返回验证手机验证码");
	                	 }else{
	                		 $("#userPwd2Msg").text("参数异常！");
	                	 }
	                 }
	            });
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
					<label style="width:80px;font-size:15px">登录密码:</label>
                    <input type="password" class="pwdState" id="userPwd1_hidden" style="color:#000; display:none" value="" onblur="checkPwd1()" />
                    <input type="text" class="pwdState" id="userPwd1_text" style="color:#b0b0b0;" value="请输入登录密码" />
					<p class="prompt" id="userPwd1Msg" style="display:inline-block"></p>
					<i class="pwdLevel level1 nolevel">弱</i>
                    <i class="pwdLevel level2 nolevel">中</i>
                    <i class="pwdLevel level3 nolevel">强</i>
				</div>
				<div class="hg" style="margin-top:10px">
					<label style="width:80px;font-size:15px">确认密码:</label>
                    <input type="password" class="pwdState" id="userPwd2_hidden" style="color: #000; display: none" onblur="checkPwd2()" value="" />
                    <input type="text" class="pwdState" id="userPwd2_text" style="color:#b0b0b0;" value="请输入确认密码" />
                    <input name="userPass" type="hidden" id="userPwd_md5" />
					<p class="prompt" id="userPwd2Msg" ></p>
				</div>
				<a onclick="changePwd()" class="sub_btn">提交</a>
				<p class="text"></p>
			</div>
		</div>
	</form>
	<%@include file="../base/footer.html"%>
</body>
</html>
