<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${userInfoDTOPage.itemList}" var="userInfoDTO">
<tr>
	<td>${userInfoDTO.userLoginName }</td>
	<td>${userInfoDTO.userName }<c:if test="${userInfoDTO.isAdmin==1 and (empty userInfoDTO.userName) }">超级管理员</c:if></td>
	<td>
		<c:forEach items="${userInfoDTO.roleDTOList}" var="roleDTO">
			<c:if test="${roleDTO.roleCode=='PAY' and roleDTO.isActive==1}">
				<img src="<%=cssSaasPath %>/images/TFS/right.png" alt="">
			</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${userInfoDTO.roleDTOList}" var="roleDTO">
			<c:if test="${roleDTO.roleCode=='VIEW' and roleDTO.isActive==1}">
				<img src="<%=cssSaasPath %>/images/TFS/right.png" alt="">
			</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${userInfoDTO.roleDTOList}" var="roleDTO">
			<c:if test="${roleDTO.roleCode=='RECHARGE' and roleDTO.isActive==1}">
				<img src="<%=cssSaasPath %>/images/TFS/right.png" alt="">
			</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${userInfoDTO.roleDTOList}" var="roleDTO">
			<c:if test="${roleDTO.roleCode=='FINANCING' and roleDTO.isActive==1}">
				<img src="<%=cssSaasPath %>/images/TFS/right.png" alt="">
			</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${userInfoDTO.roleDTOList}" var="roleDTO">
			<c:if test="${roleDTO.roleCode=='LOAN' and roleDTO.isActive==1}">
				<img src="<%=cssSaasPath %>/images/TFS/right.png" alt="">
			</c:if>
		</c:forEach>
	</td>
	<td>
		<c:forEach items="${userInfoDTO.roleDTOList}" var="roleDTO">
			<c:if test="${roleDTO.roleCode=='MESSAGE' and roleDTO.isActive==1}">
				<img src="<%=cssSaasPath %>/images/TFS/right.png" alt="">
			</c:if>
		</c:forEach>
	</td>
	<td class="td-data" data-tfs-userloginname="${userInfoDTO.userLoginName}" data-tusername="${userInfoDTO.userName}" data-tfs-userid="${tfs:encryptAES(userInfoDTO.tfsUserId)}"  >
		<div class="TFS_frozenset">
			<c:if test="${userInfoDTO.isAdmin==0 }">
				<span class="blue undl curpo J_frozen" data-status="2" <c:if test="${userInfoDTO.status==2 }">style="display:none;"</c:if>>冻结</span>
				<span class="orange undl curpo J_thaw" data-status="1" <c:if test="${userInfoDTO.status==1 }">style="display:none;"</c:if>>解冻</span>
			</c:if>
			<span class="blue undl curpo J_modify" >修改</span>
			<c:if test="${userInfoDTO.isAdmin==0 }">
				<span class="blue undl curpo J_relieve">解除权限</span>
			</c:if>
		</div>
	</td>
</tr>
</c:forEach>
<c:if test="${userInfoDTOPage.totalPage==0 }">
	<tr>
		<td colspan="10">
			<div class="TFSno_data" > <i></i>
				无相关数据！
			</div>
		</td>
	</tr>
</c:if>
<tr style="display:none">
		<td colspan="10">
			<input type="hidden" name="totalPage" class="totalPage" value="${userInfoDTOPage.totalPage}"/>
			<input type="hidden" name="pageSize" class="pageSize" value="${userInfoDTOPage.pageSize}"/>
			<input type="hidden" name="currentPage" class="currentPage" value="${userInfoDTOPage.currentPage}"/>
			<input type="hidden" name="totalCount" class="totalCount" value="${userInfoDTOPage.totalCount}"/>
		</td>
</tr>
<script type="text/javascript">

//冻结
$('.J_frozen').on('click',function(){
	var _this = $(this);
	var status = $(this).attr("data-status");
	var tEle = $(this).parents(".td-data");
	var tlogin = tEle.attr("data-tfs-userloginname");
	var tfsUserId = tEle.attr("data-tfs-userid");
	
	new top.createConfirm({
	    title:'提示',
		padding: '20px 20px 40px',		
        content : '<div class="f_14 l_h26">您确定要冻结用户“'+tlogin+'”</div>',
		ok : function(){
			$.ajax({
		        dataType : 'json',
		        context: document.body,
		        data:{"tfsUserId":tfsUserId,"status":status},
		        url : '<%=basePath%>/setting/freeze-user.shtml',        
		        success : function(result){
		        	if(result.code==1){
		        		_this.hide();
			        	_this.next(".J_thaw").show();
						new top.Tip({msg : '用户冻结成功！', type: 1 });
		        	}else{
		        		new top.Tip({msg : result.msg, type: 3 });
		        	}
		        	 
		        }
		    });
			
		}
      }); 
});
//解冻
$(".J_thaw").on('click',function(){
	var _this = $(this);
	var tEle = _this.parents(".td-data");
	var tlogin = tEle.attr("data-tfs-userloginname");
	var tfsUserId = tEle.attr("data-tfs-userid");
	var status = _this.attr("data-status");
	new top.createConfirm({
	    title:'提示',
		padding: '20px 20px 40px',		
        content : '<div class="f_14 l_h26">您确定要解冻用户“'+tlogin+'”</div>',
		ok : function(){
			$.ajax({
		        dataType : 'json',
		        context: document.body,
		        data:{"tfsUserId":tfsUserId,"status":status},
		        url : '<%=basePath%>/setting/freeze-user.shtml',        
		        success : function(result){
		        	if(result.code==1){
			        	_this.hide();
			        	_this.prev(".J_frozen").show();
						new top.Tip({msg : '用户解冻成功！', type: 1 }); 
		        	}else{
		        		new top.Tip({msg : result.msg, type: 3 });
		        	}
		        }
		    });
		}
      }); 
	
});	
//修改员工权限
$('.J_modify').on('click',function(){
	var tdEle = $(this).parents(".td-data");
	var tfsUserId = tdEle.attr("data-tfs-userid");
  	location.href='<%=basePath%>/setting/employee-add.shtml?tfsUserId='+tfsUserId;
}); 

//解除权限
$('.J_relieve').on('click',function(){
	var tdEle = $(this).parents(".td-data");
	var tUserLoginName = tdEle.attr("data-tfs-userloginname");
	var tfsUserId = tdEle.attr("data-tfs-userid");
	 new top.createConfirm({
	    title:'提示',
		padding: '20px 20px 40px',	
		width:320,	
      	content : '<div class="f_14 l_h26">你确定要删除用户“'+tUserLoginName+'”</div>',
		ok : function(){
			$.ajax({
				type:"post",
				url: '<%=basePath%>/setting/cancel-permission.shtml',	
				data:{"tfsUserId":tfsUserId},
				dataType:"json",
				success:function(result){
					if(result.code==1){
						new top.Tip({msg : '操作成功！', type: 1 });
						ajaxPage.load();
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
					new top.Tip({msg : '系统错误，请重试!', type: 3 , timer:1500});
				}
			});
      },
      cancel : function(){
      }
    }); 
}); 
</script>