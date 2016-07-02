package com.fangcang.titanjr.common.enums;

public enum ImgSizeEnum {
	SIZE_10(10,132,89,"10号图尺寸"),SIZE_50(50,812,542,"50号图尺寸");
	
	private int index;
	private int width;
	private int height;
	private String des;
	
	private ImgSizeEnum(int index,int width,int height,String des){
		this.index = index;
		this.width = width;
		this.height = height;
		this.des = des;
	}
	
	public ImgSizeEnum getImgSizeEnumByIndex(int index){
		ImgSizeEnum dest = null;
		for(ImgSizeEnum item : ImgSizeEnum.values()) {
			if(item.getIndex() == index) {
				dest = item;
				break;
			}
		}
		return dest;
	}

	public int getIndex() {
		return index;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getDes() {
		return des;
	}
	
}
