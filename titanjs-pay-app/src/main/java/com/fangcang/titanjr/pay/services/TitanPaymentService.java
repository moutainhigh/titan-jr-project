package com.fangcang.titanjr.pay.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.AmtTypeEnum;
import com.fangcang.titanjr.dto.bean.BusiCodeEnum;
import com.fangcang.titanjr.dto.bean.CharsetEnum;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.OrderMarkEnum;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.SignTypeEnum;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.AccountHistoryRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.JudgeAllowNoPwdPayRequest;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.dto.request.RechargeRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AllowNoPwdPayResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Component
public class TitanPaymentService {
	private static final Log log = LogFactory
			.getLog(TitanTradeService.class);
	
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
	
	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	 public AccountCheckResponse accountIsExist(String orgName,String titanCode ){
	    	if(StringUtil.isValidString(orgName)&&
	    			StringUtil.isValidString(titanCode)){
	    		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
	    		accountCheckRequest.setOrgName(orgName);
	    		accountCheckRequest.setTitanCode(titanCode);
	    		accountCheckRequest.setStatusId(1);//just query active org
	    		AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
	    		if(accountCheckResponse.isCheckResult()){
	    		   return accountCheckResponse;
	    		}
	    	}
			return null;
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
		 if(!StringUtil.isValidString(pwd) || !StringUtil.isValidString(fcUserId) ){
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
 
	 public boolean validateIsConfirmed(Integer transId){
		TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
		titanTransferDTO.setTransorderid(transId);
		List<TitanTransferDTO> titanTransferDTOList = titanOrderService.getTitanTransferDTOList(titanTransferDTO);
		if(titanTransferDTOList !=null && titanTransferDTOList.size()>0){
			for(TitanTransferDTO transferDTO : titanTransferDTOList){
				if(transferDTO.getStatus() !=null){
					if(TransferReqEnum.TRANSFER_SUCCESS.getStatus()==transferDTO.getStatus().intValue()){
						return false;
					}
				}
				
			}
		}
		return true;
	}
	 
	 public TransferRequest convertToTransferRequest(TransOrderDTO transOrderDTO){
			TransferRequest transferRequest = new TransferRequest();
			transferRequest.setCreator(transOrderDTO.getCreator());
	    	transferRequest.setUserid(transOrderDTO.getUserid());										//转出的用户
	    	transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
	    	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));	//请求时间
	    	if(transOrderDTO.getTradeamount()!=null ){
	    		transferRequest.setAmount(transOrderDTO.getTradeamount().toString());
	    	}
	    	transferRequest.setUserfee("0");			
	    	transferRequest.setOrderid(transOrderDTO.getOrderid());
	    	transferRequest.setUserrelateid(transOrderDTO.getUserrelateid());	
	    	transferRequest.setProductId(transOrderDTO.getProductid());
	    	return transferRequest;
		}
		
	//冻结
		public boolean freezeAccountBalance(TransferRequest transferRequest,String orderNo) {
			try{
				RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
		    	rechargeResultConfirmRequest.setOrderNo(orderNo);
		    	rechargeResultConfirmRequest.setPayAmount(transferRequest.getAmount());
		    	rechargeResultConfirmRequest.setUserid(transferRequest.getUserrelateid());
		    	rechargeResultConfirmRequest.setOrderAmount(transferRequest.getAmount());
		    	FreezeAccountBalanceResponse freezeAccountBalanceResponse = titanFinancialAccountService.freezeAccountBalance(rechargeResultConfirmRequest);
				if(freezeAccountBalanceResponse.isFreezeSuccess()){
					return true;
				}
			}catch(Exception e){
				log.error("冻结余额失败"+e.getMessage(),e);
			}
	    	return false;
		}
	 
		
		//更新订单状态
		public boolean updateOrderStatus(Integer transId,OrderStatusEnum orderStatusEnum){
			try{
				TransOrderDTO transOrderDTO = new TransOrderDTO();
				transOrderDTO.setStatusid(orderStatusEnum.getStatus());
				transOrderDTO.setTransid(transId);
				boolean flag =  titanOrderService.updateTransOrder(transOrderDTO);
				return flag;
			}catch(Exception e){
				log.error("更新订单失败"+e.getMessage(),e);
			}
			return false;
		}
		
		@RequestMapping("payConfirmPage")
		public String payConfirmPage(RechargeResultConfirmRequest rechargeResultConfirmRequest,Model model){
			if(StringUtil.isValidString(rechargeResultConfirmRequest.getOrderNo())){
				TransOrderRequest transOrderRequest = new TransOrderRequest();
				transOrderRequest.setOrderid(rechargeResultConfirmRequest.getOrderNo());
				TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
				if(transOrderDTO !=null){
					model.addAttribute("transOrderDTO", transOrderDTO);
					FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
					organQueryRequest.setOrgCode(transOrderDTO.getPayeemerchant());
					FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
			        if(financialOrganResponse.isResult()){
			        	model.addAttribute("financialOrganDTO", financialOrganResponse.getFinancialOrganDTO());
			        }
			        
			        model.addAttribute("payType", "网银支付");
			        if(!StringUtil.isValidString(rechargeResultConfirmRequest.getPayStatus())){//判断是本地回调
			        	
			        	boolean paySuccess = OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid());
			        	if(transOrderDTO.getTradeamount() !=null){
		        			rechargeResultConfirmRequest.setPayAmount(transOrderDTO.getTradeamount().toString());
		        		}
			        	rechargeResultConfirmRequest.setOrderPayTime(DateUtil.sdf5.format(transOrderDTO.getCreatetime()));
			        	rechargeResultConfirmRequest.setPayMsg("付款失败");
			        	if(paySuccess){
			        		rechargeResultConfirmRequest.setPayStatus("3");
			        		rechargeResultConfirmRequest.setPayMsg("付款成功");
			        	}
			        	model.addAttribute("payType", "余额支付");
					}
			        
				}
			}
		    model.addAttribute("rechargeResultConfirmRequest", rechargeResultConfirmRequest);
			return "checkstand-pay/payResult";
		}
		
		
		public void addAccountHistory(TransOrderDTO transOrderDTO){
			//记录收款历史
			AccountHistoryRequest accountHistoryRequest = null;
			AccountHistoryDTO accountHistoryDTO = convertToAccountHistoryDTO(transOrderDTO);
			if(accountHistoryDTO !=null){
				accountHistoryRequest = new AccountHistoryRequest();
				accountHistoryRequest.setAccountHistoryDTO(accountHistoryDTO);
				 titanFinancialAccountService.addAccountHistory2(accountHistoryRequest);
			}
		}
		
		private AccountHistoryDTO convertToAccountHistoryDTO(TransOrderDTO transOrderDTO){
			if(null==transOrderDTO || !StringUtil.isValidString(transOrderDTO.getBusinessinfo())){
				return null;
			}
			AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
			Map<String,String> bussinessInfoMap = JsonConversionTool.toObject(transOrderDTO.getBusinessinfo(), Map.class);
			if(bussinessInfoMap !=null){
				String inAccountCode = bussinessInfoMap.get("inAccountCode");
				String  outAccountCode = bussinessInfoMap.get("outAccountCode");
				accountHistoryDTO.setInaccountcode(inAccountCode);
				accountHistoryDTO.setOutaccountcode(outAccountCode);
			}
			accountHistoryDTO.setPayeeuserid(transOrderDTO.getPayeemerchant());
			accountHistoryDTO.setPayeruserid(transOrderDTO.getPayermerchant());
			accountHistoryDTO.setCreatetime(new Date());
			return accountHistoryDTO;
		}
}



