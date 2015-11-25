    $(document).ready(function() {
        //两个密码框状态转换
        $(".pwdState").on({
            focus:function(){
                if($(this).attr("type")=="text"){
                    $(this).css("display","none").prev().css("display","").css("color","black").focus();
                }else{
                    $(this).css("color","black");
                }
            },
            blur:function(){
                if($.trim($(this).val())==""){
                    $(this).val("").css("display","none").next().css("display","");
                }else{
                    $(this).css("color","#b0b0b0");
                }
            }
        });
        //其它输入框状态转换
        $(".inputState").on({
            focus:function(){
                $(this).css("color","black");
                var defaultValue = $(this).next().text();
                if($(this).val() == defaultValue){
                    $(this).val("").select();
                }
            },
            blur:function(){
                $(this).css("color","#b0b0b0");
                var defaultValue = $(this).next().text();
                if($.trim($(this).val())==""){
                    $(this).val(defaultValue);
                }
            }
        });
        //label点击热区设置
        $(document).delegate("#myform label","click",function(){
            if($(this).next().css("display")!="none"){
                $(this).next().focus();
            }else{
                 $(this).next().css("display","").focus().next().css("display","none");
            }
        });
    });