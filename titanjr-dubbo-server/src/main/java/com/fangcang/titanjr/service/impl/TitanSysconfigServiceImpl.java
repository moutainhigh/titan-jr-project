package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dao.TitanSysconfigDao;
import com.fangcang.titanjr.dto.bean.CallBackConfig;
import com.fangcang.titanjr.dto.bean.InvokeConfig;
import com.fangcang.titanjr.dto.bean.PayMethod;
import com.fangcang.titanjr.dto.request.RSInvokeConfigRequest;
import com.fangcang.titanjr.dto.request.TitanCallBackConfigRequest;
import com.fangcang.titanjr.dto.request.TitanPayMethodRequest;
import com.fangcang.titanjr.dto.response.RSInvokeConfigResponse;
import com.fangcang.titanjr.dto.response.TitanCallBackConfigResponse;
import com.fangcang.titanjr.dto.response.TitanPayMethodResponse;
import com.fangcang.titanjr.entity.TitanSysconfig;
import com.fangcang.titanjr.entity.parameter.TitanSysconfigParam;
import com.fangcang.titanjr.service.TitanSysconfigService;

@Service("titanSysconfigService")
public class TitanSysconfigServiceImpl implements TitanSysconfigService {
	private final static String OBJ_KEY_RSINVOKECONFIG = "RSInvokeConfig";
	private final static String OBJ_KEY_TITANPAYMETHOD = "TitanPayMethod";
	private final static String OBJ_KEY_TITANCALLBACKCONFIG = "TitanCallBackConfig";
	
	@Resource
	private TitanSysconfigDao titanSysconfigDao;
	
	
	@Override
	public RSInvokeConfigResponse getRSInvokeConfig(RSInvokeConfigRequest request) {
		RSInvokeConfigResponse response = new RSInvokeConfigResponse();
		TitanSysconfigParam titanSysconfigParam = new TitanSysconfigParam();
		titanSysconfigParam.setObjKey(OBJ_KEY_RSINVOKECONFIG);
		List<TitanSysconfig> list = titanSysconfigDao.query(titanSysconfigParam);
		if(CollectionUtils.isNotEmpty(list)){
			InvokeConfig invokeConfig = new InvokeConfig();
			for(TitanSysconfig item : list){
				if(item.getCfgKey().equals("RSInvokeConfig_appKey")){
					invokeConfig.setAppKey(item.getCfgValue());
					continue;
				}
				if(item.getCfgKey().equals("RSInvokeConfig_appSecret")){
					invokeConfig.setAppSecret(item.getCfgValue());
					continue;
				}
				if(item.getCfgKey().equals("RSInvokeConfig_invokeURL")){
					invokeConfig.setInvokeURL(item.getCfgValue());
					continue;
				}
				if(item.getCfgKey().equals("RSInvokeConfig_defaultMerchant")){
					invokeConfig.setDefaultMerchant(item.getCfgValue());
					continue;
				}
				if(item.getCfgKey().equals("RSInvokeConfig_defaultRoleId")){
					invokeConfig.setDefaultRoleId(NumberUtils.toLong(item.getCfgValue()));
					continue;
				}
			}
			response.setInvokeConfig(invokeConfig);
		}
		response.setResult(true);
		return response;
	}

	@Override
	public TitanPayMethodResponse getTitanPayMethod(
			TitanPayMethodRequest request) {
		TitanPayMethodResponse response = new TitanPayMethodResponse();
		TitanSysconfigParam titanSysconfigParam = new TitanSysconfigParam();
		titanSysconfigParam.setObjKey(OBJ_KEY_TITANPAYMETHOD);
		List<TitanSysconfig> list = titanSysconfigDao.query(titanSysconfigParam);
		if(CollectionUtils.isNotEmpty(list)){
			PayMethod payMethod = new PayMethod();
			for(TitanSysconfig item : list){
				if(item.getCfgKey().equals("TitanPayMethod_gatewayURL")){
					payMethod.setGatewayURL(item.getCfgValue());
					continue;
				}
				if(item.getCfgKey().equals("TitanPayMethod_checkKey")){
					payMethod.setCheckKey(item.getCfgValue());
					continue;
				}
				if(item.getCfgKey().equals("TitanPayMethod_titanjrCheckKey")){
					payMethod.setTitanjrCheckKey(item.getCfgValue());
					continue;
				}
			}
			response.setPayMethod(payMethod);
		}
		response.setResult(true);
		return response;
	}

	@Override
	public TitanCallBackConfigResponse getTitanCallBackConfig(
			TitanCallBackConfigRequest request) {
		TitanCallBackConfigResponse response = new TitanCallBackConfigResponse();
		TitanSysconfigParam titanSysconfigParam = new TitanSysconfigParam();
		titanSysconfigParam.setObjKey(OBJ_KEY_TITANCALLBACKCONFIG);
		List<TitanSysconfig> list = titanSysconfigDao.query(titanSysconfigParam);
		List<CallBackConfig> cList = new ArrayList<CallBackConfig>();
		if(CollectionUtils.isNotEmpty(list)){
			for(TitanSysconfig item : list){
				CallBackConfig callBackConfig = new CallBackConfig();
				callBackConfig.setPaySource(item.getCfgKey().split("_")[1]);
				callBackConfig.setCallBackURL(item.getCfgValue());
				cList.add(callBackConfig);
			}
		}
		response.setCallBackConfigList(cList);
		response.setResult(true);
		return response;
	}
	
}
