$(document).ready(function(e) {
	//模拟下拉框与取值
    $(".selbox span").click(function(){
		$(this).parents(".item").addClass("zindex100")
		$(this).next("ul").stop(false,true).slideDown(200);		 
		});
	var selboxleave=function(){
			$(".selbox ul").stop(false,true).slideUp(200);
			$(".item").removeClass("zindex100");			
		}
	$(".selbox").mouseleave(function(){selboxleave();});
	$(".selbox ul li").on("click",function(){
			if($(this).not(".hover")){
				$(this).addClass("hover").siblings().removeClass("hover");
				$(this).parents(".selbox").children("span").text($(this).text());				
				}
			selboxleave();				
			})
	//单选框状态显示，内置隐藏的input
	$(".rediobox").click(function(e) {        
		if($(this).not(".sel")){
			$(this).addClass("sel").siblings().removeClass("sel")
			}
    });
	//街道input的value
	$(".jiedao").focus(function(){
		if($(this).val()=="街道"){
		$(this).val("").removeClass("hoder")
		}
		});
	$(".jiedao").blur(function(){
		if($(this).val()==""){
			$(this).val("街道").addClass("hoder")
			}
		});
	//服务协议已阅读状态
	$(".readdiv span").click(function(){
		$(this).children("em").toggleClass("colorf")
		});
	//第一次加载状态显示	
	$(".orderform:not('.orderformed'):eq(1),.orderform:not('.orderformed'):eq(2),.orderformed:eq(0),.orderh3a").hide()	
	$(".orderform:eq(0) .itembtn1").one("click",function(){
		$(".orderformed:eq(0),.orderform:not('.orderformed'):eq(1),.orderh3a:eq(0)").show();
		$(".orderform:eq(0),.orderformed:eq(1)").hide();
		});
	$(".orderform:not('.orderformed'):eq(1) .itembtn1").one("click",function(){
		$(".orderformed:eq(1),.orderform:not('.orderformed'):eq(2),.orderh3a:eq(1)").show();
		$(".orderformed:eq(2),.orderform:not('.orderformed'):eq(1)").hide();
		});
	$(".orderform:not('.orderformed'):eq(2) .itembtn1").one("click",function(){
		$(".orderformed:eq(2),.orderh3a:eq(2)").show();
		$(".orderform:not('.orderformed'):eq(2)").hide();
		});
	//修改与保存切换
	$(".orderh3a").click(function(){
		$(this).hide();
		$(this).parent(".orderh3").next(".orderform").show();
		$(this).parent(".orderh3").next(".orderform").next(".orderformed").hide();
		$(this).parent(".orderh3").next(".orderform").find(".itembtn1").click(function(){
			$(this).parents(".orderform").hide();
			$(this).parents(".orderform").next(".orderformed").show();
			$(this).parents(".orderform").prev(".orderh3").children(".orderh3a").show();
			})
		})
	//正式提交浮层
	$(".mask_alpha,.fdiv").hide();
	$(".btnboxa2").click(function(){
		$(".fdiv").show(200);
		$(".mask_alpha").show();
		});
	$(".fdivbtn2").click(function(){
		$(".mask_alpha,.fdiv").hide(200);
		});
		
	//相关资料的图片显示
	var l=$(".piclist li");
	$(".piclist li img").each(function(i){
			var img = $(this);
			var realWidth;
			var realHeight;
		$("<img/>").attr("src", $(img).attr("src")).load(function(){
			realWidth = this.width;
			realHeight = this.height;			
			$(img).attr("width",realWidth).attr("height",realHeight)
			//alert(l.eq(0).find("img").width())
			var imgw=l.eq(i).find("img").width();		
			var imgh=l.eq(i).find("img").height();
		if(imgw>219){
			if(imgw > imgh){
				var r=imgh/imgw*219/2;
			l.eq(i).find("img").addClass("imgw").css({"margin-top":-r})
			}else{
				l.eq(i).find("img").addClass("imgh")/*.css({"margin-left":-imgw/2})*/
				}
		}else{
			if(imgh>168){
				l.eq(i).find("img").addClass("imgh")
				}else{
					l.eq(i).find("img").addClass("imgh");
					}
		}		
		//相关资料的图片放大显示			
		var winw=$(window).width();
		var winh=$(window).height();		
		l.eq(i).on("click",function(){
			var pichtml=$(this).find("span").html();
			var pichtmlw=$(this).find("img").attr("width");
			var pichtmlh=$(this).find("img").attr("height");			
			$(".mask,.alertimgbox").show();
			$(".alertimgbox").html(pichtml+'<p>点击图片或空白处关闭预览</p>').css({"top":$(document).scrollTop()+Math.abs(winh-pichtmlh)/2,"left":0});
			$(".alertimgbox img").removeAttr("style").removeClass("imgw imgh")
			if(pichtmlw>=winw){
				$(".alertimgbox img").css({"width":winw-106});
				if(winw/pichtmlw*pichtmlh>winh){
					$(".alertimgbox").css({"top":$(document).scrollTop(),"left":50});
					}else{
						$(".alertimgbox").css({"top":$(document).scrollTop()+Math.abs(winw/pichtmlw*pichtmlh-pichtmlh)/2,"left":50});
						}							
				}else{
					$(".alertimgbox").css("left",winw/2-pichtmlw/2-3);
					if(pichtmlh>winh){
						$(".alertimgbox").css({"top":$(document).scrollTop()});
						}
					}			
			});
		$(".mask,.alertimgbox").on("click",function(){$(".mask,.alertimgbox").hide();})				
	});
	});	
});