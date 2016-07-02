package com.fangcang.titanjr.rs.manager;
import com.fangcang.titanjr.rs.response.BankInfoResponse;
import com.fangcang.titanjr.rs.response.CityInfoResponse;


/**
 * Created by zhaoshan on 2016/4/8.
 * 融数基础信息查询接口
 * 包括查询省份、城市、总行支行四个接口
 */
public interface BaseInfoInitManager {

    /**
     * 查询融数的省份信息
     * @return
     */
    public CityInfoResponse getRSProvinceInfo();

    /**
     * 查询融数的城市信息
     * @param provinceCode
     * @return
     */
    public CityInfoResponse getRSCityInfo(String provinceCode);

    /**
     * 查询融数的总行信息
     * @return
     */
    public BankInfoResponse getRSMainBankInfo();

    /**
     * 查询融数的支行信息
     * @param mainBankCode
     * @param cityCode
     * @return
     */
    public BankInfoResponse getRSBranchBankInfo(String mainBankCode,String cityCode);

}
