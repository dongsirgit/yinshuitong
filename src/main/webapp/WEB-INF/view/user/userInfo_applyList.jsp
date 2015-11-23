<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/index/index.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/login/login.css">
	<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>/scripts/common/md5.js"></script>
	<script language="javascript" type="text/javascript">
	    if (window.self == window.top) {
	        window.top.location ="<%=basePath %>/users/init/userInfo";
	    }
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			//确认提交
			$("#btn_submit_confirm").click(function(){
				var id = $("#save_id4apl").val();
				var params = {"id":id};
				$.ajax({
					type:"POST",
			        url: "<%=basePath %>/users/applyLoan/submit?id="+id,
			        data:params,
			        success: function (data) {
			            if (data.status == "0") {
			               alert("请登陆...");
			            }else if (data.status == "1"){
			            	if(data.success=="1"){
			            		//location.href='<%=basePath %>/users/applyLoan/up';
			            		window.parent.location.href='<%=basePath %>/users/applyLoan/up';
			            	}else{
			            		alert("提交失败,请重新提交!");
			            	}
			            }
			        },
			        error:function(e) {
			        	alert("提交失败,请重新提交!");
			        }
			    })
			})
			//确认删除
			$("#btn_del_confirm").click(function(){
				var id = $("#save_id4apl").val();
				var params = {"id":id};
				$.ajax({
					type:"POST",
			        url: "<%=basePath %>/users/applyLoan/deleteApplyLoan?id="+id,
			        data:params,
			        success: function (data) {
			            if (data.status == "0") {
			               alert("请登陆...");
			            }else if (data.status == "1"){
			            	if(data.success=="1"){
			            		// location.href='<%=basePath %>/users/init/userInfo';
			            		window.parent.openLi('0')
			            	}else{
			            		alert("删除失败,请重新操作!");
			            	}
			            }
			        },
			        error:function(e) {
			        	alert("删除失败,请重新操作!");
			        }
			    })
			});
			//表格隔行有背景色
			$('.loan_list table tr:odd').css('background','#fffceb');
			
			/* $(".tc a").click(function(){
				$(".mask_alpha,.tc").hide(200);
			}); */
			$(".fdivbtn2").click(function(){
				$(".mask_alpha,.fdiv").hide(200);
			});
			$("#btn_upCheck_confirm").click(function(){
				$("#upCheckdiv").hide();
				$(".mask_alpha").hide();
			})
		})
		
		
		//为提交操作准备id值
		function smt_applyLoan(id){
			var params = {"id":id};
			$.ajax({
				type:"POST",
		        url: "<%=basePath %>/users/applyLoan/checkAtt",
		        data:params,
		        success: function (data) {
		            	if(data.success=="1"){
		            		$("#save_id4apl").val(id);
		        			$("#submit_check").show(200);
		        			$(".mask_alpha").show();
		            	}else{
		            		$("#upCheckdiv").show();
		            		$(".mask_alpha").show();
// 		            		$("#submit_check").hide();
		            	}
		        },
		        error:function(e) {
		        	alert("提交失败,请重新提交!");
		        }
		    })
			
		}
		//为删除操作准备id值
		function del_applyLoan(id){
			$("#save_id4apl").val(id);
			$("#del_check").show(200);
			$(".mask_alpha").show();
		}
		
	</script>
</head>
<body style="background-color: #fefefe; height:auto!important;height:500px;min-height:500px;"><!-- margin:0px auto; -->
	<!-- <div class="content_r"> -->
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
							<td class="handle"><c:if
									test="${loan_temp.approveStatus!='-3'}">
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