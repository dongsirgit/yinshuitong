<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>百望银税通</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/index/index.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
</head>
<body>
<!--headeer开始-->
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<!--headeer结束-->
<div class="banner_wrap">
	<div class="banner">
        <a href="<%=basePath %>/basic/productList" class="btn"><img src="<%=basePath %>/images/index/banner_btn.png" alt=""></a>
        <div class="calculatorbox">
        	<h3 class="ft20 ac">额度预估</h3>
            <div class="calculatecon cur">
            	<div class="calculatecon_choose pt23">贷款产品：<span class="cur">企业经营税务贷</span><span>企业法人税务贷</span></div>
                <div class="banfill cur">
                	<div class="banfillinfor"><span>增值税纳税税额：</span><input type="text" id="tax1" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="3" size="14"/><i>万元</i></div>
                	<div class="banfillinfor"><span>营业税纳税税额：</span><input type="text" id="tax2" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="3" size="14"/><i>万元</i></div>
                	<div class="banfillinfor"><span>所得税纳税税额：</span><input type="text" id="tax3" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="3" size="14"/><i>万元</i></div>
                </div>
                <div class="banfill">
                	<div class="banfillinfor"><span>增值税纳税税额：</span><input type="text" id="tax4" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="3" size="14"/><i>万元</i></div>
                </div>
                <p class="bannerror"><span id="errMsg"></span></p>
            </div>
            <div class="banresult clearfix ac"><p class="fl resultmnum"><span><em class="mr5">额度:</em></span><span><i id="result" class="red mr10"></i>万元</span></p><a href="javascript:;" class="fr ft22" onclick="calc()">预估一下</a></div>
        </div>
    </div>
</div>

<div class="index_icon center">
	<ul class="clearfix">
    	<li>
        	<img src="<%=basePath %>/images/index/ind_icon1.jpg" alt="">
            <p>增值税满10万</p>
            <span> 年缴增值税10万即可申请，贷款利率仅5%起</span>
        </li>
    	<li>
        	<img src="<%=basePath %>/images/index/ind_icon2.jpg" alt="">
            <p>最快当天到账</p>
            <span>  线上递交资料，线下签字放款，最快当天拿</span>
        </li>
    	<li>
        	<img src="<%=basePath %>/images/index/ind_icon3.jpg" alt="">
            <p>额度上不封顶</p>
            <span> 额度高，4倍年缴增值税额度，还有加权</span>
        </li>
    	<li>
        	<img src="<%=basePath %>/images/index/ind_icon4.jpg" alt="">
            <p>用款随借随还</p>
            <span>还款方式灵活，用款随借随还，只计用款利息</span>
        </li>
    	<li>
        	<img src="<%=basePath %>/images/index/ind_icon5.jpg" alt="">
            <p>税票即可贷款</p>
            <span>根据税票情况随时提升额度，税信者易贷</span>
        </li>
    	<li class="mr0">
        	<img src="<%=basePath %>/images/index/ind_icon6.jpg" alt="">
            <p>操作简单容易</p>
            <span>平台功能简单实用，操作容易，申请方便</span>
        </li>
    </ul>
</div>

<div class="indextable">
	<table class="ac ft14">
    	<tr class="ft12">
        	<th>贷款产品名称</th>
        	<th>放款时间</th>
        	<th>贷款利率</th>
        	<th>贷款额度</th>
        	<th>客户定位</th>
        	<th>&nbsp;</th>
        </tr>
        <tr>
        	<td><i class="ftb">企业经营税务贷</i></td>
        	<td>最快<i class="ft24 red">3天放款</i></td>
        	<td><i class="ft24 red">5%</i>起</td>
        	<td><i class="ft24 red">上不封顶</i></td>
        	<td>经国家工商行政管理机关核准登记的小微企业</td>
        	<td><a href="<%=basePath %>/basic/productList">查看详情</a></td>
        </tr>
        <tr>
        	<td><i class="ftb">企业法人税务贷</i></td>
        	<td>最快<i class="ft24 red">当天放款</i></td>
        	<td><i class="ft24 red">8%</i>起</td>
        	<td>最高<i class="ft24 red">200万元</i></td>
        	<td>公司股东和企业法人代表;个人独资企业的负责人</td>
        	<td><a href="<%=basePath %>/basic/productList">查看详情</a></td>
        </tr>
    </table>
</div>

<div class="partner center">
	<h3>合作伙伴</h3>
	<img src="<%=basePath %>/images/index/bank.jpg" alt="">
</div>
<%@include file="WEB-INF/view/base/footer.html" %>
<script type="text/javascript">
$(function(){
	$('.calculatecon_choose span').click(function(e) {
        var index=$(this).index();
		$(this).addClass('cur').siblings('span').removeClass('cur');
		$('.banfill').eq(index).addClass('cur').siblings('.banfill').removeClass('cur');
		$("#errMsg").text("");
		$("#tax1").val("");
		$("#tax2").val("");
		$("#tax3").val("");
		$("#tax4").val("");
		$("#result").text("");
    });
	$('.index_icon img,.index_icon span').hover(function() {
        $(this).parent().children('span').show();
    },function(){
		$(this).parent().children('span').hide();
		});
})
</script>
<script type="text/javascript">
	function calc(){
		var loanType = $(".calculatecon_choose.pt23 .cur").text();
		if(loanType == "企业经营税务贷"){
			var tax1 = $("#tax1").val();
			var tax2 = $("#tax2").val();
			var tax3 = $("#tax3").val();
		
			
			if(isNaN(tax1)){
				$("#errMsg").text("× 请输入正确的增值税纳税税额");
				return;
			}else if(tax1<=0 || tax1 >999){
				$("#errMsg").text("× 请输入正确的增值税纳税税额");
				return;
			}else{
				$("#errMsg").text("");
			}
			
			if(isNaN(tax2)){
				$("#errMsg").text("× 请输入正确的营业税纳税税额");
				return;
			}else if(tax2<=0 || tax2 >999){
				$("#errMsg").text("× 请输入正确的营业税纳税税额");
				return;
			}else{
				$("#errMsg").text("");
			}
			
			if(isNaN(tax3)){
				$("#errMsg").text("× 请输入正确的所得税纳税税额");
				return;
			}else if(tax3<=0 || tax3 >999){
				$("#errMsg").text("× 请输入正确的所得税纳税税额");
				return;
			}else{
				$("#errMsg").text("");
			}
			var money = (parseFloat(tax1)+parseFloat(tax2))*5+parseFloat(tax3)*7;
			
			$("#result").html(money);
			
		}else{
			var tax4 = $("#tax4").val();
			
			if(isNaN(tax4)){
				$("#errMsg").text("× 请输入正确的增值税纳税税额");
				return;
			}else if(tax4<=0 || tax4 >999){
				$("#errMsg").text("× 请输入正确的增值税纳税税额");
				return;
			}else{
				$("#errMsg").text("");
			}
			var money =parseFloat(tax4)*4
			if(money >= 200){
				$("#result").html(200);
			}else{
				$("#result").html(money);
			}
		}
	}
</script>
</body>
</html>
