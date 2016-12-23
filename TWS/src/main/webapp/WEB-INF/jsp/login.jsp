<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>泰坦钱包</title>        
    <link rel="stylesheet" href="<%=cssWalletPath%>/css/fangcang.min.css?v=20161222">
    <link rel="stylesheet" href="<%=cssWalletPath%>/css/style.css?v=20161222">
</head>
<body style="min-width: 1300px;" class="bg" >
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
				<li class="l_zh "><em class="ico "></em><input type="text" class="text" placeholder="手机号码/邮箱" datatype="*" errormsg="用户名错误"></li>
				<li class="l_mm "><em class="ico "></em><input type="password" class="text" placeholder="密码" datatype="*" errormsg="密码错误"></li>
				<li class="lb_Rememb">				
					<span><i class="ico"></i> <em>记住用户名</em></span>
				</li>
				<li class="lb_btn"><a href="">立即登录</a></li>
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
				<li class="l_zh "><em class="ico "></em><input type="text" class="text" placeholder="手机号码/邮箱" datatype="*" errormsg="用户名错误"></li>
				<li class="l_mm "><em class="ico "></em><input type="text" class="text" placeholder="验证码" datatype="*" errormsg="验证码错误"><div class="lb_verify">获取验证码</div></li>
				<li class="lb_Rememb">
					<span><i class="ico"></i> <em>记住用户名</em></span>
				</li>
				<li class="lb_btn"><a href="">立即登录</a></li>
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

<script src="<%=cssWalletPath%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=cssWalletPath%>/js/fangcang.min.js"></script>
<script type="text/javascript">
//记住用户名
$('.lb_Rememb span').on('click',function(){
	var _this = $(this),
		_thisI = _this.find('i');
	if(_thisI.is('.Ibadd')){
		_thisI.removeClass('Ibadd')
	}else{
		_thisI.addClass('Ibadd')
	}
})
//动态验证码登录
$('.dynamic .lb_cut').on('click',function(){
	$(this).parent().addClass('dn').siblings().removeClass('dn');
})
//用户密码登录
$('.user .lb_cut').on('click',function(){
	$(this).parent().addClass('dn').siblings().removeClass('dn');
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
//验证
new validform('.l_box');

</script>
</body>
</html>
