package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fangcang.dto.Response;

public class CashDeskData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//机构标识
	private String userId;
	
	//支付单号
	private String payOrderNo;
	
	//机构号
	private String merchantcode;
	
	//订单金额
	private String amount;
	
	//机构名称
	private String orgName;
	
	//泰坦码
	private String titanCode;
	
	//房仓userID
	private String fcUserid;
	
	//金融用户Id
	private String tfsUserId;
	
	//可用余额
	private String balanceusable;
	
	//操作人
	private String operator;
	
	//支付来源
	private String paySource;
	
	//是否冻结
	private String isEscrowed;
	
	//解冻时间
	private String escrowedDate;
	
	
	private String recieveOrgCode;
	
	//
	private String businessOrderCode;
	
	//收银台
	private CashierDeskDTO cashierDeskDTO;
	
	//常用的支付方式
	private List<CommonPayMethodDTO> commonPayMethodDTOList;
	
	private Map<String, FinancialOrganDTO> userIDOrgMap;
	
	private AccountHistoryDTO accountHistoryDTO;
	
	//是否成功
	private boolean isResult;
	
	private String returnCode;
	
	private String returnMsg;
	
	//返回页面
	private String returnPage;
	
    //当前商家主题
	private String currentTheme;
	

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTitanCode() {
		return titanCode;
	}

	public void setTitanCode(String titanCode) {
		this.titanCode = titanCode;
	}

	public CashierDeskDTO getCashierDeskDTO() {
		return cashierDeskDTO;
	}

	public void setCashierDeskDTO(CashierDeskDTO cashierDeskDTO) {
		this.cashierDeskDTO = cashierDeskDTO;
	}

	public String getFcUserid() {
		return fcUserid;
	}

	public void setFcUserid(String fcUserid) {
		this.fcUserid = fcUserid;
	}

	public String getBalanceusable() {
		return balanceusable;
	}

	public void setBalanceusable(String balanceusable) {
		this.balanceusable = balanceusable;
	}

	public List<CommonPayMethodDTO> getCommonPayMethodDTOList() {
		return commonPayMethodDTOList;
	}

	public void setCommonPayMethodDTOList(
			List<CommonPayMethodDTO> commonPayMethodDTOList) {
		this.commonPayMethodDTOList = commonPayMethodDTOList;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPaySource() {
		return paySource;
	}

	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}

	public String getIsEscrowed() {
		return isEscrowed;
	}

	public void setIsEscrowed(String isEscrowed) {
		this.isEscrowed = isEscrowed;
	}

	public String getEscrowedDate() {
		return escrowedDate;
	}

	public void setEscrowedDate(String escrowedDate) {
		this.escrowedDate = escrowedDate;
	}

	public String getTfsUserId() {
		return tfsUserId;
	}

	public void setTfsUserId(String tfsUserId) {
		this.tfsUserId = tfsUserId;
	}

	public String getRecieveOrgCode() {
		return recieveOrgCode;
	}

	public void setRecieveOrgCode(String recieveOrgCode) {
		this.recieveOrgCode = recieveOrgCode;
	}

	public boolean isResult() {
		return isResult;
	}

	public void setResult(boolean isResult) {
		this.isResult = isResult;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	public String getReturnPage() {
		return returnPage;
	}

	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(String currentTheme) {
		this.currentTheme = currentTheme;
	}

	public Map<String, FinancialOrganDTO> getUserIDOrgMap() {
		return userIDOrgMap;
	}

	public void setUserIDOrgMap(Map<String, FinancialOrganDTO> userIDOrgMap) {
		this.userIDOrgMap = userIDOrgMap;
	}

	public AccountHistoryDTO getAccountHistoryDTO() {
		return accountHistoryDTO;
	}

	public void setAccountHistoryDTO(AccountHistoryDTO accountHistoryDTO) {
		this.accountHistoryDTO = accountHistoryDTO;
	}

	public String getBusinessOrderCode() {
		return businessOrderCode;
	}

	public void setBusinessOrderCode(String businessOrderCode) {
		this.businessOrderCode = businessOrderCode;
	}

	public CashDeskData putError(String msg){
		this.isResult = false;
		this.returnCode="001";
		this.returnMsg = msg;
		this.returnPage="checkstand-pay/cashierDeskError";
		return this;
	}
	
	public CashDeskData putSuccess(){
		this.isResult = true;
		this.returnCode="000";
		this.returnMsg = "成功";
		return this;
	}
	
	
}
