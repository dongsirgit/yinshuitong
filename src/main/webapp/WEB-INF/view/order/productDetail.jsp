<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getContextPath();
    User user = (User) session.getAttribute(ConfigUtil.getLoginedUserStr());
    int loginflag = 0;
    if(null != user){
    	loginflag = 1;
    }
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品详情</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    
    <style type="text/css">
   		.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
    	.productt{ padding-bottom:90px; margin-bottom:60px;}
    	
    </style>
    
    <script type="text/javascript">
    	var loginflag = <%=loginflag%>;
	    <%-- $.ajax({
			type:"POST",
			url:"<%=basePath %>/product/getDetail",
			data:{id:1},
			//asysn:true,
			dataType:"JSON",
			//dataType:"text",
			success:function(data){
				/* alert(JSON.stringify(data))
				alert(data.detail.sbtj) */
				$('#sbtj').text(data.detail.sbtj)
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert("加载失败!");
	        }
		}); --%>
	
		function apply(id){
			if(loginflag==1){//登陆成功
				//if(){}
				
			}else{
				$(".mask_alpha").show();
				$("#div_login").show();
				//alert("登陆...")
				//location.href = '';
			}
		}
		$(document).ready(function(){
			$(".fdivbtn2").click(function(){
				$(".mask_alpha,#div_login").hide(200);
			});
		})
        
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	
	<c:if test="${detail != null}">
    	<div class="mainn">
	    	
	    	<table id='protectList' border="1" bordercolor="#afaeac" width="1000px;" height="100%"><!--  rules="rows" -->
	    		<thead height="60px;" style="border-bottom:hidden; "><td align="center" style="padding-top: 30px;">
	    			<div style="width: 950px; text-align: left;" >
		    			<a href="<%=basePath %>/"><span style="font-size:16px; ">首页</span></a>>
		    			<a href="<%=basePath %>/product/list"><span style="font-size:13px;">贷款产品列表</span></a>>
		    			<span style="font-size:13px;">产品详情</span>
		    			<hr width="950px;"/>
		    	</td><thead>
		    	<tr height="60px;"><td  style="padding-left:20px;padding-right:20px; vertical-align: middle;">
		    			<img alt="" src="<%=basePath %>/images/product/${detail.logoUrl}">
		    			<span style="font-size:16px; font-style: inherit;">&nbsp;&nbsp;&nbsp;&nbsp;${detail.pname}</span>
		    			<div style="float: right;">
		    				<br/>
			    			<button onclick="apply(${detail.id})">立即申请</button>
		    			</div>
	    			</div>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">开通地区</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.ktdq}</pre>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">产品说明</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.cpsm}</pre>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">产品优势/特色</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.cpys}</pre>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">贷款对象</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.dkdx}</pre>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">申办条件</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.sbtj}</pre>
	    			<!-- <textarea rows="5" cols="100" id='sbtj' name='sbtj'></textarea><br> -->
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">申办材料</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.sbcl}</pre>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">申办流程</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<pre>${detail.sblc}</pre>
	    		</td></tr>
	    		<tr><td style="height: 40px;padding-left:20px;padding-right:20px; background-color: #CCCCCC">
	    			<span style="font-weight:bold;">贷款费率</span>
	    		</td></tr>
	    		<tr><td style="height:30px; padding-left:20px;padding-right:20px; padding-top: 10px; padding-bottom: 10px;">
	    			<div style="height: 50px;">
		    			<pre>${detail.dkfl}</pre>
	    			</div>
	    			<hr/>
	    			<div style="width: 950px; height:100px; text-align:center;" >
	    				<br/><br/>
	    				<button onclick="apply(${detail.id})">立即申请</button>
	    			</div>
	    		</td></tr>
	    	</table>
        
    	</div>
	</c:if>
    
    
    <div class="mask_alpha" style="display: none;"></div>
    <div id='div_login' class="fdiv"  style="display: none;">
        <p>您还未登录，请先登录！</p>
        <div><button class="fdivbtn2">取消</button>
        <button class="fdivbtn1" onclick="javascript:location.href='<%=basePath %>/user/forwardLogin';">确定</button></div>
    </div>
    <%@include file="../base/footer.html" %>
    
</body>
</html>