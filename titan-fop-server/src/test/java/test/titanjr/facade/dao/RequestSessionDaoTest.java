package test.titanjr.facade.dao;

import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.titanjr.fop.dao.RequestSessionDao;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.entity.RequestSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.titanjr.facade.SpringTest;

import javax.annotation.Resource;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public class RequestSessionDaoTest extends SpringTest {

    @Autowired
    RequestSessionDao requestSessionDao;

    @Autowired
    TitanAccountDao titanAccountDao;

    @Test
    public void testSaveSession(){
        RequestSession requestSession = new RequestSession();
        requestSessionDao.saveRequestSession(requestSession);
    }


    @Test
    public void testGetAccountBalance(){
        AccountBalanceRequest balanceRequest = new AccountBalanceRequest();
        titanAccountDao.queryAccountBalanceList(balanceRequest);
    }

}
