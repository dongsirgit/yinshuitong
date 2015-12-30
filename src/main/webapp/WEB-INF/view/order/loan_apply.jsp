<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String basePath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写申请</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet"
	type="text/css">
<script src="<%=basePath %>/scripts/common/jquery-1.11.1.min.js"></script>
<script src="<%=basePath %>/scripts/order/order.js"></script>
<script type="text/javascript">
var a = '${err_msg}';
if(a){
	alert(a);
	location.href='<%=basePath %>/users/applyloan/toLoan';
}

if(!'${proid}'){
	location.href = '<%=basePath %>/product/list';
}

$(function(){
	$('#loansub').click(function(){
		
		if(checkApplyQuota() && checkApplyTerm() && checkConName() && che_telephone() && che_applyNote() && checkUpload()){
			$('#loan_form').attr("action","<%=basePath %>/users/applyloan/loansub");
			$('#loan_form').submit();
		}
		
	});
})
function checkUpload(){
	if($("a[name='flag4check']").length!=2){
		$("#upCheckdiv").show();
		$(".mask_alpha").show();
		return false;
	}
	return true
}
function checkApplyQuota(){
	$("#applyQuota").val($("#applyQuota").val().replace(/\s+/g,''))
	if(!/^([1-9][\d]{0,2})$/.test($("#applyQuota").val())){
		$("#e_applyQuota").show();
		$("#quotaCny").html("");
		return false;
	}else{
		change();
		$("#e_applyQuota").hide();
		return true;
	}
}
function fo_quota(){
	$("#e_applyQuota").hide();
}

function checkApplyTerm(){
	if(!/^([1-9]|([12][0-9])|([3][0-6]))$/.test($("#applyTerm").val())){
		$("#e_applyTerm").show();
		return false;
	}else{
		$("#e_applyTerm").hide();
		return true;
	}
}
function fo_term(){
	$("#e_applyTerm").hide();
}

function checkConName(){
	$("#contactName").val($("#contactName").val().replace(/\s+/g,''));
	if(!/^[\u4e00-\u9fa5]{2,10}$/.test($("#contactName").val())){
		$("#e_contactName").show();
		return false;
	}else{
		$("#e_contactName").hide();
		return true;
	}
}
function fo_conName(){
	$("#e_contactName").hide();
}

function che_telephone(){
	if(!/^1(([3|4|5|8][0-9]{9})|[7](0[0-9]{8}|8[0-9]{8}))$/.test($("#telephone").val())){
		$("#e_telephone").show();
		return false;
	}else{
		$("#e_telephone").hide();
		return true;
	}
}
function fo_tel(){
	$("#e_telephone").hide();
}

function che_applyNote(){
	if($("#applyNote").val().length<10){
		$("#e_applyNote").show();
		return false;
	}else{
		$("#e_applyNote").hide();
		return true;
	}
}
function fo_applyNote(){
	$("#e_applyNote").hide();
}
</script>
</head>
<body>
	<iframe src="<%=basePath %>/basic/head" width="100%" height="74px"
		frameborder="0" scrolling="no"></iframe>
	<div class="main">
		<div class="mainpb">
			<h2 class="orderh2">填写申请</h2>
			 <div class="order_fill_top">
				<h3 class="orderh3">选择的贷款产品</h3>
	            <table>
	            	<tr>
	                	<td><img src="<%=basePath %>/images/product/${prosyn.logoUrl}"/></td>
	                	<td><c:out value="${prosyn.pname}"/></td>
	                	<td>授信额度：<c:out value="${prosyn.sxed}"/></td>
	                	<td>参考利率：<c:out value="${prosyn.ckll}"/></td>
	                </tr>
	            </table>
	        </div>
			<div class="orderform">
				<div class="item">
					<span class="label">企业名称：</span><span id="realname"><c:out
							value="${loginedUser.corpName }" /></span>
				</div>
				<div class="item">
					<span class="label">企业纳税号：</span><span id="phonenum"><c:out
							value="${loginedUser.taxSn}" /></span>
				</div>
				<form id="loan_form" method="post">
					<div class="item">
						<span class="label"><b>*</b>期望贷款金额：</span> <input
							name="applyQuota" id="applyQuota" type="text" maxlength="3"
							class="text w264" onblur="checkApplyQuota()" onkeyup="checkApplyQuota();" onfocus="fo_quota()"><em>万元</em>
							<i id="quotaCny" class="amount_money"></i>
							 <span
							id="e_applyQuota" class="erro colorred" style="display: none;">×
							请输入正确贷款金额</span>
					</div>
					<div class="item">
						<span class="label"><b>*</b>期望贷款期限：</span> <input name="applyTerm"
							id="applyTerm" type="text" maxlength="2" class="text w264"
							onblur="checkApplyTerm()" onfocus="fo_term()"><em>个月</em> <span id="e_applyTerm"
							class="erro colorred" style="display: none;">× 请输入正确贷款期限</span>
					</div>
					<div class="item">
						<span class="label"><b>*</b>联系人姓名：</span> <input type="text" maxlength="10"
							name="contactName" id="contactName" class="text w264" onblur="checkConName()" onfocus="fo_conName()" > <span
							id="e_contactName" class="erro colorred" style="display: none;">×
							请输入正确的姓名</span>
					</div>
					<div class="item">
						<span class="label"><b>*</b>联系人手机：</span> <input type="text" maxlength="11"
							name="telephone" id="telephone" class="text w264" value="${loginedUser.mobilePhone}" onblur="che_telephone()" onfocus="fo_tel()"> <span
							id="e_telephone" class="erro colorred" style="display: none;">×
							请输入正确的手机号</span>
					</div>
					<div class="useswrap">
		                <div class="item usesbox clearfix">
						<span class="label"><b>*</b>贷款用途：</span>
						<textarea name="applyNote" id="applyNote" class="uses" maxlength="200"
							 onblur="che_applyNote()" onfocus="fo_applyNote()"></textarea>
						<p id="e_applyNote" class="colorred" style="display: none;"><span>× 至少输入10个字</span></p>
						</div>
					</div>
					<div class="item">
						<span class="label"><b>*</b>营业执照：</span>
						<div>
							<div class="fill_filebox fl"><input type="button" id="browse1"><em>上传</em></div>
							<div id="filelist1">
								<span id="e_browse1" class="erro colorred" style="display: none;">× 请上传附件</span>
							</div>
						</div>
					</div><br/>
					<div class="item">
						<span class="label"><b>*</b>贷款申请书：</span> 
						<div>
							<div class="fill_filebox fl"><input type="button" id="browse2"><em>上传</em></div>
							<div id="filelist2">
								<span id="e_browse2" class="erro colorred" style="display: none;">× 请上传附件</span>
								<span class="file_pre"><a href="<%=basePath %>/tmplt/apply_template.docx">下载申请书模板</a></span>
							</div>
						</div>
					</div>
					<p class="fill_p colorred">注：请下载贷款申请书，填写后扫描成图片上传； 图片格式为jpg、jpeg、png，单个文件不大于1M；扫描件或相片皆可，图片上的文字必须清晰；请将图片摆正后上传，方便工作人员审核，避免审核不通过</p>
					<div class="btnbox"><a id="loansub" class="btn-yellow" href="javascript:;">提交申请</a></div>
					<span class="clr"></span>
					<input type="hidden" id="yyzz_atid" name="yyzz_atid">
					<input type="hidden" id="sqs_atid" name="sqs_atid">
					<input type="hidden" id="proid" name="prodId" value="${proid}">
				</form>
			</div>
		</div>
	</div>
<%@include file="../base/footer.html"%>
<div id="upCheckdiv" class="fdiv" style="display: none;">
	<p>证件资料不齐全 或 文件正在上传中，<br/>上传完整后才可提交！</p>
    <div><a class="fdivbtn1" id="btn_upCheck_confirm">确定</a></div>
</div>
<div class="mask_alpha" style="display: none;"></div>
<script type="text/javascript" src="<%=basePath%>/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/zh_CN.js"></script>
<script type="text/javascript">

$('#btn_upCheck_confirm').click(function(){
	$('#upCheckdiv').hide();
	$(".mask_alpha").hide();
});

function change(){
	var a =$('#applyQuota').val();
	var cny = numToCny(a);
	$('#quotaCny').html(cny==''?'':cny+"万圆整");
}
function numToCny(num){
    var capUnit = ['万','亿','万',''];     
    var capDigit = { 2:['角','分',''], 4:['仟','佰','拾','']};     
    var capNum=['零','壹','贰','叁','肆','伍','陆','柒','捌','玖'];     
    if (((num.toString()).indexOf('.') > 16)||(isNaN(num)))      
        return '';     
    num = (Math.round(num*100)/100).toString();     
    num =((Math.pow(10,16-num.length)).toString()).substring(1)+num;     
    var i,ret,j,nodeNum,k,subret,len,subChr,CurChr=[];     
    for (i=0,ret='';i<5;i++,j=i*4+Math.floor(i/4)){     
        nodeNum=num.substring(j,j+4);     
        for(k=0,subret='',len=nodeNum.length;((k<len) && (parseInt(nodeNum.substring(k))!=0));k++){     
            CurChr[k%2] = capNum[nodeNum.charAt(k)]+((nodeNum.charAt(k)==0)?'':capDigit[len][k]);     
            if (!((CurChr[0]==CurChr[1]) && (CurChr[0]==capNum[0])))     
                if(!((CurChr[k%2] == capNum[0]) && (subret=='') && (ret=='')))     
                    subret += CurChr[k%2];     
        }     
        subChr = subret + ((subret=='')?'':capUnit[i]);     
        if(!((subChr == capNum[0]) && (ret=='')))     
            ret += subChr;     
    }     
    return ret;     
}   

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

//营业执照上传
var uploader = new plupload.Uploader({
    browse_button : 'browse1', 
    url : '<%=basePath%>/users/fileUpload/upload.json?upfiletype=0', 
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
	$('#yyzz_atid').val(eval("("+result.response+")").atId);
});

uploader.bind('Error',function(uploader,err){
	if("linkTimeOut"==err.response){
		uploader.stop();
    	$("#browse1").attr('disabled',false);
    	document.getElementById('filelist1').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
    	alert(err.file.name+'  :  网络异常，上传失败，请重新上传！');
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
	document.getElementById('filelist1').innerHTML = '<a class="file_pre" name="flag4check" href="<%=basePath %>/users/file/showPicById?id='+$("#yyzz_atid").val()+'" target="_blank">'+file.name+'</a>';
});
$("#browse1").attr('disabled',false);
});
//贷款申请书上传
var uploader_sqs = new plupload.Uploader({
    browse_button : 'browse2', 
    url : '<%=basePath%>/users/fileUpload/upload.json?upfiletype=1', 
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
uploader_sqs.init();
//绑定各种事件，并在事件监听函数中做你想做的事
uploader_sqs.bind('FilesAdded',function(uploader_sqs,files){
	if(!che_fileNamelen(files[0].name)){
		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
		uploader_sqs.removeFile(files[0]);
		return;
	}
	plupload.each(files, function(file) {
		document.getElementById('filelist2').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
	});
	$("#browse2").attr('disabled',true);
	uploader_sqs.start();
});
uploader_sqs.bind('UploadProgress',function(uploader_sqs,file){
	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + file.percent + "%</span>";
});
uploader_sqs.bind('FileUploaded',function(uploader_sqs,file,result){
	$('#sqs_atid').val(eval("("+result.response+")").atId);
});
uploader_sqs.bind('Error',function(uploader_sqs,err){
	if("linkTimeOut"==err.response){
		uploader_sqs.stop();
    	$("#browse2").attr('disabled',false);
    	document.getElementById('filelist2').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
    	alert(err.file.name+'  :  网络异常，上传失败，请重新上传！');
	}else if("timeOut"==err.response){
		uploader_sqs.stop();
		alert("您还没有登录或登录已超时，请重新登录！");
		location.reload();
	}else{
		alert(err.message);
	}
});
uploader_sqs.bind('UploadComplete',function(uploader_sqs,files){
	plupload.each(files, function(file) {
		document.getElementById('filelist2').innerHTML = '<a class="file_pre" name="flag4check" href="<%=basePath %>/users/file/showPicById?id='+$("#sqs_atid").val()+'" target="_blank">'+file.name+'</a>';
	});
	$("#browse2").attr('disabled',false);
	});

</script>
</body>
</html>