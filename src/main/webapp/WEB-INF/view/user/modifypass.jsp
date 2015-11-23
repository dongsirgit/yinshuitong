<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>贷款列表</title>
<link href="<%=basePath%>/styles/common/base.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>/styles/help/help.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/styles/index/index.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/styles/login/login.css">
<link href="<%=basePath%>/styles/order/order.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="<%=basePath%>/scripts/common/jquery-1.11.1.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=basePath%>/scripts/common/md5.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//确认提交
		$("#btn_submit_confirm").click(function(){
			var id = $("#save_id4apl").val();
			var params = {"id":id};
			$.ajax({
				type:"POST",
		        url: "<%=basePath%>/users/applyLoan/submit?id="+id,
		        data:params,
		        success: function (data) {
		            if (data.status == "0") {
		               alert("请登陆...");
		            }else if (data.status == "1"){
		            	if(data.success=="1"){
		            		location.href='<%=basePath%>/users/applyLoan/up';
		            	}else{
		            		alert("提交失败,请重新提交!");
		            	}
		            }
		        },
		        error:function(e) {
		        	alert("提交失败,请重新提交!");
		        }
		    })
		})
		//确认删除
		$("#btn_del_confirm").click(function(){
			var id = $("#save_id4apl").val();
			var params = {"id":id};
			$.ajax({
				type:"POST",
		        url: "<%=basePath%>/users/applyLoan/deleteApplyLoan?id="+id,
		        data:params,
		        success: function (data) {
		            if (data.status == "0") {
		               alert("请登陆...");
		            }else if (data.status == "1"){
		            	if(data.success=="1"){
		            		location.href='<%=basePath%>/users/init/userInfo';
		            	}else{
		            		alert("删除失败,请重新操作!");
		            	}
		            }
		        },
		        error:function(e) {
		        	alert("删除失败,请重新操作!");
		        }
		    })
		})
	})
	
	//为提交操作准备id值
	function smt_applyLoan(id){
		$("#save_id4apl").val(id);
		$("#submit_check").show(200);
	}
	//为删除操作准备id值
	function del_applyLoan(id){
		$("#save_id4apl").val(id);
		$("#del_check").show(200);
	}
	
	function check_UserPass(){
		var userPass=$("#userPass").val();
		var reg_pass=/^[A-Za-z0-9]{6,21}$/;
		if(!reg_pass.test(userPass)){
			$("#userPass_info").text("请输入合法的密码(6-20位的大小写字母与数字混合)");
		}else if(""==userPass){
			$("#userPass_info").text("密码不能为空");
		}else{
			$("#userPass_info").text("");
		}
	}
	function check_PassWord(){
		var userPass=$("#passWord").val();
		var reg_pass=/^[A-Za-z0-9]{6,21}$/;
		if(!reg_pass.test(userPass)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的大小写字母与数字混合)");
		}else if("(6-20位的大小写字母、数字)"==userPass){
			$("#passWord_info").text("密码不能为空");
		}else {
			$("#passWord_info").text("");
		}
	}
	function check_PassAgain(){
		
	}
	function check_all(){
		var userPass=$("#userPass").val();
		var reg_pass=/^[A-Za-z0-9]{6,21}$/;
		var passWord=$("#passWord").val();
		var pass=$("#pass").val();
		if(!reg_pass.test(userPass)){
			$("#userPass_info").text("请输入合法的密码(6-20位的大小写字母与数字混合)");
		}else if(""==userPass){
			$("#userPass_info").text("请输入原密码");
		}else if(!reg_pass.test(passWord)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的大小写字母与数字混合)");
		}else if("(6-20位的大小写字母、数字)"==passWord){
			$("#passWord_info").text("请输入新密码");
		}else if(pass!=passWord){
			$("#pass_info").text("×两次输入的确认密码不一致，请重新输入");
		}else {
			var password=$("#userPass").val();
			var hash = hex_md5(password);
			$("#userPass").val(hash);
			var pass=$("#passWord").val();
			var has = hex_md5(pass);
			$("#passWord").val(has);
			$.post(
			"<%=basePath%>/users/modifyPass",
   			$("#myform").serialize(),
   			function(data) {
   				if(data.flag==2) {
   					$("#userPass_info").text("原密码输入错误，请确认后重新输入");
   					$("#userPass").val(password);
   					$("#passWord").val(pass);
   				} else {
   					$("#userPass").val("");
   					$("#passWord").attr("type","text");
   					$("#passWord").val("(6-20位的大小写字母、数字)");
   					$("#pass").val("");
   					alert("修改密码成功");
   				}
   			});
			 $.ajax({
	     	        type:"POST",
	     	        url:"<%=basePath%>/users/modifyPass",
				data : {
					userName : $("#user_code").val()
				},
				dataType : "json",
				async : false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
				cache : false,
				success : function(data) {
					if (data.flag == 2) {
						$("#userPass_info").text("原密码输入错误，请确认后重新输入");
						$("#userPass").val(password);
						$("#passWord").val(pass);
					} else {
						$("#userPass").val("");
						$("#passWord").attr("type", "text");
						$("#passWord").val("(6-20位的大小写字母、数字)");
						$("#pass").val("");
						alert("修改密码成功");
					}
				}
			});
		}
		return;
	}

	function changepass() {
		var password = $("#passWord").val();
		if ("(6-20位的大小写字母、数字)" == password || "" == password) {
			var pass = $("#passWord").attr("type", "text");
		} else {
			var pass = $("#passWord").attr("type", "password");
		}
	}
</script>
</head>
<body>
	<iframe src="<%=basePath%>/basic/head" width="100%" height="75px"
		frameborder="0" scrolling="no"></iframe>
	<div class="content center clearfix act_list_nobg">
		<div class="content_l">
			<h3>我的银税通</h3>
			<ul>
				<li><a href="<%=basePath%>/users/init/userInfo">贷款列表</a></li>
				<li class="current">修改密码</li>
			</ul>
		</div>
		<div class="content_r">
			<ul class="loan_list">
				<li>
					<h4>贷款列表</h4>
				</li>
				<li class="current">
					<h4>修改密码</h4> <%-- <%=basePath %>/users/modifyPass --%>
					<form action="" method="post" id="myform">
						<div class="change_password">
							<div class=" form-item ">
								<label>原密码</label> <input type="password" name="userPass"
									id="userPass" style="color: #b0b0b0;"
									onblur="check_UserPass();" />
							</div>
							<div class="info">
								<span id="userPass_info"></span>
							</div>
							<div class=" form-item">
								<label>新密码</label> <input type="text" name="passWord"
									id="passWord" style="color: #b0b0b0;" value="(6-20位的大小写字母、数字)"
									onblur="check_PassWord();changepass();" />
							</div>
							<div class="info">
								<span id="passWord_info"></span>
							</div>
							<div class=" form-item">
								<label>确认密码</label> <input type="password"
									style="color: #b0b0b0;" id="pass" onblur="check_PassAgain();" />
							</div>
							<div class="info">
								<span id="pass_info"></span>
							</div>
							<a onclick="check_all();return null;">提交</a>
						</div>
					</form>
				</li>
			</ul>
		</div>
	</div>
	<!-- <div class="mask_alpha"></div> -->
	<!-- <div class="fdiv" id="submit_check11" style="display:none;"> -->
	<!-- 	<p>您确定正式提交申请贷款吗？</p> -->
	<!--     <div><a class="fdivbtn1" id="btn_submit_confirm11" name="btn_submit_confirm">确定</a><a class="fdivbtn2">取消</a></div> -->
	<!-- </div> -->
	<!-- <div class="fdiv" id="del_check" style="display:none;"> -->
	<!-- 	<p>您确定删除这条贷款申请吗？</p> -->
	<!--     <div><a class="fdivbtn1" id="btn_del_confirm11" name="btn_del_confirm">确定</a><a class="fdivbtn2">取消</a></div> -->
	<!-- </div> -->

	<input id="save_id4apl" type="hidden" value="" />
	<!--底部-->
	<%@include file="../base/footer.html"%>
	<!---->
	<script type="text/javascript">
		$(function() {
			//切换
			$('.content_l ul li').click(
					function(e) {
						var index = $(this).index();
						$(this).addClass('current').siblings().removeClass('current');
						$('.content_r ul li').eq(index).css('display', 'block').siblings().css('display', 'none');
					});
			//表格隔行有背景色
			$('.loan_list table tr:odd').css('background', '#fffceb')
			//表单
			$('input:eq(1)').focus(function(e) {
				if ($(this).val() == '(6-20位的大小写字母、数字)') {
					$(this).val('');
				}
			});

			$('input:eq(1)').blur(function(e) {
				if ($(this).val() == '') {
					$(this).val('(6-20位的大小写字母、数字)')
				}
			});
			$(".tc").hide();
			$(".mask_alpha,.fdiv").hide();
			$(".fdivbtn2").click(function() {
				$(".mask_alpha,.fdiv").hide(200);
			});
			$(".change_password a").click(function() {
				$(".tc").show(200);
				$(".mask_alpha").show();
			});
			$(".tc a").click(function() {
				$(".mask_alpha,.tc").hide(200);
			});
		})
	</script>
</body>
</html>
