<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="<%=basePath %>/scripts/common/jquery-1.11.1.js"></script>
<script type="text/javascript">

function random() {
  var num = "";
  for (i = 0; i < 6; i++) {
      num = num + Math.floor(Math.random() * 10);
  }
  return num;
}
function RemainTime() {
  var iSecond;
  var sSecond = "", sTime = "";
  if (iTime >= 0) {
      iSecond = parseInt(iTime % 300);
      if (iSecond >= 0) {
          sSecond = iTime + "秒";
      }
      sTime = "<span style='color:darkorange;font-size:13px;'>" + sSecond + "</span>";
      if (iTime == 0) {
          clearTimeout(Account);
          sTime = "<span style='color:red;font-size:12px;'>验证码已过期</span>";
      }
      else {
          Account = setTimeout("RemainTime()", 1000);
      }
      iTime = iTime - 1;
  }
  $("#endtime").html(sTime);
} 

function getCode(){
	var num=$("#phone").val();
	alert(num);
	$.ajax({
	    type: "get",
	    url: "sendCode?mobile=" + num,
	    success: function(result) {
	        if (result.returnstatus == "Success") {
	           alert("验证码已发送至您输入的手机号！有效期5分钟");
	        }
	        else if(result.returnstatus == "Faild") {
	            alert(result.message);
	        }
	        else
	        {
	        	alert("？？？");	
	        }
	     },
	     error: function() { alert("error"); }
	});

}
</script>
</head>
<body>
	<input type="text" id="phone">
	<input type="button" id="getCode" value="获取验证码" onclick="getCode()"/>
</body>
</html>