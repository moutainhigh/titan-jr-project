package test.titanjr.facade.dao;

import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanUnFundFreezereqParam;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.dao.TitanOrderDao;
import com.titanjr.fop.dto.BalanceQueryDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.titanjr.facade.SpringTest;

import java.util.List;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class OrderDaoTest extends SpringTest {

    @Autowired
    TitanOrderDao titanOrderDao;

    @Autowired
    TitanAccountDao titanAccountDao;
    @Test
    public void testQueryUnFreeze(){
        TitanUnFundFreezereqParam unFundFreezereqParam = new TitanUnFundFreezereqParam();
        unFundFreezereqParam.setRequestno("tjrf160625171842103");
        List<TitanFundUnFreezereq> unFreezereqs = titanAccountDao.queryUnFreezeRequest(unFundFreezereqParam);
    }
}
