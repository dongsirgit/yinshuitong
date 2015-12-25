<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/common/base.css" />
	<link rel="stylesheet" href="<%=basePath %>/styles/index/index.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/help/help.css"  type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/login/login.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/order/order.css" type="text/css">
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script language="javascript" type="text/javascript">
	    if (window.self == window.top) {
	        window.top.location ="<%=basePath %>/users/init/userInfo";
	    }
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			
		});
	</script>

</head>
<body style="background-color: #fefefe; background-image:none;height:auto!important;height:500px;min-height:500px;">
	<div style="width: 80%; height: 100%; padding: 10px;"><!--  border:1px solid #AFAEAC; -->
		<table style="font-size: 15px;">
			<tr height="25px;">
				<td width="120" align="right" style=" padding: 5px;">订单状态：</td>
				<td width="180" align="left">
					<c:if test="${detail.applyStatus==100}">审核中</c:if>
					<c:if test="${detail.applyStatus>=200 && detail.applyStatus<300}">已取消</c:if>
					<c:if test="${detail.applyStatus>=300 && detail.applyStatus<400}">已确认</c:if>
					<c:if test="${detail.applyStatus>=500 && detail.applyStatus<600}">审批中</c:if>
					<c:if test="${detail.applyStatus>=600 && detail.applyStatus<700}">失败</c:if>
					<c:if test="${detail.applyStatus>=700}">完成</c:if>
				</td>
				<td width="120" align="right">申请时间：</td>
				<td width="180" align="left">
					<fmt:formatDate value="${detail.applyTime}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" style=" padding: 5px;">预授信额度：</td>
				<td width="180" align="left" style="color: red;">
					<c:if test="${detail.preQuota!=null}">${detail.preQuota}万元</c:if>
					</td>
				<td width="120" align="right">银行审批额度：</td>
				<td width="180" align="left" style="color: red;">
					<c:if test="${detail.approveQuota!=null}">${detail.approveQuota}万元</c:if>
				</td>
			</tr>
		</table>
	</div>
	<hr>
	
	<div style="width: 100%;height:auto!important; height:60px; min-height:60px;"><!--  border:1px solid #AFAEAC; -->
		<table style="font-size: 13px;">
			<tr height="25px;" valign="top">
				<td width="90%" align="left">${detail.statusNote}</td>
			</tr>
		</table>
	</div>
	<hr>
	
	<div style="width: 80%; height: 100%; padding: 10px;"><!--  border:1px solid #AFAEAC; -->
		<table style="font-size: 13px;">
			<tr height="25px;">
				<td width="120" align="right" style=" padding: 5px;">申请产品：</td>
				<td width="180" align="left">${detail.relaBank}${detail.pname}</td>
				<td width="120" align="right"></td>
				<td width="180" align="left"></td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" style=" padding: 5px;">企业名称：</td>
				<td width="180" align="left">${detail.corpName}</td>
				<td width="120" align="right">企业纳税号：</td>
				<td width="180" align="left">${detail.taxSn}</td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" style=" padding: 5px;">期望贷款金额：</td>
				<td width="180" align="left">${detail.applyQuota}万元 </td>
				<td width="120" align="right">期望贷款期限：</td>
				<td width="180" align="left">${detail.applyTerm}个月</td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" style=" padding: 5px;">联系人姓名：</td>
				<td width="180" align="left">${detail.contactName}</td>
				<td width="120" align="right">联系人手机：</td>
				<td width="180" align="left">${detail.telephone}</td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" valign="top" style="padding: 5px;">贷款用途：</td>
				<td width="180" align="left" valign="top" colspan="3" style="padding-top: 5px;">${detail.applyNote}</td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" style="padding: 5px;">营业执照：</td>
				<td width="180" align="left"colspan="3">
					<c:forEach items="${detail.attaList}" var="loan_jpg">
						<c:if test="${loan_jpg.attachType==0}">
							<a class="pt5 tdul" href="<%=basePath %>/users/file/showPicById?id=${loan_jpg.id}" target="_blank">${loan_jpg.attachNote}</a>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr height="25px;">
				<td width="120" align="right" style="padding: 5px;">贷款申请书：</td>
				<td width="180" align="left"colspan="3">
					<c:forEach items="${detail.attaList}" var="loan_jpg">
						<c:if test="${loan_jpg.attachType==1}">
							<a class="pt5 tdul" href="<%=basePath %>/users/file/showPicById?id=${loan_jpg.id}" target="_blank">${loan_jpg.attachNote}</a>
						</c:if>
					</c:forEach>
					<!-- <a href="" target="_blank">贷款申请书.jpg</a> -->
				</td>
			</tr>
		</table>
	</div>
	
	<div style="width: 80%; height: 100%; padding: 10px;" align="center">
		<div class="btnbox"><a class="btnboxa1" href="<%=basePath%>/users/init/userInfo_account" >返回</a></div>
	</div>
</body>
</html>