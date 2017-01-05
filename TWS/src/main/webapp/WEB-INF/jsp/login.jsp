<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>泰坦钱包</title>        
    <link rel="stylesheet" href="<%=cssWalletPath%>/css/fangcang.min.css?v=20161222">
    <link rel="stylesheet" href="<%=cssWalletPath%>/css/style.css?v=20161222">
    <script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
</head>
<body style="min-width: 1300px;" class="bg" >
<input type="hidden" id="returnUrl" value=""/>
<div class="login">
	<div class="l_box">		
		<!-- 用户密码登录 -->
		<div class="user ">
			<div class="lb_cut ico"></div>
			<div class="lb_hint ico">动态码登录在这里</div>
			<div class="lb_logo"><i class="ico"></i></div>
			<div class="lb_title">密码登录</div>
			<div class="lb_list">
			<ul>
				<li class="l_zh "><em class="ico "></em><input type="text" class="text" id="username" placeholder="手机号码/邮箱" datatype="/\w*/" afterPassed="checkUserName" errormsg="必填项"></li>
				<li class="l_mm "><em class="ico "></em><input type="password" class="text" id="password" placeholder="密码" datatype="/\w*/" afterPassed="checkPass" errormsg="必填项"></li>
				<li class="lb_Rememb">				
					<span><i class="ico" id=""></i> <em>记住用户名</em></span>
				</li>
				<li class="lb_btn"><a href="javascript:;" onclick="Login.plogin()">立即登录</a></li>
				<li class="lb_register"><a href="注册.html">免费注册</a><a href="忘记密码.html">忘记密码？</a></li>
			</ul>
			</div>
		</div>
		<!-- 动态验证码登录 -->		
		<div class="dynamic dn">
			<div class="lb_cut ico"></div>
			<!-- <div class="lb_hint ico">密码登录在这里</div> -->
			<div class="lb_logo"><i class="ico"></i></div>
			<div class="lb_title">动态验证码登录</div>
			<div class="lb_list">
			<ul>
				<li class="l_zh "><em class="ico "></em><input type="text" class="text" id="susername" placeholder="手机号码/邮箱" datatype="*" errormsg="用户名错误"></li>
				<li class="l_mm "><em class="ico "></em><input type="text" class="text" id="code" placeholder="验证码" datatype="*" errormsg="验证码错误"><div class="lb_verify">获取验证码</div></li>
				<li class="lb_Rememb">
					<span><i class="ico"></i> <em>记住用户名</em></span>
				</li>
				<li class="lb_btn"><a href="javascript:;" onclick="Login.slogin()">立即登录</a></li>
				<li class="lb_register"><a href="注册.html">免费注册</a><a href="忘记密码.html">忘记密码？</a></li>
			</ul>
			</div>
		</div>
	</div>
</div>

<!-- 版权 -->
<div class="footer">
	Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
</div>
<div class="szcert">
	<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script>
</div>

<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
<script type="text/javascript">
var remFlag = false;
var cur_plane='user';//当前显示的登录方式，默认为密码
//记住用户名
$('.lb_Rememb span').on('click',function(){
	var _this = $(this),
		_thisI = _this.find('i');
	if(_thisI.is('.Ibadd')){
		_thisI.removeClass('Ibadd');
		remFlag = false;
	}else{
		_thisI.addClass('Ibadd');
		remFlag = true;
	}
})

//动态验证码登录
$('.dynamic .lb_cut').on('click',function(){
	$(this).parent().addClass('dn').siblings().removeClass('dn');
	 cur_plane='user';
})
//用户密码登录
$('.user .lb_cut').on('click',function(){
	$(this).parent().addClass('dn').siblings().removeClass('dn');
	 cur_plane='dynamic';
})
//验证码
function timeOut(_this){
    var i=59;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送("+i+")"); 
             i--;
         }else{
            _this.removeClass("lb_huise").html("重新获取验证码");
            clearInterval(interval);
         }; 
    }, 1000);
};
$('.lb_verify').on('click',function(){			
    if(!$(this).hasClass("lb_huise")){   
    	$(this).text("重新发送(60)");
        $(this).addClass('lb_huise');
        timeOut($(this));
    }    
})

//输入框/按钮效果控制
$(function(){
	$('.l_zh input').on('focus',function(){	
		$(this).parents('.l_zh').addClass('l_zh_hover').siblings().removeClass('l_mm_hover');
	});
	$('.l_mm input').on('focus', function(){		
		$(this).parents('.l_mm').addClass('l_mm_hover').siblings().removeClass('l_zh_hover');		
	});
	$('.l_zh input,.l_mm input').on('click',function(e){
		e.stopPropagation();
	})
	$(document).on('click',function(){ 		 
		$('.l_zh').removeClass('l_zh_hover');		
		$('.l_mm').removeClass('l_mm_hover');		
	});
})

var Login ={};
Login.valid_user_form = new validform('.l_box .user');
Login.valid_dynamic_form = new validform('.l_box .dynamic');
Login.getCurPlane = function(){
	if(cur_plane=='user'){
		return Login.valid_user_form;
	}else{
		return Login.valid_dynamic_form;
	}
}
//检查用户名
checkUserName = function(value, inputDom){
	if(value.length==0){
		Login.getCurPlane().setErrormsg(inputDom,'必填项');
		return false;
	}
	if((!phone_reg.test(value))&&(!email_reg.test(value))){
		Login.getCurPlane().setErrormsg(inputDom,'格式不正确');
		return false;
	}
	return true;
}
//检查密码
checkPass = function(value, inputDom){
	if(value.length==0){
		Login.getCurPlane().setErrormsg(inputDom,'必填项');
		return false;
	}
	if(value.length<6){
		Login.getCurPlane().setErrormsg(inputDom,'密码太简单');
		return false;
	}
	return true;
}
//密码登录
Login.plogin = function(){
	Login.rememberUsername();
	//检查参数
	if(!Login.getCurPlane().validate()){
		return;
	}
	$.ajax({
		url:'<%=basePath%>/passlogin.shtml',
		type:'post',
		data:{'loginUserName':$('#username').val(),'password':$("#password").val()},
		dataType:'json',
		//beforeSend:function(){
			
		//},
		success:function(json){
			if(json.code==1){
				Login.loginRedirect();
			}else{
				 new Tip({msg : json.msg, type: 3 , timer:3000});
			}
		},
		error:function(){
			alert("请求失败，请重试!");
		}
	});
}
//动态码登录
Login.slogin = function(){
	//检查参数
	if(!Login.getCurPlane().validate()){
		return;
	}
	$.ajax({
		url:'<%=basePath%>/smslogin.shtml',
		type:'post',
		data:{'loginUserName':$('#susername').val(),'password':$("#code").val()},
		dataType:'json',
		beforeSend:function(){
			F.loading.show();
		},
		success:function(json){
			if(json.code==1){
				Login.loginRedirect();
			}else{
				alert(json.msg);
			}
		},
		error:function(){
			alert("请求失败，请重试!");
		},
		complete:function(){
			F.loading.hide();
		}
	});
}
//登录后跳转
Login.loginRedirect = function(){
	var url = $("#returnUrl").val();
	if(typeof(url)=='undefinded'||url.length==0){
		url = "<%=basePath%>/main.shtml";
	}
	location.href=url;
}
//记住用户名
Login.rememberUsername = function(){
	if(remFlag){
		var n;
		if(cur_plane=='user'){
			n=$("#username").val();
		}else{
			n=$("#susername").val();
		}
		$.cookie("rem_username",n,{expires:10});
	}else{
		$.removeCookie("rem_username");
	}
}
Login.putUsername = function(){
	$("#username").val($.cookie("rem_username"));
	$("#susername").val($.cookie("rem_username"));
}
//Login.putUsername();
</script>
</body>
</html>
