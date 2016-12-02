package com.fangcang.titanjr.rs.manager.impl;

import com.Rop.api.domain.CityCode;
import com.Rop.api.request.WheatfieldBanknQueryRequest;
import com.Rop.api.request.WheatfieldCityQueryRequest;
import com.Rop.api.response.WheatfieldBanknQueryResponse;
import com.Rop.api.response.WheatfieldCityQueryResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.rs.dto.BankInfo;
import com.fangcang.titanjr.rs.dto.CityInfo;
import com.fangcang.titanjr.rs.manager.BaseInfoInitManager;
import com.fangcang.titanjr.rs.response.BankInfoResponse;
import com.fangcang.titanjr.rs.response.CityInfoResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.util.MyBeanUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class BaseInfoInitManagerImpl implements BaseInfoInitManager{

    private static final Log log = LogFactory.getLog(BaseInfoInitManagerImpl.class);

    @Override
    public CityInfoResponse getRSProvinceInfo() {
        CityInfoResponse response = new CityInfoResponse();
        try {
            WheatfieldCityQueryRequest req = new WheatfieldCityQueryRequest();
            WheatfieldCityQueryResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
            if (rsp != null) {
                log.debug("调用getRSProvinceInfo返回报文: \n" + rsp.getBody());
                String errorMsg;
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        errorMsg = rsp.getSubMsg();
                    } else {
                        errorMsg = rsp.getMsg();
                    }
                    response.setReturnCode(rsp.getErrorCode());
                    response.setReturnMsg(errorMsg);
                    log.error("调用接口getRSProvinceInfo异常：" + errorMsg);
                } else {
                    response.setSuccess(true);
                    response.setCityInfos(new ArrayList<CityInfo>());
                    for (CityCode cityCode : rsp.getCitycodes()) { //返回结果转换处理
                        CityInfo cityInfo = new CityInfo();
                        cityInfo.setDatatype(1);
                        cityInfo.setCitycode(cityCode.getCitycode());
                        cityInfo.setCityname(cityCode.getCityname());
                        response.getCityInfos().add(cityInfo);
                    }
                    response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
                    response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
                }
            } else {
                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
            }
        } catch (Exception e) {
            response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
            response.setReturnMsg(e.getMessage());
            log.error("调用getRSProvinceInfo过程出现未知异常", e);
        }
        return response;
    }

    @Override
    public CityInfoResponse getRSCityInfo(String provinceCode) {
        CityInfoResponse response = new CityInfoResponse();
        try {
            WheatfieldCityQueryRequest req = new WheatfieldCityQueryRequest();
            req.setCitycode(provinceCode);
            WheatfieldCityQueryResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
            if (rsp != null) {
                log.debug("调用getRSProvinceInfo返回报文: \n" + rsp.getBody());
                String errorMsg;
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        errorMsg = rsp.getSubMsg();
                    } else {
                        errorMsg = rsp.getMsg();
                    }
                    response.setReturnCode(rsp.getErrorCode());
                    response.setReturnMsg(errorMsg);
                    log.error("调用接口getRSProvinceInfo异常：" + errorMsg);
                } else {
                    response.setSuccess(true);
                    response.setCityInfos(new ArrayList<CityInfo>());
                    for (CityCode cityCode : rsp.getCitycodes()) { //返回结果转换处理
                        CityInfo cityInfo = new CityInfo();
                        cityInfo.setParentcode(provinceCode);
                        cityInfo.setDatatype(2);
                        cityInfo.setCitycode(cityCode.getCitycode());
                        cityInfo.setCityname(cityCode.getCityname());
                        response.getCityInfos().add(cityInfo);
                    }
                    response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
                    response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
                }
            } else {
                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
            }
        } catch (Exception e) {
            response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
            response.setReturnMsg(e.getMessage());
            log.error("调用getRSProvinceInfo过程出现未知异常", e);
        }
        return response;
    }

    @Override
    public BankInfoResponse getRSMainBankInfo() {
        BankInfoResponse response = new BankInfoResponse();
        try {
            WheatfieldBanknQueryRequest req = new WheatfieldBanknQueryRequest();
            WheatfieldBanknQueryResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
            if (rsp != null) {
                log.debug("调用getRSMainBankInfo返回报文: \n" + rsp.getBody());
                String errorMsg;
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        errorMsg = rsp.getSubMsg();
                    } else {
                        errorMsg = rsp.getMsg();
                    }
                    response.setReturnCode(rsp.getErrorCode());
                    response.setReturnMsg(errorMsg);
                    log.error("调用接口getRSMainBankInfo异常：" + errorMsg);
                } else {
                    response.setSuccess(true);
                    
                    List<BankInfo> bankList =  new ArrayList<BankInfo>();
                    for (com.Rop.api.domain.BankInfo bankInfo : rsp.getBankinfos()){
                        BankInfo binfo = new BankInfo();
                        MyBeanUtil.copyBeanProperties(binfo,bankInfo);
                        binfo.setBanktype(1);//总行信息
                        bankList.add(binfo);
                    }
                    response.setBankInfos(bankList);
                    response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
                    response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
                }
            } else {
                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
            }
        } catch (Exception e) {
            response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
            response.setReturnMsg(e.getMessage());
            log.error("调用getRSMainBankInfo过程出现未知异常", e);
        }
        return response;
    }

    @Override
    public BankInfoResponse getRSBranchBankInfo(String mainBankCode,String cityCode) {
        BankInfoResponse response = new BankInfoResponse();
        try {
            WheatfieldBanknQueryRequest req = new WheatfieldBanknQueryRequest();
            req.setCitycode(cityCode);
            req.setBankcode(mainBankCode);
            WheatfieldBanknQueryResponse rsp = RSInvokeConstant.ropClient.execute(req, RSInvokeConstant.sessionKey);
            if (rsp != null) {
                log.debug("调用getRSMainBankInfo返回报文: \n" + rsp.getBody());
                String errorMsg;
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        errorMsg = rsp.getSubMsg();
                    } else {
                        errorMsg = rsp.getMsg();
                    }
                    response.setReturnCode(rsp.getErrorCode());
                    response.setReturnMsg(errorMsg);
                    log.error("调用接口getRSMainBankInfo异常：" + errorMsg);
                } else {
                    response.setSuccess(true);
                 
                    List<BankInfo> bankInfoList = new ArrayList<BankInfo>();
                    for (com.Rop.api.domain.BankInfo bankInfo : rsp.getBankinfos()){
                        BankInfo binfo = new BankInfo();
                        MyBeanUtil.copyBeanProperties(binfo,bankInfo);
                        binfo.setBanktype(2);//支行信息
                        binfo.setParentcode(mainBankCode);
                        binfo.setBankcity(cityCode);
                        bankInfoList.add(binfo);
                    }
                    response.setBankInfos(bankInfoList);
                    response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
                    response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
                }
            } else {
                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
            }
        } catch (Exception e) {
            response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
            response.setReturnMsg(e.getMessage());
            log.error("调用getRSMainBankInfo过程出现未知异常", e);
        }
        return response;
    }
}
