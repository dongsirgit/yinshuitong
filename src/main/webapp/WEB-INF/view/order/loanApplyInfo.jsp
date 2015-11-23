<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String basePath = request.getContextPath(); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>贷款</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
<script src="<%=basePath %>/scripts/order/order.js"></script>
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="75px" frameborder="0" scrolling="no"></iframe>
<!-- 企业经营贷  -->
<c:if test="${applyLoan.applyType=='1'}">
	<div class="main">
		<div class="mainpb">
	    	<c:if test="${applyLoan.approveStatus=='-3' }">
			<div class="tip"><p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p></div>
			</c:if>
			<c:if test="${applyLoan.approveStatus!='-3' }">
				<div class="tip" style="word-wrap: break-word;">
					<p>审核状态：<c:if test="${applyLoan.approveStatus=='-2'}">初审中</c:if><c:if test="${applyLoan.approveStatus=='-1'}">审核未通过</c:if><c:if test="${applyLoan.approveStatus=='0'}">终审中</c:if><c:if test="${applyLoan.approveStatus=='1'}">通过审核</c:if><c:if test="${applyLoan.approveStatus=='2'}">审核未通过</c:if><br/>
						<span style="display:inline-block;">初审备注：</span><span><c:out value="${applyLoan.approveNote}"/></span><br/>
						<span style="display:inline-block;">终审备注：</span><span><c:out value="${applyLoan.judgeNote}"/>
						<c:if test="${applyLoan.approveStatus=='1' || applyLoan.approveStatus=='3'}">
							<br/>实际贷款金额：<fmt:formatNumber pattern="#,##0.00"><c:out value="${applyLoan.approveQuota}"/></fmt:formatNumber>元<br/>
							贷款起息日：<fmt:formatDate value="${applyLoan.loanDate}" pattern="yyyy-MM-dd"/><br/>
							贷款截止日：<fmt:formatDate value="${applyLoan.loanDateEnd}" pattern="yyyy-MM-dd"/><br/>
						</c:if>
						</span>
					</p>
				</div>
			</c:if>
	        <h2 class="orderh2">企业经营税务贷</h2>
			<h3 class="orderh3">基本信息<em></em></h3>
			<div class="orderform">
	        	<div class="item">
	              <span class="label">企业名称：</span><span><c:out value="${cuser.corpName}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">企业纳税号：</span><span><c:out value="${cuser.taxSn}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人姓名：</span><span><c:out value="${cuser.apName}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人手机号：</span><span><c:out value="${cuser.mobilephone}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人身份证号：</span><span><c:out value="${cuser.idcard}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">贷款金额：</span>
	              <span>
	              	<fmt:formatNumber pattern="#,##0.00"><c:out value="${applyLoan.applyQuota}"/></fmt:formatNumber>元
	              </span>
	            </div>
	        	<div class="item">
	              <span class="label">贷款期限：</span><span><c:out value="${applyLoan.applyTermNum}"/>个月</span>
	            </div>
	        	<div class="item">
	              <span class="label">联系人姓名：</span><span><c:out value="${applyLoan.contactName}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">联系人手机：</span><span><c:out value="${applyLoan.telephone}"/></span>
	            </div>
	            <span class="clr"></span>
			</div>
			<h3 class="orderh3">证件资料</h3>
	        <table class="ordertable ac">
	        	<tr>
	            	<td class="ft16 ordertable_l">图片类</td>
	            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、经年审合格的企业(含个体工商户等)营业执照、组织机构代码证书、贷款卡</li>
                        <li>2、税务登记证</li>
                        <li>3、两年及最新一期财务报表</li>
                        <li>4、公司章程或合伙经营协议</li>
                        <li>5、税务部门开具的近2年营业税、增值税、企业所得税的完税证明（盖章）或同时提供近2年缴税付款凭证、纳税账户的银行对账单（银行盖章）、网上纳税申报系统截图</li>
                    </ul>
              	  	</td>
	            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['2'].id }" target="_blank">${attachMap['2'].batchName }</a>
                	</td>
	            </tr>
	        	<tr>
	            	<td class="ft16 ordertable_l">文档类</td>
	            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）</li>
                    </ul>
                </td>
	            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/download?id=${doc.id }" target="_blank">${doc.attachNote }</a>
                </td>
	            </tr>
	            <tr>
            	<td class="ft16 ordertable_l" rowspan="2">发票类</td>
            	<td class="al ordertable_main borbdot">
                	<ul>
                    	<li>1、进项发票数据</li>
                    </ul>
                </td>
            	<td class="ordertable_r borbdot">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['4'].id }" target="_blank">${attachMap['4'].batchName }</a>
                </td>
            </tr>
        	<tr>
            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、销项发票数据</li>
                    </ul>
                </td>
            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['5'].id }" target="_blank">${attachMap['5'].batchName }</a>
                </td>
            </tr>
	        </table>
		</div>
		<div class="btnbox"><a class="btnboxa1" href="<%=basePath %>/users/init/userInfo" >返回</a></div>
	</div>

</c:if>
<!-- 个人消费贷  -->
<c:if test="${applyLoan.applyType=='2'}">
	<div class="main">
		<div class="mainpb">
	    	<c:if test="${applyLoan.approveStatus=='-3' }">
			<div class="tip"><p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p></div>
			</c:if>
			<c:if test="${applyLoan.approveStatus!='-3' }">
				<div class="tip" style="word-wrap: break-word;">
					<p>审核状态：<c:if test="${applyLoan.approveStatus=='-2'}">初审中</c:if><c:if test="${applyLoan.approveStatus=='-1'}">审核未通过</c:if><c:if test="${applyLoan.approveStatus=='0'}">终审中</c:if><c:if test="${applyLoan.approveStatus=='1'}">通过审核</c:if><c:if test="${applyLoan.approveStatus=='2'}">审核未通过</c:if><br/>
						<span style="display:inline-block;">初审备注：</span><span ><c:out value="${applyLoan.approveNote}"/></span><br/>
						<span style="display:inline-block;">终审备注：</span><span ><c:out value="${applyLoan.judgeNote}"/></span>
					</p>
				</div>
			</c:if>
	        <h2 class="orderh2">个人消费贷</h2>
			<h3 class="orderh3">基本信息<em></em></h3>
			<div class="orderform">
	        	<div class="item">
	              <span class="label">姓名：</span><span><c:out value="${puser.realname}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">联系手机：</span><span><c:out value="${puser.phonenum}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">身份证号：</span><span><c:out value="${puser.idcard}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">贷款金额：</span><span><fmt:formatNumber pattern="#,##0.00"><c:out value="${applyLoan.applyQuota}"/></fmt:formatNumber>元</span>
	            </div>
	        	<div class="item">
	              <span class="label">贷款期限：</span><span><c:out value="${applyLoan.applyTermNum}"/>个月</span>
	            </div>
	            <span class="clr"></span>
			</div>
			<h3 class="orderh3">证件资料</h3>
	        <table class="ordertable ac">
	        	<tr>
	            	<td class="ft16 ordertable_l">图片类</td>
	            	<td class="al ordertable_main">
	                	<ul>
	                    	<li>1、二代身份证复印件（身份证两面印在纸张同一面上）</li>
	                        <li>2、收入证明类材料，包括流水、缴税证明、公司员工住房公积金缴存证明、公司员工社保总计证明等复印件（任选其一）</li>
	                        <li>3、资产类证明，包括房产证、购房发票、车辆行使证、购房合同、购车发票等</li>
	                        <li>4、北京银行或者中国农业银行借记卡</li>
	                        <li>5、近六个月流水</li>
	                    </ul>
	                </td>
	            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['0'].id }" target="_blank">${attachMap['0'].batchName }</a>
                </td>
	            </tr>
	        	<tr>
	            	<td class="ft16 ordertable_l">文档类</td>
	            	<td class="al ordertable_main">
	                	<ul>
	                    	<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）</li>
	                    </ul>
	                </td>
	            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPicById?id=${doc.id }" target="_blank">${doc.attachNote}</a>
              		</td>
	            </tr>
	        </table>
		</div>
		<div class="btnbox"><a class="btnboxa1" href="<%=basePath %>/users/init/userInfo" >返回</a></div>
	</div>
</c:if>

<!-- 企业法人贷  -->
<c:if test="${applyLoan.applyType=='3'}">
	<div class="main">
		<div class="mainpb">
	    	<c:if test="${applyLoan.approveStatus=='-3' }">
			<div class="tip"><p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p></div>
			</c:if>
			<c:if test="${applyLoan.approveStatus!='-3' }">
				<div class="tip" style="word-wrap: break-word;">
					<p>审核状态：<c:if test="${applyLoan.approveStatus=='-2'}">初审中</c:if><c:if test="${applyLoan.approveStatus=='-1'}">审核未通过</c:if><c:if test="${applyLoan.approveStatus=='0'}">终审中</c:if><c:if test="${applyLoan.approveStatus=='1'}">通过审核</c:if><c:if test="${applyLoan.approveStatus=='2'}">审核未通过</c:if><br/>
						<span style="display:inline-block;">初审备注：</span><span><c:out value="${applyLoan.approveNote}"/></span><br/>
						<span style="display:inline-block;">终审备注：</span><span><c:out value="${applyLoan.judgeNote}"/></span>
					</p>
				</div>
			</c:if>
	        <h2 class="orderh2">企业法人税务贷</h2>
			<h3 class="orderh3">基本信息<em></em></h3>
			<div class="orderform">
	        	<div class="item">
	              <span class="label">企业名称：</span><span><c:out value="${cuser.corpName}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">企业纳税号：</span><span><c:out value="${cuser.taxSn}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人姓名：</span><span><c:out value="${cuser.apName}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人手机号：</span><span><c:out value="${cuser.mobilephone}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">法人身份证号：</span><span><c:out value="${cuser.idcard}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">贷款金额：</span>
	              <span>
	              	<fmt:formatNumber pattern="#,##0.00"><c:out value="${applyLoan.applyQuota}"/></fmt:formatNumber>元
	              </span>
	            </div>
	        	<div class="item">
	              <span class="label">贷款期限：</span><span><c:out value="${applyLoan.applyTermNum}"/>个月</span>
	            </div>
	        	<div class="item">
	              <span class="label">联系人姓名：</span><span><c:out value="${applyLoan.contactName}"/></span>
	            </div>
	        	<div class="item">
	              <span class="label">联系人手机：</span><span><c:out value="${applyLoan.telephone}"/></span>
	            </div>
	            <span class="clr"></span>
			</div>
			<h3 class="orderh3">证件资料</h3>
	        <table class="ordertable ac">
	        	<tr>
	            	<td class="ft16 ordertable_l">图片类</td>
	            	<td class="al ordertable_main">
	                	<ul>
	                    	<li>1、本人二代身份证正反面原件扫描件（不认可护照、户口簿、一代身份证、临时身份证）</li>
	                        <li>2、到当地人民银行开具的纸质信用报告，或登录人民银行征信中心https://ipcrs.pbccrc.org.cn获取电子</li>
	                        <li>3、近六个月的银行流水</li>
	                        <li>4、企业营业执照（注册日期距借款申请日期满 12 个月，提供资料显示实际经营满 12 个月）</li>
	                        <li>5、经营及收入证明（财务报表/所得税税单/银行流水/社保记录/工资证明/股东分红记录/租赁收入，任选其一）</li>
	                        <li>6、住址证明（房产证，如果是租房需要有中介机构盖章证实的租房合同；近一个月的水、电、煤、有线电视账单或固定电话账单）</li>
	                    </ul>
	                </td>
	            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['0'].id }" target="_blank">${attachMap['0'].batchName }</a>
                </td>
	            </tr>
	        	<tr>
	            	<td class="ft16 ordertable_l">文档类</td>
	            	<td class="al ordertable_main">
	                	<ul>
	                    	<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）</li>
	                    </ul>
	                </td>
	            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/download?id=${doc.id }"  target="_blank">${doc.attachNote }</a>
                </td>
	            </tr>
	            <tr>
            	<td class="ft16 ordertable_l" rowspan="2">发票类</td>
            	<td class="al ordertable_main borbdot">
                	<ul>
                    	<li>1、进项发票数据</li>
                    </ul>
                    
                </td>
            	<td class="ordertable_r borbdot">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['6'].id }" target="_blank">${attachMap['6'].batchName }</a>
                </td>
            </tr>
        	<tr>
            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、销项发票数据</li>
                    </ul>
                    
                </td>
            	<td class="ordertable_r">
                    <a class="pt5 tdul" href="<%=basePath %>/users/file/showPic?id=${attachMap['7'].id }" target="_blank">${attachMap['7'].batchName }</a>
                </td>
            </tr>
	        </table>
		</div>
		<div class="btnbox"><a class="btnboxa1" href="<%=basePath %>/users/init/userInfo" >返回</a></div>
	</div>
</c:if>

	<%@include file="../base/footer.html" %>
</body>
</html>