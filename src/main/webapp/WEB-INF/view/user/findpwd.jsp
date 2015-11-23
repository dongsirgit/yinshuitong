<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>忘记密码</title>
<link href="<%=basePath%>/styles/common/base.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>/styles/login/findpassword.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"
	src="<%=basePath%>/scripts/common/jquery-1.11.1.js"></script>
<script src="<%=basePath%>/scripts/common/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('input:eq(0)').focus(function(e) {
		if( $(this).val()== '用户名' ){
			$(this).val('')
		}
    });
	
	$('input:eq(0)').blur(function(e) {
		if( $(this).val()=='' ){
			$(this).val('用户名')
		}
    });
	
	$('input:eq(1)').focus(function(e) {
		if( $(this).val()== '邮箱' ){
			$(this).val('')
		}
    });
	
	$('input:eq(1)').blur(function(e) {
		if( $(this).val()=='' ){
			$(this).val('邮箱')
		}
    });
	
})

function check_name(){
	var username=$("#username").val();
	if(username==""){
		$("#name_msg").text("× 请输入合法的用户名");
		return;
	}else{
		$("#name_msg").text("");
	}
	
}
function check_mail(){
	var mail=$("#mail").val();
	if(mail=="" ||!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(mail)){
		$("#mail_msg").text("× 请输入合法的邮箱");
		return;
	}else{
		$("#mail_msg").text("");
	}
}
function checkall(){
	var username=$("#username").val();
	var mail=$("#mail").val();
	if(username == "用户名") {
		$("#name_msg").text("请输入用户名");
		return;
	}
	if(mail == "邮箱") {
		$("#mail_msg").text("请输入邮箱.");
		return;
	}
	$.post(
		"<%=basePath%>/retrieve/sentMail",
		$("#myform").serialize(),
		function(data) {
			if(data.flag==1) {
				//没有该账户
				$("#mail_msg").text("用户名与邮箱不匹配,请重新输入！");
			} else if(data.flag==10) {
				//邮箱服务器错误
				$("#mail_msg").text("邮箱服务器繁忙,请稍后再试！");
			} else {
				//成功
				$("#postMail input").val(mail);
				$("#postMail").submit();
			}
		});
	}
</script>
</head>
<body>
	<iframe src="<%=basePath%>/basic/head" width="100%" height="74px"
		frameborder="0" scrolling="no"></iframe>
	<form method="post" action="" id="myform">
		<div class="forget">
			<div class="forget_con">
				<h3>找回密码</h3>
				<div class="hg">
					<input type="text" value="用户名" name="userName" id="username"
						onblur="check_name();" />
					<p class="prompt" id="name_msg"></p>
				</div>
				<div class="hg">
					<input type="text" value="邮箱" name="mail" id="mail"
						onblur="check_mail();" />
					<p class="prompt" id="mail_msg"></p>
				</div>
				<a onclick="checkall();" class="sub_btn">提交</a>
				<p class="text">用户名和注册邮箱通过校验一致后,发送新密码到注册邮箱</p>
			</div>
		</div>
	</form>
	<form action="<%=basePath%>/retrieve/forwardSentSuccess" id="postMail" method="post" style="display:none" >
	   <input name="mail"/>
	</form>
	<%@include file="../base/footer.html"%>

</body>
</html>
