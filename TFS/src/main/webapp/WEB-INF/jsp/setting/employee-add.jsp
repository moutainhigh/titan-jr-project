<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 新增权限员工 添加 -->
<div class="S_popup clearfix S_popTop " style=" width: 800px; ">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding:0 50px;">新增员工权限 - 添加</li>
      <li class="P_right"></li>
    </ul>
  </div>
  <div class="S_popup_content1" style="top:0px;">
    <div class="TFS_addnew" id="employee_add">
    	<input type="hidden" id="fcUserId" value="${fcUserId }">
    	<div class="TFS_addtitle demo_form" id="J_form1">
    		<ul>
    			<li><span class="addtitle_left">姓名：</span><input type="text" id="userName" class="text w_180 f_ui-grey-input" datatype="zh1-6" errormsg="请输入1到6位中文字符！"></li>
    			<li><span class="addtitle_left">手机号码：</span><input type="text" id="receiveAddress" placeholder="请输入手机号码" class="text w_180 f_ui-grey-input"  customFun="checkReceiveAddress" ><span class="c_999 p_l30">此手机号将作为用户名用来登录泰坦金服官网或者APP</span></li>
    			<li><span class="addtitle_left">验证码：</span>
    			<p class="text" style="width: 187px;">
    			<input type="text" id="code" class="TFSother_input w_110" customFun="checkCode">
    			<span class="sendmes curpo">获取验证码</span></p>
    			</li>
    		</ul>
    	</div>
    	<div class="TFS_addbottom">
    		<strong>权限</strong>
    		<table width="100%" border="1" cellspacing="0" class="TFS_addtable">
    			<colgroup>
    				<col width="33%">
    				<col width="33%">
    				<col width="33%">
    			</colgroup>
    			<tr>
    				<td>
    					<div class="TFS_addtabletop"><b>付款</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="38"><i></i></label></div>
    					<p class="TFS_tips">付款的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>充值&提现</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="40"><i></i></label></div>
    					<p class="TFS_tips">可以为此泰坦金服账户账户充值账户余额可以提现的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>查看</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="39"><i></i></label></div>
    					<p class="TFS_tips">查看账户资产总额、资产分布、交易记录、增值宝、购买理财产品情况、购买信贷产品情况
    					</p>
    					<span class="TFS_tipsproposal">建议用户：财务总监、副总等</span>
    				</td>
    			</tr>
    			<tr>
    				<td>
    					<div class="TFS_addtabletop"><b>理财</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="41"><i></i></label></div>
    					<p class="TFS_tips">购买理财产品以及理财产品赎回的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>信贷</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="42"><i></i></label></div>
    					<p class="TFS_tips">购买信贷产品以及信贷产品还款的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>消息提醒</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="43"><i></i></label></div>
    					<p class="TFS_tips">收付款消息提醒
    					</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
    				</td>
    			</tr>
    		</table>
    	</div>
    </div>
  </div>
</div>
<script>
//初始化
var vform = new validform('#J_form1',{
    msgAlign: 'bottom'
});
//获取验证码
function timeOut(_this){
    var i=60;
    var interval=setInterval(function () {                
         if(i>0){
             _this.html("重新发送(" + i + ")"); 
             i--;
         }else{
            _this.removeClass("huise").html("获取验证码");
            clearInterval(interval);
         }
    }, 1000);
}
//发送验证码
$('.sendmes').on('click',function(){
	var receiveAddressEle = $("#receiveAddress");
	var receiveAddress = receiveAddressEle.val();
	if(!(phone_reg.test(receiveAddress)||email_reg.test(receiveAddress))){
		new top.Tip({msg : "格式不正确", type: 3, timer:2000});
		receiveAddressEle.focus();
		return ;
	}
	_this= $(this);
	$.ajax({
		async : false,
		data:{"receiveAddress":receiveAddress},
		url : '<%=basePath%>/organ/sendRegCode.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				if(!_this.hasClass("huise")){
			        new top.Tip({msg : '验证码已成功发送,请注意查收！', type: 1, timer:2000});    
			        _this.addClass('huise');
			        timeOut(_this);
			    } 
			}else{
				new top.Tip({msg : result.msg, type: 1, timer:2500});
			}
		},
		error : function(){
			new top.Tip({msg : '网络错误，请重试', type: 3, timer:2000});
		}
	});
});

function checkReceiveAddress(receiveAddress ){
	var flag = false;
	var receiveAddressEle = $("#receiveAddress");
	if(!(phone_reg.test(receiveAddress)||email_reg.test(receiveAddress))){
		vform.setErrormsg(receiveAddressEle,'格式不正确');
		return false;
	}
	//检查是否已经注册
	$.ajax({
		async:false,
		type:'post',
		data:{"userLoginName":receiveAddress,"isOperator":0},
		url : '<%=basePath%>/organ/checkUserLoginName.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				flag = true;
			}else{
				flag = false;
				vform.setErrormsg(receiveAddressEle,result.msg);
			}
			
		}
	});
	return flag;
}
 
//检查验证码
function checkCode(){
	var validateFlag = false;
	var receiveAddress = $("#employee_add #receiveAddress").val();
	var codeEle = $("#employee_add #code");
	var code = codeEle.val();
	if($.trim(code).length==0){
		vform.setErrormsg(codeEle,'验证码不能为空');
		return false;
	}
	$.ajax({
		type:'post',
		async:false,
		url: '<%=basePath%>/organ/checkRegCode.shtml',		
		data:{"userLoginName":receiveAddress,"regCode":code},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				validateFlag = true;
			}else{
				vform.setErrormsg(codeEle,result.msg);
			}
		},
		error:function(){
			alert("系统错误，请重试!");
		}
	});
	return validateFlag;
}

//保存员工
function saveEmployee(){
	var userName = $("#employee_add #userName").val();
	var receiveAddress = $("#employee_add #receiveAddress").val();
	var code = $("#employee_add #code").val();
	var fcUserId = $("#employee_add #fcUserId").val();
	var checkedRoleId="",uncheckedRoleId=""; 
	
	$("input[name='roleId']:checkbox").each(function(){ 
         if($(this).attr("checked")){
        	 checkedRoleId += $(this).val()+",";
         }else{
        	 uncheckedRoleId += $(this).val()+",";
         }
     });
	if(!vform.validate()){
		return;
	}
	var saveFlag = false;
	$.ajax({
		type:"post",
		async:false,
		url: '<%=basePath%>/setting/save-employee.shtml',	
		data:{"userName":userName,"fcUserId":fcUserId,"receiveAddress":receiveAddress,"code":code,"checkedRoleId":checkedRoleId,"uncheckedRoleId":uncheckedRoleId},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				saveFlag = true;
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
			new top.Tip({msg : '系统错误，请重试!', type: 3 , time:1500});
		}
	});
	
	return saveFlag;
	
}
</script>