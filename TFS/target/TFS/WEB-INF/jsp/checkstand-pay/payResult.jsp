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
	   <c:choose>
	     <c:when test="${rechargeResultConfirmRequest.payStatus=='3' }">
		     <div class="TFSsu_contenttop c_green"> 
				<i class="TFSpay_ico TFSsu"></i>
				付款成功
			</div>
	     </c:when>
	     <c:otherwise>
	      <div class="TFSsu_contenttop c_red"> 
			<i class="TFSpay_ico TFSsb"></i>
			 付款失败
		  </div>
	     </c:otherwise>
	   </c:choose>
		
		<div class="TFSsu_contentcenter">
			<h4 class="f_14"><span class="TFSsu_sleft">交易流水号：</span><span class="c_999 f_14">${transOrderDTO.userorderid }</span></h4>
			<p class="f_14">
				<span class="TFSsu_sleft">收    款   方：</span>
				<span class="c_999">${financialOrganDTO.orgName }</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">实付金额：</span>
				<span class="c_999">${rechargeResultConfirmRequest.payAmount/100 }CNY</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">付款方式：</span>
				<span class="c_999">${payType}</span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">付款时间：</span>
				<span class="c_999" id="payOrderTime"></span>
			</p>
			<p class="f_14">
				<span class="TFSsu_sleft">付款状态：</span>
				<span class="c_999">${rechargeResultConfirmRequest.payMsg}</span>
			</p>
		</div>
		<div class="TFSsu_contentfoot">
			<span class="btn btn_exit J_exitKan">关闭</span>
		</div>
	</div>
	<jsp:include page="/comm/static-js.jsp"></jsp:include>
	<script>
	if('${rechargeResultConfirmRequest.orderPayTime}'.length==14){
		var date = '${rechargeResultConfirmRequest.orderPayTime}';
		var payDate = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)+" "+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12,14);
		$("#payOrderTime").text(payDate);
	}

	$('.J_exitKan').on('click',function(){
		window.close();
	});
		
	</script>
  </body>
</html>
