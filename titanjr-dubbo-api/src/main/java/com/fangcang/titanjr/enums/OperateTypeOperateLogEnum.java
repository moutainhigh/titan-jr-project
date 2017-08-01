package com.fangcang.titanjr.enums;


/***
 * 操作日志枚举,类格式：表字段+表名
 * @author luoqinglong
 *
 */
public enum OperateTypeOperateLogEnum {
	CANCEL_ORG_BIND(1001,"取消机构绑定");
	private Integer operateType;
	private String des;
	
	private OperateTypeOperateLogEnum(Integer operateType,String des){
		
		this.operateType = operateType;
		this.des = des;
	}

	public static OperateTypeOperateLogEnum  getEnumByOperateType(Integer operateType){
		for(OperateTypeOperateLogEnum item : OperateTypeOperateLogEnum.values()) {
			if(item.operateType == operateType) {
				return item;
			}
		}
		return null;
	}
	
	public Integer getOperateType() {
		return operateType;
	}

	public String getDes() {
		return des;
	}
	
}
