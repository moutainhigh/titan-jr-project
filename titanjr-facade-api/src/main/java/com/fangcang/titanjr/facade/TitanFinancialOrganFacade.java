package com.fangcang.titanjr.facade;

import com.fangcang.titanjr.request.OrganInfoQueryRequest;
import com.fangcang.titanjr.request.OrganStatusRequest;
import com.fangcang.titanjr.response.OrganInfoResponse;
import com.fangcang.titanjr.response.OrganStatusResponse;
import com.fangcang.titanjr.response.OrganUserInfoResponse;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public interface TitanFinancialOrganFacade {


    /**
     * 查询金融机构状态
     * 根据合作方编码（ttmall机构编码）与金融机构编码查询金融机构状态
     * 2.提供机构状态查询接口
     * 3.提供通过saas商家编码查询金融机构id的方法
     * @param organStatusRequest
     * @return
     */
    public OrganStatusResponse queryOrganStatus(OrganStatusRequest organStatusRequest);

    /**
     * 根据合作方用户或机构信息查询金融机构信息
     * @param organInfoQueryRequest
     * @return
     */
    public OrganInfoResponse queryOrganInfoByPartnerUser(OrganInfoQueryRequest organInfoQueryRequest);

    /**
     * 根据合作方用户和机构信息查询金融用户信息
     * @param organInfoQueryRequest
     * @return
     */
    public OrganUserInfoResponse queryOrganUserByPartnerUser(OrganInfoQueryRequest organInfoQueryRequest);

}
