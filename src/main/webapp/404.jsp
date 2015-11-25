<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" /> -->
<title>找不到页面</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
<script src="<%=basePath %>/scripts/common/common.js"></script>
<script type="text/javascript"> 
     if(top!=self){
          if(top.location != self.location)
               top.location="<%=basePath %>/404"; 
     }
</script>
</head>
<body class="bg404">
	<div class="header_wrap">
		<div class="header clearfix">
	    	<a href="<%=basePath %>/" target="_top"><img src="<%=basePath %>/images/common/logo.jpg" alt="" class="logo"/></a>
	    </div>
	</div>
	<div class="con_404 center">
		<div class="con_404in">
	    	<h2 class="ft30 pb10">抱歉，你的页面被小偷偷走了...</h2>
	        <p class="pb40">您访问的页面不存在或者已过期</p>
	        <p class="pb15">别担心，你可以点击链接继续浏览：</p>
	        <p><a href="<%=basePath %>/">返回首页>></a></p>
	    </div>
	</div>
	<div class="foot"><span>@百望股份有限公司 All rights reserved</span> | <span>京ICP备15045264号</span></div>
</body>
</html>
