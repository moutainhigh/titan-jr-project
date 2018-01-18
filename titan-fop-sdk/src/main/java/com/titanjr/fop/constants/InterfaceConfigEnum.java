package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public enum InterfaceConfigEnum {

    SESSION_GET("ruixue.external.session.get", "获取session接口"),
    BALANCE_GETLIST("ruixue.wheatfield.balance.getlist", "查询虚拟账户余额列表"),
    ORDER_OPERATE("ruixue.wheatfield.order.oper", "付款单操作"),
    REFUND_ORDER_OPERATE("ruixue.wheatfield.order.service.returngoods", "退款单操作"),
    COMPANY_CREATE("ruixue.wheatfield.enterprise.entityaccountopt", "企业用户开户"),
    PERSON_OPERATE("ruixue.wheatfield.person.accountopr", "个人用户操作"),
    FREEZE_BALANCE("ruixue.wheatfield.order.service.authcodeservice","冻结资金获取授权码"),
    UNFREEZE_BALANCE("ruixue.wheatfield.order.service.thawauthcode","冻结资金获取授权码"),
    ORDERN_QUERY("ruixue.wheatfield.ordern.query","订单查询");



    private String key;
    private String desc;

    private InterfaceConfigEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static InterfaceConfigEnum getURlConfigByKey(String key) {
        for (InterfaceConfigEnum configEnum : InterfaceConfigEnum.values()) {
            if (configEnum.key.equals(key)) {
                return configEnum;
            }
        }

        return null;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
