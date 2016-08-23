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
					<p class="ftSize14">
						<span  class="w_250 Province" style="width: 330px;margin-right:15px;">付款账户：
                        	<i class="recharge-colorRed" title="${transOrder.transTarget }">${transOrder.transTarget }</i>
						</span>
						<span class="w_160 Province">交易类型：提现</span>
						<span class="Province">
								交易状态：
							<c:if test="${transOrder.statusid == 8}">
								已成功
							</c:if>
							<c:if test="${transOrder.statusid == 9}">
								交易失败
							</c:if>
						</span>
					</p>
					<p class="ftSize14">
                        <span class="w_250" style="width: 346px;">
                        	交易创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.createtime}"/>
                        </span>
                        <c:if test="${not empty transOrder.creator }">
						<span class="w_160 Province" style="width: 240px">操作人：
							${transOrder.creator}
						</span>
						</c:if>
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
						<colgroup>
							<col width="150">
                        	<col width="60">
                        	<col width="200">
                        	<col width="200">
                        	<col width="100">
                        	<col width="">
	                    </colgroup>
						<c:if test="${transOrder.titanWithDrawDTO != null}">
							<tr>
								<td width="20%">
									<span>
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanWithDrawDTO.createtime }"/>
									</span>
								</td>
								<td width=""><span>提现</span></td>
								<td width=""><span style="width:180px" title="收款账户：${transOrder.transTarget}">收款账户：${transOrder.transTarget}</span></td>
								<td width="">
									<span style="width:120px" title="提现到${transOrder.titanWithDrawDTO.bankname}卡">提现到${transOrder.titanWithDrawDTO.bankname}卡</span><!--融数落单单号-->
								</td>
								<td >
									<span style="width:110px">
										<fmt:formatNumber value="${transOrder.titanWithDrawDTO.amount/100.0 }" pattern="#,##0.00#"/>
									</span>
								</td>
								<td>
									<span style="width:50px">
										<c:if test="${transOrder.titanWithDrawDTO.status == 1}">
											处理中
										</c:if>
										<c:if test="${transOrder.titanWithDrawDTO.status == 2}">
											提现失败
										</c:if>
										<c:if test="${transOrder.titanWithDrawDTO.status == 3}">
											提现成功
										</c:if>
									</span>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
