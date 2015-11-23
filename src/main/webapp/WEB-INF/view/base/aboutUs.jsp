<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>关于我们</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
</head>
<body>
<!--headeer开始-->
<iframe src="<%=basePath %>/basic/head" width="100%" height="75px" frameborder="0" scrolling="no"></iframe>
<!--headeer结束-->
<div class="content center clearfix">
	<div class="content_l">
        <h3>关于我们</h3>
        <ul>
        	<li class="current">公司介绍</li>
        	<li>联系我们</li>
        </ul>
    </div>
	<div class="content_r">
    	<ul class="helpcenter">
        	<li class="current">
            	<div class="act_con_text">
                	<p>百望银税通基于企业大数据尤其以纳税信息为代表，通过“互联网+税票”打造互联网银税平台，满足纳税企业发展所需的相关信息和金融服务，为广大纳税企业提供专业、高效、安全和低成本的财税互联网增值服务。</p>
                    <br/>
                    <p>团队介绍</p>
                    <br/>
                    <p class="ft16 ftcblue">总裁（CEO） 蓝鹏</p>
                    <p>- 北京大学经济学学士，英国爱丁堡大学MBA与金融学双硕士。</p>
                    <p>- 曾担任大型金融投资集团副总裁及投资担保公司总经理。</p>
                    <p>- 总体负责金融产品设计包装、战略及大客户营销。</p>
                    <br/><br/>
                    <p class="ft16 ftcblue">首席技术官（CTO） 陶鹏</p>
                    <p>- 上海交大TR班（ACM班前身）通信专业学士，美国霍普金斯大学统计模式识别专业博士，JCGS某年度最佳论文得主。</p>
                    <p>- 多年硅谷大数据研发经验，直接汇报对象为IBM全球科学院前院长/高级副总裁、NetIQ（Nasdaq 上市公司）前董事长/CEO。</p>
                    <p>- 回国创业开发多款领先大数据产品-含金融方向应用，北京及山东省特聘专家。</p>
                    <p>- 总体负责大数据方向及互联网方面的产品设计、战略、研发。</p>
                    <br/><br/>
                    <p class="ft16 ftcblue">首席风险官（CRO）陈飞</p>
                    <p>- 布鲁塞尔大学国际经济学硕士，浙江大学经济学学士</p>
                    <p>- 在中外刊物发表金融风险管理与金融管理实践等方面论文近二十篇</p>
                    <p>- 在中欧知名金融机构如中国工商银行、法国Dexia银行、Sungard等世界五百强大型金融机构从事银行金融工作十余年，回国后在外企工作期间作为风险管理专家长驻多个大型银行总行提供风险管理咨询，曾在国资委控大型投资公司任首席风险官多年</p>
                    <p>- 负责金融信贷业务的全面风险管理（ERM）</p>
                </div>
            </li>
            <li>
            	<div class="act_con_text">
                	<p>公司地址：北京市海淀区马连洼北路59号亿城国际中心7层</p><br/>
                    <p>客服电话：4000665858</p><br/>
                    <p>传真：010—84782593</p><br/>
                    <p>网址：www.yinshuitong.com</p><br/>
                    <img src="<%=basePath %>/images/index/addpic.jpg" width="760"/>
                </div>
            </li>
        </ul>
    </div>
</div>

<!--底部-->
<%@ include  file="footer.html"%>
<!---->
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
