<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%
    String basePath = request.getContextPath();
%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/styles/login/login.css"/>
<link rel="stylesheet" href="<%=basePath %>/styles/common/base.css" type="text/css">
<script type="text/javascript" language="javascript" src="<%=basePath %>/scripts/common/md5.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/scripts/common/jquery-1.11.1.js"></script>
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
<script type="text/javascript"> 
     if(top!=self){
          if(top.location != self.location)
               top.location=self.location; 
     }
</script>
<script type="text/javascript">

    $(document).ready(function() {
        //密码框状态转换
        $(".pwd").on({
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
        //用户名输入框状态转换
        var defaultUserName = "(请输入用户名)";
        $("#user_name").on({
            focus:function(){
                $(this).css("color","black");
                if($(this).val()==defaultUserName){
                    $(this).val("");
                    $(this).select();
                }
            },
            blur:function(){
                $(this).css("color","#b0b0b0");
                if($.trim($(this).val())==""){
                    $(this).val(defaultUserName);
                }
                checkName();
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
    function submitchk(){
    	if(!checkName()||!checkPwd()) return;
        $.post(
            "<%=basePath %>/user/loginJudge",
            $("#myform").serialize(),
            function(data) {
                if(data.flag==1) {
                    //账号错误
                    $("#user_pass_msg").text("用户名或密码输入错误!");
                } else  {
                    location.href="<%=basePath %>/users/loginSuccess";
                }
            }
        );
    }
    function checkName(){
        var user_name = $("#user_name").val();
        if(user_name.indexOf("'")>=0||user_name.indexOf("\"")>=0){
            $("#user_pass_msg").text("含有非法字符,请重新输入!");
            return false;
        }else if(user_name == "(请输入用户名)") {
            $("#user_pass_msg").text("请输入正确用户名!");
            return false;
        }else{
            $("#user_pass_msg").text("");
            return true;
        }
    }
    function checkPwd(){
    	 var user_pass = $("#user_pass").val();
         if(user_pass == "") {
             $("#user_pass_msg").text("请输入密码!");
             return false;
         }else{
             $("#user_pass_msg").text("");
             var hash = hex_md5(user_pass);
             $("#userPassHash").val(hash);
             return true;
         }
    }
    function keyLogin(et){
        if(et.keyCode == 13 || et.which == 13){
            submitchk();
        }
    }
</script>
</head>
<body class="backgroud" onkeydown="keyLogin(event);">
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div>
  <div class="wrap_629">
    <div class="content">
    <img src="<%=basePath %>/images/login/logintopbg.jpg" width="629" height="26" />
      <form action="<%=basePath %>/users/loginSuccess" method="post" id="myform" >
        <div class="username form-item">
          <label>用户名</label>
          <input type="text" style="color:#b0b0b0;" name="userName" id="user_name" value="(请输入用户名)" />
          <input name="userPass" id="userPassHash" type="hidden" ></input>
        </div>
        <span id="user_name_msg" style="color:red;"></span>
        <div class="password form-item">
          <label>密码</label>
        <input type="password"  id="user_pass" style="color:#000;display:none" value="" class="pwd"/>
        <input type="text"  id="user_pass_text" style="color:#b0b0b0;" value="(请输入密码)" class="pwd"/>
        </div>
        <div class="info"><span  id="user_pass_msg"><c:if test="${null!=notePass}">${notePass }</c:if></span><a href="<%=basePath %>/user/findPwd">忘记密码？</a></div>
        <div class="login_button"><a onclick="submitchk()">登录</a></div>
      </form>
    </div>
  </div>
  <div class="footer">@百望股份有限公司 All rights reserved   |  京ICP备15045264号</div>
</div>
</body>
</html>