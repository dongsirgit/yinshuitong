<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/help/help.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/index/index.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/login/login.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/order/order.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/styles/user/user.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/scripts/common/md5.js"></script>
	<script language="javascript" type="text/javascript">
	    if (window.self == window.top) {
	        window.top.location ="<%=basePath %>/users/init/userInfo";
	    }
	</script>
	<script type="text/javascript">
		
		
	</script>
</head>
<body style="background-color: #fefefe; background-image:none;height:auto!important;height:500px;min-height:500px;">
	    <img src="<%=basePath%>/images/common/headPic.png" style="width:60px;height:60px;float:left"/>
	    <div class="userInfoDiv">
	       <span class="userInfoSpan">登录账号:</span>${loginedUser.mobilePhone}
	    </div>
	    <div class="userInfoDiv">
	       <span class="userInfoSpan">登录密码:</span>
	       <span class="userInfoSpan">已设置</span>
	       <a href="<%=basePath%>/users/changePwd" style="text-decoration:underline" target="_self">修改密码</a>
	    </div>
	    <div style="clear:both;border-bottom:2px solid #dcc09a"></div>
	       <h4 class="userInfoItemTitle">企业信息</h4>
	       <span class="userInfoVerifyState">
	           <c:choose>
	               <c:when test="${loginedUser.taxVerify == 1 ||loginedUser.taxVerify == 2}">
	                                             已经通过实名认证√                          
			       </c:when>
			       <c:when test="${loginedUser.taxVerify == 3}">
                                                        人工审核中，请等待...                                                          
                   </c:when>
			       <c:otherwise>
			                         您还未进行认证,认证后才可以办理业务.
                     <a href="javascript;" style="text-decoration:underline;color:red">立即验证</a>
			       </c:otherwise> 
			    </c:choose>
			</span>
			<table class="userInfoTable">
				<tr>
				<td class="textRight">企业名称:</td>
				<td style="width:200px"></td>
				<td class="textRight">企业办公地址:</td>
				<td style="width:200px"></td>
				</tr>
	            <tr>
	            <td class="textRight">企业纳税号:</td>
	            <td style="width:200px"></td>
	            <td class="textRight">企业办公地址:</td>
	            <td style="width:200px"></td>
	            </tr>
	            <tr>
	            <td class="textRight">法人代表姓名:</td>
	            <td style="width:200px"></td>
	            <td class="textRight">法人代表身份证号:</td>
	            <td style="width:200px"></td>
	            </tr>
			</table> 
	    <div style="clear:both;border-bottom:2px solid #dcc09a"></div>
		<ul class="loan_list">
	        <li class="current" style="display: block;">
				<h4 class="listh4">贷款列表</h4>
				<table id="loan_List">
					<tr class="th">
						<td>贷款金额</td>
						<td>贷款期限</td>
						<td>贷款种类</td>
						<td>状态</td>
						<td>提交时间</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${list}" var="loan_temp">
						<tr>
							<td>
							<c:if test="${loan_temp.approveStatus=='-3'}">
	                        	<a href="<%=basePath %>/users/loanList/reviseInfo?id=${loan_temp.id}" target="_top" class="money">
	                        		<fmt:formatNumber pattern="#,##0.00"><c:out value="${loan_temp.applyQuota}"/></fmt:formatNumber>元
	                        	</a>
                        	</c:if>
                        	<c:if test="${loan_temp.approveStatus!='-3'}">
		                    	<a href="<%=basePath %>/users/loanList/showInfo?id=${loan_temp.id}" target="_top" class="money">
		                    		<fmt:formatNumber pattern="#,##0.00"><c:out value="${loan_temp.applyQuota}"/></fmt:formatNumber>元
		                    	</a>
                        	</c:if>
							</td>
							<td><c:out value="${loan_temp.applyTermNum }" /> <c:if
									test="${loan_temp.applyTermType=='2'}">个月</c:if> <c:if
									test="${loan_temp.applyTermType=='1'}">天</c:if> <c:if
									test="${loan_temp.applyTermType=='3'}">年</c:if></td>
							<td><c:if test="${loan_temp.applyType=='1' }">企业经营贷</c:if> <c:if
									test="${loan_temp.applyType=='2' }">个人消费贷</c:if> <c:if
									test="${loan_temp.applyType=='3' }">企业法人贷</c:if></td>
							<td><c:if test="${loan_temp.approveStatus=='-3' }">已保存</c:if> <c:if
									test="${loan_temp.approveStatus=='-2' }">初审中</c:if> <c:if
									test="${loan_temp.approveStatus=='-1' }">审核未通过</c:if> <c:if
									test="${loan_temp.approveStatus=='0' }">终审中</c:if> <c:if
									test="${loan_temp.approveStatus=='1' }">通过审核</c:if> <c:if
									test="${loan_temp.approveStatus=='2' }">审核未通过</c:if></td>
							<td><c:if test="${loan_temp.approveStatus!='-3' }">
									<fmt:formatDate value="${loan_temp.modifyTime }"
										pattern="yyyy-MM-dd" />
								</c:if> <c:if test="${loan_temp.approveStatus=='-3' }">--</c:if></td>
							<td class="handle"><c:if test="${loan_temp.approveStatus!='-3'}">
									<a style="color: #bebebe; cursor: default; text-decoration: none;">修改</a>
									<a style="color: #bebebe; cursor: default; text-decoration: none;">删除</a>
									<a style="color: #bebebe; cursor: default; text-decoration: none;">提交</a>
								</c:if> <c:if test="${loan_temp.approveStatus=='-3'}">
									<a href="<%=basePath %>/users/loanList/reviseInfo?id=${loan_temp.id}" target="_top">修改</a>
									<a href="javascript:void(0);"
										onclick="del_applyLoan(${loan_temp.id})">删除</a>
									<a href = "javascript:void(0);"
										onclick="smt_applyLoan(${loan_temp.id})">提交</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</li>
		</ul>
	<!-- </div> -->	
	<div class="mask_alpha" style="display:none;"></div>
	<div class="fdiv" id="submit_check" style="display:none;">
		<p>您确定正式提交申请贷款吗？</p>
	    <div><a class="fdivbtn1" id="btn_submit_confirm" name="btn_submit_confirm">确定</a><a class="fdivbtn2">取消</a></div>
	</div>
	<div class="fdiv" id="del_check" style="display:none;">
		<p>您确定删除这条贷款申请吗？</p>
	    <div><a class="fdivbtn1" id="btn_del_confirm" name="btn_del_confirm">确定</a><a class="fdivbtn2">取消</a></div>
	</div>
	<div id="upCheckdiv" class="fdiv" style="display: none;">
	<p>证件资料不齐全,上传完整后才可提交！</p>
    <div><a class="fdivbtn1" id="btn_upCheck_confirm">确定</a></div>
	</div>
	<input id="save_id4apl" type="hidden" value=""/>
	
</body>
</html>