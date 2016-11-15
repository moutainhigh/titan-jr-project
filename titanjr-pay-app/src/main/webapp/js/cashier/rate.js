    //计算费率
	function paytable_paywayClick(itemType){
	     var userId = cashierData.userid();
	     var payAmount = "0";
		 var transferAmount ="0";
		 if (sub(cashierData.tradeAmount(), cashierData.balanceusable()) <= 0){
			if($("#d_checkbox").attr("checked")=="checked"){//余额充足二选一
				transferAmount = cashierData.tradeAmount();
	   	    }else{
	   	    	payAmount = cashierData.tradeAmount();
	   	    }
		 }else{
			if($("#d_checkbox").attr("checked")=="checked"){//余额不足，任意选择
				transferAmount = cashierData.balanceusable();
	  		    payAmount = sub(cashierData.tradeAmount(), cashierData.balanceusable());
	  	    }else{
	  	    	payAmount = cashierData.tradeAmount();
	  	    }
		 }
		 if(itemType ==1 && parseFloat(payAmount) < 1000  &&  cashierData.paySource()=='1' && !($("#d_checkbox").attr("checked")=="checked"))
		 {
			 chageComfireBut('hide');
			// $('.J_password').hide();
		 }else{
			 chageComfireBut('show');
		 }
		 
		 $.ajax({
	   	 type: "get",
	        url: "<%=basePath%>/rate/rateCompute.action?userId="+userId+"&amount="+payAmount+"&payType="+itemType+"&date=" + new Date().getTime(),
	        dataType: "json",
	        async: false,
	        success: function(data){
	       	 $('#titanRateAmount').text(data.data.exRateAmount);
	       	 var show_online_payAmount = accAdd(payAmount,data.data.exRateAmount);
	         $("#pay_surplus_amount").text(show_online_payAmount);
	        }
	      }); 
	}
	
	 //少于1000不能使用网银支付
	function chageComfireBut(status)
	{
		if(status == 'hide')
		{
			 $('.J_password').hide();
			 $('.grey_bg').show();
			 $('#enBankTitle').show();
		}else{
			 $('.J_password').show();
			 $('.grey_bg').hide();
			 $('#enBankTitle').hide();
		}
	}