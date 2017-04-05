<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>新增员工权限信息-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="/comm/header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="employee" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  >  员工权限设置  >  新增员工权限</div>			
			<div class="sr_staff clearfix">
				<div class="TFS_addnew clearfix" id="employee_add">
			    	<div class="TFS_add" id="J_form1">
			    		<ul>
			    			<li><div class="title">姓名：</div><input type="text" class="text" placeholder="请输入姓名" id="userName" value="${fcUserName}" customFun="checkUserName" maxlength="30"/></li>
			    			<li>
			    				<div class="title">用户名：</div>    				
			    				<input type="text" placeholder="请输入手机号码/邮箱地址" class="text" id="receiveAddress" datatype="/\w*/" customFun="checkReceiveAddress"/>
			    			</li>
			
			    			<li class="yzm"><div class="title">验证码：</div>
								<input type="text" class="text" placeholder="验证码" id="code" require="true" datatype="/\w*/" errormsg="验证码错误" customFun="checkCode"><div class="r_verify" style="left:210px;">获取验证码</div>
							</li>
			    		</ul>
			    	</div>
			    	<div class="TFS_addbottom clearfix">
			    		<div class="title">权限：</div>
			    	
			    		<table width="745" border="1" cellspacing="0" class="TFS_addtable">
			    			<colgroup>
			    				<col width="33%">
			    				<col width="33%">
			    				<col width="33%">
			    			</colgroup>
			    			<tr>
			    				<td>
			    					<div class="TFS_addtabletop"><b>付款</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="38"/><i></i></label></div>
			    					<p class="TFS_tips">付款的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>充值&提现</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="40" /><i></i></label></div>
			    					<p class="TFS_tips">可以为此泰坦钱包账户充值,账户余额可以提现的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>查看</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="39"/><i></i></label></div>
			    					<p class="TFS_tips">查看账户资产总额、资产分布、交易记录、增值宝、购买理财产品情况、购买信贷产品情况
			    					</p>
			    					<span class="TFS_tipsproposal">建议用户：财务总监、副总等</span>
			    				</td>
			    			</tr>
			    			<tr>
			    				<td>
			    					<div class="TFS_addtabletop"><b>理财</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="41"/><i></i></label></div>
			    					<p class="TFS_tips">购买理财产品以及理财产品赎回的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>信贷</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="42"/><i></i></label></div>
			    					<p class="TFS_tips">购买信贷产品以及信贷产品还款的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>消息提醒</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" value="43"/><i></i></label></div>
			    					<p class="TFS_tips">收付款消息提醒
			    					</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
			    				</td>
			    			</tr>
			    		</table>
			    	</div>
			    	<div class="btnAdd">
			    			<a class="btn" href="javascript:;" onclick="saveEmployee()">添加</a>
			    			<a class="btn btn_g" href="<%=basePath%>/setting/employee.shtml">取消</a>
			    	</div>
		    	</div>
			</div>	
			<div class="h_40"></div>
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot-line.jsp"></jsp:include>
<script type="text/javascript">  
//初始化
var vform = new validform('#J_form1',{
    msgAlign: 'bottom'
});
//内容高度
function scrollCon(){
    var winH=$(window).height();
    $('.s_right').css({'min-height':winH-235});
}
scrollCon();
$(window).on('resize.fixed',function(){
    scrollCon();
});

$(function(){
	//获取验证码
	TWS.initSendCode({send_btn:$('.r_verify'),receive_input:$("#receiveAddress"),msgType:1,fui_form:vform,verifyType:"all"});
});
function checkUserName()
{
	var userName = $("#employee_add #userName").val();
	if(userName ==null || $.trim(userName) =='')
	{
		vform.setErrormsg( $("#employee_add #userName"),'姓名不能为空');
		return false;
	}
	return true;
}

//检查是否可以注册
function checkReceiveAddress(receiveAddress){
	var flag = false;
	var receiveAddressEle = $("#receiveAddress");
	if(receiveAddress == null || receiveAddress =="")
	{
		vform.setErrormsg(receiveAddressEle,'用户名不能为空');
		return false;
	}
	
	if(!(phone_reg.test(receiveAddress)||email_reg.test(receiveAddress))){
		vform.setErrormsg(receiveAddressEle,'用户名格式不正确');
		return false;
	}
	 
	//格式正确，检查是否已经注册
	$.ajax({
		async:false,
		type:'post',
		data:{"userLoginName":receiveAddress,"isOperator":0},
		url : '<%=basePath%>/ex/organ/checkUserLoginName.shtml',
		dataType : 'json',
		success : function(result){
			if(result.code==1){
				flag = true;
			}else{
				flag = false;
				if(phone_reg.test(receiveAddress)){
					vform.setErrormsg(receiveAddressEle,"该号码已经注册，请使用其他手机号");
				}else{
					vform.setErrormsg(receiveAddressEle,"该邮箱已经注册，请使用其他邮箱");
				}
			}
		}
	});
	return flag;
}
 
//检查验证码
function checkCode(){
	var validateFlag = false;
	var receiveAddressEle = $("#employee_add #receiveAddress");
	var receiveAddress = receiveAddressEle.val();
	var codeEle = $("#employee_add #code");
	var code = codeEle.val();
	if($.trim(code).length==0){
		vform.setErrormsg(codeEle,'验证码不能为空');
		return false;
	}
	if($.trim(receiveAddress).length==0){
		vform._setErrorStyle(receiveAddressEle,'用户名不能为空');
		return false;
	}
	$.ajax({
		type:'post',
		async:false,
		url: '<%=basePath%>/ex/checkCode.shtml',
		data:{"userLoginName":receiveAddress,"code":code},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				validateFlag = true;
			}else{
				vform.setErrormsg(codeEle,result.msg);
			}
		},
		error:function(){
			new top.Tip({msg : '系统错误，请重试!', type: 2 , timer:2000});
		}
	});
	return validateFlag;
}

//保存员工
function saveEmployee(){
	var userName = $("#employee_add #userName").val();
	var receiveAddress = $("#employee_add #receiveAddress").val();
	var code = $("#employee_add #code").val();
	var checkedRoleId="",uncheckedRoleId=""; 
	
	$("input[name='roleId']:checkbox").each(function(){ 
         if($(this).prop("checked")){
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
		data:{"userName":userName,"receiveAddress":receiveAddress,"code":code,"checkedRoleId":checkedRoleId,"uncheckedRoleId":uncheckedRoleId},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				new top.Tip({msg : '员工添加成功', type: 1 , timer:2000});
				setTimeout(function(){
					location.href="<%=basePath%>/setting/employee.shtml";
				},2000);
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
			new top.Tip({msg : '系统错误，请重试!', type: 2 , timer:2000});
		}
	});
	return saveFlag;
}
</script>

</body>
</html>
