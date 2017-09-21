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
		body{margin:0;}     
		/*头部*/
		.w_1200{margin: 0 auto;width: 1200px;}
		.w_header{top: 0;width: 100%; z-index: 1000;height: 90px;background-color: #fff;-webkit-box-shadow:0 0 5px #c1c3c4;-moz-box-shadow:0 0 5px #c1c3c4;box-shadow:0 0 5px #c1c3c4; position:fixed;}
		.w_header .w_logo{padding-top: 30px;float: left;}
		.w_header .w_logo .l_img{float: left;width: 316px;padding-left: 10px;}
		.w_header .w_logo .l_text{float: left;font-size: 18px;color: #999;height: 24px;line-height: 24px;padding-top: 4px;}
		.w_header .w_logo .l_text span{width: 13px;height: 24px;display: inline-block;float: left;color: #ccc;font-weight: normal;padding-right: 10px;}
		.footer1 {font: 12px/100% "微软雅黑";color: #4a4a4a;height: 86px;line-height: 86px;background-color: #cbccce;text-align: center;overflow: hidden;}
		.footer1 .f_bd {width: 700px;margin: 0 auto;height: 86px;}
		.fl {float: left;}
		.footer1 .f_bd .f_bd_r {display: inline-block;padding-top: 16px;padding-left: 20px;}
	</style>  
</head>
<body >

<c:if test="${ param.wrapType=='wallet'}">
<div class="w_header">
	<div class="w_1200">
		<div class="w_logo">
			<div class="l_img"><a href="http://pay.fangcang.com" title="泰坦钱包"><img src="<%=request.getScheme()%>://hres.fangcang.com/css/saas/WALLET/images/logo.png"></a></div>
			<div class="l_text">
				<span class="">丨</span>充值
			</div>
		</div>		
	</div>
</div>
<div style="height:100px;"></div>
</c:if>
<!--弹窗白色底-->
	<div class="other_popup">
		<c:if test="${empty param.wrapType}">
			<div class="other_popup_title">
				<div class="other_popup_title2">
					<span class="visual"></span>
					充值
				</div>
			</div>
		</c:if>
		<div class="S_popup_Kan clearfix opaque">
			<div class="gold_pay">
			
				<div class="TFS_rechargeBox">
					
						<div class="TFS_rechargeBoxL fl" style="padding:0">
							<div class="goldpay_top" style="padding-top: 20px; height: 130px; width: 740px;margin:0; ">
							充值金额：<input type="text" class="text w_200" id="inputeAmount"><span id="inputeAmountError" style="color:red;font-size:12px;padding-left:15px"></span>
								<div style="padding-left: 88px; padding-top: 7px;" class="c_666 f_14">手续费：<i class="c_f00" id="rateAmount">0.00</i> 元</div>
							</div>
						</div>
					<div class="TFS_rechargeBoxR fr" style="height:110px;">
						<h3>账户名称/泰坦码：${cashDeskData.orgName } / ${cashDeskData.titanCode }</h3>
						<p>账户可用余额：<span class="gdt_red">${cashDeskData.balanceusable }<fmt:formatNumber value=""  pattern="#,##0.00#" /></span>元</p>
					</div>
					<div class="clear"></div>
				</div>
				<div class="goldpay_title1">
					<div class="pay_table">
						<ul>
						<c:if test="${not empty cashDeskData.commonPayMethodDTOList  }">
	                          <li class="on">常用</li>
	                       </c:if>
	                       <c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem">
	                           <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 }">
	                               <li>${deskItem.itemName}</li>
	                           </c:if>
	                        </c:forEach>
						</ul>
					</div>
					<div class="paytable_content pay_bank_l">
                    <ul>
                       <c:if test="${not empty  cashDeskData.commonPayMethodDTOList }">
                      <li>
                      <c:forEach items="${cashDeskData.commonPayMethodDTOList }" var="commom" varStatus="status">
								  <div class="paytable_payway" itemType='${commom.paytype}'>
                                    <div class="payc_left"><label class="f_ui-radio-c3">
                                      <input name="r2" type="radio" data-index="${status.index}" class="bankName" value="${commom.bankname}">
                                      <i></i>
                                      <span class="paycleft_img">
                                      <img src="<%=basePath%>/banks/${commom.bankname}.jpg" alt="${commom.bankmark}"
                                           width="159" height="38">
                                       </span></label>
                                    </div>
	                              <c:if test="${commom.paytype == 1 }">
	                                  <span class="payc_title fl"  id="item-${status.index}" data-index="${commom.paytype}">（企业银行）</span>
	                              </c:if>
	                              <c:if test="${commom.paytype == 2 }">
	                                  <span class="payc_title fl" id="item-${status.index }" data-index="${commom.paytype}">（个人银行）</span>
	                              </c:if>
	                              <c:if test="${commom.paytype == 3 }">
	                                  <span class="payc_title fl"  id="item-${status.index}" data-index="${commom.paytype}">（信用卡） </span>
	                              </c:if>
                                  <c:if test="${commom.bankname =='cmbc' &&commom.paytype==1}">
                                    <div class="clear"></div>
								    <div class="payc_ms">
									     <h3><i class="c_f00 mr5">*</i>企业银行客户号：</h3>
									     <input type="text" class="text w_185" placeholder="请输入企业银行客户号" id="customNo-${status.index}">
								    </div>
							      </c:if>
                               </div>
                          </c:forEach>
                          </li>
                         </c:if>
                         <c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem" varStatus="o_status">
                            <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 }">
                                <li>
                                    <c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank" varStatus="i_status">
                                        <div class="paytable_payway" itemType='${deskItem.itemType}'>
                                            <div class="payc_left"><label class="f_ui-radio-c3">
                                                <input name="r2" type="radio" data-index="${o_status.index }-${i_status.index}" class="bankName" value="${itemBank.bankName}">
                                                <i></i>
                                                <span class="paycleft_img">
                                                    <img src="<%=basePath%>/banks/${itemBank.bankName}.jpg" alt="${itemBank.bankMark}"
                                                         width="159" height="38">
                                                </span></label>
                                                
                                            </div>
                                            
                                            <c:if test="${deskItem.itemType == 1 }">
                                                <span class="payc_title fl"  id="item-${o_status.index }-${i_status.index}" data-index="${deskItem.itemType}">（企业银行）</span>
                                            </c:if>
                                            
                                            <c:if test="${deskItem.itemType == 2 }">
                                                <span class="payc_title fl" id="item-${o_status.index }-${i_status.index}" data-index="${deskItem.itemType}">（个人银行）</span>
                                            </c:if>
                                            <c:if test="${deskItem.itemType == 3 }">
                                                <span class="payc_title fl"  id="item-${o_status.index }-${i_status.index}" data-index="${deskItem.itemType}">（信用卡）</span>
                                            </c:if>
                                            <c:if test="${itemBank.bankName=='cmbc' && deskItem.itemType == 1}">
				                                    <div class="clear"></div>
												    <div class="payc_ms">
													     <h3><i class="c_f00 mr5">*</i>企业银行客户号：</h3>
													     <input type="text" class="text w_185" placeholder="请输入企业银行客户号"  id="customNo-${o_status.index }-${i_status.index}">
												    </div>
			                                 </c:if>
                                        </div>
                                   </c:forEach>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                  </div>
				</div> 
			</div>
			<div class="ysgl_bottombut">
				<a class="btn p_lr30 J_password">确定</a>
				<a class="btn btn_exit J_exitKan">取消</a>
			</div>
			<jsp:include page="../checkstand-pay/cashierDeskLimit.jsp"></jsp:include>
 		</div>
	</div>
	
	<form id ="rechargeForm" action="<%=basePath%>/payment/packageRechargeData.action" method="post"  target="_blank">
	   <input name="tradeAmount" id="tradeAmount" type="hidden" value="0.00">
	   <input name="payPassword" id="payPassword" type="hidden" value=""/>
	   <input name="payAmount" id="payAmount" type="hidden" value=""/>
	   <input name="bankInfo" type="hidden" id="bankInfo" value=""/>
	   <input name="linePayType" id="linePayType" type="hidden" value="">
	   <input name="paySource" id="paySource" type="hidden" value="5">
	   <input name="payOrderNo" id="payOrderNo" type="hidden" value="${cashDeskData.payOrderNo}">
	   <input name="deskId" id="deskId" type="hidden" value="${cashDeskData.cashierDeskDTO.deskId}">
	   <input name="payerAcount" id="payerAcount" type="hidden"/>
	   <input name="userid" id="userid" type="hidden" value="${cashDeskData.userId}"/>
	</form>
	
	<form action="<%=basePath%>/account/overview-main.shtml" id="flashPage" target="right_con_frm"></form>
	 
<!--弹窗白色底-->
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<c:if test="${ param.wrapType=='wallet'}">
<div style="height:40px;"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
</c:if>
<script type="text/javascript">
//Run_tab切换

//首先获取充值的支付单号
var payOrderNo = null;
var wrapType = '${ param.wrapType}';//从钱包页面过来的充值
var succUrl = '${ param.succUrl}';//成功后的回显页面
(function($) {
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
    }
})(jQuery);

$("document").ready(function (){
	  //点击某个银行计算费率
       $('.paytable_payway').on('click', function(){
       		var itemType = $(this).attr("itemType");
       		paytable_paywayClick(itemType ,  $('#inputeAmount').val());
       		bankCheckRadio($(this).find('input:radio[name="r2"]'));
       });
	  
       $("#inputeAmount").watch(function(value) {
	        $('.paytable_payway').each(function(){
	           	if($(this).find('input:radio[name="r2"]').is(":checked"))
	           	{
	           		$(this).click();
	           	}
            });
       });

	var firstBank = $(".bankName:first");
	firstBank.attr("checked","0");
	bankCheckRadio(firstBank);
// 	$(".pay_bank_l input[type='radio']").on("click",function(){
		
// 		bankCheckRadio($(this));
// 	});
});



function paytable_paywayClick(itemType , amount){
   
  	if(amount == '' || amount ==  null || parseFloat(amount)<= 0)
   	{
  		 $('#rateAmount').html('0.00');
  		return;
   	}
  	
	var neg = /^[1-9]{1}\d{0,7}(\.\d{1,2})?$/;
	var neg2 = /^[0]{1}(\.\d{1,2})?$/;
	var flag = neg.test(amount)||neg2.test(amount);
	if(flag==false){
		return
	}
  	
	 $.ajax({
  	   type: "get",
       url: "<%=basePath%>/rate/rateCompute.action?userId=${cashDeskData.userId}&amount="+amount+"&payType="+itemType+"&date=" + new Date().getTime(),
       dataType: "json",
       async: false,
       success: function(data){
    	   
    	   $('#rateAmount').html(data.data.exRateAmount);
       }
     }); 
}


//Run_tab切换
function tabChange(tabbtn, tabpannel, tabclass) {
    var $div_li = tabbtn;
    $div_li.click(function () {
        $(this).addClass(tabclass).siblings().removeClass(tabclass);
        var index = $div_li.index(this);
        $(tabpannel).eq(index).show().siblings().hide();
        $($(tabpannel).eq(index).find("input")[0]).click(); 
        $($(tabpannel).eq(index).find(".paytable_payway")[0]).click(); 
    });
}

$(function () {
    //tab
    tabChange($(".pay_table li"), $(".paytable_content ul li"), "on");
   /*  tabChange($(".pay_table li"), $(".paytable_content ul li"), "on"); */
}); 


//点击取消关闭弹框
$(".J_exitKan").on('click', function() {
	if(wrapType=='wallet'){
		window.close();
	}
	top.removeIframeDialog();
});

//设置交易密码
$('.J_password').on('click',function(){	
	//验证是否有交易密码
	 var validate = validate_isBlank();
	 if(validate==false){
		 return;
	 }
	 recharge_Order(); 
});	

function showPayResult(){
	 new top.createConfirm({
		    title:'提示',
			padding: '20px 20px 40px',
			cancelValue : '支付失败',
	        okValue : '支付成功',		
	        content : '<div class="f_14 l_h26 rechargeNotice">您正在用泰坦金融充值，完成操作后，<br/>请确认结果</div>',
			ok : function(){	
				confirmPayResult();
	        },
	        cancel : function(){
	        	confirmPayResult();
	        }
	      }); 	  
	window.top.$(".ui-dialog-close").hide();
}

//主动确认支付结果
function confirmPayResult(){
	$.ajax({//支付页面
      	 type: "post",
           url: "<%=basePath%>/trade/confirmedTrade.action",
           data: {
        	   payOrderNo:'${cashDeskData.payOrderNo}',
        	   paySource:'5'
           },
           dataType: "json",
           success: function (data) {
        	   if(data.result=="success"){
        		   if(succUrl.length>0){
        			   window.location.href=succUrl;
        			   return;
        		   }
        		   if(wrapType=='wallet'){
        			   window.close();
        			   return;
        		   }
        		   top.F.loading.show();
                   setTimeout(function () {
                       top.F.loading.hide();
                       top.removeIframeDialog();
                   }, 1000);
                   
                   try
                   {
                	   top.frames["right_con_frm"].location.reload();
                	   
                   }
                   catch(e)
                   {
                	   window.frames["right_con_frm"].location.reload();
                   }
        	   }
        	}
    });
}

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=parent.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=parent.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName='/TFS/';
    return(localhostPaht+projectName);
}



function recharge_Order(){
	//封装到form中
	put_value_to_form();
	//提交表单
	
	//确认支付结果
	showPayResult();
}

function put_value_to_form(recharge_data){
	var recharge_data=save_recharge_data();
	$("#payPassword").val(recharge_data.payPassword);
	$("#tradeAmount").val(recharge_data.amount);
	$("#payAmount").val(recharge_data.amount);
	$("#bankInfo").val(recharge_data.bankInfo);
	$("#linePayType").val(recharge_data.linePayType);
	$("#payerAcount").val(recharge_data.payerAccount);
	$("#rechargeForm").submit(); 
}

function save_recharge_data(){
	var amount = $("#inputeAmount").val();
	var bankInfo =  $(".bankName:checked").val();
	
	if(typeof(bankInfo) == "undefined"){
		bankInfo =null;
     }
	var payPassword = null;
	if("undefined" != typeof PasswordStr2){
		payPassword = PasswordStr2.returnStr();
	}
	
	var itemType = $(".bankName:checked").attr("data-index");
	var linePayType =null;
	if("undefined" != typeof itemType){
		linePayType =   $("#item-"+itemType).attr("data-index");
	}
	var payerAccount = null;
	var value=$('input:radio[name=r2]:checked').val();
	if(value=='cmbc' && linePayType=="1"){
		var dataIndex = $('input:radio[name=r2]:checked').attr("data-index");
		payerAccount = $("#customNo-"+dataIndex).val();
		
		var errMsg ="";
		if(payerAccount.length<1){
			errMsg="民生企业银行客户号不能为空";
		}else if(payerAccount.length>32){
			errMsg="民生企业银行客户号不能超过32位";
		}else{
			var reg = /^([a-z]|[A-Z]|[0-9]){1,32}$/;
			if(!reg.test(payerAccount)){
				errMsg="民生企业银行客户号输入有误";
			};
		}
		
		if(errMsg.length>0){
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
			  return ;
		};
	}
	//生成一个支付单号
	var data={
		amount:amount,
		bankInfo:bankInfo,
		/* payPassword:rsaData(payPassword), */
		payPassword:payPassword,
		linePayType:linePayType,
		payerAccount:payerAccount
	};
	return data;
}

function getRechargePayOrderNo(){
	$.ajax({
		dataType: "json",
        context: document.body,
        async:false,
        url: '<%=basePath%>/trade/genLoacalPayOrderNo.action',
        success: function (data) {
        	payOrderNo =  data.result;
        }
    });
}


function validate_isBlank(){
	var inputeAmount = $("#inputeAmount").val();
	if($.trim(inputeAmount).length<1){
		 $("#inputeAmountError").text("充值金额不能为空");
		 $('#rateAmount').html('0.00');
		return false;
	}
	 $("#inputeAmountError").text("");
	return true;
}

$("#inputeAmount").blur(function(){
	var inputeAmount = $(this).val();
	if($.trim(inputeAmount).length<1){
		 $("#inputeAmountError").text("充值金额不能为空");
		 $('#rateAmount').html('0.00');
		 return;
	}else{
		 $("#inputeAmountError").text("");
	}
	
	var strArry = inputeAmount.split(".");
	if(strArry[0].length>8){
		$("#inputeAmountError").text("对不起，本系统支持的最大充值金额为99,999,999");
		$(this).val("");
		$(this).focus();
		 $('#rateAmount').html('0.00');
		return;
	}
	
	/* var neg = /^\d+(\.\d{1,2})?$/; */
	var neg = /^[1-9]{1}\d{0,7}(\.\d{1,2})?$/;
	var neg2 = /^[0]{1}(\.\d{1,2})?$/;
	var flag = neg.test($(this).val())||neg2.test($(this).val());
	if(flag==false){
		$("#inputeAmountError").text("输入金额无法识别,正确格式如xx或xx.xx");
		$(this).val("");
		$(this).focus();
		 $('#rateAmount').html('0.00');
		return
	}else{
		$("#inputeAmountError").text("");
	}
});

//判断如果是民生银行出现下拉框
$('.paytable_payway input').on('change',function(){
	var _this=$(this);
	var value=$('input:radio[name=r2]:checked').val();;
	//获取
	var dataIndex = $('input:radio[name=r2]:checked').attr("data-index");
	var linePayType =   $("#item-"+dataIndex).attr("data-index");
	if(value=='cmbc' && linePayType==1){
		 _this.parents('.paytable_payway').find('.payc_ms').slideDown(); 
	}else{
		 $('.paytable_payway').find('.payc_ms').slideUp(); 
	}
	/* if(value=="cmbc"){
		$.ajax({
	        dataType : 'html',
	        context: document.body,
	        url : '微信支付.html',			
	        success : function(html){
				top.F.loading.hide();
	            var d =  window.top.dialog({
	                title: ' ',
	                padding: '0 0 0px 0',
					width: 560,
	                content: html,
	                skin : 'saas_pop',  
	            }).showModal();
	            $('.wx_close').on('click',function(){
	            	d.remove();
	            });
	        }
	    });
	} */
});
</script>
</body>
</html>
