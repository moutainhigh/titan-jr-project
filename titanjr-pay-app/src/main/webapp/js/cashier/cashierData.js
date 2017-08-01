var cashierData = {};

function initCashierData(data){
	cashierData = data;
	
	cashierData.checkBoxIsChecked = function(){
		if($("#d_checkbox").attr("checked")=="checked"){
			return true;
		}
		return false;
	};
	cashierData.isaccount = function(){
		if(cashierData.checkBoxIsChecked()){
			return 1;
		}
		return 2;
	};
	cashierData.pay_totalAmount = function(){
		return $("#pay_totalAmount").text();
	};
	//余额支付总额
	cashierData.transferAmount=function(){
		if(sub(cashierData.tradeAmount, cashierData.balanceusable) <= 0){
			if(cashierData.checkBoxIsChecked()){
				return cashierData.tradeAmount;
			}
		}else{
			if(cashierData.checkBoxIsChecked()){
				return cashierData.balanceusable;
			}
		}
		return "0";
	};
	//充值金额
	cashierData.payAmount = function(){
		if(sub(cashierData.tradeAmount, cashierData.balanceusable) <= 0){
			if(!cashierData.checkBoxIsChecked()){//不用余额
				return cashierData.tradeAmount;
			}
		}else{
			if(cashierData.checkBoxIsChecked()){//用余额
				return sub(cashierData.tradeAmount, cashierData.balanceusable);//还要充值的金额
			}else{
				return cashierData.tradeAmount;
			}
		}
		return "0";
	};
	
	cashierData.recieveOrgName = function(){
		if($("#not_exists_history").is(":visible")==true || $(".replanceArea").is(":visible")==true){
			return $.trim($("#reOrgName").val());
		}else{
			return $.trim($("#hiddenAccountName").val());
		}
	};
	
	cashierData.recieveTitanCode = function(){
		if($("#not_exists_history").is(":visible")==true || $(".replanceArea").is(":visible")==true){
			return $.trim($("#reTitanCode").val());
		}else{
			return $.trim($("#hiddenTitanCode").val());
		}
	};
	
	cashierData.bankInfo = function(){
		var bankInfo =  $(".bankName:checked").val();
		if(typeof(bankInfo) == "undefined"){
			bankInfo =null;
	     }
		return bankInfo;
	};
	
	cashierData.payPassword = function(){
		var payPassword = null;
		if("undefined" != typeof PasswordStr2){
			payPassword = PasswordStr2.returnStr();
		}
		return payPassword;
	};
	
	cashierData.linePayType = function(){
		var itemType = $(".bankName:checked").attr("data-index");
		var linePayType =null;
		if("undefined" != typeof itemType){
			linePayType =   $("#item-"+itemType).attr("data-index");
		}
		return linePayType;
	};
	
	cashierData.payerAccount = function(){
		var payerAccount = null;
		var value=$('input:radio[name=r2]:checked').val();
		if(value=='cmbc' && cashierData.linePayType()=="1"){
			var dataIndex = $('input:radio[name=r2]:checked').attr("data-index");
			payerAccount = $("#customNo-"+dataIndex).val();
		}
		return payerAccount;
	};
	
	cashierData.validatePayerAccount = function(){
		var errMsg ="";
		if(cashierData.payerAccount() !=null){
    		if(cashierData.payerAccount().length<1){
    			errMsg="民生企业银行客户号不能为空";
    		}else{
    			var reg = /^([a-z]|[A-Z]|[0-9]){1,32}$/;
    			if(!reg.test(cashierData.payerAccount())){
    				errMsg="民生企业银行客户号输入有误,只能是数字或字母";
    			};
    		}
		}
		return errMsg;
	};
	
	cashierData.onlinePayData = function(){
		var data= {
		       	 payOrderNo:cashierData.payOrderNo,
		       	 fcUserid:cashierData.fcUserid,
		       	 userid:cashierData.userid,
		       	 deskId:cashierData.deskId,
		         paySource:cashierData.paySource,
		         creator:cashierData.creator,
		       	 tradeAmount:cashierData.tradeAmount,
		       	 sign:cashierData.sign,
		       	
		       	 recieveOrgName:cashierData.recieveOrgName(),
		       	 recieveTitanCode:cashierData.recieveTitanCode(),
		       	 bankInfo:cashierData.bankInfo(),
		         linePayType:cashierData.linePayType(),
		         isaccount:cashierData.isaccount(),
		         payPassword:cashierData.payPassword()
		};
		return data;
	};
	
	cashierData.window = function(errMsg){
		 new top.createConfirm({
	            title:'提示',
	            padding: '20px 20px 40px',
	            okValue : '关闭',
	            content : errMsg,
	            skin : 'saas_confirm_singlebtn',
	            ok : function(){
	            },
	            cancel: false,
	        });
	};
	
	cashierData.form = function(){
		var form = document.createElement("form");
		form.action = '../payment/packageRechargeData.action';
		form.id = 'onlinePaymentForm';
		form.method = 'post';
		
			var isaccount = document.createElement("input");
			isaccount.type = 'hidden';
			isaccount.name = 'isaccount';
			isaccount.id = 'isaccount';
			form.appendChild(isaccount);
		
			var payPassword = document.createElement("input");
			payPassword.type = 'hidden';
			payPassword.name = 'payPassword';
			payPassword.id = 'payPassword';
			form.appendChild(payPassword);
			
			var recieveOrgName = document.createElement("input");
			recieveOrgName.type = 'hidden';
			recieveOrgName.name = 'recieveOrgName';
			recieveOrgName.id = 'recieveOrgName';
			form.appendChild(recieveOrgName);
		
			var recieveTitanCode = document.createElement("input");
			recieveTitanCode.type = 'hidden';
			recieveTitanCode.name = 'recieveTitanCode';
			recieveTitanCode.id = 'recieveTitanCode';
			form.appendChild(recieveTitanCode);
			
			var bankInfo = document.createElement("input");
			bankInfo.type = 'hidden';
			bankInfo.name = 'bankInfo';
			bankInfo.id = 'bankInfo';
			form.appendChild(bankInfo);
		
			var linePayType = document.createElement("input");
			linePayType.type = 'hidden';
			linePayType.name = 'linePayType';
			linePayType.id = 'linePayType';
			form.appendChild(linePayType);
			
			var paySource = document.createElement("input");
			paySource.type = 'hidden';
			paySource.name = 'paySource';
			paySource.id = 'paySource';
			form.appendChild(paySource);
			
			var deskId = document.createElement("input");
			deskId.type = 'hidden';
			deskId.name = 'deskId';
			deskId.id = 'deskId';
			form.appendChild(deskId);
		
			var payerAcount = document.createElement("input");
			payerAcount.type = 'hidden';
			payerAcount.name = 'payerAcount';
			payerAcount.id = 'payerAcount';
			form.appendChild(payerAcount);
			
			var userid = document.createElement("input");
			userid.type = 'hidden';
			userid.name = 'userid';
			userid.id = 'userid';
			form.appendChild(userid);
			
			var payOrderNo = document.createElement("input");
			payOrderNo.type = 'hidden';
			payOrderNo.name = 'payOrderNo';
			payOrderNo.id = 'payOrderNo';
			form.appendChild(payOrderNo);
		
			var sign = document.createElement("input");
			sign.type = 'hidden';
			sign.name = 'sign';
			sign.id = 'sign';
			form.appendChild(sign);
			
			var fcUserid = document.createElement("input");
			fcUserid.type = 'hidden';
			fcUserid.name = 'fcUserid';
			fcUserid.id = 'fcUserid';
			form.appendChild(fcUserid);
			
			var tradeAmount = document.createElement("input");
			tradeAmount.type = 'hidden';
			tradeAmount.name = 'tradeAmount';
			tradeAmount.id = 'tradeAmount';
			form.appendChild(tradeAmount);
			document.body.appendChild(form);
	};
	
	cashierData.submit = function(){
		cashierData.form();
		
		document.getElementById('isaccount').value=cashierData.isaccount();
		document.getElementById('payPassword').value=cashierData.payPassword();
		
		document.getElementById('recieveOrgName').value=cashierData.recieveOrgName();
		document.getElementById('recieveTitanCode').value=cashierData.recieveTitanCode();
		document.getElementById('bankInfo').value=cashierData.bankInfo();
		document.getElementById('linePayType').value=cashierData.linePayType();
		document.getElementById('payerAcount').value=cashierData.payerAccount();
		document.getElementById('paySource').value=cashierData.paySource;
		document.getElementById('deskId').value=cashierData.deskId;
		document.getElementById('userid').value=cashierData.userid;
		document.getElementById('payOrderNo').value=cashierData.payOrderNo;
		document.getElementById('tradeAmount').value=cashierData.tradeAmount;
		document.getElementById('fcUserid').value=cashierData.fcUserid;
		document.getElementById('sign').value=cashierData.sign;
		document.getElementById('onlinePaymentForm').submit();
	};
	
}