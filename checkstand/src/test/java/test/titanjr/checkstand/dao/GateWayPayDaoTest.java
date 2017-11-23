package test.titanjr.checkstand.dao;

import com.titanjr.checkstand.dao.GateWayPayDao;
import com.titanjr.checkstand.dto.GateWayPayDTO;
import org.junit.Test;
import test.titanjr.checkstand.GenericTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2017/11/22.
 */
public class GateWayPayDaoTest  extends GenericTest{

    @Resource
    private GateWayPayDao gateWayPayDao;

    @Test
    public void testBatchSaveRequest(){
        List<GateWayPayDTO> gateWayPayDTOList = new ArrayList<GateWayPayDTO>();

        int res = gateWayPayDao.batchSaveGateWayPayDTO(gateWayPayDTOList);
        System.out.println(res);
    }

}
