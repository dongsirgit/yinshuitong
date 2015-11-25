<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.utils.ConfigUtil "%>
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
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/order/order.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/user/formVerifier.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/common/md5.js"></script>
	<script type="text/javascript">
	    if (window.self == window.top) {
	        window.top.location ="<%=basePath%>/users/init/userInfo";
	    }
	</script>
	<script type="text/javascript">
    $(document).ready(function(){
    	$(".pwd").on({
    		//密码框状态转换
            focus:function(){
                if($(this).attr("type")=="text"){
                    $(this).css("display","none");
                    $(this).prev().css("display","");
                    $(this).prev().css("color","black");
                    $(this).prev().focus();
                }else{
                    $(this).css("color","black");
                }
            },
            blur:function(){
                if($.trim($(this).val())==""){
                    $(this).val("");
                    $(this).css("display","none");
                    $(this).next().css("display","");
                }else{
                    $(this).css("color","#b0b0b0");
                }
            }
    	});
        //label热区点击设置
        $(document).delegate("#myform label","click",function(){
            if($(this).next().css("display")!="none"){
                $(this).next().focus();
            }else{
                 $(this).next().next().css("display","none");
                 $(this).next().css("display","");
                 $(this).next().focus();
            }
        });
    });
	function check_UserPass(){
		var userPass=$("#userPass").val();
		var reg_pass=/^[A-Za-z0-9]{6,21}$/;
		if(""==userPass){
			$("#userPass_info").text("请输入原密码");
		}else if(!reg_pass.test(userPass)){
			$("#userPass_info").text("请输入合法的密码(6-20位的字母数字混合组成)");
		}else{
			$("#userPass_info").text("");
		}
	}
	function check_PassAgain(){
		var userPassOld=$("#userPass").val();
		var pass=$("#pass").val();//确认密码
		var userPass=$("#passWord").val();//新密码
		if(""==pass){
			$("#pass_info").text("请再次输入新密码");
		}else if(pass!=userPass){
			$("#pass_info").text("两次密码不一样，请重新输入");
		}else if(pass==userPassOld){
            $("#pass_info").text("原密码与新密码一致，请重新输入新密码");
        }else{
			$("#pass_info").text("");
		}
	}
	function check_PassWord(){
		var userPassOld=$("#userPass").val();
		var userPass=$("#passWord").val();
		var regpassnum=/^\d{6,}$/;
		var regpasschar=/^[A-Za-z]{6,}$/;
		var reg_pass=/^[A-Za-z0-9]{6,21}$/;
		if(!reg_pass.test(userPass)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的字母数字混合组成)");
		}else if("(6-20位的字母数字混合组成)"==userPass){
			$("#passWord_info").text("密码不能为空");
		}else if(regpassnum.test(userPass)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的字母数字混合组成)");
			return false;
		}else if(regpasschar.test(userPass)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的字母数字混合组成)");
			return false;
		}else if(userPass==userPassOld){
            $("#passWord_info").text("原密码与新密码一致，请重新输入新密码");
        }else {
			$("#passWord_info").text("");
		}
	}
	function check_all(){
		var userPass=$("#userPass").val();
		var reg_pass=/^[A-Za-z0-9]{6,21}$/;
		var passWord=$("#passWord").val();
		var regpassnum=/^\d{6,}$/;
		var regpasschar=/^[A-Za-z]{6,}$/;
		var pass=$("#pass").val();
		if(!reg_pass.test(userPass)){
			$("#userPass_info").text("请输入合法的密码(6-20位的字母数字混合组成)");
		}else if(""==userPass){
			$("#userPass_info").text("请输入原密码");
		}else if(!reg_pass.test(passWord)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的字母数字混合组成)");
		}else if(regpassnum.test(passWord)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的字母数字混合组成)");
		}else if(regpasschar.test(passWord)){
			$("#passWord_info").text("请输入合法的新密码(6-20位的字母数字混合组成)");
		}else if("(6-20位的字母数字混合组成)"==passWord){
			$("#passWord_info").text("请输入新密码");
		}else if(pass!=passWord){
			$("#pass_info").text("×两次输入的密码不一致，请重新输入");
		}else if(userPass==passWord){
            $("#pass_info").text("原密码与新密码一致，请重新输入新密码");
        }else{
			var userPass=$("#userPass").val();
			var hash = hex_md5(userPass);
			$("#userPassHidden").val(hash);
			var password=$("#passWord").val();
			var hashs = hex_md5(password);
			$("#passWordHidden").val(hashs);
			$.ajax({
	     	        type:"POST",
	     	        url:"<%=basePath %>/users/modifyPass",
	     	        data:{userPass:$("#userPassHidden").val(),passWord:$("#passWordHidden").val()},
	     	        dataType:"json",
	     	        async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
	     			cache:false,
	     	        success: function(data){
	     	        	if(data.flag==2) {
	       					$("#userPass_info").text("原密码输入错误，请确认后重新输入");
	       					$("#userPass").val(userPass);
	       					$("#passWord").val(password);
	       				} else {
	       					$("#userPass").val("");
	       					//$("#passWord").attr("type","text");
	       					$("#passWord").val("");
	       					$("#pass").val("");
	       					//alert("成功");
	       					$(".tc_password").show(200);
	       					$(".mask_alpha").show();
	       				}
	     	         }
	     	 	});	
			}
		}
	function sureForward(){
		//location.href="<%=basePath%>/users/forwardLogin";
		window.parent.location.href="<%=basePath%>/users/forwardLogin";
	}
	$(document).ready(function(){
		$("form label").click(function(){
			$(this).next().focus();
		});
	});
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
							<input type="password" id="userPass" style="color: #b0b0b0;" onblur="check_UserPass()" />
						</div>
						<div class="info">
							<span id="userPass_info"></span>
						</div>
						<div class="passWord form-item">
							<label>新密码</label>
							<input type="password" id="passWord" style="color: #b0b0b0;display:none" value="" class="pwd" onblur="check_PassWord()" /> 
							<input type="text" id="passWord_text" style="color: #b0b0b0;" value="(6-20位的字母数字混合组成)" class="pwd" />
						</div>
						<div class="info">
							<span id="passWord_info"></span>
						</div>
						<div class="pass form-item">
							<label>确认密码</label> 
							<input type="password" style="color: #b0b0b0;" id="pass" onblur="check_PassAgain()" />
						</div>
						<div class="info">
							<span id="pass_info"></span>
						</div>
						<a onclick="check_all();return null;">提交</a>
					</div>
				</form>
			</li>
		</ul>
	<div class="mask_alpha" style="display:none;"></div>
	<div class="tc tc_password" style="display:none;">
	    <p>密码修改成功，请牢记新密码！</p>
	    <a class="sure" onclick="sureForward();" target="_top">确定</a>
	</div>
</body>
</html>