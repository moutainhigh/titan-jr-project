package test.fangcang.titanjr.service;

import com.fangcang.titanjr.common.enums.FinancialRoleEnum;
import com.fangcang.titanjr.dao.TitanRoleDao;
import com.fangcang.titanjr.dto.response.OrganRoleInitResponse;
import com.fangcang.titanjr.entity.TitanRole;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/10.
 */
public class TitanFinancialBaseInfoServiceTest extends SpringTest {

    @Resource
    TitanFinancialBaseInfoService titanFinancialBaseInfoService;

    @Resource
    TitanRoleDao titanRoleDao;

    @Test
    public void initFinancialOrganRoleTest(){
        OrganRoleInitResponse roleInitResponse = titanFinancialBaseInfoService.initFinancialOrganRole();

//        List<TitanRole> roleList = new ArrayList<TitanRole>();
//        for (FinancialRoleEnum roleEnum : FinancialRoleEnum.values()){
//            TitanRole role = new TitanRole();
//            role.setFcroleid(RSInvokeConstant.defaultRoleId);
//            role.setRolecode(roleEnum.roleCode);
//            role.setRolename(roleEnum.roleName);
//            role.setIsactive(1);
//            role.setRoleremark("");
//            role.setCreator("system");
//            role.setCreatetime(new Date());
//            roleList.add(role);
//        }
//        titanRoleDao.insert(roleList);
    }

}
