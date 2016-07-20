<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 忘记登录密码  -->
<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
<div class="S_popup clearfix S_popTop forget_wrap">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 50px;">修改登录密码</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content1" style="top:0px; padding:0 20px; width: 500px;">
		<div class="password_set">
			<div class="passwordf pass_userlogin">
				<ul class="passwordset_u2">
					<li>
						<span class="reset_pass">泰坦金服用户名：</span>
						<input type="text" class="text w_250" id="userLoginName" placeholder="请输入手机号或邮箱" customFun="mobileCheck" /></li>
					<li>
						<span class="reset_pass">验证码：</span>
						<p class="text w_250 f_code">
							<input type="text" class="TFSother_input" id="code" datatype="/\d{6,}/" errormsg="请输入6位数字验证码"/>
							<span class="sendmes curpo">获取验证码</span>
						</p>
					</li>
				</ul>
				<div class="J_next">
					<span class="btn p_lr30 forget_next">下一步</span>
				</div>
			</div>
			<div class="passwordf_next" style="display: none;">
				<ul class="passwordset_u2 pass_modify clearf" style="margin:0px; padding-bottom:20px;">
					<li>
						<span class="reset_pass">新密码：</span>
						<input type="password" class="text w_250" id="newLoginPassword" placeholder="建议至少使用两种字符组合" datatype="/\w{6,}/" errormsg="密码长度至少6位"/>
						<span class="ico eye"></span>
					</li>
					<li>
						<span class="reset_pass">确认新密码：</span>
						<input type="password" class="text w_250" name="passwordConfirm" id="passwordConfirm" placeholder="请再次输入密码"  customFun="confirmPass"/>
						<span class="ico eye"></span>
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
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">
F.UI.scan();
var passUserloginForm = new validform('.pass_userlogin',{
	msgAlign: 'bottom'
});
var passNextForm = new validform('.passwordf_next',{
	msgAlign: 'bottom'
});
//检查用户名
function mobileCheck(){
	var receiveAddressEle = $(".forget_wrap #userLoginName");
	var receiveAddress =  receiveAddressEle.val();
	if(!(phone_reg.test(receiveAddress)||email_reg.test(receiveAddress))){
		passUserloginForm.setErrormsg(receiveAddressEle,'手机号码或者邮箱地址格式不正确');
		return false;
	}
	return true;
}
//下一步
$('.forget_next').on('click',function(){
	
	if(!passUserloginForm.validate()){
		return;	
	}
	var receiveAddressEle = $(".forget_wrap #userLoginName");
	//校验验证码
	$.ajax({
		type:"post",
		url: '<%=basePath%>/organ/checkRegCode.shtml',		
		data:{"userLoginName":receiveAddressEle.val(),"regCode":$(".forget_wrap #code").val()},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				$(".passwordf").hide();
			    $(".passwordf_next").show(); 
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
			new top.Tip({msg : '系统错误，请重试!', type: 3, timer:2000});
		}
	});
    
});
//确定
$(".J_close").on("click",function(){
	savePass();
});
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
//验证码发送倒计时
$('.sendmes').on('click',function(){
    var raObj = $(".forget_wrap #userLoginName");
    var receiveAddress = raObj.val();
    if(!(phone_reg.test(receiveAddress)||email_reg.test(receiveAddress))){
    	new top.Tip({msg : '请输入正确的手机号码和邮箱', type: 1, timer:2000});
    	raObj.focus();
    	return
    }
    _this = $(this);
    top.F.loading.show();
	$.ajax({
		data:{"receiveAddress":receiveAddress},
		url : '<%=basePath%>/organ/sendRegCode.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				if(!_this.hasClass("huise")){
			        new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});    
			        _this.addClass('huise');
			        timeOut(_this);
			    } 
			}else{
				new top.Tip({msg : result.msg, type: 1, timer:2500});
			}
		},
		complete:function(){
	    	  top.F.loading.hide();
	    },
		error : function(){
			new top.Tip({msg : '网络错误，请重试', type: 1, timer:2000});
		}
	});
});
$(".forget_wrap .btn_exit").on('click', function() {
	forgetDialog.remove();
});
//确认密码框失去焦点
function confirmPass(){
	var pass1 = $(".forget_wrap #newLoginPassword").val();
	var pass2 = $(".forget_wrap #passwordConfirm").val();
	if($.trim(pass2).length<1){
		passNextForm.setErrormsg($(".forget_wrap #passwordConfirm"),"确认新密码不能为空");
		return false;
	}
	if(pass1!=pass2){
		passNextForm.setErrormsg($(".forget_wrap #passwordConfirm"),"两次输入密码不一致");
		return false;
	}
	return true;
}

function savePass(){
	var saveFlag = false;
	if(!passNextForm.validate()){
		return saveFlag;
	}
	var code = $(".forget_wrap #code").val();
	var newLoginPassword = $(".forget_wrap #newLoginPassword").val();
	$.ajax({
		type:"post",
		async:false,
		url: '<%=basePath%>/setting/set-login-password-login-name.shtml',
		data:{"code":code,"newLoginPassword":newLoginPassword},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				saveFlag = true;
				new top.Tip({msg : '设置成功！', type: 1 , time:1000});
				forgetDialog.remove();
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
			new top.Tip({msg : '系统错误，请重试!', type: 3 , time:1500});
		}
	});
	
	return saveFlag;
	
}
//显示密码
$('.eye').on('click',function(){  
    var _this= $(this);
    var a= $(this).parent().find('input');    
    if (_this.is('.on')) {
        a[0].type = "password"; 
        $(this).removeClass('on');
    }else{
        a[0].type = "text"; 
        $(this).addClass('on');        
    }  
});
</script>

