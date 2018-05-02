package test.titanjr.checkstand.service;

import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.request.TLGatewayPayDownloadRequest;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.service.AccountDownloadService;
import com.titanjr.checkstand.service.RBAgentTradeService;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.titanjr.checkstand.GenericTest;

import javax.annotation.Resource;

/**
 * Created by zhaoshan on 2018/3/8.
 */
public class RBAgentTradeServiceTest extends GenericTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RBAgentTradeServiceTest.class);

    @Resource
    private RBAgentTradeService rbAgentTradeService;
	
	@Resource
	private AccountDownloadService accountDownloadService;
	
	
	@Test
    public void testRBAccountDownload(){
        RBAgentDownloadRequest rbAgentDownloadRequest = new RBAgentDownloadRequest();
        rbAgentDownloadRequest.setMerchant_id("100000001301858");
        rbAgentDownloadRequest.setTradeDate("20180307");
        rbAgentDownloadRequest.setRequestType(RequestTypeEnum.AGENT_DOWNLOAD.getKey());

        RSResponse response = accountDownloadService.rbAccountDownload(rbAgentDownloadRequest);
        logger.info("response========>>errCode={}，errMsg={}", response.getErrCode(), response.getErrMsg());
    }
    
    @Test
    public void testGatewayDownload(){
    	TLGatewayPayDownloadRequest gatewayPayDownloadRequest = new TLGatewayPayDownloadRequest();
    	gatewayPayDownloadRequest.setMchtCd(SysConstant.TL_NETBANK_MERCHANT);
    	gatewayPayDownloadRequest.setSettleDate("2018-03-14");
    	gatewayPayDownloadRequest.setRequestType(RequestTypeEnum.GATEWAY_DOWNLOAD.getKey());

        RSResponse response = accountDownloadService.tlGatewayPayDownload(gatewayPayDownloadRequest);
        logger.info("response========>>errCode={}，errMsg={}", response.getErrCode(), response.getErrMsg());
    }

}
