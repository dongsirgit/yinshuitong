<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil"
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
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet"
	type="text/css">
<script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
<script src="<%=basePath %>/scripts/order/order.js"></script>
<script type="text/javascript">
    	var loginflag = <%=loginflag%>;
		function apply(id){
			if(loginflag==1){//登陆成功
				identify(id);
			}else{//登陆失败
				$(".mask_alpha").show();
				$("#div_login").show();
			}
		}
		function tologin(){
			$('#tologin').submit();
		}
		function identify(id){
			$.ajax({
				type:"POST",
				url:"<%=basePath %>/users/identify/identflag",
				dataType:"JSON",
				data: {'id':id},
				asysn:false,
				success:function(data){
					if(data.TaxVerify>0 && data.TaxVerify <4){
						$(".mask_alpha").show();
						$("#div_iden2").show();
					}else if(data.TaxVerify == 4){//认证成功
						if(data.areaflag=='success'){//产品 地区
							if(data.appflag > 0){
								$(".mask_alpha").show();
								$("#div_unfinish").show();
							}else{
								//location.href='<%=basePath %>/users/applyloan/toLoan';
								$('#form').submit();
							}
						}else{
							alert("此产品未在您认证的地区销售，请重新选择其它产品！")
// 							$(".area").attr("disabled","disabled")

						}
					}else{//认证失败
						$(".mask_alpha").show();
						$("#div_iden").show();
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					if(XMLHttpRequest.responseText=="timeOut"){
		        		location.reload();
		        	}else{
		        		alert("Error");
		        	}
		        }
			});
		}
		
		$(document).ready(function(){
			$(".fdivbtn2,.fdivbtn1").click(function(){
				$(".mask_alpha,.fdiv").hide();
			});
		})
        
    </script>

</head>
<body>
	<iframe src="<%=basePath %>/basic/head" width="100%" height="74px"
		frameborder="0" scrolling="no"></iframe>
	<c:if test="${detail != null}">
		<div class="main product">
			<div class="crumbsbox">
				<a href="<%=basePath %>/" class="ft18">首页</a> &gt; <a
					href="<%=basePath %>/product/list">贷款产品列表</a> &gt; <span>产品详情</span>
			</div>
			<div class="productlist">
				<div class="loan_name clearfix">
					<div class="loan_name_logo fl">
						<img alt="" src="<%=basePath %>/images/product/${detail.logoUrl}">
					</div>
					<span class="fl">税e贷</span> <a class="productlist_btn fr"
						onclick="apply(${detail.id})">立即申请</a>
				</div>
				<div class="productlist_con">
					<h3 class="productlist_h3">开通地区：</h3>
					<p class="pb25">${detail.ktdq}</p>
					<h3 class="productlist_h3">产品说明：</h3>
					<p class="pb25">${detail.cpsm}</p>
					<h3 class="productlist_h3">产品优势/特色：</h3>
					<pre class="pb25">${detail.cpys}</pre>
					<h3 class="productlist_h3">贷款对象：</h3>
					<pre class="pb25">${detail.dkdx}</pre>
					<h3 class="productlist_h3">申办条件：</h3>
					<pre class="pb25">${detail.sbtj}</pre>
					<h3 class="productlist_h3">申办材料：</h3>
					<p class="pb25">${detail.sbcl}</p>
					<h3 class="productlist_h3">申办流程：</h3>
					<pre class="pb25">${detail.sblc}</pre>
					<h3 class="productlist_h3">贷款费率：</h3>
					<pre class="pb25">${detail.dkfl}</pre>

					<div class="productlist_btn_box">
						<a href="javascript:;" class="productlist_btn"
							onclick="apply(${detail.id})">填写申请</a>
					</div>
				</div>
			</div>
			<form id='form' action="<%=basePath %>/users/applyloan/toLoan"
				method="post">
				<input type="hidden" id="id" name='id' value='${detail.id}'>
			</form>
			<form id="tologin" action="<%=basePath %>/user/forwardLogin"
				method="post">
				<input type="hidden" id="proId" name='proId' value='${detail.id}'>
			</form>
		</div>
	</c:if>
	<div class="mask_alpha" style="display: none;"></div>
	<div id='div_login' class="fdiv" style="display: none;">
		<p>您还未登录，请先登录！</p>
		<div>
			<button class="fdivbtn2">取消</button>
			<button class="fdivbtn1" onclick="javascript:tologin();">确定</button>
		</div>
	</div>

	<div id='div_iden' class="fdiv" style="display: none;">
		<p>您还未进行实名认证，请先实名认证！</p>
		<div>
			<button class="fdivbtn2">取消</button>
			<button class="fdivbtn1"
				onclick="javascript:location.href='<%=basePath %>/users/identify';">确定</button>
		</div>
	</div>

	<div id='div_iden2' class="fdiv" style="display: none;">
		<p>管理员正在审核信息，请耐心等待。</p>
		<div>
			<button class="fdivbtn1">确定</button>
		</div>
	</div>

	<div id='div_unfinish' class="fdiv" style="display: none;">
		<p>您当前有一条贷款申请未完成,不能重复申请。</p>
		<div>
			<button class="fdivbtn1"
				onclick="javascript:location.href='<%=basePath %>/users/init/userInfo'">确定</button>
		</div>
	</div>


	<%@include file="../base/footer.html"%>

</body>
</html>