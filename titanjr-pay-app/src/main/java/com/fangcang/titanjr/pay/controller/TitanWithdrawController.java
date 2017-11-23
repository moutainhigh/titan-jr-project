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

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgSubDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.BalanceWithDrawRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.OrgSubCardRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.BalanceWithDrawResponse;
import com.fangcang.titanjr.dto.response.BankCardStatusResponse;
import com.fangcang.titanjr.dto.response.CityInfosResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.OrgSubCardResponse;
import com.fangcang.titanjr.pay.req.BindCardRequest;
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

import net.sf.json.JSONSerializer;

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
	public String accountWithDraw(String userId,String tfsUserId, String fcUserId,
			String orderNo, Model model) throws Exception {
		if (null != userId) {
			// 根据用户ID查询对于的组织机构信息
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setUserId(userId);
			FinancialOrganResponse organOrganResponse = titanFinancialOrganService
					.queryBaseFinancialOrgan(organQueryRequest);
			OrgSubDTO orgSub = organOrganResponse.getOrgSubDTO();
			FinancialOrganDTO organDTO = organOrganResponse.getFinancialOrganDTO();
		
			
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
			BankCardStatusResponse bankCardStatusResponse = titanFinancialBankCardService.getBankCardStatus(userId);
			model.addAttribute("bindBankCard", bankCardStatusResponse.getBankcard());

			model.addAttribute("organ", organDTO);
			model.addAttribute("orgSub", orgSub);
			model.addAttribute("fcUserId", fcUserId);
			model.addAttribute("tfsUserId", tfsUserId);
			model.addAttribute("userId", userId);
			model.addAttribute("orderNo", orderNo);
		}
		return "account-overview/account-withdraw";
	}
	/***
	 * 提现页面对私绑卡
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bindCard")
	public String bindCard(BindCardRequest bindCardRequest){
		try {
			if(!checkBindCard(bindCardRequest)){
				return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
			}
			OrgSubCardRequest orgSubCardRequest = new OrgSubCardRequest();
			orgSubCardRequest.setBankHeadName(bindCardRequest.getBankName());
			orgSubCardRequest.setAccountNumber(bindCardRequest.getAccountNum());
			orgSubCardRequest.setBankCode(bindCardRequest.getBankCode());
			orgSubCardRequest.setOrgCode(bindCardRequest.getOrgCode());// 虚拟机构

			OrgSubCardResponse orgSubCardResponse = titanFinancialBankCardService.bindOrgSubCard(orgSubCardRequest);
			if (orgSubCardResponse.isResult()) {
				return toMsgJson("0", "绑卡成功");
			} else {
				return toMsgJson("-1", orgSubCardResponse.getReturnMessage());
			}
		} catch (MessageServiceException e) {
			return toMsgJson("-1", e.getMessage());
		} catch (GlobalServiceException e) {
			 log.error("提现时绑卡失败，绑卡参数(bindCardRequest):"+Tools.gsonToString(bindCardRequest),e);
			return toMsgJson("-1", "绑卡失败");
		}
	}
	
	private boolean checkBindCard(BindCardRequest bindCardRequest){
		
		if(!StringUtil.isValidString(bindCardRequest.getOrgCode())){
			return false;
		}
		if(!StringUtil.isValidString(bindCardRequest.getAccountNum())){
			return false;
		}
		if(!StringUtil.isValidString(bindCardRequest.getBankName())){
			return false;
		}
		if(!StringUtil.isValidString(bindCardRequest.getBankCode())){
			return false;
		}
		return true;
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
	public String toAccountWithDraw(WithDrawRequest withDrawRequest) {

		if (null == withDrawRequest.getUserId()
				|| (isBlank(withDrawRequest.getFcUserId())&&isBlank(withDrawRequest.getTfsUserId()))
				|| null == withDrawRequest.getOrderNo()) {
			log.error("提现时请求参数错误，参数为:"+JSONSerializer.toJSON(withDrawRequest));
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
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
		//优先使用tfsuserId
		if(StringUtil.isValidString(withDrawRequest.getTfsUserId())){
			tfsUserid = withDrawRequest.getTfsUserId();
		}
		
		if (!StringUtil.isValidString(withDrawRequest.getPassword())
				|| !StringUtil.isValidString(tfsUserid)) {
			log.error("付款密码或用户id为空");
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		boolean istrue = titanFinancialUserService.checkPayPassword(
				withDrawRequest.getPassword(), tfsUserid);
		if (!istrue) {
			log.error("付款密码输入错误,用户id(tfsUserid):"+tfsUserid+",输入的付款密码是："+withDrawRequest.getPassword());
			return toMsgJson(TitanMsgCodeEnum.PAY_PWD_ERROR);
		}
		
		// 开始计算并设置费率
		TitanRateComputeReq computeReq = new TitanRateComputeReq();
		computeReq.setAmount(withDrawRequest.getAmount());
		computeReq.setUserId(withDrawRequest.getUserId());

		TitanRateComputeRsp computeRsp = titanRateService
				.rateCompute(computeReq);
		
		long al = Long.parseLong(NumberUtil.covertToCents(withDrawRequest
				.getAmount()));
		long er = Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getExRateAmount()));

		if (er > al) {
			log.error("手续费不能大于提现金额,手续费为："+er+"，提现参数为（withDrawRequest）："+Tools.gsonToString(withDrawRequest));
			return toMsgJson(TitanMsgCodeEnum.RATE_NOT_MORE_WITHDRAW);
		}
		 

		BalanceWithDrawRequest balanceWithDrawRequest = new BalanceWithDrawRequest();
		balanceWithDrawRequest.setUserId(withDrawRequest.getUserId());
		balanceWithDrawRequest
				.setProductid(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
		balanceWithDrawRequest.setAmount(withDrawRequest.getAmount());

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
					.getBenchmarkRateAmount());
			balanceWithDrawRequest.setReceivedfee(computeRsp.getExRateAmount());
		}
		
		
		BalanceWithDrawResponse balanceWithDrawResponse = titanFinancialAccountService
				.accountBalanceWithdraw(balanceWithDrawRequest);
		if (!balanceWithDrawResponse.isResult()) {
			log.error("提现失败，返回结果balanceWithDrawResponse："+Tools.gsonToString(balanceWithDrawResponse)+",提现参数为（withDrawRequest）："+Tools.gsonToString(withDrawRequest));
			return toMsgJson(TitanMsgCodeEnum.WITHDRAW_OPT_FAIL);
		}
		
		CreateTitanRateRecordReq req = new CreateTitanRateRecordReq();
		req.setAmount(Long.parseLong(NumberUtil.covertToCents(computeReq
				.getAmount())));
		req.setReceivablefee(Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getBenchmarkRateAmount())));
		req.setReceivedfee(Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getExRateAmount())));
		req.setStanderdfee(Long.parseLong(NumberUtil.covertToCents(computeRsp
				.getStRateAmount())));
		req.setPayType(4);
		req.setUserId(computeReq.getUserId());
		req.setReceivableRate(Float.parseFloat(computeRsp.getBenchmarkRate()));
		req.setReceivedRate(Float.parseFloat(computeRsp.getExecutionRate()));
		req.setStandardRate(Float.parseFloat(computeRsp.getStandRate()));
		req.setRateType(computeRsp.getRateType());
		req.setOrderNo(balanceWithDrawRequest.getUserorderid());
		req.setCreator(computeReq.getUserId());
		titanRateService.addRateRecord(req);
		
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);
	}
	

	private boolean isBlank(String src){
		return !StringUtil.isValidString(src);
	}
	
	 @SuppressWarnings("unused")
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
				.queryBaseFinancialOrgan(organQueryRequest);
		if (organOrganResponse.isResult()) {
			return organOrganResponse.getFinancialOrganDTO();
		}
		return null;
	}

}
