<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String basePath = request.getContextPath();%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写申请</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#loansub').click(function(){
		$('#loan_form').action="<%=basePath %>/users/applyloan/loansub";
		$('#loan_form').submit;
	});
})
</script>
</head>
<body>
	<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	<div>
	    <h1 align="center">填写申请</h1>
	</div>
	<div class="main">
		<div class="mainpb">
			<div class="orderform">
	        	<div class="item">
	              <span class="label">企业名称：</span><span id="realname"><c:out value="${loginedUser.corpName }"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">企业纳税号：</span><span id="phonenum"><c:out value="${loginedUser.taxSn}"/></span>
	            </div>
	            <form id="loan_form">
		              <div class="item"><span class="label"><b>*</b>期望贷款金额：</span>
			            	<input name="applyQuota" id="applyQuota" type="text" maxlength="9" class="text w264" onblur="che_applyQuota()"><em>万元</em>
			              	<span id="e_applyQuota" class="erro colorred" style="display: none;">× 请输入正确贷款金额</span>
			          </div>
		              <div class="item">
		              		<span class="label"><b>*</b>期望贷款期限：</span>
		              		<input name="applyTerm" id="applyTerm" type="text" maxlength="3" class="text w264" onblur=""><em>个月</em> 
		                    <span id="e_applyTerm" class="erro colorred" style="display: none;">× 请输入贷款期限</span>
		               </div>
		               <div class="item">
		              		<span class="label"><b>*</b>联系人姓名：</span>
		              		<input type="text" name="contactName" id="contactName" class="text w264" > 
		                    <span id="e_contactName" class="erro colorred" style="display: none;">× 请输入正确的姓名</span>
		               </div>
		               <div class="item">
		              		<span class="label"><b>*</b>联系人手机：</span>
		              		<input type="text" name="telephone" id="telephone" class="text w264" > 
		                    <span id="e_telephone" class="erro colorred" style="display: none;">× 请输入正确的手机号</span>
		               </div>
		               <div class="item">
		               		<div class="item">
			              		<span class="label"><b>*</b>贷款用途：</span>
			              		<textarea id="applyNote" style="width: 320px;height: 80px;" onkeydown="textCounter();" maxlength="250"></textarea>
		               		</div>
		                    <span id="e_applyNote" class="erro colorred" style="display: none;">× 至少输入10个字</span>
		               </div>
		               <div class="item">
		              		<span class="label"><b>*</b>营业执照：</span>
		              		<input type="button" value="上传" style="width: 80px"> 
		                    <span id="e_applyTermNum" class="erro colorred" style="display: none;">× 请上传附件</span>
		               </div>
		               <div class="item">
		              		<span class="label"><b>*</b>贷款申请书：</span>
		              		<input type="button" value="上传" style="width: 80px"> <a href="#" onclick="alert('下载成功！')">下载申请书模板</a>
		                    <span id="e_applyTermNum" class="erro colorred" style="display: none;">× 请上传附件</span>
		               </div>
		               <div class="btnbox">
		            	<input id="loansub" class="btn-yellow" type="button" value="提交">
		            </div>
			           <span class="clr"></span>
	            </form>
			</div>
		</div>
	</div>
	<%@include file="../base/footer.html" %>
</body>
</html>