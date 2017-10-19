/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanjrUpgradeHander.java
 * @author Jerry
 * @date 2017年8月4日 上午10:32:51  
 */
package com.fangcang.titanjr.pay.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.enums.SupportCardTypeEnum;
import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;
import com.fangcang.titanjr.common.enums.FreezeTypeEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.dto.bean.CashierDeskItemDTO;
import com.fangcang.titanjr.dto.bean.CashierItemBankDTO;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.util.StringUtil;

/**
 * 泰坦金融升级助手
 * @author Jerry
 * @date 2017年8月4日 上午10:32:51  
 */
public class TitanjrUpgradeHander {
	
	private static final Log log = LogFactory.getLog(TitanjrUpgradeHander.class);
	
	/**
	 * 检查订单中的信息是否合法
	 * @author Jerry
	 * @date 2017年8月4日 上午10:36:35
	 * @param dto
	 * @return
	 */
	public static boolean checkOrderInfo(TitanOrderRequest dto){
		
		if (!StringUtil.isValidString(dto.getPayerType())) {
			log.error("PayerType is null");
			return false;
		}
		PayerTypeEnum pe = PayerTypeEnum.getPayerTypeEnumByKey(dto
				.getPayerType());
		if (pe == null) {
			log.error("PayerType convert is null");
			return false;
		}
		if (pe == PayerTypeEnum.B2B_WX_PUBLIC_PAY) {
			log.error("PayerType is B2B_WX_PUBLIC_PAY");
			return false;
		}

		if (!pe.isRWL() && !StringUtil.isValidString(dto.getAmount())) {
			log.error("Amount is null");
			return false;
		}
		if (StringUtil.isValidString(dto.getAmount())) {
			String neg = "(^[1-9]{1}\\d{0,20}(\\.\\d{1,2})?$)";
			String neg2 = "(^[0]{1}(\\.\\d{1,2})?$)";
			if (!(Pattern.matches(neg, dto.getAmount()) || Pattern.matches(
					neg2, dto.getAmount()))) {
				log.error("传入金额格式错误");
				return false;
			}
		}

		if (!pe.isRWL() && new BigDecimal(dto.getAmount()).compareTo(BigDecimal.ZERO) <= 0 
				|| new BigDecimal(dto.getAmount()).compareTo(new BigDecimal(9999999)) > 0) {
			log.error("传入金额小等于0 或者超过7位数");
			return false;
		}

		if (StringUtil.isValidString(dto.getCurrencyType())
				&& !dto.getCurrencyType().equals(CommonConstant.CURRENT_TYPE)) {
			log.error("Currency type must be RMB ");
			return false;
		}

		/**
		 * 非充值、提现、贷款的goodsId是第三方系统的订单号，必传
		 */
		if (!pe.isRWL() && !StringUtil.isValidString(dto.getGoodsId())) {
			log.error("GoodsId is null");
			return false;
		}

		/**
		 * 必传付款方
		 */
		if(pe.isNeedPayerInfo()){
			if(!StringUtil.isValidString(dto.getPartnerOrgCode()) || !StringUtil.isValidString(
					dto.getOrgCode()) || !StringUtil.isValidString(dto.getUserId())){
				log.error(pe + "--payer is null");
				return false;
			}
		}

		/**
		 * 必传收款方 
		 */
		if (pe.isNeedPayeeInfo() && !StringUtil.isValidString(dto.getRuserId())) {
			log.error(pe + "--ruserId is null");
			return false;
		}

		if (StringUtil.isValidString(dto.getEscrowedDate())) {
			try {

				String regex = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
				boolean flg = Pattern.matches(regex, dto.getEscrowedDate());
				if (!flg) {
					log.error("parse escrowedDate fail.");
					return false;
				}
				DateUtil.sdf.parse(dto.getEscrowedDate());
			} catch (ParseException e) {
				log.error("parse escrowedDate fail.");
				return false;
			}
		}
		if (StringUtil.isValidString(dto.getNotify())) {
			try {
				dto.setNotify(URLDecoder.decode(dto.getNotify(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("notify url URLDecoder fail.");
			}
		}

		if (StringUtil.isValidString(dto.getCheckOrderUrl())) {
			try {
				dto.setCheckOrderUrl(URLDecoder.decode(dto.getCheckOrderUrl(),
						"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("CheckOrderUrl URLDecoder fail.");
			}
		}
		
		//默认冻结方案2
		if(!StringUtil.isValidString(dto.getFreezeType())){
			dto.setFreezeType(FreezeTypeEnum.FREEZE_PAYEE.getKey());
		}
		
		//如果付款方不用自己的账户，则不允许使用冻结方案3
		if(!StringUtil.isValidString(dto.getPartnerOrgCode()) || !StringUtil.isValidString(
				dto.getOrgCode()) || !StringUtil.isValidString(dto.getUserId())){
			if(StringUtil.isValidString(dto.getFreezeType()) && FreezeTypeEnum.FREEZE_PAYER.getKey()
					.equals(dto.getFreezeType())){
				log.error("freezeType error");
				return false;
			}
		}

		return true;
		
	}
	
	
	/**
	 * 重新设置收银台的数据结构（新版收银台）
	 * @author Jerry
	 * @date 2017年7月24日 上午10:10:13 
	 */
	public static BaseResponse resetCashierDeskItem(CashierDeskDTO cashierDeskDTO){
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		try {
			Integer delCreditCardCashierIndex = null; //需要删除的信用卡支付下标
			List<CashierItemBankDTO> personalEBankList = new ArrayList<CashierItemBankDTO>(); //用来保存储蓄卡和信用卡合并后银行列表
			List<CashierItemBankDTO> depositCardList = null; //原储蓄卡银行列表（个人网银）
			List<CashierItemBankDTO> creditCardList = null; //原信用卡银行列表
			List<CashierDeskItemDTO> cashierDeskItemDTOList = cashierDeskDTO.getCashierDeskItemDTOList(); //收银台支付方式
			if(CollectionUtils.isEmpty(cashierDeskItemDTOList)){
				log.error("收银台支付方式列表为空");
				baseResponse.putErrorResult("收银台支付列表为空");
			}
			
			for (int i = 0; i < cashierDeskItemDTOList.size(); i++) {
				if(CashierItemTypeEnum.isPersonalEbank(String.valueOf(cashierDeskItemDTOList.get(i).getItemType()))){
					depositCardList = cashierDeskItemDTOList.get(i).getCashierItemBankDTOList();
				}
				if(CashierItemTypeEnum.isCreditCard(String.valueOf(cashierDeskItemDTOList.get(i).getItemType()))){
					creditCardList = cashierDeskItemDTOList.get(i).getCashierItemBankDTOList();
					delCreditCardCashierIndex = i;
				}
			}
			
			if(CollectionUtils.isNotEmpty(depositCardList)){
				for (CashierItemBankDTO depositCard : depositCardList) {
					depositCard.setSupportType(String.valueOf(SupportCardTypeEnum.DEPOSIT.key));
				}
				//将原个人网银支付银行列表放进personalEBankList
				personalEBankList.addAll(depositCardList);
			}
			
			//用信用卡支付银行列表和personalEBankList对比合并
			if(CollectionUtils.isNotEmpty(creditCardList)){
				
				if(CollectionUtils.isEmpty(personalEBankList)){
					for (CashierItemBankDTO creditCard : creditCardList) {
						creditCard.setSupportType(String.valueOf(SupportCardTypeEnum.CREDIT.key));
					}
					personalEBankList.addAll(creditCardList);
					
				}else{
					for (int i = 0; i < creditCardList.size(); i++) {
						boolean isBoth = false;
						for (int j = 0; j < personalEBankList.size(); j++) {
							if(creditCardList.get(i).getBankName().equals(personalEBankList.get(j).getBankName())){
								personalEBankList.get(j).setSupportType(String.valueOf(SupportCardTypeEnum.BOTH.key));
								isBoth = true;
								break;
							}
						}
						if(!isBoth){
							creditCardList.get(i).setSupportType(String.valueOf(SupportCardTypeEnum.CREDIT.key));
							personalEBankList.add(creditCardList.get(i));
						}
					}
					
				}
				
			}
			
			if(CollectionUtils.isNotEmpty(personalEBankList)){
				for (int i = 0; i < cashierDeskItemDTOList.size(); i++) {
					if(CashierItemTypeEnum.isPersonalEbank(String.valueOf(cashierDeskItemDTOList.get(i).getItemType()))){
						//把原来的个人网银列表换成合并后的personalEBankList
						cashierDeskItemDTOList.get(i).setCashierItemBankDTOList(personalEBankList);
					}
				}
			}
			
			if(delCreditCardCashierIndex != null){
				cashierDeskItemDTOList.remove(delCreditCardCashierIndex.intValue()); //删除信用卡支付
			}
			cashierDeskDTO.setCashierDeskItemDTOList(cashierDeskItemDTOList);
			
		} catch (Exception e) {
			log.error("快捷支付重置收银台数据异常：", e);
			baseResponse.putErrorResult("系统错误，请联系管理员");
			
		}
		
		return baseResponse;
	}

}
