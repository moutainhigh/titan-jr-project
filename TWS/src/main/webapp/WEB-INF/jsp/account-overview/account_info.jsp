<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="o_crumbs">账户名称/泰坦码：${orgDTO.orgname}/${orgDTO.titanCode}</div>
<div class="MyAssets_chart">
	<div class="MyAssets_code"><img src="<%=cssWalletPath%>/images/tu09.jpg"></div>
	<div class="MyAssets_chart_list">
		<div class="MyAssets_chart_list01 fl">
			<h3>我的资产</h3>
			<h4> <i class="MyAssets_greenNotice"  id="amountSpan">加载中...</i>
				元
			</h4>
			<table cellpadding="0" cellspacing="0" class="MyAssets_chart_tab01">
				<tr>
					<td width="75" class="MyAssets_chart_td01">
							<canvas id="can1" class="canvasBox" height="60" width="60"></canvas>
							<span></span>
					</td>
					<td>
						<p style="width: 450px;display: block;">
							<span>现金可用余额：<i id="balanceusableSpan">加载中...</i></span>
							<a href="javascript:void(0)" class="blue decorationUnderline rechargeBtn">充值</a>
							<a href="javascript:void(0)" class="blue decorationUnderline withdrawBtn" >提现</a>
						</p>
						<p>
							<span>
								现金冻结余额：<i id="balancefrozonSpan">加载中...</i>
								<i class="MyAssets_noticeIco" title="交易平台在线收款默认为担保支付，在订单离店日后1天款项自动解冻"></i>
							</span>
							<a href="<%=basePath%>/account/freeze-detail-page.shtml" class="blue decorationUnderline">详情</a>
						</p>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>