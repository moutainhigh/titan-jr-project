package com.fangcang.titanjr.rs.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fangcang.titanjr.rs.dto.OrderTransferFlow;
import com.fangcang.titanjr.rs.response.AccountFlowResponse;
import com.fangcang.titanjr.rs.response.OrderInfoResponse;
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
    
    /** 
     * xml转map 不带根节点
     * @param xmlStr 
     * @return 
     * @throws DocumentException 
     */  
    public static Map<String,Object> xml2map(String xmlStr) throws DocumentException {  
        Document doc = DocumentHelper.parseText(xmlStr);  
        Element root = doc.getRootElement();  
        Map<String, Object> map = (Map<String, Object>) xml2map(root);  
        if(root.elements().size()==0 && root.attributes().size()==0){  
            return map;  
        }  
        
        return map;  
    }  
    
    private static Map<String,Object> xml2map(Element e) {  
        Map<String,Object> map = new LinkedHashMap<String,Object>();  
        List list = e.elements();  
        if (list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                Element iter = (Element) list.get(i);  
                List mapList = new ArrayList();  
  
                if (iter.elements().size() > 0) {  
                    Map<String,Object> m = xml2map(iter);  
                    if (map.get(iter.getName()) != null) {  
                        Object obj = map.get(iter.getName());  
                        if (!(obj instanceof List)) {  
                            mapList = new ArrayList();  
                            mapList.add(obj);  
                            mapList.add(m);  
                        }  
                        if (obj instanceof List) {  
                            mapList = (List) obj;  
                            mapList.add(m);  
                        }  
                        map.put(iter.getName(), mapList);  
                    } else  
                        map.put(iter.getName(), m);  
                } else {  
                    if (map.get(iter.getName()) != null) {  
                        Object obj = map.get(iter.getName());  
                        if (!(obj instanceof List)) {  
                            mapList = new ArrayList();  
                            mapList.add(obj);  
                            mapList.add(iter.getText());  
                        }  
                        if (obj instanceof List) {  
                            mapList = (List) obj;  
                            mapList.add(iter.getText());  
                        }  
                        map.put(iter.getName(), mapList);  
                    } else  
                        map.put(iter.getName(), iter.getText());  
                }  
            }  
        } else  
            map.put(e.getName(), e.getText());  
        return map;  
    }  

    
    
}
