/**
 * create by jerry  20170904
 */

//余额支付
function balancePayment() {debugger;
	isShowVeil("#Veil","show");// 显示遮罩层
	top.F.loading.show();
	$.ajax({//支付页面
		type : "post",
		url : "../payment/showTitanPayPage.action",
           data: cashierData.getCommonPayData(),
           dataType: "json",
           success: function (data) {
               //如果ajax请求成功则显示回调页面
               toResultPage(data);
           },complete:function(){
           	   
           }
     });
}
//余额支付结果页面
function toResultPage(data){debugger;
 	if(data.result == "0"){
		$("#check_orderNo").val(data.data);
		$("#payConfirmPage").submit();
	 }else{
		  //new top.Tip({msg: data.resultMsg, type: 2, timer: 2000});
		  alert(data.resultMsg);
		  top.F.loading.hide();
   	   	  isShowVeil("#Veil","hide");
	 }
}

//第三方扫码支付
function qrPayment(bankInfo){
 	$.ajax({
 		type: "post",
        dataType : 'json',
     	data: cashierData.getCommonPayData(),
        url : '../payment/qrCodePaymentUpgrade.action',			
        success : function(data){
        	if(data.result == true){debugger;
        		if(bankInfo =='wx'){
        			$("#wx_pay_amount").text((data.qrCodeDTO.orderAmount)/100);
            		$("#wx_qrcode").attr("src","../payment/wxPicture.shtml?url="+data.qrCodeDTO.respJs);
            		//微信二维码弹窗
                    $(".wx").show();
        		}else if(bankInfo =='alipay'){
        			$("#ali_pay_amount").text((data.qrCodeDTO.orderAmount)/100);
            		$("#ali_qrcode").attr("src","../payment/wxPicture.shtml?url="+data.qrCodeDTO.respJs);
            		//支付宝二维码弹窗
            		$(".zfb").show();
        		}
        		top.F.loading.hide();
        		checkOrderPayStatus(data.qrCodeDTO.orderNo)
        	}else{
        		if($.trim(data.returnMessage).length > 0){
            		$("#error_cashier_msg").val(data.returnMessage);
        		}else{
        			$("#error_cashier_msg").val(data);
        		}
        		$("#error_cashier").submit();
        	}
        },complete:function(){
        	
        }
    });
 }
var interval;
//轮询订单支付状态
function checkOrderPayStatus(_orderNo){debugger;
	$("#qr_orderNo_hid").val(_orderNo);
	interval=setInterval(function () {   
		var status = confirmOrder(_orderNo);
		if(status =="success"||status=="fail"){
			 clearInterval(interval);
			 $("#check_orderNo").val(_orderNo);
			 $("#payConfirmPage").submit();
		}else if(status=="no_effect" || status=="exception"){
			 clearInterval(interval);
		}else if(status=="delay"){
			clearInterval(interval);
			$("#check_orderNo").val(_orderNo);
			$("#check_expand").val("001_001");
			$("#payConfirmPage").submit();
		}
	},1500);
}
//关闭二维码后进行最后一次订单支付确认
function qrPayClose(){
	clearInterval(interval);
	setTimeout(function(){
		var status = confirmOrder($("#qr_orderNo_hid").val());
		if(status =="success"||status=="fail"){
			$("#check_orderNo").val(_orderNo);
			$("#payConfirmPage").submit();
		}else if(status =="delay"){
			$("#check_orderNo").val(_orderNo);
			$("#check_expand").val("001_001");
			$("#payConfirmPage").submit();
		}
	 }, 3000);
}
function confirmOrder(_orderNo){
	var status = null;
	$.ajax({
		 type: "post",
         url: "../payment/confirmOrder.action",
         data: {orderNo:_orderNo},
         dataType: "json",
         async:false,
         success: function(data){
        	 status = data.msg;
         }
	});
	return status;
}

//常用支付方式--快捷支付
function quickPay_history(){
	//调网关支付接口，发送验证码
	$.ajax({
		type : "post",
		url : "../payment/quickPayRecharge.action",
        data: cashierData.getQuickPayData(),
        dataType: "json",
        success: function (data) {
           top.F.loading.hide();
    	   if(data.success == true){
    		   $(".payment-verification").show();//输入验证码弹窗
        	   $("#rsOrderNo").val(data.orderNo);
        	   $("#safe_payerphone").text(cashierData.payerPhone);
    	   }else{
    		   if($.trim(data.errMsg).length > 0){
    			   $("#error_cashier_msg").val(data.errMsg);
	       		}else{
	       			$("#error_cashier_msg").val(data);
	       		}
    		   $("#error_cashier").submit();
    	   }
        }
    });
}
//常用支付方式--快捷支付--确认充值
function confirmRecharge(){
	$.ajax({
		type : "post",
		url : "../quickPay/quickPayRecharge.action",
           data: {
        		busiCode: "109",
        		signType: "1",
        		version: "v1.1",
    			merchantNo: "M000016",
    			orderNo: $("#rsOrderNo").val(),
    			payType: "41",
    			checkCode: $(".payment-verification input").val()
           },
           dataType: "json",
           success: function (data) {debugger;
        	   if(data.success == true){
       				checkOrderPayStatus($("#rsOrderNo").val());
        	   }else{
        		   alert(data.errMsg);
        		   top.F.loading.hide();
        		   isShowVeil("#Veil","hide");
        	   }
           }
     });
}

function validQuickPayInfo(){
	
	if($("#quick_cardType_hid").val() == '10'){
		var payerName = $("#quick_payerName_deposit").val();
		var idCode = $("#quick_idCode_deposit").val();
		var payerPhone = $("#quick_payerPhone_deposit").val();
		
		if(payerName.length <= 0){
			alert("请输入持卡人姓名");
			return false;
		}
		if(idCode.length <= 0){
			alert("请输入证件号码");
			return false;
		}
		if(payerPhone.length <= 0){
			alert("请输入银行预留手机号");
			return false;
		}
	}else{
		var payerName = $("#quick_payerName_credit").val();
		var idCode = $("#quick_idCode_credit").val();
		var payerPhone = $("#quick_payerPhone_credit").val();
		var validthruMonth = $("#quick_validthruMonth_credit").text();
		var validthruYear = $("#quick_validthruYear_credit").text();
		var safetyCode = $("#quick_safetyCode_credit").val();
		
		if(payerName.length <= 0){
			alert("请输入持卡人姓名");
			return false;
		}
		if(idCode.length <= 0){
			alert("请输入证件号码");
			return false;
		}
		if(validthruMonth == "请选择" || validthruYear == "请选择"){
			alert("请选择信用卡有效期");
			return false;
		}
		if(safetyCode.length <= 0){
			alert("请输入信用卡安全码");
			return false;
		}
		if(payerPhone.length <= 0){
			alert("请输入银行预留手机号");
			return false;
		}
	}
	
	return true;
}

var isFirstSend = true;
var btn = true;
var interval_countDown;
//添加快捷支付--发送验证码
function sendVierfyCode(_button){debugger;

	if(btn){
	    btn = false;
		var num = 60;
		//60s倒计时设置
		interval_countDown=setInterval(function(){
		    num--;
		    if(num < 0){
		        btn = true;
		        isFirstSend = false;
		        clearInterval(interval_countDown);
		        $(_button).text("重发验证码").css("color","#ccc");
		        return;
		    }
		    $(_button).text(num + "s").css("color","#bbb");
		},1000)
		
	    var param = {};
		if($("#quick_cardType_hid").val() == '10'){
			param = cashierData.getRechargeData_deposit();
		}else{
			param = cashierData.getRechargeData_credit();
		}
		
		if(isFirstSend){//第一次点发送验证码
			$.ajax({
				type : "post",
				url : "../payment/quickPayRecharge.action",
		        data: param,
		        dataType: "json",
		        success: function (data) {
		  	        if(data.isSuccess == true){
		  	        	$("#quick_rsOrder").val(data.orderNo);
		  	        }else{
		  	        	btn = true;
		  	        	clearInterval(interval_countDown);
		  	        	$(_button).text("发送验证码").css("color","#ccc");
		  	        	if($.trim(data.errMsg).length > 0){
		  	        		alert(data.errMsg);
		 	       		}else{
		 	       			alert(data);
		 	       		}
		  	        }
		        }
		    });
		}else{
			$.ajax({
				type : "post",
				url : "../quickPay/reSendVerifyCode.action",
		        data: {
		        	busiCode: "110",
		        	signType: "1",
		        	version: "v1.1",
		        	merchantNo: "M000016",
		        	orderNo: $("#quick_rsOrder").val(),
		        	payType: "41",
		        },
		        dataType: "json",
		        success: function (data) {
		  	       if(data.success == false){
		  		       alert("重发验证码失败：" + data.errMsg);
		  	       }
		        }
		    });
		}
	}
	
}

/**
 * 校验快捷支付银行卡
 */
function checkQuickCardNo(inputTextK){
	var cardNo = inputTextK.replace(/[^\d]/g,'');
	$.ajax({
		type : "post",
		url : "../quickPay/checkCardCanQuickPay.action",
           data: {
        	   busiCode: "106",
        	   signType: "1",
        	   version: "v1.1",
        	   merchantNo: "M000016",
        	   cardNo: cardNo
           },
           dataType: "json",
           success: function (data) {
        	   if(data.success == false){
        		   $("#checkCardNo_errorMsg").text(data.errMsg);
        		   $("#checkCardNo_errorMsg").removeClass("isShow");
        		   return;
        	   }
        	   
        	   $("#quick_cardNo_hid").val(cardNo);
        	   $("#quick_bankInfo_hid").val(data.bankInfo);
               $("#quick_cardType_hid").val(data.cardType);
               
               if(data.cardType == '10'){
            	   $("#quick_cardType_deposit").text("储蓄卡");
                   $("#quick_cardNo_deposit").text(inputTextK);
                   $("#quick_icon_deposit").attr("xlink:href","#icon-"+data.bankInfo);
                   $("#quick_bankName_deposit").text(data.bankName);
                   $("#card_icon_deposit").attr("xlink:href","#icon-"+data.bankInfo);//卡图片上的银行图标
            	   $("#card_cardNo_deposit").text(inputTextK);//卡图片上的卡号
                   $("#card_cardName_deposit").text(data.bankName);//卡图片上的银行名称
                   $("#quick_singleLimit_deposit").text(data.singleLimit);
                   $("#quick_dailyLimit_deposit").text(data.dailyLimit);
            	   $(".bank-account-info .savings").removeClass("isShow");
            	   $(".bank-account-info .credit").addClass("isShow");//显示天喜储蓄卡
        	   }else if(data.cardType == '11'){
        		   $("#quick_cardType_deposit").text("信用卡");
                   $("#quick_cardNo_credit").text(inputTextK);
                   $("#quick_icon_credit").attr("xlink:href","#icon-"+data.bankInfo);
                   $("#quick_bankName_credit").text(data.bankName);
                   $("#card_icon_credit").attr("xlink:href","#icon-"+data.bankInfo);//卡图片上的银行图标
        		   $("#card_cardNo_credit").text(inputTextK);//卡片图片上的卡号
        		   $("#card_cardName_credit").text(data.bankName);//卡图片上的银行名称
        		   $("#quick_singleLimit_credit").text(data.singleLimit);
                   $("#quick_dailyLimit_credit").text(data.dailyLimit);
        		   $(".bank-account-info .savings").addClass("isShow");
            	   $(".bank-account-info .credit").removeClass("isShow");//显示填写信用卡
        	   }
               
               $("#checkCardNo_errorMsg").text("");
    		   $("#checkCardNo_errorMsg").addClass("isShow");
               $(".shortcut-payment").addClass("isShow");//快捷支付tab隐藏
               $(".bank-account-info").removeClass("isShow"); //填写银行卡信息页面显示
               rateCompute($("#quick_payType_hid").val(), 'addpay');//费率计算
        	   
           },complete:function(){
           	   top.F.loading.hide();
           }
     });
}


/**
 * 检验收款方，如果是贷款则必须选择收款卡，非贷款的泰坦码和帐户名不能为空
 * @returns {Boolean}
 */
function validate_payeeInfo(){
	if(cashierData.linePayType=='10'){	
		//泰容易（贷款）暂不支持

	} else {
		var recieveOrgName = $("#recieveOrgName").val();
		var recieveTitanCode = $("#recieveTitanCode").val();
		if($.trim(recieveOrgName).length < 1){
			alert("收款方账户不能为空");
			return false;
		}
		if($.trim(recieveTitanCode).length < 1){
			alert("收款方泰坦码不能为空");
			return false;
		}
		
		return true;
	}
}

/**
 * 检查收款账户是否存在
 * @returns {Boolean}
 */
function check_account_isExit(){
	
	var recieveOrgName = $("#recieveOrgName").val();
	var recieveTitanCode = $("#recieveTitanCode").val();
	var check_account=true;
	if(cashierData.linePayType != '10')
	{	
    	
    	if(typeof recieveOrgName !="undefined" && typeof recieveTitanCode !="undefined"){
    		check_account = false;
        	$.ajax({
        		type:'post',
                dataType: 'json',
                url: '../account/check_account.action',
                async:false,
                data:{
                	recieveOrgName:recieveOrgName,
                	recieveTitanCode:recieveTitanCode,
                },
                success: function (data) {
                	if(data.result=="0"){
                		check_account = true;
                	}else{
                		alert("该账户不存在");
                	}
                }
            });
    	}
	}
	return check_account;
}




/**
 * 根据用户是否已经设置密码，显示对应的密码框
 */
function hasPayPassword(){
	$.ajax({
		type: "post",
		async:false,
        url: "../account/checkIsSetPayPassword.action",
        data: {//根据fcUserid查询titanUserBindInfo从而获取到tfsUserId，再查询TitanUser检查密码
       	 fcUserid:cashierData.fcUserid,
      	 	 tfsUserId:cashierData.tfsUserid
      	 },
        dataType: "json",
        success: function(data){
	       	 if(data.result=="0"){//未设置
	       		$(".setpassword").show();
	       		$("#isSetPassword").val("1");
	       		
	       	 }else{//已设置
	       		$(".input-password").show();
	       		$("#isSetPassword").val("0");
	       	 }
      	 }
    });
	
	return true;
}


/**
 * 校验/设置密码
 * @returns {Boolean}
 */
function check_set_payPassword(){
	
	var isSetPassword = $("#isSetPassword").val();
	
	if(isSetPassword == '0'){//不用设置密码
		
		 return check_payPassword();
		
	 }else{//需要设置密码
		 
		 return set_payPassword();
		 
	 }
	
}


/**
 * 校验密码
 * @returns {Boolean}
 */
function check_payPassword(){
	
	var result = false;
	var payPassword2 = PasswordStr2.returnStr();
	
	if($.trim(payPassword2).length < 1){
		$("#payPasswordError").text("请输入密码");
	}else{
		$.ajax({
	           type: "post",
	           async:false,
	           dataType: 'json',
	           url: '../account/check_payPassword.action',
	           data: {
	          	 payPassword: payPassword2,
	          	 fcUserid: cashierData.fcUserid
	           },
	           success: function (data) {
	          	  if(data.result=="0"){
	          		  result = true;
	          		
	          	  }else{
	          		 $("#payPasswordError").text("密码错误，请重新输入");
	          		
	          	  }
	           },error:function(data){
	        	   $("#payPasswordError").text("校验密码失败");
	           }
	  	 });
	 }
	
	return result;
}

/**
 * 设置密码
 */
function set_payPassword(){
	
	var result = false;
	var payPassword = PasswordStr.returnStr();
	var payPassword1 = PasswordStr1.returnStr();
	
	if($.trim(payPassword).length < 1 && $.trim(payPassword1).length < 1){
		$("#setPayPasswordError").text("请输入密码");
		
	}else if($.trim(payPassword).length < 1 || $.trim(payPassword1).length < 1){
		$("#setPayPasswordError").text("两次密码输入不一致");
		
	}else{
		if(payPassword == payPassword1){
			
			if($.trim(payPassword.length) == 6){
				   $.ajax({
				    	 type: "post",
				    	 async:false,
				         url: "../account/setPayPassword.action",
				         data: {
				        	 fcuserid: cashierData.fcUserid,
				        	 payPassword: payPassword,
				        	 tfsuserid: cashierData.tfsUserid,
				         },
				         dataType: "json",
				         success: function(data){
				        	 if(data.result=="0"){
				        		 result = true;
				        		 
				        	 }else{
				        		 $("#setPayPasswordError").text(data.msg);
				        	 }
				         }
				   });
			}else{
	 			$("#setPayPasswordError").text("密码必须为6位");
			}
			
		}else{
			$("#setPayPasswordError").text("两次密码输入不一致");
			
		}
	}
	
	return result;
}

/**
 * 费率计算  payType:支付方式	  type{'commpay': 常用支付点选; 'addpay': 添加支付}
 */
function rateCompute(payType, type){
	
	var userId = cashierData.userid;
	var amount = cashierData.tradeAmount;
	
	if(payType != 'balance'){
		
		var relPayType = payType;
		
		if(payType == 'wx' || payType == 'alipay'){
			relPayType = 9;
		}
		
		$.ajax({
			   	type: "get",
		        url: "../rate/rateCompute.action?userId="+userId+"&amount="+amount+"&payType="+relPayType+"&date=" + new Date().getTime(),
		        dataType: "json",
		        async: false,
		        success: function(data){
	        		 var amount = parseFloat($("#amount_" + payType).text());
	        		 var exRateAmount = parseFloat(data.data.exRateAmount);
		        	 if(type == "commpay"){
				       	 $("#commPayRateAmount_" + payType).text(exRateAmount.toFixed(2));
				       	 $("#amount_" + payType).text(amount + exRateAmount);
		        	 }else{
		        		 
		        	 }
		        }
		});
		
	}else {
		$("#amount_" + payType).text(amount);
		
	}
	
}