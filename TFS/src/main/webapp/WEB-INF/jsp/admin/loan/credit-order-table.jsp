<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${pageCreditCompanyInfoDTO.itemList}" var="creditCompanyInfoDTO">
<tr>
    <td class="tdl"><span class="pl12"><fmt:formatDate value="${creditCompanyInfoDTO.reqTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
	<td class="tdl"><span class="textleng" style="max-width:200px;" title="${creditCompanyInfoDTO.name }">${creditCompanyInfoDTO.name }</span></td>
	<td class="tdl">${creditCompanyInfoDTO.contactName }</td>
	<td class="tdl">${creditCompanyInfoDTO.contactPhone }</td>
	<td class="tdl">${creditCompanyInfoDTO.firstAuditor }</td>
	<td class="tdl">
	<c:if test="${creditCompanyInfoDTO.status ==1}">草稿</c:if>
	<c:if test="${creditCompanyInfoDTO.status ==2}"><span class="c_red">待审核</span></c:if>
	<c:if test="${creditCompanyInfoDTO.status ==3}">初审通过</c:if>
	<c:if test="${creditCompanyInfoDTO.status ==4}">审核未通过</c:if>
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
			<input type="hidden" id="creditOrderToCheckCount" value="${creditOrderToCheckCount}"/>
			<input type="hidden" name="totalPage" class="totalPage" value="${pageCreditCompanyInfoDTO.totalPage}"/>
			<input type="hidden" name="pageSize" class="pageSize" value="${pageCreditCompanyInfoDTO.pageSize}"/>
			<input type="hidden" name="currentPage" class="currentPage" value="${pageCreditCompanyInfoDTO.currentPage}"/>
			<input type="hidden" name="totalCount" class="totalCount" value="${pageCreditCompanyInfoDTO.totalCount}"/>
		</td>
</tr>
 