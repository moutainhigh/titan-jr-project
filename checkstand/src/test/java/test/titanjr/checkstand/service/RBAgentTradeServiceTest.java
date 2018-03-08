package test.titanjr.checkstand.service;

import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.service.RBAgentTradeService;
import org.junit.Test;
import test.titanjr.checkstand.GenericTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoshan on 2018/3/8.
 */
public class RBAgentTradeServiceTest extends GenericTest {

    @Resource
    private RBAgentTradeService rbAgentTradeService;

    @Test
    public void testAgentDownload(){
        RBAgentDownloadRequest rbAgentDownloadRequest = new RBAgentDownloadRequest();
        rbAgentDownloadRequest.setMerchant_id("100000001301858");
        rbAgentDownloadRequest.setTradeDate("20180307");
        rbAgentDownloadRequest.setRequestType(RequestTypeEnum.AGENT_DOWNLOAD.getKey());

        RSResponse response = rbAgentTradeService.agentDownload(rbAgentDownloadRequest);
    }

}
