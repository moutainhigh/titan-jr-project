package test.titanjr.fop;

import org.junit.Test;

import com.fangcang.titanjr.common.util.Tools;
import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.request.WheatfieldEnterpriseUpdatecompanyinfoRequest;
import com.titanjr.fop.request.WheatfieldPersonAccountoprRequest;
import com.titanjr.fop.response.WheatfieldEnterpriseUpdatecompanyinfoResponse;
import com.titanjr.fop.response.WheatfieldPersonAccountoprResponse;

public class OrgTest extends BaseTest {
	
	
	//@Test
	public void updatePersonOrg(){
		
		WheatfieldPersonAccountoprRequest personAccountoprRequest = new WheatfieldPersonAccountoprRequest();
		personAccountoprRequest.setOpertype("2");
		personAccountoprRequest.setUserid("TJM10000140");
		personAccountoprRequest.setUsername("luoqinglong222222");
		personAccountoprRequest.setCertificatenumber("111111");
		
		try {
			WheatfieldPersonAccountoprResponse response = fopClient.execute(personAccountoprRequest);
			System.out.println(Tools.gsonToString(response));
		
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void updateEnterpriseOrg(){
		WheatfieldEnterpriseUpdatecompanyinfoRequest enterpriseUpdateRequest = new WheatfieldEnterpriseUpdatecompanyinfoRequest();
		
		enterpriseUpdateRequest.setOpertype("1");
		enterpriseUpdateRequest.setUserid("TJM10000090");
		enterpriseUpdateRequest.setConnect("connect1111");
		enterpriseUpdateRequest.setUsername("username11111");
		enterpriseUpdateRequest.setCorporatename("luoqinglongname");
		enterpriseUpdateRequest.setBuslince("1111");
		
		
		try {
			WheatfieldEnterpriseUpdatecompanyinfoResponse response = fopClient.execute(enterpriseUpdateRequest);
			System.out.println(Tools.gsonToString(response));
		
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
}
