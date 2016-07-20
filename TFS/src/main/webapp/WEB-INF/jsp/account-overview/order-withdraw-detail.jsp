<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/comm/taglib.jsp" %>
<div class="S_popup clearfix">
	<div class="S_popup_title">
		<ul>
			<li class="P_left"></li>
			<li class="P_centre" style="padding:0 100px;">订单详情</li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content" style="width: 800px; padding: 20px 20px 10px;">
		<div class="recharge-record-box">
			<p><em><strong>提现金额：</strong><span class="recharge-colorRed fontSize24">
				<fmt:formatNumber value="${transOrder.tradeamount/100.0 }" pattern="#,##0.00#"/>
			</span>元</em></p>
			<div class="orderDetails-content">
				<div class="orderDetails-content1">
					<h3 class="orderDetails-title">基础信息</h3>
					<p class="ftSize14"><span class="w_250">付款账户：
                        <i class="recharge-colorRed">${transOrder.transTarget }</i></span><span
							class="w_160">交易类型：提现</span>交易状态：
						<c:if test="${transOrder.statusid == 8}">
							已成功
						</c:if>
						<c:if test="${transOrder.statusid == 9}">
							交易失败
						</c:if>
					</p>
					<p class="ftSize14">
                        <span class="w_250">
                        交易创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.createtime}"/>
                        </span>
						<span class="w_160">操作人：
							<c:if test="${transOrder.creator != null}">
								${transOrder.creator}
							</c:if>
						</span>
					</p>
					<dl class="orderDetails-dl ">
						<dt class='fl'>交易内容：</dt>
						<dd class='fr'>
							${transOrder.goodsname}
							<c:if test="${transOrder.goodsdetail != null}">
								-${transOrder.goodsdetail}
							</c:if>
						</dd>
						<div class="clear"></div>
					</dl>

				</div>
				<div class="orderDetails-content1 orderDetails-content2">
					<h3 class="orderDetails-title">交易流水</h3>
					<p class="ftSize14">交易单号：<span class="color1C">${transOrder.userorderid }</span></p><!--我方生成订单号-->
					<table cellpadding="0" cellspacing="0" width="100%" class="orderDetailsTable">
						<c:if test="${transOrder.titanWithDrawDTO != null}">
							<tr>
								<td width="20%">
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanWithDrawDTO.createtime }"/>
								</td>
								<td width="7.64%">提现</td>
								<td width="22%"><span title="${transOrder.transTarget}">收款账户：${transOrder.transTarget}</span></td>
								<td width="35%">
									<font>
										<i><fmt:formatNumber value="${transOrder.titanWithDrawDTO.amount/100.0 }"
															 pattern="#,##0.00#"/></i>
										<span title="交易单${transOrder.titanWithDrawDTO.userorderid }提现">提现到${transOrder.titanWithDrawDTO.bankname}卡</span><!--融数落单单号-->
									</font>
								</td>
								<td>
									<c:if test="${transOrder.titanWithDrawDTO.status == 1}">
										处理中
									</c:if>
									<c:if test="${transOrder.titanWithDrawDTO.status == 2}">
										提现失败
									</c:if>
									<c:if test="${transOrder.titanWithDrawDTO.status == 3}">
										提现成功
									</c:if>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
