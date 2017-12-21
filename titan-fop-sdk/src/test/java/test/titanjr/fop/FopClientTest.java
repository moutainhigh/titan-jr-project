package test.titanjr.fop;

import com.titanjr.fop.api.DefaultFopClient;
import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.request.ExternalSessionGetRequest;
import com.titanjr.fop.response.ExternalSessionGetResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class FopClientTest {

    private static String ropUrl = "https://open.fangcang.com:19010/titan-fop-server";
    private static String appKey = "93A6626A-C082-4D25-B496-EA9CC6E90EDB";
    private static String appSecret = "DC368712-18A4-4290-9A58-FF995DC161DC";
    private DefaultFopClient fopClient = null;

    @Before
    public void initFopClient() {
        fopClient = new DefaultFopClient(ropUrl, appKey, appSecret);
    }

    @Test
    public void testGetSession() {

        ExternalSessionGetRequest sessionGetReq = new ExternalSessionGetRequest();

        try {
            ExternalSessionGetResponse sessionGetRsp = fopClient.execute(sessionGetReq);
            sessionGetRsp.getSession();
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
