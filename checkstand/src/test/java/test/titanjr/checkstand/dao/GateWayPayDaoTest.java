package test.titanjr.checkstand.dao;

import com.titanjr.checkstand.dao.OrderPayRequestDao;
import com.titanjr.checkstand.dto.OrderPayRequestDTO;
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
    private OrderPayRequestDao gateWayPayDao;

    @Test
    public void testBatchSaveRequest(){
        List<OrderPayRequestDTO> gateWayPayDTOList = new ArrayList<OrderPayRequestDTO>();

        int res = gateWayPayDao.batchSaveGateWayPayDTO(gateWayPayDTOList);
        System.out.println(res);
    }

}
