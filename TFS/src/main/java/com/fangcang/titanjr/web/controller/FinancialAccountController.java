package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.TradeTypeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.service.*;
import com.fangcang.titanjr.web.pojo.WithDrawRequest;
import com.fangcang.titanjr.web.util.CommonConstant;
import com.fangcang.titanjr.web.util.RSADecryptString;
import com.fangcang.util.DateUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("account")
public class FinancialAccountController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(FinancialAccountController.class);

    @Resource
    private TitanFinancialUserService titanFinancialUserService;

    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;

    @Resource
    private TitanFinancialAccountService titanFinancialAccountService;

    @Resource
    TitanFinancialTradeService titanFinancialTradeService;

    @Resource
    TitanFinancialBankCardService titanFinancialBankCardService;

    @Resource
    TitanFinancialBaseInfoService titanFinancialBaseInfoService;

    @RequestMapping(value = "/overview-main", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
            organQueryRequest.setUserId(this.getUserId());
            FinancialOrganResponse organOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
            model.addAttribute("organ", organOrganResponse.getFinancialOrganDTO());
            AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
            accountBalanceRequest.setUserid(this.getUserId());
            AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
            if (balanceResponse.isResult() && CollectionUtils.isNotEmpty(balanceResponse.getAccountBalance())) {
                model.addAttribute("accountBalance", balanceResponse.getAccountBalance().get(0));
            }
        }
        return "account-overview/overview-main";
    }

    @RequestMapping(value = "/order-receive-detail", method = RequestMethod.GET)
    public String queryReceiveOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-receive-detail";
    }

    @RequestMapping(value = "/order-pay-detail", method = RequestMethod.GET)
    public String queryPayOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-pay-detail";
    }

    @RequestMapping(value = "/order-recharge-detail", method = RequestMethod.GET)
    public String queryRechargeOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-recharge-detail";
    }

    @RequestMapping(value = "/order-withdraw-detail", method = RequestMethod.GET)
    public String queryWithdrawOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-withdraw-detail";
    }

    @RequestMapping(value = "/query-org-page", method = RequestMethod.GET)
    public String queryForPage(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            model.addAttribute("organ", this.getTitanOrganDTO());
            AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
            accountBalanceRequest.setUserid(this.getUserId());
            AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
            if (balanceResponse.isResult() && CollectionUtils.isNotEmpty(balanceResponse.getAccountBalance())) {
                model.addAttribute("accountBalance", balanceResponse.getAccountBalance().get(0));
            }
            tradeDetailRequest.setUserid(this.getUserId());
            if (StringUtil.isValidString(tradeDetailRequest.getTradeTypeId())) {
                tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(tradeDetailRequest.getTradeTypeId()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getStartTimeStr())) {
                tradeDetailRequest.setStartTime(DateUtil.stringToDate(tradeDetailRequest.getStartTimeStr()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getEndTimeStr())) {
                tradeDetailRequest.setEndTime(DateUtil.stringToDate(tradeDetailRequest.getEndTimeStr()));
            }
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult()) {
                model.addAttribute("tradePage", tradeDetailResponse.getTransOrders());
            }
        }
        return "account-overview/trans-order-list";
    }


    @RequestMapping(value = "/query-freeze-list", method = RequestMethod.GET)
    public String queryFreezeForPage(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            model.addAttribute("organ", this.getTitanOrganDTO());
            tradeDetailRequest.setUserid(this.getUserId());
            if (StringUtil.isValidString(tradeDetailRequest.getTradeTypeId())) {
                tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(tradeDetailRequest.getTradeTypeId()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getStartTimeStr())) {
                tradeDetailRequest.setStartTime(DateUtil.stringToDate(tradeDetailRequest.getStartTimeStr()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getEndTimeStr())) {
                tradeDetailRequest.setEndTime(DateUtil.stringToDate(tradeDetailRequest.getEndTimeStr()));
            }
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult()) {
                model.addAttribute("tradePage", tradeDetailResponse.getTransOrders());
            }
        }
        return "account-overview/bind-bankcard";
    }

    @RequestMapping(value = "/freeze-detail-page", method = RequestMethod.GET)
    public String toFreezeDetailPage(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {

        return "account-overview/freeze-detail";
    }

    @ResponseBody
    @RequestMapping("validate_person_Enterprise")
    public String validatePersonOrEnterprise(HttpServletRequest request, Model model){
    	Map<String,String> resultMap = new HashMap<String, String>();
    	resultMap.put(CommonConstant.RESULT,CommonConstant.FAIL);
    	//判断是否为对公账户
    	FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
    	organQueryRequest.setUserId(this.getUserId());
    	FinancialOrganResponse response  = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
        if(response.isResult()){
        	FinancialOrganDTO financialOrganDTO = response.getFinancialOrganDTO();
        	if(financialOrganDTO !=null && financialOrganDTO.getUserType()!=null){
        		resultMap.put(CommonConstant.RESULT,CommonConstant.SUCCESS);
        		if(CommonConstant.ACCOUNT_PUBLIC.equals(financialOrganDTO.getUserType().toString())){
        			BankCardRequest bankCardRequest = new BankCardRequest();
        	    	bankCardRequest.setUserId(this.getUserId());
        	    	bankCardRequest.setAccountproperty(CommonConstant.ACCOUNT_PUBLIC);
        	    	bankCardRequest.setAccountpurpose(CommonConstant.ACCOUNT_PURPOSE_WITHDRAW);
        	    	List<BankCardDTO> bankCardDTOList = titanFinancialBankCardService.queryBankCardDTO(bankCardRequest);
        	    	if(bankCardDTOList !=null && bankCardDTOList.size()>0){
        	    		BankCardDTO bankCardDTO = bankCardDTOList.get(0);
        	    		if(bankCardDTO !=null && bankCardDTO.getStatus()!=null){
        	    			if(CommonConstant.BANKCARD_SUCCESS.intValue()==bankCardDTO.getStatus().intValue()){//对公且绑定失败
        	    				resultMap.put(CommonConstant.MSG, CommonConstant.ACCOUNT_PUBLIC_SUCCESS);
        	        		}else if(CommonConstant.BANKCARD_BINDING.intValue()==bankCardDTO.getStatus().intValue()){
        	        			resultMap.put(CommonConstant.MSG, CommonConstant.ACCOUNT_PUBLIC_BINDING);
        	        		}else{
        	        			resultMap.put(CommonConstant.MSG, CommonConstant.ACCOUNT_PUBLIC_FAIL);
        	        		}
        	    			return toJson(resultMap);
        	    		}
        	    	}else{
        	    		resultMap.put(CommonConstant.MSG, CommonConstant.ACCOUNT_PUBLIC_NO_BIND);
        	    		return toJson(resultMap);
        	    	}
        			
        		}else{
        			resultMap.put(CommonConstant.MSG, CommonConstant.ACCOUNT_PERSON);
        		}
        		return toJson(resultMap);
        	}
        }
    	resultMap.put(CommonConstant.MSG, "系统错误");
    	return toJson(resultMap);
    }
    
    @RequestMapping(value = "/toBindAccountWithDrawCard")
    public String toBindAccountWithDrawCard(HttpServletRequest request, Model model){
    	model.addAttribute("modifyOrBind",CommonConstant.BIND_BANK_CARD);
    	return "account-overview/bind-bankcard";
    }
    
    @RequestMapping("update_account-withdraw_info")
    public String updateAccountWithdrawInfo(HttpServletRequest request, Model model){
    	model.addAttribute("showBankCardInput",1);
    	model.addAttribute("modifyOrBind",CommonConstant.MODIFY_BANK_CARD);
    	return "account-overview/bind-bankcard";
    }
    
    
    @RequestMapping(value = "/account-withdraw", method = RequestMethod.GET)
    public String toAccountWithDrawPage(HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
        	
            model.addAttribute("organ", this.getTitanOrganDTO());
            AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
            accountBalanceRequest.setUserid(this.getUserId());
            AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
            if (balanceResponse.isResult() && CollectionUtils.isNotEmpty(balanceResponse.getAccountBalance())) {
                model.addAttribute("accountBalance", balanceResponse.getAccountBalance().get(0));
            }

            BankCardBindInfoRequest bankCardBindInfoRequest = new BankCardBindInfoRequest();
            bankCardBindInfoRequest.setUserid(this.getUserId());
            bankCardBindInfoRequest.setAccountPurpose(2);
            bankCardBindInfoRequest.setUsertype(String.valueOf(getTitanOrganDTO().getUserType()));
            bankCardBindInfoRequest.setObjorlist("2");
            bankCardBindInfoRequest.setConstid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
            bankCardBindInfoRequest.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
            QueryBankCardBindInfoResponse cardBindInfoResponse = titanFinancialBankCardService.getBankCardBindInfo(bankCardBindInfoRequest);
            if (cardBindInfoResponse.isResult() && CollectionUtils.isNotEmpty(
                    cardBindInfoResponse.getBankCardInfoDTOList())){
                for (BankCardInfoDTO cardInfoDTO : cardBindInfoResponse.getBankCardInfoDTOList()){
                    if (cardInfoDTO.getStatus().equals(BankCardEnum.BankCardStatusEnum.NORMAL.getKey()) &&//必须要有效
                            cardInfoDTO.getAccountpurpose().equals(BankCardEnum.BankCardPurposeEnum.DEBIT_WITHDRAW_CARD.getKey()) ||
                            cardInfoDTO.getAccountpurpose().equals(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD.getKey())){
                        model.addAttribute("bindBankCard",cardInfoDTO);
                    }
                }
            }
        }
        return "account-overview/account-withdraw";
    }

    @ResponseBody
    @RequestMapping("bankCardBind")
    public String bankCardBindToPublic(BindBankCardRequest  bindBankCardRequest,Model model){
     	if(!StringUtil.isValidString(bindBankCardRequest.getBankCardCode()) 
    			|| !StringUtil.isValidString(bindBankCardRequest.getBankCardName())
    			|| !StringUtil.isValidString(bindBankCardRequest.getUserName())
    			|| !StringUtil.isValidString(bindBankCardRequest.getBankCode())
    			|| !StringUtil.isValidString(bindBankCardRequest.getModifyOrBind())){
    	
    		return toJson(putSysError("参数不能为空"));
    	}
     	
     	if(CommonConstant.BIND_BANK_CARD.equals(bindBankCardRequest.getModifyOrBind())){//绑卡
     		CusBankCardBindResponse cardBindResponse = bindBindCardToPublic(bindBankCardRequest);
     		 if (!cardBindResponse.isResult()){
                 return toJson(putSysError(cardBindResponse.getReturnMessage()));
             }
     	}else if(CommonConstant.MODIFY_BANK_CARD.equals(bindBankCardRequest.getModifyOrBind())){//失败修改绑卡
     		ModifyInvalidWithDrawCardResponse modifyInvalidWithDrawCardResponse = modifyBindCard(bindBankCardRequest);
     	    if(!modifyInvalidWithDrawCardResponse.isResult()){
     	    	  return toJson(putSysError(modifyInvalidWithDrawCardResponse.getReturnMessage()));
     	    }
     	}else{
     		return toJson(putSysError("参数错误"));
     	}
    	
    	return toJson(putSuccess("提现卡申请审核中"));
    }
    
    
    private ModifyInvalidWithDrawCardResponse modifyBindCard(BindBankCardRequest bindBankCardRequest){
    	ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest = new ModifyInvalidWithDrawCardRequest();
    	modifyInvalidWithDrawCardRequest.setAccountnumber(bindBankCardRequest.getBankCardCode());
    	modifyInvalidWithDrawCardRequest.setAccountrealname(bindBankCardRequest.getUserName());
    	modifyInvalidWithDrawCardRequest.setHankheadname(bindBankCardRequest.getBankCardName());
    	modifyInvalidWithDrawCardRequest.setBankhead(bindBankCardRequest.getBankCode());
    	modifyInvalidWithDrawCardRequest.setUserid(this.getUserId());
    	return titanFinancialBankCardService.modifyinvalidPublicCard(modifyInvalidWithDrawCardRequest);
    }
    
    
    private CusBankCardBindResponse bindBindCardToPublic(BindBankCardRequest bindBankCardRequest){
    	 CusBankCardBindRequest  bankCardBindRequest = new CusBankCardBindRequest();
         bankCardBindRequest.setUserId(this.getUserId());
         bankCardBindRequest.setProductId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
         bankCardBindRequest.setConstId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
         bankCardBindRequest.setUserType(String.valueOf(this.getTitanOrganDTO().getUserType()));
         bankCardBindRequest.setAccountTypeId("00");
         bankCardBindRequest.setBankHeadName(bindBankCardRequest.getBankCardName());
         bankCardBindRequest.setCurrency("CNY");
         bankCardBindRequest.setReqSn(String.valueOf(System.currentTimeMillis()));
         bankCardBindRequest.setSubmitTime(DateUtil.dateToString(new Date(),"yyyyMMddHHmmss"));
         bankCardBindRequest.setAccountProperty(CommonConstant.ACCOUNT_PUBLIC);
         bankCardBindRequest.setAccountPurpose(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD.getKey());
         bankCardBindRequest.setCertificateType(String.valueOf(0));
         //查询企业营业执照号
         bankCardBindRequest.setCertificateNumber(this.getTitanOrganDTO().getCertificateNumber());
         bankCardBindRequest.setAccountNumber(bindBankCardRequest.getBankCardCode());
         bankCardBindRequest.setAccountName(bindBankCardRequest.getUserName());
         bankCardBindRequest.setBankCode(bindBankCardRequest.getBankCode());
         
         //以下是哪个说必填但是可选
         bankCardBindRequest.setBankBranch("");
         bankCardBindRequest.setBankCity("");
         bankCardBindRequest.setBankProvince("");
         
         return titanFinancialBankCardService.bankCardBind(bankCardBindRequest);
        
    }
    
    @ResponseBody
    @RequestMapping("getBankInfoList")
    public String getBankInfo(){
        BankInfoQueryRequest bankInfoQueryRequest = new BankInfoQueryRequest();
        bankInfoQueryRequest.setBankType(1);
        BankInfoResponse bankInfoResponse =  titanFinancialBaseInfoService.queryBankInfoList(bankInfoQueryRequest);
        if (bankInfoResponse.isResult() && CollectionUtils.isNotEmpty(bankInfoResponse.getBankInfoDTOList())){
            return toJson(bankInfoResponse);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("getOrgList")
    public String getOrgInfoList(){
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        OrganBriefResponse organBriefResponse =  titanFinancialOrganService.queryOrganBriefList(organQueryRequest);
        if (organBriefResponse.isResult() && CollectionUtils.isNotEmpty(organBriefResponse.getOrganDTOList())){
            return toJson(organBriefResponse);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/toAccountWithDraw")
    public String accountWithDraw(WithDrawRequest withDrawRequest){
        if (null == this.getUserId()) {
            return toJson(putSysError("会话中用户信息不正确"));
        }
        boolean needBindCard = false;
        boolean needBindNewCard = false;
        String cardNo = null;
        //1.检查参数
        if (withDrawRequest.getHasBindBanCard() == 0){
            if (!StringUtil.isValidString(withDrawRequest.getBankCode()) ||
                    !StringUtil.isValidString(withDrawRequest.getAccountNum()) ||
                    !StringUtil.isValidString(withDrawRequest.getAccountName())){
                return toJson(putSysError("请求参数不合法"));
            } else {
                needBindCard = true;
                cardNo = withDrawRequest.getAccountNum();
            }
        } else {
            if (withDrawRequest.getUseNewBankCard() == 1){
                if (!StringUtil.isValidString(withDrawRequest.getBankCode()) ||
                        !StringUtil.isValidString(withDrawRequest.getAccountNum()) ||
                        !StringUtil.isValidString(withDrawRequest.getAccountName())){
                    return toJson(putSysError("请求参数不合法"));
                } else {
                    needBindNewCard = true;
                    needBindCard = true;
                    cardNo = withDrawRequest.getAccountNum();
                }
            } else {
                cardNo = withDrawRequest.getOriginalAccount();
            }
        }

        Object tfsUserId = session.getAttribute(CommonConstant.SESSION_KEY_JR_TFS_USERID);
        boolean isValid = false;
        if (tfsUserId != null) {
            isValid = titanFinancialUserService.checkPayPassword(withDrawRequest.getPassword(),
                    tfsUserId.toString());
        }
        if (!isValid){
            return toJson(putSysError("支付密码不正确请重新输入"));
        }

        FinancialOrganDTO financialOrganDTO = this.getTitanOrganDTO();
        if (needBindNewCard) { //需判定或删除原卡配置
            DeleteBindBankRequest deleteBindBankRequest = new DeleteBindBankRequest();
            deleteBindBankRequest.setUserid(this.getUserId());
            deleteBindBankRequest.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
            deleteBindBankRequest.setConstid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
            deleteBindBankRequest.setUsertype(String.valueOf(financialOrganDTO.getUserType()));
            deleteBindBankRequest.setAccountnumber(withDrawRequest.getOriginalAccount());
            DeleteBindBankResponse deleteBindBankResponse = titanFinancialBankCardService.deleteBindBank(deleteBindBankRequest);
            if (!deleteBindBankResponse.isResult()){
                return toJson(putSysError("使用新卡提现删除原绑定卡失败"));
            }
        }

        if (needBindCard){//需要绑定卡
            CusBankCardBindRequest  bankCardBindRequest = new CusBankCardBindRequest();
            bankCardBindRequest.setUserId(this.getUserId());
            bankCardBindRequest.setProductId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
            bankCardBindRequest.setConstId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
            bankCardBindRequest.setUserType(String.valueOf(this.getTitanOrganDTO().getOrgType()));
            bankCardBindRequest.setAccountNumber(withDrawRequest.getAccountNum());
            bankCardBindRequest.setAccountName(withDrawRequest.getAccountName());
            bankCardBindRequest.setAccountTypeId("00");
            bankCardBindRequest.setBankHeadName(withDrawRequest.getBankName());
            bankCardBindRequest.setCurrency("CNY");
            bankCardBindRequest.setReqSn(String.valueOf(System.currentTimeMillis()));
            bankCardBindRequest.setSubmitTime(DateUtil.dateToString(new Date(),"yyyyMMddHHmmss"));
            bankCardBindRequest.setAccountProperty(String.valueOf(1));
            bankCardBindRequest.setAccountPurpose(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD.getKey());
            if (financialOrganDTO.getUserType() == 1) {
                bankCardBindRequest.setCertificateType(String.valueOf(TitanOrgEnum.CertificateType.SFZ.getKey()));
//                bankCardBindRequest.setCertificateNumber(financialOrganDTO.getBuslince());
                bankCardBindRequest.setCertificateNumber("411381196802185622");
            } else {
                bankCardBindRequest.setCertificateType(String.valueOf(financialOrganDTO.getCertificateType()));
                bankCardBindRequest.setCertificateNumber(String.valueOf(financialOrganDTO.getCertificateNumber()));
            }
            bankCardBindRequest.setBankCode(withDrawRequest.getBankCode());
            bankCardBindRequest.setUserType("1");
            //以下是哪个说必填但是可选
            bankCardBindRequest.setBankBranch("");
            bankCardBindRequest.setBankCity("");
            bankCardBindRequest.setBankProvince("");
            CusBankCardBindResponse cardBindResponse = titanFinancialBankCardService.bankCardBind(bankCardBindRequest);
            if (!cardBindResponse.isResult()){
                return toJson(putSysError("使用新卡提现,绑定新体现卡失败"));
            }
        }

        BalanceWithDrawRequest balanceWithDrawRequest = new BalanceWithDrawRequest();
        balanceWithDrawRequest.setUserId(this.getUserId());
        balanceWithDrawRequest.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
        balanceWithDrawRequest.setAmount(withDrawRequest.getAmount());
        balanceWithDrawRequest.setCardNo(cardNo);
        balanceWithDrawRequest.setCreator(session.getAttribute(CommonConstant.SESSION_KEY_LOGIN_USER_LOGINNAME).toString());
        balanceWithDrawRequest.setOrderDate(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        balanceWithDrawRequest.setUserorderid(OrderGenerateService.genResquestNo());
        balanceWithDrawRequest.setUserFee(0L);
        balanceWithDrawRequest.setBankName(withDrawRequest.getBankName());
        BalanceWithDrawResponse balanceWithDrawResponse = titanFinancialAccountService.accountBalanceWithdraw(balanceWithDrawRequest);
        if (!balanceWithDrawResponse.isResult()){
            return toJson(putSysError("提现操作失败，请联系管理员"));
        }
        return toJson(putSuccess());
    }

    @RequestMapping(value = "/order-remark", method = RequestMethod.GET)
    public String toOrderRemark(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            tradeDetailRequest.setUserid(this.getUserId());
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult() && CollectionUtils.isNotEmpty(tradeDetailResponse.getTransOrders().getItemList())) {
                model.addAttribute("transOrder", tradeDetailResponse.getTransOrders().getItemList().get(0));
            }
        }
        return "account-overview/order-remark";
    }

    @ResponseBody
    @RequestMapping("setPayPassword")
    public Map<String, String> setPayPassword(HttpServletRequest request, PayPasswordRequest payPasswordRequest) {
        Map<String, String> map = new HashMap<String, String>();
        if (payPasswordRequest != null && StringUtil.isValidString(payPasswordRequest.getPayPassword())) {
        	payPasswordRequest.setPayPassword(RSADecryptString.decryptString(payPasswordRequest.getPayPassword(),request));
        	payPasswordRequest.setTfsuserid(getTfsUserId());
            PayPasswordResponse payPasswordResponse = titanFinancialUserService.saveOrUpdatePayPassword(payPasswordRequest);
            if (payPasswordResponse.isSaveSuccess()) {
                map.put(CommonConstant.RESULT, CommonConstant.SUCCESS);
                return map;
            } else {
                map.put(CommonConstant.MSG, payPasswordResponse.getReturnMessage());
            }
        }
        map.put("result", "fail");
        return map;
    }

    @ResponseBody
    @RequestMapping("checkIsSetPayPassword")
    public Map<String, String> checkIsSetPayPassword(String fcUserid,HttpServletRequest request) {
    	boolean flag = false;
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "false");
        flag = titanFinancialUserService.checkIsSetPayPassword(fcUserid,getTfsUserId());
        if (flag) {
            map.put(CommonConstant.RESULT, CommonConstant.SUCCESS);
        }
        return map;
    }

    @RequestMapping(value = "/updateOrderRemark")
    @ResponseBody
    public String updateOrderRemark(TransOrderUpdateRequest transOrderUpdateRequest, HttpServletRequest request) {
        if (this.getUserId() != null) {
            transOrderUpdateRequest.setUserId(this.getUserId());
            TransOrderUpdateResponse updateResponse = titanFinancialTradeService.updateTransOrder(transOrderUpdateRequest);
            if (updateResponse.isResult()) {
                return toJson(putSuccess());
            } else {
                return toJson(putSysError(updateResponse.getReturnMessage()));
            }
        } else {
            return toJson(putSysError("当前会话机构标示为空"));
        }
    }

    @RequestMapping("exportExcel")
    public void exportExcel(TradeDetailRequest tradeDetailRequest,HttpServletRequest request, HttpServletResponse response){
        TradeDetailResponse tradeDetailResponse = null;
        if (null != this.getUserId()) {
            tradeDetailRequest.setUserid(this.getUserId());
            if (StringUtil.isValidString(tradeDetailRequest.getTradeTypeId())) {
                tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(tradeDetailRequest.getTradeTypeId()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getStartTimeStr())) {
                tradeDetailRequest.setStartTime(DateUtil.stringToDate(tradeDetailRequest.getStartTimeStr()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getEndTimeStr())) {
                tradeDetailRequest.setEndTime(DateUtil.stringToDate(tradeDetailRequest.getEndTimeStr()));
            }
            tradeDetailRequest.setPageSize(100000);
            tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
        }

        if (tradeDetailResponse!= null && tradeDetailResponse.isResult()) {
            HttpSession session = request.getSession();
            session.setAttribute("state", null);
            // 生成提示信息，
            response.setContentType("application/vnd.ms-excel");
            String codedFileName = "交易记录";
            OutputStream outputStream = null;
            try {
                codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF-8");//进行转码，使其支持中文文件名
                response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
                // 产生工作簿对象
                HSSFWorkbook workbook = new HSSFWorkbook();
                //产生工作表对象
                HSSFSheet sheet = workbook.createSheet();
                HSSFRow head = sheet.createRow(0);
                head.createCell(0).setCellValue("编号");
                head.createCell(1).setCellValue("业务单号");
                head.createCell(2).setCellValue("金融交易号");
                head.createCell(3).setCellValue("财务单号");
                head.createCell(4).setCellValue("交易时间");
                head.createCell(5).setCellValue("交易类型");
                head.createCell(6).setCellValue("交易内容");
                head.createCell(7).setCellValue("交易对方");
                head.createCell(8).setCellValue("订单金额");
                head.createCell(9).setCellValue("手续费");
                head.createCell(10).setCellValue("交易结果");
                List<TransOrderDTO> orderDTOList = tradeDetailResponse.getTransOrders().getItemList();                for (int i = 0; i < orderDTOList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);//创建一行
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(orderDTOList.get(i).getBusinessordercode());
                    row.createCell(2).setCellValue(orderDTOList.get(i).getOrderid());
                    row.createCell(3).setCellValue(orderDTOList.get(i).getPayorderno());
                    row.createCell(4).setCellValue(DateUtil.dateToString(orderDTOList.get(i).getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                    row.createCell(5).setCellValue(orderDTOList.get(i).getTradeType());
                    row.createCell(6).setCellValue(orderDTOList.get(i).getGoodsname() + orderDTOList.get(i).getGoodsdetail());
                    if (orderDTOList.get(i).getTransTarget() != null) {
                        row.createCell(7).setCellValue(orderDTOList.get(i).getTransTarget());
                    }
                    if (orderDTOList.get(i).getTradeamount() != null) {
                        row.createCell(8).setCellValue(orderDTOList.get(i).getTradeamount()/100.0);
                    }
                    if (orderDTOList.get(i).getReceivedfee() != null) {
                        row.createCell(9).setCellValue(orderDTOList.get(i).getReceivedfee()/100.0);
                    }
                    String stateStr = "";
                    if (orderDTOList.get(i).getStatusid().equals("1")){
                        stateStr = "处理中";
                    } else if (orderDTOList.get(i).getStatusid().equals("1")){
                        stateStr = "已成功";
                    } else if (orderDTOList.get(i).getStatusid().equals("3")){
                        stateStr = "已冻结";
                    } else {
                        stateStr = "交易失败";
                    }
                    row.createCell(10).setCellValue(stateStr);
                }
                outputStream = response.getOutputStream();
                workbook.write(outputStream);
                log.info("生成excel文件成功");
            } catch (UnsupportedEncodingException e1) {
                log.error("编码错误", e1);
            } catch (Exception e) {
                log.error("发生未知异常", e);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        log.error("关闭异常流失败", e);
                    }
                }
                session.setAttribute("state", "open");
            }
        }
    }

    @RequestMapping("showSetPayPassword")
    public String showSetPayPassword() {
        return "checkstand-pay/setPayPassword";
    }

    @RequestMapping("showPayPassword")
    public String showPayPassword() {
        return "checkstand-pay/putPayPassword";
    }


    private boolean setTransOrderDetail(TradeDetailRequest tradeDetailRequset, Model model) {
        if (null != this.getUserId()) {
            tradeDetailRequset.setUserid(this.getUserId());
            try {
                TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getOrderTradeDetail(tradeDetailRequset);
                if (CollectionUtils.isNotEmpty(tradeDetailResponse.getTransOrders().getItemList())) {
                    TransOrderDTO transOrderDTO = tradeDetailResponse.getTransOrders().getItemList().get(0);
                    model.addAttribute("transOrder", transOrderDTO);
                    return true;
                } else {
                    log.error("查询交易详情，结果为空");
                    return false;
                }
            } catch (Exception e) {
                log.error("查询并设置交易单详情失败", e);
                return false;
            }
        } else {
            log.error("当前会话userid为空");
            return false;
        }
    }

    private FinancialOrganDTO getTitanOrganDTO(){
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setUserId(this.getUserId());
        FinancialOrganResponse organOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
        if (organOrganResponse.isResult()){
            return organOrganResponse.getFinancialOrganDTO();
        }
        return  null;
    }
    
	@RequestMapping("/selectAccHistory")
	public String showAccountHistory(AccountHistoryDTO accountHistoryDTO,Model model){
		if(accountHistoryDTO !=null ){
			AccountHistoryRequest accountHistoryRequest = new AccountHistoryRequest();
			accountHistoryRequest.setAccountHistoryDTO(accountHistoryDTO);
			AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService.queryAccountHistory(accountHistoryRequest);
			if(accountHistoryResponse.isResult()){
				model.addAttribute("accountHistoryResponse", accountHistoryResponse);
			}
		}
		return "checkstand-pay/selectAccHistory";
	}
	
}
