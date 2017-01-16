<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>修改员工权限信息-泰坦钱包</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="./../header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">	
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="employee" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<div class="sr_crumbs">我的账户  >  泰坦钱包设置  >  员工权限设置  >  修改员工权限</div>			
			<div class="sr_staff clearfix" >
				<div class="TFS_addnew clearfix" id="employee_add">
			    	<input type="hidden" id="tfsUserId" value="${tfsUserId }">
			    	<input type="hidden" id="roleIds" value="${roleIds }">
			    	<input type="hidden" id="tUserLoginName" value="${userInfoDTO.userLoginName }">
			    	<input type="hidden" id="tUserName" value="${userInfoDTO.userName }">
			    	<div class="TFS_add" id="J_form1">
			    		<ul>
			    			<li><div class="title">姓名：</div><input type="text" class="text" placeholder="请输入姓名" id="userName" value="${userInfoDTO.userName }" customFun="checkUserName" maxlength="30"/></li>
			    			<li>
			    				<div class="title">用户名：</div>    				
			    				<input type="text" placeholder="请输入手机号码/邮箱地址" class="text" id="receiveAddress" datatype="/\w*/" readonly="readonly" value="${userInfoDTO.userLoginName }" customFun="checkReceiveAddress"/>用户名用来登录泰坦金融官网或者APP
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
			    					<div class="TFS_addtabletop"><b>付款</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="38"/><i></i></label></div>
			    					<p class="TFS_tips">付款的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>充值&提现</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="40" /><i></i></label></div>
			    					<p class="TFS_tips">可以为此泰坦金服账户账户充值账户余额可以提现的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>查看</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="39"/><i></i></label></div>
			    					<p class="TFS_tips">查看账户资产总额、资产分布、交易记录、增值宝、购买理财产品情况、购买信贷产品情况
			    					</p>
			    					<span class="TFS_tipsproposal">建议用户：财务总监、副总等</span>
			    				</td>
			    			</tr>
			    			<tr>
			    				<td>
			    					<div class="TFS_addtabletop"><b>理财</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="41"/><i></i></label></div>
			    					<p class="TFS_tips">购买理财产品以及理财产品赎回的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>信贷</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="42"/><i></i></label></div>
			    					<p class="TFS_tips">购买信贷产品以及信贷产品还款的权限</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
			    				</td>
			    				<td>
			    					<div class="TFS_addtabletop"><b>消息提醒</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="43"/><i></i></label></div>
			    					<p class="TFS_tips">收付款消息提醒
			    					</p>
			    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
			    				</td>
			    			</tr>
			    		</table>
			    	</div>
			    	<div class="btnAdd">
			    			<a class="btn" href="javascript:;" onclick="saveUpdateEmployee()">保存</a>
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
<jsp:include page="/comm/foot.jsp"></jsp:include>
<script type="text/javascript">  
$(function(){
	initRole();
});
//初始化
var vform = new validform('#J_form1',{
    msgAlign: 'bottom'
});
//初始化选中权限
function initRole(){
	var roleIds = $("#roleIds").val();
	if(roleIds.length==0){
		return 
	}
	$("input[name='roleId']:checkbox").each(function(){ 
		if(roleIds.indexOf($(this).val()+",")>-1){
			$(this).attr({"checked":true})
		}
    });
}
function checkUserName()
{
	var userName = $("#employee_add #userName").val();
	if(userName ==null || $.trim(userName) =='')
	{
		vform.setErrormsg( $("#employee_add #userName"),'姓名不能为空！');
		return false;
	}
	return true;
}


//保存员工
function saveUpdateEmployee(){
	var userName = $("#employee_add #userName").val();
	var tfsUserId = $("#tfsUserId").val();
	var checkedRoleId="",uncheckedRoleId="";
	
	if(userName ==null || userName =='')
	{
		vform.setErrormsg($("#employee_add #userName"),'姓名不能为空');
		return false;
	}
	
	$("input[name='roleId']:checkbox").each(function(){ 
         if($(this).prop("checked")){
        	 checkedRoleId += $(this).val()+",";
         }else{
        	 uncheckedRoleId += $(this).val()+",";
         }
     });
	alert(11);
	if(!vform.validate()){
		return;
	}
	$.ajax({
		type:"post",
		async:false,
		url: '<%=basePath%>/setting/save-update-user.shtml',
		data:{"userName":userName,"tfsUserId":tfsUserId,"checkedRoleId":checkedRoleId,"uncheckedRoleId":uncheckedRoleId},
		dataType:"json",
		success:function(result){
			if(result.code==1){
				new top.Tip({msg : '员工权限信息修改成功', type: 1 , timer:2000});
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
		beforeSend:function(){
	    	  top.F.loading.show();
	      },
	    complete:function(){
	    	  top.F.loading.hide();
	    },
		error:function(){
			new top.Tip({msg : '系统错误，请重试!', type: 3 , time:2000});
		}
	});
	
}
//内容高度
function scrollCon(){
    var winH=$(window).height();
    $('.s_right').css({'min-height':winH-235});
}
scrollCon();
$(window).on('resize.fixed',function(){
    scrollCon();
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
</script>

</body>
</html>
