/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLAgentTradeRequest.java
 * @author Jerry
 * @date 2017年12月25日 下午2:54:51  
 */
package com.titanjr.checkstand.request;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.dto.TLAgentInfoRequestDTO;

/**
 * @author Jerry
 * @date 2017年12月25日 下午2:54:51  
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class TLAgentTradeRequest {
	
	private TLAgentInfoRequestDTO INFO;
	private List trxData;
	/**
	 * 请求类型，内部使用，非渠道需要字段  @see RequestTypeEnum
	 */
	@NotBlank
    private String requestType;
	
	public TLAgentInfoRequestDTO getINFO() {
		return INFO;
	}
	public void setINFO(TLAgentInfoRequestDTO iNFO) {
		INFO = iNFO;
	}
	public List getTrxData() {
		return trxData;
	}

	public void setTrxData(List trxData) {
		this.trxData = trxData;
	}
	
	public void addTrx(Object o) {
		if(o==null) {
			return ;
		}
		if(trxData==null) {
			trxData = new ArrayList();
		}
		trxData.add(o);
	}
	
	public Object findObj(Class clzx) {
		if(trxData == null) {
			return null;
		}
		for(Object ox : trxData) {
			if(clzx.isInstance(ox)) return ox;
		}
		return trxObj();
	}
	
	public Object trxObj() {
		if(trxData==null) {
			return null;
		}
		if(!trxData.isEmpty()) {
			return trxData.iterator().next();
		}
		return null;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
