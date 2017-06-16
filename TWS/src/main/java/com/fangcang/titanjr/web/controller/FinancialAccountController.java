package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TradeTypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.bean.CityInfoDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.ForgetPayPassword;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.*;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.util.WebConstant;
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
    
    
    
    /****************************************************************************
     * 
     * 
     * 泰坦钱包使用的Controlller
     * 
     * 
     * 
     * 
     */
    
    

    @RequestMapping(value = "/overview-main", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_VIEW_39})
    public String overviewMain(HttpServletRequest request, Model model) throws Exception {
    	UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(Integer.valueOf(getTfsUserId()));
		UserInfoPageResponse userInfoPageResponse = titanFinancialUserService.queryUserInfoPage(userInfoQueryRequest);
		TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setUserid(titanUser.getUserid());
        orgDTO = titanFinancialOrganService.queryOrg(orgDTO);
        model.addAttribute("orgDTO", orgDTO);
        return "account-overview/overview-main";
    }
    
    
    
    /**
     * 提供客户端查询当前账户的余额
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query-account-balance", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryAccountBalance()
    {
		 AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
	     accountBalanceRequest.setUserid(this.getUserId());
	      
	     AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
	      
	     if (balanceResponse.isResult() && CollectionUtils.isNotEmpty(balanceResponse.getAccountBalance())) {
	         return toJson(balanceResponse.getAccountBalance().get(0));
	     }
	      return "";
    }
    
    @ResponseBody
    @RequestMapping("validate_person_Enterprise")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String validatePersonOrEnterprise(HttpServletRequest request, Model model){
    	Map<String,String> resultMap = new HashMap<String, String>();
    	resultMap.put(WebConstant.RESULT,WebConstant.FAIL);
    	//判断是否为对公账户
    	FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
    	organQueryRequest.setUserId(this.getUserId());
    	FinancialOrganResponse response  = titanFinancialOrganService.queryBaseFinancialOrgan(organQueryRequest);
    	if(!response.isResult() || response.getFinancialOrganDTO()==null)
    	{
    		log.error("无该机构,查询机构userid:"+this.getUserId()+",返回结果response:"+Tools.gsonToString(response));
    		resultMap.put(WebConstant.MSG, "系统错误");
        	return toJson(resultMap);
    	}
    	
    	Integer userType = response.getFinancialOrganDTO().getUserType();
    	resultMap.put(WebConstant.RESULT,WebConstant.SUCCESS);
    	if(userType !=null && WebConstant.ACCOUNT_PUBLIC.equals(userType.toString()))
    	{
    		QueryBankCardBindInfoResponse  rsp = this.queryBankCardInfo(userType.toString());
		
			if(!rsp.isResult() || rsp.getBankCardInfoDTOList()==null)
			{
				log.info("该卡尚未绑定");
				resultMap.put(WebConstant.MSG, WebConstant.ACCOUNT_PUBLIC_NO_BIND_3);
	    		return toJson(resultMap);
			}
			BankCardInfoDTO dto = rsp.getBankCardInfoDTOList().get(0);
			if(WebConstant.BANKCARD_SUCCESS==Integer.parseInt(dto.getStatus()))
			{
				resultMap.put(WebConstant.MSG, WebConstant.ACCOUNT_PUBLIC_SUCCESS_4);
    		}else if(WebConstant.BANKCARD_BINDING==Integer.parseInt(dto.getStatus()) 
    				||WebConstant.BANKCARD_AUDIT==Integer.parseInt(dto.getStatus()))
    		{
    			resultMap.put(WebConstant.MSG, WebConstant.ACCOUNT_PUBLIC_BINDING_6);
    		}else if(WebConstant.BANKCARD_FAILED ==Integer.parseInt(dto.getStatus()))
    		{
    			resultMap.put(WebConstant.MSG, WebConstant.ACCOUNT_PUBLIC_FAIL_5);
    		}else if(WebConstant.BANKCARD_DELETE == Integer.parseInt(dto.getStatus()))
    		{
    			resultMap.put(WebConstant.MSG, WebConstant.ACCOUNT_PUBLIC_NO_BIND_3);
    		}
			titanFinancialBankCardService.bindBankCardForOne(this.getUserId());
			return toJson(resultMap);
    	}
    	resultMap.put(WebConstant.MSG, WebConstant.ACCOUNT_PERSON);
    	return toJson(resultMap);
    }
    
    private QueryBankCardBindInfoResponse queryBankCardInfo(String userType)
    {
    	BankCardBindInfoRequest brq = new BankCardBindInfoRequest();
		brq.setUserid(this.getUserId());
		//账户用途
		brq.setUsertype(userType);
		////1：结算卡，2：所有绑定卡
		brq.setObjorlist(CommonConstant.ALLCARD);
		brq.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
		brq.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		return titanFinancialBankCardService.getBankCardBindInfo(brq);
    }
    
    @RequestMapping(value = "/query-org-page")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryForPage(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            model.addAttribute("organ", this.getTitanOrganDTO());
            tradeDetailRequest.setUserid(this.getUserId());
            if (StringUtil.isValidString(tradeDetailRequest.getTradeTypeId())) {
                tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(tradeDetailRequest.getTradeTypeId()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getStartTimeStr())) {
                tradeDetailRequest.setStartTime(com.fangcang.titanjr.common.util.DateUtil.getDayBeginTime(DateUtil.stringToDate(tradeDetailRequest.getStartTimeStr())));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getEndTimeStr())) {
                tradeDetailRequest.setEndTime(com.fangcang.titanjr.common.util.DateUtil.getDayEndTime(DateUtil.stringToDate(tradeDetailRequest.getEndTimeStr())));
            }
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult()) {
                model.addAttribute("tradePage", tradeDetailResponse.getTransOrders());
            }
        }
        return "account-overview/trans-order-list";
    }
    
    @ResponseBody
    @RequestMapping("getOrgList")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String getOrgInfoList(){
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setUserId(this.getUserId());
        OrganBriefResponse organBriefResponse =  titanFinancialOrganService.queryOrganBriefByUserId(organQueryRequest);
        if (organBriefResponse.isResult() && CollectionUtils.isNotEmpty(organBriefResponse.getOrganDTOList())){
            return toJson(organBriefResponse);
        }
        return null;
    }
    
    
    @RequestMapping(value = "/order-receive-detail", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryReceiveOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-receive-detail";
    }
    @RequestMapping(value = "/order-pay-detail", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryPayOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        //付款时需查出当前机构
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setUserId(this.getUserId());
        FinancialOrganResponse organOrganResponse = titanFinancialOrganService.queryBaseFinancialOrgan(organQueryRequest);
        model.addAttribute("organ", organOrganResponse.getFinancialOrganDTO());
        return "account-overview/order-pay-detail";
    }

    @RequestMapping(value = "/order-recharge-detail", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryRechargeOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-recharge-detail";
    }

    @RequestMapping(value = "/order-withdraw-detail", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryWithdrawOrderDetail(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        setTransOrderDetail(tradeDetailRequest,model);
        return "account-overview/order-withdraw-detail";
    }

    @RequestMapping(value = "/order-remark", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String toOrderRemark(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            tradeDetailRequest.setUserid(this.getUserId());
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult() && CollectionUtils.isNotEmpty(tradeDetailResponse.getTransOrders().getItemList())) {
            	TransOrderDTO transOrder = tradeDetailResponse.getTransOrders().getItemList().get(0);
            	transOrder.setRemark(Tools.replaceEnterKeyHTML(transOrder.getRemark())); 
            	model.addAttribute("transOrder", transOrder);
                
            }
        }
        return "account-overview/order-remark";
    }
    
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_RECHARGE_40})
    @RequestMapping(value = "/goto_cashierDesk", method = RequestMethod.GET)
    public String gotoCashierDesk(String payType ,String succUrl, Model model)
    {
    	if(StringUtil.isValidString(payType))
    	{
    		PayerTypeEnum enum1 =  PayerTypeEnum.getPayerTypeEnumByKey(payType);
    		if(enum1 != null)
    		{
		    	model.addAttribute("userId", this.getUserId());
		    	model.addAttribute("payType",enum1.getKey());
		    	model.addAttribute("payOrderNo", OrderGenerateService.genLoacalPayOrderNo());
		    	model.addAttribute("userName",getUserNameByUserId(this.getTfsUserId()));
		    	model.addAttribute("payName", enum1.getMsg());
		    	model.addAttribute("payDesc", enum1.getMsg());
		    	model.addAttribute("tfsUserId", getTfsUserId());
		    	model.addAttribute("succUrl", succUrl);
		    	return "account-overview/goto-cashierDesk";
    		}
    	}
    	
    	return "";
    }
    
    
    /**
     * 交易单备注历史
     * @param tradeDetailRequest
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    
    @RequestMapping(value = "/order-remark-history")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String toOrderRemarkHisotry(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            tradeDetailRequest.setUserid(this.getUserId());
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult() && CollectionUtils.isNotEmpty(tradeDetailResponse.getTransOrders().getItemList())) {
            	TransOrderDTO transOrder = tradeDetailResponse.getTransOrders().getItemList().get(0);
            	transOrder.setRemark(Tools.replaceEnterKeyHTML(transOrder.getRemark())); 
            	model.addAttribute("transOrder", transOrder);
            }
        }
        return "account-overview/order-remark-history";
    }
    
    @RequestMapping(value = "/freeze-detail-page", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String toFreezeDetailPage(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        return "account-overview/freeze-detail";
    }

    @RequestMapping(value = "/query-freeze-list", method = RequestMethod.GET)
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String queryFreezeForPage(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, Model model) throws Exception {
        if (null != this.getUserId()) {
            model.addAttribute("organ", this.getTitanOrganDTO());
            tradeDetailRequest.setUserid(this.getUserId());
            if (StringUtil.isValidString(tradeDetailRequest.getTradeTypeId())) {
                tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(tradeDetailRequest.getTradeTypeId()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getStartTimeStr())) {
                tradeDetailRequest.setStartTime(com.fangcang.titanjr.common.util.DateUtil.getDayBeginTime(DateUtil.stringToDate(tradeDetailRequest.getStartTimeStr())));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getEndTimeStr())) {
                tradeDetailRequest.setEndTime(com.fangcang.titanjr.common.util.DateUtil.getDayEndTime(DateUtil.stringToDate(tradeDetailRequest.getEndTimeStr())));
            }
            tradeDetailRequest.setStatusId(OrderStatusEnum.FREEZE_SUCCESS.getStatus());
            
            TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
            if (tradeDetailResponse.isResult()) {
                model.addAttribute("tradePage", tradeDetailResponse.getTransOrders());
            }
        }
        return "account-overview/freeze-order-list";
    }
    
    @RequestMapping("exportExcel")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public void exportExcel(TradeDetailRequest tradeDetailRequest, HttpServletRequest request, HttpServletResponse response) {
        TradeDetailResponse tradeDetailResponse = null;
        if (null != this.getUserId()) {
            tradeDetailRequest.setUserid(this.getUserId());
            if (StringUtil.isValidString(tradeDetailRequest.getTradeTypeId())) {
                tradeDetailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(tradeDetailRequest.getTradeTypeId()));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getStartTimeStr())) {
                tradeDetailRequest.setStartTime(com.fangcang.titanjr.common.util.DateUtil.getDayBeginTime(DateUtil.stringToDate(tradeDetailRequest.getStartTimeStr())));
            }
            if (StringUtil.isValidString(tradeDetailRequest.getEndTimeStr())) {
                tradeDetailRequest.setEndTime(com.fangcang.titanjr.common.util.DateUtil.getDayEndTime(DateUtil.stringToDate(tradeDetailRequest.getEndTimeStr())));
            }
            if ("0".equals(tradeDetailRequest.getIsEscrowedPayment())) {
                tradeDetailRequest.setStatusId(OrderStatusEnum.FREEZE_SUCCESS.getStatus());
            }
            tradeDetailRequest.setPageSize(100000);
            tradeDetailResponse = titanFinancialTradeService.getTradeDetail(tradeDetailRequest);
        }


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
            head.createCell(1).setCellValue("金融交易号");
            head.createCell(2).setCellValue("业务单号");
            head.createCell(3).setCellValue("外部单号");
            head.createCell(4).setCellValue("交易时间");
            head.createCell(5).setCellValue("交易类型");
            head.createCell(6).setCellValue("交易内容");
            head.createCell(7).setCellValue("交易对方");
            head.createCell(8).setCellValue("订单金额");
            head.createCell(9).setCellValue("退款金额");
            head.createCell(10).setCellValue("手续费");
            head.createCell(11).setCellValue("交易结果");
            List<TransOrderDTO> orderDTOList = tradeDetailResponse.getTransOrders().getItemList();
            if (tradeDetailResponse != null && tradeDetailResponse.isResult()) {
                for (int i = 0; i < orderDTOList.size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);//创建一行
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(orderDTOList.get(i).getUserorderid());
                    row.createCell(2).setCellValue(orderDTOList.get(i).getBusinessordercode());
                    row.createCell(3).setCellValue(orderDTOList.get(i).getPayorderno());
                    row.createCell(4).setCellValue(DateUtil.dateToString(orderDTOList.get(i).getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
                    row.createCell(5).setCellValue(orderDTOList.get(i).getTradeType());
                    String tradeContent = orderDTOList.get(i).getGoodsname();
                    if (StringUtil.isValidString(orderDTOList.get(i).getGoodsdetail())) {
                        tradeContent = tradeContent + orderDTOList.get(i).getGoodsdetail();
                    }
                    row.createCell(6).setCellValue(tradeContent);
                    if (orderDTOList.get(i).getTransTarget() != null) {
                        row.createCell(7).setCellValue(orderDTOList.get(i).getTransTarget());
                    }
                    if (orderDTOList.get(i).getTradeamount() != null) {
                        double tradeAmount = orderDTOList.get(i).getTradeamount() / 100.0;
                        if ("付款".equals(orderDTOList.get(i).getTradeType()) ||
                                "提现".equals(orderDTOList.get(i).getTradeType())) {
                            tradeAmount = 0 - tradeAmount;
                        }
                        row.createCell(8).setCellValue(tradeAmount);
                    }
                    row.createCell(9).setCellValue(this.refundAmount(orderDTOList.get(i)));
                    if (orderDTOList.get(i).getReceivedfee() != null) {
                        row.createCell(10).setCellValue(orderDTOList.get(i).getReceivedfee() / 100.0);
                    }
                    if (StringUtil.isValidString(OrderStatusEnum.getStatusMsgByKey(orderDTOList.get(i).getStatusid()))) {
                        row.createCell(11).setCellValue(OrderStatusEnum.getStatusMsgByKey(orderDTOList.get(i).getStatusid()));
                        if(OrderStatusEnum.ORDER_IN_PROCESS.equals(orderDTOList.get(i).getStatusid())
                        		||OrderStatusEnum.RECHARGE_FAIL.equals(orderDTOList.get(i).getStatusid())
                        		||OrderStatusEnum.FREEZE_FAIL.equals(orderDTOList.get(i).getStatusid())){
                        	 row.createCell(11).setCellValue("处理中");
                        }
                    }
                }
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

    private String refundAmount(TransOrderDTO order){
    	if(!StringUtil.isValidString(order.getStatusid()) 
    			|| !StringUtil.isValidString(order.getPayerType())
    			||!OrderStatusEnum.isRefund(order.getStatusid())){//必须是退款
    		return "0";
    	}
    	
    	PayerTypeEnum payerType = PayerTypeEnum.getPayerTypeEnumByKey(order.getPayerType());
    	BigDecimal refundAmount = null;
    	if("收款".equals(order.getTradeType())){//收款方，且使用的是收款方的收银台
    		if(payerType.useReceiverCashDesk()){
    			refundAmount = new BigDecimal(order.getTradeamount()).subtract(new BigDecimal(order.getReceivedfee()));
        	}else{
        		refundAmount = new BigDecimal(order.getTradeamount());
        	}
    		return "-"+refundAmount.divide(new BigDecimal(100)).toString();
    	}else if("付款".equals(order.getTradeType())){
    		if(payerType.useReceiverCashDesk()){
    			refundAmount = new BigDecimal(order.getTradeamount());
        	}else{
        		refundAmount = new BigDecimal(order.getTradeamount()).add(new BigDecimal(order.getReceivedfee()));
        	}
    		return "+"+refundAmount.divide(new BigDecimal(100)).toString();
    	}
    	
    	return "0";
    }
    
//    绑定提现卡start
    @RequestMapping("toBindCardStepOne")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_RECHARGE_40})
    public String toBindCardStepOne(String modifyOrBind,Model model){
    	model.addAttribute("modifyOrBind", modifyOrBind);
    	return "account-overview/bind_card_one";
    }
    
    @RequestMapping("toBindCardStepTwo")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_RECHARGE_40})
    public String toBindCardStepTwo(String modifyOrBind,Model model){
    	if (null != this.getUserId()) {
            model.addAttribute("organ", this.getTitanOrganDTO());
       }
    	model.addAttribute("modifyOrBind", modifyOrBind);
    	return "account-overview/bind_card_two";
    }
    
    @RequestMapping("bankCardBind")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_RECHARGE_40})
    public String bankCardBindToPublic(BindBankCardRequest  bindBankCardRequest,Model model){
     	if(!StringUtil.isValidString(bindBankCardRequest.getBankCardCode()) 
    			|| !StringUtil.isValidString(bindBankCardRequest.getBankCardName())
    			|| !StringUtil.isValidString(bindBankCardRequest.getUserName())
    			|| !StringUtil.isValidString(bindBankCardRequest.getBankCode())
    			){
     		model.addAttribute("msg", "参数不能为空");
    		return "account-overview/bind_card_three";
    	}
     	
     	if(WebConstant.BIND_BANK_CARD.equals(bindBankCardRequest.getModifyOrBind())){//绑卡
     		CusBankCardBindResponse cardBindResponse = bindBindCardToPublic(bindBankCardRequest);
     		 if (!cardBindResponse.isResult()){
     			 log.error("绑卡失败"+cardBindResponse.getReturnMessage());
     			 model.addAttribute("msg", cardBindResponse.getReturnMessage());
             }
     	}else if(WebConstant.MODIFY_BANK_CARD.equals(bindBankCardRequest.getModifyOrBind())){//失败修改绑卡
     		ModifyInvalidWithDrawCardResponse modifyInvalidWithDrawCardResponse = modifyBindCard(bindBankCardRequest);
     	    if(!modifyInvalidWithDrawCardResponse.isResult()){
     	    	 log.error("绑卡失败"+modifyInvalidWithDrawCardResponse.getReturnMessage());
     			 model.addAttribute("msg", modifyInvalidWithDrawCardResponse.getReturnMessage());
     	    }
     	}else{
     		model.addAttribute("msg", "参数错误");
     	}
		return "account-overview/bind_card_three";
    }
    
    @ResponseBody
    @RequestMapping("getBankInfoList")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String getBankInfo(BankInfoQueryRequest request){
        BankInfoResponse bankInfoResponse =  titanFinancialBaseInfoService.queryBankInfoList(request);
        if (bankInfoResponse.isResult() && CollectionUtils.isNotEmpty(bankInfoResponse.getBankInfoDTOList())){
            return toJson(bankInfoResponse);
        }
        return null;
    }
    
    /**
     * 支行ss获取城市
     * @return
     */
    @ResponseBody
    @RequestMapping("getCitys")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String getCitys(){
    	CityInfosResponse response = new CityInfosResponse();
    	Map<String,CityInfoDTO> cityMap = this.getParentCity();
    	List<CityInfoDTO> cityInfos = getCity();
    	for(CityInfoDTO city :cityInfos){
    		city.setCityName(this.getCityName(city,cityMap));
    	}
    	response.setCityInfoDTOList(cityInfos);
    	return toJson(response);
    }
    
    private String getCityName(CityInfoDTO city,Map<String,CityInfoDTO> cityMap){
    	if(city ==null || !StringUtil.isValidString(city.getCityCode())){
    		return "";
    	}
    	if(CommonConstant.BEIJING_CODE.equals(city.getCityCode())
    			||CommonConstant.TIANJING_CODE.equals(city.getCityCode())
    			||CommonConstant.CHONGQING_CODE.equals(city.getCityCode())
    			||CommonConstant.SHNAGHAI_CODE.equals(city.getCityCode())){//直辖市
    		return city.getCityName();
    	}
    	StringBuffer cityName=new StringBuffer(city.getCityName());
    	city = cityMap.get(city.getParentCode());
    	if(city ==null){
    		return cityName.toString();
    	}
    	cityName = cityName.insert(0, "-").insert(0, city.getCityName()) ;
    	city = cityMap.get(city.getParentCode());
    	if(city ==null){
    		return cityName.toString();
    	}
    	return cityName.insert(0, "-").insert(0, city.getCityName()).toString();
    }
    
    private Map<String,CityInfoDTO> getParentCity(){
    	CityInfoDTO cityInfo = new CityInfoDTO();
    	cityInfo.setDataType(1);
    	CityInfosResponse response =  titanFinancialAccountService.getCityInfoList(cityInfo);
    	
    	Map<String,CityInfoDTO> citys = new HashMap<String, CityInfoDTO>();
     	for(CityInfoDTO city : response.getCityInfoDTOList() ){//将其code和name放到键值对中
     		citys.put(city.getCityCode(), city);
     	}
     	
     	Set<String> cityCodes = new HashSet<String>(citys.keySet());
     	for(String cityCode : cityCodes){
     		cityInfo.setDataType(null);
     		cityInfo.setParentCode(cityCode);
     		response =  titanFinancialAccountService.getCityInfoList(cityInfo);
     		for(CityInfoDTO city : response.getCityInfoDTOList() ){//将其code和name放到键值对中
         		citys.put(city.getCityCode(), city);
         	}
     	}
    	return citys;
    }
    
    
    private List<CityInfoDTO> getCity(){
    	List<CityInfoDTO> citys = new ArrayList<CityInfoDTO>();
    	
    	CityInfoDTO cityInfo = new CityInfoDTO();
    	cityInfo.setDataType(1);
    	CityInfosResponse response =  titanFinancialAccountService.getCityInfoList(cityInfo);
    	
     	for(CityInfoDTO city : response.getCityInfoDTOList()){
     		cityInfo.setDataType(null);
     		cityInfo.setParentCode(city.getCityCode());
     		response =  titanFinancialAccountService.getCityInfoList(cityInfo);
     		for(CityInfoDTO cityinfo : response.getCityInfoDTOList() ){//将其code和name放到键值对中
     			citys.add(cityinfo) ;
         	}
     	}
     	return citys;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/checkBindAccountWithDrawCard")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String checkBindAccountWithDrawCard(HttpServletRequest request, Model model){
    	titanFinancialBankCardService.bindBankCardForOne(this.getUserId());
    	 return this.validatePersonOrEnterprise(request, model);
    }
    
    @RequestMapping(value = "/updateOrderRemark")
    @ResponseBody
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
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

    @RequestMapping("showSetPayPassword")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String showSetPayPassword() {
        return "checkstand-pay/setPayPassword";
    }
    
    
    
    
    
    /****************************************************************************
     * 
     * 
     * 泰坦钱包使用的Controlller end
     * 
     * 
     * 
     * 
     */
    
    
 
    
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
    
    
    
    private ModifyInvalidWithDrawCardResponse modifyBindCard(BindBankCardRequest bindBankCardRequest){
    	
    	ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest = new ModifyInvalidWithDrawCardRequest();
    	modifyInvalidWithDrawCardRequest.setAccountnumber(bindBankCardRequest.getBankCardCode());
    	modifyInvalidWithDrawCardRequest.setAccountrealname(bindBankCardRequest.getUserName());
    	modifyInvalidWithDrawCardRequest.setHankheadname(bindBankCardRequest.getBankCardName());
    	modifyInvalidWithDrawCardRequest.setBankhead(bindBankCardRequest.getBankCode());
    	modifyInvalidWithDrawCardRequest.setUserid(this.getUserId());
    	modifyInvalidWithDrawCardRequest.setBankcity(bindBankCardRequest.getCityName());
    	modifyInvalidWithDrawCardRequest.setBankprovinec(this.queryProvinceName(bindBankCardRequest.getCityCode()));
    	modifyInvalidWithDrawCardRequest.setHankbranch(bindBankCardRequest.getBranchCode());
    	modifyInvalidWithDrawCardRequest.setUsertype(WebConstant.ACCOUNT_PUBLIC);
    	return titanFinancialBankCardService.modifyinvalidPublicCard(modifyInvalidWithDrawCardRequest);
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
         bankCardBindRequest.setAccountProperty(WebConstant.ACCOUNT_PUBLIC);
         //暂时改为私人账户
//         bankCardBindRequest.setAccountProperty(CommonConstant.ACCOUNT_PERSON);
         bankCardBindRequest.setAccountPurpose(BankCardEnum.BankCardPurposeEnum.WITHDRAW_CARD.getKey());
         bankCardBindRequest.setCertificateType(String.valueOf(0));
         //查询企业营业执照号
         bankCardBindRequest.setCertificateNumber(this.getTitanOrganDTO().getBuslince());
         
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
    @RequestMapping("setPayPassword")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public Map<String, String> setPayPassword(HttpServletRequest request, PayPasswordRequest payPasswordRequest) {
        Map<String, String> map = new HashMap<String, String>();
        if (payPasswordRequest != null && StringUtil.isValidString(payPasswordRequest.getPayPassword())) {
//        	payPasswordRequest.setPayPassword(RSADecryptString.decryptString(payPasswordRequest.getPayPassword(),request));
        	payPasswordRequest.setPayPassword(payPasswordRequest.getPayPassword());
        	if(StringUtil.isValidString(payPasswordRequest.getOldPassword())){
//        		payPasswordRequest.setOldPassword(RSADecryptString.decryptString(payPasswordRequest.getOldPassword(),request));
        		payPasswordRequest.setOldPassword(payPasswordRequest.getOldPassword());
        	}
        	payPasswordRequest.setTfsuserid(this.getTfsUserId());
        	log.info("设置付款密码的传入参数:"+toJson(payPasswordRequest));
            PayPasswordResponse payPasswordResponse = titanFinancialUserService.saveOrUpdatePayPassword(payPasswordRequest);
            if (payPasswordResponse.isSaveSuccess()) {
                map.put(WebConstant.RESULT, WebConstant.SUCCESS);
            } else {
                map.put(WebConstant.MSG, payPasswordResponse.getReturnMessage());
            }
            return map;
        }
        map.put(WebConstant.RESULT, "fail");
        map.put(WebConstant.MSG, "系统错误");
        return map;
    }

    @ResponseBody
    @RequestMapping("forgetPayPassword")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String forgetPayPassword(ForgetPayPassword forgetPayPassword){
    	if(forgetPayPassword ==null
    			||!StringUtil.isValidString(forgetPayPassword.getPayPassword())
    			||!StringUtil.isValidString(forgetPayPassword.getUserName())
    			){
    		return toJson(putSysError("密码不能空格或者空"));
    	}
    	
    	//获取该用户的用户名
    	if(!this.getUserName().equals(forgetPayPassword.getUserName())){
    		return toJson(putSysError("您输入的用户名错误"));
    	}
    	
		PayPasswordRequest payPasswordRequest  = new PayPasswordRequest();

//    		payPasswordRequest.setPayPassword(RSADecryptString.decryptString(forgetPayPassword.getPayPassword(),request));
		payPasswordRequest.setPayPassword(forgetPayPassword.getPayPassword());
		payPasswordRequest.setIsForget(com.fangcang.titanjr.common.util.CommonConstant.IS_FORGET_PAYPASSWORD);
		payPasswordRequest.setTfsuserid(this.getTfsUserId());
	    PayPasswordResponse payPasswordResponse = titanFinancialUserService.saveOrUpdatePayPassword(payPasswordRequest);
	    if (payPasswordResponse.isSaveSuccess()) {
	    	return toJson(putSuccess());
        } else {
        	return toJson(putSysError(payPasswordResponse.getReturnMessage()));
        }
    }
    
    @ResponseBody
    @RequestMapping("checkIsSetPayPassword")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public Map<String, String> checkIsSetPayPassword(String fcUserid,HttpServletRequest request) {
    	boolean flag = false;
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "false");
        flag = titanFinancialUserService.checkIsSetPayPassword(fcUserid,getTfsUserId());
        if (flag) {
            map.put(WebConstant.RESULT, WebConstant.SUCCESS);
        }
        return map;
    }

  

    @RequestMapping("showPayPassword")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
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
        FinancialOrganResponse organOrganResponse = titanFinancialOrganService.queryBaseFinancialOrgan(organQueryRequest);
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
	
	@ResponseBody
	@RequestMapping("/check_account")
	public String checkRecieveAccount(String recieveOrgName,String recieveTitanCode){
		if(!StringUtil.isValidString(recieveOrgName)
				|| !StringUtil.isValidString(recieveTitanCode)){
			return toJson(putSysError("账户名和泰坦码不能为空"));
		}
		
		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
		accountCheckRequest.setOrgName(recieveOrgName);
		accountCheckRequest.setTitanCode(recieveTitanCode);
		AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
		if(accountCheckResponse.isCheckResult()){
		   return toJson(putSuccess());
		}
		return toJson(putSysError(accountCheckResponse.getReturnMessage()));
	} 
	
	@RequestMapping("/error_cashier")
	public String returnErrorPage(String msg,Model model){
		model.addAttribute("msg", msg);
		return "checkstand-pay/cashierDeskError";
	}
	
}