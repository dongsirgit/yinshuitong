<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	String pageBack = request.getParameter("pageBack");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完善企业信息</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/common/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/help/help.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/index/index.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/login/login.css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/styles/order/order.css" />
	<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	    if (window.self == window.top) {
	        window.top.location ="<%=basePath %>/users/init/userInfo";
	    }
	</script>
	<script type="text/javascript">
	var updateflag = 0;
	var typeUser = '<%=typeUser %>';
	
	var corpName = '';
	var taxSn = '';
	var apName = '';
	var mobilephone = '';
	var idcard = '';
	var realname = '';
	var phonenum = '';
	<%
		if(null!=cuser){
	%>
			corpName = '<%=cuser.getCorpName()%>';
			taxSn = '<%=cuser.getTaxSn()%>';
			apName = '<%=cuser.getApName()%>';
			mobilephone = '<%=cuser.getMobilephone()%>';
			idcard = '<%=cuser.getIdcard()%>';
			if(corpName=='null' ||corpName==null || corpName=='' || corpName=='undefined'){
				corpName = '';
			}
			if(taxSn=='null' ||taxSn==null || taxSn=='' || taxSn=='undefined'){
				taxSn='';
			}
			if(apName=='null' ||apName==null || apName=='' || apName=='undefined'){
				apName='';
			}
			if(mobilephone=='null' ||mobilephone==null || mobilephone=='' || mobilephone=='undefined'){
				mobilephone='';
			}
			if(idcard=='null' ||idcard==null || idcard=='' || idcard=='undefined'){
				idcard=''
			}
	<%
		}else if(null!=puser){
	%>
			realname = '<%=puser.getRealname()%>';
			phonenum = '<%=puser.getPhonenum()%>';
			idcard = '<%=puser.getIdcard()%>';
			if(realname=='null' ||realname==null || realname=='' || realname=='undefined'){
				realname = '';
			}
			if(phonenum=='null' ||phonenum==null || phonenum=='' || phonenum=='undefined'){
				phonenum = '';
			}
			if(idcard=='null' ||idcard==null || idcard=='' || idcard=='undefined'){
				idcard = '';
			}
	<%
		}
	%>
	var pageBack = '<%=pageBack%>';//返回页面
	
	$(document).ready(function() {
		
		if(typeUser=='0'){
			$("#corpName").val(corpName);
			$("#taxSn").val(taxSn);
			$("#apName").val(apName);
			$("#mobilephone").val(mobilephone);
			$("#idcard").val(idcard);
		}else if(typeUser=='1'){
			$("#realname").val(realname);
			$("#phonenum").val(phonenum);
			$("#idcard").val(idcard);
		}
		$(".backPage").click(function(){
			if(updateflag == 0){
				if(pageBack=='order'){
					parent.location.href="<%=basePath %>/basic/productList"
				}
			}
			$(".tc,.mask_alpha").hide(200);
		});
		$("form label").click(function(){
			$(this).next().focus();
		});
	});
	</script>
	
	<script type="text/javascript">
	//更改企业信息提交前 校验
	function checkUserInfo(){
		if(typeUser=='0'){
			return checkCorpName() && checkApName() && checkMobilephone() && checkIdcard() && checkTaxSn();
		}else if(typeUser=='1'){
			aa  =checkRealname() && checkPhonenum() && checkIdcard();
			return checkRealname() && checkPhonenum() && checkIdcard();
		}else{
			return false;
		}
	}
	//校验
	function checkRealname(){
		if($("#realname").val()==null ||$("#realname").val()==''){
			$("#s_realname").show();
		}else if(!/^[\u4e00-\u9fa5]{2,8}$/.test($("#realname").val())){
			$("#s_realname").show();
		}else{
			$("#s_realname").hide();
			return true;
		}
		return false;
	}
	function checkPhonenum(){
		if($("#phonenum").val()==null ||$("#phonenum").val()==''){
			$("#s_phonenum").show();
		}else if(!/^1(([3|4|5|8][0-9]{9})|[7](0[0-9]{8}|8[0-9]{8}))$/.test($("#phonenum").val())){
			$("#s_phonenum").show();
		}else{
			$("#s_phonenum").hide();
			return true;
		}
			return false;
	}
	function checkCorpName(){
		if($("#corpName").val()==null ||$("#corpName").val()==''){
			$("#s_corpName").show();
		}else if(!/^[\u4e00-\u9fa5]{1,25}$/.test($("#corpName").val())){
			$("#s_corpName").show();
		}else{
			$("#s_corpName").hide();
			return true;
		}
			return false;
	}
	function checkTaxSn(){
		if($("#taxSn").val()==null ||$("#taxSn").val()==''){
			$("#s_taxSn").show();
		}else if(!/^[a-zA-Z0-9]{15,20}$/.test($("#taxSn").val())){
			$("#s_taxSn").show();
		}else{
			$("#s_taxSn").hide();
			return true;
		}
			return false;
	}
	function checkApName(){
		if($("#apName").val()==null ||$("#apName").val()==''){
			$("#s_apName").show();
		}else if(!/^[\u4e00-\u9fa5]{2,8}$/.test($("#apName").val())){
			$("#s_apName").show();
		}else{
			$("#s_apName").hide();
			return true;
		}
			return false;
	}
	function checkMobilephone(){
		if($("#mobilephone").val()==null ||$("#mobilephone").val()==''){
			$("#s_mobilephone").show();
		}else if(!/^1(([3|4|5|8][0-9]{9})|[7](0[0-9]{8}|8[0-9]{8}))$/.test($("#mobilephone").val())){
			$("#s_mobilephone").show();
		}else{
			$("#s_mobilephone").hide();
			return true;
		}
			return false;
	}
	function checkIdcard(){
		var checkResult=true;
	     $.ajax({
	    	 type:'post',
	    	 url:"<%=basePath %>/user/checkIdCard",
	    	 data:{"idCard":$("#idcard").val()},
	    	 async: false,
	    	 success:function(data){
	                      if(data.flag != 0){
	                          $("#s_idcard").show();
	                          checkResult=false;
	                      }else{
	                          $("#s_idcard").hide();
	                      }
	                  },
	         error:function(e) {
	        	 $("#s_idcard").show();
	        	 checkResult=false;
             }
	    }); 
		return checkResult;
	}
	
	//更改企业信息
	function saveUserInfo(){
		if(checkUserInfo()){
			var params = null;
			if(typeUser=='0'){
				params = $("#userInfo_company").serializeArray();
			}else if(typeUser=='1'){
				params = $("#userInfo_persion").serializeArray();
			}
			$.ajax({
				type:"POST",
	            url: "<%=basePath %>/users/applyLoan/saveUserInfo",
	            data:params,
	            success: function (data) {
	                if(data.status=='1' && data.success=='1'){
	                	updateflag = 0;
	                	$(".tc_company").show(200);
	            		$(".mask_alpha").show();
	                }else if(data.status=='1' && data.success=='-2'){
	                	updateflag = 1;
	                	$(".tc_faile_corpName").show(200);
	            		$(".mask_alpha").show();
	                }else if(data.status=='1' && data.success=='-1'){
	                	updateflag = 1;
	                	$(".tc_faile").show(200);
	            		$(".mask_alpha").show();
	                }else if(data.status=='1' && data.success=='-11'){
	                	updateflag = 1;
	                	$(".tc_faile_idcard").show(200);
	            		$(".mask_alpha").show();
	                }
	            },
	            error:function(e) {
	            	alert("保存失败!");
	            }
	        })
		}
	}
		
	</script>


</head>
<body class="listh4 company">
<!-- <div class="content_r company"> --><!-- content -->
	<ul class="loan_list">
		<li class="current"  style="display: block;">
			<%
            	if(null!=typeUser && "0".equals(typeUser)){
           	%>
           	<h4>企业信息</h4>
               	<form id="userInfo_company">
	                <div class="change_password info_company">
	                        <div class=" form-item ">
	                          <label>企业名称:</label>
	                          <input id="corpName" name="corpName" type="text" value="" maxlength="25" onblur="checkCorpName()"/>
	                          <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                        </div>
	                        <div class="info"><span id="s_corpName" style="display: none;">× 请输入合法的企业名称</span></div>
	                        <div class=" form-item">
	                          <label>企业纳税人号:</label>
	                          <input id="taxSn" name="taxSn" type="text" value="" maxlength="20" onblur="checkTaxSn()"/>
	                          <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                        </div>
	                        <div class="info"><span id="s_taxSn" style="display: none;">× 请输入合法的企业纳税号</span></div>
	                        <div class=" form-item">
	                          <label>法人姓名:</label>
	                          <input id="apName" name="apName" type="text" value="" maxlength="8" onblur="checkApName()" />
	                          <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                        </div>
	                        <div class="info"><span id="s_apName" style="display: none;">× 请输入合法的法人姓名</span></div>
	                        <div class=" form-item">
	                          <label>法人手机号:</label>
	                          <input id="mobilephone" name="mobilephone" type="text" value="" maxlength="11" onblur="checkMobilephone()" />
	                          <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                        </div>
	                        <div class="info"><span id="s_mobilephone" style="display: none;">× 请输入合法的法人手机号</span></div>
	                        <div class=" form-item">
	                          <label>法人身份证号:</label>
	                          <input id="idcard" name="idcard" type="text" value="" maxlength="18" onblur="checkIdcard()"/>
	                          <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                        </div>
	                        <div class="info"><span id="s_idcard" style="display: none;">× 请输入合法的法人身份证号</span></div>
	                       <!--  <input type="button" value="提交" onclick="javascript:saveUserInfo();"> -->
	                        <a href="javascript:saveUserInfo();">提交</a>
	                </div>
                    </form>   
           <%
           	}else if(null!=typeUser && "1".equals(typeUser)){
           %>
           		<h4>个人信息</h4>
               	<form id="userInfo_persion">
	                <div class="change_password info_company">
                    	<div class=" form-item ">
                          <label>姓名:</label>
                          <input id="realname" name="realname" type="text" value="" maxlength="8" onblur="checkRealname()"/>
                          <img src="<%=basePath %>/images/index/start.jpg" alt="">
                        </div>
	                 	<div class="info"><span id="s_realname" style="display: none;">× 请输入合法的姓名</span></div>
	                        
	                    <div class=" form-item">
	                    	<label>联系手机:</label>
	                        <input id="phonenum" name="phonenum" type="text" value="" maxlength="11" onblur="checkPhonenum()" />
	                        <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                    </div>
	                    <div class="info"><span id="s_phonenum" style="display: none;">× 请输入合法的联系手机</span></div>
	                    <div class=" form-item">
	                        <label>身份证号:</label>
	                        <input id="idcard" name="idcard" type="text" value="" maxlength="18" onblur="checkIdcard()"/>
	                        <img src="<%=basePath %>/images/index/start.jpg" alt="">
	                    </div>
                        <div class="info"><span id="s_idcard" style="display: none;">× 请输入合法的法人身份证号</span></div>
                        	<a href="javascript:saveUserInfo();">提交</a>
                		</div>
                    </form> 
           <%
           		}
           %>	
		</li>
	</ul>
<!-- </div> -->
	
	<div class="mask_alpha" style="display:none;"></div>
	<div class="tc tc_company" style="display:none;">
	    <p>账户信息修改成功！</p>
	    <a id="backPage" class="sure backPage">确定</a>
	</div>
	<div class="tc tc_faile" style="display:none;">
	    <p>账户信息修改失败,纳税号已存在！</p>
	    <a id="backPage" class="sure backPage">确定</a>
	</div>
	<div class="tc tc_faile_corpName" style="display:none;">
	    <p>账户信息修改失败,企业名称已存在！</p>
	    <a id="backPage" class="sure backPage">确定</a>
	</div>
	<div class="tc tc_faile_idcard" style="display:none;">
	    <p>账户信息修改失败,身份证号已存在！</p>
	    <a id="backPage" class="sure backPage">确定</a>
	</div>

</body>
</html>