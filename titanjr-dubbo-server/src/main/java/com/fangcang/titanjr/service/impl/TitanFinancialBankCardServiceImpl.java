package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.util.GenericValidate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.ROPErrorEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dao.TitanBankcardDao;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.ModifyWithDrawCardRequest;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.ModifyWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.rs.dto.BankCardInfo;
import com.fangcang.titanjr.rs.dto.BankInfo;
import com.fangcang.titanjr.rs.manager.RSBankCardInfoManager;
import com.fangcang.titanjr.rs.request.BankCardBindRequest;
import com.fangcang.titanjr.rs.request.BankCardQueryRequest;
import com.fangcang.titanjr.rs.request.DeletePersonCardRequest;
import com.fangcang.titanjr.rs.response.BankCardBindResponse;
import com.fangcang.titanjr.rs.response.BankCardQueryResponse;
import com.fangcang.titanjr.rs.response.DeletePersonCardResponse;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

@Service("titanFinancialBankCardService")
public class TitanFinancialBankCardServiceImpl implements TitanFinancialBankCardService {

    private static final Log log = LogFactory.getLog(TitanFinancialBankCardServiceImpl.class);

    @Resource
    private RSBankCardInfoManager rsBankCardInfoManager;

    @Resource
    private TitanBankcardDao titanBankcardDao;

    @Override
    public QueryBankCardBindInfoResponse getBankCardBindInfo(BankCardBindInfoRequest bankCardBindInfoRequest) {
        QueryBankCardBindInfoResponse bankCardBindResponse = new QueryBankCardBindInfoResponse();
        try {
            if (!GenericValidate.validate(bankCardBindInfoRequest)) {
                bankCardBindResponse.putErrorResult("参数校验失败");
                return bankCardBindResponse;
            }
            BankCardQueryRequest bankCardQueryRequest = new BankCardQueryRequest();
            MyBeanUtil.copyProperties(bankCardQueryRequest, bankCardBindInfoRequest);
            BankCardQueryResponse bankCardQueryResponse = rsBankCardInfoManager.queryBindCard(bankCardQueryRequest);
            if (CommonConstant.OPERATE_SUCCESS.equals(bankCardQueryResponse.getOperateStatus()) && bankCardQueryResponse.getBankCardInfoList() != null) {
                bankCardBindResponse.putSuccess();
                List<BankCardInfoDTO> bankCardInfoDTOList = new ArrayList<BankCardInfoDTO>();
                for (BankCardInfo bankCardInfo : bankCardQueryResponse.getBankCardInfoList()) {
                    if (bankCardInfo != null) {
                        BankCardInfoDTO bankCardInfoDTO = new BankCardInfoDTO();
                        MyBeanUtil.copyProperties(bankCardInfoDTO, bankCardInfo);
                        bankCardInfoDTOList.add(bankCardInfoDTO);
                    }
                }
                bankCardBindResponse.setBankCardInfoDTOList(bankCardInfoDTOList);
                return bankCardBindResponse;
            }
        } catch (Exception e) {
            log.error("查询银行卡绑定信息失败", e);
        }
        bankCardBindResponse.putSysError();
        return bankCardBindResponse;
    }

    @Override
    public CusBankCardBindResponse bankCardBind(CusBankCardBindRequest cusBankCardBindRequest) {
        CusBankCardBindResponse cusBankCardBindResponse = new CusBankCardBindResponse();
        try {
            if (cusBankCardBindRequest != null) {
                cusBankCardBindResponse.putErrorResult("绑卡参数不合法");
            }
            BankCardBindRequest bankCardBindRequest = new BankCardBindRequest();
            bankCardBindRequest.setUserid(cusBankCardBindRequest.getUserId());
            bankCardBindRequest.setProductid(cusBankCardBindRequest.getProductId());
            bankCardBindRequest.setConstid(cusBankCardBindRequest.getConstId());
            bankCardBindRequest.setUsertype(cusBankCardBindRequest.getUserType());
            bankCardBindRequest.setAccountnumber(cusBankCardBindRequest.getAccountNumber());
            bankCardBindRequest.setAccount_name(cusBankCardBindRequest.getAccountName());
            bankCardBindRequest.setAccounttypeid(cusBankCardBindRequest.getAccountTypeId());
            bankCardBindRequest.setBankheadname(cusBankCardBindRequest.getBankHeadName());
            bankCardBindRequest.setCurrency(cusBankCardBindRequest.getCurrency());
            bankCardBindRequest.setReq_sn(cusBankCardBindRequest.getReqSn());
            bankCardBindRequest.setSubmit_time(cusBankCardBindRequest.getSubmitTime());
            bankCardBindRequest.setAccountproperty(cusBankCardBindRequest.getAccountProperty());
            bankCardBindRequest.setAccountpurpose(cusBankCardBindRequest.getAccountPurpose());
            bankCardBindRequest.setCertificatetype(cusBankCardBindRequest.getCertificateType());
            bankCardBindRequest.setCertificatenumnumber(cusBankCardBindRequest.getCertificateNumber());
            bankCardBindRequest.setBank_code(cusBankCardBindRequest.getBankCode());
            bankCardBindRequest.setUsertype(cusBankCardBindRequest.getUserType());
            BankCardBindResponse bankCardBindResponse = rsBankCardInfoManager.bindBankCard(bankCardBindRequest);
            if (bankCardBindResponse != null) {
                if (CommonConstant.OPERATE_SUCCESS.equals(bankCardBindResponse.getOperateStatus())) {
                    try {//绑定卡成功，本地初始化
                        TitanBankcard titanBankcard = covertToTitanBankcard(cusBankCardBindRequest);
                        if (titanBankcard != null) {
                            titanBankcardDao.insert(titanBankcard);
                        }
                    } catch (Exception e) {
                        log.error("绑卡本地信息记录失败" + e.getMessage(), e);
                        e.printStackTrace();
                    }
                    cusBankCardBindResponse.putSuccess();
                    return cusBankCardBindResponse;
                } else {
                    if (ROPErrorEnum.getROPErrorEnumByCode(bankCardBindResponse.getReturnCode()) != null) {
                        BankCardBindInfoRequest bankCardBindInfoRequest = new BankCardBindInfoRequest();
                        bankCardBindInfoRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
                        bankCardBindInfoRequest.setProductid(cusBankCardBindRequest.getProductId());
                        bankCardBindInfoRequest.setUserid(cusBankCardBindRequest.getUserId());
                        bankCardBindInfoRequest.setUsertype(cusBankCardBindRequest.getUserType());
                        bankCardBindInfoRequest.setObjorlist(CommonConstant.ALLCARD);
                        QueryBankCardBindInfoResponse queryBankCardBindInfoResponse = this.getBankCardBindInfo(bankCardBindInfoRequest);
                        if (queryBankCardBindInfoResponse != null && queryBankCardBindInfoResponse.getBankCardInfoDTOList() != null) {
                            for (BankCardInfoDTO bankCardInfoDTO : queryBankCardBindInfoResponse.getBankCardInfoDTOList()) {
                                if (bankCardInfoDTO.getAccount_number().equals(bankCardBindRequest.getAccountnumber())) {
                                    cusBankCardBindResponse.putSuccess();
                                    return cusBankCardBindResponse;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("绑卡失败" + e.getMessage(), e);
        }
        cusBankCardBindResponse.putSysError();
        return cusBankCardBindResponse;
    }

    //查询银行编码
    private String getBankCode(String bankName) {

        return null;
    }

    private TitanBankcard covertToTitanBankcard(CusBankCardBindRequest cusBankCardBindRequest) {
        TitanBankcard titanBankcard = new TitanBankcard();
        try {
            titanBankcard.setAccountname(cusBankCardBindRequest.getAccountName());
            titanBankcard.setAccountnumber(cusBankCardBindRequest.getAccountNumber());
            titanBankcard.setAccountproperty(cusBankCardBindRequest.getAccountProperty());
            titanBankcard.setAccountpurpose(cusBankCardBindRequest.getAccountPurpose());
            titanBankcard.setAccounttypeid(cusBankCardBindRequest.getAccountTypeId());

            titanBankcard.setBankbranch(cusBankCardBindRequest.getBankBranch());
            titanBankcard.setBankbranchname(cusBankCardBindRequest.getBankBranchName());
            titanBankcard.setBankcity(cusBankCardBindRequest.getBankCity());
            titanBankcard.setBankcode(cusBankCardBindRequest.getBankCode());
            titanBankcard.setBankheadname(cusBankCardBindRequest.getBankHeadName());
            titanBankcard.setBankprovince(cusBankCardBindRequest.getBankProvince());
            titanBankcard.setBindid(cusBankCardBindRequest.getBindId());

            titanBankcard.setCertificatenumnumber(cusBankCardBindRequest.getCertificateNumber());
            titanBankcard.setCertificatetype(cusBankCardBindRequest.getCertificateType());
            titanBankcard.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
            titanBankcard.setCreatetime(new Date());
            titanBankcard.setCreator(cusBankCardBindRequest.getCreator());
            titanBankcard.setCurrency(cusBankCardBindRequest.getCurrency());

            titanBankcard.setMerrem(cusBankCardBindRequest.getMerRem());
            titanBankcard.setOpenaccountdate(cusBankCardBindRequest.getOpenAccountDate());
            titanBankcard.setOpenaccountdescription(cusBankCardBindRequest.getOpenAccountDescription());
            titanBankcard.setProductid(cusBankCardBindRequest.getProductId());

            titanBankcard.setReferuserid(cusBankCardBindRequest.getReferUserId());
            titanBankcard.setRelatedcard(cusBankCardBindRequest.getRelatedcard());
            titanBankcard.setRelatid(cusBankCardBindRequest.getRelatId());
            titanBankcard.setRemark(cusBankCardBindRequest.getRemark());
            titanBankcard.setReqsn(cusBankCardBindRequest.getReqSn());
            titanBankcard.setRole(cusBankCardBindRequest.getRole());
            if (cusBankCardBindRequest.getSubmitTime() != null) {
                titanBankcard.setSubmittime(DateUtil.sdf5.parse(cusBankCardBindRequest.getSubmitTime()));
            }
            titanBankcard.setTel(cusBankCardBindRequest.getTel());
            titanBankcard.setUserid(cusBankCardBindRequest.getUserId());
            if (StringUtil.isValidString(cusBankCardBindRequest.getUserType())) {
                titanBankcard.setUsertype(Integer.parseInt(cusBankCardBindRequest.getUserType()));
            }
        } catch (Exception e) {
            log.error("绑定卡本地化信息失败" + e.getMessage(), e);
        }
        return titanBankcard;
    }

    @Override
    public DeleteBindBankResponse deleteBindBank(DeleteBindBankRequest deleteBindBankRequest) {
        DeleteBindBankResponse deleteBindBankResponse = new DeleteBindBankResponse();
        try {
            if (deleteBindBankRequest != null) {
                DeletePersonCardRequest deletePersonCardRequest = new DeletePersonCardRequest();
                deletePersonCardRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
                deletePersonCardRequest.setProductid(deleteBindBankRequest.getProductid());
                deletePersonCardRequest.setUserid(deleteBindBankRequest.getUserid());
                deletePersonCardRequest.setAccountnumber(deleteBindBankRequest.getAccountnumber());
                deletePersonCardRequest.setUsertype(deleteBindBankRequest.getUsertype());
                deletePersonCardRequest.setReferuserid(deleteBindBankRequest.getReferuserid());
                DeletePersonCardResponse deletePersonCardResponse = rsBankCardInfoManager.deletePersonCard(deletePersonCardRequest);
                if (deletePersonCardResponse != null) {
                    if (CommonConstant.OPERATE_SUCCESS.equals(deletePersonCardResponse.getOperateStatus())) {
                        deleteBindBankResponse.putSuccess();
                    } else {
                        deleteBindBankResponse.putErrorResult(deletePersonCardResponse.getReturnCode(), deletePersonCardResponse.getReturnMsg());
                    }
                    return deleteBindBankResponse;
                }
            }
        } catch (Exception e) {
            log.error("解绑卡信息失败" + e.getMessage());
        }
        return deleteBindBankResponse;
    }

    @Override
    public ModifyWithDrawCardResponse modifyWithDrawCard(
            ModifyWithDrawCardRequest modifyWithDrawCardRequest) {
        // TODO Auto-generated method stub
        return null;
    }

}
