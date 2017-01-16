package com.fangcang.titanjr.rs.response;

import java.io.Serializable;

public class RsRefundResponse extends BaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//�˿�� ����ص��˿��
	private String refundOrderNo;

	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}
	
}
