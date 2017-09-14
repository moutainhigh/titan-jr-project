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
<body>
<c:if test="${ param.wrapType=='wallet'}">
<div class="w_header">
	<div class="w_1200">
		<div class="w_logo">
			<div class="l_img"><a href="http://pay.fangcang.com" title="泰坦钱包"><img src="<%=request.getScheme()%>://hres.fangcang.com/css/saas/WALLET/images/logo.png"></a></div>
			<div class="l_text">
				<span class="">丨</span>提现
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
	            提现
	        </div>
	    </div>
    </c:if>
    <div class="S_popup_Kan clearfix opaque">
        <div class="gold_pay">
            <div class="TFS_rechargeBox">
                <div class="TFS_rechargeBoxL fl">
                    提现金额：<input type="text" id="withDrawNum" class="text w_300"> 元<span id="inputeAmountError" style="color:red;font-size:13px;padding-left:20px;"></span>
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
                                    <span>收款账号：</span>${bindBankCard.accountnumber }
                                </p>
                                <p>
                                    <span>持卡人姓名：</span>
                                    ${bindBankCard.accountname }
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
                        <h4><span id="withdrawRate">0.00</span>元</h4>
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
<c:if test="${ param.wrapType=='wallet'}">
<div style="height:40px;"></div>
<!-- 版权 -->
<jsp:include page="/comm/foot.jsp"></jsp:include>
</c:if>
<form action="<%=basePath%>/account/overview-main.shtml" id="flashPage" target="right_con_frm"></form>
<script>
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
        
        window.focus();
        checkIsSetPayPassword();
        
        
        $("#withDrawNum").watch(function(value) {
        	
        	if(value == '' || value ==  null || parseFloat(value)<= 0)
        	{
        		 $('#withdrawRate').text("0.00");
        		 return;
        	}
        	 $.ajax({
        	   	 type: "get",
        	        url: "<%=basePath%>/rate/rateCompute.action?userId=${userId}&amount="+value+"&payType="+0+"&date=" + new Date().getTime(),
        	        dataType: "json",
        	        async: false,
        	        success: function(data){
        	       	 
        	       	 $('#withdrawRate').text(data.data.exRateAmount);
        	        }
        	   }); 
        });
        
    })

    //点击取消关闭弹框
    $(".J_exitKan").on('click', function() {
    	if(wrapType=='wallet'){
    		window.close();
    		return;
    	}
        top.removeIframeDialog();
        $("#right_con_frm").attr('src', $('#right_con_frm').attr('src'));
    });


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
   
    //默认个人提现支持的银行
    var data={
    		content:[
    		         {'key':'102','val':'中国工商银行'},
    		         {'key':'104','val':'中国银行'},
    		         {'key':'103','val':'中国农业银行'},
    		         {'key':'105','val':'中国建设银行'},
    		         {'key':'301','val':'交通银行'},
    		         {'key':'302','val':'中信银行'},
    		         {'key':'303','val':'中国光大银行'},
    		         {'key':'304','val':'华夏银行'},
    		         {'key':'305','val':'中国民生银行'},
    		         {'key':'308','val':'招商银行'},
    		         {'key':'309','val':'兴业银行'},
    		         {'key':'306','val':'广发银行'},
    		         {'key':'307','val':'平安银行'},
    		         {'key':'310','val':'上海浦东发展银行'},
    		         {'key':'403','val':'中国邮政储蓄银行'}
    		         ]
    };
    
    new AutoComplete($('#bankCode'), {
        data : data.content,
        key : 'bankCode',  //数据源中，做为key的字段
        val : 'bankName', //数据源中，做为val的字段
        width : 240,
        height : 300,
        autoSelectVal : true,
        clickEvent : function(d, input){
        	console.log(d);
            input.attr('data-id', d.key);
            $("#bankName").val(d.val);
        }
    });


    $('.J_password').on('click',function(){
    	//验证提现的金额
    	var withdraw_amount = $("#withDrawNum").val();
    	if(withdraw_amount.length<1){
    		withDrawCallBack("提现金额不能为空",1);
    		return;
    	}
    	
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
    	
    	if($('#withdrawRate').length >= 1 && parseFloat(withdraw_amount) <= parseFloat($('#withdrawRate').text())  )
    	{
    		withDrawCallBack("提现金额必须大于手续费！",1);
    		return;
    	}
    	
    	if($("#accountNum").is(":visible")==true){//如果是需要输入银行卡号
    		
    		var bankName= $("#bankName").val();
        	if(typeof bankName=="undefined" ||bankName.length<1 ){
        		withDrawCallBack("收款银行不能为空",1);
        		return;
        	}
    		
    		var accountNum = $("#accountNum").val();
    		 if(accountNum.length<1){
    	        	withDrawCallBack("收款账号不能为空",1);
    	        	return;
    	     }else{
    	    	var neg = /^[0-9]\d*$/
   	        	if(accountNum.length>30||!neg.test(accountNum)){
   	        		withDrawCallBack("请输入正确的卡号",1);
   	        		return;
   	        	}
    	     }
    	}
   
    	 showPayPassword();

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
             url: '<%=basePath%>/account/check_payPassword.shtml',
             data: {
            	 payPassword:PasswordStr2.returnStr(),
            	 fcUserid:'${fcUserId}',
            	 tfsUserid:'${tfsUserId}'
             },
             success: function (data) {
            	 if(data.result=="0"){
            		 result=true;
            		 toAccountWithdraw();
            		 
            	 }else{
            		new top.Tip({msg: '输入的密码错误', type: 2, timer: 2000});
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
                 originalAccount:'${bindBankCard.accountnumber }',
                 originalBankName:'${bindBankCard.bankheadname}',
                 amount:$("#withDrawNum").val(),
                 fcUserId:'${fcUserId}',
                 tfsUserId:'${tfsUserId}',
                 userId:'${userId}',
                 orderNo:'${orderNo}'
             },
             context: document.body,
             url: '<%=basePath%>/withdraw/toAccountWithDraw.shtml',
             success: function (data) {
                 if(data.result == "0"){
                	 if(succUrl.length>0){
          			 	window.location.href=succUrl;
          			 	return;
	          		 }
	          		 if(wrapType=='wallet'){
	          			 window.close();
	          			 return;
	          		 }
                	 
                	 top.F.loading.hide();
                     withDrawCallBack('提现申请已提交，等待银行处理。<br/>预计到账时间：t+1个工作日', 1);
                     $("#flashPage").attr('action' , getRootPath()+"/account/overview-main.shtml");
                     $("#flashPage").submit();//刷新页面
                     top.removeIframeDialog();
                     
                 } else {
                     if (data.result == '110100009') {
                         withDrawCallBack(data.resultMsg, 0);
                     } else {
                         withDrawCallBack(data.resultMsg, 1);
                     }
                 }
             },
             complete:function(){
            	 top.F.loading.hide();
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
    
    
    
    function checkIsSetPayPassword(){
   	 $.ajax({
       	 type: "post",
            url: "<%=basePath%>/account/checkIsSetPayPassword.action",
            data: {fcUserid:'${fcUserId}',tfsUserId:'${tfsUserId}'},
            dataType: "json",
            success: function(data){
           	 if(data.result=="0"){
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
		                        			 new top.Tip({msg: "密码必须为6位", type: 3, timer: 2000});
		                        			 
		                        			 $(".ui-dialog-content").html(html);
		                         			setTimeout(function(){
		                         				clickPassword();
		                                		 },500);
			                        		
		                        		}
		                        	}else{
		                        		 new top.Tip({msg: "两次输入密码不一致", type: 3, timer: 2000});
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
    
    function set_PayPassword(){
    	var result = false;
    	 $.ajax({
	    	 type: "post",
	         url: "<%=basePath%>/account/setPayPassword.action",
	         async:false,
	         data: {
	        	 fcuserid:'${fcUserId}',
	        	 tfsuserid:'${tfsUserId}',
	        	/*  payPassword:rsaData(PasswordStr.returnStr()) */
	        	 payPassword:PasswordStr.returnStr()
	         },
	         dataType: "json",
	         success: function(data){
	        	 if(data.result=="0"){
	        		 result = true;
	        		top.F.loading.show();
                     setTimeout(function () {
                         top.F.loading.hide();
                         new top.Tip({msg: '密码设置成功！', type: 1, timer: 2000});
                     }, 1000);
	        	 }else{
	        			top.F.loading.show();
                         setTimeout(function () {
                             top.F.loading.hide();
                             new top.Tip({msg: data.resultMsg, type: 3, timer: 2000});
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
