package test.fangcang.titanjr.service;

import com.fangcang.finance.remote.FinanceSearchRemote;
import com.fangcang.security.domain.Role;
import com.fangcang.security.domain.User;
import com.fangcang.security.facade.RoleFacade;
import com.fangcang.security.facade.UserFacade;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.fangcang.titanjr.SpringTest;

/**
 * Created by zhaoshan on 2016/5/16.
 */
public class FangcangSecurityTest  extends SpringTest {

    @Autowired
    HessianProxyBeanFactory hessianProxyBeanFactory ;

    @Test
    public void testCasUserFacade(){
        UserFacade userFacade = hessianProxyBeanFactory.getHessianProxyBean(
                UserFacade.class, ProxyFactoryConstants.securityUrl + "userFacade");
        User user = userFacade.getUserById(23415);
        System.out.println(user);
    }

    @Test
    public void testCasRoleFacade(){
        RoleFacade roleFacade = hessianProxyBeanFactory.getHessianProxyBean(
                RoleFacade.class, ProxyFactoryConstants.securityUrl + "roleFacade");
        Role role = roleFacade.getRoleById(1301);

        UserFacade userFacade = hessianProxyBeanFactory.getHessianProxyBean(
                UserFacade.class, ProxyFactoryConstants.securityUrl + "userFacade");
        User user = userFacade.getUserById(23415);
        Role[] roles = new Role[]{role};
        userFacade.addRolesToUser(user,roles);

    }

}
