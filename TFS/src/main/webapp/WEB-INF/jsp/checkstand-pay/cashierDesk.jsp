<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>SAAS后台管理</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	
</head>
<body>
<!--弹窗白色底-->
<div class="other_popup">
    <div class="other_popup_title">
        <div class="other_popup_title2">
            <span class="visual"></span>
            付款
        </div>
    </div>

    <div class="S_popup_Kan clearfix opaque">
        <div class="gold_pay">
           <c:if test="${not empty gDPOrderDTO }">
              <div class="goldpay_title">付款金额：<span class="gdt_red" id="pay_totalAmount"><fmt:formatNumber value="${gDPOrderDTO.orderSum  }"  pattern="#,##0.00#" /></span>元</div>
           </c:if>
           <c:if test="${not empty orderDTO }">
              <div class="goldpay_title">付款金额：<span class="gdt_red" id="pay_totalAmount"><fmt:formatNumber value="${orderDTO.payAmount  }"  pattern="#,##0.00#" /></span>元</div>
           </c:if>
            <div class="goldpay_top">
                <ul>
                    <li id="not_exists_history">
                        <div class="goldpay">
                            <div>
                                <span class="w_160"><i class="c_f00">*</i>收款账户（公司名称）：</span>
                                <input type="text" class="text w_250" id="reOrgName" dataType="*" errorMsg="收款账户不能为空"><span id="reOrgNameError" style="color:red"></span>
                            </div>
                            <div>
                                <span class="w_160"><i class="c_f00">*</i>收款方 - 泰坦码：</span>
                                <input type="text" class="text w_250" id="reTitanCode" dataType="*" errorMsg="泰坦码不能为空"><span id="reTitanCodeError" style="color:red"></span>
                            </div>
                        </div>
                    </li>
                    <li class="goldpay_replace" id="exists_history">
                        <div class="goldpay_card"><img src="<%=cssSaasPath%>/images/TFS/card.png" alt=""></div>
                        <div class="goldpay_custom">
                            <div>
                                <span class="w_160"><i class="c_f00">*</i>收款账户（公司名称）：</span>
                                <c:choose>
                                  <c:when test="${ not empty accountHistory }">
                                    <input type="hidden" id="hiddenAccountName" value="${accountHistory.orgname}">
                                    <span id="showAccountName">${accountHistory.orgname}</span> 
                                    <span class="blue p_l18 curpo J_payother">付款给其他账户&gt;&gt;</span>
                                  </c:when>
                                  <c:otherwise>
                                    <c:if test="${not empty  orgDTO}">
                                      <input type="hidden" id="hiddenAccountName" value="${orgDTO.orgname}">
                                       <span id="showAccountName">${orgDTO.orgname}</span> 
                                    </c:if>
                                  </c:otherwise> 
                                </c:choose>
                            </div>
                          <div>
                                <span class="w_160"><i class="c_f00">*</i>收款方 - 泰坦码：</span>
                                <c:choose>
                                  <c:when test="${ not empty  accountHistory}">
                                     <input type="hidden" id="hiddenTitanCode" value="${userIDOrgMap[accountHistory.payeeuserid].titanCode} ">
                                     <span id="showTitanCode">${userIDOrgMap[accountHistory.payeeuserid].titanCode}</span>
                                  </c:when>
                                 <c:otherwise>
                                    <c:if test="${not empty  orgDTO}">
                                       <input type="hidden" id="hiddenTitanCode" value="${orgDTO.titancode} ">
                                     <span id="showTitanCode">${orgDTO.titancode}</span>
                                    </c:if>
                                  </c:otherwise>
                                </c:choose>
                            </div> 
                        </div>
                    </li>
                    <c:forEach items="${cashierDesk.cashierDeskItemDTOList }" var="deskItem">
                        <c:if test="${deskItem.itemType == 4}">
                            <input type="hidden" id="canUseAccBalance" value="1">
                            <c:if test="${ not empty fcUserid}">
                            <li class="p_l27">
                                <label class="f_ui-checkbox-c3 p_r10">
                                    <input type="checkbox" checked="" id="d_checkbox" onclick="checktest()" ><i ></i>
                                    使用账户可用余额付款</label>丨
                                <span class="p_l10">账户可用余额：<fmt:formatNumber value="${accountBalance.balanceusable }"  pattern="#,##0.00#" />元</span>
                            </li>
                            </c:if>
                            
                        </c:if>
                    </c:forEach>

                </ul>
            </div>
            <input type="hidden" id="onlinePayAmount" name="onlinePayAmount"><!--通过网银充值并支付的金额-->
              <div class="goldpay_title1" style="border-bottom:#ddd 1px solid;">
               <c:if test="${ not empty fcUserid}">
                <div class="goldpaytitle1_top" id="not_enough_amount">剩余余额：<!--账户余额不够用余额付款-->
                    <span class="c_f00" id="pay_surplus_amount"><fmt:formatNumber value="${orderDTO.payAmount - accountBalance.balanceusable}"  pattern="#,##0.00#" /></span>元
                    <span class="p_l27">使用以下方式付款：</span>
                </div>
                </c:if>

                <div class="goldpaytitle1_top blue J_payway" id="enough_amount"><!--账户余额足够但是不用余额付款-->
                    <span class="payway_other blue">使用其他方式付款</span>
                    <i class="jiantou"></i>
                </div>
                <div class="goldpayway" id="useCashierDeskPay">
                  <div class="pay_table">
                    <ul>
                        <c:if test="${not empty  commomPayMethod}">
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
                     <c:if test="${not empty  commomPayMethod }">
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
        </div>
        <div class="ysgl_bottombut">
            <div>
                <a class="btn p_lr30 J_password">确定</a>
                <a class="btn btn_exit J_exitKan">取消</a>
            </div>
        </div>
    </div>
</div>

<div>
  <form id="onlinePaymentForm" method="post" action="<%=basePath%>/trade/genRechargeData.action">
    <input name="payPassword" id="payPassword" type="hidden" value=""/>
    <input name="merchantcode" type="hidden" value="${merchantcode}"/>
    <input name="payOrderNo" type="hidden" value="${payOrderNo}"/>
    <input name="transferAmount" id="transferAmount" type="hidden" value=""/>
    <input name="payAmount" id="payAmount" type="hidden" value=""/>
    <input name="recieveOrgName" id="recieveOrgName" type="hidden" value=""/>
    <input name="recieveTitanCode" id="recieveTitanCode" type="hidden" value=""/>
    <input name="bankInfo" type="hidden" id="bankInfo" value=""/>
    <input name="fcUserid" type="hidden" value="${fcUserid}"/>
    <input name="userid" type="hidden" value="${userId}"/>
    <input name="linePayType" id="linePayType" type="hidden" value="">
    <input name="creator" id="creator" type="hidden" value="${operator}">
    <input name="paySource" id="paySource" type="hidden" value="${paySource}">
    <input name="deskId" id="deskId" type="hidden" value="${cashierDesk.deskId}">
    <input name="isEscrowed" id="isEscrowed" type="hidden" value="${isEscrowed}">
    <input name="escrowedDate" id="escrowedDate" type="hidden" value="${escrowedDate}">
  </form>
</div>

<form action="<%=basePath%>/trade/payConfirmPage.action" id="confirmOrder">
  <input name="orderNo" id="orderNo" type="hidden">
</form>

<!--弹窗白色底-->
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script src="<%=cssSaasPath%>/js/password.js"></script>
<script>
    $("document").ready(function (){
    	//每次刷新只要余额足够，默认选中
        if ('${accountHistory}'.length>0 || '${orgDTO}'.length>0){
            $("#exists_history").show();
            $("#not_exists_history").remove();
        } else {
            $("#exists_history").remove();
            $("#not_exists_history").show();
        }
        if ('${orderDTO.payAmount}' - '${accountBalance.balanceusable}' <= 0){
            $("#useCashierDeskPay").hide();
            $("#enough_amount").show();
            $("#not_enough_amount").hide();
            $("#onlinePayAmount").val('${orderDTO.payAmount}');
            $("#d_checkbox").attr("checked",true);
        } else {
            $("#useCashierDeskPay").show();
            $("#enough_amount").hide();
            $("#not_enough_amount").show();
            $("#onlinePayAmount").val(sub('${orderDTO.payAmount}', '${accountBalance.balanceusable}'));
        }
        
        $(".bankName:first").attr("checked",'0');
    });

    function sub(a, b) {
        var c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
       
       return e = Math.pow(10, Math.max(c, d)), (mul(a, e) - mul(b, e)) / e;
    }
    
    function mul(a, b) {//改进的乘法
        var c = 0,
            d = a.toString(),
            e = b.toString();
        try {
            c += d.split(".")[1].length;
        } catch (f) {}
        try {
            c += e.split(".")[1].length;
        } catch (f) {}
        return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
    }
    
    function checktest() {
    	//如果余额足够则只能用余额或者网银付款，二选一，如果余额不足则自由选择
    	if (sub('${orderDTO.payAmount}','${accountBalance.balanceusable}')<= 0){
    		 if($("#d_checkbox").attr("checked")=="checked"){
    			 var arrow = $('.J_payway').find('i');
    			 var className = arrow.attr("class");
    			 if(className =="jiantou"){
    				 conPayWay();
    			 }
    			 $(".bankName:first").attr("checked",'0');
    	       }else{
    	    	 var arrow = $('.J_payway').find('i');
      			 var className = arrow.attr("class");
      			 if(className !="jiantou"){
      				conPayWay();
      			 }
    	       }
    	}else{//余额不足，将支持两种结合的方式或者选择充值
    		 if($("#d_checkbox").attr("checked")=="checked"){//在线支付剩余余额
    			 $("#pay_surplus_amount").text('${orderDTO.payAmount}');
    		 }else{//在线支付全款
    			 $("#pay_surplus_amount").text(sub('${orderDTO.payAmount}','${accountBalance.balanceusable}'));
    		 }
    	}
    }

    function conPayWay(){
    	  var arrow = $(".J_payway").find('i');
	      $(".J_payway").next(".goldpayway").toggle();
	      arrow.toggleClass('jiantou');
	      $(".payway_other").text('使用其他方式付款');
    }
    
    $(".J_payother").on('click', function() {
        var backcont=null;
        $.ajax({
            dataType : 'html',
            context: document.body,
            data: {payeruserid:'${accountHistory.payeruserid}',
                inaccountcode:'${accountHistory.inaccountcode}',
                outaccountcode:'${accountHistory.outaccountcode}'},
            url : "<%=basePath%>/account/selectAccHistory.action",
            success : function(html){
                var d =  dialog({
                    title: ' ',
                    padding: '0 0 0px 0 ',
                    content: html,
                    skin : 'saas_pop',

                }).showModal();
                $(".J_chose").on('click', function() {
                    d.remove();
                    var textinput='<li class="replanceArea">'+
                            ' <div class="goldpay"><div>'+
                            ' <span class="w_160"><i class="c_f00">*</i>收款账户（公司名称）：</span>' +
                            ' <input type="text" id="reOrgName" class="text w_250" ></div><div>' +
                            ' 	<span class="w_160"><i class="c_f00">*</i>收款方 - 泰坦码：</span>'+
                            '  <input type="text" id="reTitanCode"  class="text w_250"></div></div>'	+
                            '</li>';
                    $(".goldpay_top ul").children('.goldpay_replace').replaceWith(textinput);
                    
                });
               $(".J_close").on('click', function() {
                    d.remove();
                    backcont=$(this).prev().html();
                    var arr = backcont.split("<br>");
                    $("#showAccountName").text(arr[0]);
                    $("#showTitanCode").text(arr[1]);
                    //给隐藏域赋值
                    $("#hiddenAccountName").val(arr[0]);
                    $("#hiddenTitanCode").val(arr[1]);
                }); 
            }
        });
    });
    //Run_tab切换
    function tabChange(tabbtn, tabpannel, tabclass) {
        var $div_li = tabbtn;
        $div_li.click(function () {
            $(this).addClass(tabclass).siblings().removeClass(tabclass);
            var index = $div_li.index(this);
            $(tabpannel).eq(index).show().siblings().hide();
        });
    }
    $(function () {
        //tab
        tabChange($(".pay_table li"), $(".paytable_content ul li"), "on");
        tabChange($(".pay_table li"), $(".paytable_content ul li"), "on");
    });

    //支付方式下拉显示隐藏
    $('.J_payway').on('click',function(){
        var arrow = $(this).find('i');
        $(this).next(".goldpayway").toggle();
        arrow.toggleClass('jiantou');
        if(arrow.hasClass("jiantou")){
        	if('${orderDTO.payAmount}' - '${accountBalance.balanceusable}' <= 0){//余额充足
        		  $("#d_checkbox").attr("checked",true);
        	}
            $(".payway_other").text('使用其他方式付款');
           
        }else{
        	if('${orderDTO.payAmount}' - '${accountBalance.balanceusable}' <= 0){//余额充足
      		  $("#d_checkbox").removeAttr("checked");
      	     }
            $(".payway_other").text('使用其他方式付款');
        }
        return false;
    });
   
    if('${paySource}'!="1"){
    	checkIsSetPayPassword();
    }
    
   function checkIsSetPayPassword(){
    	 $.ajax({
        	 type: "post",
             url: "<%=basePath%>/account/checkIsSetPayPassword.action",
             data: {fcUserid:'${fcUserid}'},
             dataType: "json",
             success: function(data){
            	 if(data.result=="success"){
            		 show_set_payPassword();
            	 }
            	}
            }); 
    }
  
    $('.J_exitKan').on('click',function(){
		window.close();
	});
		
    
    function show_set_payPassword(){
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
		                        skin: 'btn p_lr30',
		                        callback: function () {
		                        	if(PasswordStr.returnStr()==PasswordStr1.returnStr()){
		                        		if(PasswordStr.returnStr().length==6){
		                        			   $.ajax({
		                        			    	 type: "post",
		                        			         url: "<%=basePath%>/account/setPayPassword.action",
		                        			         data: {
		                        			        	 fcuserid:'${fcUserid}',
		                        			        	/*  payPassword:rsaData(PasswordStr.returnStr()) */
		                        			        	 payPassword:PasswordStr.returnStr()
		                        			         },
		                        			         dataType: "json",
		                        			         success: function(data){
		                        			        	 if(data.result=="success"){
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
		                        			   })
		                        		}else{
		                        			 new top.Tip({msg: "密码必须为6位", type: 1, timer: 1000});
			                        		 setTimeout(function () {
			                        			 checkIsSetPayPassword();
	       		                            }, 1000);
		                        		}
		                        	}else{
		                        		 new top.Tip({msg: "两次输入的密码不一致", type: 1, timer: 1000});
		                        		 setTimeout(function () {
		                        			 checkIsSetPayPassword();
       		                            }, 1000);
		                        		
		                        	}
		                        },
		                        autofocus: true
		                    },

		                ]
		            }).showModal();
		        }
		    });
    }
    
    function closeWindow(){
    	var userAgent = navigator.userAgent;
        if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
        	   window.location.href="about:blank";
        	   window.close();
        } else {
           window.opener = null;
           window.open("", "_self");
           window.close();
        }
    }
    
    function showPayResultDialog(){
    	  new top.createConfirm({
              title: '提示',
              padding: '20px 20px 40px',
              cancelValue: '支付失败',
              okValue: '支付成功',
              content: '<div class="f_14 l_h26 rechargeNotice">您正在用.....支付，完成操作后，<br/>请确认结果</div>',
              ok: function () {
              	confirmPayResult();
              },
              cancel: function () {
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
            	   payOrderNo:'${payOrderNo}',
            	   paySource:'${paySource}'
               },
               dataType: "json",
               success: function (data) {
            	   if(data.result=="success"){
            		   top.F.loading.show();
            		   new top.Tip({msg: data.msg, type: 1, timer: 2000});
                       setTimeout(function () {
                           top.F.loading.hide();
                           top.removeIframeDialog();
                       }, 2000);
            	   }
            	}
        });
    }
    
    //点击确定弹出输入密码
    $(".J_password").on('click', function () {
    	//验证收款账户和泰坦码
        var validate = validate_isBlank();
    	if(validate==false){
    		return ;
    	}
    	var flag = validate_isInput_password();
    	if(flag==false){
    		show_payPassword();
    	}else{
    		pay_Order(); 
    	}
    });
    
    function show_payPassword(){
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
                            	//验证支付密码是否准确
                            	check_payPassword();
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
    }
    
    function check_payPassword(){
    	 $.ajax({
             type: "post",
             dataType: 'json',
             url: '<%=basePath%>/setting/check_payPassword.action',
             data: {
            	 payPassword:PasswordStr2.returnStr(),
            	 fcUserid:'${fcUserid}'
             },
             success: function (data) {
            	 if(data.code=="1"){
            		 pay_Order();
            	 }else{
            		new top.Tip({msg: '输入的密码错误', type: 1, timer: 2000});
            		  setTimeout(function () {
            			  show_payPassword();
                      }, 2000);
            		
            	 }
             },error:function(data){
            	 alert(data.code);
            	 alert("344");
             }
    	 });
    }
    
    
    function pay_Order(){
    	//获取数据
   	    var pay_date=save_payDate();
    	alert("1111");
    	alert(pay_date.recieveOrgName);
    	alert(pay_date.recieveTitanCode);
        if(pay_date.payAmount =="0"){
    		$.ajax({//支付页面
           	 type: "post",
                url: "<%=basePath%>/trade/showTitanPayPage.action",
                data: {
               	 payPassword:pay_date.payPassword,
               	 merchantcode:'${merchantcode}',
               	 payOrderNo:'${payOrderNo}',
               	 transferAmount:pay_date.transferAmount,
               	 payAmount:pay_date.payAmount,
               	 recieveOrgName:pay_date.recieveOrgName,
               	 recieveTitanCode:pay_date.recieveTitanCode,
               	 bankInfo:pay_date.bankInfo,
               	 fcUserid:'${fcUserid}',
               	 userid:'${userId}',
               	 deskId:'${deskId}',
               	 paySource:'${paySource}',
               	 creator:'${operator}',
               	 escrowedDate:'${escrowedDate}',
               	 isEscrowed:'${isEscrowed}'
                },
                dataType: "json",
                success: function (data) {
                //如果ajax请求成功则显示回调页面
					 if(data.result == "success"){

						$("#orderNo").val(data.orderNo);
						$("#confirmOrder").submit();
					 }else{
						  new top.Tip({msg: data.msg, type: 1, timer: 2000});
						  setTimeout(function () {
							 if(typeof data.orderNo !='undefined'){
									$("#orderNo").val(data.orderNo);
								 }
							 $("#confirmOrder").submit();
						  }, 2000);

					 }
                }
                });
    	}else{
    		$("#payPassword").val(pay_date.payPassword);
    		$("#transferAmount").val(pay_date.transferAmount);
    		$("#payAmount").val(pay_date.payAmount);
    		$("#recieveOrgName").val(pay_date.recieveOrgName);
    		$("#recieveTitanCode").val(pay_date.recieveTitanCode);
    		$("#bankInfo").val(pay_date.bankInfo);
    		$("#linePayType").val(pay_date.linePayType);
    		$("#onlinePaymentForm").submit();
    	}  
    }
    
    
    function validate_isInput_password(){
    	if('${paySource}'=='1'){//如果分销商付款不需要输入密码，不用余额支付也不需要输入付款密码
    		return true;
    	}
    	if(($("#d_checkbox").attr("checked")=="checked" && '${accountBalance.balanceusable}'=="0")||$("#d_checkbox").attr("checked")!="checked"){
    		return true;
    	}
    	
    	var flag = false;
       	 $.ajax({
                dataType: 'json',
                context: document.body,
                async:false,
                url: '<%=basePath%>/trade/allownopwdpay.action',
                data:{
               	 totalAmount :$("#pay_totalAmount").text(),
               	 userid:'${userId}'
                },
                success: function (data) {
               	 if(data.result =="success"){
               		 flag =  true;
               	 }
                }
            });
    	
    	return flag; 
    }
    
    function save_payDate(){
    	var transferAmount ="0";
    	var payAmount = "0";
    	
    	if (sub('${orderDTO.payAmount}', '${accountBalance.balanceusable}') <= 0){
    		if($("#d_checkbox").attr("checked")=="checked"){//余额充足二选一
       		    transferAmount = '${orderDTO.payAmount}';
       	    }else{
       	    	payAmount = '${orderDTO.payAmount}';
       	    }
    	}else{
    		if($("#d_checkbox").attr("checked")=="checked"){//余额不足，任意选择
       		    transferAmount = '${accountBalance.balanceusable}';
       		    payAmount = sub('${orderDTO.payAmount}', '${accountBalance.balanceusable}');
       	    }else{
       	    	payAmount = '${orderDTO.payAmount}';
       	    }
    	}
    	var recieveOrgName = null;
    	var recieveTitanCode = null;
    	if($("#not_exists_history").is(":visible")==true || $(".replanceArea").is(":visible")==true){
    		recieveOrgName = $("#reOrgName").val();
        	recieveTitanCode = $("#reTitanCode").val();
    	}else{
    		recieveOrgName =  $("#hiddenAccountName").val();
    		recieveTitanCode = $("#hiddenTitanCode").val();
    	}
    	
    	var bankInfo =  $(".bankName:checked").val();
    	if(typeof(bankInfo) == "undefined"){
    		bankInfo =null;
         }
    	var payPassword = null;
    	if("undefined" != typeof PasswordStr2){
    		/* payPassword = rsaData(PasswordStr2.returnStr()); */
    		payPassword = PasswordStr2.returnStr();
    	}
    	var itemType = $(".bankName:checked").attr("data-index");
    	var linePayType =null;
    	if("undefined" != typeof itemType){
    		linePayType =   $("#item-"+itemType).attr("data-index");
    	}
    	var data={
    	    transferAmount:transferAmount,	
    	    payAmount:payAmount,
    	    recieveOrgName:recieveOrgName,
    	    recieveTitanCode:recieveTitanCode,
    	    bankInfo:bankInfo,
    	    payPassword:payPassword,
    	    linePayType:linePayType,
    	};
    	return data;
    }
    
    function validate_isBlank(){
    	if($("#not_exists_history").is(":visible")==true){
    		if($.trim($("#reOrgName").val()).length<1){
        		$("#reOrgNameError").text("收款方泰坦码不能为空");
        		return false;
        	}
        	if($.trim($("#reTitanCode").val()).length<1){
        		$("#reTitanCodeError").text("收款方账户不能为空");
        		return false;
        	}
    	}
    	return true;
    }
    
    $("#reOrgName").blur(function(){
    	 var orgName =  $(this).val();
    	 if($.trim(orgName).length<1){
    		 $("#reOrgNameError").text("收款方账户不能为空");
    	 }else{
    		 $("#reOrgNameError").text("");
    	 }
    });
    
    $("#reTitanCode").blur(function(){
    	var titanCode = $(this).val();
    	if($.trim(titanCode).length<1){
    		 $("#reTitanCodeError").text("收款方泰坦码不能为空");
    	}else{
    		 $("#reTitanCodeError").text("");
    	}
    });
    
</script>
</body>
</html>
