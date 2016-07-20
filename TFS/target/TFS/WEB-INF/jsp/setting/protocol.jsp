<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>服务协议-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
 <body>
	<div id="scroll" >
		<div class="main_sell clearfix user_title">
			<div class="p_r30 p_l10">
				<span>泰坦金融&nbsp;-&nbsp;泰坦金融设置&nbsp;-&nbsp;泰坦金融协议</span>
			</div>
		</div>
	</div>
	<div class="scroll_x hide t_56"></div>
	<div class="main_con p_t56">
		<div class="TFS_set scoll" style="padding:0 16px 0 0; background: #fdfbfb; border: #cdcdcd 1px solid; margin-right: 16px;">
			<div class="TFS_deal">
				<div class="TFS_dealtitle">泰坦云金融服务协议</div>
				<div class="TFS_dealcon">
					<jsp:include page="/comm/content.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div class="TFS_agbuton">
		<span class="btn deepblue mr15" onclick="window.open('http://static.fangcang.com/static/tfs/titan_finance_protocol.doc')">下载</span>
		<span class="btn p_lr30" onclick="window.print()">打印</span>
	</div>
</body>
</html>
