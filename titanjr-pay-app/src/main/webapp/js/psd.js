
var payPassdword = {
		show_psd:function(url,data){
			$.ajax({
	            dataType: 'html',
	            context: document.body,
	            url: url+"/account/showPayPassword.action",
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
	                            	if(! payPassdword.check_pasword(url,data))
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
		},
		check_pasword:function(url,data){
			 data.payPassword=PasswordStr2.returnStr();
			 var result = false;
		   	 $.ajax({
		            type: "post",
		            async:false,
		            dataType: 'json',
		            url: url+'/account/check_payPassword.action',
		            data: data,
		            success: function (data) {
		           	 if(data.result=="0"){
		           		 result = true;
		           		 refundObj.submitObj();
		           	 }else{
		           		new top.Tip({msg: '输入的密码错误', type: 1, timer: 2000});
		           	 }
		            },error:function(data){
		            }
		   	 });
		   	 return result;
		}
		

}