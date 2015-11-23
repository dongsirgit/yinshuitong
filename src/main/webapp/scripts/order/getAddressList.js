/**
 * 本文件用来获取省、市、区/县的三级联动列表
 */
//省
function loadProvince(regionId){
	  $("#u_province").html("");
	  $("#u_province").append("<li data-value=''>请选择省份</li>");
	  var jsonStr = getAddress(regionId,0);
	  for(var k in jsonStr) {		//<li data-value="0">新疆维吾尔族自治区</li>
		$("#u_province").append("<li data-value='"+k+"'>"+jsonStr[k]+"</li>");
	  }
	  if(regionId.length!=6) {
		$("#u_city").html("");
	    $("#u_city").append("<li data-value=''>请选择城市</li>");
		$("#u_district").html("");
	    $("#u_district").append("<li data-value=''>请选择区域</li>");
	  } else {
//		 $("#province").val(regionId.substring(0,2)+"0000");
		 $("#u_province").attr("data-value",regionId.substring(0,2)+"0000");
		 loadCity(regionId);
	  }
	}
//市
function loadCity(regionId){
	  $("#u_city").html("");
	  $("#u_city").append("<li data-value=''>请选择城市</li>");
	  if(regionId.length!=6) {
		$("#u_district").html("");
	    $("#u_district").append("<li data-value=''>请选择区域</li>");
	  } else {
		var jsonStr = getAddress(regionId,1);
	    for(var k in jsonStr) {
		  $("#u_city").append("<li data-value='"+k+"'>"+jsonStr[k]+"</li>");
	    }
		if(regionId.substring(2,6)=="0000") {
		  $("#u_district").html("");
	      $("#u_district").append("<li data-value=''>请选择区域</li>");
		} else {
		   $("#u_city").val(regionId.substring(0,4)+"00");
		   loadArea(regionId);
		}
	  }
	}
//县
function loadArea(regionId){
	  $("#u_district").html("");
	  $("#u_district").append("<li data-value=''>请选择区域</li>");
	  if(regionId.length==6) {
	    var jsonStr = getAddress(regionId,2);
	    for(var k in jsonStr) {
		  $("#u_district").append("<li data-value='"+k+"'>"+jsonStr[k]+"</li>");
	    }
		if(regionId.substring(4,6)!="00") {$("#u_district").val(regionId);}
	  }
	}
//第一处房产
function loadProvince1(regionId){
	  $("#fangAddProvince1").html("");
	  $("#fangAddProvince1").append("<option value=''>请选择省份</option>");
	  var jsonStr = getAddress(regionId,0);
	  for(var k in jsonStr) {
		$("#fangAddProvince1").append("<option value='"+k+"'>"+jsonStr[k]+"</option>");
	  }
	  if(regionId.length!=6) {
		$("#fangAddCity1").html("");
	    $("#fangAddCity1").append("<option value=''>请选择城市</option>");
		$("#fangAddDistrict1").html("");
	    $("#fangAddDistrict1").append("<option value=''>请选择区域</option>");
	  } else {
		 $("#fangAddProvince1").val(regionId.substring(0,2)+"0000");
		 loadCity(regionId);
	  }
	}

	function loadCity1(regionId){
	  $("#fangAddCity1").html("");
	  $("#fangAddCity1").append("<option value=''>请选择城市</option>");
	  if(regionId.length!=6) {
		$("#fangAddDistrict1").html("");
	    $("#fangAddDistrict1").append("<option value=''>请选择区域</option>");
	  } else {
		var jsonStr = getAddress(regionId,1);
	    for(var k in jsonStr) {
		  $("#fangAddCity1").append("<option value='"+k+"'>"+jsonStr[k]+"</option>");
	    }
		if(regionId.substring(2,6)=="0000") {
		  $("#fangAddDistrict1").html("");
	      $("#fangAddDistrict1").append("<option value=''>请选择区域</option>");
		} else {
		   $("#fangAddCity1").val(regionId.substring(0,4)+"00");
		   loadArea(regionId);
		}
	  }
	}

	function loadArea1(regionId){
	  $("#fangAddDistrict1").html("");
	  $("#fangAddDistrict1").append("<option value=''>请选择区域</option>");
	  if(regionId.length==6) {
	    var jsonStr = getAddress(regionId,2);
	    for(var k in jsonStr) {
		  $("#fangAddDistrict1").append("<option value='"+k+"'>"+jsonStr[k]+"</option>");
	    }
		if(regionId.substring(4,6)!="00") {$("#fangAddDistrict1").val(regionId);}
	  }
	}

//	第二处房产
	function loadProvince2(regionId){
		  $("#fangAddProvince2").html("");
		  $("#fangAddProvince2").append("<option value=''>请选择省份</option>");
		  var jsonStr = getAddress(regionId,0);
		  for(var k in jsonStr) {
			$("#fangAddProvince2").append("<option value='"+k+"'>"+jsonStr[k]+"</option>");
		  }
		  if(regionId.length!=6) {
			$("#fangAddCity2").html("");
		    $("#fangAddCity2").append("<option value=''>请选择城市</option>");
			$("#fangAddDistrict2").html("");
		    $("#fangAddDistrict2").append("<option value=''>请选择区域</option>");
		  } else {
			 $("#fangAddProvince2").val(regionId.substring(0,2)+"0000");
			 loadCity(regionId);
		  }
		}

		function loadCity2(regionId){
		  $("#fangAddCity2").html("");
		  $("#fangAddCity2").append("<option value=''>请选择城市</option>");
		  if(regionId.length!=6) {
			$("#fangAddDistrict2").html("");
		    $("#fangAddDistrict2").append("<option value=''>请选择区域</option>");
		  } else {
			var jsonStr = getAddress(regionId,1);
		    for(var k in jsonStr) {
			  $("#fangAddCity2").append("<option value='"+k+"'>"+jsonStr[k]+"</option>");
		    }
			if(regionId.substring(2,6)=="0000") {
			  $("#fangAddDistrict2").html("");
		      $("#fangAddDistrict2").append("<option value=''>请选择区域</option>");
			} else {
			   $("#fangAddCity2").val(regionId.substring(0,4)+"00");
			   loadArea(regionId);
			}
		  }
		}

		function loadArea2(regionId){
		  $("#fangAddDistrict2").html("");
		  $("#fangAddDistrict2").append("<option value=''>请选择区域</option>");
		  if(regionId.length==6) {
		    var jsonStr = getAddress(regionId,2);
		    for(var k in jsonStr) {
			  $("#fangAddDistrict2").append("<option value='"+k+"'>"+jsonStr[k]+"</option>");
		    }
			if(regionId.substring(4,6)!="00") {$("#fangAddDistrict2").val(regionId);}
		  }
		}

	