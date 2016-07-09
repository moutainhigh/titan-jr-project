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
						<p>账户可用余额：<span class="gdt_red">${accountBalance.balanceusable }</span>元</p>
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
					<div class="paytable_content">
						<ul>
						<c:if test="${commomPayMethod !=null }">
							<li>
								<c:forEach items="${commomPayMethod }" var="commom">
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
                                  <span class="payc_title fl"  id="item-${status.index}" data-index="${commom.paytype}">（信用卡）</span>
                              </c:if>
                             </div>
                          </c:forEach>
							</li>
							</c:if>
							     <c:forEach items="${cashierDesk.cashierDeskItemDTOList }" var="deskItem" varStatus="status">
                            <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 }">
                                <li>
                                    <c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank">
                                        <div class="paytable_payway">
                                            <div class="payc_left"><label class="f_ui-radio-c3">
                                                <input name="r2" type="radio" data-index="${status.index}" class="bankName" value="${itemBank.bankName}">
                                                <i></i>
                                                <span class="paycleft_img">
                                                    <img src="<%=basePath%>/banks/${itemBank.bankName}.jpg" alt="${itemBank.bankMark}"
                                                         width="159" height="38">
                                                </span></label>
                                            </div>
                                            <c:if test="${deskItem.itemType == 1 }">
                                                <span class="payc_title fl"  id="item-${status.index}" data-index="${deskItem.itemType}">（企业银行）</span>
                                            </c:if>
                                            <c:if test="${deskItem.itemType == 2 }">
                                                <span class="payc_title fl" id="item-${status.index }" data-index="${deskItem.itemType}">（个人银行）</span>
                                            </c:if>
                                            <c:if test="${deskItem.itemType == 3 }">
                                                <span class="payc_title fl"  id="item-${status.index}" data-index="${deskItem.itemType}">（信用卡）</span>
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
 		</div>
	</div>
	
	<form id ="rechargeForm" action="<%=basePath%>/trade/genRechargeData.action" method="post"  target="_blank">
	   <input name="payPassword" id="payPassword" type="hidden" value=""/>
	   <input name="payAmount" id="payAmount" type="hidden" value=""/>
	   <input name="bankInfo" type="hidden" id="bankInfo" value=""/>
	   <input name="linePayType" id="linePayType" type="hidden" value="">
	   <input name="paySource" id="paySource" type="hidden" value="5">
	   <input name="payOrderNo" id="payOrderNo" type="hidden" value="">
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
	$(".bankName:first").attr("checked",'0');
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
		 alert("必须输入金额");
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
                       new top.Tip({msg: data.msg, type: 1, time: 1000});
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
	//生成一个支付单号
	var data={
		amount:amount,
		bankInfo:bankInfo,
		payPassword:rsaData(payPassword),
		linePayType:linePayType
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
		return false;
	}
	return true;
}

$("#inputeAmount").blur(function(){
	var inputeAmount = $(this).val();
	if($.trim(inputeAmount).length<1){
		 $("#inputeAmountError").text("收款方泰坦码不能为空");
	}else{
		 $("#inputeAmountError").text("");
	}
	
	var neg = /^([+-]?)((\d{1,3}(,\d{3})*)|(\d+))(\.\d{2})?$/;
	var flag = neg.test($(this).val());
	if(flag==false){
		$("#inputeAmountError").text("输入金额无法识别,正确格式如xx或xx.xx");
		$(this).val("");
		$(this).focus();
	}else{
		$("#inputeAmountError").text("");
	}
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
