package com.fangcang.titanjr.rs.response;

import com.Rop.api.domain.BorrowRepayment;
import com.fangcang.titanjr.rs.dto.TBorrowRepayment;
import com.fangcang.util.MyBeanUtil;

public class InterestRepaymentQueryborrowinfoResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7703759926079175516L;
	/**
	 * 应还款对象信息
	 */
	private TBorrowRepayment tBorrowRepayment;
	
	
	public TBorrowRepayment gettBorrowRepayment() {
		return tBorrowRepayment;
	}
	
	public void settBorrowRepayment(TBorrowRepayment tBorrowRepayment) {
		this.tBorrowRepayment = tBorrowRepayment;
	}
	/**
	 * 属性拷贝
	 * @param borrowRepayment
	 */
	public void settBorrowRepayment(BorrowRepayment borrowRepayment){
		if(tBorrowRepayment==null){
			tBorrowRepayment = new TBorrowRepayment();
		}
		MyBeanUtil.copyProperties(tBorrowRepayment, borrowRepayment);
	}
}
