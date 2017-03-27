<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 记得登录密码  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>修改登录密码-泰坦钱包</title>
     <jsp:include page="/comm/tws-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<jsp:include page="./head.jsp">
	<jsp:param value="修改登录密码" name="title"/>
</jsp:include>
<div class="register r_three forget r_alter">
	<div class="r_box">		
		<div class="r_c">
			<div class="r_tit"><b>原密码：</b></div>
			<div class="r_text">
				<ul>					
					<li class="r_y2"><input type="password" class="text" name="password" id="oldLoginPassword" readonly="readonly" placeholder="原密码"  datatype="/\w{6,}/" require="true"  errormsg="长度太短"><i class="ico rt_eye"></i></li>
				</ul>
			</div>	
			<div class="r_tit"><b>新密码：</b></div>
			<div class="r_text">
				<ul>					
					<li class="r_y2"><input type="password" class="text" id="newLoginPassword" readonly="readonly" placeholder="新密码" datatype="/\w{6,}/" require="true" errormsg="长度太短"><i class="ico rt_eye"></i><em class="ico hint_1" id="newLoginPassword_hint"></em></li>					
				</ul>
			</div>
			<div class="r_tit"><b>确认密码：</b></div>
			<div class="r_text">
				<ul>
					<li class="r_y3"><input type="password" class="text" name="passwordConfirm" id="passwordConfirm" readonly="readonly" placeholder="确认密码" datatype="*" require="true" errormsg="两次密码输入不一致" customFun="confirmPass"><i class="ico rt_eye "></i></li>				
					<li class="lb_btn"><a href="javascript:;" class="" onclick="savePassword()">确定</a></li>
				</ul>
			</div>
		</div>		
	</div>
</div>

<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script type="text/javascript">
var passform ;
$(function(){
	F.UI.scan();
	passform = new validform('.r_box');
	//修复在360下记住了密码bug
	window.setTimeout(function(){
		$("input[readonly='readonly']").attr("readonly", false);
	}, 500);
	//显示密码
	$('.rt_eye').on('click',function(){
		var _this=$(this);
		if(_this.is('.rt_open')){
			_this.parent().find('input').attr('type','password');
			_this.removeClass('rt_open');
		}else{		
			_this.parent().find('input').attr('type','text');
			_this.addClass('rt_open');
		}
	})
});

//确认密码框失去焦点
function confirmPass(){
	var pass1 = $("#newLoginPassword").val();
	var pass2 = $("#passwordConfirm").val();
	if($.trim(pass2).length<1){
		passform.setErrormsg($("#passwordConfirm"),"不能为空");
		return false;
	}
	if(pass1!=pass2){
		passform.setErrormsg($("#passwordConfirm"),"两次输入密码不一致");
		return false;
	}
	return true;
}
//保存密码
function savePassword(){
	if(!passform.validate()){
		return ;
	}
	$.ajax({
		type:"post",
		url: '<%=basePath%>/setting/set-login-password-password.shtml',
		data:{"oldLoginPassword":$("#oldLoginPassword").val(),"newLoginPassword":$("#newLoginPassword").val()},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				location.href="<%=basePath%>/ex/pwd-set-succ.shtml?pageType=1";
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
	    error:function(xhr,status){
			new top.Tip({msg : '系统错误，请重试!', type: 2 , time:1500});
		}
	});
}
$('#newLoginPassword').bind("keyup",function(){
	var m = checkComplicacy($(this).val());
	if(m>=3){
		m = 3;
	}
	$("#"+$(this).attr("id")+'_hint').attr({'class':"ico hint_"+m});
});
</script>