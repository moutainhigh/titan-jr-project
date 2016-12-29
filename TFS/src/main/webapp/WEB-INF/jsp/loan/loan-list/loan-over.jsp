<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/comm/taglib.jsp"%>
		<div style="margin-right:17px;margin-left: 17px; " class="label">
				<table width="100%" cellspacing="0" border="0">
				<colgroup>					
					<col width="50">					
					<col width="13%">
					<col width="10%">
				
					<col width="13%">
					<col width="9%">
					<col width="11%">
					<col width="15%">
					<col width="15%">			
					<col width="">
				</colgroup>
				<tbody>
				<tr>					
					<td class="tdl">序号</td>
					<td width="tdl">还款时间</td>
					<td class="tdr">贷款金额(元)</td>
				
					<td class="tdr">本息合计(元)</td>
					<td></td>
					<td class="tdl">贷款类型</td>
					<td class="tdl">还款方式</td>	
					<td class="tdl">放款时间</td>
					<td class="tdl">操作</td>		
				</tr>
			</tbody></table>
		</div>
		<div class="MyAssets_listContent">
			<table cellpadding="0" cellspacing="0" width="100%" class="MyAssets_listTable">
				<colgroup>					
					<col width="50">					
					<col width="13%">
					<col width="10%">
				
					<col width="13%">
					<col width="9%">
					<col width="11%">
					<col width="15%">
					<col width="15%">			
					<col width="">
				</colgroup>
				<c:forEach items="${loanInfoList}" var="loanInfoItem" varStatus="status">
					<tr>
						
						<td width="">${status.index +1}</td>
						<td width=""><fmt:formatDate value="${loanInfoItem.lastRepaymentDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="tdr"><fmt:formatNumber value="${loanInfoItem.repaymentPrincipal /100}"  pattern="#,##0.00#" /></td>
						<td class="tdr"><fmt:formatNumber value="${(loanInfoItem.repaymentPrincipal + loanInfoItem.repaymentInterest) / 100}"  pattern="#,##0.00#" /> </td>
						<td></td>
						<td width="" >
							<c:if test="${loanInfoItem.productType==10001}">
								包房贷
							</c:if>
							<c:if test="${loanInfoItem.productType==10002}">
								运营贷
							</c:if>
						</td>
						<td width="" ><span style="max-width: 166px" >
							<c:if test="${loanInfoItem.repaymentType==1}">
								按日计利，随借随还
							</c:if>
						</span></td>
						<td width=""><fmt:formatDate value="${loanInfoItem.relMoneyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>					
						<td class=""><a class="blue decorationUnderline m_r10" href="<%=basePath%>/loan/getLoanDetailsInfo.shtml?orderNo=${loanInfoItem.orderNo}">详情</a> </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<div id="kkpager" class="page_turning"></div>
	
	<script type="text/javascript">

	var pageSize =' ${pageSize}';
	var totalRecords = '${totalCount}';
	var totalPage =parseInt( (totalRecords % pageSize) == 0 ? totalRecords / pageSize
			: (totalRecords / pageSize) + 1);

	var pageNo = 1;
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	var pageObj =new Pager({
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		isShowTotalRecords : true,
		isGoPage : true,
		mode : 'click',//默认值是link，可选link或者click

		click : function(n) {
			pageClick(n);
			//手动选中按钮
			this.selectPage(n);
			return false;
		}
	});
</script>