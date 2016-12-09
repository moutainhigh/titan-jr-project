package com.fangcang.titanjr.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.FileTypeEnum;
import com.fangcang.titanjr.common.enums.LoanApplyOrderEnum;
import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FileHelp;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.dao.LoanRoomPackSpecDao;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;
import com.fangcang.titanjr.dto.bean.LoanRepaymentBean;
import com.fangcang.titanjr.dto.bean.LoanRoomPackSpecBean;
import com.fangcang.titanjr.dto.bean.LoanSpecBean;
import com.fangcang.titanjr.dto.bean.OrgLoanStatInfo;
import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.request.CancelLoanRequest;
import com.fangcang.titanjr.dto.request.GetHistoryRepaymentListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.request.GetOrgLoanStatInfoRequest;
import com.fangcang.titanjr.dto.request.RepaymentAmountComputeRequest;
import com.fangcang.titanjr.dto.request.RepaymentLoanRequest;
import com.fangcang.titanjr.dto.request.SaveLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.dto.response.CancelLoanResponse;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.GetHistoryRepaymentListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.GetOrgLoanStatInfoResponse;
import com.fangcang.titanjr.dto.response.RepaymentAmountComputeResponse;
import com.fangcang.titanjr.dto.response.RepaymentLoanResponse;
import com.fangcang.titanjr.dto.response.SaveLoanOrderInfoResponse;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanExpiryStat;
import com.fangcang.titanjr.entity.LoanProductAmountStat;
import com.fangcang.titanjr.entity.LoanRoomPackSpec;
import com.fangcang.titanjr.entity.LoanSevenDaysStat;
import com.fangcang.titanjr.entity.parameter.LoanQueryConditions;
import com.fangcang.titanjr.rs.dto.NewLoanApplyJsonData;
import com.fangcang.titanjr.rs.dto.TBorrowRepayment;
import com.fangcang.titanjr.rs.dto.TUserArepayment;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;
import com.fangcang.titanjr.rs.request.QueryBorrowinfoRequest;
import com.fangcang.titanjr.rs.request.QueryLoanApplyRequest;
import com.fangcang.titanjr.rs.request.QueryUserInitiativeRepaymentRequest;
import com.fangcang.titanjr.rs.request.RSFsFileUploadRequest;
import com.fangcang.titanjr.rs.request.UserInitiativeRepamentRequest;
import com.fangcang.titanjr.rs.response.NewLoanApplyResponse;
import com.fangcang.titanjr.rs.response.QueryBorrowinfoResponse;
import com.fangcang.titanjr.rs.response.QueryLoanApplyResponse;
import com.fangcang.titanjr.rs.response.QueryUserInitiativeRepaymentResponse;
import com.fangcang.titanjr.rs.response.RSFsFileUploadResponse;
import com.fangcang.titanjr.rs.response.UserInitiativeRepamentResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.titanjr.util.LoanTypeConvertUtil;
import com.fangcang.util.StringUtil;

@Service("titanFinancialLoanService")
public class TitanFinancialLoanServiceImpl implements TitanFinancialLoanService {

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
		try {
			if (req == null || req.getLcanSpec() == null
					|| req.getProductType() == null) {
				response.putErrorResult("参数错误");
				return response;
			}
			String loanApplyOrderNo = "";
			String contactNames = "";
			LoanProductEnum productType = req.getProductType();
			if (LoanProductEnum.ROOM_PACK.getCode() == productType.getCode()) {
				LoanRoomPackSpecBean LoanSpecBean = (LoanRoomPackSpecBean) req
						.getLcanSpec();
				loanApplyOrderNo = LoanSpecBean.getLoanOrderNo();
				contactNames = LoanSpecBean.getContractUrl();
				// 保存相关数据
				boolean flag = this.saveLoanRoomPackSpecBean(LoanSpecBean);
				if (!flag) {
					log.error("保存包房贷订单失败");
					throw new Exception("保存包房贷订单失败");
				}

			} else {// 运营贷

			}

			// 保存贷款申请单
			boolean flag = saveLoanOrderBean(req.getLcanSpec(),
					productType.getCode(), req.getOrgCode());
			if (!flag) {
				throw new Exception("保存订单失败");
			}

			// 上传文件到融数，并且获取相应的urlKey
			String urlKey = getApplyLoanUrlKey(req.getOrgCode(),
					loanApplyOrderNo, 1, contactNames);

			// 申请贷款
			NewLoanApplyRequest request = this.convertToNewLoanApplyRequest(
					req.getLcanSpec(), productType.getCode(), req.getOrgCode());
			request.setUrlkey(urlKey);
			this.packLoanJSonData(request, req.getLcanSpec(),
					productType.getCode());
			NewLoanApplyResponse loanResponse = rsCreditManager
					.newLoanApply(request);
			if (!loanResponse.isSuccess()) {
				log.error(loanResponse.getReturnCode() + ":"
						+ loanResponse.getReturnMsg());
				throw new Exception(loanResponse.getReturnCode() + ":"
						+ loanResponse.getReturnMsg());
			}

			// 更新融数返回的贷款单号
			LoanApplyOrder loanOrder = new LoanApplyOrder();
			loanOrder.setOrderid(loanResponse.getOrderid());
			loanOrder.setOrderNo(request.getUserorderid());
			this.updateLoanOrderBean(loanOrder);

			response.putSuccess();
			return response;

		} catch (Exception e) {
			log.error("贷款申请异常", e);
			throw e;
		}
	}

	/**
	 * 上传包房贷文件
	 * 
	 * @param contactNames
	 *            文件名数组， 如：123.jpg,3333.jpg,4444.jpg
	 * @param loanApplyOrderNo
	 *            包房贷申请订单号
	 * @param Loantype
	 *            贷款类型：1-企业，2-个人
	 * @return
	 * @throws Exception
	 */
	private String getApplyLoanUrlKey(String orgCode, String loanApplyOrderNo,
			Integer Loantype, String contactNames) throws Exception {
		if (!StringUtil.isValidString(contactNames)) {
			throw new Exception("包房贷申请没有上传申请合同文件");
		}
		String orgLoanFileRootDir = TitanFinancialLoanServiceImpl.class
				.getClassLoader().getResource("").getPath()
				+ "tmp"
				+ FtpUtil.UPLOAD_PATH_LOAN_APPLY
				+ "/"
				+ orgCode
				+ "/"
				+ loanApplyOrderNo;
		String localFileDir;
		String localFather;
		if (Loantype == 1) {
			localFather = "EnterpriseLoanPackage";
			// 企业
			localFileDir = "/" + localFather + "/DocumentInfo";
		} else {
			localFather = "PersonalLoanPackage";
			// 个人
			localFileDir = "/" + localFather + "/PersonalDocumentInfo";
		}

		// 清理环境
		FileHelp.deleteFile(orgLoanFileRootDir);
		// 创建目录
		FtpUtil.makeLocalDirectory(orgLoanFileRootDir + localFileDir);
		// 下载
		try {
			FTPConfigResponse ftpConfigResponse = titanSysconfigService
					.getFTPConfig();
			FtpUtil ftpUtil = new FtpUtil(ftpConfigResponse.getFtpServerIp(),
					ftpConfigResponse.getFtpServerPort(),
					ftpConfigResponse.getFtpServerUser(),
					ftpConfigResponse.getFtpServerPassword());
			ftpUtil.ftpLogin();
			String[] fileNames = contactNames.split(",");
			for (String file : fileNames) {
				if (StringUtil.isValidString(file)) {
					ftpUtil.downloadFile(file, orgLoanFileRootDir
							+ localFileDir, FtpUtil.baseLocation
							+ FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/" + orgCode
							+ "/" + loanApplyOrderNo);
				}
			}

			ftpUtil.ftpLogOut();
		} catch (Exception e) {
			log.error("下载ftp文件失败，路径：" + FtpUtil.baseLocation
					+ FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/" + orgCode + "/"
					+ loanApplyOrderNo + "，文件为：" + contactNames, e);
			throw new Exception("文件下载失败", e);
		}

		File srcZipFile = FileHelp.zipFile(orgLoanFileRootDir + "/"
				+ localFather, localFather + "_src.zip");
		long zipFileLength = srcZipFile.length() / 1024;
		log.info("包房贷申请文件(" + srcZipFile.getName() + ")压缩后大小：" + zipFileLength
				+ " KB,包房订单号是[loanApplyOrderNo]:" + loanApplyOrderNo);
		// 小于10K，则表示文件不存在或者下载失败
		if (zipFileLength < 10) {
			log.error("从ftp下载文件时，文件太小或者不存在，原文件路径srcZipFile："
					+ srcZipFile.getAbsolutePath());
			throw new Exception("包房贷申请的文件下载失败或者文件太小");
		}
		// 加密
		String encryptFilePath = orgLoanFileRootDir + "/" + localFather
				+ ".zip";
		try {
			FileHelp.encryptRSFile(srcZipFile, encryptFilePath);
		} catch (Exception e) {
			log.error("encryptRSFile，融数授信申请资料文件加密失败，原文件路径srcZipFile："
					+ srcZipFile.getAbsolutePath(), e);
			throw new Exception("文件rsa加密失败", e);
		}

		// 上传到融数
		RSFsFileUploadRequest rsFsFileUploadRequest = new RSFsFileUploadRequest();
		rsFsFileUploadRequest.setUserid(orgCode);
		rsFsFileUploadRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
		rsFsFileUploadRequest
				.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		rsFsFileUploadRequest
				.setType(FileTypeEnum.UPLOAD_FILE_73.getFileType());
		rsFsFileUploadRequest.setInvoiceDate(com.fangcang.util.DateUtil
				.getCurrentDate());
		rsFsFileUploadRequest.setPath(encryptFilePath);
		rsFsFileUploadRequest.setBacth(orgCode
				+ com.fangcang.util.DateUtil.getCurrentDate().getTime());

		RSFsFileUploadResponse rsFsFileUploadResponse = rsFileManager
				.fsFileUpload(rsFsFileUploadRequest);
		// 上传失败
		if (rsFsFileUploadResponse.isSuccess() == false
				|| (!StringUtil.isValidString(rsFsFileUploadResponse
						.getUrlKey()))) {
			log.error("包房贷单号loanApplyOrderNo:" + loanApplyOrderNo
					+ ",上传包房贷压缩包文件到融数失败,"
					+ Tools.gsonToString(rsFsFileUploadRequest));
			throw new Exception("文件上传失败,错误信息:"
					+ rsFsFileUploadResponse.getReturnMsg());
		}

		FileHelp.deleteFile(orgLoanFileRootDir);
		return rsFsFileUploadResponse.getUrlKey();
	}

	private boolean saveLoanRoomPackSpecBean(LoanRoomPackSpecBean loanSpecBean)
			throws Exception {
		LoanRoomPackSpec loanRoomPackSpec = new LoanRoomPackSpec();
		try {
			loanRoomPackSpec.setAccount(loanSpecBean.getAccount());
			loanRoomPackSpec.setAccountName(loanSpecBean.getAccountName());
			loanRoomPackSpec.setBank(loanSpecBean.getBank());
			loanRoomPackSpec.setBeginDate(loanSpecBean.getBeginDate());
			loanRoomPackSpec.setEndDate(loanSpecBean.getEndDate());
			loanRoomPackSpec.setContractUrl(loanSpecBean.getContractUrl());
			loanRoomPackSpec.setHotelName(loanSpecBean.getHotleName());
			loanRoomPackSpec.setRoomNights(loanSpecBean.getRoomNights());
			loanRoomPackSpec.setOrderNo(loanSpecBean.getLoanOrderNo());
			int row = loanRoomPackSpecDao
					.saveLoanRoomPackSpec(loanRoomPackSpec);
			if (row > 0) {
				return true;
			}
			throw new Exception("保存包房单失效");
		} catch (Exception e) {
			log.error("保存包房贷信息失败", e);
			throw e;
		}
	}

	private void updateLoanOrderBean(LoanApplyOrder loanApplyOrder) {
		try {
			loanOrderDao.updateLoanApplyOrder(loanApplyOrder);
		} catch (Exception e) {
			log.error("保存融数返回贷款单号失败：" + loanApplyOrder.getOrderid() + ":" + e);
		}

	}

	private boolean saveLoanOrderBean(LoanSpecBean loanSpecBean, Integer type,
			String orgCode) throws Exception {
		LoanApplyOrder loanApplyOrder = new LoanApplyOrder();
		try {

			LoanRoomPackSpecBean loanRoomPackSpecBean = null;

			LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
			loanCreditOrder.setOrgCode(orgCode);
			List<LoanCreditOrder> loanCreditOrderList = loanCreditOrderDao
					.queryLoanCreditOrder(loanCreditOrder);
			if (loanCreditOrderList.size() != 1) {
				throw new Exception("查询授信申请单失败");
			}
			loanCreditOrder = loanCreditOrderList.get(0);
			if (LoanProductEnum.ROOM_PACK.getCode() == type.intValue()) {
				loanRoomPackSpecBean = (LoanRoomPackSpecBean) loanSpecBean;
				loanApplyOrder.setCreditOrderNo(loanCreditOrder.getOrderNo());
				loanApplyOrder
						.setOrderNo(loanRoomPackSpecBean.getLoanOrderNo());
				if (StringUtil.isValidString(loanRoomPackSpecBean.getAmount())) {
					loanApplyOrder.setAmount(Long
							.parseLong(loanRoomPackSpecBean.getAmount()));
				}
			} else if (LoanProductEnum.OPERACTION.getCode() == type.intValue()) {// 运营贷

			}

			loanApplyOrder.setCreateTime(new Date());
			loanApplyOrder.setOrgCode(orgCode);
			loanApplyOrder.setProductId(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
			loanApplyOrder.setRateTmp(CommonConstant.RATE_TEMPLETE);
			loanApplyOrder.setProductType(type);
			loanApplyOrder.setRsorgId(orgCode);
			loanApplyOrder.setRspId(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
			loanApplyOrder.setStatus(LoanApplyOrderEnum.LoanOrderStatus.LOAN_APPLYING.status);
			int row = loanOrderDao.saveLoanApplyOrder(loanApplyOrder);
			if (row > 0) {
				return true;
			}
			throw new Exception("查询授信申请单失败");
		} catch (Exception e) {
			log.error("保存包房贷订单失效");
			throw e;
		}
	}

	private NewLoanApplyRequest convertToNewLoanApplyRequest(
			LoanSpecBean loanSpecBean, Integer type, String orgCode)
			throws Exception {
		try {
			NewLoanApplyRequest request = new NewLoanApplyRequest();
			LoanRoomPackSpecBean loanRoomPackSpecBean = null;
			if (LoanProductEnum.ROOM_PACK.getCode() == type.intValue()) {
				loanRoomPackSpecBean = (LoanRoomPackSpecBean) loanSpecBean;
				request.setAmount(loanRoomPackSpecBean.getAmount());
				request.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
				request.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
				request.setRatetemplate(CommonConstant.RATE_TEMPLETE);
				request.setReqesttime("");
				request.setRequestdate(DateUtil.sdf4.format(new Date()));
				request.setUserid(orgCode);
				request.setUserorderid(loanRoomPackSpecBean.getLoanOrderNo());
				// 暂时还未确定TODO
				request.setUserrelateid("TJM10000110");
				request.setUsername("罗庆龙");
			} else if (LoanProductEnum.OPERACTION.getCode() == type.intValue()) {

			}
			return request;
		} catch (Exception e) {
			log.error("申请贷款失败");
			throw e;
		}
	}

	private void packLoanJSonData(NewLoanApplyRequest request,
			LoanSpecBean loanSpecBean, Integer type) {
		NewLoanApplyJsonData moneyCreditJsonData = new NewLoanApplyJsonData();
		LoanRoomPackSpecBean loanRoomPackSpecBean = null;
		if (LoanProductEnum.ROOM_PACK.getCode() == type.intValue()) {
			loanRoomPackSpecBean = (LoanRoomPackSpecBean) loanSpecBean;
		}
		moneyCreditJsonData.setLoanApplicateName(loanRoomPackSpecBean
				.getAccountName());
		moneyCreditJsonData.setInParty(request.getUsername());
		moneyCreditJsonData.setUserOrderId(request.getUserorderid());
		moneyCreditJsonData.setOrderTime(request.getReqesttime());
		moneyCreditJsonData.setProductName(loanRoomPackSpecBean.getHotleName());
		moneyCreditJsonData.setInBankAccount(loanRoomPackSpecBean.getBank());
		moneyCreditJsonData.setInBankAccountNo(loanRoomPackSpecBean
				.getAccount());
		moneyCreditJsonData.setDeliveryStatus("");
		moneyCreditJsonData.setReceivingState("");
		moneyCreditJsonData.setReceiptAddress("深圳市宝安区");
		moneyCreditJsonData.setCode("10000000000009");
		moneyCreditJsonData.setUnitPrice("");
		moneyCreditJsonData
				.setNumber(loanRoomPackSpecBean.getRoomNights() + "");
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

	private LoanApplyOrder getBaseLoanApplyInfo(String orderNo, String orgCode) {

		LoanQueryConditions conditions = new LoanQueryConditions();
		conditions.setOrderNo(orderNo);
		conditions.setOrgCode(orgCode);

		PaginationSupport<LoanApplyOrder> paginationSupport = new PaginationSupport<LoanApplyOrder>();
		paginationSupport = loanOrderDao.queryLoanApplyOrder(conditions,
				paginationSupport);

		for (LoanApplyOrder tempApplyOrder : paginationSupport.getItemList()) {
			return tempApplyOrder;
		}

		return null;
	}

	public void synLoanOrderInfo(String orderNo, String orgCode) {

		LoanApplyOrder loanApplyOrder = getBaseLoanApplyInfo(orderNo, orgCode);

		if (loanApplyOrder == null) {
			log.error("query loan apply order is null  [ orderNo = " + orgCode
					+ "  orgCode=" + orderNo + "]");
			return;
		}

		QueryLoanApplyRequest request = new QueryLoanApplyRequest();
		request.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		request.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
		request.setUserorderid(request.getUserorderid());
		request.setUserid(orgCode);

		QueryLoanApplyResponse rsp = rsCreditManager.queryLoanApply(request);

		if (!rsp.isSuccess()) {

			log.error("query Loan Apply info fail.  [ orderNo = " + orderNo
					+ "  orgCode=" + orgCode + "]");
			return;
		}
		
		QueryBorrowinfoRequest bReq = new QueryBorrowinfoRequest();
		bReq.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		bReq.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
		bReq.setUserid(orgCode);
		bReq.setUserorderid(loanApplyOrder.getOrderid());

		QueryBorrowinfoResponse brsp = rsCreditManager.queryBorrowinfo(bReq);

		if (!brsp.isSuccess() || brsp.gettBorrowRepayment() == null) {

			log.error("query borrow info fail.  [ orderNo = " + orderNo
					+ "  orgCode=" + orgCode + "]");
			return;
		}
		
		log.info("query loan apply result " + JsonConversionTool.toJson(rsp));
		log.info("query borrow info result " + JsonConversionTool.toJson(brsp));

		
		// 融数方指定的特殊状态位，表示状态已经结束
		if ("贷款已结束".equals(rsp.getStatustring())) {
			loanApplyOrder.setStatus(LoanOrderStatusEnum.LOAN_FINISH.getKey());
		}
		
//		/**
//		 * 放款金额
//		 */
//		private String loanmoney;
//		/**
//		 * 	放款日期
//		 */
//		private String loandate;
		
		//:yyyy-MM-dd HH:mm:ss 终审通过时间
		if (StringUtil.isValidString(rsp.getLastappealtime())) {
			
		}
	}

	/**
	 * 對還款的金額進行預分析
	 */
	public RepaymentAmountComputeResponse repaymentAmountCompute(
			RepaymentAmountComputeRequest req) {

		RepaymentAmountComputeResponse loanResponse = new RepaymentAmountComputeResponse();

		if (req == null || !StringUtil.isValidString(req.getOrderNo())
				|| !StringUtil.isValidString(req.getOrgCode())) {
			log.error("request param is null [ orderNo = " + req.getOrderNo()
					+ "  orgCode=" + req.getOrgCode() + "]");
			loanResponse.putParamError();
			return loanResponse;
		}

		LoanApplyOrder loanApplyOrder = getBaseLoanApplyInfo(req.getOrderNo(),
				req.getOrgCode());

		if (loanApplyOrder == null) {
			log.error("query loan apply order is null  [ orderNo = "
					+ req.getOrderNo() + "  orgCode=" + req.getOrgCode() + "]");
			loanResponse.putSysError();
			return loanResponse;
		}

		QueryBorrowinfoRequest bReq = new QueryBorrowinfoRequest();
		bReq.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		bReq.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
		bReq.setUserid(req.getOrgCode());
		bReq.setUserorderid(loanApplyOrder.getOrderid());

		QueryBorrowinfoResponse brsp = rsCreditManager.queryBorrowinfo(bReq);

		if (!brsp.isSuccess() || brsp.gettBorrowRepayment() == null) {

			log.error("query borrow info fail.  [ orderNo = "
					+ req.getOrderNo() + "  orgCode=" + req.getOrgCode() + "]");
			loanResponse.putSysError();
			return loanResponse;
		}

		BigDecimal amount = new BigDecimal(req.getAmount());

		TBorrowRepayment repayment = brsp.gettBorrowRepayment();
		loanResponse.setRealoverdueinterestAmount(amountCompute(amount,
				new BigDecimal(repayment.getUseroverdueinterest())));// 逾期利息还款金额
		loanResponse.setRealoverduecapitalAmount(amountCompute(amount,
				new BigDecimal(repayment.getUseroverduefine())));// 逾期本金还款金额
		loanResponse.setRealinterestAmount(amountCompute(amount,
				new BigDecimal(repayment.getUsershouldinterest())));// 利息还款金额
		loanResponse.setRealcapitalAmount(amountCompute(amount, new BigDecimal(
				repayment.getUsershouldcapital())));// 本金

		return loanResponse;
	}

	/**
	 * 金额计算方法
	 * 
	 * @param amount
	 * @param bAmount
	 * @return
	 */
	private Long amountCompute(BigDecimal amount, BigDecimal bAmount) {
		// 如果金额已经等于小于0了，那么标示已经被分配完毕
		if (amount.longValue() <= 0) {
			return 0l;
		}
		// 为分配完毕则继续减去
		BigDecimal tempBigDecimal = amount.subtract(bAmount);

		// 如果减去后小于等于0，那么标示剩余的金额分配了
		if (tempBigDecimal.longValue() <= 0) {
			Long ramount = amount.longValue();
			amount = new BigDecimal(0);
			return ramount;
		}
		// 标示还有剩下
		if (tempBigDecimal.longValue() > 0) {
			amount = tempBigDecimal;
		}
		return bAmount.longValue();
	}

	@Override
	public RepaymentLoanResponse repaymentLoan(RepaymentLoanRequest req) {

		RepaymentLoanResponse loanResponse = new RepaymentLoanResponse();

		if (req == null || !StringUtil.isValidString(req.getOrderNo())
				|| !StringUtil.isValidString(req.getOrgCode())) {
			log.error("request param is null [ orderNo = " + req.getOrderNo()
					+ "  orgCode=" + req.getOrgCode() + "]");
			loanResponse.putParamError();
			return loanResponse;
		}

		UserInitiativeRepamentRequest request = new UserInitiativeRepamentRequest();
		request.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		request.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
		request.setUserid(req.getOrgCode());

		LoanApplyOrder loanApplyOrder = getBaseLoanApplyInfo(req.getOrderNo(),
				req.getOrgCode());

		if (loanApplyOrder == null) {
			log.error("query loan apply order is null  [ orderNo = "
					+ req.getOrderNo() + "  orgCode=" + req.getOrgCode() + "]");
			loanResponse.putSysError();
			return loanResponse;
		}
		RepaymentAmountComputeRequest cReq = new RepaymentAmountComputeRequest();
		cReq.setAmount(req.getAmount());
		cReq.setOrderNo(req.getOrderNo());
		cReq.setOrgCode(req.getOrgCode());

		RepaymentAmountComputeResponse computeResponse = repaymentAmountCompute(cReq);

		if (!computeResponse.isResult()) {
			log.error("repayment amount compute fail.[ orderNo = "
					+ req.getOrderNo() + "  orgCode=" + req.getOrgCode() + "]");
			loanResponse.putSysError();
			return loanResponse;
		}

		request.setRealoverdueinterestamount(""
				+ computeResponse.getRealoverdueinterestAmount());// 逾期利息还款金额
		request.setRealoverduecapitalamount(""
				+ computeResponse.getRealoverduecapitalAmount());// 逾期本金还款金额
		request.setRealinterestamount(""
				+ computeResponse.getRealinterestAmount());// 利息还款金额
		request.setRealcapitalamount(""
				+ computeResponse.getRealcapitalAmount());// 本金

		UserInitiativeRepamentResponse response = rsCreditManager
				.userInitiativeRepament(request);

		if (!response.isSuccess()) {

			log.error("initiative repament fail ! request["
					+ JsonConversionTool.toJson(request) + "] response["
					+ JsonConversionTool.toJson(response) + "]");

			loanResponse.putSysError();
			return loanResponse;
		}
		return loanResponse;
	}

	@Override
	public GetLoanOrderInfoResponse getLoanOrderInfo(GetLoanOrderInfoRequest req) {

		GetLoanOrderInfoResponse infoResponse = new GetLoanOrderInfoResponse();

		LoanQueryConditions conditions = new LoanQueryConditions();
		conditions.setOrderNo(req.getOrderNo());
		conditions.setOrgCode(req.getOrgCode());

		PaginationSupport<LoanApplyOrder> paginationSupport = new PaginationSupport<LoanApplyOrder>();
		paginationSupport = loanOrderDao.queryLoanApplyOrder(conditions,
				paginationSupport);

		for (LoanApplyOrder loanApplyOrder : paginationSupport.getItemList()) {

			LoanApplyOrderBean loanRoomPackSpec = LoanTypeConvertUtil
					.getApplyOrderBean(loanApplyOrder);

			infoResponse.setApplyOrderInfo(loanRoomPackSpec);

			if (LoanProductEnum.ROOM_PACK.getCode() == loanRoomPackSpec
					.getProductType().intValue()) {
				LoanRoomPackSpec packSpec = new LoanRoomPackSpec();
				packSpec.setOrderNo(loanRoomPackSpec.getOrderNo());

				List<LoanRoomPackSpec> packSpecs = loanRoomPackSpecDao
						.queryLoanRoomPackSpec(packSpec);

				if (packSpecs != null && packSpecs.size() > 0) {
					loanRoomPackSpec.setLoanSpec(LoanTypeConvertUtil
							.getLoanRoomPackSpecBean(packSpecs.get(0)));
				}
			}
			break;
		}
		infoResponse.setResult(true);
		return infoResponse;
	}

	/**
	 * 对字符为空的日期统一转成NULL
	 * 
	 * @param dateStr
	 * @return
	 */
	private Date convertDateUtil(String dateStr) {
		if (StringUtil.isValidString(dateStr)) {
			try {
				return DateUtil.sdf.parse(dateStr);
			} catch (ParseException e) {
				log.error("", e);
			}
		}
		return null;
	}

	/**
	 * 将请求转换呈查询条件对象
	 * 
	 * @param req
	 * @return
	 */
	private LoanQueryConditions getLoanQueryConditions(
			GetLoanOrderInfoListRequest req) {
		LoanQueryConditions conditions = new LoanQueryConditions();

		conditions.setBeginActualRepaymentDate(DateUtil
				.getDayBeginTime(convertDateUtil(req
						.getBeginActualRepaymentDate())));

		conditions.setBeginCreateTime(DateUtil
				.getDayBeginTime(convertDateUtil(req.getBeginCreateTime())));

		conditions.setBeginLastRepaymentDate(DateUtil
				.getDayBeginTime(convertDateUtil(req
						.getBeginLastRepaymentDate())));

		conditions.setBeginRelMoneyTime(DateUtil
				.getDayBeginTime(convertDateUtil(req.getBeginRelMoneyTime())));

		conditions
				.setEndActualRepaymentDate(DateUtil
						.getDayEndTime(convertDateUtil(req
								.getEndActualRepaymentDate())));

		conditions.setEndCreateTime(DateUtil.getDayEndTime(convertDateUtil(req
				.getEndCreateTime())));

		conditions.setEndLastRepaymentDate(DateUtil
				.getDayEndTime(convertDateUtil(req.getEndLastRepaymentDate())));

		conditions.setEndRelMoneyTime(DateUtil
				.getDayEndTime(convertDateUtil(req.getEndRelMoneyTime())));

		if (req.getProductEnum() != null) {
			conditions.setProductType(req.getProductEnum().getCode());
		}

		if (req.getOrderStatusEnum() != null
				&& req.getOrderStatusEnum().length > 0) {
			Integer is[] = new Integer[req.getOrderStatusEnum().length];
			for (int i = 0; i < is.length; i++) {
				is[i] = req.getOrderStatusEnum()[i].getKey();
			}
			conditions.setOrderStatusEnum(is);
		}
		return conditions;
	}

	/**
	 * 根据指定的查询条件查询对于的贷款单信息
	 */
	@Override
	public GetLoanOrderInfoListResponse getLoanOrderInfoList(
			GetLoanOrderInfoListRequest req) {

		GetLoanOrderInfoListResponse infoListResponse = new GetLoanOrderInfoListResponse();

		LoanQueryConditions conditions = getLoanQueryConditions(req);

		PaginationSupport<LoanApplyOrder> paginationSupport = new PaginationSupport<LoanApplyOrder>();
		paginationSupport.setCurrentPage(req.getCurrentPage());
		paginationSupport.setOrderBy(req.getOrderBy());

		paginationSupport = loanOrderDao.queryLoanApplyOrder(conditions,
				paginationSupport);

		List<LoanApplyOrderBean> loanApplyOrderBeans = new ArrayList<LoanApplyOrderBean>();

		for (LoanApplyOrder loanApplyOrder : paginationSupport.getItemList()) {

			LoanApplyOrderBean applyOrderBean = LoanTypeConvertUtil
					.getApplyOrderBean(loanApplyOrder);
			loanApplyOrderBeans.add(applyOrderBean);
		}
		infoListResponse.setPageSize(paginationSupport.getPageSize());
		infoListResponse.setTotalCount(paginationSupport.getTotalCount());
		infoListResponse.setCurrentPage(paginationSupport.getCurrentPage());
		infoListResponse.setApplyOrderInfo(loanApplyOrderBeans);
		infoListResponse.setResult(true);
		return infoListResponse;
	}

	@Override
	public GetOrgLoanStatInfoResponse getOrgLoanStatInfo(
			GetOrgLoanStatInfoRequest req) {

		log.info("get loan stat info by orgCode=" + req.getOrgCode());

		GetOrgLoanStatInfoResponse rsp = new GetOrgLoanStatInfoResponse();

		OrgLoanStatInfo info = new OrgLoanStatInfo();

		rsp.setOrgLoanStatInfo(info);

		LoanExpiryStat expiryStat = loanOrderDao.queryLoanExpiryStat(req
				.getOrgCode());

		log.info("query expiry info [" + expiryStat + "]");

		LoanSevenDaysStat loanSevenDaysStat = loanOrderDao
				.queryLoanSevenDaysStat(req.getOrgCode());

		log.info("query SevenDay info [" + loanSevenDaysStat + "]");

		List<LoanProductAmountStat> amountStats = loanOrderDao
				.queryLoanProductAmountStat(req.getOrgCode());

		log.info("query product amount info [" + amountStats + "]");

		if (expiryStat != null) {
			info.setExpiryNum(expiryStat.getExpiryNum());
			info.setExpiryAmount(expiryStat.getExpiryAmount());
		}

		if (loanSevenDaysStat != null) {
			info.setSevenDaysAmount(loanSevenDaysStat.getSevenDaysAmount());
			info.setSevenDaysNum(loanSevenDaysStat.getSevenDaysNum());
		}

		if (amountStats != null && amountStats.size() > 0) {
			Map<LoanProductEnum, Long> statMap = new HashMap<LoanProductEnum, Long>();
			BigDecimal totalAmount = new BigDecimal(0);
			for (LoanProductAmountStat loanProductAmountStat : amountStats) {
				totalAmount = totalAmount.add(new BigDecimal(
						loanProductAmountStat.getAmount()));
				statMap.put(LoanProductEnum.getEnumByKey(loanProductAmountStat
						.getProductId()), loanProductAmountStat.getAmount());
			}
			info.setLoanAmount(totalAmount.longValue());
			info.setProductAmount(statMap);
		}

		return rsp;
	}

	@Override
	public GetHistoryRepaymentListResponse getHistoryRepaymentList(
			GetHistoryRepaymentListRequest req) {

		GetHistoryRepaymentListResponse listResponse = new GetHistoryRepaymentListResponse();

		if (req == null || !StringUtil.isValidString(req.getOrderNo())
				|| !StringUtil.isValidString(req.getOrgCode())) {
			log.error("request param is null [ orderNo = " + req.getOrderNo()
					+ "  orgCode=" + req.getOrgCode() + "]");
			listResponse.putParamError();
			return listResponse;
		}

		QueryUserInitiativeRepaymentRequest request = new QueryUserInitiativeRepaymentRequest();
		request.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		request.setProductid(LoanApplyOrderEnum.ProductId.MAIN_PRODUCTID.productId);
		request.setUserid(req.getOrgCode());

		LoanApplyOrder loanApplyOrder = getBaseLoanApplyInfo(req.getOrderNo(),
				req.getOrgCode());

		if (loanApplyOrder == null) {
			log.error("query loan apply order is null  [ orderNo = "
					+ req.getOrderNo() + "  orgCode=" + req.getOrgCode() + "]");
			listResponse.putSysError();
			return listResponse;
		}

		request.setUserorderid(req.getOrderNo());
		QueryUserInitiativeRepaymentResponse response = rsCreditManager
				.queryUserInitiativeRepayment(request);

		LoanRepaymentBean repaymentBean = null;

		// 实际贷款金额拿出来，然后按照每次还款减去对应的还款本金，计算出每次还款后剩下的本金
		BigDecimal actualAmount = new BigDecimal(
				loanApplyOrder.getActualAmount());

		for (TUserArepayment arepayment : response.gettUserArepaymentList()) {
			repaymentBean = LoanTypeConvertUtil
					.getLoanRepaymentBean(arepayment);

			repaymentBean.setOrderNo(loanApplyOrder.getOrderNo());
			actualAmount = actualAmount.subtract(new BigDecimal(repaymentBean
					.getRepaymentAmount()));

			repaymentBean.setRemainAmount(actualAmount.longValue());
		}

		listResponse.setResult(true);
		return listResponse;
	}

	@Override
	public SaveLoanOrderInfoResponse saveLoanOrderInfo(
			SaveLoanOrderInfoRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
