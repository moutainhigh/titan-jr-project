


package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.request.BankInfoQueryRequest;
import com.fangcang.titanjr.dto.response.BankInfoInitResponse;
import com.fangcang.titanjr.dto.response.BankInfoResponse;
import com.fangcang.titanjr.dto.response.CityInfoInitResponse;
import com.fangcang.titanjr.dto.response.OrganRoleInitResponse;

/**
 * 基础数据初始化相关服务
 * 初始化金服平台银行信息，省市信息，角色权限信息
 * Created by zhaoshan on 2016/4/21.
 */
public interface TitanFinancialBaseInfoService {

    /**
     * 现在做简化，只向金服系统内做初始化
     * 初始化所有角色信息
     * @param
     * @return
     */
    public OrganRoleInitResponse initFinancialOrganRole();

    /**
     * 初始化融数城市信息接口，保存于数据库
     * @return
     */
    public CityInfoInitResponse saveRSCityInfo()throws Exception;

    /**
     * 初始化融数银行信息，包括支行，保存于数据库
     * 初始化时候银行图片信息需上传
     * @return
     */
    public BankInfoInitResponse saveRSBankInfo()throws Exception;

    /**
     * 查询本地已保存的银行信息，用户绑定体现卡使用
     * @param bankInfoQueryRequest
     * @return
     */
    public BankInfoResponse queryBankInfoList(BankInfoQueryRequest bankInfoQueryRequest);

}
