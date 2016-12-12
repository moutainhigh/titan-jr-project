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
								<span class="gdt_red"><fmt:parseNumber value="${(transOrderDTO.tradeamount+transOrderDTO.receivedfee)/100}" pattern="#,##0.00#"></fmt:parseNumber></span> 元 
							</div>
							<div class="refund_main">
								<ul>
									<li><span>原交易单号：</span>${transOrderDTO.payorderno }</li>
									<li><span>原交易名称：</span>${transOrderDTO.goodsname }</li>
									<li><span>原交易内容：</span>${transOrderDTO.goodsdetail }</li>
									<li><span>原交易状态：</span>
									  <c:choose>
									    <c:when test="${transOrderDTO.statusid=='8'}">
									            已完成
									    </c:when>
									    <c:when test="${transOrderDTO.statusid=='6'}">
									            已冻结
									    </c:when>
									    <c:otherwise>
									            失败
									    </c:otherwise>
									  </c:choose>
									
									</li>
									<li><span>泰坦金融总余额：</span><i class="colorRed"><fmt:parseNumber  pattern="#,##0.00#" value="${accountBalance/100}"></fmt:parseNumber></i>元<b class="mar_10 balanceTip">(请先充值后进行退款)</b></li>
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
					<button class="sure_btn,J_password" >确定</button><button class="def_btn">取消</button>
				</p>
								
			</div>
		</div>
		<jsp:include page="/comm/static-js.jsp"></jsp:include>
		<script type="text/javascript" src="../js/common.js"></script>
		<script>
		  var refundObj = {
				  refundAmount:function(){
					  return 
				  },
				  enoughBalance:function(){
					  var amount  = sub('${balance}', '${(transOrderDTO.tradeamount+transOrderDTO.receivedfee)}');
					  if(amount<0){
				    	  $(".balanceTip").show();
				    	  $(".sure_btn").attr("disabled","disabled");
				      }else{
				    	  $(".balanceTip").hide();
				      }
				  
				  },
				  payPasswordObj:function(){
					  $(".J_password").bind("click",function(){
						  var url = "<%=basePath%>";
						  var flag = payPassdword.show_psd(url, {
							  tfsUserid:refundRequest.payPassword
						  });
						  if(flag){
							  
						  }
						  
					  })
				  },
				  submitObj:function(){
					  
					  $.ajax({
						 url:"<%=basePath%>/refund/orderRefund.action",
						 type:"post",
						 dataType:"json",
						 data:{
							 tfsUserid:RefundRequest.tfsUserid,
							 
						 },
						 success:function(){
							 
						 }
						  
					  });
				  }
		  };
		  
		  refundObj.enoughBalance();
		  refundObj.submitObj();
		  
		  
		  
		  
		</script>
		
	</body>

</html>