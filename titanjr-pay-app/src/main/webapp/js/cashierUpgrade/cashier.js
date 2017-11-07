/**
 * Created by Administrator on 2017/8/22.
 */
// 收银台
$(function(){

    // 泰容易icon
    $("#iconRotate").on("click",function(){
        if($(this).attr("class") == "icon icon-rotate"){
            $(this).attr("class","icon");
            $(".payment-info-isShow").addClass("isShow");
        }else{
            console.log($(this).attr("class"));
            $(this).attr("class","icon icon-rotate");
            $(".payment-info-isShow").removeClass("isShow");
        }
    });

    // 泰坦易
    $(".taitangyi").on("click","li",function(){
       $(".taitangyi li").removeClass("choice-color").find("i").addClass("isShow");
        $(this).addClass("choice-color").find("i").removeClass("isShow");
    });
    // 分期
    $(".by-stages-coontent").on("click","li",function(){
        $(".by-stages-coontent li").removeClass("choice-color").find("i").addClass("isShow");
        $(this).addClass("choice-color").find("i").removeClass("isShow");
    });

    // 查询收款账户或新增付款账户
    $(".payment-type .right").on("click",".new-payment",function(){
        if($(this).hasClass("payment-disable")){
            return;
        }
        var type = $(this).attr("data-type");
        if(type == "number"){
            isShowVeil("#Veil","show");
            //查询账户历史记录
            queryAccountHistory('2');
            // 选择付款账户弹窗
            $(".payment2taitan").show();
        }else{
            isShowVeil("#Veil","show");
            // 选择银行卡弹窗
            $(".payment2Bank").show();
        }
    })
    //$(".new-payment").click(function(){
    //    if()
    //    console.log(1);
    //    console.log($(this).find("a").text());
    //    if($(this).find("a").text() == "付款至对方泰坦金融账户>"){
    //        console.log(1);
    //        // 显示遮罩层
    //        isShowVeil("#Veil","show");
    //        // 显示优惠弹窗
    //        $(".payment2taitan").show();
    //    }else if($(this).find("a").text() == "付款至对方银行卡账户>"){
    //        console.log(2);
    //        // 显示遮罩层
    //        isShowVeil("#Veil","show");
    //        // 显示优惠弹窗
    //        $(".payment2Bank").show();
    //    }
    //});
    $(".payment-mode>li").each(function(i,v){
        if($(v).attr("data-choice") == "false"){
            $(v).find("svg").css("opacity","0.4");
            $(v).find("span").css("opacity","0.4");
            $(v).find(".content").css("opacity","0.4");
        }
    })

    // 支付方式点选
    $(".payment-mode>li").on("click",function(){
        if($(this).attr("data-choice") != "false"){
        	var paymentInfor = $(this).find(".payment-mode-info").text();
        	
        	//如果是使用余额支付则必须输入密码（贷款未校验）
        	if(paymentInfor == "账户余额"){
        		hasPayPassword();
        	}else{
        		$(".setpassword").hide();
        		$(".input-password").hide();
        	}
        	
            // 移除所有勾选icon的样式
            $(".payment-mode>li").removeClass("selected");
            $(".payment-mode>li").find("b").removeClass("icon-check icon-check-color").addClass("icon-check1");
            // 移除所有状态
            $(".payment-mode>li").find(".right-money").addClass("isShow");
            $(".payment-mode>li").find(".discount-icon").css("backgroundColor","#85A5DB").next().css("color","#85A5DB");
            // 添加自己
            $(this).addClass("selected");
            $(this).find("b").removeClass("icon-check1").addClass("icon-check icon-check-color");
            $(this).find(".right-money").removeClass("isShow");
            $(this).find(".discount-icon").css("backgroundColor","#d71319").next().css("color","#d71319");
            // 显示泰融易内容
            if($(".payment-mode>.payment-mode-item").hasClass("selected")){
                //console.log($(".payment-mode>.payment-mode-item").attr("class"));
                $(".payment-mode>.payment-mode-item").find(".content").removeClass("isShow");
                console.log($(".payment-mode>.payment-mode-item").find(".content"));
            }else{
                $(".payment-mode>.payment-mode-item").find(".content").addClass("isShow");
            }
            initData();//初始化收银台
            var paytyep = cashierData.linePayType;
            var index = $(this).find(".index").val();
            if(cashierData.linePayType == '9'){
            	paytyep = cashierData.bankInfo;
            }
            rateCompute(paytyep, 'commpay', index);//费率计算
        }else{
            return;
        }
    });

    // 选择常用支付方式去支付按钮
    $(".payment>.go-payment>button").on("click",function(e){
    	
        var $liSelected =  $(".payment-mode>li");
        
        $liSelected.each(function(i,v){
            if($(v).hasClass("selected")){
            	var itemType = $(v).find(".itemType").val();
                var paymentInfor = $(v).find(".payment-mode-info").text();
                console.log(paymentInfor);
                
                //校验收款账户或者泰坦码
                var validate = validate_payeeInfo();
            	if(validate==false){
            		return ;
            	}
            	//账户是否存在
                var check_account = check_account_isExit();
            	if(check_account ==false){
            		return false;
            	}
                
                if(paymentInfor == "泰容易"){ //贷款
                	//暂不支持
                	
                }else if(itemType == "4"){ //余额支付
                	var flag = check_set_payPassword();
                	if(flag){
                		showLoading();
                		balancePayment();
                	}else{
   	          		    e.stopPropagation()
                	}
                	
                }else if(itemType == "9"){ //第三方扫码支付
                	var bankInfo = $(v).find(".bankInfo").val();
                    isShowVeil("#Veil","show");
                    showLoading();
                    top.F.loading.show();
                    qrPayment(bankInfo);
                    
                }else if(itemType == "11"){ //快捷支付
             	    isShowVeil("#Veil","show");
             	    showLoading();
             	    top.F.loading.show();
                	quickPay_history();
                	
                }else if(itemType == "1" || itemType == "2"){// 网银支付
                	isShowVeil("#Veil","show");
                	showLoading();
                	top.F.loading.show();
                    cashierData.commonPaySubmit();
                }
            }
        })
    });
    

    // 更多方式
    $(".payment>.more").on("click",".more-payment",function(){
        if($(".payment-mode>li").hasClass("isShow")){
            $(".payment-mode>li").removeClass("isShow");
            $(this).parent().remove();
        }
    });

    // 优惠方式
    /*$(".payment-mode>li>.discount").on("click",function(){
        //$(this).find(".discount-icon").css("backgroundColor","#d71319").next().css("color","#d71319");
        // 显示遮罩层
        isShowVeil("#Veil","show");
        // 显示优惠弹窗
        $(".discount-mode").show();
        return false;
    });*/
    $(".discount-mode>.btn").on("click","button",function(){debugger;
        isShowVeil("#Veil","hide");
        $(".discount-mode").hide();
    });
    // 优惠方式点选
    $(".discount-mode>.discount-mode-content").on("click","li",function(){
        $(".discount-mode>.discount-mode-content").find("li").removeClass("selected").find("i").addClass("isShow");
        $(this).addClass("selected");
        $(this).find("i").removeClass("isShow");
    });
    $(".discount-mode>.discount-mode-title").on("click",".close",function(){
        console.log(11);
        $(".discount-mode>.btn>button").trigger("click");
    });

    // 微信支付
    $(".wx>.wx-payment-content>.buttom").on("click","a",function(){
        isShowVeil("#Veil","hide");
        $(".wx").hide();
        qrPayClose();
    });
    $(".wx>.wx-payment-title").on("click",".close",function(){
        $(".wx-payment>.wx-payment-content>.buttom>a").trigger("click");
    })

    // 支付宝支付
    $(".zfb>.wx-payment-content>.buttom").on("click","a",function(){
        isShowVeil("#Veil","hide");
        $(".zfb").hide();
        qrPayClose();
    });
    $(".zfb>.wx-payment-title").on("click",".close",function(){
        $(".zfb>.wx-payment-content>.buttom>a").trigger("click");
    });

    //常用支付方式--快捷支付--输入验证码--确认支付
    $(".payment-verification .confirm-btn").on("click",function(){debugger;
        if($(".payment-verification input").val() != ""){
            //isShowVeil("#Veil","hide");
            $(".payment-verification").hide();
            top.F.loading.show();
            confirmRecharge();
        }else{
            $(".verification-sentence").removeClass("isShow");
            $(".payment-verification input").css("borderColor","#d71319");
        }

    });
    $(".payment-verification .close").on("click",function(){
        isShowVeil("#Veil","hide");
        $(".payment-verification").hide();
    })

    // 收款账户列表点选
    $(".payment2account .history-items").on("click","li",function(){debugger;
        if($(this).index() <= 0){
            return;
        }
        
        var type = $(this).parent().attr("data-type");
        var orgName = $(this).find("p").eq(0).text();
        var titanCode = $(this).find("p").eq(1).text();
        
        if(type == "number"){
            isShowVeil("#Veil","hide");
            $(".payment2account").hide();
            $(".payment-type .payment-taitang").addClass("isShow");
            $("#recieveOrgName_p").text(orgName);
            $("#recieveTitanCode_p").text(titanCode);
            $("#recieveOrgName").val(orgName);
            $("#recieveTitanCode").val(titanCode);
            $(".new-payment-bank").removeClass("isShow");//选中后显示在收银台收款账户
            // 隐藏银行卡显示默认状态
            $(".payment-type .payment-bank").removeClass("isShow");
            $(".new-payment-number").addClass("isShow");
        }else{
            isShowVeil("#Veil","hide");
            $(".payment2account").hide();
            $(".payment-type .payment-bank").addClass("isShow");
            $(".new-payment-number").removeClass("isShow");
            // 隐藏账户显示默认状态
            $(".payment-type .payment-taitang").removeClass("isShow");
            $(".new-payment-bank").addClass("isShow");
        }

    });

    // 付款至对方泰坦金融账户
    $(".payment2taitan>.title").on("click",".close",function(){debugger;
        isShowVeil("#Veil","hide");
        $(".payment2taitan").hide();
    });
    // 收款账户最终选中显示
    $(".payment2taitan .history-items").on("click","li",function(){debugger;
        if($(this).index() <= 0){
            return;
        }
        $(".payment2taitan .history-items>.history-item").removeClass("selected").find("i").addClass("isShow");
        $(this).addClass("selected").find("i").removeClass("isShow");
    });
    
    // 付款至供应商移入移出
    $(".payment2taitan>.content").on("mouseover",".left",function(){
        $(this).find(".left-veil").addClass("isShow");
    });
    $(".payment2taitan>.content").on("mouseout",".left",function(){
        if($(".right-veil").hasClass("isShow")){
            $(this).find(".left-veil").removeClass("isShow");
        }else{
            $(this).find(".left-veil").addClass("isShow");
        }
    });
    $(".payment2taitan>.content").on("mouseover",".right",function(){
        $(this).find(".right-veil").addClass("isShow");
    });
    $(".payment2taitan>.content").on("mouseout",".right",function(){
        if($(".left-veil").hasClass("isShow")){
            $(this).find(".right-veil").removeClass("isShow");
        }else{
            $(this).find(".right-veil").addClass("isShow");
        }
    });
    $(".payment2taitan>.content button").on("click",function(){
        //$(".payment2taitan>.title>.close").trigger("click");
    });
    // 清空按钮
    $(".payment2taitan .right").on("keyup","input",function(){
       if($(this).val() != ""){
           $(this).parent().next().removeClass("isShow");
       }else{
           $(this).parent().next().addClass("isShow");
        }
    });
    $(".payment2taitan .right").on("focus","input",function(){
        if($(this).val() != ""){
            $(this).parent().next().removeClass("isShow");
        }
    })
    $(".payment2taitan .right").on("blur","input",function(){
        $(this).parent().next().addClass("isShow");
    })
    // 清空按钮点击
    $(".number-empty-btn").on("click",function(){
        $(this).prev().find("input").val("");
        $(this).addClass("isShow");
    })

    // 付款至对方银行账户
    $(".payment2Bank>.title").on("click",".close",function(){
        isShowVeil("#Veil","hide");
        $(".payment2Bank").hide();
    });
    // 历史收款银行卡点选
    $(".payment2Bank .history-items").on("click","li",function(){
        if($(this).index() <= 0){
            return;
        }
        $(".payment2Bank .history-items>li").removeClass("selected").find("i").addClass("isShow");
        $(this).addClass("selected").find("i").removeClass("isShow");
    });
    // 移入移出
    var flag = 1;
    $(".payment2account>.content").on("mouseover",function(){
        if(flag == 1){
            $(this).find(".left-veil").hide();
            $(this).find(".right-veil").show();
        }else{
            $(this).find(".right-veil").hide();
            $(this).find(".left-veil").show();
        }
    })
    // 清空按钮
    $(".payment2Bank .right .input-text").on("keyup","input",function(){
        if($(this).val() != ""){
            $(this).parent().next().removeClass("isShow");
        }else{
            $(this).parent().next().addClass("isShow");
        }
    });
    // 清空按钮点击
    $(".bank-empty-btn").on("click",function(){
       $(this).prev().find("input").val("");
        $(this).addClass("isShow");
    });

    $(".payment2account>.content").on("mouseover",".left",function(){
        flag = 1
        $(this).find(".left-veil").hide();
    });

    $(".payment2account>.content").on("mouseover",".right",function(){
        flag =2;
        $(this).find(".right-veil").hide();
    });

    $(".payment2Bank>.content button").on("click",function(){
        //$(".payment2Bank>.title>.close").trigger("click");
    });

    // 添加支付--默认显示快捷支付
    $(".payment>.more").on("click",".quick-bank",function(){
        isShowVeil("#Veil","show");
        $(".shortcut").show();
    });
    // 添加支付--关闭
    $(".shortcut>.title>.close").on("click",function(){
        isShowVeil("#Veil","hide");
        /*$(".bank-account-info .bank-number").text("");
        $("#checkCardNo_errorMsg").text("");
		$("#checkCardNo_errorMsg").addClass("isShow");*/
        $(".shortcut").hide();
    });
    // 添加支付 tab切换
    $(".shortcut>.title>ul").on("click","li",function(){
    	$(this).siblings().removeClass("title-color c_319");
        $(this).addClass("title-color c_319");
        var mode = $(this).attr("data-mode");
        sessionStorage.setItem("mode", mode);
        tab();
    });

    //银行卡号输入校验处理
    !function () {
        $('#haorooms').on('keyup mouseout input',function(e){
            if((e.which >= 48 && e.which <= 57) ||(e.which >= 96 && e.which <= 105 )){
                var $this = $(this),
                    v = $this.val();
                /\S{5}/.test(v) && $this.val(v.replace(/\s/g,'').replace(/(.{4})/g, "$1 "));
                $(".shortcut-payment>.bank-account>.enlarge").removeClass("isShow").text(v);
            }
            if(e.keyCode == 8){
                $(".shortcut-payment>.bank-account>.enlarge").removeClass("isShow").text($(this).val());
            }
        });
        $('#haorooms').on('change',function(){
            var $this = $(this),
                v = $this.val();
            /\S{5}/.test(v) && $this.val(v.replace(/\s/g,'').replace(/(.{4})/g, "$1 "));
            $(".shortcut-payment>.bank-account>.enlarge").removeClass("isShow").text(v);
        })
    }();

    $(".shortcut>.shortcut-payment>.bank-account>input").on("blur",function(){
        $(".shortcut-payment .enlarge").addClass("isShow");
    })
    //$(".shortcut>.shortcut-payment>.bank-account>input").on("focus",function(){
    //    $(".shortcut-payment .enlarge").removeClass("isShow");
    //})
    $(".shortcut>.shortcut-payment>.bank-account>input").on("mousedown",function(){
        //console.log($(this).select());
    })

    // 输入框清空
    $(".bank-account .empty").click(function(){
       $(this).prev().val("");
        $(".shortcut-payment>.bank-account>.enlarge").text("");
        $(this).addClass("isShow");
        $(".shortcut-payment>.bank-account>.enlarge").addClass("isShow");
    });
    // 快捷支付输入卡号--点击按钮校验卡号
    $(".shortcut-payment>.bank-account>button").on("click",function(){
        var inputTextK = $(".shortcut>.shortcut-payment>.bank-account>input").val();
        checkQuickCardNo(inputTextK);
        //if(inputText.length == 19){
        //}else{
        //    $(this).prev().removeClass("isShow");
        //}
    })

    // 更换付款银行
    $(".bank-account-info .change-bank").on("click",function(){
        $(".bank-account-info").addClass("isShow");
        $(".shortcut-payment").removeClass("isShow");
    });
    //检验快捷支付信息输入
    $(".register_information .type_li input").on("keyup",function(){
        if($(this).val() != ""){
            $(this).nextAll("s").removeClass("isShow");
        }else{
            $(this).next().addClass("isShow");
        }
    })
    $(".register_information .J_empty").on("click",function(){
        $(this).prevAll("input").val("");
        $(this).addClass("isShow");
    })
    // 保存至常用卡勾选
    $(" .common-bank-icon").click(function(){
        if($(this).hasClass("icon-fuxuan1")){//勾选
            $(this).removeClass("icon-fuxuan1").addClass("icon-fuxuan").css("color","#d71319");
            $("#isSaveHistorypay").val("1");
        }else{//不勾
            $(this).removeClass("icon-fuxuan").addClass("icon-fuxuan1").css("color","#ccc");
            $("#isSaveHistorypay").val("0");
        }
    });
    // 同意协议勾选
    $(".agreement-gou").click(function(){
        if($(this).hasClass("icon-fuxuan1")){//勾选
            $(this).removeClass("icon-fuxuan1").addClass("icon-fuxuan").css("color","#d71319");
            $("#confirm_quickpay_deposit").removeClass("disabledButton").attr("disabled", false);
            $("#confirm_quickpay_credit").removeClass("disabledButton").attr("disabled", false);
        }else{//不勾
            $(this).removeClass("icon-fuxuan").addClass("icon-fuxuan1").css("color","#ccc");
            $("#confirm_quickpay_deposit").addClass("disabledButton").attr("disabled", true);
            $("#confirm_quickpay_credit").addClass("disabledButton").attr("disabled", true);
        }
    })
    
    //添加快捷支付--获取验证码
    $(".get-verification-btn").click(function(){
    	if(validQuickPayInfo()){
    		sendVierfyCode(this);
    	}
    })
    
    // 输入框清空按钮显示与隐藏
    $(".bank-account-info .type_li").on("keyup","input",function(){
        if($(this).val() != ""){
            $(this).parent().next().removeClass("isShow");
        }else{
            $(this).parent().next().addClass("isShow");
        }
    });
    // 点击清空按钮
    $(".info-empty-btn").on("click",function(){
       $(this).prev().find("input").val("");
        $(this).addClass("isShow");
    });
    
    /*// 快捷支付储蓄卡 确认支付
    $(".savings .register_btn").on("click",function(){
        $(this).text("正在支付...").css("opacity","0.4");
        $("#VeilWhite").removeClass("isShow");
    });
    // 快捷支付信用卡 确认支付
    $(".credit .register_btn").on("click",function(){
        $(this).text("正在支付...").css("opacity","0.4");
        $("#VeilWhite").removeClass("isShow");
    }); */

    // 个人网银选中
    $(".personal-bank>ul").on("click","li",function(){
        $(".personal>ul>li").removeClass("selected").find("i").addClass("isShow");
        $(this).addClass("selected").find("i").removeClass("isShow"); //显示勾
        
        var bankInfo = $(this).addClass("selected").find(".bankInfo_personal").val();
        var linePayType = $(this).addClass("selected").find(".linePayType_personal").val();
        var bankName = $(this).addClass("selected").find(".bankName_personal").val();
        var supportType = $(this).addClass("selected").find(".supportType_personal").val();
        
        $("#bankInfo_wy_hid").val(bankInfo);
        $("#linePayType_wy_hid").val(linePayType);
        $("#bankImg_personal").attr("xlink:href","#icon-" + bankInfo);
        $("#bankName_personal").text(bankName);
        
        if(supportType == "1"){
        	//隐藏信用卡并去除选中
    		$("#cardType_credit").removeClass("selected").addClass("isShow");
        	$("#cardType_credit").find("i").removeClass("icon-check").addClass("icon-check1");
        	//显示储蓄卡并选中
        	$("#cardType_deposit").removeClass("isShow").addClass("selected");
        	$("#cardType_deposit").find("i").removeClass("icon-check1").addClass("icon-check");
        	//显示限额说明
        	limitShow(bankInfo + "_10")
        }else if(supportType == "2"){
        	//隐藏储蓄卡并去除选中
    		$("#cardType_deposit").removeClass("selected").addClass("isShow");
        	$("#cardType_deposit").find("i").removeClass("icon-check").addClass("icon-check1");
        	//显示信用卡并选中
        	$("#cardType_credit").removeClass("isShow").addClass("selected");
        	$("#cardType_credit").find("i").removeClass("icon-check1").addClass("icon-check");
        	//显示限额说明
        	limitShow(bankInfo + "_11")
        }else{
        	//都显示，选中储蓄卡
        	$("#cardType_deposit").removeClass("isShow").addClass("selected");
        	$("#cardType_deposit").find("i").removeClass("icon-check1").addClass("icon-check");
        	$("#cardType_credit").removeClass("selected").removeClass("isShow");
        	$("#cardType_credit").find("i").removeClass("icon-check").addClass("icon-check1");
        	//显示限额说明
        	limitShow(bankInfo + "_10")
        }
        
        $(".personal-bank").addClass("isShow"); //个人银行列表隐藏
        $(".personal-bank-infor").removeClass("isShow"); //显示跳转网银界面
        rateCompute(linePayType, 'addpay', "personal");//费率计算
    });
    function limitShow(tableId){
    	$("table[name='bankLimit']").addClass("isShow");
    	$("#" + tableId).removeClass("isShow");
    }
    
    //个人网银跳转按钮
    $(".personal-bank-infor button").click(function(){
    	debugger;
    	if($("#cardType_deposit").hasClass("selected")){
    		$("#accountType_wy_hid").val("10");
    	}else{
    		$("#accountType_wy_hid").val("11");
    	}
        $(".shortcut").hide();
        showLoading();
        top.F.loading.show();
        cashierData.paySubmit();
    });
    
    // 更换银行卡
    $(".personal-bank-infor .change-bank").on("click",function(){
        $(".personal-bank-infor").addClass("isShow");
        $(".personal-bank").removeClass("isShow");
    });

    // 企业网银
    $(".enterprise-bank>ul").on("click","li",function(){
        $(".enterprise-bank>ul>li").removeClass("selected").find("i").addClass("isShow");
        $(this).addClass("selected").find("i").removeClass("isShow");
        
        var bankInfo = $(this).addClass("selected").find(".bankInfo_enterprise").val();
        var linePayType = $(this).addClass("selected").find(".linePayType_enterprise").val();
        var bankName = $(this).addClass("selected").find(".bankName_enterprise").val();
        
        $("#bankInfo_wy_hid").val(bankInfo);
        $("#linePayType_wy_hid").val(linePayType);
        $("#accountType_wy_hid").val("10");
        $("#bankImg_enterprise").attr("xlink:href","#icon-" + bankInfo);
        $("#bankName_enterprise").text(bankName);
        
        //民生银行显示企业银行客户号输入框
        if(bankInfo == 'cmbc'){
        	$("#enterpriseCustomerNoDev").removeClass("isShow");
        }else{
        	$("#enterpriseCustomerNoDev").addClass("isShow");
        }
        //工商银行显示温馨提示
        if(bankInfo == 'icbc'){
        	$("#enterprise_warm").show();
        }else{
        	$("#enterprise_warm").hide();
        }
        
        $(".enterprise-bank").addClass("isShow"); //企业银行列表隐藏
        $(".enterprise-bank-info").removeClass("isShow"); //显示跳转网银界面
        rateCompute(linePayType, 'addpay', "enterprise");//费率计算
    });
    
    //企业网银跳转按钮
    $(".enterprise-bank-info button").click(function(){
    	var bankInfo = $("#bankInfo_wy_hid").val();
    	if(bankInfo == 'cmbc' && $.trim($("#enterpriseCustomerNo").val()).length <= 0){
    		new top.Tip({msg: "请输入企业银行客户号", type: 2, timer: 2000});
    		return;
    	}
        $(".shortcut").hide();
        showLoading();
        top.F.loading.show();
        cashierData.paySubmit();
    });
    // 更换银行卡
    $(".enterprise-bank-info .change-bank").on("click",function(){
        $(".enterprise-bank-info").addClass("isShow");
        $(".enterprise-bank").removeClass("isShow");
    });
    // 网银支付--卡类型选择选择
    $(".enterprise .items").on("click",".item",function(){
        $(this).siblings().removeClass("selected").find("i").removeClass("icon-check").addClass("icon-check1");
        $(this).addClass("selected").find("i").removeClass("icon-check1").addClass("icon-check");
        limitShow($("#bankInfo_wy_hid").val() + "_" + $(this).find("i").attr("id"));
        
    });
    
    // 清空按钮
    $(".enterprise-bank-info").on("keyup","input",function(){
        if($(this).val() != ""){
            $(this).next().removeClass("isShow");
        }else{
            $(this).next().addClass("isShow");
        }
    });
    // 清空按钮点击
    $(".enterprise-empty-btn").on("click",function(){
       $(this).prev().val("");
        $(this).addClass("isShow");
    });

    // 快捷支付验证码输入
    $(".payment-verification input").on("keyup",function(){
       if($(this).val() != ""){
           $(this).next().removeClass("isShow");
       }else{
           $(this).next().addClass("isShow");
       }
    });
    //$(".payment-verification input").on("keyup",function(){
    //    if($(this).val() != ""){
    //        $(this).next().removeClass("isShow");
    //    }else{
    //        $(this).next().addClass("isShow");
    //    }
    //});
    $(".forgot-password-empty").click(function(){
       $(this).addClass("isShow").prev().val("");
    });
    // 验证码输入框验证
    $(".payment-verification input").on("blur",function(){debugger;
       if($(this).val() == ""){
           $(".verification-sentence").removeClass("isShow");
           $(this).css("borderColor","#d71319");
       }else{
           $(".verification-sentence").addClass("isShow");
           $(this).css("borderColor","#dadfe2");
       }
    });
    
    //常用支付方式--重新获取验证码
    var btn2 = true;
    var quick_count;
    $(".obtain-btn-1").click(function(){
        if(btn2){
        	if($(this).text() == '重新获取'){
        		re_quickPay_history();
        	}
            btn2 = false;
            var num = 60;
            var _this = this;
            quick_count = setInterval(function(){
                num--;
                if(num < 0){
                    btn2 = true;
                    $(_this).text("重新获取").css("color","#222");
                    clearInterval(quick_count);
                    return;
                }
                $(_this).text(num + "s").css("color","#bbb");
            },1000)
        }else{
            return;
        }
    })

// 定义input的移动、焦点事件
    $("body").on("focus","input",function(){
        $(this).css("borderColor","#666");
    });
    $("body").on("blur","input",function(){
        $(this).css("borderColor","#dadfe2");
    });
    //console.log($(".payment2account .input-text"));
    //$(".payment2Bank .company-name").focus(function(){
    //    $(this).parent().css("borderColor","#666");
    //    console.log($(this).parent());
    //    console.log(1);
    //});
    //$(".payment2account .input-text>.company-name").blur(function(){
    //    $(this).parent().css("borderColor","#dadfe2");
    //});


    //$(".enterprise-form input").on("mouseover",function(){
    //    $(this).css("borderColor","#666");
    //}).on("mouseout",function(){
    //    $(this).css("borderColor","#dadfe2");
    //})
    //$(".enterprise-form input").on("focus",function(){
    //    $(this).css("borderColor","#666");
    //}).on("mouseout",function(){
    //    $(this).css("blur","#dadfe2");
    //})
    
})


var cashierData = {};
//初始化收银台数据
function buildCashierData(data){
	cashierData = data;
	var bankInfo;
	var itemType;
	var payerAccount;
	var payerAccountType;
	var payerName;
	var payerPhone;
	var idCode;
	var safetyCode;
	var validthru;
	
	var $liSelected =  $(".payment-mode>li");
    $liSelected.each(function(i,v){
        if($(v).hasClass("selected")){
        	bankInfo = $(v).find(".bankInfo").val();
        	itemType = $(v).find(".itemType").val();
    		payerAccountType = $(v).find(".payerAccountType").val();
        	if(itemType == '11'){
        		payerAccount = $(v).find(".payerAccount").val();
        		payerName = $(v).find(".payerName").val();
        		payerPhone = $(v).find(".payerPhone").val();
            	idCode = $(v).find(".idCode").val();
            	safetyCode = $(v).find(".safetyCode").val();
            	validthru = $(v).find(".validthru").val();
        	}
        }
    });
    
	//是否使用账户余额
	cashierData.isaccount = function(){
		if(itemType == '4'){
			return 1;
		}
		return 2;
	};
	//收款方信息
	cashierData.recieveOrgName = $("#recieveOrgName").val();
	cashierData.recieveTitanCode = $("#recieveTitanCode").val();
	//支付密码-以前设置的
	cashierData.payPassword_old = function(){
		var payPassword = null;
		if("undefined" != typeof PasswordStr2){
			payPassword = PasswordStr2.returnStr();
		}
		return payPassword;
	};
	//支付密码-新设置的
	cashierData.payPassword_new = function(){
		var payPassword = null;
		if("undefined" != typeof PasswordStr){
			payPassword = PasswordStr.returnStr();
		}
		return payPassword;
	};
	//支付方式
	cashierData.linePayType = itemType;
	//银行标识
	cashierData.bankInfo = bankInfo;
	//支付卡类型  10储蓄卡  11信用卡
	cashierData.payerAccountType = payerAccountType;
	
	if(itemType == '11'){
		cashierData.payerAccount = payerAccount;
		cashierData.payerName = payerName;
		cashierData.payerPhone = payerPhone;
		cashierData.idCode = idCode;
		cashierData.safetyCode = safetyCode;
		cashierData.validthru = validthru;
	}
	
	console.log("isaccount:"+cashierData.isaccount+"--recieveOrgName:"+cashierData.recieveOrgName
			+"--recieveTitanCode:"+cashierData.recieveTitanCode+"--bankinfo:"+cashierData.bankInfo
			+"--itemtype:"+cashierData.linePayType+"--userid:"+cashierData.userid+"--tradeAmount:"
			+cashierData.tradeAmount+"--jrVersion:"+cashierData.jrVersion+"--payOrderNo:"+cashierData.payOrderNo
			+"--payerAccount:"+cashierData.payerAccount+"--idCode:"+cashierData.idCode);
	
	//常用支付方式--第三方扫码及余额支付--参数设置
	cashierData.getCommonPayData = function(){
		var isSetPassword = $("#isSetPassword").val();//是否需要设置密码  0否  1是
		var payPassword; //密码只针对余额支付
		if(isSetPassword == '1'){
			payPassword = cashierData.payPassword_new();
		}else{
			payPassword = cashierData.payPassword_old();
		}
		var data= {
		       	 payOrderNo:cashierData.payOrderNo,
		       	 fcUserid:cashierData.fcUserid,
		       	 userid:cashierData.userid,
		       	 deskId:cashierData.deskId,
		         paySource:cashierData.paySource,
		         creator:cashierData.creator,
		       	 tradeAmount:cashierData.tradeAmount,
		       	 jrVersion:cashierData.jrVersion,
		       	 sign:cashierData.sign,
		       	
		       	 recieveOrgName:$("#recieveOrgName").val(),
		       	 recieveTitanCode:$("#recieveTitanCode").val(),
		       	 bankInfo:cashierData.bankInfo,
		         linePayType:cashierData.linePayType,
		         isaccount:cashierData.isaccount(),
		         payPassword:payPassword
		};
		return data;
	};

	//常用支付方式--快捷支付--参数设置
	cashierData.getQuickPayData = function(){
		var data= {
				payOrderNo:cashierData.payOrderNo,
		       	fcUserid:cashierData.fcUserid,
		       	userid:cashierData.userid,
		       	deskId:cashierData.deskId,
		        paySource:cashierData.paySource,
		        creator:cashierData.creator,
		       	tradeAmount:cashierData.tradeAmount,
		       	jrVersion:cashierData.jrVersion,
		       	sign:cashierData.sign,
		       	recieveOrgName:$("#recieveOrgName").val(),
		       	recieveTitanCode:$("#recieveTitanCode").val(),
		       	bankInfo:cashierData.bankInfo,
		       	payerAcount:cashierData.payerAccount,
		       	payerAccountType:cashierData.payerAccountType,
		       	payerName:cashierData.payerName,
		       	payerPhone:cashierData.payerPhone,
		       	idCode:cashierData.idCode,
		       	safetyCode:cashierData.safetyCode,
		       	validthru:cashierData.validthru,
		       	partnerOrgCode:cashierData.partnerOrgCode,
		        linePayType:cashierData.linePayType,
		        isaccount:cashierData.isaccount(),
		        payPassword:cashierData.payPassword_old()
		};
		return data;
	};
	
	//添加支付--快捷支付储蓄卡--获取验证码参数设置
	cashierData.getRechargeData_deposit = function(){debugger;
		var data= {
				payOrderNo:cashierData.payOrderNo,
		       	fcUserid:cashierData.fcUserid,
		       	userid:cashierData.userid,
		       	deskId:cashierData.deskId,
		        paySource:cashierData.paySource,
		        creator:cashierData.creator,
		       	tradeAmount:cashierData.tradeAmount,
		       	jrVersion:cashierData.jrVersion,
		       	sign:cashierData.sign,
		       	recieveOrgName:$("#recieveOrgName").val(),
		       	recieveTitanCode:$("#recieveTitanCode").val(),
		       	bankInfo:$("#quick_bankInfo_hid").val(),
		       	payerAcount:$("#quick_cardNo_hid").val(),
		       	payerAccountType:$("#quick_cardType_hid").val(),
		       	payerName:$("#quick_payerName_deposit").val(),
		       	payerPhone:$("#quick_payerPhone_deposit").val(),
		       	idCode:$("#quick_idCode_deposit").val(),
		       	partnerOrgCode:cashierData.partnerOrgCode,
		        linePayType:$("#quick_payType_hid").val(),
		        isaccount:2,
		        isSaveHistorypay:$("#isSaveHistorypay").val()
		};
		return data;
	};
	
	//添加支付--快捷支付信用卡--获取验证码参数设置
	cashierData.getRechargeData_credit = function(){debugger;
		/*var bankInfo = $("#quick_bankInfo_hid").val();
		var isValid = $("#validAuth").val();*/
		var safetyCode = $("#quick_safetyCode_credit").val();
		var validthruMonth = $("#quick_validthruMonth_credit").text();
		var validthruYear = $("#quick_validthruYear_credit").text().substr(2);
		var validthru = validthruMonth + validthruYear;
		
		/*if(bankInfo == 'icbc'){//工商银行不需要校验cvv码
			safetyCode = "123";
		}else if(isValid == '0'){//不需要验证cvv码和有效期，随便写
			safetyCode = "123";
			validthru = "0618";
		}*/
		
		var data= {
				payOrderNo:cashierData.payOrderNo,
		       	fcUserid:cashierData.fcUserid,
		       	userid:cashierData.userid,
		       	deskId:cashierData.deskId,
		        paySource:cashierData.paySource,
		        creator:cashierData.creator,
		       	tradeAmount:cashierData.tradeAmount,
		       	jrVersion:cashierData.jrVersion,
		       	sign:cashierData.sign,
		       	recieveOrgName:$("#recieveOrgName").val(),
		       	recieveTitanCode:$("#recieveTitanCode").val(),
		       	bankInfo:$("#quick_bankInfo_hid").val(),
		       	payerAcount:$("#quick_cardNo_hid").val(),
		       	payerAccountType:$("#quick_cardType_hid").val(),
		       	payerName:$("#quick_payerName_credit").val(),
		       	payerPhone:$("#quick_payerPhone_credit").val(),
		       	idCode:$("#quick_idCode_credit").val(),
		       	safetyCode:safetyCode,
		       	validthru:validthru,
		       	partnerOrgCode:cashierData.partnerOrgCode,
		        linePayType:$("#quick_payType_hid").val(),
		        isaccount:2,
		        isSaveHistorypay:$("#isSaveHistorypay").val()
		};
		return data;
	};
	
	//网银支付表单
	cashierData.form = function(){
		$("#onlinePaymentForm").remove();
		var form = document.createElement("form");
		form.action = '../payment/packageRechargeData.action';
		form.id = 'onlinePaymentForm';
		form.method = 'post';
			var isaccount = document.createElement("input");
			isaccount.type = 'hidden';
			isaccount.name = 'isaccount';
			isaccount.id = 'f_isaccount';
			form.appendChild(isaccount);
			
			var recieveOrgName = document.createElement("input");
			recieveOrgName.type = 'hidden';
			recieveOrgName.name = 'recieveOrgName';
			recieveOrgName.id = 'f_recieveOrgName';
			form.appendChild(recieveOrgName);
		
			var recieveTitanCode = document.createElement("input");
			recieveTitanCode.type = 'hidden';
			recieveTitanCode.name = 'recieveTitanCode';
			recieveTitanCode.id = 'f_recieveTitanCode';
			form.appendChild(recieveTitanCode);
		
			var payerAcount = document.createElement("input");
			payerAcount.type = 'hidden';
			payerAcount.name = 'payerAcount';
			payerAcount.id = 'f_payerAcount';
			form.appendChild(payerAcount);
			
			var payerAccountType = document.createElement("input");
			payerAccountType.type = 'hidden';
			payerAccountType.name = 'payerAccountType';
			payerAccountType.id = 'f_payerAccountType';
			form.appendChild(payerAccountType);
			
			var bankInfo = document.createElement("input");
			bankInfo.type = 'hidden';
			bankInfo.name = 'bankInfo';
			bankInfo.id = 'f_bankInfo';
			form.appendChild(bankInfo);
		
			var linePayType = document.createElement("input");
			linePayType.type = 'hidden';
			linePayType.name = 'linePayType';
			linePayType.id = 'f_linePayType';
			form.appendChild(linePayType);
			
			var paySource = document.createElement("input");
			paySource.type = 'hidden';
			paySource.name = 'paySource';
			paySource.id = 'f_paySource';
			form.appendChild(paySource);
			
			var deskId = document.createElement("input");
			deskId.type = 'hidden';
			deskId.name = 'deskId';
			deskId.id = 'f_deskId';
			form.appendChild(deskId);
			
			var userid = document.createElement("input");
			userid.type = 'hidden';
			userid.name = 'userid';
			userid.id = 'f_userid';
			form.appendChild(userid);
			
			var partnerOrgCode = document.createElement("input");
			partnerOrgCode.type = 'hidden';
			partnerOrgCode.name = 'partnerOrgCode';
			partnerOrgCode.id = 'f_partnerOrgCode';
			form.appendChild(partnerOrgCode);
			
			var payOrderNo = document.createElement("input");
			payOrderNo.type = 'hidden';
			payOrderNo.name = 'payOrderNo';
			payOrderNo.id = 'f_payOrderNo';
			form.appendChild(payOrderNo);
			
			var fcUserid = document.createElement("input");
			fcUserid.type = 'hidden';
			fcUserid.name = 'fcUserid';
			fcUserid.id = 'f_fcUserid';
			form.appendChild(fcUserid);
			
			var tradeAmount = document.createElement("input");
			tradeAmount.type = 'hidden';
			tradeAmount.name = 'tradeAmount';
			tradeAmount.id = 'f_tradeAmount';
			form.appendChild(tradeAmount);
		
			var sign = document.createElement("input");
			sign.type = 'hidden';
			sign.name = 'sign';
			sign.id = 'f_sign';
			form.appendChild(sign);
			
			var jrVersion = document.createElement("input");
			jrVersion.type = 'hidden';
			jrVersion.name = 'jrVersion';
			jrVersion.id = 'jrVersion';
			form.appendChild(jrVersion);
			
			document.body.appendChild(form);
	};

	//新增支付选择银行--网银支付
	cashierData.paySubmit = function(){
		cashierData.form();
		document.getElementById('f_isaccount').value=2;
		document.getElementById('f_recieveOrgName').value=$("#recieveOrgName").val(),
		document.getElementById('f_recieveTitanCode').value=$("#recieveTitanCode").val(),
		document.getElementById('f_bankInfo').value=$("#bankInfo_wy_hid").val();
		document.getElementById('f_linePayType').value=$("#linePayType_wy_hid").val();
		document.getElementById('f_paySource').value=cashierData.paySource;
		document.getElementById('f_deskId').value=cashierData.deskId;
		document.getElementById('f_userid').value=cashierData.userid;
		document.getElementById('f_partnerOrgCode').value=cashierData.partnerOrgCode;
		document.getElementById('f_payOrderNo').value=cashierData.payOrderNo;
		document.getElementById('f_tradeAmount').value=cashierData.tradeAmount;
		document.getElementById('f_fcUserid').value=cashierData.fcUserid;
		document.getElementById('f_payerAcount').value=$("#enterpriseCustomerNo").val();//民生银行企业网银需要银行客户号
		document.getElementById('f_payerAccountType').value=$("#accountType_wy_hid").val();//用于保存常用支付方式
		document.getElementById('f_sign').value=cashierData.sign;
		document.getElementById('jrVersion').value=cashierData.jrVersion;
		document.getElementById('onlinePaymentForm').submit();
	};
	
	//常用支付方式--网银支付
	cashierData.commonPaySubmit = function(){debugger;
		cashierData.form();
		document.getElementById('f_isaccount').value=2;
		document.getElementById('f_recieveOrgName').value=cashierData.recieveOrgName;
		document.getElementById('f_recieveTitanCode').value=cashierData.recieveTitanCode;
		document.getElementById('f_bankInfo').value=cashierData.bankInfo;//支付方式点选会重新构建cashierData
		document.getElementById('f_linePayType').value=cashierData.linePayType;
		document.getElementById('f_paySource').value=cashierData.paySource;
		document.getElementById('f_deskId').value=cashierData.deskId;
		document.getElementById('f_userid').value=cashierData.userid;
		document.getElementById('f_partnerOrgCode').value=cashierData.partnerOrgCode;
		document.getElementById('f_payOrderNo').value=cashierData.payOrderNo;
		document.getElementById('f_tradeAmount').value=cashierData.tradeAmount;
		document.getElementById('f_fcUserid').value=cashierData.fcUserid;
		document.getElementById('f_payerAccountType').value=cashierData.payerAccountType;
		document.getElementById('f_sign').value=cashierData.sign;
		document.getElementById('jrVersion').value=cashierData.jrVersion;
		document.getElementById('onlinePaymentForm').submit();
	};
	
}


//$(function() {
//
//    $('#haorooms').on('keyup', function(e) {
//        //只对输入数字时进行处理
//        if((e.which >= 48 && e.which <= 57) ||
//            (e.which >= 96 && e.which <= 105 )){
//            //获取当前光标的位置
//            var caret = this.selectionStart
//            //获取当前的value
//            var value = this.value
//            //从左边沿到坐标之间的空格数
//            var sp =  (value.slice(0, caret).match(/\s/g) || []).length
//            //去掉所有空格
//            var nospace = value.replace(/\s/g, '')
//            //重新插入空格
//            var curVal = this.value = nospace.replace(/(\d{4})/g, "$1 ").trim()
//            //从左边沿到原坐标之间的空格数
//            var curSp = (curVal.slice(0, caret).match(/\s/g) || []).length
//            //修正光标位置
//            this.selectionEnd = this.selectionStart = caret + curSp - sp
//
//        }
//    })
//})



// 显示隐藏遮罩层
function isShowVeil(ele,showAndHide){
    if(showAndHide == "show"){
        $(ele).show();
        $("body").css("overflow","hidden");
    }else{
        $(ele).hide();
        $("body").css("overflow","visible");
    }
}
// tab切换
function tab(){
    var mode = sessionStorage.getItem("mode");
    if(mode == "quick"){
        $(".personal-bank").addClass("isShow");
        $(".enterprise-bank").addClass("isShow");
        $(".enterprise").addClass("isShow");
        $(".bank-account-info").addClass("isShow");
        $(".shortcut-payment").removeClass("isShow");
    }else if(mode == "personal"){
        $(".shortcut-payment").addClass("isShow");
        $(".enterprise").addClass("isShow");
        $(".bank-account-info").addClass("isShow");
        $(".enterprise-bank").addClass("isShow");
        $(".personal-bank").removeClass("isShow");
    }else{
        $(".personal-bank").addClass("isShow");
        $(".shortcut-payment").addClass("isShow");
        $(".enterprise").addClass("isShow");
        $(".bank-account-info").addClass("isShow");
        $(".enterprise-bank").removeClass("isShow");
    }
}
// 60s倒计时
function countDown(ele){
    var num = 60;
    setInterval(function(){
        num--;
        if(num < 0){
            return;
        }
        $(ele).find(".count-down>span").text(num);
    },1000)
}