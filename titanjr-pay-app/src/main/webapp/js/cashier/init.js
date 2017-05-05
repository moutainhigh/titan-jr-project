function initCashierDesk(){
	//识别出当前的余额是否足够支付，如果不够支付则位false 足够则为true
	var isEnough = false;
	if(isGDP()||isOpenOrg()||isTTMALL()){//GDP支付的逻辑控制
		show_history();
		$("#useCashierDeskPay").show();
        $("#enough_amount").hide();
	}
	
	if(isFinance()){//财务中需要显示历史,显示余额充足或者不充足。
		//收款账户历史，或者收款方账户存在时
		if(cashierData.accountHistoryDTO.length>0 ||cashierData.orgName.length>0){
			show_history();
		}else{
			hide_history();
		}
	
		isEnough = (cashierData.tradeAmount - cashierData.balanceusable) <= 0;
		
		if(isEnough){
			amount_enough_show();
		}else{
			amount_not_enough_show();
		}   
	}
	
	 
	//账户可用余额为0的时候不能让复选按钮选中
    if(cashierData.balanceusable=="0.0" 
    		||cashierData.balanceusable=="0.00"
    		||cashierData.balanceusable=="0"){
    	$("#d_checkbox").attr("checked",false);
    	$("#d_checkbox").attr('disabled',true);
    }
    
    //点击某个银行计算费率
    $('.paytable_payway').on('click', function(){
    	var itemType = $(this).attr("itemType");
    	paytable_paywayClick(itemType);
    	//表示贷款
		  if (itemType == 10) {
			$("#exists_history").hide();
			$("#not_exists_history").hide();
			$("#loanItemType").show();
			$("#useBalanceCheck").hide();
			$("#not_enough_amount").hide();

		} else {
			$("#exists_history").show();
			$("#not_exists_history").show();
			$("#loanItemType").hide();
			$("#useBalanceCheck").show();
			if(isFinance()){//财务中需要显示历史,显示余额充足或者不充足。
			
				isEnough = (cashierData.tradeAmount - cashierData.balanceusable) <= 0;
				
				if(isEnough){
					amount_enough_show();
				}else{
					amount_not_enough_show();
				}   
			}
		}
    });
    
  // 单选框选中是触发限额显示
	$(".pay_bank_l input[type='radio']").on("click",function(){
		bankCheckRadio($(this));
	});
    
	if($("#useCashierDeskPay").css("display")=="none"){
		$(".bank-limit-wrap").hide();
	}else{
		$(".bank-limit-wrap").show();
	}
	
    //判断网银是否展开
    var isShow = $("#useCashierDeskPay").is(":visible");
    if(isShow){
    	 //默认选中第一个银行
    	var firstBank = $(".bankName:first");
	    firstBank.attr("checked","0");
	    $('.paytable_payway').each(function(){
	    	if($(this).find('input:radio[name="r2"]').is(":checked"))
	          	{
	          		$(this).click();
	          		$(this).find('input:radio[name="r2"]').click();
	          	}
	         });
    }
    
	
    if(cashierData.paySource!="1"){
    	payPasswordObj.checkIsSetPayPassword();
    }
}

//GDP
function isGDP(){
	if(cashierData.paySource=="1"){
		return true;
	}
	return false;
}

//财务
function isFinance(){
	if(cashierData.paySource=="2"){
		return true;
	}
	return false;
}

function isOpenOrg(){
	if(cashierData.paySource=="6"){
		return true;
	}
	return false;
}

function isTTMALL(){
	if(cashierData.paySource=="7"){
		return true;
	}
	return false;
}


//显示历史账户
function show_history(){
    $("#exists_history").show();
    $("#not_exists_history").remove();
}

//隐藏历史账户	
function hide_history(){
   $("#exists_history").remove();
   $("#not_exists_history").show();
}

//余额够了
function amount_enough_show(){
	$("#useCashierDeskPay").hide();
    $("#enough_amount").show();
    $("#not_enough_amount").hide();
    $("#onlinePayAmount").val(cashierData.tradeAmount);
    $("#d_checkbox").attr("checked",true);
}
//余额不够
function  amount_not_enough_show(){
   $("#useCashierDeskPay").show();
   $("#enough_amount").hide();
   $("#not_enough_amount").show();
}

