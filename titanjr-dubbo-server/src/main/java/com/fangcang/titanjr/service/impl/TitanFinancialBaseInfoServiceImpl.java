package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.fangcang.titanjr.common.enums.FinancialRoleEnum;
import com.fangcang.titanjr.common.enums.MunicipalityEnum;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.ThreadPoolUtil;
import com.fangcang.titanjr.dao.TitanBankinfoDao;
import com.fangcang.titanjr.dao.TitanCityInfoDao;
import com.fangcang.titanjr.dao.TitanRoleDao;
import com.fangcang.titanjr.dto.bean.BankInfoDTO;
import com.fangcang.titanjr.dto.request.BankInfoQueryRequest;
import com.fangcang.titanjr.dto.response.BankInfoInitResponse;
import com.fangcang.titanjr.dto.response.CityInfoInitResponse;
import com.fangcang.titanjr.dto.response.OrganRoleInitResponse;
import com.fangcang.titanjr.entity.TitanBankinfo;
import com.fangcang.titanjr.entity.TitanCityInfo;
import com.fangcang.titanjr.entity.TitanRole;
import com.fangcang.titanjr.rs.dto.BankInfo;
import com.fangcang.titanjr.rs.dto.CityInfo;
import com.fangcang.titanjr.rs.manager.BaseInfoInitManager;
import com.fangcang.titanjr.rs.response.BankInfoResponse;
import com.fangcang.titanjr.rs.response.CityInfoResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import com.fangcang.titanjr.task.BankInfoThread;
import com.fangcang.util.MyBeanUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("titanFinancialBaseInfoService")
public class TitanFinancialBaseInfoServiceImpl implements TitanFinancialBaseInfoService {

	private static final Log log = LogFactory.getLog(TitanFinancialBankCardServiceImpl.class);

	@Resource
	BaseInfoInitManager baseInfoInitManager;

	@Resource
	TitanCityInfoDao titanCityInfoDao;
	
	@Resource
	TitanBankinfoDao titanBankinfoDao;

	@Resource
	TitanRoleDao titanRoleDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	public OrganRoleInitResponse initFinancialOrganRole() {
		OrganRoleInitResponse response = new OrganRoleInitResponse();
		List<TitanRole> roleList = new ArrayList<TitanRole>();
		for (FinancialRoleEnum roleEnum : FinancialRoleEnum.values()){
			TitanRole role = new TitanRole();
			role.setFcroleid(RSInvokeConstant.defaultRoleId);
			role.setRolecode(roleEnum.roleCode);
			role.setRolename(roleEnum.roleName);
			role.setIsactive(1);
			role.setRoleremark("");
			role.setCreator("system");
			role.setCreatetime(new Date());
			roleList.add(role);
		}
		try {
			int result =  titanRoleDao.batchSaveRoles(roleList);
			if (roleList.size() == result){
				response.setResult(true);
				response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
				response.setReturnMessage(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
			}
		} catch (Exception e) {

		}
		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public CityInfoInitResponse saveRSCityInfo() throws Exception {
		CityInfoInitResponse cityInfoInitResponse = new CityInfoInitResponse();
		try {
			//查询省信息
			CityInfoResponse rsProvinceInfo = baseInfoInitManager
					.getRSProvinceInfo();
			if (rsProvinceInfo != null && rsProvinceInfo.getCityInfos() != null) {
				//插入之前先删除原有的数据
				titanCityInfoDao.deleteTitanCitys();
				
				List<CityInfo> rsProvinceList = rsProvinceInfo.getCityInfos();
				// 省份信息循环
				if (rsProvinceList != null && rsProvinceList.size() > 0) {
					
					//保存省级信息
					List<TitanCityInfo> proInfo = new ArrayList<TitanCityInfo>();
					for(int i = 0; i < rsProvinceList.size(); i++){
						TitanCityInfo rsProvince = convertCityInfo2TitanCityInfo(rsProvinceList.get(i));
						proInfo.add(rsProvince);
					}
					//保存省份信息
					titanCityInfoDao.insertBatch(proInfo);
					
					//保存市级信息
					for (int i = 0; i < rsProvinceList.size(); i++) {
						if (rsProvinceList.get(i) != null) {
							// 插入省份信息
							TitanCityInfo rsProvince = convertCityInfo2TitanCityInfo(rsProvinceList.get(i));
							// 获取省份下面的城市信息,并且排除全国
							if((CommonConstant.COUNTRY_CODE).equals(rsProvinceList.get(i).getCitycode())){
								continue;
							}else{
								
								CityInfoResponse rsCityInfo = baseInfoInitManager
										.getRSCityInfo(rsProvinceList.get(i)
												.getCitycode());
								List<CityInfo> cityInfoList = rsCityInfo.getCityInfos();
								List<TitanCityInfo> titanCityInfos = new ArrayList<TitanCityInfo>();
								//计数器，记录省份下面的城市的个数，每满100条数据批量插入一次
								int k = 0;
								//城市信息不为空则循环批量插入城市信息
								if (cityInfoList != null && cityInfoList.size() > 0) {
									for (int j = 0; j < cityInfoList.size(); j++) {
										k++;
										if (cityInfoList.get(j) != null) {
											TitanCityInfo titanCity = convertCityInfo2TitanCityInfo(cityInfoList
													.get(j));
											titanCityInfos.add(titanCity);
										}
										// 执行一次批量插入
										if ((k == CommonConstant.INSERT_BATCH_NUM ||j==(cityInfoList.size()-1))&&titanCityInfos.size()>0) {
											titanCityInfoDao.insertBatch(titanCityInfos);
											titanCityInfos.clear();
											k = 0;
										}
									}
								}
							}
						}
					}
				}
			}else{
				cityInfoInitResponse.setResult(false);
				cityInfoInitResponse.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				cityInfoInitResponse.setReturnMessage(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
			cityInfoInitResponse.setResult(true);
			cityInfoInitResponse.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
			cityInfoInitResponse.setReturnMessage(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return cityInfoInitResponse;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public BankInfoInitResponse saveRSBankInfo() throws Exception {
		BankInfoInitResponse bankInfoInitResponse = new BankInfoInitResponse();
		try{
			//删除已有数据
			titanBankinfoDao.delete();
			//查询所有总行信息并批量插入
			List<String> bankCodes = getBankCode();
		   //查询所有城市的编码
			List<String> cityCodes = getCityCode();
			//循环遍历总行和城市信息
			if(bankCodes !=null && cityCodes!=null){
				List<TitanBankinfo> titanBankInfoList = new ArrayList<TitanBankinfo>();
				for(String bankCode:bankCodes){
					BankInfoThread thread = new BankInfoThread(cityCodes, baseInfoInitManager, bankCode, titanBankInfoList, titanBankinfoDao);
					ThreadPoolUtil.excute(thread);
				}
			}else{
				bankInfoInitResponse.setResult(false);
				bankInfoInitResponse.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
				bankInfoInitResponse.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
		bankInfoInitResponse.setResult(true);
		bankInfoInitResponse.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
		bankInfoInitResponse.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
		//查询所有的城市编码
		return bankInfoInitResponse;
	}

	@Override
	public com.fangcang.titanjr.dto.response.BankInfoResponse queryBankInfoList(BankInfoQueryRequest bankInfoQueryRequest) {
		com.fangcang.titanjr.dto.response.BankInfoResponse bankInfoResponse =
				new com.fangcang.titanjr.dto.response.BankInfoResponse();
		try {
			List<BankInfoDTO> bankInfoDTOs = titanBankinfoDao.queryBankInfoList(bankInfoQueryRequest);
			bankInfoResponse.setBankInfoDTOList(bankInfoDTOs);
			bankInfoResponse.putSuccess();
		} catch (Exception e) {
			log.error("查询银行信息失败", e);
			bankInfoResponse.putErrorResult("查询银行信息失败");
		}
		return bankInfoResponse;
	}

	/**
	 * 将返回的 省市 信息 转换成本地的 省市 信息
	 * @param cityInfo
	 * @return
	 */
	private TitanCityInfo convertCityInfo2TitanCityInfo(CityInfo cityInfo) {
		if (cityInfo != null) {
			TitanCityInfo titanCityInfo = new TitanCityInfo();
			titanCityInfo.setCitycode(cityInfo.getCitycode());
			titanCityInfo.setCityname(cityInfo.getCityname());
			titanCityInfo.setCountry("CN");
			titanCityInfo.setParentcode(cityInfo.getParentcode());
			titanCityInfo.setDatatype(cityInfo.getDatatype());
			titanCityInfo.setCreatetime(new Date());
			return titanCityInfo;
		}
		return null;
	}
	
	
	/**
	 * 获取总行的编码
	 * @return
	 * @throws Exception
	 * @author fangdaikang
	 */
	private List<String> getBankCode() throws Exception{
		List<String> bankCodeList = new ArrayList<String>();
		try{
			BankInfoResponse bankInfoResponse = baseInfoInitManager.getRSMainBankInfo();
			if(bankInfoResponse !=null){
				List<TitanBankinfo> titanBankinfoList = new ArrayList<TitanBankinfo>();
				List<BankInfo> bankInfoList =  bankInfoResponse.getBankInfos();
				if(bankInfoList !=null && bankInfoList.size()>0){
					int k=0;
					for(int i=0;i<bankInfoList.size();i++){
						k++;
						if(bankInfoList.get(i) !=null){
							TitanBankinfo titanBankinfo = new TitanBankinfo();
							MyBeanUtil.copyBeanProperties(titanBankinfo,bankInfoList.get(i));
							titanBankinfo.setCreatetime(new Date());
							bankCodeList.add(bankInfoList.get(i).getBankcode());
							titanBankinfoList.add(titanBankinfo);
						}
						if(k==CommonConstant.INSERT_BATCH_NUM || i==bankInfoList.size()){
							saveBankInfo(titanBankinfoList);
							k=0;
							titanBankinfoList.clear();
						}
					}
				}
			}
			return bankCodeList;
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	
	/**
	 * 批量插入银行信息
	 * @param titanBankinfos
	 * @return
	 * @throws Exception
	 * @author fangdaikang
	 */
	private int saveBankInfo(List<TitanBankinfo> titanBankinfos) throws Exception{
		try{
			return titanBankinfoDao.insertBatch(titanBankinfos);
		}catch(Exception e){
			throw new Exception(e);
		}
		
	}
	
	/**
	 * 获取城市编码
	 * @return
	 * @author fangdaikang
	 * @throws Exception 
	 */
	private List<String> getCityCode() throws Exception{
		try{
			//保存城市编码
			List<String> cityCodeList = new ArrayList<String>();
			CityInfoResponse rsProvinceInfo = baseInfoInitManager.getRSProvinceInfo();
			//省级信息不为空，则遍历省
			if (rsProvinceInfo != null && rsProvinceInfo.getCityInfos() != null) {
				List<CityInfo> rsProvinceList = rsProvinceInfo.getCityInfos();
				if(rsProvinceList !=null && rsProvinceList.size()>0){
					List<String>  municipalityList = getMunicipality();
					//遍历省
					for(int i = 0;i<rsProvinceList.size();i++){
						CityInfo provinceInfo = rsProvinceList.get(i);
						if(provinceInfo !=null){
							//将直辖市的编码也加入市区编码中,否则查询省下面的市的编码
							if(municipalityList.contains(provinceInfo.getCityname())){
								cityCodeList.add(provinceInfo.getCitycode());
							}else if(!CommonConstant.COUNTRY_CODE.equals(provinceInfo.getCitycode())){
								//获取城市信息
								CityInfoResponse rsCityInfo = baseInfoInitManager.getRSCityInfo(provinceInfo.getCitycode());
								if(rsCityInfo !=null){
									List<CityInfo> cityInfoList = rsCityInfo.getCityInfos();
									if(cityInfoList !=null && cityInfoList.size()>0){
										//遍历城市信息
										for(int j=0;j<cityInfoList.size();j++){
											CityInfo CityInfo = cityInfoList.get(j);
											if(CityInfo !=null){
												cityCodeList.add(CityInfo.getCitycode());
											}
										}
									}
								}
							}
						}
					}
				}
			}
			return cityCodeList;
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	private List<String> getMunicipality(){
		List<String> municipality = new ArrayList<String>();
		municipality.add(MunicipalityEnum.BEIJING.cityMsg);
		municipality.add( MunicipalityEnum.TIANJING.cityMsg);
		municipality.add(MunicipalityEnum.CHONGQING.cityMsg);
		municipality.add(MunicipalityEnum.SHANGHAI.cityMsg);
		return municipality;
	}
	
}
