<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>修改付款密码-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body style="min-width: 1300px;" class="bg" >
<jsp:include page="/comm/head-title.jsp">
	<jsp:param value="修改付款密码" name="title"/>
</jsp:include>

<div class="register r_three forget r_alter">
	<div class="r_box">		
		<div class="r_c overview_pop">
			<div class="r_tit"><b>原密码：</b></div>			
				<!-- 密码 -->
				<div class="password_set " id="password_set">
				<div class="press_pass"></div>
				<ul class="passwordset_u1" >
					<li class="" id="six_pwd_err_1">					
						<div class="sixDigitPassword" id="passwordbox"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>
						<!-- 错误提示 -->
						<div class="rp_hint" style="font-size:14px;">原密码错误</div>
					</li>
				</ul>
				</div>
		
			<div class="r_tit"><b>新密码：</b></div>	
			
				<div class="password_set " id="password_set">
				<div class="press_pass"></div>
				<ul class="passwordset_u1" >
					<li id="six_pwd_err_2">
						
						<div class="sixDigitPassword" id="passwordbox1"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>
						<!-- 错误提示 -->
						<div class="rp_hint" style="font-size:14px;"></div>
					</li>
				</ul>
				
			</div>	
			<div class="r_tit"><b>确认密码：</b></div>	
			
				<div class="password_set " id="password_set">
				<div class="press_pass"></div>
				<ul class="passwordset_u1" >
					<li id="six_pwd_err_3">
						
						<div class="sixDigitPassword" id="passwordbox2"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>
						<!-- 错误提示 -->
						<div class="rp_hint" style="font-size:14px;">两次输入的密码不一致</div>
					</li>
				</ul>				
			</div>

			<div class="r_text" style="margin-top:10px;">
				<ul>
					<li class="lb_btn"><a href="javascript:;" class="" onclick="savePayPwd()">确定</a></li>
				</ul>
			</div>
		</div>		
	</div>
</div>
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script>
//密码输入框
var PasswordStr=null;
var PasswordStr1=null;
var PasswordStr2=null;
var htmlPwd,htmlPwd1,htmlPwd2;
$("document").ready(function (){
	PasswordStr=new sixDigitPassword("passwordbox");
	PasswordStr1=new sixDigitPassword("passwordbox1");
	PasswordStr2=new sixDigitPassword("passwordbox2");
	htmlPwd = $("#passwordbox").html();
	htmlPwd1 = $("#passwordbox1").html();
	htmlPwd2 = $("#passwordbox2").html();
});
//校验密码输入
function validate_payPassword(){
	var payPassword = PasswordStr.returnStr();
	var payPassword1 = PasswordStr1.returnStr();
	var payPassword2 = PasswordStr2.returnStr();
	$("#six_pwd_err_1").removeClass("sp_add");
	$("#six_pwd_err_2").removeClass("sp_add");
	$("#six_pwd_err_3").removeClass("sp_add");
	
	if((payPassword.length!=6)||(!payPwd_reg.test(payPassword))){
		$("#six_pwd_err_1").addClass("sp_add");
		$("#six_pwd_err_1 .rp_hint").html('原密码必须为6位数字');
		reInitOldPwd();
		return false;
	}
	
	if(payPassword1.length!=6||payPassword2.length!=6||(!payPwd_reg.test(payPassword1))||(!payPwd_reg.test(payPassword2))){
		$("#six_pwd_err_3").addClass("sp_add");
		$("#six_pwd_err_3 .rp_hint").html('新密码必须为6位数字');
		reInitNewPwd();
		return false;
	}
	
	if(payPassword1!=payPassword2){
		$("#six_pwd_err_3").addClass("sp_add");
		$("#six_pwd_err_3 .rp_hint").html('两次新密码输入不相同');
		reInitNewPwd();
		return false;
	}
	
	if(payPassword==payPassword1){
		$("#six_pwd_err_2").addClass("sp_add");
		$("#six_pwd_err_2 .rp_hint").html('新密码和原密码不能相同！');
		
		reInitNewPwd();
		return false;
	}
	return true;
	
}
function reInitOldPwd(){
	$("#passwordbox").html(htmlPwd);
	$("#passwordbox1").html(htmlPwd1);
	$("#passwordbox2").html(htmlPwd2);
	PasswordStr=new sixDigitPassword("passwordbox");
	PasswordStr1=new sixDigitPassword("passwordbox1");
	PasswordStr2=new sixDigitPassword("passwordbox2");
	window.setTimeout(function(){
		$("#six_pwd_err_1").removeClass("sp_add");
		$("#six_pwd_err_2").removeClass("sp_add");
		$("#six_pwd_err_3").removeClass("sp_add");
	}, 2000);
	window.setTimeout(function(){
		clickPassword();
	}, 200);
}
function reInitNewPwd(){
	$("#passwordbox1").html(htmlPwd1);
	$("#passwordbox2").html(htmlPwd2);
	PasswordStr1=new sixDigitPassword("passwordbox1");
	PasswordStr2=new sixDigitPassword("passwordbox2");
	window.setTimeout(function(){
		$("#six_pwd_err_1").removeClass("sp_add");
		$("#six_pwd_err_2").removeClass("sp_add");
		$("#six_pwd_err_3").removeClass("sp_add");
	}, 2000);
	window.setTimeout(function(){
		clickPassword();
	}, 200);
}
//保存支付密码
function savePayPwd(){
	 var validate_password = validate_payPassword();
     if(validate_password==true){
  	   update_old_pwd();
     }
     else{
  	   setTimeout(function(){
  		   clickPassword();
    		},500);
     }
}
//
function update_old_pwd(){
	$.ajax({
		 type: "post",
         url: "<%=basePath%>/account/setPayPassword.action",
         data: {
        	 oldPassword:PasswordStr.returnStr(),
        	 payPassword:PasswordStr1.returnStr()
         },
         dataType: "json",
        success: function(data){
        	 if(data.result=="success"){//密码设置成功
        		 location.href="<%=basePath%>/ex/pwd-set-succ.shtml?pageType=2";
        	 }else{
        		 $("#six_pwd_err_1").addClass("sp_add");
       			$("#six_pwd_err_1 .rp_hint").html(data.msg);
       			reInitOldPwd();
        	 }
         }
	});
}
//到下一行密码框
function clickPassword()
{
	$('#passwordbox').click();
	//监控第一个输入框的最后一个
	timeIndex = setInterval(function(){
			try
			{
				if($('#passwordbox i:last b:first-child').attr('style').indexOf('inherit') != -1)
				{
					$('#passwordbox1').click();
					clearInterval(timeIndex);
				}
			}catch(e)
			{}
	},100);
	//监控第二个输入框的最后一个
	timeIndex1 = setInterval(function(){
		try
		{
			if($('#passwordbox1 i:last b:first-child').attr('style').indexOf('inherit') != -1)
			{
				$('#passwordbox2').click();
				clearInterval(timeIndex1);
			}
		}catch(e)
		{}
	},100);
	timeIndex2 = setInterval(function(){
		try
		{
			if($('#passwordbox2 i:last b:first-child').attr('style').indexOf('inherit') != -1)
			{
				clearInterval(timeIndex2);
			}
		}catch(e)
		{}
	},100);
}
clickPassword();
</script>
</body>
</html>
