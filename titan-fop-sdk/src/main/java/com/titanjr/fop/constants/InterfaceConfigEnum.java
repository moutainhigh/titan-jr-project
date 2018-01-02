package com.titanjr.fop.constants;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public enum InterfaceConfigEnum {

    SESSION_GET("ruixue.external.session.get", "获取session接口"),
    BALANCE_GETLIST("ruixue.wheatfield.balance.getlist", "查询虚拟账户余额列表"),
    COMPANY_CREATE("ruixue.wheatfield.enterprise.entityaccountopt", "企业用户开户"),
    PERSON_OPERATE("ruixue.wheatfield.person.accountopr", "个人用户操作"),
    FREEZE_BALANCE("ruixue.wheatfield.order.service.authcodeservice","冻结资金获取授权码");

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
}
