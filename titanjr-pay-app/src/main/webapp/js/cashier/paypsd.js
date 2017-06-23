var payPasswordObj ={};

function initPayPassword(){
	payPasswordObj.show_payPassword = function(){
		$.ajax({
            dataType: 'html',
            context: document.body,
            url: '../account/showPayPassword.action',
            success: function (html) {
                var d = dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin: 'saas_pop',
                    button: [
                        {
                            value: '确定',
                            skin: 'btn p_lr30',
                            callback: function () {
                            	//验证付款密码是否准确
                            	if(! payPasswordObj.check_payPassword())
                            	{
                            		 $(".ui-dialog-content").html(html);
                            			setTimeout(function(){
                                   			$('#passwordbox').click();
                                   		},500);
                            		return false;
                            	}
                            	return true;
                            	//获取密码
                            },
                            autofocus: true
                        },
                        {
                            value: '取消',
                            skin: 'btn btn_grey btn_exit',
                            callback: function () {
                                //   alert('c');
                            }
                        }
                    ]
                }).showModal();
            }
        });
	};
	
	payPasswordObj.check_payPassword = function(){
		 var result = false;
	   	 $.ajax({
	            type: "post",
	            async:false,
	            dataType: 'json',
	            url: '../account/check_payPassword.action',
	            data: {
	           	 payPassword:PasswordStr2.returnStr(),
	           	 fcUserid:cashierData.fcUserid
	            },
	            success: function (data) {
	           	 if(data.result=="0"){
	           		 result = true;
	           		 pay_Order();
	           	 }else{
	           		new top.Tip({msg: '输入的密码错误', type: 2, timer: 2000});
	           	 }
	            },error:function(data){
	            }
	   	 });
	   	 return result;
	};
	//验证密码
	payPasswordObj.checkIsSetPayPassword = function(){
		 $.ajax({
        	 type: "post",
             url: "../account/checkIsSetPayPassword.action",
             data: {fcUserid:cashierData.fcUserid,
            	 	tfsUserId:cashierData.tfsUserid},
             dataType: "json",
             success: function(data){
            	 if(data.result=="0"){
            		 payPasswordObj.show_set_payPassword();
            	 }
            	}
            }); 
	};
	
	payPasswordObj.show_set_payPassword = function(){
		 $.ajax({
		        dataType: 'html',
		        context: document.body,
		        url: '../account/showSetPayPassword.action',
		        success: function (html) {
		        	payPasswordObj.clickPassword();
		            var d = dialog({
		                title: ' ',
		                padding: '0 0 0px 0 ',
		                content: html,
		                skin: 'saas_pop',
		                button: [
		                    {
		                        value: '确定',
		                        skin: 'btn p_lr30',
		                        callback: function () {
		                        	if(PasswordStr.returnStr()==PasswordStr1.returnStr()){
		                        		if(PasswordStr.returnStr().length==6){
		                        			   $.ajax({
		                        			    	 type: "post",
		                        			         url: "../account/setPayPassword.action",
		                        			         data: {
		                        			        	 fcuserid:cashierData.fcUserid,
		                        			        	 payPassword:PasswordStr.returnStr(),
		                        			        	 tfsuserid:cashierData.tfsUserid,
		                        			         },
		                        			         dataType: "json",
		                        			         success: function(data){
		                        			        	 if(data.result=="0"){
		                        			        		top.F.loading.show();
		                 		                            setTimeout(function () {
		                 		                                top.F.loading.hide();
		                 		                                new top.Tip({msg: '密码设置成功！', type: 1, timer: 2000});
		                 		                            }, 1000);
		                        			        	 }else{
		                        			        			top.F.loading.show();
		                 		                                setTimeout(function () {
		                 		                                top.F.loading.hide();
		                 		                                new top.Tip({msg: data.msg, type: 2, timer: 2000});
		                 		                            }, 1000);
		                        			        	 }
		                        			         }
		                        			   });
		                        		}else{
		                        			 new top.Tip({msg: "密码必须为6位", type: 2, timer: 2000});
		                        			 $(".ui-dialog-content").html(html);
			                         			setTimeout(function(){
			                         				clickPassword();
			                                		 },500);
			                         			return false;
		                        		}
		                        	}else{
		                        		 new top.Tip({msg: "两次输入的密码不一致", type: 2, timer: 2000});
		                        		 $(".ui-dialog-content").html(html);
		                         			setTimeout(function(){
		                         				clickPassword();
		                                		 },500);
		                         			return false;
		                        		
		                        	}
		                        },
		                        autofocus: true
		                    },

		                ]
		            }).showModal();
		        }
		    });
	};
	
	payPasswordObj.clickPassword = function(){
	 	$('#passwordbox').click();
  		timeIndex = setInterval(function(){
  			try
  			{
  				if($('#passwordbox i:last b:first-child').attr('style').indexOf('inherit') != -1)
  				{
  					$('#passwordbox1').click();
  					clearInterval(timeIndex);
  				}
  			}catch(e)
  			{}
  		},100);
	};
	
	 //验证是否输入密码
	payPasswordObj.validate_isInput_password = function(){
		if(cashierData.paySource=='1'){//如果分销商付款不需要输入密码，不用余额支付也不需要输入付款密码
    		return true;
    	}
    	if(($("#d_checkbox").attr("checked")=="checked" && cashierData.balanceusable=="0")||$("#d_checkbox").attr("checked")!="checked"){
    		return true;
    	}
    	var flag = false;
       	 $.ajax({
                dataType: 'json',
                context: document.body,
                async:false,
                url: '../account/allownopwdpay.action',
                data:{
               	 totalAmount :cashierData.pay_totalAmount(),
               	 userid:cashierData.userid
                },
                success: function (data) {
               	 if(data.result =="0"){
               		 flag =  true;
               	 }
                }
            });
    	return flag; 
	};
};


