package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

public enum PayerTypeEnum {
	
	  B2B_PUS("1","B2B交易平台"),
	  B2B_GDP("2","GDP"),
	  SUPPLY_FINACIAL("3","财务供应商"),
	  SUPPLY_UNION("4","联盟供应商付款"),
	  ALLIANCE("5","商家联盟"),
	  MOBILE("6","移动端"),
	  RECHARGE("7","充值");

	  public String key;
	  
	  public String msg;
	  
	  private PayerTypeEnum(String key,String msg){
		  this.key =key;
		  this.msg=msg;
	  }
	  
	  public static PayerTypeEnum getPayerTypeEnumByKey(String key){
		  if(!StringUtil.isValidString(key)){
			  return null;
		  }
		  
		  for(PayerTypeEnum payerTypeEnum : PayerTypeEnum.values()){
			  if(payerTypeEnum.key.equals(key)){
				  return payerTypeEnum;
			  }
		  }
		  return null;
	  }
	  
	  public static Integer getPaySource(String key){
		  if(RECHARGE.key.equals(key)){//充值收银台
			  return 5;
		  }else if( B2B_PUS.key.equals(key) || B2B_GDP.key.equals(key)){
			  return 1;
		  }else if(SUPPLY_FINACIAL.key.equals(key)|| SUPPLY_UNION.key.equals(key)){
			  return 2;
		  }else if(ALLIANCE.key.equals(key)){
			  return 3;
		  }
		  return null;
	  }
	  
	  public boolean isRechargeCashDesk(){
		  return RECHARGE.key.equals(this.key);
	  }

	  
	  public boolean isUserId()
	  {
		  return RECHARGE.key.equals(this.key);
	  }
	  
	  public boolean isReicveMerchantCode(){//接收方为机构编码的
		  return B2B_PUS.key.equals(this.key) || B2B_GDP.key.equals(this.key)||SUPPLY_UNION.key.equals(this.key);
	  }
	  
	  public boolean isFcUserId(){
		  return SUPPLY_FINACIAL.key.equals(this.key)||SUPPLY_UNION.key.equals(this.key);
	  }
	  
	  public boolean isMustPayerment(){
		
		return SUPPLY_FINACIAL.key.equals(this.key)||SUPPLY_UNION.key.equals(this.key);
	  }
	
	  public boolean isMustPayeement(){
		
		return SUPPLY_UNION.key.equals(this.key)|| B2B_PUS.key.equals(this.key) || B2B_GDP.key.equals(this.key);
	  }
	
    
	  public boolean isB2BPayment(){
		
		return B2B_PUS.key.equals(this.key) || B2B_GDP.key.equals(this.key);
	  }
}
