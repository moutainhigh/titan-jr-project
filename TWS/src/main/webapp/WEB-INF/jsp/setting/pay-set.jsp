<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>付款密码-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
  <body style="min-width: 1300px;" class="bg" >
<jsp:include page="./head.jsp">
	<jsp:param value="设置付款密码" name="title"/>
</jsp:include>

<div class="register r_three forget">
	<div class="r_box">		
		<div class="r_c overview_pop">
			<div class="r_tit"><b>付款密码：</b></div>
			
				<!-- 密码 -->
				<div class="password_set " id="password_set">
				<div class="press_pass"></div>
				<ul class="passwordset_u1" >
					<li>
					
						<div class="sixDigitPassword" id="passwordbox"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>

					</li>
				</ul>
				</div>
		
			<div class="r_tit"><b>确认密码：</b></div>	
			
				<div class="password_set " id="password_set">
				<div class="press_pass"></div>
				<ul class="passwordset_u1" >
					<li class="" id="six_error_red">
						
						<div class="sixDigitPassword " id="passwordbox1"> 
							<i><b></b></i>  
							<i><b></b></i> 
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<i><b></b></i>
							<span></span>
						</div>
						<!-- 错误提示 -->
						<div class="rp_hint">两次输入的密码不一致,请重新输入</div>
					</li>
				</ul>
				
			</div>	

			<div class="r_text p_t10">
				<ul>					
					<li class="lb_btn"><a href="javascript:;" class="" onclick="savePayPwd()">确定</a></li>
				</ul>
			</div>
		</div>		
	</div>
</div>
<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script type="text/javascript">
var isadmin = '${isJrAdmin}';
//渲染组件
F.UI.scan();
 
//保存密码  
function savePayPwd() {
	var validate_pay = validate_payPassword();
	if(validate_pay==true){
		$.ajax({
			 type: "post",
	         url: "<%=basePath%>/account/setPayPassword.action",
	         data: {
	        	 payPassword:PasswordStr1.returnStr()
	         },
	         dataType: "json",
	         success: function(data){
	        	 if(data.result=="success"){//密码设置成功
	        		 location.href="<%=basePath%>/setting/pwd-set-succ.shtml?pageType=2";
	        	 }
	         }
			
		});
	}
}  

function validate_payPassword(){
	var payPassword1 = PasswordStr1.returnStr();
	var payPassword2 = PasswordStr2.returnStr();
	if(payPassword1.length!=6||payPassword2.length!=6||(!payPwd_reg.test(payPassword1))||(!payPwd_reg.test(payPassword2))){
		new top.Tip({msg : '密码必须为6位数字，请重新输入', type: 1 , timer:2000}); 
		add_PayPassword();
		return false;
	}
	$("#six_error_red").removeClass("sp_add");
	if(payPassword1!=payPassword2){
		$("#six_error_red").addClass("sp_add");
		add_PayPassword();
		return false;
	}
	return true;
	
}
var dPop;
$('.J_password').on('click',function(){
    top.F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/modify-pwd.shtml',           
        success : function(html){
            top.F.loading.hide();
            dPop =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
            }).showModal();
            
        }
    });
});  

function add_PayPassword(){
	 setTimeout(function(){
		 window.location.reload();
	 },2000);
}

//密码输入框
var PasswordStr1=new sixDigitPassword("passwordbox");
var PasswordStr2=new sixDigitPassword("passwordbox1");

var timeIndex = 0;

function clickPassword()
{
	$('#passwordbox').click();
  		timeIndex = setInterval(function(){
  			try
  			{
  				if($('#passwordbox i:last b:first-child').attr('style').indexOf('inherit') != -1)
  				{
  					$('#passwordbox1').click();
  					clearInterval(timeIndex);
  				}
  			}catch(e)
  			{}
	},100);
}
clickPassword();


</script>
</body>
</html>
