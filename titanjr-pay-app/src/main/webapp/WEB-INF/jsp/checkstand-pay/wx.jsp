<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>微信支付-泰坦金融</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/static-js.jsp"></jsp:include>
  <script type="text/javascript" src="<%=cssSaasPath%>/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/jquery.qrcode.min.js"></script> 
</head>
  <body>
  <c:if test="${not empty qrCode }">
    <div class="S_popup_title wx_ewm bgColor">
		<ul>
			<li class="P_centre" style="">付款：<span><b><fmt:formatNumber value="${qrCode.orderAmount/100 }" pattern="#,##0.00#"></fmt:formatNumber></b>元</span></li>
			<li class="P_right"></li>
		</ul>
	</div>
	<div class="S_popup_content adjust_c">
	
	<c:if test="${qrCode.payType=='32' }">
		<div class="wx_fk fl">
			<div class="wx_t fl"><img src="<%=cssSaasPath%>/images/TFS/alipay1.jpg" alt="" ></div>
			 <img id="qrcode" alt="支付宝扫描二维码支付" src="" class="fl">
			<div class="wx_c fl">扫码付款</div>		
		</div>
	</c:if>
	<c:if test="${qrCode.payType=='30' }">
		<div class="wx_fk fl">
				<div class="wx_t fl"><img src="<%=cssSaasPath%>/images/TFS/wx1.jpg" alt="" ></div>
			 <img id="qrcode" alt="微信扫描二维码支付" src="" class="fl">
			<div class="wx_c fl">扫码付款</div>				
		</div>
	</c:if>
		
		</div>
		<div class="wx_fkr fl">
			<c:if test="${qrCode.payType=='32' }">
				<img src="<%=cssSaasPath%>/images/TFS/alipay2.jpg" alt="">
					<!-- 支付宝扫描二维码支付 -->
			</c:if>
			<c:if test="${qrCode.payType=='30' }">
				<img src="<%=cssSaasPath%>/images/TFS/iphone.jpg" alt="">
				<!-- 微信扫描二维码支付 -->
			</c:if>
		
		</div>
		<div class="clear"></div>
		<div class="wx_close blue curpo">选择其它支付方式 >></div>
	</div>
  </c:if>
  
 <form action="<%=basePath%>/account/error_cashier.action" name="error_cashier"  id="error_cashier" method="post">
  <input name="msg" id="msg" type="hidden" value="${msg}"/>
</form>
	
	
	
<form action="<%=basePath%>/payment/payConfirmPage.action" id="confirmOrder1" method="post">
  <input name="orderNo" id="orderNo" value="${qrCode.orderNo}" type="hidden">
  <input name="expand" id="expand" type="hidden">
</form>
</body>
<script>
    var _orderNo = null;
	$("document").ready(function (){
		if('${result}'=="success"){
			_orderNo = '${qrCode.orderNo}';
			share2dImg('${qrCode.respJs}')
			closeWin('${qrCode.orderNo}');
		}else{
			$("#msg").val('${msg}');
			$("#error_cashier").submit();
		}
		 
	});
	
	function share2dImg(url){
		$("#qrcode").attr("src","<%=basePath%>/payment/wxPicture.shtml?url="+url);   
	}
	
	function timeOut(_this){
	    var i=60*15;
	    var interval=setInterval(function () {                
	         if(i>0){
	             _this.html( i +'s'); 
	             i--;
	         }else{
	            _this.html("重新获取");
	            clearInterval(interval);
	         }
	    }, 1000);
	}
	timeOut($('#Time'));
	
	function closeWin(_orderNo){
		var interval=setInterval(function () {   
			var status = confirmOrder(_orderNo);
			if(status =="success"||status=="fail"){
				 clearInterval(interval);
				 $("#confirmOrder1").submit();
			}else if(status=="no_effect" || status=="exception"){
				 clearInterval(interval);
			}else if(status=="delay"){
				clearInterval(interval);
				$("#expand").val("001_001");
				$("#confirmOrder1").submit();
			}
		},5000);
	}
	
	function confirmOrder(_orderNo){
		var status = null;
		$.ajax({
			 type: "post",
             url: "<%=basePath%>/payment/confirmOrder.action",
             data: {orderNo:_orderNo},
             dataType: "json",
             async:false,
             success: function(data){
            	 status = data.msg;
             }
		});
		return status;
	}
	
</script>

</html>
