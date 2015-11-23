<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	Cuser user = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
	Long id = 0l;
	try{
		id = Long.valueOf((null == request.getParameter("id") ||"".equals(request.getParameter("id")) ? "0":request.getParameter("id")));
	}catch(Exception e){
		
	}
	String applyType = request.getParameter("type");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>企业经营税务贷1</title> -->
<% if(null!= applyType && "1".equals(applyType)){ %>
	<title>企业经营税务贷</title>
<% }else if(null!= applyType && "3".equals(applyType)){ %>
	<title>企业法人税务贷</title>
<%}%>
	<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
	<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
	<script src="<%=basePath %>/scripts/order/order_company.js"></script>
	<script type="text/javascript">
		var basePath = '<%=basePath %>';
		var id = <%=id %>;
		var corpName = '<%=user.getCorpName()%>';
		var taxSn = '<%=user.getTaxSn()%>';
		var apName = '<%=user.getApName()%>';
		var mobilephone = '<%=user.getMobilephone()%>';
		var idcard = '<%=user.getIdcard()%>';
	
		var applyType = '<%=request.getParameter("type")%>';//申请类型
	</script>	
</head>
<body>
	<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	
	<div class="steps">
	    <ul class="clearfix stepsnum1">
	    	<li class="colorred">填写基本信息</li>
	    	<li>上传证件资料</li>
	    	<li>提交贷款申请</li>
	    </ul>
	</div>
	<div class="main">
		<div class="mainpb">
			<div class="tip"><p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p></div>
			<% if(null!= applyType && "1".equals(applyType)){ %>
				<h2 class="orderh2">企业经营税务贷</h2>
			<% }else if(null!= applyType && "3".equals(applyType)){ %>
				<h2 class="orderh2">企业法人税务贷</h2>
			<%}%>
	        
			<h3 class="orderh3">基本信息<em>（必填）</em></h3>
			<div class="orderform">
	        	<div class="item">
	              <span class="label">企业名称：</span><span id="corpName"></span>
	            </div>
	        	<div class="item">
	              <span class="label">企业纳税号：</span><span id="taxSn"></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人姓名：</span><span id="apName"></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人手机号：</span><span id="mobilephone"></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人身份证号：</span><span id="idcard"></span>
	            </div>
	            <form id="com_form">
	            	<input type="hidden" id='id' name='id'>
	            	<input type="hidden" id='applyType' name='applyType'>
		            <div class="item"><span class="label"><b>*</b>贷款金额：</span>
		            	<input name="applyQuota" id="applyQuota" type="text" maxlength="9" class="text w264" onblur="che_applyQuota()" autocomplete="off"><em>元</em>
		              	<span id="e_applyQuota" class="erro colorred" style="display: none;">× 请输入正确贷款金额</span>
		            </div>
		            <div class="item">
		            	<span class="label"><b>*</b>贷款期限：</span>
		                <div class="selbox w266"><span id='ss_applyTermNum'>选择期限</span>
		                	<ul id="u_applyTermNum">
		                    	<li data-value='3'>3</li>
		                      		<li data-value='6'>6</li>
		                      		<li data-value='9'>9</li>
		                      		<li data-value='12'>12</li>
		                      		<li data-value='24'>24</li>
		                      		<li data-value='36'>36</li>
		                      	</ul><input type="hidden" name="applyTermNum" id="applyTermNum">
		                </div> <em>月</em>
		                <span id="e_applyTermNum" class="erro colorred" style="display: none;">× 请选择贷款期限</span></div>
		            <div class="item"><span class="label"><b>*</b>联系人姓名：</span>
		            	<input name="contactName" id="contactName" type="text" maxlength="8" onblur="che_contactName()" class="text w264"  autocomplete="off">
		            	<span id="e_contactName" class="erro colorred" style="display: none;">× 请输入正确的姓名</span>
		            </div>
		            <div class="item"><span class="label"><b>*</b>联系人手机：</span>
		              	<input name="telephone" id="telephone" type="text" maxlength="11" onblur="che_telephone()" class="text w264"  autocomplete="off">
		              	<span id="e_telephone" class="erro colorred" style="display: none;">× 请输入正确的手机号</span>
		            </div>
		            <div class="btnbox">
		            	<!-- <a class="btn-yellow" href="javascript:com_next();">下一步</a> -->
		            	<input id="com_next" class="btn-yellow" type="button" value="下一步"> <!--  onclick="return com_next();" -->
		            </div>
		            <span class="clr"></span>
	            </form>
			</div>
		</div>
	</div>
	<form id="nextpage1" action="<%=basePath %>/users/applyLoan/comUpload" method="post">
		<input type="hidden" id="qyjyid" name="id">
	</form>
	<form id="nextpage3" action="<%=basePath %>/users/applyLoan/apUpload" method="post">
		<input type="hidden" id="qyfrid" name="id">
	</form>
	
	<%@include file="../base/footer.html" %>
	
</body>
</html>