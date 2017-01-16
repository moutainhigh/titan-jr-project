<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 记得登录密码  -->
<div class="S_popup clearfix S_popTop remember_wrap">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding:0 50px;">修改登录密码</li>
      <li class="P_right"></li>
    </ul>
  </div>
  <div class="S_popup_content1" style="top:0px; padding:0 20px; width: 500px;">
  	<div class="password_set">
			<ul class="passwordset_u2 pass_modify clearf" style="margin:0px;">
				<li>
					<span class="reset_pass">原密码：</span>
					<input type="password" class="text w_250 " name="password" id="oldLoginPassword" readonly="readonly" placeholder="请输入原密码" datatype="/\w{1,}/" errormsg="请输入原始密码"/>
					<span class="ico eye"></span>
				</li>
				<li>
					<span class="reset_pass">新密码：</span>
					<input type="password" class="text w_250 " id="newLoginPassword" readonly="readonly" placeholder="建议至少使用两种字符组合" datatype="/\w{6,}/" errormsg="密码长度至少6位"/>
					<span class="ico eye"></span>
				</li>
				<li>
					<span class="reset_pass">确认新密码：</span>
					<input type="password" class="text w_250 " name="passwordConfirm" id="passwordConfirm" readonly="readonly" placeholder="请再次输入密码"  customFun="confirmPass"/>
					<span class="ico eye"></span>
				</li>
			</ul>
		</div> 	
  </div>
</div>
<!--弹窗白色底--> 
<script>
var passform ;
$(function(){
	F.UI.scan();
	passform = new validform('.password_set',{
		msgAlign: 'bottom'
	});
	//修复在360下记住了密码bug
	window.setTimeout(function(){
		$("input[readonly='readonly']").attr("readonly", false);
	}, 500);
});

//显示密码
$('.eye').on('click',function(){  
    var _this= $(this);
    var a= $(this).parent().find('input');    
    if (_this.is('.on')) {
        a[0].type = "password"; 
        $(this).removeClass('on');
    }else{
        a[0].type = "text"; 
        $(this).addClass('on');        
    }  
});
//确认密码框失去焦点
function confirmPass(){
	var pass1 = $("#newLoginPassword").val();
	var pass2 = $("#passwordConfirm").val();
	if($.trim(pass2).length<1){
		passform.setErrormsg($(".remember_wrap #passwordConfirm"),"确认新密码不能为空");
		return false;
	}
	if(pass1!=pass2){
		passform.setErrormsg($(".remember_wrap #passwordConfirm"),"两次输入密码不一致");
		return false;
	}
	return true;
}

//保存密码
function savePassword(){
	var saveFlag = false;
	if(!passform.validate()){
		return saveFlag;
	}
	$.ajax({
		type:"post",
		async:false,
		url: '<%=basePath%>/setting/set-login-password-password.shtml',
		data:{"oldLoginPassword":$(".remember_wrap #oldLoginPassword").val(),"newLoginPassword":$(".remember_wrap #newLoginPassword").val()},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				saveFlag = true;
				new top.Tip({msg : '设置成功！', type: 1 , timer:1000});
			}else{
				new top.Tip({msg : result.msg, type: 3, timer:2000});
			}
		},
		beforeSend:function(){
	    	  top.F.loading.show();
	      },
	    complete:function(){
	    	  top.F.loading.hide();
	    },
	    error:function(xhr,status){
			if(xhr.status&&xhr.status==403){
    			new top.Tip({msg : '没有权限访问，请联系管理员', type: 3 , time:2000});
    			return ;
    		}
			new top.Tip({msg : '系统错误，请重试!', type: 3 , time:1500});
		}
	});
	
	return saveFlag;
}
</script>