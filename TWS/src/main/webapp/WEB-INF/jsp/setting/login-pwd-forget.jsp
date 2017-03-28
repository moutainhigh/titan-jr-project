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

<div class="register r_three forget r_alter">
	<div class="r_box p_t60">	
		<div class="r_c ">
			<div class="r_text pass_userlogin">
				<ul>			
					<li class="f_14 l_h24">用户名：<span id="userLoginName">${tfsUserLoginName }</span></li>		
					<li class="r_yzm m_b30">
					<input type="text" class="text" placeholder="请输入验证码" id="code" require="true" datatype="/\w*/" errormsg="验证码错误"><div class="r_verify">获取验证码</div>
					</li>			
					<li class="lb_btn"><a href="javascript:;" class="" onclick="next()">下一步</a></li>	
				</ul>
			</div>
		</div>		
	</div>
</div>
<jsp:include page="/comm/foot.jsp"></jsp:include>
<form action="<%=basePath %>/ex/login-pwd-set.shtml" method="post" id="to_pwd_set_form">
	<input type="hidden" name="userLoginName" id="f_userLoginName"/>
	<input type="hidden" name="code" id="f_code"/>
</form>
<script type="text/javascript">
F.UI.scan();
var passUserloginForm = new validform('.pass_userlogin');
 
//下一步
function next(){
	if(!passUserloginForm.validate()){
		return;	
	}
	var receiveAddressEle = $("#userLoginName");
	//校验验证码
	$.ajax({
		type:"post",
		url: '<%=basePath%>/ex/checkCode.shtml',		
		data:{"userLoginName":receiveAddressEle.html(),"code":$("#code").val()},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				$("#f_userLoginName").val(receiveAddressEle.html());
				$("#f_code").val($("#code").val());
				$("#to_pwd_set_form").submit();
			}else{
				new top.Tip({msg : result.msg, type: 3, timer:2000});
			}
		},
		beforeSend:function(){
	    	  top.F.loading.show();
	    },
	    complete:function(){
	    	  top.F.loading.hide();
	    },
		error:function(){
			new top.Tip({msg : '系统错误，请重试!', type: 2, timer:2000});
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
