/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanFinancialUpgradeServiceImpl.java
 * @author Jerry
 * @date 2017年8月4日 下午3:45:53  
 */
package com.fangcang.titanjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.TransOrderTypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanUserDao;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.enums.PermissionEnum;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUpgradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

/**
 * @author Jerry
 * @date 2017年8月4日 下午3:45:53  
 */
@Service("titanFinancialUpgradeService")
public class TitanFinancialUpgradeServiceImpl implements TitanFinancialUpgradeService {
	
	private static final Log log = LogFactory.getLog(TitanFinancialUpgradeServiceImpl.class);
	
	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	@Resource
	private TitanUserDao titanUserDao;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;
	

	@Override
	public TransOrderCreateResponse setBaseUserInfo(
			TitanOrderRequest titanOrderRequest, TitanTransOrder titanTransOrder) {
		
		TransOrderCreateResponse response = new TransOrderCreateResponse();
		response.putSuccess();
		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(titanOrderRequest.getPayerType());
		
		//付款方信息
		if(StringUtil.isValidString(titanOrderRequest.getPartnerOrgCode()) 
				&& StringUtil.isValidString(titanOrderRequest.getOrgCode()) 
				&& StringUtil.isValidString(titanOrderRequest.getUserId())){
			
			OrgBindInfo orgBindInfo = queryOrgBindInfo(titanOrderRequest.getPartnerOrgCode(),
					titanOrderRequest.getOrgCode());
			if(orgBindInfo == null){
				response.putErrorResult("查询机构绑定信息失败");
				return response;
			}
			titanTransOrder.setUserid(orgBindInfo.getOrgcode());
			titanTransOrder.setPayermerchant(orgBindInfo.getOrgcode());
			titanTransOrder.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			
			//校验用户信息
			if(StringUtil.isValidString(titanOrderRequest.getUserId())){
				
				TitanUser titanUser = checkUserInfo(titanOrderRequest.getUserId());
				if(titanUser == null){
					log.error("付款方用户不存在，根据userId查询金融用户失败，参数titanOrderRequest："+Tools.gsonToString(titanOrderRequest));
					response.putErrorResult("付款方用户不存在");
					return response;
				}
				//余额支付权限
				CheckPermissionResponse permissionResponse = isCanAccountBalance(
						String.valueOf(titanUser.getTfsuserid()));
				response.setCanAccountBalance(permissionResponse.isPermission());
				
			}else{
				response.setCanAccountBalance(false);
			}
			
		}else{
			
			titanTransOrder.setUserid(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setPayermerchant(RSInvokeConstant.DEFAULTPAYERCONFIG_USERID);
			titanTransOrder.setProductid(RSInvokeConstant.DEFAULTPAYERCONFIG_PRODUCTID);
			response.setCanAccountBalance(false);
		}
		
		//收款方信息
		if(StringUtil.isValidString(titanOrderRequest.getRuserId())){
			
			OrgBindInfoDTO orgBindDTO = new OrgBindInfoDTO();
			if (titanOrderRequest.getRuserId().startsWith("TJM")){
				orgBindDTO.setUserid(titanOrderRequest.getRuserId());
			}else {
				orgBindDTO.setMerchantCode(titanOrderRequest.getRuserId());
			}
			orgBindDTO.setResultKey("PASS");
			orgBindDTO.setBindStatus(1);
			List<OrgBindInfoDTO> orgBindDTOList = titanFinancialOrganService.queryOrgBindInfoDTO(orgBindDTO);

			if (CollectionUtils.isEmpty(orgBindDTOList) || orgBindDTOList.size() != 1) {
				log.error("接收方机构不存在或不正确,接收方为："+titanOrderRequest.getRuserId());
				response.putErrorResult("接收方机构不存在或不正确");
				return response;
			}
			titanTransOrder.setPayeemerchant(orgBindDTOList.get(0).getUserid());
			titanTransOrder.setUserrelateid(orgBindDTOList.get(0).getUserid());
		}
		
		//设置订单类型
		if(payerTypeEnum.isRechargeCashDesk()){
			titanTransOrder.setTransordertype(TransOrderTypeEnum.RECHARGE.type);
		}
		if(payerTypeEnum.isWithdraw()){
			titanTransOrder.setTransordertype(TransOrderTypeEnum.WITHDRAW.type);
		}else{
			titanTransOrder.setTransordertype(TransOrderTypeEnum.PAYMENT.type);
		}
		
		return response;

	}
	
	
	/**
	 * 根据付款方机构编码和金融机构编码查询机构绑定信息
	 * @author Jerry
	 * @date 2017年8月3日 下午8:39:58
	 */
	private OrgBindInfo queryOrgBindInfo(String partnerOrgCode, String orgCode) {
		OrgBindInfo orgBindInfo = new OrgBindInfo();
		orgBindInfo.setMerchantCode(partnerOrgCode);
		orgBindInfo.setOrgcode(orgCode);
		return titanFinancialOrganService.queryOrgBindInfo(orgBindInfo);
	}
	
	/**
	 * 根据付款方用户ID校验是否绑定金融用户
	 * @author Jerry
	 * @date 2017年8月3日 下午8:45:54
	 */
	private TitanUser checkUserInfo(String userId){
		TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
		titanUserBindInfoDTO.setFcuserid(Long.parseLong(userId));
		titanUserBindInfoDTO = titanFinancialUserService
				.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
		
		if (titanUserBindInfoDTO == null
				|| titanUserBindInfoDTO.getTfsuserid() == null) {
			log.error("查询用户绑定信息失败");
			return null;
		}
		TitanUser titanUser = titanUserDao.selectTitanUser(titanUserBindInfoDTO.getTfsuserid());
		if(titanUser == null){
			log.error("查询金融用户失败");
			return null;
		}
		return titanUser;
	}
	
	/**
	 * 校验是否有余额支付权限
	 * @author Jerry
	 * @date 2017年8月4日 下午5:08:48
	 */
	private CheckPermissionResponse isCanAccountBalance(String userId) {
		
		log.info("check isCanAccountBalance tfsUserId= " + userId);

		CheckPermissionResponse permissionResponse = new CheckPermissionResponse();
        permissionResponse.setResult(false);
        permissionResponse.setPermission(false);
        try {
            PermissionRequest permissionRequest = new PermissionRequest();
            permissionRequest.setTfsuserid(userId);
            permissionRequest.setPermission(PermissionEnum.Pay.key);
            CheckPermissionResponse checkResponse = titanFinancialUserService.checkUserPermission(permissionRequest);
            log.info("验证该用户是否有支付权限结果:"+JSONSerializer.toJSON(checkResponse));
            if (checkResponse.isPermission()) {
                permissionResponse.setPermission(true);
                permissionResponse.setResult(true);
            } else {
                permissionResponse.putErrorResult(checkResponse.getReturnCode(), checkResponse.getReturnMessage());
            }
        } catch (Exception e) {
            permissionResponse.setReturnMessage("查询用户授权操作失败");
            log.error("查询用户授权操作失败", e);
        }
        return permissionResponse;
	}

}
