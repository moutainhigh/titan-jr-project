package com.fangcang.titanjr.rs.util;

import java.util.ArrayList;
import java.util.List;

import com.fangcang.titanjr.rs.dto.OrderTransferFlow;
import com.fangcang.titanjr.rs.response.AccountFlowResponse;
import com.fangcang.titanjr.rs.response.OrderInfoResponse;
import com.fangcang.titanjr.rs.response.OrderTransferFlowResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class MyConvertXmlToObject {
	private static XStream xstream = new XStream(){
        @Override
        protected MapperWrapper wrapMapper(MapperWrapper next) {
            return new MapperWrapper(next) {
                @Override
                public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                    if (definedIn == Object.class) {
                        try {
                            return this.realClass(fieldName) != null;
                        } catch(Exception e) {
                            return false;
                        }
                    } else {
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                }
            };
        }
    };
    static {

//        xstream.registerConverter(new CtripDateConverter());
//        xstream.registerConverter(new EmptyDoubleConverter());
//        xstream.registerConverter(new EmptyIntegerConverter());
//        xstream.registerConverter(new EmptyLongConverter());

		//1.设置要处理的类。
		List<Class> clsList = new ArrayList<Class>();
        clsList.add(OrderInfoResponse.class);
        clsList.add(AccountFlowResponse.class);
        clsList.add(OrderTransferFlow.class);
        Class[] classes = new Class[clsList.size()];
		clsList.toArray(classes);
		xstream.processAnnotations(classes);
        xstream.autodetectAnnotations(true);
	}
    
    public static Object convertXml2Object(String requestXml){
		Object result = null;
		try{
			result = xstream.fromXML(requestXml);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return result;
	}

    
    
}
