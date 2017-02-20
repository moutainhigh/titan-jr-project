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

	private String cityCode;
	private BaseInfoInitManager baseInfoInitManager;
	private String bankCode;
	private List<TitanBankinfo> titanBankInfoList;
	private TitanBankinfoDao titanBankinfoDao;
	public BankInfoThread(String cityCode,BaseInfoInitManager baseInfoInitManager,
			              TitanBankinfoDao titanBankinfoDao){
		this.cityCode=cityCode;
		this.baseInfoInitManager = baseInfoInitManager;
		this.titanBankinfoDao = titanBankinfoDao;
	}
	
	@Override
	public void run() {
		BankInfoResponse bankInfoResponse = baseInfoInitManager
				.getRSBranchBankInfo(null, cityCode);
		if (bankInfoResponse != null && bankInfoResponse.getBankInfos() != null) {
			List<BankInfo> bankInfos = bankInfoResponse.getBankInfos();
			if (bankInfos != null && bankInfos.size() > 0) {
				// 循环遍历支行信息
				for (int i = 0; i < bankInfos.size(); i++) {
					if (bankInfos.get(i) != null) {
						TitanBankinfo titanBankinfo = new TitanBankinfo();
						MyBeanUtil.copyBeanProperties(titanBankinfo,
								bankInfos.get(i));
						titanBankinfo.setCreatetime(new Date());
						titanBankinfoDao.insert(titanBankinfo);
					}
				}
			}
		}
	}

}
