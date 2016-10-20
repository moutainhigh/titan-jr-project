<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/comm/taglib.jsp"%>
<div class="bank_notice bank-limit-wrap">
			<h3 class="bank_notice_h3">付款金额可能超限，限额如下：</h3>
			<!-- 工商银行（借记卡） -->
			<div class="bank-limit icbc-jjk ">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>100万元</td>
					<td>100万元</td>
					<td class="bank_notice_td01">U盾用户</td>
					<td rowspan="4">工商银行客服电话：95588</td>
				</tr>
				<tr>
					<td>100万元</td>
					<td>500万元</td>
					<td class="bank_notice_td01">二代U盾用户</td>
				</tr>
				<tr>
					<td>500元</td>
					<td>1,000元</td>
					<td class="bank_notice_td01">电子银行口令卡 -- 非手机短信认证客户</td>
				</tr>
				<tr>
					<td>2,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">电子银行口令卡 -- 口令卡手机短信认证客户</td>
				</tr>
			</tbody></table>
			</div>
			<!-- 工商银行（信用卡） -->
			<div class="bank-limit icbc-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>100万元</td>
					<td>不超过信用卡额度，最高不超过100万元</td>
					<td class="bank_notice_td01">U盾用户</td>
					<td rowspan="4">工商银行客服电话：95588</td>
				</tr>
				<tr>
					<td>100万元</td>
					<td>不超过信用卡额度，最高不超过100万元</td>
					<td class="bank_notice_td01">二代U盾用户</td>
				</tr>
				<tr>
					<td>500元</td>
					<td>500元</td>
					<td class="bank_notice_td01">电子银行口令卡 -- 非手机短信认证客户</td>
				</tr>
				<tr>
					<td>2,000万元</td>
					<td>2,000万元</td>
					<td class="bank_notice_td01">电子银行口令卡 -- 口令卡手机短信认证客户</td>
				</tr>
			</tbody></table>
			</div>
			<!-- 农业银行（借记卡） -->
			<div class="bank-limit abc-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>1,000元</td>
					<td>3,000元</td>
					<td class="bank_notice_td01">动态口令卡用户</td>
					<td rowspan="2">农业银行客服电话：95599</td>
				</tr>
				<tr>
					<td>无限额</td>
					<td>无限额</td>
					<td class="bank_notice_td01">移动证书用户</td>
				</tr>
			</tbody></table>
			</div>
			<!-- 农业银行（信用卡） -->
			<div class="bank-limit abc-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>1,000元</td>
					<td>3,000元</td>
					<td class="bank_notice_td01">动态口令卡用户</td>
					<td rowspan="2">农业银行客服电话：95599</td>
				</tr>
				<tr>
					<td>无限额</td>
					<td>无限额</td>
					<td class="bank_notice_td01">移动证书用户</td>
				</tr>					
			</tbody></table>
			</div>
			<!-- 中国银行（借记卡） -->
			<div class="bank-limit boc-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>10,000元</td>
					<td>50,000元</td>
					<td class="bank_notice_td01">网银用户</td>
					<td>中国银行客服电话：95566</td>
				</tr>					
			</tbody></table>
			</div>
			<!-- 中国银行（信用卡） -->
			<div class="bank-limit boc-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>不超过信用卡额度，最高不超过5,000元</td>
					<td class="bank_notice_td01">网银用户</td>
					<td rowspan="">中国银行客服电话：95566</td>
				</tr>									
			</tbody></table>
			</div>
			<!-- 建设银行（借记卡） -->
			<div class="bank-limit ccb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>1,000元</td>
					<td>1,000元</td>
					<td class="bank_notice_td01">开通网上支付功能</td>
					<td rowspan="4">建设银行客服电话：95533</td>
				</tr>	
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">动态口令卡用户</td>					
				</tr>	
				<tr>
					<td>5万元</td>
					<td>10万元</td>
					<td class="bank_notice_td01">一代网银盾</td>					
				</tr>
				<tr>
					<td>50万元</td>
					<td>50万元</td>
					<td class="bank_notice_td01">二代网银盾</td>					
				</tr>			
			</tbody></table>
			</div>
			<!-- 建设银行（信用卡） -->
			<div class="bank-limit ccb-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>500元</td>
					<td>500元</td>
					<td class="bank_notice_td01">开通网上支付功能</td>
					<td rowspan="">建设银行客服电话：95533</td>
				</tr>									
			</tbody></table>
			</div>
			<!-- 交通银行（借记卡） -->
			<div class="bank-limit comm-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">手机注册版用户</td>
					<td rowspan="2">交通银行客服电话：95559</td>
				</tr>	
				<tr>
					<td>5万元</td>
					<td>5万元</td>
					<td class="bank_notice_td01">证书认证版用户</td>					
				</tr>		
			</tbody></table>
			</div>
			<!-- 交通银行（信用卡） -->
			<div class="bank-limit comm-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="190">
					<col width="190">
					<col width="260">
					<col width="260">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>单月限额</td>						
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>不超过信用卡额度，最高不超过1,000元</td>
					<td>不超过信用卡额度，最高不超过1,000元</td>
					<td class="bank_notice_td01">无限额</td>
					<td class="bank_notice_td01">手机注册版用户</td>
					<td rowspan="2">交通银行客服电话：95559</td>
				</tr>	
				<tr>
					<td>不超过信用卡额度，最高不超过1,000元</td>
					<td>不超过信用卡额度，最高不超过1,000元</td>
					<td class="bank_notice_td01">3万元</td>
					<td class="bank_notice_td01">证书认证版用户</td>						
				</tr>								
			</tbody></table>
			</div>
			<!-- 招商银行（借记卡） -->
			<div class="bank-limit cmb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>客户自行设定，最高无限额</td>
					<td>客户自行设定，最高无限额</td>
					<td class="bank_notice_td01">专业版用户</td>
					<td rowspan="3">招商银行客服电话：95555</td>
				</tr>	
				<tr>
					<td>5,000元</td>
					<td>10,000元</td>
					<td class="bank_notice_td01">一卡通支付</td>					
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">一卡通直付</td>					
				</tr>			
			</tbody></table>
			</div>
			<!-- 招商银行（信用卡） -->
			<div class="bank-limit cmb-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>2,000元</td>
					<td>无限额</td>
					<td class="bank_notice_td01">实物类付款</td>
					<td rowspan="2">招商银行客服电话：95555</td>
				</tr>	
				<tr>
					<td>500元</td>
					<td>无限额</td>
					<td class="bank_notice_td01">虚拟类付款</td>					
				</tr>								
			</tbody></table>
			</div>
			<!-- 浦发银行（借记卡） -->
			<div class="bank-limit spdb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>客户自行设定</td>
					<td>客户自行设定</td>
					<td class="bank_notice_td01">数字证书版用户</td>
					<td rowspan="2">浦发银行客服电话：95528</td>
				</tr>	
				<tr>
					<td>5万元</td>
					<td>5万元</td>
					<td class="bank_notice_td01">动态密码版用户</td>					
				</tr>
			</tbody></table>
			</div>
			<!-- 民生银行（借记卡） -->
			<div class="bank-limit cmbc-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">短信验证码支付</td>
					<td rowspan="4">民生银行客服电话：95568</td>
				</tr>	
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">浏览器证书支付</td>					
				</tr>
				<tr>
					<td>50万元</td>
					<td>50万元</td>
					<td class="bank_notice_td01">动态口令（OTP）支付</td>					
				</tr>	
				<tr>
					<td>50万元</td>
					<td>50万元</td>
					<td class="bank_notice_td01">U宝支付</td>
				</tr>		
			</tbody></table>
			</div>
			<!-- 民生银行（信用卡） -->
			<div class="bank-limit cmbc-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">短信验证码支付</td>
					<td rowspan="4">民生银行客服电话：95568</td>
				</tr>	
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">一卡通支付</td>					
				</tr>
				<tr>
					<td>50万元</td>
					<td>50万元</td>
					<td class="bank_notice_td01">动态口令（OTP）支付</td>					
				</tr>	
				<tr>
					<td>50万元</td>
					<td>50万元</td>
					<td class="bank_notice_td01">U宝支付</td>
				</tr>			
			</tbody></table>
			</div>
			<!-- 兴业银行（借记卡） -->
			<div class="bank-limit cib-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>100万元</td>
					<td>100万元</td>
					<td class="bank_notice_td01">柜面开通支付功能 -- 网盾或短信口令</td>
					<td rowspan="4">兴业银行客服电话：95561</td>
				</tr>	
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">柜面开通支付功能 -- 令牌保护</td>					
				</tr>
				<tr>
					<td>100万元</td>
					<td>100万元</td>
					<td class="bank_notice_td01">网银开通支付功能 -- 网盾</td>					
				</tr>	
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">网银开通支付功能 -- 令牌或短信或令牌+短信</td>
				</tr>		
			</tbody></table>
			</div>
			<!-- 兴业银行（借记卡） -->
			<div class="bank-limit cib-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>客户信用卡额度</td>
					<td>客户信用卡额度</td>
					<td class="bank_notice_td01">无</td>
					<td rowspan="">兴业银行客服电话：95561</td>
				</tr>								
			</tbody></table>
			</div>
			<!-- 光大银行（借记卡） -->
			<div class="bank-limit ceb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">浏览器证书用户</td>
					<td rowspan="2">光大银行客服电话：95595</td>
				</tr>	
				<tr>
					<td>20万元</td>
					<td>50万元</td>
					<td class="bank_notice_td01">阳光网盾证书用户</td>					
				</tr>							
			</tbody></table>
			</div>
			<!-- 光大银行（贷记卡） -->
			<div class="bank-limit ceb-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>300元</td>
					<td>300元</td>
					<td class="bank_notice_td01">无</td>
					<td rowspan="">光大银行客服电话：95595</td>
				</tr>								
			</tbody></table>
			</div>
			<!-- 平安银行（借记卡） -->
			<div class="bank-limit pingan-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5万元</td>
					<td>5万元</td>
					<td class="bank_notice_td01">手机动态口令用户</td>
					<td rowspan="2">平安银行客服电话：95511</td>
				</tr>	
				<tr>
					<td>无限额</td>
					<td>无限额</td>
					<td class="bank_notice_td01">KEY盾</td>					
				</tr>							
			</tbody></table>
			</div>
			<!-- 平安银行（信用卡） -->
			<div class="bank-limit pingan-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="260">
					<col width="260">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>每日支付次数</td>
					<td>需要满足的条件</td>						
					<td>备注</td>
				</tr>
				<tr>
					<td>3,000元</td>
					<td>3,000元</td>
					<td class="bank_notice_td01">10次</td>
					<td class="bank_notice_td01">无</td>						
					<td rowspan="">平安银行客服电话：95511</td>
				</tr>								
			</tbody></table>
			</div>
			<!-- 中信银行（借记卡） -->
			<div class="bank-limit citic-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>1,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">文件正式用户</td>
					<td rowspan="3">中信银行客服电话：95558</td>
				</tr>	
				<tr>
					<td>10,000元</td>
					<td>50,000元</td>
					<td class="bank_notice_td01">动态口令文件证书用户</td>					
				</tr>	
				<tr>
					<td>无限额</td>
					<td>无限额</td>
					<td class="bank_notice_td01">USBKEY移动证书用户</td>					
				</tr>						
			</tbody></table>
			</div>
			<!-- 中信银行（信用卡） -->
			<div class="bank-limit citic-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>					
					<td>需要满足的条件</td>						
					<td>备注</td>
				</tr>
				<tr>
					<td>客户信用卡额度</td>
					<td>客户信用卡额度</td>
					<td class="bank_notice_td01">无</td>					
					<td rowspan="">中信银行客服电话：95558</td>
				</tr>								
			</tbody></table>
			</div>
			<!-- 华夏银行（借记卡） -->
			<div class="bank-limit hxb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">无</td>
					<td rowspan="">华夏银行客服电话：95577</td>
				</tr>					
			</tbody></table>
			</div>
			<!-- 华夏银行（信用卡） -->
			<div class="bank-limit hxb-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>					
					<td>需要满足的条件</td>						
					<td>备注</td>
				</tr>
				<tr>
					<td>不超过信用卡额度，最高不超过5,000元</td>
					<td>不超过信用卡额度，最高不超过5,000元</td>
					<td class="bank_notice_td01">无</td>					
					<td rowspan="">华夏银行客服电话：95577</td>
				</tr>								
			</tbody></table>
			</div>
			<!-- 广发银行（借记卡） -->
			<div class="bank-limit cgb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">手机动态验证码</td>
					<td rowspan="3">广发银行客服电话：95508</td>
				</tr>
				<tr>
					<td>5万元</td>
					<td>5万元</td>
					<td class="bank_notice_td01">KEY令</td>						
				</tr>
				<tr>
					<td>100万元</td>
					<td>100万元</td>
					<td class="bank_notice_td01">Key盾</td>
				</tr>
			</tbody></table>
			</div>
			<!-- 广发银行（借记卡） -->
			<div class="bank-limit cgb-xyk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5,000元</td>
					<td>5,000元</td>
					<td class="bank_notice_td01">手机动态验证码</td>
					<td rowspan="3">广发银行客服电话：95508</td>
				</tr>
				<tr>
					<td>5万元</td>
					<td>5万元</td>
					<td class="bank_notice_td01">KEY令</td>						
				</tr>
				<tr>
					<td>100万元</td>
					<td>100万元</td>
					<td class="bank_notice_td01">Key盾</td>
				</tr>							
			</tbody></table>
			</div>
			<!-- 邮储银行（借记卡） -->
			<div class="bank-limit psbc-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5万元</td>
					<td>5万元</td>
					<td class="bank_notice_td01">个人网银短信客户</td>
					<td rowspan="4">邮储银行客服电话：95580</td>
				</tr>
				<tr>
					<td>2万元</td>
					<td>2万元</td>
					<td class="bank_notice_td01">手机银行普通客户</td>						
				</tr>
				<tr>
					<td>200万元</td>
					<td>200万元</td>
					<td class="bank_notice_td01">手机银行万能版客户</td>
				</tr>
				<tr>
					<td>500万元</td>
					<td>500万元</td>
					<td class="bank_notice_td01">办理USB-KEY并开通短信服务金卡客户</td>
				</tr>
			</tbody></table>
			</div>
			<!-- 贵阳银行（借记卡） -->
			<div class="bank-limit gyccb-jjk hide">
			<table class="bank_notice_table" width="100%" cellspacing="0" cellpadding="0">
				<colgroup>
					<col width="185">
					<col width="260">
					<col width="395">
					<col>
				</colgroup>
				<tbody><tr>
					<td>单笔限额</td>
					<td>单日限额</td>
					<td>需要满足的条件</td>
					<td>备注</td>
				</tr>
				<tr>
					<td>5万元</td>
					<td>20万元</td>
					<td class="bank_notice_td01">卡密支付</td>
					<td rowspan="2">贵阳银行客服电话：40011-96033<br>可通过营业网点修改银行卡支付额度</td>
				</tr>
				<tr>
					<td>2,000元</td>
					<td>10,000元</td>
					<td class="bank_notice_td01">网银支付</td>						
				</tr>								
			</tbody></table>
			</div>		
		</div>	
		
		
<script type="text/javascript">

 
/**
 * 显示相应的限额
 * rObj : 单选select
 */
function bankCheckRadio(rObj){
	var bankname = $(rObj).val();
	var dataIndex = $(rObj).attr("data-index");
	var itemType = $("#item-"+dataIndex).attr("data-index");
	var bankType= "jjk";
	$(".bank-limit").addClass("hide");
	if(itemType==2){//个人银行
		bankType= "jjk";
	}else if(itemType==3){//信用卡
		bankType= "xyk";
	}else if(itemType==1){//企业银行
		$(".bank-limit-wrap").hide();
		return;
	}
	$("."+bankname+"-"+bankType).removeClass("hide");
	//不存在限额，则隐藏提示
	if($("."+bankname+"-"+bankType).length==0){
		$(".bank-limit-wrap").hide();
	}else{
		$(".bank-limit-wrap").show();
	}
}

</script>		

