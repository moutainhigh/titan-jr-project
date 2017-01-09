<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>注册申请-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body style="min-width: 1300px;" class="bg" >
<div class="header">
	<div class="w_1200">
		<div class="logo">
			<div class="l_img"><img src="<%=cssWalletPath %>/images/logo.png"></div>
			<div class="l_text">
				<i class="ico"></i>欢迎注册
			</div>
		</div>
		<div class="head_r">
			<ul>
				<!-- <li class="lion">首页</li>
				<li>解决方案</li> -->
				<li class="w_240 li_1">
					已注册，现在就
					<a class="li_btn" href="<%=basePath%>/ex/login.shtml">登录</a>
				</li>
			</ul>
		</div>
	</div>
</div>

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
			<form action="<c:url value='/organ/showEnterpriseInfo.shtml'/>" id="reg_form_qy">
				<ul>
					<li class="r_y1"><div class="rt_title">用户名</div><input type="text" class="text ui-loginusername"  name="userLoginName" placeholder="邮箱" datatype="e"  errormsg="格式不正确" afterPassed="checkExist"></li>
					<li class="r_y2"><div class="rt_title">登录密码</div><input type="password" class="text pass1" name="password" placeholder="设置登录密码" datatype="/\w{6,}/" errormsg="长度太短"><i class="ico rt_eye"></i><em class="ico hint_1"></em></li>
					<li class="r_y3"><div class="rt_title">确认密码</div><input type="password" class="text pass2" name="passwordConfirm" placeholder="确认登录密码" datatype="/\w*/" errormsg="长度太短" afterPassed="confirmPass"><i class="ico rt_eye "></i></li>
					<li class="r_yzm"><div class="rt_title">验证码</div>
					<input type="text" class="text ui-reg" name="regCode" placeholder="验证码" datatype="/\w{4,}/" errormsg="长度太短"><div class="r_verify">获取验证码</div>
					</li>
					<li class="lb_Rememb">
						<span class="fl qiye"><i class="ico"></i> 我已阅读并同意</span> <div class="colour m_l14 dib services_terms">《泰坦云金融服务协议》</div>
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
				<form action="<c:url value='/organ/showPersernalInfo.shtml'/>" id="reg_form_phone">
				<ul>
					<li class="r_y1"><div class="rt_title">用户名</div><input type="text" class="text ui-loginusername" name="userLoginName" placeholder="邮箱/手机号码" datatype="/\w*/" errormsg="格式不正确" afterPassed="checkExist"></li>
					<li class="r_y2"><div class="rt_title">登录密码</div><input type="password" class="text pass1" name="password" placeholder="设置登录密码" datatype="/\w{6,}/" errormsg="度太短"><i class="ico rt_eye"></i><em class="ico hint_1"></em></li>
					<li class="r_y3"><div class="rt_title">确认密码</div><input type="password" class="text pass2" name="passwordConfirm" placeholder="确认登录密码" datatype="/\w{6,}/" errormsg="度太短" afterPassed="confirmPass"><i class="ico rt_eye"></i></li>
					<li class="r_yzm"><div class="rt_title">验证码</div>
					<input type="text" class="text ui-reg" name="regCode" placeholder="验证码" datatype="/\w{4,}/" errormsg="长度太短"><div class="r_verify">获取验证码</div>
					</li>
					<li class="lb_Rememb">
						<span class="fl geren"><i class="ico"></i> 我已阅读并同意</span> <div class="colour m_l14 dib services_terms">《泰坦云金融服务协议》</div>
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

<!-- 版权 -->
<div class="footer">
	Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
</div>
<div class="szcert">
	<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script>
</div>

<!-- 查看示例 -->
<div class="dn" id="example">
<div class="example" style="max-width:1000px;">
	<img src="<%=cssWalletPath %>/images/tu01.jpg" alt=""  style="max-width: 1000px; max-height: 600px;display: block;margin: 0 auto;">
</div>	
</div>
<!-- 泰坦云金融服务协议 -->
<div id="services_terms" class="dn">
		<div class="ser_content">			
			<p class="p1">泰坦云金融 服务协议（以下简称本协议）由 深圳市天下房仓科技 有限公司（以下简称本公司）和您签订。</p>
			<p class="p1">一、声明与承诺</p>
			<p>1、在接受本协议或您以本公司允许的其他方式实际使用 泰坦云金融 服务之前，请您仔细阅读本协议的全部内容（特别是以粗体标注的内容）。如果您不同意本协议的任意内容，
     或者无法准确理解本公司对条款的解释，请不要进行后续操作，包括但不限于不接受本协议，不使用本服务。如果您对本协议的条款有疑问，请通过本公司客服渠道进行询问
    （客服电话为 0755-23736753 ），本公司将向您解释条款内容。</p>
			<p>2、您同意，如本公司需要对本协议进行变更或修改的，须通过 jr.fangcang .com网站公告的方式提前予以公布，公告期限届满后即时生效；若您在本协议内容公告变更生效后
     继续使用 泰坦云金融 服务的，表示您已充分阅读、理解并接受变更后的协议内容，也将遵循变更后的协议内容使用 泰坦云金融 服务；若您不同意变更后的协议内容，您应
     在变更生效前停止使用 泰坦云金融 服务。</p>
			<p>3、如您为无民事行为能力人或为限制民事行为能力人，例如您未满18周岁，则您应在监护人监护、指导下阅读本协议和使用本服务。若您非自然人，则您确认，在您取得 泰坦
     云金融 账户时，或您以其他本公司允许的方式实际使用 泰坦云金融 服务时，您为在中国大陆地区合法设立并开展经营活动或其他业务的法人或其他组织，且您订立并履行本
     协议不受您所属、所居住或开展经营活动或其他业务的国家或地区法律法规的排斥。不具备前述条件的，您应立即终止注册或停止使用 泰坦云金融 服务。</p>
			<p>4、您在使用 泰坦云金融 服务时，应自行判断交易对方是否具有完全民事行为能力并自行决定是否使用 泰坦云金融 服务与对方进行交易，且您应自行承担与此相关的所有风险。
			<p>5、您确认，您在 泰坦云系统 上发生的所有交易，您已经不可撤销地授权 泰坦云 按照其制定的规则进行处理；同时， 您不可撤销地授权本公司按照 泰坦云 的指令将争议款项
     的全部或部分支付给交易一方或双方，同时本公司有权从 泰坦云 获取您的相关信息（包括但不限于交易商品 信息 、 预订 信息、行为信息、账户相关信息等）。本公司按
     照泰坦云 的指令进行资金的止付、扣划完全来自于您的授权，本公司对因此给您造成的任何损失均不承担责任。但您确认，您使用 泰坦云金融 服务时，您仍应完全遵守本
     协议及本公司制定的各项规则及页面提示等。</p>
			<p class="p1">二、定义及解释</p>
			<p>1、泰坦云金融 账户（或“该账户”）：指您按照本公司允许的方式取得的供您使用 泰坦云金融 服务的账户。</p>
			<p>2、本网站：除本协议另有规定外，指 jr.fangcang.com 及/或客户端。	</p>			
</div>		
</div>

<script src="<%=cssSaasPath %>/js/jquery.mCustomScrollbar.concat.min.js"></script>
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

//验证
new validform('.r_box');

// 我已阅读并同意 个人
$('.lb_Rememb span.geren').on('click',function(){
	var _this = $(this),
		_thisI = _this.find('i');
	if(_thisI.is('.Ibadd')){
		_thisI.removeClass('Ibadd');
		//_this.parents('.r_text').find('.lb_btn a').attr('href','javascript:;');
		_this.parents('.r_text').find('.lb_btn').addClass('disable');
		$('.gere-next').unbind("click",next);
	}else{
		_thisI.addClass('Ibadd');
		//_this.parents('.r_text').find('.lb_btn a').attr('href','个人注册.html');
		_this.parents('.r_text').find('.lb_btn').removeClass('disable');
		$('.gere-next').bind("click",next);
	}
});
// 我已阅读并同意 企业
$('.lb_Rememb span.qiye').on('click',function(){
	var _this = $(this),
		_thisI = _this.find('i');
	if(_thisI.is('.Ibadd')){
		_thisI.removeClass('Ibadd');
		//_this.parents('.r_text').find('.lb_btn a').attr('href','javascript:;');
		_this.parents('.r_text').find('.lb_btn').addClass('disable');
		$('.qiye-next').unbind("click",next);
	}else{
		_thisI.addClass('Ibadd');
		//_this.parents('.r_text').find('.lb_btn a').attr('href','企业注册.html');
		_this.parents('.r_text').find('.lb_btn').removeClass('disable');
		$('.qiye-next').bind("click",next);
	}
});


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
$('.r_verify').on('click',function(){			
    if(!$(this).hasClass("r_huise")){   
    	$(this).text("重新发送(60)");
        $(this).addClass('r_huise');
        timeOut($(this));
    }    
})

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
        }).showModal()
        return false;
});   
//泰坦云金融服务协议
$('.services_terms').on('click',function(){  
    var d = dialog({
        title: ' 泰坦云金融服务协议 ',
        fixed: true,
        padding: '0 0 0px 0',
        content: $('#services_terms'),
        skin : 'wallet_pop1'
    }).showModal()
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
	if(!$('.f_ui-checkbox-c3 input[type=checkbox]').is(':checked'))
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
	
	var formId = getCurrentFormId();
	var loginEle = $("#"+formId+" .ui-loginusername");
	var regCodeEle = $("#"+formId+" .ui-reg");
	var userLoginName = loginEle.val();
	var regCode = regCodeEle.val();
	
	$.ajax({
		type:"post",
		url: '<%=basePath%>/organ/checkRegCode.shtml',		
		data:{"userLoginName":userLoginName,"regCode":regCode},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				var formId = getCurrentFormId();
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

//泰坦云金融服务协议
$('.J_agreement').on('click',function(){
        $.ajax({
            dataType : 'html',
            context: document.body,
            url : '<%=basePath%>/organ/showAgreement.shtml',
            success : function(html){
                var d =  window.top.dialog({
                    title: ' ',
                    padding: '0 0 20px 0',
                    content: html,
                    skin : 'saas_pop',                  
                    button : [ 
                        {
                            value: '我已阅读并同意此协议',
                            skin : 'btn p_lr30',
                            callback: function () {                               
                            },
                            autofocus: true
                        }
                    ],
                    close : function(){                        
                    }
                }).showModal();
            }
        });
    });
//发送验证码
$('.verify').on('click',function(){
	var formId = getCurrentFormId();
	var loginEle = $("#"+formId+" .ui-loginusername");
	var receiveAddress = loginEle.val();
    //
    if($.trim(receiveAddress).length==0){
    	new top.Tip({msg : '用户名不能为空', type: 1, timer:2000});
    	loginEle.focus();
    	return
    }
	if(formId=='reg_form_1_qy'||formId=='reg_form_3_email'){
		if(!email_reg.test(receiveAddress)){
			getValidate().setErrormsg(loginEle,'邮箱格式不正确');
			loginEle.focus();
			return ;
		}
	}else if(formId=='reg_form_2_phone'){
		if(!phone_reg.test(receiveAddress)){
			getValidate().setErrormsg(loginEle,'手机号码格式不正确');
			loginEle.focus();
			return ;
		}
    }
	_this = $(this);
	$.ajax({
		async : false,
		data:{"receiveAddress":receiveAddress,"msgType":1},
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
				new top.Tip({msg : result.msg, type: 3, timer:2500});
			}
		},
		error : function(){
			new top.Tip({msg : '请求错误，请重试', type: 3, timer:2000});
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

$('.pass1').bind("cut copy paste", function(e) {  
    e.preventDefault();  
});  
$('.pass2').bind("cut copy paste", function(e) {  
    e.preventDefault();  
});  
 

</script>
  </body>
</html>
