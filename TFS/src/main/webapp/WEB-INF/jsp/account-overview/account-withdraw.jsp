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
                    提现金额：<input type="text" id="withDrawNum" class="text w_300"> 元
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
                            <span><i>*</i>持卡人姓名：</span><input type="text" id="accountName" class="text w_250">
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
                                        if(data.code == 1){
                                            withDrawCallBack('提现申请已提交，等待银行处理。<br/>预计到账时间：2小时内', 1)
                                        } else {
                                            if (data.msg == '支付密码不正确请重新输入') {
                                                withDrawCallBack(data.msg, 0);
                                            } else {
                                                withDrawCallBack(data.msg, 1);
                                            }
                                        }
                                    }
                                });

                            }
                        }
                    ]
                }).showModal();
            }
        });

    });

    
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
		                        skin: 'btn p_lr30',
		                        callback: function () {
		                        	if(PasswordStr.returnStr()==PasswordStr1.returnStr()){
		                        		if(PasswordStr.returnStr().length==6){
		                        			set_PayPassword();
		                        		}
		                        	}
		                        },
		                        autofocus: true
		                    },

		                ]
		            }).showModal();
		        }
		    });
    }
    
    function set_PayPassword(){
    	 $.ajax({
	    	 type: "post",
	         url: "<%=basePath%>/account/setPayPassword.action",
	         data: {
	        	/*  payPassword:rsaData(PasswordStr.returnStr()) */
	        	 payPassword:PasswordStr.returnStr()
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
                    top.removeIframeDialog();
                    $("#right_con_frm").attr('src','<%=basePath%>/account/overview-main.shtml');
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
