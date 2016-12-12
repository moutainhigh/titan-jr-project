<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
	    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	    <jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	    <jsp:include page="/comm/static-js.jsp"></jsp:include>
	    
	    <style>
			.grey_bg,
			.grey_bg:hover,
			.grey_bg:active {
				background-color: #bcc8c9 !important;
				border: 1px solid #acbbbc !important;
				cursor: auto;
			}
		</style>
   </head>

	<body>
		<!--弹窗白色底-->
		<div class="other_popup">
			<div class="other_popup_title">
				<div class="other_popup_title2">
					<span class="visual"></span> 在线退款
				</div>
			</div>
			<div class="S_popup_Kan clearfix opaque" style="padding-bottom: 0px;margin-top: 53px;">
				<div class="gold_pay refund_content">

					<!--费率-->
					<div class="clearfix">
						<div class="fl w_800">
							<div class="goldpay_title">
								付款金额：
								<span class="gdt_red">16,032.00</span> 元 
							</div>
							<div class="refund_main">
								<ul>
									<li><span>原交易单号：</span>15664654131616165</li>
									<li><span>原交易名称：</span>TTM在线付款</li>
									<li><span>原交易内容：</span>格林豪泰大酒店<b class="mar_10">12月7日</b>张三</li>
									<li><span>原交易状态：</span>已完成</li>
									<li><span>泰坦金融总余额：</span><i class="colorRed">800.00</i>元<b class="mar_10">(请先充值后进行退款)</b></li>
								</ul>
							</div>
							
						</div>
						<div class="TFS_withdrawBoxR fr">
							<h3>温馨提示</h3>
							<div class="TFS_withdrawBoxR_content">
								<p>手续费：</p>
								<h4>
									<span>0</span>
									元
								</h4>
							</div>
						</div>
					</div>
	
				<p class="rtn_item">
					<button class="sure_btn">确定</button><button class="def_btn">取消</button>
				</p>
								
			</div>
		</div>
		<jsp:include page="/comm/static-js.jsp"></jsp:include>
	</body>

</html>