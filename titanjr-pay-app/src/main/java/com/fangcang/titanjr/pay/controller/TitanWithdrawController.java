package com.fangcang.titanjr.pay.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.BalanceWithDrawRequest;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.BalanceWithDrawResponse;
import com.fangcang.titanjr.dto.response.CityInfosResponse;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.pay.req.CreateTitanRateRecordReq;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.req.WithDrawRequest;
import com.fangcang.titanjr.pay.rsp.TitanRateComputeRsp;
import com.fangcang.titanjr.pay.services.TitanRateService;
import com.fangcang.titanjr.pay.services.TitanTradeService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("/withdraw")
/**
 * 金融提现控制器
 * @ClassName: TitanWithdrawController 
 * @Description: 对外提供各种提现操作服务
 * @author: wengxitao
 * @date: 2016年10月17日 上午11:07:19
 */
public class TitanWithdrawController extends BaseController {
	private static final long serialVersionUID = -3445993955420936758L;

	private static final Log log = LogFactory
			.getLog(TitanWithdrawController.class);

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	@Resource
	private TitanFinancialBankCardService titanFinancialBankCardService;

	@Resource
	private TitanFinancialUserService titanFinancialUserService;

	@Resource
	private TitanRateService titanRateService;
	
	@Resource
	private TitanFinancialOrganService financialOrganService;
	
	@Resource
	private TitanTradeService financialTradeService;
	
	/**
	 * 进入提现操作界面
	 * @Title: toAccountWithDrawPage 
	 * @param userId   提现机构编号
	 * @param fcUserId 提现具体人ID
	 * @param orderNo 提现订单号
	 * @param model 
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping(value = "/account-withdraw", method = RequestMethod.GET)
	public String toAccountWithDrawPage(String userId, String fcUserId,
			String orderNo, Model model) throws Exception {
		if (null != userId) {
			// 根据用户ID查询对于的组织机构信息
			FinancialOrganDTO financialOrganDTO = this.getTitanOrganDTO(userId);
			
			// 查询用户账户余额信息
			AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
			accountBalanceRequest.setUserid(userId);
			AccountBalanceResponse balanceResponse = titanFinancialAccountService
					.queryAccountBalance(accountBalanceRequest);

			if (balanceResponse.isResult()
					&& CollectionUtils.isNotEmpty(balanceResponse
							.getAccountBalance())) {
				model.addAttribute("accountBalance", balanceResponse
						.getAccountBalance().get(0));
			}

			// 查询用户对应绑定的银行卡信息
			BankCardBindInfoRequest brq = new BankCardBindInfoRequest();
			brq.setUserid(userId);
			//账户用途
			brq.setAccountPurpose(2);
			brq.setUsertype(String.valueOf(financialOrganDTO.getUserType()));
			////1：结算卡，2：所有绑定卡
			brq.setObjorlist("2");
			brq.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			brq.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);

			
			String mCode = null;
			OrgBindInfo orgBindInfo = new OrgBindInfo();
			orgBindInfo.setUserid(userId);
			OrgBindInfo bindInfo = financialOrganService
					.queryOrgBindInfoByUserid(orgBindInfo);
			if (bindInfo != null) {
				mCode = bindInfo.getMerchantCode();
			}

			// 设置商家主题]
			log.info("the merchantcode is " + mCode);
			if (StringUtil.isValidString(mCode)) {
				MerchantResponseDTO merchantResponseDTO = financialTradeService
						.getMerchantResponseDTO(mCode);
				if (null != merchantResponseDTO) {
					log.info("the theme is" + merchantResponseDTO.getTheme());
					model.addAttribute("CURRENT_THEME",
							merchantResponseDTO.getTheme());
				}
			}
			
			QueryBankCardBindInfoResponse cbr = titanFinancialBankCardService
					.getBankCardBindInfo(brq);

			if (cbr.isResult()
					&& CollectionUtils.isNotEmpty(cbr.getBankCardInfoDTOList())) {
				for (BankCardInfoDTO cid : cbr.getBankCardInfoDTOList()) {
					if (cid.getStatus().equals(
							BankCardEnum.BankCardStatusEnum.NORMAL.getKey())
							&& cid.getAccountpurpose()
									.equals(BankCardEnum.BankCardPurposeEnum.DEBIT_WITHDRAW_CARD.getKey())
							|| cid.getAccountpurpose()
									.equals(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD.getKey())) {
						model.addAttribute("bindBankCard", cid);
					}
				}
			}

			// 设置界面需要获取的信息
			model.addAttribute("organ", financialOrganDTO);
			model.addAttribute("fcUserId", fcUserId);
			model.addAttribute("userId", userId);
			model.addAttribute("orderNo", orderNo);
		}
		return "account-overview/account-withdraw";
	}
	/**
	 * 执行提现操作申请
	 * @Title: accountWithDraw 
	 * @Description: TODO
	 * @param withDrawRequest
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "/toAccountWithDraw")
	public String accountWithDraw(WithDrawRequest withDrawRequest) {

		if (null == withDrawRequest.getUserId()
				|| null == withDrawRequest.getFcUserId()
				|| null == withDrawRequest.getOrderNo()) {

			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		boolean needBindCard = false;
		boolean needBindNewCard = false;
		String cardNo = null;
		// 1.检查参数
		if (withDrawRequest.getHasBindBanCard() == 0) {
			if (!StringUtil.isValidString(withDrawRequest.getBankCode())
					|| !StringUtil.isValidString(withDrawRequest
							.getAccountNum())
					|| !StringUtil.isValidString(withDrawRequest
							.getAccountName())) {
				return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
			} else {
				needBindCard = true;
				cardNo = withDrawRequest.getAccountNum();
			}
		} else {
			if (withDrawRequest.getUseNewBankCard() == 1) {
				if (!StringUtil.isValidString(withDrawRequest.getBankCode())
						|| !StringUtil.isValidString(withDrawRequest
								.getAccountNum())
						|| !StringUtil.isValidString(withDrawRequest
								.getAccountName())) {
					return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
				} else {
					needBindNewCard = true;
					needBindCard = true;
					cardNo = withDrawRequest.getAccountNum();
				}
			} else {
				cardNo = withDrawRequest.getOriginalAccount();
			}
		}

		String tfsUserid = null;
		if (StringUtil.isValidString(withDrawRequest.getFcUserId())) {
			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
			titanUserBindInfoDTO.setFcuserid(Long.parseLong(withDrawRequest
					.getFcUserId()));
			titanUserBindInfoDTO = titanFinancialUserService
					.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
			if (titanUserBindInfoDTO != null
					&& titanUserBindInfoDTO.getTfsuserid() != null) {
				tfsUserid = titanUserBindInfoDTO.getTfsuserid().toString();
			}
		}

		if (!StringUtil.isValidString(withDrawRequest.getPassword())
				|| !StringUtil.isValidString(tfsUserid)) {
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		boolean istrue = titanFinancialUserService.checkPayPassword(
				withDrawRequest.getPassword(), tfsUserid);
		if (!istrue) {
			return toMsgJson(TitanMsgCodeEnum.PAY_PWD_ERROR);
		}
		
		// 开始计算并设置费率
		TitanRateComputeReq computeReq = new TitanRateComputeReq();
		computeReq.setAmount(withDrawRequest.getAmount());
		computeReq.setItemTypeEnum(null);
		computeReq.setUserId(withDrawRequest.getUserId());

		TitanRateComputeRsp computeRsp = titanRateService
				.rateCompute(computeReq);
		
		long al = Long.parseLong(NumberUtil.covertToCents(withDrawRequest
				.getAmount()));
		long er = Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getExRateAmount()));

		if (er > al) {
			return toMsgJson(TitanMsgCodeEnum.RATE_NOT_MORE_WITHDRAW);
		}
		
		FinancialOrganDTO financialOrganDTO = this
				.getTitanOrganDTO(withDrawRequest.getUserId());
		if (needBindNewCard) { // 需判定或删除原卡配置
			DeleteBindBankRequest deleteBindBankRequest = new DeleteBindBankRequest();
			deleteBindBankRequest.setUserid(withDrawRequest.getUserId());
			deleteBindBankRequest
					.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
			deleteBindBankRequest
					.setConstid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
			deleteBindBankRequest.setUsertype(String.valueOf(financialOrganDTO
					.getUserType()));
			deleteBindBankRequest.setAccountnumber(withDrawRequest
					.getOriginalAccount());
			DeleteBindBankResponse deleteBindBankResponse = titanFinancialBankCardService
					.deleteBindBank(deleteBindBankRequest);
			if (!deleteBindBankResponse.isResult()) {
				return toMsgJson(TitanMsgCodeEnum.USE_NEW_CARD_WITHDRAW_DEL_OLD_CARD_FAIL);
			}
		}

		if (needBindCard) {// 需要绑定卡
			CusBankCardBindRequest bankCardBindRequest = new CusBankCardBindRequest();
			bankCardBindRequest.setUserId(withDrawRequest.getUserId());
			bankCardBindRequest
					.setProductId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
			bankCardBindRequest
					.setConstId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
			bankCardBindRequest.setUserType(String.valueOf(financialOrganDTO
					.getOrgType()));
			bankCardBindRequest.setAccountNumber(withDrawRequest
					.getAccountNum());
			bankCardBindRequest
					.setAccountName(withDrawRequest.getAccountName());
			bankCardBindRequest.setAccountTypeId("00");
			bankCardBindRequest.setBankHeadName(withDrawRequest.getBankName());
			bankCardBindRequest.setCurrency("CNY");
			bankCardBindRequest.setReqSn(String.valueOf(System
					.currentTimeMillis()));
			bankCardBindRequest.setSubmitTime(DateUtil.dateToString(new Date(),
					"yyyyMMddHHmmss"));
			bankCardBindRequest.setAccountProperty(String.valueOf(2));
			bankCardBindRequest
					.setAccountPurpose(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD
							.getKey());
			if (financialOrganDTO.getUserType() == 1) {
				bankCardBindRequest.setCertificateType(String
						.valueOf(TitanOrgEnum.CertificateType.SFZ.getKey()));
				bankCardBindRequest.setCertificateNumber(financialOrganDTO
						.getBuslince());
				// bankCardBindRequest.setCertificateNumber("411381196802185622");
			} else {
				bankCardBindRequest.setCertificateType(String
						.valueOf(financialOrganDTO.getCertificateType()));
				bankCardBindRequest.setCertificateNumber(String
						.valueOf(financialOrganDTO.getCertificateNumber()));
			}
			bankCardBindRequest.setBankCode(withDrawRequest.getBankCode());
			bankCardBindRequest.setUserType("1");
			
			bankCardBindRequest.setBankBranch(withDrawRequest.getBranchCode());
			bankCardBindRequest.setBankCity(withDrawRequest.getCityName());
			if(StringUtil.isValidString(withDrawRequest.getCityCode())){
				bankCardBindRequest.setBankProvince(this.queryProvinceName(withDrawRequest.getCityCode()));
			}
			CusBankCardBindResponse cardBindResponse = titanFinancialBankCardService
					.bankCardBind(bankCardBindRequest);
			if (!cardBindResponse.isResult()) {
				return toMsgJson(TitanMsgCodeEnum.USE_NEW_CARD_WITHDRAW_BING_CARD_FAIL);
			}
		}

		BalanceWithDrawRequest balanceWithDrawRequest = new BalanceWithDrawRequest();
		balanceWithDrawRequest.setUserId(withDrawRequest.getUserId());
		balanceWithDrawRequest
				.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
		balanceWithDrawRequest.setAmount(withDrawRequest.getAmount());
		balanceWithDrawRequest.setCardNo(cardNo);

		balanceWithDrawRequest.setCreator(this.getUserNameByUserId(tfsUserid));
		balanceWithDrawRequest.setOrderDate(DateUtil.dateToString(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		balanceWithDrawRequest.setUserorderid(OrderGenerateService
				.genResquestNo());
		balanceWithDrawRequest.setBankName(withDrawRequest.getBankName());
		if (StringUtil.isValidString(withDrawRequest.getOriginalBankName())) {
			balanceWithDrawRequest.setBankName(withDrawRequest
					.getOriginalBankName());
		}
		balanceWithDrawRequest.setOrderNo(withDrawRequest.getOrderNo());
		
		
		if (computeRsp != null) {
			balanceWithDrawRequest.setStandfee(computeRsp.getStRateAmount());
			balanceWithDrawRequest.setReceivablefee(computeRsp
					.getRsRateAmount());
			balanceWithDrawRequest.setReceivedfee(computeRsp.getExRateAmount());
		}

		BalanceWithDrawResponse balanceWithDrawResponse = titanFinancialAccountService
				.accountBalanceWithdraw(balanceWithDrawRequest);
		if (!balanceWithDrawResponse.isResult()) {
			return toMsgJson(TitanMsgCodeEnum.WITHDRAW_OPT_FAIL);
		}
		
		CreateTitanRateRecordReq req = new CreateTitanRateRecordReq();
		req.setAmount(Long.parseLong(NumberUtil.covertToCents(computeReq
				.getAmount())));
		req.setReceivablefee(Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getRsRateAmount())));
		req.setReceivedfee(Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getExRateAmount())));
		req.setStanderdfee(Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getStRateAmount())));
		req.setPayType(4);
		req.setUserId(computeReq.getUserId());
		req.setReceivableRate(Float.parseFloat(computeRsp.getRsRate()));
		req.setReceivedRate(Float.parseFloat(computeRsp.getExecutionRate()));
		req.setStandardRate(Float.parseFloat(computeRsp.getStandRate()));
		req.setRateType(computeRsp.getRateType());
		req.setOrderNo(balanceWithDrawRequest.getUserorderid());
		req.setCreator(computeReq.getUserId());
		titanRateService.addRateRecord(req);
		
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
	}
	
	
	 private String queryProvinceName(String cityCode){
		 if(!StringUtil.isValidString(cityCode)){
			 return null;
		 }
		CityInfoDTO cityInfo = new CityInfoDTO();
    	cityInfo.setCityCode(cityCode);
    	CityInfosResponse response  = titanFinancialAccountService.getCityInfoList(cityInfo);
    	if (!response.isResult() || response.getCityInfoDTOList() ==null &&response.getCityInfoDTOList().size()>0){//如果是北京市或者重庆市的话，这个地方的size为2
    		return null;
    	}
    	
    	cityInfo = response.getCityInfoDTOList().get(0);
    	if(response.getCityInfoDTOList().size()==2){
    		return cityInfo.getCityName();
    	}
    	
    	if(StringUtil.isValidString(cityInfo.getParentCode())){
    		return queryProvinceName(cityInfo.getParentCode());
    	}else{
    		return cityInfo.getCityName();
    	}
		}
	
	
	@ResponseBody
	@RequestMapping("getCityList")
	public String getCityList(CityInfoDTO cityInfo) {
		CityInfosResponse response = titanFinancialAccountService
				.getCityInfoList(cityInfo);

		if (response.isResult()&& CollectionUtils.isNotEmpty(response.getCityInfoDTOList())) {
			return JsonConversionTool.toJson(response);
		}
		return null;
	}
	/**
	 * 根据用户ID查询对应的用户名信息
	 * @Title: getUserNameByUserId 
	 * @Description: TODO
	 * @param tfsUserid
	 * @return
	 * @return: String
	 */
	private String getUserNameByUserId(String tfsUserid) {
		if (StringUtil.isValidString(tfsUserid)) {
			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
			titanUserBindInfoDTO.setTfsuserid(Integer.parseInt(tfsUserid));
			try {
				titanUserBindInfoDTO = titanFinancialUserService
						.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
			} catch (Exception e) {
				log.error("查询用户名失败:" + e.getMessage());
			}
			if (titanUserBindInfoDTO != null) {
				return titanUserBindInfoDTO.getUsername();
			}
		}
		return null;
	}
	/**
	 * 根据用户ID查询对于的组织机构信息
	 * @Title: getTitanOrganDTO 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: FinancialOrganDTO
	 */
	private FinancialOrganDTO getTitanOrganDTO(String userId) {
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setUserId(userId);
		FinancialOrganResponse organOrganResponse = titanFinancialOrganService
				.queryFinancialOrgan(organQueryRequest);
		if (organOrganResponse.isResult()) {
			return organOrganResponse.getFinancialOrganDTO();
		}
		return null;
	}

}
