package test.fangcang.titanjr.facade;

import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.fangcang.GenericTest;

/**
 * Created by zhaoshan on 2016/4/20.
 */
public class MerchantFacadeTest extends GenericTest{
//    private static MerchantFacade merchantFacade = null;

//    @Before
//    public void init(){
//        merchantFacade = (MerchantFacade)cfx.getBean("merchantFacade");
//    }

    @Test
    public void testQueryMerchant(){
//        merchantFacade = (MerchantFacade)cfx.getBean("merchantFacade");
//        MerchantDetailQueryDTO queryDTO=new MerchantDetailQueryDTO();
//        queryDTO.setMerchantCode("M10000001");
//        MerchantResponseDTO response = merchantFacade.queryMerchantDetail(queryDTO);
//        System.out.println(response);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cfx = new ClassPathXmlApplicationContext("classpath:applicationContext-hessian.xml");
//        merchantFacade = (MerchantFacade)cfx.getBean("merchantFacade");
//        MerchantDetailQueryDTO queryDTO=new MerchantDetailQueryDTO();
//        queryDTO.setMerchantCode("M10000001");
//        MerchantResponseDTO response = merchantFacade.queryMerchantDetail(queryDTO);
//        System.out.println(response);

        HessianProxyBeanFactory commonHessianBeanFactory = (HessianProxyBeanFactory)cfx.getBean("commonHessianBeanFactory");
        //commonHessianBeanFactory.getMerchantFacade();
//        MerchantFacade facade = commonHessianBeanFactory.getHessianProxyBean(MerchantFacade.class,
//                ProxyFactoryConstants.merchantServerUrl  + "merchantFacade");
//        MerchantDetailQueryDTO queryDTO=new MerchantDetailQueryDTO();
//        queryDTO.setMerchantCode("M10000001");
//        MerchantResponseDTO response = facade.queryMerchantDetail(queryDTO);
//        System.out.println(response);
    }

}
