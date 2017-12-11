<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>泰坦金融设置-收银台设置</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
<body>
	<div id="scroll" >
		<div class="main_sell clearfix user_title">
			<div class="p_r30 p_l10">
				<span>泰坦金融&nbsp;-&nbsp;泰坦金融设置&nbsp;-&nbsp;收银台设置</span>
			</div>
		</div>
	</div>
	<div class="scroll_x hide t_85"></div>
	<div class="main_con p_t60" style="padding-top:60px;">
		<div class="TFS_set" style="padding:0 16px 0 0; ">
			<header>
				<div class="banner"><img src="<%=basePath%>/images/set_banner.jpg" alt=""/></div>
			</header>
			<div class="main">
				<div class="table">
					<div class="rate"><!--receivables-rate  payment-rate-->
						<div class="title">收款费率 <span class="h">（商家向分销商收钱时的手续费费率）</span></div>
						<table>
							<thead>
							<tr>
								<th width="20%">收款场景</th>
								<th width="37%">支付方式</th>
								<th width="12%">承担费率</th>
								<th width="16%">限时优惠</th>
								<th width="15%">是否开通在线付款</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${not empty rateInfoList }">
								<c:forEach items="${rateInfoList }" var="rateInfo" varStatus="status">
									<c:if test="${rateInfo.usedfor == '1' }">
										<tr>
											<td>SaaS分销工具收银台</td>
											<td>微信扫码支付、支付宝扫码支付、信用卡快捷支付、个人网银支付、企业网银支付、信贷支付等</td>
											<td>${rateInfo.standrate}%</td>
											<td>
												<c:if test="${rateInfo.executionrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.executionrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.executionrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.executionrate}元/笔</c:if>
												</c:if>
												<span class="h">截止至<fmt:formatDate pattern="yyyy-MM-dd" value="${rateInfo.expiration }"/></span></td>
											<td>
												<div class="sw" id="saasToggle" value = "1">
						                            <span class="sw_ctr">
						                                <span class="base"></span>
						                                <span class="slider"></span>
						                            </span>
												</div>
											</td>
										</tr>
									</c:if>
									<c:if test="${rateInfo.usedfor == '3' }">
										<tr>
											<td>商家联盟交易平台收银台</td>
											<td>微信扫码支付、支付宝扫码支付、信用卡快捷支付、个人网银支付、企业网银支付、信贷支付等</td>
											<td>${rateInfo.standrate}%</td>
											<td><c:if test="${rateInfo.executionrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.executionrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.executionrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.executionrate}元/笔</c:if>
												</c:if>
												<span class="h">截止至<fmt:formatDate pattern="yyyy-MM-dd" value="${rateInfo.expiration }"/></span></td>
											<td>
												<div class="sw" id="unionToggle" value = "2">
						                            <span class="sw_ctr">
						                                <span class="base"></span>
						                                <span class="slider"></span>
						                            </span>
												</div>
											</td>
										</tr>
									</c:if>
									<c:if test="${rateInfo.usedfor == '7' }">
										<tr>
											<td>TTM交易平台</td>
											<td>微信扫码支付、支付宝扫码支付、信用卡快捷支付、个人网银支付、企业网银支付、信贷支付等</td>
											<td>${rateInfo.standrate}%</td>
											<td>
												<c:if test="${rateInfo.executionrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.executionrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.executionrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.executionrate}元/笔</c:if>
												</c:if>
												<span class="h">截止至<fmt:formatDate pattern="yyyy-MM-dd" value="${rateInfo.expiration }"/></span></td>
											<td><span class="s">已开启</span>&nbsp;不支持关闭</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>
					<div class="rate">
						<div class="title">付款款费率（商家向供应商付钱时的手续费费率）</div>
						<table>
							<thead>
							<tr>
								<th width="20%">付款场景</th>
								<th width="37%">支付方式</th>
								<th width="12%">承担费率</th>
								<th width="16%">限时优惠</th>
								<th width="15%">是否开通在线付款</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${not empty rateInfoList }">
								<c:forEach items="${rateInfoList }" var="rateInfo" varStatus="status">
									<c:if test="${rateInfo.usedfor == '2' }">
										<tr>
											<td>财务付款给供应商</td>
											<td>微信扫码支付、支付宝扫码支付、信用卡快捷支付、个人网银支付、企业网银支付、信贷支付等</td>
											<td>${rateInfo.standrate}%</td>
											<td>
												<c:if test="${rateInfo.executionrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.executionrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.executionrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.executionrate}元/笔</c:if>
												</c:if>
												<span class="h">截止至<fmt:formatDate pattern="yyyy-MM-dd" value="${rateInfo.expiration }"/></span></td>
											<td><span class="s">已开启</span>&nbsp;不支持关闭</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>
							<tr>
								<td>余额转账</td>
								<td>账户余额</td>
								<td>免费</td>
								<td>免费</td>
								<td><span class="s">已开启</span>&nbsp;不支持关闭</td>
							</tr>
							<tr>
								<td>余额提现</td>
								<td>账户余额</td>
								<td>免费</td>
								<td>免费</td>
								<td><span class="s">已开启</span>&nbsp;不支持关闭</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	</div>
</div>
<div style="height:50px;"></div>

<script>

	$(document).ready(function(){
		if ("${saasOpen}" == 1) {
			$("#saasToggle").toggleClass("slide");
			$("#saasToggle").find(".base").css("backgroundColor", "#36c817").next().css("left", "14px");
		}
		if ("${unionOpen}" == 1){
			$("#unionToggle").toggleClass("slide");
			$("#unionToggle").find(".base").css("backgroundColor", "#36c817").next().css("left", "14px");
		}
	});

	$(function(){
		// 滑块
		$(".sw").on("click",function(){
			var cashierType =  $(this).attr("value");
			var obj = $(this);
			obj.toggleClass("slide");
			if($(this).hasClass("slide")){
				$.ajax({
					type: "post",
					url: "<%=basePath%>/setting/switch-cashier.action",
					data: {
						cashierType:cashierType,
						open:1
					},
					dataType: "json",
					success: function(data){
						if(data.code == 1){
							obj.find(".base").css("backgroundColor","#36c817").next().css("left","14px");
						} else {
							alert("设置失败,请联系管理员");
						}
					}
				});
			}else{
				$.ajax({
					type: "post",
					url: "<%=basePath%>/setting/switch-cashier.action",
					data: {
						cashierType:cashierType,
						open:0
					},
					dataType: "json",
					success: function(data){
						if(data.code == 1){
							obj.find(".base").css("backgroundColor","#fff").next().css("left","-4px");
						} else {
							alert("设置失败,请联系管理员");
						}
					}
				});

			}
		})
	})
</script>
</body>
</html>