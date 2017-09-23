<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <title>SAAS后台管理-收银台</title>
    <%@include file="/comm/upgrade/upgrade-css.jsp"%>
    <style type="text/css">
        .icon {
            width: 1em; height: 1em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
        .disabledButton{
        	opacity: 0.3;
        }
                .City_components{
            position: relative;
        }
        .month-drop-down-frame,.year-drop-down-frame{
            position: absolute;
            width: 118px;
            max-height: 100px;
            top: 36px;
            left: 0;
            z-index: 11;
            border: 1px solid #ccc;
            background-color: #fff;
            overflow-y: auto;
        }
        .year-drop-down-frame{
            left: 10px;
        }
        .City_components .drop-down-frame{
            height: 30px;
            line-height: 30px;
            padding-left: 10px;
            border-bottom: 1px solid #ccc;
            cursor: pointer;
        }
        .City_components .drop-down-frame:hover{
            background-color: #238AF7;
        }
        .City_components .select-items{
            box-sizing: border-box;
            display: inline-block;
            width: 120px;
            height: 36px;
            border: 1px solid #ccc;
            border-radius: 2px;
            line-height: 36px;
            padding-left: 10px;
            margin-right: 10px;
            margin-left: 10px;
            cursor: pointer;
        }
        .City_components .select-items:hover{
            border-color: #666;
        }
        .payment2account .right .input-text .valid-tip b{
            margin-left: 20px;
        }
    </style>
</head>
<body>
    <header>
        <div class="h">
            <b></b>
            <span>收银台</span>
        </div>
    </header>
    <div class="main w clearfix">
        <div class="payment-info clearfix">
            <div class="payment-info-left fl">
                <p>${cashDeskData.goodName }</p>
                <p>${cashDeskData.goodDetail }<svg class="icon" id="iconRotate" aria-hidden="true"><use xlink:href="#icon-downArrow"></use></svg></p>
               	<div class="payment-info-isShow isShow">
               		<c:if test="${not empty  cashDeskData.orgName  }">
                    	<p>收款方：${cashDeskData.orgName }</p>
                    	<input id="recieveOrgName" type="hidden" value="${cashDeskData.orgName }"/>
               		</c:if>
               		<c:if test="${not empty  cashDeskData.titanCode  }">
                    	<p>泰坦码： ${cashDeskData.titanCode }</p>
                    	<input id="recieveTitanCode" type="hidden" value="${cashDeskData.titanCode }"/>
               		</c:if>
                </div>
            </div>
            <div class="payment-info-right fr">
                <p><span>￥</span><fmt:formatNumber value="${cashDeskData.amount }"  pattern="#,##0.00#" /></p>
            </div>
        </div>
        
        <!-- 财务付款给供应商并且没有传收款方的情况下，需要选择收款账户 -->
        <c:if test="${cashDeskData.payerTypeEnum.key == 3 and empty cashDeskData.orgName and empty cashDeskData.titanCode }">
        	<div class="payment-type clearfix">
	            <div class="left fl">请选择付款方式：</div>
	            <ul class="right fl">
	                <li class="new-payment payment-taitang" data-type="number"><a href="javascript:void(0)" class="payment-type-account">付款至对方泰坦金融账户 ></a></li>
	                <!-- 选择之后显示-->
	                <li class="new-payment new-payment-bank isShow" data-type="number">
	                    <i class="iconfont icon-gou"></i>
	                    <div class="li-left fl"><span></span></div>
	                    <div class="li-right fl">
	                        <p id="recieveOrgName_p"></p>
	                        <p id="recieveTitanCode_p"></p>
	                        <input id="recieveOrgName" type="hidden" />
	                        <input id="recieveTitanCode" type="hidden" />
	                    </div>
	                    <a href="javascript:void(0)">更换账户</a>
	                </li>
	                <!-- <li class="new-payment payment-bank payment-disable" data-type="bank"><a href="javascript:void(0)" class="payment-type-bank">付款至对方银行卡账户 ></a></li> -->
	                <!-- 选择之后显示-->
	                <!-- <li class="new-payment new-payment-number isShow" data-type="bank">
	                    <i class="iconfont icon-gou"></i>
	                    <div class="li-left fl"><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-zs"></use></svg></div>
	                    <div class="li-right fl">
	                        <p>深圳市天下房仓科技有限公司</p>
	                        <p>1222&nbsp;5678</p>
	                    </div>
	                    <a href="javascript:void(0)">更换账户</a>
	                </li> -->
	            </ul>
	        </div>
        </c:if>
        
        <!-- 支付方式-->
        <ul class="payment-mode">
        	<!-- 余额支付 -->
            <c:if test="${ not empty cashDeskData.balanceusable }">
            	<c:if test="${cashDeskData.canAccountBalance eq true }">
            		<!-- 无余额支付权限或者余额小于支付金额，设置不可选 -->
	            	<li class="payment-mode-prohibit clearfix" <c:if test="${cashDeskData.canAccountBalance ne true or cashDeskData.balanceusable < cashDeskData.amount }">data-choice="false"</c:if> >
		                <div class="icon fl">
		                    <b class="iconfont icon-check1"></b>
		                    <svg class="icon" aria-hidden="true" style="font-size: 26px;margin-left: 35px;top: 4px;">
		                        <use xlink:href="#icon-qb"></use>
		                    </svg>
		                    <span class="payment-mode-info">账户余额</span>
		                    <input class="itemType" type="hidden" value="balance"/>
		                </div>
		                <div class="content fl">
		                   	 账户余额：<span><fmt:formatNumber value="${cashDeskData.balanceusable }"  pattern="#,##0.00#" />元</span>
		                </div>
		                <div class="right-money isShow">
		                    <span class="money"><s>￥</s><span id="amount_balance">${cashDeskData.amount }</span></span>
		                </div>
		            </li>
            	</c:if>
            </c:if>
            <!-- 微信和支付宝支付默认显示 -->
            <li class="clearfix selected">
                <div class="icon fl">
                    <b class="iconfont icon-check icon-check-color"></b>
                    <svg class="icon" aria-hidden="true" style="font-size: 26px;margin-left: 35px;top: 4px;">
                        <use xlink:href="#icon-wx"></use>
                    </svg>
                    <span class="payment-mode-info">微信支付</span>
                    <input class="bankInfo" type="hidden" value="wx"/>
                    <input class="itemType" type="hidden" value="9"/>
                </div>
                <div class="right-money">
                    <!-- 财务付款给供应商才显示手续费 -->
					<c:if test="${cashDeskData.paySource =='2' }">
						<span id="rateSpan_wx">手续费 ￥<span id="commPayRateAmount_wx">0.00</span></span>
					</c:if>
					<!-- 这里需要考虑手续费 -->
					<span class="money"><s>￥</s><span id="amount_wx">${cashDeskData.amount }</span></span>
                </div>
            </li>
         	<li class="clearfix">
                <div class="icon fl">
                    <b class="iconfont icon-check1"></b>
                    <svg class="icon" aria-hidden="true" style="font-size: 26px;margin-left: 35px;top: 4px;">
                        <use xlink:href="#icon-zfb"></use>
                    </svg>
                    <span class="payment-mode-info">支付宝支付</span>
                    <input class="bankInfo" type="hidden" value="alipay"/>
                    <input class="itemType" type="hidden" value="9"/>
                </div>
                <div class="right-money isShow">
                    <!-- 财务付款给供应商才显示手续费 -->
					<c:if test="${cashDeskData.paySource =='2' }">
						<span id="rateSpan_alipay">手续费 ￥<span id="commPayRateAmount_alipay">0.00</span></span>
					</c:if>
					<!-- 这里需要考虑手续费 -->
					<span class="money"><s>￥</s><span id="amount_alipay">${cashDeskData.amount }</span></span>
                </div>
            </li>
            <!-- 常用支付方式历史 -->
            <c:if test="${not empty cashDeskData.commonPayHistoryList }">
            	 <c:forEach items="${cashDeskData.commonPayHistoryList }" var="commonPay" varStatus="status">
	            	<li class="<c:if test='${status.index > 0}'>isShow </c:if>clearfix">
		                <div class="icon fl">
		                    <b class="iconfont icon-check1"></b>
		                    <svg class="icon" aria-hidden="true" style="font-size: 26px;margin-left: 35px;top: 4px;">
		                        <use xlink:href="#icon-${commonPay.bankinfo }"></use>
		                    </svg>
		                    <span class="payment-mode-info">${commonPay.bankname }</span>
		                    <input class="bankInfo" type="hidden" value="${commonPay.bankinfo }"/>
                    		<input class="itemType" type="hidden" value="${commonPay.paytype }"/>
                    		<input class="payerAccount" type="hidden" value="${commonPay.payeracount }"/>
                    		<input class="payerAccountType" type="hidden" value="${commonPay.payeraccounttype }"/>
                    		<input class="payerName" type="hidden" value="${commonPay.payername }"/>
                    		<input class="payerPhone" type="hidden" value="${commonPay.payerphone }"/>
                    		<input class="idCode" type="hidden" value="${commonPay.idcode }"/>
                    		<input class="safetyCode" type="hidden" value="${commonPay.safetycode }"/>
                    		<input class="validthru" type="hidden" value="${commonPay.validthru }"/>
		                </div>
		                <c:if test="${commonPay.paytype == '11' }">
		                	<div class="content discount fl">
			                    <div class="bank-card fl"><span>${commonPay.subpayeracount }</span><s>|</s><span>
									<c:if test="${commonPay.payeraccounttype =='10'}">
								  		储蓄卡
									</c:if>
									<c:if test="${commonPay.payeraccounttype =='11'}">
									  	信用卡 
									</c:if>
								 </span><s>|</s><span>快捷支付</span></div>
			                </div>
		                </c:if>
		                <c:if test="${commonPay.paytype != '11' }">
		                	<div class="content discount fl">
			                    <div class="bank-card fl"><span style="display:inline-block;width: 58px;"></span>
			                    	<c:if test="${commonPay.paytype =='1'}">
			                    		<!-- 民生银行的payeracount是企业银行客户号 -->
								  		<span>${commonPay.subpayeracount }</span><s>|</s><span>企业网银</span></div>
									</c:if>
									<c:if test="${commonPay.paytype =='2'}">
										<s></s>
										<c:if test="${commonPay.payeraccounttype =='10'}">
									  		<span>
									  		储蓄卡
									  		</span>
										</c:if>
										<c:if test="${commonPay.payeraccounttype =='11'}">
										  	<span>
										  	信用卡 
										  	</span>
										</c:if>
										<s>|</s><span>个人网银</span></div>
									</c:if>
			                </div>
		                </c:if>
		                <div class="right-money isShow">
		                    <!-- 财务付款给供应商才显示手续费 -->
							<c:if test="${cashDeskData.paySource =='2' }">
								<span id="rateSpan_${commonPay.paytype }">手续费 ￥<span id="commPayRateAmount_${commonPay.paytype }">0.00</span></span>
							</c:if>
							<!-- 这里需要考虑手续费 -->
							<span class="money"><s>￥</s><span id="amount_${commonPay.paytype }">${cashDeskData.amount }</span></span>
		                </div>
		            </li>
	            </c:forEach>
            </c:if>
        </ul>
        <div class="payment">
            <ul class="more clearfix">
                <li><a href="javascript:void(0)" class="more-payment" style="display: none;">更多支付方式</a></li>
                <li><a href="javascript:void(0)" class="quick-bank">添加快捷/银行卡支付</a></li>
            </ul>
            <!-- 密码-->
            <input id="isSetPassword" type="hidden" /><!-- 是否设置密码  0否  1是-->
            <div class="password">
                <div class="setpassword" style="display: none;">
                    <p class="TFSpassw_title" style="margin-bottom: 20px;">您还没设置支付密码，请设置密码：</p>
                    <ul class="TFSpassw_set">
                        <li class="clearfix">
                            <span class="Passname fl">支付密码</span>
                            <div class="sixDigitPassword fl" id="passwordbox">
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <span></span>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="Passname fl">确认支付密码</span>
                            <div class="sixDigitPassword fl" id="passwordbox1">
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <span></span>
                            </div>
                        </li>
                    </ul>
                    <span id="setPayPasswordError" class="password-prompt"></span>
                </div>
                <div class="input-password" style="display: none; position: relative;">
                    <ul class="TFSpassw_set">
                        <li class="clearfix">
                            <span class="Passname fl">请输入密码</span>
                            <div class="sixDigitPassword fl" id="passwordbox2">
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <span></span>
                            </div>
                            <a href="忘记密码.html" target="_blank" class="fl">忘记密码?</a>
                        </li>
                    </ul>
                    <span id="payPasswordError" class="password-prompt" style="top: 12px;right: 230px;"></span>
                </div>
            </div>
            <div class="go-payment"><button type="button" id="btn">去支付</button></div>
        </div>
    </div>
    <!-- 底部-->
    <footer>
        <div class="h">
            <p>Copyright 2012-2016，fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号 <a href="javascript:void(0);"></a>工商网监 电子标识</p>
        </div>
    </footer>
<!--遮罩层 -->
<div class="payment-box isShow" id="Veil"></div>
<!--添加快捷/银行卡支付弹窗-->
<div class="shortcut isShow">
	<!-- tab切换 -->
    <div class="title" style="position: relative;">
        <ul class="clearfix">
            <li class="title-color" data-mode="quick">快捷支付</li>
            <c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem" varStatus="item_status">
            	<c:if test="${deskItem.itemType == 2 }">
            		<li data-mode="personal">个人网银</li>
            	</c:if>
            	<c:if test="${deskItem.itemType == 1 }">
            		<li data-mode="enterprise">企业网银</li>
            	</c:if>
            </c:forEach>
        </ul>
        <i class="iconfont icon-sc close"></i>
    </div>
    <!-- 快捷支付-->
    <div class="shortcut-payment">
        <div class="bank-account clearfix">
            <div class="enlarge isShow"></div>
            <label class="fl">银行卡号</label>
            <input class="fl" type="text" maxlength="23" id="haorooms" placeholder="请输入银行卡号"/>
            <i class="iconfont icon-sc empty isShow"></i>
            <p id="checkCardNo_errorMsg" class="err isShow" style="color: #FF6000; position: absolute;bottom: 80px; left: 95px;font-size: 13px;">xxx</p>
            <button class="fl">确定</button>
        </div>
        <div class="title">仅支持以下16家银行卡支付</div>
        <ul class="content clearfix">
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-boc"></use></svg><span>中国银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-abc"></use></svg><span>农业银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-icbc"></use></svg><span>工商银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-ccb"></use></svg><span>建设银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-cib"></use></svg><span>兴业银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-ceb"></use></svg><span>光大银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-cmbc"></use></svg><span>民生银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-abc"></use></svg><span>邮政储蓄</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-comm"></use></svg><span>交通银行</span><svg class="icon bank-type-icon" aria-hidden="true"><use xlink:href="#icon-Savingsdepositcard"></use></svg></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-citic"></use></svg><span>中信银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-pingan"></use></svg><span>平安银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-hxb"></use></svg><span>华夏银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-spdb"></use></svg><span>浦发银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-cgb"></use></svg><span>广发银行</span></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-cmb"></use></svg><span>招商银行</span><svg class="icon bank-type-icon" aria-hidden="true"><use xlink:href="#icon-CreditCard"></use></svg></li>
            <li><svg class="icon" aria-hidden="true" style="top:3px;"><use xlink:href="#icon-bos"></use></svg><span>上海银行</span><svg class="icon bank-type-icon" aria-hidden="true"><use xlink:href="#icon-Savingsdepositcard"></use></svg></li>
        </ul>
    </div>
    <!-- 快捷支付--填写银行卡信息-->
    <div class="bank-account-info isShow">
    	<input id="quick_cardNo_hid" type="hidden" />
    	<input id="quick_cardType_hid" type="hidden" />
        <input id="quick_bankInfo_hid" type="hidden" />
        <input id="quick_payType_hid" type="hidden" value="11" />
        <!-- 是否保存常用卡  0否  1是 -->
        <input id="isSaveHistorypay" name="isSaveHistorypay" type="hidden" value="1" />
        <!-- 储蓄卡-->
        <div class="savings">
            <div class="prompt">将用于实名认证，请务必使用本人信息填写。</div>
            <div class="content clearfix">
                <!-- 表单-->
                <div class="register_information fl">
                    <form id="J_form1" class="demo_form" action="<%=basePath%>/quickPay/confirmRecharge.action">
	                    <input id="busiCode" name="busiCode" type="hidden" value="109" />
	                    <input id="signType" name="signType" type="hidden" value="1" />
	                    <input id="version" name="version" type="hidden" value="v1.1" />
	                    <input id="merchantNo" name="merchantNo" type="hidden" value="M000016" />
	                    <input id="payType" name="payType" type="hidden" value="41" />
	                    <!-- 融数订单号，获取验证码的时候设置 -->
	                    <input id="quick_rsOrder" name="orderNo" type="hidden" />
                        <ul class="register_list">
                            <li class="type_li clearfix">
                                <div class="type_name fl">
                                    <label>付款银行</label>
                                </div>
                                <div class="type fl clearfix">
                                    <div class="icon fl"><svg class="icon" aria-hidden="true" style="top:3px;margin-right: 10px;">
                                   	<use id="quick_icon_deposit" xlink:href=""></use></svg>
                                   	<span id="quick_bankName_deposit"></span>
                                    <span id="quick_cardType_deposit" style="color: #777;"></span>
                                   	<a class="change-bank fr" href="javascript:void(0)" style="color: #238AF7">更换付款银行</a></div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>银行卡号</label>
                                </div>
                                <div class="type type_mz fl">
                                    <div class="pdd icon fl">
                                        <span id="quick_cardNo_deposit" class="bank-number"></span>
                                        <div class="common-bank"><s></s><i class="iconfont icon-fuxuan common-bank-icon" style="color:#d71319;"></i><span>保存至常用卡</span></div>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>持卡人姓名</label>
                                </div>
                                <div class="type_mz fl">
                                    <div class="pdd">
                                        <input id="quick_payerName_deposit" style="background-color: #fff;" type="text" class="re_color f_ui-grey-input focus_on " datatype="zh" errormsg="请输入真实姓名"  class="re_color focus_on" placeholder="请输入联系人姓名">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>证件类型</label>
                                </div>
                                <div class="type_adress fl" id="city_4">
                                    <div class="City_components m_r10 prov">
                                        <select name="">
                                            <option value="1">身份证</option>
                                            <option value="">护照</option>
                                            <option value="">港澳通行证</option>
                                        </select>
                                        <i class="iconfont icon-downArrow"></i>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>证件号码</label>
                                </div>
                                <div class="type_mz type_phone fl">
                                    <div class="pdd">
                                        <input id="quick_idCode_deposit" style="background-color: #fff;" class="re_color f_ui-grey-input focus_on " datatype="*" errormsg="请输入证件号" type="text" placeholder="请输身份证号码">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>银行预留手机号</label>
                                </div>
                                <div class="type_mz type_phone fl">
                                    <div class="pdd">
                                        <input id="quick_payerPhone_deposit" style="background-color: #fff;" class="re_color f_ui-grey-input focus_on " datatype="m" errormsg="请输入预留手机号" type="text" placeholder="请输入手机号码">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>手机验证码</label>
                                </div>
                                <div class="type_mz fl">
                                    <div class="pdd pdd_y fl bank-input-span">
                                        <input name="checkCode" style="background-color: #fff;width: 184px;" class="re_color f_ui-grey-input focus_on " datatype="n" type="text" focus_on errormsg="请输入验证码" placeholder="请输入验证码" onkeyup="value=value.replace(/[^\d]/g,'')"><s class="iconfont icon-sc info-empty-btn isShow" style="right: 100px;"></s><button type="button" class="get-verification-btn ">获取验证码</button>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li type_li02 clearfix">
                                <div class="type_name fl"></div>
                                <div class="type_agree fl">
                                    <label class="la_agree">
                                        <i class="iconfont icon-fuxuan agreement-gou" style="color: #d71319;"></i> &nbsp;<span>同意</span><a href="支付协议.html" target="_blank">《支付服务协议》</a>
                                    </label>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name fl"></div>
                                <div class="type_register go_pay">
                                    <button id="confirm_quickpay_deposit" onclick="submitQuickpay_deposit();" class="register_btn" type="button">确认支付</button>
                                </div>
                            </li>
                        </ul>
                    </form>
                </div>
                <div class="right fr">
                    <div class="card">
                        <div class="title">
                        <span>
                            <svg class="icon" aria-hidden="true" style="position: relative;top: 2px;">
                                <use id="card_icon_deposit" xlink:href=""></use>
                            </svg>
                            	<b id="card_cardName_deposit"></b>
                        </span>
                            <span>储蓄卡</span>
                        </div>
                        <p id="card_cardNo_deposit" class=""></p>
                        <div class="card-icon clearfix">
                            <svg class="icon" aria-hidden="true" style="float: right; font-size: 60px;">
                                <use xlink:href="#icon-yinlian"></use>
                            </svg>
                        </div>
                    </div>
                    <div class="quota">
                        <div class="quota-num clearfix">
                            <div class="left fl">服务商</div>
                            <div class="right fl">融宝支付</div>
                        </div>
                        <div class="quota-num clearfix">
                            <div class="left fl">支付单笔限额</div>
                            <div id="quick_singleLimit_deposit" class="right fl"></div>
                        </div>
                        <div class="quota-num clearfix">
                            <div class="left fl">支付单日限额</div>
                            <div id="quick_dailyLimit_deposit" class="right fl"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 信用卡-->
        <div class="credit isShow">
            <div class="prompt">将用于实名认证，请务必使用本人信息填写。</div>
            <div class="content clearfix">
                <!-- 表单-->
                <div class="register_information fl">
                    <form id="J_form3" class="demo_form" action="<%=basePath%>/quickPay/confirmRecharge.action">
                    	<input id="busiCode" name="busiCode" type="hidden" value="109" />
	                    <input id="signType" name="signType" type="hidden" value="1" />
	                    <input id="version" name="version" type="hidden" value="v1.1" />
	                    <input id="merchantNo" name="merchantNo" type="hidden" value="M000016" />
	                    <input id="payType" name="payType" type="hidden" value="41" />
	                    <input id="quick_rsOrder" name="orderNo" type="hidden" /><!-- 获取验证码的时候设置 -->
                        <ul class="register_list">
                            <li class="type_li clearfix">
                                <div class="type_name fl">
                                    <label>付款银行</label>
                                </div>
                                <div class="type fl clearfix">
                                    <div class="icon fl"><svg class="icon" aria-hidden="true" style="top:3px;margin-right: 10px;">
                                    <use id="quick_icon_credit" xlink:href=""></use></svg>
                                    <span id="quick_bankName_credit"></span>
                                    <span id="quick_cardType_credit" style="color: #777;"></span>
                                    <a class="change-bank fr" href="javascript:void(0)" style="color: #238AF7">更换付款银行</a></div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>银行卡号</label>
                                </div>
                                <div class="type type_mz fl">
                                    <div class="pdd icon fl">
                                        <span id="quick_cardNo_credit" class="bank-number"></span>
                                        <div class="common-bank"><s></s><i class="iconfont icon-fuxuan common-bank-icon" style="color:#d71319;"></i><span>保存至常用卡</span></div>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>持卡人姓名</label>
                                </div>
                                <div class="type_mz fl">
                                    <div class="pdd">
                                        <input id="quick_payerName_credit" style="background-color: #fff;" type="text" class="re_color f_ui-grey-input focus_on " datatype="zh" errormsg="请输入真实姓名"  class="re_color focus_on" placeholder="请输入联系人姓名">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>证件类型</label>
                                </div>
                                <div class="type_adress fl" id="city_4">
                                    <div class="City_components m_r10 prov">
                                        <select name="">
                                            <option value="1">身份证</option>
                                            <option value="">护照</option>
                                            <option value="">港澳通行证</option>
                                        </select>
                                        <i class="iconfont icon-downArrow"></i>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>证件号码</label>
                                </div>
                                <div class="type_mz type_phone fl">
                                    <div class="pdd">
                                        <input id="quick_idCode_credit" style="background-color: #fff;" class="re_color f_ui-grey-input focus_on " datatype="*" errormsg="请输入证件号" type="text" placeholder="请输身份证号码">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>信用卡有效期</label>
                                </div>
                                <div class="type_adress fl" id="city_5">
                                    <div class="City_components m_r10 prov fl">
                                        <span id="quick_validthruMonth_credit" class="select-items" style="margin-left: 0;">请选择</span>月
                                        <!-- 月份下拉框-->
                                        <div class="month-drop-down-frame isShow">
                                            <div class="drop-down-frame">01</div>
                                            <div class="drop-down-frame">02</div>
                                            <div class="drop-down-frame">03</div>
                                            <div class="drop-down-frame">04</div>
                                            <div class="drop-down-frame">05</div>
                                            <div class="drop-down-frame">06</div>
                                            <div class="drop-down-frame">07</div>
                                            <div class="drop-down-frame">08</div>
                                            <div class="drop-down-frame">09</div>
                                            <div class="drop-down-frame">10</div>
                                            <div class="drop-down-frame">11</div>
                                            <div class="drop-down-frame">12</div>
                                        </div>
                                        <i class="iconfont icon-downArrow" style="right: 24px;"></i>
                                    </div>
                                    <div class="City_components m_r10 prov fl">
                                        <span id="quick_validthruYear_credit" class="select-items">请选择</span>年
                                        <!-- 年份下拉框-->
                                        <div class="year-drop-down-frame isShow">
                                            <div class="drop-down-frame">2011</div>
                                            <div class="drop-down-frame">2012</div>
                                            <div class="drop-down-frame">2013</div>
                                            <div class="drop-down-frame">2014</div>
                                            <div class="drop-down-frame">2015</div>
                                            <div class="drop-down-frame">2016</div>
                                            <div class="drop-down-frame">2017</div>
                                            <div class="drop-down-frame">2018</div>
                                            <div class="drop-down-frame">2019</div>
                                            <div class="drop-down-frame">2020</div>
                                            <div class="drop-down-frame">2021</div>
                                            <div class="drop-down-frame">2022</div>
                                            <div class="drop-down-frame">2023</div>
                                            <div class="drop-down-frame">2024</div>
                                            <div class="drop-down-frame">2025</div>
                                            <div class="drop-down-frame">2026</div>
                                            <div class="drop-down-frame">2027</div>
                                            <div class="drop-down-frame">2028</div>
                                            <div class="drop-down-frame">2029</div>
                                            <div class="drop-down-frame">2030</div>
                                        </div>
                                        <i class="iconfont icon-downArrow" style="right: 24px;"></i>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>信用卡安全码</label>
                                </div>
                                <div class="type_mz type_phone fl">
                                    <div class="pdd">
                                        <input id="quick_safetyCode_credit" style="background-color: #fff;" class="re_color f_ui-grey-input focus_on " datatype="*" errormsg="信用卡验证码" type="text" placeholder="卡背面末三位数字">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>银行预留手机号</label>
                                </div>
                                <div class="type_mz type_phone fl">
                                    <div class="pdd">
                                        <input id="quick_payerPhone_credit" style="background-color: #fff;" class="re_color f_ui-grey-input focus_on " datatype="m" errormsg="请输入预留手机号" type="text" placeholder="请输入手机号码">
                                        <s class="iconfont icon-sc info-empty-btn isShow"></s>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name number fl">
                                    <label>手机验证码</label>
                                </div>
                                <div class="type_mz fl">
                                    <div class="pdd pdd_y fl bank-input-span">
                                        <input style="background-color: #fff;width: 184px;" class="re_color f_ui-grey-input focus_on " datatype="n" type="text" focus_on errormsg="请输入验证码" placeholder="6位数字验证码" onkeyup="value=value.replace(/[^\d]/g,'')"><s class="iconfont icon-sc info-empty-btn isShow" style="right: 100px;"></s><button type="button" class="get-verification-btn ">获取验证码</button>
                                    </div>
                                </div>
                            </li>
                            <li class="type_li type_li02 clearfix">
                                <div class="type_name fl"></div>
                                <div class="type_agree fl">
                                    <label class="la_agree">
                                        <i class="iconfont icon-fuxuan agreement-gou" style="color: #d71319;"></i> &nbsp;<span>同意</span><a href="支付协议.html" target="_blank">《支付服务协议》</a>
                                    </label>
                                </div>
                            </li>
                            <li class="type_li clearfix">
                                <div class="type_name fl"></div>
                                <div class="type_register go_pay">
                                    <button id="confirm_quickpay_credit" onclick="submitQuickpay_credit();" class="register_btn" type="button">确认支付</button>
                                </div>
                            </li>
                        </ul>
                    </form>
                </div>
                <div class="right fr">
                    <div class="card">
                        <div class="title">
                        <span>
                            <svg class="icon" aria-hidden="true" style="position: relative;top: 2px;">
                                <use id="card_icon_credit" xlink:href=""></use>
                            </svg>
                           	 <b id="card_cardName_credit"></b>
                        </span>
                            <span>信用卡</span>
                        </div>
                        <p id="card_cardNo_credit" class=""></p>
                        <div class="card-icon clearfix">
                            <svg class="icon" aria-hidden="true" style="float: right; font-size: 60px;">
                                <use xlink:href="#icon-yinlian"></use>
                            </svg>
                        </div>
                    </div>
                    <div class="quota">
                        <div class="quota-num clearfix">
                            <div class="left fl">服务商</div>
                            <div class="right fl">融宝支付</div>
                        </div>
                        <div class="quota-num clearfix">
                            <div class="left fl">支付单笔限额</div>
                            <div id="quick_singleLimit_credit" class="right fl"></div>
                        </div>
                        <div class="quota-num clearfix">
                            <div class="left fl">支付单日限额</div>
                            <div id="quick_dailyLimit_credit" class="right fl"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 网银支付 -->
    <input id="accountType_personal_hid" type="hidden" /><!-- 针对个人网银，调转网银之前设值 -->
    <input id="bankInfo_personal_hid" type="hidden" />
    <input id="linePayType_personal_hid" type="hidden" />
    <!-- 个人网银-->
    <div class="personal personal-bank isShow">
        <ul class="clearfix">
        	<c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem" varStatus="item_status">
        		<c:if test="${deskItem.itemType == 2 }">
        			<c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank" varStatus="bank_status">
        				<li><svg class="icon" aria-hidden="true" style="font-size: 26px;margin-right:10px;top: 4px;">
        				<use xlink:href="#icon-${itemBank.bankName }"></use></svg>
        				<span>${itemBank.bankMark }</span><i class="iconfont icon-gou isShow"></i>
        				<input class="bankInfo_personal" type="hidden" value="${itemBank.bankName }" />
        				<input class="linePayType_personal" type="hidden" value="${deskItem.itemType }" />
        				<input class="bankName_personal" type="hidden" value="${itemBank.bankMark }" />
        				<input class="supportType_personal" type="hidden" value="${itemBank.supportType }" />
        				</li>
        			</c:forEach>
        		</c:if>
        	</c:forEach>
        </ul>
    </div>
    <!-- 个人网银 跳转-->
    <div class="enterprise personal-bank-infor isShow">
        <div class="prompt browser" style="display: none;">您当前的浏览器环境可能不支持您选择的网银，建议更换为IE浏览器。</div>
        <div class="content enterprise-content clearfix">
            <div class="enterprise-form clearfix" style="margin-bottom: 20px;line-height: 26px">
                <div class="left fl">付款银行</div>
                <div class="right fl">
                    <svg class="icon" aria-hidden="true" style="position: relative;top: 2px;">
                        <use id="bankImg_personal" xlink:href=""></use>
                    </svg>
                    <span id="bankName_personal"></span><a href="javascript:void (0);" class="change-bank">更换付款银行</a></div>
            </div>
            <div class="enterprise-form clearfix" style="margin-bottom: 20px">
                <div class="left fl">银行类型</div>
                <div class="right items fl">
                    <div id="cardType_deposit" class="item selected fl"><i id="10" class="iconfont icon-check"></i><span>储蓄卡</span></div>
                    <div id="cardType_credit" class="item fl"><i id="11" class="iconfont icon-check1"></i><span>信用卡</span></div>
                </div>
            </div>
            <div class="enterprise-form clearfix" style="margin-bottom: 30px">
                <div class="left fl" style="line-height: 34px">付款限额</div>
                <div class="right fl">
                    <%@include file="limit.jsp"%>
                    <button>跳转至网银并支付</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 企业网银-->
    <div class="personal enterprise-bank isShow">
        <ul class="clearfix">
        	<c:forEach items="${cashDeskData.cashierDeskDTO.cashierDeskItemDTOList }" var="deskItem" varStatus="item_status">
        		<c:if test="${deskItem.itemType == 1 }">
        			<c:forEach items="${deskItem.cashierItemBankDTOList }" var="itemBank" varStatus="bank_status">
        				<li><svg class="icon" aria-hidden="true" style="font-size: 26px;margin-right:10px;top: 4px;">
        				<use xlink:href="#icon-${itemBank.bankName }"></use></svg>
        				<span>${itemBank.bankMark }</span><i class="iconfont icon-gou isShow"></i>
        				<input class="bankInfo_enterprise" type="hidden" value="${itemBank.bankName }" />
        				<input class="linePayType_enterprise" type="hidden" value="${deskItem.itemType }" />
        				<input class="bankName_enterprise" type="hidden" value="${itemBank.bankMark }" />
        				</li>
        			</c:forEach>
        		</c:if>
        	</c:forEach>
        </ul>
    </div>
    <!-- 企业网银 跳转-->
    <div class="enterprise enterprise-bank-info isShow">
        <div class="prompt browser" style="display: none;">您当前的浏览器环境可能不支持您选择的网银，建议更换为IE浏览器。</div>
        <div class="content enterprise-content clearfix">
            <div class="enterprise-form clearfix" style="margin-bottom: 20px;line-height: 26px">
                <div class="left fl">付款银行</div>
                <div class="right fl">
                    <svg class="icon" aria-hidden="true" style="position: relative;top: 2px;">
                        <use id="bankImg_enterprise" xlink:href=""></use>
                    </svg>
                    <span id="bankName_enterprise"></span><a href="javascript:void (0);" class="change-bank">更换付款银行</a></div>
            </div>
            <div id="enterpriseCustomerNoDev" class="enterprise-form clearfix isShow" style="margin-bottom: 20px">
                <div class="left fl" style="line-height: 38px">企业银行客户号</div>
                <div class="right fl">
                    <input id="enterpriseCustomerNo" type="text" placeholder="请输入企业银行客户号"/>
                    <s class="iconfont icon-sc enterprise-empty-btn isShow"></s>
                </div>
            </div>
            <div class="enterprise-form clearfix" style="margin-bottom: 30px">
                <div class="right fl">
                    <button>跳转至网银并支付</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--微信支付 -->
<div class="wx-payment wx isShow">
    <div class="wx-payment-title">微信支付<i class="iconfont icon-sc close"></i></div>
    <div class="wx-payment-content">
        <p><span>￥</span><span id="wx_pay_amount"></span></p>
        <img id="wx_qrcode" src="" alt="微信扫描二维码支付"/>
        <div class="icon">
            <div class="left fl">
                <i class="iconfont icon-wx"></i>
            </div>
            <div class="right fl">
                <p>请使用微信扫一扫</p>
                <p>扫描二维码支付</p>
            </div>
        </div>
        <div class="count-down"></div>
        <div class="buttom"><a href="javascript:void(0);">更换支付方式</a></div>
    </div>
</div>
<!--支付宝支付 -->
<div class="wx-payment zfb isShow">
    <div class="wx-payment-title">支付宝支付<i class="iconfont icon-sc close"></i></div>
    <div class="wx-payment-content">
        <p><span>￥</span><span id="ali_pay_amount"></span></p>
        <img id="ali_qrcode" src="" alt="支付宝扫描二维码支付"/>
        <div class="icon">
            <div class="left fl"><i class="iconfont icon-zfb"></i></div>
            <div class="right fl">
                <p>请使用支付宝扫一扫</p>
                <p>扫描二维码支付</p>
            </div>
        </div>
        <div class="count-down"></div>
        <div class="buttom"><a href="javascript:void(0);">更换支付方式</a></div>
    </div>
</div>
<input id="qr_orderNo_hid" type="hidden">
<form action="<%=basePath%>/account/error_cashier.action" name="error_cashier"  id="error_cashier" method="post">
  <input name="msg" id="error_cashier_msg" type="hidden"/>
</form>

<!-- 快捷支付验证码-->
<input name="rsOrderNo" id="rsOrderNo" type="hidden">
<div class="payment-verification isShow">
    <div class="payment-verification-title">收银台快捷支付验证码<i class="iconfont icon-sc close"></i></div>
    <div class="payment-verification-content">
        <div class="phone clearfix">
            <div class="left fl">安全手机号</div>
            <div class="right fl"><span id="safe_payerphone"></span></div>
        </div>
        <div class="phone message clearfix">
            <div class="left fl">短信验证</div>
            <div class="right fl"><input type="text" placeholder="请输入验证码" onkeyup="value=value.replace(/[^\d]/g,'')"/><i class="iconfont icon-sc forgot-password-empty isShow"></i><button class="obtain-btn obtain-btn-1">重新获取</button></div>
            <!--<span class="verification-sentence isShow">验证码不能为空</span>-->
        </div>
        <div class="phone verification-code clearfix">
            <div class="left fl"></div>
            <div class="right fl"><i class="iconfont icon-check"></i>验证码已发送至您的手机</div>
        </div>
        <div class="phone confirm clearfix">
            <div class="left fl"></div>
            <div class="right fl"><button class="confirm-btn">确认支付</button></div>
        </div>
    </div>
</div>
<!--付款至供应商泰坦金融账户 -->
<div class="payment2account payment2taitan isShow">
    <div class="title">付款至供应商泰坦金融账户<i class="iconfont icon-sc close"></i></div>
    <div class="content clearfix">
        <div class="left fl">
            <p style="font-size: 14px;">历史收款账户</p>
            <ul id="payeeAccountList" class="history-items" data-type="number">
                <li class="input-text">
                    <input id="orgNameInput" type="text" placeholder="公司名称"/>
                    <i onclick="queryAccountHistory('1');" class="iconfont icon-search"></i>
                </li>
            </ul>
            <div class="left-veil isShow" style="position: absolute;top: 0;left: 0; width: 100%;height: 100%;background-color: rgba(255,255,255,0.6); z-index: 9;"></div>
        </div>
        <div class="right fl">
            <form id="J_form2" class="demo_form" action="">
                <p style="font-size: 14px;">新增收款账户</p>
                <div class="input-text">
                    <span>账户名称</span>
                    <input id="orgNameNew" class="re_color f_ui-grey-input focus_on " datatype="*" errormsg="账户名称不能为空" type="text" placeholder="收款方泰坦金融机构名称"/>
                    <i class="iconfont icon-sc number-empty-btn isShow"></i>
                </div>
                 <div class="input-text">
                     <span>泰坦码</span>
                    <input id="titanCodeNew" class="re_color f_ui-grey-input focus_on " datatype="*" errormsg="泰坦码不能为空" type="text" placeholder="收款方泰坦码"/>
                     <i class="iconfont icon-sc number-empty-btn isShow"></i>
                </div>
                <button type="button" onclick="addNewAccount();">确认</button>
            </form>
            <div class="right-veil" style="position: absolute;top: 0;left: 0; width: 100%;height: 100%;background-color: rgba(255,255,255,0.6);"></div>
        </div>
    </div>
</div>

<form action="<%=basePath%>/payment/payConfirmPage.action" id="payConfirmPage" method="post">
  <input name="orderNo" id="check_orderNo" type="hidden">
  <input name="expand" id="check_expand" type="hidden">
</form>

</body>
<%@include file="/comm/upgrade/upgrade-js.jsp"%>
<script>
	//下拉框
	$(".select-items").on("click",function(){
	    $(".select-items").next().addClass("isShow");
	    $(this).next().removeClass("isShow");
	});
	$(".shortcut").on("clcik",function(){
	    $(".select-items").next().addClass("isShow");
	});
	$(".month-drop-down-frame").on("click",".drop-down-frame",function(){
	    var text = $(this).text();
	    $(this).parent().prev().text(text);
	    $(this).parent().addClass("isShow");
	});
	$(".year-drop-down-frame").on("click",".drop-down-frame",function(){
	    var text = $(this).text();
	    $(this).parent().prev().text(text);
	    $(this).parent().addClass("isShow");
	});
	// 进度条
	function showLoading(){
		F.loading_line.show() //显示loading
		setTimeout(function(){
		    F.loading_line.remove() //隐藏loading
		},12000)
	}

    // 表单验证
    var quickpayDeposit = new validform('#J_form1',{
        noMust:true
    });
    var addAccount = new validform('#J_form2',{
        noMust:true
    });
    var quickpayCredit = new validform('#J_form3',{
        noMust:true
    });
    var d=new validform('#J_form4',{
        noMust:true
    });
    // 密码
    var PasswordStr=new sixDigitPassword("passwordbox");
    var PasswordStr1=new sixDigitPassword("passwordbox1");
    var PasswordStr2=new sixDigitPassword("passwordbox2");
    /* $("#btn").on('click',function(){
        alert (PasswordStr.returnStr()+"+"+PasswordStr1.returnStr()+"+"+PasswordStr2.returnStr())
    }) */
    
    
    $("document").ready(function (){
		//初始化收银台数据集合
		initData();	
		isMorePay();
		setRate();
		showHistoryAccount();
		isIE();
	}); 
    function initData(){
    	buildCashierData({
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
    		partnerOrgCode:'${cashDeskData.partnerOrgCode}',
    		jrVersion:'${cashDeskData.jrVersion}'
    	});
    }
    
    //更多支付方式
    function isMorePay(){
    	if('${cashDeskData.commonPayHistoryList }' != null){
    		var count = '${cashDeskData.commonPayHistoryList.size() }'
    		if(count > 1){
    			$(".more-payment").show();
    		}
    	}
    }
    
    //费率显示
    function setRate(){
    	if('${cashDeskData.paySource }' == '2'){//目前财务付款才有手续费，第一次进来默认选中微信支付
    		rateCompute('wx', 'commpay');
    	}
    }
    
    //查询收款账户记录
    function queryAccountHistory(querytype){
    	
    	var param;
    	//1是点搜索按钮查询
    	if(querytype == '1'){
    		if($.trim($("#orgNameInput").val()).length > 0){
    			param = {
    	    			payeruserid: '${cashDeskData.accountHistoryDTO.payeruserid}',
    	    			orgname: $.trim($("#orgNameInput").val())
    	    		}
    		}else{
    			param = {
    	   				payeruserid: '${cashDeskData.accountHistoryDTO.payeruserid}',
    	                inaccountcode: '${cashDeskData.accountHistoryDTO.inaccountcode}',
    	                outaccountcode: '${cashDeskData.accountHistoryDTO.outaccountcode}'
    	    		}
    		}
    		
    	}else{
    		if($("#payeeAccountList li").length > 1){
        		return;
        	}
    		param = {
   				payeruserid: '${cashDeskData.accountHistoryDTO.payeruserid}',
                inaccountcode: '${cashDeskData.accountHistoryDTO.inaccountcode}',
                outaccountcode: '${cashDeskData.accountHistoryDTO.outaccountcode}'
    		}
    	}
    	
    	$.ajax({
        	type: "post",
            dataType : 'json',
            async:false,
            data: param,
            url : "../account/queryAccountHistory.action",
            success : function(data){
            	$("#payeeAccountList li").remove(".history-item");
            	if(data.accountHistoryDTOList != null && data.accountHistoryDTOList.length > 0){
            		var payeeAccountHtml = "";
            		for (var i = 0; i < data.accountHistoryDTOList.length; i++) {
            			if(i == 0){
            				payeeAccountHtml = payeeAccountHtml + 
	            			"<li class='history-item clearfix selected'>" +
		                        "<i class='iconfont icon-gou'></i>" +
		                        "<div class='li-left fl'><span></span></div>" +
		                        "<div class='li-right fl'>" +
		                            "<p>"+data.accountHistoryDTOList[i].orgname+"</p>" +
		                            "<p>"+data.accountHistoryDTOList[i].titancode+"</p>" +
		                        "</div>" +
		                    "</li>"
            			}else{
            				payeeAccountHtml = payeeAccountHtml + 
	            			"<li class='history-item clearfix'>" +
		                        "<i class='iconfont icon-gou isShow'></i>" +
		                        "<div class='li-left fl'><span></span></div>" +
		                        "<div class='li-right fl'>" +
			                        "<p>"+data.accountHistoryDTOList[i].orgname+"</p>" +
		                            "<p>"+data.accountHistoryDTOList[i].titancode+"</p>" +
		                        "</div>" +
		                    "</li>"
            			}
					}
            		$("#payeeAccountList").append(payeeAccountHtml);
            	}
            }
        });
    }
    
    function addNewAccount(){
    	if(addAccount.validate()){
    		isShowVeil("#Veil","hide");
            $(".payment2account").hide();
            $(".payment-type .payment-taitang").addClass("isShow");
    		$("#recieveOrgName_p").text($("#orgNameNew").val());
            $("#recieveTitanCode_p").text($("#titanCodeNew").val());
            $("#recieveOrgName").val($("#orgNameNew").val());
            $("#recieveTitanCode").val($("#titanCodeNew").val());
            $(".new-payment-bank").removeClass("isShow");//选中后显示在收银台收款账户
    	}
    }
    
    function showHistoryAccount(){
    	if('${cashDeskData.payerTypeEnum.key }' != '3'){
    		return;
    	}
    	var orgName;
    	var titanCode;
    	if('${cashDeskData.accountHistoryDTO}' != null){
    		orgName = '${cashDeskData.accountHistoryDTO.orgname}';
    		titanCode = '${cashDeskData.accountHistoryDTO.titancode}';
    	}
    	if(orgName.length > 0 && titanCode.length > 0){
    		$(".payment-type .payment-taitang").addClass("isShow");
    		$("#recieveOrgName_p").text(orgName);
            $("#recieveTitanCode_p").text(titanCode);
            $("#recieveOrgName").val(orgName);
            $("#recieveTitanCode").val(titanCode);
            $(".new-payment-bank").removeClass("isShow");
    	}
    }
    
    //添加快捷支付--储蓄卡--表单提交事件
    function submitQuickpay_deposit(){debugger;
    	if(quickpayDeposit.validate()){
    		$("#confirm_quickpay_deposit").addClass("disabledButton").attr("disabled", true);
        	showLoading();
        	top.F.loading.show();
        	$("#J_form1").submit();
    	}
    }
    //添加快捷支付--信用卡--表单提交事件
    function submitQuickpay_credit(){debugger;
    	if(quickpayCredit.validate()){
    		$("#confirm_quickpay_credit").addClass("disabledButton").attr("disabled", true);
        	showLoading();
        	top.F.loading.show();
        	$("#J_form3").submit();
    	}
    }
    
    function isIE(){
    	/* var isIE = false;
    	if (!!window.ActiveXObject || "ActiveXObject" in window){
    		isIE = true;
    	}
    	if(!isIE){
    		$(".browser").show();
    	} */
    	
	    /* if(!document.all){ 
	    	$(".browser").show();
    	} */
    	$(".browser").show();
    }

</script>
</html>