<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.User,com.baiwang.banktax.utils.ConfigUtil"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getContextPath();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实名认证-人工审核</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
<script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>

<style type="text/css">
	.mainn{ width: 1000px;height:auto!important; height:600px; min-height:590px; border:1px solid #AFAEAC; margin: 10px auto; background:#FFF; padding: 0px 0 0 0;}
	.procurrent{background-color: blue;color: white;}
</style>

<script type="text/javascript">
	var id_sub = 0;
	$(document).ready(function(){
		
		//服务协议已阅读状态
		$(".readdiv span").click(function(){
			id_sub +=1;
			$(this).children("em").toggleClass("colorf")
		});
		
		$(".fdivbtn1").click(function(){
			$(".mask_alpha,.fdiv").hide();
		});
		
	})
	
	function nextIdentify(){
		if($('#corpName').val()=='请填写公司全称' || $('#corpName').val()==''){
			$('#corpName').val('');
			$('#sp_fail_cor').show();
			return;
		}
		if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#corpName").val())){
			$('#sp_fail_cor').show();
			return;
		}
		if($('#taxSn').val()=='请填写公司纳税识别号' || $('#taxSn').val()==''){
			$('#taxSn').val('');
			$('#sp_fail_tax').show();
			return;
		}
		if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#taxSn").val())){
			$('#sp_fail_tax').show();
			return ;
		}
		
		if($('#code').val()=='请输入验证码' || $('#code').val()==''){
			$('#code').val('');
			$('#sp_fail_code_null').show();
			return;
		}
		
		if($('#xieyi').is(":checked")){
			$("#xieyidiv").hide();//阅读协议
		}else{
			$("#xieyidiv").show();//阅读协议
			return;
		}
		if(!checkUpload()){
			return;
		}
		var data = $('#form').serialize();
		 $.ajax({
				type:"POST",
				url:"<%=basePath %>/users/identify/plat3",
		data:data,
		dataType:"JSON",
		//dataType:"text",
		success:function(data){
			//alert(JSON.stringify(data));
			if(data.success=='1'){
				$('.fdiv').hide();
				location.href='<%=basePath %>/users/identify/succes';
					}
					else if(data.success == -2){//验证码错误
						$('#sp_fail_code').show();
					}
					else if(data.success == -1){//验证码错误
						$('#sp_fail_code_null').show();
					}
					else if(data.success == -22){//重复认证
						$('#div_reiden').show();
						$('#mask_alpha').show();
					}
					else if(data.success == -3){
						$('#div_iniden').show();
						$('#mask_alpha').show();
					}else{
						$('.fdiv').hide();
						$('#sp_fail').show();
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
	function goback(){
		location.href = '<%=basePath %>/users/identify/';
	}
	
	
	function corfocus(obj){
		$('#sp_fail_cor').hide();
		if(obj.value =='请填写公司全称'){
			obj.value ='';
		}
	}
	function corblur(obj){
		if(obj.value == ''){
			obj.value ='请填写公司全称';
			$('#sp_fail_cor').show();
		}else 
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#corpName").val())){
				$('#sp_fail_cor').show();
			}
	}
	
	function taxfocus(obj){
		$('#sp_fail_tax').hide();
		if(obj.value =='请填写公司纳税识别号'){
			obj.value ='';
		}
	}
	function taxblur(obj){
		if(obj.value == ''){
			obj.value ='请填写公司纳税识别号';
			$('#sp_fail_tax').show();
		}else 
			if(!/^[\u4e00-\u9fa50-9a-zA-Z]{1,20}$/.test($("#taxSn").val())){
				$('#sp_fail_tax').show();
			}
	}
	function checkUpload(){
		if($("a[name='flag4check']").length!=1){
			$("#upCheckdiv").show();
			$(".mask_alpha").show();
			return false;
		}
		return true
	}
	function codefocus(obj){
		$('#sp_fail_code').hide();
		$('#sp_fail_code_null').hide();
		
		if(obj.value =='请输入验证码'){
			obj.value ='';
		}
	}
	function codeblur(obj){
		if(obj.value == ''){
			obj.value ='请输入验证码';
			$('#sp_fail_code_null').hide();
		}
	}
	
	function changeCode(){
		$('#img_code').attr('src',"<%=basePath%>/imageServlet?temp="+new Date().getTime().toString(36));
	}

</script>
    
</head>
<body>
    <iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
	<div class="main">
		<div class="renzheng_pingtai">
	    	<h2 class="shimingrzh2">实 名 认 证</h2>
	        <div class="renzheng_pingtai_in">
	        	<p class="renzheng_pingtai_in_top">上传证件认证&nbsp;&nbsp;&nbsp;需人工审核</p>
	        	<form id = 'form'>
		            <div class="pingtai_info_box">
			            <input id="corpName" name="corpName" type="text" value="请填写公司全称" onfocus="corfocus(this)" onblur="corblur(this)" maxlength="20">
					   	<p><span id='sp_fail_cor' style="display: none;">请输入正确公司名称</span></p>
		            </div>
		            <div class="pingtai_info_box">
		            	<input id="taxSn" name="taxSn" type="text" value="请填写公司纳税识别号"  onfocus="taxfocus(this)" onblur="taxblur(this)" maxlength="20">
				   		<p><span id='sp_fail_tax' style="display: none;">请输入正确纳税识别号</span></p>
		            </div>
		            <div class="pingtai_info_box num">
		            	<input type="text" name="code" id="code" value="请输入验证码" onfocus="codefocus(this)" onblur="codeblur(this)" maxlength="4"/>
	                    <p><span id='sp_fail_code' style="display:none;">验证码输入错误</span></p>
	                    <p><span id='sp_fail_code_null' style="display:none;">请输入验证码</span></p>
	                    <img id='img_code' src="<%=basePath%>/imageServlet" alt="验证码" title="点击更换" onclick="changeCode()"/>
		            </div>
	            </form>
	            <div class="renzheng_up clearfix">
	            	<p class="fl">税务登记复印件加盖公章</p>
		            <div>
						<input class="fill_filebox fl" type="button" id="browse1" value="上传附件">
						<div id="filelist1">
							<span class="erro colorred" style="display: none;">× 请上传附件</span>
						</div>
					</div>
	            </div>
	            <div class="al renzheng_notes"><i class="colorred">注：</i>图片格式为jpg、jpeg、png，单个文件不大于1M；扫描件或相片皆可，图片上 的文字必须清晰；请将图片选择摆正后上传，方便工作人员审核，避免审核不通过</div>
	            
	            <p class="ac ft14"><input id="xieyi" type="checkbox"/>我已经阅读并同意<a href="<%=basePath %>/users/identify/toProtocol" target="_blank">《数据授权协议》</a></p>
	            <div  class="uploadbtn clearfix">
		   			<a href="javascript:;" onclick="goback()" class="think">返回</a>
		   			<a href="javascript:;" onclick="nextIdentify()" class="download">去认证</a>
		    		<span id='sp_fail' style=" font-size: 13px; color: red; display:none;">认证失败，如有问题请联系客服！</span>
		   		</div>
	        </div>
	    </div>
	</div>
    <div class="mask_alpha" style="display: none;"></div>
    <div id='xieyidiv' class="fdiv"  style="display: none;">
        <p>请阅读协议并同意！</p>
        <div><button class="fdivbtn1">确定</button>
        </div>
    </div>
    
    <div id='div_reiden' class="fdiv"  style="display: none;">
        <p>该企业已经通过实名认证，不能重复认证!</p>
        <div><button class="fdivbtn1">确定</button>
        </div>
    </div>
    <div id='div_iniden' class="fdiv"  style="display: none;">
        <p>该企业正在进行实名认证，不能重复认证!</p>
        <div><button class="fdivbtn1">确定</button>
        </div>
    </div>
	<div id="upCheckdiv" class="fdiv" style="display: none;">
		<p>证件资料不齐全 或 文件正在上传中，<br/>上传完整后才可提交！</p>
	    <div><a class="fdivbtn1" id="btn_upCheck_confirm">确定</a></div>
	</div>    
    <input type="hidden" id="swdj_atid" name="swdj_atid">
    <%@include file="../base/footer.html" %>
<script type="text/javascript" src="<%=basePath%>/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn_upCheck_confirm').click(function(){
		$('#upCheckdiv').hide();
		$(".mask_alpha").hide();
	});
	//检验上传文件的文件名长度
	function che_fileNamelen(fileName){
		var max_len = 75;
		var flag=false;
		var realLength = 0,len = fileName.length,charCode = -1;
		for(var i=0;i<len;i++){
			charCode = fileName.charCodeAt(i);
			if(charCode>0 && charCode<=128) realLength +=1;
			else realLength += 2;
		}
		if(realLength<=max_len) flag=true;
		return flag;
	}

	//税务登记证上传
	var uploader = new plupload.Uploader({
	    browse_button : 'browse1', 
	    url : '<%=basePath%>/users/fileUpload/upload.json?upfiletype=2', 
	    flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
	    silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
		filters: {
			  mime_types : [ 
			    { title : "Image files", extensions : "jpg,jpeg,png" }
			  ],
			  max_file_size : '1mb'
			},
		multi_selection:false
	});    
	uploader.init();
	//绑定各种事件，并在事件监听函数中做你想做的事
	uploader.bind('FilesAdded',function(uploader,files){
		if(!che_fileNamelen(files[0].name)){
			alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
			uploader.removeFile(files[0]);
			return;
		}
		plupload.each(files, function(file) {
			document.getElementById('filelist1').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
		$("#browse1").attr('disabled',true);
		uploader.start();
	});
	uploader.bind('UploadProgress',function(uploader,file){
		document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + file.percent + "%</span>";
	});
	uploader.bind('FileUploaded',function(uploader,file,result){
		$('#swdj_atid').val(eval("("+result.response+")").atId);
	});
	uploader.bind('Error',function(uploader,err){
		if("linkTimeOut"==err.response){
			uploader.stop();
	    	$("#browse1").attr('disabled',false);
	    	document.getElementById('filelist1').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
		}else if("timeOut"==err.response){
			uploader.stop();
			alert("您还没有登录或登录已超时，请重新登录！");
			location.reload();
		}else{
			alert(err.message);
		}
	});
	uploader.bind('UploadComplete',function(uploader,files){
		plupload.each(files, function(file) {
			document.getElementById('filelist1').innerHTML = '<a class="pt5 tdul" name="flag4check" href="<%=basePath %>/users/file/showPicById?id='+$("#swdj_atid").val()+'" target="_blank">'+file.name+'</a>';
		});
		$("#browse1").attr('disabled',false);
		});
	
	
})

</script>    
</body>
</html>