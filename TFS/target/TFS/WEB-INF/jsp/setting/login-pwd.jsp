<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="S_popup clearfix">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 50px;">修改登录密码</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 500px; padding: 0 20px;">
		<div class="password_set">
			<ul class="password_change">
				<li class="J_remember curpo">我记得原登录密码<i class="tourism06"></i></li>
				<li class="J_forget curpo">我忘记原登录密码了<i class="tourism06"></i></li>
			</ul>
		</div>
	</div>
</div>

<script>
//修改密码记得原支付密码
$('.J_remember').on('click',function(){
	var IframeBox=document.getElementById('right_con_frm').contentWindow || window.frames['right_con_frm'].contentWindow || window.frames['right_con_frm'];
    IframeBox.de.remove();
    top.F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/login-pwd-remember.shtml',
        success : function(html){
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
                            	// 保存密码
                            	if(savePassword()){
                            		return true;
                            	}
                            	return false;
                            	
                               	//top.F.loading.show();        
                               //	setTimeout(function(){
                              ////     top.F.loading.hide();
                                  // new top.Tip({msg : '设置成功！', type: 1 , time:1000}); 
                              // 	},1000);  
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
        },
        complete:function(){
			top.F.loading.hide();
		},
		error:function(xhr,status){
			 new top.Tip({msg : '请求失败，请重试', type: 2});
		}
    });
});   
//修改密码忘记原支付密码
var forgetDialog;
$('.J_forget').on('click',function(){
	var IframeBox=document.getElementById('right_con_frm').contentWindow || window.frames['right_con_frm'].contentWindow || window.frames['right_con_frm'];
    IframeBox.de.remove();
    top.F.loading.show();
    $.ajax({
        dataType : 'html',
        context: document.body,
        url : '<%=basePath%>/setting/login-pwd-forget.shtml',
        success : function(html){
        	forgetDialog =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
            }).showModal();
        },
        complete:function(){
			top.F.loading.hide();
		},
		error:function(xhr,status){
			 new top.Tip({msg : '请求失败，请重试', type: 2});
		}
    });
}); 
  
</script>
