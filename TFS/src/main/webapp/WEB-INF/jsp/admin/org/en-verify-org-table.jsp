<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${orgCheckDTOPage.itemList}" var="orgCheckDTO">
<tr>
	<td class="tdl"><span class="pl12">${orgCheckDTO.userloginname }</span></td>
	<td class="tdl"><span class="textleng" style="max-width:200px;" title="${orgCheckDTO.subOrgname }">${orgCheckDTO.subOrgname }</span></td>
	<td class="tdl">${orgCheckDTO.subBuslince }</td>
	<td class="tdl">${orgCheckDTO.subConnect }</td>
	<td class="tdl">${orgCheckDTO.subMobiletel }</td>
	<td class="tdl">${orgCheckDTO.checkuser }</td>
	<td class="tdl">
		<c:if test="${orgCheckDTO.resultkey=='FT'}"><span class="c_red">待审核</span></c:if>
		<c:if test="${orgCheckDTO.resultkey=='FT_INVALID'}">初审未通过</c:if>
		<c:if test="${orgCheckDTO.resultkey=='REVIEW'}">复审中</c:if>
		<c:if test="${orgCheckDTO.resultkey=='REVIEW_INVALID'}">复审未通过</c:if>
		<c:if test="${orgCheckDTO.resultkey=='PASS'}">已通过</c:if>
		<c:if test="${orgCheckDTO.resultkey=='FT'}"></c:if>
	</td>
	<td class="td-data" data-orgId="${orgCheckDTO.orgId}" data-orgCode="${orgCheckDTO.orgcode}" data-tfsLoginUsername="${orgCheckDTO.userloginname }">
		<c:if test="${orgCheckDTO.resultkey!='FT' and orgCheckDTO.resultkey!='REVIEW'}"><span class="orange cursor undl J_examine" data-opt="view">查看</span></c:if>
		<c:if test="${orgCheckDTO.resultkey=='FT' or orgCheckDTO.resultkey=='REVIEW'}"><span class="c_blue cursor undl J_examine" data-opt="check" style="padding-left:5px;">审核</span></c:if>
		
		<c:if test="${not empty orgCheckDTO.merchantcode}"><span class="c_blue cursor undl J_cancel_orgbind" style="padding-left:5px;">取消绑定</span></c:if>
		
	</td>
</tr>
</c:forEach>
<c:if test="${orgCheckDTOPage.totalPage==0 }">
	<tr>
		<td colspan="8">
			<div class="TFSno_data" > <i></i>
				无相关数据！
			</div>
		</td>
	</tr>
</c:if>
<tr style="display:none">
		<td colspan="8">
			<input type="hidden" id="enCount"  value="${enCount}"/>
			<input type="hidden" id="perCount" value="${perCount}"/>
			<input type="hidden" name="totalPage" class="totalPage" value="${orgCheckDTOPage.totalPage}"/>
			<input type="hidden" name="pageSize" class="pageSize" value="${orgCheckDTOPage.pageSize}"/>
			<input type="hidden" name="currentPage" class="currentPage" value="${orgCheckDTOPage.currentPage}"/>
			<input type="hidden" name="totalCount" class="totalCount" value="${orgCheckDTOPage.totalCount}"/>
		</td>
</tr>

<script type="text/javascript">
//取消机构绑定
$(".J_cancel_orgbind").on('click',function(){
	var tEle = $(this).parents(".td-data");
	var orgCode = tEle.attr("data-orgCode");
	
	var d = top.dialog({
	        title: '提示',
	        padding: '35px 20px 39px 35px',
	        width:420,
	        content: '确定要取消该金融账户的绑定关系?',
	        okValue: '确 定',
	        ok: function () {
	      	  $.ajax({
	      		  type:"post",
	      		  dataType:"json",
	      		  data:{'orgCode':orgCode},
		          url : '<%=basePath%>/admin/cancelOrgBind.shtml',
	      		  success:function(data){
	      			  if(data.code=="1"){
		        		new top.Tip({msg : '取消成功', type: 1 , timer:1500});
		        		search();
		        	}else{
		        		new top.Tip({msg :data.msg, type: 3 , timer:3000});
		        	}
	      		  }
	      	  });
	        },
	        cancelValue: '取消',
	        cancel: function () {}
		}).showModal();
	 
});
//查看/审核操作
$('.J_examine').on('click',function(){
	var opt = $(this).attr("data-opt");
	var tEle = $(this).parents(".td-data");
	var orgId = tEle.attr("data-orgid");
	var tfsLoginUsername = tEle.attr("data-tfsLoginUsername");
	top.F.loading.show("mask");
	$.ajax({
        dataType : 'html',
        type:'post',
        context: document.body,
        data:{'orgId':orgId,'opt':opt,'tfsLoginUsername':tfsLoginUsername},
        url : '<%=basePath%>/admin/viewOrg.shtml',            
        success : function(html){
            var d =  window.top.dialog({
                title: '账户管理  >  申请审核  >  企业用户  >  快速审核',
                padding: '0 0 0px 0',
                width: 1100,
                content: html,
                skin : 'saas_pop'  
            }).showModal();
            //关闭
            $('.J_close').on('click',function(){
            	d.remove();
			});
			//保存
            $('.J_save').on('click',function(){
            	if(check()){
            		new top.Tip({msg : '审核成功', type: 1 , timer:1500});
            		d.remove();
            		search();
            	}
			});
        },
        error:function(){
        	new top.Tip({msg : '请求失败，请重试', type: 3 , timer:1500});
        },
        complete:function(){
        	top.F.loading.hide();
        }
    });
}); 
//审核
function check(){
	var checkStatus = $("#checkStatus").val();
	if(checkStatus==''){
		new top.Tip({msg : '请选择审核结果!', type: 3 , timer:1500});
		return ;
	}
	var reason = $("#checkReason").val().trim();
	if(checkStatus==0&&reason==''){
		new top.Tip({msg : '请输入不通过的原因!', type: 3 , timer:1500});
		return;
	}
	var orgId = $(".checkOrg #orgId").val();
	var flag = false;
	top.F.loading.show("mask");
	$.ajax({
		async:false,
        dataType : 'json',
        type:"post",
        context: document.body,
        data:{'checkStatus':checkStatus,'reason':reason,'orgId':orgId},
        url : '<%=basePath%>/admin/checkOrg.shtml',      
        success : function(result){
           if(result.code==1){
        	   flag = true;
           }else{
        	   new top.Tip({msg : result.msg, type: 3 , timer:1500});
           }
        },
        error:function(){
        	new top.Tip({msg : '请求失败，请重试', type: 3 , timer:1500});
        },
        complete:function(){
        	top.F.loading.hide();
        }
    });
	return flag;
}
</script>