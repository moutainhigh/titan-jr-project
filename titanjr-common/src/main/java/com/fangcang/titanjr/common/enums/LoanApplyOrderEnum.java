package com.fangcang.titanjr.common.enums;

public class LoanApplyOrderEnum {

//	'0 无效贷款  1 贷款申请中  2 待放款   3 已放款  4 放款失败  5 贷款失败  6 已逾期 7 已结清'
	public enum LoanOrderStatus{
		INVALID_APPLY(0,"无效申请"),LOAN_APPLYING(1,"贷款申请中"),LOAN_WAIT(2,"待放款"),LOAN_COMPLETED(3,"贷款完成"),
		LOAN_FAILED(4,"放款失败"),MAKE_LOAN_FAILED(5,"贷款失败"),REFUND_OVERFUE(6,"已逾期"),REFUND_COMPLETED(7,"已结清");
		
		public int status;
		
		public String msg;
		
		private LoanOrderStatus(int status,String msg){
			this.status = status;
			this.msg = msg;
		}

	}
	
	public enum ProductId{
		LOAN_PRODUCTID("P000230","信贷子账户产品ID"),MAIN_PRODUCTID("P000070","主账户产品ID"),MIDDEL_PRODUCTID("P000148","中间账户ID");
		
		public String productId;
		
		public String detail;
		
		private ProductId(String productId,String detail ){
			this.productId=productId;
			this.detail=detail;
		}
	}
	
}
