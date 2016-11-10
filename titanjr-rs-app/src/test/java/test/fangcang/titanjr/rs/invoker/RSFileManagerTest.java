package test.fangcang.titanjr.rs.invoker;

import java.text.ParseException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Before;
import org.junit.Test;

import test.fangcang.titanjr.rs.base.GenericTest;

import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.GetFileUrlRequest;
import com.fangcang.titanjr.rs.request.GetUrlKeyRequest;
import com.fangcang.titanjr.rs.response.GetFileUrlResponse;
import com.fangcang.titanjr.rs.response.GetUrlKeyResponse;

public class RSFileManagerTest extends GenericTest{
	private RSFileManager rsFileManager;
	@Before
	public void init(){
		rsFileManager = (RSFileManager)cfx.getBean("rsFileManager");
	}
	@Test
	public void testgetUrlKey(){
		GetUrlKeyRequest getUrlKeyRequest = new GetUrlKeyRequest();
		getUrlKeyRequest.setType(58);
		try {
			getUrlKeyRequest.setInvoice_date(DateUtil.sdf.parse("2016-08-13"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GetUrlKeyResponse response = rsFileManager.getUrlKey(getUrlKeyRequest);
		System.out.println("GetUrlKeyResponse:"+ToStringBuilder.reflectionToString(response));
		/***********************/
		GetFileUrlRequest getFileUrlRequest = new GetFileUrlRequest();
		getFileUrlRequest.setUrlKey(response.gettFileUrlList().get(0).getUrl_key());
		GetFileUrlResponse getFileUrlResponse = rsFileManager.getFileUrl(getFileUrlRequest);
		System.out.println("getFileUrlResponse:"+getFileUrlResponse.getFile_url());
	}
	
	//@Test
	public void testgetFileUrl(){
		GetFileUrlRequest getFileUrlRequest = new GetFileUrlRequest();
		getFileUrlRequest.setUrlKey("96d3d572-4df4-4ac5-89ab-4f91c22bbd1f");
		GetFileUrlResponse response = rsFileManager.getFileUrl(getFileUrlRequest);
		System.out.println("GetFileUrlResponse:"+response.getFile_url());
		// <file_url>http://ruixuesoftprivatefilestest.oss-cn-beijing.aliyuncs.com/7e59d76464844f779db10eb953e55dbc/58de0e35-53c8-49db-9990-78eaa3618fb2.csv?Expires=1471231095&amp;OSSAccessKeyId=1Mq5RScP2vBwHO6A&amp;Signature=blsyT8m2%2FdgJ4D%2FvsxmlT2mHXAQ%3D</file_url>
	}
	
	 
}
