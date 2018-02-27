/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName SignValidater.java
 * @author Jerry
 * @date 2018年2月27日 上午9:58:30  
 */
package com.titanjr.checkstand.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.util.MD5;
import com.titanjr.checkstand.dto.TitanBindCardQueryDTO;
import com.titanjr.checkstand.dto.TitanCardBINQueryDTO;
import com.titanjr.checkstand.dto.TitanPayConfirmDTO;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.dto.TitanPayQueryDTO;
import com.titanjr.checkstand.dto.TitanReSendMsgDTO;
import com.titanjr.checkstand.dto.TitanRefundDTO;
import com.titanjr.checkstand.dto.TitanRefundQueryDTO;
import com.titanjr.checkstand.dto.TitanUnBindCardDTO;

/**
 * 收银台请求验签
 * @author Jerry
 * @date 2018年2月27日 上午9:58:30  
 */
@Component
public class TitanSignValidater {
	
	private static final Logger logger = LoggerFactory.getLogger(TitanSignValidater.class);
	
	/**
	 * 支付验签
	 * @author Jerry
	 * @date 2018年2月27日 上午10:30:51
	 * @param payDTO
	 * @return
	 */
	public static boolean validatePaySign(TitanPayDTO payDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(payDTO != null){
			sign.append("merchantNo=");
			sign.append(payDTO.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(payDTO.getOrderNo());
			sign.append("&orderAmount=");
			sign.append(payDTO.getOrderAmount());
			sign.append("&payType=");
			sign.append(payDTO.getPayType());
			sign.append("&orderTime=");
			sign.append(payDTO.getOrderTime());
			sign.append("&signType=");
			sign.append(payDTO.getSignType());
			sign.append("&version=");
			sign.append(payDTO.getVersion());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(payDTO.getSignMsg())){
			logger.error("收银台支付请求验签失败");
			return false;
		}
		logger.info("收银台支付请求验签成功");
		return true;
		
	}
	

	/**
	 * 支付查询请求验签
	 * @author Jerry
	 * @date 2018年2月27日 上午11:40:54
	 * @param payDTO
	 * @param key
	 * @return
	 */
	public static boolean validatePayQuerySign(TitanPayQueryDTO payQueryDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(payQueryDTO != null){
			sign.append("signType=");
			sign.append(payQueryDTO.getSignType());
			sign.append("&version=");
			sign.append(payQueryDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(payQueryDTO.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(payQueryDTO.getOrderNo());
			sign.append("&orderTime=");
			sign.append(payQueryDTO.getOrderTime());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(payQueryDTO.getSignMsg())){
			logger.error("收银台支付查询验签失败");
			return false;
		}
		logger.info("收银台支付查询验签成功");
		return true;
		
	}
	
	
	/**
	 * 退款验签
	 * @author Jerry
	 * @date 2018年2月27日 下午2:45:01
	 * @param refundDTO
	 * @param key
	 * @return
	 */
	public static boolean validateRefundSign(TitanRefundDTO refundDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(refundDTO != null){
			sign.append("signType=");
			sign.append(refundDTO.getSignType());
			sign.append("&version=");
			sign.append(refundDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(refundDTO.getMerchantNo());
			sign.append("&refundOrderno=");
			sign.append(refundDTO.getRefundOrderno());
			sign.append("&orderNo=");
			sign.append(refundDTO.getOrderNo());
			sign.append("&orderTime=");
			sign.append(refundDTO.getOrderTime());
			sign.append("&refundAmount=");
			sign.append(refundDTO.getRefundAmount());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(refundDTO.getSignMsg())){
			logger.error("收银台退款请求验签失败");
			return false;
		}
		logger.info("收银台退款请求验签成功");
		return true;
		
	}
	
	
	/**
	 * 退款查询验签
	 * @author Jerry
	 * @date 2018年2月27日 下午2:46:36
	 * @param refundQueryDTO
	 * @param key
	 * @return
	 */
	public static boolean validateRefundQuerySign(TitanRefundQueryDTO refundQueryDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(refundQueryDTO != null){
			sign.append("signType=");
			sign.append(refundQueryDTO.getSignType());
			sign.append("&version=");
			sign.append(refundQueryDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(refundQueryDTO.getMerchantNo());
			sign.append("&refundOrderno=");
			sign.append(refundQueryDTO.getRefundOrderno());
			sign.append("&orderNo=");
			sign.append(refundQueryDTO.getOrderNo());
			sign.append("&orderTime=");
			sign.append(refundQueryDTO.getOrderTime());
			sign.append("&refundAmount=");
			sign.append(refundQueryDTO.getRefundAmount());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(refundQueryDTO.getSignMsg())){
			logger.error("收银台退款查询验签失败");
			return false;
		}
		logger.info("收银台退款查询验签成功");
		return true;
		
	}
	
	
	/**
	 * 卡BIN查询验签
	 * @author Jerry
	 * @date 2018年2月27日 下午2:58:07
	 * @param titanCardBINQueryDTO
	 * @param key
	 * @return
	 */
	public static boolean validateCardBINQuerySign(TitanCardBINQueryDTO titanCardBINQueryDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(titanCardBINQueryDTO != null){
			sign.append("signType=");
			sign.append(titanCardBINQueryDTO.getSignType());
			sign.append("&version=");
			sign.append(titanCardBINQueryDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(titanCardBINQueryDTO.getMerchantNo());
			sign.append("&cardNo=");
			sign.append(titanCardBINQueryDTO.getCardNo());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(titanCardBINQueryDTO.getSignMsg())){
			logger.error("卡BIN查询验签失败");
			return false;
		}
		logger.info("卡BIN查询验签成功");
		return true;
		
	}
	
	
	/**
	 * 快捷-确认支付验签
	 * @author Jerry
	 * @date 2018年2月27日 下午3:02:30
	 * @param titanPayConfirmDTO
	 * @param key
	 * @return
	 */
	public static boolean validatePayConfirmSign(TitanPayConfirmDTO titanPayConfirmDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(titanPayConfirmDTO != null){
			sign.append("signType=");
			sign.append(titanPayConfirmDTO.getSignType());
			sign.append("&version=");
			sign.append(titanPayConfirmDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(titanPayConfirmDTO.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(titanPayConfirmDTO.getOrderNo());
			sign.append("&checkCode=");
			sign.append(titanPayConfirmDTO.getCheckCode());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(titanPayConfirmDTO.getSignMsg())){
			logger.error("确认支付验签失败");
			return false;
		}
		logger.info("确认支付验签成功");
		return true;
		
	}
	
	
	/**
	 * 重发验证码验签
	 * @author Jerry
	 * @date 2018年2月27日 下午3:06:36
	 * @param titanReSendMsgDTO
	 * @param key
	 * @return
	 */
	public static boolean validateReSendMsgSign(TitanReSendMsgDTO titanReSendMsgDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(titanReSendMsgDTO != null){
			sign.append("signType=");
			sign.append(titanReSendMsgDTO.getSignType());
			sign.append("&version=");
			sign.append(titanReSendMsgDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(titanReSendMsgDTO.getMerchantNo());
			sign.append("&orderNo=");
			sign.append(titanReSendMsgDTO.getOrderNo());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(titanReSendMsgDTO.getSignMsg())){
			logger.error("重发验证码验签失败");
			return false;
		}
		logger.info("重发验证码验签成功");
		return true;
		
	}
	
	
	/**
	 * 绑卡列表查询验签
	 * @author Jerry
	 * @date 2018年2月27日 下午3:14:32
	 * @param titanBindCardQueryDTO
	 * @param key
	 * @return
	 */
	public static boolean validateBindCardListSign(TitanBindCardQueryDTO titanBindCardQueryDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(titanBindCardQueryDTO != null){
			sign.append("signType=");
			sign.append(titanBindCardQueryDTO.getSignType());
			sign.append("&version=");
			sign.append(titanBindCardQueryDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(titanBindCardQueryDTO.getMerchantNo());
			sign.append("&userId=");
			sign.append(titanBindCardQueryDTO.getIdCode());//身份证当用户ID
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(titanBindCardQueryDTO.getSignMsg())){
			logger.error("绑卡列表查询验签失败");
			return false;
		}
		logger.info("绑卡列表查询验签成功");
		return true;
		
	}
	
	
	/**
	 * 解绑卡验签
	 * @author Jerry
	 * @date 2018年2月27日 下午3:19:59
	 * @param titanUnBindCardDTO
	 * @param key
	 * @return
	 */
	public static boolean validateUnBindCardSign(TitanUnBindCardDTO titanUnBindCardDTO, String key) {
		
		StringBuffer sign = new StringBuffer();
		if(titanUnBindCardDTO != null){
			sign.append("signType=");
			sign.append(titanUnBindCardDTO.getSignType());
			sign.append("&version=");
			sign.append(titanUnBindCardDTO.getVersion());
			sign.append("&merchantNo=");
			sign.append(titanUnBindCardDTO.getMerchantNo());
			sign.append("&userId=");
			sign.append(titanUnBindCardDTO.getIdCode());//身份证当用户ID
			sign.append("&idCode=");
			sign.append(titanUnBindCardDTO.getIdCode());
			sign.append("&key=");
			sign.append(key);
		}
		if(!MD5.MD5Encode(sign.toString(), "UTF-8").equals(titanUnBindCardDTO.getSignMsg())){
			logger.error("解绑卡验签失败");
			return false;
		}
		logger.info("解绑卡验签成功");
		return true;
		
	}

}
