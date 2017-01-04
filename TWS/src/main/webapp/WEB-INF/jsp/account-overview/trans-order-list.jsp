<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<c:forEach items="${tradePage.itemList}" var="tradeItem">
	<tr>				
		<td width=""><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${tradeItem.createtime}" /></td>
		<td width="tdr">
			  <c:if test="${tradeItem.remark != null and tradeItem.remark != ''}">
                <i class="flag_ico J_remark" orderId="${tradeItem.userorderid}" onclick="showRemark(this)"></i>
            </c:if>
		</td>
		<td width="">${tradeItem.tradeType}</td>
		<td width="" >
			<span style="max-width: 240px" title="${tradeItem.goodsname}-${tradeItem.goodsdetail}">
				${tradeItem.goodsname}
                <c:if test="${tradeItem.goodsdetail != null}">
                    -${tradeItem.goodsdetail}
                </c:if>
		
			</span>
		</td>
		<td width="" >
			<span style="max-width: 170px" title="${tradeItem.transTarget}">
		  		${tradeItem.transTarget}
        	</span>
        </td>
		<td class="tdr">
			<c:if test="${tradeItem.tradeType == '付款' or tradeItem.tradeType == '提现'}">
                -
            </c:if>
            <c:if test="${tradeItem.tradeType == '收款' or tradeItem.tradeType == '充值'}">
                +
            </c:if>
            <fmt:formatNumber value="${tradeItem.tradeamount /100}"  pattern="#,##0.00#" />
		</td>
		<td>
			<c:if test="${tradeItem.isEscrowedPayment == 0 and tradeItem.tradeType == '收款' and tradeItem.statusid=='6'}">
                <i class="freeze_ico" title="供应商确认订单号即可解冻"/>
            </c:if>
		</td>
		<td class="tdr"><fmt:formatNumber value="${tradeItem.receivedfee /100}"  pattern="#,##0.00#" /></td>
		<td></td>
		<td>
			 <c:if test="${(tradeItem.statusid == 0 or tradeItem.statusid==3) and tradeItem.tradeType !='充值'  or tradeItem.statusid==7 }">
               	 处理中
            </c:if>
            <c:if test="${tradeItem.statusid == 8 }">
               	 已成功
            </c:if>
            <c:if test="${tradeItem.statusid == 6 }">
              	  已冻结
            </c:if>
            <c:if test="${tradeItem.statusid == 9}">
                <i class="MyAssets_red">交易失败</i>
            </c:if>
		</td>
		<td class=" ">
			<c:if test="${tradeItem.tradeType == '收款'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" href="<%=basePath%>/account/order-receive-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">详情</a>
            </c:if>
            <c:if test="${tradeItem.tradeType == '付款'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" href="<%=basePath%>/account/order-pay-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">详情</a>
            </c:if>
            <c:if test="${tradeItem.tradeType == '充值'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" href="<%=basePath%>/account/order-recharge-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">详情</a>
            </c:if>   
            <c:if test="${tradeItem.tradeType == '提现'}">
                <a class="J_orderDetails blue decorationUnderline m_r10" href="<%=basePath%>/account/order-withdraw-detail.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">详情</a>
            </c:if>
            <a class="J_remark blue decorationUnderline"  href="<%=basePath%>/account/order-remark-history.shtml?userOrderId=${tradeItem.userorderid}" target="_blank">备注</a>
		</td>
	</tr>
</c:forEach>
<input type="hidden" id="tradePageTotal" value="${tradePage.totalCount}">
<input type="hidden" id="tradePageCurrent" value="${tradePage.currentPage}">
