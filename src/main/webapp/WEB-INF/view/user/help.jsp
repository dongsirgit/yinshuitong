<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>帮助中心</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/help/help.css" />
	<script src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
</head>
<body>
<iframe src="<%=basePath%>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div class="content center clearfix">
	<div class="content_l">
        <h3>帮助中心</h3>
        <ul>
        	<li class="current">如何申请</li>
        	<li>贷款审核</li>
        </ul>
    </div>
	<div class="content_r">
    	<ul class="helpcenter">
        	<li class="current">
            	<p><span class="ft16">借款申请条件有哪些？</span><br/>不同贷款产品的申请人需要符合不同的申请条件，您需要登录后才能查看详细的贷款产品介绍和申请条件。</p>
                <br/>
                <p>
                	<span class="ft16">借款申请步骤有哪些？</span><br/>
                	1. 选择借款产品<br/>
                	2. 填写借款申请<br/>
                	3. 填写个人信息和上传认证资料<br/>
                	4. 通过审核<br/>
                	5. 获得借款<br/>
                </p>
                <br/>
                <p>
                	<span class="ft16">如何填写贷款申请表？</span><br/>
                	1.登录网站，点击【去贷款】；<br/>
                	2.进入第①步【贷款产品列表】，了解不同贷款产品的介绍和条件，根据需要选择“企业法人税务贷”、“企业经营税务贷”之一；进入第②步【填写贷款申请】，按要求填写贷款信息并上传认证材料，点击【正式提交】，提交贷款申请。<br/>
                </p>
                <br/>
                <p>
                	<span class="ft16">如何修改或取消借款申请？</span><br/>
                    在您正式提交贷款申请后，将无法自行修改或取消申请，请您在提交时注意认真填写各项信息，确保信息的正确性和真实性。如遇特殊情况需要修改或取消申请时，您可以联系客服，工作人员将会帮助您解决问题。
                </p>
            </li>
            <li>
            	<p>
                	<span class="ft16">什么是信用审核？</span><br/>
                    申请贷款的用户需要根据不同的产品填写贷款信息，包括个人信息、家庭信息、工作信息、资产信息、信用信息，并提交相应的信用认证材料。信审部门会综合评估贷款人的个人、家庭、工作、资产、信用情况，最终根据贷款人的整体信用资质给出相应的信用分数、信用等级及借款额度。
                </p>
                <br/>
            	<p>
                	<span class="ft16">提交申请后何时开始审核？审核时间多久？</span><br/>
                    您正式提交贷款申请后，工作人员将于7个工作日内为您审核完成。
                </p>
                <br/>
            	<p>
                	<span class="ft16">如何查看我的审核进度和结果？</span><br/>
                   登录百望银税通网站，进入‘个人中心--贷款列表’页面，可以查看到保存或已经提交的贷款申请记录。点击申请记录的‘贷款金额’，即可查看申请的详细信息，以及页面上方可看到申请的审核状态和审核备注信息。
                </p>
                <br/>
                <p>
                	<span class="ft16">贷款申请未被通过的原因有哪些？</span><br/>
                    <!-- <span>借款用户的申请未被通过，可能由以下一个或多个原因造成：</span><br/> -->
                	1. 在现单位的在职时间太短；<br/>
                	2. 年龄不符合要求；<br/>
                	3. 收入不符合要求；<br/>
                	4. 工资以现金方式发放，收入无法认定；<br/>
                	5. 还款能力不足，负债比过高；<br/>
                	6. 工作和收入稳定性不足；<br/>
                	7. 信用记录不良；<br/>
                	8. 未按要求提供资料或提供的资料不够详尽；<br/>
                	9. 系统综合信用评分不足。<br/>
                </p>
            </li>
        </ul>
    </div>
</div>
<%@include file="../base/footer.html" %>
<script type="text/javascript">
$(function(){
	$('.content_l ul li').click(function(e) {
        var index=$(this).index();
		$(this).addClass('current').siblings().removeClass('current');
		$('.content_r ul li').eq(index).css('display','block').siblings().css('display','none');
    });
})
</script>
</body>
</html>
