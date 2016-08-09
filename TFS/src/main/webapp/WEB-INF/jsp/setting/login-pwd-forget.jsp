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
						<span class="reset_pass">泰坦金融用户名：</span><span id="userLoginName">${tfsUserLoginName }</span>
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
<script type="text/javascript" src="<%=cssSaasPath%>/js/password.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
<script type="text/javascript">
F.UI.scan();
var passUserloginForm = new validform('.pass_userlogin',{
	msgAlign: 'bottom'
});
var passNextForm = new validform('.passwordf_next',{
	msgAlign: 'bottom'
});
 
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
		data:{"userLoginName":receiveAddressEle.html(),"regCode":$(".forget_wrap #code").val()},
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
    var receiveAddress = raObj.html();
    _this = $(this);
    top.F.loading.show();
	$.ajax({
		data:{"receiveAddress":receiveAddress,"msgType":3},
		url : '<%=basePath%>/organ/sendCode.shtml',
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
	    error:function(xhr,status){
			if(xhr.status&&xhr.status==403){
    			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , time:2000});
    			return ;
    		}
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

