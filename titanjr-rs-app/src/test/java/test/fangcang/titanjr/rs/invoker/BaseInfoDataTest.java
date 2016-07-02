package test.fangcang.titanjr.rs.invoker;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.rs.dto.BankInfo;
import com.fangcang.titanjr.rs.dto.CityInfo;
import com.fangcang.titanjr.rs.manager.BaseInfoInitManager;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.response.BankInfoResponse;
import com.fangcang.titanjr.rs.response.CityInfoResponse;

import test.fangcang.titanjr.rs.base.GenericTest;

public class BaseInfoDataTest extends GenericTest{
	 private static BaseInfoInitManager baseInfoInitManager = null;

	    @Before
	    public void init(){
	    	baseInfoInitManager = (BaseInfoInitManager)cfx.getBean("baseInfoInitManager");
	    }
	    
//	    @Test
	    public void testGetRSProvinceInfo(){
	    	CityInfoResponse cityInfoResponse =  baseInfoInitManager.getRSProvinceInfo();
	    	if(cityInfoResponse !=null){
	    		List<CityInfo> cityInfos = cityInfoResponse.getCityInfos();
	    		for(int i=0;i<cityInfos.size();i++){
	    			CityInfo cityInfo = cityInfos.get(i);
	    			System.out.println(cityInfo.getCityname()+":"+cityInfo.getCitycode()+"--"+cityInfo.getParentcode()+"---"+cityInfo.getDatatype());
	    		}
	    		
	    	}
	    }
	    
//	    @Test
	    public void testGetRSCityInfo(){
	    	CityInfoResponse cityInfoResponse =  baseInfoInitManager.getRSCityInfo("00");
	    	if(cityInfoResponse !=null){
	    		List<CityInfo> cityInfos = cityInfoResponse.getCityInfos();
	    		for(int i=0;i<cityInfos.size();i++){
	    			CityInfo cityInfo = cityInfos.get(i);
	    			System.out.println(cityInfo.getCityname()+":"+cityInfo.getCitycode()+"--"+cityInfo.getParentcode()+"---"+cityInfo.getDatatype());
	    		}
	    		
	    	}
	    }
	    
	    @Test
	    public void testGetRSMainBankInfo(){
	    	BankInfoResponse bankInfoResponse = baseInfoInitManager.getRSMainBankInfo();
	    	if(bankInfoResponse !=null){
	    		List<BankInfo> bankInfos = bankInfoResponse.getBankInfos();
	    		for(int i=0;i<bankInfos.size();i++){
	    			BankInfo bankInfo = bankInfos.get(i);
	    			System.out.println(bankInfo.getBankname()+":"+bankInfo.getBankcode()+"--"+bankInfo.getBankcity()+"---"+bankInfo.getBanktype());
	    		}
	    		
	    	}
	    }
	    
//	    @Test
	    public void testGetRSBranchBankInfo(){
	    	BankInfoResponse bankInfoResponse = baseInfoInitManager.getRSBranchBankInfo("102", "1000");
	    	if(bankInfoResponse !=null){
	    		List<BankInfo> bankInfos = bankInfoResponse.getBankInfos();
	    		System.out.println("----------"+bankInfos.size());
	    		for(int i=0;i<bankInfos.size();i++){
	    			BankInfo bankInfo = bankInfos.get(i);
	    			System.out.println(bankInfo.getBankname()+":"+bankInfo.getBankcode()+"--"+bankInfo.getBankcity()+"---"+bankInfo.getBanktype());
	    		}
	    	}
	    }
}
