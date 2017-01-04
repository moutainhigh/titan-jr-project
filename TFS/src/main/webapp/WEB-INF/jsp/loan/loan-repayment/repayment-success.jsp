<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>还款成功</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body>
	<div class="TFSsuccess_top">
		<div class="TFSsu_topc">
			<h1 class="TFSsu_toptitle"><img src="<%=cssSaasPath %>/images/TFS/tfslogo1.png" alt="" width="364" height="27"></h1>
		</div>
	</div>
	<div class="TFSsu_content">
		<div class="TFSsu_contenttop c_green"> 
			<i class="TFSpay_ico TFSsu"></i>
			还款成功
		</div>
		<div class="TFSsu_contentcenter">
			<h4 class="f_14"><span class="TFSsu_sleft">贷款单号：</span><span class="c_999 f_14">${repaymentOrderNo}</span></h4>
			<p class="f_14">
				<span class="TFSsu_sleft">还    款   方：</span>
				<span class="c_999">${repaymentUserName}</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">还款金额：</span>
				<span class="c_999">${repaymentAmount} CNY</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">还款方式：</span>
				<span class="c_999">主动还款</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">还款时间：</span>
				<span class="c_999">${repaymentDate}</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">还款状态：</span>
				<span class="c_999">还款成功</span>
			</p>
		</div>
		<div class="TFSsu_contentfoot">
			<span class="btn btn_exit J_exitKan">关闭</span>
		</div>
	</div>
</body>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">


$('.J_exitKan').on('click',function(){
	
	window.location.href="<%=basePath %>/loan/credit/checkCreditStatus.shtml";
	
});
	
</script>
</html>
