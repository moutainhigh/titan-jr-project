package com.fangcang.titanjr.service;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.CancelPermissionRequest;
import com.fangcang.titanjr.dto.request.FinancialUserBindRequest;
import com.fangcang.titanjr.dto.request.FinancialUserUnBindRequest;
import com.fangcang.titanjr.dto.request.LoginPasswordModifyRequest;
import com.fangcang.titanjr.dto.request.LoginPasswordRequest;
import com.fangcang.titanjr.dto.request.PayPasswordRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.SaaSUserRoleRequest;
import com.fangcang.titanjr.dto.request.TitanRoleQueryRequest;
import com.fangcang.titanjr.dto.request.UpdateUserRequest;
import com.fangcang.titanjr.dto.request.UserBindInfoRequest;
import com.fangcang.titanjr.dto.request.UserFreezeRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.UserLoginNameExistRequest;
import com.fangcang.titanjr.dto.request.UserRegisterRequest;
import com.fangcang.titanjr.dto.request.UserRoleSetRequest;
import com.fangcang.titanjr.dto.response.CancelPermissionResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.FinancialUserBindResponse;
import com.fangcang.titanjr.dto.response.FinancialUserUnBindResponse;
import com.fangcang.titanjr.dto.response.LoginPasswordModifyResponse;
import com.fangcang.titanjr.dto.response.PayPasswordResponse;
import com.fangcang.titanjr.dto.response.PermissionResponse;
import com.fangcang.titanjr.dto.response.RoleUserInfoPageResponse;
import com.fangcang.titanjr.dto.response.SaaSUserRoleResponse;
import com.fangcang.titanjr.dto.response.TitanRoleResponse;
import com.fangcang.titanjr.dto.response.UpdateUserResponse;
import com.fangcang.titanjr.dto.response.UserBindInfoResponse;
import com.fangcang.titanjr.dto.response.UserFreezeResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserLoginNameExistResponse;
import com.fangcang.titanjr.dto.response.UserRegisterResponse;
import com.fangcang.titanjr.dto.response.UserRoleSetResponse;

/**
 * 金服平台用户相关服务
 * 用户添加绑定，用户删除
 * 用户权限管理设置
 * Created by zhaoshan on 2016/4/21.
 */
public interface TitanFinancialUserService {

    /**
     * 用户注册接口，最完整的步骤是以下六个
     * 1.金服添加用户
     * 2.金服添加用户绑定关系
     * 3.金服添加用户权限绑定关系
     * 4.SaaS系统添加员工属于固定金服商家（需配置起来）
     * 5.SaaS系统新添加的员工绑定权限
     * 6.SaaS被绑定的员工绑定权限
     * 分三种情况：
     * 第一种：SaaS页面注册机构以及新增员工时候使用1,2,3,4,5,6
     * 第二种：金服官网注册机构以及新增员工时候使用1,3,4,5
     * 第三种：后台自动注册机构时添加员工使用1,4
     * @param userRegisterRequest
     * @return
     */
    public UserRegisterResponse registerFinancialUser(UserRegisterRequest userRegisterRequest) throws Exception;

    /**
     * 用户信息查询接口
     * 查询的用户信息需包含：用户基础信息，角色信息，用户绑定信息
     * @return
     */
    public UserInfoResponse queryFinancialUser(UserInfoQueryRequest userInfoQueryRequest);

    public UserInfoPageResponse queryUserInfoPage(UserInfoQueryRequest userInfoQueryRequest); 
    
    public UserBindInfoResponse queryUserBindInfoDTO(UserBindInfoRequest userBindInfoRequest);
    /**
     * 分页查询金服员工信息
     * @param pageUserInfoRequest
     * @return
     */
    public RoleUserInfoPageResponse queryRoleUserInfoPage(UserInfoQueryRequest userInfoQueryRequest);
    /**
     * 设置金服用户的角色(添加和修改)
     * @param userRoleSetRequest
     * @return
     */
    public UserRoleSetResponse setFinancialUserRole(UserRoleSetRequest userRoleSetRequest) throws GlobalServiceException;


    //冻结账户接口  查询账户列表接口
    
    /**
     * 修改金服员工信息
     */
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) throws GlobalServiceException;
    /**
     * 1.金服添加用户绑定关系
     * 2.SaaS被绑定的员工绑定权限。
     * @param financialUserBindRequest
     * @return
     */
    public FinancialUserBindResponse bindFinancialUser(FinancialUserBindRequest financialUserBindRequest);

    /**
     * 解绑用户
     * @param financialUserUnBindRequest,
     * @return
     * @throws GlobalServiceException
     */
    public FinancialUserUnBindResponse unbindFinancialUser(FinancialUserUnBindRequest financialUserUnBindRequest) throws GlobalServiceException;

    /**
     * 能根据角色编码列表或者房仓角色编码去查询所有的角色信息
     * @param titanRoleQueryRequest
     * @return
     */
    public TitanRoleResponse queryTitanRole(TitanRoleQueryRequest titanRoleQueryRequest);
    
    /**
   	 * 根据用户id查询权限
   	 * @param permissionRequest
   	 * @return
   	 * @author fangdaikang
   	 */
   	public PermissionResponse getUserPermission(PermissionRequest permissionRequest);
   	
   	
   	/**
   	 * 验证用户是否具有相应的权限
   	 * @param permissionRequest
   	 * @return
   	 */
   	public CheckPermissionResponse checkUserPermission(PermissionRequest permissionRequest);
   	/**
   	 * 分页查询SAAS用户
   	 * @param request
   	 * @return
   	 * @throws GlobalServiceException
   	 */
   	public SaaSUserRoleResponse querySaaSUserRolePage(SaaSUserRoleRequest request) throws GlobalServiceException;
   
   	/***
   	 * 解除用户权限
   	 * @param cancelPermissionRequest
   	 * @return
   	 * @throws GlobalServiceException
   	 * @throws MessageServiceException
   	 */
   	public CancelPermissionResponse cancelPermission(CancelPermissionRequest cancelPermissionRequest) throws GlobalServiceException,MessageServiceException;
   	/**
   	 * 检测付款密码是否准确
   	 * @param payPassword
   	 * @param tfsuserid
   	 * @return
   	 * @author fangdaikang
   	 */
   	public boolean checkPayPassword(String payPassword,String tfsuserid);
   	/**
   	 * 保存付款密码
   	 * @param payPasswordRequest
   	 * @return
   	 * @author fangdaikang
   	 */
   	public PayPasswordResponse saveOrUpdatePayPassword(PayPasswordRequest payPasswordRequest);
   	
   	/**
   	 * 修改登录密码
   	 * @param loginPasswordRequest
   	 * @return
   	 * @throws GlobalServiceException
   	 * @throws MessageServiceException
   	 */
   	public BaseResponseDTO saveLoginPassword(LoginPasswordRequest loginPasswordRequest) throws GlobalServiceException;
   	
   	/**
   	 * 判断交易密码谁否设置
   	 * @param fcUserid
   	 * @return true：没有设置密码，false:设置了密码
   	 * @author fangdaikang 
   	 */
   	public boolean checkIsSetPayPassword(String fcUserid,String tfsUserId);
   	/**
   	 * 冻结/解冻用户
   	 * @param userFreezeRequest
   	 * @return
   	 * @throws GlobalServiceException
   	 */
   	public UserFreezeResponse freezeUser(UserFreezeRequest userFreezeRequest) throws GlobalServiceException;
   	
    /***
     * 登录名是否能注册
     * @param request
     * @return
     * @throws GlobalServiceException
     */
    public UserLoginNameExistResponse userLoginNameExist(UserLoginNameExistRequest request) throws GlobalServiceException;
    

   	/**
   	 * 根据fcUserid获取TfsUserid
   	 * @param fcuserid
   	 * @return
   	 * @throws GlobalServiceException
   	 * @author fangdaikang
   	 */
    public TitanUserBindInfoDTO getUserBindInfoByFcuserid(TitanUserBindInfoDTO titanUserBindInfoDTO) ;
}
