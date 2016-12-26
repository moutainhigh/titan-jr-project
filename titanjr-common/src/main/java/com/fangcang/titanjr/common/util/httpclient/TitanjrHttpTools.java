package com.fangcang.titanjr.common.util.httpclient;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.fangcang.titanjr.common.bean.CallBackInfo;


public class TitanjrHttpTools {

	public static <T> List<NameValuePair> getCommonHttpParams(T t) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		Field[] cls =  t.getClass().getDeclaredFields();
		for(Field field : cls){
			field.setAccessible(true);  
			if(field.get(t) !=null){
				params.add(new BasicNameValuePair(field.getName(), field.get(t).toString()));
			}else{
				params.add(new BasicNameValuePair(field.getName(), null));
			}
			
		}
		return params;
	}
	
	public static CallBackInfo analyzeResponse(String info) {
		CallBackInfo callBackInfo = new CallBackInfo();
		String[] sourceStrArray = info.split("&");
		if (sourceStrArray != null && sourceStrArray.length > 0) {
			String[] code = sourceStrArray[0].split("=");
			if (null != code && code.length == 2) {
				callBackInfo.setCode(code[1]);
			}
			if (sourceStrArray.length == 2) {
				String[] msg = sourceStrArray[1].split("=");
				if (null != msg && msg.length == 2) {
					callBackInfo.setMsg(msg[1]);
				}
			}
			return callBackInfo;
		}
		return null;
	}
	
	//HttpClient的调用
	public static String confirmRefund(Object object,HttpPost httpPost) throws Exception{
		
		if(object ==null ){
			return null;
		}
		List<NameValuePair> params = TitanjrHttpTools.getCommonHttpParams(object);
	    HttpResponse resp = HttpClient.httpRequest(params,  httpPost);
	    String response = "";
		if (null != resp) {
			InputStream in = resp.getEntity().getContent();
			byte b[] = new byte[1024];
			
			int length = 0;
			if((length = in.read(b)) !=-1){
				byte d[] = new byte[length];
				System.arraycopy(b, 0, d, 0, length);
				response = new String(d , "UTF-8");
			}
			httpPost.releaseConnection();
		}
		return response;
	}
	
}
