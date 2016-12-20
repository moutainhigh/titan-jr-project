<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SAAS后台管理</title>
	<jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
<body>
<div id="scroll">  
    <div class="main_top clearfix headline bg_bom">
         <div class="main_return fl"><a href="javascript:history.go(-1)"><i></i>返回上一页</a></div>
        <div class="history fl">我的贷款 >  还款</div>
    </div>   
</div>

<div class="Refund clearfix">
	<div class="R_title"><i></i>还款</div>
	<div class="R_c clearfix">		
		<!-- <div class="RC_t">
			<ul>
				<li>贷款类型：包房贷</li>
				<li>贷款编号：D13245678913264</li>
				<li>申请时间：2016-10-02  12:25:36</li>
			</ul>
		</div> -->
		<div class="RC_xinxi">
			<h3>账户名称/泰坦码：${orgName}/${titanCode}</h3>
			<p>账户可用余额：<span class="" id="amountSpan"></span>元</p>
			<a href="javascript:void(0);" class="rechargeBtn">去充值</a>
		</div>

		<div class="RC_c clearfix">
			<ul>
				<li class="clearfix"><div class="tit">还款金额</div>
					<i class="c_666 f_20"><input id="repaymentAmount" class="text w_250  c_666 " placeholder="" type="text" value=""></i> 
					<i class="c_666 ">元</i> <i class="c_fe2b2b m_l20" id="inputeAmountError"></i>
				</li>
				<li class="clearfix"><div class="tit">还款详情</div>
				<table cellpadding="0" cellspacing="0" width="460">
					<colgroup>
						<col width="110">
						<col width="">						
						<col width="">
					</colgroup>
					<tr>
						<td ></td>
						<td class="tdr"><i>应还金额</i></td>
						<td class="tdr"><i>本次实还金额</i></td>												
					</tr>	
					<tr>
					
					
						<td >逾期罚金</td>
						<td class="tdr" id="useroverduefine"></td>
						<td class="tdr" id="realoverduecapitalAmount"></td>
					</tr>
					<tr>
						<td >逾期利息</td>
						<td class="tdr" id="useroverdueinterest"></td>
						<td class="tdr" id="realoverdueinterestAmount"></td>
					</tr>
					<tr>
						<td >利息</td>
						<td class="tdr" id="usershouldinterest"></td>
						<td class="tdr" id="realinterestAmount"></td>
					</tr>
					<tr>
						<td >本金</td>
						<td class="tdr" id="usershouldcapital"></td>
						<td class="tdr" id="realcapitalAmount"></td>
					</tr>
				</table>
				</li>
			</ul>
		</div>
	</div>
</div>	
	
<div style="height: 60px"></div>
<div class="TFS_data_button">	
	<a class="btn btnNext J_password" href="javascript:void(0);" style="display: none">确定还款</a>
    <a class=" btn_exit_long bnt_exit_padding12" href="javascript:history.go(-1)">取消</a>
</div>


<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript">  

var orderNo = '${orderNo}';
var currBalanceusable = 0;


function validateRepayment()
{
  		//验证提现的金额
    	var repaymentAmount = $("#repaymentAmount").val();
    	if(repaymentAmount.length<1){
    		 $("#inputeAmountError").text("还款金额不能为空!");
    		return false;
    	}
    	var neg = /^[1-9]{1}\d{0,20}(\.\d{1,2})?$/;
        var neg2 = /^[0]{1}(\.\d{1,2})?$/;
       	var flag = neg.test(repaymentAmount)||neg2.test(repaymentAmount);
       	
        if(flag==false){
              $("#inputeAmountError").text("输入金额无法识别,正确格式如xx或xx.xx");
              $("#repaymentAmount").val("");
              $("#repaymentAmount").focus();
              return false;
        }else{
              $("#inputeAmountError").text("");
        }
    	
    	if(repaymentAmount=="0" ||repaymentAmount=="0.0" ||repaymentAmount=="0.00"){
    		$("#inputeAmountError").text("您的还款额度必须大于0");
    		return false;
    	}
 		
    	if(parseFloat(repaymentAmount) > parseFloat(currBalanceusable)
    			|| currBalanceusable == 0 
    			|| currBalanceusable == 0.00)
    	{
   			$("#inputeAmountError").text("可用余额不足，不能还款,请充值！");
   			return false;
   	    }
    	
		return true;
}

function loadReapymentAmount()
{
	var amount = $("#repaymentAmount").val();
	if(amount == null || amount == '')
	{
		amount=0;
	}
	$.ajax({
	//	async : false,
		type : 'get',
		data : {
			"orderNo" : orderNo,
			"amount" : amount
		},
		url :  '<%=basePath%>/loan/repayment/checkRepaymentOrder.shtml'+"?DateTime="+new Date().getTime(),
		dataType : 'json',
		success : function(obj) {
			if(obj.result == 1)
			{
				for(var name in obj)
				{
					if($('#'+name).length >= 1)
					{
						var amount = formatCurrency(obj[name]/100);
						if(amount == null || amount =='')
						{
							amount = 0.00;
						}
						$('#'+name).html(amount);
					}
				}
				 $(".J_password").show();
			}
			else{
				new top.Tip({msg : obj.msg, type: 3, timer:5000});
			}
		},
        error:function(){
        	new Tip({msg : '计算还款金额失败！',type : 3 });
        }
	});
}
	
//异步加载账户余额信息
var errorIndex = 1;
function loadAccountBalance(isAsync)
{
	var balanceusable = 0;
	$.ajax({
		async : isAsync,
		dataType : 'json',		      
        url : '<%=basePath%>/account/query-account-balance.shtml?date=' + new Date().getTime() ,
        success:function(data){
        	if(data)
        	{
        		balanceusable = formatCurrency(data.balanceusable/100);
        		currBalanceusable = balanceusable;
        		$('#amountSpan').text(balanceusable);
        	}
        },
        error: function()
        {
        	if(++errorIndex <= 3)
        	{
        		loadAccountBalance();	
        	}
        }
	});
	return balanceusable;
}


 $(function(){
		//点击还款按钮
	 	 $(".J_password").click(function(){
	 			F.loading.show();
				if(validateRepayment())
				{
					var repaymentAmount = $("#repaymentAmount").val();
					var bAmount= loadAccountBalance(false);
			 		
				     	
			    	if(parseFloat(repaymentAmount) > parseFloat(bAmount)
			    			|| bAmount == 0 
			    			|| bAmount == 0.00)
			    	{
			   			$("#inputeAmountError").text("可用余额不足，不能还款,请充值！");
			   		 F.loading.hide();
			   			return false;
			   	    }
			    	showPayPassword();
			    
				}
				F.loading.hide();
	 	 });
	 
		 $("#repaymentAmount").blur(function(){
			 validateRepayment();
		 });
	 
		 $("#repaymentAmount").keyup(function () {
                var reg = $(this).val().match(/\d+\.?\d{0,2}/);
                var txt = '';
                if (reg != null) {
                    txt = reg[0];
                }
                $(this).val(txt);
            }).change(function () {
                $(this).keypress();
                var v = $(this).val();
                if (/\.$/.test(v))
                {
                    $(this).val(v.substr(0, v.length - 1));
                }
            });
	 
	 	$('.rechargeBtn').on('click',function(){
			window.top.createIframeDialog({
				    url : '<%=basePath%>/account/goto_cashierDesk.shtml?payType=7&backUrl'+encodeURIComponent(window.location.href),
			 });
		      return false;
		});
	 
	 	
// 	 	//创建输入框监控事件
	    $.fn.watch = function(callback) {
	        return this.each(function() {
	            //缓存以前的值
	            $.data(this, 'originVal', $(this).val());

	            //event
	            $(this).on('keyup paste', function() {
	                var originVal = $(this, 'originVal');
	                var currentVal = $(this).val();

	                if (originVal !== currentVal) {
	                    $.data(this, 'originVal', $(this).val());
	                    callback(currentVal);
	                }
	            });
	        });
	    };
	 	
	    var timeObj = null;
	   $("#repaymentAmount").watch(function(value) {
		  
			   clearTimeout(timeObj);
			   timeObj = setTimeout(function(){
				   if(validateRepayment())
					{
				  		 loadReapymentAmount();
					}
			   } , 1000);
			
       });
	   
	    loadReapymentAmount();
	  
		loadAccountBalance(true);
	 	
       
        function bigImgShow(){              
           var _index=0; 
           var leftBtn,rightBtn,ulDiv;
            $(".TFSimgOnBig").find("img").live('click',function(){
            var _div='<div tabindex="0" style=" position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; z-index: 1024; background-color: rgba(0, 0, 0,0.7);" class="bigImgShow_box_bg"><span class="left_btn"></span><span class="right_btn"></span><div class="bigImgShow_box"><span class="close_btn"></span><ul>';     
            _index=$(this).parents(".TFSimgOnBig").index();
            console.log()
            for(var i=0;i< $(".TFSimgOnBig").find("img").length;i++){
                var img=$(".TFSimgOnBig").eq(i).find("img").attr("src");
                 if(i==_index){
                   _div+="<li class='cur MissionRoom_annexList_li'><img src='"+img+"'></li>" 
                }else{
                   _div+="<li class='MissionRoom_annexList_li'><img src='"+img+"'></li>"  
                }                              
            };
            _div+="</ul></div></div>";
            window.top.$("body").append(_div);
            leftBtn=window.parent.$(".left_btn");
            rightBtn=window.parent.$(".right_btn");
            var closeBtn=window.parent.$(".close_btn");
            var divId=window.parent.$(".bigImgShow_box_bg");
            ulDiv=window.parent.$(".bigImgShow_box").find("ul");
            if(_index==0){
                leftBtn.hide();
            };
            if(_index==$(".TFSimgOnBig").length-1){
                rightBtn.hide();
            };        
            leftBtn.on('click',function(){
               _index--;
               if(_index==0){
                 leftBtn.hide();
               };
               rightBtn.show();
               showImg();
           }); 
           rightBtn.on('click',function(){                 
               _index++;
               if(_index==$(".TFSimgOnBig").length-1){
                 rightBtn.hide();
               };
               leftBtn.show();
               showImg();
           });  
           closeBtn.live('click',function(){                 
              divId.remove();
           });          
        });
           
           function showImg(){
            ulDiv.find(".MissionRoom_annexList_li").hide().eq(_index).show(); 
           } 
         };   
         new bigImgShow(); 
       $(".TFSaddImg").live('click',function(){
            $(this).addClass("hidden");
            $(this).parent().find(".TFSuploading").removeClass("hidden");
            loading($(this).parent().find(".TFSuploading"));
       });
       $(".J_delete_upload").live('click',function(){
            $(this).parent().addClass("hidden").removeClass("TFSimgOnBig");
            $(this).parent().parent().find(".TFSaddImg").removeClass("hidden");
       });
       function loading(obj){
           var l1=obj.find("span");
           var l2=obj.find("i");
           var i=0;
           var loadingJ=setInterval(function(){
                l1.css("width",i+"%");
                l2.html(i);
                if(i==90){
                    clearInterval(loadingJ);
                }
                i++;
        　　},20); 
           setTimeout(function(){
             var loadingN=setInterval(function(){
                l1.css("width",i+"%");
                l2.html(i);
                if(i==100){
                    clearInterval(loadingN);
                }
                i++;
        　　},20);
                             
           },2500);
           setTimeout(function(){
             obj.addClass("hidden");  
             obj.parent().find(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
           },3000);
       }
       
       
       checkIsSetPayPassword();
    });
    
    
 function checkIsSetPayPassword(){
   	 $.ajax({
       	 type: "post",
           url: "<%=basePath%>/account/checkIsSetPayPassword.action",
           data: {fcUserid:'${LOGIN_USER_ID}'},
           dataType: "json",
           success: function(data){
          	 if(data.result=="success"){
          		show_setPayPassword();
          	 }
          	}
          }); 
   }
	
 
 function show_setPayPassword(){
	 $.ajax({
	        dataType: 'html',
	        context: document.body,
	        url: '<%=basePath%>/account/showSetPayPassword.action',
	        success: function (html) {
	        
	            var d = dialog({
	                title: ' ',
	                padding: '0 0 0px 0 ',
	                content: html,
	                skin: 'saas_pop',
	                button: [
	                    {
	                        value: '确定',
	                        skin: 'btn btn_grey',
	                        callback: function () {
	                        	if(PasswordStr.returnStr()==PasswordStr1.returnStr()){
	                        		if(PasswordStr.returnStr().length==6){
	                        			if(!set_PayPassword())
	                        			{
	                        				 $(".ui-dialog-content").html(html);
	                             			setTimeout(function(){
	                             				clickPassword();
	                                    		 },500);
	                        				return false;
	                        			}
	                        			return true;
	                        		}else{
	                        			 new top.Tip({msg: "密码必须为6位", type: 1, timer: 1000});
	                        			 
	                        			 $(".ui-dialog-content").html(html);
	                         			setTimeout(function(){
	                         				clickPassword();
	                                		 },500);
		                        		
	                        		}
	                        	}else{
	                        		 new top.Tip({msg: "两次输入密码不一致", type: 1, timer: 1000});
	                        		 $(".ui-dialog-content").html(html);
	                     			setTimeout(function(){
	                     				clickPassword();
	                            		 },500);
	                        	}
	                        	
	                        	 return false;
	                        },
	                        autofocus: true
	                    },

	                ]
	            }).showModal();
	            
	          
	            setTimeout(function(){
	            	 clickPassword();
	            },500);
	            
	        }
	    });
}
 
 function clickPassword()
 {
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
 }
 
 
 var pwdHtml = '';
 
 function showPayPassword(){
 	$.ajax({
         dataType: 'html',
         context: document.body,
         url : '<%=basePath%>/account/showPayPassword.shtml',
         success : function(html){
             var d =  dialog({
                 title: ' ',
                 padding: '0 0 0px 0 ',
                 content: html,
                 skin : 'saas_pop',
                 button : [
                     {
                         value: '确定',
                         skin : 'btn btn_grey ',
                         callback: function () {
                         	pwdHtml = html;
                         	if(PasswordStr2.returnStr().length==6){
                         		return to_check_payPassword();
                         	}else{
                         		new top.Tip({msg: '输入的密码必须为6位', type: 1, timer: 2000});
                         		
                         		 $(".ui-dialog-content").html(html);
                        			setTimeout(function(){
                               			$('#passwordbox').click();
                               		},500);
                        			return false;
                         	}
                         }
                     }
                 ]
             }).showModal();
         }
     });
 }
 
 function to_check_payPassword(){
	 
 	var result = false;
 	 $.ajax({
          type: "post",
          dataType: 'json',
          async:false,
          url: '<%=basePath%>/setting/check_payPassword.shtml',
          data: {
         	 payPassword:PasswordStr2.returnStr()
          },
          success: function (data) {
        	  
         	 if(data.code=="1"){
         		 result=true;
         		 
         		top.F.loading.show();
         		
         		$.ajax({
	    			async : false,
	    			type : 'get',
	    			data : {
	    				"orderNo" : orderNo,
	    				"amount" : $("#repaymentAmount").val()
	    			},
	    			url :  '<%=basePath%>/loan/repayment/repayment.shtml'+"?DateTime="+new Date().getTime(),
	    			dataType : 'json',
	    			success : function(result) {
	    				top.F.loading.hide();
	    				if(result.code == 1){
	    					window.location.href="<%=basePath %>/loan/repayment/repayment-success.shtml?orderNo="+orderNo　+"&amount="+$("#repaymentAmount").val() ;
	    				}else{
	    					new top.Tip({msg : result.msg, type: 3, timer:2500});
	    				}
	    			},
	    	        error:function(){
	    	        	top.F.loading.hide();
	    	        	new Tip({msg : '还款失败！',type : 3 });
	    	        }
		    	});
         		 
         		 
         	 }else{
         		new top.Tip({msg: '输入的密码错误', type: 1, timer: 2000});
         		 $(".ui-dialog-content").html(pwdHtml);
         			setTimeout(function(){
                			$('#passwordbox').click();
                		 },500);
         	 }
          }
 	 });
 	 
 	 return result;
 }
    
    function set_PayPassword(){
    	var result = false;
    	 $.ajax({
	    	 type: "post",
	         url: "<%=basePath%>/account/setPayPassword.action",
	         async:false,
	         data: {
	        	 payPassword:PasswordStr.returnStr()
	         },
	         dataType: "json",
	         success: function(data){
	        	 if(data.result=="success"){
	        		 result = true;
	        		top.F.loading.show();
                     setTimeout(function () {
                         top.F.loading.hide();
                         new top.Tip({msg: '密码设置成功！', type: 1, timer: 1000});
                     }, 1000);
	        	 }else{
	        			top.F.loading.show();
                         setTimeout(function () {
                             top.F.loading.hide();
                             new top.Tip({msg: data.msg, type: 1, timer: 1000});
                         }, 1000);
	        	 }
	         }
	   });
    	 return result;
    }

//ajax - 对比录底价
//     $('.J_password').on('click',function(){        
    
//         $.ajax({
//             dataType : 'html',
//             context: document.body,
//             url : '确定还款.html',
//             success : function(html){

//                 var d =  window.top.dialog({
//                     title: ' ',
//                     padding: '0 0 0px 0 ',
//                     content: html,
//                     skin : 'saas_pop',          
//                     button : [ 
//                         {
//                             value: '确认',
//                             skin : 'btn btn_save ',
//                             callback: function () {
//                               new window.top.Tip({ msg : '还款成功！'});                          
//                               setTimeout(function(){                                  
//                                 window.location.href="泰坦金融-我的贷款首页.html"                                
//                               },1500)  
//                             },
//                             autofocus: true
//                         },
//                         {
//                             value: '取消',
//                             skin : 'btn btn_grey btn_exit',
//                             callback: function () {
//                                alert('c');
//                             }
//                         }
//                     ]
//                 }).showModal()
//             }
//         })
//     });	


</script>
</body>
</html>