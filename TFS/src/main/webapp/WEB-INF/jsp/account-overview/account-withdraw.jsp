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
            提现
        </div>
    </div>
    <div class="S_popup_Kan clearfix opaque">
        <div class="gold_pay">
            <div class="TFS_rechargeBox">
                <div class="TFS_rechargeBoxL fl">
                    提现金额：<input type="text" id="withDrawNum" class="text w_300"> 元<span id="inputeAmountError" style="color:red;font-size:13px"></span>
                </div>
                <div class="TFS_rechargeBoxR fr">
                    <h3>账户名称/泰坦码：${organ.orgName}/${organ.titanCode }</h3>
                    <p>可用余额：<span class="gdt_red">
                        <fmt:formatNumber value="${accountBalance.balanceusable / 100 }"  pattern="#,##0.00#" />
                    </span>元</p>
                </div>
                <div class="clear"></div>
            </div>
            <input type="hidden" id="useNewBankCard"><!-- 记录是否使用新卡提现 -->
            <input type="hidden" id="hasBindBanCard"><!-- 记录是否有已绑定的卡 -->
            <div class="TFS_withdrawBox">
                <div class="TFS_withdrawBoxL fl">
                    <div class="TFS_withdrawBoxL_first">
                        <label>
                            <span><i>*</i>收款银行：</span>
                            <input type="text" id="bankCode" class="text w_250">
                            <input type="hidden" id="bankName" >
                        </label>
                        <label>
                            <span><i>*</i>收款账号：</span><input type="text" id="accountNum" class="text w_250">
                        </label>
                        <label>
                            <span><i>*</i>持卡人姓名：</span><input type="text" id="accountName" class="text w_250" value="${organ.orgName}" disabled>
                        </label>
                        <c:if test="${bindBankCard != null}">
                            <a href="javascript:void(0)" id="withDrawToCurrentCard"
                               class="blue decorationUnderline TFS_withdrawBoxL_else_aHref hide">提现到已绑定银行卡 >></a>
                        </c:if>
                    </div>
                    <div class="TFS_withdrawBoxL_else">
                        <div class="TFS_withdrawBoxL_card">
                            <h3>提现卡信息</h3>
                            <div class="TFS_withdrawBoxL_card_content">
                                <p>
                                    <span>收款银行：</span>${bindBankCard.bankheadname }
                                </p>
                                <p>
                                    <span>收款账号：</span>${bindBankCard.account_number }
                                </p>
                                <p>
                                    <span>持卡人姓名：</span>
                                    ${bindBankCard.accountrealname }
                                </p>
                            </div>

                        </div>
                        <c:if test="${organ.userType == 2 }"> <!--只有个人机构允许提现到其它卡-->
                            <a href="javascript:void(0)" id="withDrawToNewCard"
                                class="blue decorationUnderline TFS_withdrawBoxL_else_aHref">提现到其他银行卡 >></a>
                        </c:if>
                    </div>

                </div>
                <div class="TFS_withdrawBoxR fr">
                    <h3>温馨提示</h3>
                    <div class="TFS_withdrawBoxR_content">
                        <p>手续费：</p>
                        <h4><span>0</span>元</h4>
                    </div>
                </div>
                <div class="clear"></div>
            </div>

        </div>
        <div class="ysgl_bottombut">
            <div class="massBtn">
                <a class="btn btn_grey J_password">确定</a>
                <a class="btn btn_grey btn_exit J_exitKan">取消</a>
            </div>
        </div>
    </div>
</div>
<!--弹窗白色底-->

<form action="<%=basePath%>/account/overview-main.shtml" id="flashPage" target="right_con_frm"></form>
<script>

    $(function(){
        if ('${bindBankCard }'){
            $("#useNewBankCard").val("0");
            $("#hasBindBanCard").val("1");
            $(".TFS_withdrawBoxL_first").hide();
            $(".TFS_withdrawBoxL_else").show();
        } else {
            $("#useNewBankCard").val("1");
            $("#hasBindBanCard").val("0");
            $(".TFS_withdrawBoxL_first").show();
            $(".TFS_withdrawBoxL_else").hide();
        }
        checkIsSetPayPassword();
    })

    //点击取消关闭弹框
    $(".J_exitKan").on('click', function() {
        top.removeIframeDialog();
        $("#right_con_frm").attr('src', $('#right_con_frm').attr('src'));
    });

   /*  $("#withDrawNum").blur(function(){
        var inputeAmount = $(this).val();
        if($.trim(inputeAmount).length<1){
            $("#inputeAmountError").text("提现金额不能为空");
        }else{
            $("#inputeAmountError").text("");
        }

        var neg = /^[1-9]{1}\d+(\.\d{1,2})?$/;
        var neg2 = /^[0]{1}(\.\d{1,2})?$/;
        var flag = neg.test($(this).val())||neg2.test($(this).val());
        if(flag==false){
            $("#inputeAmountError").text("输入金额无法识别,正确格式如xx或xx.xx");
            $(this).val("");
            $(this).focus();
        }else{
            $("#inputeAmountError").text("");
        }
    }); */

    //使用新卡提现或者旧卡
    $("#withDrawToNewCard").live('click',function(){
        $(".TFS_withdrawBoxL_first").show();
        $(".TFS_withdrawBoxL_else").hide();
        $("#useNewBankCard").val("1");
    });

    $("#withDrawToCurrentCard").live('click',function(){
        $(".TFS_withdrawBoxL_first").hide();
        $(".TFS_withdrawBoxL_else").show();
        $("#useNewBankCard").val("0");
    });

    new AutoComplete($('#bankCode'), {
        url : '<%=basePath%>/account/getBankInfoList.shtml',
        source : 'bankInfoDTOList',
        key : 'bankCode',  //数据源中，做为key的字段
        val : 'bankName', //数据源中，做为val的字段
        width : 240,
        height : 300,
        autoSelect : false,
        clickEvent : function(d, input){
            input.attr('data-id', d.key);
            $("#bankName").val(d.val);
        }
    });


    $('.J_password').on('click',function(){
    	//验证提现的金额
    	var withdraw_amount = $("#withDrawNum").val();
    	if(withdraw_amount.length<1){
    		alert("提现金额不能为空");
    		return;
    	}
    	
    	/* var neg = /^([+-]?)((\d{1,3}(,\d{3})*)|(\d+))(\.\d{2}))?$/; */
    	/*   var neg = /^[1-9]{1}\d{0,10}(\.\d{1,2})?$/;
	      var neg2 = /^[0]{1}(\.\d{1,2})?$/; */
    	  
    	  var neg = /^[1-9]{1}\d{0,20}(\.\d{1,2})?$/;
          var neg2 = /^[0]{1}(\.\d{1,2})?$/;
          var flag = neg.test(withdraw_amount)||neg2.test(withdraw_amount);
          if(flag==false){
              $("#inputeAmountError").text("输入金额无法识别,正确格式如xx或xx.xx");
              $(this).val("");
              $(this).focus();
              return;
          }else{
              $("#inputeAmountError").text("");
          }
    	
    	if(withdraw_amount=="0" ||withdraw_amount=="0.0" ||withdraw_amount=="0.00"){
    		$("#inputeAmountError").text("您的提现额度必须大于0");
    		return ;
    	}
    	
    	if(parseFloat(withdraw_amount)>parseFloat('${accountBalance.balanceusable / 100 }') ||'${accountBalance.balanceusable / 100 }'=='0'){
    		$("#inputeAmountError").text("可用余额不足，不能提现");
    		return ;
    	}
    	
    	
    	if($("#accountNum").is(":visible")==true){//如果是需要输入银行卡号
    		
    		var bankName= $("#bankName").val();
        	if(typeof bankName=="undefined"){
        		alert("收款银行不能为空");
        		return;
        	}
    		
    		var accountNum = $("#accountNum").val();
    		 if(accountNum.length<1){
    	        	alert("银行卡号不能为空");
    	        	return;
    	     }else{
    	    	var neg = /^[0-9]\d*$/
   	        	if(accountNum.length>20||!neg.test(accountNum)){
   	        		alert("请输入正确的卡号");
   	        		return;
   	        	}
    	     }
    	}
   
    	 showPayPassword();
    	
       /*  if (isNaN($("#withDrawNum").val())){
            alert("请输入数字金额");
            return false;
        }
        if ($("#bankCode").attr("data-id") == null){
            alert("收款银行不能为空");
            return false;
        }
        if ($("#accountName").val() == null){
            alert("收款人姓名不能为空");
            return false;
        }
        if ($("#accountNum").val() == null){
            alert("收款账号不能为空");
            return false;
        } */
        //验证是否设置支付密码
        

    });
	
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
            		 toAccountWithdraw();
            		 
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
    
    function toAccountWithdraw(){
    	top.F.loading.show();
    	 $.ajax({
             type: "post",
             dataType: 'json',
             data: {
                 useNewBankCard: $("#useNewBankCard").val(),
                 hasBindBanCard: $("#hasBindBanCard").val(),
                 bankCode: $("#bankCode").attr("data-id"),
                 bankName: $("#bankName").val(),
                 accountNum: $("#accountNum").val(),
                 accountName: $("#accountName").val(),
                 password:PasswordStr2.returnStr(),
                 originalAccount:'${bindBankCard.account_number }',
                 amount:$("#withDrawNum").val(),
             },
             context: document.body,
             url: '<%=basePath%>/account/toAccountWithDraw.shtml',
             success: function (data) {
                 if(data.code == "1"){
                	 top.F.loading.hide();
                     withDrawCallBack('提现申请已提交，等待银行处理。<br/>预计到账时间：2小时内', 1);
                     $("#flashPage").submit();//刷新页面
                     top.removeIframeDialog();
                     
                 } else {
                     if (data.msg == '支付密码不正确请重新输入') {
                         withDrawCallBack(data.msg, 0);
                     } else {
                         withDrawCallBack(data.msg, 1);
                     }
                 }
             },
             complete:function(){
            	 top.F.loading.hide();
             }
         });
    }
    
    function checkIsSetPayPassword(){
   	 $.ajax({
       	 type: "post",
            url: "<%=basePath%>/account/checkIsSetPayPassword.action",
            data: {fcUserid:'${fcUserid}'},
            dataType: "json",
            success: function(data){
           	 if(data.result=="success"){
           		show_setPayPassword();
           	 }
           	}
           }); 
   }
    
    var timeIndex = 0;
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

    function show_setPayPassword(){
    	 $.ajax({
		        dataType: 'html',
		        context: document.body,
		        url: '<%=basePath%>/account/showSetPayPassword.action',
		        success: function (html) {
		        	clickPassword();
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
		        }
		    });
    }
    
    function set_PayPassword(){
    	var result = false;
    	 $.ajax({
	    	 type: "post",
	         url: "<%=basePath%>/account/setPayPassword.action",
	         async:false,
	         data: {
	        	/*  payPassword:rsaData(PasswordStr.returnStr()) */
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
    
    
    function withDrawCallBack(returnMsg,needClose) {
        var contentStr = '<div class="f_14 l_h26 p_l35">' + returnMsg + '</div>';
        new top.createConfirm({
            title:'提示',
            padding: '20px 20px 40px',
            okValue : '关闭',
            content : contentStr,
            skin : 'saas_confirm_singlebtn',
            ok : function(){
                if (needClose == 1){
                	 
                <%-- 	$(window.parent.frames["right_con_frm"]).attr('src','<%=basePath%>/account/overview-main.shtml'); --%>
                    <%--  $("#right_con_frm").attr('src','<%=basePath%>/account/overview-main.shtml'); --%>
                }
                //TODO 需刷新当前提现记录页面
//                $.ajax({
//                    dataType : 'html',
//                    context: document.body,
//                    url : '提现记录.html',
//                    success : function(html){
//                        var d =  dialog({
//                            title: ' ',
//                            padding: '0 0 0px 0 ',
//                            content: html,
//                            skin : 'saas_pop',
//                            button : [
//                                {
//                                    value: '关闭',
//                                    skin : 'btn btn_grey btn_exit',
//                                    callback: function () {
//                                        top.removeIframeDialog();
//                                    }
//                                }
//                            ]
//                        }).showModal();
//                    }
//                });
            },
            cancel: false
        });
    }

</script>
</body>
</html>
