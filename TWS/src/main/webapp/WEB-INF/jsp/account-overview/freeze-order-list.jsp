<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/comm/taglib.jsp" %>
<c:if test="${fn:length(tradePage.itemList)==0}">
<tr>
	<td colspan="7">暂无已冻结金额</td>
</tr>
</c:if>
<c:if test="${fn:length(tradePage.itemList)>0}">
<c:forEach items="${tradePage.itemList}" var="tradeItem">
	<tr>
		<td></td>
		<td class="tdl"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${tradeItem.createtime}"/></td>
		<td class="tdl">
			<c:if test="${tradeItem.tradeType == '收款'}">
				<a class="blue undl" href="<%=basePath%>/account/order-receive-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">${tradeItem.userorderid}</a>
			</c:if>
			<c:if test="${tradeItem.tradeType == '付款'}">
                <a class="blue undl" href="<%=basePath%>/account/order-pay-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">${tradeItem.userorderid}</a>
            </c:if>

            <c:if test="${tradeItem.tradeType == '充值'}">
               <a class="blue undl" href="<%=basePath%>/account/order-recharge-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">${tradeItem.userorderid}</a>
            </c:if>

            <c:if test="${tradeItem.tradeType == '提现'}">
              <a class="blue undl" href="<%=basePath%>/account/order-withdraw-detail?userOrderId=${tradeItem.userorderid}" target="_blank">${tradeItem.userorderid}</a>
            </c:if>
		</td>
		<td class="tdl">
			<span class="Province" style="max-width:300px;" title="${tradeItem.goodsname}">
				${tradeItem.goodsname}
                <c:if test="${tradeItem.goodsdetail != null}">
                    -${tradeItem.goodsdetail}
                </c:if>
			</span>
		</td>
		<td class="tdl"><span class="Province" style="max-width:185px;" title="${tradeItem.transTarget}">${tradeItem.transTarget}</span></td>
		<td class="tdr">
			<c:if test="${tradeItem.tradeType == '付款' or tradeItem.tradeType == '提现'}">
                -
            </c:if>
            <c:if test="${tradeItem.tradeType == '收款' or tradeItem.tradeType == '充值'}">
                +
            </c:if>
            <fmt:formatNumber value="${tradeItem.tradeamount /100}"  pattern="#,##0.00#" />
            
		</td>
		<td class="tdl"><span class="ico_forzen" title="供应商确认订单号即可解冻"></span></td>
	</tr>
</c:forEach>
</c:if>
<input type="hidden" id="tradePageTotal" value="${tradePage.totalCount}">
<input type="hidden" id="tradePageCurrent" value="${tradePage.currentPage}">