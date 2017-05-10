package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public enum CashierItemTypeEnum {

	B2B_ITEM("1", "企业网银"), B2C_ITEM("2", "个人网银"), CREDIT_ITEM("3", "信用卡"), BALANCE_ITEM(
			"4", "账户余额"), FINANCING_ITEM("5", "理财"), MOBILE_ITEM("6", "移动端"),WX_PUBLIC("8", "微信公众号支付"),QR_ITEM("9", "第三方支付") ,LOAN("10", "贷款支付");

	public String itemCode;
	public String itemName;

	public String getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public static CashierItemTypeEnum getCashierItemTypeEnumByKey(String key) {
		if (!StringUtil.isValidString(key)) {
			return null;
		}

		for (CashierItemTypeEnum cashierItemTypeEnum : CashierItemTypeEnum
				.values()) {
			if (cashierItemTypeEnum.itemCode.equals(key)) {
				return cashierItemTypeEnum;
			}
		}
		return null;
	}

	private CashierItemTypeEnum(String itemCode, String itemName) {
		this.itemCode = itemCode;
		this.itemName = itemName;
	}
}
