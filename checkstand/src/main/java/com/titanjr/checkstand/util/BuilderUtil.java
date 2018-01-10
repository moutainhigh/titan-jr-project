/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName ParamBuilderUtil.java
 * @author Jerry
 * @date 2017年11月25日 上午11:03:12  
 */
package com.titanjr.checkstand.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.RBBindCardDTO;
import com.titanjr.checkstand.dto.TitanBindCardDTO;
import com.titanjr.checkstand.request.TitanPayCallbackRequest;
import com.titanjr.checkstand.respnse.RBBindCardQueryResponse;
import com.titanjr.checkstand.respnse.RBUnBindCardResponse;
import com.titanjr.checkstand.respnse.TitanBindCardQueryResponse;
import com.titanjr.checkstand.respnse.TitanUnBindCardResponse;

/**
 * 对象构建工具类
 * @author Jerry
 * @date 2017年11月25日 上午11:03:12  
 */
public class BuilderUtil {
	
	/**
	 * 参数错误时，获取前台回调的请求参数
	 * @author Jerry
	 */
	public static TitanPayCallbackRequest getPayFailedCallbackRequest(){
		
		TitanPayCallbackRequest payCallbackRequest = new TitanPayCallbackRequest();
		payCallbackRequest.setOrderNo("2017081714095100001");
		payCallbackRequest.setPayStatus("4");
		payCallbackRequest.setPayAmount("100");
		payCallbackRequest.setPayMsg("支付失败");
		payCallbackRequest.setOrderPayTime("yyyyMMddHHmmss");
		payCallbackRequest.setTitanjrGateWayUrl("http://192.168.0.77:8084/titanjr-pay-app/payment/payConfirmPage.action");
		
		return payCallbackRequest;
		
	}
	
	
	public static TitanBindCardQueryResponse convertBindCardQueryRes(RBBindCardQueryResponse rbBindCardQueryResponse){
		
		TitanBindCardQueryResponse titanBindCardQueryResponse = new TitanBindCardQueryResponse();
		List<TitanBindCardDTO> agentProtocolList = new ArrayList<TitanBindCardDTO>();
		titanBindCardQueryResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		titanBindCardQueryResponse.setUserId(rbBindCardQueryResponse.getMember_id());
		titanBindCardQueryResponse.setCount(CollectionUtils.isEmpty(rbBindCardQueryResponse.getBind_card_list()) 
				? "0" : String.valueOf(rbBindCardQueryResponse.getBind_card_list().size()));
		if(CollectionUtils.isNotEmpty(rbBindCardQueryResponse.getBind_card_list())){
			for (RBBindCardDTO rbBindCardDTO : rbBindCardQueryResponse.getBind_card_list()) {
				TitanBindCardDTO titanBindCardDTO = new TitanBindCardDTO();
				titanBindCardDTO.setBind_id(rbBindCardDTO.getBind_id());
				titanBindCardDTO.setAccountNo(rbBindCardDTO.getCard_top()+"****"+rbBindCardDTO.getCard_last());
				if(StringUtil.isValidString(rbBindCardDTO.getBank_card_type())){
					titanBindCardDTO.setAccountType("10");
					if(rbBindCardDTO.getBank_card_type().equals("1")){
						titanBindCardDTO.setAccountType("11");
					}
				}
				titanBindCardDTO.setBankNo(rbBindCardDTO.getBank_code());
				titanBindCardDTO.setMobile(rbBindCardDTO.getPhone());
				agentProtocolList.add(titanBindCardDTO);
			}
		}
		titanBindCardQueryResponse.setAgentProtocolList(agentProtocolList);
		titanBindCardQueryResponse.setErrCode(SysConstant.RS_SUCCESS_CODE);
		titanBindCardQueryResponse.setErrMsg(SysConstant.RS_SUCCESS_MSG);
		return titanBindCardQueryResponse;
		
	}
	
	public static TitanUnBindCardResponse convertUnBindCardRes(RBUnBindCardResponse rbUnBindCardResponse){
		
		TitanUnBindCardResponse titanUnBindCardResponse = new TitanUnBindCardResponse();
		titanUnBindCardResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		titanUnBindCardResponse.setUserId(rbUnBindCardResponse.getMember_id());
		titanUnBindCardResponse.setErrCode(SysConstant.RS_SUCCESS_CODE);
		titanUnBindCardResponse.setErrMsg(SysConstant.RS_SUCCESS_MSG);
		return titanUnBindCardResponse;
		
	}
 
}
