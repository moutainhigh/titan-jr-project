<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>支付密码-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
<div id="scroll" >
		<div class="main_sell clearfix user_title">
			<div class="p_r30 p_l10">
				<span>泰坦金服&nbsp;-&nbsp;泰坦金服设置&nbsp;-&nbsp;付款密码设置</span>
			</div>
		</div>
	</div>
	<div class="scroll_x hide t_56"></div>
	<div class="main_con p_t56">
		<div class="TFSpassset">
			<c:if test="${hasSetPayPass==0}">
				<h3 class="TFSpassw_title">请设置您的付款密码：</h3>
				<ul class="TFSpassw_set">
					<li>
						<span class="Passname">密码：</span>
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
					<li>
						<span class="Passname">确认密码：</span>
						<div class="sixDigitPassword" id="passwordbox1"> 
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
				<div class="Passsure"><span class="btn p_lr30 J_confirm">确定</span></div>
			
			</c:if>
			<c:if test="${hasSetPayPass==1}">
				<h3 class="TFSpassw_title"><span class="fl">您的付款密码：</span><span class="TFS_changepassword fl"><img src="<%=cssSaasPath%>/images/TFS/lock.png" alt=""><i class="blue undl curpo J_password">修改密码</i></span></h3>
			</c:if>
			<div class="TFSpayset">
				<h3 class="TFSpassw_title fl">小额免支付开关</h3>
				<div class="glright fl">
					<span uitype="switch" <c:if test="${allownopwdpay==1}">checked</c:if> class="switch-text" id="J_siwtch_auto"></span>
					<c:if test="${allownopwdpay==0}"><span class="nopassword">未开通小额免密支付，付款时需要密码</span></c:if>
					<c:if test="${allownopwdpay==1}"><span class="nopassword tip">已开通小额免密支付，1000元以下付款时无需密码</span></c:if>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script>
//渲染组件
	F.UI.scan();
	//展开、收缩表格
	var msg,sw,payDialog,toState;
    $.each($('.TFSpayset'), function(){
        var _this = $(this), siwtchArray = [], siwtchs = F.UI.find(_this.find('*[uitype=switch]'));
        if(siwtchs){
            siwtchs.length ? siwtchArray = siwtchs : siwtchArray.push(siwtchs);
            $.each(siwtchArray, function(k,v){
                v.dom.on('click',function(e){
                	sw = F.UI.find($(this));
                	toState = sw.getChecked();
                	//还原状态
                	sw.setChecked(!toState);
                    if(toState){//切换到开启
                    	msg = "您选择开通小额免密支付，1000元以下付款时无需密码。";
                    }else{//切换到关闭
                    	msg = "您选择关闭小额免密支付，所有付款都需要输入支付密码。";
                    }
                	if(toState){//切换到开启
                		//是否设置了支付密码
                    	if(checkIsSetPayPassword()){
                    		new top.Tip({msg : '请先设置支付密码', type: 2});
                    		return ;
                    	}
                    	showPayPassword();
                	}else{
                		showConfirm();
                	}
				});
            });
        }
    });
//开关确认框
function showConfirm(){
	 window.top.createConfirm({
     	width:310,
         content: msg,
         ok : function(){
             var swi = toState?1:0;
             var pay="";
             if(toState){
            	 pay = PasswordStr2.returnStr();
             }
             $.ajax({
             		type:"post",
             		url:"<%=basePath%>/setting/set-swicth.shtml",
             		data:{"allownopwdpay":swi,"payPassword":pay},
             		dataType:"json",
             		success:function(result){
             			if(result.code==1){
                 			if(toState){
                 				$(".nopassword").addClass('tip').html('已开通小额免密支付，1000元以下付款时无需密码');
                        	 } else{
                        	   	$(".nopassword").removeClass('tip').html('未开通小额免密支付，付款时需要支付密码');
                        	 }
                 			 sw.setChecked(toState);
             			}else{
             				new top.Tip({msg : result.msg, type: 2});
             			}
             		},
             		complete:function(){
             			top.F.loading.hide();
             		},
             		error:function(xhr,status){
             			 new top.Tip({msg : '请求失败，请重试', type: 2});
             		}
             	}); 
             
         }
	});
}	
	
//检查是否设置了密码
function checkIsSetPayPassword(){
	var setFlag = false;
  	 $.ajax({
      	 type: "post",
      	 async:false,
         url: "<%=basePath%>/account/checkIsSetPayPassword.action",
         data: {fcUserid:'${fcUserid}'},
         dataType: "json",
         success: function(data){
          	 if(data.result=="success"){//没有设置密码
          		setFlag = true;
          	 }else{//设置了密码
          		setFlag = false;
          	 }
          }
       }); 
  	 return setFlag;
}
//显示输入支付密码框
function showPayPassword(){
   	$.ajax({
           dataType: 'html',
           context: document.body,
           url : '<%=basePath%>/account/showPayPassword.shtml',
           success : function(html){
        	   payDialog =  dialog({
                   title: ' ',
                   padding: '0 0 0px 0 ',
                   content: html,
                   skin : 'saas_pop',
                   button : [
                       {
                           value: '确定',
                           skin : 'btn btn_grey ',
                           callback: function () {
                           	if(PasswordStr2.returnStr().length==6){
                           		if(to_check_payPassword()){
                           			return true;
                           		}
                           		$(".ui-dialog-content").html(html);
                           		payDialog.focus();
                           		return false;
                           	}else{
                           		new top.Tip({msg: '请输入6位付款密码', type: 1, timer: 2000});
                           		return false;
                           	}
                           }
                       }
                   ]
               }).showModal().focus();
           }
       });
   }

 
//检查密码是否正确
function to_check_payPassword(){
	var flag=false;
	 $.ajax({
		async:false, 
        type: "post",
        dataType: 'json',
        url: '<%=basePath%>/setting/check_payPassword.shtml',
        data: {
       	 payPassword:PasswordStr2.returnStr()
        },
        success: function (data) {
       	 if(data.code=="1"){
       		showConfirm();
       		flag = true;
       	 }else{
       		new top.Tip({msg: '输入的付款密码错误', type: 1, timer: 2000});
       		flag = false;
       	 }
        }
	 });
	 return flag;
}
//点击设置密码    
$(".J_confirm").on('click', function() {
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
	        			new top.Tip({msg : '设置成功！', type: 1 , time:1000});   
	        			  window.location.reload();//刷新当前页面.
	        	 }
	         }
			
		});
	}

});    

function validate_payPassword(){
	var payPassword1 = PasswordStr1.returnStr();
	var payPassword2 = PasswordStr2.returnStr();
	if(payPassword1.length!=6||payPassword2.length!=6){
		new top.Tip({msg : '设置的密码必须为6位！', type: 1 , time:1000}); 
		add_PayPassword();
		return false;
	}
	
	if(payPassword1!=payPassword2){
		new top.Tip({msg : '两次密码输入不相同！', type: 1 , time:1000});   
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
	 window.location.reload();
}

//密码输入框
var PasswordStr2=new sixDigitPassword("passwordbox1");
</script>
</body>
</html>
