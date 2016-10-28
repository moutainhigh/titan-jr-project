package test.fangcang.titanjr.rs.invoker.loancredit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 主营业务信息
 */
public class MainBusinessData implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3714090607195833251L;
	//主要产品/服务
    private String mainProductsOrService;
    //年销售规模
    private String mainAnnualSale;
    //占总销售额比例
    private String mainSaleProportion;
    
	public String getMainProductsOrService() {
		return mainProductsOrService;
	}
	public void setMainProductsOrService(String mainProductsOrService) {
		this.mainProductsOrService = mainProductsOrService;
	}
	
	public String getMainAnnualSale() {
		return mainAnnualSale;
	}
	public void setMainAnnualSale(String mainAnnualSale) {
		this.mainAnnualSale = mainAnnualSale;
	}
	public String getMainSaleProportion() {
		return mainSaleProportion;
	}
	public void setMainSaleProportion(String mainSaleProportion) {
		this.mainSaleProportion = mainSaleProportion;
	}
    
}
