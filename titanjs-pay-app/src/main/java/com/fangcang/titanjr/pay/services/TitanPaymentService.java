package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dto.bean.AmtTypeEnum;
import com.fangcang.titanjr.dto.bean.BusiCodeEnum;
import com.fangcang.titanjr.dto.bean.CharsetEnum;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.OrderMarkEnum;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.SignTypeEnum;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.bean.VersionEnum;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.JudgeAllowNoPwdPayRequest;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.dto.request.PaymentRequest;
import com.fangcang.titanjr.dto.request.RechargePageRequest;
import com.fangcang.titanjr.dto.request.RechargeRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AllowNoPwdPayResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Component
public class TitanPaymentService {
	private static final Log log = LogFactory
			.getLog(FinancialTradeService.class);
	
	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanCashierDeskService titanCashierDeskService;
	
	 public boolean accountIsExist(String orgName,String titanCode ){
	    	if(StringUtil.isValidString(orgName)&&
	    			StringUtil.isValidString(titanCode)){
	    		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
	    		accountCheckRequest.setOrgName(orgName);
	    		accountCheckRequest.setTitanCode(titanCode);
	    		AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
	    		if(accountCheckResponse.isCheckResult()){
	    			return true;
	    		}
	    	}
			return false;
	    } 
	 
	 public boolean isAllowNoPwdPay(String userid,String totalAmount){
			JudgeAllowNoPwdPayRequest judgeAllowNoPwdPayRequest = new JudgeAllowNoPwdPayRequest();
			judgeAllowNoPwdPayRequest.setMoney(totalAmount);
			judgeAllowNoPwdPayRequest.setUserid(userid);
			AllowNoPwdPayResponse allowNoPwdPayResponse = titanFinancialTradeService.isAllowNoPwdPay(judgeAllowNoPwdPayRequest);
		    if(allowNoPwdPayResponse.isAllowNoPwdPay()){
		    	return true;
		    }
			return false;
		}
	 
	 public boolean checkPwd(String pwd, String fcUserId){
		 if(StringUtil.isValidString(pwd) ||StringUtil.isValidString(fcUserId) ){
			 return false;
		 }
		 
		 TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
		 titanUserBindInfoDTO.setFcuserid(Long.parseLong(fcUserId));
		 titanUserBindInfoDTO = titanFinancialUserService.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
		 
		 if(null == titanUserBindInfoDTO || null ==titanUserBindInfoDTO.getTfsuserid()){
			 return false;
		 }
		 
		 return titanFinancialUserService.checkPayPassword(pwd,titanUserBindInfoDTO.getTfsuserid().toString());
	 }
	   

	 public RechargeResponse packageRechargeData(TitanPaymentRequest titanPaymentRequest){
		 RechargeRequest rechargeRequest = this.convertToRechargePageRequest(titanPaymentRequest);
		 return titanFinancialTradeService.packageRechargeData(rechargeRequest);
	 }
	 
	 private RechargeRequest convertToRechargePageRequest(TitanPaymentRequest titanPaymentRequest) {
		 RechargeRequest rechargeRequest = new  RechargeRequest ();
		    //根据系统生成的单号在本地查询订单信息
            TransOrderRequest transOrderRequest = new TransOrderRequest();
            transOrderRequest.setOrderid(titanPaymentRequest.getOrderid());
            //获取本地落单信息
                //为什么取第一个单，因为若单是成功或者失败，在下单的时候就获取不到单号，只有在处理中的单才能
        	TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
        	
        	if(null ==transOrderDTO ||transOrderDTO.getAmount()==null){
        		return rechargeRequest;
        	}
            rechargeRequest.setTransorderid(transOrderDTO.getTransid());
            rechargeRequest.setOrderAmount(transOrderDTO.getAmount().toString());
            rechargeRequest.setProductName(transOrderDTO.getGoodsname());
            rechargeRequest.setUserid(transOrderDTO.getUserid());
            rechargeRequest.setOrderNo(titanPaymentRequest.getOrderid());
            rechargeRequest.setProductNum("1");
            rechargeRequest.setAmtType(AmtTypeEnum.RMB.getKey());
            rechargeRequest.setPayerAcount(titanPaymentRequest.getPayerAcount());
            rechargeRequest.setBankInfo(titanPaymentRequest.getBankInfo());
            rechargeRequest.setOrderTime(DateUtil.sdf5.format(new Date()));
            rechargeRequest.setOrderExpireTime(45);
            rechargeRequest.setSignType(SignTypeEnum.MD5.getKey());
            rechargeRequest.setBusiCode(BusiCodeEnum.MerchantOrder.getKey());
            rechargeRequest.setOrderMark( OrderMarkEnum.InsideOrder.getKey());
            rechargeRequest.setCharset(CharsetEnum.UTF_8.getKey());
            
            if (titanPaymentRequest.getPayType() != null) {
                rechargeRequest.setPayType(titanPaymentRequest.getPayType().getKey());
            }
            PayMethodConfigRequest payMethodConfigRequest = new PayMethodConfigRequest();
			payMethodConfigRequest.setUserId(titanPaymentRequest.getUserid());
			PayMethodConfigDTO payMethodConfigDTO = titanFinancialTradeService.getPayMethodConfigDTO(payMethodConfigRequest);
			if(payMethodConfigDTO !=null){
				rechargeRequest.setPageUrl(payMethodConfigDTO.getPageurl());
				rechargeRequest.setNotifyUrl(payMethodConfigDTO.getNotifyurl());
			}
        	
        	return rechargeRequest;
		}
	 
	 public void saveCommonPayMethod(TitanPaymentRequest titanPaymentRequest){
			try{
				CommonPayMethodDTO commonPayMethodDTO = new CommonPayMethodDTO();
				if(StringUtil.isValidString(titanPaymentRequest.getDeskId())){
					commonPayMethodDTO.setDeskid(Integer.parseInt(titanPaymentRequest.getDeskId()));
				}
				commonPayMethodDTO.setBankname(titanPaymentRequest.getBankInfo());
				commonPayMethodDTO.setCreator(titanPaymentRequest.getCreator());
				commonPayMethodDTO.setCreatetime(new Date());
				if(StringUtil.isValidString(titanPaymentRequest.getLinePayType())){
					commonPayMethodDTO.setPaytype(Integer.parseInt(titanPaymentRequest.getLinePayType()));
				}
				titanCashierDeskService.saveCommonPayMethod(commonPayMethodDTO);
			}catch(Exception e){
				log.error("保存常用的支付方式失败"+e.getMessage(),e);
				e.printStackTrace();
			}
		}
	 
}
