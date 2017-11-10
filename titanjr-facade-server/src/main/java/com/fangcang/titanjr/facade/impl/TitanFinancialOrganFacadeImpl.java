package com.fangcang.titanjr.facade.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.titanjr.common.enums.entity.TitanOrgBindinfoEnum;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.OrganStatusEnum;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.facade.TitanFinancialOrganFacade;
import com.fangcang.titanjr.request.OrganInfoQueryRequest;
import com.fangcang.titanjr.request.OrganStatusRequest;
import com.fangcang.titanjr.response.OrganInfoResponse;
import com.fangcang.titanjr.response.OrganStatusResponse;
import com.fangcang.titanjr.response.OrganUserInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhaoshan on 2016/12/27.
 */
@Service("titanFinancialOrganFacade")
public class TitanFinancialOrganFacadeImpl implements TitanFinancialOrganFacade {

    private static final Log log = LogFactory.getLog(TitanFinancialOrganFacadeImpl.class);

    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;

    @Resource
    private TitanFinancialUserService titanFinancialUserService;

    @Override
    public OrganStatusResponse queryOrganStatus(OrganStatusRequest organStatusRequest) {
        OrganStatusResponse statusResponse = new OrganStatusResponse();
        if (null == organStatusRequest.getOrganTypeEnum() || !StringUtil.isValidString(organStatusRequest.getPartnerCode())
                || !StringUtil.isValidString(organStatusRequest.getTitanOrgCode())){
            statusResponse.putParamError();
            log.error("参数校验失败");
            return statusResponse;
        }
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setMerchantcode(organStatusRequest.getPartnerCode());
        organQueryRequest.setUserId(organStatusRequest.getTitanOrgCode());
        organQueryRequest.setCoopType(organStatusRequest.getOrganTypeEnum().typeId);
        FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryBaseFinancialOrgan(organQueryRequest);
        if (null == financialOrganResponse.getFinancialOrganDTO() ||
                !StringUtil.isValidString(financialOrganResponse.getFinancialOrganDTO().getUserId())){
            statusResponse.putErrorResult("NO_RESULT","查询机构为空");
            log.error("查询机构为空");
            return statusResponse;
        }
        statusResponse.setResult(true);
        statusResponse.setTitanOrgId(financialOrganResponse.getFinancialOrganDTO().getOrgId());
        statusResponse.setOrganStatusEnum(OrganStatusEnum.getEnumByCode(financialOrganResponse.getFinancialOrganDTO()
                .getCheckStatus().getCheckResultKey()));
        statusResponse.setTitanCode(financialOrganResponse.getFinancialOrganDTO().getTitanCode());
        log.info("状态查询成功");
        return statusResponse;
    }

    @Override
    public OrganInfoResponse queryOrganInfoByPartnerUser(OrganInfoQueryRequest organInfoQueryRequest) {
        OrganInfoResponse infoResponse = new OrganInfoResponse();
        if (!StringUtil.isValidString(organInfoQueryRequest.getPartnerCode())){
            infoResponse.putParamError();
            log.error("PartnerCode不能为空");
            return infoResponse;
        }
        log.info("根据合作方用户或机构信息查询金融机构信息，查询参数organInfoQueryRequest："+Tools.gsonToString(organInfoQueryRequest));
        //传入的用户信息不为空，先查用户信息再比较并查询机构信息
        if (StringUtil.isValidString(organInfoQueryRequest.getPartnerLoginName()) || StringUtil.
                isValidString(organInfoQueryRequest.getPartnerUserId())){
            UserInfoQueryRequest userInfoQueryRequest =new UserInfoQueryRequest();
            userInfoQueryRequest.setBindLoginName(organInfoQueryRequest.getPartnerLoginName());
            userInfoQueryRequest.setBindMerchantCode(organInfoQueryRequest.getPartnerCode());
            userInfoQueryRequest.setBindUserId(organInfoQueryRequest.getPartnerUserId());
            UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
            if (CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList()) && StringUtil.
                    isValidString(userInfoResponse.getUserInfoDTOList().get(0).getUserId())){
                return buildOrganInfoResponse(infoResponse,organInfoQueryRequest.getPartnerCode(),
                        organInfoQueryRequest.getOrganTypeEnum().typeId);
            } else {
                infoResponse.putErrorResult("NO_VALID_RESULT","传入的用户信息查询机构失败");
                log.error("未查询到关联的用户,请求参数organInfoQueryRequest："+Tools.gsonToString(organInfoQueryRequest));
                return infoResponse;
            }
        } else {
            return buildOrganInfoResponse(infoResponse,organInfoQueryRequest.getPartnerCode(),
                    organInfoQueryRequest.getOrganTypeEnum().typeId);
        }
    }

    @Override
    public OrganUserInfoResponse queryOrganUserByPartnerUser(OrganInfoQueryRequest organInfoQueryRequest) {
        OrganUserInfoResponse infoResponse = new OrganUserInfoResponse();
        //判定条件不一样
        if (!StringUtil.isValidString(organInfoQueryRequest.getPartnerCode()) || (!StringUtil.isValidString(organInfoQueryRequest.getPartnerLoginName())
                && !StringUtil.isValidString(organInfoQueryRequest.getPartnerUserId()))){
            infoResponse.putParamError();
            log.error("参数校验失败");
            return infoResponse;
        }
        log.info("根据合作方信息查金融用户信息，查询参数organInfoQueryRequest："+Tools.gsonToString(organInfoQueryRequest));
        UserInfoQueryRequest userInfoQueryRequest =new UserInfoQueryRequest();
        userInfoQueryRequest.setBindLoginName(organInfoQueryRequest.getPartnerLoginName());
        userInfoQueryRequest.setBindUserId(organInfoQueryRequest.getPartnerUserId());
        userInfoQueryRequest.setBindMerchantCode(organInfoQueryRequest.getPartnerCode());
        UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
        if (CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList()) && StringUtil.
                isValidString(userInfoResponse.getUserInfoDTOList().get(0).getUserId())){
            infoResponse.setResult(true);
            infoResponse.setTitanOrgCode(userInfoResponse.getUserInfoDTOList().get(0).getOrgCode());
            infoResponse.setTitanUserId(userInfoResponse.getUserInfoDTOList().get(0).getTfsUserId());
            infoResponse.setTitanUserLoginName(userInfoResponse.getUserInfoDTOList().get(0).getUserLoginName());
            infoResponse.setTitanUserName(userInfoResponse.getUserInfoDTOList().get(0).getUserName());
        } else{
            infoResponse.putErrorResult("NO_VALID_RESULT","查询正确的用户信息失败");
            log.error("根据合作方信息查金融用户信息,未找到用户，请求参数organInfoQueryRequest:"+Tools.gsonToString(organInfoQueryRequest)+",查询响应结果："+Tools.gsonToString(userInfoResponse));
            return infoResponse;
        }
        return infoResponse;
    }

    private OrganInfoResponse buildOrganInfoResponse(OrganInfoResponse infoResponse,String merchantcode,Integer regChannel){
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setMerchantcode(merchantcode);
        organQueryRequest.setBindStatus(TitanOrgBindinfoEnum.BindStatus.BIND.getKey());
        log.info("查询机构信息，查询参数buildOrganInfoResponse："+Tools.gsonToString(organQueryRequest));
        FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryBaseFinancialOrgan(organQueryRequest);
        if (null != financialOrganResponse.getFinancialOrganDTO() && StringUtil.
                isValidString(financialOrganResponse.getFinancialOrganDTO().getUserId())){
            infoResponse.setResult(true);
            infoResponse.setTitanCode(financialOrganResponse.getFinancialOrganDTO().getTitanCode());
            infoResponse.setOrganStatusEnum(OrganStatusEnum.getEnumByCode(financialOrganResponse.getFinancialOrganDTO()
                    .getCheckStatus().getCheckResultKey()));
            infoResponse.setTitanOrgCode(financialOrganResponse.getFinancialOrganDTO().getOrgCode());
            infoResponse.setTitanOrgId(financialOrganResponse.getFinancialOrganDTO().getOrgId());
            infoResponse.setTitanOrgName(financialOrganResponse.getFinancialOrganDTO().getOrgName());
        } else {
            infoResponse.putErrorResult("BIND_INFO_INVALID","机构绑定信息不合法");
            log.error("未查询到绑定机构，查询参数merchantcode："+merchantcode+",regChannel:"+regChannel+",查询响应结果："+Tools.gsonToString(financialOrganResponse));
            return infoResponse;
        }
        return  infoResponse;
    }

}
