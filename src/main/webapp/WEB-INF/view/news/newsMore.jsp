<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司动态</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/index/index.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
</head>
<body>
<!--headeer开始-->
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<!--headeer结束-->
<div class="content center clearfix">
	<div class="content_l">
        <h3>公司动态</h3>
    </div>
	<div class="content_r">
        <div class="act">
        	<h4>公司动态</h4>
            <ul class="act_list">
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2" target="_self">
                	<span class="act_list_l">7月份金融数据点评:关注企业贷款结构的恶化</span>
                    <span class="act_list_r">2015-08-18</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-2">
                	<span class="act_list_l">中小企业贷款难的金融环境解析</span>
                    <span class="act_list_r">2015-08-03</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-3">
                	<span class="act_list_l">关于帮扶中小企业发展的调查与思考</span>
                    <span class="act_list_r">2015-08-14</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-4">
                	<span class="act_list_l">货币紧缩 没有赢家</span>
                    <span class="act_list_r">2015-04-15</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-5">
                	<span class="act_list_l">银行贷款难依然是中小企业融资的瓶颈</span>
                    <span class="act_list_r">2015-08-14</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-6">
                	<span class="act_list_l">中小微企业为啥越来越难做</span>
                    <span class="act_list_r">2015-08-19</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-7">
                	<span class="act_list_l">中小企业从银行贷款的流程和注意事项</span>
                    <span class="act_list_r">2014-01-13</span>
                </a></li>
            	<li class="clearfix"><a href="<%=basePath %>/basic/news?news_flag=news2-8">
                	<span class="act_list_l">银行贷款为什么放不出去？</span>
                    <span class="act_list_r">2015-04-09</span>
                </a></li>
            </ul>
            <div class="page_turn_box clearfix">
                <div class="page_turn clearfix">
                	<!--
                    <a href="javascript:;" class="left"></a>
                    <a href="javascript:;" class="current">1</a>
                    <a href="javascript:;">2</a>
                    <a href="javascript:;">3</a>
                    <a href="javascript:;" class="more">...</a>
                    <a href="javascript:;">4</a>
                    <a href="javascript:;">5</a>
                    <a href="javascript:;" class="right"></a>
                    -->
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部-->
<%@include file="../base/footer.html" %>
<!---->
</body>
</html>
