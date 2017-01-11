<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>注册申请-泰坦钱包</title>
    <link rel="stylesheet" href="<%=cssWalletPath%>/css/fangcang.min.css?v=20161222">
	<link rel="stylesheet" href="<%=cssWalletPath%>/css/AD.css"> 
	<link rel="stylesheet" href="<%=cssWalletPath%>/css/style.css">
	<link rel="stylesheet" href="<%=cssWalletPath%>/css/jquery-ui-1.9.2.custom.css" >
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body style="min-width: 1300px;" class="bg" >
<jsp:include page="org-head.jsp"></jsp:include>

<div class="register">
	<div class="r_box">
		<div class="r_title">
			<ul>
				<li class="lion" data-tab="enterprise">企业用户</li>		
				<li data-tab="personage">个人用户</li>
			</ul>
			<div class="r_xi ico"></div>
		</div>
		<div class="r_c ">
			<div class="r_text pour_c" >
			<form action="<c:url value='/ex/showEnterpriseInfo.shtml'/>" id="reg_form_qy">
				<ul>
					<li class="r_y1"><div class="rt_title">用户名</div><input type="text" class="text ui-loginusername"  name="userLoginName" placeholder="邮箱" datatype="e"  errormsg="格式不正确" afterPassed="checkExist"></li>
					<li class="r_y2"><div class="rt_title">登录密码</div><input type="password" class="text pass1" name="password" placeholder="设置登录密码" datatype="/\w{6,}/" errormsg="长度太短"><i class="ico rt_eye"></i><em class="ico hint_1"></em></li>
					<li class="r_y3"><div class="rt_title">确认密码</div><input type="password" class="text pass2" name="passwordConfirm" placeholder="确认登录密码" datatype="/\w*/" errormsg="长度太短" afterPassed="confirmPass"><i class="ico rt_eye "></i></li>
					<li class="r_yzm"><div class="rt_title">验证码</div>
					<input type="text" class="text ui-reg" name="regCode" placeholder="验证码" datatype="/\w{4,}/" errormsg="长度太短"><div class="r_verify">获取验证码</div>
					</li>
					<li class="lb_Rememb">
						<span class="fl qiye"><i class="i_agree ico"></i> 我已阅读并同意</span> <div class="colour m_l14 dib services_terms">《泰坦云金融服务协议》</div>
					</li>
					<li class="lb_btn disable"><a href="javascript:;" class="qiye-next">下一步</a></li>
				</ul>
				<input type="submit" class="regbtn" style="display:none;">
			</form>
			</div>
			<div class="r_hint">
				<div class="r_hint_l">注册前需要准备：</div>
				<div class="r_hint_r">				
					<p>营业执照照片（注册过程中需上传）</p>
					<p>企业联系人姓名及手机号码</p>				
				</div>
			</div>
		</div>
		<div class="r_c dn">
			<div class="r_text" id="reg_person">
				<form action="<c:url value='/ex/showPersernalInfo.shtml'/>" id="reg_form_phone">
				<ul>
					<li class="r_y1"><div class="rt_title">用户名</div><input type="text" class="text ui-loginusername" name="userLoginName" placeholder="邮箱/手机号码" datatype="/\w*/" errormsg="格式不正确" afterPassed="checkGeExist"></li>
					<li class="r_y2"><div class="rt_title">登录密码</div><input type="password" class="text pass1" name="password" placeholder="设置登录密码" datatype="/\w{6,}/" errormsg="长度太短"><i class="ico rt_eye"></i><em class="ico hint_1"></em></li>
					<li class="r_y3"><div class="rt_title">确认密码</div><input type="password" class="text pass2" name="passwordConfirm" placeholder="确认登录密码" datatype="/\w{6,}/" errormsg="长度太短" afterPassed="confirmPass"><i class="ico rt_eye"></i></li>
					<li class="r_yzm"><div class="rt_title">验证码</div>
					<input type="text" class="text ui-reg" name="regCode" placeholder="验证码" datatype="/\w{4,}/" request="true" errormsg="长度太短"><div class="r_verify">获取验证码</div>
					</li>
					<li class="lb_Rememb">
						<span class="fl geren"><i class="i_agree ico"></i> 我已阅读并同意</span> <div class="colour m_l14 dib services_terms">《泰坦云金融服务协议》</div>
					</li>
					<li class="lb_btn disable"><a href="javascript:;" class="geren-next">下一步</a></li>
				</ul>
				<input type="submit" class="regbtn" style="display:none;">
				</form>
			</div>
			<div class="r_hint">
				<div class="r_hint_l">注册前需要准备：</div>
				<div class="r_hint_r">				
					<p>本人持身份证正面照片（注册过程中需上传）</p>
					<p>照片要求放大后能看清身份证信息 </p>
					<a class="J_example" href="javascript:;">查看示例</a>					
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/comm/foot.jsp"></jsp:include>

<!-- 查看示例 -->
<div class="dn" id="example">
<div class="example" style="max-width:1000px;">
	<img src="<%=cssWalletPath %>/images/tu01.jpg" alt=""  style="max-width: 1000px; max-height: 600px;display: block;margin: 0 auto;">
</div>	
</div>
<script src="<%=cssWalletPath %>/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript">
//验证
var valid_qy_form = new validform('#reg_form_qy');
var valid_phone_form = new validform('#reg_form_phone');
 

var current_form = "reg_form_qy";
//企业还是个人tab
var TAB_TYPE={"enterprise":1,"personage":2};
//选择是手机号码还是邮箱注册
//var LOGIN_TYPE={"email":1,"phone":2};
var current_tab = TAB_TYPE["enterprise"];
//var loginType = LOGIN_TYPE["email"];

$(".ser_content").mCustomScrollbar({  		                  
   	scrollButtons:{  
            enable:true //是否使用上下滚动按钮  
       },      
    autoHideScrollbar: false, //是否自动隐藏滚动条  
    scrollInertia :0,//滚动延迟  
    horizontalScroll : false,//水平滚动条  
    callbacks:{  
        /*onScroll: function(){alert(1)} *///滚动完成后触发事件  
    }  
});  

// 我已阅读并同意 个人
$('.lb_Rememb span.geren').on('click',function(){
	var _this = $(this),
		_thisI = _this.find('i');
	if(_thisI.is('.Ibadd')){
		_thisI.removeClass('Ibadd');
		_this.parents('.r_text').find('.lb_btn').addClass('disable');
		$('.geren-next').unbind("click",next);
	}else{
		_thisI.addClass('Ibadd');
		_this.parents('.r_text').find('.lb_btn').removeClass('disable');
		$('.geren-next').bind("click",next);
	}
});
// 我已阅读并同意 企业
$('.lb_Rememb span.qiye').on('click',function(){
	var _this = $(this),
		_thisI = _this.find('i');
	if(_thisI.is('.Ibadd')){
		_thisI.removeClass('Ibadd');
		_this.parents('.r_text').find('.lb_btn').addClass('disable');
		$('.qiye-next').unbind("click",next);
	}else{
		_thisI.addClass('Ibadd');
		_this.parents('.r_text').find('.lb_btn').removeClass('disable');
		$('.qiye-next').bind("click",next);
	}
});



//个人企业切换
$('.r_title li').on('click',function(){
	var _this=$(this),
		_index=_this.index();
	_this.addClass('lion').siblings().removeClass('lion')	
	$('.r_c').eq(_index).removeClass('dn').siblings('.r_c').addClass('dn');
	current_tab = TAB_TYPE[_this.attr("data-tab")];
})

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

//查看示例
$('.J_example').on('click',function(){  
        var d = dialog({
            title: ' ',
            fixed: true,
            padding: '0 0 0px 0',
            content: $('#example'),
            skin : 'wallet_pop'      
        }).showModal();
        return false;
});   
//泰坦云金融服务协议
$('.services_terms').on('click',function(){  
	$.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/ex/showAgreement.shtml',
        success : function(html){
        	var d = dialog({
                title: ' 泰坦云金融服务协议 ',
                fixed: true,
                padding: '0 0 0px 0',
                content: html,
                skin : 'wallet_pop1'
            }).showModal();
        }
    });
    return false;
});
 
//tws

//判断当前显示的formid
function getCurrentFormId(){
	if(current_tab==TAB_TYPE["enterprise"]){
		current_form = "reg_form_qy";
		return current_form;
	}else{
		current_form = "reg_form_phone";
		return current_form;
	}
}
//表单id后缀
function getformSuffix(){
	if(current_tab==TAB_TYPE["enterprise"]){
		return "qy";
	}else{
		return "phone";
	}
}
//表单组件
function getValidate(){
	var suffix = getformSuffix();
	if(suffix=="qy"){
		return valid_qy_form;
	}
	if(suffix=="phone"){
		return valid_phone_form;
	}
}
//确认密码框失去焦点
function confirmPass(){
	var formId = getCurrentFormId();
	var pass1 = $("#"+formId+" .pass1").val();
	var pass2 = $("#"+formId+" .pass2").val();
	if($.trim(pass2).length==0){
		getValidate().setErrormsg($("#"+formId+" .pass2"),"长度太短");
		return false;
	}
	if(pass1!=pass2){
		getValidate().setErrormsg($("#"+formId+" .pass2"),"密码不一致");
		return false;
	}
	return true;
}

//下一步
function next(){
	if(!getValidate().validate()){
		return;
	}
	//我已阅读并同意
	var formId = getCurrentFormId();
	if(!$("#"+formId+" .i_agree").hasClass("Ibadd"))
	{
		   new top.createConfirm({
		        padding:'30px 20px 65px',
		        width:'330px',
		        title:'提示',
		        content : '<div class="f_14 t_a_c">请阅读并勾选《泰坦云金融服务协议》！</div>',      
		        button:false
		      }); 
		   return;
	}
	
	var loginEle = $("#"+formId+" .ui-loginusername");
	var regCodeEle = $("#"+formId+" .ui-reg");
	var userLoginName = loginEle.val();
	var regCode = regCodeEle.val();
	
	$.ajax({
		type:"post",
		url: '<%=basePath%>/ex/checkRegCode.shtml',		
		data:{"userLoginName":userLoginName,"regCode":regCode},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				 $("#"+formId+" .regbtn").click();
			}else{
				new top.createConfirm({
			        padding:'30px 20px 65px',
			        width:'330px',
			        title:'提示',
			        content : '<div class="f_14 t_a_c">'+result.msg+'</div>',      
			        button:false
			     }); 
			}
			
		},
		error:function(){
			new top.Tip({msg : '请求错误，请重试', type: 3, timer:2000});
		}
	});
}

//验证码
var sendingFlag = false;
function timeOut(_this){
    var i=59;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送("+i+")"); 
             i--;
         }else{
            _this.removeClass("r_huise").html("重新获取验证码");
            clearInterval(interval);
            sendingFlag = false;
         };
    }, 1000);
};
//发送验证码
$('.r_verify').on('click',function(){
	if(sendingFlag){
		return;
	}
	var formId = getCurrentFormId();
	var loginEle = $("#"+formId+" .ui-loginusername");
	var receiveAddress = loginEle.val();
    
    if($.trim(receiveAddress).length==0){
    	new top.Tip({msg : '用户名不能为空', type: 1, timer:2000});
    	loginEle.focus();
    	return
    }
	if(formId=='reg_form_qy'){
		if(!email_reg.test(receiveAddress)){
			getValidate().setErrormsg(loginEle,'邮箱格式不正确');
			loginEle.focus();
			return ;
		}
	}else if(formId=='reg_form_phone'){
		if(!phone_reg.test(receiveAddress)){
			getValidate().setErrormsg(loginEle,'手机号码格式不正确');
			loginEle.focus();
			return ;
		}
    }
	_this = $(this);
	if(!$(this).hasClass("r_huise")){  
		sendingFlag = true;
    	$(this).text("重新发送(60)");
        $(this).addClass('r_huise');
        timeOut($(this));
    } 
	$.ajax({
		data:{"receiveAddress":receiveAddress,"msgType":1},
		url : '<%=basePath%>/ex/sendCode.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
		        new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});    
			}else{
				new Tip({msg : result.msg, type: 2, timer:2500});
			}
		},
		error : function(){
			new Tip({msg : '请求错误，请重试', type: 2, timer:2000});
		}
	});
});
 

//检查是否已经注册
function checkExist(value, inputDom){
	var flag = false;
	$.ajax({
		async:false,
		type:'post',
		data:{"userLoginName":value,"isOperator":0},
		url : '<%=basePath%>/ex/organ/checkUserLoginName.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				flag = true;
			}else{
				flag = false;
				//修改错误提示信息
				getValidate().setErrormsg(inputDom,result.msg);
			}
		}
	});
	return  flag;
}

function checkGeExist(value, inputDom){
	var flag = false;
	if($.trim(value).length==0){
		getValidate().setErrormsg(inputDom,"必填项");
		return false;
	}
	if((!phone_reg.test(value))&&(!email_reg.test(value))){
		getValidate().setErrormsg(inputDom,"格式不正确");
		return false;
	}
	
	$.ajax({
		async:false,
		type:'post',
		data:{"userLoginName":value,"isOperator":0},
		url : '<%=basePath%>/ex/organ/checkUserLoginName.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				flag = true;
			}else{
				flag = false;
				//修改错误提示信息
				getValidate().setErrormsg(inputDom,result.msg);
			}
		}
	});
	return  flag;
	
}

$('.pass1').bind("cut copy paste", function(e) {  
    e.preventDefault();  
});  
$('.pass2').bind("cut copy paste", function(e) {  
    e.preventDefault();  
});  
 

</script>
  </body>
</html>
