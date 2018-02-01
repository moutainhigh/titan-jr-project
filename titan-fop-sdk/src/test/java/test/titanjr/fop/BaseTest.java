package test.titanjr.fop;

import org.junit.Before;

import com.titanjr.fop.api.DefaultFopClient;

public class BaseTest {
	//正式环境需要对titan-fop-server进行nginx配置，实际请求地址是调用时获取
    private static String ropUrl = "http://192.168.0.89:8040/titan-fop-server/";
    private static String appKey = "93A6626A-C082-4D25-B496-EA9CC6E90EDB";
    private static String appSecret = "6461B23C-3ABE-4BE2-8E2C-D3FF4B2F5415";
    protected DefaultFopClient fopClient = null;

    @Before
    public void initFopClient() {
        fopClient = new DefaultFopClient(ropUrl, appKey, appSecret);
    }
}
