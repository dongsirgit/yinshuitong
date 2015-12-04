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
		$(document).ready(function(){
			
			
			$(".fdivbtn2").click(function(){
				$('#idid').val('');
				$(".mask_alpha,.fdiv").hide(200);
			});
			$("#btn_quxiao").click(function(){
				var id = $('#idid').val();
				$.ajax({
					type:"POST",
					url:"<%=basePath %>/users/loan/quxiao",
					data:{id:id},
					//asysn:true,
					dataType:"JSON",
					//dataType:"text",
					success:function(data){
						//alert(JSON.stringify(data))
						if(data==1){
							$('.mask_alpha').hide();
							$('#div_quxiao').hide();
							$('#quxiao'+id).remove();
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
						if(XMLHttpRequest.responseText=="timeOut"){
			        		top.location.reload();
			        	}else{
			        		alert("Error_plat1");
			        	}
			        }
				});
			});
			
		})
	
		function quxiao(id){
			$('.mask_alpha').show();
			$('#div_quxiao').show();
			$('#idid').val(id);
		}
		
		function chakan(id){
			
		}
		
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
	               <c:when test="${loginedUser.taxVerify == 4}">
	                                             已经通过实名认证√                          
			       </c:when>
			       <c:when test="${loginedUser.taxVerify == 3}">
                                                        人工审核中，请等待...                                                          
                   </c:when>
			       <c:otherwise>
			                         您还未进行认证,认证后才可以办理业务.
                     <a href="<%=basePath%>/users/identify" target="top" style="text-decoration:underline;color:red">立即认证>></a>
			       </c:otherwise> 
			    </c:choose>
			</span>
			<table class="userInfoTable">
				<tr>
				<td class="textRight">企业名称:</td>
				<td class="textLeft" style="width:200px">${loginedUser.corpName}</td>
				<td class="textRight">企业办公地址:</td>
				<td class="textLeft" style="width:200px">${loginedUser.address}</td>
				</tr>
	            <tr>
	            <td class="textRight">企业纳税号:</td>
	            <td class="textLeft" style="width:200px">${loginedUser.taxSn}</td>
	            <td class="textRight">营业执照注册号:</td>
	            <td class="textLeft" style="width:200px">${loginedUser.licenseRegnum}</td>
	            </tr>
	            <tr>
	            <td class="textRight">法人代表姓名:</td>
	            <td class="textLeft" style="width:200px">${loginedUser.apName}</td>
	            <td class="textRight">法人代表身份证号:</td>
	            <td class="textLeft" style="width:200px">${loginedUser.idcard}</td>
	            </tr>
			</table> 
	    <div style="clear:both;border-bottom:2px solid #dcc09a"></div>
		<ul class="loan_list">
	        <li class="current" style="display: block;">
				<h4 class="listh4">贷款列表</h4>
				<table id="loan_List">
					<tr class="th">
						<td>申请金额</td>
						<td>申请时间</td>
						<td>申请产品</td>
						<td>订单状态</td>
						<td>预授信额度</td>
						<td>银行审批额度</td> 
						<td>操作</td>
					</tr>
					<c:forEach items="${list}" var="loan_temp">
						<tr>
							<td>
	                        		<c:out value="${loan_temp.applyQuota}"/>万元
							</td>
							<td>
								<fmt:formatDate value="${loan_temp.applyTime}" pattern="yyyy-MM-dd" />
							</td>
							<td>${loan_temp.relaBank}${loan_temp.pname}</td>
							<td>
								<c:if test="${loan_temp.applyStatus==100}">审核中</c:if>
								<c:if test="${loan_temp.applyStatus>=200 && loan_temp.applyStatus<300}">已取消</c:if>
								<c:if test="${loan_temp.applyStatus>=300 && loan_temp.applyStatus<400}">已确认</c:if>
								<c:if test="${loan_temp.applyStatus>=500 && loan_temp.applyStatus<600}">审批中</c:if>
								<c:if test="${loan_temp.applyStatus>=600 && loan_temp.applyStatus<700}">失败</c:if>
								<c:if test="${loan_temp.applyStatus>=700}">完成</c:if>
							</td>
							<td>${loan_temp.preQuota}</td>
							<td>${loan_temp.approveQuota}</td>
							<td class="handle">
								<c:if test="${loan_temp.applyStatus==100}">
									<a id='quxiao${loan_temp.id}' href="javascript:quxiao(${loan_temp.id});">取消</a>
								</c:if>
									<a href="<%=basePath%>/users/loan/detail?id=${loan_temp.id}"
										onclick="chakan(${loan_temp.id})">查看详情</a>
								</td>
						</tr>
					</c:forEach>
				</table>
			</li>
		</ul>
	<!-- </div> -->	
	<div class="mask_alpha" style="display:none;"></div>
	
	<div class="fdiv" id="del_check" style="display:none;">
		<p>您确定删除这条贷款申请吗？</p>
	    <div><a class="fdivbtn1" id="btn_del_confirm" name="btn_del_confirm">确定</a><a class="fdivbtn2">取消</a></div>
	</div>
	
	<div id="div_quxiao" class="fdiv" style="display: none;">
		<p>您确定进行这个操作吗?</p>
    	<div><a class="fdivbtn1" id="btn_quxiao" name="btn_quxiao">确定</a><a class="fdivbtn2">取消</a></div>
	</div>
	<input id="idid" type="hidden" value=""/>
	
</body>
</html>