package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.BindCardStatus;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.util.GenericValidate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.ROPErrorEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dao.TitanBankcardDao;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.BankCardRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.ModifyInvalidWithDrawCardRequest;
import com.fangcang.titanjr.dto.request.ModifyWithDrawCardRequest;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.ModifyInvalidWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.ModifyWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.parameter.TitanBankcardParam;
import com.fangcang.titanjr.rs.dto.BankCardInfo;
import com.fangcang.titanjr.rs.dto.BankInfo;
import com.fangcang.titanjr.rs.manager.RSBankCardInfoManager;
import com.fangcang.titanjr.rs.request.BankCardBindRequest;
import com.fangcang.titanjr.rs.request.BankCardQueryRequest;
import com.fangcang.titanjr.rs.request.DeletePersonCardRequest;
import com.fangcang.titanjr.rs.request.InvalidPubCardModifyRequest;
import com.fangcang.titanjr.rs.response.BankCardBindResponse;
import com.fangcang.titanjr.rs.response.BankCardQueryResponse;
import com.fangcang.titanjr.rs.response.DeletePersonCardResponse;
import com.fangcang.titanjr.rs.response.InvalidPubCardModifyResponse;
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
                        	if(CommonConstant.ENTERPRISE.equals(cusBankCardBindRequest.getAccountProperty())){
                            	titanBankcard.setStatus(BindCardStatus.BIND_BINDING.status);
                            }
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
                cusBankCardBindResponse.putErrorResult(bankCardBindResponse.getReturnCode(),bankCardBindResponse.getReturnMsg());
                return cusBankCardBindResponse;
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

	@Override
	public List<BankCardDTO> queryBankCardDTO(BankCardRequest bankCardRequest) {
		List<BankCardDTO> bankCardDTOList = new ArrayList<BankCardDTO>();
		
		TitanBankcardParam condition = new TitanBankcardParam();
		PaginationSupport<TitanBankcard> paginationSupport = new PaginationSupport<TitanBankcard>();
		
		condition.setStatus(bankCardRequest.getStatus());
		condition.setAccountproperty(bankCardRequest.getAccountproperty());
		condition.setUserid(bankCardRequest.getUserId());
		condition.setAccountpurpose(bankCardRequest.getAccountpurpose());
		titanBankcardDao.selectForPage(condition, paginationSupport);
		
		List<TitanBankcard> titanBankcardList = paginationSupport.getItemList();
		if(titanBankcardList !=null && titanBankcardList.size()>0){
			for(TitanBankcard titanBankcard :titanBankcardList){
				BankCardDTO bankCardDTO = new BankCardDTO();
				MyBeanUtil.copyBeanProperties(bankCardDTO, titanBankcard);
				bankCardDTOList.add(bankCardDTO);
			}
			return bankCardDTOList;
		}
		return null;
	}

	@Override
	public void bindBankCardBatch() {//批量修改绑定的银行卡
		//此处采用第三方分页工具，offset不能为1，因为分页到了最后始终返回最后一条数据，陷入死循环
		bindBankCard(1,100);
	}

	private void bindBankCard(int rows,int offset){
		
		TitanBankcardParam condition = new TitanBankcardParam();
		PaginationSupport<TitanBankcard> paginationSupport = new PaginationSupport<TitanBankcard>();
		condition.setStatus(BindCardStatus.BIND_BINDING.status);
		condition.setAccountproperty(CommonConstant.ENTERPRISE);
		condition.setAccountpurpose(CommonConstant.WITHDRAW_CARD);
		paginationSupport.setCurrentPage(rows);
		paginationSupport.setPageSize(offset);
		titanBankcardDao.selectForPage(condition, paginationSupport);
		
		if( paginationSupport.getItemList()!=null){
			List<TitanBankcard> bankcardList  = paginationSupport.getItemList();
			offset = bankcardList.size();
			if(bankcardList.size()>0){
				for(TitanBankcard titanBankcard :bankcardList){
					//查询融数
					String bindStatus = this.queryBindCard(titanBankcard);
					if(StringUtil.isValidString(bindStatus)){//绑定成功,更新自己代码
						updateBankCard(titanBankcard,bindStatus);
					}
				}
			}
		}
		
		if(offset <100){
			return ;
		}else{
			this.bindBankCard(rows+1,offset);
		}
		
	}
	
	/**
	 * 检查该卡是否绑定成功
	 * @param titanBankcard
	 * @return
	 */
	private String queryBindCard(TitanBankcard titanBankcard){
		
		String status = "";
		BankCardQueryRequest bankCardQueryRequest = new BankCardQueryRequest();
		bankCardQueryRequest.setConstid(titanBankcard.getConstid());
		bankCardQueryRequest.setProductid(titanBankcard.getProductid());
		bankCardQueryRequest.setUserid(titanBankcard.getUserid());
		bankCardQueryRequest.setObjorlist(CommonConstant.ALLCARD);
		bankCardQueryRequest.setUsertype(CommonConstant.ENTERPRISE);
		BankCardQueryResponse bankCardQueryResponse = rsBankCardInfoManager.queryBindCard(bankCardQueryRequest);
		if(bankCardQueryResponse.getBankCardInfoList() !=null && bankCardQueryResponse.getBankCardInfoList().size()>0){
			for(BankCardInfo bankCardInfo :bankCardQueryResponse.getBankCardInfoList()){
				if(CommonConstant.WITHDRAW_CARD.equals(bankCardInfo.getAccountpurpose()) 
					&& CommonConstant.ENTERPRISE.equals(bankCardInfo.getAccountproperty())){//对公，提现卡
			        if(CommonConstant.BIND_SUCCESS.equals(bankCardInfo.getStatus()) ){
			        	status = CommonConstant.BIND_SUCCESS;
			        }else if(CommonConstant.BIND_FAIL.equals(bankCardInfo.getStatus())){//审核失败
			        	status = CommonConstant.BIND_FAIL;
			        }
				}
			}
		}
		return status;
	}
	
	
	private void updateBankCard(TitanBankcard titanBankcard,String status){
		TitanBankcard bankcard = new TitanBankcard();
		bankcard.setBankcardid(titanBankcard.getBankcardid());
		bankcard.setStatus(BindCardStatus.BIND_FAIL.status);
		if(status.equals(CommonConstant.BIND_SUCCESS)){
			bankcard.setStatus(BindCardStatus.BIND_SUCCESS.status);
		}
		try{
			titanBankcardDao.update(bankcard);
		}catch(Exception e){
			log.error("更新银行卡绑定信息失败："+e.getMessage(),e);
		}
	}
	
	
	public ModifyInvalidWithDrawCardResponse modifyinvalidPublicCard(ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest){
		ModifyInvalidWithDrawCardResponse response = new ModifyInvalidWithDrawCardResponse();
		String accountId = queryAccountid(modifyInvalidWithDrawCardRequest);
		if(StringUtil.isValidString(accountId)){
			InvalidPubCardModifyRequest invalidPubCardModifyRequest = new InvalidPubCardModifyRequest();
			MyBeanUtil.copyProperties(invalidPubCardModifyRequest, modifyInvalidWithDrawCardRequest);
			InvalidPubCardModifyResponse invalidPubCardModifyResponse = rsBankCardInfoManager.modifyInvalidPublicCard(invalidPubCardModifyRequest);
			if(invalidPubCardModifyResponse.getOperateStatus().equals(CommonConstant.OPERATE_SUCCESS)){
				response.putSuccess();
			}else{
				response.putErrorResult(invalidPubCardModifyResponse.getReturnCode(), invalidPubCardModifyResponse.getReturnMsg());
			}
			return response;
		}
		response.putErrorResult("您还未绑定过卡");
		return response;
	}
	
	private String queryAccountid(ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest){
		BankCardQueryRequest bankCardQueryRequest = new BankCardQueryRequest();
		bankCardQueryRequest.setConstid(modifyInvalidWithDrawCardRequest.getConstid());
		bankCardQueryRequest.setProductid(modifyInvalidWithDrawCardRequest.getProductid());
		bankCardQueryRequest.setUserid(modifyInvalidWithDrawCardRequest.getUserid());
		bankCardQueryRequest.setObjorlist(CommonConstant.ALLCARD);
		bankCardQueryRequest.setUsertype(CommonConstant.ENTERPRISE);
		BankCardQueryResponse bankCardQueryResponse = rsBankCardInfoManager.queryBindCard(bankCardQueryRequest);
		if(bankCardQueryResponse.getBankCardInfoList() !=null && bankCardQueryResponse.getBankCardInfoList().size()>0){
			for(BankCardInfo bankCardInfo :bankCardQueryResponse.getBankCardInfoList()){
				if(CommonConstant.WITHDRAW_CARD.equals(bankCardInfo.getAccountpurpose()) 
						&& CommonConstant.BIND_FAIL.equals(bankCardInfo.getStatus()) 
						&& CommonConstant.ENTERPRISE.equals(bankCardInfo.getAccountproperty())){//对公，提现卡
				        return bankCardInfo.getAccountid();
				}
			}
		}
		return null;
	}
	
}
