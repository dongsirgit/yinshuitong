<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>找不到页面</title>
<script type="text/javascript"> 
     if(top!=self){
          if(top.location != self.location)
               top.location="<%=basePath %>/500"; 
     }
</script>

<!--[if IE 6]>
<script src="./public/js/png.js"></script>
<script>DD_belatedPNG.fix('*')</script>
<![endif]-->

</head>
<body>

<div id="wrap">
	<div>
		<b>错误页面设计中.....请等待...</b><br/><br/>
		<table><tr><td style='margin:5px;padding:5px 5px 5px 5px;border:1px solid #e70112;'>
		User-Agent：<%=request.getHeader("User-Agent") %><br/>
		ErrorRequest：<%=request.getRequestURL() %><br/>
		By：<%=com.baiwang.banktax.utils.IPUtil.getIpAddr(request) %><br/>
		ThroughPort：<%=request.getRemotePort() %><br/>
		RequestMethod：<%=request.getMethod() %>		
		</td></tr></table>
		...has been logged.<br/>
		<br/>
	</div>
	<div id="text">
		<strong>
			<span></span>
		　　　<a href="/">返回首页</a>
		　　　<a href="javascript:history.back(-1)">返回上一页</a>
		</strong>
	</div>
</div>

<div class="animate below"></div>
<div class="animate above"></div>
<div style="text-align:center;">
</div>
</body>
</html>
