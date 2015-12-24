<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath();%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>百望银税通</title>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/common/base.css" />
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/index/index.css" />
    <script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
    <style type="text/css">
   		.mainn{ width: 1000px;height:600px; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
    </style>
</head>
<body>
<!--headeer开始-->
<iframe src="<%=basePath%>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<!--headeer结束-->
<div class="banner_wrap">
    <div class="banner">
        <a href="<%=basePath %>/product/list" class="btn"><img src="<%=basePath %>/images/index/banner_btn.png" alt=""></a>
    </div>
</div>
<div class="index_icon center">
    <ul class="clearfix">
        <li>
            <img src="<%=basePath%>/images/index/ind_icon1.jpg" alt="">
            <p>增值税满10万</p>
            <span> 年缴增值税10万即可申请，贷款利率仅5%起</span>
        </li>
        <li>
            <img src="<%=basePath%>/images/index/ind_icon2.jpg" alt="">
            <p>最快当天到账</p>
            <span>  线上递交资料，线下签字放款，最快当天拿</span>
        </li>
        <li>
            <img src="<%=basePath%>/images/index/ind_icon3.jpg" alt="">
            <p>额度上不封顶</p>
            <span> 额度高，4倍年缴增值税额度，还有加权</span>
        </li>
        <li>
            <img src="<%=basePath%>/images/index/ind_icon4.jpg" alt="">
            <p>用款随借随还</p>
            <span>还款方式灵活，用款随借随还，只计用款利息</span>
        </li>
        <li>
            <img src="<%=basePath%>/images/index/ind_icon5.jpg" alt="">
            <p>税票即可贷款</p>
            <span>根据税票情况随时提升额度，税信者易贷</span>
        </li>
        <li class="mr0">
            <img src="<%=basePath%>/images/index/ind_icon6.jpg" alt="">
            <p>操作简单容易</p>
            <span>平台功能简单实用，操作容易，申请方便</span>
        </li>
    </ul>
</div>

<div class="indextable">
	<table class="ac ft14">
    	<tr class="ft12">
        	<th>银行名称</th>
        	<th>产品名称</th>
        	<th>授信额度</th>
        	<th>参考利率</th>
        	<th>产品特点</th>
        	<th>&nbsp;</th>
        </tr>
        <tr>
        	<td><img src="<%=basePath%>/images/common/jh_bank.png"/></td>
        	<td><i class="ftb">税e贷</i></td>
        	<td>最高<i class="ft24 red">200</i>万元</td>
        	<td>年化利率<i class="ft20 red">8.8%</i></td>
        	<td>无抵押，无担保，纯信用贷款，随借随还</td>
        	<td><a href="javascript:;" onclick="javascript:productDetail(1)">查看详情</a></td>
        </tr>
        <tr>
        	<td><img src="<%=basePath%>/images/common/jt_bank.png"/></td>
        	<td><i class="ftb">税金贷</i></td>
        	<td>最高<i class="ft24 red">100</i>万元</td>
        	<td>日利率<i class="ft24 red">0.3‰-0.4‰</i>/天</td>
        	<td>线上操作，随借随还，最快2天放款，便捷高效。</td>
        	<td><a href="javascript:;" onclick="javascript:productDetail(2)">查看详情</a></td>
        </tr>
    </table>
</div>
 <div class="partner center">
<!--     <h3>合作伙伴</h3> -->
    <img src="<%=basePath %>/images/index/bank.png" alt="">
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

function productDetail(id){
	location.href='<%=basePath %>/product/detail?id='+id;
// 	$('#id').val(id);
// 	$('#form').submit();
}
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