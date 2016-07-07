<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>支付密码-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
<div class="S_popup clearfix S_popTop ">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 50px;">修改支付密码</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content1" style="top:0px; padding:0 20px; width: 500px;">
		<div class="password_set">
			<div class="passwordf">
				<ul class="passwordset_u2">
					<li>
						<span class="reset_pass">泰坦金服用户名：</span>
						<input type="text" class="text w_250" id="userName" placeholder="请输入手机号或邮箱"></li>
					<li>
						<span class="reset_pass">验证码：</span>
						<p class="text w_250">
							<input type="text" class="TFSother_input">
							<span class="sendmes curpo">获取验证码</span>
						</p>
					</li>
				</ul>
				<div class="J_next">
					<span class="btn p_lr30">下一步</span>
				</div>
			</div>
			<div class="passwordf_next" style="display: none;">
				<ul class="passwordset_u1 clearf">
					<li>
						<span class="pass_name">新密码：</span>
						<div class="sixDigitPassword" id="passwordbox3"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>
					</li>
					<li>
						<span class="pass_name">确认新密码：</span>
						<div class="sixDigitPassword" id="passwordbox4"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>
					</li>
				</ul>
				<div class="pas_close">
					<span class="btn p_lr30 J_close">确定</span>
					<span class="btn btn_grey btn_exit">取消</span>
				</div>
			</div>
		</div>
	</div>
</div>
<!--弹窗白色底-->
<script src="js/password.js"></script>
<script>
//下一步
$('.J_next').on('click',function(){
    //需要验证用户名和验证码
	var data =  getMessageData();
	var code = $(".TFSother_input").val();
	//验证其正确性
	if( typeof data.userName =="undefined" || data.userName.length<1 ){
		new top.Tip({msg : '用户名不能为空！', type: 1, timer:2000});      
		return;
	}
	
	 var flag = validate_email_or_phone(data);
	 if(!flag){
		 new top.Tip({msg : '用户名必须是您注册时的手机或者邮箱！', type: 1, timer:2000});      
	     return;
	 }
	
	if(typeof code =="undefined" || code.length<1){
		new top.Tip({msg : '验证码必须填写！', type: 1, timer:2000});      
		return;
	}
	
	var codeTest = /^\d{6}$/;
	if(!codeTest.test(code)){
		new top.Tip({msg : '验证码必须输入6位数字！', type: 1, timer:2000});      
		return;
	}
	 $(".passwordf").hide();
	 $(".passwordf_next").show(); 
    
}); 

$(".J_close").on('click',function(){
	//修改密码
    var data = forget_pwd_data();
    $.ajax({
		 type: "post",
        url: "<%=basePath%>/account/forgetPayPassword.shtml",
        data: {
        	userName:data.userName,
    		code:data.code,
    		payPassword:data.payPassword
        },
        dataType: "json",
        success: function(data){
       	 if(data.code=="1"){//短信发送成功
       	    new top.Tip({msg : '密码成功找回！', type: 1, timer:2000});      
       		$(this).addClass('huise');
    	        timeOut($(this));
       	 }else{
       		 new top.Tip({msg : 'data.msg', type: 1 , time:1000});   
       	 }
        }    		
	});
});


function forget_pwd_data(){
	var userName =  $("#userName").val();
	var code = $(".TFSother_input").val();
	var payPassword = PasswordStr6.returnStr();
	var payPassword2 = PasswordStr7.returnStr();
	
	if(payPassword2.length !=6 || payPassword.length!=6){
		new top.Tip({msg : '密码必须为6位数！', type: 1, timer:2000});      
		return;
	}
	if(payPassword2 !=payPassword){
		new top.Tip({msg : '两次输入的密码必须相同！', type: 1, timer:2000});      
		return;
	}
	
	return{
		userName:userName,
		code:code,
		/* payPassword:rsaData(payPassword) */
		payPassword:payPassword
	};
}

//获取验证码
function timeOut(_this){
    var i=60;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送(" + i + ")"); 
             i--;
         }else{
            _this.removeClass("huise").html("获取验证码");
            clearInterval(interval);
         }
    }, 1000);
}
$('.sendmes').on('click',function(){
	var phoneOrEmail=getMessageData();
	var flag = validate_email_or_phone(phoneOrEmail);
	if(flag == true){
		if(!$(this).hasClass("huise")){
	    	$.ajax({
	    		 type: "post",
		         url: "<%=basePath%>/organ/sendCode.shtml",
		         data: {
		        	 receiveAddress:phoneOrEmail.userName,
		        	 msgType:3
		         },
		         dataType: "json",
		         success: function(data){
		        	 if(data.code=="1"){//短信发送成功
		        	    new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:1000});      
		        		$(".sendmes").addClass('huise');
		     	        timeOut($(".sendmes"));
		        	 }else{
		        		 new top.Tip({msg : 'data.msg', type: 1 , time:1000});   
		        	 }
		         },  		
	    	});
	    } 
	}
       
});

function getMessageData(){
	var userName = $("#userName").val();
	if(userName.length>0){
		return {
			userName:userName
		};
	}
	return "";
}

function validate_email_or_phone(data){//验证其输入的是不是邮箱或者手机号
	if(data.userName.length>0){
		var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var phone = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(email.test(data.userName) || phone.test(data.userName) ){
			return true;
		}
		return false;
	}
}

var PasswordStr6=new sixDigitPassword("passwordbox3");
var PasswordStr7=new sixDigitPassword("passwordbox4");

</script>


</body>
</html>
