<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
    <jsp:include page="/comm/static-js.jsp"></jsp:include>
</head>
<body>
<!--弹窗白色底-->
	<div class="other_popup">
		<div class="other_popup_title">
			<div class="other_popup_title2">
				<span class="visual"></span>
				充值
			</div>
		</div>
		<div class="S_popup_Kan clearfix opaque">
			<div class="gold_pay">
				
				<div class="TFS_rechargeBox">
					<div class="TFS_rechargeBoxL fl">
						充值金额：<input type="text" class="text w_300" id="inputeAmount"><span id="inputeAmountError" style="color:red;font-size:13px"></span>
					</div>
					<div class="TFS_rechargeBoxR fr">
						<h3>账户名称/泰坦码：${financialOrgan.orgName } / ${financialOrgan.titanCode }</h3>
						<p>账户可用余额：<span class="gdt_red"><fmt:formatNumber value="${accountBalance.balanceusable }"  pattern="#,##0.00#" /></span>元</p>
						<!-- <p>泰坦增值宝可用余额：<span class="gdt_red">10,000.00</span>元</p> -->
					</div>
					<div class="clear"></div>
				</div>
				<div class="goldpay_title1">			
					<div class="pay_table">
						<ul>
						<c:if test="${commomPayMethod !=null }">
	                          <li class="on">常用</li>
	                       </c:if>
	                       <c:forEach items="${cashierDesk.cashierDeskItemDTOList }" var="deskItem">
	                           <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 }">
	                               <li>${deskItem.itemName}</li>
	                           </c:if>
	                        </c:forEach>
						</ul>
					</div>
					<div class="paytable_content pay_bank_l">
						<ul>
						<c:if test="${commomPayMethod !=null }">
							<li>
								<c:forEach items="${commomPayMethod }" var="commom" varStatus="status">
								<c:if test="${commom.bankname !='cmbc'}">
								  <div class="paytable_payway">
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
                               </div>
						   </c:if>
                          </c:forEach>
                         
                          <c:forEach items="${commomPayMethod }" var="commom"  varStatus="status">
                               <c:if test="${commom.bankname =='cmbc'}">
								  <div class="paytable_payway">
                                    <div class="payc_left"><label class="f_ui-radio-c3">
                                      <input name="r2" type="radio" data-index="i_${status.index}" class="bankName" value="${commom.bankname}">
                                      <i></i>
                                      <span class="paycleft_img">
                                      <img src="<%=basePath%>/banks/${commom.bankname}.jpg" alt="${commom.bankmark}"
                                           width="159" height="38">
                                       </span></label>
                                    </div>
	                              <c:if test="${commom.paytype == 1 }">
	                                  <span class="payc_title fl"  id="item-i_${status.index}" data-index="${commom.paytype}">（企业银行）</span>
	                              </c:if>
	                              <c:if test="${commom.paytype == 2 }">
	                                  <span class="payc_title fl" id="item-i_${status.index }" data-index="${commom.paytype}">（个人银行）</span>
	                              </c:if>
	                              <c:if test="${commom.paytype == 3 }">
	                                  <span class="payc_title fl"  id="item-i_${status.index}" data-index="${commom.paytype}">（信用卡） </span>
	                              </c:if>
	                              <c:if test="${commom.bankname =='cmbc' &&commom.paytype==1}">
                                    <div class="clear"></div>
								    <div class="payc_ms">
									     <h3><i class="c_f00 mr5">*</i>企业银行客户号：</h3>
									     <input type="text" class="text w_185" placeholder="请输入企业银行客户号" id="customNo-i_${status.index}">
								    </div>
							      </c:if>
                               </div>
						   </c:if>
                           </c:forEach>

							</li>
							</c:if>
						  <c:forEach items="${cashierDesk.cashierDeskItemDTOList }" var="deskItem" varStatus="o_status">
                            <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 }">
                                <li>
                                    <c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank" varStatus="i_status">
                                      <c:if test="${itemBank.bankName !='cmbc'}">
                                        <div class="paytable_payway">
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
                                        </div>
                                       </c:if>
                                   </c:forEach>
                                  <c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank" varStatus="d_status">
                                    <c:if test="${itemBank.bankName=='cmbc'}">
                                        <div class="paytable_payway">
                                            <div class="payc_left"><label class="f_ui-radio-c3">
                                                <input name="r2" type="radio" data-index="d_${o_status.index }-${d_status.index}" class="bankName" value="${itemBank.bankName}">
                                                <i></i>
                                                <span class="paycleft_img">
                                                    <img src="<%=basePath%>/banks/${itemBank.bankName}.jpg" alt="${itemBank.bankMark}"
                                                         width="159" height="38">
                                                </span></label>
                                                
                                            </div>
                                            
                                            <c:if test="${deskItem.itemType == 1 }">
                                                <span class="payc_title fl"  id="item-d_${o_status.index }-${d_status.index}" data-index="${deskItem.itemType}">（企业银行）</span>
                                            </c:if>
                                            
                                            <c:if test="${deskItem.itemType == 2 }">
                                                <span class="payc_title fl" id="item-d_${o_status.index }-${d_status.index}" data-index="${deskItem.itemType}">（个人银行）</span>
                                            </c:if>
                                            <c:if test="${deskItem.itemType == 3 }">
                                                <span class="payc_title fl"  id="item-d_${o_status.index }-${d_status.index}" data-index="${deskItem.itemType}">（信用卡）</span>
                                            </c:if>
                                            <c:if test="${itemBank.bankName=='cmbc' && deskItem.itemType == 1}">
				                                    <div class="clear"></div>
												    <div class="payc_ms">
													     <h3><i class="c_f00 mr5">*</i>企业银行客户号：</h3>
													     <input type="text" class="text w_185" placeholder="请输入企业银行客户号"  id="customNo-d_${o_status.index }-${d_status.index}">
												    </div>
			                                 </c:if>
                                        </div>
                                      </c:if>
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
			
		
 		</div>
	</div>
	
	<form id ="rechargeForm" action="<%=basePath%>/trade/genRechargeData.action" method="post"  target="_blank">
	   <input name="payPassword" id="payPassword" type="hidden" value=""/>
	   <input name="payAmount" id="payAmount" type="hidden" value=""/>
	   <input name="bankInfo" type="hidden" id="bankInfo" value=""/>
	   <input name="linePayType" id="linePayType" type="hidden" value="">
	   <input name="paySource" id="paySource" type="hidden" value="5">
	   <input name="payOrderNo" id="payOrderNo" type="hidden" value="">
	   <input name="payerAcount" id="payerAcount" type="hidden" value="">
	   <input name="deskId" id="deskId" type="hidden" value="${cashierDesk.deskId}">
	</form>
	
	<form action="<%=basePath%>/account/overview-main.shtml" id="flashPage" target="right_con_frm"></form>
	 
<!--弹窗白色底-->
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script>
//Run_tab切换

//首先获取充值的支付单号
var payOrderNo = null;

$("document").ready(function (){
	getRechargePayOrderNo();
	var firstBank = $(".bankName:first");
	firstBank.attr("checked","0");
});


function tabChange(tabbtn, tabpannel, tabclass) {
    var $div_li = tabbtn;
    $div_li.click(function() {
        $(this).addClass(tabclass).siblings().removeClass(tabclass);
        var index = $div_li.index(this);
        $(tabpannel).eq(index).show().siblings().hide();
    });
}
$(function(){
    //tab
	tabChange($(".pay_table li"),$(".paytable_content ul li"), "on");	
	tabChange($(".pay_table li"),$(".paytable_content ul li"), "on");
});


//点击取消关闭弹框
$(".J_exitKan").on('click', function() {
	top.removeIframeDialog();
});

//检查是否有交易密码
 <%-- $.ajax({
    	 type: "post",
         url: "<%=basePath%>/account/checkIsSetPayPassword.action",
         dataType: "json",
         success: function(data){
        	 if(data.result=="success"){
        		 $.ajax({
        		        dataType: 'html',
        		        context: document.body,
        		        url: '<%=basePath%>/account/showSetPayPassword.action',
        		        success: function (html) {
        		        	setPayPassword(html);
        		        }
        		    });
        	 }
        	}
        });  --%>
	
	
//设置交易密码
$('.J_password').on('click',function(){	
	//验证是否有交易密码
	 var validate = validate_isBlank();
	 if(validate==false){
		 return;
	 }
	 recharge_Order(); 
});	

<%-- var is_Input_Password= validate_isInput_password();
if(is_Input_Password==false){
	 $.ajax({
           dataType: 'html',
           context: document.body,
           url: '<%=basePath%>/account/showPayPassword.action',
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
                           	//获取密码
                           	recharge_Order();
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
}else{ 

}  --%>

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
        	   payOrderNo:payOrderNo,
        	   paySource:'5'
           },
           dataType: "json",
           success: function (data) {
        	   if(data.result=="success"){
        		   top.F.loading.show();
                   setTimeout(function () {
                       top.F.loading.hide();
                      /*  new top.Tip({msg: data.msg, type: 1, time: 1000}); */
                       top.removeIframeDialog();
                   }, 1000);
                   $("#flashPage").submit();
        	   }
        	}
    });
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
	$("#payAmount").val(recharge_data.amount);
	$("#bankInfo").val(recharge_data.bankInfo);
	$("#linePayType").val(recharge_data.linePayType);
	$("#payOrderNo").val(payOrderNo);
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
		payerAccount:payerAccount,
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


<%-- function validate_isInput_password(){
	var flag = false;
	 $.ajax({
		 dataType: "json",
         context: document.body,
         async:false,
         url: '<%=basePath%>/trade/allownopwdpay.action',
         data:{
        	 totalAmount :$("#inputeAmount").val(),
         },
         success: function (data) {
        	 if(data.result =="success"){
        		 flag =  true;
        	 }
         }
     });
	return flag;
} --%>

function validate_isBlank(){
	var inputeAmount = $("#inputeAmount").val();
	if($.trim(inputeAmount).length<1){
		 $("#inputeAmountError").text("充值金额不能为空");
		return false;
	}
	 $("#inputeAmountError").text("");
	return true;
}

$("#inputeAmount").blur(function(){
	var inputeAmount = $(this).val();
	if($.trim(inputeAmount).length<1){
		 $("#inputeAmountError").text("充值金额不能为空");
		 return;
	}else{
		 $("#inputeAmountError").text("");
	}
	
	var strArry = inputeAmount.split(".");
	if(strArry[0].length>8){
		$("#inputeAmountError").text("对不起，本系统支持的最大充值金额为99,999,999");
		 $(this).val(""); 
		$(this).focus();
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

//设置交易密码
<%-- function setPayPassword(html){
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
                			         url: "<%=basePath%>/account/setPayPassword.action",
                			         data: {
                			        	 payPassword:rsaData(PasswordStr.returnStr())
                			         },
                			         dataType: "json",
                			         success: function(data){
                			        	 if(data.result=="success"){
                			        		top.F.loading.show();
         		                            setTimeout(function () {
         		                                top.F.loading.hide();
         		                                new top.Tip({msg: '密码设置成功！', type: 1, time: 1000});
         		                            }, 1000);
                			        	 }else{
                			        			top.F.loading.show();
             		                            setTimeout(function () {
             		                                top.F.loading.hide();
             		                                new top.Tip({msg: data.msg, type: 1, time: 1000});
             		                            }, 1000);
                			        	 }
                			         }
                			   })
                		}
                	}
                },
                autofocus: true
            },

        ]
    }).showModal();
}
 --%>
</script>
</body>
</html>
