package com.fangcang.titanjr.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.finance.exception.ParameterException;
import com.fangcang.titanjr.common.enums.ImgSizeEnum;
import com.fangcang.titanjr.common.enums.LoginSourceEnum;
import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.entity.TitanCheckCodeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgBindinfoEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgImageEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
//import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.ImageIOExtUtil;
import com.fangcang.titanjr.common.util.ImageResizer;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanAccountDao;
import com.fangcang.titanjr.dao.TitanCheckCodeDao;
import com.fangcang.titanjr.dao.TitanOrgBindinfoDao;
import com.fangcang.titanjr.dao.TitanOrgCheckDao;
import com.fangcang.titanjr.dao.TitanOrgCheckLogDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dao.TitanOrgImageDao;
import com.fangcang.titanjr.dao.TitanRoleDao;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;
import com.fangcang.titanjr.dto.request.CashierDeskInitRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.FinancialUserBindRequest;
import com.fangcang.titanjr.dto.request.FinancialUserUnBindRequest;
import com.fangcang.titanjr.dto.request.GetCheckCodeRequest;
import com.fangcang.titanjr.dto.request.OrgRegisterValidateRequest;
import com.fangcang.titanjr.dto.request.OrgUpdateRequest;
import com.fangcang.titanjr.dto.request.OrganBindRequest;
import com.fangcang.titanjr.dto.request.OrganCheckRequest;
import com.fangcang.titanjr.dto.request.OrganFreezeRequest;
import com.fangcang.titanjr.dto.request.OrganImageRequest;
import com.fangcang.titanjr.dto.request.OrganImageUploadRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterUpdateRequest;
import com.fangcang.titanjr.dto.request.UpdateCheckCodeRequest;
import com.fangcang.titanjr.dto.request.UserRegisterRequest;
import com.fangcang.titanjr.dto.request.VerifyCheckCodeRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.FinancialUserBindResponse;
import com.fangcang.titanjr.dto.response.FinancialUserUnBindResponse;
import com.fangcang.titanjr.dto.response.GetCheckCodeResponse;
import com.fangcang.titanjr.dto.response.OrgRegisterValidateResponse;
import com.fangcang.titanjr.dto.response.OrganBindResponse;
import com.fangcang.titanjr.dto.response.OrganBriefResponse;
import com.fangcang.titanjr.dto.response.OrganCheckResponse;
import com.fangcang.titanjr.dto.response.OrganFreezeResponse;
import com.fangcang.titanjr.dto.response.OrganImageResponse;
import com.fangcang.titanjr.dto.response.OrganImageUploadResponse;
import com.fangcang.titanjr.dto.response.OrganQueryCheckResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterUpdateResponse;
import com.fangcang.titanjr.dto.response.UserRegisterResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.entity.TitanAccount;
import com.fangcang.titanjr.entity.TitanCheckCode;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.TitanOrgBindinfo;
import com.fangcang.titanjr.entity.TitanOrgCheck;
import com.fangcang.titanjr.entity.TitanOrgCheckLog;
import com.fangcang.titanjr.entity.TitanOrgImage;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;
import com.fangcang.titanjr.entity.parameter.TitanCheckCodeParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgBindinfoParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgCheckParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgImageParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgParam;
import com.fangcang.titanjr.entity.parameter.TitanUserParam;
import com.fangcang.titanjr.rs.dao.RSInvokeConfigDao;
import com.fangcang.titanjr.rs.manager.RSAccTradeManager;
import com.fangcang.titanjr.rs.manager.RSAccountManager;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.request.AccountFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountUnFreezeRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgRegRequest;
import com.fangcang.titanjr.rs.request.PersonOrgRegRequest;
import com.fangcang.titanjr.rs.response.AccountFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountUnFreezeResponse;
import com.fangcang.titanjr.rs.response.BaseResponse;
import com.fangcang.titanjr.rs.response.CompanyOrgRegResponse;
import com.fangcang.titanjr.rs.response.PersonOrgRegResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/3/30.
 */
@Service("titanOrgService")
public class TitanFinancialOrganServiceImpl implements TitanFinancialOrganService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TitanFinancialOrganServiceImpl.class);
	private final int ORGBINGINFO_CODE_SUCCESS = 1;
	private final int ORGBINGINFO_CODE_ERROR = -1;
	private final int ORGBINGINFO_CODE_EXIST = -2;
	@Resource
    private TitanOrgDao titanOrgDao;
    @Resource
    private TitanUserDao titanUserDao;
    @Resource
    private TitanOrgImageDao titanOrgImageDao;
    private RSInvokeConfigDao rsInvokeConfigDao;
    @Resource
    private TitanOrgBindinfoDao titanOrgBindinfoDao;
    @Resource
    private TitanCodeCenterService titanCodeCenterService;
    @Resource
    private RSOrganizationManager rsOrganizationManager;
    @Resource
    private RSAccTradeManager rsAccTradeManager;
    
    @Resource
    private TitanCashierDeskService titanCashierDeskService;
    
    @Resource
    private RSAccountManager rsAccountManager;
    @Resource
    private TitanAccountDao titanAccountDao;
    @Resource
    private TitanOrgCheckDao titanOrgCheckDao;
    @Resource
    private TitanOrgCheckLogDao titanOrgCheckLogDao;
    
    @Resource
    private TitanRoleDao titanRoleDao;
    @Resource
    private TitanCheckCodeDao checkCodeDao;
    @Resource
    private TitanFinancialUserService titanFinancialUserService;

    @Override
    public FinancialOrganResponse queryFinancialOrgan(FinancialOrganQueryRequest request) {
    	FinancialOrganResponse response = new FinancialOrganResponse();
        try {
        	if(request.getOrgId()==null&&!StringUtil.isValidString(request.getOrgCode())&&!StringUtil.isValidString(request.getUserId())&&!StringUtil.isValidString(request.getMerchantcode())){
        		response.putErrorResult("参数错误，必填参数不能为空");
        		return response;
        	}
            PaginationSupport<FinancialOrganDTO> paginationSupport = new PaginationSupport<FinancialOrganDTO>();
            paginationSupport =  titanOrgDao.queryTitanOrgForPage(request, paginationSupport);
            if(paginationSupport.getItemList()!=null&&paginationSupport.getItemList().size()==1){
            	response.setFinancialOrganDTO(paginationSupport.getItemList().get(0));
            	response.putSuccess();
            }else{
            	response.putErrorResult("数据结果集个数错误");
            }
//          List<RSInvokeConfig> cfgs =  rsInvokeConfigDao.queryRSInvokeConfig();
          //rsOrganizationManager.queryCompOrgInfo(new CompOrgInfoQueryRequest());
		} catch (Exception e) {
			LOGGER.error("查询错误，参数FinancialOrganQueryRequest："+JSONSerializer.toJSON(request).toString(), e);
			response.putSysError();
		}
        
        return response;
    }

	@Override
	public OrganBriefResponse queryOrganBriefList(FinancialOrganQueryRequest organQueryRequest) {
		OrganBriefResponse response = new OrganBriefResponse();
		List<FinancialOrganDTO> organDTOList = new ArrayList<FinancialOrganDTO>();
		List<TitanOrg> titanOrgs = null;
		try {
			titanOrgs = titanOrgDao.queryTitanOrgList(organQueryRequest);
		} catch (Exception e){
			response.putErrorResult("查询机构概要信息失败");
			return response;
		}
		if (CollectionUtils.isNotEmpty(titanOrgs)){
			for (TitanOrg titanOrg : titanOrgs){
				FinancialOrganDTO organDTO = new FinancialOrganDTO();
				organDTO.setUserId(titanOrg.getUserid());
				organDTO.setOrgName(titanOrg.getOrgname());
				organDTO.setProductId(titanOrg.getProductid());
				organDTO.setConstId(titanOrg.getConstid());
				organDTO.setUserType(titanOrg.getUsertype());
				organDTO.setStatusId(titanOrg.getStatusid());
				organDTO.setOrgType(titanOrg.getOrgtype());
				organDTOList.add(organDTO);
			}
			response.setOrganDTOList(organDTOList);
			response.putSuccess();
			return response;
		} else {
			response.putErrorResult("机构列表信息为空");
			return response;
		}
	}

	@Override
    public OrganQueryCheckResponse queryFinancialOrganForPage(FinancialOrganQueryRequest titanOrgQueryDTO) {
    	OrganQueryCheckResponse responsePageDTO = new OrganQueryCheckResponse();
        try {
            PaginationSupport<OrgCheckDTO> paginationSupport = new PaginationSupport<OrgCheckDTO>();
            paginationSupport.setCurrentPage(titanOrgQueryDTO.getCurrentPage());
            paginationSupport.setPageSize(titanOrgQueryDTO.getPageSize());
            paginationSupport.setOrderBy(" G.createTime asc ");
            titanOrgDao.queryTitanOrgCheckForPage(titanOrgQueryDTO, paginationSupport);
            responsePageDTO.setPaginationSupport(paginationSupport);
            responsePageDTO.putSuccess();
            
		} catch (Exception e) {
			e.printStackTrace();
			responsePageDTO.putSysError();
		}
        return responsePageDTO;
    }
    /**
     * 判断某个组织是否已经创建了超级管理员账号
     * @param orgCode
     * @return
     */
    private boolean isExistAdminUser(String orgCode){
    	TitanUserParam condition = new TitanUserParam();
    	condition.setOrgcode(orgCode);
    	PaginationSupport<TitanUser> page = new PaginationSupport<TitanUser>();
    	titanUserDao.selectForPage(condition, page);
    	if(CollectionUtils.isEmpty(page.getItemList())){
    		return false;
    	}else{
    		return true;
    	}
    }
    /***
     * 从saas页面注册的
     * @param organRegisterRequest
     * @return 1:成功，-1：参数错误，-2：
     * @throws Exception
     */
    private TitanOrg registerFromSaaS(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException{
    	TitanOrg titanOrg = null;
    	try{
	    	//TODO 必填参数校验
    		
    		OrgBindInfo orgBindInfo = new OrgBindInfo();
            orgBindInfo.setMerchantCode(organRegisterRequest.getMerchantCode());
            orgBindInfo = this.queryOrgBindInfoByUserid(orgBindInfo);
    		if(orgBindInfo!=null&&StringUtil.isValidString(orgBindInfo.getOrgcode())){
    			throw new MessageServiceException("该商家已经开通了金融账号，不允许重复开通");
    		}
    		titanOrg = addOrg(organRegisterRequest);
    		updateOrgImg(organRegisterRequest.getImageid(),organRegisterRequest.getOrgCode());
	    	// 注册员工
    		UserRegisterResponse registerResponse = registerUser(organRegisterRequest);
    		if(registerResponse.isResult()==false){
        		throw new MessageServiceException(registerResponse.getReturnCode(),registerResponse.getReturnMessage());
        	}
	    	// 绑定关系
	    	int returnCode =addOrgbingInfo(organRegisterRequest.getOrgCode(), organRegisterRequest.getMerchantCode(), organRegisterRequest.getOrgCode(), organRegisterRequest.getMerchantname());
	    	if(ORGBINGINFO_CODE_EXIST==returnCode){
	    		throw new MessageServiceException("该商家已经绑定过泰坦金融账号，不能重复绑定");
	    	}
    	} catch (MessageServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalServiceException("用户注册失败", e);
		}
    	return titanOrg;
    }
    
    /***
     * 从金服官网注册
     * @param organRegisterRequest
     * @throws GlobalServiceException 
     * @throws Exception 
     */
    private  TitanOrg registerFromJinfuSite(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException{
    	TitanOrg titanOrg = null;
    	try {
    		titanOrg = addOrg(organRegisterRequest);
        	updateOrgImg(organRegisterRequest.getImageid(),organRegisterRequest.getOrgCode());
    		// 注册员工
    		UserRegisterResponse registerResponse = registerUser(organRegisterRequest);
    		if(registerResponse.isResult()==false){
        		throw new MessageServiceException(registerResponse.getReturnCode(),registerResponse.getReturnMessage());
        	}
		} catch (MessageServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalServiceException("用户注册失败", e);
		}
    	return titanOrg;
    }
    /**
     * 系统自动注册
     * @param organRegisterRequest
     * @throws Exception 
     */
    private TitanOrg registerFromAuto(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException{
    	TitanOrg titanOrg = null;
    	try {
    		titanOrg = addOrg(organRegisterRequest);
        	
        	OrganCheckRequest organCheckRequest = new OrganCheckRequest();
        	organCheckRequest.setOrgId(titanOrg.getOrgid());
        	organCheckRequest.setCheckstatus(OrgCheckResultEnum.PASS.getCheckstatus());
        	checkFinancialOrgan(organCheckRequest);
    		
    		// 注册员工
    		UserRegisterResponse registerResponse = registerUser(organRegisterRequest);
    		if(registerResponse.isResult()==false){
        		throw new MessageServiceException(registerResponse.getReturnCode(),registerResponse.getReturnMessage());
        	}
		} catch (MessageServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalServiceException("用户注册失败", e);
		}
    	return titanOrg;
    }
    /**
     * 更新金服机构图片信息
     * @param organRegisterRequest
     * @throws GlobalServiceException 
     */
    private void updateOrgImg(String imageId,String orgCode) throws GlobalServiceException{
    	if(StringUtils.isNotBlank(imageId)){
    		String[] imgArr = imageId.split(",");
    		for(String temp: imgArr){
    			int tempImageId = 0;
    			try {
    				tempImageId = NumberUtils.toInt(temp);
				} catch (Exception e) {
					throw new GlobalServiceException("更新金服机构图片信息错误，tempImageId非法",e);
				}
    			
    			TitanOrgImage titanOrgImage = new TitanOrgImage();
    			titanOrgImage.setImageid(tempImageId);
    			titanOrgImage.setOrgcode(orgCode);
    			titanOrgImage.setUserid(orgCode);
    			titanOrgImageDao.update(titanOrgImage);
    		}
    	}
    }
    /**
     * 注册员工账号
     * @param organRegisterRequest
     * @return
     * @throws Exception
     */
    private UserRegisterResponse registerUser(OrganRegisterRequest organRegisterRequest) throws Exception{
    	boolean isExistAdminUser = isExistAdminUser(organRegisterRequest.getOrgCode());
    	UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
    	
    	if(isExistAdminUser){//已经存在超级管理员,则新增的第二个不是超级管理员
    		userRegisterRequest.setIsAdminUser(0);
    	}else{
    		//超级管理员
    		userRegisterRequest.setIsAdminUser(1);
    		userRegisterRequest.setUserName("管理员");
    	}
    	userRegisterRequest.setOperator(organRegisterRequest.getOperator());
    	userRegisterRequest.setFcLoginUserName(organRegisterRequest.getFcLoginUserName());
    	userRegisterRequest.setLoginUserName(organRegisterRequest.getUserloginname());
    	userRegisterRequest.setMerchantCode(organRegisterRequest.getMerchantCode());
    	userRegisterRequest.setMobilePhone(organRegisterRequest.getMobileTel());
    	userRegisterRequest.setOrgCode(organRegisterRequest.getOrgCode());
    	userRegisterRequest.setPassword(MD5.MD5Encode(organRegisterRequest.getPassword()));
    	userRegisterRequest.setRegisterSource(organRegisterRequest.getRegisterSource());
    	userRegisterRequest.setUserId(organRegisterRequest.getOrgCode());
    	UserRegisterResponse respose = titanFinancialUserService.registerFinancialUser(userRegisterRequest);
    	if(respose.isResult()==false){
    		LOGGER.info("用户注册失败,错误信息："+respose.getReturnMessage()+",param:"+JSONSerializer.toJSON(userRegisterRequest).toString());
    	}
    	return respose;
    }
    /**
     * 添加机构
     * @param organRegisterRequest
     */
    private TitanOrg addOrg(OrganRegisterRequest organRegisterRequest){
    	TitanOrg titanOrg = new TitanOrg();
    	String titancode = titanCodeCenterService.createTitanCode();
    	String orgcode = titanCodeCenterService.createOrgCode();
    	organRegisterRequest.setTitancode(titancode);
    	organRegisterRequest.setOrgCode(orgcode);
    	titanOrg.setOrgcode(orgcode);
    	titanOrg.setUserid(orgcode);
    	titanOrg.setTitancode(titancode);
    	titanOrg.setStatusid(TitanOrgEnum.StatusId.AVAILABLE.getKey());
    	titanOrg.setUsertype(organRegisterRequest.getUserType());
    	titanOrg.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
    	titanOrg.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    	titanOrg.setCreateTime(new Date());
    	organRegisterRequest.setOrgCode(orgcode);
    	if(organRegisterRequest.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    		validateEnterpriseParam(organRegisterRequest);
    		titanOrg.setEmail(organRegisterRequest.getEmail());
    		titanOrg.setUsername(organRegisterRequest.getOrgName());
    		//机构
    		titanOrg.setOrgtype(organRegisterRequest.getOrgType());
        	titanOrg.setOrgname(organRegisterRequest.getOrgName());
        	
        	titanOrg.setBuslince(organRegisterRequest.getBuslince());
        	titanOrg.setConnect(organRegisterRequest.getConnect());
        	titanOrg.setMobiletel(organRegisterRequest.getMobileTel());
        	
    	}else if(organRegisterRequest.getUserType()==TitanOrgEnum.UserType.PERSONAL.getKey()){
    		validatePersonalParam(organRegisterRequest);
    		titanOrg.setOrgtype(organRegisterRequest.getOrgType());
        	titanOrg.setOrgname(organRegisterRequest.getOrgName());
        	titanOrg.setPersonengname(organRegisterRequest.getOrgName());
        	titanOrg.setCertificatetype(NumberUtils.toInt(organRegisterRequest.getCertificateType()));
        	titanOrg.setCertificatenumber(organRegisterRequest.getCertificateNumber());
    	}
        
    	titanOrgDao.insert(titanOrg);
    	return titanOrg;
    }
    /**
     * 企业机构注册必填参数校验
     * @param organRegisterRequest
     * @throws ParameterException
     */
    private void validateEnterpriseParam(OrganRegisterRequest organRegisterRequest) throws ParameterException{
    	if(StringUtil.isValidString(organRegisterRequest.getBuslince())==false){
    		throw new ParameterException("param error: Buslince can not be  null");
    	}
    	if(StringUtil.isValidString(organRegisterRequest.getConnect())==false){
    		throw new ParameterException("param error: Connect can not be  null");
    	}
    	if(StringUtil.isValidString(organRegisterRequest.getMobileTel())==false){
    		throw new ParameterException("param error: MobileTel can not be  null");
    	}
    }
    /**
     * 个人机构注册必填参数校验
     * @param organRegisterRequest
     * @throws ParameterException
     */
    private void validatePersonalParam(OrganRegisterRequest organRegisterRequest) throws ParameterException{
    	if(StringUtil.isValidString(organRegisterRequest.getCertificateType())==false){
    		throw new ParameterException("param error: CertificateType can not be  null");
    	}
    	if(StringUtil.isValidString(organRegisterRequest.getCertificateNumber())==false){
    		throw new ParameterException("param error: CertificateNumber can not be  null");
    	}
    	
    }
    /**
     * 金服添加机构绑定关系
     * @throws GlobalServiceException
     */
    private int addOrgbingInfo(String userId,String merchantCode,String orgcode,String merchantname) throws Exception{
    	if(StringUtil.isValidString(userId)==false){
    		throw new ParameterException("param error: userId can not be  null");
    	}
    	if(StringUtil.isValidString(merchantCode)==false){
    		throw new ParameterException("param error: merchantCode can not be  null");
    	}
    	if(StringUtil.isValidString(orgcode)==false){
    		throw new ParameterException("param error: orgcode can not be  null");
    	}
    	if(StringUtil.isValidString(merchantname)==false){
    		throw new ParameterException("param error: merchantname can not be  null");
    	}
    	//检查商家的绑定关系是否已经存在 
    	TitanOrgBindinfoParam condition = new TitanOrgBindinfoParam();
    	condition.setUserid(userId);
    	condition.setMerchantcode(merchantCode);
    	condition.setBindstatus(TitanOrgBindinfoEnum.BindStatus.BIND.getKey());
    	PaginationSupport<TitanOrgBindinfo> paginationSupport = new PaginationSupport<TitanOrgBindinfo>();
    	titanOrgBindinfoDao.selectForPage(condition, paginationSupport);
    	if(paginationSupport.getItemList()==null||paginationSupport.getItemList().size()==0){//暂无绑定关系
    		TitanOrgBindinfo orgBindinfo = new TitanOrgBindinfo();
    		orgBindinfo.setUserid(userId);
    		orgBindinfo.setOrgcode(orgcode);
    		orgBindinfo.setMerchantcode(merchantCode);
    		orgBindinfo.setMerchantname(merchantname);
    		orgBindinfo.setBindstatus(TitanOrgBindinfoEnum.BindStatus.BIND.getKey());
    		orgBindinfo.setCreatetime(new Date());
    		
        	try {
				titanOrgBindinfoDao.insert(orgBindinfo);
			} catch (DaoException e) {
				LOGGER.error("addOrgbingInfo error. parameter:userId["+userId+"],merchantCode["+merchantCode+"]"+"],orgcode["+orgcode+"]"+"]", e);
				throw new Exception(e);
			}
        	return ORGBINGINFO_CODE_SUCCESS;
    	}else{
    		LOGGER.info("OrgbingInfo has exist ,can not be duplicated. parameter:userId["+userId+"],merchantCode["+merchantCode+"]"+"],orgcode["+orgcode+"]"+"]");
    		return ORGBINGINFO_CODE_EXIST;
    	}
    }
    /***
     * 注册的时增加第一条待审核记录
     * @param organRegisterRequest
     */
    private TitanOrgCheck addOrgCheck(String orgUserId,String creator){
    	TitanOrgCheck entity = new TitanOrgCheck();
    	Date nowDate = new Date();
    	entity.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
    	entity.setUserid(orgUserId);
		entity.setResultkey(OrgCheckResultEnum.FT.getResultkey());
    	entity.setResultmsg(OrgCheckResultEnum.FT.getResultmsg());
    	if(StringUtil.isValidString(creator)){
    		entity.setCreator(creator);
    	}else{
    		entity.setCreator("jr-admin");
    	}
    	entity.setCreatetime(nowDate);
    	titanOrgCheckDao.insert(entity);
    	
    	return entity;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public OrganRegisterResponse registerFinancialOrgan(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException {
    	OrganRegisterResponse response = new OrganRegisterResponse();
    	response.putSuccess();
    	//公共参数校验,密码校验
    	if (!(GenericValidate.validate(organRegisterRequest)&&StringUtil.isValidString(organRegisterRequest.getPassword()))){
    		LOGGER.info("参数错误，organRegisterRequest："+JSONSerializer.toJSON(organRegisterRequest).toString());
			response.putParamError();
			return response;
		}
    	//判断是否可以注册
    	OrgRegisterValidateRequest request = new OrgRegisterValidateRequest();
    	request.setUsertype(organRegisterRequest.getUserType());
    	request.setBuslince(organRegisterRequest.getBuslince());
    	request.setCertificatetype(organRegisterRequest.getCertificateType());
    	request.setCertificateNumber(organRegisterRequest.getCertificateNumber());
    	OrgRegisterValidateResponse orgRegisterValidateResponse = validateOrg(request);
    	if(orgRegisterValidateResponse.isResult()==false){
    		response.putErrorResult(orgRegisterValidateResponse.getReturnMessage());
			return response;
    	}else if(orgRegisterValidateResponse.getOrgDTO()!=null){
    		response.putErrorResult("该证件已经注册，请使用其他证件注册。");
			return response;
    	}
    	
    	if(organRegisterRequest.getRegisterSource()==LoginSourceEnum.SAAS.getKey()){
    		
    		
    		registerFromSaaS(organRegisterRequest);
    		addOrgCheck(organRegisterRequest.getOrgCode(),organRegisterRequest.getOperator());
    	}else if(organRegisterRequest.getRegisterSource()==LoginSourceEnum.TFS.getKey()){
    		registerFromJinfuSite(organRegisterRequest);
    		addOrgCheck(organRegisterRequest.getOrgCode(),organRegisterRequest.getOperator());
    	}else if(organRegisterRequest.getRegisterSource()==LoginSourceEnum.AUTO.getKey()){
    		TitanOrg titanOrg = registerFromAuto(organRegisterRequest);
    		addOrgCheck(organRegisterRequest.getOrgCode(),organRegisterRequest.getOperator());
    		OrganCheckRequest organCheckRequest = new OrganCheckRequest();
    		organCheckRequest.setCheckstatus(1);
    		organCheckRequest.setOperator(CommonConstant.CHECK_ADMIN_JR);
    		organCheckRequest.setOrgId(titanOrg.getOrgid());
    		checkFinancialOrgan(organCheckRequest);
    	}else{
    		LOGGER.error("注册参数："+JSONSerializer.toJSON(organRegisterRequest).toString());
    		response.putErrorResult("-1", "未知的注册来源");
    	}
    	
    	return response;
    }

	@Override
	public OrgRegisterValidateResponse validateOrg(OrgRegisterValidateRequest request) {
		OrgRegisterValidateResponse response = new OrgRegisterValidateResponse();
		TitanOrg titanOrg = null;
		if(request.getUsertype()==null){
			response.putParamError();
			return response;
		}
		
		if(request.getUsertype()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			if(request.getCertificatetype()==null||!StringUtil.isValidString(request.getCertificateNumber())){
				response.putParamError();
				return response;
			}
			TitanOrgParam condition = new TitanOrgParam();
			condition.setCertificatetype(NumberUtils.toInt(request.getCertificatetype()));
			condition.setCertificatenumber(request.getCertificateNumber());
			titanOrg = titanOrgDao.selectOne(condition);
		}else if(request.getUsertype()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
			if(!StringUtil.isValidString(request.getBuslince())){
				response.putParamError();
				return response;
			}
			TitanOrgParam condition = new TitanOrgParam();
			condition.setBuslince(request.getBuslince());
			titanOrg = titanOrgDao.selectOne(condition);
		}else {
			response.putParamError();
			return response;
		}
		if(titanOrg!=null){//已经存在机构
			OrgDTO orgDTO = new OrgDTO();
			orgDTO.setOrgid(titanOrg.getOrgid());
			orgDTO.setOrgcode(titanOrg.getOrgcode());
			orgDTO.setUserid(titanOrg.getUserid());
			orgDTO.setProductid(titanOrg.getProductid());
			response.setOrgDTO(orgDTO);
		}
		response.putSuccess();
		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public OrganRegisterUpdateResponse reRegisterOrgan(OrganRegisterUpdateRequest organRegisterUpdateRequest) throws GlobalServiceException {
		OrganRegisterUpdateResponse response = new OrganRegisterUpdateResponse();
		if (organRegisterUpdateRequest.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
			if (!( organRegisterUpdateRequest
					.getOrgId()!=null
					&& StringUtil.isValidString(organRegisterUpdateRequest
							.getOrgName())&& StringUtil.isValidString(organRegisterUpdateRequest
									.getBuslince())
					&& StringUtil.isValidString(organRegisterUpdateRequest
							.getConnect())&& StringUtil.isValidString(organRegisterUpdateRequest
									.getMobileTel()))) {
				response.putParamError();
				return response;
			}
		}else if(organRegisterUpdateRequest.getUserType()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			if(!(organRegisterUpdateRequest.getOrgId()!=null&&StringUtil.isValidString(organRegisterUpdateRequest.getOrgName())&&StringUtil.isValidString(organRegisterUpdateRequest.getCertificateType())&&StringUtil.isValidString(organRegisterUpdateRequest.getCertificateNumber()))){
				response.putParamError();
				return response;
			}
		}else{
			response.putErrorResult("非法参数");
			return response;
		}
		
		try {
			TitanOrgParam param = new TitanOrgParam();
			param.setOrgid(organRegisterUpdateRequest.getOrgId());
			TitanOrg oldOrg = titanOrgDao.selectOne(param);
			if(StringUtil.isValidString(organRegisterUpdateRequest.getImageid())){
				updateOrgImg(organRegisterUpdateRequest.getImageid(),oldOrg.getOrgcode());
			}
			
			TitanOrg updateEntity = new TitanOrg();
			updateEntity.setOrgid(organRegisterUpdateRequest.getOrgId());
			updateEntity.setOrgname(organRegisterUpdateRequest.getOrgName());
			updateEntity.setBuslince(organRegisterUpdateRequest.getBuslince());
			updateEntity.setConnect(organRegisterUpdateRequest.getConnect());
			updateEntity.setMobiletel(organRegisterUpdateRequest.getMobileTel());
			//if(StringUtil.isValidString(organRegisterUpdateRequest.getResultKey())){
				//TitanOrgParam orgParam = new TitanOrgParam();
				//orgParam.setOrgid(organRegisterUpdateRequest.getOrgId());
				//TitanOrg newOrgEntity = titanOrgDao.selectOne(orgParam);
				
				TitanOrgCheck titanOrgCheck = new TitanOrgCheck();
				TitanOrgCheckParam checkParam = new TitanOrgCheckParam();
		    	checkParam.setConstid(oldOrg.getConstid());
		    	checkParam.setUserid(oldOrg.getUserid());
		    	
		    	PaginationSupport<TitanOrgCheck> orgCheckPage = new PaginationSupport<TitanOrgCheck>();
		    	titanOrgCheckDao.selectForPage(checkParam, orgCheckPage);
		    	titanOrgCheck = orgCheckPage.getItemList().get(0);
		    	
		    	titanOrgCheck.setResultkey(OrgCheckResultEnum.FT.getResultkey());
		    	titanOrgCheck.setResultmsg("修改注册资料，重新注册");
		    	titanOrgCheck.setCheckuser(organRegisterUpdateRequest.getOperator());
		    	titanOrgCheck.setChecktime(new Date());
		    	titanOrgCheckDao.update(titanOrgCheck);
			//}
			
			updateEntity.setCertificatetype(NumberUtils.toInt(organRegisterUpdateRequest.getCertificateType()));
			updateEntity.setCertificatenumber(organRegisterUpdateRequest.getCertificateNumber());
			titanOrgDao.update(updateEntity);
			response.putSuccess();
		} catch (Exception e) {
			throw new GlobalServiceException("修改机构信息失败,param:"+JSONSerializer.toJSON(organRegisterUpdateRequest).toString(), e);
		}
		return response;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public OrganCheckResponse checkFinancialOrgan(OrganCheckRequest organCheckRequest) throws GlobalServiceException, MessageServiceException   {
		OrganCheckResponse response = new OrganCheckResponse();
		if (!GenericValidate.validate(organCheckRequest)){
			response.putParamError();
			return response;
		}
    	//更新机构的审核状态
		try {
			Date nowDate = new Date();
			TitanOrgParam orgParam = new TitanOrgParam();
			orgParam.setOrgid(organCheckRequest.getOrgId());
			TitanOrg newOrgEntity = titanOrgDao.selectOne(orgParam);
	    	//机构审核记录
	    	
	    	TitanOrgCheckParam param = new TitanOrgCheckParam();
	    	TitanOrgCheck titanOrgCheck = new TitanOrgCheck();
	    	param.setConstid(newOrgEntity.getConstid());
	    	param.setUserid(newOrgEntity.getUserid());
	    	PaginationSupport<TitanOrgCheck> orgCheckPage = new PaginationSupport<TitanOrgCheck>();
	    	titanOrgCheckDao.selectForPage(param, orgCheckPage);
	    	OrgCheckResultEnum newOrgCheckResultEnum = convertCheckResultEnum(organCheckRequest.getCheckstatus());
	    	if(CollectionUtils.isEmpty(orgCheckPage.getItemList())){
	    		titanOrgCheck = addOrgCheck(newOrgEntity.getUserid(),organCheckRequest.getOperator());
	    	}else{
	    		titanOrgCheck = orgCheckPage.getItemList().get(0);
	    		if(OrgCheckResultEnum.PASS.getResultkey().equals(titanOrgCheck.getResultkey())){
	    			throw new MessageServiceException("该机构已经审核通过，请不要重复审核");
	    		}
	    	}
	    	
	    	titanOrgCheck.setResultkey(newOrgCheckResultEnum.getResultkey());
	    	if(newOrgCheckResultEnum.equals(OrgCheckResultEnum.FT_INVALID)){//不通过，则保存原因
	    		titanOrgCheck.setResultmsg(organCheckRequest.getResultMsg());
	    	}else{
	    		titanOrgCheck.setResultmsg(newOrgCheckResultEnum.getResultmsg());
	    	}
	    	
	    	titanOrgCheck.setCheckuser(organCheckRequest.getOperator());
	    	titanOrgCheck.setChecktime(nowDate);
	    	//添加审核日志
	    	TitanOrgCheckLog titanOrgCheckLog = new TitanOrgCheckLog();
	    	titanOrgCheckLog.setCheckid(titanOrgCheck.getCheckid());
	    	titanOrgCheckLog.setConstid(newOrgEntity.getConstid());
	    	titanOrgCheckLog.setUserid(newOrgEntity.getUserid());
	    	titanOrgCheckLog.setResultkey(newOrgCheckResultEnum.getResultkey());
	    	titanOrgCheckLog.setResultmsg(newOrgCheckResultEnum.getResultmsg());
	    	titanOrgCheckLog.setUserid(newOrgEntity.getUserid());
	    	titanOrgCheckLog.setOptuser(organCheckRequest.getOperator());
	    	titanOrgCheckLog.setOpttime(nowDate);
	    	
	    	titanOrgCheckLogDao.insert(titanOrgCheckLog);
	    	
	    	// 审核通过创建账号 account
	    	if(organCheckRequest.getCheckstatus()==1){
	    		PaginationSupport<TitanAccount> accountPage = new PaginationSupport<TitanAccount>();
	        	TitanAccountParam condition = new TitanAccountParam();
	        	//如何确定该机构的账号还没有创建，唯一性
	        	condition.setUserid(newOrgEntity.getUserid());
	        	titanAccountDao.selectForPage(condition, accountPage);
	        	if(CollectionUtils.isEmpty(accountPage.getItemList())){
	        		//融数创建账号
	        		BaseResponse baseResponse = registerRSOrg(organCheckRequest.getOrgId());
	        		TitanOrgCheckLog titanOrgCheckLog2 = new TitanOrgCheckLog();
	        		titanOrgCheckLog2.setCheckid(titanOrgCheck.getCheckid());
	        		titanOrgCheckLog2.setConstid(newOrgEntity.getConstid());
	        		titanOrgCheckLog2.setUserid(newOrgEntity.getUserid());
	        		
	        		titanOrgCheckLog2.setUserid(newOrgEntity.getUserid());
	        		titanOrgCheckLog2.setOptuser(CommonConstant.CHECK_ADMIN_RS);
	        		titanOrgCheckLog2.setOpttime(nowDate);
	        		
	        		if(CommonConstant.OPERATE_SUCCESS.equals(baseResponse.getOperateStatus())){
	            		TitanAccount account = new TitanAccount();
	            		account.setUserid(newOrgEntity.getUserid());
	            		account.setAccountcode(titanCodeCenterService.createTitanAccountCode());
	            		account.setAccountname(newOrgEntity.getUserid());
	            		account.setAllownopwdpay(0);
	            		account.setCreditamount(0d);
	            		account.setNopwdpaylimit(CommonConstant.NO_PWD_PAY_LIMIT);
	            		account.setSettleamount(0d);
	            		account.setForzenamount(0d);
	            		account.setBalanceoverlimit(0d);
	            		account.setUsableamount(0d);
	            		account.setTotalamount(0d);
	            		account.setCreator(organCheckRequest.getOperator());
	            		account.setCreatetime(nowDate);
	            		titanAccountDao.insert(account);
	            		
	            		CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest(); 
	            		cashierDeskInitRequest.setUserId(newOrgEntity.getUserid());
	            		cashierDeskInitRequest.setConstId(CommonConstant.RS_FANGCANG_CONST_ID);
	            		
	            		titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
	            		
	            		titanOrgCheck.setResultkey(OrgCheckResultEnum.PASS.getResultkey());
	            		titanOrgCheck.setResultmsg(OrgCheckResultEnum.PASS.getResultmsg());
	            		//titanOrgCheckLog2.setResultkey(OrgCheckResultEnum.PASS.getResultkey());
	            		//titanOrgCheckLog2.setResultmsg(OrgCheckResultEnum.PASS.getResultmsg());
	            		//titanOrgCheckLogDao.insert(titanOrgCheckLog2);
	            		titanOrgCheckDao.update(titanOrgCheck);
	        		}else{//失败抛出异常
	        			String rsmsg  = baseResponse.getReturnMsg();
	        			titanOrgCheck.setCheckuser(CommonConstant.CHECK_ADMIN_RS);
		        		titanOrgCheck.setChecktime(nowDate);
	        			titanOrgCheck.setResultkey(OrgCheckResultEnum.REVIEW.getResultkey());
	        			titanOrgCheck.setResultmsg(rsmsg);
	        			//titanOrgCheckLog2.setResultkey(OrgCheckResultEnum.REVIEW.getResultkey());
	        			//titanOrgCheckLog2.setResultmsg(rsmsg);
		        		//titanOrgCheckLogDao.insert(titanOrgCheckLog2);
		        		titanOrgCheckDao.update(titanOrgCheck);
	        			LOGGER.error("调用融数接口rsOrganizationManager.resigterPersonOrg 失败,request orgId:"+organCheckRequest.getOrgId()+",rs return content[baseResponse]:"+JSONSerializer.toJSON(baseResponse).toString()+"。 organCheckRequest:"+JSONSerializer.toJSON(organCheckRequest).toString()+",response:"+JSONSerializer.toJSON(response).toString());
	        			response.putErrorResult(baseResponse.getReturnMsg());
	        			return response;
	        			//throw new MessageServiceException("融数账户创建失败,错误信息："+baseResponse.getReturnMsg());
	        		}
	        		
	        	}else{
	        		titanOrgCheckDao.update(titanOrgCheck);
	        		LOGGER.info("机构审核时， t_tfs_account插入失败,errormsg:该账户已经创建，不需要重复创建,param:"+JSONSerializer.toJSON(organCheckRequest).toString()); 
	        	}
	    	}else{
	    		titanOrgCheckDao.update(titanOrgCheck);
	    	}
	    	
			
		} catch (MessageServiceException e) {
			throw e;
		}catch (Exception e) {
			LOGGER.error("机构审核 失败 , param is organCheckRequest:"+JSONSerializer.toJSON(organCheckRequest).toString());
			throw new GlobalServiceException("融数账户创建失败",e);
		}
    	
    	response.putSuccess("操作成功");
    	return response;
    }
	
	/***
	 * 
	 * @param checkStatus 通过 -1， 不通过-0
	 * @param lastState 审核前的状态
	 * @return
	 */
	private OrgCheckResultEnum convertCheckResultEnum(int checkStatus){
		if(checkStatus==0){
			return OrgCheckResultEnum.FT_INVALID;
		}else if(checkStatus==1){
			return OrgCheckResultEnum.PASS;
		}
		return OrgCheckResultEnum.PASS;
	}
	/***
	 * 金服机构账户开户
	 * @param tfsOrgId
	 * @return 成功
	 */
	private BaseResponse registerRSOrg(int tfsOrgId){
		PaginationSupport<TitanOrg> pagePaginationSupport = new PaginationSupport<TitanOrg>();
    	TitanOrgParam orgParam = new TitanOrgParam();
    	orgParam.setOrgid(tfsOrgId);
    	titanOrgDao.selectForPage(orgParam, pagePaginationSupport);
    	TitanOrg newOrg = pagePaginationSupport.getItemList().get(0);
		if(newOrg.getUsertype()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
			//企业账户
			CompanyOrgRegRequest companyOrgRegRequest = new CompanyOrgRegRequest();
			companyOrgRegRequest.setCompanyname(newOrg.getOrgname());
			companyOrgRegRequest.setBuslince(newOrg.getBuslince());
			companyOrgRegRequest.setUserid(newOrg.getUserid());
			companyOrgRegRequest.setUsertype(String.valueOf(newOrg.getUsertype()));
			companyOrgRegRequest.setConstid(newOrg.getConstid());
			companyOrgRegRequest.setProductid(newOrg.getProductid());
			companyOrgRegRequest.setUsername(newOrg.getUsername());
			
			CompanyOrgRegResponse response = rsOrganizationManager.resigterCompanyOrg(companyOrgRegRequest);
			return response;
			
		}else if(newOrg.getUsertype()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			//个人账户
			PersonOrgRegRequest personOrgRegRequest = new PersonOrgRegRequest();
			personOrgRegRequest.setPersonchnname(newOrg.getOrgname());
			personOrgRegRequest.setCertificatetype(String.valueOf(newOrg.getCertificatetype()));
			personOrgRegRequest.setCertificatenumber(newOrg.getCertificatenumber());
			personOrgRegRequest.setUserid(newOrg.getUserid());
			personOrgRegRequest.setConstid(newOrg.getConstid());
			personOrgRegRequest.setProductid(newOrg.getProductid());
			personOrgRegRequest.setOpertype("1");
			
			PersonOrgRegResponse personResponse = rsOrganizationManager.resigterPersonOrg(personOrgRegRequest);
			return personResponse;
		}
		
		return new BaseResponse();
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public OrganImageUploadResponse uploadFinancialOrganImages(OrganImageUploadRequest request) throws GlobalServiceException {
    	OrganImageUploadResponse response = new OrganImageUploadResponse();
    	InputStream newInputStream = null;
    	InputStream inStream = new ByteArrayInputStream(request.getFileBytes()); 
    	try {
    		String imgIdString = "";
            //原图
        	String _fileName = request.getFileName();
        	String suffix = ImageIOExtUtil.getFileSuffix(_fileName);
        	newInputStream = ImageIOExtUtil.createCycleInputStream(inStream);
        	int imageId = uploadOrgImg(_fileName,newInputStream,request.getUserId(),request.getOrgCode(),request.getImageType(),0);
        	imgIdString = imageId +",";
        	
        	//10号图
        	newInputStream.reset();
        	_fileName = ImageIOExtUtil.addImgSize(request.getFileName(), ImgSizeEnum.SIZE_10.getIndex());
        	InputStream _inputStream = ImageResizer.sizeImgInputStream(newInputStream, ImgSizeEnum.SIZE_10.getWidth(), ImgSizeEnum.SIZE_10.getHeight(),suffix);
        	imageId = uploadOrgImg(_fileName,_inputStream,request.getUserId(),request.getOrgCode(),request.getImageType(),ImgSizeEnum.SIZE_10.getIndex());
        	imgIdString = imgIdString + imageId +",";
         	
        	//50号图
        	newInputStream.reset();
        	_fileName = ImageIOExtUtil.addImgSize(request.getFileName(), ImgSizeEnum.SIZE_50.getIndex());
        	_inputStream = ImageResizer.sizeImgInputStream(newInputStream, ImgSizeEnum.SIZE_50.getWidth(), ImgSizeEnum.SIZE_50.getHeight(),suffix);
        	imageId = uploadOrgImg(_fileName,_inputStream,request.getUserId(),request.getOrgCode(),request.getImageType(),ImgSizeEnum.SIZE_50.getIndex());
        	imgIdString = imgIdString + imageId ;
        	
            response.setImageId(imgIdString);
            response.putSuccess();
		} catch (Exception e) {
			throw new GlobalServiceException("upload org img error,param:"+JSONSerializer.toJSON(request).toString(),e);
		}finally{
			if(inStream!=null){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(newInputStream!=null){
				try {
					newInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	
    	return response;
    }
    
    
    @Override
	public OrganImageResponse getOrganImage(OrganImageRequest request) {
    	TitanOrgImageParam condition = new TitanOrgImageParam();
    	condition.setImageid(request.getImageid());
    	condition.setImagetype(request.getImagetype());
    	condition.setSizetype(request.getSizetype());
    	condition.setIsactive(request.getIsactive());
    	PaginationSupport<TitanOrgImage> paginationSupport = new PaginationSupport<TitanOrgImage>();
    	paginationSupport.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
    	paginationSupport = titanOrgImageDao.selectForPage(condition, paginationSupport);
    	OrganImageResponse response = new OrganImageResponse();
    	List<OrgImageInfo> orgImageInfoList = new ArrayList<OrgImageInfo>();
    	for( TitanOrgImage itemImage : paginationSupport.getItemList()){
    		OrgImageInfo item = new OrgImageInfo();
    		item.setImageName(itemImage.getImageName());
    		item.setImageType(itemImage.getImagetype());
    		item.setImageURL(itemImage.getImageurl());
    		item.setSizeType(itemImage.getSizetype());
    		orgImageInfoList.add(item);
    	}
    	response.setOrgImageInfoList(orgImageInfoList);
		return response;
	}

	/***
     * 上传图片
     * @param fileName 格式如：123.jpg
     * @param inputStream
     * @param userId
     * @param orgCode
     * @param imageType
     * @param sizeType
     * @return
     */
    private int uploadOrgImg(String fileName,InputStream inputStream, String userId,String orgCode,int imageType,int sizeType){
    	String imgUrl = FtpUtil.uploadStreamExt(fileName, inputStream, FtpUtil.UPLOAD_PATH_ORG_REGISTER+"/"+FtpUtil.getFormatDate());
    	// 拼图片url，保存到数据库
        TitanOrgImage entity = new TitanOrgImage();
        entity.setUserid(userId);
        entity.setOrgcode(orgCode);
        entity.setImageName(TitanOrgImageEnum.ImageType.getEnumByKey(imageType).getDes());
        entity.setImagetype(imageType);
        entity.setSizetype(sizeType);
        entity.setImageurl(imgUrl);
        entity.setIsactive(1);
        entity.setCreatetime(new Date());
        titanOrgImageDao.insert(entity);
    	return entity.getImageid();
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public OrganFreezeResponse freezeFinancialOrgan(OrganFreezeRequest request) throws GlobalServiceException {
		OrganFreezeResponse response = new OrganFreezeResponse();
		if (!GenericValidate.validate(request)){
			response.putParamError();
			return response;
		}
		
		try {
			if(request.getOperateType()==OrganFreezeRequest.OPERATETYPE_FREEZE_2){//冻结
			    // 冻结金服机构
				TitanOrg param = new TitanOrg();
				param.setUserid(request.getUserid());
				param.setStatusid(TitanOrgEnum.StatusId.FREEZE.getKey());
				titanOrgDao.update(param);
				
				// 调用融数接口，冻结机构
	        	AccountFreezeRequest accountFreezeRequest = new AccountFreezeRequest();
	        	accountFreezeRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
	        	accountFreezeRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
	        	accountFreezeRequest.setUserid(request.getUserid());
	        	accountFreezeRequest.setUsertype(String.valueOf(request.getUsertype()));
	        	AccountFreezeResponse accountFreezeResponse = rsAccountManager.freezeAccount(accountFreezeRequest);
	        	if(!CommonConstant.OPERATE_SUCCESS.equals(accountFreezeResponse.getOperateStatus())){
	        		// 融数操作失败，回滚金服数据
	        		throw new GlobalServiceException("融数冻结账户接口失败，param:"+JSONSerializer.toJSON(accountFreezeRequest).toString()+",response:"+JSONSerializer.toJSON(accountFreezeResponse).toString());
	        	}
	        	
			}else if(request.getOperateType()==OrganFreezeRequest.OPERATETYPE_UNFREEZE_1){//解冻
				 // 解冻金服机构
				TitanOrg param = new TitanOrg();
				param.setUserid(request.getUserid());
				param.setStatusid(TitanOrgEnum.StatusId.AVAILABLE.getKey());
				titanOrgDao.update(param);
				//调用融数接口，解冻结构
				AccountUnFreezeRequest accountUnFreezeRequest = new AccountUnFreezeRequest();
				accountUnFreezeRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				accountUnFreezeRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
				accountUnFreezeRequest.setUserid(request.getUserid());
				accountUnFreezeRequest.setUsertype(String.valueOf(request.getUsertype()));
				AccountUnFreezeResponse accountFreezeResponse = rsAccountManager.unFreezeAccount(accountUnFreezeRequest);
				if(!CommonConstant.OPERATE_SUCCESS.equals(accountFreezeResponse.getOperateStatus())){
	        		// 融数操作失败，回滚金服数据
	        		throw new GlobalServiceException("融数解冻账户接口失败，param:"+JSONSerializer.toJSON(accountUnFreezeRequest).toString()+",response:"+JSONSerializer.toJSON(accountFreezeResponse).toString());
	        	}
			}
		} catch (Exception e) {
			throw new GlobalServiceException(e.getMessage(),e);
		}
		//TODO 操作成功，加入操作日志
		response.putSuccess();
	    return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public OrganBindResponse bindFinancialOrgan(OrganBindRequest request) throws MessageServiceException,GlobalServiceException {
    	OrganBindResponse response = new OrganBindResponse();
    	if (!GenericValidate.validate(request)){
			response.putParamError();
			return response;
		}
    	
    	LOGGER.info("机构绑定解绑操作bindFinancialOrgan："+JSONSerializer.toJSON(request).toString());
    	try {
    		//  1- 是否是管理员
        	
        	if(request.getOperateType()==0){
        		//解除机构绑定
        		TitanOrgBindinfo bindinfo = new TitanOrgBindinfo();
        		bindinfo.setUserid(request.getUserId());
        		bindinfo.setMerchantcode(request.getMerchantCode());
        		bindinfo.setBindstatus(TitanOrgBindinfoEnum.BindStatus.NOT_BIND.getKey());
            	bindinfo.setModifier(request.getOperator());
            	bindinfo.setModifytime(new Date());
    			titanOrgBindinfoDao.update(bindinfo);
        		// 解除机构下的用户绑定
    			FinancialUserUnBindRequest financialUserUnBindRequest = new FinancialUserUnBindRequest();
    			financialUserUnBindRequest.setMerchantcode(request.getMerchantCode());
    			financialUserUnBindRequest.setOperator(request.getOperator());
    			FinancialUserUnBindResponse financialUserUnBindResponse = titanFinancialUserService.unbindFinancialUser(financialUserUnBindRequest);
    			if(financialUserUnBindResponse.isResult()==false){
            		throw new MessageServiceException(financialUserUnBindResponse.getReturnCode(),financialUserUnBindResponse.getReturnMessage());
            	}
        	}else if(request.getOperateType()==1){
        		//添加绑定
        		//校验绑定机构的管理员账号用户名和密码
        		TitanUserParam condition= new TitanUserParam();
        		condition.setUserid(request.getUserId());
        		condition.setIsadmin(1);
        		condition.setStatus(1);
        		PaginationSupport<TitanUser> userPage = new PaginationSupport<TitanUser>();
        		titanUserDao.selectForPage(condition, userPage);
        		TitanUser titanAdminUser = null;
        		if(userPage.getItemList().size()>0){
        			titanAdminUser = userPage.getItemList().get(0);
        			if(titanAdminUser.getUserloginname().equals(request.getUserloginname())&&titanAdminUser.getPassword().equals(MD5.MD5Encode(request.getPassword()))){
        			}else{
        				throw new MessageServiceException("绑定的用户名或密码不正确");
        			}
        		}else{
        			throw new MessageServiceException("绑定的用户名或密码不正确");
        		}
        		
        		TitanOrgBindinfoParam param = new TitanOrgBindinfoParam();
            	param.setUserid(request.getUserId());
            	param.setBindstatus(TitanOrgBindinfoEnum.BindStatus.BIND.getKey());
            	PaginationSupport<TitanOrgBindinfo> existBindPage = new PaginationSupport<TitanOrgBindinfo>();
            	titanOrgBindinfoDao.selectForPage(param, existBindPage);
            	if(existBindPage.getItemList().size()>0){
            		throw new MessageServiceException("不能重复绑定，请先解除已有绑定，再尝试该绑定操作");
            	}
            	TitanOrgBindinfoParam param2 = new TitanOrgBindinfoParam();
        		param2.setUserid(request.getUserId());
        		param2.setMerchantcode(request.getMerchantCode());
            	PaginationSupport<TitanOrgBindinfo> bindPage = new PaginationSupport<TitanOrgBindinfo>();
            	titanOrgBindinfoDao.selectForPage(param2, bindPage);
            	if(bindPage.getItemList().size()>0){
            		//修改绑定关系
            		TitanOrgBindinfo bindinfo = bindPage.getItemList().get(0);
            		bindinfo.setUserid(request.getUserId());
            		bindinfo.setBindstatus(TitanOrgBindinfoEnum.BindStatus.BIND.getKey());
                	bindinfo.setModifier(request.getOperator());
                	bindinfo.setModifytime(new Date());
        			titanOrgBindinfoDao.update(bindinfo);
            	}else{
            		//增加新绑定关系
            		TitanOrgBindinfo bindinfo = new TitanOrgBindinfo();
            		bindinfo.setUserid(request.getUserId());
            		bindinfo.setOrgcode(request.getUserId());
            		bindinfo.setMerchantcode(request.getMerchantCode());
            		bindinfo.setBindstatus(TitanOrgBindinfoEnum.BindStatus.BIND.getKey());
                	bindinfo.setCreator(request.getOperator());
                	bindinfo.setCreatetime(new Date());
        			titanOrgBindinfoDao.insert(bindinfo);
            	}
            	// 绑定SAAS的管理员
            	FinancialUserBindRequest financialUserBindRequest = new FinancialUserBindRequest();
            	financialUserBindRequest.setFcLoginUserName(request.getFcloginname());
            	financialUserBindRequest.setLoginUserName(titanAdminUser.getUserloginname());
            	financialUserBindRequest.setFcuserid(request.getFcuserid());
            	financialUserBindRequest.setMerchantCode(request.getMerchantCode());
            	financialUserBindRequest.setUserid(titanAdminUser.getTfsuserid());
            	financialUserBindRequest.setOperator("SAAS-"+request.getFcloginname());
            	FinancialUserBindResponse financialUserBindResponse = titanFinancialUserService.bindFinancialUser(financialUserBindRequest);
        		if(financialUserBindResponse.isResult()==false){
            		throw new MessageServiceException(financialUserBindResponse.getReturnCode(),financialUserBindResponse.getReturnMessage());
            	}
        	}
		} catch (MessageServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalServiceException("绑定关系失败,param:"+JSONSerializer.toJSON(request).toString(), e);
		}
    	//TODO  操作成功，写入操作日志表 
    	response.putSuccess();
     	return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO updateOrg(OrgUpdateRequest orgUpdateRequest) throws GlobalServiceException {
    	BaseResponseDTO responseDTO = new BaseResponseDTO();
    	if(!StringUtil.isValidString(orgUpdateRequest.getOrgCode())){
    		responseDTO.putParamError();
    		return responseDTO;
    	}
    	
    	TitanOrg titanOrg = new TitanOrg();
    	titanOrg.setOrgcode(orgUpdateRequest.getOrgCode());
    	titanOrg.setConnect(orgUpdateRequest.getConnect());
    	titanOrg.setMobiletel(orgUpdateRequest.getMobiletel());
    	try {
    		titanOrgDao.update(titanOrg);
    		responseDTO.putSuccess();
		} catch (DaoException e) {
			throw new GlobalServiceException("更新机构信息失败,param,orgUpdateRequest:"+JSONSerializer.toJSON(orgUpdateRequest).toString(), e);
		}
		
		return responseDTO;
	}

	public RSInvokeConfigDao getRsInvokeConfigDao() {
        return rsInvokeConfigDao;
    }

    public void setRsInvokeConfigDao(RSInvokeConfigDao rsInvokeConfigDao) {
        this.rsInvokeConfigDao = rsInvokeConfigDao;
    }

    public RSOrganizationManager getRsOrganizationManager() {
        return rsOrganizationManager;
    }

    public void setRsOrganizationManager(RSOrganizationManager rsOrganizationManager) {
        this.rsOrganizationManager = rsOrganizationManager;
    }
    public TitanOrgDao getTitanOrgDao() {
		return titanOrgDao;
	}

	public void setTitanOrgDao(TitanOrgDao titanOrgDao) {
		this.titanOrgDao = titanOrgDao;
	}

	@Override
	public OrgBindInfo queryOrgBindInfoByUserid(OrgBindInfo orgBindInfo) {
		try{
			TitanOrgBindinfo titanOrgBindinfo = new TitanOrgBindinfo();
			titanOrgBindinfo.setUserid(orgBindInfo.getUserid());
			titanOrgBindinfo.setMerchantcode(orgBindInfo.getMerchantCode());
			titanOrgBindinfo.setOrgcode(orgBindInfo.getOrgcode());
		    List<TitanOrgBindinfo> titanOrgBindinfoList =  titanOrgBindinfoDao.selectTitanOrgBindinfoByUserid(titanOrgBindinfo);
			if(titanOrgBindinfoList !=null && titanOrgBindinfoList.size()==1){
				return convertOrgBindInfo(titanOrgBindinfoList.get(0));
			}
		    
		}catch(Exception e){
			LOGGER.error("查询绑定机构信息失败"+e.getMessage(), e);
		}
		return null;
	}
	
	private OrgBindInfo convertOrgBindInfo(TitanOrgBindinfo titanOrgBindinfo){
		if(titanOrgBindinfo !=null){
			OrgBindInfo orgBindInfo = new OrgBindInfo();
			orgBindInfo.setBindStatus(titanOrgBindinfo.getBindstatus());
			orgBindInfo.setMerchantCode(titanOrgBindinfo.getMerchantcode());
			orgBindInfo.setMerchantName(titanOrgBindinfo.getMerchantname());
			orgBindInfo.setUserid(titanOrgBindinfo.getUserid());
			orgBindInfo.setOrgcode(titanOrgBindinfo.getOrgcode());
			return orgBindInfo;
		}
		return null;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public GetCheckCodeResponse getCheckCode(GetCheckCodeRequest getCheckCodeRequest) throws GlobalServiceException {
		GetCheckCodeResponse response = new GetCheckCodeResponse();
		if (!GenericValidate.validate(getCheckCodeRequest)){
    		LOGGER.info("参数错误， getCheckCodeRequest："+JSONSerializer.toJSON(getCheckCodeRequest).toString());
			response.putParamError();
			return response;
		}
		TitanCheckCodeParam condition = new TitanCheckCodeParam();
		condition.setReceiveAddress(getCheckCodeRequest.getReceiveAddress());
		condition.setIsactive(TitanCheckCodeEnum.Isactive.ACTIVE.getKey());
		PaginationSupport<TitanCheckCode> paginationSupport= new PaginationSupport<TitanCheckCode>();
		paginationSupport.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		paginationSupport.setOrderBy(" expiredTime desc");
		paginationSupport = checkCodeDao.selectForPage(condition, paginationSupport);
		Date now = new Date();
		try {
			if(paginationSupport.getItemList().size()>0){
				//取最新的一个记录
//				TitanCheckCode titanCheckCode = paginationSupport.getItemList().get(0);
//				if(now.before(titanCheckCode.getExpiredTime())){
//					//验证码可以用
//					response.setCheckCode(titanCheckCode.getCode());
//					response.putSuccess("可以使用");
//					return response;
//				}else{
					//如果过期则更改状态
					TitanCheckCode entityUpdate = new TitanCheckCode();
					//entityUpdate.setCodeId(titanCheckCode.getCodeId());
					entityUpdate.setReceiveAddress(getCheckCodeRequest.getReceiveAddress());
					entityUpdate.setIsactive(TitanCheckCodeEnum.Isactive.NOT_ACTIVE.getKey());
					checkCodeDao.update(entityUpdate);
				//}
			}
			//生成一个新的验证码
			Date expiredTime = DateUtils.addHours(now, CommonConstant.CODE_TIME_OUT_HOUR);
			TitanCheckCode newCheckCode = new TitanCheckCode();
			newCheckCode.setReceiveAddress(getCheckCodeRequest.getReceiveAddress());
			newCheckCode.setCode(Tools.getRegCode());
			newCheckCode.setCodeType(getCheckCodeRequest.getMsgType());
			newCheckCode.setIsactive(TitanCheckCodeEnum.Isactive.ACTIVE.getKey());
			newCheckCode.setCreateTime(now);
			newCheckCode.setExpiredTime(expiredTime);
			checkCodeDao.insert(newCheckCode);
			
			response.setCheckCode(newCheckCode.getCode());
			response.putSuccess("验证码获取成功");
			return response;
		} catch (DaoException e) {
			throw new GlobalServiceException("获取验证码失败，param参数getCheckCodeRequest："+JSONSerializer.toJSON(getCheckCodeRequest).toString(),e);
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public VerifyCheckCodeResponse verifyCheckCode(VerifyCheckCodeRequest verifyCheckCodeRequest) {
		VerifyCheckCodeResponse response = new VerifyCheckCodeResponse();
		if (!GenericValidate.validate(verifyCheckCodeRequest)){
    		LOGGER.info("参数错误， verifyCheckCodeRequest："+JSONSerializer.toJSON(verifyCheckCodeRequest).toString());
			response.putParamError();
			return response;
		}
		TitanCheckCodeParam condition = new TitanCheckCodeParam();
		condition.setReceiveAddress(verifyCheckCodeRequest.getReceiveAddress());
		condition.setIsactive(TitanCheckCodeEnum.Isactive.ACTIVE.getKey());
		PaginationSupport<TitanCheckCode> paginationSupport= new PaginationSupport<TitanCheckCode>();
		paginationSupport.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		paginationSupport.setOrderBy(" expiredTime desc");
		paginationSupport = checkCodeDao.selectForPage(condition, paginationSupport);
		Date now = new Date();
		if(paginationSupport.getItemList().size()>0){
			//取最新的一个记录
			TitanCheckCode titanCheckCode = paginationSupport.getItemList().get(0);
			if(now.before(titanCheckCode.getExpiredTime())){
				//判断验证码是否正确
				if(titanCheckCode.getCode().equals(verifyCheckCodeRequest.getInputCode())){
					response.setCodeId(titanCheckCode.getCodeId());
					response.setReturnCode("SUCCESS");
					response.setResult(true);
				}else{
					response.setReturnCode("WRONG");
					response.setResult(false);
				}
				return response;
			}else{
				//如果过期则更改状态
				TitanCheckCode entityUpdate = new TitanCheckCode();
				entityUpdate.setCodeId(titanCheckCode.getCodeId());
				entityUpdate.setIsactive(TitanCheckCodeEnum.Isactive.NOT_ACTIVE.getKey());
				checkCodeDao.update(entityUpdate);
				response.setReturnCode("EXPIRE");
				response.setResult(false);
				return response;
			}
		}else{
			//接收者不存在
			response.setReturnCode("NOTEXIST");
			response.setResult(false);
			return response;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO useCheckCode(UpdateCheckCodeRequest updateCheckCodeRequest) throws  GlobalServiceException{
		BaseResponseDTO response = new BaseResponseDTO();
		if(updateCheckCodeRequest.getCodeId()==null||updateCheckCodeRequest.getIsactive()==null){
			LOGGER.info("参数错误， updateCheckCodeRequest："+ToStringBuilder.reflectionToString(updateCheckCodeRequest).toString());
			response.putParamError();
			return response;
		}
		
		TitanCheckCode entityUpdate = new TitanCheckCode();
		entityUpdate.setCodeId(updateCheckCodeRequest.getCodeId());
		entityUpdate.setIsactive(updateCheckCodeRequest.getIsactive());
		entityUpdate.setUseTime(new Date());
		try {
			int i = checkCodeDao.update(entityUpdate);
			response.putSuccess("状态更新成功");
			return response;
		} catch (DaoException e) {
			throw new GlobalServiceException("更新验证码失败，param参数updateCheckCodeRequest："+ToStringBuilder.reflectionToString(updateCheckCodeRequest).toString(),e);
		}
		
	}

	@Override
	public OrgDTO queryOrg(OrgDTO orgDTO) {
		if(orgDTO !=null){
			TitanOrgParam condition = new TitanOrgParam();
			condition.setOrgcode(orgDTO.getOrgcode());
			condition.setOrgid(orgDTO.getOrgid());
			condition.setTitancode(orgDTO.getTitancode());
			condition.setOrgname(orgDTO.getOrgname());
			TitanOrg titanOrg = titanOrgDao.selectOne(condition);
			if(titanOrg !=null){
				MyBeanUtil.copyProperties(orgDTO, titanOrg);
			}
			return orgDTO;
		}
		
		return null;
	}

	@Override
	public int countOrg(FinancialOrganQueryRequest financialOrganQueryRequest) {
		return titanOrgDao.countOrg(financialOrganQueryRequest);
	}
	
	
}
