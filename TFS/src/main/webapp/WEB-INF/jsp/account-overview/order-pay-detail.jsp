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
			</span>元</em>业务单号：${transOrder.businessordercode }</p><!--业务系统单号-->
            <div class="orderDetails-content">
                <div class="orderDetails-content1">
                    <h3 class="orderDetails-title">基础信息</h3>
                    <p class="ftSize14">
                        <span class="w_250 Province" style="width: 330px;margin-right:15px;">收款方：
                            <i class="recharge-colorRed" title="${transOrder.transTarget }">${transOrder.transTarget }</i>
                        </span>
                        <span class="w_160 Province">交易类型：付款</span>
                        <span class="Province">交易状态：
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
                                <c:when test="${transOrder.statusid == 12 }">
                                退款中
                                </c:when>
                                <c:when test="${transOrder.statusid == 13 }">
                                 退款成功
                                </c:when>
                                <c:when test="${transOrder.statusid == 14 }">
                                退款失败
                                </c:when>
                                <c:otherwise>
                                    处理中
                                </c:otherwise>
                            </c:choose>
			            </span>
                    </p>

                    <p class="ftSize14">
                        <span class="w_250" style="width: 346px;">
                        交易创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.createtime}"/>
                        </span>
                        <c:if test="${not empty transOrder.creator}"> 
                          <span class="w_160 Province" style="width: 240px">
                                                                      操作人：${transOrder.creator}
                          </span>
                        </c:if>
                    </p>
                    <dl class="orderDetails-dl">
                        <dt class='fl'>交易内容：</dt>
                        <dd class='fr'>${transOrder.goodsname}
                            <c:if test="${not empty transOrder.goodsdetail }">
                                -${transOrder.goodsdetail}
                            </c:if></dd>
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
                            <col width="140">
                            <col width="200">
                            <col width="100">
                            <col width="80">
                            <col width="70">
                        </colgroup>

                        <c:if test="${transOrder.titanOrderPayDTO != null}">
                            <tr>
                                <td width="">
                                    <span>
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanOrderPayDTO.orderDate }"/>
                                    </span>
                                </td>

                                <td width=""><span>充值</span></td>

                                <td width="">
                                    <span title="对方：${organ.orgName}">对方：${organ.orgName}</span>
                                </td>

                                <td width="">
                                    <span title="交易单${transOrder.titanOrderPayDTO.orderNo }充值"> 交易单${transOrder.titanOrderPayDTO.orderNo }充值</span><!--融数落单单号-->
                                </td>

                                <td width="">
                                    <span>+<fmt:formatNumber value="${transOrder.titanOrderPayDTO.orderAmount/100.0 }" pattern="#,##0.00#"/></span>
                                </td>
                                  <td width="40px">
                                </td>
                                <td><span>
                                    <c:if test="${transOrder.titanOrderPayDTO.reqstatus == 1}">
                                        处理中
                                    </c:if>
                                    <c:if test="${transOrder.titanOrderPayDTO.reqstatus == 2}">
                                        充值成功
                                    </c:if>
                                    <c:if test="${transOrder.titanOrderPayDTO.reqstatus == 3}">
                                        充值失败
                                    </c:if>
                                    </span>
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${transOrder.titanTransferDTO != null}">
                            <tr>
                                <td>
                                    <span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanTransferDTO.createtime }"/></span>
                                </td>
                                <td><span>转账</span></td>

                                <td><span title="对方：${transOrder.transTarget}">对方：${transOrder.transTarget}</span></td>

                                <td>
                                    <span  title="财务单${transOrder.payorderno }支付">财务单${transOrder.payorderno }支付</span>
                                </td><!--房仓财务系统单号-->
                                <td><span>-<fmt:formatNumber value="${(transOrder.titanTransferDTO.amount) / 100.0 }" pattern="#,##0.00#"/></span></td>
                                <td>
                                	<c:if test="${(transOrder.receivedfee) / 100.0 !=0.0 && (transOrder.receivedfee) / 100.0 !=0.00}">
                                	  <span>-<fmt:formatNumber value="${(transOrder.receivedfee) / 100.0 }" pattern="#,##0.00#"/></span>
                                	</c:if>
                                </td>
                                <td>
                                <span>
                                    <c:if test="${transOrder.titanTransferDTO.status == 1}">
                                        处理中
                                    </c:if>
                                    <c:if test="${transOrder.titanTransferDTO.status == 2}">
                                        转账成功
                                    </c:if>
                                    <c:if test="${transOrder.titanTransferDTO.status == 3}">
                                        失败
                                    </c:if></span>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${transOrder.refundDTO != null}">
                        	<tr>
                                <td>
                                    <span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.refundDTO.createtime }"/></span>
                                </td>
                                <td><span>退款</span></td>

                                <td><span  title="退款人：${transOrder.refundDTO.creator}">退款人：${transOrder.refundDTO.creator}</span></td>

                                <td>
                                    <span title="财务单${transOrder.payorderno }退款">财务单${transOrder.payorderno }退款</span>
                                </td><!--房仓财务系统单号-->
                                <td><span>+<fmt:formatNumber value="${(transOrder.refundDTO.transferAmount) / 100.0 }" pattern="#,##0.00#"/></span></td>
                                <td>
                                  <c:if test="${(transOrder.refundDTO.fee) / 100.0 !=0.0 && (transOrder.refundDTO.fee) / 100.0 !=0.00}">
                                   <span>+<fmt:formatNumber value="${(transOrder.refundDTO.fee) / 100.0 }" pattern="#,##0.00#"/></span>
                                  </c:if>
                                </td>
                                <td>
                                <span>
                                     <c:if test="${transOrder.refundDTO.status == 0}">
                                        退款处理中
                                    </c:if>
                                    <c:if test="${transOrder.refundDTO.status == 1}">
                                        退款审核失败
                                    </c:if>
                                    <c:if test="${transOrder.refundDTO.status == 2}">
                                        退款成功
                                    </c:if>
                                    <c:if test="${transOrder.refundDTO.status == 3}">
                                        退款失败
                                    </c:if>
                                    <c:if test="${transOrder.refundDTO.status == 4}">
                                        退款冲销
                                    </c:if></span>
                                </td>
                            </tr>
                        </c:if>
                        
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
