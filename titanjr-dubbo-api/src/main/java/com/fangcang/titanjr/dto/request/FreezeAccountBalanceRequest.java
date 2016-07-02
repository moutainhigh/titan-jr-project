package com.fangcang.titanjr.dto.request;

import java.util.Date;

import com.fangcang.titanjr.common.enums.FreezeConditionCodeEnum;
import com.fangcang.titanjr.dto.BaseRequestDTO;

public class FreezeAccountBalanceRequest extends BaseRequestDTO {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单关联类型（主业务单：1 业务子单：0 无业务单：2）
    private FreezeConditionCodeEnum conditioncode;
    //冻结金额
    private Long amount;
    //功能编码：冻结返回授权码 为：40171
    private String funccode;
    //商户编码/机构号
    private String merchantcode;
    //订单金额
    private Long orderamount;
    //状态 1：正常
    private Integer status;
    //用户ip地址
    private String useripaddress;
    //请求单号
    private String requestno;
    //订单号
    private String orderno;
    //订单数量
    private Integer ordercount;
    //用户手续费
    private Long userfee;
    //二级机构标识
    private String userid;
    
    private String productid;
    

    //非必输
    //中间商户编码
    private String intermerchantcode;
    //银行联行编码
    private String bankcode;
    //业务类型ID
    private String busitypeid;
    //错误编码
    private String errorcode;
    //错误信息
    private String errormsg;
    //手续费金额
    private Long feeamount;
    //订单日期 格式 yyyy-MM-dd hh:mm:ss
    private Date orderdate;
    //交易请求时间 格式 yyyy-MM-dd hh:mm:ss
    private Date requesttime;
    //订单包号
    private String orderpackageno;
    //支付渠道ID
    private String paychannelid;
    //利润
    private Long profit;
     
    private String tradeflowno;
    //备注
    private String remark;
    
    
    public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public FreezeConditionCodeEnum getConditioncode() {
		return conditioncode;
	}
	public void setConditioncode(FreezeConditionCodeEnum conditioncode) {
		this.conditioncode = conditioncode;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getFunccode() {
		return funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}
	public String getMerchantcode() {
		return merchantcode;
	}
	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}
	public Long getOrderamount() {
		return orderamount;
	}
	public void setOrderamount(Long orderamount) {
		this.orderamount = orderamount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUseripaddress() {
		return useripaddress;
	}
	public void setUseripaddress(String useripaddress) {
		this.useripaddress = useripaddress;
	}
	public String getRequestno() {
		return requestno;
	}
	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Integer getOrdercount() {
		return ordercount;
	}
	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}
	public Long getUserfee() {
		return userfee;
	}
	public void setUserfee(Long userfee) {
		this.userfee = userfee;
	}
	public String getIntermerchantcode() {
		return intermerchantcode;
	}
	public void setIntermerchantcode(String intermerchantcode) {
		this.intermerchantcode = intermerchantcode;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBusitypeid() {
		return busitypeid;
	}
	public void setBusitypeid(String busitypeid) {
		this.busitypeid = busitypeid;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public Long getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(Long feeamount) {
		this.feeamount = feeamount;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public Date getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}
	public String getOrderpackageno() {
		return orderpackageno;
	}
	public void setOrderpackageno(String orderpackageno) {
		this.orderpackageno = orderpackageno;
	}
	public String getPaychannelid() {
		return paychannelid;
	}
	public void setPaychannelid(String paychannelid) {
		this.paychannelid = paychannelid;
	}
	public Long getProfit() {
		return profit;
	}
	public void setProfit(Long profit) {
		this.profit = profit;
	}
	public String getTradeflowno() {
		return tradeflowno;
	}
	public void setTradeflowno(String tradeflowno) {
		this.tradeflowno = tradeflowno;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
