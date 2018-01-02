/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLAgentPayResponse.java
 * @author Jerry
 * @date 2017年12月26日 上午10:17:42  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.titanjr.checkstand.dto.TLAgentInfoRequestDTO;
import com.titanjr.checkstand.dto.TLAgentInfoResponseDTO;
import com.titanjr.checkstand.request.TLAgentTradeRequest;

/**
 * @author Jerry
 * @date 2017年12月26日 上午10:17:42  
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class TLAgentTradeResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -1420314296313802286L;
	
	private TLAgentInfoResponseDTO INFO;
	private List trxData;
	
	public static TLAgentTradeResponse packRsp(TLAgentTradeRequest req, String errcode,String strMsg) {
		return packRsp(req.getINFO(),errcode,strMsg);
	}
	public static TLAgentTradeResponse packRsp(TLAgentInfoRequestDTO reqInf, String errcode,String strMsg) {
		TLAgentTradeResponse rsp = new TLAgentTradeResponse();
		rsp.setINFO(TLAgentInfoResponseDTO. packRsp(reqInf, errcode, strMsg));
		return rsp;
	}
	public TLAgentInfoResponseDTO getINFO() {
		return INFO;
	}
	public void setINFO(TLAgentInfoResponseDTO iNFO) {
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
			trxData=new ArrayList();
		}
		trxData.add(o);
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
	
	public Object findObj(Class clzx) {
		if(trxData==null) {
			return null;
		}
		for(Object ox:trxData) {
			if(clzx.isInstance(ox)) return ox;
		}
		return trxObj();
	}

}
