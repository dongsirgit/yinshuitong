$(function(){
	var list = null;
	//加载页面 取list
	$.ajax({
		type:"POST",
        url: "/banktax/loanList/initpage",
        success: function (data) {
            alert(JSON.stringify(data));
            
            list = data.list;
            
            if(list!=null){
            	alert("加载表格");
            }
            
        },
        error:function(e) {
        	alert(error);
        }
    });
	
	//切换
	$('.content_l ul li').click(function(e) {
        var index=$(this).index();
		$(this).addClass('current').siblings().removeClass('current');
		$('.content_r ul li').eq(index).css('display','block').siblings().css('display','none');
    });
	//表格隔行有背景色
	$('.loan_list table tr:odd').css('background','#fffceb')
	//表单
	$('input:eq(1)').focus(function(e) {
		if( $(this).val()== '(6-20位的大小写字母、数字)' ){
			$(this).val('')
		};
    });
	
	$('input:eq(1)').blur(function(e) {
		if( $(this).val()=='' ){
			$(this).val('(6-20位的大小写字母、数字)')
		}
    });
	$(".tc").hide();
	$(".mask_alpha,.fdiv").hide();
	$(".handle a:eq(2)").click(function(){
		$(".fdiv").show(200);
		$(".mask_alpha").show();
		});
	$(".fdivbtn2").click(function(){
		$(".mask_alpha,.fdiv").hide(200);
		});
	$(".change_password a").click(function(){
		$(".tc").show(200);
		$(".mask_alpha").show();
		});
	$(".tc a").click(function(){
		$(".mask_alpha,.tc").hide(200);
		});
})
