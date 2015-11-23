<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baiwang.banktax.beans.Puser,com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.utils.ConfigUtil "
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
	String basePath = request.getContextPath(); 
	String typeUser = (String)session.getAttribute("typeUser");
	Cuser cuser = null;
	Puser puser = null;
	if(null!=typeUser && "0".equals(typeUser)){
		cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
	}else if(null!=typeUser && "1".equals(typeUser)){
		puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>贷款</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript">
var id = '${applyLoan.id}';
if(id==null || id=='0' || id=='') window.location.href = '<%=basePath %>/users/init/userInfo';
</script>
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
<script src="<%=basePath %>/scripts/order/order.js"></script>

<%-- <script src="<%=basePath %>/scripts/order/revise_upload.js"></script> --%>
</head>
<body>
	<iframe src="<%=basePath %>/basic/head" width="100%" height="75px"
		frameborder="0" scrolling="no"></iframe>

	<!-- 企业经营贷款 -->
	<c:if test="${applyLoan.applyType=='1'}">
		<div class="main">
			<div class="mainpb">
				<c:if test="${applyLoan.approveStatus=='-3' }">
					<div class="tip">
						<p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p>
					</div>
				</c:if>
				<c:if test="${applyLoan.approveStatus!='-3' }">
					<div class="tip">
						<p>
							审核状态：
							<c:if test="${applyLoan.approveStatus=='-2'}">初审中</c:if>
							<c:if test="${applyLoan.approveStatus=='-1'}">审核未通过</c:if>
							<c:if test="${applyLoan.approveStatus=='0'}">终审中</c:if>
							<c:if test="${applyLoan.approveStatus=='1'}">通过审核</c:if>
							<c:if test="${applyLoan.approveStatus=='2'}">审核未通过</c:if>
							<br /> <span style="display: inline-block;">初审备注：</span><span
								style="word-wrap: break-word;"><c:out
									value="${applyLoan.approveNote}" /></span><br /> <span
								style="display: inline-block;">终审备注：</span><span
								style="word-wrap: break-word;"><c:out
									value="${applyLoan.judgeNote}" /></span>
						</p>
					</div>
				</c:if>
				<h2 class="orderh2">企业经营税务贷</h2>
				<h3 class="orderh3">
					基本信息<em></em>
				</h3>
				<div class="orderform">
					<form id="applyLoan">
						<div class="item">
							<span class="label">企业名称：</span><span><c:out
									value="${cuser.corpName}" /></span>
						</div>
						<div class="item">
							<span class="label">企业纳税号：</span><span><c:out
									value="${cuser.taxSn}" /></span>
						</div>
						<div class="item">
							<span class="label">法人姓名：</span><span><c:out
									value="${cuser.apName}" /></span>
						</div>
						<div class="item">
							<span class="label">法人手机号：</span><span><c:out
									value="${cuser.mobilephone}" /></span>
						</div>
						<div class="item">
							<span class="label">法人身份证号：</span><span><c:out
									value="${cuser.idcard}" /></span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>贷款金额：</span> <input name="applyQuota"
								id="applyQuota" type="text" maxlength="9" class="text w264"
								onblur="che_applyQuota()" value="${applyLoan.applyQuota}"><em>元</em>
							<span id="e_applyQuota" class="erro colorred"
								style="display: none;">× 请输入正确贷款金额</span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>贷款期限：</span>
							<div class="selbox w266">
								<span id='ss_applyTermNum'>${applyLoan.applyTermNum}</span>
								<ul id="u_applyTermNum">
									<li data-value='3'>3</li>
									<li data-value='6'>6</li>
									<li data-value='9'>9</li>
									<li data-value='12'>12</li>
									<li data-value='24'>24</li>
									<li data-value='36'>36</li>
								</ul>
								<input type="hidden" name="applyTermNum" id="applyTermNum">
							</div>
							<em>月</em> <span id="e_applyTermNum" class="erro colorred"
								style="display: none;">× 请选择贷款期限</span>
						</div>
						<%-- <div class="item"><span class="label"><b>*</b>联系人姓名：</span><input type="text" class="text w264"  value="${applyLoan.contactName}"><span class="erro colorred" style="display: none;">× 请输入正确的姓名</span></div>
             	<div class="item"><span class="label"><b>*</b>联系人手机：</span><input type="text" class="text w264"  value="${applyLoan.telephone}"><span class="erro colorred" style="display: none;">× 请输入正确的手机号</span></div> --%>
						<div class="item">
							<span class="label"><b>*</b>联系人姓名：</span> <input
								name="contactName" id="contactName" type="text" maxlength="8"
								value="${applyLoan.contactName}" onblur="che_contactName()"
								class="text w264" autocomplete="off"> <span
								id="e_contactName" class="erro colorred" style="display: none;">×
								请输入正确的姓名</span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>联系人手机：</span> <input name="telephone"
								id="telephone" type="text" maxlength="11"
								value="${applyLoan.telephone}" onblur="che_telephone()"
								class="text w264" autocomplete="off"> <span
								id="e_telephone" class="erro colorred" style="display: none;">×
								请输入正确的手机号</span>
						</div>
						<span class="clr"></span>
					</form>
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
							<p class="pl20 colorred pb20">* 证件资料扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse11" value="上传附件">
								<div id="filelist11">
									<c:if test="${empty attachMap['2'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['2'].batchName }">
										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['2'].id }"
											target="_blank">${attachMap['2'].batchName }</a>
									</c:if>

								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="ft16 ordertable_l">文档类</td>
						<td class="al ordertable_main">
							<ul>
								<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）</li>
							</ul>
							<p class="pl20 colorred pb20">
								* 下载word文档模版，填写后上传。<a href="/files/help/com_apply.docx"
									target="_blank" rel="external">下载模版>></a>
							</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse12" value="上传附件">
								<div id="filelist12">
									<c:if test="${empty doc.attachNote }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty doc.attachNote }">
										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/download?id=${doc.id }"
											target="_blank">${doc.attachNote }</a>
									</c:if>
								</div>
							</div>


						</td>
					</tr>
					<tr>
						<td class="ft16 ordertable_l" rowspan="2">发票类</td>
						<td class="al ordertable_main borbdot">
							<ul>
								<li>1、进项发票数据</li>
							</ul>
							<p class="pl20 colorred pb20">* 进项发票数据扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse13" value="上传附件">
								<div id="filelist13">
									<c:if test="${empty attachMap['4'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['4'].batchName }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['4'].id }"
											target="_blank">${attachMap['4'].batchName }</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
					<tr>
						<td class="al ordertable_main">
							<ul>
								<li>1、销项发票数据</li>
							</ul>
							<p class="pl20 colorred pb20">* 销项发票数据扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse14" value="上传附件">
								<div id="filelist14">
									<c:if test="${empty attachMap['5'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['5'].batchName }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['5'].id }"
											target="_blank">${attachMap['5'].batchName }</a>
									</c:if>
								</div>
							</div>


						</td>
					</tr>
				</table>
				<p class="ftc5c pl12 pt10 pb35">注：以上证件资料均是必传资料，缺一不可！</p>
			</div>
		</div>

	</c:if>

	<!-- 个人消费贷款 -->
	<c:if test="${applyLoan.applyType=='2'}">
		<div class="main">
			<div class="mainpb">
				<c:if test="${applyLoan.approveStatus=='-3' }">
					<div class="tip">
						<p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p>
					</div>
				</c:if>
				<c:if test="${applyLoan.approveStatus!='-3' }">
					<div class="tip">
						<p>
							审核状态：
							<c:if test="${applyLoan.approveStatus=='-2'}">初审中</c:if>
							<c:if test="${applyLoan.approveStatus=='-1'}">审核未通过</c:if>
							<c:if test="${applyLoan.approveStatus=='0'}">终审中</c:if>
							<c:if test="${applyLoan.approveStatus=='1'}">通过审核</c:if>
							<c:if test="${applyLoan.approveStatus=='2'}">审核未通过</c:if>
							<br /> <span style="display: inline-block;">初审备注：</span><span
								style="word-wrap: break-word;"><c:out
									value="${applyLoan.approveNote}" /></span><br /> <span
								style="display: inline-block;">终审备注：</span><span
								style="word-wrap: break-word;"><c:out
									value="${applyLoan.judgeNote}" /></span>
						</p>
					</div>
				</c:if>
				<h2 class="orderh2">个人消费贷</h2>
				<h3 class="orderh3">
					基本信息<em></em>
				</h3>
				<div class="orderform">
					<form id="applyLoan">
						<div class="item">
							<span class="label">姓名：</span><span><%=puser.getRealname()%></span>
						</div>
						<div class="item">
							<span class="label">联系手机：</span><span><%=puser.getPhonenum()%></span>
						</div>
						<div class="item">
							<span class="label">身份证号：</span><span><%=puser.getIdcard()%></span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>贷款金额：</span> <input name="applyQuota"
								id="applyQuota" type="text" maxlength="9" class="text w264"
								onblur="che_applyQuota()" autocomplete="off"
								value="${applyLoan.applyQuota}"><em>元</em> <span
								id="e_applyQuota" class="erro colorred" style="display: none;">×
								请输入正确贷款金额</span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>贷款期限：</span>
							<div class="selbox w266">
								<span id='ss_applyTermNum'>${applyLoan.applyTermNum}</span>
								<ul id="u_applyTermNum">
									<li data-value='3'>3</li>
									<li data-value='6'>6</li>
									<li data-value='9'>9</li>
									<li data-value='12'>12</li>
									<li data-value='24'>24</li>
									<li data-value='36'>36</li>
								</ul>
								<input type="hidden" name="applyTermNum" id="applyTermNum">
							</div>
							<em>月</em> <span id="e_applyTermNum" class="erro colorred"
								style="display: none;">× 请选择贷款期限</span>
						</div>
						<span class="clr"></span>
					</form>
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
							<p class="pl20 colorred pb20">* 证件资料扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse21" value="上传附件">
								<div id="filelist21">
									<c:if test="${empty attachMap['0'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['0'].batchName }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['0'].id }"
											target="_blank">${attachMap['0'].batchName }</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
					<tr>
						<td class="ft16 ordertable_l">文档类</td>
						<td class="al ordertable_main">
							<ul>
								<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）<a
									href="/files/help/order_per2.jpg" target="_blank"
									rel="external">查看背面介绍>></a></li>
							</ul>
							<p class="pl20 colorred pb20">
								* 下载并打印申请书正面模版，填写后扫描上传<a href="/files/help/order_per1.jpg"
									target="_blank" rel="external">下载模版>></a>
							</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse22" value="上传附件">
								<div id="filelist22">
									<c:if test="${empty doc.attachNote }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty doc.attachNote }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPicById?id=${doc.id }"
											target="_blank">${doc.attachNote}</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
				</table>
				<p class="ftc5c pl12 pt10 pb35">注：以上证件资料均是必传资料，缺一不可！</p>
			</div>
		</div>
	</c:if>

	<!-- 企业法人贷款 -->
	<c:if test="${applyLoan.applyType=='3'}">
		<div class="main">
			<div class="mainpb">
				<c:if test="${applyLoan.approveStatus=='-3' }">
					<div class="tip">
						<p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p>
					</div>
				</c:if>
				<c:if test="${applyLoan.approveStatus!='-3' }">
					<div class="tip">
						<p>
							审核状态：
							<c:if test="${applyLoan.approveStatus=='-2'}">初审中</c:if>
							<c:if test="${applyLoan.approveStatus=='-1'}">审核未通过</c:if>
							<c:if test="${applyLoan.approveStatus=='0'}">终审中</c:if>
							<c:if test="${applyLoan.approveStatus=='1'}">通过审核</c:if>
							<c:if test="${applyLoan.approveStatus=='2'}">审核未通过</c:if>
							<br /> <span style="display: inline-block;">初审备注：</span><span
								style="word-wrap: break-word;"><c:out
									value="${applyLoan.approveNote}" /></span><br /> <span
								style="display: inline-block;">终审备注：</span><span
								style="word-wrap: break-word;"><c:out
									value="${applyLoan.judgeNote}" /></span>
						</p>
					</div>
				</c:if>
				<h2 class="orderh2">企业法人税务贷</h2>
				<h3 class="orderh3">
					基本信息<em></em>
				</h3>
				<div class="orderform">
					<form id="applyLoan">
						<div class="item">
							<span class="label">企业名称：</span><span><c:out
									value="${cuser.corpName}" /></span>
						</div>
						<div class="item">
							<span class="label">企业纳税号：</span><span><c:out
									value="${cuser.taxSn}" /></span>
						</div>
						<div class="item">
							<span class="label">法人姓名：</span><span><c:out
									value="${cuser.apName}" /></span>
						</div>
						<div class="item">
							<span class="label">法人手机号：</span><span><c:out
									value="${cuser.mobilephone}" /></span>
						</div>
						<div class="item">
							<span class="label">法人身份证号：</span><span><c:out
									value="${cuser.idcard}" /></span>
						</div>
						<%-- <div class="item"><span class="label"><b>*</b>贷款金额：</span><input type="text" class="text w264" value="${applyLoan.applyQuota}"><em>元</em><span class="erro colorred" style="display: none;">× 请输入正确贷款金额</span></div> --%>
						<div class="item">
							<span class="label"><b>*</b>贷款金额：</span> <input name="applyQuota"
								id="applyQuota" type="text" maxlength="9" class="text w264"
								onblur="che_applyQuota()" autocomplete="off"
								value="${applyLoan.applyQuota}"><em>元</em> <span
								id="e_applyQuota" class="erro colorred" style="display: none;">×
								请输入正确贷款金额</span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>贷款期限：</span>
							<div class="selbox w266">
								<span id='ss_applyTermNum'>${applyLoan.applyTermNum}</span>
								<ul id="u_applyTermNum">
									<li data-value='3'>3</li>
									<li data-value='6'>6</li>
									<li data-value='9'>9</li>
									<li data-value='12'>12</li>
									<li data-value='24'>24</li>
									<li data-value='36'>36</li>
								</ul>
								<input type="hidden" name="applyTermNum" id="applyTermNum">
							</div>
							<em>月</em> <span id="e_applyTermNum" class="erro colorred"
								style="display: none;">× 请选择贷款期限</span>
						</div>
						<%-- <div class="item"><span class="label"><b>*</b>联系人姓名：</span><input type="text" class="text w264"  value="${applyLoan.contactName}"><span class="erro colorred" style="display: none;">× 请输入正确的姓名</span></div>
             	<div class="item"><span class="label"><b>*</b>联系人手机：</span><input type="text" class="text w264"  value="${applyLoan.telephone}"><span class="erro colorred" style="display: none;">× 请输入正确的手机号</span></div> --%>
						<div class="item">
							<span class="label"><b>*</b>联系人姓名：</span> <input
								name="contactName" id="contactName" type="text" maxlength="8"
								value="${applyLoan.contactName}" onblur="che_contactName()"
								class="text w264" autocomplete="off"> <span
								id="e_contactName" class="erro colorred" style="display: none;">×
								请输入正确的姓名</span>
						</div>
						<div class="item">
							<span class="label"><b>*</b>联系人手机：</span> <input name="telephone"
								id="telephone" type="text" maxlength="11"
								value="${applyLoan.telephone}" onblur="che_telephone()"
								class="text w264" autocomplete="off"> <span
								id="e_telephone" class="erro colorred" style="display: none;">×
								请输入正确的手机号</span>
						</div>
						<span class="clr"></span>
					</form>
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
							<p class="pl20 colorred pb20">* 证件资料扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse31" value="上传附件">
								<div id="filelist31">
									<c:if test="${empty attachMap['0'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['0'].batchName }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['0'].id }"
											target="_blank">${attachMap['0'].batchName }</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
					<tr>
						<td class="ft16 ordertable_l">文档类</td>
						<td class="al ordertable_main">
							<ul>
								<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）</li>
							</ul>
							<p class="pl20 colorred pb20">
								* 下载word文档模版，填写后上传。<a href="/files/help/com_ap_apply.doc"
									target="_blank" rel="external">下载模版>></a>
							</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse32" value="上传附件">
								<div id="filelist32">
									<c:if test="${empty doc.attachNote }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty doc.attachNote }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/download?id=${doc.id }"
											target="_blank">${doc.attachNote }</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
					<tr>
						<td class="ft16 ordertable_l" rowspan="2">发票类</td>
						<td class="al ordertable_main borbdot">
							<ul>
								<li>1、进项发票数据</li>
							</ul>
							<p class="pl20 colorred pb20">* 进项发票数据扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r borbdot">
							<div>
								<input type="button" id="browse33" value="上传附件">
								<div id="filelist33">
									<c:if test="${empty attachMap['6'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['6'].batchName }">

										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['6'].id }"
											target="_blank">${attachMap['6'].batchName }</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
					<tr>
						<td class="al ordertable_main">
							<ul>
								<li>1、销项发票数据</li>
							</ul>
							<p class="pl20 colorred pb20">* 销项发票数据扫描后并压缩成zip压缩包上传</p>
						</td>
						<td class="ordertable_r">
							<div>
								<input type="button" id="browse34" value="上传附件">
								<div id="filelist34">
									<c:if test="${empty attachMap['7'].batchName }">
										<a class="erropro" href="javascript:;">* 请上传附件</a>
									</c:if>
									<c:if test="${!empty attachMap['7'].batchName }">
										<a class="pt5 tdul" name="flge4check"
											href="<%=basePath %>/users/file/showPic?id=${attachMap['7'].id }"
											target="_blank">${attachMap['7'].batchName }</a>
									</c:if>
								</div>
							</div>

						</td>
					</tr>
				</table>
				<p class="ftc5c pl12 pt10 pb35">注：以上证件资料均是必传资料，缺一不可！</p>
			</div>
		</div>
	</c:if>


	<div class="readdiv">
		<span><em>√</em></span>我已阅读并同意<a
			href="<%=basePath %>/users/applyLoan/read" target="_blank">百望金融融资服务平台《用户服务协议》</a>
	</div>

	<div class="btnbox">
		<a id="btn_owner_save" class="btnboxa1">暂时保存</a> <a
			id="btn_owner_submit" class="btnboxa2">正式提交</a>
	</div>
	<%@include file="../base/footer.html"%>
	<div class="mask_alpha"></div>
	<div id="submitdiv" class="fdiv" style="display: none;">
		<p>您确定正式提交申请贷款吗？</p>
		<div>
			<a class="fdivbtn1" id="btn_submit_confirm">确定</a><a class="fdivbtn2">取消</a>
		</div>
	</div>
	<div id="xieyidiv" class="fdiv" style="display: none;">
		<p>请先阅读并同意《用户服务协议》</p>
		<div>
			<a class="fdivbtn1" id="btn_xieyi_confirm">确定</a>
			<!-- <a class="fdivbtn2">确定</a> -->
		</div>
	</div>
	<div id="upCheckdiv" class="fdiv" style="display: none;">
		<p>
			证件资料不齐全 或 文件正在上传中，<br />上传完整后才可提交！
		</p>
		<div>
			<a class="fdivbtn1" id="btn_upCheck_confirm">确定</a>
		</div>
	</div>
	<div id="uploadingCheckdiv" class="fdiv" style="display: none;">
	<p>文件正在上传中，请稍后操作 ！</p>
    <div><a class="fdivbtn1" id="btn_upLoadingCheck_confirm">确定</a></div>
	</div>
	
	
<script type="text/javascript"
	src="<%=basePath%>/plupload/plupload.full.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/plupload/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/zh_CN.js"></script>
<script type="text/javascript">
var typeUser = '${typeUser}';
var id_sub = 0;//判断用户协议
$(document).ready(function(){
	$("#applyTermNum").val('${applyLoan.applyTermNum}')
	$("#ss_applyTermNum").attr("data-value",'${applyLoan.applyTermNum}');
	setOption($("#ss_applyTermNum"));
	$("#btn_owner_save").click(function(){
		if($("span[name='uploading']").length>0){
			$("#uploadingCheckdiv").show();
			$(".mask_alpha").show();
			return;
		}
		if(typeUser=="0"){ 
			if(che_applyQuota() && che_applyTermNum() && che_contactName() && che_telephone()){
				if(id_sub%2==0){
					$("#xieyidiv").hide();//请先阅读并同意百望金融融资服务平台《用户服务协议》
					saveLoan('-3');
				}else{
					$("#xieyidiv").show();//先阅读《用户服务协议》
					return;
				}
			}
		 }else if(typeUser=="1"){ 
			if( che_applyQuota() && che_applyTermNum()){
				if(id_sub%2==0){
					$("#xieyidiv").hide();//请先阅读并同意百望金融融资服务平台《用户服务协议》
					saveLoan('-3');
				}else{
					$("#xieyidiv").show();//先阅读《用户服务协议》
					return;
				}
			}
		}
	})
	//正式提交
	$("#btn_owner_submit").click(function(){
		 if(typeUser=="0"){
			if(che_applyQuota() && che_applyTermNum() && che_contactName() && che_telephone()){
				if($("a[name='flge4check']").length!=$("input[type='button']").length){
					$("#upCheckdiv").show();
					$(".mask_alpha").show();
					return;
				}
				var params ={"id":id};
				$.ajax({
					type:"POST",
			        url: "<%=basePath %>/users/applyLoan/checkAtt",
			        data:params,
			        success: function (data) {
			            	if(data.success!="1"){
			            		$("#upCheckdiv").show();
			            		$(".mask_alpha").show();
			            		return;
			            	}
			        },
			        error:function(e) {
			        	alert("提交失败,请重新提交!");
			        }
			    })
				if(id_sub%2==0){
					$("#xieyidiv").hide();//请先阅读并同意百望金融融资服务平台《用户服务协议》
					$("#submitdiv").show(200);
					$(".mask_alpha").show();
				}else{
					$("#xieyidiv").show();//先阅读《用户服务协议》
					return;
				}
			}
		 }else if(typeUser=="1"){ 
			if( che_applyQuota() && che_applyTermNum()){
				if($("a[name='flge4check']").length!=$("input[type='button']").length){
					$("#upCheckdiv").show();
					$(".mask_alpha").show();
					return;
				}
				var params ={"id":id};
				$.ajax({
					type:"POST",
			        url: "<%=basePath %>/users/applyLoan/checkAtt",
			        data:params,
			        success: function (data) {
			            	if(data.success!="1"){
			            		$("#upCheckdiv").show();
			            		$(".mask_alpha").show();
			            		return;
			            	}
			        },
			        error:function(e) {
			        	alert("提交失败,请重新提交!");
			        }
			    })
				if(id_sub%2==0){
					$("#xieyidiv").hide();//请先阅读并同意百望金融融资服务平台《用户服务协议》
					$("#submitdiv").show(200);
					$(".mask_alpha").show();
				}else{
					$("#xieyidiv").show();//先阅读《用户服务协议》
					return;
				}
			}
		}
	})
	$("#btn_submit_confirm").click(function(){
		$(".mask_alpha,.fdiv").hide(200);
		saveLoan('-2');
	});
	
})

function saveLoan(sub){
	
		var params = $("#applyLoan").serializeArray();
		params.push({"name":"id","value":id});
		if(sub=='-2'){
			params.push({"name":"approveStatus","value":'-2'});
		}else if(sub=='-3'){
			params.push({"name":"approveStatus","value":'-3'});
		}
		if('<%=typeUser%>'=='0'){
			var addr = "<%=basePath %>/users/applyLoan/subFirstC";
		}else if('<%=typeUser%>'=='1'){
			var addr = "<%=basePath %>/users/applyLoan/subFirstP";
		}
		$.ajax({
			type:"POST",
	        url: addr,
	        data:params,
	        success: function (data) {
	            //alert(JSON.stringify(data));
	            
                if(data.success=='1'){
                	if(sub=='-2'){
                		location.href='<%=basePath %>/users/applyLoan/up';
                	}else if(sub=='-3'){
                		location.href='<%=basePath %>/users/init/userInfo';
                	}
                }else{
                	alert("保存失败!");
                }
	        },
	        error:function(e) {
	        	alert("保存失败!");
	        },
	        dataFilter: function(data, type){
	        	if(data == "timeOut" || data == "[object XMLDocument]"){//ajax请求，发现session过期，重新刷新页面，跳转到登录页面
	        		 location.reload();
	            }else{
	                return data;
	            }
	        }
	    })
	
}

//检验上传文件的文件名长度
function che_fileNamelen(fileName){
	var max_len = 75;
	var flag=false;
	var realLength = 0,len = fileName.length,charCode = -1;
	for(var i=0;i<len;i++){
		charCode = fileName.charCodeAt(i);
		if(charCode>0 && charCode<=128) realLength +=1;
		else realLength += 2;
	}
	if(realLength<=max_len) flag=true;
	return flag;
}


//校验贷款金额
function che_applyQuota(){
	if(!/^([1-9][\d]{0,8})$/.test($("#applyQuota").val())){
		$("#e_applyQuota").show();
		return false;
	}else{
		$("#e_applyQuota").hide();
		return true;
	}
}

//校验贷款期限
function che_applyTermNum(){
	if($("#ss_applyTermNum").text()==''){
		$("#e_applyTermNum").show();
		return false;
	}else{
		$("#e_applyTermNum").hide();
		return true;
	}
}

function che_contactName(){
	if(!/^[\u4e00-\u9fa5]{2,8}$/.test($("#contactName").val())){
		$("#e_contactName").show();
		return false;
	}else{
		$("#e_contactName").hide();
		return true;
	}
}
//校验联系人手机
function che_telephone(){
	if(!/^1(([3|4|5|8][0-9]{9})|[7](0[0-9]{8}|8[0-9]{8}))$/.test($("#telephone").val())){
		$("#e_telephone").show();
		return false;
	}else{
		$("#e_telephone").hide();
		return true;
	}
}

//给下拉框赋值
function setOption(s){
//	var s=$(".selbox span");
	for(i=0;i<s.length;i++){
		var v1=s.eq(i).attr("data-value");
		var sl=s.eq(i).next("ul").children("li");
		for(a=0;a<sl.length;a++){
			var v2=sl.eq(a).attr("data-value");
			if(v2==v1){
				sl.eq(a).addClass("hover");
				s.eq(i).text(sl.eq(a).text())
			}
		}		
	}
}

$(function(){
	
	/**
	 * 企业经营贷款-上传
	 */
	
	//图片类上传
    var uploader1 = new plupload.Uploader({
        browse_button : 'browse11', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=2&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader1.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader1.bind('FilesAdded',function(uploader1,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader1.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader1.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist11').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse11").attr('disabled',true);
    	
    	uploader1.start();
    });
    uploader1.bind('UploadProgress',function(uploader1,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader1.bind('Error',function(uploader1,err){
    	
    	if("failToUnZip"==err.response){
    		uploader1.stop();
	    	$("#browse11").attr('disabled',false);
	    	document.getElementById('filelist11').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader1.stop();
	    	$("#browse11").attr('disabled',false);
	    	document.getElementById('filelist11').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader1.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}

    });
    uploader1.bind('UploadComplete',function(uploader1,files){
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist11').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=2 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse11").attr('disabled',false);
    });
    
  //文档类上传
    var uploader_doc1 = new plupload.Uploader({
        browse_button : 'browse12', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=docf&upfiletype=3&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Doc files", extensions : "doc,docx" }
   		  ],
   		  max_file_size : '1mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
   		multi_selection:false
    });    
    uploader_doc1.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_doc1.bind('FilesAdded',function(uploader_doc1,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_doc1.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_doc1.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist12').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse12").attr('disabled',true);
    	uploader_doc1.start();
    });
    uploader_doc1.bind('UploadProgress',function(uploader_doc1,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_doc1.bind('Error',function(uploader_doc1,err){
    	if("failToUnZip"==err.response){
    		uploader_doc1.stop();
	    	$("#browse12").attr('disabled',false);
	    	document.getElementById('filelist12').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_doc1.stop();
	    	$("#browse12").attr('disabled',false);
	    	document.getElementById('filelist12').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_doc1.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else if("文件类型错误，仅支持zip文件！"==err.message){
    		alert("文件类型错误，仅支持doc/docx文件！")
    	}else{
    		alert(err.message);
    	}
    });
    uploader_doc1.bind('UploadComplete',function(uploader_doc1,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist12').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/downloadByIdType?id='+id+'&attType=3 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse12").attr('disabled',false);
    });
    
  //发票类进项上传
    var uploader_invoices_in1 = new plupload.Uploader({
        browse_button : 'browse13', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=4&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader_invoices_in1.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_invoices_in1.bind('FilesAdded',function(uploader_invoices_in1,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_invoices_in1.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_invoices_in1.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist13').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse13").attr('disabled',true);
    	uploader_invoices_in1.start();
    });
    uploader_invoices_in1.bind('UploadProgress',function(uploader_invoices_in1,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_invoices_in1.bind('Error',function(uploader_invoices_in1,err){
    	if("failToUnZip"==err.response){
    		uploader_invoices_in1.stop();
	    	$("#browse13").attr('disabled',false);
	    	document.getElementById('filelist13').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票进项类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_invoices_in1.stop();
	    	$("#browse13").attr('disabled',false);
	    	document.getElementById('filelist13').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票进项类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_invoices_in1.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_invoices_in1.bind('UploadComplete',function(uploader_invoices_in1,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist13').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=4 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse13").attr('disabled',false);
    });
    
  //发票类销项上传
    var uploader_invoices_out1 = new plupload.Uploader({
        browse_button : 'browse14', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=5&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader_invoices_out1.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_invoices_out1.bind('FilesAdded',function(uploader_invoices_out1,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_invoices_out1.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_invoices_out1.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist14').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse14").attr('disabled',true);
    	uploader_invoices_out1.start();
    });
    uploader_invoices_out1.bind('UploadProgress',function(uploader_invoices_out1,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_invoices_out1.bind('Error',function(uploader_invoices_out1,err){
    	if("failToUnZip"==err.response){
    		uploader_invoices_out1.stop();
	    	$("#browse14").attr('disabled',false);
	    	document.getElementById('filelist14').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票销项类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_invoices_out1.stop();
	    	$("#browse14").attr('disabled',false);
	    	document.getElementById('filelist14').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票销项类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_invoices_out1.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_invoices_out1.bind('UploadComplete',function(uploader_invoices_out1,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist14').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=5 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse14").attr('disabled',false);
    });
    
    /**
	 * 企业法人贷款-上传
	 */
	
	//图片类上传
    var uploader3 = new plupload.Uploader({
        browse_button : 'browse31', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=0&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader3.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader3.bind('FilesAdded',function(uploader3,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader3.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader3.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist31').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse31").attr('disabled',true);
    	uploader3.start();
    });
    uploader3.bind('UploadProgress',function(uploader3,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader3.bind('Error',function(uploader3,err){
    	if("failToUnZip"==err.response){
    		uploader3.stop();
	    	$("#browse31").attr('disabled',false);
	    	document.getElementById('filelist31').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader3.stop();
	    	$("#browse31").attr('disabled',false);
	    	document.getElementById('filelist31').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader3.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader3.bind('UploadComplete',function(uploader3,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist31').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=0 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse31").attr('disabled',false);
    });
    
  //文档类上传
    var uploader_doc3 = new plupload.Uploader({
        browse_button : 'browse32', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=docf&upfiletype=1&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Doc files", extensions : "doc,docx" }
   		  ],
   		  max_file_size : '1mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
   		multi_selection:false
    });    
    uploader_doc3.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_doc3.bind('FilesAdded',function(uploader_doc3,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_doc3.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_doc3.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist32').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse32").attr('disabled',true);
    	uploader_doc3.start();
    });
    uploader_doc3.bind('UploadProgress',function(uploader_doc3,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_doc3.bind('Error',function(uploader_doc3,err){
    	if("failToUnZip"==err.response){
    		uploader_doc3.stop();
	    	$("#browse32").attr('disabled',false);
	    	document.getElementById('filelist32').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_doc3.stop();
	    	$("#browse32").attr('disabled',false);
	    	document.getElementById('filelist32').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_doc3.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else if("文件类型错误，仅支持zip文件！"==err.message){
    		alert("文件类型错误，仅支持doc/docx文件！")
    	}else{
    		alert(err.message);
    	}
    });
    uploader_doc3.bind('UploadComplete',function(uploader_doc3,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist32').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/downloadByIdType?id='+id+'&attType=1 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse32").attr('disabled',false);
    });
    
  //发票类进项上传
    var uploader_invoices_in3 = new plupload.Uploader({
        browse_button : 'browse33', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=6&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader_invoices_in3.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_invoices_in3.bind('FilesAdded',function(uploader_invoices_in3,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_invoices_in3.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_invoices_in3.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist33').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse33").attr('disabled',true);
    	uploader_invoices_in3.start();
    });
    uploader_invoices_in3.bind('UploadProgress',function(uploader_invoices_in3,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_invoices_in3.bind('Error',function(uploader_invoices_in3,err){
    	if("failToUnZip"==err.response){
    		uploader_invoices_in3.stop();
	    	$("#browse33").attr('disabled',false);
	    	document.getElementById('filelist33').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票进项类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_invoices_in3.stop();
	    	$("#browse33").attr('disabled',false);
	    	document.getElementById('filelist33').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票进项类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_invoices_in3.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_invoices_in3.bind('UploadComplete',function(uploader_invoices_in3,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist33').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=6 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse33").attr('disabled',false);
    });
    
  //发票类销项上传
    var uploader_invoices_out3 = new plupload.Uploader({
        browse_button : 'browse34', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=7&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader_invoices_out3.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_invoices_out3.bind('FilesAdded',function(uploader_invoices_out3,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_invoices_out3.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_invoices_out3.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist34').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse34").attr('disabled',true);
    	uploader_invoices_out3.start();
    });
    uploader_invoices_out3.bind('UploadProgress',function(uploader_invoices_out3,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_invoices_out3.bind('Error',function(uploader_invoices_out3,err){
    	if("failToUnZip"==err.response){
    		uploader_invoices_out3.stop();
	    	$("#browse34").attr('disabled',false);
	    	document.getElementById('filelist34').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票销项类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_invoices_out3.stop();
	    	$("#browse34").attr('disabled',false);
	    	document.getElementById('filelist34').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票销项类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_invoices_out3.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_invoices_out3.bind('UploadComplete',function(uploader_invoices_out3,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist34').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=7 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse34").attr('disabled',false);
    });
    
    /**
	 * 个人消费贷款-上传
	 */
	
	//图片类上传
    var uploader2 = new plupload.Uploader({
        browse_button : 'browse21', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=isZip&upfiletype=0&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader2.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader2.bind('FilesAdded',function(uploader2,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader2.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader2.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist21').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse21").attr('disabled',true);
    	uploader2.start();
    });
    uploader2.bind('UploadProgress',function(uploader2,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader2.bind('Error',function(uploader2,err){
    	if("failToUnZip"==err.response){
    		uploader2.stop();
	    	$("#browse21").attr('disabled',false);
	    	document.getElementById('filelist21').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader2.stop();
	    	$("#browse21").attr('disabled',false);
	    	document.getElementById('filelist21').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader2.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader2.bind('UploadComplete',function(uploader2,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist21').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=0 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse21").attr('disabled',false);
    });
    
  //文档类上传
    var uploader_doc2 = new plupload.Uploader({
        browse_button : 'browse22', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=revise&zipFlag=docf&upfiletype=1&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Image files", extensions : "jpg,jpeg" }
   		  ],
   		  max_file_size : '1mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
   		multi_selection:false
    });    
    uploader_doc2.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_doc2.bind('FilesAdded',function(uploader_doc2,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_doc2.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_doc2.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist22').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse22").attr('disabled',true);
    	uploader_doc2.start();
    });
    uploader_doc2.bind('UploadProgress',function(uploader_doc2,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_doc2.bind('Error',function(uploader_doc2,err){
    	if("failToUnZip"==err.response){
    		uploader_doc2.stop();
	    	$("#browse22").attr('disabled',false);
	    	document.getElementById('filelist22').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_doc2.stop();
	    	$("#browse22").attr('disabled',false);
	    	document.getElementById('filelist22').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_doc2.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_doc2.bind('UploadComplete',function(uploader_doc2,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist22').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=1 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse22").attr('disabled',false);
    });
	
})

</script>
</body>
</html>