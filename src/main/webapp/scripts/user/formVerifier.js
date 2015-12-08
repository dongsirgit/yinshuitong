var verifier={
		//验证的表单对象
		userName:"",
		mobilePhone:"",
		userPwd1:"",
		userPwd2:"",
		phoneCode:"",
		imgCode:"",
		email:"",
		//消息提示框对象
		userNameMsg:"",
		mobilePhoneMsg:"",
		userPwd1Msg:"",
		userPwd2Msg:"",
		phoneCodeMsg:"",
		imgCodeMsg:"",
		emailMsg:"",
		//验证正则
		reg_userName:"",
		reg_mobilePhone:"",
		reg_userPwd:"",
		reg_userPwd_weak:"",
		reg_userPwd_normal:"",
		reg_userPwd_strong:"",
		reg_phoneCode:"",
		reg_imgCode:"",
		reg_email:"",
		init:function(settings){
			this.userName=this.getRealValue(settings.userName);
			this.mobilePhone=this.getRealValue(settings.mobilePhone);
			this.userPwd1=this.getRealValue(settings.userPwd1);
			this.userPwd2=this.getRealValue(settings.userPwd2);
			this.phoneCode=this.getRealValue(settings.phoneCode);
			this.imgCode=this.getRealValue(settings.imgCode);
			this.email=this.getRealValue(settings.email);
			this.userNameMsg=settings.userNameMsg?settings.userNameMsg:"";
			this.mobilePhoneMsg=settings.mobilePhoneMsg?settings.mobilePhoneMsg:"";
			this.userPwd1Msg=settings.userPwd1Msg?settings.userPwd1Msg:"";
			this.userPwd2Msg=settings.userPwd2Msg?settings.userPwd2Msg:"";
			this.phoneCodeMsg=settings.phoneCodeMsg?settings.phoneCodeMsg:"";
			this.imgCodeMsg=settings.imgCodeMsg?settings.imgCodeMsg:"";
			this.emailMsg=settings.emailMsg?settings.emailMsg:"";
			this.reg_userName=settings.userNameMsg?settings.userNameMsg:"";
			this.reg_mobilePhone=settings.reg_mobilePhone?settings.reg_mobilePhone:"";
			this.reg_userPwd=settings.reg_userPwd?settings.reg_userPwd:this.reg_userPwd;
			this.reg_userPwd_weak=settings.reg_userPwd_weak?settings.reg_userPwd_weak:this.reg_userPwd_weak;
			this.reg_userPwd_normal=settings.reg_userPwd_normal?settings.reg_userPwd_normal:this.reg_userPwd_normal;
			this.reg_userPwd_strong=settings.reg_userPwd_strong?settings.reg_userPwd_strong:this.reg_userPwd_strong;
			this.reg_phoneCode=settings.reg_phoneCode?settings.reg_phoneCode:"";
			this.reg_imgCode=settings.reg_imgCode?settings.reg_imgCode:"";
			this.reg_email=settings.reg_email?settings.reg_email:"";
			return this;
		},
		getRealValue:function(jqueryObj){
			if(jqueryObj){
				var realValue=$.trim(jqueryObj.val());
				jqueryObj.val(realValue);
				return realValue;
			}
			return "";
		},
		checkUserName:function(){
			var userName=this.userName,
				userNameMsg=this.userNameMsg,
				reg_userName=this.reg_userName,
				result=false;
			if(!userName){
				userNameMsg.text("用户名不能为空！");
			}else if(!reg_userName.test(userName)){
				userNameMsg.text("用户名不符合规则！");
			}else{
				result=true;
			}
			return result;
		},
		checkMobilePhone:function(){
			var mobilePhone=this.mobilePhone,
				mobilePhoneMsg=this.mobilePhoneMsg,
				reg_mobilePhone=this.reg_mobilePhone,
				result=false;
			if(!mobilePhone){
				mobilePhoneMsg.text("手机号不能为空");
			}else if(!reg_mobilePhone.test(mobilePhone)){
				mobilePhoneMsg.text("请输入合法的手机号");
			}else{
				$.ajax({
		             type:"POST",
		             url:basePath+"/user/checkMobilePhone", 
		             data:{"mobilePhone":mobilePhone},
		             async:false,
		             success:function(data){
		            	 if(data == 0){
		     				result = "noexist";
		            	 }else if(data == 1){
		            		result = "exist";
		            	 }else{
		            		//未接收到参数异常码为15
		            		mobilePhoneMsg.text("参数异常！");
		            	 }
		             }
		        });
			}
			return result;
		},
		checkPwd1:function(){
			var userPwd1=this.userPwd1,
				userPwd1Msg=this.userPwd1Msg,
				reg_userPwd=this.reg_userPwd;
				reg_userPwd_weak=this.reg_userPwd_weak,
				reg_userPwd_normal=this.reg_userPwd_normal,
				reg_userPwd_strong=this.reg_userPwd_strong,
				result=false;
			if(!userPwd1){
				userPwd1Msg.text("密码不能为空！");
			}else if(!reg_userPwd.test(userPwd1)){
				userPwd1Msg.text("请输入8-16位的数字,字母,特殊字符");
			}else{
				userPwd1Msg.text("");
				if(reg_userPwd_weak.test(userPwd1)){
					result="weak";
				}else if(reg_userPwd_normal.test(userPwd1)){
					result="normal";
				}else if(reg_userPwd_strong.test(userPwd1)){
					result="strong";
				}else{
					//防止验证规则有问题
					userPwd1Msg.text("密码不符合规则！！");
				}
			}
			return result;
		},
		checkPwd2:function(){
			var result=false;
			if(this.checkPwd1()){
				var userPwd1=this.userPwd1,
					userPwd2=this.userPwd2,
					userPwd2Msg=this.userPwd2Msg;
				if(!userPwd2){
					userPwd2Msg.text("确认密码不能为空！");
				}else if(userPwd2 != userPwd1){
					userPwd2Msg.text("两次密码输入不一致");
				}else{
					userPwd2Msg.text("");
					result=true;
				}
			}
			return result;
		},
		checkPhoneCode:function(){
			var phoneCode=this.phoneCode,
				phoneCodeMsg=this.phoneCodeMsg,
				mobilePhone=this.mobilePhone,
				reg_phoneCode=this.reg_phoneCode,
				result=false;
			if(!phoneCode){
				phoneCodeMsg.text("验证码不能为空");
			}else if(!reg_phoneCode.test(phoneCode)){
				phoneCodeMsg.text("请输入合法的验证码");
			}else{
				phoneCodeMsg.text("");
				result=true;
			}
			return result;
		},
		checkPhoneOwner:function(){
			var phoneCode=this.phoneCode,
				phoneCodeMsg=this.phoneCodeMsg,
				mobilePhone=this.mobilePhone,
				result=false;
			$.ajax({
	             type:"POST",
	             url:basePath+"/user/checkPhoneOwner", 
	             data:{"phoneCode":phoneCode,"mobilePhone":mobilePhone},
	             async:false,
	             success:function(data){
	            	 if(data == 0){
	            		 phoneCodeMsg.text("");
	            		 result=true;
	            	 }else if(data == 15){
	            		 phoneCodeMsg.text("验证码错误");
	            	 }else{
	            		 phoneCodeMsg.text("参数异常！");
	            	 }
	             }
	        });
			return result;
		}
}