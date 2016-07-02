package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

public enum ROPErrorEnum {
	
	ERROR_S15("S15","获取供应商信息失败"), ERROR_S25("S25","供应商没有返回数据"),
	ERROR_S28("S28","请求或响应数据超长"),ERROR_S26("S26","无法连接到远程服务器"),ERROR_S27("S27","供应商连接被过早关闭");

    public String code;
    public String msg;

    private ROPErrorEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ROPErrorEnum getROPErrorEnumByCode(String code){
    	if(StringUtil.isValidString(code)){
    		for(ROPErrorEnum ropErrorEnum :ROPErrorEnum.values()){
    			if(code.equals(ropErrorEnum.code)){
    				return ropErrorEnum;
    			}
    		}
    	}
    	return null;
    }
    
}
