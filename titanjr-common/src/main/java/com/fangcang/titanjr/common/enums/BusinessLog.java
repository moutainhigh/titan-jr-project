package com.fangcang.titanjr.common.enums;

/**
 * 业务跟踪日志
 * @author luoqinglong
 * @date   2017年4月13日
 */
public class BusinessLog {
	/**
	 * 业主类型
	 * @author luoqinglong
	 * @date   2017年4月17日
	 */
	public enum BusinessType{
		Rechange("1","充值"),Pay("2","支付"),Withdraw("3","提现"),Refund("4","退款");
		
		private String type;
		private String des;
		
		private BusinessType(String type,String des){
			this.type = type;
			this.des = des;
		}

		public String getType() {
			return type;
		}

		public String getDes() {
			return des;
		}
		
	}
	
	/**
	 * 充值
	 */
	public enum RechangeStep{
		saveTitanTransOrder(BusinessType.Rechange,"保存订单");
		private BusinessType businessType;
		private String info;
		
		private RechangeStep(BusinessType businessType,String info){
			this.businessType = businessType;
			this.info = info;
		}

		public BusinessType getBusinessType() {
			return businessType;
		}

		public String getInfo() {
			return info;
		}
	}
	/**
	 * 提现
	 */
	public enum WithdrawStep{
		saveTitanTransOrder(BusinessType.Withdraw,"保存订单");
		private BusinessType businessType;
		private String info;
		
		private WithdrawStep(BusinessType businessType,String info){
			this.businessType = businessType;
			this.info = info;
		}

		public BusinessType getBusinessType() {
			return businessType;
		}

		public String getInfo() {
			return info;
		}
	}
	
	/**
	 * 支付
	 */
	public enum PayStep{
		SaveTitanTransOrder("保存TransOrder订单"),
		EnterCashierDesk("进入收银台"),
		QueryCashierDeskData("查询收银台数据"),
		ShowCashierDesk("显示收银台"),
		BeginPackageRechargeData("封装支付参数"),
		CreateRsOrder("融数落单"),
		WechatpayStep("进行微信支付"),
		CyberBankStep("进行网银支付"),
		PayConfirmPage("网关支付回调页面"),
		CallbackNotify("支付后,融数通知泰坦金融"),
		TransferSucc("融数转账成功"),
		FreezeSucc("融数冻结成功"),
		UnfreezeSucc("融数解冻成功");
		
		private BusinessType businessType = BusinessType.Pay;
		private String info;
		
		private PayStep(String info){
			this.info = info;
		}

		public BusinessType getBusinessType() {
			return businessType;
		}

		public String getInfo() {
			return info;
		}
		
		
	}
	
	
	
	/**
	 * 退款
	 */
	public enum RefundStep{
		saveTitanTransOrder(BusinessType.Refund,"保存支付订单");
		private BusinessType businessType;
		private String info;
		
		private RefundStep(BusinessType businessType,String info){
			this.businessType = businessType;
			this.info = info;
		}

		public BusinessType getBusinessType() {
			return businessType;
		}

		public String getInfo() {
			return info;
		}
		
	}
	
}
