package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Rop.api.DefaultRopClient;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.rsa.RSAUtil;
import com.fangcang.titanjr.dao.TitanSysConfigDao;
import com.fangcang.titanjr.dto.bean.RSInvokeConfig;
import com.fangcang.titanjr.dto.bean.TitanCallBackConfig;
import com.fangcang.titanjr.dto.bean.TitanPayMethod;
import com.fangcang.titanjr.entity.TitanSysConfig;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.titanjr.fop.api.DefaultFopClient;

/**o
 * Created by zhaoshan on 2016/4/8.
 */
public class RSInvokeInitManagerImpl {

    private static final Log log = LogFactory.getLog(RSInvokeInitManagerImpl.class);
    private final static String OBJ_KEY_RSINVOKECONFIG = "RSInvokeConfig";
	private final static String OBJ_KEY_TITANPAYMETHOD = "TitanPayMethod";
	private final static String OBJ_KEY_TITANCALLBACKCONFIG = "TitanCallBackConfig";
	private final static String OBJ_KEY_DEFAULTPAYERCONFIG = "DefaultPayerConfig";
	//融数上传zip资料rsa 加密key
	private final static String CFG_KEY_UPLOAD_FILE_RSA_PUBLIC_KEY = "upload_file_ras_public_key";
	private final static String AGENT_PAY_PROVIDER = "AGENT_PAY_PROVIDER";
	
	@Resource(name="titanSysConfigDao")
	private TitanSysConfigDao titanSysConfigDao;
	
    public void initRopClient() {
 
        RSInvokeConfig rSInvokeConfig = getRSInvokeConfig();
        if (rSInvokeConfig!=null) {
            String ropUrl = rSInvokeConfig.getInvokeURL();
            String fopUrl = rSInvokeConfig.getFopInvokeURL();
            String appKey = rSInvokeConfig.getAppKey();
            String appSecret = rSInvokeConfig.getAppSecret();
            RSInvokeConstant.fopClient = new DefaultFopClient(fopUrl, appKey, appSecret, "xml");
            RSInvokeConstant.ropClient = new DefaultRopClient(ropUrl, appKey, appSecret, "xml");
            RSInvokeConstant.defaultMerchant = rSInvokeConfig.getDefaultMerchant();
            RSInvokeConstant.defaultRoleId = rSInvokeConfig.getDefaultRoleId();
            log.info("------sdk init success, rSInvokeConfig:"+Tools.gsonToString(rSInvokeConfig));
        }else{
        	throw new RuntimeException("rong shu sdk init failed, param[rSInvokeConfig] is null");
        }
        //initSessionKey(RSInvokeConstant.ropClient);
        //用新的方式查询
        TitanPayMethod titanPayMethod = getTitanPayMethod();
        if (titanPayMethod!=null) {
            RSInvokeConstant.gateWayURL = titanPayMethod.getGatewayURL();
            RSInvokeConstant.rsCheckKey = titanPayMethod.getCheckKey();
            RSInvokeConstant.titanjrCheckKey = titanPayMethod.getTitanjrCheckKey();
            log.info("----------rong shu notify url init success, titanPayMethod:"+Tools.gsonToString(titanPayMethod));
        }else{
        	throw new RuntimeException("rong shu notify url init failed, param[titanPayMethod] is null");
        }
        log.info("当前构建的sessionkey是：" + RSInvokeConstant.sessionKey);
       
        getDefaultPayerConfig();
        initUploadFileRSAPublicKey();
        getAgentPayProvider();
    }
	/***
	 * 融数sdk初始化参数
	 * @return
	 */
	private RSInvokeConfig getRSInvokeConfig() {
		TitanSysConfig param = new TitanSysConfig();
		param.setObjKey(OBJ_KEY_RSINVOKECONFIG);
		List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		if(CollectionUtils.isNotEmpty(list)){
			RSInvokeConfig invokeConfig = new RSInvokeConfig();
			for(TitanSysConfig item : list){
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
				//fop接口地址
				if(item.getCfgKey().equals("FopInvokeConfig_invokeURL")){
					invokeConfig.setFopInvokeURL(item.getCfgValue());
					continue;
				}
				break;
			}
			return invokeConfig;
		}
		return null;
	}
	/**
	 * 读取融数中间账户
	 */
	private void getDefaultPayerConfig(){
		TitanSysConfig param = new TitanSysConfig();
		param.setObjKey(OBJ_KEY_DEFAULTPAYERCONFIG);
		List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		if(CollectionUtils.isNotEmpty(list)){
			for(TitanSysConfig item : list){
				if(item.getCfgKey().equals("DefaultPayerConfig_USERID")){
					RSInvokeConstant.DEFAULTPAYERCONFIG_USERID = item.getCfgValue();
					continue;
				}
				if(item.getCfgKey().equals("DefaultPayerConfig_PRODUCTID")){
					RSInvokeConstant.DEFAULTPAYERCONFIG_PRODUCTID = item.getCfgValue();
					continue;
				}
				break;
			}
		}else{
			log.error("OBJ_KEY_DEFAULTPAYERCONFIG 融数中间账户没有配置,请检查t_tfs_sysconfig表 ");
		}
	}
		
	/**
	 * 支付成功后，融数的通知泰坦云的地址
	 * @return
	 */
	private TitanPayMethod getTitanPayMethod() {
		TitanSysConfig param = new TitanSysConfig();
		param.setObjKey(OBJ_KEY_TITANPAYMETHOD);
		List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		if(CollectionUtils.isNotEmpty(list)){
			TitanPayMethod payMethod = new TitanPayMethod();
			for(TitanSysConfig item : list){
				if(item.getCfgKey().equals("TitanPayMethod_gatewayURL")){
					//payMethod.setGatewayURL(item.getCfgValue());
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
				//checkstand支付网关
				if(item.getCfgKey().equals("CSPayMethod_gatewayURL")){
					payMethod.setGatewayURL(item.getCfgValue());
					continue;
				}
				break;
			}
			return payMethod;
		}
		return null;
	}
	/**
	 * 金融系统处理成功后通知第三方地址
	 * @return
	 */
	public List<TitanCallBackConfig> getTitanCallBackConfig() {
		TitanSysConfig param = new TitanSysConfig();
		param.setObjKey(OBJ_KEY_TITANCALLBACKCONFIG);
		List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		List<TitanCallBackConfig> cList = new ArrayList<TitanCallBackConfig>();
		if(CollectionUtils.isNotEmpty(list)){
			for(TitanSysConfig item : list){
				TitanCallBackConfig callBackConfig = new TitanCallBackConfig();
				callBackConfig.setPaySource(item.getCfgKey().split("_")[1]);
				callBackConfig.setCallBackURL(item.getCfgValue());
				cList.add(callBackConfig);
			}
		}
		return cList;
	}
    
    /**
     * 初始化融数上传资料使用的rsa 公钥
     */
    private void initUploadFileRSAPublicKey(){
    	TitanSysConfig param = new TitanSysConfig();
    	param.setCfgKey(CFG_KEY_UPLOAD_FILE_RSA_PUBLIC_KEY);
    	List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		if(CollectionUtils.isNotEmpty(list)){
			RSAUtil.UPLOAD_FILE_RSA_PUBLIC_KEY = list.get(0).getCfgValue();
		}else{
			log.error("融数上传zip资料的RSA公钥没有在配置表中配置，参数key:"+CFG_KEY_UPLOAD_FILE_RSA_PUBLIC_KEY);
		}
		
    }
    private void getAgentPayProvider(){
    	TitanSysConfig param = new TitanSysConfig();
    	param.setCfgKey(AGENT_PAY_PROVIDER);
    	List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		if(CollectionUtils.isNotEmpty(list)){
			RSInvokeConstant.agent_pay_provider = list.get(0).getCfgValue();
		}else{
			log.error("代付渠道未配置，请先在数据库中配置");
		}
    	
    }
    
}
