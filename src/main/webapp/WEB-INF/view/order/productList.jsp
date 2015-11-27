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
    <link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
    <%-- <script src="<%=basePath %>/scripts/common/jquery.base64.js"></script> --%>
    <style type="text/css">
   		.mainn{ width: 1000px;height:600px; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
    </style>
    
    <script type="text/javascript">
        
    </script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
    
    <div class="mainn">
    	<table style='border-collapse:collapse;' id='protectList' border="1" bordercolor="#afaeac" width="1000px;" rules="rows"><!--  -->
    		<thead>
    			<td align="left" height="60px;" colspan="7">
    				<p style="font-size:16px;"><a href="<%=basePath %>/"><span style="font-size:16px;">首页</span></a>
    				<span style="font-size:13px;">&nbsp; &gt;&nbsp; </span>
    				<span style="font-size:13px;">贷款产品列表</span></p>
    			</td>
    		</thead>
    		<thead height="40px;">
    			<th colspan="2">贷款名称</th>
    			<th width="180">授信额度</th>
    			<th width="180">参考利率</th>
    			<th>产品特点</th>
    			<th width="10"></th>
    			<th width="150px;"></th>
    		</thead>
    		<tbody id="list" align="center">
    			<%-- <tr height="80px;">
    				<td width="80" align="right"><img src='<%=basePath %>/images/product/bank_jh.png'></td>
    				<td width="100" align="left">建设银行<br/>税e贷</td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td><button onclick="javascript:location.href='<%=basePath %>/">查看详情</button></td>
    			</tr> --%>
    		</tbody>
    		<tfoot  align="center">
    			<tr height="50px;">
    				<td colspan="7"></td>
    			</tr>
    		</tfoot>
    	</table>
        
    </div>
    
    
    <script type="text/javascript">
    	//$('#protectList tbody').empty();
    	$('#protectList tbody tr').mouseover(function(){
    		$(this).css('background','#fffceb');
    	});
    	$('#protectList tbody tr').mouseout(function(){
    		$(this).css('background','#fff');
    	});
    	
    	//页面初始化
    	$.ajax({
    		type:"POST",
    		url:"<%=basePath %>/getproList",
    		asysn:false,
    		dataType:"JSON",
    		//dataType:"text",
    		success:function(data){
    			//alert(data);
    			if(data != null && data.list != null && data.list.length > 0){
    				$.each( data.list, function(i, p){
    					$('#protectList tbody').append("<tr height='80px;'>"
    						+ "<td width='80' align='right'><img src='<%=basePath %>/images/product/"+p.icoUrl+"'></td>"
    						+ "<td width='100' align='left'>" + p.relaBank+"<br/>"+p.pname + "</td>" 
    			    		+ "<td>" + p.sxed + "</td>" 
    			    		+ "<td>" + p.ckll + "</td>" 
    			    		+ "<td>" + p.cptd + "</td>" 
    			    		+ "<td><img src='<%=basePath %>/images/product/u74_line.png'></td>"
    			    		+ "<td><button onclick='javascript:productDetail("+p.id+")'>查看详情</button></td>"
    			    		+ "</tr>");
    				});
    			}else{
    				$('#protectList tbody').html("<tr height='80px;'><td width='80px;'></td><td width='100' align='left'>无记录</td><td></td><td></td><td></td><td></td><td></td></tr>");
    			}
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert("加载失败!");
	        }
    	});
    	
    	function productDetail(id){
    		//alert($.base64.btoa(id))
    		//alert(id)
    		//location.href='<%=basePath %>/product/detail?id=' + $.base64.btoa(id);
    		location.href='<%=basePath %>/product/detail?id='+id;
    	}
    	
    </script>
    
    
    <%@include file="../base/footer.html" %>
    
</body>
</html>