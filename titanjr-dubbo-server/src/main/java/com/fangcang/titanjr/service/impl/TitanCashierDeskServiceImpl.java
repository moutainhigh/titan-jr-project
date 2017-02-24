package com.fangcang.titanjr.service.impl;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.BusTypeEnum;
import com.fangcang.titanjr.common.enums.CashierDeskTypeEnum;
import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;
import com.fangcang.titanjr.common.enums.SupportBankEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dao.TitanCashierDeskDao;
import com.fangcang.titanjr.dao.TitanCashierDeskItemDao;
import com.fangcang.titanjr.dao.TitanCashierItemBankDao;
import com.fangcang.titanjr.dao.TitanCommonPayMethodDao;
import com.fangcang.titanjr.dao.TitanOrgDao;
import com.fangcang.titanjr.dao.TitanRateConfigDao;
import com.fangcang.titanjr.dto.bean.CashierDeskDTO;
import com.fangcang.titanjr.dto.bean.CashierItemBankDTO;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.ItemBankDTO;
import com.fangcang.titanjr.dto.request.CashierDeskInitRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.PaymentItemRequest;
import com.fangcang.titanjr.dto.response.CashierDeskInitResponse;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.entity.TitanCashierDesk;
import com.fangcang.titanjr.entity.TitanCashierDeskItem;
import com.fangcang.titanjr.entity.TitanCashierItemBank;
import com.fangcang.titanjr.entity.TitanCommonPayMethod;
import com.fangcang.titanjr.entity.TitanOrg;
import com.fangcang.titanjr.entity.TitanRateConfig;
import com.fangcang.titanjr.entity.parameter.TitanCommonPayMethodParam;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/18.
 */
@Service("titanCashierDeskService")
public class TitanCashierDeskServiceImpl implements TitanCashierDeskService, Serializable {

    private static final Log log = LogFactory.getLog(TitanCashierDeskServiceImpl.class);

    @Resource
    TitanCashierDeskDao titanCashierDeskDao;

    @Resource
    TitanCashierDeskItemDao titanCashierDeskItemDao;

    @Resource
    TitanCashierItemBankDao titanCashierItemBankDao;
    
    @Resource
    TitanCommonPayMethodDao titanCommonPayMethodDao;

    @Resource
    TitanRateConfigDao titanRateConfigDao;

    @Resource
    TitanFinancialOrganService titanFinancialOrganService;
    
    @Resource 
    TitanOrgDao titanOrgDao;

    @Override
    public CashierDeskResponse queryCashierDesk(CashierDeskQueryRequest cashierDeskQueryRequest) {
        //需要补充accountHistory，账户是否需要密码等数据
        CashierDeskResponse deskResponse = new CashierDeskResponse();
        try {
            List<CashierDeskDTO> result = titanCashierDeskDao.queryCashierDesk(cashierDeskQueryRequest);
            if (CollectionUtils.isNotEmpty(result)) {
            	//设置收银台的顺序
                deskResponse.setCashierDeskDTOList(result);
                if (StringUtil.isValidString(cashierDeskQueryRequest.getPayerOrgCode())) {//验证付款方编码
                    FinancialOrganQueryRequest titanOrgQueryDTO = new FinancialOrganQueryRequest();
                    titanOrgQueryDTO.setMerchantcode(cashierDeskQueryRequest.getPayerOrgCode());
                    titanFinancialOrganService.queryFinancialOrgan(titanOrgQueryDTO);
                }
            }
            deskResponse.putSuccess();
        } catch (Exception e) {
            log.error("查询收银台异常", e);
            deskResponse.putSysError();
        }
        return deskResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public CashierDeskInitResponse initCashierDesk(CashierDeskInitRequest cashierDeskInitRequest) throws Exception {
        CashierDeskInitResponse deskInitResponse = new CashierDeskInitResponse();
        try {
            TitanCashierDesk b2bCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.B2B_DESK);
            TitanCashierDesk supplyCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.SUPPLY_DESK);
            TitanCashierDesk allianceCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.ALLIANCE_DESK);
            TitanCashierDesk rechargeCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.RECHARGE);
            TitanCashierDesk openOrgCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.OPEN_ORG);
            TitanCashierDesk ttMAllCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.TT_MALL);
            //批量插入初始化收银台
            titanCashierDeskDao.saveCashierDesk(b2bCashierDesk);
            titanCashierDeskDao.saveCashierDesk(supplyCashierDesk);
            titanCashierDeskDao.saveCashierDesk(allianceCashierDesk);
            titanCashierDeskDao.saveCashierDesk(rechargeCashierDesk);
            titanCashierDeskDao.saveCashierDesk(openOrgCashierDesk);
            titanCashierDeskDao.saveCashierDesk(ttMAllCashierDesk);
            

            //B2B的收银台有下面三个选项
            TitanCashierDeskItem b2bitem = buildCahsierDesk(b2bCashierDesk.getDeskid(), CashierItemTypeEnum.B2B_ITEM);
            TitanCashierDeskItem b2citem = buildCahsierDesk(b2bCashierDesk.getDeskid(), CashierItemTypeEnum.B2C_ITEM);
            TitanCashierDeskItem creditItem = buildCahsierDesk(b2bCashierDesk.getDeskid(), CashierItemTypeEnum.CREDIT_ITEM);
            TitanCashierDeskItem qritem =  buildCahsierDesk(b2bCashierDesk.getDeskid(), CashierItemTypeEnum.QR_ITEM);
            
            //分销商付款给供应商以及账单结算时有下面四个选项
            TitanCashierDeskItem b2bsitem = buildCahsierDesk(supplyCashierDesk.getDeskid(), CashierItemTypeEnum.B2B_ITEM);
            TitanCashierDeskItem b2csitem = buildCahsierDesk(supplyCashierDesk.getDeskid(), CashierItemTypeEnum.B2C_ITEM);
            TitanCashierDeskItem creditsitem = buildCahsierDesk(supplyCashierDesk.getDeskid(), CashierItemTypeEnum.CREDIT_ITEM);
            TitanCashierDeskItem balancesitem = buildCahsierDesk(supplyCashierDesk.getDeskid(), CashierItemTypeEnum.BALANCE_ITEM);
            TitanCashierDeskItem qrsitem =  buildCahsierDesk(supplyCashierDesk.getDeskid(), CashierItemTypeEnum.QR_ITEM);
            //联盟分销商付款给联盟供应商时有以下一个选项
            TitanCashierDeskItem balanceaitem = buildCahsierDesk(allianceCashierDesk.getDeskid(), CashierItemTypeEnum.BALANCE_ITEM);

            //账户充值的收银台有下面三个选项
            TitanCashierDeskItem b2britem = buildCahsierDesk(rechargeCashierDesk.getDeskid(), CashierItemTypeEnum.B2B_ITEM);
            TitanCashierDeskItem b2critem = buildCahsierDesk(rechargeCashierDesk.getDeskid(), CashierItemTypeEnum.B2C_ITEM);

            TitanCashierDeskItem openOrg2bitem = buildCahsierDesk(openOrgCashierDesk.getDeskid(), CashierItemTypeEnum.B2B_ITEM);
            TitanCashierDeskItem openOrg2citem = buildCahsierDesk(openOrgCashierDesk.getDeskid(), CashierItemTypeEnum.B2C_ITEM);
            TitanCashierDeskItem openOrgCritem = buildCahsierDesk(openOrgCashierDesk.getDeskid(), CashierItemTypeEnum.CREDIT_ITEM);
            TitanCashierDeskItem openOrgQritem = buildCahsierDesk(openOrgCashierDesk.getDeskid(), CashierItemTypeEnum.QR_ITEM);
           
            
            TitanCashierDeskItem ttMall2bitem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.B2B_ITEM);
            TitanCashierDeskItem ttMall2citem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.B2C_ITEM);
            TitanCashierDeskItem ttMallCritem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.CREDIT_ITEM);
            TitanCashierDeskItem ttMallQritem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.QR_ITEM);
            //批量插入初始化收银台子项
            titanCashierDeskItemDao.saveCashierDeskItem(b2bitem);
            titanCashierDeskItemDao.saveCashierDeskItem(b2citem);
            titanCashierDeskItemDao.saveCashierDeskItem(creditItem);
            titanCashierDeskItemDao.saveCashierDeskItem(b2bsitem);
            titanCashierDeskItemDao.saveCashierDeskItem(b2csitem);
            titanCashierDeskItemDao.saveCashierDeskItem(creditsitem);
            titanCashierDeskItemDao.saveCashierDeskItem(balancesitem);
            titanCashierDeskItemDao.saveCashierDeskItem(balanceaitem);
            titanCashierDeskItemDao.saveCashierDeskItem(b2britem);
            titanCashierDeskItemDao.saveCashierDeskItem(b2critem);
            titanCashierDeskItemDao.saveCashierDeskItem(qritem);
            titanCashierDeskItemDao.saveCashierDeskItem(qrsitem);
            
            titanCashierDeskItemDao.saveCashierDeskItem(openOrg2bitem);
            titanCashierDeskItemDao.saveCashierDeskItem(openOrg2citem);
            titanCashierDeskItemDao.saveCashierDeskItem(openOrgCritem);
            titanCashierDeskItemDao.saveCashierDeskItem(openOrgQritem);
            
            titanCashierDeskItemDao.saveCashierDeskItem(ttMall2bitem);
            titanCashierDeskItemDao.saveCashierDeskItem(ttMall2citem);
            titanCashierDeskItemDao.saveCashierDeskItem(ttMallCritem);
            titanCashierDeskItemDao.saveCashierDeskItem(ttMallQritem);
            //充值
         

            //默认初始化银行：
            List<TitanCashierItemBank> allItemBanks = new ArrayList<TitanCashierItemBank>();
            allItemBanks.addAll(buildItemBankList(b2bitem.getItemid(), "B2B"));
            allItemBanks.addAll(buildItemBankList(b2bsitem.getItemid(), "B2B"));
            allItemBanks.addAll(buildItemBankList(b2britem.getItemid(), "B2B"));
            allItemBanks.addAll(buildItemBankList(openOrg2bitem.getItemid(), "B2B"));
            allItemBanks.addAll(buildItemBankList(ttMall2bitem.getItemid(), "B2B"));
            

            allItemBanks.addAll(buildItemBankList(b2citem.getItemid(), "B2C"));
            allItemBanks.addAll(buildItemBankList(b2csitem.getItemid(), "B2C"));
            allItemBanks.addAll(buildItemBankList(b2critem.getItemid(), "B2C"));
            allItemBanks.addAll(buildItemBankList(openOrg2citem.getItemid(), "B2C"));
            allItemBanks.addAll(buildItemBankList(ttMall2citem.getItemid(), "B2C"));

            allItemBanks.addAll(buildItemBankList(creditItem.getItemid(), "Credit"));
            allItemBanks.addAll(buildItemBankList(creditsitem.getItemid(), "Credit"));
            allItemBanks.addAll(buildItemBankList(openOrgCritem.getItemid(), "Credit"));
            allItemBanks.addAll(buildItemBankList(ttMallCritem.getItemid(), "Credit"));
            
            
            //第三方支付
            allItemBanks.addAll(buildQRBankList(qritem.getItemid()));
            allItemBanks.addAll(buildQRBankList(qrsitem.getItemid()));
            allItemBanks.addAll(buildQRBankList(openOrgQritem.getItemid()));
            allItemBanks.addAll(buildQRBankList(ttMallQritem.getItemid()));
            

            
            titanCashierItemBankDao.batchSaveItemBanks(allItemBanks);

            //默认初始化费率设置
            List<TitanRateConfig> rateConfigList = new ArrayList<TitanRateConfig>();
            rateConfigList.add(bulidPayRateConfig(BusTypeEnum.B2B_RATE, cashierDeskInitRequest.getUserId(), "企业网银支付费率"));
            rateConfigList.add(bulidPayRateConfig(BusTypeEnum.B2C_RATE, cashierDeskInitRequest.getUserId(), "个人网银支付费率"));
            rateConfigList.add(bulidPayRateConfig(BusTypeEnum.CREDIT_RATE, cashierDeskInitRequest.getUserId(), "信用卡网银支付费率"));

            rateConfigList.add(bulidPayRateConfig(BusTypeEnum.QR_RATE, cashierDeskInitRequest.getUserId(), "第三方支付费率"));
            rateConfigList.add(bulidPayRateConfig(BusTypeEnum.WITHDRAW_RATE, cashierDeskInitRequest.getUserId(), "账户提现费率"));

            titanRateConfigDao.batchSaveRateConfigs(rateConfigList);
            deskInitResponse.putSuccess();
        } catch (Exception e) {
            log.error("初始化收银台数据异常", e);
            deskInitResponse.putSysError();
        }
        return deskInitResponse;
    }

    
    private TitanRateConfig bulidPayRateConfig(BusTypeEnum bustype, String userId, String desc) {
    	 TitanRateConfig rateConfig = new TitanRateConfig();
    	 rateConfig.setBustype(bustype.type);//1表示付款费率
         rateConfig.setDescription(desc);
         if(bustype.isB2B()){
        	 rateConfig.setRatetype(2);//按笔收费
        	 rateConfig.setRsrate(10f);//千分之一点五
             rateConfig.setStandrate(10f);
             rateConfig.setExecutionrate(0f);
        	 
         }else if(bustype.isB2C()){
           	 rateConfig.setRatetype(1);//按百分比
        	 rateConfig.setRsrate(0.2f);//千分之一点五
             rateConfig.setStandrate(0.3f);
             rateConfig.setExecutionrate(0f);
        	 
         }else if(bustype.isCREDIT()){
        	 rateConfig.setRatetype(1);//按百分比
        	 rateConfig.setRsrate(0.2f);//千分之一点五
             rateConfig.setStandrate(0.3f);
             rateConfig.setExecutionrate(0f);
        	 
         }else if(bustype.isQR()){
        	 rateConfig.setRatetype(1);//按百分比
        	 rateConfig.setRsrate(0.4f);//千分之一点五
             rateConfig.setStandrate(0.4f);
             rateConfig.setExecutionrate(0f);
        	 
         }else if(bustype.isWITHDRAW()){
             rateConfig.setRatetype(2);//按笔收费
             rateConfig.setRsrate(3f);//每笔3元
             rateConfig.setStandrate(5f);//每笔5元
             rateConfig.setExecutionrate(0f);
         }
         rateConfig.setUserid(userId);
         rateConfig.setCreator("system");
         rateConfig.setCreatetime(new Date());
         rateConfig.setExpiration(DateUtil.getDate(new Date(), 6));//默认6个月
    	
    	return rateConfig;
    }
    
    private List<TitanCashierItemBank> buildItemBankList(Integer bankItemId, String type) {
        List<TitanCashierItemBank> result = new ArrayList<TitanCashierItemBank>();
        for (SupportBankEnum bankEnum : SupportBankEnum.values()) {
            if (bankEnum.bankType.equals(type)) {
                TitanCashierItemBank bank = new TitanCashierItemBank();
                bank.setItemid(bankItemId);
                bank.setBankmark(bankEnum.bankRemark);
                bank.setBankname(bankEnum.bankName);
                bank.setCreator("system");
                bank.setCreatetime(new Date());
                result.add(bank);
            }
        }
        return result;
    }
    
    private List<TitanCashierItemBank> buildQRBankList(Integer bankItemId){
    	List<TitanCashierItemBank> result = new ArrayList<TitanCashierItemBank>();
    	TitanCashierItemBank wx = new TitanCashierItemBank();
    	wx.setItemid(bankItemId);
    	wx.setBankmark("微信");
    	wx.setBankname("wx");
    	wx.setCreator("system");
    	wx.setCreatetime(new Date());
        result.add(wx);
        TitanCashierItemBank alipay = new TitanCashierItemBank();
        alipay.setItemid(bankItemId);
        alipay.setBankmark("支付宝");
        alipay.setBankname("alipay");
        alipay.setCreator("system");
        alipay.setCreatetime(new Date());
        result.add(alipay);
        return result;
    }

    private TitanCashierDesk buildCahsierDesk(CashierDeskInitRequest cashierDeskInitRequest, CashierDeskTypeEnum deskType) {
        TitanCashierDesk desk = new TitanCashierDesk();
        desk.setUserid(cashierDeskInitRequest.getUserId());
        desk.setCreator(cashierDeskInitRequest.getOperator());
        desk.setCreatetime(new Date());
        desk.setConstid(cashierDeskInitRequest.getConstId());
        desk.setPaytype(1);
        desk.setUsedfor(Integer.valueOf(deskType.deskCode));
        desk.setDeskname(deskType.deskName + "收银台");
        return desk;
    }

    private TitanCashierDeskItem buildCahsierDesk(Integer deskId, CashierItemTypeEnum deskItemType) {
        TitanCashierDeskItem deskItem = new TitanCashierDeskItem();
        deskItem.setDeskid(deskId);
        deskItem.setItemtype(Integer.valueOf(deskItemType.itemCode));
        deskItem.setItemname(deskItemType.itemName);
        deskItem.setCreator("system");
        deskItem.setCreatetime(new Date());
        return deskItem;
    }

    @Override
    public CashierItemBankDTO queryCashierItemBankDTOByBankName(String bankName) {
        try {
            List<TitanCashierItemBank> titanCashierItemBankList = titanCashierItemBankDao.queryCashierItemBankDTOByBankName(bankName);
            if (titanCashierItemBankList != null && titanCashierItemBankList.size() > 0) {
                return converToCashierItemBankDTO(titanCashierItemBankList.get(0));
            }

        } catch (Exception e) {
            log.error("查询银行信息失败" + e.getMessage(), e);
        }
        return null;
    }

    private CashierItemBankDTO converToCashierItemBankDTO(TitanCashierItemBank titanCashierItemBank) {
        if (titanCashierItemBank != null) {
            CashierItemBankDTO cashierItemBankDTO = new CashierItemBankDTO();
            cashierItemBankDTO.setBankImage(titanCashierItemBank.getBankimage());
            cashierItemBankDTO.setBankMark(titanCashierItemBank.getBankmark());
            cashierItemBankDTO.setBankName(cashierItemBankDTO.getBankName());
            return cashierItemBankDTO;
        }
        return null;
    }

	@Override
	public boolean saveCommonPayMethod(CommonPayMethodDTO commonPayMethodDTO) {
		if(commonPayMethodDTO !=null && StringUtil.isValidString(commonPayMethodDTO.getBankname())
				 && commonPayMethodDTO.getDeskid()!=null
				 && commonPayMethodDTO.getPaytype()!=null){
			//更新还是插入，根据deskId,bankName,payType判断
			TitanCommonPayMethodParam condition = new TitanCommonPayMethodParam();
			condition.setBankname(commonPayMethodDTO.getBankname());
			condition.setDeskid(commonPayMethodDTO.getDeskid());
			condition.setPaytype(commonPayMethodDTO.getPaytype());
			
			PaginationSupport<TitanCommonPayMethod> paginationSupport = new PaginationSupport<TitanCommonPayMethod>();
			titanCommonPayMethodDao.selectForPage(condition, paginationSupport);
			//判断该deskId,bankMark,payType只对应一条记录
			if(paginationSupport.getItemList()!=null && paginationSupport.getItemList().size()!=0 ){
			   if(paginationSupport.getItemList().size()==1){
				   TitanCommonPayMethod titanCommonPayMethod = paginationSupport.getItemList().get(0);
				   if(paginationSupport.getItemList().get(0).getCount() !=null){
					   titanCommonPayMethod.setCount(titanCommonPayMethod.getCount()+1);
				   }
				  return titanCommonPayMethodDao.update(titanCommonPayMethod)>0?true:false;
			   }else{
				   log.error("常用的的支付方式错误"+"deskId:"+commonPayMethodDTO.getDeskid());
			   }
			}else{
				TitanCommonPayMethod titanCommonPayMethod = new TitanCommonPayMethod();
				MyBeanUtil.copyProperties(titanCommonPayMethod, commonPayMethodDTO);
				titanCommonPayMethod.setCount(1);
				return titanCommonPayMethodDao.insert(titanCommonPayMethod)>0?true:false;
			}
		}
		return false;
	}

	@Override
	public List<CommonPayMethodDTO> queryCommonPayMethod(
			CashierDeskQueryRequest cashierDeskQueryRequest) {
		try{
			return titanCommonPayMethodDao.queryCommomPayMethod(cashierDeskQueryRequest);
		}catch(Exception e){
			 log.error("查询常用支付方式失败" + e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public void initttMallCashDesk(){
		//查询出所以的userID
		 CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest();
		 FinancialOrganQueryRequest organ = new FinancialOrganQueryRequest();
		 organ.setStatusId(1);
		 List<TitanOrg> orgList = titanOrgDao.queryTitanOrgList(organ);
		 for(TitanOrg org :orgList){
			 cashierDeskInitRequest.setConstId(CommonConstant.RS_FANGCANG_CONST_ID);
			 cashierDeskInitRequest.setUserId(org.getUserid());
			 this.initDesk(cashierDeskInitRequest);
		 }
	}
	
	private void initDesk(CashierDeskInitRequest cashierDeskInitRequest){
		try{
			//首先添加CashDesk
			 TitanCashierDesk ttMAllCashierDesk = this.buildCahsierDesk(cashierDeskInitRequest, CashierDeskTypeEnum.TT_MALL);
	        //批量插入初始化收银台
	        titanCashierDeskDao.saveCashierDesk(ttMAllCashierDesk);
	        TitanCashierDeskItem ttMall2bitem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.B2B_ITEM);
	        TitanCashierDeskItem ttMall2citem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.B2C_ITEM);
	        TitanCashierDeskItem ttMallCritem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.CREDIT_ITEM);
	        TitanCashierDeskItem ttMallQritem = buildCahsierDesk(ttMAllCashierDesk.getDeskid(), CashierItemTypeEnum.QR_ITEM);
	        //批量插入初始化收银台子项
	      
	        titanCashierDeskItemDao.saveCashierDeskItem(ttMall2bitem);
	        titanCashierDeskItemDao.saveCashierDeskItem(ttMall2citem);
	        titanCashierDeskItemDao.saveCashierDeskItem(ttMallCritem);
	        titanCashierDeskItemDao.saveCashierDeskItem(ttMallQritem);

	        //默认初始化银行：
	        List<TitanCashierItemBank> allItemBanks = new ArrayList<TitanCashierItemBank>();
	        allItemBanks.addAll(buildItemBankList(ttMall2bitem.getItemid(), "B2B"));
	        allItemBanks.addAll(buildItemBankList(ttMall2citem.getItemid(), "B2C"));
	        allItemBanks.addAll(buildItemBankList(ttMallCritem.getItemid(), "Credit"));
	        allItemBanks.addAll(buildQRBankList(ttMallQritem.getItemid()));

	        titanCashierItemBankDao.batchSaveItemBanks(allItemBanks);
		}catch(Exception e){
			log.error("初始化收银台失败:"+cashierDeskInitRequest.getUserId());
		}
	}

	@Override
	public <T> void addModelOfPayment(PaymentItemRequest<T> request) {
		if(null == request || !StringUtil.isValidString(request.getType()) ||request.getItem()==null){
			log.error("增加支付选项参数错误");
			return;
		}
		
		TitanCashierDeskItem item = new TitanCashierDeskItem();
		item.setItemtype(Integer.parseInt(request.getType()));
		List<TitanCashierDeskItem> items = titanCashierDeskItemDao.queryCashierDeskItems(item);
		List<TitanCashierItemBank> banks = new ArrayList<TitanCashierItemBank>();
		if(request.getItem() instanceof ItemBankDTO)
		{
			ItemBankDTO bankDTO  = (ItemBankDTO)request.getItem();
			for(TitanCashierDeskItem itm : items)
			{
				TitanCashierItemBank bank = new TitanCashierItemBank();
				MyBeanUtil.copyProperties(bank, bankDTO);
				bank.setItemid(itm.getItemid());
				banks.add(bank);
			}
		}
		titanCashierItemBankDao.batchSaveItemBanks(banks);
	}
	
}
