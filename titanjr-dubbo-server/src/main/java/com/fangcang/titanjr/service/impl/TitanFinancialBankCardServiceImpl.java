package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.BindCardStatus;
import com.fangcang.titanjr.common.enums.ROPErrorEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanBankcardDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.bean.BankCardInfoDTO;
import com.fangcang.titanjr.dto.request.BankCardBindInfoRequest;
import com.fangcang.titanjr.dto.request.BankCardRequest;
import com.fangcang.titanjr.dto.request.CusBankCardBindRequest;
import com.fangcang.titanjr.dto.request.DeleteBindBankRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.ModifyInvalidWithDrawCardRequest;
import com.fangcang.titanjr.dto.request.ModifyWithDrawCardRequest;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.ModifyInvalidWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.ModifyWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.parameter.TitanBankcardParam;
import com.fangcang.titanjr.rs.dto.BankCardInfo;
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

import net.sf.json.JSONSerializer;

@Service("titanFinancialBankCardService")
public class TitanFinancialBankCardServiceImpl implements TitanFinancialBankCardService {

    private static final Log log = LogFactory.getLog(TitanFinancialBankCardServiceImpl.class);

    @Resource
    private RSBankCardInfoManager rsBankCardInfoManager;

    @Resource
    private TitanBankcardDao titanBankcardDao;
    
    @Resource
    private TitanOrgDao titanOrgDao;

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
            if (cusBankCardBindRequest == null) {
                cusBankCardBindResponse.putErrorResult("绑卡参数不合法");
                return cusBankCardBindResponse;
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
            bankCardBindRequest.setBank_branch(cusBankCardBindRequest.getBankBranch());
            bankCardBindRequest.setBank_province(cusBankCardBindRequest.getBankProvince());
            bankCardBindRequest.setBank_city(cusBankCardBindRequest.getBankCity());
            
            log.info("绑定卡的参数:"+JSONSerializer.toJSON(bankCardBindRequest));
            BankCardBindResponse bankCardBindResponse = rsBankCardInfoManager.bindBankCard(bankCardBindRequest);
            if (bankCardBindResponse != null) {
            	TitanBankcard titanBankcard = covertToTitanBankcard(cusBankCardBindRequest);
                if (CommonConstant.OPERATE_SUCCESS.equals(bankCardBindResponse.getOperateStatus())) {
                    try {//绑定卡成功，本地初始化
                        if (titanBankcard != null) {
                        	if(CommonConstant.ENTERPRISE.equals(cusBankCardBindRequest.getAccountProperty())){
                            	titanBankcard.setStatus(BindCardStatus.BIND_BINDING.status);//对公绑卡成功，还出于审核状态
                            }else if(CommonConstant.PERSONAL.equals(cusBankCardBindRequest.getAccountProperty())){
                            	titanBankcard.setStatus(BindCardStatus.BIND_SUCCESS.status); //对私绑卡成功就直接记录成功
                            }
                        	titanBankcardDao.insert(titanBankcard);
                        }
	                } catch (Exception e) {
	                    log.error("绑卡本地信息记录失败。异常响应信息：" + e.getMessage()+",绑卡参数:"+Tools.gsonToString(cusBankCardBindRequest), e);
	                    cusBankCardBindResponse.putErrorResult("绑卡失败，请重试");
	                    return cusBankCardBindResponse;
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
	                //绑卡失败也要在本地保存绑卡失败记录
                    if (titanBankcard != null) {
                    	try {
							titanBankcard.setStatus(BindCardStatus.BIND_FAIL.status);
							titanBankcard.setRemark(bankCardBindResponse.getReturnMsg());
							titanBankcardDao.insert(titanBankcard);
						} catch (Exception e) {
							log.error("绑卡本地信息记录失败" + e.getMessage(), e);
							e.printStackTrace();
						}
                    }
	            }
	            log.error("绑卡失败,绑卡参数："+Tools.gsonToString(cusBankCardBindRequest)+",错误代码："+bankCardBindResponse.getReturnCode()+"，错误信息："+bankCardBindResponse.getReturnMsg());
	            cusBankCardBindResponse.putErrorResult(bankCardBindResponse.getReturnCode(), bankCardBindResponse.getReturnMsg());
	            return cusBankCardBindResponse;
            }
            
        } catch (Exception e) {
        	log.error("绑卡失败。异常响应信息：" + e.getMessage()+",绑卡参数:"+Tools.gsonToString(cusBankCardBindRequest), e);
        	cusBankCardBindResponse.putErrorResult("绑卡失败，请重试");
            return cusBankCardBindResponse;
        }
        
        cusBankCardBindResponse.putSysError();
        return cusBankCardBindResponse;
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
                        //融数删除成功后需要删除本地绑卡
                        TitanBankcard entity = new TitanBankcard();
                        entity.setUserid(deleteBindBankRequest.getUserid());
                        entity.setUsertype(Integer.parseInt(deleteBindBankRequest.getUsertype()));
                        //entity.setAccountnumber(deleteBindBankRequest.getAccountnumber());
                        entity.setProductid(deleteBindBankRequest.getProductid());
                        try {
							int result = titanBankcardDao.delete(entity);
							log.info("本地删除绑卡成功，共删除" + result + "条记录");
						} catch (Exception e) {
							log.error("本地删除绑卡异常", e);
						}
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
		this.bindBankCard(1,100,null);
	}

	
	
	private void bindBankCard(int rows,int offset,String userId){
		
		boolean doUpdate = false;
		TitanBankcardParam condition = new TitanBankcardParam();
		PaginationSupport<TitanBankcard> paginationSupport = new PaginationSupport<TitanBankcard>();
		condition.setStatus(BindCardStatus.BIND_BINDING.status);
		condition.setAccountproperty(CommonConstant.ENTERPRISE);
//		condition.setAccountpurpose(CommonConstant.WITHDRAW_CARD);
		if(StringUtil.isValidString(userId)){
			condition.setUserid(userId);
		}
		paginationSupport.setCurrentPage(rows);
		paginationSupport.setPageSize(offset);
		titanBankcardDao.selectForPage(condition, paginationSupport);
		log.info("绑卡的查询结果:"+JSONSerializer.toJSON(paginationSupport));
		if( paginationSupport.getItemList()!=null){
			List<TitanBankcard> bankcardList  = paginationSupport.getItemList();
			offset = bankcardList.size();
			if(bankcardList.size()>0){
				for(TitanBankcard titanBankcard :bankcardList){
					//查询融数
					log.info("查询绑卡的入参titanBankcard:"+JSONSerializer.toJSON(titanBankcard));
					String bindStatus = this.queryBindCard(titanBankcard);
					log.info("查询绑卡结果bindStatus:"+bindStatus);
					if(StringUtil.isValidString(bindStatus)){//绑定成功,更新自己代码
						updateBankCard(titanBankcard,bindStatus);
						doUpdate = true;
					}
				}
			}
		}
		
		if(offset <100){
			return ;
		}else{
			if (!doUpdate) {
				rows++;
			}
			this.bindBankCard(rows,offset,userId);
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
				if(bankCardInfo.getAccount_number().equals(titanBankcard.getAccountnumber())){
					if( CommonConstant.ENTERPRISE.equals(bankCardInfo.getAccountproperty())){//对公，提现卡
				        if(CommonConstant.BIND_SUCCESS.equals(bankCardInfo.getStatus()) ){
				        	status = CommonConstant.BIND_SUCCESS;
				        }else if(CommonConstant.BIND_FAIL.equals(bankCardInfo.getStatus())){//审核失败
				        	status = CommonConstant.BIND_FAIL;
				        }
					}
					break;
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
		log.info("更新绑卡信息:"+JSONSerializer.toJSON(bankcard));
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
			invalidPubCardModifyRequest.setAccountid(accountId);
			InvalidPubCardModifyResponse invalidPubCardModifyResponse = rsBankCardInfoManager.modifyInvalidPublicCard(invalidPubCardModifyRequest);
			if(invalidPubCardModifyResponse.getOperateStatus().equals(CommonConstant.OPERATE_SUCCESS)){
				response.putSuccess("修改绑卡信息成功");
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

	@Override
	public void bindBankCardForOne(String userId) {
		this.bindBankCard(0,100,userId);
	}
	
	
	@Override
	public TitanBankcard queryBankCardInfo(TitanBankcardParam param) {
		TitanBankcard titanBankcard = null;
		try {
			titanBankcard = titanBankcardDao.selectEntity(param);
		} catch (DaoException e) {
			log.error("查询本地绑卡信息异常", e);
		}
		return titanBankcard;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO batchUpdatePersonalCard() {
		
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		List<TitanBankcard> cardList = new ArrayList<TitanBankcard>();
		StringBuffer errorMsg = new StringBuffer("");
		
		//查询个人机构
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setUserType(Integer.parseInt(CommonConstant.PERSONAL));
		List<TitanOrg> orgList = titanOrgDao.queryTitanOrgList(organQueryRequest);
		if(CollectionUtils.isEmpty(orgList)){
			baseResponseDTO.setReturnMessage("未查询到本地机构信息");
			return baseResponseDTO;
		}
		for (TitanOrg titanOrg : orgList) {
			if(CommonConstant.PERSONAL.equals(String.valueOf(titanOrg.getUsertype()))){
				//查询融数对私绑卡信息
				BankCardBindInfoRequest brq = new BankCardBindInfoRequest();
				brq.setUserid(titanOrg.getUserid());
				brq.setUsertype(String.valueOf(titanOrg.getUsertype()));
				brq.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				brq.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
				//1：结算卡，2：所有绑定卡
				brq.setObjorlist("2");
				QueryBankCardBindInfoResponse cbr = this.getBankCardBindInfo(brq);
				if (cbr.isResult()
						&& CollectionUtils.isNotEmpty(cbr.getBankCardInfoDTOList())) {
					for (BankCardInfoDTO cid : cbr.getBankCardInfoDTOList()) {
						if (cid.getStatus().equals(
								BankCardEnum.BankCardStatusEnum.NORMAL.getKey())
								&& !cid.getAccountpurpose().equals(BankCardEnum.BankCardPurposeEnum.OTHER_CARD.getKey())
								&& String.valueOf(titanOrg.getUsertype()).equals(cid.getAccountproperty())) {
							TitanBankcard titanBankcard = new TitanBankcard();
							if(!StringUtil.isValidString(cid.getAccountrealname()) ||
									!StringUtil.isValidString(cid.getAccount_number()) ||
									!StringUtil.isValidString(cid.getBankheadname()) ||
									!StringUtil.isValidString(cid.getStatus()) ||
									!StringUtil.isValidString(cid.getCertificatetype()) || 
									!StringUtil.isValidString(cid.getCertificatenumber())){
								errorMsg.append("【"+cid.getAccount_number()+"-参数错误】");
								continue;
							}
							titanBankcard.setAccountname(cid.getAccountrealname());
				            titanBankcard.setAccountnumber(cid.getAccount_number());
				            titanBankcard.setAccountproperty(cid.getAccountproperty());
				            titanBankcard.setAccountpurpose(cid.getAccountpurpose());
				            titanBankcard.setAccounttypeid(cid.getAccount_type_id());

				            titanBankcard.setBankbranch(cid.getBankbranch());
				            titanBankcard.setBankbranchname(cid.getBankbranchname());
				            titanBankcard.setBankcity(cid.getBankcity());
				            titanBankcard.setBankheadname(cid.getBankheadname());
				            titanBankcard.setBankprovince(cid.getBankprovince());
				            titanBankcard.setBankcode(cid.getBankhead());
				            
				            titanBankcard.setCertificatetype(cid.getCertificatetype());
				            titanBankcard.setCertificatenumnumber(cid.getCertificatenumber());
				            
				            titanBankcard.setUserid(titanOrg.getUserid());
				            titanBankcard.setUsertype(titanOrg.getUsertype());
				            
				            titanBankcard.setStatus(Integer.parseInt(cid.getStatus()));
				            titanBankcard.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
				            titanBankcard.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				            titanBankcard.setCurrency(cid.getCurrency());
				            titanBankcard.setCreatetime(new Date());
				            titanBankcard.setCreator("servlet");
				            titanBankcard.setRemark("同步更新");
				            if(StringUtil.isValidString(cid.getOpen_account_date())){
				            	titanBankcard.setOpenaccountdate(DateUtil.toDataYYYYMMDD(cid.getOpen_account_date()));
				            }
				            cardList.add(titanBankcard);
						}
					}
				}
			}
			
		}
		
		if(CollectionUtils.isEmpty(cardList)){
			baseResponseDTO.setReturnMessage("未查询到需要同步的对私绑卡记录，errorMsg：" + errorMsg.toString());
			return baseResponseDTO;
		}
		log.info("在融数查询到" + cardList.size() + "条需要同步的对私绑卡记录");
		
		//插入之前先删除本地对私绑卡信息
		TitanBankcard deleteReq = new TitanBankcard();
		deleteReq.setAccountproperty(CommonConstant.PERSONAL);
		deleteReq.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
		deleteReq.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		log.info("执行删除本地对私卡绑定信息，参数：" + deleteReq.toString());
		int deleteResult = titanBankcardDao.delete(deleteReq);
		log.info("删除本地对私绑卡信息成功，一共删除" + deleteResult + "条记录");
		
		//批量插入
		log.info("执行批量插入对私卡绑定记录");
		int inserResult = titanBankcardDao.intsertBatch(cardList);
		baseResponseDTO.setResult(true);
		baseResponseDTO.setReturnMessage("成功同步" + inserResult + "条对私绑卡记录，errorMsg：" + errorMsg.toString());
		return baseResponseDTO;
	}

}
