<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.beans.Puser,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath();
    String typeUser = (String)session.getAttribute("typeUser");
    Cuser cuser = null;
    Puser puser = null;
    String loginFlag = "-1";
    if(null!=typeUser && "0".equals(typeUser)){
        loginFlag = "0";
        cuser = (Cuser) session.getAttribute(ConfigUtil.getSessionInfoName());
    }else if(null!=typeUser && "1".equals(typeUser)){
        loginFlag = "1";
        puser = (Puser) session.getAttribute(ConfigUtil.getSessionInfoName());
    }
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
    <script src="<%=basePath %>/scripts/order/order.js"></script>
    
    <script type="text/javascript">
        //var userFlag = '<%=typeUser%>';//0:企业,1:个人
        var loginFlag = "<%=loginFlag%>";//是否登陆
        
        function apply(type){
            if(loginFlag=='0'){
                <%
                    if(null != cuser){
                %>
                    
                var corpName = '<%=cuser.getCorpName()%>';
                var taxSn = '<%=cuser.getTaxSn()%>';
                var apName = '<%=cuser.getApName()%>';
                var mobilephone = '<%=cuser.getMobilephone()%>';
                var idcard = '<%=cuser.getIdcard()%>';
                
                if(corpName=='null' ||corpName==null || corpName=='' || corpName=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                if(taxSn=='null' ||taxSn==null || taxSn=='' || taxSn=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                if(apName=='null' ||apName==null || apName=='' || apName=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                if(mobilephone=='null' ||mobilephone==null || mobilephone=='' || mobilephone=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                if(idcard=='null' ||idcard==null || idcard=='' || idcard=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                location.href = "<%=basePath %>/users/applyLoan/com1?type="+type;
                <%
                    }
                %>
            }else if(loginFlag=='1'){
                <%
                    if(null!=puser){
                %>
                
                var realname = '<%=puser.getRealname()%>';
                var phonenum = '<%=puser.getPhonenum()%>';
                var idcard = '<%=puser.getIdcard()%>';
                if(realname=='null' ||realname==null || realname=='' || realname=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                if(phonenum=='null' ||phonenum==null || phonenum=='' || phonenum=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                if(idcard=='null' ||idcard==null || idcard=='' || idcard=='undefined'){
                    $('.mask_alpha,.fdiv').show();
                    return;
                }
                location.href = "<%=basePath %>/users/applyLoan/per1?type="+type;
                <%
                    }
                %>
            }else if(loginFlag =="-1"){
                location.href = "<%=basePath %>/users/applyLoan/com1?type="+type;
            }
            
            //location.href = "<%=basePath %>/users/applyLoan/loan";
        }
        function userInfo(){
            //alert('完善信息页面!')
            location.href = "<%=basePath %>/users/init/userInfo?showPage=1&page=order";
        }
        
        $(document).ready(function(e) {
            if(loginFlag=='0'){
                $(".company").show();
                $(".personal").hide();
            }else if(loginFlag=='1'){
                $(".company").hide();
                $(".personal").show();
            }else if(loginFlag=='-1'){
                $(".company").show();
                $(".personal").hide();
            }
            
            $(".fdivbtn2").click(function(){
                $(".mask_alpha,.fdiv").hide();
            });
            
            //切换
            $('.tabbox li').click(function(e) {
                $(this).addClass('cur').siblings().removeClass('cur');
                var index=$(this).index();
                $(this).parent().parent().find('.tabcon').removeClass('cur').eq(index).addClass('cur');
            });
        });
        
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
    
    <div class="main product">
        <div class="productlist">
            <h2 class="productlist_h2">企业经营税务贷</h2>
            <div class="productlist_con">
                <div class="productlist_con_top clearfix">
                    <ul class="fl ft14">
                        <li class="fl loantime">放款时间：<i class="colorred">最快3天放款</i></li>
                        <li class="fl rate">贷款利率：<i class="colorred">5%起</i></li>
                        <li class="fl quota">贷款额度：<i class="colorred">上不封顶</i></li>
                    </ul>
                    <a href="javascript:apply(1);" class="productlist_btn fr">填写申请</a>
                </div>
                <ul class="tabbox">
                    <li class="fl cur">客户定位</li><li class="fl">申请条件</li><li class="fl">贷款流程</li>
                </ul>
                <div class="tabcon cur">
                    <ul>
                        <li>1、经国家工商行政管理机关核准登记的小微企业，包含小型企业和微型企业两种；</li>
                        <li>2、符合国家产业政策和信贷政策，依法合规从事生产经营,近两年无环保违法记录和食品安全违法记录；</li>
                        <li>3、在当地（北京）具有固定的经营场所。若经营场所为租赁，需按时交纳房屋租金；</li>
                        <li>4、信誉良好，通过中国人民银行企业征信系统查询近5年无不良信用记录；</li>
                        <li>5、无参与高利贷、洗钱、地下钱庄等交易行为，无购买期货等高风险经营行为，且其实际控制人（及其配偶）无涉黑、参与高利贷等违法行为。</li>
                    </ul>
                </div>
                <div class="tabcon">
                    <ul>
                        <li>1、经工商行政管理部门核准登记，持有人民银行核发的贷款卡（证），并年检有效；</li>
                        <li>2、符合国家产业政策和银行信贷政策，无任何违法记录；</li>
                        <li>3、企业能够提供完备的纳税信息；</li>
                        <li>4、企业成立且实际经营2年（含）以上，主营业务突出；</li>
                        <li>5、企业主从事本行业3年（含）以上；</li>
                        <li>6、企业近2年按时足额缴税，无不良纳税记录。</li>
                        <li>7、其他银行所需材料</li>
                    </ul>
                </div>
                <div class="tabcon productlist_process">
                    <ul class="clearfix pt10">
                        <li><img src="<%=basePath %>/images/order/pro1.jpg" alt=""><br/><span>提交申请</span></li>
                        <li class="arr"><img src="<%=basePath %>/images/order/pro.jpg" alt=""></li>
                        <li><img src="<%=basePath %>/images/order/pro2.jpg" alt=""><br/><span>量身定制贷款方案</span></li>
                        <li class="arr"><img src="<%=basePath %>/images/order/pro.jpg" alt=""></li>
                        <li><img src="<%=basePath %>/images/order/pro3.jpg" alt=""><br/><span>机构审批</span></li>
                        <li class="arr"><img src="<%=basePath %>/images/order/pro.jpg" alt=""></li>
                        <li><img src="<%=basePath %>/images/order/pro4.jpg" alt=""><br/><span>资金到位</span></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="productlist">
            <h2 class="productlist_h2">企业法人税务贷</h2>
            <div class="productlist_con">
                <div class="productlist_con_top clearfix">
                    <ul class="fl ft14">
                        <li class="fl loantime">放款时间：<i class="colorred">最快当天放款</i></li>
                        <li class="fl rate">贷款利率：<i class="colorred">8%起</i></li>
                        <li class="fl quota">贷款额度：<i class="colorred">最高200万元</i></li>
                    </ul>
                    <a href="javascript:apply(3);" class="productlist_btn fr">填写申请</a>
                </div>
                <ul class="tabbox">
                    <li class="fl cur">客户定位</li><li class="fl">申请条件</li><li class="fl">贷款流程</li>
                </ul>
                <div class="tabcon cur">
                    <ul>
                        <li>1、公司股东和企业法人代表:在公司章程、工商登记信息等有效法律文件中登记占有10％以上（含）股份的人及企业的法人代表；</li>
                        <li>2、依法成立的个人独资企业的负责人；</li>
                        <li>3、依法成立的个体工商户的负责人。</li>
                    </ul>
                </div>
                <div class="tabcon">
                    <ul>
                        <li>1、具有完全民事行为能力的中国公民，并持有第二代身份证；</li>
                        <li>2、提供企业相关纳税信息；</li>
                        <li>3、持续缴纳税款满一年；</li>
                        <li>4、无不良信用记录；</li>
                        <li>5、能够提供完税证明；</li>
                        <li>6、年销售额不低于100万元。</li>
                    </ul>
                </div>
                <div class="tabcon productlist_process">
                    <ul class="clearfix pt10">
                        <li><img src="<%=basePath %>/images/order/pro1.jpg" alt=""><br/><span>提交申请</span></li>
                        <li class="arr"><img src="<%=basePath %>/images/order/pro.jpg" alt=""></li>
                        <li><img src="<%=basePath %>/images/order/pro2.jpg" alt=""><br/><span>量身定制贷款方案</span></li>
                        <li class="arr"><img src="<%=basePath %>/images/order/pro.jpg" alt=""></li>
                        <li><img src="<%=basePath %>/images/order/pro3.jpg" alt=""><br/><span>机构审批</span></li>
                        <li class="arr"><img src="<%=basePath %>/images/order/pro.jpg" alt=""></li>
                        <li><img src="<%=basePath %>/images/order/pro4.jpg" alt=""><br/><span>资金到位</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    
    <div class="mask_alpha" style="display: none;"></div>
    <div class="fdiv"  style="display: none;">
        <p>您需要完善用户信息后才能申请贷款！</p>
        <div><a class="fdivbtn1" href="javascript:userInfo();">去完善</a><a class="fdivbtn2">取消</a></div>
    </div>
    <%@include file="../base/footer.html" %>
    
</body>
</html>