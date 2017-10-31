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
import com.fangcang.titanjr.common.enums.OrgBindCardEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanBankcardDao;
import com.fangcang.titanjr.dao.TitanOrgCardMapDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dao.TitanOrgMapInfoDao;
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
import com.fangcang.titanjr.dto.request.OrgBaseInfoRequest;
import com.fangcang.titanjr.dto.request.OrgSubCardRequest;
import com.fangcang.titanjr.dto.request.OrgSubRequest;
import com.fangcang.titanjr.dto.request.RegOrgSubRequest;
import com.fangcang.titanjr.dto.response.BankCardStatusResponse;
import com.fangcang.titanjr.dto.response.CusBankCardBindResponse;
import com.fangcang.titanjr.dto.response.DeleteBindBankResponse;
import com.fangcang.titanjr.dto.response.ModifyInvalidWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.ModifyWithDrawCardResponse;
import com.fangcang.titanjr.dto.response.OrgSubCardResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.QueryBankCardBindInfoResponse;
import com.fangcang.titanjr.entity.TitanBankcard;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.TitanOrgCardMap;
import com.fangcang.titanjr.entity.TitanOrgMapInfo;
import com.fangcang.titanjr.entity.TitanOrgSub;
import com.fangcang.titanjr.entity.parameter.TitanBankcardParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgCardMapParam;
import com.fangcang.titanjr.entity.parameter.TitanOrgMapInfoParam;
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
import com.fangcang.titanjr.service.TitanFinancialOrganService;
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
    
    @Resource
    private TitanOrgCardMapDao orgCardMapDao;
    
    @Resource
    private TitanOrgMapInfoDao orgMapInfoDao;
    
    @Resource
    TitanFinancialOrganService orgService;
    
    
    @Override
	public BankCardStatusResponse getBankCardStatus(String orgCode) {
    	BankCardStatusResponse response = new BankCardStatusResponse();
    	response.putSuccess();
    	//是否存在机构关联关系
    	TitanOrgMapInfo orgMapInfo = orgMapInfoDao.getOneTitanOrgMapInfo(orgCode);
    	if(orgMapInfo==null){
    		response.setOrgBankcardStatus(OrgBindCardEnum.NO_ORGSUB.getStatus());
    		response.setOrgBankcardMsg(OrgBindCardEnum.NO_ORGSUB.getDes());
			return response;
    	}
		// 虚拟机构是否存在绑卡关联的记录
    	TitanOrgCardMapParam titanOrgCardMapParam = new TitanOrgCardMapParam();
		titanOrgCardMapParam.setOrgCode(orgCode);
		List<TitanOrgCardMap> orgCardMapList = orgCardMapDao.selectList(titanOrgCardMapParam);
		if(CollectionUtils.isEmpty(orgCardMapList)){
			response.setOrgBankcardStatus(OrgBindCardEnum.NO_BANKCARD.getStatus());
    		response.setOrgBankcardMsg(OrgBindCardEnum.NO_BANKCARD.getDes());
			return response;
		}
		//真实机构的绑卡记录是否存在
		TitanBankcardParam bankcardParam = new TitanBankcardParam();
		bankcardParam.setBankcardid(orgCardMapList.get(0).getBankcardid());
		TitanBankcard  bankcard = titanBankcardDao.selectEntity(bankcardParam);
		if(bankcard==null){
			log.error("绑卡数据异常:真实机构的绑卡记录是否存在，虚拟机构(orgCode):"+orgCode+",真实机构的绑卡记录id(Bankcardid)："+orgCardMapList.get(0).getBankcardid());
			response.setOrgBankcardStatus(OrgBindCardEnum.NO_BANKCARD.getStatus());
    		response.setOrgBankcardMsg(OrgBindCardEnum.NO_BANKCARD.getDes());
			return response;
		}
		response.setBankcard(bankcard);
		//本地绑卡状态
		BankCardEnum.BankCardStatusEnum  bankCardStatusEnum = BankCardEnum.BankCardStatusEnum.getEnumByKey(bankcard.getStatus()+"");
		response.setOrgSubCode(orgMapInfo.getOrgSubcode());
		response.setOrgBankcardStatus(bankCardStatusEnum.getKey());
		response.setOrgBankcardMsg(bankCardStatusEnum.getDes());
		if(!bankCardStatusEnum.getKey().equals(BankCardEnum.BankCardStatusEnum.NORMAL.getKey())){
			if(StringUtil.isValidString(bankcard.getRemark())){
				response.setOrgBankcardMsg(bankcard.getRemark());
			}
			//更新绑卡记录的状态
			bindBankCardForOne(orgMapInfo.getOrgSubcode());
			
		}
		return response;
		
	}

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
	/***
	 * 机构绑卡
	 * @throws GlobalServiceException 
	 * @throws MessageServiceException 
	 */
	public OrgSubCardResponse bindOrgSubCard(OrgSubCardRequest orgSubCardRequest) throws GlobalServiceException{
		OrgSubCardResponse orgSubCardResponse = new OrgSubCardResponse();
		log.info("机构绑卡,参数orgSubCardRequest："+Tools.gsonToString(orgSubCardRequest));
		OrgSubRequest orgSubRequest = new OrgSubRequest();
		orgSubRequest.setOrgCode(orgSubCardRequest.getOrgCode()); 
		TitanOrgSub orgSub = null;
		boolean isDeleteMapInfo = false;//是否删除机构映射关系
		boolean isDeleteCardMap = false;//是否删除绑卡记录
		if(StringUtil.isValidString(orgSubCardRequest.getCertificateNumber())){//界面输入了身份证号码就机构映射关系
			isDeleteMapInfo = true;
		}
		if(StringUtil.isValidString(orgSubCardRequest.getAccountNumber())){//界面输入了卡号就删除绑卡记录
			isDeleteCardMap = true;
		}
		
		if(StringUtil.isValidString(orgSubCardRequest.getCertificateNumber())){//优先使用界面输入的机构(开户)信息
			deleteOrgBindCard(orgSubCardRequest.getOrgCode(),isDeleteMapInfo,isDeleteCardMap);
		}else {//如果没传证件信息，则存在关联关系，则用当前关联的信息
			orgSub = orgService.getOrgSub(orgSubRequest);
			if(orgSub!=null){
				orgSubCardRequest.setUserType(orgSub.getUsertype()+"");
				orgSubCardRequest.setAccountName(orgSub.getOrgname());
				orgSubCardRequest.setCertificateNumber(StringUtil.isValidString(orgSub.getBuslince())?orgSub.getBuslince():orgSub.getCertificatenumber());
			}
		}
		if (!GenericValidate.validate(orgSubCardRequest)){
			log.error("机构绑卡的必填参数不能为空,参数为orgSubCardRequest："+Tools.gsonToString(orgSubCardRequest));
			orgSubCardResponse.putErrorResult("必填参数不能为空");
			return orgSubCardResponse;
		}
		//设置默认值
		setOrgSubCardDefaultValue(orgSubCardRequest);
		if(orgSub==null){//不存在机构关联关系则需要注册建立 
			//1-添加机构信息
			RegOrgSubRequest regOrgSubRequest = new RegOrgSubRequest();
			regOrgSubRequest.setOrgName(orgSubCardRequest.getAccountName());
			regOrgSubRequest.setUserType(Integer.valueOf(orgSubCardRequest.getUserType()));
			regOrgSubRequest.setCertificateType("0");//身份证或者营业执照
			if(orgSubCardRequest.getUserType().equals("1")){//企业
				regOrgSubRequest.setBuslince(orgSubCardRequest.getCertificateNumber());
			}else{//个人
				regOrgSubRequest.setCertificateNumber(orgSubCardRequest.getCertificateNumber());
			}
			OrganRegisterResponse registerResponse;
			try {
				registerResponse = orgService.registerOrgSub(regOrgSubRequest, orgSubCardRequest.getOrgCode());
				if(registerResponse.isResult()==false){
					orgSubCardResponse.putErrorResult(registerResponse.getReturnMessage());
					log.error("注册真实机构失败,注册参数："+Tools.gsonToString(orgSubCardRequest)+",错误信息："+registerResponse.getReturnMessage());
					orgSubCardResponse.putErrorResult(registerResponse.getReturnMessage());
					return orgSubCardResponse;
				}
			} catch (MessageServiceException e) {
				log.error("注册真实机构异常,注册参数："+Tools.gsonToString(orgSubCardRequest)+",错误信息："+e.getMessage());
				orgSubCardResponse.putErrorResult(e.getMessage());
				return orgSubCardResponse;
			}
			//注册成功
			orgSub = orgService.getOrgSub(orgSubRequest);
		}
		orgSubCardRequest.setUserId(orgSub.getOrgcode());
		Integer bankcardid = null;
		//2-调用融数新增绑卡（公和私）
		if(orgSub.getUsertype()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			orgSubCardRequest.setAccountProperty("2");//账户属性（1：对公，2：对私）
		}else{
			orgSubCardRequest.setAccountProperty("1");
		}
		CusBankCardBindResponse cusBankCardBindResponse = bankCardBind(orgSubCardRequest);
		if(cusBankCardBindResponse.isResult()){//绑卡成功
			bankcardid = cusBankCardBindResponse.getBankcardid();
		}else{
			log.error("真实机构绑卡失败,绑卡参数："+Tools.gsonToString(orgSubCardRequest));
			orgSubCardResponse.putErrorResult(cusBankCardBindResponse.getReturnMessage());
			deleteOrgBindCard(orgSubCardRequest.getOrgCode(),isDeleteMapInfo,isDeleteCardMap);
			return orgSubCardResponse;
		}
		//3-实名验证成功后，检查本地和融数的机构信息是否一致，并修改
		if(orgSub.getUsertype()==TitanOrgEnum.UserType.PERSONAL.getKey()){
			//TODO 测试  ，上线后打开，存在关联的机构，则修改名字和身份证号码
			OrgBaseInfoRequest orgBaseInfoRequest = new OrgBaseInfoRequest();
			orgBaseInfoRequest.setOrgCode(orgSub.getOrgcode());
			orgBaseInfoRequest.setOrgName(orgSubCardRequest.getAccountName());
			orgBaseInfoRequest.setCertificatenumber(orgSubCardRequest.getCertificateNumber());
			try {
				orgService.updateOrgBaseInfo(orgBaseInfoRequest);
			} catch (MessageServiceException e) {
				log.error("融数修改机构信息失败，参数："+Tools.gsonToString(orgBaseInfoRequest)+"，错误信息："+e.getMessage());
				orgSubCardResponse.putErrorResult(e.getMessage());
				return orgSubCardResponse;
			}
		}
		//4-新增绑卡关联记录
		TitanOrgCardMapParam orgCardMapParam = new TitanOrgCardMapParam();
		orgCardMapParam.setOrgCode(orgSubCardRequest.getOrgCode());
		orgCardMapDao.delete(orgCardMapParam);
		TitanOrgCardMap orgCardMap = new TitanOrgCardMap();
		orgCardMap.setOrgCode(orgSubCardRequest.getOrgCode());
		orgCardMap.setBankcardid(bankcardid);
		orgCardMap.setIsactive(1);//启用
		orgCardMap.setCreator(orgSubCardRequest.getOperator());
		orgCardMap.setCreateTime(new Date());
		orgCardMapDao.insert(orgCardMap);
		orgSubCardResponse.putSuccess("绑卡成功");
		return orgSubCardResponse;
	}
	/***
	 * 删除虚拟机构关联和绑卡记录
	 * @param orgCode 虚拟机构orgcode
	 * @param certificateNumber 证件号码
	 * @param accountNumber 卡号
	 */
	private void deleteOrgBindCard(String orgCode ,boolean isDeleteMapInfo,boolean isDeleteCardMap){
		if(isDeleteMapInfo){//页面输入了证件信息，则删除映射
			TitanOrgMapInfoParam titanOrgMapInfoParam = new TitanOrgMapInfoParam();
			titanOrgMapInfoParam.setOrgCode(orgCode);
			log.info("删除机构映射");
			orgMapInfoDao.delete(titanOrgMapInfoParam);
		}
		if(isDeleteCardMap){//页面输入了卡号，则删除卡关联
			TitanOrgCardMapParam param = new TitanOrgCardMapParam();
			param.setOrgCode(orgCode);
			log.info("删除机构卡关联");
			orgCardMapDao.delete(param);
		}
	}
	/***
	 * 设置默认值，如果为空
	 * @param orgSubCardRequest
	 */
	private void setOrgSubCardDefaultValue(OrgSubCardRequest orgSubCardRequest){
		orgSubCardRequest.setConstId(CommonConstant.RS_FANGCANG_CONST_ID);
		orgSubCardRequest.setReqSn(String.valueOf(System.currentTimeMillis()));
		orgSubCardRequest.setAccountPurpose("2");//统一绑为其他卡,2
		orgSubCardRequest.setSubmitTime(DateUtil.dateToString(new Date(),"yyyyMMddHHmmss"));
		if(!StringUtil.isValidString(orgSubCardRequest.getProductId())){//使用默认的产品
			orgSubCardRequest.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		}
		if(!StringUtil.isValidString(orgSubCardRequest.getCurrency())){
			orgSubCardRequest.setCurrency("CNY");//人民币
		}
		if(!StringUtil.isValidString(orgSubCardRequest.getAccountTypeId())){
			orgSubCardRequest.setAccountTypeId("00");//银行卡
		}
		orgSubCardRequest.setCertificateType("0");//身份证或者营业执照
		
	 
		
		
	}
	
	
	private CusBankCardBindResponse bindCardForRs(CusBankCardBindRequest cusBankCardBindRequest){
		CusBankCardBindResponse cusBankCardBindResponse = new CusBankCardBindResponse();
		//1、查询该卡号是否绑卡
		BankCardBindInfoRequest bankCardBindInfoRequest = new BankCardBindInfoRequest();
        bankCardBindInfoRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
        bankCardBindInfoRequest.setProductid(cusBankCardBindRequest.getProductId());
        bankCardBindInfoRequest.setUserid(cusBankCardBindRequest.getUserId());
        bankCardBindInfoRequest.setUsertype(cusBankCardBindRequest.getUserType());
        bankCardBindInfoRequest.setObjorlist(CommonConstant.ALLCARD);
        BankCardInfoDTO bankCardInfoDTOExist = null;
        QueryBankCardBindInfoResponse queryBankCardBindInfoResponse = this.getBankCardBindInfo(bankCardBindInfoRequest);
        if (queryBankCardBindInfoResponse != null && queryBankCardBindInfoResponse.getBankCardInfoDTOList() != null) {
            for (BankCardInfoDTO bankCardInfoDTO : queryBankCardBindInfoResponse.getBankCardInfoDTOList()) {
                if (bankCardInfoDTO.getAccount_number().equals(cusBankCardBindRequest.getAccountNumber())) {
                	bankCardInfoDTOExist = bankCardInfoDTO;
                	break;
                }
            }
        }
        if(bankCardInfoDTOExist==null){//1.2未绑过，则新增
            if(cusBankCardBindRequest.getUserType().equals("1")){//公司类型为企业，则绑定对公
            	cusBankCardBindRequest.setAccountProperty("1");
            }else{//对私
            	cusBankCardBindRequest.setAccountProperty("2");
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
            log.info("绑定卡的参数bankCardBindRequest:"+JSONSerializer.toJSON(bankCardBindRequest));
            BankCardBindResponse bankCardBindResponse = rsBankCardInfoManager.bindBankCard(bankCardBindRequest);
            if (CommonConstant.OPERATE_SUCCESS.equals(bankCardBindResponse.getOperateStatus())) {
            	cusBankCardBindResponse.putSuccess("绑卡成功");
            }else{
            	cusBankCardBindResponse.putErrorResult(bankCardBindResponse.getReturnMsg());
            }  
            return cusBankCardBindResponse;
        }else{//如果融数已经有绑这个卡号，则检查该卡信息和录入信息是否一致
        	if(!bankCardInfoDTOExist.getBankhead().equals(cusBankCardBindRequest.getBankCode())){
        		cusBankCardBindResponse.putErrorResult("开户银行不正确");
            	return cusBankCardBindResponse;
        	}
        }
        
        log.info(cusBankCardBindRequest.getUserId()+",卡号:"+cusBankCardBindRequest.getAccountNumber()+",融数返回的绑卡状态："+bankCardInfoDTOExist.getStatus());
        //1.1 绑过，则修改
        if(bankCardInfoDTOExist.getStatus().equals("4")){//审核失败
        	if(cusBankCardBindRequest.getUserType().equals("1")){//1.1.2 对公 修改
        		ModifyInvalidWithDrawCardRequest modifyInvalidWithDrawCardRequest = new ModifyInvalidWithDrawCardRequest();
            	modifyInvalidWithDrawCardRequest.setAccountnumber(cusBankCardBindRequest.getAccountNumber());
            	modifyInvalidWithDrawCardRequest.setAccountrealname(cusBankCardBindRequest.getAccountName());
            	modifyInvalidWithDrawCardRequest.setHankheadname(cusBankCardBindRequest.getBankHeadName());
            	modifyInvalidWithDrawCardRequest.setBankhead(cusBankCardBindRequest.getBankCode());
            	modifyInvalidWithDrawCardRequest.setUserid(cusBankCardBindRequest.getUserId());
            	modifyInvalidWithDrawCardRequest.setBankcity(cusBankCardBindRequest.getBankCity());
            	modifyInvalidWithDrawCardRequest.setBankprovinec(cusBankCardBindRequest.getBankProvince());
            	modifyInvalidWithDrawCardRequest.setHankbranch(cusBankCardBindRequest.getBankBranch());
            	modifyInvalidWithDrawCardRequest.setUsertype("1");//对公
            	ModifyInvalidWithDrawCardResponse response = modifyinvalidPublicCard(modifyInvalidWithDrawCardRequest);
            	if (response.isResult()) {
                	cusBankCardBindResponse.putSuccess("绑卡成功");
                }else{
                	log.error("绑卡时，修改对公银行卡信息失败，卡号："+cusBankCardBindRequest.getAccountNumber()+",userid:"+cusBankCardBindRequest.getUserId()+",绑卡参数cusBankCardBindRequest："+Tools.gsonToString(cusBankCardBindRequest));
                	cusBankCardBindResponse.putErrorResult(response.getReturnCode(),response.getReturnMessage());
                }
            	return cusBankCardBindResponse;
        	}else{//1.1.1对私 ，则表示成功;如果不成功，那么需要人工处理
        		log.error("融数的对私绑卡状态失败,卡号："+cusBankCardBindRequest.getAccountNumber()+",userid:"+cusBankCardBindRequest.getUserId()+",绑卡参数cusBankCardBindRequest："+Tools.gsonToString(cusBankCardBindRequest));
        		cusBankCardBindResponse.putErrorResult("对私绑卡失败，请联系技术处理");
        		return cusBankCardBindResponse;
        	}
        }else{
        	cusBankCardBindResponse.putSuccess("绑卡信息正常");
        	return cusBankCardBindResponse;
        }
		
	}
	
	
    @Override
    public CusBankCardBindResponse bankCardBind(CusBankCardBindRequest cusBankCardBindRequest) {
        CusBankCardBindResponse cusBankCardBindResponse = new CusBankCardBindResponse();
        try {
        	TitanBankcard newTitanBankcard = covertToTitanBankcard(cusBankCardBindRequest);
        	TitanBankcardParam param = new TitanBankcardParam();
    		param.setAccountnumber(cusBankCardBindRequest.getAccountNumber());
    		param.setUserid(cusBankCardBindRequest.getUserId());
    		TitanBankcard titanBankcard = titanBankcardDao.selectEntity(param);
        	if(titanBankcard!=null&&titanBankcard.getStatus().intValue()==Integer.valueOf(BankCardEnum.BankCardStatusEnum.NORMAL.getKey())){//成功的记录
        		if(!titanBankcard.getBankcode().equals(cusBankCardBindRequest.getBankCode())){
            		cusBankCardBindResponse.putErrorResult("开户银行不正确");
                	return cusBankCardBindResponse;
            	}
        		
        		cusBankCardBindResponse.putSuccess("该卡号已经绑卡成功");
        		cusBankCardBindResponse.setBankcardid(titanBankcard.getBankcardid());
        		return cusBankCardBindResponse;
        	}
        	
    		if(titanBankcard!=null){
    			newTitanBankcard.setBankcardid(titanBankcard.getBankcardid());
    		}
            cusBankCardBindResponse = bindCardForRs(cusBankCardBindRequest);
            if (cusBankCardBindResponse.isResult()) {
                //本地保存绑卡记录
               cusBankCardBindResponse = saveBindCardRecord(true, newTitanBankcard, null);
               return cusBankCardBindResponse;
            }else{
            	//绑卡失败也要在本地保存绑卡失败记录
                saveBindCardRecord(false, newTitanBankcard, cusBankCardBindResponse.getReturnMessage());
                cusBankCardBindResponse.putErrorResult(cusBankCardBindResponse.getReturnMessage());
                return cusBankCardBindResponse;
            }
        } catch (Exception e) {
        	log.error("绑卡失败。异常响应信息：" + e.getMessage()+",绑卡参数:"+Tools.gsonToString(cusBankCardBindRequest), e);
        	cusBankCardBindResponse.putErrorResult("绑卡失败，请重试");
            return cusBankCardBindResponse;
        }
    }
    
    /**
     * @param isBindSuccess  提交到融数是否成功
     * @param titanBankcard
     * @param errorMsg
     * @return
     */
    
    private CusBankCardBindResponse saveBindCardRecord(boolean isBindSuccess, TitanBankcard 
    		titanBankcard, String errorMsg){
    	
    	CusBankCardBindResponse cusBankCardBindResponse = new CusBankCardBindResponse();
    	if(titanBankcard == null){
    		cusBankCardBindResponse.putErrorResult("保存绑卡记录失败 titanBankcard is null");
            return cusBankCardBindResponse;
    	}
    	
    	try {
    		if(isBindSuccess){
        		if(CommonConstant.ENTERPRISE.equals(titanBankcard.getAccountproperty())){
                	titanBankcard.setStatus(BindCardStatus.BIND_BINDING.status);//对公绑卡成功，还出于审核状态
                }else if(CommonConstant.PERSONAL.equals(titanBankcard.getAccountproperty())){
                	titanBankcard.setStatus(BindCardStatus.BIND_SUCCESS.status); //对私绑卡成功就直接记录成功
                }else{
                	log.error("绑卡类型错误  accountProperty：" + titanBankcard.getAccountproperty()+",参数titanBankcard："+Tools.gsonToString(titanBankcard));
                }
        	}else{
        		titanBankcard.setStatus(BindCardStatus.BIND_FAIL.status);
				titanBankcard.setRemark(errorMsg);
        	}
    		int bankcardid = 0;
    		if(titanBankcard.getBankcardid()!=null){
    			bankcardid = titanBankcard.getBankcardid();
    			titanBankcardDao.update(titanBankcard);
    		}else{
    			bankcardid = titanBankcardDao.insert(titanBankcard);
    		}
        	cusBankCardBindResponse.setBankcardid(bankcardid);
        } catch (Exception e) {
        	
            log.error("本地保存绑卡记录异常", e);
            cusBankCardBindResponse.putErrorResult("保存绑卡记录失败");
            return cusBankCardBindResponse;
        }
    	
    	cusBankCardBindResponse.putSuccess();
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
        organQueryRequest.setSubUserType(Integer.parseInt(CommonConstant.PERSONAL));
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
