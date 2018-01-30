package com.fangcang.titanjr.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

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
import com.fangcang.exception.ParameterException;
import com.fangcang.titanjr.common.enums.BindStatusOrgEnum;
import com.fangcang.titanjr.common.enums.CoopTypeEnum;
import com.fangcang.titanjr.common.enums.ImgSizeEnum;
import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.RegSourceEnum;
import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.enums.entity.TitanCheckCodeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgBindinfoEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum.UserType;
import com.fangcang.titanjr.common.enums.entity.TitanOrgImageEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.ImageIOExtUtil;
import com.fangcang.titanjr.common.util.ImageResizer;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.NumberPrefix;
import com.fangcang.titanjr.common.util.SMSTemplate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.rsa.RSAUtil;
import com.fangcang.titanjr.dao.TitanAccountDao;
import com.fangcang.titanjr.dao.TitanBankcardDao;
import com.fangcang.titanjr.dao.TitanCheckCodeDao;
import com.fangcang.titanjr.dao.TitanOpenOrgDao;
import com.fangcang.titanjr.dao.TitanOrgBindinfoDao;
import com.fangcang.titanjr.dao.TitanOrgCheckDao;
import com.fangcang.titanjr.dao.TitanOrgCheckLogDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dao.TitanOrgImageDao;
import com.fangcang.titanjr.dao.TitanOrgMapInfoDao;
import com.fangcang.titanjr.dao.TitanOrgSubDao;
import com.fangcang.titanjr.dao.TitanRoleDao;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgBindInfoDTO;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;
import com.fangcang.titanjr.dto.bean.OrgSubDTO;
import com.fangcang.titanjr.dto.bean.RSOrg;
import com.fangcang.titanjr.dto.bean.TitanOpenOrgDTO;
import com.fangcang.titanjr.dto.request.BalanceInfoRequest;
import com.fangcang.titanjr.dto.request.CancelOrgBindRequest;
import com.fangcang.titanjr.dto.request.CashierDeskInitRequest;
import com.fangcang.titanjr.dto.request.DeleteBindUserRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.FinancialUserBindRequest;
import com.fangcang.titanjr.dto.request.FinancialUserUnBindRequest;
import com.fangcang.titanjr.dto.request.GetCheckCodeRequest;
import com.fangcang.titanjr.dto.request.OperateLogRequest;
import com.fangcang.titanjr.dto.request.OrgBaseInfoRequest;
import com.fangcang.titanjr.dto.request.OrgRegisterValidateRequest;
import com.fangcang.titanjr.dto.request.OrgSubRequest;
import com.fangcang.titanjr.dto.request.OrgUpdateRequest;
import com.fangcang.titanjr.dto.request.OrganBindRequest;
import com.fangcang.titanjr.dto.request.OrganCheckRequest;
import com.fangcang.titanjr.dto.request.OrganFreezeRequest;
import com.fangcang.titanjr.dto.request.OrganImageRequest;
import com.fangcang.titanjr.dto.request.OrganImageUploadRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterUpdateRequest;
import com.fangcang.titanjr.dto.request.RegOrgSubRequest;
import com.fangcang.titanjr.dto.request.SendMessageRequest;
import com.fangcang.titanjr.dto.request.UpdateCheckCodeRequest;
import com.fangcang.titanjr.dto.request.UserBindInfoRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
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
import com.fangcang.titanjr.dto.response.UserBindInfoResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserRegisterResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.entity.TitanAccount;
import com.fangcang.titanjr.entity.TitanCheckCode;
import com.fangcang.titanjr.entity.TitanOpenOrg;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.TitanOrgBindinfo;
import com.fangcang.titanjr.entity.TitanOrgCheck;
import com.fangcang.titanjr.entity.TitanOrgCheckLog;
import com.fangcang.titanjr.entity.TitanOrgImage;
import com.fangcang.titanjr.entity.TitanOrgMapInfo;
import com.fangcang.titanjr.entity.TitanOrgSub;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.entity.parameter.TitanAccountParam;
import com.fangcang.titanjr.entity.parameter.TitanCheckCodeParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgBindinfoParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgCheckParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgImageParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgMapInfoParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgSubParam;
import com.fangcang.titanjr.entity.parameter.TitanUserParam;
import com.fangcang.titanjr.enums.OperateTypeOperateLogEnum;
import com.fangcang.titanjr.rs.manager.RSAccountManager;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.request.AccountFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountUnFreezeRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgRegRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgUpdateRequest;
import com.fangcang.titanjr.rs.request.PersOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.PersonOrgRegRequest;
import com.fangcang.titanjr.rs.request.PersonOrgUpdateRequest;
import com.fangcang.titanjr.rs.response.AccountFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountUnFreezeResponse;
import com.fangcang.titanjr.rs.response.BaseResponse;
import com.fangcang.titanjr.rs.response.CompanyOrgRegResponse;
import com.fangcang.titanjr.rs.response.PersOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.PersonOrgRegResponse;
import com.fangcang.titanjr.rs.response.PersonOrgUpdateResponse;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.dto.OpenAccountPerson;

import net.sf.json.JSONSerializer;
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
	private TitanOrgMapInfoDao titanOrgMapInfoDao;
	@Resource
    private TitanOrgSubDao orgSubDao;
    @Resource
    private TitanUserDao titanUserDao;
    @Resource
    private TitanOrgImageDao titanOrgImageDao;
    @Resource
    private TitanOrgBindinfoDao titanOrgBindinfoDao;
    @Resource
    private TitanCodeCenterService titanCodeCenterService;
    @Resource
    private RSOrganizationManager rsOrganizationManager;

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
    
    @Resource
    private TitanFinancialAccountService accountService;
    @Resource
	private TitanFinancialSendSMSService smsService;
    @Resource
    private TitanOpenOrgDao titanOpenOrgDao;
    @Resource
    private BusinessLogService businessLog;
    @Resource
    private TitanBankcardDao bankcardDao;

    @Override
    public FinancialOrganResponse queryFinancialOrgan(FinancialOrganQueryRequest request) {
    	FinancialOrganResponse response = new FinancialOrganResponse();
        try {
        	if(request.getOrgId()==null&&!StringUtil.isValidString(request.getOrgCode())&&!StringUtil.isValidString(request.getUserId())&&!StringUtil.isValidString(request.getMerchantcode())){
        		response.putErrorResult("参数错误，必填参数不能为空");
        		return response;
        	}
        	//通过合作方商家编码查询的，默认为SAAS
        	if(StringUtil.isValidString(request.getMerchantcode())&&request.getCoopType()==null){
        		request.setCoopType(CoopTypeEnum.SAAS.getKey());
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
	public FinancialOrganResponse queryBaseFinancialOrgan(
			FinancialOrganQueryRequest request) {
    	FinancialOrganResponse response = new FinancialOrganResponse();
        try {
        	if(request.getOrgId()==null&&!StringUtil.isValidString(request.getOrgCode())&&!StringUtil.isValidString(request.getUserId())&&!StringUtil.isValidString(request.getMerchantcode())){
        		response.putErrorResult("参数错误，必填参数不能为空");
        		return response;
        	}
        	//通过合作方商家编码查询的，默认为SAAS
        	if(StringUtil.isValidString(request.getMerchantcode())&&request.getCoopType()==null){
        		request.setCoopType(CoopTypeEnum.SAAS.getKey());
        	}
        	
            PaginationSupport<FinancialOrganDTO> paginationSupport = new PaginationSupport<FinancialOrganDTO>();
            paginationSupport =  titanOrgDao.queryBaseTitanOrgForPage(request, paginationSupport);
            if(paginationSupport.getItemList()!=null&&paginationSupport.getItemList().size()==1){
            	OrgSubDTO orgSubDTO = getOrgSubDTO(paginationSupport.getItemList().get(0).getOrgCode());
            	response.setOrgSubDTO(orgSubDTO);
            	response.setFinancialOrganDTO(paginationSupport.getItemList().get(0));
            	response.putSuccess();
            }else{
            	LOGGER.error("金融机构查询错误，查询参数FinancialOrganQueryRequest："+Tools.gsonToString(request)+",记录个数："+paginationSupport.getItemList().size());
            	response.putErrorResult("机构结果集个数错误");
            }
		} catch (Exception e) {
			LOGGER.error("查询错误，参数FinancialOrganQueryRequest："+JSONSerializer.toJSON(request).toString(), e);
			response.putSysError();
		}
        
        return response;
	}
    
    private OrgSubDTO getOrgSubDTO(String orgCode){
    	TitanOrgMapInfoParam titanOrgMapInfoParam = new TitanOrgMapInfoParam();
    	titanOrgMapInfoParam.setOrgCode(orgCode);;
    	List<TitanOrgMapInfo> list = titanOrgMapInfoDao.queryList(titanOrgMapInfoParam);
    	if(CollectionUtils.isEmpty(list)||list.size()>1){
    		return null;
    	}
    	TitanOrgSub titanOrgSub = orgSubDao.getOneByOrgCode(list.get(0).getOrgSubcode());
    	OrgSubDTO orgSubDTO = new OrgSubDTO();
    	orgSubDTO.setOrgCode(orgCode);
    	orgSubDTO.setOrgName(titanOrgSub.getOrgname());
    	orgSubDTO.setOrgSubId(titanOrgSub.getOrgsubid());
    	orgSubDTO.setCertificateType(titanOrgSub.getCertificatetype());
    	orgSubDTO.setCertificateNumber(titanOrgSub.getCertificatenumber());
    	orgSubDTO.setBuslince(titanOrgSub.getBuslince());
    	orgSubDTO.setUserType(titanOrgSub.getUsertype());
    	
    	return orgSubDTO;
    }
    
    
    
	@Override
    public OrganQueryCheckResponse queryFinancialOrganForPage(FinancialOrganQueryRequest request) {
    	OrganQueryCheckResponse responsePageDTO = new OrganQueryCheckResponse();
        try {
        	//通过合作方商家编码查询的，默认为SAAS
        	if(StringUtil.isValidString(request.getMerchantcode())&&request.getCoopType()==null){
        		request.setCoopType(CoopTypeEnum.SAAS.getKey());
        	}
            PaginationSupport<OrgCheckDTO> paginationSupport = new PaginationSupport<OrgCheckDTO>();
            paginationSupport.setCurrentPage(request.getCurrentPage());
            paginationSupport.setPageSize(request.getPageSize());
            paginationSupport.setOrderBy(" G.createTime desc ");
            titanOrgDao.queryTitanOrgCheckForPage(request, paginationSupport);

            responsePageDTO.setPaginationSupport(paginationSupport);
            responsePageDTO.putSuccess();
            
		} catch (Exception e) {
			LOGGER.error("查询机构失败,查询参数request："+Tools.gsonToString(request), e);
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
     * 从合作平台过来注册的(saas,ttm)页面注册的
     * @param organRegisterRequest
     * @return 1:成功，-1：参数错误，-2：
     * @throws Exception
     */
    private Map<String, Object> registerFromCoop(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException{
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		OrgBindInfo orgBindInfo = new OrgBindInfo();
            orgBindInfo.setMerchantCode(organRegisterRequest.getMerchantCode());
            orgBindInfo = this.queryOrgBindInfoByUserid(orgBindInfo);
    		if(orgBindInfo!=null&&StringUtil.isValidString(orgBindInfo.getOrgcode())){
    			throw new MessageServiceException("6002","该商家已经开通了金融账号，不允许重复开通");
    		}
    		TitanOrg titanOrg = addOrg(organRegisterRequest);
    		result.put("titanOrg", titanOrg);
    		
    		if(StringUtil.isValidString(organRegisterRequest.getImageid())){
    			updateOrgImg(organRegisterRequest.getImageid(),organRegisterRequest.getOrgCode());
    		}
    		
	    	// 注册员工
    		UserRegisterResponse registerResponse = registerUser(organRegisterRequest);
    		
    		if(registerResponse.isResult()==false){
        		throw new MessageServiceException(registerResponse.getReturnCode(),registerResponse.getReturnMessage());
        	}
    		result.put("tfsUserId", registerResponse.getTfsUserId().toString());
	    	// 绑定关系
	    	int returnCode = addOrgbingInfo(organRegisterRequest.getOrgCode(), organRegisterRequest.getMerchantCode(), organRegisterRequest.getOrgCode(), organRegisterRequest.getMerchantname(),organRegisterRequest.getCoopType());
	    	if(ORGBINGINFO_CODE_EXIST==returnCode){
	    		throw new MessageServiceException("该商家已经绑定过泰坦金融账号，不能重复绑定");
	    	}
    	} catch (MessageServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalServiceException("用户注册失败", e);
		}
    	return result;
    }
    
    /***
     * 从泰坦钱包官网注册
     * @param organRegisterRequest
     * @throws GlobalServiceException 
     * @throws Exception 
     */
    private  Map<String, Object> registerFromJinfuSite(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException{
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
    		TitanOrg titanOrg = addOrg(organRegisterRequest);
    		result.put("titanOrg", titanOrg);
        	updateOrgImg(organRegisterRequest.getImageid(),organRegisterRequest.getOrgCode());
    		// 注册员工
    		UserRegisterResponse registerResponse = registerUser(organRegisterRequest);
    		if(registerResponse.isResult()==false){
        		throw new MessageServiceException(registerResponse.getReturnCode(),registerResponse.getReturnMessage());
        	}
    		result.put("tfsUserId", registerResponse.getTfsUserId().toString());
		} catch (MessageServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalServiceException("用户注册失败", e);
		}
    	return result;
    }
    /**
     * 系统自动注册
     * @param organRegisterRequest
     * @throws Exception 
     */
//    private TitanOrg registerFromAuto(OrganRegisterRequest organRegisterRequest) throws MessageServiceException, GlobalServiceException{
//    	TitanOrg titanOrg = null;
//    	try {
//    		titanOrg = addOrg(organRegisterRequest);
//    		// 注册员工
//    		UserRegisterResponse registerResponse = registerUser(organRegisterRequest);
//    		if(registerResponse.isResult()==false){
//        		throw new MessageServiceException(registerResponse.getReturnCode(),registerResponse.getReturnMessage());
//        	}
//		} catch (MessageServiceException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new GlobalServiceException("用户注册失败", e);
//		}
//    	return titanOrg;
//    }
    /**
     * 更新金服机构图片信息
     * @param imageId  机构有效图片的所有imageid,格式：11,22,33
     * @param orgCode 
     * @throws GlobalServiceException 
     */
    private void updateOrgImg(String imageId,String orgCode) throws GlobalServiceException{
    	if(StringUtils.isNotBlank(imageId)){
    		String[] imgArr = imageId.split(",");
    		//1、把旧的照片删除
    		TitanOrgImageParam orgImageParam = new TitanOrgImageParam();
    		orgImageParam.setOrgcode(orgCode);
			titanOrgImageDao.delete(orgImageParam);
    		//2、新上传的图片置为有效
    		for(String temp: imgArr){
    			int tempImageId = 0;
    			try {
    				tempImageId = NumberUtils.toInt(temp);
				} catch (Exception e) {
					throw new GlobalServiceException("更新金服机构图片信息错误，tempImageId非法",e);
				}
    			
    			TitanOrgImage newTitanOrgImage = new TitanOrgImage();
    			newTitanOrgImage.setImageid(tempImageId);
    			newTitanOrgImage.setOrgcode(orgCode);
    			newTitanOrgImage.setUserid(orgCode);
    			newTitanOrgImage.setIsactive(TitanOrgImageEnum.IsActive.ACTIVE.getKey());
    			newTitanOrgImage.setModifytime(new Date());
    			titanOrgImageDao.update(newTitanOrgImage);
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
    	UserRegisterResponse respose = new UserRegisterResponse();
    	//如果没传用户名，则不添加
    	if(!StringUtil.isValidString(organRegisterRequest.getUserloginname())){
    		LOGGER.error("注册金融机构时，金融用户名[Userloginname],不能为空，机构id(orgcode)为："+organRegisterRequest.getOrgCode());
    		respose.putErrorResult("金融用户名[Userloginname],不能为空");
    		return respose;
    	}
    	
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
    	userRegisterRequest.setOrgCode(organRegisterRequest.getOrgCode());
    	userRegisterRequest.setCoopUserId(organRegisterRequest.getCoopUserId());
    	userRegisterRequest.setPassword(organRegisterRequest.getPassword());
    	userRegisterRequest.setRegisterSource(organRegisterRequest.getRegisterSource());
    	userRegisterRequest.setUserId(organRegisterRequest.getOrgCode());
    	respose = titanFinancialUserService.registerFinancialUser(userRegisterRequest);
    	if(respose.isResult()==false){
    		LOGGER.error("用户注册失败,错误信息："+respose.getReturnMessage()+",param:"+JSONSerializer.toJSON(userRegisterRequest).toString());
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
    	String orgcode = null;
    	if(!StringUtil.isValidString(organRegisterRequest.getOrgCode())){//没有值，则用新生成的
    		orgcode = titanCodeCenterService.createOrgCode();
    		organRegisterRequest.setOrgCode(orgcode);
    	}else{//有传值，就用入参的
    		orgcode = organRegisterRequest.getOrgCode();
    	}
    	
    	titanOrg.setOrgcode(orgcode);
    	titanOrg.setUserid(orgcode);
    	titanOrg.setTitancode(titancode);
    	titanOrg.setStatusid(TitanOrgEnum.StatusId.AVAILABLE.getKey());
    	titanOrg.setUsertype(organRegisterRequest.getUserType());
    	titanOrg.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
    	titanOrg.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    	titanOrg.setCreateTime(new Date());
    	titanOrg.setRegChannel(CoopTypeEnum.getCoopTypeEnum(organRegisterRequest.getRegisterSource()).getKey());
    	titanOrg.setRegSource(organRegisterRequest.getRegisterSource());
    	
    	if(organRegisterRequest.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    		validateEnterpriseParam(organRegisterRequest);
        	titanOrg.setOrgname(organRegisterRequest.getOrgName());
        	titanOrg.setBuslince(organRegisterRequest.getBuslince());
        	titanOrg.setConnect(organRegisterRequest.getConnect());
        	titanOrg.setMobiletel(organRegisterRequest.getMobileTel());
        	
    	}else if(organRegisterRequest.getUserType()==TitanOrgEnum.UserType.PERSONAL.getKey()){
    		validatePersonalParam(organRegisterRequest);
        	titanOrg.setOrgname(organRegisterRequest.getOrgName());
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
    private void validateEnterpriseParam(RegOrgSubRequest regOrgSubRequest) throws ParameterException{
    	if(StringUtil.isValidString(regOrgSubRequest.getBuslince())==false){
    		LOGGER.error("机构注册参数错误,营业执照号(Buslince)不能为空");
    		throw new ParameterException("param error: Buslince can not be  null");
    	}
    	//if(StringUtil.isValidString(regOrgSubRequest.getConnect())==false){
    		//LOGGER.error("机构注册参数错误,联系人(Connect)不能为空");
    		//throw new ParameterException("param error: Connect can not be  null");
    	//}
    	//if(StringUtil.isValidString(regOrgSubRequest.getMobileTel())==false){
    		//LOGGER.error("机构注册参数错误,联系电话(MobileTel)不能为空");
    		//throw new ParameterException("param error: MobileTel can not be  null");
    	//}
    }
    /**
     * 个人机构注册必填参数校验
     * @param organRegisterRequest
     * @throws ParameterException
     */
    private void validatePersonalParam(RegOrgSubRequest regOrgSubRequest) throws ParameterException {
    	if(StringUtil.isValidString(regOrgSubRequest.getCertificateType())==false){
    		throw new ParameterException("param error: CertificateType can not be  null");
    	}
    	if(StringUtil.isValidString(regOrgSubRequest.getCertificateNumber())==false){
    		throw new ParameterException("param error: CertificateNumber can not be  null");
    	}
    	
    }
    /**
     * 金服添加机构绑定关系
     * @throws GlobalServiceException
     */
    private int addOrgbingInfo(String userId,String merchantCode,String orgcode,String merchantname,int coopType) throws Exception{
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
    		orgBindinfo.setCooptype(coopType);
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
     * @param orgUserId
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
    	response.putSuccess("注册成功");
    	RegOrgSubRequest orgSubRequest = null;
    	//注册类型转为合作方类型
    	organRegisterRequest.setCoopType(CoopTypeEnum.getCoopTypeEnum(organRegisterRequest.getRegisterSource()).getKey());
    	
    	String orgcode = titanCodeCenterService.createOrgCode();
    	String orgSubcode = titanCodeCenterService.createOrgSubCode();
    	if(StringUtil.isValidString(organRegisterRequest.getBuslince())||StringUtil.isValidString(organRegisterRequest.getCertificateNumber())){
    		//真实证件
    		orgSubRequest = new RegOrgSubRequest();
    		MyBeanUtil.copyProperties(orgSubRequest, organRegisterRequest);//拷贝真实证件信息
    		orgSubRequest.setOrgCode(orgSubcode);
    		//判断是否可以注册
        	OrgRegisterValidateRequest request = new OrgRegisterValidateRequest();
        	request.setUsertype(organRegisterRequest.getUserType());
        	request.setBuslince(organRegisterRequest.getBuslince());
        	request.setCertificatetype(organRegisterRequest.getCertificateType());
        	request.setCertificateNumber(organRegisterRequest.getCertificateNumber());
        	OrgRegisterValidateResponse orgRegisterValidateResponse = validateOrg(request);
        	if(orgRegisterValidateResponse.isResult()==false){
        		//如果已经注册
        		if(orgRegisterValidateResponse.getOrgDTO()!=null){
        			response.setOrgCode(orgRegisterValidateResponse.getOrgDTO().getOrgcode());
        		}
        		response.putErrorResult(orgRegisterValidateResponse.getReturnMessage());
    			return response;
        	}
    	}
    	organRegisterRequest.setBuslince(orgcode);//使用虚拟营业执照
    	organRegisterRequest.setOrgCode(orgcode);
    	organRegisterRequest.setUserType(1);//1-企业 

    	//---虚拟证件注册，公共参数校验,密码校验
    	if (!GenericValidate.validate(organRegisterRequest)){
    		LOGGER.error("机构注册时，参数错误，organRegisterRequest："+JSONSerializer.toJSON(organRegisterRequest).toString());
			response.putErrorResult("必填参数不能为空");
			return response;
		}
    	
    	Map<String, Object> orgResult = new HashMap<String, Object>();
    	if(organRegisterRequest.getRegisterSource()==RegSourceEnum.SAAS.getType()||organRegisterRequest.getRegisterSource()==RegSourceEnum.TTM.getType()){
    		orgResult = registerFromCoop(organRegisterRequest);
    		addOrgSubMap(orgSubRequest,((TitanOrg)orgResult.get("titanOrg")).getOrgcode());
    		addOrgCheck(organRegisterRequest.getOrgCode(),organRegisterRequest.getOperator());
    	}else if(organRegisterRequest.getRegisterSource()==RegSourceEnum.TWS.getType()){
    		orgResult = registerFromJinfuSite(organRegisterRequest);
    		addOrgSubMap(orgSubRequest,((TitanOrg)orgResult.get("titanOrg")).getOrgcode());
    		addOrgCheck(organRegisterRequest.getOrgCode(),organRegisterRequest.getOperator());
    	}else if(RegSourceEnum.isInterfaceReg(organRegisterRequest.getRegisterSource())){//接口注册，只有注册虚拟证件
    		orgResult = registerFromCoop(organRegisterRequest);
    		addOrgCheck(organRegisterRequest.getOrgCode(),organRegisterRequest.getOperator());
    		//虚拟证件注册的，则自动审核
    		OrganCheckRequest organCheckRequest = new OrganCheckRequest();
    		organCheckRequest.setCheckstatus(1);//1-审核通过,
    		organCheckRequest.setOperator(CommonConstant.CHECK_ADMIN_JR);
    		organCheckRequest.setOrgId(((TitanOrg)orgResult.get("titanOrg")).getOrgid());
    		organCheckRequest.setIsSendSms(false);
    		checkFinancialOrgan(organCheckRequest);
    	}else{
    		LOGGER.error("无法注册，注册来源(registerSource)未知，注册参数："+JSONSerializer.toJSON(organRegisterRequest).toString());
    		response.putErrorResult("-1", "未知的注册来源");
    	}
    	if(!orgResult.isEmpty()){
    		response.setOrgCode(((TitanOrg)orgResult.get("titanOrg")).getOrgcode());
    		response.setTfsUserId(Integer.valueOf(orgResult.get("tfsUserId").toString()));
    	}
    	return response;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public OrganRegisterResponse registerOrgSub(RegOrgSubRequest regOrgSubRequest,String relateOrgCode) throws GlobalServiceException, MessageServiceException{
    	OrganRegisterResponse response = new OrganRegisterResponse();
    	LOGGER.info("开始注册真实机构，注册参数regOrgSubRequest："+Tools.gsonToString(regOrgSubRequest)+",虚拟机构编码relateOrgCode:"+relateOrgCode);
    	//判断是否可以注册
    	if(regOrgSubRequest.getUserType()==null){
    		response.putErrorResult("机构类型不能为空");
    		return response;
    	}
    	if(!StringUtil.isValidString(regOrgSubRequest.getOrgName())){
    		response.putErrorResult("机构名称不能为空");
    		return response;
    	}
    	if(!(StringUtil.isValidString(regOrgSubRequest.getBuslince())||StringUtil.isValidString(regOrgSubRequest.getCertificateNumber()))){
    		response.putErrorResult("证件不能为空");
    		return response;
    	}
    	
    	OrgRegisterValidateRequest request = new OrgRegisterValidateRequest();
    	request.setUsertype(regOrgSubRequest.getUserType());
    	request.setOrgName(regOrgSubRequest.getOrgName());
    	request.setBuslince(regOrgSubRequest.getBuslince());
    	request.setCertificatetype(regOrgSubRequest.getCertificateType());
    	request.setCertificateNumber(regOrgSubRequest.getCertificateNumber());
    	OrgRegisterValidateResponse orgRegisterValidateResponse = validateOrg(request);
    	LOGGER.info("注册真实机构注册信息,虚拟机构编码(relateOrgCode):"+relateOrgCode+",参数校验结果为："+Tools.gsonToString(orgRegisterValidateResponse));
    	if(orgRegisterValidateResponse.isResult()){//证件可用
    		try {
        		String orgSubcode = titanCodeCenterService.createOrgSubCode();
            	regOrgSubRequest.setOrgCode(orgSubcode);
            	//添加机构关联关系
        		TitanOrgSub orgSub = addOrgSubMap(regOrgSubRequest,relateOrgCode);
        		BaseResponse baseResponse = regOrgSubForRS(orgSub.getOrgcode());
        		if(!CommonConstant.OPERATE_SUCCESS.equals(baseResponse.getOperateStatus())){//失败
        			LOGGER.error("调用融数接口注册失败,注册机构参数 orgSubcode:"+orgSubcode+",rs返回信息[baseResponse]:"+JSONSerializer.toJSON(baseResponse).toString());
        			throw new MessageServiceException(baseResponse.getReturnMsg());
        		}
        		response.putSuccess("注册成功");
        		response.setOrgCode(orgSub.getOrgcode());
        		return response;
    		}catch (MessageServiceException ee) {
    			throw ee;
			}catch (Exception e) {
    			LOGGER.error("真实机构注册失败,注册参数："+Tools.gsonToString(regOrgSubRequest)+",relateOrgCode："+relateOrgCode,e);
    			throw new GlobalServiceException("真实机构注册失败",e);
    		}
    	}else if(orgRegisterValidateResponse.getReturnCode().equals("500")){//该证件和姓名（公司名称）已经注册
    		//添加机构关联关系
    		TitanOrgMapInfo orgMapInfo = new TitanOrgMapInfo();
    		orgMapInfo.setOrgCode(relateOrgCode);
    		orgMapInfo.setOrgSubcode(orgRegisterValidateResponse.getOrgDTO().getOrgcode());
    		orgMapInfo.setIsactive(1);//1-有效，2-删除
    		orgMapInfo.setCreateTime(new Date());
    		titanOrgMapInfoDao.insert(orgMapInfo);
    		response.putSuccess("注册成功");
        	return response;
    	}else if(orgRegisterValidateResponse.isResult()==false){
    		response.putErrorResult(orgRegisterValidateResponse.getReturnMessage());
			return response;
    	}
    	LOGGER.error("真实机构注册发生未知异常,注册参数regOrgSubRequest："+Tools.gsonToString(regOrgSubRequest)+",虚拟机构编码relateOrgCode:"+relateOrgCode);
    	response.putErrorResult("未知异常");
    	return response;
    }
    
    /***
     * 注册子机构(真实证件),并添加机构间的关联关系
     * @param regOrgSubRequest 子机构信息
     * @param relateOrgCode 虚拟证件机构编码,如果为空，则不添加关联关系
     * @param isRegRs 是否到融数注册机构，默认为false，即不注册
     * @return
     **/
    private TitanOrgSub addOrgSubMap(RegOrgSubRequest regOrgSubRequest,String relateOrgCode){
    	//本地保存机构
    	TitanOrgSub orgSub = addOrgSub(regOrgSubRequest);
    	if(StringUtil.isValidString(relateOrgCode)){
    		//添加机构关联关系
    		TitanOrgMapInfo orgMapInfo = new TitanOrgMapInfo();
    		orgMapInfo.setOrgCode(relateOrgCode);
    		orgMapInfo.setOrgSubcode(orgSub.getOrgcode());
    		orgMapInfo.setIsactive(1);//1-有效，2-删除
    		orgMapInfo.setCreateTime(new Date());
    		titanOrgMapInfoDao.insert(orgMapInfo);
    	}
		 
    	return orgSub;
    }
    
   
    
    /***
     * 添加orgsub
     * @param regOrgSubRequest
     * @return
     */
    private TitanOrgSub addOrgSub(RegOrgSubRequest regOrgSubRequest){
    	TitanOrgSub orgSub = new TitanOrgSub();
    	orgSub.setOrgcode(regOrgSubRequest.getOrgCode());
    	orgSub.setUsertype(regOrgSubRequest.getUserType());
    	orgSub.setCreateTime(new Date());
    	if(regOrgSubRequest.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    		validateEnterpriseParam(regOrgSubRequest);
    		orgSub.setOrgname(regOrgSubRequest.getOrgName());
    		orgSub.setBuslince(regOrgSubRequest.getBuslince());
        	
    	}else if(regOrgSubRequest.getUserType()==TitanOrgEnum.UserType.PERSONAL.getKey()){
    		validatePersonalParam(regOrgSubRequest);
    		orgSub.setOrgname(regOrgSubRequest.getOrgName());
    		orgSub.setCertificatetype(NumberUtils.toInt(regOrgSubRequest.getCertificateType()));
    		orgSub.setCertificatenumber(regOrgSubRequest.getCertificateNumber());
    	}
    	orgSubDao.insert(orgSub);
    	return orgSub;
    }
    
    
	@Override
	public OrgRegisterValidateResponse validateOrg(OrgRegisterValidateRequest request) {
		OrgRegisterValidateResponse response = new OrgRegisterValidateResponse();
		TitanOrgSub titanOrgSub = null;
		if(request.getUsertype()==null){
			response.putErrorResult("机构类型不能为空");
			return response;
		}
		
		if(request.getUsertype()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			if(request.getCertificatetype()==null||!StringUtil.isValidString(request.getCertificateNumber())){
				response.putErrorResult("证件类型或者证件号码不能为空");
				return response;
			}
			TitanOrgSubParam condition = new TitanOrgSubParam();
			condition.setCertificatetype(NumberUtils.toInt(request.getCertificatetype()));
			condition.setCertificatenumber(request.getCertificateNumber());
			titanOrgSub = orgSubDao.selectOne(condition);
			if(titanOrgSub!=null){
				OrgDTO orgDTO = new OrgDTO();
				orgDTO.setOrgid(titanOrgSub.getOrgsubid());
				orgDTO.setOrgcode(titanOrgSub.getOrgcode());
				orgDTO.setOrgname(titanOrgSub.getOrgname());
				response.setOrgDTO(orgDTO);
			}
			if(titanOrgSub!=null&&(!StringUtil.isValidString(request.getOrgName()))){
				response.putErrorResult("已注册");
				return response;
			}
			if(titanOrgSub!=null){//已经存在机构
				OrgDTO orgDTO = new OrgDTO();
				orgDTO.setOrgid(titanOrgSub.getOrgsubid());
				orgDTO.setOrgcode(titanOrgSub.getOrgcode());
				orgDTO.setOrgname(titanOrgSub.getOrgname());
				response.setOrgDTO(orgDTO);
				if(titanOrgSub.getOrgname().equals(request.getOrgName())){//证件号和名字都相同
					response.putErrorResult("500", "该证件已经注册，请使用其他证件");
				}else{//证件号相同，名字不相同
					response.putErrorResult("该证件号已经注册，请核实证件");
				}
				return response;
			}
		}else if(request.getUsertype()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
			if(!StringUtil.isValidString(request.getBuslince())){
				response.putErrorResult("营业执照号不能为空");
				return response;
			}
			TitanOrgSubParam condition = new TitanOrgSubParam();
			condition.setBuslince(request.getBuslince());
			titanOrgSub = orgSubDao.selectOne(condition);
			if(titanOrgSub!=null){
				OrgDTO orgDTO = new OrgDTO();
				orgDTO.setOrgid(titanOrgSub.getOrgsubid());
				orgDTO.setOrgcode(titanOrgSub.getOrgcode());
				orgDTO.setOrgname(titanOrgSub.getOrgname());
				response.setOrgDTO(orgDTO);
			}
			if(titanOrgSub!=null&&(!StringUtil.isValidString(request.getOrgName()))){
				response.putErrorResult("已注册");
				return response;
			}
			//证件号相同,公司名字不同
			if(titanOrgSub!=null&&(!titanOrgSub.getOrgname().equals(request.getOrgName()))){
				response.putErrorResult("公司名称和证件号不相符，请核实证件");
				return response;
			}else if(titanOrgSub!=null&&titanOrgSub.getOrgname().equals(request.getOrgName())){
				response.putErrorResult("500", "该证件已经注册，请使用其他证件");
				return response;
			}
			//检查公司名称是否已经存在
			if(StringUtil.isValidString(request.getOrgName())){
				condition.setBuslince(null);
				condition.setOrgname(request.getOrgName());
				titanOrgSub = orgSubDao.selectOne(condition);
				//公司名字相同，证件号不相同,
				if(titanOrgSub!=null&&(!titanOrgSub.getBuslince().equals(request.getBuslince()))){
					OrgDTO orgDTO = new OrgDTO();
					orgDTO.setOrgid(titanOrgSub.getOrgsubid());
					orgDTO.setOrgcode(titanOrgSub.getOrgcode());
					orgDTO.setOrgname(titanOrgSub.getOrgname());
					response.setOrgDTO(orgDTO);
					response.putErrorResult("公司名称和证件号不相符，请核实证件");
					return response;
				}
			}
			
		}else {
			response.putErrorResult("注册证件类型错误");
			return response;
		}
		
		response.putSuccess("该证件可以使用");
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
			param.setOrgId(organRegisterUpdateRequest.getOrgId());
			TitanOrg oldOrg = titanOrgDao.selectOne(param);
			if(StringUtil.isValidString(organRegisterUpdateRequest.getImageid())){
				updateOrgImg(organRegisterUpdateRequest.getImageid(),oldOrg.getOrgcode());
			}
			//修改真实机构信息
			TitanOrgMapInfo titanOrgMapInfo = titanOrgMapInfoDao.getOneTitanOrgMapInfo(oldOrg.getOrgcode());
			TitanOrgSubParam updateOrgSubParam  = new TitanOrgSubParam();
			updateOrgSubParam.setOrgcode(titanOrgMapInfo.getOrgSubcode());
			updateOrgSubParam.setOrgname(organRegisterUpdateRequest.getOrgName());
			updateOrgSubParam.setBuslince(organRegisterUpdateRequest.getBuslince());
			updateOrgSubParam.setCertificatetype(NumberUtils.toInt(organRegisterUpdateRequest.getCertificateType()));
			updateOrgSubParam.setCertificatenumber(organRegisterUpdateRequest.getCertificateNumber());
			orgSubDao.update(updateOrgSubParam);
			//同时修改虚拟机构
			TitanOrg updateOrg = new TitanOrg();
			updateOrg.setOrgcode(titanOrgMapInfo.getOrgCode());
			updateOrg.setConnect(organRegisterUpdateRequest.getConnect());
			updateOrg.setMobiletel(organRegisterUpdateRequest.getMobileTel());
			updateOrg.setOrgname(organRegisterUpdateRequest.getOrgName());
			titanOrgDao.update(updateOrg);
			
			//修改机构审核状态为待审核	
			TitanOrgCheck titanOrgCheck = new TitanOrgCheck();
			TitanOrgCheckParam checkParam = new TitanOrgCheckParam();
	    	checkParam.setConstid(oldOrg.getConstid());
	    	checkParam.setUserid(oldOrg.getUserid());
	    	PaginationSupport<TitanOrgCheck> orgCheckPage = new PaginationSupport<TitanOrgCheck>();
	    	titanOrgCheckDao.selectForPage(checkParam, orgCheckPage);
	    	titanOrgCheck = orgCheckPage.getItemList().get(0);
	    	titanOrgCheck.setResultkey(OrgCheckResultEnum.FT.getResultkey());
	    	titanOrgCheck.setResultmsg("修改注册资料，重新注册");
	    	titanOrgCheckDao.update(titanOrgCheck);
	    	//写一条待审核的日志
	    	TitanOrgCheckLog titanOrgCheckLog = new TitanOrgCheckLog();
	    	titanOrgCheckLog.setCheckid(titanOrgCheck.getCheckid());
	    	titanOrgCheckLog.setConstid(titanOrgCheck.getConstid());
	    	titanOrgCheckLog.setUserid(titanOrgCheck.getUserid());
	    	titanOrgCheckLog.setResultkey(OrgCheckResultEnum.FT.getResultkey());
	    	titanOrgCheckLog.setResultmsg(OrgCheckResultEnum.FT.getResultmsg());
	    	titanOrgCheckLog.setUserid(titanOrgCheck.getUserid());
	    	titanOrgCheckLog.setOptuser(organRegisterUpdateRequest.getOperator());
	    	titanOrgCheckLog.setOpttime(new Date());
	    	titanOrgCheckLogDao.insert(titanOrgCheckLog);
	    	
			response.putSuccess("资料提交成功");
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
			orgParam.setOrgId(organCheckRequest.getOrgId());
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
	    	// 审核通过创建账号 account
	    	if(organCheckRequest.getCheckstatus()==1){//审核通过
	    		PaginationSupport<TitanAccount> accountPage = new PaginationSupport<TitanAccount>();
	        	TitanAccountParam condition = new TitanAccountParam();
	        	//如何确定该机构的账号还没有创建，唯一性
	        	condition.setUserid(newOrgEntity.getUserid());
	        	titanAccountDao.selectForPage(condition, accountPage);
	        	if(CollectionUtils.isEmpty(accountPage.getItemList())){
	        		//虚拟证件融数注册机构
	        		RSOrg rsOrg = new RSOrg();
	        		rsOrg.setUserid(newOrgEntity.getUserid());
	        		rsOrg.setOrgname(newOrgEntity.getOrgname());
	        		rsOrg.setBuslince(newOrgEntity.getBuslince());
	        		rsOrg.setCertificatetype(newOrgEntity.getCertificatetype());
	        		rsOrg.setCertificatenumber(newOrgEntity.getCertificatenumber());
	        		rsOrg.setUsertype(newOrgEntity.getUsertype());
	        		BaseResponse orgBaseResponse = registerRSOrg(rsOrg);
	        		//真实证件融数注册机构
	        		TitanOrgMapInfoParam titanOrgMapInfoParam = new TitanOrgMapInfoParam();
	        		titanOrgMapInfoParam.setOrgCode(newOrgEntity.getOrgcode());
	        		List<TitanOrgMapInfo> list = titanOrgMapInfoDao.queryList(titanOrgMapInfoParam);
	        		if(list!=null&&list.size()>1){
	        			LOGGER.error("金融机构关联关系记录太多，关联机构orgcode:"+newOrgEntity.getOrgcode()+",查询到关联机构条数："+list.size());
	        			throw new MessageServiceException("机构数据异常，请联系系统管理员");
	        		}else if(list.size()==1){
	        			String orgSubcode = list.get(0).getOrgSubcode();
		        		BaseResponse orgSubResponse = regOrgSubForRS(orgSubcode); 
		        		
		        		if(!CommonConstant.OPERATE_SUCCESS.equals(orgSubResponse.getOperateStatus())){//失败
		        			LOGGER.error("调用融数接口失败,注册机构参数 orgSubcode:"+orgSubcode+",rs返回信息[baseResponse]:"+JSONSerializer.toJSON(orgSubResponse).toString());
		        			response.putErrorResult(orgBaseResponse.getReturnMsg());
		        			return response;
		        		}
	        		}
	        		
	        		if(CommonConstant.OPERATE_SUCCESS.equals(orgBaseResponse.getOperateStatus())){
	            		BalanceInfoRequest balanceInfoRequest = new BalanceInfoRequest();
	            		balanceInfoRequest.setUserId(newOrgEntity.getUserid());
	            		balanceInfoRequest.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
	            		accountService.synBalanceInfo(balanceInfoRequest);
	            		
	            		CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest(); 
	            		cashierDeskInitRequest.setUserId(newOrgEntity.getUserid());
	            		cashierDeskInitRequest.setConstId(CommonConstant.RS_FANGCANG_CONST_ID);
	            		
	            		//初始化收银台
	            		titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
	            		
	            		//给商家分配密钥对以及前缀
	            		this.initKeyInfo(newOrgEntity.getUserid());
	            		
	            		titanOrgCheck.setResultkey(OrgCheckResultEnum.PASS.getResultkey());
	            		titanOrgCheck.setResultmsg(OrgCheckResultEnum.PASS.getResultmsg());
	            		titanOrgCheckDao.update(titanOrgCheck);
	            		if(organCheckRequest.getIsSendSms()){
	            			sendRegNotify(newOrgEntity.getUserid(), true, null);
	            		}
	            		
	        		}else{//失败
	        			String rsmsg  = orgBaseResponse.getReturnMsg();
	        			titanOrgCheck.setCheckuser(CommonConstant.CHECK_ADMIN_RS);
		        		titanOrgCheck.setChecktime(nowDate);
	        			titanOrgCheck.setResultkey(OrgCheckResultEnum.REVIEW.getResultkey());
	        			titanOrgCheck.setResultmsg(rsmsg);
		        		titanOrgCheckDao.update(titanOrgCheck);
		        		addOrgCheckLog(titanOrgCheck);
	        			LOGGER.error("调用融数接口rsOrganizationManager.resigterPersonOrg 失败,request orgId:"+organCheckRequest.getOrgId()+",rs return content[orgBaseResponse]:"+JSONSerializer.toJSON(orgBaseResponse).toString()+"。 organCheckRequest:"+JSONSerializer.toJSON(organCheckRequest).toString());
	        			response.putErrorResult(orgBaseResponse.getReturnMsg());
	        			return response;
	        		}
	        		
	        	}else{
	        		titanOrgCheckDao.update(titanOrgCheck);
	        		addOrgCheckLog(titanOrgCheck);
	        		LOGGER.info("机构审核时， t_tfs_account插入失败,errormsg:该账户已经创建，不需要重复创建,param:"+JSONSerializer.toJSON(organCheckRequest).toString()); 
	        	}
	    	}else{
	    		titanOrgCheckDao.update(titanOrgCheck);
	    		addOrgCheckLog(titanOrgCheck);
	    		if(organCheckRequest.getIsSendSms()){
	    			sendRegNotify(newOrgEntity.getUserid(), false, organCheckRequest.getResultMsg());
	    		}
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
	
	/**
	 * 融数注册orgsub子机构
	 */
	private BaseResponse regOrgSubForRS(String orgsubCode){
		TitanOrgSub orgSub = orgSubDao.getOneByOrgCode(orgsubCode);
		 
		RSOrg rsOrg = new RSOrg();
		rsOrg.setUserid(orgSub.getOrgcode());
		rsOrg.setOrgname(orgSub.getOrgname());
		rsOrg.setBuslince(orgSub.getBuslince());
		rsOrg.setCertificatetype(orgSub.getCertificatetype());
		rsOrg.setCertificatenumber(orgSub.getCertificatenumber());
		rsOrg.setUsertype(orgSub.getUsertype());
		return registerRSOrg(rsOrg);
 
	}
	
	private void sendRegNotify(String userId, boolean isSuccess, String reason){
		OrgDTO orgDTO = new OrgDTO();
		orgDTO.setUserid(userId);
		orgDTO = this.queryOrg(orgDTO);
		OrgSubDTO orgSubDTO =  getOrgSubDTO(userId);
		String receiveAddress;
		
		if(orgSubDTO.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){//企业用户
			//给联系人发
			receiveAddress = orgDTO.getMobiletel();
		}else{
			//给管理员发
			UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
			userInfoQueryRequest.setIsadmin(1);
			userInfoQueryRequest.setOrgCode(orgDTO.getOrgcode());
			UserInfoPageResponse userInfoPageResponse = titanFinancialUserService.queryUserInfoPage(userInfoQueryRequest);
			receiveAddress = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0).getUserloginname();
		}
		SendMessageRequest sendCodeRequest = new SendMessageRequest();
		sendCodeRequest.setReceiveAddress(receiveAddress);
		if(isSuccess){
			sendCodeRequest.setSubject(SMSTemplate.ORG_REG_SUCCESS.getSubject());
			sendCodeRequest.setContent(SMSTemplate.ORG_REG_SUCCESS.getContent());
		}else{
			Pattern patPunc = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]$");
			Matcher matcher = patPunc.matcher(reason);//最后一个字符是特殊符号
			if(!matcher.find()){
				reason += "，";
			}
			String content = MessageFormat.format(SMSTemplate.ORG_REG_FAID.getContent(), reason);
			sendCodeRequest.setSubject(SMSTemplate.ORG_REG_FAID.getSubject());
			sendCodeRequest.setContent(content);
		}
		sendCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
		smsService.asynSendMessage(sendCodeRequest);
	}
	
	/**
	 * 添加审核日志
	 * @param titanOrgCheck
	 */
	private void addOrgCheckLog(TitanOrgCheck titanOrgCheck){
		//添加审核日志
    	TitanOrgCheckLog titanOrgCheckLog = new TitanOrgCheckLog();
    	titanOrgCheckLog.setCheckid(titanOrgCheck.getCheckid());
    	titanOrgCheckLog.setConstid(titanOrgCheck.getConstid());
    	titanOrgCheckLog.setUserid(titanOrgCheck.getUserid());
    	titanOrgCheckLog.setResultkey(titanOrgCheck.getResultkey());
    	titanOrgCheckLog.setResultmsg(titanOrgCheck.getResultmsg());
    	titanOrgCheckLog.setUserid(titanOrgCheck.getUserid());
    	titanOrgCheckLog.setOptuser(titanOrgCheck.getCheckuser());
    	titanOrgCheckLog.setOpttime(titanOrgCheck.getChecktime());
    	titanOrgCheckLogDao.insert(titanOrgCheckLog);
	}
	/***
	 * 
	 * @param checkStatus 通过 -1， 不通过-0
	 * @param checkStatus 审核前的状态
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
	private BaseResponse registerRSOrg(RSOrg rsOrg){
    	
		if(rsOrg.getUsertype()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
			//企业账户
			CompanyOrgRegRequest companyOrgRegRequest = new CompanyOrgRegRequest();
			companyOrgRegRequest.setCompanyname(rsOrg.getOrgname());
			companyOrgRegRequest.setBuslince(rsOrg.getBuslince());
			companyOrgRegRequest.setUserid(rsOrg.getUserid());
			companyOrgRegRequest.setUsertype(String.valueOf(rsOrg.getUsertype()));
			companyOrgRegRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			companyOrgRegRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			companyOrgRegRequest.setUsername(rsOrg.getOrgname());
			
			CompanyOrgRegResponse response = rsOrganizationManager.resigterCompanyOrg(companyOrgRegRequest);
			return response;
			
		}else if(rsOrg.getUsertype()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			//个人账户
			PersonOrgRegRequest personOrgRegRequest = new PersonOrgRegRequest();
			personOrgRegRequest.setPersonchnname(rsOrg.getOrgname());
			personOrgRegRequest.setCertificatetype(String.valueOf(rsOrg.getCertificatetype()));
			personOrgRegRequest.setCertificatenumber(rsOrg.getCertificatenumber());
			personOrgRegRequest.setUserid(rsOrg.getUserid());
			personOrgRegRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
			personOrgRegRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			personOrgRegRequest.setOpertype("1");
			
			PersonOrgRegResponse personResponse = rsOrganizationManager.resigterPersonOrg(personOrgRegRequest);
			return personResponse;
		}
		LOGGER.error("注册机构类型不存在，参数注册类型[Usertype]："+rsOrg.getUsertype());
		return new BaseResponse("-400","注册机构类型不存在");
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
			throw new GlobalServiceException("upload org img error,param:imageType["+request.getImageType()+"],filename["+request.getFileName()+"]",e);
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
    	//通过合作方商家编码查询的，默认为SAAS
    	if(StringUtil.isValidString(request.getMerchantCode())&&request.getCoopType()==null){
    		response.putErrorResult("合作方类型不能为空");
			return response;
    	}
    	LOGGER.info("机构绑定解绑操作bindFinancialOrgan："+Tools.gsonToString(request));
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
    			TitanOrgBindinfo param = new TitanOrgBindinfo();
    			param.setUserid(request.getUserId());
    			List<TitanOrgBindinfo> orgBindinfoList = titanOrgBindinfoDao.selectTitanOrgBindinfoByUserid(param);
    			
        		// 解除机构下的用户绑定
    			FinancialUserUnBindRequest financialUserUnBindRequest = new FinancialUserUnBindRequest();
    			financialUserUnBindRequest.setMerchantcode(request.getMerchantCode());
    			financialUserUnBindRequest.setCoopType(orgBindinfoList.get(0).getCooptype());
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
            		bindinfo.setCooptype(request.getCoopType());
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
            	financialUserBindRequest.setCoopType(request.getCoopType());
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
    	titanOrg.setLastUpdateDate(orgUpdateRequest.getLastUpdateDate());
    	titanOrg.setMaxLoanAmount(orgUpdateRequest.getMaxLoanAmount());
    	try {
    		titanOrgDao.update(titanOrg);
    		responseDTO.putSuccess();
		} catch (DaoException e) {
			throw new GlobalServiceException("更新机构信息失败,param,orgUpdateRequest:"+JSONSerializer.toJSON(orgUpdateRequest).toString(), e);
		}
		
		return responseDTO;
	}
    
    

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO updateOrgBaseInfo(OrgBaseInfoRequest orgBaseInfoRequest) throws MessageServiceException {
    	BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
    	TitanOrgSubParam orgSubParam = new TitanOrgSubParam();
    	orgSubParam.setOrgcode(orgBaseInfoRequest.getOrgCode());
    	orgSubParam.setOrgname(orgBaseInfoRequest.getOrgName());
    	orgSubParam.setCertificatenumber(orgBaseInfoRequest.getCertificatenumber());
    	if(StringUtil.isValidString(orgBaseInfoRequest.getCertificatetype())){
    		orgSubParam.setCertificatetype(Integer.valueOf(orgBaseInfoRequest.getCertificatetype()));
    	}
    	orgSubParam.setBuslince(orgBaseInfoRequest.getBuslince());
    	orgSubDao.update(orgSubParam);
    	if(orgBaseInfoRequest.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    		//TODO 修改企业机构信息
    		//rsOrganizationManager.updateCompanyOrg(companyOrgUpdateRequest)
    	}else{
    		PersOrgInfoQueryRequest persOrgInfoQueryRequest = new PersOrgInfoQueryRequest();
    		persOrgInfoQueryRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
    		persOrgInfoQueryRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
    		persOrgInfoQueryRequest.setUserid(orgBaseInfoRequest.getOrgCode());
    		
    		PersOrgInfoQueryResponse persOrgInfoQueryResponse = rsOrganizationManager.queryPersOrgInfo(persOrgInfoQueryRequest);
    		if(CollectionUtils.isNotEmpty(persOrgInfoQueryResponse.getPersonOrgList())){
    			OpenAccountPerson openAccountPerson = persOrgInfoQueryResponse.getPersonOrgList().get(0);
    			PersonOrgUpdateRequest personOrgUpdateRequest = new PersonOrgUpdateRequest();
    			boolean isUpdate = false;
    			//证件号码不同
    			if(!openAccountPerson.getCertificatenumber().equals(orgBaseInfoRequest.getCertificatenumber())){
    				personOrgUpdateRequest.setCertificatenumber(orgBaseInfoRequest.getCertificatenumber());
    				isUpdate = true;
        		}
    			//机构名称不同
    			if(!openAccountPerson.getPersonchnname().equals(orgBaseInfoRequest.getOrgName())){
    				personOrgUpdateRequest.setUsername(orgBaseInfoRequest.getOrgName());
    				isUpdate = true;
    			}
        		if(isUpdate){//机构名称或者证件号不同，才修改
        			personOrgUpdateRequest.setUserid(orgBaseInfoRequest.getOrgCode());
            		personOrgUpdateRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
            		personOrgUpdateRequest.setOpertype("2");//修改
            		personOrgUpdateRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
            		PersonOrgUpdateResponse response = rsOrganizationManager.updatePersonOrg(personOrgUpdateRequest);
            		if(!CommonConstant.OPERATE_SUCCESS.equals(response.getOperateStatus())){
            			// 融数操作失败，回滚金服数据
            			LOGGER.error("调用融数接口修改机构信息失败，机构id(userid):"+orgBaseInfoRequest.getOrgCode()+",泰坦金融机构证件号为："+orgBaseInfoRequest.getCertificatenumber()+",机构名称："+orgBaseInfoRequest.getOrgName()+",融数机构证件号为："+openAccountPerson.getCertificatenumber()+",机构名称："+openAccountPerson.getPersonchnname());
                		throw new MessageServiceException(response.getReturnMsg());
                	}
        		}
    		}
    	}
    	baseResponseDTO.putSuccess("修改成功");
		return baseResponseDTO;
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
			orgBindInfo.setCoopType(titanOrgBindinfo.getCooptype());
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
    		LOGGER.info("获取验证码时，参数错误， getCheckCodeRequest："+JSONSerializer.toJSON(getCheckCodeRequest).toString());
			response.putParamError();
			return response;
		}
		//非注册场景时，需要检验信息接收方是否已经注册
		if(getCheckCodeRequest.getMsgType()!=SMSType.REG_CODE.getType()){
			TitanUserParam userParam = new TitanUserParam();
			userParam.setUserloginname(getCheckCodeRequest.getReceiveAddress());
			PaginationSupport<TitanUser> userPaginationSupport = new PaginationSupport<TitanUser>();
			userPaginationSupport = titanUserDao.selectForPage(userParam, userPaginationSupport);
			if(userPaginationSupport.getItemList().size()==0){
				LOGGER.info("获取验证码时，用户名不存在,用户名："+getCheckCodeRequest.getReceiveAddress());
				response.putErrorResult("该用户名不存在");
				return response;
			}
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
				TitanCheckCodeParam entityUpdate = new TitanCheckCodeParam();
				entityUpdate.setReceiveAddress(getCheckCodeRequest.getReceiveAddress());
				entityUpdate.setIsactive(TitanCheckCodeEnum.Isactive.NOT_ACTIVE.getKey());
				checkCodeDao.disableIsactive(entityUpdate);
			}
			//改时间，生成一个新的验证码
			Date expiredTime = DateUtils.addHours(now, CommonConstant.CODE_TIME_OUT_HOUR);
			TitanCheckCode newCheckCode = new TitanCheckCode();
			newCheckCode.setReceiveAddress(getCheckCodeRequest.getReceiveAddress());
			newCheckCode.setCode(Tools.getRegCode());
			newCheckCode.setCodeType(getCheckCodeRequest.getMsgType());
			newCheckCode.setIsactive(TitanCheckCodeEnum.Isactive.ACTIVE.getKey());
			newCheckCode.setCreateTime(now);
			newCheckCode.setExpiredTime(expiredTime);
			checkCodeDao.insert(newCheckCode);
			LOGGER.info(getCheckCodeRequest.getReceiveAddress()+"----create new Code:"+newCheckCode.getCode());
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
					response.setReturnMessage("验证成功");
					response.setResult(true);
				}else{
					response.setReturnCode("WRONG");
					response.setReturnMessage("验证码错误，请重新输入");
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
				response.setReturnMessage("验证码已经过期，请重新获取验证码");
				response.setResult(false);
				return response;
			}
		}else{
			//接收者不存在
			response.setReturnCode("NOTEXIST");
			response.setReturnMessage("验证码错误，请重新获取验证码");
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
			condition.setOrgCode(orgDTO.getOrgcode());
			condition.setOrgId(orgDTO.getOrgid());
			condition.setUserId(orgDTO.getUserid());
			condition.setTitanCode(orgDTO.getTitancode());
			condition.setOrgName(orgDTO.getOrgname());
			condition.setStatusId(orgDTO.getStatusId());
			TitanOrg titanOrg = titanOrgDao.selectOne(condition);
			if(titanOrg==null){
				return null;
			}
			if(titanOrg !=null){
				MyBeanUtil.copyProperties(orgDTO, titanOrg);
				orgDTO.setStatusId(titanOrg.getStatusid());
			}
			return orgDTO;
		}
		
		return null;
	}
	

	@Override
	public TitanOrgSub getOrgSub(OrgSubRequest orgSubRequest) {
		//参数不能为空
		if(!(StringUtil.isValidString(orgSubRequest.getOrgCode())||StringUtil.isValidString(orgSubRequest.getOrgSubCode()))){
			return null;
		}
		String orgSubCode = null;
		if(StringUtil.isValidString(orgSubRequest.getOrgCode())){//通过虚拟机构orgcode查询
			TitanOrgMapInfo orgMapInfo = titanOrgMapInfoDao.getOneTitanOrgMapInfo(orgSubRequest.getOrgCode());
			if(orgMapInfo==null){
				return null;
			}else{
				orgSubCode = orgMapInfo.getOrgSubcode();
			}
		}else if(StringUtil.isValidString(orgSubRequest.getOrgSubCode())){
			orgSubCode = orgSubRequest.getOrgSubCode();
		}else{
			return null;
		}
		if(StringUtil.isValidString(orgSubCode)){
			return orgSubDao.getOneByOrgCode(orgSubCode);
		}
		return null;
	}

	@Override
	public int countOrg(FinancialOrganQueryRequest financialOrganQueryRequest) {
		return titanOrgDao.countOrg(financialOrganQueryRequest);
	}

	@Override
	public OrganBriefResponse queryOrganBriefByUserId(
			FinancialOrganQueryRequest organQueryRequest) {
		OrganBriefResponse response = new OrganBriefResponse();
		List<FinancialOrganDTO> organDTOList = new ArrayList<FinancialOrganDTO>();
		List<TitanOrg> titanOrgs = null;
		try {
			titanOrgs = titanOrgDao.queryTitanOrgListByUserId(organQueryRequest);
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
	public OrgBindInfo queryActiveOrgBindInfo(OrgBindInfo orgBindInfo) {
		try{
			if(StringUtil.isValidString(orgBindInfo.getMerchantCode())&&orgBindInfo.getCoopType()==null){
				orgBindInfo.setCoopType(CoopTypeEnum.SAAS.getKey());
			}
			TitanOrgBindinfo titanOrgBindinfo = new TitanOrgBindinfo();
			titanOrgBindinfo.setMerchantcode(orgBindInfo.getMerchantCode());
			if(titanOrgBindinfo.getBindstatus()==null){//默认查绑定
				titanOrgBindinfo.setBindstatus(1);//绑定
			}
		    List<TitanOrgBindinfo> titanOrgBindinfoList =  titanOrgBindinfoDao.selectActiveTitanOrgBindinfo(titanOrgBindinfo);
			if(titanOrgBindinfoList !=null && titanOrgBindinfoList.size()==1){
				return convertOrgBindInfo(titanOrgBindinfoList.get(0));
			}
		    
		}catch(Exception e){
			LOGGER.error("查询绑定机构信息失败"+e.getMessage(), e);
		}
		return null;
	}
	
	//为商家分配秘钥对
	private void initKeyInfo(String userId){
		if(!StringUtil.isValidString(userId)){
			return ;
		}
		TitanOpenOrg openOrg = new TitanOpenOrg();
		try{
			String prefix = "";
			List<String> prefixs = titanOpenOrgDao.selectMaxPrefix();
			if(prefixs==null || prefixs.size()==0){
				prefix="AA";
			}else{
				prefix = prefixs.get(0);
			}
			openOrg.setPrefix(NumberPrefix.getPayOrderCodePrefix(prefix));
			Map<String,String> rsaMap= RSAUtil.generateStringKsys();
			openOrg.setPrivatekey(rsaMap.get(RSAUtil.PRIVATE_KEY));
			openOrg.setModule(rsaMap.get(RSAUtil.PUBLIC_KEY_MODULE));
			openOrg.setEmpoent(rsaMap.get(RSAUtil.PUBLIC_KEY_EMPOENT));
			openOrg.setPublicKey(rsaMap.get(RSAUtil.PUBLIC_KEY));
			openOrg.setUserid(userId);
		    titanOpenOrgDao.insert(openOrg);
			
		}catch(Exception e){
			LOGGER.error("初始化密钥对失败",e);
		}
	}


	@Override
	public TitanOpenOrgDTO queryTitanOpenOrgDTO(String userId) {
		try{
			TitanOpenOrg openOrg = new TitanOpenOrg();
			openOrg.setUserid(userId);
			List<TitanOpenOrgDTO> titanOpenOrgDTOList =  titanOpenOrgDao.selectList(openOrg);
		    if(titanOpenOrgDTOList!=null && titanOpenOrgDTOList.size()==1){
		    	return titanOpenOrgDTOList.get(0);
		    }
		}catch(Exception e){
			LOGGER.error("初始化密钥对失败",e);
		}
		LOGGER.error("该商家没有开通金融账户或账户秘钥信息");
		return null;
	}
	
	
	//@Override
	//public void test() throws Exception{
//		CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest(); 
//		cashierDeskInitRequest.setUserId("TJM10000109");
//		cashierDeskInitRequest.setConstId(CommonConstant.RS_FANGCANG_CONST_ID);
//		titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
		
		//initKeyInfo("TJM10000110");



	@Override
	public List<OrgBindInfoDTO> queryOrgBindInfoDTO(OrgBindInfoDTO orgBindDTO) {
		if(orgBindDTO.getBindStatus()==null){//默认查绑定状态
			orgBindDTO.setBindStatus(1);//绑定
		}
		return titanOrgBindinfoDao.queryOrgBindInfoDTO(orgBindDTO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO cancelOrgBind(CancelOrgBindRequest cancelOrgBindRequest) throws MessageServiceException {
		BaseResponseDTO  responseDTO = new BaseResponseDTO();
		if(!StringUtil.isValidString(cancelOrgBindRequest.getOrgCode())){
			responseDTO.putErrorResult("机构编码[OrgCode]不能为空");
			return  responseDTO;
		}
		//1-删除机构绑定
		OrgBindInfoDTO orgBindDTOParam = new OrgBindInfoDTO();
		orgBindDTOParam.setBindStatus(BindStatusOrgEnum.BING.getBindStatus());//绑定状态
		orgBindDTOParam.setOrgcode(cancelOrgBindRequest.getOrgCode());
		List<OrgBindInfoDTO> orgBindInfoDTOList = titanOrgBindinfoDao.queryOrgBindInfoDTO(orgBindDTOParam);
		String merchantcode = null;
		
		if(CollectionUtils.isNotEmpty(orgBindInfoDTOList)){
			merchantcode = orgBindInfoDTOList.get(0).getMerchantCode();
			
			OperateLogRequest operateLogRequest = new OperateLogRequest();
        	operateLogRequest.setOperateType(OperateTypeOperateLogEnum.CANCEL_ORG_BIND.getOperateType());
        	operateLogRequest.setOperateContent("成功取消机构绑定关系，商家编码merchantcode："+merchantcode+",金融机构orgCode："+cancelOrgBindRequest.getOrgCode());
        	operateLogRequest.setOperateTime(new Date());
        	operateLogRequest.setOperator(cancelOrgBindRequest.getOperator());
        	businessLog.addOperateLog(operateLogRequest);
			try {
				TitanOrgBindinfoParam deleteParam = new TitanOrgBindinfoParam();
				deleteParam.setOrgcode(cancelOrgBindRequest.getOrgCode());
				titanOrgBindinfoDao.delete(deleteParam);
				//更改注册渠道，防止在钱包系统无法登陆问题.
				TitanOrg entity = new TitanOrg();
				entity.setOrgcode(cancelOrgBindRequest.getOrgCode());
				entity.setRegChannel(CoopTypeEnum.TWS.getKey());
				titanOrgDao.update(entity);
				
			} catch (DaoException e) {
				LOGGER.error("删除机构绑定关系失败，参数orgCode："+cancelOrgBindRequest.getOrgCode(),e);
				throw  new MessageServiceException("删除机构绑定关系失败，参数orgCode："+cancelOrgBindRequest.getOrgCode(),e);
			}
		}else{//绑定关系不存在
			responseDTO.putErrorResult("机构绑定关系不存在，请确认后操作");
			return responseDTO;
		}
		
		//2-删除员工绑定
		UserBindInfoRequest userBindInfoRequest = new UserBindInfoRequest();
		userBindInfoRequest.setMerchantcode(merchantcode);
		UserBindInfoResponse userBindInfoResponse = titanFinancialUserService.queryUserBindInfoDTO(userBindInfoRequest);
		if(CollectionUtils.isNotEmpty(userBindInfoResponse.getPaginationSupport().getItemList())){
			DeleteBindUserRequest deleteBindUserRequest = new DeleteBindUserRequest();
			deleteBindUserRequest.setMerchantCode(merchantcode);
			try {
				titanFinancialUserService.deleteBindUser(deleteBindUserRequest);
			} catch (MessageServiceException e) {
				LOGGER.error("删除员工绑定关系失败，参数merchantcode："+merchantcode,e);
				throw  new MessageServiceException("删除员工绑定关系失败",e);
			}
		}else{//绑定关系不存在
			responseDTO.putErrorResult("用户绑定关系不存在，请确认后操作");
			return responseDTO;
		}
		responseDTO.putSuccess("成功解除机构和员工绑定关系");
		return responseDTO;
	}
	
	@Override
	public OrgBindInfo queryOrgBindInfo(OrgBindInfo orgBindInfo) {
		try{
			TitanOrgBindinfo titanOrgBindinfo = new TitanOrgBindinfo();
			titanOrgBindinfo.setMerchantcode(orgBindInfo.getMerchantCode());
			titanOrgBindinfo.setOrgcode(orgBindInfo.getOrgcode());
		    List<TitanOrgBindinfo> titanOrgBindinfoList =  titanOrgBindinfoDao.queryTitanOrgBindinfo(titanOrgBindinfo);
			if(titanOrgBindinfoList !=null && titanOrgBindinfoList.size()==1){
				return convertOrgBindInfo(titanOrgBindinfoList.get(0));
			}
		    
		}catch(Exception e){
			LOGGER.error("查询绑定机构信息失败"+e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void fixOldOrg() {
		TitanOrgMapInfoParam titanOrgMapInfoParam = new TitanOrgMapInfoParam();
		titanOrgMapInfoParam.setOrgCode("TJM10020008");
		List<TitanOrgMapInfo> orgMapInfoList = titanOrgMapInfoDao.queryList(titanOrgMapInfoParam);
		
		for(TitanOrgMapInfo item : orgMapInfoList){
			if(item.getOrgCode().equals(item.getOrgSubcode())){//映射表中真实和虚拟的相同，则要修改
				
				TitanOrgParam condition = new TitanOrgParam();
				condition.setOrgCode(item.getOrgCode());
				TitanOrg entity = titanOrgDao.selectOne(condition);
				
				LOGGER.info("虚拟机构生成真实机构，暂存数据=机构org信息："+Tools.gsonToString(entity));
				//a1 本地修改证件号
				TitanOrg updateTitanOrg = new TitanOrg();
				if(entity.getUsertype().equals(UserType.PERSONAL.getKey())){
					updateTitanOrg.setCertificatenumber(entity.getOrgcode());
				}else{
					updateTitanOrg.setBuslince(entity.getOrgcode());
				}
				updateTitanOrg.setOrgcode(entity.getOrgcode());
				titanOrgDao.update(updateTitanOrg);
				
				//a2融数修改个人机构证件号
				if(entity.getUsertype().equals(UserType.PERSONAL.getKey())){
					PersonOrgUpdateRequest personOrgUpdateRequest = new PersonOrgUpdateRequest();
					personOrgUpdateRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
					personOrgUpdateRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
					personOrgUpdateRequest.setUserid(entity.getOrgcode());
					personOrgUpdateRequest.setCertificatenumber(entity.getOrgcode());//用orgcode作为虚拟证件号
					personOrgUpdateRequest.setOpertype("2");//1-新增，2-修改
					rsOrganizationManager.updatePersonOrg(personOrgUpdateRequest);
				}else{//a3融数修改企业机构证件号
					CompanyOrgUpdateRequest companyOrgUpdateRequest = new CompanyOrgUpdateRequest();
					companyOrgUpdateRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
					companyOrgUpdateRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
					companyOrgUpdateRequest.setUserid(entity.getOrgcode());
					companyOrgUpdateRequest.setCompanyname(entity.getOrgname());
					companyOrgUpdateRequest.setUsername(entity.getOrgname());
					companyOrgUpdateRequest.setOpertype("1");//更新
					companyOrgUpdateRequest.setBuslince(entity.getOrgcode());//用orgcode作为虚拟证件号
					rsOrganizationManager.updateCompanyOrg(companyOrgUpdateRequest);
				}
				
				//b1 本地修改真实orgcode
				String newUserId = entity.getOrgcode().replace("TJM", "TJMS");
				TitanOrgSubParam orgSubParam = new TitanOrgSubParam();
				orgSubParam.setNewOrgCode(newUserId);
				orgSubParam.setOrgcode(entity.getOrgcode());
				orgSubDao.update(orgSubParam);
				//修改绑卡记录
				bankcardDao.updateUserId(newUserId, entity.getOrgcode());
				//修改机构映射
				TitanOrgMapInfoParam orgMapInfoParam = new TitanOrgMapInfoParam();
				orgMapInfoParam.setNewOrgSubCode(newUserId);
				orgMapInfoParam.setOrgCode(entity.getOrgcode());
				titanOrgMapInfoDao.update(orgMapInfoParam);
				//b2真实证件机构注册
				RSOrg rsOrg = new RSOrg();
				rsOrg.setUserid(newUserId);
				rsOrg.setUsertype(entity.getUsertype());
				rsOrg.setOrgname(entity.getOrgname());
				rsOrg.setCertificatetype(entity.getCertificatetype());
				rsOrg.setCertificatenumber(entity.getCertificatenumber());
				rsOrg.setBuslince(entity.getBuslince());
				BaseResponse baseResponse = registerRSOrg(rsOrg);
				if(!baseResponse.isSuccess()){
					LOGGER.error("虚拟机构生成真实机构失败,虚拟机构编码orgcode："+entity.getOrgcode()+",错误信息baseResponse："+Tools.gsonToString(baseResponse));
				}
				//break;
			}
		}
	}
	
}
