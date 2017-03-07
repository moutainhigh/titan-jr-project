<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>泰坦钱包</title>
		<jsp:include page="/comm/static-resource.jsp"></jsp:include>
		<jsp:include page="/comm/static-js.jsp"></jsp:include>
	</head>
	<body style="min-width: 1300px;" class="bg" >
		<!-- 头部 -->
		<jsp:include page="order-head.jsp"/>
		<!-- 内容 -->
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
		        	                <col width="200">
		                	        <col width="200">
		                            <col width="100">
			                        <col width="">
		                        </colgroup>
		
		                        <c:if test="${transOrder.titanWithDrawDTO != null}">
		                            <tr>
		                                <td width="">
		                                    <span>
		                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanWithDrawDTO.createtime }"/>
		                                    </span>
		                                </td>
		
		                                <td width=""><span>提现</span></td>
		
		                                <td width="">
		                                    <span style="width:240px" title="收款账户：${transOrder.transTarget}">收款账户：${transOrder.transTarget}</span>
		                                </td>
		
		                                <td width="">
		                                
		                               		<font>
												<i><fmt:formatNumber value="${transOrder.titanWithDrawDTO.amount/100.0 }" pattern="#,##0.00#"/></i>
												<span style="width:280px">提现到${transOrder.titanWithDrawDTO.bankname}卡</span>
											</font>
		                                </td>
		                                <td>
		                                	<span>
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
		
		<div class="w_1200 ">
			<div class="overview clearfix m_t20 ">
				<div class="recharge-record-box o_detail">
				<p class="f_14"><em class="w_360">
					<strong class="f_18">订单金额：</strong>
					<span class="recharge-colorRed fontSize24">
						<fmt:formatNumber value="${transOrder.tradeamount/100.0 }" pattern="#,##0.00#"/>
					</span><i class="f_12">元</i></em>业务单号：${transOrder.businessordercode }</p>
						<div class="orderDetails-content">
							<div class="orderDetails-content1">
								<h3 class="orderDetails-title">基础信息</h3>
								<p class="ftSize14"><span class="w_280">分销商：<i class="recharge-colorRed">${transOrder.transTarget }</i></span><span class="w_180">交易类型：付款</span>交易状态：已冻结</p>
								<p>交易创建时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.createtime}"/></p>
								<dl class="orderDetails-dl">
									<dt class="fl">交易内容：</dt>
									<dd class="fl">
										${transOrder.goodsname}
			                            <c:if test="${not empty transOrder.goodsdetail }">
			                                -${transOrder.goodsdetail}
			                            </c:if>
									</dd>
									<div class="clear"></div>
								</dl>
			
							</div>
							<div class="orderDetails-content1 orderDetails-content2">
								<h3 class="orderDetails-title">交易流水</h3>
								<p class="ftSize14">交易单：<span class="color1C">${transOrder.userorderid }</span></p>
								<table cellpadding="0" cellspacing="0" width="100%" class="orderDetailsTable">
									<colgroup>
										<col width="19%">
										<col width="8%">
										<col width="23%">
										<col width="34%">
										<col width="">
									</colgroup>
									<c:if test="${transOrder.titanWithDrawDTO != null}">
			                            <tr>
			                                <td width="">
			                                    <span>
			                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${transOrder.titanWithDrawDTO.createtime }"/>
			                                    </span>
			                                </td>
			
			                                <td width=""><span>提现</span></td>
			
			                                <td width="">
			                                    <span style="width:240px" title="收款账户：${transOrder.transTarget}">收款账户：${transOrder.transTarget}</span>
			                                </td>
			
			                                <td width="">
			                                
			                               		<font>
													<i><fmt:formatNumber value="${transOrder.titanWithDrawDTO.amount/100.0 }" pattern="#,##0.00#"/></i>
													<span style="width:280px">提现到${transOrder.titanWithDrawDTO.bankname}卡</span>
												</font>
			                                </td>
			                                <td>
			                                	<span>
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
						<div class="orderDetails-notice">
							<span>账户余额充足时系统将会自动扣取订单金额</span><span>充值成功后系统可能不会立即扣取，请留意查看！</span>
							<h4><a href="<%=basePath%>/account/goto_cashierDesk.shtml?payType=7" target="_blank" class="J_rechargeBtn blue decorationUnderline">去充值 >></a></h4>
						</div>
				</div>
			</div>
		</div>
		<div class="h_40"></div>
		<!-- 版权 -->
	      <jsp:include page="/comm/foot-line.jsp"/>
	</body>
</html>
