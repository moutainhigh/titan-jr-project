<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${pageCreditCompanyInfoDTO.itemList}" var="creditCompanyInfoDTO">
<tr>
    <td class="tdl"><span class="pl12">${creditCompanyInfoDTO.createTime}</span></td>
	<td class="tdl"><span class="textleng" style="max-width:200px;" title="${creditCompanyInfoDTO.name }">${creditCompanyInfoDTO.name }</span></td>
	<td class="tdl">${creditCompanyInfoDTO.contactName }</td>
	<td class="tdl">${creditCompanyInfoDTO.contactPhone }</td>
	<td class="tdl">小明明</td>
	<td class="tdl">
	<c:if test="${creditCompanyInfoDTO.status ==1}">草稿</c:if>
	<c:if test="${creditCompanyInfoDTO.status ==2}"><span class="c_red">待审核</span></c:if>
	<c:if test="${creditCompanyInfoDTO.status ==3}">初审通过</c:if>
	<c:if test="${creditCompanyInfoDTO.status ==4}">初审未通过</c:if>
	<c:if test="${creditCompanyInfoDTO.status ==5}">审核已通过</c:if>
	</td>
	<td class="tdl">
	<c:if test="${creditCompanyInfoDTO.status ==2}"><a href="<%=basePath%>/admin/credit-order-detail.shtml?orderNo=${creditCompanyInfoDTO.orderNo }&opt=check" class="c_blue cursor undl j_loan_check">审核</a></c:if>
	<c:if test="${creditCompanyInfoDTO.status !=2}"><a href="<%=basePath%>/admin/credit-order-detail.shtml?orderNo=${creditCompanyInfoDTO.orderNo }&opt=view" class="c_blue cursor undl j_loan_check">查看</a></c:if>
	
	</td>		
</tr> 
</c:forEach>
<c:if test="${pageCreditCompanyInfoDTO.totalPage==0 }">
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
			<input type="hidden" id="creditOrderCount" value="${creditOrderCount}"/>
			<input type="hidden" name="totalPage" class="totalPage" value="${pageCreditCompanyInfoDTO.totalPage}"/>
			<input type="hidden" name="pageSize" class="pageSize" value="${pageCreditCompanyInfoDTO.pageSize}"/>
			<input type="hidden" name="currentPage" class="currentPage" value="${pageCreditCompanyInfoDTO.currentPage}"/>
			<input type="hidden" name="totalCount" class="totalCount" value="${pageCreditCompanyInfoDTO.totalCount}"/>
		</td>
</tr>

<script type="text/javascript">
//查看/审核操作
$('.j_loan_check').on('click',function(){
	var opt = $(this).attr("data-opt");
	var orderno = $(this).attr("data-order-no");
	top.F.loading.show("mask");
	$.ajax({
        dataType : 'html',
        type:'post',
        context: document.body,
        data:{'orderNo':orderno,'opt':opt},
        url : '<%=basePath%>/admin/credit-order-detail.shtml',            
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
            		new top.Tip({msg : '审核成功', type: 1 , time:1500});
            		d.remove();
            		search();
            	}
			});
        },
        error:function(){
        	new top.Tip({msg : '请求失败，请重试', type: 3 , time:1500});
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
		new top.Tip({msg : '请选择审核结果!', type: 3 , time:1500});
		return ;
	}
	var reason = $("#checkReason").val().trim();
	if(checkStatus==0&&reason==''){
		new top.Tip({msg : '请输入不通过的原因!', type: 3 , time:1500});
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
        	   new top.Tip({msg : result.msg, type: 3 , time:1500});
           }
        },
        error:function(){
        	new top.Tip({msg : '请求失败，请重试', type: 3 , time:1500});
        },
        complete:function(){
        	top.F.loading.hide();
        }
    });
	return flag;
}
</script>