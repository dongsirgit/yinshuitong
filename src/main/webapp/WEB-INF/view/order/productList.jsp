<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表</title>
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>/styles/index/index.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
    <div class="main">
    	<div class="crumbsbox"><a href="<%=basePath %>" class="ft18">首页</a> &gt; <span>贷款产品列表</span></div>
    	<div class="indextable prolist_tab">
        <table class="ac ft14" id='productList'>
            <tr class="ft12">
                <th>银行名称</th>
                <th>产品名称</th>
                <th>授信额度</th>
                <th>参考利率</th>
                <th>产品特点</th>
                <th>&nbsp;</th>
            </tr>
        </table>
    </div>
    <p class="pro_list_p">更多银行贷款产品，敬请期待...</p>
    <form id='form' action="<%=basePath %>/product/detail" method="post">
   		<input type="hidden" id="id" name='id'>
   	</form>
    </div>
    <script type="text/javascript">
    	$('#productList').delegate('tbody tr','mouseover',function(){
    		$(this).css('background','#fffceb');
    	})
    	$('#productList').delegate('tbody tr','mouseout',function(){
    		$(this).css('background','#fff');
    	})
    	//页面初始化
    	$.ajax({
    		type:"POST",
    		url:"<%=basePath %>/getproList",
    		asysn:false,
    		dataType:"JSON",
    		success:function(data){
    			if(data != null && data.list != null && data.list.length > 0){
    				$.each( data.list, function(i, p){
    					$('#productList').append("<tr>"
    						+ "<td ><img src='<%=basePath %>/images/product/"+p.logoUrl+"'></td>"
    						+ "<td >"+ p.pname + "</td>" 
    			    		+ "<td>" + p.sxed + "</td>" 
    			    		+ "<td>" + p.ckll + "</td>" 
    			    		+ "<td>" + p.cptd + "</td>" 
    			    		+ "<td><a href=javascript:; onclick='javascript:productDetail("+p.id+")'>查看详情</a></td>"
    			    		+ "</tr>");
    				});
    			}else{
    				$('#productList tbody').html("无记录");
    			}
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert("加载失败!");
	        }
    	});
    	
    	function productDetail(id){
    		$('#id').val(id);
    		$('#form').submit();
    	}
    	
    </script>
    <%@include file="../base/footer.html" %>
</body>
</html>