package com.fangcang.titanjr.common.enums;
/**
 * 文件类型
 * @author luoqinglong
 * @date   2016年12月1日
 */
public enum FileTypeEnum {
	UPLOAD_FILE_73(73,"天下房仓文件上传"),ONLINE_PAY_74(74,"账户给融数钱包的对账文件(在线支付)"),ONLINE_NOT_PAY_75(75,"账户给融数钱包的对账文件(在线支付以外)"),THIRD_PAY_76(76,"账户给融数钱包的对账文件(代收付)");
	private int fileType;
	private String des;
	
	private FileTypeEnum(int fileType,String des){
		this.fileType = fileType;
		this.des = des;
	}

	public static FileTypeEnum  getEnumByFileType(int fileType){
		FileTypeEnum entity = null;
		for(FileTypeEnum item : FileTypeEnum.values()) {
			if(item.getFileType() == fileType) {
				entity = item;
				break;
			}
		}
		return entity;
	}
	
	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}
