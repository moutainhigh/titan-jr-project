<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 忘记登录密码  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>修改登录密码-泰坦钱包</title>
    <jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<jsp:include page="/comm/head-title.jsp">
	<jsp:param value="修改登录密码" name="title"/>
</jsp:include>

<div class="register r_three forget">
	<div class="r_box ">		
		<div class="r_c pass_userlogin">
			<div class="r_tit">请输入注册的手机号码或邮箱：</div>
			<div class="r_text">
				<ul>				
					<li class="r_y1"><input type="text" class="text" id="userLoginName" placeholder="手机号码/邮箱" datatype="/\w*/" afterPassed="checkUserName" errormsg="必填项"></li>
					<li class="r_yzm m_b40">
					<input type="text" class="text" placeholder="请输入验证码" id="code" require="true" datatype="/\w*/" errormsg="验证码错误"><div class="r_verify">获取验证码</div>
					</li>
					<li class="lb_btn h100"><a href="javascript:;" class="" onclick="next()">下一步</a></li>
				</ul>
			</div>			
		</div>		
	</div>
</div>

<form action="<%=basePath %>/ex/login-pwd-set.shtml" method="post" id="to_pwd_set_form">
	<input type="hidden" name="userLoginName" id="f_userLoginName"/>
	<input type="hidden" name="code" id="f_code"/>
</form>
<script type="text/javascript">
F.UI.scan();
var passUserloginForm = new validform('.pass_userlogin');
//检查用户名
function checkUserName(value, inputDom){
	if(value.length==0){
		passUserloginForm.setErrormsg(inputDom,'必填项');
		return false;
	}
	if((!phone_reg.test(value))&&(!email_reg.test(value))){
		passUserloginForm.setErrormsg(inputDom,'格式不正确');
		return false;
	}
	return true;
}
//下一步
function next(){
	if(!passUserloginForm.validate()){
		return;	
	}
	var receiveAddress = $("#userLoginName").val();
	//校验验证码
	$.ajax({
		type:"post",
		url: '<%=basePath%>/ex/checkCode.shtml',		
		data:{"userLoginName":receiveAddress,"code":$("#code").val()},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				$("#f_userLoginName").val(receiveAddress);
				$("#f_code").val($("#code").val());
				$("#to_pwd_set_form").submit();
			}else{
				new top.Tip({msg : result.msg, type: 2, timer:2000});
			}
		}
	});
}
$(function(){
	//获取验证码
	TWS.initSendCode({send_btn:$('.r_verify'),receive_input:$("#userLoginName"),msgType:3,fui_form:passUserloginForm,verifyType:"all"});
});
</script>
</body>
</html>
