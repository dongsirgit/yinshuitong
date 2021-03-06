<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	String dmsh;
	if(null!=basePath&&basePath.trim().length()>0){
	dmsh =request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+basePath;//内网
	}else{
	dmsh =request.getScheme()+"://"+ request.getServerName();//外网
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邀请码</title>
	<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>/styles/help/help.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/index/index.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/login/login.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>/styles/order/order.css" type="text/css">
	<script src="<%=basePath %>/scripts/common/jquery.min.js"></script>
	<script src="<%=basePath %>/scripts/common/common.js"></script>
	<script src="<%=basePath %>/scripts/common/jquery.simplePagination.js"></script>
	<link href="<%=basePath %>/styles/common/simplePagination.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript">
	    if (window.self == window.top) {
	        window.top.location ="<%=basePath %>/users/init/userInfo";
	    }
		var inviteeTotal = 0;
		var awardTotal = 0;
	</script>
	
</head>
<body class="listh4"><!-- content字体颜色变暗 -->
	    	<ul class="loan_list">
	        	<li class="current">
	            	<h4 class="listh4">邀请好友</h4>
	                <P class="invitetop">凡是通过您的专属链接或邀请码注册的用户，在成功申请贷款后，您都会获得一定的现金奖励，工作人员会通过账户信息里的联系方式联系您，处理返利事宜，请及时
	                	<a href="<%=basePath %>/users/init/userInfo?showPage=1" target="_top">完善账户信息</a>>></P>                <div class="invitenum">
	                	<p class="myinvitenum">我的邀请码：<span class="red" id="inviteCode"></span></p>
	                    <p class="invitlink clearfix">专属链接：<span id="link_inviteCode" class="red"></span><a href="javascript:copyLink();">复制链接</a></p>
	                    <p class="invitepro">友情提示：由于不同浏览器的安全设置不同，如遇不能复制请手工复制</p>
	                </div>
	                <div class="invitetab">
	                	<ul class="invitetabtop clearfix">
	                    	<li class="cur">邀请的人</li>
	                        <li>我的奖励</li>
	                    </ul>
	                    <div class="invitetable_box cur">
	                    	<div id='div_invitee' class="invitetable_box_top">
		                        <table id="invitee" class="invitetable invitetable1">
		                            <!-- <tr>
		                                <th>邀请的用户</th>
		                                <th>注册时间</th>
		                                <th>是否获得贷款</th>
		                            </tr> -->
		                        </table>
	                        </div>
	                        <p id="NoInvitee" class="invitepro pt10" style="display: none;">您还没有邀请记录</p><!--没有邀请记录显示，有邀请记录隐藏-->
	                        <div class="page_turn_box clearfix invitepage ac">
	                        <div id="paging1"></div>
	                        </div>
	                        
	                    </div>
	                    <div class="invitetable_box">
	                    	<div id='div_award' class="invitetable_box_top">
		                        <table id="award" class="invitetable invitetable2">
		                        </table>
	                        </div>
	                        <p id="NoInvitee2" class="invitepro pt10" style="display: none;">您还没有邀请记录</p><!--没有邀请记录显示，有邀请记录隐藏-->
	                        <div class="page_turn_box clearfix invitepage ac">
	                            <div id="paging2"></div>
	                        </div>
	                    </div>
	                </div>
	            </li>
	        </ul>        
	
	<script type="text/javascript">
		init(1);
		p1();
		$(function(){
			
			$('.invitetabtop li').click(function(e) {
		        $(this).addClass('cur').siblings().removeClass('cur');
				var index=$(this).index();
				$('.invitetable_box').eq(index).addClass('cur').siblings().removeClass('cur');
				if(index==0){
					init(1);
					p1();
				}
				if(index==1){
					initAward(1);
					p2();
				}
				parent.dyniframesize('openli');
		    });
		})
		
		function p1(){
			$("#paging1").pagination({
		        items: inviteeTotal, 
		        itemsOnPage: 10,
		        cssStyle: 'light-theme',
		        onPageClick: function(pageNumber, event) {
					init(pageNumber)
				},
				onInit: function() {
					
				}
		    });
		}
		function p2(){
			$("#paging2").pagination({
		        items: awardTotal, 
		        itemsOnPage: 10,
		        cssStyle: 'light-theme',
		        onPageClick: function(pageNumber, event) {
		        	initAward(pageNumber);
				},
				onInit: function(pageNumber) {
					initAward(pageNumber);
					this.items=awardTotal;
				}
		    });
		}
		//邀请人列表
		function init(page){
			var	params = {"page":page};
			$.ajax({
				type:"POST",
		        url: "<%=basePath%>/users/applyLoan/queryInviter",
		        data: params,
		        async: false,
		        success: function (data) {
		        	//alert(JSON.stringify(data))
		        	if(data.success=='1'){
		        		$("#inviteCode").text(data.inviteCode)
		        		$("#link_inviteCode").text("<%=dmsh%>/user/forwardRegist?inviteid="+data.inviteCode);
		        		inviteeTotal = data.inviteeTotal;
		        		awardTotal = data.awardTotal;
		        		if(data.list.length>0){
		        			$("#div_invitee").show();
		        			$("#NoInvitee").hide();
		        			$("#invitee").children().remove();
		        			$("#invitee").append('<tr>'
	        						+'<th>邀请的用户</th>'
	        						+'<th>注册时间</th>'
	        						+'<th>是否获得贷款</th>'
	        						+'</tr>');
		        			for(var i=0;i<data.list.length;i++){
		        				$("#invitee").append('<tr>'
		        						+'<td class="ftcolorff8">'+data.list[i].userName+'</td>'
		        						+'<td>'+data.list[i].regTime+'</td>'
		        						+'<td>'+data.list[i].sucLoan+'</td>'
		        						+'</tr>');
		        			}
		        			$('.invitetable1 tr:odd').css('background','#fffceb');
		        			parent.dyniframesize('openli');
		        		}else{
		        			$("#div_invitee").hide();
		        			$("#NoInvitee").show();
		        			parent.dyniframesize('openli');
		        		}
		        	}
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	if(XMLHttpRequest.responseText=="timeOut"){
		        		location.reload();
		        	}else{
		        		alert("Error");
		        	}
		        }
			});
		}
		
		//奖励列表
		function initAward(page){
			var	params = {"page":page};
			$.ajax({
				type:"POST",
		        url: "<%=basePath%>/users/applyLoan/queryMyAward",
		        data: params,
		        async: false,
		        success: function (data) {
		        	//alert(JSON.stringify(data))
		        	if(data.success=='1'){
		        		awardTotal = data.awardTotal;
		        		//alert("awardTotal="+awardTotal)
		        		if(data.list.length>0){
		        			$("#div_award").show();
		        			$("#NoInvitee2").hide();
		        			$("#award").children().remove();
		        			$("#award").append('<tr>'
	        						+'<th>来源用户</th>'
	        						+'<th>贷款金额</th>'
	        						+'<th>奖励金额</th>'
	        						+'<th>奖励状态</th>'
	        						+'</tr>');
		        			for(var i=0;i<data.list.length;i++){
		        				var sta = data.list[i].awardStatus =='2'?"已返利":"待返利";
		        				$("#award").append('<tr>'
		        						+'<td class="ftcolorff8">'+data.list[i].userName+'</td>'
		        						+'<td>'+data.list[i].approveQuota+'</td>'
		        						+'<td>'+data.list[i].award+'</td>'
		        						+'<td>'+sta+'</td>'
		        						+'</tr>');
		        			}
		        			$('.invitetable2 tr:odd').css('background','#fffceb');
		        			parent.dyniframesize('openli');
		        		}else{
		        			$("#div_award").hide();
		        			$("#NoInvitee2").show();
		        			parent.dyniframesize('openli');
		        		}
		        	}
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	if(XMLHttpRequest.responseText=="timeOut"){
		        		location.reload();
		        	}else{
		        		alert("Error");
		        	}
		        }
			});
		}
		
		function copyLink(){
			/* if (window.clipboardData) {
		        window.clipboardData.setData("Text",$("#link_inviteCode").text());   
		    } */
			var a = $('#link_inviteCode').text();
			copyToClipBoard(a);
		}
		function copyToClipBoard(s) {
	        if (window.clipboardData) {
	            window.clipboardData.setData("Text", s);
	            alert("已经复制到剪切板！"+ "\n" + s);
	        } else if (navigator.userAgent.indexOf("Opera") != -1) {
	            window.location = s;
	            alert("已经复制到剪切板！"+ "\n" + s);
	        } else if (window.netscape) {
	            try {
	                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	            } catch (e) {
	                alert("该浏览器不支持一键复制！请手工复制！");
	                return;
	            }
	            var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
	            if (!clip)
	                return;
	            var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
	            if (!trans)
	                return;
	            trans.addDataFlavor('text/unicode');
	            var str = new Object();
	            var len = new Object();
	            var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
	            var copytext = s;
	            str.data = copytext;
	            trans.setTransferData("text/unicode", str, copytext.length * 2);
	            var clipid = Components.interfaces.nsIClipboard;
	            if (!clip)
	                return false;
	            clip.setData(trans, null, clipid.kGlobalClipboard);
	            alert("已经复制到剪切板！" + "\n" + s);
	        }else{
	        	alert("该浏览器不支持一键复制！请手工复制！");
	        }
	    }
		
	</script>
</body>
</html>