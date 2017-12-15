<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>个人账户资料-泰坦钱包</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>


<body style="min-width: 1300px;" class="bg" >
<!-- 头部 -->
<jsp:include page="/comm/header.jsp"></jsp:include>
<div class="h_90"></div>
<!-- 内容 -->
<div class="w_1200 ">
	<div class="overview clearfix m_t20 o_set">
		<jsp:include page="../slidemenu/jr-setting-menu.jsp">
			<jsp:param value="fee" name="menu"/>
		</jsp:include>
		<div class="s_right clearfix">
			<header>
				<div class="route"><span>我的账户 > 泰坦钱包设置 ></span> 收银台设置</div>
				<div class="banner"><img src="<%=basePath%>/images/set_banner.jpg" alt=""/></div>
			</header>
			<div class="main">
				<div class="table">
					<div class="rate"><!--receivables-rate  payment-rate-->
						<div class="title">收款费率 <span class="h">（商家向分销商收钱时的手续费费率）</span></div>
						<table>
							<thead>
							<tr>
								<th width="198">收款场景</th>
								<th width="362">支付方式</th>
								<th width="114">承担费率</th>
								<th width="158">限时优惠</th>
								<th width="145">是否开通在线付款</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${not empty rateInfoList }">
								<c:forEach items="${rateInfoList }" var="rateInfo" varStatus="status">
									<c:if test="${rateInfo.usedfor == '1' }">
										<tr>
											<td>SaaS分销工具收银台</td>
											<td>微信扫码支付、支付宝扫码支付、信用卡快捷支付、个人网银支付、企业网银支付、信贷支付等</td>
											<td>
												<c:if test="${rateInfo.standrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.standrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.standrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.standrate}元/笔</c:if>
												</c:if>
											</td>
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
											<td>
												<c:if test="${rateInfo.standrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.standrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.standrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.standrate}元/笔</c:if>
												</c:if>
											</td>
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
											<td>
												<c:if test="${rateInfo.standrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.standrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.standrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.standrate}元/笔</c:if>
												</c:if>
											</td>
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
								<th width="198">付款场景</th>
								<th width="362">支付方式</th>
								<th width="114">承担费率</th>
								<th width="158">限时优惠</th>
								<th width="145">是否开通在线付款</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${not empty rateInfoList }">
								<c:forEach items="${rateInfoList }" var="rateInfo" varStatus="status">
									<c:if test="${rateInfo.usedfor == '2' }">
										<tr>
											<td>财务付款给供应商</td>
											<td>微信扫码支付、支付宝扫码支付、信用卡快捷支付、个人网银支付、企业网银支付、信贷支付等</td>
											<td>
												<c:if test="${rateInfo.standrate == 0.0}">免费</c:if>
												<c:if test="${rateInfo.standrate != 0.0}">
													<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.standrate}%</c:if>
													<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.standrate}元/笔</c:if>
												</c:if>
											</td>
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
								<td>
								    <c:if test="${rateInfo.standrate == 0.0}">免费</c:if>
									<c:if test="${rateInfo.standrate != 0.0}">
										<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.standrate}%</c:if>
										<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.standrate}元/笔</c:if>
									</c:if>
								</td>
								<td>
									<c:if test="${rateInfo.executionrate == 0.0}">免费</c:if>
									<c:if test="${rateInfo.executionrate != 0.0}">
										<c:if test="${rateInfo.ratetype == 1 }">${rateInfo.executionrate}%</c:if>
										<c:if test="${rateInfo.ratetype == 2 }">${rateInfo.executionrate}元/笔</c:if>
									</c:if>
									<span class="h">截止至<fmt:formatDate pattern="yyyy-MM-dd" value="${rateInfo.expiration }"/></span>
								</td>
								<td><span class="s">已开启</span>&nbsp;不支持关闭</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="h_90"></div>
		</div>
	</div>
</div>
<div class="h_40"></div>
<!-- 版权 -->
<div class="footer1">
	<div class="f_bd">
		<div class="fl">
			Copyright © 2012-2016, fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号	
		</div>
		<div class="fl f_bd_r">
		<script charset="utf-8" type="text/javascript" src="http://szcert.ebs.org.cn/govicon.js?id=78ccac39-a97a-452c-9f81-162cd840cff6&amp;width=130&amp;height=50&amp;type=2" id="ebsgovicon"></script>
		</div>
	</div>	
</div>

<script type="text/javascript">

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

	// 弹窗
	$(function(){
		// 滑块
		$(".sw").on("click",function(){
			var cashierType =  $(this).attr("value");
			var obj = $(this);
			obj.toggleClass("slide");
			if($(this).hasClass("slide")){
				$.ajax({
					type: "post",
					url: "<%=basePath%>" + "/setting/switch-cashier.shtml",
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
					url: "<%=basePath%>" + "/setting/switch-cashier.shtml",
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
		});

		//我的账户
		$('.hr_login').hover(function(){
			$(this).find('.hrl_ul').removeClass('dn');
			$(this).find('.hrl_hover').addClass('l_red');
		},function(){
			$(this).find('.hrl_ul').addClass('dn');
			$(this).find('.hrl_hover').removeClass('l_red');
		});

	})

</script>

</body>
</html>
