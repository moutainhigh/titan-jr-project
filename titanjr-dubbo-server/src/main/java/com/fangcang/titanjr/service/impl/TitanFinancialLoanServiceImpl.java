package com.fangcang.titanjr.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.dto.bean.LoanOrderBean;
import com.fangcang.titanjr.dto.bean.LoanRoomPackSpecBean;
import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.request.CancelLoanRequest;
import com.fangcang.titanjr.dto.request.GetHistoryRepaymentListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.request.GetOrgLoanStatInfoRequest;
import com.fangcang.titanjr.dto.request.RepaymentLoanRequest;
import com.fangcang.titanjr.dto.request.SaveLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.dto.response.CancelLoanResponse;
import com.fangcang.titanjr.dto.response.GetHistoryRepaymentListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.GetOrgLoanStatInfoResponse;
import com.fangcang.titanjr.dto.response.RepaymentLoanResponse;
import com.fangcang.titanjr.dto.response.SaveLoanOrderInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;

public class TitanFinancialLoanServiceImpl implements TitanFinancialLoanService{
	
	private static final Log log = LogFactory
			.getLog(TitanFinancialLoanServiceImpl.class);

	@Override
	public ApplyLoanResponse applyLoan(ApplyLoanRequest req) {
		ApplyLoanResponse response = new ApplyLoanResponse();
		try{
			if(req ==null || req.getLcanSpec()==null ||req.getProductType()==null ){
				response.putErrorResult("参数错误");
				return response;
			}
			
			
			
			
			LoanProductEnum productType = req.getProductType();
			if(LoanProductEnum.ROOM_PACK.getCode()==productType.getCode()){
				LoanRoomPackSpecBean LoanSpecBean  = (LoanRoomPackSpecBean)req.getLcanSpec();
				//保存相关数据
				boolean flag = this.saveLoanRoomPackSpecBean(LoanSpecBean);
				if(!flag){
					response.putErrorResult("保存订单失败");
					return response;
				}
				
				
			}else{
				
			}
			
			
		}catch(Exception e){
			log.error("");
		}
		
		return null;
	}
	
	private boolean saveLoanRoomPackSpecBean(LoanRoomPackSpecBean LoanSpecBean){
		LoanRoomPackSpecBean loanRoomPackSpecBean = new LoanRoomPackSpecBean();
        	return false;
	}
	
	private LoanOrderBean convertTOLoanOrderBean(LoanRoomPackSpecBean LoanSpecBean,Integer type){
		LoanOrderBean loanOrderBean = new LoanOrderBean();
		return null;
	}
	
	private boolean addLoanOrder(LoanOrderBean loanOrderBean){
	
		return false;
	}
	

	@Override
	public CancelLoanResponse cancelLoan(CancelLoanRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepaymentLoanResponse repaymentLoan(RepaymentLoanRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetLoanOrderInfoResponse getLoanOrderInfo(GetLoanOrderInfoRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetLoanOrderInfoListResponse getLoanOrderInfoList(
			GetLoanOrderInfoListRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetOrgLoanStatInfoResponse getOrgLoanStatInfo(
			GetOrgLoanStatInfoRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetHistoryRepaymentListResponse getHistoryRepaymentList(
			GetHistoryRepaymentListRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaveLoanOrderInfoResponse saveLoanOrderInfo(
			SaveLoanOrderInfoRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
