<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link rel="stylesheet" type="text/css"
    href="<%=basePath%>/styles/login/login.css" />
<link rel="stylesheet" type="text/css"
    href="<%=basePath%>/styles/order/order.css" />
<script type="text/javascript"
    src="<%=basePath%>/scripts/common/jquery.min.js"></script>
<script type="text/javascript" language="javascript"
    src="<%=basePath%>/scripts/common/jquery-1.11.1.js"></script>
<script type="text/javascript" language="javascript"
    src="<%=basePath%>/scripts/common/md5.js"></script>
<script type="text/javascript"
    src="<%=basePath%>/scripts/order/order.js"></script>
</head>
<body class="backgroud" onkeydown="keyLogin(event);">
    <iframe src="<%=basePath%>/basic/head" width="100%" height="74px"
        frameborder="0" scrolling="no"></iframe>
    <div class="register">
        <div class="wrap_629">
            <div class="content">
                <img src="<%=basePath%>/images/login/logintopbg.jpg" width="629"
                    height="26" />
                <div class="regist_info">今后您可以通过注册的用户名和密码登录百望融资服务平台，办理贷款申请、查询审批进度
                </div>
                <div class="choose" style="display: none">
                    <!-- 取消个人用户业务模块 -->
                    <label class="rediobox"><input type="radio" value="company"
                        name="regType_radio" id="regType01">企业或企业法人</label> <label
                        class="rediobox"><input type="radio" value="personal"
                        name="regType_radio" id="regType02">个人用户</label>
                    <div class="info">
                        <span id="selectMsg"></span>
                    </div>
                </div>
                <br />
                <form action="<%=basePath%>/user/toLogin" method="post" id="myform"
                    onsubmit="return submitchk()">
                    <div class=" form-item ">
                        <label>用户名</label> <input type="text" style="color: #b0b0b0;"
                            class="changeState" id="user_code" name="userName"
                            value="(6-20位的大小写字母、数字)" onblur="checkuser_Name();" /><span
                            style="display: none">(6-20位的大小写字母、数字)</span> <input
                            name="userPass" type="hidden" id="passHiddens"></input> <input
                            name="regType" type="hidden" id="typeHiddens"></input>
                    </div>
                    <div class="info">
                        <span id="user_name_msg"></span>
                    </div>
                    <div class="password form-item">
                        <label class="inputpwd">登录密码</label> <input type="password"
                            class="pwdState" style="color: #000; display: none"
                            id="user_pass" value="" onblur="regpassw()" /> <input type="text"
                            style="color: #b0b0b0;" id="user_pass_text" class="pwdState"
                            value="(6-20位的字母数字混合组成)" />
                    </div>
                    <div class="info">
                        <span id="password_msg"></span>
                    </div>
                    <div class="passwordAgain form-item pwdState">
                        <label class="inputpwd">确认密码</label> <input type="password"
                            class="pwdState" style="color: #000; display: none" value=""
                            id="PassWords" onblur="check_pass()" /> <input type="text"
                            style="color: #b0b0b0;" value="(请再次输入密码确认)" class="pwdState"
                            id="PassWords_text" />
                    </div>
                    <div class="info">
                        <span id="user_pass_msg"></span>
                    </div>
                    <div class=" form-item">
                        <label>邮箱</label> <input type="text" style="color: #b0b0b0;"
                            class="changeState" name="mail" id="mail" value="(找回密码时会使用该邮箱)"
                            onblur="reg_mail();" /> <span style="display: none">(找回密码时会使用该邮箱)</span>
                    </div>
                    <div class="info">
                        <span id="mail_msg"></span>
                    </div>
                    <div class=" form-item hasInviteCode">
                        <label>邀请码</label> <input type="text" style="color: #b0b0b0;"
                            class="changeState" id="inviteCodeId" value="(非必填)" name="inviteCode"
                            onblur="checkInvite();" /> <span style="display: none">(非必填)</span>
                    </div>
                    <div class="info">
                        <span id="invite_msg"></span>
                    </div>
                    <div class="yzm form-item">
                        <img src="<%=basePath%>/imageServlet" alt="验证码" title="点击更换"
                            id="code_image" /> <label>验证码</label> <input type="text"
                            class="changeState" name="code" id="code"
                            style="color: #b0b0b0; width: 160px;" value="(输入验证码)"
                            onblur="check_code();" /> <span style="display: none">(输入验证码)</span>
                    </div>
                    <div class="info">
                        <span id="code_msg"> </span><a onclick="change();">看不清，换一张</a>
                    </div>
                    <div class="login_button">
                        <input type="submit" value='注册'
                            style='background-color: #b88c52; height: 56px; line-height: 56px; border-radius: 3px; text-align: center; color: #ffffff; font-size: 24px; width: 350px;'>
                    </div>
                </form>
            </div>
        </div>
        <div class="footer">@百望股份有限公司 All rights reserved |
            京ICP备15045264号</div>
    </div>
</body>
<script>
        $(document).ready(function() {
        	var inviteId='${inviteId}';
        	if(inviteId){
        		$(".hasInviteCode").hide().next().hide();
        		$("#inviteCodeId").val(inviteId);
        	}
            //两个密码框状态转换
            $(".pwdState").on({
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
            //其它输入框状态转换
            $(".changeState").on({
                focus:function(){
                    $(this).css("color","black");
                    var defaultValue = $(this).next().text();
                    if($(this).val()==defaultValue){
                        $(this).val("");
                        $(this).select();
                    }
                },
                blur:function(){
                    $(this).css("color","#b0b0b0");
                    var defaultValue = $(this).next().text();
                    if($.trim($(this).val())==""){
                        $(this).val(defaultValue);
                    }
                }
            });
            //label点击热区设置
            $(document).delegate("#myform label","click",function(){
                if($(this).next().css("display")!="none"){
                    $(this).next().focus();
                }else{
                     $(this).next().next().css("display","none");
                     $(this).next().css("display","");
                     $(this).next().focus();
                }
            });
            //后台验证码验证结果返回接受（非主要验证结果，主要保护后台，防止频繁访问数据库）
            var flag = '<%=request.getAttribute("flag")%>';
            if(flag==3){
                var user = '<%=request.getAttribute("user")%>';
                user=eval("("+user+")");
                $("#code_msg").text("验证码输入错误！");
                $("#user_code").val(user.userName);
                $("#user_code").css("color","black");
                $("#mail").val(user.mail);
                $("#mail").css("color","black");
            }
        });
        function set_msg(id, msg) {
            $("#"+id).text(msg);
        }
        //刷新验证码
        function change() {
            $("#code_image").attr("src", "<%=basePath%>/imageServlet?date=" + new Date().getTime());
        }
        function checkuser_Name(){
            var user_name=$("#user_code").val();
            var reg_pass=/^[A-Za-z0-9]{6,20}$/;
            var reg_passnum=/^\d{11}$/;
            if(user_name == "(6-20位的大小写字母、数字)") {
                $("#user_name_msg").text("用户名不能为空");
                return;
            }else if(reg_passnum.test(user_name)){
                $("#user_name_msg").text("用户名不能输入11位的纯数字");
                return;
            }else if(!reg_pass.test(user_name)){
                $("#user_name_msg").text("请输入合法的用户名(6-20位的大小写字母，数字)");
                return;
            }else{
                $("#user_name_msg").text("");
            }
            $.post(
                "<%=basePath%>/user/checkUserName",
                $("#myform").serialize(),
                function(data) {
                    if(data.flag==5) {
                        $("#user_name_msg").text("用户名已注册！");
                    } else if(data.flag==1){
                        $("#user_name_msg").text("用户名含有非法字符,请重新输入！");
                    } else {
                        userNameOk=true;
                    }
                }
            );
        }
        var userNameOk=false;
        function checkUserName(){
             $.ajax({
                type:"POST",
                url:"<%=basePath%>/user/checkUserName",
                data:{userName:$("#user_code").val()},
                dataType:"json",
                async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
                cache:false,
                success: function(data){
                    if(data.flag==5) {
                        $("#user_name_msg").text("用户名已注册！");
                    } else if(data.flag==1){
                    	$("#user_name_msg").text("用户名含有非法字符,请重新输入！");
                    } else {
                        userNameOk=true;
                    }
                }
            }); 
        }
        function checkInvite(){
            var invite_uid=$.trim($("#inviteCodeId").val());
            var checkInviteCodeByReg=/^[A-Za-z]{6}$/.test(invite_uid);
            if(invite_uid!="(非必填)" && invite_uid!="" && !checkInviteCodeByReg){
            	$("#invite_msg").text("请输入正确的邀请码格式,或者请置空不输入！");
            	return false;
            }else{
            	if(checkInviteCodeByReg){
            		var result=true;
                    $.ajax({
                        type:"POST",
                        url:"<%=basePath%>/user/checkInviteCode",
                        data:{"inviteCode":invite_uid},
                        async:false,
                        success: function(data){
                            if(data.flag!=0) {
                                $("#invite_msg").text("邀请码不正确,请输入正确邀请码或者请置空不输入！");
                                result=false;
                            } else {
                                $("#invite_msg").text("");
                            }
                        }
                    });
                    return result;
            	}else{
            		$("#invite_msg").text("");
            		return true;
            	}
            }
        }
        function regpassw(){
            var user_pass=$("#user_pass").val();
            var reg_pass=/^[A-Za-z0-9]{6,20}$/;
            var regpassnum=/^\d{6,}$/;
            var regpasschar=/^[A-Za-z]{6,}$/;
            if(user_pass=="(6-20位的字母数字混合组成)") {
                $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            }else if(!reg_pass.test(user_pass)){
                $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            }else if(regpassnum.test(user_pass)){
                $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            }else if(regpasschar.test(user_pass)){
                $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            }else{
                $("#password_msg").text("");
            }
        }
        function reg_mail(){
            var reg_mail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            var mail=$("#mail").val();
            if(mail=="(找回密码时会使用该邮箱)"){
                $("#mail_msg").text("请输入合法的邮箱");
            }else if(!reg_mail.test(mail)){
                $("#mail_msg").text("请输入合法的邮箱");    
            }else if(mail.length>30){
                $("#mail_msg").text("邮箱过长,请输入合法的邮箱");    
            }else{
                $("#mail_msg").text("");    
            }
        }
        mailOk=false;
        function checkMail(){
            var mail=$("#mail").val();
            $.ajax({
                type:"POST",
                url:"<%=basePath%>/user/checkMail",
                data:{mail:$("#mail").val()},
                dataType:"json",
                async:false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
                cache:false,
                success: function(data){
                    if(data.flag==8) {
                        $("#mail_msg").text("邮箱已注册");
                    } else {
                        $("#mail_msg").text("");
                        mailOk=true;
                    }
                }
            });
        }
        function check_pass(){
            var user_pass=$("#user_pass").val();        
            var password=$("#PassWords").val(); 
            if(""==password){
                $("#user_pass_msg").text("确认密码不能为空");
            }else if(password.length>21){
                $("#user_pass_msg").text("请输入正确的密码格式(6-20位的字母数字混合组成)");
            }else if(user_pass!=password){
                $("#user_pass_msg").text("两次输入的密码不一致，请重新输入");
            }else{
                $("#user_pass_msg").text("");
            }
        }
        function check_code(){
            var code = $("#code").val();
            if(code == "(输入验证码)") {
                $("#code_msg").text("验证码不能为空");
                return;
            }else if(code.length!=4){
                $("#code_msg").text("请输入合法的验证码");
            }else{
                $("#code_msg").text("");
            }
        }
        var codeOk=false;
        function checkCodes(){
            $.ajax({
                type:"POST",
                url:"<%=basePath%>/user/checkCode",
            data : {
                code : $("#code").val()
            },
            dataType : "json",
            async : false,//是否异步请求，如果是异步，那么不会等服务器返回，我们这个函数就向下运行了。
            cache : false,
            success : function(data) {
                if (data.flag == 3) {
                    $("#code_msg").text("验证码错误!");
                } else {
                    codeOk = true;
                }
            }
        });
    }
    /* function checkUsersType(){  //取消个人用户业务模块
        var list=$(".rediobox.sel input").val();
        if("company"==list){
            $("#typeHiddens").val("company");
        }else if("personal"==list){
            $("#typeHiddens").val("personal");
        }
    } */
    function submitchk() {
        var user_pass = $("#user_pass").val();
        var password = $("#PassWords").val();
        var user_name = $("#user_code").val();
        var mail = $("#mail").val();
        var code = $("#code").val();
        var regpassnum = /^\d{6,}$/;
        var regpasschar = /^[A-Za-z]{6,}$/;
        var reg_userandpass = /^[A-Za-z0-9]{6,20}$/;
        var reg_phone = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
        var reg_mail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        var invite_uid = $("#inviteCodeId").val();
        var list = $(".rediobox.sel input").val();
        /* if (null == list) { //取消个人用户业务模块
             $("#selectMsg").text("× 请慎重选择用户身份");
             return false; 
        } else */ if (user_name == "(6-20位的大小写字母、数字)") {
            $("#user_name_msg").text("请输入用户名");
            return false;
        } else if ("(6-20位的字母数字混合组成)" == user_pass) {
            $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            return false;
        }
        if (password == "") {
            $("#user_pass_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            return false;
        } else if (user_pass != password) {
            $("#user_pass_msg").text("两次输入的密码不一致，请重新输入");
            return false;
        } else if (!reg_userandpass.test(user_pass)) {
            $("#user_pass_msg").text("请输入正确的密码格式(6-20位的字母数字混合组成)");
            return false;
        } else if (regpassnum.test(user_pass)) {
            $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            return false;
        } else if (regpasschar.test(user_pass)) {
            $("#password_msg").text("请输入合法的密码(6-20位的字母数字混合组成)");
            return false;
        } else if (!reg_userandpass.test(user_name)) {
            $("#user_name_msg").text("请输入正确的用户名(6-20位的字母数字混合组成)");
            return false;
        } else if (user_pass == "(6-20位的字母数字混合)") {
            $("#user_pass_msg").text("请输入密码");
            return false;
        } else if (mail == "(找回密码时会使用该邮箱)") {
            $("#mail_msg").text("请输入邮箱");
            return false;
        } else if (mail.length > 30) {
            $("#mail_msg").text("邮箱过长,请输入合法的邮箱");
            return false;
        } else if (!reg_mail.test(mail)) {
            $("#mail_msg").text("请输入正确的邮箱格式");
            return false;
        } else if (code == "(输入验证码)") {
            $("#code_msg").text("请输入验证码");
            return false;
        } else {
            if ("(非必填)" == invite_uid) {
                $("#inviteCodeId").val("");
            }
            //如果邀请码输入框存在,但输入不正确则返回
            if("block"==$(".hasInviteCode").css("display")&&!checkInvite()){
            	return false;
            }
            var password = $("#user_pass").val();
            var hash = hex_md5(password);
            checkUserName();
            checkCodes();
            checkMail();
            //checkUsersType();  //取消个人用户业务模块
            var user_name = $("#user_code").val();
            $("#user_code").val(user_name.toLowerCase());
            $("#passHiddens").val(hash);
            //alert(codeOk+"   "+userNameOk+"  "+mailOk)
            if (codeOk && userNameOk && mailOk) {
                return true;
            } else {
                return false;
            }
        }
    }
    function keyLogin(et) {
        if (et.keyCode == 13) {
            submitchk();
        } else if (et.which == 13) {
            submitchk();
        }
    }
    /*取消个人用户业务模块*/
    /*      function checkUserType(){
     var list=$(".rediobox.sel input").val();
     if(null==list){
     $("#selectMsg").text("× 请慎重选择用户身份且提交后不可更改");
     }else{
     $("#selectMsg").text("");
     }
     } */
</script>
</html>
