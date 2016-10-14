package com.fangcang.titanjr.pay.controller;

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

import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.BalanceWithDrawRequest;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.BalanceWithDrawResponse;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.req.WithDrawRequest;
import com.fangcang.titanjr.pay.rsp.TitanRateComputeRsp;
import com.fangcang.titanjr.pay.services.TitanRateService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("/withdraw")
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

	@RequestMapping(value = "/account-withdraw", method = RequestMethod.GET)
	public String toAccountWithDrawPage(String userId, String fcUserId,
			String orderNo, Model model) throws Exception {
		if (null != userId) {

			model.addAttribute("organ", this.getTitanOrganDTO(userId));

			model.addAttribute("fcUserId", fcUserId);
			model.addAttribute("userId", userId);
			model.addAttribute("orderNo", orderNo);

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

			BankCardBindInfoRequest bankCardBindInfoRequest = new BankCardBindInfoRequest();
			bankCardBindInfoRequest.setUserid(userId);
			bankCardBindInfoRequest.setAccountPurpose(2);
			bankCardBindInfoRequest.setUsertype(String
					.valueOf(getTitanOrganDTO(userId).getUserType()));
			bankCardBindInfoRequest.setObjorlist("2");
			bankCardBindInfoRequest
					.setConstid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
			bankCardBindInfoRequest
					.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
			QueryBankCardBindInfoResponse cardBindInfoResponse = titanFinancialBankCardService
					.getBankCardBindInfo(bankCardBindInfoRequest);
			if (cardBindInfoResponse.isResult()
					&& CollectionUtils.isNotEmpty(cardBindInfoResponse
							.getBankCardInfoDTOList())) {
				for (BankCardInfoDTO cardInfoDTO : cardBindInfoResponse
						.getBankCardInfoDTOList()) {
					if (cardInfoDTO.getStatus().equals(
							BankCardEnum.BankCardStatusEnum.NORMAL.getKey())
							&& // 必须要有效
							cardInfoDTO
									.getAccountpurpose()
									.equals(BankCardEnum.BankCardPurposeEnum.DEBIT_WITHDRAW_CARD
											.getKey())
							|| cardInfoDTO
									.getAccountpurpose()
									.equals(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD
											.getKey())) {
						model.addAttribute("bindBankCard", cardInfoDTO);
					}
				}
			}
		}
		return "account-overview/account-withdraw";
	}

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
			// 以下是哪个说必填但是可选
			bankCardBindRequest.setBankBranch("");
			bankCardBindRequest.setBankCity("");
			bankCardBindRequest.setBankProvince("");
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
		// 开始计算并设置费率
		TitanRateComputeReq computeReq = new TitanRateComputeReq();
		computeReq.setAmount(withDrawRequest.getAmount());
		computeReq.setItemTypeEnum(null);
		computeReq.setUserId(withDrawRequest.getUserId());
		
		TitanRateComputeRsp computeRsp =  titanRateService.rateCompute(computeReq);
		
		if(computeRsp != null)
		{
			balanceWithDrawRequest.setStandfee(computeRsp.getStRateAmount());
			balanceWithDrawRequest.setReceivablefee(computeRsp.getRsRateAmount());
			balanceWithDrawRequest.setReceivedfee(computeRsp.getExRateAmount());
		}
		
		BalanceWithDrawResponse balanceWithDrawResponse = titanFinancialAccountService
				.accountBalanceWithdraw(balanceWithDrawRequest);
		if (!balanceWithDrawResponse.isResult()) {
			toMsgJson(TitanMsgCodeEnum.WITHDRAW_OPT_FAIL);
		}
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
	}

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
