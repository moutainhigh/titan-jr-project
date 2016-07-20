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
            <p><em><strong>订单金额：</strong><span class="recharge-colorRed fontSize24">
				<fmt:formatNumber value="${transOrder.tradeamount/100.0 }" pattern="#,##0.00#"/>
			</span>元</em>业务单号：${transOrder.businessordercode }</p>
            <div class="orderDetails-content">
                <div class="orderDetails-content1">
                    <h3 class="orderDetails-title">基础信息</h3>
                    <p class="ftSize14"><span class="w_250">付款方：
                        <i class="recharge-colorRed">${transOrder.transTarget }</i></span><span
                            class="w_160">交易类型：收款</span>交易状态：
                       <c:choose>
                              <c:when test="${transOrder.statusid == 8}">
                                                                                    已成功
                              </c:when>
                              <c:when test="${transOrder.statusid == 9}">
                                                                                   交易失败
                              </c:when>
                              <c:when test="${transOrder.statusid == 6}">
                                                                                    已冻结
                              </c:when>
                              <c:otherwise>
                                                                                   处理中
                              </c:otherwise>
                            </c:choose>
                    </p>
                    <p class="ftSize14">
                        <span class="w_250">
                        交易创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.createtime}"/>
                        </span>
                        <c:if test="${not empty transOrder.creator }"><span class="w_160">操作人：${transOrder.creator}</span></c:if>
                    </p>
                    <dl class="orderDetails-dl">
                        <dt class='fl'>交易内容：</dt>
                        <dd class='fr'>${transOrder.goodsname}
                            <c:if test="${transOrder.goodsdetail != null }">
                            -${transOrder.goodsdetail}
                            </c:if>
                        </dd>
                        <div class="clear"></div>
                    </dl>

                </div>
                <div class="orderDetails-content1 orderDetails-content2">
                    <h3 class="orderDetails-title">交易流水</h3>
                    <p class="ftSize14">交易单号：<span class="color1C">${transOrder.userorderid }</span></p>
                    <table cellpadding="0" cellspacing="0" width="100%" class="orderDetailsTable">
                        <c:if test="${transOrder.titanOrderPayDTO != null}">
                            <tr>
                                <td width="20%">
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                    value="${transOrder.titanOrderPayDTO.orderDate }"/></td>
                                <td width="7.64%">充值</td>
                                <td width="22%"><span>对方：${transOrder.transTarget}</span></td>
                                <td width="35%">
                                    <font>
                                        <i><fmt:formatNumber value="${transOrder.titanOrderPayDTO.orderAmount/100.0 }"
                                                             pattern="#,##0.00#"/></i>
                                        <span>交易单${transOrder.titanOrderPayDTO.orderNo }充值</span>
                                    </font>
                                </td>
                                <td>
                                    <c:if test="${transOrder.titanOrderPayDTO.reqstatus == 1}">
                                        处理中
                                    </c:if>
                                    <c:if test="${transOrder.titanOrderPayDTO.reqstatus == 2}">
                                        充值成功
                                    </c:if>
                                    <c:if test="${transOrder.titanOrderPayDTO.reqstatus == 3}">
                                        充值失败
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${transOrder.titanTransferDTO != null}">
                            <tr>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanTransferDTO.createtime }"/></td>
                                <td>转账</td>
                                <td><span>对方：${transOrder.transTarget}</span></td>
                                <td><font><i><fmt:formatNumber value="${transOrder.titanTransferDTO.amount / 100.0 }"
                                                               pattern="#,##0.00#"/></i>
                                    <span>财务单${transOrder.payorderno }支付</span></font></td>
                                <td>
                                    <c:if test="${transOrder.titanTransferDTO.status == 1}">
                                        处理中
                                    </c:if>
                                    <c:if test="${transOrder.titanTransferDTO.status == 2}">
                                        转账成功
                                    </c:if>
                                    <c:if test="${transOrder.titanTransferDTO.status == 3}">
                                        失败
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
