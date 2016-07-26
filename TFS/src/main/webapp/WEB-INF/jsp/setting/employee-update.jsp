<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 新增权限员工 修改 -->
<div class="S_popup clearfix S_popTop " style=" width: 800px; ">
  <div class="S_popup_title">
    <ul>
      <li class="P_left"></li>
      <li class="P_centre" style="padding:0 50px;">员工权限 - 修改</li>
      <li class="P_right"></li>
    </ul>
  </div>
  <div class="S_popup_content1" style="top:0px;">
    <div class="TFS_addnew" id="employee_add">
    	<input type="hidden" id="fcUserId" value="${fcUserId }">
    	<input type="hidden" id="tfsUserId" value="${tfsUserId }">
    	<input type="hidden" id="roleIds" value="${roleIds }">
    	<input type="hidden" id="tUserLoginName" value="${userInfoDTO.userLoginName }">
    	<input type="hidden" id="tUserName" value="${userInfoDTO.userName }">
    	<input type="hidden" id="fcUserLoginName" value="${userInfoDTO.userBindInfoDTO.fcLoginName}">
    	<div class="TFS_addtitle demo_form" id="J_form1">
    		<ul>
    			<li><span class="addtitle_left">姓名：</span><input type="text" id="userName" class="text w_180 f_ui-grey-input" datatype="s1-30" errormsg="请输入1到30位字符！" value="${userInfoDTO.userName }"></li>
    			<c:if test="${fn:indexOf(userInfoDTO.userLoginName,'@')>0}">
    				<li><span class="addtitle_left">邮箱地址：</span><input type="text" id="receiveAddress" class="text w_180 f_ui-grey-input" readonly="readonly"  value="${userInfoDTO.userLoginName }"><span class="c_999 p_l30">此邮箱地址将作为用户名用来登录泰坦金融官网或者APP</span></li>
    			</c:if>
    			<c:if test="${fn:indexOf(userInfoDTO.userLoginName,'@')==-1}">
    				<li><span class="addtitle_left">手机号码：</span><input type="text" id="receiveAddress" class="text w_180 f_ui-grey-input" readonly="readonly"  value="${userInfoDTO.userLoginName }"><span class="c_999 p_l30">此手机号将作为用户名用来登录泰坦金融官网或者APP</span></li>
    			</c:if>
    			
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
    					<div class="TFS_addtabletop"><b>付款</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="38"><i></i></label></div>
    					<p class="TFS_tips">付款的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员、预订员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>充值&提现</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> name="roleId" value="40"><i></i></label></div>
    					<p class="TFS_tips">可以为此泰坦金融账户账户充值账户余额可以提现的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
    				</td>
    				<td >
    					<div class="TFS_addtabletop"><b>查看</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" <c:if test="${userInfoDTO.isAdmin==1 }">disabled="true"</c:if> value="39"><i></i></label></div>
    					<p class="TFS_tips" >查看账户资产总额、资产分布、交易记录、增值宝、购买理财产品情况、购买信贷产品情况
    					</p>
    					<span class="TFS_tipsproposal">建议用户：财务总监、副总等</span>
    				</td>
    			</tr>
    			<tr>
    				<td>
    					<div class="TFS_addtabletop"><b>理财</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> value="41"><i></i></label></div>
    					<p class="TFS_tips">购买理财产品以及理财产品赎回的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>信贷</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> value="42"><i></i></label></div>
    					<p class="TFS_tips">购买信贷产品以及信贷产品还款的权限</p>
    					<span class="TFS_tipsproposal">建议用户：财务人员等</span>
    				</td>
    				<td>
    					<div class="TFS_addtabletop"><b>消息提醒</b><label class="f_ui-checkbox-c3 curpo fr"><input type="checkbox" name="roleId" <c:if test="${userInfoDTO.isAdmin==1 }"> disabled="true"</c:if> value="43"><i></i></label></div>
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


//保存员工
function saveUpdateEmployee(){
	var userName = $("#employee_add #userName").val();
	var tfsUserId = $("#tfsUserId").val();
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
		url: '<%=basePath%>/setting/save-update-user.shtml',
		data:{"userName":userName,"tfsUserId":tfsUserId,"checkedRoleId":checkedRoleId,"uncheckedRoleId":uncheckedRoleId},
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
		beforeSend:function(){
	    	  top.F.loading.show();
	      },
	    complete:function(){
	    	  top.F.loading.hide();
	    },
		error:function(){
			new top.Tip({msg : '系统错误，请重试!', type: 3 , time:1500});
		}
	});
	
	return saveFlag;
	
}
</script>