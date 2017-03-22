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
<jsp:include page="../setting/head.jsp">
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
					<input type="text" class="text" placeholder="请输入验证码" id="code" datatype="/\d{6,}/" errormsg="验证码错误"><div class="r_verify">获取验证码</div>
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
//获取验证码
var sendingFlag = false;
function timeOut(_this){
    var i=60;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送(" + i + ")"); 
             i--;
         }else{
            _this.removeClass("r_huise").html("重新获取验证码");
            clearInterval(interval);
            sendingFlag = false;
         }
    }, 1000);
}
//验证码发送倒计时
$('.r_verify').on('click',function(){
	if(sendingFlag){
		return;
	}
    var raObj = $("#userLoginName");
    var receiveAddress = raObj.val();
    _this = $(this);
	if(!$(this).hasClass("r_huise")){  
		sendingFlag = true;
    	$(this).text("重新发送(60)");
        $(this).addClass('r_huise');
        timeOut($(this));
    }
	$.ajax({
		method:'post',
		url : '<%=basePath%>/ex/sendCode.shtml',
		data:{"receiveAddress":receiveAddress,"msgType":3},
		dataType : 'json',
		success : function(result){
			if(result.code==1){
			    new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});    
			}else{
				new top.Tip({msg : result.msg, type: 2, timer:2500});
			}
		},
		complete:function(){
	    	  top.F.loading.hide();
	    },
		error : function(){
			new top.Tip({msg : '网络错误，请重试', type: 2, timer:2000});
		}
	});
});

</script>
</body>
</html>
