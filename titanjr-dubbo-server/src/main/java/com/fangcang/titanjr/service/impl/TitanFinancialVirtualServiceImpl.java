package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dao.TitanVirtualOrgDao;
import com.fangcang.titanjr.dto.bean.TitanVirtualOrgRelation;
import com.fangcang.titanjr.dto.request.BindingVirtuaOrgBankCardRequest;
import com.fangcang.titanjr.dto.request.CreateVirtualOrgRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.GetVirtuaOrgBindCarListRequest;
import com.fangcang.titanjr.dto.response.BindingVirtuaOrgBankCardResponse;
import com.fangcang.titanjr.dto.response.CreateVirtualOrgResponse;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.GetVirtuaOrgBindCarListResponse;
import com.fangcang.titanjr.entity.TitanVirtualOrg;
import com.fangcang.titanjr.entity.VirtualOrgRelation;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.request.CompanyOrgRegRequest;
import com.fangcang.titanjr.rs.response.CompanyOrgRegResponse;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialVirtualService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

@Service("titanFinancialVirtualService")
public class TitanFinancialVirtualServiceImpl implements
		TitanFinancialVirtualService {

	private static final Log log = LogFactory
			.getLog(TitanFinancialVirtualServiceImpl.class);

	@Resource
	private TitanVirtualOrgDao titanVirtualOrgDao;

	@Resource
	private RSOrganizationManager rsOrganizationManager;

	@Resource
	private TitanCodeCenterService titanCodeCenterService;

	@Resource
	private TitanFinancialBankCardService bankCardService;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public CreateVirtualOrgResponse createVirtualOrg(
			CreateVirtualOrgRequest createVirtualOrgRequest)
			throws ServiceException

	{
		CreateVirtualOrgResponse response = new CreateVirtualOrgResponse();

		response.putSysError();

		if (createVirtualOrgRequest == null
				|| !StringUtil.isValidString(createVirtualOrgRequest
						.getVirtualOrgName())) {
			log.error("create virtual org is null");
			response.putParamError();
			return response;
		}

		log.info("create virtual org  orgName="
				+ createVirtualOrgRequest.getVirtualOrgName());

		TitanVirtualOrg vorgCode = new TitanVirtualOrg();

		vorgCode.setOrgName(createVirtualOrgRequest.getVirtualOrgName());
		
		List<TitanVirtualOrg> titanVirtualOrgs = titanVirtualOrgDao
				.queryVirtualOrgInfos(vorgCode);
		//如果虚拟机构已经注册成功过了，那就直接返回该机构即可
		if (titanVirtualOrgs != null && !titanVirtualOrgs.isEmpty()) {
			response.putSuccess();
			response.setvOrgCode(titanVirtualOrgs.get(0).getOrgCode());
			log.info("company already exist ! ");
			return response;
		}
		
		//生成虚拟机机构的编码
		String orgCode = titanCodeCenterService.createOrgCode();
		// 设置创建虚拟机构的基本信息
		CompanyOrgRegRequest companyOrgRegRequest = new CompanyOrgRegRequest();
		companyOrgRegRequest.setCompanyname(createVirtualOrgRequest
				.getVirtualOrgName());
	
		companyOrgRegRequest.setBuslince("BL" + orgCode);
		companyOrgRegRequest.setUserid("V" + orgCode);
		companyOrgRegRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
		companyOrgRegRequest
				.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		companyOrgRegRequest.setUsername(createVirtualOrgRequest
				.getVirtualOrgName());

		vorgCode.setBuslince(companyOrgRegRequest.getBuslince());
		vorgCode.setCreateTime(new Date());
		vorgCode.setOrgCode(companyOrgRegRequest.getUserid());
		vorgCode.setAccount(companyOrgRegRequest.getUserid());
		vorgCode.setOrgName(createVirtualOrgRequest.getVirtualOrgName());
		vorgCode.setStatus(1);
		
		//虚拟机构入库
		titanVirtualOrgDao.addVirtualOrg(vorgCode);

		log.info("begin company reg [ info = "
				+ JsonConversionTool.toJson(companyOrgRegRequest) + "]");
		//虚拟机构调用融数接口进行创建
		CompanyOrgRegResponse regResponse = rsOrganizationManager
				.resigterCompanyOrg(companyOrgRegRequest);

		if (regResponse != null && regResponse.isSuccess()) {
			response.putSuccess();
			log.info("company create success . [ info = "
					+ JsonConversionTool.toJson(companyOrgRegRequest) + "]");
			response.setvOrgCode(vorgCode.getOrgCode());

			return response;
		}

		log.error("company create fail! [ regResponse = "
				+ JsonConversionTool.toJson(regResponse) + "]");

		throw new ServiceException("company create fail!");

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public BindingVirtuaOrgBankCardResponse bindingBankCardToVirtuaOrg(
			BindingVirtuaOrgBankCardRequest request) throws ServiceException {

		BindingVirtuaOrgBankCardResponse cardResponse = new BindingVirtuaOrgBankCardResponse();

		if (request == null || !StringUtil.isValidString(request.getBankCard())
				|| !StringUtil.isValidString(request.getBankName())
				|| !StringUtil.isValidString(request.getOrgCode())
				|| !StringUtil.isValidString(request.getvOrgCode())) {
			log.error("bind request param is null");
			cardResponse.putParamError();
			return cardResponse;
		}

		TitanVirtualOrg orgCode = new TitanVirtualOrg();
		orgCode.setOrgCode(request.getvOrgCode());
		
		List<TitanVirtualOrg> titanVirtualOrgs = titanVirtualOrgDao
				.queryVirtualOrgInfos(orgCode);
		//创建绑卡对应的虚拟机构是否存在，如果不存则直接异常
		if (titanVirtualOrgs == null || titanVirtualOrgs.isEmpty()) {

			log.error("bind request param is null");
			cardResponse.putParamError();
			return cardResponse;
		}

		TitanVirtualOrg titanVirtualOrg = titanVirtualOrgs.get(0);

		VirtualOrgRelation orgRelation = new VirtualOrgRelation();
		orgRelation.setBankCard(request.getBankCard());
		orgRelation.setBankName(request.getBankName());
		orgRelation.setOrgCode(request.getOrgCode());
		orgRelation.setVorgCode(request.getvOrgCode());
		orgRelation.setVorgName(titanVirtualOrg.getOrgName());
		orgRelation.setBankCode(request.getBankCode());

		List<VirtualOrgRelation> orgRelations = titanVirtualOrgDao
				.queryVirtualOrgRelationInfos(orgRelation);
		//如果银行卡已经存则不进行入库操作
		if (orgRelations == null || orgRelations.isEmpty()) {
			titanVirtualOrgDao.addVirtualOrgRelation(orgRelation);
		}
		
		
		VirtualOrgRelation queryCon = new VirtualOrgRelation();
		queryCon.setBankCard(request.getBankCard());
		queryCon.setBankName(request.getBankName());
		queryCon.setVorgCode(request.getvOrgCode());
		List<VirtualOrgRelation> vList = titanVirtualOrgDao
				.queryOrgBindCardHistoryList(orgRelation);
		
		if(vList != null  && vList.size() > 0)
		{
			log.info("bind card success .");
			cardResponse.putSuccess();
			return cardResponse;
		}
		
//		//原先已经绑卡并且在历史表中不存在，那么就认为需要修改绑卡
//		if ((orgRelations != null && orgRelations.size() > 0)
//				&& (vList == null || vList.isEmpty())) {
//			ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest = new ModifyInvalidWithDrawCardRequest();
//	    	modifyInvalidWithDrawCardRequest.setAccountnumber(request.getBankCard());
//	    	modifyInvalidWithDrawCardRequest.setAccountrealname(titanVirtualOrg.getOrgName());
//	    	modifyInvalidWithDrawCardRequest.setHankheadname(request.getBankName());
//	    	modifyInvalidWithDrawCardRequest.setBankhead(request.getBankCode());
//	    	modifyInvalidWithDrawCardRequest.setUserid(request.getvOrgCode());
//	    	modifyInvalidWithDrawCardRequest.setBankcity(request.getBankCity());
//	    	modifyInvalidWithDrawCardRequest.setBankprovinec(request.getBankProvince());
//	    	modifyInvalidWithDrawCardRequest.setHankbranch(request.getBankBranch());
//	    	modifyInvalidWithDrawCardRequest.setUsertype("1");
//			ModifyInvalidWithDrawCardResponse modifyCard = bankCardService
//					.modifyinvalidPublicCard(modifyInvalidWithDrawCardRequest);
//			
//			if (modifyCard != null && modifyCard.isResult()) {
//
//				log.info("bind card success .");
//				cardResponse.putSuccess();
//				return cardResponse;
//			}
//		}
		//构建绑卡的基本信息
		CusBankCardBindRequest bankCardBindRequest = new CusBankCardBindRequest();
		bankCardBindRequest.setUserId(request.getvOrgCode());
		bankCardBindRequest
				.setProductId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_PRODUCT_ID);
		bankCardBindRequest
				.setConstId(com.fangcang.titanjr.common.util.CommonConstant.RS_FANGCANG_CONST_ID);
		bankCardBindRequest.setUserType("1");
		bankCardBindRequest.setAccountTypeId("00");

		bankCardBindRequest.setCurrency("CNY");
		bankCardBindRequest
				.setReqSn(String.valueOf(System.currentTimeMillis()));
		bankCardBindRequest.setSubmitTime(DateUtil.dateToString(new Date(),
				"yyyyMMddHHmmss"));
		bankCardBindRequest.setAccountProperty("1");
		bankCardBindRequest
				.setAccountPurpose(BankCardEnum.BankCardPurposeEnum.OTHER_CARD
						.getKey());
		bankCardBindRequest.setCertificateType(String.valueOf(0));
		// //查询企业营业执照号
		bankCardBindRequest.setCertificateNumber(titanVirtualOrg.getBuslince());
		bankCardBindRequest.setBankHeadName(request.getBankName());
		bankCardBindRequest.setAccountNumber(request.getBankCard());
		bankCardBindRequest.setAccountName(titanVirtualOrg.getOrgName());
		bankCardBindRequest.setBankCode(request.getBankCode());

		bankCardBindRequest.setBankBranch(request.getBankBranch());
		bankCardBindRequest.setBankCity(request.getBankCity());
		bankCardBindRequest.setBankProvince(request.getBankProvince());

		//调用本系统提供的绑卡接口，触发原先的绑卡流程。
		CusBankCardBindResponse bindResponse = bankCardService
				.bankCardBind(bankCardBindRequest);
		
		if (bindResponse != null && bindResponse.isResult()) {

			log.info("bind card success .");

			cardResponse.putSuccess();

			return cardResponse;
		}

		throw new ServiceException("bind card fail!");
	}

	@Override
	public GetVirtuaOrgBindCarListResponse getVirtuaOrgBindingBankCardList(
			GetVirtuaOrgBindCarListRequest req) {
		GetVirtuaOrgBindCarListResponse bindCarListResponse = new GetVirtuaOrgBindCarListResponse();

		VirtualOrgRelation orgRelation = new VirtualOrgRelation();
		orgRelation.setOrgCode(req.getOrgCode());

		List<VirtualOrgRelation> orgRelations = titanVirtualOrgDao
				.queryOrgBindCardHistoryList(orgRelation);

		List<TitanVirtualOrgRelation> d = new ArrayList<TitanVirtualOrgRelation>();

		for (VirtualOrgRelation virtualOrgRelation : orgRelations) {
			TitanVirtualOrgRelation relation = new TitanVirtualOrgRelation();
			relation.setBankCard(virtualOrgRelation.getBankCard());
			relation.setBankName(virtualOrgRelation.getBankName());
			relation.setOrgCode(virtualOrgRelation.getOrgCode());
			relation.setVorgName(virtualOrgRelation.getVorgName());
			relation.setVorgCode(virtualOrgRelation.getVorgCode());
			relation.setBankCode(virtualOrgRelation.getBankCode());
			d.add(relation);
		}
		bindCarListResponse.setvOrgRelationList(d);
		return bindCarListResponse;
	}

}
