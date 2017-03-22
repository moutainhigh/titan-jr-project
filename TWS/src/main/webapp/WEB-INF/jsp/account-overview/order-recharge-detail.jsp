<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>充值记录-泰坦钱包</title>
		<jsp:include page="/comm/static-resource.jsp"></jsp:include>
		<jsp:include page="/comm/static-js.jsp"></jsp:include>
	</head>
	<body style="min-width: 1300px;" class="bg" >
		<!-- 头部 -->
		<jsp:include page="order-head.jsp"/>
		<!-- 内容 -->
		<div class="w_1200 ">
			<div class="overview clearfix m_t20 ">
				 <div class="recharge-record-box o_detail">
			<p class="f_14"><em class="w_360">
					<strong class="f_18">充值金额：</strong><span class="recharge-colorRed fontSize24">
				<fmt:formatNumber value="${transOrder.tradeamount/100.0 }" pattern="#,##0.00#"/>
			</span><i class="f_12">元</i></em></p>
			<div class="orderDetails-content">
				<div class="orderDetails-content1">
					<h3 class="orderDetails-title">基础信息</h3>
					<p class="ftSize14">
						<span class="w_250 Province" style="width: 330px;margin-right:15px;">
							收款账户： <i title="${transOrder.transTarget }" class="recharge-colorRed">${transOrder.transTarget }</i>
						</span>
						<span class="w_180">交易类型：充值</span>
						<span class="Province">交易状态：
							<c:if test="${transOrder.statusid == 8}">
								已成功
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
					<dl class="orderDetails-dl">
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
					<p class="ftSize14">流水号：<span class="color1C">${transOrder.userorderid }</span></p><!--我方生成订单号-->

					<table cellpadding="0" cellspacing="0" width="100%" class="orderDetailsTable">
						<c:if test="${transOrder.titanOrderPayDTO != null}">
							<tr>
								<td style="width:160px"><span>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanOrderPayDTO.orderDate }"/>
								</span>
								</td>
								<td ><span>充值</span></td>
								<td ><span style="width:180px" title="付款账户：${transOrder.transTarget}">付款账户：${transOrder.transTarget}</span></td>
								<td >
									<span style="width:180px" title="交易单号${transOrder.titanOrderPayDTO.orderNo }充值">交易单号${transOrder.titanOrderPayDTO.orderNo }充值</span><!--融数落单单号-->
								</td>

								<td>
									<span style="width:110px">
										<fmt:formatNumber value="${transOrder.titanOrderPayDTO.orderAmount/100.0 }" pattern="#,##0.00#"/>
									</span>
								</td>
								<td><span style="width:80px"> 
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
					</table>
				</div>
			</div>
		</div>
			</div>
		</div>
		<div class="h_40"></div>
		<!-- 版权 -->
	      <jsp:include page="/comm/foot-line.jsp"/>
	</body>
</html>