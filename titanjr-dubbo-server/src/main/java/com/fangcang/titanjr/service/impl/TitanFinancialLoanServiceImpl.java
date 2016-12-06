package com.fangcang.titanjr.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.titanjr.common.enums.FileTypeEnum;
import com.fangcang.titanjr.common.enums.LoanApplyOrderEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FileHelp;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.dao.LoanRoomPackSpecDao;
import com.fangcang.titanjr.dto.bean.LoanRoomPackSpecBean;
import com.fangcang.titanjr.dto.bean.LoanSpecBean;
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
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.GetHistoryRepaymentListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.GetOrgLoanStatInfoResponse;
import com.fangcang.titanjr.dto.response.RepaymentLoanResponse;
import com.fangcang.titanjr.dto.response.SaveLoanOrderInfoResponse;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanRoomPackSpec;
import com.fangcang.titanjr.rs.dto.NewLoanApplyJsonData;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;
import com.fangcang.titanjr.rs.request.RSFsFileUploadRequest;
import com.fangcang.titanjr.rs.response.NewLoanApplyResponse;
import com.fangcang.titanjr.rs.response.RSFsFileUploadResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.util.StringUtil;

@Service("titanFinancialLoanService")
public class TitanFinancialLoanServiceImpl implements TitanFinancialLoanService{
	
	private static final Log log = LogFactory
			.getLog(TitanFinancialLoanServiceImpl.class);
	
	@Resource 
	private LoanOrderDao loanOrderDao;
	
	@Resource
	private LoanRoomPackSpecDao loanRoomPackSpecDao;
	
	@Resource
	private LoanCreditOrderDao loanCreditOrderDao;
	
	@Resource 
	private RSCreditManager rsCreditManager;
	
	@Resource
	private TitanSysconfigService titanSysconfigService;
	
	@Resource
	private RSFileManager rsFileManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ApplyLoanResponse applyLoan(ApplyLoanRequest req) throws Exception {
		ApplyLoanResponse response = new ApplyLoanResponse();
		try{
			if(req ==null || req.getLcanSpec()==null ||req.getProductType()==null ){
				response.putErrorResult("参数错误");
				return response;
			}
			String loanApplyOrderNo ="";
			String contactNames ="";
			LoanProductEnum productType = req.getProductType();
			if(LoanProductEnum.ROOM_PACK.getCode()==productType.getCode()){
				LoanRoomPackSpecBean LoanSpecBean  = (LoanRoomPackSpecBean)req.getLcanSpec();
				loanApplyOrderNo = LoanSpecBean.getLoanOrderNo();
				contactNames = LoanSpecBean.getContractUrl();
				//保存相关数据
				boolean flag = this.saveLoanRoomPackSpecBean(LoanSpecBean);
				if(!flag){
					log.error("保存包房贷订单失败");
					throw new Exception("保存包房贷订单失败");
				}
				
			}else {//运营贷
				
			}
			
			//保存贷款申请单
			boolean flag = saveLoanOrderBean(req.getLcanSpec(), productType.getCode(), req.getOrgCode());
			if(!flag){
				throw new Exception("保存订单失败");
			}
			
			//上传文件到融数，并且获取相应的urlKey
			String urlKey = getApplyLoanUrlKey(req.getOrgCode(),loanApplyOrderNo,1,contactNames);
			
			//申请贷款
			NewLoanApplyRequest request = this.convertToNewLoanApplyRequest(req.getLcanSpec(), productType.getCode(), req.getOrgCode());
			request.setUrlkey(urlKey);
			this.packLoanJSonData(request, req.getLcanSpec(), productType.getCode());
			NewLoanApplyResponse loanResponse = rsCreditManager.newLoanApply(request);
			if(!loanResponse.isSuccess()){
				log.error(loanResponse.getReturnCode()+":"+loanResponse.getReturnMsg());
				throw new Exception(loanResponse.getReturnCode()+":"+loanResponse.getReturnMsg());
			}
			
			//更新融数返回的贷款单号
			LoanApplyOrder loanOrder = new LoanApplyOrder();
			loanOrder.setOrderid(loanResponse.getOrderid());
			loanOrder.setOrderNo(request.getUserorderid());
			this.updateLoanOrderBean(loanOrder);
			
			
			response.putSuccess();
			return response;
			
		}catch(Exception e){
			log.error("贷款申请异常",e);
			throw e;
		}
	}
	/**
	 * 上传包房贷文件
	 * @param contactNames 文件名数组， 如：123.jpg,3333.jpg,4444.jpg
	 * @param loanApplyOrderNo 包房贷申请订单号
	 * @param Loantype  贷款类型：1-企业，2-个人
	 * @return
	 * @throws Exception 
	 */
	private String getApplyLoanUrlKey(String orgCode,String loanApplyOrderNo,Integer Loantype,String contactNames) throws Exception{
		if(!StringUtil.isValidString(contactNames)){
			throw new Exception("包房贷申请没有上传申请合同文件");
		}
		String orgLoanFileRootDir = TitanFinancialLoanServiceImpl.class.getClassLoader().getResource("").getPath()+"tmp"+FtpUtil.UPLOAD_PATH_LOAN_APPLY+"/"+orgCode+"/"+loanApplyOrderNo;
		String localFileDir;
		String localFather;
		if(Loantype==1){
			localFather = "EnterpriseLoanPackage";
			//企业
			localFileDir = "/"+localFather+"/DocumentInfo";
		}else{
			localFather = "PersonalLoanPackage";
			//个人
			localFileDir = "/"+localFather+"/PersonalDocumentInfo";
		}
		
		//清理环境
		FileHelp.deleteFile(orgLoanFileRootDir);
		//创建目录
		FtpUtil.makeLocalDirectory(orgLoanFileRootDir+localFileDir);
		//下载
		try {
			FTPConfigResponse ftpConfigResponse = titanSysconfigService.getFTPConfig();
			FtpUtil ftpUtil = new FtpUtil(ftpConfigResponse.getFtpServerIp(),ftpConfigResponse.getFtpServerPort(),ftpConfigResponse.getFtpServerUser(),ftpConfigResponse.getFtpServerPassword());
			ftpUtil.ftpLogin();
			String[] fileNames = contactNames.split(",");
			for(String file : fileNames){
				if(StringUtil.isValidString(file)){
					ftpUtil.downloadFile(file, orgLoanFileRootDir+localFileDir, FtpUtil.baseLocation+FtpUtil.UPLOAD_PATH_LOAN_APPLY+"/"+orgCode+"/"+loanApplyOrderNo);
				}
			}
			
			ftpUtil.ftpLogOut();
		} catch (Exception e) {
			log.error("下载ftp文件失败，路径："+FtpUtil.baseLocation+FtpUtil.UPLOAD_PATH_LOAN_APPLY+"/"+orgCode+"/"+loanApplyOrderNo+"，文件为："+contactNames,e);
			throw new Exception("文件下载失败", e);
		}
		
		File srcZipFile = FileHelp.zipFile(orgLoanFileRootDir+"/"+localFather,localFather+"_src.zip");
		long zipFileLength = srcZipFile.length()/1024;
		log.info("包房贷申请文件("+srcZipFile.getName()+")压缩后大小："+zipFileLength+" KB,包房订单号是[loanApplyOrderNo]:"+loanApplyOrderNo);
		//小于10K，则表示文件不存在或者下载失败
		if(zipFileLength<10){
			log.error("从ftp下载文件时，文件太小或者不存在，原文件路径srcZipFile："+srcZipFile.getAbsolutePath());
			throw new Exception("包房贷申请的文件下载失败或者文件太小");
		}
		//加密
		String encryptFilePath = orgLoanFileRootDir+"/"+localFather+".zip";
		try {
			FileHelp.encryptRSFile(srcZipFile, encryptFilePath);
		} catch (Exception e) {
			log.error("encryptRSFile，融数授信申请资料文件加密失败，原文件路径srcZipFile："+srcZipFile.getAbsolutePath(), e);
			throw new Exception("文件rsa加密失败", e);
		}
		
		//上传到融数
		RSFsFileUploadRequest rsFsFileUploadRequest = new RSFsFileUploadRequest();
		rsFsFileUploadRequest.setUserid(orgCode);
		rsFsFileUploadRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
		rsFsFileUploadRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		rsFsFileUploadRequest.setType(FileTypeEnum.UPLOAD_FILE_73.getFileType());
		rsFsFileUploadRequest.setInvoiceDate(com.fangcang.util.DateUtil.getCurrentDate());
		rsFsFileUploadRequest.setPath(encryptFilePath);
		rsFsFileUploadRequest.setBacth(orgCode+com.fangcang.util.DateUtil.getCurrentDate().getTime());
		
		RSFsFileUploadResponse rsFsFileUploadResponse = rsFileManager.fsFileUpload(rsFsFileUploadRequest);
		//上传失败
		if(rsFsFileUploadResponse.isSuccess()==false||(!StringUtil.isValidString(rsFsFileUploadResponse.getUrlKey()))){
			log.error("包房贷单号loanApplyOrderNo:"+loanApplyOrderNo+",上传包房贷压缩包文件到融数失败,"+Tools.gsonToString(rsFsFileUploadRequest));
			throw new Exception("文件上传失败,错误信息:"+rsFsFileUploadResponse.getReturnMsg());
		}
		
		FileHelp.deleteFile(orgLoanFileRootDir);
		return rsFsFileUploadResponse.getUrlKey();
	}
	
	private boolean saveLoanRoomPackSpecBean(LoanRoomPackSpecBean loanSpecBean) throws Exception{
		LoanRoomPackSpec loanRoomPackSpec = new LoanRoomPackSpec();
		try{
			loanRoomPackSpec.setAccount(loanSpecBean.getAccount());
			loanRoomPackSpec.setAccountName(loanSpecBean.getAccountName());
			loanRoomPackSpec.setBank(loanSpecBean.getBank());
			loanRoomPackSpec.setBeginDate(loanSpecBean.getBeginDate());
			loanRoomPackSpec.setEndDate(loanSpecBean.getEndDate());
			loanRoomPackSpec.setContractUrl(loanSpecBean.getContractUrl());
			loanRoomPackSpec.setHotelName(loanSpecBean.getHotleName());
			loanRoomPackSpec.setRoomNights(loanSpecBean.getRoomNights());
			loanRoomPackSpec.setOrderNo(loanSpecBean.getLoanOrderNo());
			int row = loanRoomPackSpecDao.saveLoanRoomPackSpec(loanRoomPackSpec);
			if(row>0){
				return true;
			}
			throw new Exception("保存包房单失效");
		}catch(Exception e){
			log.error("保存包房贷信息失败",e);
			throw e;
		}
	}
	
	private void updateLoanOrderBean(LoanApplyOrder loanApplyOrder){
		try{
			loanOrderDao.updateLoanApplyOrder(loanApplyOrder);
		}catch(Exception e){
			log.error("保存融数返回贷款单号失败："+loanApplyOrder.getOrderid()+":"+e);
		}
		
	}
	
	
	private boolean saveLoanOrderBean(LoanSpecBean loanSpecBean,Integer type,String orgCode) throws Exception{
		LoanApplyOrder loanApplyOrder = new LoanApplyOrder();
		try{
			
			LoanRoomPackSpecBean loanRoomPackSpecBean  = null;
			
			LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
			loanCreditOrder.setOrgCode(orgCode);
			List<LoanCreditOrder> loanCreditOrderList = loanCreditOrderDao.queryLoanCreditOrder(loanCreditOrder);
			if(loanCreditOrderList.size()!=1){
				throw new Exception("查询授信申请单失败");
			}
			loanCreditOrder =  loanCreditOrderList.get(0);
			if(LoanProductEnum.ROOM_PACK.getCode()==type.intValue()){
				loanRoomPackSpecBean = (LoanRoomPackSpecBean)loanSpecBean;
				loanApplyOrder.setCreditOrderNo(loanCreditOrder.getOrderNo());
				loanApplyOrder.setOrderNo(loanRoomPackSpecBean.getLoanOrderNo());
				if(StringUtil.isValidString(loanRoomPackSpecBean.getAmount())){
					loanApplyOrder.setAmount(Long.parseLong(loanRoomPackSpecBean.getAmount()));
				}
			}else if(LoanProductEnum.OPERACTION.getCode()==type.intValue()){//运营贷
				
			}
			
			loanApplyOrder.setCreateTime(new Date());
			loanApplyOrder.setOrgCode(orgCode);
			loanApplyOrder.setProductId(LoanApplyOrderEnum.ProductId.LOAN_PRODUCTID.productId);
			loanApplyOrder.setRateTmp(CommonConstant.RATE_TEMPLETE);
			loanApplyOrder.setProductType(type);
			loanApplyOrder.setRsorgId(CommonConstant.RS_FANGCANG_CONST_ID);
			loanApplyOrder.setRspId(LoanApplyOrderEnum.ProductId.LOAN_PRODUCTID.productId);
			loanApplyOrder.setStatus(LoanApplyOrderEnum.LoanOrderStatus.LOAN_APPLYING.status);
			
			int row = loanOrderDao.saveLoanApplyOrder(loanApplyOrder);
			if(row >0){
				return true;
			}
			throw new Exception("查询授信申请单失败");
		}catch(Exception e){
			log.error("保存包房贷订单失效");
			throw e;
		}
	}
	
	
	private NewLoanApplyRequest convertToNewLoanApplyRequest(LoanSpecBean loanSpecBean,Integer type,String orgCode) throws Exception{
		try{
			NewLoanApplyRequest request = new NewLoanApplyRequest();
			LoanRoomPackSpecBean loanRoomPackSpecBean  = null;
			if(LoanProductEnum.ROOM_PACK.getCode()==type.intValue()){
				loanRoomPackSpecBean = (LoanRoomPackSpecBean)loanSpecBean;
				request.setAmount(loanRoomPackSpecBean.getAmount());
				request.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
				request.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
				request.setRatetemplate(CommonConstant.RATE_TEMPLETE);
				request.setReqesttime("");
				request.setRequestdate(DateUtil.sdf4.format(new Date()));
				request.setUserid(orgCode);
				request.setUserorderid(loanRoomPackSpecBean.getLoanOrderNo());
				//暂时还未确定TODO
				request.setUserrelateid("TJM10000110");
				request.setUsername("罗庆龙");
			}else if(LoanProductEnum.OPERACTION.getCode()==type.intValue()){
				
			}
			return request;
		}catch(Exception e){
			log.error("申请贷款失败");
			throw e;
		}
	}

	private void packLoanJSonData(NewLoanApplyRequest request,LoanSpecBean loanSpecBean,Integer type){
		NewLoanApplyJsonData moneyCreditJsonData = new NewLoanApplyJsonData();
		LoanRoomPackSpecBean loanRoomPackSpecBean  = null;
		if(LoanProductEnum.ROOM_PACK.getCode()==type.intValue()){
			loanRoomPackSpecBean = (LoanRoomPackSpecBean)loanSpecBean;
		}
    	moneyCreditJsonData.setLoanApplicateName(loanRoomPackSpecBean.getAccountName());
    	moneyCreditJsonData.setInParty(request.getUsername());
    	moneyCreditJsonData.setUserOrderId(request.getUserorderid());
    	moneyCreditJsonData.setOrderTime(request.getReqesttime());
    	moneyCreditJsonData.setProductName(loanRoomPackSpecBean.getHotleName());
    	moneyCreditJsonData.setInBankAccount(loanRoomPackSpecBean.getBank());
    	moneyCreditJsonData.setInBankAccountNo(loanRoomPackSpecBean.getAccount());
    	moneyCreditJsonData.setDeliveryStatus("");
    	moneyCreditJsonData.setReceivingState("");
    	moneyCreditJsonData.setReceiptAddress("深圳市宝安区");
    	moneyCreditJsonData.setCode("10000000000009");
    	moneyCreditJsonData.setUnitPrice("");
    	moneyCreditJsonData.setNumber(loanRoomPackSpecBean.getRoomNights()+"");
    	moneyCreditJsonData.setOrderAmount(loanRoomPackSpecBean.getAmount());
    	moneyCreditJsonData.setOrderType("");
    	moneyCreditJsonData.setRootInstCd(request.getRootinstcd());
    	moneyCreditJsonData.setLoanTerm("60");
    	JSON result = JSONSerializer.toJSON(moneyCreditJsonData);
    	request.setJsondata(result.toString());
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
