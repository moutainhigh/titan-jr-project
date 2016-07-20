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
<div class="S_popup clearfix">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 50px;">修改支付密码</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 500px; padding: 0 20px;">
		<div class="password_set">
			<ul class="password_change">
				<li class="J_remember curpo">我记得原支付密码<i class="tourism06"></i></li>
				<li class="J_forget curpo">我忘记原支付密码了<i class="tourism06"></i></li>
			</ul>
		</div>
	</div>
</div>

<script>
//修改密码记得原支付密码
$('.J_remember').on('click',function(){
	 IframeBox.dPop.remove();
    top.F.loading.show();
    modify_pwd();
});   

function modify_pwd(){
	$.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/modify-pwd-remember.shtml',           
        success : function(html){
            top.F.loading.hide();
            var d =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
                button : [ 
                        {
                            value: '确定',
                            skin : 'btn p_lr30',
                            callback: function () {
                               var validate_password = validate_payPassword();
                               if(validate_password==true){
                            	   update_old_pwd();
                               }
                            },
					    autofocus: true
                        },
                        {
                            value: '取消',
                            skin : 'btn btn_grey btn_exit',
                            callback: function () {
                            //   alert('c');
                            }
                        }	
                    ]              
            }).showModal();
        }
    });
}

function validate_payPassword(){
	var payPassword3 = PasswordStr3.returnStr();
	var payPassword4 = PasswordStr4.returnStr();
	var payPassword5 = PasswordStr5.returnStr();
	
	if(payPassword3.length!=6){
		new top.Tip({msg : '原密码必须为6位！', type: 1 , timer:1000}); 
		modify_pwd();
		return false;
	}
	
	if(payPassword4.length!=6||payPassword5.length!=6){
		new top.Tip({msg : '设置的密码必须为6位！', type: 1 , timer:1000}); 
		modify_pwd();
		return false;
	}
	
	if(payPassword4!=payPassword5){
		new top.Tip({msg : '两次新密码输入不相同！', type: 1 , timer:1000});   
		modify_pwd();
		return false;
	}
	
	if(payPassword3==payPassword4){
		new top.Tip({msg : '原密码不能和新密码相同！', type: 1 , timer:1000});   
		modify_pwd();
		return false;
	}
	return true;
	
}

function update_old_pwd(){
	$.ajax({
		 type: "post",
         url: "<%=basePath%>/account/setPayPassword.action",
         data: {
        	/*  oldPassword:rsaData(PasswordStr3.returnStr()),
        	 payPassword:rsaData(PasswordStr4.returnStr()) */
        	 oldPassword:PasswordStr3.returnStr(),
        	 payPassword:PasswordStr4.returnStr()
         },
         dataType: "json",
         success: function(data){
        	 if(data.result=="success"){//密码设置成功
        			new top.Tip({msg : '设置成功！', type: 1 , time:1000});   
        		      top.F.loading.show();        
                      setTimeout(function(){
                          top.F.loading.hide();
                          new top.Tip({msg : '设置成功！', type: 1 , time:1000}); 
                      },1000);  
        	 }else{
        		 new top.Tip({msg : data.msg, type: 1 , time:1000}); 
        		 modify_pwd();
        	 }
         }
	});
}
var IframeBox=document.getElementById('right_con_frm').contentWindow || window.frames['right_con_frm'].contentWindow || window.frames['right_con_frm'];


//修改密码忘记原支付密码
$('.J_forget').on('click',function(){
/* 	console.log(dPop)
	parent.dPop.remove();
 */   	
   IframeBox.dPop.remove();
    top.F.loading.show();   
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/modify-pwd-forget.shtml',           
        success : function(html){
            top.F.loading.hide();
            var d =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
            }).showModal();
            //点击关闭
			$(".J_close,.btn_exit").on('click', function() {
				d.remove();
			});
            
        }
    });
}); 
  
</script>
</body>
</html>
