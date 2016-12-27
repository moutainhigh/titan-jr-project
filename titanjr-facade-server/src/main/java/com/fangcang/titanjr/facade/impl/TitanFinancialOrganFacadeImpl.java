package com.fangcang.titanjr.facade.impl;

import com.fangcang.titanjr.dto.OrganStatusEnum;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.facade.TitanFinancialOrganFacade;
import com.fangcang.titanjr.request.OrganInfoQueryRequest;
import com.fangcang.titanjr.request.OrganStatusRequest;
import com.fangcang.titanjr.request.OrganUserInfoQueryRequest;
import com.fangcang.titanjr.response.OrganInfoResponse;
import com.fangcang.titanjr.response.OrganStatusResponse;
import com.fangcang.titanjr.response.OrganUserInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.util.StringUtil;

import javax.annotation.Resource;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class TitanFinancialOrganFacadeImpl implements TitanFinancialOrganFacade {

    @Resource
    TitanFinancialOrganService titanFinancialOrganService;

    @Override
    public OrganStatusResponse queryOrganStatus(OrganStatusRequest organStatusRequest) {
        OrganStatusResponse statusResponse = new OrganStatusResponse();
        if (null == organStatusRequest.getOrganTypeEnum() || !StringUtil.isValidString(organStatusRequest.getPartnerCode())
                || !StringUtil.isValidString(organStatusRequest.getTitanOrgCode())){
            statusResponse.putParamError();
            return statusResponse;
        }
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setMerchantcode(organStatusRequest.getPartnerCode());
        organQueryRequest.setUserId(organStatusRequest.getTitanOrgCode());
        organQueryRequest.setRegchannel(organStatusRequest.getOrganTypeEnum().typeId);
        FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
        if (null == financialOrganResponse.getFinancialOrganDTO() ||
                !StringUtil.isValidString(financialOrganResponse.getFinancialOrganDTO().getUserId())){
            statusResponse.putErrorResult("NO_RESULT","查询机构为空");
            return statusResponse;
        }
        statusResponse.setResult(true);
        statusResponse.setTitanOrgId(financialOrganResponse.getFinancialOrganDTO().getOrgId());
        statusResponse.setOrganStatusEnum(OrganStatusEnum.getEnumByCode(financialOrganResponse.getFinancialOrganDTO()
                .getCheckStatus().getCheckResultKey()));
        statusResponse.setTitanCode(financialOrganResponse.getFinancialOrganDTO().getTitanCode());
        return statusResponse;
    }

    @Override
    public OrganInfoResponse queryOrganInfoByPartnerUser(OrganInfoQueryRequest organInfoQueryRequest) {
        return null;
    }

    @Override
    public OrganUserInfoResponse queryOrganUserByPartnerUser(OrganUserInfoQueryRequest organUserInfoQueryRequest) {
        return null;
    }
}
