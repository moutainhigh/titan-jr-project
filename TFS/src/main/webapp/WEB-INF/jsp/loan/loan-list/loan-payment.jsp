<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/comm/taglib.jsp"%>

<div style="margin-right: 17px; margin-left: 17px;" class="label">
	<table width="100%" cellspacing="0" border="0">
		<colgroup>
			<col width="50">
			<col width="9%">
			<col width="9%">
			<col width="11%">
			<col width="7%">
			<col width="10%">
			<col width="15%">
			<col width="15%">
			<col width="11%">
			<col width="">
		</colgroup>
		<tbody>
			<tr>
				<td class="tdl">序号</td>
				<td width="tdl">还款到期日</td>
				<td class="tdr">待还本金(元)</td>
				<td class="tdr">参考待还本息(元)</td>
				<td></td>
				<td class="tdl">贷款类型</td>
				<td class="tdl">还款方式</td>
				<td class="tdl">放款时间</td>
				<td class="tdl">贷款状态</td>
				<td class="tdl">操作</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="MyAssets_listContent">
	<div style="display: none; margin: 150px auto; width: 250px;"
		class="no_data1">
		<p>
			<i class="c_999">暂无待还款项</i>
		</p>
	</div>
	<table cellpadding="0" cellspacing="0" width="100%"
		class="MyAssets_listTable">
		<colgroup>
			<col width="50">
			<col width="9%">
			<col width="9%">
			<col width="11%">
			<col width="7%">
			<col width="10%">
			<col width="15%">
			<col width="15%">
			<col width="11%">
			<col width="">
		</colgroup>
		<c:forEach items="${loanInfoList}" var="loanInfoItem">
			<tr>
				<td width="">1</td>
				<td width=""><fmt:formatDate value="${loanInfoItem.actualRepaymentDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="tdr"> <fmt:formatNumber value="${loanInfoItem.shouldCapital /100}"  pattern="#,##0.00#" /> </td>
				<td class="tdr">  <fmt:formatNumber value="${loanInfoItem.shouldInterest /100}"  pattern="#,##0.00#" /> </td>
				<td></td>
				<td width="">
					<c:if test="${loanInfoItem.productType==10001}">
						包房贷
					</c:if>
					<c:if test="${loanInfoItem.productType==10002}">
						运营贷
					</c:if>
				</td>
				
				<td width=""><span style="max-width: 180px" >
					<c:if test="${loanInfoItem.repaymentType==1}">
						按日计利，随借随还
					</c:if>
				</span></td>
				<td class=""><fmt:formatDate value="${loanInfoItem.relMoneyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${loanInfoItem.status==0}">
						无效贷款
					</c:if>
					<c:if test="${loanInfoItem.status==1}">
						审核中
					</c:if>
					<c:if test="${loanInfoItem.status==2}">
						待放款
					</c:if>
					<c:if test="${loanInfoItem.status==3}">
						待还款
					</c:if>
					<c:if test="${loanInfoItem.status==4}">
						放款失败
					</c:if>
					<c:if test="${loanInfoItem.status==5}">
						贷款失败
					</c:if>
					<c:if test="${loanInfoItem.status==6}">
						<span class="MyAssets_red">已逾期</span>
					</c:if>
					<c:if test="${loanInfoItem.status==7}">
						已结清
					</c:if>
				</td>
				<td class=""><a class="blue decorationUnderline m_r10"
					href="<%=basePath%>/loan/getLoanDetailsInfo.shtml?orderNo=${loanInfoItem.orderNo}">详情</a> <a
					class="blue decorationUnderline" href="泰坦金融-我的贷款首页-还款.html">还款</a></td>
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
