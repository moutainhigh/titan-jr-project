<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>付款结果-泰坦金融</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
  
  <body>
    	<div class="TFSsuccess_top">
		<div class="TFSsu_topc">
			<h1 class="TFSsu_toptitle"><img src="<%=cssSaasPath %>/images/TFS/tfslogo.png" alt="" width="364" height="27"></h1>
		</div>
	</div>
	<div class="TFSsu_content">
		     <div class="TFSsu_contenttop c_green"> 
				<i class="TFSpay_ico TFSsu"></i>
				贷款申请成功
			</div>
		
		<div class="TFSsu_contentcenter">
			<h4 class="f_14"><span class="TFSsu_sleft">交易流水号：</span><span class="c_999 f_14">${transOrderDTO.userorderid }</span></h4>
			<p class="f_14">
				<span class="TFSsu_sleft">贷款单号：</span>
				<span class="c_999">${transOrderDTO.loanOrderNo}</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">实付金额：</span>
				<span class="c_999">${transOrderDTO.tradeamount/100 }CNY</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">付款方式：</span>
				<span class="c_999">运营贷</span>
			</p>
<!-- 			<p class="f_14"> -->
<!-- 				<span class="TFSsu_sleft">付款时间：</span> -->
<!-- 				<span class="c_999" id="payOrderTime"></span> -->
<!-- 			</p> -->
			<p class="f_14">
				<span class="TFSsu_sleft">付款状态：</span>
				<span class="c_999">成功</span>
			</p>
		</div>
		<div class="TFSsu_contentfoot">
			<span class="btn btn_exit J_exitKan">关闭</span>
		</div>
	</div>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script>

	$('.J_exitKan').on('click',function(){
		window.close();
	});
		
	</script>
  </body>
</html>
