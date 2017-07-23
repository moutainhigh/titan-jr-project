package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.exception.ParameterException;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.api.MerchantUserFacade;
import com.fangcang.merchant.dto.BaseResultDTO;
import com.fangcang.merchant.dto.MerchantUserCheckDTO;
import com.fangcang.merchant.dto.MerchantUserCreateDTO;
import com.fangcang.merchant.dto.MerchantUserDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.query.dto.MerchantUserQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.security.domain.Role;
import com.fangcang.security.domain.User;
import com.fangcang.security.facade.RoleFacade;
import com.fangcang.security.facade.UserFacade;
import com.fangcang.titanjr.common.enums.FinancialRoleEnum;
import com.fangcang.titanjr.common.enums.CoopTypeEnum;
import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.entity.TitanCheckCodeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanOrgCheckDao;
import com.fangcang.titanjr.dao.TitanRoleDao;
import com.fangcang.titanjr.dao.TitanUserBindInfoDao;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dao.TitanUserRoleDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.CheckStatus;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.SaaSMerchantUserDTO;
import com.fangcang.titanjr.dto.bean.TitanRoleDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.UserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.CancelPermissionRequest;
import com.fangcang.titanjr.dto.request.CheckUserRequest;
import com.fangcang.titanjr.dto.request.DeleteBindUserRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.FinancialUserBindRequest;
import com.fangcang.titanjr.dto.request.FinancialUserUnBindRequest;
import com.fangcang.titanjr.dto.request.LoginPasswordModifyRequest;
import com.fangcang.titanjr.dto.request.LoginPasswordRequest;
import com.fangcang.titanjr.dto.request.PassLoginRequest;
import com.fangcang.titanjr.dto.request.PayPasswordRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.SaaSUserRoleRequest;
import com.fangcang.titanjr.dto.request.SmsLoginRequest;
import com.fangcang.titanjr.dto.request.TitanRoleQueryRequest;
import com.fangcang.titanjr.dto.request.UpdateCheckCodeRequest;
import com.fangcang.titanjr.dto.request.UpdateUserRequest;
import com.fangcang.titanjr.dto.request.UserBindInfoRequest;
import com.fangcang.titanjr.dto.request.UserFreezeRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.UserLoginNameExistRequest;
import com.fangcang.titanjr.dto.request.UserRegisterRequest;
import com.fangcang.titanjr.dto.request.UserRoleSetRequest;
import com.fangcang.titanjr.dto.request.VerifyCheckCodeRequest;
import com.fangcang.titanjr.dto.response.CancelPermissionResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.CheckUserResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.FinancialUserBindResponse;
import com.fangcang.titanjr.dto.response.FinancialUserUnBindResponse;
import com.fangcang.titanjr.dto.response.LoginPasswordModifyResponse;
import com.fangcang.titanjr.dto.response.PassLoginResponse;
import com.fangcang.titanjr.dto.response.PayPasswordResponse;
import com.fangcang.titanjr.dto.response.PermissionResponse;
import com.fangcang.titanjr.dto.response.RoleUserInfoPageResponse;
import com.fangcang.titanjr.dto.response.SaaSUserRoleResponse;
import com.fangcang.titanjr.dto.response.SmsLoginResponse;
import com.fangcang.titanjr.dto.response.TitanRoleResponse;
import com.fangcang.titanjr.dto.response.UpdateUserResponse;
import com.fangcang.titanjr.dto.response.UserBindInfoResponse;
import com.fangcang.titanjr.dto.response.UserFreezeResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserLoginNameExistResponse;
import com.fangcang.titanjr.dto.response.UserRegisterResponse;
import com.fangcang.titanjr.dto.response.UserRoleSetResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.entity.TitanOrgCheck;
import com.fangcang.titanjr.entity.TitanRole;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.entity.TitanUserBindInfo;
import com.fangcang.titanjr.entity.TitanUserRole;
import com.fangcang.titanjr.entity.parameter.TitanOrgCheckParam;
import com.fangcang.titanjr.entity.parameter.TitanUserBindInfoParam;
import com.fangcang.titanjr.entity.parameter.TitanUserParam;
import com.fangcang.titanjr.entity.parameter.TitanUserRoleParam;
import com.fangcang.titanjr.enums.PermissionEnum;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

@Service("titanFinancialUserService")
public class TitanFinancialUserServiceImpl implements TitanFinancialUserService {

    private static final Log log = LogFactory.getLog(TitanFinancialUserServiceImpl.class);
    private static final int ISACTIVE_0_NO = 0;//无效
    private static final int ISACTIVE_1_YES = 1;//有效
    
    @Resource
    private TitanUserDao titanUserDao;

    @Resource
    private TitanUserBindInfoDao titanUserBindInfoDao;

    @Resource
    private TitanUserRoleDao titanUserRoleDao;

    @Resource
    private TitanRoleDao titanRoleDao;
    @Resource
    private TitanOrgCheckDao titanOrgCheckDao;
    
    @Resource  
    private TitanFinancialOrganService organService;
    
    @Resource
    private HessianProxyBeanFactory hessianProxyBeanFactory;

    private MerchantUserFacade merchantUserFacade;

    private MerchantFacade merchantFacade;

    //cas系统提供角色的hessian接口
    private RoleFacade roleFacade;

    //cas系统提供的用户操作的hessian接口
    private UserFacade userFacade;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public UserRegisterResponse registerFinancialUser(UserRegisterRequest userRegisterRequest) throws Exception {
        //参数校验,基础的非空校验
        UserRegisterResponse response = new UserRegisterResponse();
        if (!GenericValidate.validate(userRegisterRequest)) {
            response.putParamError();
            return response;
        }
        //SaaS页面注册时，商家编码和房仓登录名不能为空
        if (userRegisterRequest.getRegisterSource() == CoopTypeEnum.SAAS.getKey()) {
            if (!StringUtil.isValidString(userRegisterRequest.getFcLoginUserName()) ||
                    !StringUtil.isValidString(userRegisterRequest.getMerchantCode())) {
                response.putParamError();
                return response;
            }
        }
        //不是管理员用户时，金服角色id列表不能为空
        if (userRegisterRequest.getIsAdminUser() != 1) {
            if (CollectionUtils.isEmpty(userRegisterRequest.getRoleIdList())) {
                response.putErrorResult("金融权限不能为空");
                return response;
            }
        }
 
        //SAAS验证用户是否存在当前商家,2016-12-23
        if(userRegisterRequest.getRegisterSource()==CoopTypeEnum.SAAS.getKey()){
        	 MerchantUserCheckDTO checkDTO = new MerchantUserCheckDTO();
             checkDTO.setUserLoginName(userRegisterRequest.getLoginUserName());
             checkDTO.setMerchantCode(RSInvokeConstant.defaultMerchant);
             //TODO 检测机制要改变下，如果重复的用户属于金服的商家编码，则允许重复创建。但要修改相应的数据
             BaseResultDTO checkResult = getMerchantUserFacade().checkMerchantUser(checkDTO);
             if (!checkResult.getIsSuccessed()) {//登录名已存在
                 response.putErrorResult("USER_EXISTS", "登录用户名已存在");
                 return response;
             }
        }

        //1.金服添加用户
        TitanUser titanUser = new TitanUser();
        titanUser.setCreatetime(new Date());
        titanUser.setCreator(userRegisterRequest.getOperator());
        titanUser.setIsadmin(userRegisterRequest.getIsAdminUser());
        titanUser.setMerchantcode(userRegisterRequest.getMerchantCode());
        titanUser.setMobilephone(userRegisterRequest.getMobilePhone());
        titanUser.setOrgcode(userRegisterRequest.getOrgCode());
        titanUser.setUserid(userRegisterRequest.getUserId());
        titanUser.setPassword(MD5.MD5Encode(userRegisterRequest.getPassword()));
        titanUser.setStatus(TitanUserEnum.Status.AVAILABLE.getKey());
        titanUser.setUserloginname(userRegisterRequest.getLoginUserName());
        titanUser.setUsername(userRegisterRequest.getUserName());
        titanUserDao.insert(titanUser);//失败直接抛异常并回滚
        int tfsUserid = titanUser.getTfsuserid();

        //2.saas页面注册时金服添加用户绑定关系
        Long orgiUserId = null;//SaaS注册时存在，当前登录的SaaS用户的用户id
        if (userRegisterRequest.getRegisterSource() == CoopTypeEnum.SAAS.getKey()||userRegisterRequest.getRegisterSource() == CoopTypeEnum.TTM.getKey()) {
        	TitanUserBindInfo bindInfo = new TitanUserBindInfo();
        	//查询房仓金服商家已添加上的用户
            if(userRegisterRequest.getRegisterSource() == CoopTypeEnum.SAAS.getKey()){
            	MerchantUserQueryDTO queryDTO = new MerchantUserQueryDTO();
                List<String> loginNameList = new ArrayList<String>();
                loginNameList.add(userRegisterRequest.getFcLoginUserName());
                queryDTO.setUserLoginNameList(loginNameList);
                queryDTO.setMerchantCode(userRegisterRequest.getMerchantCode());
                com.fangcang.dao.PaginationSupport pg = merchantUserFacade.queryMerchantUser(queryDTO);
                if (CollectionUtils.isNotEmpty(pg.getItemList())) {
                    for (MerchantUserDTO userDTO : (List<MerchantUserDTO>)pg.getItemList()) {
                        orgiUserId = userDTO.getUserId();
                    }
                }
            }else{
            	if(StringUtil.isValidString(userRegisterRequest.getCoopUserId())){
                 	orgiUserId = Long.valueOf(userRegisterRequest.getCoopUserId());
                }
            }
            bindInfo.setUsername(userRegisterRequest.getUserName());
            bindInfo.setLoginname(userRegisterRequest.getLoginUserName());
            bindInfo.setFcloginname(userRegisterRequest.getFcLoginUserName());
            bindInfo.setIsactive(1);
            bindInfo.setTfsuserid(tfsUserid);
            bindInfo.setFcuserid(orgiUserId);
            bindInfo.setCooptype(userRegisterRequest.getRegisterSource());
            bindInfo.setMerchantcode(userRegisterRequest.getMerchantCode());
            bindInfo.setCreatetime(new Date());
            bindInfo.setCreator(userRegisterRequest.getOperator());
            titanUserBindInfoDao.insert(bindInfo);
        }
        //3.金服添加用户权限
        UserRoleSetRequest userRoleSetRequest = new UserRoleSetRequest();
        userRoleSetRequest.setUserRoleIdMap(new HashMap<Long, List<Long>>());
        userRoleSetRequest.setDeleteUserRoleIdMap(new HashMap<Long, List<Long>>());
        if (userRegisterRequest.getIsAdminUser() == 1) {
            List<TitanRole> titanRoles = titanRoleDao.queryTitanRoles(new TitanRoleQueryRequest());
            userRoleSetRequest.getUserRoleIdMap().put(Long.valueOf(String.valueOf(tfsUserid)),
                    new ArrayList<Long>());
            for (TitanRole role : titanRoles) {
                //新增时不能添加运营员权限
                if (role.getRolecode().equals(FinancialRoleEnum.OPERATION.roleCode)){
                    continue;
                }
                userRoleSetRequest.getUserRoleIdMap().
                        get(Long.valueOf(String.valueOf(tfsUserid))).add(role.getRoleid());
            }
        } else {
            userRoleSetRequest.getUserRoleIdMap().put(Long.valueOf(String.valueOf(tfsUserid)),
                    userRegisterRequest.getRoleIdList());
        }
        userRoleSetRequest.getDeleteUserRoleIdMap().put(Long.valueOf(String.valueOf(tfsUserid)),
                new ArrayList<Long>());
        userRoleSetRequest.getDeleteUserRoleIdMap().put(Long.valueOf(String.valueOf(tfsUserid)),userRegisterRequest.getUnselectRoleIdList());
        UserRoleSetResponse userRoleSetResponse = this.setFinancialUserRole(userRoleSetRequest);
        if (!userRoleSetResponse.isResult()) {
            log.error("金融权限添加失败，抛出异常回滚,userid:"+userRegisterRequest.getUserId());
            throw new Exception("金融权限初始化失败");
        }
        if(userRegisterRequest.getRegisterSource() == CoopTypeEnum.SAAS.getKey()){
	        //4.SaaS系统添加员工属于固定金服商家（需配置起来）
	        MerchantDetailQueryDTO merchantDetailQueryDTO = new MerchantDetailQueryDTO();
	        merchantDetailQueryDTO.setMerchantCode(RSInvokeConstant.defaultMerchant);
	        MerchantResponseDTO merchantResponseDTO = getMerchantFacade().queryMerchantDetail(merchantDetailQueryDTO);
	
	        MerchantUserCreateDTO user = new MerchantUserCreateDTO();
	        user.setMerchantId(merchantResponseDTO.getMerchantId());
	        user.setMerchantCode(RSInvokeConstant.defaultMerchant);
	        user.setUserLoginName(userRegisterRequest.getLoginUserName());
	        user.setUserName(userRegisterRequest.getLoginUserName());
	        user.setMobileNum(userRegisterRequest.getMobilePhone());
	        user.setIsSMS(1);
	        if(StringUtil.isValidString(userRegisterRequest.getOperator())){
	        	user.setCreator(userRegisterRequest.getOperator());
	        }else{
	        	user.setCreator(CommonConstant.CHECK_ADMIN_JR);
	        }
	        user.setCreatedate(new Date());
	        user.setUserLoginPassword(userRegisterRequest.getPassword());
	        BaseResultDTO baseResultDTO = merchantUserFacade.addMerchantUser(user);
	        if (!baseResultDTO.getIsSuccessed()) {
	            log.error("在房仓金服对应商家添加用户失败");
	            throw new Exception("在房仓固定商家添加用户失败");
	        }
        }
        response.putSuccess();
        return response;
    }

    @Override
    public UserInfoResponse queryFinancialUser(UserInfoQueryRequest userInfoQueryRequest) {
        UserInfoResponse response = new UserInfoResponse();
        if(StringUtil.isValidString(userInfoQueryRequest.getBindLoginName())&&!StringUtil.isValidString(userInfoQueryRequest.getBindMerchantCode())){
        	response.putErrorResult("合作方商家编码和登录用户名不能为空");
        	return response;
        }
        
        List<UserInfoDTO> userInfoDTOs =  titanUserDao.queryTitanUserList(userInfoQueryRequest);
        response.setUserInfoDTOList(userInfoDTOs);
        response.putSuccess();
        return response;
    }
    
     
    
    @Override
	public UserInfoPageResponse queryUserInfoPage(UserInfoQueryRequest userInfoQueryRequest) {
    	UserInfoPageResponse userInfoPageResponse = new UserInfoPageResponse();
    	PaginationSupport<TitanUser> paginationSupport = new PaginationSupport<TitanUser>();
    	paginationSupport.setPageSize(userInfoQueryRequest.getPageSize());
    	paginationSupport.setCurrentPage(userInfoQueryRequest.getCurrentPage());
    	
    	TitanUserParam condition = new TitanUserParam();
    	condition.setTfsuserid(userInfoQueryRequest.getTfsUserId());
    	condition.setOrgcode(userInfoQueryRequest.getOrgCode());
    	condition.setUserloginname(userInfoQueryRequest.getUserLoginName());
    	condition.setStatus(userInfoQueryRequest.getStatus());
    	condition.setIsadmin(userInfoQueryRequest.getIsadmin());
    	paginationSupport = titanUserDao.selectForPage(condition, paginationSupport);
    	userInfoPageResponse.setTitanUserPaginationSupport(paginationSupport);
    	
		return userInfoPageResponse;
	}

	@Override
	public UserBindInfoResponse queryUserBindInfoDTO(UserBindInfoRequest userBindInfoRequest) {
		UserBindInfoResponse userBindInfoResponse = new UserBindInfoResponse();
		if(StringUtil.isValidString(userBindInfoRequest.getMerchantcode())&&userBindInfoRequest.getCooptype()==null){
			//如果传了商家编码，默认合作方为SAAS
			userBindInfoRequest.setCooptype(CoopTypeEnum.SAAS.getKey());
		}
    	
    	TitanUserBindInfoParam param = new TitanUserBindInfoParam();
    	MyBeanUtil.copyBeanProperties(param, userBindInfoRequest);
    	PaginationSupport<TitanUserBindInfo> paginationSupport = new PaginationSupport<TitanUserBindInfo>();
    	PaginationSupport<UserBindInfoDTO> userBindInfoPage = new PaginationSupport<UserBindInfoDTO>();
    	
    	paginationSupport.setPageSize(userBindInfoRequest.getPageSize());
    	paginationSupport.setCurrentPage(userBindInfoRequest.getCurrentPage());
    	userBindInfoPage.setPageSize(userBindInfoRequest.getPageSize());
    	userBindInfoPage.setCurrentPage(userBindInfoRequest.getCurrentPage());
    	
    	paginationSupport = titanUserBindInfoDao.selectForPage(param, paginationSupport);
    	List<UserBindInfoDTO> userBindInfoDTOList = new ArrayList<UserBindInfoDTO>();
    	if(paginationSupport.getItemList().size()>0){
    		for(TitanUserBindInfo entityBindInfo : paginationSupport.getItemList()){
    			UserBindInfoDTO item = new UserBindInfoDTO();
        		item.setFcLoginName(entityBindInfo.getFcloginname());
        		item.setFcUserId(entityBindInfo.getFcuserid());
        		item.setFcUserName(entityBindInfo.getFcusername());
        		item.setLoginName(entityBindInfo.getLoginname());
        		item.setUserName(entityBindInfo.getUsername());
        		item.setCoopType(entityBindInfo.getCooptype());
        		item.setMerchantCode(entityBindInfo.getMerchantcode());
        		item.setIsActive(entityBindInfo.getIsactive());
        		userBindInfoDTOList.add(item);
    		}
    	}
    	userBindInfoPage.setItemList(userBindInfoDTOList);
    	userBindInfoResponse.setPaginationSupport(userBindInfoPage);
    	userBindInfoResponse.putSuccess();
		return userBindInfoResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO deleteBindUser(DeleteBindUserRequest deleteBindUserRequest) throws MessageServiceException {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		baseResponseDTO.putSuccess("删除成功");
		if(!StringUtil.isValidString(deleteBindUserRequest.getMerchantCode())){
			baseResponseDTO.putErrorResult("参数MerchantCode[商家编码]不能为空");
			return baseResponseDTO;
		}
		try {
			TitanUserBindInfoParam deletePrama = new TitanUserBindInfoParam();
			deletePrama.setMerchantcode(deleteBindUserRequest.getMerchantCode());
			titanUserBindInfoDao.delete(deletePrama);
			TitanUserParam entity = new TitanUserParam();
			entity.setMerchantcode("");
			entity.setClauseMerchantCode(deleteBindUserRequest.getMerchantCode());
			titanUserDao.update(entity);
		} catch (DaoException e) {
			log.error("删除用户绑定关系失败,参数deleteBindUserRequest："+Tools.gsonToString(deleteBindUserRequest),e);
			throw new MessageServiceException("删除用户绑定关系失败",e);
		}
		return baseResponseDTO;
	}

	@Override
	public RoleUserInfoPageResponse queryRoleUserInfoPage(UserInfoQueryRequest userInfoQueryRequest) {
    	RoleUserInfoPageResponse response = new RoleUserInfoPageResponse();
		PaginationSupport<UserInfoDTO> paginationSupport = new PaginationSupport<UserInfoDTO>();
		if(StringUtil.isValidString(userInfoQueryRequest.getBindLoginName())&&!StringUtil.isValidString(userInfoQueryRequest.getBindMerchantCode())){
        	response.putErrorResult("合作方商家编码和登录用户名不能为空");
        	return response;
        }
		//通过合作方商家编码查询的，默认为SAAS
    	if(StringUtil.isValidString(userInfoQueryRequest.getBindMerchantCode())&&userInfoQueryRequest.getCoopType()==null){
    		userInfoQueryRequest.setCoopType(CoopTypeEnum.SAAS.getKey());
    	}
		try {
			paginationSupport = titanUserDao.selectForRoleUserInfoPage(userInfoQueryRequest);
			response.setPaginationSupport(paginationSupport);
		} catch (Exception e) {
			 log.error("分页查询金服用户错误，param:"+JSONSerializer.toJSON(userInfoQueryRequest).toString(), e);
			response.putSysError();
			return response;
		}
		response.putSuccess();
		return response;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public UserRoleSetResponse setFinancialUserRole(UserRoleSetRequest userRoleSetRequest) throws GlobalServiceException {
        UserRoleSetResponse response = new UserRoleSetResponse();
        List<TitanUserRole> userRoleList = new ArrayList<TitanUserRole>();
        Date now = new Date();
        try {
	        if (!userRoleSetRequest.getUserRoleIdMap().isEmpty()) {
	            for (Long tfsUserId : userRoleSetRequest.getUserRoleIdMap().keySet()) {
	                if (CollectionUtils.isNotEmpty(userRoleSetRequest.getUserRoleIdMap().get(tfsUserId))) {
	             		
	                    for (Long roleId : userRoleSetRequest.getUserRoleIdMap().get(tfsUserId)) {
	                        TitanUserRole userRole = new TitanUserRole();
	                        userRole.setTfsuserid(Integer.valueOf(String.valueOf(tfsUserId)));
	                        userRole.setRoleid(Integer.valueOf(String.valueOf(roleId)));
	                        userRole.setIsactive(1);
	                        userRole.setCreator(userRoleSetRequest.getOperator());
	                        userRole.setCreatetime(now);
	                        userRoleList.add(userRole);
	                        
	                        TitanUserRoleParam condition = new TitanUserRoleParam();
	                        condition.setTfsuserid(Integer.valueOf(String.valueOf(tfsUserId)));
	                        condition.setRoleid(Integer.valueOf(String.valueOf(roleId)));
	                        //先删除要添加的角色
	                        titanUserRoleDao.deleteUserRole(condition);
	                    }
	                }
	            }
	        }
       
        	//把取消勾选的设置为无效的
    		for (Long tfsUserId : userRoleSetRequest.getDeleteUserRoleIdMap().keySet()) {
                if (CollectionUtils.isNotEmpty(userRoleSetRequest.getDeleteUserRoleIdMap().get(tfsUserId))) {
                    for (Long roleId : userRoleSetRequest.getDeleteUserRoleIdMap().get(tfsUserId)) {
                        TitanUserRole userRole = new TitanUserRole();
                        userRole.setTfsuserid(Integer.valueOf(String.valueOf(tfsUserId)));
                        userRole.setRoleid(Integer.valueOf(String.valueOf(roleId)));
                        userRole.setIsactive(0);
                        userRole.setModifier(userRoleSetRequest.getOperator());
                        userRole.setModifytime(now);
                        titanUserRoleDao.update(userRole);
                    }
                }
            }
         	//添加所有勾选的
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                titanUserRoleDao.batchSaveUserRoles(userRoleList);
            }
        } catch (Exception e) {
            log.error("批量保存用户角色映射关系错误", e);
            throw new GlobalServiceException("批量保存用户角色映射关系错误,参数:"+JSONSerializer.toJSON(userRoleSetRequest).toString(),e);
        }
        response.putSuccess();
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws GlobalServiceException {
    	UpdateUserResponse updateUserResponse = new UpdateUserResponse();
    	if(updateUserRequest.getTfsUserId()<=0){
    		updateUserResponse.putErrorResult("TfsUserId参数错误");
    		return updateUserResponse;
    	}
    	TitanUser titanUser =  titanUserDao.selectTitanUser(updateUserRequest.getTfsUserId());
    	if(titanUser==null){
    		updateUserResponse.putErrorResult("参数错误");
    		return updateUserResponse;
    	}
    	try {
    		TitanUserParam entity = new TitanUserParam();
	    	entity.setTfsuserid(updateUserRequest.getTfsUserId());
	    	entity.setUsername(updateUserRequest.getUserName());
	    	entity.setModifier(updateUserRequest.getOperator());
	    	entity.setModifytime(new Date());
	    	titanUserDao.update(entity);
	    	
	    	if(titanUser.getIsadmin()==0){
	    		//非管理员才可以被修改角色
	    		UserRoleSetRequest userRoleSetRequest = new UserRoleSetRequest();
		    	userRoleSetRequest.setUserRoleIdMap(new HashMap<Long, List<Long>>());
		    	userRoleSetRequest.setOperator(updateUserRequest.getOperator());
		        userRoleSetRequest.getUserRoleIdMap().put(Long.valueOf(String.valueOf(updateUserRequest.getTfsUserId())),updateUserRequest.getRoleIdList());
		        userRoleSetRequest.setDeleteUserRoleIdMap(new HashMap<Long, List<Long>>());
		        userRoleSetRequest.getDeleteUserRoleIdMap().put(Long.valueOf(String.valueOf(updateUserRequest.getTfsUserId())),updateUserRequest.getUnselectRoleIdList());
				setFinancialUserRole(userRoleSetRequest);
	    	}
			
			updateUserResponse.putSuccess();
		} catch (GlobalServiceException e) {
			throw new GlobalServiceException("金服员工信息修改失败，参数updateUserRequest："+JSONSerializer.toJSON(updateUserRequest).toString(),e);
		}
    	
		return updateUserResponse;
	}

	@Override
	public SaaSUserRoleResponse querySaaSUserRolePage(SaaSUserRoleRequest request) throws GlobalServiceException {
    	SaaSUserRoleResponse response = new SaaSUserRoleResponse();
    	if(!StringUtil.isValidString(request.getMerchantCode())){
    		response.putErrorResult("商家编码不能为空");
    		return response;
    	}
    	
    	MerchantUserQueryDTO merchantuserquerydto = new MerchantUserQueryDTO();
    	merchantuserquerydto.setMerchantCode(request.getMerchantCode());
    	List<Long> userIdList = new ArrayList<Long>();
    	if(request.getFcUserId()!=null&&request.getFcUserId()>0){
    		userIdList.add(Long.valueOf(request.getFcUserId().toString()));
        	merchantuserquerydto.setUserIdList(userIdList);
    	}
    	
    	merchantuserquerydto.setUserName(request.getSaasUserName());
    	merchantuserquerydto.setPageNo(request.getCurrentPage());
    	merchantuserquerydto.setPageSize(request.getPageSize());
    	try {
    		com.fangcang.dao.PaginationSupport merchantUserPage = getMerchantUserFacade().queryMerchantUser(merchantuserquerydto);
        	PaginationSupport<SaaSMerchantUserDTO> saasUserPaginationSupport = new PaginationSupport<SaaSMerchantUserDTO>();
        	List<SaaSMerchantUserDTO> saaslist = new ArrayList<SaaSMerchantUserDTO>();
        	saasUserPaginationSupport.setCurrentPage(merchantUserPage.getCurrentPage());
        	saasUserPaginationSupport.setPageSize(request.getPageSize());
        	saasUserPaginationSupport.setTotalCount(merchantUserPage.getTotalCount());
        	saasUserPaginationSupport.setTotalPage(merchantUserPage.getTotalPage());
        	
        	List<MerchantUserDTO> merchantUserList = (List<MerchantUserDTO>)merchantUserPage.getItemList();
        	if(merchantUserPage.getItemList().size()>0){
        		for(MerchantUserDTO item : merchantUserList){
        			SaaSMerchantUserDTO entity = new SaaSMerchantUserDTO();
        			entity.setUserId(item.getUserId());
        			entity.setUserLoginName(item.getUserLoginName());
        			entity.setUserName(item.getUserName());
        			entity.setMobileNum(item.getMobileNum());
        			entity.setRoleName(appendRoleName(item.getRoleList()));
        			entity.setIsSMS(item.getIsSMS());
        			entity.setActive(item.getActive());
        			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
        			titanUserBindInfoDTO.setFcuserid(item.getUserId());
        			titanUserBindInfoDTO= this.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
        			if(titanUserBindInfoDTO!=null){
        				entity.setIsAddTfs(1);
        			}else{
        				entity.setIsAddTfs(0);
        			}
        			
        			saaslist.add(entity);
        		}
        	}
        	saasUserPaginationSupport.setItemList(saaslist);
        	response.setPaginationSupport(saasUserPaginationSupport);
		} catch (Exception e) {
			throw new GlobalServiceException("查询SaaS用户失败,SaaSUserRoleRequest:"+JSONSerializer.toJSON(request).toString(),e);
		}
    	
    	response.putSuccess();
		return response;
	}
	/**
	 * 角色名称
	 * @param list
	 * @return
	 */
    private String appendRoleName(List<RoleDTO> list){
    	String temp = "";
    	if(list!=null&&list.size()>0){
    		for(RoleDTO itemDto : list){
    			temp += itemDto.getRoleName()+",";
    		}
    		temp = temp.substring(0, temp.length()-1);
    	}
    	return temp;
    }
    
	@Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public FinancialUserBindResponse bindFinancialUser(FinancialUserBindRequest financialUserBindRequest) {
        FinancialUserBindResponse response = new FinancialUserBindResponse();
        if (!GenericValidate.validate(financialUserBindRequest)) {
            response.putErrorResult("必填参数不能为空");
            return response;
        }
        //1.金服添加用户绑定关系
        //判断是否已经存在
        TitanUserBindInfoParam condition = new TitanUserBindInfoParam();
        condition.setTfsuserid(financialUserBindRequest.getUserid());
        condition.setMerchantcode(financialUserBindRequest.getMerchantCode());
        PaginationSupport<TitanUserBindInfo> page = new PaginationSupport<TitanUserBindInfo>();
        titanUserBindInfoDao.selectForPage(condition, page);
        if(page.getItemList().size()>0){
        	//存在，则修改
        	TitanUserBindInfo bindInfo = new TitanUserBindInfo();
        	bindInfo.setTfsuserid(financialUserBindRequest.getUserid());
            bindInfo.setMerchantcode(financialUserBindRequest.getMerchantCode());
            bindInfo.setCooptype(financialUserBindRequest.getCoopType());
            bindInfo.setUsername("");
            bindInfo.setLoginname(financialUserBindRequest.getLoginUserName());
            bindInfo.setFcloginname(financialUserBindRequest.getFcLoginUserName());
            bindInfo.setFcuserid(NumberUtils.toLong(financialUserBindRequest.getFcuserid().toString()));
            bindInfo.setIsactive(ISACTIVE_1_YES);
            bindInfo.setModifior(financialUserBindRequest.getOperator());
            bindInfo.setModifytime(new Date());
            titanUserBindInfoDao.update(bindInfo);
        	
        }else{
        	//不存在，则添加
        	TitanUserBindInfo bindInfo = new TitanUserBindInfo();
        	bindInfo.setTfsuserid(financialUserBindRequest.getUserid());
            bindInfo.setMerchantcode(financialUserBindRequest.getMerchantCode());
            bindInfo.setCooptype(financialUserBindRequest.getCoopType());
            bindInfo.setUsername("");
            bindInfo.setLoginname(financialUserBindRequest.getLoginUserName());
            bindInfo.setFcloginname(financialUserBindRequest.getFcLoginUserName());
            bindInfo.setFcuserid(NumberUtils.toLong(financialUserBindRequest.getFcuserid().toString()));
            bindInfo.setIsactive(ISACTIVE_1_YES);
            bindInfo.setCreatetime(new Date());
            bindInfo.setCreator(financialUserBindRequest.getOperator());
            titanUserBindInfoDao.insert(bindInfo);
        }
        //2.SaaS被绑定的员工绑定权限
        MerchantUserQueryDTO queryDTO = new MerchantUserQueryDTO();
        queryDTO.setUserLoginNameList(new ArrayList<String>());
        queryDTO.getUserLoginNameList().add(financialUserBindRequest.getFcLoginUserName());
        queryDTO.setMerchantCode(financialUserBindRequest.getMerchantCode());
        List<MerchantUserDTO> merchantUserDTOs = getMerchantUserFacade().queryMerchantUserForSMS(queryDTO);
        if (CollectionUtils.isNotEmpty(merchantUserDTOs)){
            Role titanjrRole = getRoleFacade().getRoleById(Integer.valueOf(String.valueOf(RSInvokeConstant.defaultRoleId)));
            User newUser = getUserFacade().getUserById(Integer.valueOf(String.valueOf(merchantUserDTOs.get(0).getUserId())));
            getUserFacade().addRolesToUser(newUser,new Role[]{titanjrRole});
        }
        response.putSuccess();
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public FinancialUserUnBindResponse unbindFinancialUser(FinancialUserUnBindRequest financialUserUnBindRequest)throws GlobalServiceException {
    	FinancialUserUnBindResponse response = new FinancialUserUnBindResponse();
    	try {
    		if(financialUserUnBindRequest.getTfsuserid()!=null){
        		//根据金服用户id解除关系
        		TitanUserBindInfo entity = new TitanUserBindInfo();
        		entity.setIsactive(ISACTIVE_0_NO);
        		entity.setTfsuserid(financialUserUnBindRequest.getTfsuserid());
        		entity.setModifior(financialUserUnBindRequest.getOperator());
        		entity.setModifytime(new Date());
        		titanUserBindInfoDao.update(entity);	
        	}else if(financialUserUnBindRequest.getTfsuserid()==null&&financialUserUnBindRequest.getMerchantcode()!=null&&financialUserUnBindRequest.getCoopType()!=null){
        		//批量解绑机构下的所有员工
        		titanUserBindInfoDao.updateIsactiveBatch(ISACTIVE_0_NO,financialUserUnBindRequest.getCoopType(), financialUserUnBindRequest.getMerchantcode(),financialUserUnBindRequest.getOperator(),new Date());
        	}else{
        		throw new ParameterException("非法参数");
        	}
		} catch (Exception e) {
			throw new GlobalServiceException("用户解绑失败,param:"+JSONSerializer.toJSON(financialUserUnBindRequest).toString(),e);
		}
    	response.putSuccess();
		return response;
	}
    
    
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public UserFreezeResponse freezeUser(UserFreezeRequest userFreezeRequest)throws GlobalServiceException {
		UserFreezeResponse response = new UserFreezeResponse();
		if (!GenericValidate.validate(userFreezeRequest)){
			response.putParamError();
			return response;
		}
		try {
			TitanUserParam titanUser = new TitanUserParam();
			titanUser.setTfsuserid(userFreezeRequest.getTfsUserId());
			titanUser.setStatus(userFreezeRequest.getStatus());
			titanUser.setModifier(userFreezeRequest.getOperator());
			titanUser.setModifytime(new Date());
			titanUserDao.update(titanUser);
			//TODO 写入日志表
			log.info("冻结或解冻用户,param:"+JSONSerializer.toJSON(userFreezeRequest).toString());
		} catch (Exception e) {
			throw new GlobalServiceException("冻结或解冻用户失败，param:"+JSONSerializer.toJSON(userFreezeRequest).toString(),e);
		}
		response.putSuccess();
		return response;
	}

	@Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public TitanRoleResponse queryTitanRole(TitanRoleQueryRequest titanRoleQueryRequest) {
        TitanRoleResponse response = new TitanRoleResponse();
        List<TitanRole> titanRoles = null;
        try {
            titanRoles = titanRoleDao.queryTitanRoles(titanRoleQueryRequest);
        } catch (Exception e) {
            log.error("查询金服角色失败", e);
            response.putSysError();
            return response;
        }
        List<TitanRoleDTO> titanRoleDTOList = MyBeanUtil.copyCollection(titanRoles, TitanRoleDTO.class);
        response.setTitanRoleDTOList(titanRoleDTOList);
        response.putSuccess();
        return response;
    }

    private MerchantUserFacade getMerchantUserFacade(){
        if (null == merchantUserFacade){
            merchantUserFacade = hessianProxyBeanFactory.getHessianProxyBean(MerchantUserFacade.class,
                    ProxyFactoryConstants.merchantServerUrl + "merchantUserFacade");
        }
        return merchantUserFacade;
    }

    private MerchantFacade getMerchantFacade(){
        if (null == merchantFacade){
            merchantFacade = hessianProxyBeanFactory.getHessianProxyBean(MerchantFacade.class,
                    ProxyFactoryConstants.merchantServerUrl + "merchantFacade");
        }
        return merchantFacade;
    }
    private UserFacade getUserFacade(){
        if (null == userFacade){
            userFacade = hessianProxyBeanFactory.getHessianProxyBean(UserFacade.class,
            		ProxyFactoryConstants.securityUrl + "userFacade");
        }
        return userFacade;
    }

    private RoleFacade getRoleFacade(){
        if (null == roleFacade){
            roleFacade = hessianProxyBeanFactory.getHessianProxyBean(RoleFacade.class,
            		ProxyFactoryConstants.securityUrl + "roleFacade");
        }
        return roleFacade;
    }

	@Override
	public PermissionResponse getUserPermission(PermissionRequest permissionRequest) {
		PermissionResponse permissionResponse = new PermissionResponse();
		try{
			if(permissionRequest !=null){
				//fcUserid 查找tfsUserid
				if(StringUtil.isValidString(permissionRequest.getFcuserid())){
					TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
					titanUserBindInfoDTO.setFcuserid(Long.parseLong(permissionRequest.getFcuserid()));
					titanUserBindInfoDTO = this.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
				    if(titanUserBindInfoDTO !=null && titanUserBindInfoDTO.getTfsuserid()!=null){
				    	permissionRequest.setTfsuserid(titanUserBindInfoDTO.getTfsuserid().toString());
				    }
				}
				List<TitanUserRole> titanUserRoleList = titanUserRoleDao.selectUserRolesByuserid(permissionRequest.getTfsuserid());
				if(titanUserRoleList !=null && titanUserRoleList.size()>0){
					List<String> roleids = new ArrayList<String>();
					for(TitanUserRole userRole:titanUserRoleList){
						if(userRole.getRoleid() !=null){
							roleids.add(userRole.getRoleid().toString());
						}
					}
					if(roleids.size()>0){
						List<TitanRoleDTO> titanRoleDtoList = new ArrayList<TitanRoleDTO>();
						//查询出其对应的权限
						TitanRoleQueryRequest titanRoleQueryRequest = new TitanRoleQueryRequest();
						titanRoleQueryRequest.setRoleids(roleids);
						List<TitanRole> titanRoleList = titanRoleDao.queryTitanRolesByRoleId(titanRoleQueryRequest);
						for(TitanRole titanRole :titanRoleList){
							TitanRoleDTO titanRoleDTO = new TitanRoleDTO();
							MyBeanUtil.copyProperties(titanRoleDTO, titanRole);
							titanRoleDtoList.add(titanRoleDTO);
						}
						permissionResponse.putSuccess();
						permissionResponse.setTitanRoleDtoList(titanRoleDtoList);
						return permissionResponse;
					}
				}
			}
		}catch(Exception e){
			log.error("查询"+permissionRequest.getTfsuserid()+"用户权限失败", e);
		}
		permissionResponse.putSysError();
		return permissionResponse;
	}

	@Override
	public PayPasswordResponse saveOrUpdatePayPassword(
			PayPasswordRequest payPasswordRequest) {
		PayPasswordResponse payPasswordResponse = new PayPasswordResponse();
		try{
			if(payPasswordRequest !=null){
				TitanUserParam titanUser = new TitanUserParam();
				if(StringUtil.isValidString(payPasswordRequest.getTfsuserid())){
					titanUser.setTfsuserid(Integer.parseInt(payPasswordRequest.getTfsuserid()));
				}else{
					TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
					titanUserBindInfoDTO.setFcuserid(Long.parseLong(payPasswordRequest.getFcuserid()));
					
					titanUserBindInfoDTO = this.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
					if(titanUserBindInfoDTO !=null){
						titanUser.setTfsuserid(titanUserBindInfoDTO.getTfsuserid());
					}
				}
				//如果原密码不为空则验证原密码，修改密码，
				if(titanUser.getTfsuserid()!=null && StringUtil.isValidString(payPasswordRequest.getOldPassword())){
					TitanUser user = titanUserDao.selectTitanUser(titanUser.getTfsuserid());
					if(user !=null && StringUtil.isValidString(user.getPaypassword())){
						String oldPassword = MD5.titanEncrypt(payPasswordRequest.getOldPassword(), user.getPaySalt()).toUpperCase();
						if(!oldPassword.equals(user.getPaypassword())){
							payPasswordResponse.putErrorResult("原密码错误");
							return payPasswordResponse;
						}
					}
				}
				
				//如果交易密码未设置，则设置交易密码，
				if(StringUtil.isValidString(payPasswordRequest.getPayPassword())){
					String paySalt = MD5.getSalt();
					String md5PayPassword = MD5.titanEncrypt(payPasswordRequest.getPayPassword(),paySalt);
					titanUser.setPaypassword(md5PayPassword.toUpperCase());
					if(titanUser.getTfsuserid() !=null){
						TitanUser user = titanUserDao.selectTitanUser(titanUser.getTfsuserid());
//						如果交易密码已经设置，则需要原密码进行修改交易密码
						if(user !=null){
							if(!StringUtil.isValidString(user.getPaypassword()) 
									|| StringUtil.isValidString(payPasswordRequest.getPayPassword()) 
									|| CommonConstant.IS_FORGET_PAYPASSWORD.equals(payPasswordRequest.getIsForget()) ){
								titanUser.setPaySalt(paySalt);
								int row = titanUserDao.update(titanUser);
								if(row>0){
									payPasswordResponse.putSuccess();
									payPasswordResponse.setSaveSuccess(true);
								}
							}else{
								payPasswordResponse.putErrorResult("交易密码已设置，修改密码请输入原密码");
							}
						}
						return payPasswordResponse;
					}
				}
			}
			payPasswordResponse.putParamError();
		}catch(Exception e){
			e.printStackTrace();
			payPasswordResponse.putSysError();
		}
		return payPasswordResponse;
	}
	
	@Override
	public BaseResponseDTO saveLoginPassword(LoginPasswordRequest loginPasswordRequest)
			throws GlobalServiceException {
		BaseResponseDTO response = new BaseResponseDTO();
		if (!GenericValidate.validate(loginPasswordRequest)) {
            response.putErrorResult("参数不能为空");
            return response;
        }
		TitanUserParam entity = new TitanUserParam();
		entity.setTfsuserid(loginPasswordRequest.getTfsuserid());
		entity.setPassword(MD5.MD5Encode(loginPasswordRequest.getNewLoginPassword()));
		entity.setModifier(loginPasswordRequest.getOperator());
		entity.setModifytime(new Date());
		try {
			titanUserDao.update(entity);
			response.putSuccess("登录密码修改成功");
		} catch (DaoException e) {
			throw new GlobalServiceException("修改登录密码错误，参数loginPasswordRequest:"+JSONSerializer.toJSON(loginPasswordRequest).toString(), e);
		}
		return response;
	}

	public void setTitanUserDao(TitanUserDao titanUserDao) {
		this.titanUserDao = titanUserDao;
	}

	public void setTitanUserBindInfoDao(TitanUserBindInfoDao titanUserBindInfoDao) {
		this.titanUserBindInfoDao = titanUserBindInfoDao;
	}

	public void setTitanUserRoleDao(TitanUserRoleDao titanUserRoleDao) {
		this.titanUserRoleDao = titanUserRoleDao;
	}

	public void setHessianProxyBeanFactory(HessianProxyBeanFactory hessianProxyBeanFactory) {
		this.hessianProxyBeanFactory = hessianProxyBeanFactory;
	}

	@Override
	public CheckPermissionResponse checkUserPermission(PermissionRequest permissionRequest) {
		CheckPermissionResponse checkPermissionResponse = new CheckPermissionResponse();
		try{
			if(permissionRequest != null && StringUtil.isValidString(permissionRequest.getPermission())){
				PermissionResponse permissionResponse = this.getUserPermission(permissionRequest);
				if(CollectionUtils.isNotEmpty(permissionResponse.getTitanRoleDtoList())){
					for(TitanRoleDTO titanRoleDto: permissionResponse.getTitanRoleDtoList()){
						String per = PermissionEnum.getValueByKey(permissionRequest.getPermission());
						if(per.equals(titanRoleDto.getRolecode())){
							checkPermissionResponse.setPermission(true);
							checkPermissionResponse.putSuccess();
							return checkPermissionResponse;
						}
			 		}
			    }
			}
		}catch(Exception e){
			log.error("获取是否授权的操作失败", e);
		}
		checkPermissionResponse.setPermission(false);
		checkPermissionResponse.putSysError();
		return checkPermissionResponse;
	}

	
	@Override
	public CancelPermissionResponse cancelPermission(CancelPermissionRequest cancelPermissionRequest)throws GlobalServiceException, MessageServiceException {
		CancelPermissionResponse response = new CancelPermissionResponse();
		
		if(cancelPermissionRequest.getTfsUserId()==null||cancelPermissionRequest.getTfsUserId()<=0){
			response.putParamError();
			return response;
		}
		TitanUser titanUser = titanUserDao.selectTitanUser(cancelPermissionRequest.getTfsUserId());
		//管理员权限不允许修改
		if(titanUser.getIsadmin()==1){
			response.putErrorResult("该用户权限不允许修改");
			return response;
		}
		try {
			TitanUserParam modifyTitanUser = new TitanUserParam();
			modifyTitanUser.setTfsuserid(cancelPermissionRequest.getTfsUserId());
			modifyTitanUser.setStatus(TitanUserEnum.Status.NOT_AVAILABLE.getKey());
			titanUserDao.update(modifyTitanUser);
			
			FinancialUserUnBindRequest financialUserUnBindRequest = new FinancialUserUnBindRequest();
			financialUserUnBindRequest.setMerchantcode(cancelPermissionRequest.getMerchantcode());
			financialUserUnBindRequest.setTfsuserid(cancelPermissionRequest.getTfsUserId());
			this.unbindFinancialUser(financialUserUnBindRequest);
			
		} catch (Exception e) {
			throw new GlobalServiceException("删除用户权限失败，cancelPermissionRequest:"+JSONSerializer.toJSON(cancelPermissionRequest).toString(),e);
		}
		response.putSuccess();
		
		return response;
	}

	@Override
	public boolean checkPayPassword(String payPassword, String tfsuserid) {
		try{
			if(StringUtil.isValidString(tfsuserid) && StringUtil.isValidString(payPassword)){
				TitanUser user = titanUserDao.selectTitanUser(Integer.parseInt(tfsuserid));
				if(user !=null){
					String md5PayPassword = MD5.titanEncrypt(payPassword, user.getPaySalt());
					if(StringUtil.isValidString(md5PayPassword)&&md5PayPassword.toUpperCase().equals(user.getPaypassword())){
						return true;
					}
				}
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		return false;
	}

	@Override
	public UserLoginNameExistResponse userLoginNameExist(UserLoginNameExistRequest request) throws GlobalServiceException {
		UserLoginNameExistResponse response = new UserLoginNameExistResponse();
		try {
			if (!GenericValidate.validate(request)) {
	            response.putParamError();
	            return response;
	        }
			TitanUserParam param = new TitanUserParam();
			param.setUserloginname(request.getUserLoginName());
			PaginationSupport<TitanUser> page = new PaginationSupport<TitanUser>();
			page = titanUserDao.selectForPage(param, page);
			if(page.getItemList().size()>0){
				response.putErrorResult("-100", "该登录用户名已经存在，请使用其他用户名");
				return response;
			}
			//saas 是否已经存在
			MerchantUserCheckDTO checkDTO = new MerchantUserCheckDTO();
	        checkDTO.setUserLoginName(request.getUserLoginName());
	        checkDTO.setMerchantCode(RSInvokeConstant.defaultMerchant);
	        BaseResultDTO checkResult = getMerchantUserFacade().checkMerchantUser(checkDTO);
	        if (!checkResult.getIsSuccessed()) {//登录名已存在
	        	response.putErrorResult("-100", "该登录用户名已经存在，请使用其他用户名");
	            return response;
	        }
			
		} catch (Exception e) {
			throw new GlobalServiceException("userLoginNameExist,param:"+JSONSerializer.toJSON(request).toString(),e);
		}
		
		response.putSuccess("该用户名不存在，可以注册");
		return response;
	}

	
	@Override
	public boolean checkIsSetPayPassword(String fcUserid,String tfsUserId) {
		try{
			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
			if(StringUtil.isValidString(fcUserid)){
				titanUserBindInfoDTO.setFcuserid(Long.parseLong(fcUserid));
				titanUserBindInfoDTO = getUserBindInfoByFcuserid(titanUserBindInfoDTO);
				if(titanUserBindInfoDTO !=null && titanUserBindInfoDTO.getTfsuserid() !=null){
					tfsUserId = titanUserBindInfoDTO.getTfsuserid().toString();
				}
			}
			
			if(StringUtil.isValidString(tfsUserId)){
				TitanUser user = titanUserDao.selectTitanUser(Integer.parseInt(tfsUserId));
				if(user !=null && !StringUtil.isValidString(user.getPaypassword())){
					//没有设置
				   return true;
				}
			}
			
		}catch(Exception e){
			log.error("检查密码失败"+e.getMessage(),e);
		}
		//设置了密码
		return false;
	}

	@Override
	public TitanUserBindInfoDTO getUserBindInfoByFcuserid(TitanUserBindInfoDTO titanUserBindInfoDTO)
			 {
		try{
			TitanUserBindInfo titanUserBindInfo = new TitanUserBindInfo();
			titanUserBindInfo.setFcuserid(titanUserBindInfoDTO.getFcuserid());
			if(titanUserBindInfoDTO.getCooptype()==null){
				titanUserBindInfo.setCooptype(CoopTypeEnum.SAAS.getKey());
			}else{
				titanUserBindInfo.setCooptype(titanUserBindInfoDTO.getCooptype());
			}
			
			titanUserBindInfo.setTfsuserid(titanUserBindInfoDTO.getTfsuserid());
			List<TitanUserBindInfo>  titanUserBindInfoList=  titanUserBindInfoDao.selectUserBindInfoByFcuserid(titanUserBindInfo);
		    if(titanUserBindInfoList !=null && titanUserBindInfoList.size()==1){
		    	TitanUserBindInfoDTO  userBindInfoDTO = new TitanUserBindInfoDTO();
		    	titanUserBindInfo = titanUserBindInfoList.get(0);
		    	MyBeanUtil.copyProperties(userBindInfoDTO, titanUserBindInfo);
		    	return userBindInfoDTO;
		    }
		}catch(Exception e){
			log.error("根据房仓用户信息查询金融用户信息失败"+e.getMessage(),e);
//			throw new GlobalServiceException();
		}
		return null;
	}

	@Override
	public PassLoginResponse passLogin(PassLoginRequest passLoginRequest)
			throws GlobalServiceException {
		PassLoginResponse  passLoginResponse = new PassLoginResponse();
		TitanUser titanUser =  getTitanUser(passLoginRequest.getLoginUsername());
		if(titanUser==null){
			passLoginResponse.putErrorResult("用户名或密码错误");
			return passLoginResponse;
		}
		String passswordmd5 = MD5.MD5Encode(passLoginRequest.getPassword());
		if(passswordmd5.equals(titanUser.getPassword())){
			passLoginResponse.putSuccess("登录成功");
			passLoginResponse.setTfsuserId(titanUser.getTfsuserid());
			passLoginResponse.setUserId(titanUser.getUserid());
			passLoginResponse.setUserLoginName(titanUser.getUserloginname());
		}else{
			passLoginResponse.putErrorResult("密码不正确");
			return passLoginResponse;
		}
		
		return passLoginResponse;
	}
	
	public SmsLoginResponse smsLogin(SmsLoginRequest smsLoginRequest)
			throws GlobalServiceException {
		SmsLoginResponse smsLoginResponse = new SmsLoginResponse();
		TitanUser titanUser =  getTitanUser(smsLoginRequest.getLoginUsername());
		if(titanUser==null){
			smsLoginResponse.putErrorResult("用户名或密码错误");
			return smsLoginResponse;
		}
		//验证动态码
		VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
		verifyCheckCodeRequest.setInputCode(smsLoginRequest.getSmsCode());
		verifyCheckCodeRequest.setReceiveAddress(smsLoginRequest.getLoginUsername());
		VerifyCheckCodeResponse  verifyCheckCodeResponse = organService.verifyCheckCode(verifyCheckCodeRequest);
		if(verifyCheckCodeResponse.isResult()){
			//使用该验证码后，置为无效。
			if(verifyCheckCodeResponse.getCodeId()>0){
				UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
				updateCheckCodeRequest.setCodeId(verifyCheckCodeResponse.getCodeId());
				updateCheckCodeRequest.setIsactive(TitanCheckCodeEnum.Isactive.NOT_ACTIVE.getKey());
				organService.useCheckCode(updateCheckCodeRequest);
			}
			smsLoginResponse.putSuccess("登录成功");
			smsLoginResponse.setUserId(titanUser.getUserid());
			smsLoginResponse.setTfsuserId(titanUser.getTfsuserid());
			smsLoginResponse.setUserLoginName(titanUser.getUserloginname());
		}else{
			smsLoginResponse.putErrorResult(verifyCheckCodeResponse.getReturnMessage());
		}
		
		return smsLoginResponse;
	}
	/**
	 * 查询用户
	 * @param loginUsername
	 * @return
	 */
	private TitanUser getTitanUser(String loginUsername){
		TitanUserParam param = new TitanUserParam();
		param.setUserloginname(loginUsername);
		PaginationSupport<TitanUser> userPage = new PaginationSupport<TitanUser>();
		userPage.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		userPage = titanUserDao.selectForPage(param, userPage);
		if(CollectionUtils.isNotEmpty(userPage.getItemList())){
			return userPage.getItemList().get(0);
		}else{
			return null;
		}
	}

	@Override
	public CheckUserResponse checkUser(CheckUserRequest checkUserRequest)
			throws GlobalServiceException {
		CheckUserResponse checkUserResponse = new CheckUserResponse();
		if(checkUserRequest.getTfsUserId()==null||checkUserRequest.getTfsUserId()<1){
			checkUserResponse.putErrorResult("参数tfsUserId不能为空");
			return checkUserResponse;
		}
		//检查用户状态
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(checkUserRequest.getTfsUserId());
		UserInfoPageResponse userInfoPageResponse = this.queryUserInfoPage(userInfoQueryRequest);
		TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
		//检查机构状态
		OrgDTO orgDTO = new OrgDTO();
		orgDTO.setUserid(titanUser.getUserid());
		orgDTO = organService.queryOrg(orgDTO);
		Integer statusId = orgDTO.getStatusId();
		TitanOrgCheck titanOrgCheck = new TitanOrgCheck();
		TitanOrgCheckParam checkParam = new TitanOrgCheckParam();
    	checkParam.setUserid(titanUser.getUserid());
    	
    	PaginationSupport<TitanOrgCheck> orgCheckPage = new PaginationSupport<TitanOrgCheck>();
    	titanOrgCheckDao.selectForPage(checkParam, orgCheckPage);
    	titanOrgCheck = orgCheckPage.getItemList().get(0);
		
		String resultkey = titanOrgCheck.getResultkey();
		//机构状态不正常
		//if((!OrgCheckResultEnum.PASS.getResultkey().equals(resultkey))||(!(statusId==TitanOrgEnum.StatusId.AVAILABLE.getKey()))){
			if(statusId == TitanOrgEnum.StatusId.FREEZE.getKey()){
				checkUserResponse.putErrorResult("ORG_"+TitanOrgEnum.StatusId.FREEZE.getKey()+"_FREEZE", "机构冻结中");
				return checkUserResponse;
			}
			if(statusId == TitanOrgEnum.StatusId.NOT_AVAILABLE.getKey()){
				checkUserResponse.putErrorResult("ORG_"+TitanOrgEnum.StatusId.NOT_AVAILABLE.getKey()+"_NOT_AVAILABLE", "机构已注销");
				return checkUserResponse;
			}
		 
			if(OrgCheckResultEnum.FT.getResultkey().equals(resultkey)){
				checkUserResponse.putErrorResult("ORG_"+OrgCheckResultEnum.FT.getResultkey(), "机构"+OrgCheckResultEnum.FT.getResultmsg());
				return checkUserResponse;
			}
			if(OrgCheckResultEnum.FT_INVALID.getResultkey().equals(resultkey)){
				checkUserResponse.putErrorResult("ORG_"+OrgCheckResultEnum.FT_INVALID.getResultkey(), titanOrgCheck.getResultmsg());
				return checkUserResponse;
			}
			if(OrgCheckResultEnum.REVIEW.getResultkey().equals(resultkey)){
				checkUserResponse.putErrorResult("ORG_"+OrgCheckResultEnum.REVIEW.getResultkey(), "机构"+OrgCheckResultEnum.REVIEW.getResultmsg());
				return checkUserResponse;
			}
			if(OrgCheckResultEnum.REVIEW_INVALID.getResultkey().equals(resultkey)){
				checkUserResponse.putErrorResult("ORG_"+OrgCheckResultEnum.REVIEW_INVALID.getResultkey(), titanOrgCheck.getResultmsg());
				return checkUserResponse;
			}
		//}
		//用户状态不对
		if(titanUser.getStatus()==TitanUserEnum.Status.FREEZE.getKey()){
			checkUserResponse.putErrorResult("USER_"+TitanUserEnum.Status.FREEZE.getKey()+"_FREEZE", "用户"+TitanUserEnum.Status.FREEZE.getDes());
			return checkUserResponse;
		}
		if(titanUser.getStatus()==TitanUserEnum.Status.NOT_AVAILABLE.getKey()){
			checkUserResponse.putErrorResult("USER_"+TitanUserEnum.Status.NOT_AVAILABLE.getKey()+"_NOT_AVAILABLE", "用户"+TitanUserEnum.Status.NOT_AVAILABLE.getDes());
			return checkUserResponse;
		}

		checkUserResponse.putSuccess("机构和用户状态正常");
		return checkUserResponse;
	}
	
}
