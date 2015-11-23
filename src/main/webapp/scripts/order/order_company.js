$(document).ready(function() {
	if(!(corpName=='null' ||corpName==null || corpName=='' || corpName=='undefined')){
		$("#corpName").text(corpName);
	}else{
		location.href = basePath+"/users/init/userInfo?showPage=1&page=order";
	}
	if(!(taxSn=='null' ||taxSn==null || taxSn=='' || taxSn=='undefined')){
		$("#taxSn").text(taxSn);
	}else{
		location.href = basePath+"/users/init/userInfo?showPage=1&page=order";
	}
	if(!(apName=='null' ||apName==null || apName=='' || apName=='undefined')){
		$("#apName").text(apName);
	}else{
		location.href = basePath+"/users/init/userInfo?showPage=1&page=order";
	}
	if(!(mobilephone=='null' ||mobilephone==null || mobilephone=='' || mobilephone=='undefined')){
		$("#mobilephone").text(mobilephone);
	}else{
		location.href = basePath+"/users/init/userInfo?showPage=1&page=order";
	}
	if(!(idcard=='null' ||idcard==null || idcard=='' || idcard=='undefined')){
		$("#idcard").text(idcard);
	}else{
		location.href = basePath+"/users/init/userInfo?showPage=1&page=order";
	}
	$("#ss_applyTermNum").text('选择期限');
	if(!(applyType=='1'||applyType=='3')){
		location.href = basePath+"/basic/productList";
	}
	$("#contactName").val(apName);//默认显示法人信息
	$("#telephone").val(mobilephone);
	
	$("#applyType").val(applyType);
	$("#id").val(id);
	//加载页面信息
	if(id != '0'){
		var params_id = {"id":id};
		$.ajax({
			type:"POST",
	        url: basePath+"/users/applyLoan/init_company",
	        data:params_id,
	        async:false,
	        success: function (data) {
//	        	alert(JSON.stringify(data))
	        	if(data.status=='1'){
	        		$("#applyQuota").val(data.applyQuota)
	        		$("#applyTermNum").val(data.applyTermNum)
	        		$("#ss_applyTermNum").attr("data-value",data.applyTermNum);
	        		setOption($("#ss_applyTermNum"));
	        		$("#contactName").val(data.contactName);
	        		$("#telephone").val(data.telephone);
	        	}
	        },
	        error:function(e) {
	        	alert("加载失败!");
	        }
		});
	}
	
	
	//模拟下拉框与取值
    $(".selbox span").click(function(){
		$(this).parents(".item").addClass("zindex100")
		$(this).next("ul").stop(false,true).slideDown(200);		 
	});
	var selboxleave=function(){
			$(".selbox ul").stop(false,true).slideUp(200);
			$(".item").removeClass("zindex100");			
	};
	
	$(".selbox").mouseleave(function(){
		selboxleave();
	});
	
	$(".selbox ul li").on("click",function(){
		if($(this).not(".hover")){
			var v=$(this).attr("data-value");
			$(this).addClass("hover").siblings().removeClass("hover");
			$(this).parents(".selbox").children("span").text($(this).text()).attr("data-value",v);
			var v_id = $(this).parents("ul").attr('id').substring(2, $(this).parents("ul").attr('id').length);
			$("#"+v_id).val(v);
		}
		selboxleave();				
	})
	
	//下一步
	$("#com_next").click(function(){
		if(che_applyQuota() && che_applyTermNum() && che_contactName() && che_telephone()){
			$.ajax({
				type:"POST",
		        url: basePath+"/users/applyLoan/subFirstC",
		        data:$("#com_form").serialize(),
		        success: function (data) {
//		            alert(JSON.stringify(data));
		            if (data.success == "1") {
		               $("#id").val(data.id);
		               if(applyType=='1'){
//		            	   alert("data.id="+data.id)
		            	   $("#qyjyid").val(data.id);
		            	   $("#nextpage1").submit();
//		            	   location.href=basePath+"/users/applyLoan/comUpload?id="+data.id;
		               }else if(applyType=='3'){
		            	   $("#qyfrid").val(data.id);
		            	   $("#nextpage3").submit();
//		            	   location.href=basePath+"/users/applyLoan/apUpload?id="+data.id;
		               }
		            }else if (data.success == "0"){
		            	alert("保存失败!");
		            }
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	if(XMLHttpRequest.responseText=="timeOut"){
		        		location.reload();
		        	}else{
		        		alert("Error");
		        	}
		        }
		    })
		}else{
			return false;
		}
	});
		
});

//校验贷款金额
function che_applyQuota(){
	if(!/^([1-9][\d]{0,8})$/.test($("#applyQuota").val())){
		$("#e_applyQuota").show();
		return false;
	}else{
		$("#e_applyQuota").hide();
		return true;
	}
}
//校验贷款期限
function che_applyTermNum(){
	if($("#ss_applyTermNum").text()=='选择期限'){
		$("#e_applyTermNum").show();
		return false;
	}else{
		$("#e_applyTermNum").hide();
		return true;
	}
}
//联系人姓名
function che_contactName(){
	if(!/^[\u4e00-\u9fa5]{2,8}$/.test($("#contactName").val())){
		$("#e_contactName").show();
		return false;
	}else{
		$("#e_contactName").hide();
		return true;
	}
}
//校验联系人手机
function che_telephone(){
	if(!/^1(([3|4|5|8][0-9]{9})|[7](0[0-9]{8}|8[0-9]{8}))$/.test($("#telephone").val())){
		$("#e_telephone").show();
		return false;
	}else{
		$("#e_telephone").hide();
		return true;
	}
}

//给下拉框赋值
function setOption(s){
//	var s=$(".selbox span");
	for(i=0;i<s.length;i++){
		var v1=s.eq(i).attr("data-value");
		var sl=s.eq(i).next("ul").children("li");
		for(a=0;a<sl.length;a++){
			var v2=sl.eq(a).attr("data-value");
			if(v2==v1){
				sl.eq(a).addClass("hover");
				s.eq(i).text(sl.eq(a).text())
			}
		}		
	}
}


