package com.fangcang.titanjr.service.impl.loandispose;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.enums.FileTypeEnum;
import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.FileHelp;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dto.bean.LoanSpecificationBean;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.request.ConfirmFinanceRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.exception.ServiceException;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.NewLoanApplyRequest;
import com.fangcang.titanjr.rs.request.RSFsFileUploadRequest;
import com.fangcang.titanjr.rs.response.NewLoanApplyResponse;
import com.fangcang.titanjr.rs.response.RSFsFileUploadResponse;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.titanjr.service.impl.TitanFinancialLoanServiceImpl;
import com.fangcang.util.StringUtil;

/**
 * 运营贷逻辑处理
 * 
 * @ClassName: LoanOperactionDispose
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月14日 下午3:07:32
 */
@Component("loanOperactionDispose")
public class LoanOperactionDispose extends LoanProductDisposeAbstrator {

	private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(
			2, 15, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

	@Resource
	private TitanOrderService titanOrderService;

	@Resource
	private TitanTransOrderDao titanTransOrderDao;

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	@Resource
	private LoanOrderDao loanOrderDao;

	@Resource
	private RSFileManager rsFileManager;

	@Resource
	private RSCreditManager rsCreditManager;

	@Override
	public ApplyLoanResponse applyLoan(NewLoanApplyRequest request,
			ApplyLoanRequest req) throws ServiceException {

		log.info("operation loan dispose request .");

		ApplyLoanResponse response = new ApplyLoanResponse();
		response.putSysError();

		String transOrderNo = null;
		String billOrderNo = null;

		if (req.getLcanSpec() instanceof LoanSpecificationBean) {
			LoanSpecificationBean bean = (LoanSpecificationBean) req
					.getLcanSpec();
			Map<String, String> map = JsonConversionTool.toObject(
					bean.getContent(), Map.class);
			billOrderNo = map.get("billOrderNo");
			transOrderNo = map.get("transOrderNo");
		}

		log.info("billOrderNo and transOrderNo [" + billOrderNo + ","
				+ transOrderNo + "]");
		if (!StringUtil.isValidString(billOrderNo)
				|| !StringUtil.isValidString(transOrderNo)) {
			log.error("billCode or transOrder is null!");
			throw new ServiceException("billCode or transOrder is null!");
		}
		// 调用财务进行确认
		try {

			// 确认交易单号是否正确
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setUserorderid(transOrderNo);
			TransOrderDTO dto = titanOrderService
					.queryTransOrderDTO(transOrderRequest);

			if (dto == null) {
				log.error("query trans order is null!  orderNo = "
						+ transOrderNo);
				throw new ServiceException(
						"query trans order is null!  orderNo = " + transOrderNo);
			}
			// 告诉财务贷款处理中
			ConfirmFinanceRequest cReq = new ConfirmFinanceRequest();
			cReq.setTransOrderDTO(dto);
			cReq.setStatus(2);
			// 向财务发起确认
			titanFinancialTradeService.confirmFinance(cReq);

			// 将交易单跟贷款单进行关联
			TitanTransOrder entity = new TitanTransOrder();
			entity.setTransid(dto.getTransid());
			entity.setLoanOrderNo(req.getLcanSpec().getOrderNo());
			entity.setTradeamount(Long.parseLong(req.getLcanSpec().getAmount()));
			titanTransOrderDao.updateTitanTransOrderByTransId(entity);

			// 异步执行贷款的任务
			executor.execute(new ExecuteLoanTask(billOrderNo, dto, req, request));

		} catch (Exception ex) {
			log.error("", ex);
			throw new ServiceException(ex.getMessage());
		}
		response.putSuccess();
		return response;
	}
	/**
	 * 执行贷款异步任务类
	 * @ClassName: ExecuteLoanTask 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年3月23日 下午7:59:52
	 */
	class ExecuteLoanTask implements Runnable {
		private String billOrderNo;
		private TransOrderDTO dto;
		private ApplyLoanRequest req;
		private NewLoanApplyRequest request;

		public ExecuteLoanTask(String billOrderNo, TransOrderDTO dto,
				ApplyLoanRequest req, NewLoanApplyRequest request) {
			this.billOrderNo = billOrderNo;
			this.dto = dto;
			this.req = req;
			this.request = request;
		}

		@Override
		public void run() {
			executeLoanApply(billOrderNo, dto, req, request);
		}

	}

	/**
	 * 当发生贷款失败后需要调用该处理方法
	 * 
	 * @Title: loanFailNotify
	 * @Description: TODO
	 * @param orderNo
	 *            贷款单号
	 * @param status
	 *            设置失败状态
	 *            
	 * @return: void
	 */
	public void loanFailNotify(String orderNo, LoanOrderStatusEnum status , String msg) {
		try {
			// 设置贷款单的状态为贷款失败
			LoanApplyOrder loanOrder = new LoanApplyOrder();
			loanOrder.setOrderNo(orderNo);
			loanOrder.setStatus(status.getKey());
			loanOrder.setErrorMsg(msg);
			loanOrderDao.updateLoanApplyOrder(loanOrder);

			// 根据贷款单号查询对于的交易单号
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setLoanOrderNo(orderNo);
			TransOrderDTO dto = titanOrderService
					.queryTransOrderDTO(transOrderRequest);
			if (dto == null) {
				log.error("query trans result is null loanOrderNo=" + orderNo);
				return;
			}

			TitanTransOrder entity = new TitanTransOrder();
			entity.setTransid(dto.getTransid());
			entity.setStatusid(OrderStatusEnum.ORDER_FAIL.getStatus());
			titanTransOrderDao.updateTitanTransOrderByTransId(entity);

			// 向财务报告贷款失败已经无能为力了
			ConfirmFinanceRequest cReq = new ConfirmFinanceRequest();
			cReq.setTransOrderDTO(dto);
			cReq.setStatus(3);
			// 向财务发起确认
			titanFinancialTradeService.confirmFinance(cReq);
		} catch (Exception e) {
			log.error("send confirm messge fail! ", e);
		}
	}
	
	@Override
	public void loanAuditPassNotify(String orderNo, LoanOrderStatusEnum status) {
		// 设置贷款单的状态为贷款失败
		LoanApplyOrder loanOrder = new LoanApplyOrder();
		loanOrder.setOrderNo(orderNo);
		loanOrder.setStatus(status.getKey());
		loanOrderDao.updateLoanApplyOrder(loanOrder);
	}

	public void loanSuccessNotify(String orderNo, LoanOrderStatusEnum status) {
		try {

			// 设置贷款单的状态为贷款失败
			LoanApplyOrder loanOrder = new LoanApplyOrder();
			loanOrder.setOrderNo(orderNo);
			loanOrder.setStatus(status.getKey());
			loanOrderDao.updateLoanApplyOrder(loanOrder);

			// 根据贷款单号查询对于的交易单号
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setLoanOrderNo(orderNo);
			TransOrderDTO dto = titanOrderService
					.queryTransOrderDTO(transOrderRequest);
			if (dto == null) {
				log.error("query trans result is null loanOrderNo=" + orderNo);
				return;
			}

			TitanTransOrder entity = new TitanTransOrder();
			entity.setTransid(dto.getTransid());
			entity.setStatusid(OrderStatusEnum.ORDER_SUCCESS.getStatus());
			titanTransOrderDao.updateTitanTransOrderByTransId(entity);
			
			ConfirmFinanceRequest cReq = new ConfirmFinanceRequest();
			cReq.setTransOrderDTO(dto);
			cReq.setStatus(1);
			titanFinancialTradeService.confirmFinance(cReq);

		} catch (Exception e) {
			log.error("send confirm messge fail! ", e);
		}
	}

	/**
	 * 执行运营贷款逻辑
	 * 
	 * @Title: executeLoanApply
	 * @Description: TODO
	 * @param dto
	 * @param req
	 * @return: void
	 */
	public void executeLoanApply(String billOrderNo, TransOrderDTO dto,
			ApplyLoanRequest req, NewLoanApplyRequest request) {

		/** 循环主要是保证重试机制，如果发生一次进入重试，正常完成则直接跳出循环 */
		for (int i = 1; i <= 3; i++) {
			try {

				if (!StringUtil.isValidString(dto.getNotifyUrl())) {
					log.error("notify url is null!");
					throw new ServiceException("notify url is null!");
				}
				/**
				 * 下载本地文件到本地
				 */
				URL url = new URL(dto.getNotifyUrl());

				StringBuilder downloadUrl = new StringBuilder();
				downloadUrl.append(url.getProtocol()).append("://");
				downloadUrl.append(url.getHost()).append(":")
						.append(url.getPort());
				downloadUrl
						.append("/HFP/billitem-list!getSupplierBillDetail.action");

				log.info("download bill file url = " + downloadUrl.toString());

				Map<String, String> param = new HashMap<String, String>();
				param.put("billId", billOrderNo);
				param.put("merchantCode", dto.getMerchantcode());
				param.put("operator", "jrloan");

				log.info("download bill file request param= " + param);

				// 生成本地临时目录
				String orgLoanFileRootDir = TitanFinancialLoanServiceImpl.class
						.getClassLoader().getResource("").getPath()
						+ "tmp"
						+ FtpUtil.UPLOAD_PATH_LOAN_APPLY
						+ "/"
						+ dto.getUserid()
						+ "/"
						+ req.getLcanSpec().getOrderNo();

				String localFileDir;
				String localFather;
				localFather = "EnterpriseLoanPackage";
				localFileDir = "/" + localFather + "/DocumentInfo";
				// 个人
				// localFather = "PersonalLoanPackage";
				// localFileDir = "/" + localFather + "/PersonalDocumentInfo";

				FileHelp.deleteFile(orgLoanFileRootDir);// 清理环境

				FtpUtil.makeLocalDirectory(orgLoanFileRootDir + localFileDir);// 创建目录

				log.info("begin download file to local  localPath = "
						+ orgLoanFileRootDir);

				HttpClient.dowloadResToLoacl(downloadUrl.toString(), param,
						orgLoanFileRootDir + localFileDir,
						"billing_details.xls");// 下载文件到本地

				log.info("download is ok , local files to zip!");
				/**
				 * 对文件进行加密
				 */
				File srcZipFile = FileHelp.zipFile(orgLoanFileRootDir + "/"
						+ localFather, localFather + "_src.zip");
				long zipFileLength = srcZipFile.length() / 1024;
				log.info("包房贷申请文件(" + srcZipFile.getName() + ")压缩后大小："
						+ zipFileLength + " KB,包房订单号是[loanApplyOrderNo]:"
						+ req.getLcanSpec().getOrderNo());
				// 小于10K，则表示文件不存在或者下载失败

				if (zipFileLength < 10) {
					log.error("从ftp下载文件时，文件太小或者不存在，原文件路径srcZipFile："
							+ srcZipFile.getAbsolutePath());
					throw new ServiceException("包房贷申请的文件下载失败或者文件太小");
				}
				/**
				 * 上传给融数
				 */
				log.info("local zip file encrypt to new file ");
				// 加密
				String encryptFilePath = orgLoanFileRootDir + "/" + localFather
						+ ".zip";
				try {
					FileHelp.encryptRSFile(srcZipFile, encryptFilePath);
				} catch (Exception e) {
					log.error("encryptRSFile，融数授信申请资料文件加密失败，原文件路径srcZipFile："
							+ srcZipFile.getAbsolutePath(), e);
					throw new ServiceException("文件rsa加密失败");
				}

				log.info("begin upload to rs  filePath=" + encryptFilePath);

				// 上传到融数
				RSFsFileUploadRequest rsFsFileUploadRequest = new RSFsFileUploadRequest();
				rsFsFileUploadRequest.setUserid(dto.getUserid());
				rsFsFileUploadRequest
						.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				rsFsFileUploadRequest
						.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
				rsFsFileUploadRequest.setType(FileTypeEnum.UPLOAD_FILE_73
						.getFileType());
				rsFsFileUploadRequest.setInvoiceDate(com.fangcang.util.DateUtil
						.getCurrentDate());
				rsFsFileUploadRequest.setPath(encryptFilePath);
				rsFsFileUploadRequest
						.setBacth(dto.getUserid()
								+ com.fangcang.util.DateUtil.getCurrentDate()
										.getTime());

				log.info("upload file to rs requestInfo = "
						+ JsonConversionTool.toJson(rsFsFileUploadRequest));

				RSFsFileUploadResponse rsFsFileUploadResponse = rsFileManager
						.fsFileUpload(rsFsFileUploadRequest);
				// 上传失败
				if (rsFsFileUploadResponse.isSuccess() == false
						|| (!StringUtil.isValidString(rsFsFileUploadResponse
								.getUrlKey()))) {
					log.error("包房贷单号loanApplyOrderNo:"
							+ req.getLcanSpec().getOrderNo()
							+ ",上传包房贷压缩包文件到融数失败,"
							+ Tools.gsonToString(rsFsFileUploadRequest));
					throw new ServiceException("文件上传失败,错误信息:"
							+ rsFsFileUploadResponse.getReturnMsg());
				}

				log.info("clear local temp files. filePath="
						+ orgLoanFileRootDir);

				FileHelp.deleteFile(orgLoanFileRootDir);

				String key = rsFsFileUploadResponse.getUrlKey();

				log.info("upload file success  UrlKey=" + key);

				request.setUrlkey(key);

				log.info("begin apply local  requestInfo = "
						+ JsonConversionTool.toJson(request));

				NewLoanApplyResponse loanResponse = rsCreditManager
						.newLoanApply(request);

				if (loanResponse == null || (!loanResponse.isSuccess())) {
					log.error(loanResponse.getReturnCode() + ":"
							+ loanResponse.getReturnMsg());
					throw new ServiceException(loanResponse.getReturnCode()
							+ ":" + loanResponse.getReturnMsg());
				}

				log.info("apply local success . responseInfo = "
						+ JsonConversionTool.toJson(loanResponse));

				TitanTransOrder entity = new TitanTransOrder();
				entity.setTransid(dto.getTransid());
				entity.setOrderid(loanResponse.getOrderid());
				titanTransOrderDao.updateTitanTransOrderByTransId(entity);

				break;

			} catch (Exception ex) {
				log.error("apply loan fail!  index=" + i, ex);
				if (i >= 3) {

					loanFailNotify(req.getLcanSpec().getOrderNo(),
							LoanOrderStatusEnum.LOAN_FAIL,"apply loan fail!");
				}
			}
		}
	}

	
}
