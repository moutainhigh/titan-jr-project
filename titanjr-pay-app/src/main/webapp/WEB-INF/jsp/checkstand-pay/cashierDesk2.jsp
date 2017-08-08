<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>
<%@ include file="/comm/taglib.jsp"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>SAAS后台管理</title>
    <jsp:include page="/comm/static-resource.jsp"></jsp:include>
	<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
	<style>		
		.grey_bg,.grey_bg:hover,.grey_bg:active{background-color: #bcc8c9 !important;border: 1px solid #acbbbc !important; cursor:auto;}
	</style>
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
        <div class="clearfix">
		  <div class="fl w_800">
           <div class="goldpay_title">付款金额：<span class="gdt_red" id="pay_totalAmount"><fmt:formatNumber value="${cashDeskData.amount }"  pattern="#,##0.00#" /></span>元
           	<i class="f_12 c_f00 m_l20" style="font-weight: normal; display: none;" id="enBankTitle">付款金额不足1000元，无法使用企业网银付款</i>
            </div>
            <div class="goldpay_top">
                <ul>
                	<li class="clearfix h_70" id="loanItemType" style="display: none;">	
						<div class="sel_card " id="notSelectBank">							
					        <div class="gt_Bank  J_add_Bank"> 
					            <i class="blue gti underline cursor J_Bank2">付款至供应商银行卡 &gt;
					            <span style="display: none;">目前仅运营贷可付款至供应商银行卡！</span>
					            </i>							            
					        </div>
				        </div>
				       <div class="s_bank clearfix  dn" id="selectBankZone">							       		
					  	<div class="s_title">付款至供应商银行卡：</div>
					  	<div class="Bank fl">
					         <div class="b_img"><img src="../images/TFS/Bankico.png" id="bankIco"></div>
					         <p class="" title="" id="accountNameSelect"></p>
					         <p id="accountSelect"></p>
					         <div class="b_replace blue underline cursor J_Bank2" id="switchBankCard">更换银行卡</div>
					      </div>
					  </div>
					  
					  <input type="hidden" id="accountName" />
					  <input type="hidden" id="account" />
					  <input type="hidden" id="bank" />
					  
					</li>
                    <li id="not_exists_history">
                        <div class="goldpay">
                            <div>
                                <span class="w_160"><i class="c_f00">*</i>收款账户（公司名称）：</span>
                                <input type="text" class="text w_250" id="reOrgName" dataType="*" errorMsg="收款账户不能为空"><span id="reOrgNameError" style="color:red"></span>
                            </div>
                            <div>
                                <span class="w_160"><i class="c_f00">*</i>收款方 - 泰坦码：</span>
                                <input type="text" class="text w_250" id="reTitanCode" dataType="*" errorMsg="收款泰坦码不能为空"><span id="reTitanCodeError" style="color:red"></span>
                            </div>
                        </div>
                    </li>
                    <li class="goldpay_replace" id="exists_history">
                        <div class="goldpay_card"><img src="<%=cssSaasPath%>/images/TFS/card.png" alt=""></div>
                        <div class="goldpay_custom">
                            <div>
                                <span class="w_160"><i class="c_f00">*</i>收款账户（公司名称）：</span>
                                <c:choose>
                                  <c:when test="${not empty  cashDeskData.orgName}">
                                     <input type="hidden" id="hiddenAccountName" value="${cashDeskData.orgName}">
                                     <span id="showAccountName">${cashDeskData.orgName}</span> 
                                  </c:when>
                                  <c:otherwise>
                                    <c:if test="${ not empty cashDeskData.accountHistoryDTO}">
                                       <input type="hidden" id="hiddenAccountName" value="${cashDeskData.accountHistoryDTO.orgname}">
                                   	   <span id="showAccountName">${cashDeskData.accountHistoryDTO.orgname}</span> 
                                       <span class="blue p_l18 curpo J_payother">付款给其他账户&gt;&gt;</span>
                                    </c:if>
                                  </c:otherwise> 
                                </c:choose>
                            </div>
                          <div>
                                <span class="w_160"><i class="c_f00">*</i>收款方 - 泰坦码：</span>
                                 <c:choose>
                                  <c:when test="${not empty  cashDeskData.titanCode}">
                                     <input type="hidden" id="hiddenTitanCode" value="${cashDeskData.titanCode} ">
                                     <span id="showTitanCode">${cashDeskData.titanCode}</span>
                                  </c:when>
                                 <c:otherwise>
                                     <c:if test="${not empty  cashDeskData.accountHistoryDTO}">
	                                     <input type="hidden" id="hiddenTitanCode" value="${cashDeskData.accountHistoryDTO.titancode} ">
	                                     <span id="showTitanCode">${cashDeskData.accountHistoryDTO.titancode}</span>
                                     </c:if>
                                  </c:otherwise>
                                </c:choose>
                            </div> 
                        </div>
                    </li>
                    
                  
                     <c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem">
                        <c:if test="${deskItem.itemType == 4}">
                            <input type="hidden" id="canUseAccBalance" value="1">
                            <c:if test="${ not empty cashDeskData.balanceusable and cashDeskData.canAccountBalance eq true }">
                            <li class="p_l27" id="useBalanceCheck">
                                <label class="f_ui-checkbox-c3 p_r10">
                                    <input type="checkbox" checked="" id="d_checkbox" onclick="checkedBalance()" ><i ></i>
                                    使用账户可用余额付款</label>丨
                                <span class="p_l10">账户可用余额：<fmt:formatNumber value="${cashDeskData.balanceusable }"  pattern="#,##0.00#" />元</span>
                            </li>
                            </c:if>
                            
                        </c:if>
                    </c:forEach>
                </ul>
                
            </div>
            </div>
                <c:if test="${cashDeskData.paySource =='2' }">
                   <div class="TFS_withdrawBoxR fr">
					<h3>温馨提示</h3>
					<div class="TFS_withdrawBoxR_content">
						<p>手续费：</p>
						<h4><span id="titanRateAmount">0.00</span>元</h4>	
					</div>
				  </div>
                </c:if>
				</div>
            <input type="hidden" id="onlinePayAmount" name="onlinePayAmount"><!--通过网银充值并支付的金额-->
              <div class="goldpay_title1" style="border-bottom:#ddd 1px solid;">
              <c:if test="${deskItem.itemType == 4}">
	                <c:if test="${ not empty cashDeskData.fcUserid}">
		                <div class="goldpaytitle1_top" id="not_enough_amount">剩余余额：<!--账户余额不够用余额付款-->
		                    <span class="c_f00" id="pay_surplus_amount"><fmt:formatNumber value="${cashDeskData.amount - cashDeskData.balanceusable}"  pattern="#,##0.00#" /></span>元
		                    <span class="p_l27">使用以下方式付款：</span>
		                </div>
	                </c:if>
                </c:if>

                <div class="goldpaytitle1_top blue J_payway" id="enough_amount"><!--账户余额足够但是不用余额付款-->
                    <span class="payway_other blue">使用其他方式付款</span>
                    <i class="jiantou"></i>
                </div>
               
                <div class="goldpayway" id="useCashierDeskPay">
                  <div class="pay_table">
                    <ul>
                         <c:if test="${not empty cashDeskData.commonPayMethodDTOList or not empty cashDeskData.quickCardHistoryList}">
                           <li class="on" id="pay_table_ttt">常用</li>
                        </c:if>
                        <c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem">
                        
                            <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 or deskItem.itemType == 9 or deskItem.itemType == 11 }">
                                <li>${deskItem.itemName}</li>
                            </c:if>
                            
                            <c:if test="${deskItem.itemType == 10 and isSupportLoanApply}">
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
	                              <c:if test="${commom.paytype == 9}">
	                                  <span class="payc_title fl"  id="item-${status.index}" data-index="${commom.paytype}"> </span>
                                  </c:if>
                                  
                                  <c:if test="${ commom.paytype == 10 and isSupportLoanApply}">
	                                  <span class="payc_title fl"  id="item-${status.index}" data-index="${commom.paytype}"> </span>
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
                         <!-- 快捷支付卡历史记录 -->
                         <c:if test="${not empty cashDeskData.quickCardHistoryList }">
		                      <li>
		                      <c:forEach items="${cashDeskData.quickCardHistoryList }" var="quickCard" varStatus="status">
								  <span class="payc_title fl">${quickCard.bankname }---${quickCard.payeracount }--- 快捷支付
								  （<c:if test="${quickCard.payeraccounttype =='10'}">
								  	储蓄卡
								  </c:if>
								  <c:if test="${quickCard.payeraccounttype =='11'}">
								  	信用卡
								  </c:if>）
								  </span>
	                          </c:forEach>
	                          </li>
                         </c:if>
                         <c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem" varStatus="o_status">
                            <c:if test="${deskItem.itemType == 1 or deskItem.itemType == 2 or deskItem.itemType == 3 or deskItem.itemType == 9  or (deskItem.itemType == 10 and  isSupportLoanApply) or deskItem.itemType == 11}">
                                <li >
                                    <c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank" varStatus="i_status">
                                        <div class="paytable_payway" itemType='${deskItem.itemType}' >
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
                                            <c:if test="${deskItem.itemType == 9 or deskItem.itemType == 10}">
                                                <span class="payc_title fl"  id="item-${o_status.index }-${i_status.index}" data-index="${deskItem.itemType}"></span>
                                            </c:if>
                                            
                                            <c:if test="${deskItem.itemType == 10 and isSupportLoanApply }">
                                                <span class="payc_title fl"  id="item-${o_status.index }-${i_status.index}" data-index="${deskItem.itemType}"></span>
                                            </c:if>
                                            
                                            <c:if test="${deskItem.itemType == 11 }">
                                                <span class="payc_title fl"  id="item-${o_status.index }-${i_status.index}" data-index="${deskItem.itemType}"></span>
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
        </div>
        <div class="ysgl_bottombut">
            <div>
                <a class="btn p_lr30 J_password">确定</a>
                <a class="btn p_lr30 grey_bg" style="display:none;">确定</a>
                <a class="btn btn_exit J_exitKan">取消</a>
            </div>
        </div>
        <jsp:include page="../checkstand-pay/cashierDeskLimit.jsp"></jsp:include>
    </div>
</div>

<form action="<%=basePath%>/payment/payConfirmPage.action" id="confirmOrder" method="post">
  <input name="orderNo" id="orderNo" type="hidden">
   <input name="payOrderNo" id="confirmOrder_payOrderNo" type="hidden" value="${cashDeskData.payOrderNo}">
  <input name="delay" id="delay" type="hidden">
</form>

<!--弹窗白色底-->
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script src="<%=cssSaasPath%>/js/password.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/cashier/paypsd.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/cashier/rate.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/cashier/cashierData.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/cashier/init.js"></script>
<script>
	$("document").ready(function (){
		//初始化收银台数据集合
		initData();
		//初始化密码函数
		initPayPassword();
		//初始化收银台相关
		initCashierDesk();	
	}); 

	
	function initData(){
		initCashierData({
			merchantcode:'${cashDeskData.merchantcode}',
			payOrderNo:'${cashDeskData.payOrderNo}',
			fcUserid:'${cashDeskData.fcUserid}',
			userid:'${cashDeskData.userId}',
			deskId:'${cashDeskData.cashierDeskDTO.deskId}',
			paySource:'${cashDeskData.paySource}',
			creator:'${cashDeskData.operator}',
			escrowedDate:'${cashDeskData.escrowedDate}',
			isEscrowed:'${cashDeskData.isEscrowed}',
			tradeAmount:'${cashDeskData.amount}',
			sign:'${cashDeskData.sign}',
			balanceusable:'${cashDeskData.balanceusable}',
			accountHistoryDTO:'${cashDeskData.accountHistoryDTO}',
			orgName:'${cashDeskData.orgName}',
		});
	}
	
	function getLoanReqData()
	{
		return {
			'userId':"${cashDeskData.userId}",
			'bankName':$('#bank').val(),
			'bankCode':'',
			'cardNum':$('#account').val(),
			'accountName':$('#accountName').val(),
			'amount':'${cashDeskData.amount}',
			'payOrderNo':'${cashDeskData.payOrderNo}',
			'fcUserId':'${cashDeskData.fcUserid}'
		};
	}
	
// 	private String bankName;
// 	private String bankCode;
// 	private String cardNum;
// 	private String accountName;
// //	// 开户行支行号
// //	private String bankBranch;
// //	private String bankCityCode;
// //	private String bankCityName;
	
// 	private String amount;
// 	private String transOrderNo;
 
	var selectBankWinow = null;
	function selectBankCallBack(bankName , bankCode , account , accountName)
	{
		$('#accountName').val(accountName);
		$('#account').val(account);
		$('#bank').val(bankName);
		
		$('#accountNameSelect').text(accountName);
		$('#accountSelect').text(account);
		$("#bankIco").attr("src","<%=basePath%>/banks/ico36/"+bankCode+".png");
		
		 $('#notSelectBank').hide();
         $('#selectBankZone').show();
         
         if(selectBankWinow)
         {
        	 selectBankWinow.remove();
        	 selectBankWinow= null;
         }
	}
	
	 $('.J_Bank2').hover(function(){
			$(this).find('span').show();
		},function(){
			$(this).find('span').hide();
		});
		
	$("#switchBankCard").on("click" , function(){
		$('.J_add_Bank').click();
	});
	$('.J_add_Bank').on('click',function(){
		  $.ajax({
		    dataType : 'html',
		    context: document.body,
		    url : '<%=basePath%>/trade/selectBank.shtml?userId=${cashDeskData.userId}',     
		    success : function(html){
		    	selectBankWinow =  window.top.dialog({
		            title: ' ',
		            padding: '0 0 35px 0',
		            content: html,
		            skin : 'saas_pop',  
		        }).showModal();
		    }
		  });
		})
	
    //点击使用余额支付
    function checkedBalance() {
    	//如果余额足够则只能用余额或者网银付款，二选一，如果余额不足则自由选择
    	if (sub(cashierData.tradeAmount,cashierData.balanceusable)<= 0){
   		 if($("#d_checkbox").attr("checked")=="checked"){
   			$(".bank-limit-wrap").hide();
   			 var arrow = $('.J_payway').find('i');
     			 var className = arrow.attr("class");
     			 if(className !="jiantou"){
     				conPayWay();
     			 }
     			  $('#titanRateAmount').text("0.00");
     			 chageComfireBut('show');
   	       }else{
   	    	  var arrow = $('.J_payway').find('i');
     			 var className = arrow.attr("class");
     			 if(className =="jiantou"){
     				 conPayWay();
     			 }
     			//第一个银行选中
     		    $(".bankName:first").attr("checked",'0');
     		    $(".bankName:checked").parents(".paytable_payway").click();
     		   	$($(".pay_table li")[0]).click();
     		  	$(".bankName:first").click();
     		  	
     		  
   	       }
	   	}else{//余额不足，将支持两种结合的方式或者选择充值
	   		 if($("#d_checkbox").attr("checked")=="checked"){//在线支付剩余余额
	   			 //获取选中银行
	   			 
	   			var itemType = $(".bankName:checked").parents(".paytable_payway").attr("itemType");
	   			paytable_paywayClick(itemType);
	   		  //计算选中银行的手续费
	   			//amount_not_enough_rate(itemType);
	   		 }else{//在线支付全款
	   			 $(".bankName:checked").parents(".paytable_payway").click();
	   		     var rateAmount = $("#titanRateAmount").text();

		         var show_online_PayAmount =  accAdd(rateAmount, cashierData.payAmount());
		         $("#pay_surplus_amount").text(show_online_PayAmount);
	   		 }
	   	}
    	
    }

    //控制显示的样式
    function conPayWay(){
    	  var arrow = $(".J_payway").find('i');
	      $(".J_payway").next(".goldpayway").toggle();
	      arrow.toggleClass('jiantou');
	      $(".payway_other").text('使用其他方式付款');
    }
    
    //展示付款历史
    $(".J_payother").on('click', function() {
        var backcont=null;
        $.ajax({
            dataType : 'html',
            context: document.body,
            data: {payeruserid:'${cashDeskData.accountHistoryDTO.payeruserid}',
                inaccountcode:'${cashDeskData.accountHistoryDTO.inaccountcode}',
                outaccountcode:'${cashDeskData.accountHistoryDTO.outaccountcode}'},
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
                            ' <input type="text" id="reOrgName" class="text w_250" ><span id="reOrgNameError" style="color:red"></div><div>' +
                            ' 	<span class="w_160"><i class="c_f00">*</i>收款方 - 泰坦码：</span>'+
                            '  <input type="text" id="reTitanCode"  class="text w_250"><span id="reTitanCodeError" style="color:red"></div></div>'	+
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
            $($(tabpannel).eq(index).find("input")[0]).click(); 
            $($(tabpannel).eq(index).find(".paytable_payway")[0]).click(); 
        });
    }
    
   $(function () {
        //tab
        tabChange($(".pay_table li"), $(".paytable_content ul li"), "on");
       /*  tabChange($(".pay_table li"), $(".paytable_content ul li"), "on"); */
    }); 

    //支付方式下拉显示隐藏
    $('.J_payway').on('click',function(){
    	
        var arrow = $(this).find('i');
        $(this).next(".goldpayway").toggle();
        arrow.toggleClass('jiantou');
        if(arrow.hasClass("jiantou")){
        	if('${cashDeskData.amount}' - '${cashDeskData.balanceusable}' <= 0){//余额充足
        		  $("#d_checkbox").attr("checked",true);
        		  $('#titanRateAmount').text("0.00");
        	}
            $(".payway_other").text('使用其他方式付款');
            $(".bank-limit-wrap").hide();
        }else{
        	if('${cashDeskData.amount}' - '${cashDeskData.balanceusable}' <= 0){//余额充足
      		  $("#d_checkbox").removeAttr("checked");
      		  $(".bankName:first").attr("checked",'0');
 			  $(".paytable_payway:first").click();
 				$($(".pay_table li")[0]).click();
     		  	$(".bankName:first").click();
      	     }
            $(".payway_other").text('使用其他方式付款');
        }
        return false;
    });
   
    
    //点击取消关闭页面
    $('.J_exitKan').on('click',function(){
		window.close();
	});
	
    var timeIndex = 0;
    
    //点击确定弹出输入密码 ，提交
    $(".J_password").on('click', function () {
    	//验证收款账户和泰坦码
        var validate = validate_isBlank();
    	if(validate==false){
    		return ;
    	}
    	//验证账户是否存在
    	var check_account = check_account_isExit();
    	if(check_account ==false){
    		return false;
    	}
    	
    	var flag = payPasswordObj.validate_isInput_password();
    	if(flag==false || cashierData.linePayType() == '10'){
    		payPasswordObj.show_payPassword();
    	}else{
    		pay_Order(); 
    	}
    });
    
    function loanPay()
    {
    	$.ajax({
    		type:'post',
            dataType: 'json',
            url: '<%=basePath%>/payment/operationLoanPay.action',
            async:false,
            data:getLoanReqData(),
            success: function (data) {
            	top.F.loading.hide();
            	if(data.result=="0"){
            		 $("#confirmOrder").submit();
            	}else{
            		new top.Tip({msg: '申请运营贷款失败，请联系管理员！', type: 1, timer: 2000});
            	}
            }
        });
    }
    
    //检查账户是否存在
    function check_account_isExit(){
    	
    	var recieveOrgName = $("#reOrgName").val();
    	var recieveTitanCode = $("#reTitanCode").val();
    	var check_account=true;
    	if(cashierData.linePayType()!='10')
    	{	
	    	
	    	if(typeof recieveOrgName !="undefined" && typeof recieveTitanCode !="undefined"){
	    		check_account = false;
	        	$.ajax({
	        		type:'post',
	                dataType: 'json',
	                url: '<%=basePath%>/account/check_account.action',
	                async:false,
	                data:{
	                	recieveOrgName:recieveOrgName,
	                	recieveTitanCode:recieveTitanCode,
	                },
	                success: function (data) {
	                	if(data.result=="0"){
	                		check_account = true;
	                	}else{
	                		new top.Tip({msg: '该账户不存在', type: 1, timer: 2000});
	                	}
	                }
	            });
	    	}
    	}
    	return check_account;
    }
    
     //验证是否部分为空
    function validate_isBlank(){
     	
    	if(cashierData.linePayType()=='10')
    	{	
    		var data = getLoanReqData();
			if (data["cardNum"] == null || data["cardNum"] == '') 
			{
				new top.Tip({msg: '请选择收款的银行卡!', type: 1, timer: 2000});
				return false;
			} 
			else if (data["accountName"] == null || data["accountName"] == '')
			{
				new top.Tip({msg: '请选择收款的银行卡!', type: 1, timer: 2000});
				return false;
			} else if (data["bankName"] == null || data["bankName"] == '') {
				new top.Tip({msg: '请选择收款的银行卡!', type: 1, timer: 2000});
				return false;
			} else if (data["payOrderNo"] == null || data["payOrderNo"] == '') {
				new top.Tip({msg: '付款单号为空，请联系管理员!', type: 1, timer: 2000});
				return false;

			} else if (data["userId"] == null || data["userId"] == '') {
				new top.Tip({msg: '用户标示为空，请联系管理员!', type: 1, timer: 2000});
				return false;
			}

		} else {
			if ($("#not_exists_history").is(":visible") == true
					|| $(".replanceArea").is(":visible") == true) {
				if ($.trim($("#reOrgName").val()).length < 1) {
					$("#reOrgNameError").text("收款方账户不能为空");
					return false;
				} else {
					$("#reOrgNameError").text("");
				}
				if ($.trim($("#reTitanCode").val()).length < 1) {
					$("#reTitanCodeError").text("收款方泰坦码不能为空");
					return false;
				} else {
					$("#reTitanCodeError").text("");
				}
			}
			$("#reOrgNameError").text("");
			$("#reTitanCodeError").text("");
		}
		return true;
	}

	$("#reOrgName").blur(function() {
		var orgName = $(this).val();
		if ($.trim(orgName).length < 1) {
			$("#reOrgNameError").text("收款方账户不能为空");
		} else {
			$("#reOrgNameError").text("");
		}
	});

	$("#reTitanCode").blur(function() {
		var titanCode = $(this).val();
		if ($.trim(titanCode).length < 1) {
			$("#reTitanCodeError").text("收款方泰坦码不能为空");
		} else {
			$("#reTitanCodeError").text("");
		}
	});

	//判断如果是民生银行出现下拉框
	$('.paytable_payway input').on('change', function() {
		var _this = $(this);
		var value = $('input:radio[name=r2]:checked').val();
		;
		//获取
		var dataIndex = $('input:radio[name=r2]:checked').attr("data-index");
		var linePayType = $("#item-" + dataIndex).attr("data-index");
		if (value == 'cmbc' && linePayType == 1) {
			_this.parents('.paytable_payway').find('.payc_ms').slideDown();
		} else {
			$('.paytable_payway').find('.payc_ms').slideUp();
		}
	});

	/** 支付相关开始 **/
	//提交表单
	function pay_Order() {
		//获取数据
		if (cashierData.validatePayerAccount().length > 0) {
			cashierData.window(cashierData.validatePayerAccount());
			return;
		}
		top.F.loading.show();
		if (cashierData.payAmount() == "0") {//余额支付
			balancePayment();
		} else if (cashierData.linePayType() == '9') {//微信支付
			qrPayment();

		} else if (cashierData.linePayType() == '10') {
			loanPay();
		} else if (cashierData.linePayType() == '11') { //测试快捷支付
			quickPay();
			
		} else {//有网银支付
			cashierData.submit();
		}
	}

	//余额支付
	function balancePayment() {
		$.ajax({//支付页面
			type : "post",
			url : "<%=basePath%>/payment/showTitanPayPage.action",
               data: cashierData.onlinePayData(),
               dataType: "json",
               success: function (data) {
               //如果ajax请求成功则显示回调页面
               	toResultPage(data);
               },complete:function(){
               	top.F.loading.hide();
               }
         });
    }
    
    //微信支付
   function qrPayment(pay_date){
    	$.ajax({//支付页面
    		type: "post",
            dataType : 'html',
   	        context: document.body,
   	     	data: cashierData.onlinePayData(),
   	        url : '<%=basePath%>/payment/qrCodePayment.action',			
   	        success : function(html){
   	            var d =  window.top.dialog({
   	                title: ' ',
   	                padding: '0 0 0px 0',
   					width: 560,
   	                content: html,
   	                skin : 'saas_pop wx_close wx_p', 
   	            	onclose: function () {
   	            		toWxPayPage();
   	          		}
   	            }).showModal();
   	            $('.wx_close').on('click',function(){
   	            	d.remove();
   	            	toWxPayPage();
   	            });
   	        },complete:function(){
               	top.F.loading.hide();
            }
           });
    }
    
    function quickPay(){
    	var orderNo = "";
    	$.ajax({
    		type: "post",
            dataType : 'html',
   	        context: document.body,
   	        url : '<%=basePath%>/payment/showQuickPayView.action',			
   	        success : function(html){
   	            var d =  window.top.dialog({
   	                title: ' ',
   	                padding: '0 0 0px 0',
   					width: 400,
   	                content: html,
   	                skin : 'saas_pop wx_close wx_p'
   	            }).showModal();
   	            $('#getCheckCode').on('click',function(){
   	            	$.ajax({
	   	 				type : "post",
	   	 				url : "<%=basePath%>/payment/quickPayRecharge.action",
	   	 	               data: cashierData.onlineQuickPayData(),
	   	 	               dataType: "json",
	   	 	               success: function (data) {
	   	 	            	   if(data.isSuccess == false){
	   	 	            		   alert(data.errMsg);
	   	 	            		   return;
	   	 	            	   }
	   	 	            	   orderNo = data.orderNo;
	   	 	            	   
	   	 	            	   var certificate = data.certificate;
	   	 	            	   if(certificate != null && typeof(certificate) != 'undefined' && certificate == '1'){
	   	 	            		   window.open("<%=basePath%>/quickPay/cardSceurityVerify.shtml?orderNo=" + data.orderNo + "&cardNo=" + $('#payerAcount').val(), "_blank");
	   	 	            		   
	   	 	            	   }else{
	   	 	            		   alert("验证码发送成功");
	   	 	            	   }
	   	 	            	   
	   	 	               }
	   	 	         });
   	            });
   	            $('#recharge').on('click',function(){
 	            	$('#recharge').attr("disabled", true);
	            	$.ajax({
	   	 				type : "post",
	   	 				url : "<%=basePath%>/quickPay/confirmRecharge.action",
	   	 	               data: {
	   	 	            		busiCode: "109",
	   	 	            		signType: "1",
	   	 	            		version: "v1.1",
	   	 	        			merchantNo: "M000016",
	   	 	        			orderNo: orderNo,
	   	 	        			payType: "41",
	   	 	        			checkCode: $("#checkCode").val()
	   	 	               },
	   	 	               dataType: "json",
	   	 	               success: function (data) {
	   	 	            	   if(data.success == false){
	   	 	            		   alert(data.errMsg);
	   	 	            		   $('#recharge').attr("disabled", false);
	   	 	            		   return;
	   	 	            	   }
	   	 	            	   alert("充值成功");
	   	 	            	   d.close().remove();
	   	 	            	   top.F.loading.hide();
	   	 	               }
	   	 	         });
	             });
   	            
   	        },complete:function(){
               	top.F.loading.hide();
            }
        });
    }
    
    function toWxPayPage(){
    	 setTimeout(function(){
    		var status = confirmOrder(_orderNo);
 			if(status =="success"||status=="fail"){
 				$("#orderNo").val(_orderNo);
 				 $("#confirmOrder").submit();
 			}else if(status =="delay"){
 				$("#orderNo").val(_orderNo);
 				$("#expand").val("001_001");
 				$("#confirmOrder").submit();
 			}
    	 }, 4000);
    }
	
    
     //到结果页面
    function toResultPage(data){
   	 	if(data.result == "0"){
			$("#orderNo").val(data.data);
			$("#confirmOrder").submit();
		 }else{
			  new top.Tip({msg: data.resultMsg, type: 2, timer: 2000});
			  /**setTimeout(function () {
				 if(typeof data.data !='undefined'){
						$("#orderNo").val(data.data);
					 }
				 $("#confirmOrder").submit();
			  }, 2000);
			  */
		 }
    }
    
</script>
</body>
</html>
