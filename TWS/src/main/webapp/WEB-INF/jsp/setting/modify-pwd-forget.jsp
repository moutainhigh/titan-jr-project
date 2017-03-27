<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>修改付款密码-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
  
<body style="min-width: 1300px;" class="bg">
<jsp:include page="./head.jsp">
	<jsp:param value="修改付款密码" name="title"/>
</jsp:include>
<div class="register r_three forget r_alter">
	<div class="r_box">
		<input type="hidden" name="userLoginName" id="userLoginName" value="${userLoginName }"/>
		<input type="hidden" name="code" id="code" value="${code }"/>
		<div class="r_c overview_pop">
			<div id="pwd_wrap">
				<div class="r_tit"><b>新密码：</b></div>			
				<!-- 密码 -->
				<div class="password_set " id="password_set">
				<div class="press_pass"></div>
				<ul class="passwordset_u1" >
					<li>
						<div class="sixDigitPassword" id="passwordbox3"> 
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
						<li id="six_pwd_err">
							<div class="sixDigitPassword" id="passwordbox4"> 
								<i><b></b></i>  
								<i><b></b></i> 
								<i><b></b></i>
								<i><b></b></i>
								<i><b></b></i>
								<i><b></b></i>
								<span></span>
							</div>
							<!-- 错误提示 -->
							<div class="rp_hint" style="font-size:14px;">两次密码不一致</div>
						</li>
					</ul>
				</div>	
			</div>		
			<div class="r_text">
				<ul>
					<li class="lb_btn"><a href="javascript:;" class="" onclick="savePayPwd()">确定</a></li>
				</ul>
			</div>
		</div>		
	</div>
</div>
 <jsp:include page="/comm/foot.jsp"></jsp:include>
 <script type="text/javascript">
 var passwordStr3=null;
 var passwordStr4=null;
 var pwdHtmlbak;
 $("document").ready(function (){
	passwordStr3 =new sixDigitPassword("passwordbox3");
 	passwordStr4=new sixDigitPassword("passwordbox4");
 	pwdHtmlbak = $('#pwd_wrap').html();
 });

 var timeIndex = 0;
//跳转到下一个输入框
 function clickPassword()
 {
 	$('#passwordbox3').click();
   		timeIndex = setInterval(function(){
   			try
   			{
   				if($('#passwordbox3 i:last b:first-child').attr('style').indexOf('inherit') != -1)
   				{
   					$('#passwordbox4').click();
   					clearInterval(timeIndex);
   				}
   			}catch(e)
   			{}
 	},100);
 }
 clickPassword();
 function savePayPwd(){
 	//修改密码
     var data = forget_pwd_data();
     $.ajax({
 		 type: "post",
         url: "<%=basePath%>/account/forgetPayPassword.shtml",
         data: {
         	userName:data.userName,
     		code:data.code,
     		payPassword:data.payPassword
         },
         dataType: "json",
         beforeSend:function(){
	    	  top.F.loading.show();
	      },
	    complete:function(){
	    	  top.F.loading.hide();
	    },
        success: function(data){
        	 if(data.code=="1"){
        		 location.href="<%=basePath%>/ex/pwd-set-succ.shtml?pageType=2"; 
        	 }else{
        		 new top.Tip({msg : data.msg, type: 2 , timer:2000});
        	 }
         }	
 	});
 }

//检验参数
 function forget_pwd_data(){
 	var userName =  $("#userLoginName").val();
 	var code = $("#code").val();
 	var payPassword3 = passwordStr3.returnStr();
 	var payPassword4 = passwordStr4.returnStr();
 	$("#six_pwd_err").removeClass("sp_add");
 	if(payPassword3.length !=6 || payPassword4.length!=6||(!payPwd_reg.test(payPassword3))||(!payPwd_reg.test(payPassword4))){
 		$("#six_pwd_err").addClass("sp_add");
		$("#six_pwd_err .rp_hint").html('密码必须为6位数字');
		setTimeout(function(){
			reInitPwdPanel();
		},2000);
 		
 		return;
 	}
 	if(payPassword3 !=payPassword4){
 		$("#six_pwd_err").addClass("sp_add");
		$("#six_pwd_err .rp_hint").html('两次输入的密码必须相同');
		setTimeout(function(){
			reInitPwdPanel();
		},2000);
 		return;
 	}
 	
 	return{
 		userName:userName,
 		code:code,
 		payPassword:payPassword3
 	};
 }
//重新初始化密码框
function reInitPwdPanel(){
	$('#pwd_wrap').html(pwdHtmlbak);
	passwordStr3=new sixDigitPassword("passwordbox3");
	passwordStr4=new sixDigitPassword("passwordbox4");
	setTimeout(function(){
		 clickPassword();
	},200);
}
 
 </script>
 </body>
 </html>