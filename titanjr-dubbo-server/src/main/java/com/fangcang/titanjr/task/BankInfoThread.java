package com.fangcang.titanjr.task;

import java.util.Date;
import java.util.List;

import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dao.TitanBankinfoDao;
import com.fangcang.titanjr.entity.TitanBankinfo;
import com.fangcang.titanjr.rs.dto.BankInfo;
import com.fangcang.titanjr.rs.manager.BaseInfoInitManager;
import com.fangcang.titanjr.rs.response.BankInfoResponse;
import com.fangcang.util.MyBeanUtil;

public class BankInfoThread implements Runnable {

	private List<String> cityCodes;
	private BaseInfoInitManager baseInfoInitManager;
	private String bankCode;
	private List<TitanBankinfo> titanBankInfoList;
	private TitanBankinfoDao titanBankinfoDao;
	public BankInfoThread(List<String> cityCodes,BaseInfoInitManager baseInfoInitManager,
			              String bankCode,List<TitanBankinfo> titanBankInfoList,TitanBankinfoDao titanBankinfoDao){
		this.cityCodes=cityCodes;
		this.baseInfoInitManager = baseInfoInitManager;
		this.bankCode = bankCode;
		this.titanBankInfoList = titanBankInfoList;
		this.titanBankinfoDao = titanBankinfoDao;
	}
	
	@Override
	public void run() {
		int j=0;
		for(String cityCode:cityCodes){
			BankInfoResponse bankInfoResponse = baseInfoInitManager.getRSBranchBankInfo(bankCode,cityCode);
			if(bankInfoResponse !=null && bankInfoResponse.getBankInfos()!=null){
				 List<BankInfo> bankInfos = bankInfoResponse.getBankInfos();
				 if(bankInfos !=null && bankInfos.size()>0){
					 int k =0;
					 //循环遍历支行信息
					for(int i = 0;i<bankInfos.size();i++){
						k++;
						if(bankInfos.get(i) !=null){
							TitanBankinfo titanBankinfo = new TitanBankinfo();
							MyBeanUtil.copyBeanProperties(titanBankinfo,bankInfos.get(i));
							titanBankinfo.setCreatetime(new Date());
							titanBankInfoList.add(titanBankinfo);
						}
						if(k==CommonConstant.INSERT_BATCH_NUM || i==bankInfos.size()){
							titanBankinfoDao.insertBatch(titanBankInfoList);
							k=0;
							titanBankInfoList.clear();
						}
					}
				 }
			}
		}
	}

}
