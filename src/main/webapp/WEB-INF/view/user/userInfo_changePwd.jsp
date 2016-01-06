<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/help/help.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/index/index.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/login/login.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/user/user.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/order/order.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formVerifier.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formState.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/common/md5.js"></script>
	<script type="text/javascript">
		(function(){ 
		     if(window.self == window.top){
		         window.top.location = "<%=basePath%>/users/init/userInfo";
		     }
		})();
		var settings = {
				reg_userPwd:/^(?![^a-zA-Z]+$)(?!\D+$).{8,16}$/,
                reg_userPwd_weak:/^(\S)\1+$|^\d{8}$|^[A-Za-z_]{8}$|^\W{8}?$/,
                reg_userPwd_normal:/^[\d|(A-Za-z_)]{8,}$|^[\d|\W]{8,}$|^[(A-Za-z_)|\W]{8,}$/,       
                reg_userPwd_strong:/^[\d|(A-Za-z_)|\W]{8,}$/
		};
		verifier.init(settings);
		function checkOldPwd(){
			var settings = {
					userPwd1:$("#oldUserPwd"),
					userPwd1Msg:$("#oldUserPwdMsg")
					},
					result = verifier.init(settings).checkPwd1();
			if(result!=false){
				$("#oldUserPwdMsg").text("");
				$("#userPwd2Msg").text("");
				return true;
			}
			return false;
		}
		function checkOldPwdAjax(){
			var result = false;
			if(checkOldPwd()){
	            $.ajax({
	                 type:"POST",
	                 url:"<%=basePath%>/users/checkOldPwd", 
	                 data:{"userPwd":hex_md5($("#oldUserPwd").val())},
	                 async:false,
	                 success:function(data){
	                     if(data == 0){
	                    	 $("#userPwd2Msg").text("");
	                         result=true;
	                     }else if(data == 5){
	                    	 $("#userPwd2Msg").text("原密码输入错误");
	                     }else{
	                    	 //包含参数异常错误码15
	                         $("#userPwd2Msg").text("参数异常！");
	                     }
	                 }
	            });
			}
			return result;
		}
		function checkPwd1(){
		    if(checkOldPwd()){
		    	$(".pwdLevel").css("visibility","hidden").addClass("nolevel");
		    	if($("#userPwd1_hidden").val() == $("#oldUserPwd").val()){
		    		$("#userPwd1Msg").text("不能与原密码一致,请重新输入");
		    		return false;
		        }
				var settings = {
						userPwd1:$("#userPwd1_hidden"),
						userPwd1Msg:$("#userPwd1Msg")
						},
						result = verifier.init(settings).checkPwd1(),
				        checkResult = true;
				if(result){
				    $(".pwdLevel").css("visibility","visible");
				    if(result == "strong"){
				        $(".pwdLevel:lt(3)").removeClass("nolevel");
				    }else if(result == "normal"){
				        $(".pwdLevel:lt(2)").removeClass("nolevel");
				    }else{
				        $(".pwdLevel:lt(1)").removeClass("nolevel");
				    }
				}else{
				    checkResult = false;
				}
				return checkResult;
		    }
		    return false;
		}
		function checkPwd2(){
		    if(checkPwd1()){
		    	var settings = {
		    			userPwd1:$("#userPwd1_hidden"),
		    			userPwd2:$("#userPwd2_hidden"),
	                    userPwd1Msg:$("#userPwd1Msg"),
	                    userPwd2Msg:$("#userPwd2Msg")
	                    };
		    	return verifier.init(settings).checkPwd2();
		    }
		    return false;
		}
		function changePwd(){
			if(checkOldPwd() && checkPwd1() && checkPwd2() && checkOldPwdAjax()){
				  $.ajax({
	                     type:"POST",
	                     url:"<%=basePath%>/users/changePwdOnline", 
	                     data:{"userPwd":hex_md5($("#userPwd1_hidden").val())},
	                     async:false,
	                     success:function(data){
	                         if(data == 0){
	                             $("#userPwd2Msg").text("");
	                             $(".tc_password").show();
	                         }else{
	                             //包含参数异常错误码15
	                             $("#userPwd2Msg").text("密码修改失败,请刷新后重试！");
	                         }
	                     }
	              });
			}
		}
		
	</script>
</head>
<body class="listh4">
		<ul class="loan_list">
			<li class="current"  style="display: block;">
				<h4 class="listh4">修改密码</h4>
				<form action="" method="post" id="myform">
					<div class="change_password">
						<input type="hidden" name="userPass" id="userPassHidden" /> 
						<input type="hidden" id="passWordHidden" name="passWord" />
						<div class="userPass form-item ">
							<label>原密码</label> 
							<input type="password" id="oldUserPwd" style="color: #b0b0b0;" 
							onblur="checkOldPwd()" 
							onkeyup="if(this.value.length>16){this.value=this.value.substr(0,16)};" 
							onpaste="return false"/>
						</div>
						<div class="info"><span id="oldUserPwdMsg"></span></div>
						<div class="passWord form-item">
							<label>新密码</label>
							<input type="password" class="pwdState" id="userPwd1_hidden" style="color: #b0b0b0;display:none" value="" 
							     onblur="checkPwd1()" 
							     onkeyup="if(this.value.length>16){this.value=this.value.substr(0,16)};"
							     onpaste="return false"/> 
							<input type="text" class="pwdState" id="userPwd1_text" style="color: #b0b0b0;" value="8-16位的字母数字混合组成" />
						</div>
						<div class="info">
						  <span id="userPwd1Msg" style="margin-right:130px"></span>
						  <i class="pwdLevel level1 nolevel">弱</i>
                          <i class="pwdLevel level2 nolevel">中</i>
                          <i class="pwdLevel level3 nolevel">强</i>
						</div>
						<div class="pass form-item">
							<label>确认密码</label>
							<input type="password" style="color: #b0b0b0;" id="userPwd2_hidden" 
							     onblur="checkPwd2()" 
							     onkeyup="if(this.value.length>16){this.value=this.value.substr(0,16)};" 
							     onpaste="return false"/>
						</div>
						<div class="info"><span id="userPwd2Msg"></span></div>
						<a onclick="changePwd();">提交</a>
					</div>
				</form>
			</li>
		</ul>
	<div class="mask_alpha" style="display:none;"></div>
	<div class="tc tc_password" style="display:none;">
	    <p>密码修改成功，请牢记新密码！</p>
	    <a class="sure" href="<%=basePath%>/user/forwardLogin" target="_self">确定</a>
	</div>
</body>
</html>