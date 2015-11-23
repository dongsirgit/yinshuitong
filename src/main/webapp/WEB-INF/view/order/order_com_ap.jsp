<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baiwang.banktax.beans.Cuser,com.baiwang.banktax.utils.ConfigUtil "
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
	Long id = 0l;
	try{
		id = Long.valueOf((null == request.getParameter("id") ||"".equals(request.getParameter("id")) ? "0":request.getParameter("id")));
	}catch(Exception e){
		
	}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业法人税务贷</title>
<link href="<%=basePath %>/styles/common/base.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/styles/order/order.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>/scripts/common/jquery-1.11.1.js"></script>
<script src="<%=basePath %>/scripts/order/order.js"></script>
</head>
<body>
<iframe src="<%=basePath %>/basic/head" width="100%" height="74px" frameborder="0" scrolling="no"></iframe>
<div class="steps">
    <ul class="clearfix stepsnum2">
    	<li class="colorred">填写基本信息</li>
    	<li class="colorred">上传证件资料</li>
    	<li>提交贷款申请</li>
    </ul>
</div>
<div class="main">
	<div class="mainpb">
		<div class="tip"><p>您输入的越完整、真实，则越容易快速通过银行的审批。在银行审批贷款时，将参考以下内容，必要时可能进行人工核实，请务必填写真实情况。</p></div>
        <h2 class="orderh2">企业法人税务贷</h2>
		<h3 class="orderh3">上传证件资料</h3>
			
        <table class="ordertable ac">
        	<tr>
            	<td class="ft16 ordertable_l">图片类</td>
            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、本人二代身份证正反面原件扫描件（不认可护照、户口簿、一代身份证、临时身份证）</li>
                        <li>2、到当地人民银行开具的纸质信用报告，或登录人民银行征信中心https://ipcrs.pbccrc.org.cn获取电子</li>
                        <li>3、近六个月的银行流水</li>
                        <li>4、企业营业执照（注册日期距借款申请日期满 12 个月，提供资料显示实际经营满 12 个月）</li>
                        <li>5、经营及收入证明（财务报表/所得税税单/银行流水/社保记录/工资证明/股东分红记录/租赁收入，任选其一）</li>
                        <li>6、住址证明（房产证，如果是租房需要有中介机构盖章证实的租房合同；近一个月的水、电、煤、有线电视账单或固定电话账单）</li>
                    </ul>
                    <p class="pl20 colorred pb20">* 证件资料扫描后并压缩成zip压缩包上传</p>
                </td>
            	<td class="ordertable_r">
                	<div>
                		<input type="button" id="browse1" value="上传附件">
                        <div id="filelist1">
                        <c:if test="${empty attachMap['0'].batchName }">
	            			<a class="erropro" href="javascript:;">* 请上传附件</a>
                    	</c:if>
                    	<c:if test="${!empty attachMap['0'].batchName }">
                    	
	                    <a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPic?id=${attachMap['0'].id }" target="_blank">${attachMap['0'].batchName }</a>
                    	</c:if>
                        </div>
                    </div>
                </td>
            </tr>
        	<tr>
            	<td class="ft16 ordertable_l">文档类</td>
            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、信贷申请书（必须本人亲签，如有内容修改，需本人在修改处按右手拇指印）</li>
                    </ul>
                    <p class="pl20 colorred pb20">* 下载word文档模版，填写后上传。<a href="/files/help/com_ap_apply.doc"  target="_blank" rel="external" >下载模版>></a></p>
                </td>
            	<td class="ordertable_r">
                	<div>
                		<input type="button" id="browse2" value="上传附件">
                        <div id="filelist2">
                        <c:if test="${empty doc.attachNote }">
	            			<a class="erropro" href="javascript:;">* 请上传附件</a>
                    	</c:if>
                    	<c:if test="${!empty doc.attachNote }">
                    	
	                    <a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/download?id=${doc.id }"  target="_blank">${doc.attachNote }</a>
                    	</c:if>
                        </div>
                    </div>
                </td>
            </tr>
        	<tr>
            	<td class="ft16 ordertable_l" rowspan="2">发票类</td>
            	<td class="al ordertable_main borbdot">
                	<ul>
                    	<li>1、进项发票数据</li>
                    </ul>
                    <p class="pl20 colorred pb20">* 进项发票数据扫描后并压缩成zip压缩包上传</p>
                </td>
            	<td class="ordertable_r borbdot">
                	<div>
                		<input type="button" id="browse3" value="上传附件">
                        <div id="filelist3">
                        <c:if test="${empty attachMap['6'].batchName }">
	            			<a class="erropro" href="javascript:;">* 请上传附件</a>
                    	</c:if>
                    	<c:if test="${!empty attachMap['6'].batchName }">
                    	
	                    <a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPic?id=${attachMap['6'].id }" target="_blank">${attachMap['6'].batchName }</a>
                    	</c:if>
                        </div>
                    </div>
                </td>
            </tr>
        	<tr>
            	<td class="al ordertable_main">
                	<ul>
                    	<li>1、销项发票数据</li>
                    </ul>
                    <p class="pl20 colorred pb20">* 销项发票数据扫描后并压缩成zip压缩包上传</p>
                </td>
            	<td class="ordertable_r">
                	<div>
                		<input type="button" id="browse4" value="上传附件">
                        <div id="filelist4">
                        <c:if test="${empty attachMap['7'].batchName }">
	            			<a class="erropro" href="javascript:;">* 请上传附件</a>
                    	</c:if>
                    	<c:if test="${!empty attachMap['7'].batchName }">
	                    	<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPic?id=${attachMap['7'].id }" target="_blank">${attachMap['7'].batchName }</a>
                    	</c:if>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <p class="ftc5c pl12 pt10 pb35">注：以上证件资料均是必传资料，缺一不可！</p>
	</div>
</div>
<!--勾选服务协议-->
<div class="readdiv"><span><em>√</em></span>我已阅读并同意<a href="<%=basePath %>/users/applyLoan/read" target="_blank">百望金融融资服务平台《用户服务协议》</a></div>
<div class="btnbox"><a id="backPage" class="btn-yellow" href="javascript:;">上一步</a><a id="saveApply" class="btnboxa1" >暂时保存</a><a id="btn_owner_submit" class="btnboxa2">提交申请</a></div>

<%@include file="../base/footer.html" %>
<div class="mask_alpha"></div>
<div id="submitdiv" class="fdiv" style="display: none;">
	<p>您确定正式提交申请贷款吗？</p>
    <div><a class="fdivbtn1" id="btn_submit_confirm">确定</a><a class="fdivbtn2">取消</a></div>
</div>
<div id="xieyidiv" class="fdiv" style="display: none;">
	<p>请先阅读并同意《用户服务协议》</p>
    <div><a class="fdivbtn1" id="btn_xieyi_confirm">确定</a><!-- <a class="fdivbtn2">确定</a> --></div>
</div>
<div id="upCheckdiv" class="fdiv" style="display: none;">
	<p>证件资料不齐全 或 文件正在上传中，<br/>上传完整后才可提交！</p>
    <div><a class="fdivbtn1" id="btn_upCheck_confirm">确定</a></div>
</div>
<div id="uploadingCheckdiv" class="fdiv" style="display: none;">
	<p>文件正在上传中，请稍后操作 ！</p>
    <div><a class="fdivbtn1" id="btn_upLoadingCheck_confirm">确定</a></div>
</div>
<form id="returnpage3" action="<%=basePath %>/users/applyLoan/com1" method="post">
	<input type="hidden" id="qyfrid" name="id">
	<input type="hidden" id="qyfrtype" name="type" value='3'>
</form>


<script type="text/javascript" src="<%=basePath%>/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="<%=basePath%>/plupload/zh_CN.js"></script>
<script type="text/javascript">
var id = <%=id %>;
if(id==null || id=='0') window.location.href = '<%=basePath %>/users/init/userInfo';
var id_sub = 0;//判断用户协议
$(function(){
	//上一步
	$("#backPage").click(function(){
		$("#qyfrid").val(id);
  	   	$("#returnpage3").submit();
		//location.href = '<%=basePath %>/users/applyLoan/com1?type=1&id='+id;
	});
	//保存
	$("#saveApply").click(function(){
		if($("span[name='uploading']").length>0){
			$("#uploadingCheckdiv").show();
			$(".mask_alpha").show();
			return;
		}
		if(id_sub%2==0){
			location.href = '<%=basePath %>/users/init/userInfo';
		}else{
			$("#xieyidiv").show();//先阅读《用户服务协议》
			return;
		}
	});
	//正式提交
	$("#btn_owner_submit").click(function(){
		if($("a[name='flge4check']").length!=4){
			$("#upCheckdiv").show();
			$(".mask_alpha").show();
			return;
		}
		var params ={"id":id};
		$.ajax({
			type:"POST",
	        url: "<%=basePath %>/users/applyLoan/checkAtt",
	        data:params,
	        success: function (data) {
	            	if(data.success!="1"){
	            		$("#upCheckdiv").show();
	            		$(".mask_alpha").show();
	            		return;
	            	}
	        },
	        error:function(e) {
	        	alert("提交失败,请重新提交!");
	        }
	    })
		if(id_sub%2==0){
			$("#xieyidiv").hide();//请先阅读并同意百望金融融资服务平台《用户服务协议》
			$("#submitdiv").show(200);
			$(".mask_alpha").show();
		}else{
			$("#xieyidiv").show();//先阅读《用户服务协议》
			return;
		}
	})
	//确认提交
	$("#btn_submit_confirm").click(function(){
		//location.href = '<%=basePath %>/users/applyLoan/submit?id='+id;
		var params = {"id":id};
		$.ajax({
			type:"POST",
	        url: "<%=basePath %>/users/applyLoan/submit",
	        data:params,
	        success: function (data) {
	            if (data.status == "0") {
	               alert("请登陆...");
	            }else if (data.status == "1"){
	            	if(data.success=="1"){
	            		//location.href='<%=basePath %>/users/applyLoan/up';
	            		window.parent.location.href='<%=basePath %>/users/applyLoan/com3?type=3';
	            	}else{
	            		alert("提交失败,请重新提交!");
	            	}
	            }
	        },
	        error:function(e) {
	        	alert("提交失败,请重新提交!");
	        }
	    })
	})
	//图片类上传
    var uploader = new plupload.Uploader({
        browse_button : 'browse1', 
        url : '<%=basePath%>/users/fileUpload/upload?zipFlag=isZip&pageFlag=ap2&upfiletype=0&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader.bind('FilesAdded',function(uploader,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader.removeFile(files[0]);
			return;
		}
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
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader.bind('Error',function(uploader,err){
    	if("failToUnZip"==err.response){
    		uploader.stop();
	    	$("#browse1").attr('disabled',false);
	    	document.getElementById('filelist1').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("图片类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
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
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist1').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=0 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse1").attr('disabled',false);
    });
  //文档类上传
    var uploader_doc = new plupload.Uploader({
        browse_button : 'browse2', 
        url : '<%=basePath%>/users/fileUpload/upload?pageFlag=ap2&zipFlag=docf&upfiletype=1&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Doc files", extensions : "doc,docx" }
   		  ],
   		  max_file_size : '1mb'
//    		  prevent_duplicates : true, //不允许选取重复文件
   		},
   		multi_selection:false
    });    
    uploader_doc.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_doc.bind('FilesAdded',function(uploader_doc,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_doc.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_doc.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist2').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse2").attr('disabled',true);
    	uploader_doc.start();
    });
    uploader_doc.bind('UploadProgress',function(uploader_doc,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_doc.bind('Error',function(uploader_doc,err){
    	if("failToUnZip"==err.response){
    		uploader_doc.stop();
	    	$("#browse2").attr('disabled',false);
	    	document.getElementById('filelist2').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_doc.stop();
	    	$("#browse2").attr('disabled',false);
	    	document.getElementById('filelist2').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("文档类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_doc.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else if("文件类型错误，仅支持zip文件！"==err.message){
    		alert("文件类型错误，仅支持doc/docx文件！")
    	}else{
    		alert(err.message);
    	}
    });
    uploader_doc.bind('UploadComplete',function(uploader_doc,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist2').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/downloadByIdType?id='+id+'&attType=1 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse2").attr('disabled',false);
    });
    
  //发票类进项上传
    var uploader_invoices_in = new plupload.Uploader({
        browse_button : 'browse3', 
        url : '<%=basePath%>/users/fileUpload/upload?zipFlag=isZip&pageFlag=ap2&upfiletype=6&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader_invoices_in.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_invoices_in.bind('FilesAdded',function(uploader_invoices_in,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_invoices_in.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_invoices_in.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist3').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse3").attr('disabled',true);
    	uploader_invoices_in.start();
    });
    uploader_invoices_in.bind('UploadProgress',function(uploader_invoices_in,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_invoices_in.bind('Error',function(uploader_invoices_in,err){
    	if("failToUnZip"==err.response){
    		uploader_invoices_in.stop();
	    	$("#browse3").attr('disabled',false);
	    	document.getElementById('filelist3').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票进项类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_invoices_in.stop();
	    	$("#browse3").attr('disabled',false);
	    	document.getElementById('filelist3').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票进项类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_invoices_in.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_invoices_in.bind('UploadComplete',function(uploader_invoices_in,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist3').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=6 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse3").attr('disabled',false);
    });
    
  //发票类销项上传
    var uploader_invoices_out = new plupload.Uploader({
        browse_button : 'browse4', 
        url : '<%=basePath%>/users/fileUpload/upload?zipFlag=isZip&pageFlag=ap2&upfiletype=7&applyId='+id, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : "Zip files", extensions : "zip" }
   		  ],
   		  max_file_size : '10mb'
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploader_invoices_out.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader_invoices_out.bind('FilesAdded',function(uploader_invoices_out,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader_invoices_out.removeFile(files[0]);
			return;
		}
    	if(!che_fileNamelen(files[0].name)){
    		alert("文件名过长，请修改后重新操作！\n提示：文件名不能超过35个中文或70个英文字符。");
    		uploader_invoices_out.removeFile(files[0]);
    		return;
    	}
    	plupload.each(files, function(file) {
			document.getElementById('filelist4').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#browse4").attr('disabled',true);
    	uploader_invoices_out.start();
    });
    uploader_invoices_out.bind('UploadProgress',function(uploader_invoices_out,file){
    	var percent;
    	file.percent==100?percent=99:percent=file.percent
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span name="uploading" style="color:red;">' + percent + "%</span>";
    });
    uploader_invoices_out.bind('Error',function(uploader_invoices_out,err){
    	if("failToUnZip"==err.response){
    		uploader_invoices_out.stop();
	    	$("#browse4").attr('disabled',false);
	    	document.getElementById('filelist4').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票销项类附件"+err.file.name+'  :  文件解压失败！请检查文件完整性并重新上传！');
    	}else if("linkTimeOut"==err.response){
    		uploader_invoices_out.stop();
	    	$("#browse4").attr('disabled',false);
	    	document.getElementById('filelist4').innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
	    	alert("发票销项类附件"+err.file.name+'  :  网络异常，上传失败，请重新上传！');
    	}else if("timeOut"==err.response){
    		uploader_invoices_out.stop();
    		alert("您还没有登录或登录已超时，请重新登录！");
    		location.reload();
    	}else{
    		alert(err.message);
    	}
    });
    uploader_invoices_out.bind('UploadComplete',function(uploader_invoices_out,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById('filelist4').innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=7 " target="_blank">'+file.name+'</a>';
		});
    	$("#browse4").attr('disabled',false);
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
})
	
	


</script>
</body>
</html>