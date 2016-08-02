<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!-- 新增员工权限  table -->
<c:forEach items="${saasUserRoleList}" var="saasMerchantUserDTO">
 <tr <c:if test="${saasMerchantUserDTO.isAddTfs==1}">class='chose'</c:if>>
	<td class="tdl"><div class="Province ml10" style="max-width: 170px;" title="${saasMerchantUserDTO.userLoginName }">${saasMerchantUserDTO.userLoginName }</div></td>
	<td class="tdl"><div class="Province" style="max-width:90px;" title="${saasMerchantUserDTO.userName }">${saasMerchantUserDTO.userName }</div></td>
	<td class="tdl">${saasMerchantUserDTO.mobileNum }</td>
	<td class="tdl">${saasMerchantUserDTO.roleName }</td>
	<td class="tdl">
		<c:if test="${saasMerchantUserDTO.isAddTfs==1}"><a class="btn btn_add p_lr30 chose_btn"  href="javascript:;" data-istfs="${saasMerchantUserDTO.isAddTfs}">已添加</a></c:if>
		<c:if test="${saasMerchantUserDTO.isAddTfs==0}"><a class="btn btn_add p_lr30 J_add"  href="javascript:;" data-istfs="${saasMerchantUserDTO.isAddTfs}" data-saas-userLoginName="${saasMerchantUserDTO.userLoginName }" data-saas-userName="${saasMerchantUserDTO.userName }" data-saas-userid="${saasMerchantUserDTO.userId }">添加</a></c:if>
	</td>
</tr>
</c:forEach>
<script>

//新增员工添加权限
$('.J_add').on('click',function(){
	var row = $(this).parent().parent();
    var _this=$(this);
    var istfs = _this.attr("data-istfs");
    if(istfs==1){
    	return;
    }
    top.F.loading.show();
    $.ajax({
        dataType : 'html',
        type:"post",
        context: document.body,
        data:{"saasUserLoginName":_this.attr("data-saas-userLoginName"),"fcUserId":_this.attr("data-saas-userid")},
        url : '<%=basePath%>/setting/employee-add.shtml',           
        success : function(html){
            var d =  window.top.dialog({
                title: ' ',
                padding: '0 0 0px 0',
                content: html,
                skin : 'saas_pop' ,
                button : [ 
                        {
                            value: '添加',
                            skin : 'btn p_lr30',
                            callback: function () {
                            	if(saveEmployee()){
                            		row.addClass('chose');
                                	_this.addClass("chose_btn").text("已添加");
                                	 _this.attr({"data-istfs":1});
                            		return true;
                            	}
                            	return false;
                            },
                            autofocus: true
                        },
                        {
                            value: '取消',
                            skin : 'btn btn_grey btn_exit',
                            callback: function () {
                            }
                        }
                    ]              
            }).showModal();
        },
        complete:function(){
        	top.F.loading.hide();
        }
    });
}); 
</script>