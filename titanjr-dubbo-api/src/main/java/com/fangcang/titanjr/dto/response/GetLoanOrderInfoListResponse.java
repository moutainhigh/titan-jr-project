package com.fangcang.titanjr.dto.response;

import java.util.List;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;

public class GetLoanOrderInfoListResponse extends BaseResponseDTO {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private List<LoanApplyOrderBean> applyOrderInfo;

	private long totalCount;

	private int pageSize;

	private int currentPage;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<LoanApplyOrderBean> getApplyOrderInfo() {
		return applyOrderInfo;
	}

	public void setApplyOrderInfo(List<LoanApplyOrderBean> applyOrderInfo) {
		this.applyOrderInfo = applyOrderInfo;
	}
}
