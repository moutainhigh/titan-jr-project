package com.fangcang.titanjr.rs.manager.impl;

import com.Rop.api.ApiException;
import com.Rop.api.DefaultRopClient;
import com.Rop.api.RopRequest;
import com.Rop.api.RopResponse;

public class ROPClient {
	//融数调用构造的client
    public DefaultRopClient ropClient = null;
    public String sessionKey = null;
    
    public  <T extends RopRequest<RopResponse>>  RopResponse execute(T r) throws ApiException{
    	return ropClient.execute(r, sessionKey);
    	
    }
}
